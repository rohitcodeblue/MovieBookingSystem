package com.project.moviebooking.model.response;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

/**
 * 
 * @author Rohit
 *
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DropdownModel implements Serializable {

	static final long serialVersionUID = 1L;

	@JsonProperty(value = "value")
	Object id;

	@JsonProperty(value = "label")
	String name;

	public DropdownModel(String name, Long id) {
		this.name = name;
		this.id = id;
	}

	public DropdownModel(Long id, String name) {
		this.name = name;
		this.id = id;
	}
	
	public DropdownModel(String label, Object value) {
		this.name = label;
		this.id = value;
	}

}
