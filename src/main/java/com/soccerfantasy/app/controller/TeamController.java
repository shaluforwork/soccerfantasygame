package com.soccerfantasy.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.soccerfantasy.app.model.request.TeamRequestModel;
import com.soccerfantasy.app.model.response.TeamResponseModel;
import com.soccerfantasy.app.service.TeamService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * APIs to fetch team details based on team Id and update team name and country.
 * @author shalu
 *
 */
@CrossOrigin(allowedHeaders = "*")
@Tag(name = "Team Controller", description = "APIs to fetch team details based on team Id and update team name and country.")
@RestController
@RequestMapping("/team")
public class TeamController {

	@Autowired
	private TeamService teamService;

    @Operation(security = @SecurityRequirement(name = "jwtBearerToken"))
	@GetMapping(produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<TeamResponseModel> getTeam(@RequestParam Long teamId) {
		TeamResponseModel teamResponseModel = teamService.fetchTeamById(teamId);
		return new ResponseEntity<TeamResponseModel>(teamResponseModel, HttpStatus.OK);
	}

    @Operation(security = @SecurityRequirement(name = "jwtBearerToken"))
	@PutMapping(consumes = { MediaType.APPLICATION_JSON_VALUE },
			produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<TeamResponseModel> updateTeam(@RequestParam Long teamId, @RequestBody TeamRequestModel teamRequestModel) {
		teamService.updateTeam(teamId, teamRequestModel);
		return new ResponseEntity<TeamResponseModel>(HttpStatus.ACCEPTED);
	}
}
