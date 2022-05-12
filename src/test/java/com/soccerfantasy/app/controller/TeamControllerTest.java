package com.soccerfantasy.app.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.soccerfantasy.app.SoccerFantasyApplicationTests;
import com.soccerfantasy.app.model.request.TeamRequestModel;
import com.soccerfantasy.app.model.response.TeamResponseModel;
import com.soccerfantasy.app.service.TeamService;


public class TeamControllerTest extends SoccerFantasyApplicationTests {

	@InjectMocks
	TeamController teamController;

	@Mock
	TeamService teamService;

	@Test
	public void testGetTeam() {
		MockHttpServletRequest request = new MockHttpServletRequest();
		RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
		TeamResponseModel teamResponse = new TeamResponseModel();
		teamResponse.setTeamName("Team 1");
		teamResponse.setTeamId(1);
		when(teamService.fetchTeamById((long) 1)).thenReturn(teamResponse);
		ResponseEntity<TeamResponseModel> responseEntity = teamController.getTeam((long) 1);

		assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
		assertThat(responseEntity.getBody().getTeamName().equals("Team 1"));
		assertThat(responseEntity.getBody().getTeamId().equals(1));
	}

	@Test
	public void updateTeam() {
		MockHttpServletRequest request = new MockHttpServletRequest();
		RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
		TeamResponseModel teamResponse = new TeamResponseModel();
		teamResponse.setTeamName("Team 2");
		teamResponse.setCountry("India");
		teamResponse.setTeamId(1);
		TeamRequestModel teamReq = new TeamRequestModel();
		teamReq.setTeamName("Team 2");
		teamReq.setCountry("India");
		doNothing().when(teamService).updateTeam((long) 1, teamReq);
		ResponseEntity<TeamResponseModel> responseEntity = teamController.updateTeam((long) 1, teamReq);
		assertThat(responseEntity.getStatusCodeValue()).isEqualTo(202);
	}

}
