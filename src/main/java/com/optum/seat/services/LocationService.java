package com.optum.seat.services;

import java.util.List;
import java.util.Locale.Category;

import org.aspectj.internal.lang.annotation.ajcDeclareAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.optum.seat.models.Location;
import com.optum.seat.repository.LocationRepo;



@Service
public class LocationService {
	@Autowired
	LocationRepo locationRepo;
   public void createLocation(Location location)
   {	locationRepo.save(location);
	   
   }
   public List<Location> listLocation()
   {
	   return locationRepo.findAll();
   }
   public void editLocation(int locationId,Location updateLocation)
   {
	   Location location=locationRepo.getById(locationId);
	   location.setLocationName(updateLocation.getLocationName());
	   location.setLocationCode(updateLocation.getLocationCode());
	   location.setLocationSeatLimit(updateLocation.getLocationSeatLimit());
	   locationRepo.save(location);
	   
	
   }
   public boolean editByLocationCode(String locationCode,Location updateLocation)
   {
	   List<Location> list=locationRepo.findByLocationCode(locationCode);
	   if(list.size()==0) return false;
	   list.get(0).setLocationCode(updateLocation.getLocationCode());
	   list.get(0).setLocationName(updateLocation.getLocationName());
	   list.get(0).setLocationSeatLimit(updateLocation.getLocationSeatLimit());
	   locationRepo.save(list.get(0));
	  return true;
	   
   }
public boolean findbyId(int locationId) {
	// TODO Auto-generated method stub
	return locationRepo.findById(locationId).isPresent();
	
}
}
