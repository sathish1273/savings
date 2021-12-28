 package com.stackroute.keepnote.model;

import java.io.Serializable;
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
public class Deposits implements Serializable {
	
	@javax.persistence.Id
	@Column
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="DEPOSITS_SEQ")
	@SequenceGenerator(name = "DEPOSITS_SEQ", sequenceName = "DEPOSITS_SEQ", allocationSize = 1)
	    private long DepositsUid;
	   
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
		private double interestPaid;
		
		@Column
		private double amountPaid;
		
		@ManyToOne(fetch = FetchType.EAGER)
		@JoinColumn(name = "institution_uid")
		private Institution institution;
		
		@ManyToOne(fetch = FetchType.EAGER)
	    @JoinColumn(name = "ledgerId")
		private Ledger ledger;
		
		@Column
		private double fine;
		
		@Column
		private int depositType;
		
		
		
		
		public int getDepositType() {
			return depositType;
		}

		public void setDepositType(int depositType) {
			this.depositType = depositType;
		}

		public double getFine() {
			return fine;
		}

		public void setFine(double fine) {
			this.fine = fine;
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
		
		

		public double getInterestPaid() {
			return interestPaid;
		}

		public void setInterestPaid(double interestPaid) {
			this.interestPaid = interestPaid;
		}

		public double getAmountPaid() {
			return amountPaid;
		}

		public void setAmountPaid(double amountPaid) {
			this.amountPaid = amountPaid;
		}

		public long getDepositsUid() {
			return DepositsUid;
		}

		public void setDepositsUid(long depositsUid) {
			DepositsUid = depositsUid;
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

}
