package com.soccerfantasy.app.service;

import org.springframework.stereotype.Service;

import com.soccerfantasy.app.domain.PlayerEntity;
import com.soccerfantasy.app.domain.TeamEntity;
import com.soccerfantasy.app.domain.UserEntity;
import com.soccerfantasy.app.exception.TransferServiceException;
import com.soccerfantasy.app.model.request.TransferRequestModel;
import com.soccerfantasy.app.model.response.ErrorMessages;

@Service
public class PlayerTransferValidationService {

	public boolean validateBudget(PlayerEntity playerEntity, TeamEntity toTeam) {
		if (playerEntity.getTeam().getId() != toTeam.getId()
				&& toTeam.getBudget().compareTo(playerEntity.getMarketValue()) != -1) {
			return true;
		}
		throw new TransferServiceException(ErrorMessages.NOT_ENOUGH_FUNDS.getErrorMessage());
	}

	public void validateBuyingTeamUser(UserEntity userEntity, TransferRequestModel transferRequest) {
		if (!userEntity.getTeam().getId().equals(transferRequest.getToTeam())) {
			throw new TransferServiceException(ErrorMessages.TRANSFER_VALIDATION_FAILED.getErrorMessage());
		}
	}

}
