package com.test.user.userdemo.error;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

//@ResponseStatus(HttpStatus.NOT_FOUND) 
public class UserNotFoundException extends RuntimeException {
	public UserNotFoundException(String message) {
		super(message);
		System.out.println("bookid::::::::::message::::::::::" + message);

	}

	public UserNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserNotFoundException(Throwable cause) {
        super(cause);
    }
	/*
	 * public UserNotFoundException(String message, Throwable cause) {
	 * super(message, cause); }
	 * 
	 * public UserNotFoundException(Throwable cause) { super(cause); }
	 */

}
