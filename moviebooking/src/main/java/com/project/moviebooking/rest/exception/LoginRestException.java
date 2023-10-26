package com.project.moviebooking.rest.exception;

import com.project.moviebooking.exception.RestExceptionCode;
import com.project.moviebooking.exception.RestExecption;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class LoginRestException extends RestExecption {
	public final String errorCode = RestExceptionCode.DATA_NOT_FOUND_ERROR_CODE;
	private String message = "";

	public LoginRestException(String message) {
		this.message = message;
	}

}