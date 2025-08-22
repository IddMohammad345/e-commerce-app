package com.idd.ecommerce.handler;

import java.util.HashMap;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.idd.ecommerce.exception.CustomerNotFoundException;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(CustomerNotFoundException.class)
	public ResponseEntity<String>handle(CustomerNotFoundException exception){
		return ResponseEntity
				.status(HttpStatus.NOT_FOUND)
				.body(exception.getMsg());
				
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorResponse>handle(MethodArgumentNotValidException ex){
		var errors=new HashMap<String,String>();
		ex.getBindingResult().getAllErrors()
		 .forEach(error ->{
			 var fieldName=((FieldError)error).getField();
			 var errorMessage=error.getDefaultMessage();
			 errors.put(fieldName, errorMessage);
		 });
		return ResponseEntity
				.status(HttpStatus.BAD_REQUEST)
				.body(new ErrorResponse(errors));
				
	}
	
}
