package com.project.moviebooking.model;

import java.io.Serializable;

import lombok.Value;

/**
 * 
 * @author Rohit
 *
 */
@Value
public class DropdownEnumModel implements Serializable {

	static final long serialVersionUID = 1L;

	String label;

	String value;

}
