package com.soccerfantasy.app.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.soccerfantasy.app.domain.PlayerEntity;
import com.soccerfantasy.app.domain.TransferListEntity;
import com.soccerfantasy.app.exception.TransferServiceException;
import com.soccerfantasy.app.mapping.TransferListMapper;
import com.soccerfantasy.app.model.response.ErrorMessages;
import com.soccerfantasy.app.model.response.TeamResponseModel;
import com.soccerfantasy.app.model.response.TransferListResponseModel;
import com.soccerfantasy.app.repository.TransferListRepository;
import com.soccerfantasy.app.repository.TransferRepository;

@Service
public class TransferService {

	@Autowired
	private TransferListRepository transferListRepository;

	@Autowired
	private TransferRepository transferRepository;

	@Autowired
	private TransferListMapper transferListMapper;

	@Autowired
	private PlayerService playerService;

	@Autowired
	private PlayerTransferValidationService transferValidationService;

	@Transactional
	public Boolean addOnTransferList(Long playerId) {
		boolean added = false;
		PlayerEntity playerEntity = playerService.fetchPlayerEntity(playerId);
		Optional<TransferListEntity> optionalTransferList = transferListRepository.findByPlayer_IdAndTransferred(playerId, false);
		if (optionalTransferList == null || optionalTransferList.isEmpty()) {
			TransferListEntity transferListEntity = new TransferListEntity();
			transferListEntity.setPlayer(playerEntity);
			transferListEntity.setAskingPrice(playerEntity.getMarketValue());
			transferListEntity.setTransferred(false);
			transferListRepository.save(transferListEntity);
		} else  {
			throw new TransferServiceException(ErrorMessages.RECORD_ALREADY_EXISTS.getErrorMessage());
		}
		return added;
	}

	public List<TransferListResponseModel> fetchPendingTransferList() {
		List<TransferListResponseModel> pendingTransfers = new ArrayList<TransferListResponseModel>();
		List<TransferListEntity> transferList = transferListRepository.findAllByTransferred(false);
		if (transferList == null || transferList.isEmpty()) {
			throw new TransferServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());			
		} else {
			for (TransferListEntity transferListEntity : transferList) {
				TransferListResponseModel pendingTransfer = transferListMapper.transferListEntityToTransferListResponseModel(transferListEntity);
				pendingTransfers.add(pendingTransfer);
			}
		}
		return pendingTransfers;
	}

	@Transactional
	public Boolean buyPlayer(String userId, Long toTeamId, Long transferListId) {
		boolean transferred = false;
		TeamService teamService = new TeamService();
		TeamResponseModel teamEntity = teamService.fetchTeamById(toTeamId);
		UserService userService = new UserService();
		userService.loadUserByUsername(userId);
		//user is authorized and owns the team
		//player is on transferlist and not on the same team
		//there is enough balance to buy the player
		
		//make new entry in the transfer table
		//reduce toTeam budget and increment total value
		//increase fromTeam budget and decrease total value
		//update player has been transferred, update player's team and new market price}
		//performTransfer(toTeam, fromTeam, playerEntity, TransferEntity, transferList
		return transferred;
	}

}
