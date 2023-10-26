package com.project.moviebooking.security;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.project.moviebooking.constant.Constants;
import com.project.moviebooking.constant.RoleTypeEnum;
import com.project.moviebooking.entity.User;
import com.project.moviebooking.security.model.CustomUserDetail;
import com.project.moviebooking.service.UserService;

/**
 *
 * @author Rohit
 */
@Service
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    private UserService userService;


	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		if (email != null && !email.equals("")) {
			User user = userService.findByEmailId(email);
			if (user == null) {
				throw new UsernameNotFoundException("Invalid username or password");
			}
				return new CustomUserDetail(user.getId(),
						user.getUserDetail() != null ? user.getUserDetail().getName() : null, user.getEmailId(),
						user.getEmailId(), user.getPassword(),
						Constants.DEFAULT_LOCALE,user.getRoleType());
			} else {
				throw new UsernameNotFoundException("User name is required");
			}
		}
/**
 * fill user role in security
 * @param user
 * @return
 */
    private List<RoleTypeEnum> fillUserRole(User user){
    	List<RoleTypeEnum> userRoles = Optional.ofNullable(user.getUserRoleList())
        .orElseGet(Collections::emptyList).stream().map((userRole)->userRole.getRole().getType()).collect(Collectors.toList());
    	return userRoles;
    }

}
