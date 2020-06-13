package com.test.user.userdemo.error;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class UserExceptionHandler {
	  @ExceptionHandler
	    public ResponseEntity<UserErrorResponse> handleException(UserNotFoundException ine){
		  UserErrorResponse errorResponse = new UserErrorResponse();
	        errorResponse.setStatus(HttpStatus.NOT_FOUND.value());
	        errorResponse.setMessage(ine.getMessage());
	      
	        return new ResponseEntity<UserErrorResponse>(errorResponse,HttpStatus.NOT_FOUND);
	    }

	    @ExceptionHandler
	    public ResponseEntity<UserErrorResponse> handleException(Exception ex){
	    	UserErrorResponse errorResponse = new UserErrorResponse();
	        errorResponse.setStatus(HttpStatus.BAD_REQUEST.value());
	        errorResponse.setMessage(ex.getMessage());
	       
	        return new ResponseEntity<UserErrorResponse>(errorResponse,HttpStatus.BAD_REQUEST);
	    }
}
