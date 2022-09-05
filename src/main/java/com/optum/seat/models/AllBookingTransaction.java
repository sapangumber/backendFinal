package com.optum.seat.models;

import java.sql.Date;

import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name="all_booking_transaction")
public class AllBookingTransaction {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer transactionId;
	
	@Column(name="empID")
	private String empID;
	
	@Column(name="location_code")
	private String locationCode;
	
	@Column (name="date_of_transaction")
	 @JsonFormat(pattern="yyyy-MM-dd")
	private Date dateOfTransaction;
	
	@Column(name="slot_time")
	private String slotTime; // "I" "II"

	
     
	public Integer getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(Integer transactionId) {
		this.transactionId = transactionId;
	}

	public String getEmpID() {
		return empID;
	}

	public void setEmpID(String empID) {
		this.empID = empID;
	}

	public String getLocationCode() {
		return locationCode;
	}

	public void setLocationCode(String locationCode) {
		this.locationCode = locationCode;
	}

	public Date getDateOfTransaction() {
		return dateOfTransaction;
	}

	public void setDateOfTransaction(Date dateOfTransaction) {
		this.dateOfTransaction = dateOfTransaction;
	}

	public String getSlotTime() {
		return slotTime;
	}

	public void setSlotTime(String slotTime) {
		this.slotTime = slotTime;
	}

	public AllBookingTransaction() {
		super();
	}

	public AllBookingTransaction(String empID, String locationCode, Date dateOfTransaction, String slotTime) {
		super();
		this.empID = empID;
		this.locationCode = locationCode;
		this.dateOfTransaction = dateOfTransaction;
		this.slotTime = slotTime;
	}
	
	
}
