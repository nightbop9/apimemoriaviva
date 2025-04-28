package com.api.memoriaviva.util;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseBuilder {
	
	public static ResponseEntity<Object> buildMessage(HttpStatus status, String message) {
    	return ResponseEntity.status(HttpStatus.OK).body(Map.of("status", status, "message", message));
    }
}
