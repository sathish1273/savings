package com.stackroute.keepnote.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

@Entity
@org.hibernate.annotations.Proxy(lazy=false)
public class Ledger implements Serializable {
	
	@javax.persistence.Id
	@Column
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="LEDGER_SEQ")
	@SequenceGenerator(name = "LEDGER_SEQ", sequenceName = "LEDGER_SEQ", allocationSize = 1)
    private long ledger_uid;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "institution_uid")
	private Institution institution;
	
	
    @OneToMany(mappedBy = "ledger", cascade = CascadeType.ALL)
    private Set<Deposits> deposits;
	
	
	
    @OneToMany(mappedBy = "ledger", cascade = CascadeType.ALL)
    private Set<Loans> loans;
	
	
	
	@Column
	private double openingBalance;
	
	@Column
	private double closingBalance;
	
	@Column
	private double balance;
	
	@Column
	private LocalDateTime ledger_entry_date;
	
	@Column
	private long createdBy;
	
	@Column
	private long updatedBy;
	
	@Column
	private long status;

	public long getLedger_uid() {
		return ledger_uid;
	}

	public void setLedger_uid(long ledger_uid) {
		this.ledger_uid = ledger_uid;
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

	public double getOpeningBalance() {
		return openingBalance;
	}

	public void setOpeningBalance(double openingBalance) {
		this.openingBalance = openingBalance;
	}

	public double getClosingBalance() {
		return closingBalance;
	}

	public void setClosingBalance(double closingBalance) {
		this.closingBalance = closingBalance;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public LocalDateTime getLedger_entry_date() {
		return ledger_entry_date;
	}

	public void setLedger_entry_date(LocalDateTime ledger_entry_date) {
		this.ledger_entry_date = ledger_entry_date;
	}

	public long getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(long createdBy) {
		this.createdBy = createdBy;
	}

	public long getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(long updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Institution getInstitution() {
		return institution;
	}

	public void setInstitution(Institution institution) {
		this.institution = institution;
	}

	public long getStatus() {
		return status;
	}

	public void setStatus(long status) {
		this.status = status;
	}

	

	

}
