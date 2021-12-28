package com.stackroute.keepnote.dao;

import java.util.ArrayList;
import java.util.List;

import com.stackroute.keepnote.model.Customer;
import com.stackroute.keepnote.model.Deposits;
import com.stackroute.keepnote.model.Expenses;
import com.stackroute.keepnote.model.LoanHistory;
import com.stackroute.keepnote.model.Loans;
import com.stackroute.keepnote.model.OtherIncome;
import com.stackroute.keepnote.model.SpLoanHistory;
import com.stackroute.keepnote.model.SpecialLoan;
import com.stackroute.keepnote.model.UnpaidCustomerDto;

public class AuditDto {
	
	private double openingBalance;
	private List<Deposits> deposits=new ArrayList<>();
	private List<Loans> loans=new ArrayList<>();
	private List<LoanHistory> loansHistory=new ArrayList<>();
	private List<SpecialLoan> specialLoan=new ArrayList<>();
	private List<SpLoanHistory> specialLoanHistory=new ArrayList<>();
	private List<Expenses> expenseslist=new ArrayList<>();
	private List<OtherIncome> otherIncomeList=new ArrayList<>();
	private List<Customer> customerList=new ArrayList<>();
	private double monthlyDepositsAmount;
	private double rlCanMonthlyDepositsAmount;
	private double newEnrollmentAmounts;
	private double fines;
	private double normalLoanInterestsAmount;
	private double specialLoanInterestsAmount;
	private double applicationFormsAmount;
	private double normalloans;
	private double specialloans;
	private double unpaidLoansAmount;
	private double expenses;
	private double currentBalance;
	private double nLafterInstallmentDeduction;
	private double repaymentLoans;
	private double repaymentSpecialLoans;
	private double withdrawalAmount;
	private List<Customer> withdrawalCustomerList=new ArrayList<>();;
	private List<UnpaidCustomerDto> unpaidCustomerDtoList=new ArrayList<>();;
	
	
	
	
	public List<OtherIncome> getOtherIncomeList() {
		return otherIncomeList;
	}
	public void setOtherIncomeList(List<OtherIncome> otherIncomeList) {
		this.otherIncomeList = otherIncomeList;
	}
	public List<UnpaidCustomerDto> getUnpaidCustomerDtoList() {
		return unpaidCustomerDtoList;
	}
	public void setUnpaidCustomerDtoList(List<UnpaidCustomerDto> unpaidCustomerDtoList) {
		this.unpaidCustomerDtoList = unpaidCustomerDtoList;
	}
	public List<Customer> getWithdrawalCustomerList() {
		return withdrawalCustomerList;
	}
	public void setWithdrawalCustomerList(List<Customer> withdrawalCustomerList) {
		this.withdrawalCustomerList = withdrawalCustomerList;
	}
	public List<Customer> getCustomerList() {
		return customerList;
	}
	public void setCustomerList(List<Customer> customerList) {
		this.customerList = customerList;
	}
	public double getWithdrawalAmount() {
		return withdrawalAmount;
	}
	public void setWithdrawalAmount(double withdrawalAmount) {
		this.withdrawalAmount = withdrawalAmount;
	}
	public List<LoanHistory> getLoansHistory() {
		return loansHistory;
	}
	public void setLoansHistory(List<LoanHistory> loansHistory) {
		this.loansHistory = loansHistory;
	}
	public double getRepaymentLoans() {
		return repaymentLoans;
	}
	public void setRepaymentLoans(double repaymentLoans) {
		this.repaymentLoans = repaymentLoans;
	}
	public double getRepaymentSpecialLoans() {
		return repaymentSpecialLoans;
	}
	public void setRepaymentSpecialLoans(double repaymentSpecialLoans) {
		this.repaymentSpecialLoans = repaymentSpecialLoans;
	}
	public List<SpLoanHistory> getSpecialLoanHistory() {
		return specialLoanHistory;
	}
	public void setSpecialLoanHistory(List<SpLoanHistory> specialLoanHistory) {
		this.specialLoanHistory = specialLoanHistory;
	}
	public List<Expenses> getExpenseslist() {
		return expenseslist;
	}
	public void setExpenseslist(List<Expenses> expenseslist) {
		this.expenseslist = expenseslist;
	}
	public List<SpecialLoan> getSpecialLoan() {
		return specialLoan;
	}
	public void setSpecialLoan(List<SpecialLoan> specialLoan) {
		this.specialLoan = specialLoan;
	}
	public double getnLafterInstallmentDeduction() {
		return nLafterInstallmentDeduction;
	}
	public void setnLafterInstallmentDeduction(double nLafterInstallmentDeduction) {
		this.nLafterInstallmentDeduction = nLafterInstallmentDeduction;
	}
	public double getOpeningBalance() {
		return openingBalance;
	}
	public void setOpeningBalance(double openingBalance) {
		this.openingBalance = openingBalance;
	}
	public List<Deposits> getDeposits() {
		return deposits;
	}
	public void setDeposits(List<Deposits> deposits) {
		this.deposits = deposits;
	}
	public List<Loans> getLoans() {
		return loans;
	}
	public void setLoans(List<Loans> loans) {
		this.loans = loans;
	}
	public double getMonthlyDepositsAmount() {
		return monthlyDepositsAmount;
	}
	public void setMonthlyDepositsAmount(double monthlyDepositsAmount) {
		this.monthlyDepositsAmount = monthlyDepositsAmount;
	}
	public double getRlCanMonthlyDepositsAmount() {
		return rlCanMonthlyDepositsAmount;
	}
	public void setRlCanMonthlyDepositsAmount(double rlCanMonthlyDepositsAmount) {
		this.rlCanMonthlyDepositsAmount = rlCanMonthlyDepositsAmount;
	}
	public double getNewEnrollmentAmounts() {
		return newEnrollmentAmounts;
	}
	public void setNewEnrollmentAmounts(double newEnrollmentAmounts) {
		this.newEnrollmentAmounts = newEnrollmentAmounts;
	}
	public double getFines() {
		return fines;
	}
	public void setFines(double fines) {
		this.fines = fines;
	}
	public double getNormalLoanInterestsAmount() {
		return normalLoanInterestsAmount;
	}
	public void setNormalLoanInterestsAmount(double normalLoanInterestsAmount) {
		this.normalLoanInterestsAmount = normalLoanInterestsAmount;
	}
	public double getSpecialLoanInterestsAmount() {
		return specialLoanInterestsAmount;
	}
	public void setSpecialLoanInterestsAmount(double specialLoanInterestsAmount) {
		this.specialLoanInterestsAmount = specialLoanInterestsAmount;
	}
	public double getApplicationFormsAmount() {
		return applicationFormsAmount;
	}
	public void setApplicationFormsAmount(double applicationFormsAmount) {
		this.applicationFormsAmount = applicationFormsAmount;
	}
	public double getNormalloans() {
		return normalloans;
	}
	public void setNormalloans(double normalloans) {
		this.normalloans = normalloans;
	}
	public double getSpecialloans() {
		return specialloans;
	}
	public void setSpecialloans(double specialloans) {
		this.specialloans = specialloans;
	}
	public double getUnpaidLoansAmount() {
		return unpaidLoansAmount;
	}
	public void setUnpaidLoansAmount(double pendingLoansAmount) {
		this.unpaidLoansAmount = pendingLoansAmount;
	}
	public double getExpenses() {
		return expenses;
	}
	public void setExpenses(double expenses) {
		this.expenses = expenses;
	}
	public double getCurrentBalance() {
		return currentBalance;
	}
	public void setCurrentBalance(double currentBalance) {
		this.currentBalance = currentBalance;
	}
	
	
	

}
