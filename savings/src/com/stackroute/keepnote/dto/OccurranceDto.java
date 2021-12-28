package com.stackroute.keepnote.dto;

import java.util.Map;

public class OccurranceDto {
	
	private Map<Integer, String> transactionType=TransactionTypeE.integers;
	private long occurranceId;
	private long ledgerId;
	private String fromOccurrance;
	private String toOccurrance;
	private String occurrancePlace;
	private String coveredMonth;
	private String activehours;
	private String landMark;
	private String stateId;
	private String mandalId;
	private String districtId;
	private String villgaeId;
	private String status;
	private String pincode;
	private String reason;
	
	

	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public long getLedgerId() {
		return ledgerId;
	}
	public void setLedgerId(long ledgerId) {
		this.ledgerId = ledgerId;
	}
	public long getOccurranceId() {
		return occurranceId;
	}
	public void setOccurranceId(long occurranceId) {
		this.occurranceId = occurranceId;
	}
	public String getFromOccurrance() {
		return fromOccurrance;
	}
	public void setFromOccurrance(String fromOccurrance) {
		this.fromOccurrance = fromOccurrance;
	}
	public String getToOccurrance() {
		return toOccurrance;
	}
	public void setToOccurrance(String toOccurrance) {
		this.toOccurrance = toOccurrance;
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
	public String getPincode() {
		return pincode;
	}
	public void setPincode(String pincode) {
		this.pincode = pincode;
	}
	public Map<Integer, String> getTransactionType() {
		return transactionType;
	}
	public void setTransactionType(Map<Integer, String> transactionType) {
		this.transactionType = transactionType;
	}
	public String getOccurrancePlace() {
		return occurrancePlace;
	}
	public void setOccurrancePlace(String occurrancePlace) {
		this.occurrancePlace = occurrancePlace;
	}
	public String getCoveredMonth() {
		return coveredMonth;
	}
	public void setCoveredMonth(String coveredMonth) {
		this.coveredMonth = coveredMonth;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getActivehours() {
		return activehours;
	}
	public void setActivehours(String activehours) {
		this.activehours = activehours;
	}
	
	

}
