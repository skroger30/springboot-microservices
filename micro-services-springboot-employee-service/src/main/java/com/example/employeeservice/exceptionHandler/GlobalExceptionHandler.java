package com.example.employeeservice.exceptionHandler;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@ControllerAdvice // Used to handle specific as well as global exceptions in single class
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler{
	
	
//	ResourceNotFoundException for getDepartmentByCode()
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ErrorDetails>  handleResourceNotFoundException(ResourceNotFoundException exception, 
			WebRequest webRequest) {
		 
		ErrorDetails errorDetails = new ErrorDetails(
				LocalDateTime.now(),
				exception.getMessage(),
				webRequest.getDescription(false),
				"NO_EMPLOYEE_EXIST_WITH_PROVIDED_ID");		
		return new ResponseEntity<>(errorDetails,HttpStatus.NOT_FOUND);
	}
	
	
//	Exception is super class of all checked and un-checked exceptions. 
//	In case of exceptions apart from specific and controller level exeptions ocuurs, following method will be called.
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorDetails>  handleGlobalException(Exception exception, WebRequest webRequest) {
		 
		ErrorDetails errorDetails = new ErrorDetails(
				LocalDateTime.now(),
				exception.getMessage(),
				webRequest.getDescription(false),
				"INTERNAL_SERVER_ERROR");		
		return new ResponseEntity<>(errorDetails,HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
