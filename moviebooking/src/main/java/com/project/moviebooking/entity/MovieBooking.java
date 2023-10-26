package com.project.moviebooking.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.project.moviebooking.entity.mappedclass.BaseEntity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

/**
*
* @author Rohit
*/
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "movie_booking")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MovieBooking extends BaseEntity {
	static final long serialVersionUID = 1L;

	@ManyToOne(targetEntity = Movies.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "movie_id", nullable = false)
	Movies movie;

	@ManyToOne(targetEntity = User.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "user_id", nullable = false)
	User user;

}
