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
import com.soccerfantasy.app.model.request.TransferListRequestModel;
import com.soccerfantasy.app.model.response.ErrorMessages;
import com.soccerfantasy.app.model.response.TransferListResponseModel;
import com.soccerfantasy.app.repository.TransferListRepository;

@Service
public class TransferListService {

	@Autowired
	private TransferListMapper transferListMapper;

	@Autowired
	private TransferListRepository transferListRepository;

	@Autowired
	private PlayerService playerService;

	@Transactional
	public Boolean addOnTransferList(TransferListRequestModel transferListRequest) {
		PlayerEntity playerEntity = playerService.fetchPlayerEntity(transferListRequest.getPlayerId());
		Optional<TransferListEntity> optionalTransferList = transferListRepository
				.findByPlayer_IdAndTransferred(transferListRequest.getPlayerId(), false);
		if (optionalTransferList == null || optionalTransferList.isEmpty()) {
			TransferListEntity transferListEntity = new TransferListEntity();
			transferListEntity.setPlayer(playerEntity);
			if (transferListRequest.getAskingPrice() != null) {
				transferListEntity.setAskingPrice(transferListRequest.getAskingPrice());
			} else {
				transferListEntity.setAskingPrice(playerEntity.getMarketValue());
			}
			transferListEntity.setTransferred(false);
			transferListRepository.save(transferListEntity);
		} else  {
			throw new TransferServiceException(ErrorMessages.RECORD_ALREADY_EXISTS.getErrorMessage());
		}
		return true;
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

	public Boolean updateTransferListRequest(Long id, TransferListRequestModel transferListRequest) {
		Optional<TransferListEntity> optional = transferListRepository.findById(id);
		if (optional == null || optional.isEmpty()) {
			throw new TransferServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());
		}
		TransferListEntity transferListEntity = optional.get();
		if (transferListEntity.getPlayer().getId() == transferListRequest.getPlayerId() &&
				transferListEntity.getAskingPrice() != transferListRequest.getAskingPrice()) {
			transferListEntity.setAskingPrice(transferListRequest.getAskingPrice());
			transferListRepository.saveAndFlush(transferListEntity);
		}
		if (transferListEntity.getPlayer().getId() != transferListRequest.getPlayerId()) {
			throw new TransferServiceException(ErrorMessages.COULD_NOT_UPDATE_RECORD.getErrorMessage());
		}
		return true;
	}

	public TransferListEntity getTransferListById(Long transferListId) {
		Optional<TransferListEntity> optional = transferListRepository.findById(transferListId);
		if (optional == null || optional.isEmpty()) {
			throw new TransferServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());
		}
		return optional.get();
	}

}
