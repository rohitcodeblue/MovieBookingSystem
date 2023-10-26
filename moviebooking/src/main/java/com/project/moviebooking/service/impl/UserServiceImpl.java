package com.project.moviebooking.service.impl;

import org.springframework.stereotype.Service;

import com.project.moviebooking.entity.User;
import com.project.moviebooking.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Override
	public User findByEmailId(String email) {
		return null;
	}

	@Override
	public User findActiveUserByEmailId(String username) {
		return null;
	}

}
