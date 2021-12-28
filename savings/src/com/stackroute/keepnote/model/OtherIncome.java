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
public class OtherIncome {
	@javax.persistence.Id
	@Column
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="OTHERINCOME_SEQ")
	@SequenceGenerator(name = "OTHERINCOME_SEQ", sequenceName = "OTHERINCOME_SEQ", allocationSize = 1)
	private long otherIncomeUId;
	private long quantity;
	private double amount;
	private String description;
	private LocalDate createdDate;
	private long createdBy;
	private String type;
	
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ledger_uid")
	private Ledger ledger ;
	
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "institution_uid")
	private Institution institution;


	
	public long getQuantity() {
		return quantity;
	}


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}


	public void setQuantity(long quantity) {
		this.quantity = quantity;
	}


	public long getOtherIncomeUId() {
		return otherIncomeUId;
	}


	public void setOtherIncomeUId(long otherIncomeUId) {
		this.otherIncomeUId = otherIncomeUId;
	}




	public double getAmount() {
		return amount;
	}


	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public LocalDate getCreatedDate() {
		return createdDate;
	}


	public void setCreatedDate(LocalDate createdDate) {
		this.createdDate = createdDate;
	}


	public long getCreatedBy() {
		return createdBy;
	}


	public void setCreatedBy(long createdBy) {
		this.createdBy = createdBy;
	}


	public Ledger getLedger() {
		return ledger;
	}


	public void setLedger(Ledger ledger) {
		this.ledger = ledger;
	}


	public Institution getInstitution() {
		return institution;
	}


	public void setInstitution(Institution institution) {
		this.institution = institution;
	}
	
	
	

}
