package com.cognixia.jump.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends Exception {

	private static final long serialVersionUID = 1L;

	public ResourceNotFoundException(Object resource) {
		super("Resource of: " + resource + " not found.");
	}
	
	public ResourceNotFoundException(String msg) {
		super("Resource of: " + msg + " not found.");
	}
	
	
	public ResourceNotFoundException(String resource, String id) {
		super("Resource " + resource +" "+ "with ID" + id + " not found.");
	}

}
