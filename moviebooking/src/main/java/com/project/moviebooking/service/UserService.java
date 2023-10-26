package com.project.moviebooking.service;

import com.project.moviebooking.entity.User;

public interface UserService {

	User findByEmailId(String email);

	User findActiveUserByEmailId(String username);

}
