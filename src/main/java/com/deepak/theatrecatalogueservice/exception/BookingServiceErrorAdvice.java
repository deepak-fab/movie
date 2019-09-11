package com.deepak.theatrecatalogueservice.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class BookingServiceErrorAdvice {

	
    @ExceptionHandler({ ResourceNotFoundException.class })
    public ResponseEntity<Object> handleException(
      Exception ex, WebRequest request) {
        return new ResponseEntity<>(
          ex.getMessage(), new HttpHeaders(), HttpStatus.NOT_FOUND);
    }	 
}
