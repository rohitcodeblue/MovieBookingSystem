package com.project.moviebooking.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MenuEnum {
	DASHBOARD("Dashboard"),
	CITY("City"),
	STATE("State"),
	ROLE("Role"),
	CODE_LOOKUP("Code Lookup"),
	CODE_LOOKUP_TYPE("Code Lookup Type"),
	USER("User"),
	FRANCHISE("Franchise"),
	SETTING("Setting");
	
	private final String displayName;
}
