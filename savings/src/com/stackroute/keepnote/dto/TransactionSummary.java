package com.stackroute.keepnote.dto;

import java.util.List;

import com.stackroute.keepnote.model.Loans;
import com.stackroute.keepnote.model.SpecialLoan;

public class TransactionSummary {
	
	private double currentBalance;
	private double totalLoans;
	private double totalSpLoans;
	private double totaldeposits;
	private int noOfCustomers;
	private Loans pendingLoans;
	private SpecialLoan pendingSpLoans;
	private List<DepositDto> pendingDeposits;
	
	

	public double getTotaldeposits() {
		return totaldeposits;
	}
	public void setTotaldeposits(double totaldeposits) {
		this.totaldeposits = totaldeposits;
	}
	public Loans getPendingLoans() {
		return pendingLoans;
	}
	public void setPendingLoans(Loans pendingLoans) {
		this.pendingLoans = pendingLoans;
	}
	public SpecialLoan getPendingSpLoans() {
		return pendingSpLoans;
	}
	public void setPendingSpLoans(SpecialLoan pendingSpLoans) {
		this.pendingSpLoans = pendingSpLoans;
	}
	public List<DepositDto> getPendingDeposits() {
		return pendingDeposits;
	}
	public void setPendingDeposits(List<DepositDto> pendingDeposits) {
		this.pendingDeposits = pendingDeposits;
	}
	public int getNoOfCustomers() {
		return noOfCustomers;
	}
	public void setNoOfCustomers(int noOfCustomers) {
		this.noOfCustomers = noOfCustomers;
	}
	public double getCurrentBalance() {
		return currentBalance;
	}
	public void setCurrentBalance(double currentBalance) {
		this.currentBalance = currentBalance;
	}
	public double getTotalLoans() {
		return totalLoans;
	}
	public void setTotalLoans(double totalLoans) {
		this.totalLoans = totalLoans;
	}
	public double getTotalSpLoans() {
		return totalSpLoans;
	}
	public void setTotalSpLoans(double totalSpLoans) {
		this.totalSpLoans = totalSpLoans;
	}
	
	
	

}
