package com.soccerfantasy.app.exception;

public class UserServiceException extends RuntimeException{
 
	private static final long serialVersionUID = 9053976317742045323L;

	public UserServiceException(String message)
	{
		super(message);
	}
}
