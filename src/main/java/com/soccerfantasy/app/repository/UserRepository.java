package com.soccerfantasy.app.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.soccerfantasy.app.domain.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {

	Optional<UserEntity> findByEmail(String email);

	Optional<UserEntity> findByUserId(String userId);

	Optional<UserEntity> findByEmailOrUserId(String username, String username2);

}
