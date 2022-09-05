package com.optum.seat.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


import org.hibernate.annotations.NaturalId;

@Entity
@Table(name="location")
public class Location {
	@Id
	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name="location_place")
    private String locationName;
	
	@Column(name = "location_code",unique = true)
	@NaturalId
	private String locationCode;
    
	@Column(name="location_SeatLimit")
    private Integer locationSeatLimit;
    
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLocationName() {
		return locationName;
	}

	public Integer getLocationSeatLimit() {
		return locationSeatLimit;
	}

	public void setLocationSeatLimit(Integer locationSeatLimit) {
		this.locationSeatLimit = locationSeatLimit;
	}

	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}

	public String getLocationCode() {
		return locationCode;
	}

	public void setLocationCode(String locationCode) {
		this.locationCode = locationCode;
	}

	public Location() {
		super();
	}
	
	
	

}
