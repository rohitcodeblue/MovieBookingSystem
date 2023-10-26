package com.project.moviebooking.constant;

import lombok.Getter;

/**
 *
 * @author Rohit
 *
 */
public enum RoleTypeEnum {
	MASTER_ADMIN("Master admin", "A"),
	ADMIN("Admin", "A"),
	ORGANIZATION("Organization", "O"), 
	MASTER_ORGANIZATION("Master organization", "O"),
	MOBILE("Mobile","M");

	@Getter
	private String displayName;
	@Getter
	private String typeCode;

	RoleTypeEnum(String displayName, String typeCode) {
		this.displayName = displayName;
		this.typeCode = typeCode;
	}

}
