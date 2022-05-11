package com.soccerfantasy.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.soccerfantasy.app.domain.TeamEntity;

@Repository
public interface TeamRepository extends JpaRepository<TeamEntity, Long> {

}
