package com.optum.seat.controllers;

import java.security.Principal;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.optum.seat.models.AllBookingTransaction;
import com.optum.seat.models.LastBookingResponse;
import com.optum.seat.models.Location;
import com.optum.seat.models.SeatLeftAtLocationCode;
import com.optum.seat.models.User;
import com.optum.seat.repository.AllBookingTransactionRepo;
import com.optum.seat.repository.LocationRepo;
import com.optum.seat.repository.UserLastBookingRepository;
import com.optum.seat.repository.UserRepository;
import com.optum.seat.services.AllBookingTransactionService;
import com.optum.seat.services.EmailSenderService;
import com.optum.seat.services.LocationService;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/test")
public class TestController {
	 @Autowired
	 LocationService locationService;
	 @Autowired
	 UserRepository userrepo;
	 @Autowired
	 AllBookingTransactionService allBookingTransactionService;
	 @Autowired
	 LocationRepo locationRepo;
	 @Autowired
	 UserLastBookingRepository userLastBookingRepository;
	 @Autowired
	 EmailSenderService emailSenderService;
	 @Autowired
	 AllBookingTransactionRepo allBookingTransactionRepo;
	 
	 
	 @GetMapping("/all")
	  public String allAccess() {
	    return "Office Seat Booking Portal.";
	  }
	  @GetMapping("/user")
	  @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	  public String userAccess() {
	    return "User Content.";
	  }
	  @GetMapping("/user/profile")
	  @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	  public Optional<User> profile(Principal principal) {
		  String name= principal.getName();
		  return userrepo.findByEmpID(name);
	  }
	  
	  @PostMapping("/user/book/{Code}")
	  @Transactional
	  @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	  public String BookSeatAtLocationCode(@PathVariable("Code") String code, Principal principal,
				@RequestBody AllBookingTransaction allBookingTransaction) {
			   //Take JSON in format {
	       // "dateOfTransaction" : "2022-08-24",
	       // "slotTime" : "II"}
			allBookingTransaction.setEmpID(principal.getName());
			List <AllBookingTransaction> listlastbooking = allBookingTransactionRepo.findByEmpIDAndDateOfTransaction(principal.getName(), allBookingTransaction.getDateOfTransaction());
			if (listlastbooking.size()!=0) {
				if (listlastbooking.get(0).getSlotTime()==allBookingTransaction.getSlotTime()) {
				return "You have already booked for this slot";
			}
				if (listlastbooking.get(0).getSlotTime()!=allBookingTransaction.getSlotTime()) {
					return "You have already booked for another slot on same day. Please cancel previous booking to book this slot.";
				}
			}
			Integer trx = allBookingTransactionService.bookSeat(code, allBookingTransaction);
			// mail_Service
//			String email = userrepo.findByEmpID(principal.getName()).get().getEmail();
//			emailSenderService.sendEmail(email, "Booking Confirmation" , "You have successfully booked for a slot"
//					+ " for the date " + allBookingTransaction.getDateOfTransaction()+ " at location " + code +  ". Your Booking ID is " + trx.toString());
//			
			return "You have suucessfully booked for a slot. Your Booking ID is " + trx.toString();
		
		}
	  
	  @PostMapping("/user/cancel/{trxID}")
	  @Transactional
	  @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	  public String CancelSeat(@PathVariable("trxID") Integer trxID, Principal principal) {
		    AllBookingTransaction bookings = allBookingTransactionRepo.getById(trxID);
		    if(!bookings.getEmpID().equals(principal.getName())) {
		    	return "You cannot cancel for this invalid booking";
		    }
		    else {
		    	allBookingTransactionRepo.deleteById(trxID);
		    }
//		    String mail = userrepo.findByEmpID(principal.getName()).get().getEmail();
//			emailSenderService.sendEmail(mail, "Booking Cancellation" , "You have suucessfully cancelled for a booking Id" + trxID.toString() );
			return "You have suucessfully cancelled for a booking Id " + trxID.toString();
		}
	  
	  @PostMapping("/user/seatleftbydateslot/{code}")
	  @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	  public Integer SeatLeftByDateSlot(@PathVariable("code")String code,@RequestBody AllBookingTransaction allBookingTransaction)
	    {
	    	Date date=allBookingTransaction.getDateOfTransaction();
	    	String slotTimeString=allBookingTransaction.getSlotTime();
	    //	Integer n= locationRepo.
	    	Integer countbooking =   allBookingTransactionService.CountBookingByDateAndSlot(code, date, slotTimeString);
	    	Integer seatLimit = locationRepo.findByLocationCode(code).get(0).getLocationSeatLimit();
	    	return seatLimit - countbooking;
	    }
	  
	  @GetMapping("/user/availableoffice/{place}")
	  @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	  public List<SeatLeftAtLocationCode> availableoffice(@PathVariable("place") String place, @RequestParam(name="date",required = true, defaultValue = "1990-01-01") Date date, 
			  @RequestParam(name="slot",required = true, defaultValue = "I") String slot){
		  List <Location> codes = locationRepo.findByLocationName(place);
		  List <SeatLeftAtLocationCode> ans = new ArrayList<>();
		  for (var loc : codes) {
			  Integer seatLeft = loc.getLocationSeatLimit()-allBookingTransactionService.CountBookingByDateAndSlot(loc.getLocationCode(), date, slot);
			  if (seatLeft == 0) {
				  ans.add(new SeatLeftAtLocationCode(loc.getLocationCode(), seatLeft, false));
			  }
			  else {
				  ans.add(new SeatLeftAtLocationCode(loc.getLocationCode(), seatLeft, true));
			  }
		  }
		  return ans;
		  
	  }
	  
	  @GetMapping("/user/lastbooking")
	  @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
	  public List<AllBookingTransaction> lastbooking(Principal principal){
//		  Integer bookingID =0;
//		  String slot = "";
//		  String date = "";
//		  String place="";
//		  String code = "";
//		  List <UserLastBooking> data = userLastBookingRepository.findByEmpID(principal.getName());
//		  if (data.size()==0) {
//			  return new LastBookingResponse(0,"-","-","-","-");
//		  }
//		  else {
//			  bookingID = data.get(0).getLast_trxn_id();
//			  date = data.get(0).getDate().toString();
//			  slot = data.get(0).getSlot();
//			  code = allBookingTransactionService.getByTransactionId(bookingID).getLocationCode();
//			  place = locationRepo.findByLocationCode(code).get(0).getLocationName();
//			  return new LastBookingResponse(bookingID, slot, date, place, code);
//		  }
		  LocalDateTime dtm = LocalDateTime.now();
		  System.out.println(dtm.toLocalDate());
		  Date date = Date.valueOf(dtm.toLocalDate().plusDays(1));
		  
		  Date end = Date.valueOf(dtm.toLocalDate().plusDays(10));
		  
		  List <AllBookingTransaction> answer = allBookingTransactionRepo.findByEmpIDAndDateOfTransactionBetween(principal.getName(), date, end);
		  for (var a:answer) {
			 a.setDateOfTransaction(Date.valueOf(LocalDate.parse(a.getDateOfTransaction().toString()).plusDays(1).toString() ));
		  }
	     return answer;
	    }
	  
	  
	  @GetMapping("/admin")
	  @PreAuthorize("hasRole('ADMIN')")
	  public String adminAccess() {
	    return "Admin Board.";
	  }
	  @PostMapping("/admin/location/create")
	  @PreAuthorize("hasRole('ADMIN')")

	  public String createLocation(@RequestBody Location location){
			locationService.createLocation(location);
			return "success";
		}
	  //list of all locations
	  @GetMapping("/admin/location/list")
	  @PreAuthorize("hasRole('ADMIN')")
	  public List<Location> getListLocation()
		{ 
			return locationService.listLocation();
		}
	  
	  @PostMapping ("/admin/location/update/byCode")
	  @PreAuthorize("hasRole('ADMIN')")
	  public String updateLocationByCode(@RequestParam(name="code",required = false, defaultValue = "*****") String code,@RequestBody Location location)
		{
			boolean ret=locationService.editByLocationCode(code, location);
			return ret==true?"Success" :"Not a valid code";
		}
	  
	  @GetMapping ("/admin/getbookingpertimeslot/{code}")
	  @PreAuthorize("hasRole('ADMIN')")
	  public List<AllBookingTransaction> getbookingpertimeslot(@RequestParam(name="date",required = true, defaultValue = "*****") Date date,
			  @RequestParam(name="slot",required = true, defaultValue = "*****") String slot, @PathVariable("code") String code)
	  {
		  return allBookingTransactionService.getbookingpertimeslotservice(date, slot, code);
	  }
}
