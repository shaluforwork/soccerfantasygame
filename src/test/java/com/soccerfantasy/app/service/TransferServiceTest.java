package com.soccerfantasy.app.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;

import com.soccerfantasy.app.SoccerFantasyApplicationTests;
import com.soccerfantasy.app.exception.TransferServiceException;
import com.soccerfantasy.app.model.request.TransferRequestModel;
import com.soccerfantasy.app.model.response.ErrorMessages;
import com.soccerfantasy.app.repository.UserRepository;

public class TransferServiceTest extends SoccerFantasyApplicationTests {


	@Autowired
	TransferService transferService;

	@Mock
	private UserRepository userRepository;

	@Test
	public void testSignUp() {
		String userId = "45116d16-3f5b-4f80-9435-35f3dc6363a0";
		TransferRequestModel transferRequestModel = new TransferRequestModel();
		transferRequestModel.setTransferListId((long) 5);
		transferRequestModel.setToTeam((long) 2);
		Exception exception = Assertions.assertThrows(TransferServiceException.class, () -> transferService.buyPlayer(userId, transferRequestModel));
	    String expectedMessage = ErrorMessages.NO_RECORD_FOUND.getErrorMessage();
	    String actualMessage = exception.getMessage();

	    Assertions.assertTrue(actualMessage.contains(expectedMessage));

	}
}
