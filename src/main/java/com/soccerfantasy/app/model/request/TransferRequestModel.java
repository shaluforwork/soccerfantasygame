package com.soccerfantasy.app.model.request;

import java.math.BigInteger;

import javax.validation.constraints.NotNull;

import lombok.Data;

/**
 * Transfer Request Model for Signing Up and Logging In.
 * @author shalu
 *
 */
@Data
public class TransferRequestModel {

	@NotNull(message="Player Id cannot be null")
	private Long playerId;

	@NotNull(message="From Team cannot be null")
	private Long fromTeam;

	@NotNull(message="To Team cannot be null")
	private Long toTeam;

	private BigInteger buyingPrice;
}