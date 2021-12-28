package com.stackroute.keepnote.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import com.stackroute.keepnote.dto.PendingLoansDto;

public class LoanDto {
	
	private long loanId;
	private long customeId;
	private long interestTypeId;
	private long institutionId;
	private int noOfInstallments;
	private double amount;
	private int introducer;
	private int paidInstallments;
	private double paidAmount;
	private double paidInterest;
	private int remainingInstallments;
	private double remainingAmount;
	private long occurranceId;
	private String initialLoanStatus;
	private List<PendingLoansDto> remainingInstallmentsList=new ArrayList<>();
	private String currentPaidInstallmentIds;
	private double proposedPayAmount;
	private double proposedPayInterest;
	private List<Customer> customerlist=new ArrayList<>();
	private String loanSanctionDate;
	private double fine;
	private LocalDate coveredMonth;
	
	

	public LocalDate getCoveredMonth() {
		return coveredMonth;
	}
	public void setCoveredMonth(LocalDate coveredMonth) {
		this.coveredMonth = coveredMonth;
	}
	public double getFine() {
		return fine;
	}
	public void setFine(double fine) {
		this.fine = fine;
	}
	public String getLoanSanctionDate() {
		return loanSanctionDate;
	}
	public void setLoanSanctionDate(String loanSanctionDate) {
		this.loanSanctionDate = loanSanctionDate;
	}
	public List<Customer> getCustomerlist() {
		return customerlist;
	}
	public void setCustomerlist(List<Customer> customerlist) {
		this.customerlist = customerlist;
	}
	public long getLoanId() {
		return loanId;
	}
	public void setLoanId(long loanId) {
		this.loanId = loanId;
	}
	public long getCustomeId() {
		return customeId;
	}
	public void setCustomeId(long customeId) {
		this.customeId = customeId;
	}
	public long getInterestTypeId() {
		return interestTypeId;
	}
	public void setInterestTypeId(long interestTypeId) {
		this.interestTypeId = interestTypeId;
	}
	public long getInstitutionId() {
		return institutionId;
	}
	public void setInstitutionId(long institutionId) {
		this.institutionId = institutionId;
	}
	public int getNoOfInstallments() {
		return noOfInstallments;
	}
	public void setNoOfInstallments(int noOfInstallments) {
		this.noOfInstallments = noOfInstallments;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public int getIntroducer() {
		return introducer;
	}
	public void setIntroducer(int introducer) {
		this.introducer = introducer;
	}
	public int getPaidInstallments() {
		return paidInstallments;
	}
	public void setPaidInstallments(int paidInstallments) {
		this.paidInstallments = paidInstallments;
	}
	public double getPaidAmount() {
		return paidAmount;
	}
	public void setPaidAmount(double paidAmount) {
		this.paidAmount = paidAmount;
	}
	public double getPaidInterest() {
		return paidInterest;
	}
	public void setPaidInterest(double paidInterest) {
		this.paidInterest = paidInterest;
	}
	public int getRemainingInstallments() {
		return remainingInstallments;
	}
	public void setRemainingInstallments(int remainingInstallments) {
		this.remainingInstallments = remainingInstallments;
	}
	public double getRemainingAmount() {
		return remainingAmount;
	}
	public void setRemainingAmount(double remainingAmount) {
		this.remainingAmount = remainingAmount;
	}
	public long getOccurranceId() {
		return occurranceId;
	}
	public void setOccurranceId(long occurranceId) {
		this.occurranceId = occurranceId;
	}
	public String getInitialLoanStatus() {
		return initialLoanStatus;
	}
	public void setInitialLoanStatus(String initialLoanStatus) {
		this.initialLoanStatus = initialLoanStatus;
	}


	public List<PendingLoansDto> getRemainingInstallmentsList() {
		return remainingInstallmentsList;
	}
	public void setRemainingInstallmentsList(List<PendingLoansDto> remainingInstallmentsList) {
		this.remainingInstallmentsList = remainingInstallmentsList;
	}
	public String getCurrentPaidInstallmentIds() {
		return currentPaidInstallmentIds;
	}
	public void setCurrentPaidInstallmentIds(String currentPaidInstallmentIds) {
		this.currentPaidInstallmentIds = currentPaidInstallmentIds;
	}
	public double getProposedPayAmount() {
		return proposedPayAmount;
	}
	public void setProposedPayAmount(double proposedPayAmount) {
		this.proposedPayAmount = proposedPayAmount;
	}
	public double getProposedPayInterest() {
		return proposedPayInterest;
	}
	public void setProposedPayInterest(double proposedPayInterest) {
		this.proposedPayInterest = proposedPayInterest;
	}
	
	
	
}
