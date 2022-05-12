package com.soccerfantasy.app.service;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;

import com.soccerfantasy.app.SoccerFantasyApplicationTests;
import com.soccerfantasy.app.model.request.UserRequestModel;
import com.soccerfantasy.app.model.response.UserResponseModel;
import com.soccerfantasy.app.repository.UserRepository;

public class UserServiceTest extends SoccerFantasyApplicationTests {


	@Autowired
	UserService userService;

	@Mock
	private UserRepository userRepository;

	@Test
	public void testSignUp() {
		UserRequestModel requestModel = new UserRequestModel();
		requestModel.setEmail("shaluforwork@gmail.com");
		requestModel.setPassword("password");
		UserResponseModel userResp =  userService.signUp(requestModel);		
		assertThat(userResp.getEmail().equals("shaluforwork@gmail.com"));
	}
}
