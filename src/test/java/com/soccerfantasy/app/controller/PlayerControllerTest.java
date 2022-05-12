package com.soccerfantasy.app.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.soccerfantasy.app.SoccerFantasyApplicationTests;
import com.soccerfantasy.app.model.response.Player;
import com.soccerfantasy.app.service.PlayerService;


public class PlayerControllerTest extends SoccerFantasyApplicationTests {


	@InjectMocks
	PlayerController playerController;

	@Mock
	PlayerService playerService;

	@Test
	public void tesGetPlayer() {

		MockHttpServletRequest request = new MockHttpServletRequest();
		RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
		Player player = new Player();
		player.setPlayerId((long) 1);
		player.setFirstName("John");
		when(playerService.fetchPlayer((long) 1)).thenReturn(player);
		ResponseEntity<Player> playerResp = playerController.getPlayer((long) 1);

		assertThat(playerResp.getStatusCodeValue()).isEqualTo(200);
		assertThat(playerResp.getBody().getPlayerId().equals(1));
		assertThat(playerResp.getBody().getFirstName().equals("John"));
	}
}
