package com.stackroute.keepnote.dto;

import com.stackroute.keepnote.model.Customer;
import com.stackroute.keepnote.model.Institution;
import com.stackroute.keepnote.model.User;

public class DepositsSuccess {
	
	private Institution institution;
	private Customer customer;
	private User user;
	private String coveredMonth;
	private double amount;
	private double fine;
	private double transactionId;
	private double interest;
	private int installmentNo;
	
	private String loanIssuedTime;
	private Customer introducer;
	private double loanAmount;
	private String photo;
	private String signature;
	private String note;
	private double remainingloanamount;
	private long rid;
	private String indicator;
	
	
	
	public String getIndicator() {
		return indicator;
	}
	public void setIndicator(String indicator) {
		this.indicator = indicator;
	}
	public long getRid() {
		return rid;
	}
	public void setRid(long rid) {
		this.rid = rid;
	}
	public double getRemainingloanamount() {
		return remainingloanamount;
	}
	public void setRemainingloanamount(double remainingloanamount) {
		this.remainingloanamount = remainingloanamount;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public String getLoanIssuedTime() {
		return loanIssuedTime;
	}
	public void setLoanIssuedTime(String loanIssuedTime) {
		this.loanIssuedTime = loanIssuedTime;
	}
	public Customer getIntroducer() {
		return introducer;
	}
	public void setIntroducer(Customer introducer) {
		this.introducer = introducer;
	}
	public double getLoanAmount() {
		return loanAmount;
	}
	public void setLoanAmount(double loanAmount) {
		this.loanAmount = loanAmount;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public String getSignature() {
		return signature;
	}
	public void setSignature(String signature) {
		this.signature = signature;
	}
	public double getInterest() {
		return interest;
	}
	public void setInterest(double interest) {
		this.interest = interest;
	}
	public int getInstallmentNo() {
		return installmentNo;
	}
	public void setInstallmentNo(int installmentNo) {
		this.installmentNo = installmentNo;
	}
	public Institution getInstitution() {
		return institution;
	}
	public void setInstitution(Institution institution) {
		this.institution = institution;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public String getCoveredMonth() {
		return coveredMonth;
	}
	public void setCoveredMonth(String coveredMonth) {
		this.coveredMonth = coveredMonth;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public double getFine() {
		return fine;
	}
	public void setFine(double fine) {
		this.fine = fine;
	}
	public double getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(double transactionId) {
		this.transactionId = transactionId;
	}
	
	
	

}
