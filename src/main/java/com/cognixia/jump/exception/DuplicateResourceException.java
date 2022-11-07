package com.cognixia.jump.exception;

public class DuplicateResourceException extends Exception {
	private static final long serialVersionUID = 1L;

	public DuplicateResourceException(Object resource) {
		super("Duplicate entry  of " + resource );
	}
	
}
