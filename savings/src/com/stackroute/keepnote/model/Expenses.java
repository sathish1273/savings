package com.stackroute.keepnote.model;

import java.time.LocalDate;

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
public class Expenses {
	@javax.persistence.Id
	@Column
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="EXPENSE_SEQ")
	@SequenceGenerator(name = "EXPENSE_SEQ", sequenceName = "EXPENSE_SEQ", allocationSize = 1)
	private long expenseId;
	private String expenseName;
	private double amount;
	private String comments;
	private LocalDate createdAt;
	
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ledger_uid")
	private Ledger ledger ;
	
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "institution_uid")
	private Institution institution;
	
	
	

	public Ledger getLedger() {
		return ledger;
	}

	public void setLedger(Ledger ledger) {
		this.ledger = ledger;
	}

	public long getExpenseId() {
		return expenseId;
	}

	public void setExpenseId(long expenseId) {
		this.expenseId = expenseId;
	}

	public String getExpenseName() {
		return expenseName;
	}

	public void setExpenseName(String expenseName) {
		this.expenseName = expenseName;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public LocalDate getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDate createdAt) {
		this.createdAt = createdAt;
	}

	public Institution getInstitution() {
		return institution;
	}

	public void setInstitution(Institution institution) {
		this.institution = institution;
	}
	
	
	
	

}
