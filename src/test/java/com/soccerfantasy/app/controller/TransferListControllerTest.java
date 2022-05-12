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
import com.soccerfantasy.app.model.request.TransferListRequestModel;
import com.soccerfantasy.app.service.TransferListService;


public class TransferListControllerTest extends SoccerFantasyApplicationTests {


	@InjectMocks
	TransferListController transferListController;

	@Mock
	TransferListService transferListService;

	@Test
	public void testAddToTransferList() {

		MockHttpServletRequest request = new MockHttpServletRequest();
		RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
		TransferListRequestModel transferListReq = new TransferListRequestModel();
		when(transferListService.addOnTransferList(transferListReq)).thenReturn(true);
		ResponseEntity<Boolean> responseEntity = transferListController.addToTransferList(transferListReq);

		assertThat(responseEntity.getStatusCodeValue()).isEqualTo(201);
		assertThat(responseEntity.getBody().equals(true));
	}
}
