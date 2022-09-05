package com.optum.seat.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.optum.seat.models.UserLastBooking;

@Repository
public interface UserLastBookingRepository extends JpaRepository<UserLastBooking, Integer> {
	public List <UserLastBooking>findByEmpID(String empID);

}
