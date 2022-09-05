package com.optum.seat.models;

public class SeatLeftAtLocationCode {
	private String code;
	private Integer seatLeft;
	private boolean book;
	public SeatLeftAtLocationCode(String code, Integer seatLeft, boolean book) {
		super();
		this.code = code;
		this.seatLeft = seatLeft;
		this.book = book;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public Integer getSeatLeft() {
		return seatLeft;
	}
	public void setSeatLeft(Integer seatLeft) {
		this.seatLeft = seatLeft;
	}
	public boolean isBook() {
		return book;
	}
	public void setBook(boolean book) {
		this.book = book;
	}
	
}
