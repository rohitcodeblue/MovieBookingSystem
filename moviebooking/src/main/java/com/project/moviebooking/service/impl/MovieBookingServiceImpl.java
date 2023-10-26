package com.project.moviebooking.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.project.moviebooking.repository.MovieBookingRepository;
import com.project.moviebooking.service.MovieBookingService;

@Service
public class MovieBookingServiceImpl implements MovieBookingService {
	
	@Autowired MovieBookingRepository movieBookingRepository;

	@Override
	public JpaRepository getJpaRepository() {
		return movieBookingRepository;
	}

	@Override
	public Boolean sendEmail() {
		
		
		
		
		
		return null;
	}
	

}
