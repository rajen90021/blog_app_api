package com.blog.exceptions;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.blog.payloads.apiresponse;

@RestControllerAdvice
public class globalexceptionHandler  {

	
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<apiresponse>  resourcenotfoundexception(ResourceNotFoundException exception){
		
		    String message = exception.getMessage();
		    apiresponse apiresponse= new apiresponse(message,false);
		    return new ResponseEntity<apiresponse>(apiresponse,HttpStatus.NOT_FOUND);
		
	}
	
	
	   @ExceptionHandler(MethodArgumentNotValidException.class)
	    public ResponseEntity<Map<String, String>> handleValidationErrors(MethodArgumentNotValidException ex) {
	        Map<String, String> validationErrors = new HashMap<>();

	        List<ObjectError> errors = ex.getBindingResult().getAllErrors();

	        for (ObjectError error : errors) {
	            if (error instanceof FieldError) {
	                FieldError fieldError = (FieldError) error;
	                validationErrors.put(fieldError.getField(), error.getDefaultMessage());
	            }
	        }

	        return new ResponseEntity<>(validationErrors, HttpStatus.BAD_REQUEST);
	    }
	
}
