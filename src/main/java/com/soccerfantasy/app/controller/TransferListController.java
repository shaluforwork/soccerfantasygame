package com.soccerfantasy.app.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.soccerfantasy.app.model.request.TransferListRequestModel;
import com.soccerfantasy.app.model.response.TransferListResponseModel;
import com.soccerfantasy.app.service.TransferListService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@CrossOrigin(allowedHeaders = "*")
@Tag(name = "Transfer List Controller", description = "API to put a player on the transfer list and get transfer list.")
@RestController
@RequestMapping("/transferlist")
public class TransferListController {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private TransferListService transferListService;

    @Operation(security = @SecurityRequirement(name = "jwtBearerToken"))
	@GetMapping(produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<List<TransferListResponseModel>> getTransferList() {
    	logger.info("Fetch all transfer list API called.");
		List<TransferListResponseModel> transferList = transferListService.fetchPendingTransferList();
		return new ResponseEntity<>(transferList, HttpStatus.OK);
	}

    @Operation(security = @SecurityRequirement(name = "jwtBearerToken"))
	@PostMapping(consumes = { MediaType.APPLICATION_JSON_VALUE },
			produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Boolean> addToTransferList(@RequestBody TransferListRequestModel transferListRequestModel) {
    	logger.info("Add player to transfer list :: {}", transferListRequestModel.getPlayerId());
		Boolean added = transferListService.addOnTransferList(transferListRequestModel);
		return new ResponseEntity<Boolean>(added, HttpStatus.CREATED);
	}

    @Operation(security = @SecurityRequirement(name = "jwtBearerToken"))
	@PutMapping(path = "/{id}", consumes = { MediaType.APPLICATION_JSON_VALUE },
			produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Boolean> updateTransferList(@PathVariable Long id,
			@RequestBody TransferListRequestModel transferListRequest) {
    	logger.info("Update transfer list :: {}", id);
		Boolean updated = transferListService.updateTransferListRequest(id, transferListRequest);
		return new ResponseEntity<Boolean>(updated, HttpStatus.CREATED);
	}
}
