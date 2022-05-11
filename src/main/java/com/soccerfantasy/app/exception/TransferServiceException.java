package com.soccerfantasy.app.exception;

public class TransferServiceException extends RuntimeException {
 
	private static final long serialVersionUID = 6093473080553958810L;

	public TransferServiceException(String message)
	{
		super(message);
	}
}
