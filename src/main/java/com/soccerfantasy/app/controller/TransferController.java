package com.soccerfantasy.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.soccerfantasy.app.model.request.UserRequestModel;
import com.soccerfantasy.app.model.response.UserResponseModel;
import com.soccerfantasy.app.service.UserService;

@RestController
@RequestMapping("/transfer")
public class TransferController {

	@Autowired
	private UserService userService;

    @GetMapping(consumes = { MediaType.APPLICATION_JSON_VALUE },
    		produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<UserResponseModel> getTransferList(@RequestBody UserRequestModel userRequestModel) {
    	UserResponseModel userResponseModel = userService.signUp(userRequestModel);
    	return new ResponseEntity<UserResponseModel>(userResponseModel, HttpStatus.CREATED);
    }

	@PostMapping(path = "/login", consumes = { MediaType.APPLICATION_JSON_VALUE },
    		produces = { MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<UserResponseModel> logIn(@RequestBody UserRequestModel userRequestModel) {
    	UserResponseModel userResponseModel = userService.logIn(userRequestModel);
    	return new ResponseEntity<UserResponseModel>(userResponseModel, HttpStatus.CREATED);
    }
}
