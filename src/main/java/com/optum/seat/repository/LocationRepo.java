package com.optum.seat.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.optum.seat.models.Location;



public interface LocationRepo extends JpaRepository<Location, Integer>{

 public List<Location> findByLocationCode(String locationCode);
 public List<Location> findByLocationName(String locationName);
 
 
}
