package com.optum.seat.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.optum.seat.models.AllBookingTransaction;



public interface AllBookingTransactionRepo extends JpaRepository<AllBookingTransaction, Integer> {

	public List<AllBookingTransaction> findByLocationCode(String locationCode);
    public List<AllBookingTransaction> findByDateOfTransactionAndEmpIDAndSlotTimeAndLocationCode(Date dateOfTransaction,String empID,String slotTime,String LocationCode);
public List<AllBookingTransaction>   findByDateOfTransactionAndSlotTimeAndLocationCode(Date dateOfTransaction,String slotTime,String locationCode);
    // public List<AllBookingTransaction>findByDateOfTransactionAndSlotTimeAndLocationCode(Date dateOfTransaction,String slotTime,String locationCode);
public  List<AllBookingTransaction>  findByEmpIDAndSlotTimeAndLocationCode(String empID,String slotTime,String locationCode);
public List<AllBookingTransaction> findByEmpIDAndDateOfTransactionBetween(String empID, Date startDate, Date endDate);
public List<AllBookingTransaction> findByEmpIDAndDateOfTransaction(String empID, Date dateOfTransaction);

}
