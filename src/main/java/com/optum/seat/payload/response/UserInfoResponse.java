package com.optum.seat.payload.response;

import java.util.List;

public class UserInfoResponse {
	private Long id;
	private String empID;
	private String email;
	private String phone;
	private List<String> roles;
	public UserInfoResponse(Long id, String empID, String email, String phone, List<String> roles) {
		this.id = id;
		this.empID = empID;
		this.email = email;
		this.phone = phone;
		this.roles = roles;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getEmpID() {
		return empID;
	}
	public void setEmpID(String empID) {
		this.empID = empID;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public List<String> getRoles() {
		return roles;
	}
	
	
	


}
