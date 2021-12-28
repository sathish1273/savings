package com.stackroute.keepnote.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import com.stackroute.keepnote.model.Institution;
import com.stackroute.keepnote.model.Occurrance;

@Entity
@org.hibernate.annotations.Proxy(lazy=false)
public class ExcelDto  implements Serializable {
	
	@javax.persistence.Id
	@Column
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="EXCEL_SEQ")
	@SequenceGenerator(name = "EXCEL_SEQ", sequenceName = "EXCEL_SEQ", allocationSize = 1)
	private long pTransactions_uid;
	
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "occurrance_uid")
	private Occurrance occurrance ;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "institution_uid")
	private Institution institution;
	
	private String customerName;
	private String fatherName ;
	private String mobileNo;
	private double pDeposits;
	private double pFine;
	private double pendingMonths;
	private double pLoansAmount;
	private double pLoansInterest;
	private double pLoansFine;
	private double loanPaidAmount;
	private double loanAmountperInstallment;
	private int noOfInstallments;
	private int paidInstallments;
	private int pendingInstallments;
	private long introducerId;
	private double loanprinciple;
	private double spLoansPrinciple;
	private String SPLoanSanctionedDate;
	private double pSPLoansInterest;
	private double pSPLoansFine;
	private LocalDateTime localDateTime;
	private long createdBy;
	
	
	
	public double getLoanAmountperInstallment() {
		return loanAmountperInstallment;
	}
	public void setLoanAmountperInstallment(double loanAmountperInstallment) {
		this.loanAmountperInstallment = loanAmountperInstallment;
	}
	public long getpTransactions_uid() {
		return pTransactions_uid;
	}
	public void setpTransactions_uid(long pTransactions_uid) {
		this.pTransactions_uid = pTransactions_uid;
	}
	public Occurrance getOccurrance() {
		return occurrance;
	}
	public void setOccurrance(Occurrance occurrance) {
		this.occurrance = occurrance;
	}
	public Institution getInstitution() {
		return institution;
	}
	public void setInstitution(Institution institution) {
		this.institution = institution;
	}
	public LocalDateTime getLocalDateTime() {
		return localDateTime;
	}
	public void setLocalDateTime(LocalDateTime localDateTime) {
		this.localDateTime = localDateTime;
	}
	public long getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(long createdBy) {
		this.createdBy = createdBy;
	}
	public int getPendingInstallments() {
		return pendingInstallments;
	}
	public void setPendingInstallments(int pendingInstallments) {
		this.pendingInstallments = pendingInstallments;
	}
	public double getSpLoansPrinciple() {
		return spLoansPrinciple;
	}
	public void setSpLoansPrinciple(double spLoansPrinciple) {
		this.spLoansPrinciple = spLoansPrinciple;
	}
	public String getSPLoanSanctionedDate() {
		return SPLoanSanctionedDate;
	}
	public void setSPLoanSanctionedDate(String sPLoanSanctionedDate) {
		SPLoanSanctionedDate = sPLoanSanctionedDate;
	}
	public double getLoanPaidAmount() {
		return loanPaidAmount;
	}
	public void setLoanPaidAmount(double loanPaidAmount) {
		this.loanPaidAmount = loanPaidAmount;
	}
	public double getPendingMonths() {
		return pendingMonths;
	}
	public void setPendingMonths(double pendingMonths) {
		this.pendingMonths = pendingMonths;
	}
	public int getPaidInstallments() {
		return paidInstallments;
	}
	public void setPaidInstallments(int paidInstallments) {
		this.paidInstallments = paidInstallments;
	}
	public long getIntroducerId() {
		return introducerId;
	}
	public void setIntroducerId(long introducerId) {
		this.introducerId = introducerId;
	}
	public double getLoanprinciple() {
		return loanprinciple;
	}
	public void setLoanprinciple(double loanprinciple) {
		this.loanprinciple = loanprinciple;
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
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	public double getpDeposits() {
		return pDeposits;
	}
	public void setpDeposits(double pDeposits) {
		this.pDeposits = pDeposits;
	}
	public double getpFine() {
		return pFine;
	}
	public void setpFine(double pFine) {
		this.pFine = pFine;
	}
	public double getpLoansAmount() {
		return pLoansAmount;
	}
	public void setpLoansAmount(double pLoansAmount) {
		this.pLoansAmount = pLoansAmount;
	}
	public double getpLoansInterest() {
		return pLoansInterest;
	}
	public void setpLoansInterest(double pLoansInterest) {
		this.pLoansInterest = pLoansInterest;
	}
	public double getpLoansFine() {
		return pLoansFine;
	}
	public void setpLoansFine(double pLoansFine) {
		this.pLoansFine = pLoansFine;
	}
	
	public int getNoOfInstallments() {
		return noOfInstallments;
	}
	public void setNoOfInstallments(int noOfInstallments) {
		this.noOfInstallments = noOfInstallments;
	}
	public double getpSPLoansInterest() {
		return pSPLoansInterest;
	}
	public void setpSPLoansInterest(double pSPLoansInterest) {
		this.pSPLoansInterest = pSPLoansInterest;
	}
	public double getpSPLoansFine() {
		return pSPLoansFine;
	}
	public void setpSPLoansFine(double pSPLoansFine) {
		this.pSPLoansFine = pSPLoansFine;
	}
	
	
	

}
