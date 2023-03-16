package com.kani.oams.exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	// Handle business logic related exceptions
	@ExceptionHandler(value = { CartNotFoundException.class,
			CategoryNotFoundException.class,
			CustomerNotFoundException.class,
			MedicineNotFoundException.class,
			OrderNotFoundException.class })

	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ResponseEntity<Object> handleNotFoundException(RuntimeException e, WebRequest request) {
		return handleExceptionInternal(e, e.getMessage(), new HttpHeaders(), HttpStatus.NOT_FOUND, request);

	}

	// Handle all remaining unchecked exceptions
	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ResponseEntity<Object> handleAllUncaughtException(Exception exception, WebRequest request) {
		return handleExceptionInternal(exception, exception.getMessage(), new HttpHeaders(),
				HttpStatus.INTERNAL_SERVER_ERROR, request);

	}

}
