package com.soccerfantasy.app.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.soccerfantasy.app.model.request.TransferRequestModel;
import com.soccerfantasy.app.service.TransferService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@CrossOrigin(allowedHeaders = "*")
@Tag(name = "Transfer Controller", description = "API to buy a player present on the transfer list.")
@RestController
@RequestMapping("/transfer")
public class TransferController {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private TransferService transferService;

    @Operation(security = @SecurityRequirement(name = "jwtBearerToken"))
	@PostMapping(consumes = { MediaType.APPLICATION_JSON_VALUE },
			produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Boolean> buyPlayer(@RequestParam String userId,
			@RequestBody TransferRequestModel transferRequest) {
    	logger.info("Buy player request called for transfer list :: {}", transferRequest.getTransferListId());
		boolean transferred = transferService.buyPlayer(userId, transferRequest);
		return new ResponseEntity<Boolean>(transferred, HttpStatus.CREATED);
	}

}
