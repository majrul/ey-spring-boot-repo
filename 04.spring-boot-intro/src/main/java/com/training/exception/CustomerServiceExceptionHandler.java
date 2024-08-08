package com.training.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.training.dto.Status;

@ControllerAdvice
public class CustomerServiceExceptionHandler {

	@ExceptionHandler(CustomerServiceException.class)
	public ResponseEntity<Status> handleError(CustomerServiceException e) {
		//routine exception handling/logging code here
		
		Status status = new Status();
		status.setStatus(false);
		status.setStatusMessage(e.getMessage());
		
		return new ResponseEntity<Status>(status, HttpStatus.BAD_REQUEST);
	}

}
