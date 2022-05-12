package com.soccerfantasy.app.service;

import static com.soccerfantasy.app.util.AppConstants.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.soccerfantasy.app.domain.PlayerEntity;
import com.soccerfantasy.app.domain.PlayerType;
import com.soccerfantasy.app.domain.TeamEntity;
import com.soccerfantasy.app.exception.TeamServiceException;
import com.soccerfantasy.app.mapping.PlayerMapper;
import com.soccerfantasy.app.model.request.PlayerRequestModel;
import com.soccerfantasy.app.model.response.ErrorMessages;
import com.soccerfantasy.app.model.response.Player;
import com.soccerfantasy.app.repository.PlayerRepository;
import com.soccerfantasy.app.service.builder.PlayerBuilder;

@Service
public class PlayerService {

	@Autowired
	private PlayerRepository playerRepository;

	@Autowired
	private PlayerMapper playerMapper;

	public List<Player> fetchTeamPlayers(Long teamId) {
		List<PlayerEntity> teamPlayers = playerRepository.findAllByTeam_Id(teamId);
		if (teamPlayers == null || teamPlayers.isEmpty()) {
			throw new TeamServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());
		}
		List<Player> players = new ArrayList<>();
		for (PlayerEntity playerEntity : teamPlayers) {
			Player player = playerMapper.playerEntityToPlayer(playerEntity);
			players.add(player);
		}
		return players;
	}

	public Player fetchPlayer(Long playerId) {
		PlayerEntity playerEntity = fetchPlayerEntity(playerId);
		return playerMapper.playerEntityToPlayer(playerEntity);
	}

	public PlayerEntity fetchPlayerEntity(Long playerId) {
		Optional<PlayerEntity> optionalPlayerEntity = playerRepository.findById(playerId);
		if (optionalPlayerEntity == null || optionalPlayerEntity.isEmpty()) {
			throw new TeamServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());
		}
		PlayerEntity playerEntity = optionalPlayerEntity.get();
		return playerEntity;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void initializePlayers(TeamEntity teamEntity) {
		List<PlayerEntity> playerEntities = new ArrayList<>();
		for (int i = 0; i < NUM_ATTACKER; i++) {
			PlayerEntity playerEntity = new PlayerBuilder(PlayerType.ATTACKER, teamEntity).build();
			playerEntities.add(playerEntity);
		}
		for (int i = 0; i < NUM_MIDFIELDER; i++) {
			PlayerEntity playerEntity = new PlayerBuilder(PlayerType.MIDFIELDER, teamEntity).build();
			playerEntities.add(playerEntity);
		}
		for (int i = 0; i < NUM_DEFENDER; i++) {
			PlayerEntity playerEntity = new PlayerBuilder(PlayerType.DEFENDER, teamEntity).build();
			playerEntities.add(playerEntity);
		}
		for (int i = 0; i < NUM_GOALKEEPER; i++) {
			PlayerEntity playerEntity = new PlayerBuilder(PlayerType.GOALKEEPER, teamEntity).build();
			playerEntities.add(playerEntity);
		}
		playerRepository.saveAll(playerEntities);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void updatePlayer(Long playerId, PlayerRequestModel playerRequestModel) {
		PlayerEntity playerEntity = fetchPlayerEntity(playerId);
		if (playerRequestModel.getFirstName() != null && !playerRequestModel.getFirstName().equals(playerEntity.getFirstName())) {
			playerEntity.setFirstName(playerRequestModel.getFirstName());
		}
		if (playerRequestModel.getLastName() != null && !playerRequestModel.getLastName().equals(playerEntity.getLastName())) {
			playerEntity.setLastName(playerRequestModel.getLastName());
		}
		if (playerRequestModel.getCountry() != null && !playerRequestModel.getCountry().equals(playerEntity.getCountry())) {
			playerEntity.setCountry(playerRequestModel.getCountry());
		}
		playerRepository.saveAndFlush(playerEntity);
	}

	public void saveUpdatedRecord(PlayerEntity playerEntity) {
		playerRepository.saveAndFlush(playerEntity);	
	}
}
