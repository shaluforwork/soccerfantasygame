package com.soccerfantasy.app.model.response;

import java.math.BigInteger;
import java.sql.Date;

import lombok.Data;

@Data
public class Player {

	private Long playerId;
	private String firstName;
	private String lastName;
	private String country;
	private Date birthDate;
	private BigInteger marketValue;
	private String position;
	private Integer teamId;

}
