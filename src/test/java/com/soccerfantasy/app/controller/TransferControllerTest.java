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
import com.soccerfantasy.app.model.request.TransferRequestModel;
import com.soccerfantasy.app.service.TransferService;


public class TransferControllerTest extends SoccerFantasyApplicationTests {


	@InjectMocks
	TransferController transferController;

	@Mock
	TransferService transferService;

	@Test
	public void testBuyPlayer() {

		MockHttpServletRequest request = new MockHttpServletRequest();
		RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
		String userId = "";
		TransferRequestModel transferRequest = new TransferRequestModel();
		transferRequest.setToTeam((long) 2);
		transferRequest.setTransferListId((long) 1);
		when(transferService.buyPlayer(userId, transferRequest)).thenReturn(true);
		ResponseEntity<Boolean> responseEntity = transferController.buyPlayer(userId, transferRequest);

		assertThat(responseEntity.getStatusCodeValue()).isEqualTo(201);
		assertThat(responseEntity.getBody().equals(true));
	}
}
