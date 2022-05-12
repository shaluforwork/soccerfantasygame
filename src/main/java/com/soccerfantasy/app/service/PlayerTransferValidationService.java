package com.soccerfantasy.app.service;

import java.math.BigInteger;

import org.springframework.stereotype.Service;

import com.soccerfantasy.app.domain.TeamEntity;
import com.soccerfantasy.app.domain.UserEntity;
import com.soccerfantasy.app.exception.TransferServiceException;
import com.soccerfantasy.app.model.request.TransferRequestModel;
import com.soccerfantasy.app.model.response.ErrorMessages;

@Service
public class PlayerTransferValidationService {

	public void validateBudget(BigInteger buyingPrice, TeamEntity toTeam) {
		if (toTeam.getBudget().compareTo(buyingPrice) == -1) {
			throw new TransferServiceException(ErrorMessages.NOT_ENOUGH_FUNDS.getErrorMessage());
		}
	}

	public void validateBuyingTeamUser(UserEntity userEntity, TransferRequestModel transferRequest) {
		if (!userEntity.getTeam().getId().equals(transferRequest.getToTeam())) {
			throw new TransferServiceException(ErrorMessages.TRANSFER_VALIDATION_FAILED.getErrorMessage());
		}
	}

	public void validateNotSameTeam(TeamEntity fromTeamEntity, TeamEntity toTeamEntity) {
		if (fromTeamEntity.getId() == toTeamEntity.getId()) {
			throw new TransferServiceException(ErrorMessages.TRANSFER_VALIDATION_FAILED.getErrorMessage());			
		}
	}

}
