package com.soccerfantasy.app.model.response;

import lombok.Data;

/**
 * User Response Model for signing up and logging in.
 * @author shalu
 *
 */
@Data
public class UserResponseModel {

	private String userId;

	private String email;

	private String teamId;

}