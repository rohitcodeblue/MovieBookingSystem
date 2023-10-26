package com.project.moviebooking.rest.helper;

import java.util.List;

import org.springframework.stereotype.Component;

import com.project.moviebooking.entity.MovieBooking;
import com.project.moviebooking.request.MovieBookingRequest;


/**
 * @author Rohit Gupta
 */
@Component
public class MovieBookingHelper extends AbstractHelper<MovieBooking, MovieBookingRequest, Object, Object, Object> {

	@Override
	public MovieBooking getEntity(MovieBookingRequest request) throws Exception {
		return null;
	}

	@Override
	public List<Object> getListItemTypeResponse(List<MovieBooking> entityList) {
		return null;
	}

	@Override
	public Object getDetailResponse(MovieBooking entity) {
		return null;
	}

	@Override
	public Object getDetailForAdminResponse(MovieBooking entity) {
		return null;
	}

}
