package com.soccerfantasy.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.soccerfantasy.app.domain.TransferEntity;

@Repository
public interface TransferRepository extends JpaRepository<TransferEntity, Long> {

	List<TransferEntity> findAllByPlayer_Id(Long playerId);

}
