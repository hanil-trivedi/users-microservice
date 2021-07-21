package com.hanil.demo.exception;

import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestController
@ControllerAdvice //***VVIMP Spring AOP Part which is used to implement common attributes across controllers
//Using a controller advice, the typical things which 
//are defined are exception handling (Using @ExceptionHandler ). That’s what we are defining right now.The other things which can be 
//defined is how do you handle dates. That's defined by using init binder
//for example.
//And also the other things which can be defined are common modal attributes that you would want to use
//across controllers. In all these scenarios.
//we would use a controller advice. We have defined the controller advice annotation we have made it a rest controller

public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

	//copy 'handleException' method from ResponseEntityExceptionHandler and change names and implement
	//for customizing common errors based on exceptions thrown
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) throws Exception {

		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(),
				request.getDescription(false));
		
		return new ResponseEntity(exceptionResponse,HttpStatus.INTERNAL_SERVER_ERROR);

	}
	
	@ExceptionHandler(UserNotFoundException.class)
	public final ResponseEntity<Object> handleUserNotFoundExceptions(UserNotFoundException ex, WebRequest request) throws Exception {

		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(),
				request.getDescription(false));
		
		return new ResponseEntity(exceptionResponse,HttpStatus.NOT_FOUND);

	}
	
	
	//override handleMethodArgumentNotValid method for customizing Javax validation error responses 
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(
			MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), "Field Validation Failed",
				ex.getBindingResult().getFieldError().toString());
		
		return new ResponseEntity(exceptionResponse,HttpStatus.BAD_REQUEST);
	}

}
