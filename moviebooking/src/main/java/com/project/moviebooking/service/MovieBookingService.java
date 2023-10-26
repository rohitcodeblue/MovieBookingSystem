package com.project.moviebooking.service;

import com.project.moviebooking.entity.MovieBooking;

public interface MovieBookingService extends AbstractService<MovieBooking> {

	Boolean sendEmail();

}
