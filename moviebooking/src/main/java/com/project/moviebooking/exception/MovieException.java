package com.project.moviebooking.exception;

import org.springframework.http.HttpStatus;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;


@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MovieException extends Exception {

	static final long serialVersionUID = 1L;
	
	HttpStatus statusCode = HttpStatus.INTERNAL_SERVER_ERROR;
	ExceptionType type = ExceptionType.ERROR;
		
	public MovieException(Exception e) {
		super(e.getMessage());
	}

	public MovieException(String message) {
		super(message);
	}

	public MovieException(int errorCodeValue, String message) {
		super(message);
		this.statusCode = HttpStatus.valueOf(errorCodeValue);
	}

	public MovieException(int errorCodeValue, ExceptionType type,  String message) {
		super(message);
		this.statusCode = HttpStatus.valueOf(errorCodeValue);
		this.type = type;
	}
	
}
