package com.stackroute.keepnote.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
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
public class Loans implements Serializable {

	
	@javax.persistence.Id
	@Column
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="LOANS_SEQ")
	@SequenceGenerator(name = "LOANS_SEQ", sequenceName = "LOANS_SEQ", allocationSize = 1)
	    private long loans_uid;
		
		
		@ManyToOne(fetch = FetchType.EAGER)
	    @JoinColumn(name = "customer_uid")
		private Customer customer ;
		
		
		@ManyToOne(fetch = FetchType.EAGER)
	    @JoinColumn(name = "interestType_uid")
		private InterestType interestsType ;
		
		@Column
		private Double loanPrinciple ;
		
	    @OneToMany(mappedBy = "loans", cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	    private Set<LoanHistory> loanHistory;
		
		@Column
		private int noOfInstallments;
		
		@Column
		private int paidInstallments;
		
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
		
		@Column
		private int typeOfEntry;
		
		@Column
		private String paidStatus;
		
		
		

		public int getTypeOfEntry() {
			return typeOfEntry;
		}

		public void setTypeOfEntry(int typeOfEntry) {
			this.typeOfEntry = typeOfEntry;
		}

		public String getPaidStatus() {
			return paidStatus;
		}

		public void setPaidStatus(String paidStatus) {
			this.paidStatus = paidStatus;
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
		
		


		public Set<LoanHistory> getLoanHistory() {
			return loanHistory;
		}

		public void setLoanHistory(Set<LoanHistory> loanHistory) {
			this.loanHistory = loanHistory;
		}

		public long getLoans_uid() {
			return loans_uid;
		}

		public void setLoans_uid(long loans_uid) {
			this.loans_uid = loans_uid;
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

		public Double getLoanPrinciple() {
			return loanPrinciple;
		}

		public void setLoanPrinciple(Double loanPrinciple) {
			this.loanPrinciple = loanPrinciple;
		}

		public int getNoOfInstallments() {
			return noOfInstallments;
		}

		public void setNoOfInstallments(int noOfInstallments) {
			this.noOfInstallments = noOfInstallments;
		}

		public int getPaidInstallments() {
			return paidInstallments;
		}

		public void setPaidInstallments(int paidInstallments) {
			this.paidInstallments = paidInstallments;
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

		
}
