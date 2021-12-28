package com.stackroute.keepnote.dto;

public class MonthlyLoansSummaryDto {
	
	private double simpleLoan;
	private double specialLoan;
	private int noOfInstallments;
	private String customerName;
	private long customerId;
	private String introducerName;
	private long introducerId;
	private String customerSignature;
	private String introducerSignature;
	public double getSimpleLoan() {
		return simpleLoan;
	}
	public void setSimpleLoan(double simpleLoan) {
		this.simpleLoan = simpleLoan;
	}
	public double getSpecialLoan() {
		return specialLoan;
	}
	public void setSpecialLoan(double specialLoan) {
		this.specialLoan = specialLoan;
	}
	public int getNoOfInstallments() {
		return noOfInstallments;
	}
	public void setNoOfInstallments(int noOfInstallments) {
		this.noOfInstallments = noOfInstallments;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public long getCustomerId() {
		return customerId;
	}
	public void setCustomerId(long customerId) {
		this.customerId = customerId;
	}
	public String getIntroducerName() {
		return introducerName;
	}
	public void setIntroducerName(String introducerName) {
		this.introducerName = introducerName;
	}
	public long getIntroducerId() {
		return introducerId;
	}
	public void setIntroducerId(long introducerId) {
		this.introducerId = introducerId;
	}
	public String getCustomerSignature() {
		return customerSignature;
	}
	public void setCustomerSignature(String customerSignature) {
		this.customerSignature = customerSignature;
	}
	public String getIntroducerSignature() {
		return introducerSignature;
	}
	public void setIntroducerSignature(String introducerSignature) {
		this.introducerSignature = introducerSignature;
	}
	
	

}
