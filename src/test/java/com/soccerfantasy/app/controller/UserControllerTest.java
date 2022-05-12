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
import com.soccerfantasy.app.model.request.UserRequestModel;
import com.soccerfantasy.app.model.response.UserResponseModel;
import com.soccerfantasy.app.service.UserService;


public class UserControllerTest extends SoccerFantasyApplicationTests {


	@InjectMocks
	UserController userController;

	@Mock
	UserService userService;

	@Test
	public void testSignUp() {

		MockHttpServletRequest request = new MockHttpServletRequest();
		RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
		UserResponseModel userResponseModel = new UserResponseModel();
		userResponseModel.setEmail("shaluforwork@gmail.com");
		userResponseModel.setUserId("aljgdhlkfhkjasdmbmljs");
		UserRequestModel requestModel = new UserRequestModel();
		requestModel.setEmail("shaluforwork@gmail.com");
		requestModel.setPassword("password");
		when(userService.signUp(requestModel)).thenReturn(userResponseModel);
		ResponseEntity<UserResponseModel> responseEntity = userController.signUp(requestModel);

		assertThat(responseEntity.getStatusCodeValue()).isEqualTo(201);
		assertThat(responseEntity.getBody().getEmail().equals("shaluforwork@gmail.com"));
		assertThat(responseEntity.getBody().getUserId().equals("aljgdhlkfhkjasdmbmljs"));
	}
}
