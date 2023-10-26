package com.project.moviebooking.security;

import java.io.Serializable;

import com.project.moviebooking.model.UserResponseModel;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 *
 * @author Rohit
 */
@AllArgsConstructor
public class JwtResponse implements Serializable {

    private static final long serialVersionUID = 1L;

    @Getter
    private final String jwttoken;

    @Getter
    private final UserResponseModel userResponseModel;
}
