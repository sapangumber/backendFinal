package com.optum.seat.models;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
@Entity
@Table(name = "users",
       uniqueConstraints = {
           @UniqueConstraint(columnNames = "empID"),
           @UniqueConstraint(columnNames = "email")
       })

public class User {
	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 private Long id;
	 @NotBlank
	 @Size(max = 20)
	 private String empID;
	 @NotBlank
	 @Size(max = 50)
	 @Email
	 private String email;
	 @NotBlank
	 @Size(max = 50)
	 private String phone;
	 @NotBlank
	 @Size(max = 120)
	 private String password;
	 @ManyToMany(fetch = FetchType.LAZY)
     @JoinTable(name = "user_roles", 
	             joinColumns = @JoinColumn(name = "user_id"),
	             inverseJoinColumns = @JoinColumn(name = "role_id"))
	 private Set<Role> roles = new HashSet<>();
	 
	 public User() {
	  }

	 public User(String empID, String email,String phone, String password) {
		    this.empID = empID;
		    this.email = email;
		    this.phone = phone;
		    this.password = password;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	 
	 
	 

}
