package com.project.moviebooking.model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.project.moviebooking.util.DateUtil;

import lombok.Data;

@Data
public class DateModel implements Serializable{

	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("E, MMM dd, yyyy");
	    private Date startDate = new Date();
	    private Date endDate = new Date();
	    private Date todaysLimitDate = new Date();

	    @Override
	    public String toString() {
	        String dateString = "";
	        if (startDate != null) {
	            dateString = simpleDateFormat.format(startDate);
	        }
	        if (endDate != null) {
	            dateString += " - " + simpleDateFormat.format(endDate);

	        }
	        return dateString;
	    }

	    public Date getEndDate() {
	        if (endDate != null) {
	            return DateUtil.getEndDate(endDate);
	        }
	        return null;
	    }

	    public Date getStartDate() {
	        if (startDate != null) {
	            return DateUtil.getStartDate(startDate);
	        }
	        return null;
	    }
	
}
