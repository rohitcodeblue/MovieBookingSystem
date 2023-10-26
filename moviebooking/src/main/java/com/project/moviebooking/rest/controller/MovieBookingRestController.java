package com.project.moviebooking.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.moviebooking.entity.MovieBooking;
import com.project.moviebooking.exception.MovieException;
import com.project.moviebooking.request.MovieBookingRequest;
import com.project.moviebooking.rest.controller.inf.CreateUpdateRest;
import com.project.moviebooking.rest.helper.AbstractHelper;
import com.project.moviebooking.rest.helper.MovieBookingHelper;
import com.project.moviebooking.service.AbstractService;
//import com.project.moviebooking.request.MovieBookingRequest;
import com.project.moviebooking.service.MovieBookingService;

import io.swagger.annotations.ApiOperation;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

@RestController
@RequestMapping("/movies")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MovieBookingRestController implements CreateUpdateRest<MovieBooking, MovieBookingRequest> {

	@Autowired MovieBookingService movieBookingService;

	@Autowired MovieBookingHelper movieBookingHelper;

	@Override
	public AbstractService getService() {
		return movieBookingService;
	}

	@Override
	public AbstractHelper getHelper() {
		return movieBookingHelper;
	}
	
	@GetMapping("/sendEmail")
	@ApiOperation(value = "Get Report time")
	public ResponseEntity<Object> getreport() throws MovieException{
		movieBookingService.sendEmail();
		return new ResponseEntity<>(HttpStatus.OK);
	}


}
