package com.soccerfantasy.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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

@RestController
@RequestMapping("/transferlist")
public class TransferListController {

	@Autowired
	private TransferListService transferListService;

	@GetMapping(produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<List<TransferListResponseModel>> getTransferList() {
		List<TransferListResponseModel> transferList = transferListService.fetchPendingTransferList();
		return new ResponseEntity<>(transferList, HttpStatus.OK);
	}

	@PostMapping(consumes = { MediaType.APPLICATION_JSON_VALUE },
			produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Boolean> addToTransferList(@RequestBody TransferListRequestModel transferListRequestModel) {
		Boolean added = transferListService.addOnTransferList(transferListRequestModel);
		return new ResponseEntity<Boolean>(added, HttpStatus.CREATED);
	}

	@PutMapping(path = "/{id}", consumes = { MediaType.APPLICATION_JSON_VALUE },
			produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Boolean> updateTransferList(@PathVariable Long id,
			@RequestBody TransferListRequestModel transferListRequest) {
		Boolean updated = transferListService.updateTransferListRequest(id, transferListRequest);
		return new ResponseEntity<Boolean>(updated, HttpStatus.CREATED);
	}
}
