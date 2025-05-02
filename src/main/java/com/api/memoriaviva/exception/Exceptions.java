package com.api.memoriaviva.exception;

import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.api.memoriaviva.util.ResponseBuilder;

@ControllerAdvice
public class Exceptions {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handler (MethodArgumentNotValidException e) {
        Map<String, String> errors = e.getBindingResult()
                .getFieldErrors()
                .stream()
                .collect(Collectors.toMap(
                        FieldError::getField,
                        FieldError::getDefaultMessage
                ));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
	}

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handler (Exception e) {
		return ResponseBuilder.buildMessage(HttpStatus.INTERNAL_SERVER_ERROR, "Erro interno do servidor. " +
                "Tente novamente mais tarde."+ e.getMessage());
	}

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Object> handler (IllegalArgumentException e) {
		return ResponseBuilder.buildMessage(HttpStatus.NOT_FOUND, e.getMessage());
	}
}
