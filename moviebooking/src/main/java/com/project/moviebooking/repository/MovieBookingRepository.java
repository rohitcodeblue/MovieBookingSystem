package com.project.moviebooking.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.moviebooking.entity.MovieBooking;

public interface MovieBookingRepository extends JpaRepository<MovieBooking, Long> {

}
