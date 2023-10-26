package com.project.moviebooking.model;

import java.io.Serializable;
import java.util.List;

import com.project.moviebooking.constant.RoleTypeEnum;
import com.project.moviebooking.constant.UserTypeEnum;
import com.project.moviebooking.model.response.DropdownModel;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author Rohit
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseModel implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String name;

    private String orgName;

    private String emailId;

    private String sex;

    private String mobileNo;

    private Long activeCityId;

    private String profilePicPath;

    private String imageUrlPrefix;

    private UserTypeEnum roleType;
    
    private String welcomePageUrl;

    private List<DropdownModel> cities;
    
    private List<RoleTypeEnum> roles;
    
    private Long deliveryCityId;

}
