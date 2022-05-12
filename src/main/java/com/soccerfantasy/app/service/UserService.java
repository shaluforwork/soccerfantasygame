package com.soccerfantasy.app.service;

import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.soccerfantasy.app.domain.TeamEntity;
import com.soccerfantasy.app.domain.UserEntity;
import com.soccerfantasy.app.mapping.UserMapper;
import com.soccerfantasy.app.model.request.UserRequestModel;
import com.soccerfantasy.app.model.response.UserResponseModel;
import com.soccerfantasy.app.repository.UserRepository;

@Service
public class UserService implements UserDetailsService {

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserMapper userMapper;

	@Autowired
	private TeamService teamService;
	
	@Transactional
	public UserResponseModel signUp(UserRequestModel userRequestModel) {
		UserEntity userEntity = userMapper.userRequestModelToUserEntity(userRequestModel);
		userEntity.setUserId(UUID.randomUUID().toString());
		userEntity.setEncryptedPassword(bCryptPasswordEncoder.encode(userRequestModel.getPassword()));
		userEntity = userRepository.save(userEntity);
		TeamEntity teamEntity = teamService.initializeTeam(userEntity);
		userEntity.setTeam(teamEntity);
		userEntity = userRepository.saveAndFlush(userEntity);
		UserResponseModel userResponseModel = userMapper.userEntityToUserResponseModel(userEntity);
		return userResponseModel;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<UserEntity> optional = userRepository.findByEmail(username);

		if(optional == null || optional.get() == null) {
			throw new UsernameNotFoundException(username);
		}
		UserEntity userEntity = optional.get();
		return new User(userEntity.getEmail(), userEntity.getEncryptedPassword(), true, true, true, true, new ArrayList<>());
	}

	public UserEntity findUserByUserId(String userId) throws UsernameNotFoundException {
		Optional<UserEntity> optional = userRepository.findByUserId(userId);

		if(optional == null || optional.get() == null) {
			throw new UsernameNotFoundException(userId);
		}
		return optional.get();
	}
}
