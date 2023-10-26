package com.project.moviebooking.rest.controller.inf.embedded;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.project.moviebooking.rest.helper.AbstractHelper;
import com.project.moviebooking.service.AbstractService;

/**
 *
 * @author Rohit
 */
public interface AbstractInf {

	Logger BASE_LOGGER = LoggerFactory.getLogger(AbstractInf.class);

	public AbstractService getService();

	public AbstractHelper getHelper();

}
