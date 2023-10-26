package com.project.moviebooking.exception;

/**
 *
 * @author Rohit
 */
public interface RestExceptionCode {

    public static final String DATA_NOT_FOUND_ERROR_CODE = "R101";
    public static final String VALIDATION_ERROR_CODE = "R102";
    public static final String SAVE_ERROR_CODE = "R103";
    public static final String UNKNOWN_ERROR_CODE = "R104";
    public static final String DELETE_ERROR_CODE = "R105";
    public static final String CITY_NOT_FOUND_ERROR_CODE = "R106";
    public static final String CUSTOM_ERROR_CODE = "R107";
    
    
    public static final String RETAILER_NOT_EXISTS_OR_IT_IS_NOT_AGENT = "R1001";
    public static final String RETAILER_REQUEST_IS_NOT_ACCEPTED = "R1002";
}
