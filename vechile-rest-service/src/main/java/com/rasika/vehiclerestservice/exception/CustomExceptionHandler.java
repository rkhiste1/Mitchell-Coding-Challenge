package com.rasika.vehiclerestservice.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(value = CustomException.class)
	private ResponseEntity<Object> handleException(CustomException exception) {
	
		return new ResponseEntity<>(exception.getMessage(), exception.getStatus());
	}

}
