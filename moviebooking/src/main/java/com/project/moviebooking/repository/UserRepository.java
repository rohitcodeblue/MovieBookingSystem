package com.project.moviebooking.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.moviebooking.entity.User;
import com.project.moviebooking.request.UserRequest;

public interface UserRepository extends JpaRepository<User, UserRequest> {

}
