package com.soccerfantasy.app.exception;

public class TeamServiceException extends RuntimeException{

	private static final long serialVersionUID = 6557631932356761447L;

	public TeamServiceException(String message)
	{
		super(message);
	}
}
