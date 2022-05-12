package com.soccerfantasy.app.model.response;

import java.math.BigInteger;
import java.util.List;

import lombok.Data;

/**
 * Team Response Model for signing up and logging in.
 * @author shalu
 *
 */
@Data
public class TeamResponseModel {

	private Integer teamId;
	private String teamName;
	private String country;
	private BigInteger totalValue;
	private BigInteger budget;
	private List<Player> players;

}