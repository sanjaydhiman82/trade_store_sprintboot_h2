package com.store.validator;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.store.exception.LowerMaturityException;
import com.store.exception.LowerVersionException;

@ControllerAdvice
public class ApiValidationHandler extends ResponseEntityExceptionHandler {
	@ExceptionHandler({LowerMaturityException.class,LowerVersionException.class})
	public ResponseEntity<Object> handleBusinessExp(Exception ex, WebRequest request) {

		Map<String, Object> response = new LinkedHashMap<>();
		response.put("timestamp", LocalDateTime.now());
		response.put("message", ex.getMessage());

		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}
	
}
