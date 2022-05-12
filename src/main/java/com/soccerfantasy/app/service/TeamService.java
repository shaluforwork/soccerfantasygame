package com.soccerfantasy.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.soccerfantasy.app.domain.TeamEntity;
import com.soccerfantasy.app.domain.UserEntity;
import com.soccerfantasy.app.exception.TeamServiceException;
import com.soccerfantasy.app.mapping.TeamMapper;
import com.soccerfantasy.app.model.request.TeamRequestModel;
import com.soccerfantasy.app.model.response.ErrorMessages;
import com.soccerfantasy.app.model.response.Player;
import com.soccerfantasy.app.model.response.TeamResponseModel;
import com.soccerfantasy.app.repository.TeamRepository;
import com.soccerfantasy.app.service.builder.TeamBuilder;

@Service
public class TeamService {

	@Autowired
	private TeamRepository teamRepository;

	@Autowired
	private TeamMapper teamMapper;

	@Autowired
	private PlayerService playerService;

	public TeamResponseModel fetchTeamById(Long teamId) {
		TeamEntity teamEntity = findTeamEntityByTeamById(teamId);
		List<Player> players = playerService.fetchTeamPlayers(teamId);
		TeamResponseModel teamResponseModel = teamMapper.teamEntityToTeamResponseModel(teamEntity);
		teamResponseModel.setPlayers(players);
		return teamResponseModel;
	}

	public void updateTeam(Long teamId, TeamRequestModel teamRequestModel) {
		Optional<TeamEntity> optionalTeamEntity = teamRepository.findById(teamId);
		if (optionalTeamEntity == null || optionalTeamEntity.isEmpty()) {
			throw new TeamServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());
		}
		TeamEntity teamEntity = optionalTeamEntity.get();
		if (teamRequestModel.getTeamName() != null && teamEntity.getTeamName().equals(teamRequestModel.getTeamName())) {
			teamEntity.setTeamName(teamRequestModel.getTeamName());
		}
		if (teamRequestModel.getTeamName() != null && teamEntity.getCountry().equals(teamRequestModel.getCountry())) {
			teamEntity.setCountry(teamRequestModel.getCountry());			
		}
		teamRepository.saveAndFlush(teamEntity);
	}

	public TeamEntity initializeTeam(UserEntity userEntity) {
		TeamEntity teamEntity = new TeamBuilder().build();
		teamRepository.save(teamEntity);
		playerService.initializePlayers(teamEntity);
		return teamEntity;
	}

	public TeamEntity findTeamEntityByTeamById(Long teamId) {
		Optional<TeamEntity> optionalTeamEntity = teamRepository.findById(teamId);
		if (optionalTeamEntity == null || optionalTeamEntity.isEmpty()) {
			throw new TeamServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());
		}
		return optionalTeamEntity.get();
	}

	public void saveUpdatedRecord(TeamEntity teamEntity) {
		teamRepository.save(teamEntity);		
	}
}
