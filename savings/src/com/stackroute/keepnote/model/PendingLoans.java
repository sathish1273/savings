package com.stackroute.keepnote.model;

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
public class PendingLoans {
	
	@javax.persistence.Id
	@Column
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="PENDINGLOANS_SEQ")
	@SequenceGenerator(name = "PENDINGLOANS_SEQ", sequenceName = "PENDINGLOANS_SEQ", allocationSize = 1)
	private long pTransactions_uid;
	
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "customer_uid")
	private Customer customer;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "institution_uid")
	private Institution institution;
	
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ledgerId")
	private Ledger ledger;
	
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "occurrance_uid")
	private Occurrance occurrance ;
	
	@Column
	private LocalDateTime createdDate ;
	
	@Column
	private LocalDateTime updatedDate ;
	
	@Column
	private String createdBy;
	
	@Column
	private String updatedBy;
	
	@Column
	private String paidStatus;
	
	@Column
	private double pendingAmount;
	
	@Column
	private int pendingInstallments;
	
	@Column
	private double pendingInterest;
	
	@Column
	private double pendingFine;

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

	public Occurrance getOccurrance() {
		return occurrance;
	}

	public void setOccurrance(Occurrance occurrance) {
		this.occurrance = occurrance;
	}

	public LocalDateTime getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}

	public LocalDateTime getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(LocalDateTime updatedDate) {
		this.updatedDate = updatedDate;
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

	public String getPaidStatus() {
		return paidStatus;
	}

	public void setPaidStatus(String paidStatus) {
		this.paidStatus = paidStatus;
	}

	public double getPendingAmount() {
		return pendingAmount;
	}

	public void setPendingAmount(double pendingAmount) {
		this.pendingAmount = pendingAmount;
	}

	public int getPendingInstallments() {
		return pendingInstallments;
	}

	public void setPendingInstallments(int pendingInstallments) {
		this.pendingInstallments = pendingInstallments;
	}

	public double getPendingInterest() {
		return pendingInterest;
	}

	public void setPendingInterest(double pendingInterest) {
		this.pendingInterest = pendingInterest;
	}

	public double getPendingFine() {
		return pendingFine;
	}

	public void setPendingFine(double pendingFine) {
		this.pendingFine = pendingFine;
	}

}
