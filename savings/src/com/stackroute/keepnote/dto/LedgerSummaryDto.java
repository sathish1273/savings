package com.stackroute.keepnote.dto;

import java.time.LocalDate;

public class LedgerSummaryDto {
	
	private LocalDate coveredMonth;
	private double balance;
	private double openingBalance;
	private double depsitsAmount;
	private double depositsInterestsAmount;
	private double loanAmountPrinciples;
	private double specialLoanPrinciples;
	private double specialLoanPaidPrinciples;
	private double specialLoanInterests;
	private double specialLoanFines;
	private double loanInteretsAmount;
	private double givenLoansAmount;
	private double closingBalance;
	private double additionalExpenses;
	private double loanFines;
    private double depositFines;
    private double withdrawals;
    private double applicationforms;
    
    
	
	public double getApplicationforms() {
		return applicationforms;
	}
	public void setApplicationforms(double applicationforms) {
		this.applicationforms = applicationforms;
	}
	public double getWithdrawals() {
		return withdrawals;
	}
	public void setWithdrawals(double withdrawals) {
		this.withdrawals = withdrawals;
	}
	public double getSpecialLoanPaidPrinciples() {
		return specialLoanPaidPrinciples;
	}
	public void setSpecialLoanPaidPrinciples(double specialLoanPaidPrinciples) {
		this.specialLoanPaidPrinciples = specialLoanPaidPrinciples;
	}
	public double getSpecialLoanInterests() {
		return specialLoanInterests;
	}
	public void setSpecialLoanInterests(double specialLoanInterests) {
		this.specialLoanInterests = specialLoanInterests;
	}
	public double getSpecialLoanFines() {
		return specialLoanFines;
	}
	public void setSpecialLoanFines(double specialLoanFines) {
		this.specialLoanFines = specialLoanFines;
	}
	public double getSpecialLoanPrinciples() {
		return specialLoanPrinciples;
	}
	public void setSpecialLoanPrinciples(double specialLoanPrinciples) {
		this.specialLoanPrinciples = specialLoanPrinciples;
	}
	public double getLoanFines() {
		return loanFines;
	}
	public void setLoanFines(double loanFines) {
		this.loanFines = loanFines;
	}
	public double getDepositFines() {
		return depositFines;
	}
	public void setDepositFines(double depositFines) {
		this.depositFines = depositFines;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	public double getDepositsInterestsAmount() {
		return depositsInterestsAmount;
	}
	public void setDepositsInterestsAmount(double depositsInterestsAmount) {
		this.depositsInterestsAmount = depositsInterestsAmount;
	}
	public LocalDate getCoveredMonth() {
		return coveredMonth;
	}
	public void setCoveredMonth(LocalDate coveredMonth) {
		this.coveredMonth = coveredMonth;
	}
	public double getOpeningBalance() {
		return openingBalance;
	}
	public void setOpeningBalance(double openingBalance) {
		this.openingBalance = openingBalance;
	}
	public double getDepsitsAmount() {
		return depsitsAmount;
	}
	public void setDepsitsAmount(double depsitsAmount) {
		this.depsitsAmount = depsitsAmount;
	}
	public double getLoanAmountPrinciples() {
		return loanAmountPrinciples;
	}
	public void setLoanAmountPrinciples(double loanAmountPrinciples) {
		this.loanAmountPrinciples = loanAmountPrinciples;
	}
	public double getLoanInteretsAmount() {
		return loanInteretsAmount;
	}
	public void setLoanInteretsAmount(double loanInteretsAmount) {
		this.loanInteretsAmount = loanInteretsAmount;
	}
	public double getGivenLoansAmount() {
		return givenLoansAmount;
	}
	public void setGivenLoansAmount(double givenLoansAmount) {
		this.givenLoansAmount = givenLoansAmount;
	}
	public double getClosingBalance() {
		return closingBalance;
	}
	public void setClosingBalance(double closingBalance) {
		this.closingBalance = closingBalance;
	}
	public double getAdditionalExpenses() {
		return additionalExpenses;
	}
	public void setAdditionalExpenses(double additionalExpenses) {
		this.additionalExpenses = additionalExpenses;
	}
	
	

}
