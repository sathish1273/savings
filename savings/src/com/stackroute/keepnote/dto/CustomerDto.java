package com.stackroute.keepnote.dto;

import java.time.LocalDate;

import javax.persistence.Column;

import org.springframework.web.multipart.MultipartFile;

public class CustomerDto {
	
	private String customerUid;
	private String customerId;
	private String customerName;
	private String fatherName;
	private String phoneNo;
	private String gmail;
	private MultipartFile photo;
	private MultipartFile signature;
	private MultipartFile customerFile;
	private String landMark;
	private String stateId;
	private String mandalId;
	private String districtId;
	private String villgaeId;
	private String status;
	private String pincode;
	private String initialContribution;
	private double depositsAmount;
	private String depositslastPaidDate;
	private double noOfMonths;
	private double loansAmount;
	private double noOfInstallments;
	private String loanslastPaidDate;
	private double specialLoansAmount;
	private String sploanslastPaidDate;
	private String type;
	
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public MultipartFile getCustomerFile() {
		return customerFile;
	}
	public void setCustomerFile(MultipartFile customerFile) {
		this.customerFile = customerFile;
	}
	public double getDepositsAmount() {
		return depositsAmount;
	}
	public void setDepositsAmount(double depositsAmount) {
		this.depositsAmount = depositsAmount;
	}
	public String getDepositslastPaidDate() {
		return depositslastPaidDate;
	}
	public void setDepositslastPaidDate(String depositslastPaidDate) {
		this.depositslastPaidDate = depositslastPaidDate;
	}
	public double getNoOfMonths() {
		return noOfMonths;
	}
	public void setNoOfMonths(double noOfMonths) {
		this.noOfMonths = noOfMonths;
	}
	public double getLoansAmount() {
		return loansAmount;
	}
	public void setLoansAmount(double loansAmount) {
		this.loansAmount = loansAmount;
	}
	public double getNoOfInstallments() {
		return noOfInstallments;
	}
	public void setNoOfInstallments(double noOfInstallments) {
		this.noOfInstallments = noOfInstallments;
	}
	public String getLoanslastPaidDate() {
		return loanslastPaidDate;
	}
	public void setLoanslastPaidDate(String loanslastPaidDate) {
		this.loanslastPaidDate = loanslastPaidDate;
	}
	public double getSpecialLoansAmount() {
		return specialLoansAmount;
	}
	public void setSpecialLoansAmount(double specialLoansAmount) {
		this.specialLoansAmount = specialLoansAmount;
	}
	public String getSploanslastPaidDate() {
		return sploanslastPaidDate;
	}
	public void setSploanslastPaidDate(String sploanslastPaidDate) {
		this.sploanslastPaidDate = sploanslastPaidDate;
	}
	public String getCustomerUid() {
		return customerUid;
	}
	public void setCustomerUid(String customerUid) {
		this.customerUid = customerUid;
	}
	public MultipartFile getSignature() {
		return signature;
	}
	public void setSignature(MultipartFile signature) {
		this.signature = signature;
	}
	public String getInitialContribution() {
		return initialContribution;
	}
	public void setInitialContribution(String initialContribution) {
		this.initialContribution = initialContribution;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getFatherName() {
		return fatherName;
	}
	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}
	public String getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	public String getGmail() {
		return gmail;
	}
	public void setGmail(String gmail) {
		this.gmail = gmail;
	}
	public MultipartFile getPhoto() {
		return photo;
	}
	public void setPhoto(MultipartFile photo) {
		this.photo = photo;
	}
	public String getLandMark() {
		return landMark;
	}
	public void setLandMark(String landMark) {
		this.landMark = landMark;
	}
	public String getStateId() {
		return stateId;
	}
	public void setStateId(String stateId) {
		this.stateId = stateId;
	}
	public String getMandalId() {
		return mandalId;
	}
	public void setMandalId(String mandalId) {
		this.mandalId = mandalId;
	}
	public String getDistrictId() {
		return districtId;
	}
	public void setDistrictId(String districtId) {
		this.districtId = districtId;
	}
	public String getVillgaeId() {
		return villgaeId;
	}
	public void setVillgaeId(String villgaeId) {
		this.villgaeId = villgaeId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getPincode() {
		return pincode;
	}
	public void setPincode(String pincode) {
		this.pincode = pincode;
	}
	
	

}
