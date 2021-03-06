package com.soccerfantasy.app.model.request;

import javax.validation.constraints.NotNull;

import lombok.Data;

/**
 * Transfer Request Model for Signing Up and Logging In.
 * @author shalu
 *
 */
@Data
public class TransferRequestModel {

	@NotNull(message="Transfer List Id cannot be null")
	private Long transferListId;

	@NotNull(message="To Team cannot be null")
	private Long toTeam;
}