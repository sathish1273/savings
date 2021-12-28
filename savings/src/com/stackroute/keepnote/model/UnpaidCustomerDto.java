package com.stackroute.keepnote.model;

public class UnpaidCustomerDto {
	
	private Customer customer;
	private int depositPendingMonths;
	private int loansPendingMonths;
	private int specialLoanPendingMonths;
	private double depositAmount;
	private double loansAmount;
	private double specialLoanAmount;
	
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public int getDepositPendingMonths() {
		return depositPendingMonths;
	}
	public void setDepositPendingMonths(int depositPendingMonths) {
		this.depositPendingMonths = depositPendingMonths;
	}
	public int getLoansPendingMonths() {
		return loansPendingMonths;
	}
	public void setLoansPendingMonths(int loansPendingMonths) {
		this.loansPendingMonths = loansPendingMonths;
	}
	public int getSpecialLoanPendingMonths() {
		return specialLoanPendingMonths;
	}
	public void setSpecialLoanPendingMonths(int specialLoanPendingMonths) {
		this.specialLoanPendingMonths = specialLoanPendingMonths;
	}
	public double getDepositAmount() {
		return depositAmount;
	}
	public void setDepositAmount(double depositAmount) {
		this.depositAmount = depositAmount;
	}
	public double getLoansAmount() {
		return loansAmount;
	}
	public void setLoansAmount(double loansAmount) {
		this.loansAmount = loansAmount;
	}
	public double getSpecialLoanAmount() {
		return specialLoanAmount;
	}
	public void setSpecialLoanAmount(double specialLoanAmount) {
		this.specialLoanAmount = specialLoanAmount;
	}
	
	
	

}
