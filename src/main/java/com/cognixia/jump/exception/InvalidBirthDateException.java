package com.cognixia.jump.exception;

public class InvalidBirthDateException extends Exception{

	private static final long serialVersionUID = 1L;
	public InvalidBirthDateException(Object resource) {
		super("Invalid date of birth" + resource);
	}
	

}

