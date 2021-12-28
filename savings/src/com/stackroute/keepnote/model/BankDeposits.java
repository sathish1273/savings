package com.stackroute.keepnote.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

@Entity
public class BankDeposits implements Serializable {

    @javax.persistence.Id
	@Column
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="BANKDEPOSITS_SEQ")
	@SequenceGenerator(name = "BANKDEPOSITS_SEQ", sequenceName = "BANKDEPOSITS_SEQ", allocationSize = 1)
    private long bank_deposits_uid;
    
    @Column
    private double amount_in_bank;
    
    @Column
    private double deposited_amount;
    
    @Column
    private Date date;
    
    @Column
    private String deposit_responsible_person;
    
    @Column
    private String upload_deposit_form;
    
    @Column
    private double undeposited_amount;
    
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "institution_uid")
	private Institution institution;
	
	public Institution getInstitution() {
		return institution;
	}

	public void setInstitution(Institution institution) {
		this.institution = institution;
	}

	public long getBank_deposits_uid() {
		return bank_deposits_uid;
	}

	public void setBank_deposits_uid(long bank_deposits_uid) {
		this.bank_deposits_uid = bank_deposits_uid;
	}

	public double getAmount_in_bank() {
		return amount_in_bank;
	}

	public void setAmount_in_bank(double amount_in_bank) {
		this.amount_in_bank = amount_in_bank;
	}

	public double getDeposited_amount() {
		return deposited_amount;
	}

	public void setDeposited_amount(double deposited_amount) {
		this.deposited_amount = deposited_amount;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getDeposit_responsible_person() {
		return deposit_responsible_person;
	}

	public void setDeposit_responsible_person(String deposit_responsible_person) {
		this.deposit_responsible_person = deposit_responsible_person;
	}

	public String getUpload_deposit_form() {
		return upload_deposit_form;
	}

	public void setUpload_deposit_form(String upload_deposit_form) {
		this.upload_deposit_form = upload_deposit_form;
	}

	public double getUndeposited_amount() {
		return undeposited_amount;
	}

	public void setUndeposited_amount(double undeposited_amount) {
		this.undeposited_amount = undeposited_amount;
	}
  
	
}
