package com.project.moviebooking.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 *
 * @author Rohit
 */
@Getter
@NoArgsConstructor
public class SaveRestException extends RestExecption {

    public final String errorCode = RestExceptionCode.VALIDATION_ERROR_CODE;
    public String message = "Unable To Save. ";

    public SaveRestException(String message) {
        this.message += message;
    }
}
