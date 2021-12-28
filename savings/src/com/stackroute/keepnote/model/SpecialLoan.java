package com.stackroute.keepnote.model;

import java.time.LocalDateTime;
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
public class SpecialLoan {
	@javax.persistence.Id
	@Column
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="SPECIALLOAN_SEQ")
	@SequenceGenerator(name = "SPECIALLOAN_SEQ", sequenceName = "SPECIALLOAN_SEQ", allocationSize = 1)
	    private long SpecialLoanUid;
	   
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "customer_uid")
	private Customer customer ;
	
	
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "interestType_uid")
	private InterestType interestsType ;
	
	@Column
	private double loanPrinciple ;
	
	@Column
	private String paidStatus;
	
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "occurrance_uid")
	private Occurrance occurrance ;
	
	@Column
	private int introducer_id;
	
	@Column
	private LocalDateTime loan_paid_date;
	
	@Column
	private String createdBy;
	
	@Column
	private String updatedBy;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "institution_uid")
	private Institution institution;
	
	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ledgerId")
	private Ledger ledger;
	
    @OneToMany(mappedBy = "sploans", cascade = CascadeType.ALL)
    private Set<SpLoanHistory> sploanHistory;
    
    @Column
	private int typeOfEntry;
    
    @Column
	private double interest;
    
    @Column
	private double fine;
    
    

	public double getInterest() {
		return interest;
	}

	public void setInterest(double interest) {
		this.interest = interest;
	}

	public double getFine() {
		return fine;
	}

	public void setFine(double fine) {
		this.fine = fine;
	}

	public int getTypeOfEntry() {
		return typeOfEntry;
	}

	public void setTypeOfEntry(int typeOfEntry) {
		this.typeOfEntry = typeOfEntry;
	}

	public Set<SpLoanHistory> getSploanHistory() {
		return sploanHistory;
	}

	public void setSploanHistory(Set<SpLoanHistory> sploanHistory) {
		this.sploanHistory = sploanHistory;
	}

	public long getSpecialLoanUid() {
		return SpecialLoanUid;
	}

	public void setSpecialLoanUid(long specialLoanUid) {
		SpecialLoanUid = specialLoanUid;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public InterestType getInterestsType() {
		return interestsType;
	}

	public void setInterestsType(InterestType interestsType) {
		this.interestsType = interestsType;
	}

	public double getLoanPrinciple() {
		return loanPrinciple;
	}

	public void setLoanPrinciple(double loanPrinciple) {
		this.loanPrinciple = loanPrinciple;
	}

	public String getPaidStatus() {
		return paidStatus;
	}

	public void setPaidStatus(String paidStatus) {
		this.paidStatus = paidStatus;
	}

	public Occurrance getOccurrance() {
		return occurrance;
	}

	public void setOccurrance(Occurrance occurrance) {
		this.occurrance = occurrance;
	}

	public int getIntroducer_id() {
		return introducer_id;
	}

	public void setIntroducer_id(int introducer_id) {
		this.introducer_id = introducer_id;
	}

	public LocalDateTime getLoan_paid_date() {
		return loan_paid_date;
	}

	public void setLoan_paid_date(LocalDateTime loan_paid_date) {
		this.loan_paid_date = loan_paid_date;
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
	
	

}
