package com.optum.seat.services;

import java.sql.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.optum.seat.models.AllBookingTransaction;
import com.optum.seat.repository.AllBookingTransactionRepo;


@Service

public class AllBookingTransactionService {
    
	@Autowired
	AllBookingTransactionRepo allBookingTransactionRepo;
	
	public Integer bookSeat(String code, AllBookingTransaction allBookingTransaction)
	{
		allBookingTransaction.setLocationCode(code);
		allBookingTransaction=allBookingTransactionRepo.save(allBookingTransaction);
	return allBookingTransaction.getTransactionId();
	}

	public void DeletebyId(Integer id) {
		allBookingTransactionRepo.deleteById(id);
		
	}

	public List<AllBookingTransaction> ListOfAllTransaction() {
		// TODO Auto-generated method stub
		return allBookingTransactionRepo.findAll();
	}
	
	public List<AllBookingTransaction>   ListOfAllBookingByCode(String Code)
	{
		return allBookingTransactionRepo.findByLocationCode(Code);
	}
	
	public Integer CountBookingByDateAndSlot(String code,Date date,String Slot)
	{
		List<AllBookingTransaction> list=allBookingTransactionRepo.findByDateOfTransactionAndSlotTimeAndLocationCode(date, Slot, code);
		
		return list.size();
	}
	
	public List<AllBookingTransaction> func()
	{String code="RJ14";
	 String empID ="1007";
	 String slot="AM";
	 return allBookingTransactionRepo.findByEmpIDAndSlotTimeAndLocationCode(empID, slot, code);
	 
		
	}
	
	public List<AllBookingTransaction> getbookingpertimeslotservice(Date dateOfTransaction,String slotTime,String locationCode){
		return allBookingTransactionRepo.findByDateOfTransactionAndSlotTimeAndLocationCode(dateOfTransaction, slotTime, locationCode);
	}
	
	public Boolean isTransactionPresent(Integer trxnID) {
		return allBookingTransactionRepo.existsById(trxnID);
	}
	
	public AllBookingTransaction getByTransactionId(Integer trxnID) {
		return allBookingTransactionRepo.getById(trxnID);
	}

}

