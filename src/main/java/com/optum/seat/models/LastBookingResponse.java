package com.optum.seat.models;

public class LastBookingResponse {
	Integer bookingID;
	  String slot;
	  String date;
	  String place;
	  String code;
	public LastBookingResponse(Integer bookingID, String slot, String date, String place, String code) {
		super();
		this.bookingID = bookingID;
		this.slot = slot;
		this.date = date;
		this.place = place;
		this.code = code;
	}
	public Integer getBookingID() {
		return bookingID;
	}
	public void setBookingID(Integer bookingID) {
		this.bookingID = bookingID;
	}
	public String getSlot() {
		return slot;
	}
	public void setSlot(String slot) {
		this.slot = slot;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getPlace() {
		return place;
	}
	public void setPlace(String place) {
		this.place = place;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	  
	  
	  
	  
	  

}
