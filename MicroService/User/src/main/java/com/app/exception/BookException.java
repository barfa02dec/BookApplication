package com.app.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

@SuppressWarnings("serial")
//@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Actor Not Found")
public class BookException extends ResponseStatusException {
	public BookException(HttpStatus status,String msg){
		super(status, msg);
	}
	
}
