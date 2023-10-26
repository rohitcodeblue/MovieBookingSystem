package com.project.moviebooking.exception;

import org.springframework.http.HttpStatus;

/**
 *
 * @author uday
 */
public abstract class RestExecption {

    public static final HttpStatus HTTP_STATUS = HttpStatus.INTERNAL_SERVER_ERROR;

    public abstract String getMessage();

    public abstract String getErrorCode();

}
