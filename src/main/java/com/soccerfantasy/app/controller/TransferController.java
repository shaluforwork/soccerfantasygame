package com.soccerfantasy.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.soccerfantasy.app.model.request.TransferRequestModel;
import com.soccerfantasy.app.service.TransferService;

@RestController
@RequestMapping("/transfer")
public class TransferController {

	@Autowired
	private TransferService transferService;

	@PostMapping(consumes = { MediaType.APPLICATION_JSON_VALUE },
			produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Boolean> buyPlayer(@RequestParam String userId,
			@RequestBody TransferRequestModel transferRequest) {
		boolean transferred = transferService.buyPlayer(userId, transferRequest);
		return new ResponseEntity<Boolean>(transferred, HttpStatus.CREATED);
	}

}
