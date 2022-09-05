package com.optum.seat.payload.request;

import java.util.Set;

import javax.validation.constraints.*;

public class SignupRequest {
	 @NotBlank
	 @Size(max = 20)
	 private String empID;
	 
	 @NotBlank
	 @Size(max = 50)
	 @Email
	 private String email;
	 
	 @NotBlank
	 @Size(max = 20)
	 private String phone;
	    
	  private Set<String> role;
	    
	  @NotBlank
	  @Size(min = 8, max = 20)
	  private String password;

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

	public Set<String> getRole() {
		return role;
	}

	public void setRole(Set<String> role) {
		this.role = role;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	  
	  
	

}
