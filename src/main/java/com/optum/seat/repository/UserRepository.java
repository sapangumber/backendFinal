package com.optum.seat.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.optum.seat.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	Optional<User> findByEmpID(String empID);
	Boolean existsByEmpID(String empID);
	Boolean existsByEmail(String email);

}
