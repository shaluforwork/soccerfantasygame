package com.soccerfantasy.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.soccerfantasy.app.model.request.PlayerRequestModel;
import com.soccerfantasy.app.model.response.Player;
import com.soccerfantasy.app.service.PlayerService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * APIs to fetch player details based on player Id and update player first name, last name and country.
 * @author shalu
 *
 */
@CrossOrigin(allowedHeaders = "*")
@Tag(name = "Player Controller", description = "APIs to fetch player details based on player Id and update player name and country.")
@RestController
@RequestMapping("/player")
public class PlayerController {

	@Autowired
	private PlayerService playerService;

    @Operation(security = @SecurityRequirement(name = "jwtBearerToken"))
	@GetMapping(path = "/{id}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Player> getPlayer(@PathVariable Long id) {
		Player player = playerService.fetchPlayer(id);
		return new ResponseEntity<Player>(player, HttpStatus.OK);
	}

    @Operation(security = @SecurityRequirement(name = "jwtBearerToken"))
	@PutMapping(path = "/{id}", consumes = { MediaType.APPLICATION_JSON_VALUE },
			produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Player> updatePlayer(@PathVariable Long id, @RequestBody PlayerRequestModel playerRequestModel) {
    	playerService.updatePlayer(id, playerRequestModel);
		return new ResponseEntity<Player>(HttpStatus.ACCEPTED);
	}
}
