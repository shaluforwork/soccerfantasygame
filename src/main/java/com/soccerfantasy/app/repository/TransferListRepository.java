package com.soccerfantasy.app.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.soccerfantasy.app.domain.TransferListEntity;

@Repository
public interface TransferListRepository extends JpaRepository<TransferListEntity, Long> {

	List<TransferListEntity> findAllByTransferred(Boolean transferred);

	Optional<TransferListEntity> findByPlayer_IdAndTransferred(Long playerId, boolean b);
}
