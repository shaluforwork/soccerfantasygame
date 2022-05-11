package com.soccerfantasy.app.model.request;

import lombok.Data;

/**
 * Player Request Model for editing.
 * @author shalu
 *
 */
@Data
public class PlayerRequestModel {

	private String firstName;
	private String lastName;
	private String country;

}