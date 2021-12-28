package com.stackroute.keepnote.dto;

import java.time.LocalDate;

public class DepositDto {
	
	private int customerId;
	private String customerName;
	private String coveredMonth;
	private int noofMonths;
	private double interest;
	private double principleAmount;
	private double total;
	private int occurranceId;
	private String paidStatus;
	private double fine;
	
	
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	public String getCoveredMonth() {
		return coveredMonth;
	}
	public void setCoveredMonth(String coveredMonth) {
		this.coveredMonth = coveredMonth;
	}
	public int getNoofMonths() {
		return noofMonths;
	}
	public void setNoofMonths(int noofMonths) {
		this.noofMonths = noofMonths;
	}
	public double getInterest() {
		return interest;
	}
	public void setInterest(double interest) {
		this.interest = interest;
	}
	public double getPrincipleAmount() {
		return principleAmount;
	}
	public void setPrincipleAmount(double principleAmount) {
		this.principleAmount = principleAmount;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	public int getOccurranceId() {
		return occurranceId;
	}
	public void setOccurranceId(int occurranceId) {
		this.occurranceId = occurranceId;
	}
	public String getPaidStatus() {
		return paidStatus;
	}
	public void setPaidStatus(String paidStatus) {
		this.paidStatus = paidStatus;
	}
	public double getFine() {
		return fine;
	}
	public void setFine(double fine) {
		this.fine = fine;
	}
	

}
