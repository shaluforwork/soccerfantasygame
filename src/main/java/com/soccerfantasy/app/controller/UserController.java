package com.soccerfantasy.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.soccerfantasy.app.model.request.UserRequestModel;
import com.soccerfantasy.app.model.response.UserResponseModel;
import com.soccerfantasy.app.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserService userService;

    @PostMapping(consumes = { MediaType.APPLICATION_JSON_VALUE },
    		produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<UserResponseModel> signUp(@RequestBody UserRequestModel userRequestModel) {
    	UserResponseModel userResponseModel = userService.signUp(userRequestModel);
    	return new ResponseEntity<UserResponseModel>(userResponseModel, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public void fakeLogin(@RequestBody UserRequestModel userRequestModel) {
        throw new IllegalStateException("This method shouldn't be called. It's implemented by Spring Security filters.");
    }

    @PostMapping("/logout")
    public void fakeLogout() {
        throw new IllegalStateException("This method shouldn't be called. It's implemented by Spring Security filters.");
    }
}
