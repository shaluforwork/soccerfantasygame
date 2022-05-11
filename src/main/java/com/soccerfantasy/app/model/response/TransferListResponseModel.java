package com.soccerfantasy.app.model.response;

import java.math.BigInteger;

import lombok.Data;

/**
 * Transfer List Request Model for Signing Up and Logging In.
 * @author shalu
 *
 */
@Data
public class TransferListResponseModel {

	private Long transferId;
	private Player player;
	private BigInteger askingPrice;
	private Boolean transferred;
}