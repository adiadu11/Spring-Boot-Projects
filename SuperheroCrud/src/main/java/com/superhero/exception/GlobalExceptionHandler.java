package com.superhero.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.superhero.model.ErrorDetails;

/**
 * Handles Exceptions at global level. Any Exception thrown can be
 * caught here.
 */
@ControllerAdvice
public class GlobalExceptionHandler
{
	/**
	 * Handles any generic exception.
	 */
	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> handleGeneralException(Exception ex, WebRequest request)
	{
		ErrorDetails errorDetails = new ErrorDetails(ex.getMessage(), new Date(), request.getDescription(false));
		return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	
	/**
	 * Handles ResourceNotFoundException type exception.
	 */
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<?> handleResourceNotFoundException(ResourceNotFoundException ex, 
															 WebRequest request)
	{
		ErrorDetails errorDetails = new ErrorDetails(ex.getMessage(), new Date(), request.getDescription(false));
		return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.NOT_FOUND);
	}
	
	/**
	 * Handles MethodArgumentNotValidException type exception.
	 * This will handle any violations in validation annotations we have put over
	 * our fields in our @Entity.
	 */
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex, 
															 WebRequest request)
	{
		ErrorDetails errorDetails = new ErrorDetails(ex.getMessage(), new Date(), request.getDescription(false));
		return new ResponseEntity<ErrorDetails>(errorDetails, HttpStatus.NOT_FOUND);
	}
}
