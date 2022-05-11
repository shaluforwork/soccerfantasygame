package com.soccerfantasy.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.soccerfantasy.app.domain.PlayerEntity;

@Repository
public interface PlayerRepository extends JpaRepository<PlayerEntity, Long> {

	List<PlayerEntity> findAllByTeam_Id(Long teamId);

}
