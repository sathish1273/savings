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
public class LoanHistory {
	
	@javax.persistence.Id
	@Column
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="LOANHISTORY_SEQ")
	@SequenceGenerator(name = "LOANHISTORY_SEQ", sequenceName = "LOANHISTORY_SEQ", allocationSize = 1)
	    private long loanHistory_uid;
	   
	   @Column
	   private LocalDateTime loanPaidDate;
	   
	   @Column
	   private double paidInterest;
	   
	   @Column
	   private double paidAmount;
	   
		@ManyToOne(fetch = FetchType.EAGER)
		@JoinColumn(name = "institution_uid")
		private Institution institution;
	  
		@ManyToOne(fetch = FetchType.EAGER)
		@JoinColumn(name = "loansId")
		private Loans loans ;
	  
	  @Column
	  private long installmentId;
	  
	  @Column
	  private String paidStatus;
	  
		@ManyToOne(fetch = FetchType.EAGER)
	    @JoinColumn(name = "occurrance_uid")
		private Occurrance occurrance ;
		
		@Column
		private double fine;
		
		@ManyToOne(fetch = FetchType.EAGER)
	    @JoinColumn(name = "ledgerId")
		private Ledger ledger;
		
		@ManyToOne(fetch = FetchType.EAGER)
	    @JoinColumn(name = "customer_uid")
		private Customer customer ;
		
		
		
		
	   public Customer getCustomer() {
			return customer;
		}

		public void setCustomer(Customer customer) {
			this.customer = customer;
		}

	public Ledger getLedger() {
			return ledger;
		}

		public void setLedger(Ledger ledger) {
			this.ledger = ledger;
		}

	public double getFine() {
			return fine;
		}

		public void setFine(double fine) {
			this.fine = fine;
		}

	public Occurrance getOccurrance() {
			return occurrance;
		}

		public void setOccurrance(Occurrance occurrance) {
			this.occurrance = occurrance;
		}

	public String getPaidStatus() {
		return paidStatus;
	}

	public void setPaidStatus(String paidStatus) {
		this.paidStatus = paidStatus;
	}

	public long getInstallmentId() {
		return installmentId;
	}

	public void setInstallmentId(long installmentId) {
		this.installmentId = installmentId;
	}



		public Loans getLoans() {
		return loans;
	}

	public void setLoans(Loans loans) {
		this.loans = loans;
	}

		public Institution getInstitution() {
			return institution;
		}

		public void setInstitution(Institution institution) {
			this.institution = institution;
		}

		public long getLoanHistory_uid() {
			return loanHistory_uid;
		}

		public void setLoanHistory_uid(long loanHistory_uid) {
			this.loanHistory_uid = loanHistory_uid;
		}

		public LocalDateTime getLoanPaidDate() {
			return loanPaidDate;
		}

		public void setLoanPaidDate(LocalDateTime loanPaidDate) {
			this.loanPaidDate = loanPaidDate;
		}

		public Double getPaidInterest() {
			return paidInterest;
		}

		public void setPaidInterest(Double paidInterest) {
			this.paidInterest = paidInterest;
		}

		public Double getPaidAmount() {
			return paidAmount;
		}

		public void setPaidAmount(Double paidAmount) {
			this.paidAmount = paidAmount;
		}
		
		
		
	   

}
