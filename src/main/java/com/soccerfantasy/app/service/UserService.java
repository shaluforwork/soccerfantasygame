package com.soccerfantasy.app.service;

import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

import javax.transaction.Transactional;

import org.hibernate.exception.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.soccerfantasy.app.domain.TeamEntity;
import com.soccerfantasy.app.domain.UserEntity;
import com.soccerfantasy.app.exception.UserServiceException;
import com.soccerfantasy.app.mapping.UserMapper;
import com.soccerfantasy.app.model.request.UserRequestModel;
import com.soccerfantasy.app.model.response.ErrorMessages;
import com.soccerfantasy.app.model.response.UserResponseModel;
import com.soccerfantasy.app.repository.UserRepository;

@Service
public class UserService implements UserDetailsService {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

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
		logger.info("Inside User Service Sign up flow");
		UserEntity userEntity = userMapper.userRequestModelToUserEntity(userRequestModel);
		userEntity.setUserId(UUID.randomUUID().toString());
		userEntity.setEncryptedPassword(bCryptPasswordEncoder.encode(userRequestModel.getPassword()));
		try {
			userEntity = userRepository.save(userEntity);			
		} catch (DataIntegrityViolationException | ConstraintViolationException e) {
			logger.error("Exception occured while saving record :: {}", userRequestModel.getEmail());
			throw new UserServiceException(ErrorMessages.RECORD_ALREADY_EXISTS.getErrorMessage());
		}
		TeamEntity teamEntity = teamService.initializeTeam(userEntity);
		userEntity.setTeam(teamEntity);
		userEntity = userRepository.saveAndFlush(userEntity);
		UserResponseModel userResponseModel = userMapper.userEntityToUserResponseModel(userEntity);
		return userResponseModel;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<UserEntity> optional = userRepository.findByEmailOrUserId(username, username);

		if(optional == null || optional.get() == null) {
			logger.error("No user found for email/userId :: {}", username);
			throw new UsernameNotFoundException(username);
		}
		UserEntity userEntity = optional.get();
		return new User(userEntity.getUserId(), userEntity.getEncryptedPassword(), true, true, true, true, new ArrayList<>());
	}

	public UserEntity findUserByUserId(String userId) throws UsernameNotFoundException {
		Optional<UserEntity> optional = userRepository.findByUserId(userId);

		if(optional == null || optional.get() == null) {
			throw new UsernameNotFoundException(userId);
		}
		return optional.get();
	}
}
