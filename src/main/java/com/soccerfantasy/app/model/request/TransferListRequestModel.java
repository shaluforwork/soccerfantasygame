package com.soccerfantasy.app.model.request;

import java.math.BigInteger;

import javax.validation.constraints.NotNull;

import lombok.Data;

/**
 * Transfer List Request Model for Signing Up and Logging In.
 * @author shalu
 *
 */
@Data
public class TransferListRequestModel {

	@NotNull(message="Player Id cannot be null")
	private Long playerId;

	private BigInteger askingPrice;

}