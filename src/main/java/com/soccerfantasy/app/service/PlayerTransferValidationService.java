package com.soccerfantasy.app.service;

import org.springframework.stereotype.Service;

import com.soccerfantasy.app.domain.PlayerEntity;
import com.soccerfantasy.app.domain.TeamEntity;

@Service
public class PlayerTransferValidationService {

	public boolean validateBudget(PlayerEntity playerEntity, TeamEntity toTeam) {
		boolean validated = false;
		if (playerEntity.getTeam().getId() != toTeam.getId()
				&& toTeam.getBudget().compareTo(playerEntity.getMarketValue()) != -1) {
			validated = true;
		}
		return validated;
	}

}
