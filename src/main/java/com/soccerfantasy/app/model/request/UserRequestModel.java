package com.soccerfantasy.app.model.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

/**
 * User Request Model for Signing Up and Logging In.
 * @author shalu
 *
 */
@Data
public class UserRequestModel {

	@NotNull(message="Email cannot be null")
	@Email
	private String email;

	@NotNull(message="Password cannot be null")
	@Size(min=8, max=16, message="Password must be equal or grater than 8 characters and less than 16 characters")
	private String password;

}