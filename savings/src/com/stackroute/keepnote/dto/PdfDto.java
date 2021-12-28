package com.stackroute.keepnote.dto;

import java.util.Set;
import java.util.TreeSet;

import com.stackroute.keepnote.model.Deposits;
import com.stackroute.keepnote.model.Institution;
import com.stackroute.keepnote.model.Loans;
import com.stackroute.keepnote.model.SpecialLoan;

public class PdfDto {
	
	private int sNo;
	private long occurranceId;
	private String coveredMonth;
	private String customerName;
	private long customerUid;
	private double monthlyShare;
	private double monthlySharefine;
	private Set<Deposits> deposits = new TreeSet<>();
	private String installments;
	private double monthlyInstallment;
	private double monthlyInstallmentinterest;
	private double monthlyInstallmentloanFine;
	private Set<Loans> loans = new TreeSet<>();
	private double specialLoan;
	private double specialLoanInterest;
	private double specialLoanFine;
	private double specialLoanPrinciple;
	private Set<SpecialLoan> specialLoanList = new TreeSet<>();
	private double others;
	private double total;
	private double loanAmount;
	private double due;
	private double lTDue;
	private double stDue;
	private String contactNo;
	private Institution institution;
	
	
	
	public long getOccurranceId() {
		return occurranceId;
	}
	public void setOccurranceId(long occurranceId) {
		this.occurranceId = occurranceId;
	}
	public double getSpecialLoanPrinciple() {
		return specialLoanPrinciple;
	}
	public void setSpecialLoanPrinciple(double specialLoanPrinciple) {
		this.specialLoanPrinciple = specialLoanPrinciple;
	}
	public String getCoveredMonth() {
		return coveredMonth;
	}
	public void setCoveredMonth(String coveredMonth) {
		this.coveredMonth = coveredMonth;
	}
	public Set<Deposits> getDeposits() {
		return deposits;
	}
	public void setDeposits(Set<Deposits> deposits) {
		this.deposits = deposits;
	}
	public Set<Loans> getLoans() {
		return loans;
	}
	public void setLoans(Set<Loans> loans) {
		this.loans = loans;
	}
	public Set<SpecialLoan> getSpecialLoanList() {
		return specialLoanList;
	}
	public void setSpecialLoanList(Set<SpecialLoan> specialLoanList) {
		this.specialLoanList = specialLoanList;
	}
	public int getsNo() {
		return sNo;
	}
	public void setsNo(int sNo) {
		this.sNo = sNo;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public long getCustomerUid() {
		return customerUid;
	}
	public void setCustomerUid(long customerUid) {
		this.customerUid = customerUid;
	}
	public double getMonthlyShare() {
		return monthlyShare;
	}
	public void setMonthlyShare(double monthlyShare) {
		this.monthlyShare = monthlyShare;
	}
	public double getMonthlySharefine() {
		return monthlySharefine;
	}
	public void setMonthlySharefine(double monthlySharefine) {
		this.monthlySharefine = monthlySharefine;
	}

	public String getInstallments() {
		return installments;
	}
	public void setInstallments(String installments) {
		this.installments = installments;
	}
	public double getMonthlyInstallment() {
		return monthlyInstallment;
	}
	public void setMonthlyInstallment(double monthlyInstallment) {
		this.monthlyInstallment = monthlyInstallment;
	}
	public double getMonthlyInstallmentinterest() {
		return monthlyInstallmentinterest;
	}
	public void setMonthlyInstallmentinterest(double monthlyInstallmentinterest) {
		this.monthlyInstallmentinterest = monthlyInstallmentinterest;
	}
	public double getMonthlyInstallmentloanFine() {
		return monthlyInstallmentloanFine;
	}
	public void setMonthlyInstallmentloanFine(double monthlyInstallmentloanFine) {
		this.monthlyInstallmentloanFine = monthlyInstallmentloanFine;
	}
	public double getSpecialLoan() {
		return specialLoan;
	}
	public void setSpecialLoan(double specialLoan) {
		this.specialLoan = specialLoan;
	}
	public double getSpecialLoanInterest() {
		return specialLoanInterest;
	}
	public void setSpecialLoanInterest(double specialLoanInterest) {
		this.specialLoanInterest = specialLoanInterest;
	}
	public double getSpecialLoanFine() {
		return specialLoanFine;
	}
	public void setSpecialLoanFine(double specialLoanFine) {
		this.specialLoanFine = specialLoanFine;
	}
	public double getOthers() {
		return others;
	}
	public void setOthers(double others) {
		this.others = others;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	public double getLoanAmount() {
		return loanAmount;
	}
	public void setLoanAmount(double loanAmount) {
		this.loanAmount = loanAmount;
	}
	public double getDue() {
		return due;
	}
	public void setDue(double due) {
		this.due = due;
	}
	public double getlTDue() {
		return lTDue;
	}
	public void setlTDue(double lTDue) {
		this.lTDue = lTDue;
	}
	public double getStDue() {
		return stDue;
	}
	public void setStDue(double stDue) {
		this.stDue = stDue;
	}
	public String getContactNo() {
		return contactNo;
	}
	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}
	public Institution getInstitution() {
		return institution;
	}
	public void setInstitution(Institution institution) {
		this.institution = institution;
	}
	
	
	

}
