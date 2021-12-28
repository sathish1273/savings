package com.stackroute.keepnote.dto;

import java.time.LocalDate;

public class InstitutionDto {
	private String institutionName;
	private String institutionlogo;
	private double openingBalance;
	private String landMark;
	private String stateId;
	private String mandalId;
	private String districtId;
	private String villgaeId;
	private String status;
	private String pincode;
	private String institutionId;
	private String validityTo;
	private String rechargeAmount;
	private int msgScheduledDate;
	private String sangamStartDate;
	private String messagesRequired;
	
	
	
	
	public String getMessagesRequired() {
		return messagesRequired;
	}
	public void setMessagesRequired(String messagesRequired) {
		this.messagesRequired = messagesRequired;
	}
	public String getSangamStartDate() {
		return sangamStartDate;
	}
	public void setSangamStartDate(String sangamStartDate) {
		this.sangamStartDate = sangamStartDate;
	}
	public int getMsgScheduledDate() {
		return msgScheduledDate;
	}
	public void setMsgScheduledDate(int msgScheduledDate) {
		this.msgScheduledDate = msgScheduledDate;
	}
	public String getRechargeAmount() {
		return rechargeAmount;
	}
	public void setRechargeAmount(String rechargeAmount) {
		this.rechargeAmount = rechargeAmount;
	}
	public String getValidityTo() {
		return validityTo;
	}
	public void setValidityTo(String validityTo) {
		this.validityTo = validityTo;
	}
	
	
	public String getInstitutionId() {
		return institutionId;
	}
	public void setInstitutionId(String institutionId) {
		this.institutionId = institutionId;
	}
	public double getOpeningBalance() {
		return openingBalance;
	}
	public void setOpeningBalance(double openingBalance) {
		this.openingBalance = openingBalance;
	}
	public String getInstitutionName() {
		return institutionName;
	}
	public void setInstitutionName(String institutionName) {
		this.institutionName = institutionName;
	}
	public String getInstitutionlogo() {
		return institutionlogo;
	}
	public void setInstitutionlogo(String institutionlogo) {
		this.institutionlogo = institutionlogo;
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
