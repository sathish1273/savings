package com.stackroute.keepnote.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;


@Entity
@org.hibernate.annotations.Proxy(lazy=false)
public class PendingTransactions implements Serializable {
	
	@javax.persistence.Id
	@Column
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="PENDINGTRANSACTION_SEQ")
	@SequenceGenerator(name = "PENDINGTRANSACTION_SEQ", sequenceName = "PENDINGTRANSACTION_SEQ", allocationSize = 1)
	private long pTransactions_uid;
	
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "customer_uid")
	private Customer customer;
	
	@Column
	private LocalDateTime depositDate ;
	
	@Column
	private String createdBy;
	
	@Column
	private String updatedBy;
	
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "occurrance_uid")
	private Occurrance occurrance ;
	
	@Column
	private double depositsAmount;
	
	@Column
	private LocalDate depositslastPaidDate;
	
	@Column
	private double noOfMonths;
	
	@Column
	private double loansAmount;
	
	@Column
	private double noOfInstallments;
	
	@Column
	private LocalDate loanslastPaidDate;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "institution_uid")
	private Institution institution;
	
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ledgerId")
	private Ledger ledger;
	
	@Column
	private double specialLoansAmount;
	
	@Column
	private LocalDate sploanslastPaidDate;
	
	@Column
	private int depositType;

	public long getpTransactions_uid() {
		return pTransactions_uid;
	}

	public void setpTransactions_uid(long pTransactions_uid) {
		this.pTransactions_uid = pTransactions_uid;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public LocalDateTime getDepositDate() {
		return depositDate;
	}

	public void setDepositDate(LocalDateTime depositDate) {
		this.depositDate = depositDate;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Occurrance getOccurrance() {
		return occurrance;
	}

	public void setOccurrance(Occurrance occurrance) {
		this.occurrance = occurrance;
	}

	public double getDepositsAmount() {
		return depositsAmount;
	}

	public void setDepositsAmount(double depositsAmount) {
		this.depositsAmount = depositsAmount;
	}

	public LocalDate getDepositslastPaidDate() {
		return depositslastPaidDate;
	}

	public void setDepositslastPaidDate(LocalDate depositslastPaidDate) {
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

	public LocalDate getLoanslastPaidDate() {
		return loanslastPaidDate;
	}

	public void setLoanslastPaidDate(LocalDate loanslastPaidDate) {
		this.loanslastPaidDate = loanslastPaidDate;
	}

	public Institution getInstitution() {
		return institution;
	}

	public void setInstitution(Institution institution) {
		this.institution = institution;
	}

	public Ledger getLedger() {
		return ledger;
	}

	public void setLedger(Ledger ledger) {
		this.ledger = ledger;
	}

	public double getSpecialLoansAmount() {
		return specialLoansAmount;
	}

	public void setSpecialLoansAmount(double specialLoansAmount) {
		this.specialLoansAmount = specialLoansAmount;
	}

	public LocalDate getSploanslastPaidDate() {
		return sploanslastPaidDate;
	}

	public void setSploanslastPaidDate(LocalDate sploanslastPaidDate) {
		this.sploanslastPaidDate = sploanslastPaidDate;
	}

	public int getDepositType() {
		return depositType;
	}

	public void setDepositType(int depositType) {
		this.depositType = depositType;
	}
	
	
	
	
	
	

}
