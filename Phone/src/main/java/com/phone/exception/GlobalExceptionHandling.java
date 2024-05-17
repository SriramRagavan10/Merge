package com.phone.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandling {

	@ExceptionHandler(value = RatingException.class)
	public ResponseEntity<Object> rating(RatingException re) {
		return new ResponseEntity<>(re.getMessage(), HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(value = BrandException.class)
	public ResponseEntity<Object> brand(BrandException be) {
		return new ResponseEntity<>(be.getMessage(), HttpStatus.BAD_REQUEST);
	}

}
