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
public class Receipt {
	

	@javax.persistence.Id
	@Column
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="RECEIPT_SEQ")
	@SequenceGenerator(name = "RECEIPT_SEQ", sequenceName = "RECEIPT_SEQ", allocationSize = 1)
	    private long receiptsUid;
	   
		@ManyToOne(fetch = FetchType.EAGER)
	    @JoinColumn(name = "customer_uid")
		private Customer customer;
		
		@Column
		private LocalDateTime generatedDate ;
		
		@Column
		private String createdBy;
		
		@Column
		private String updatedBy;
		
		@ManyToOne(fetch = FetchType.EAGER)
	    @JoinColumn(name = "occurrance_uid")
		private Occurrance occurrance ;
		
		@ManyToOne(fetch = FetchType.EAGER)
		@JoinColumn(name = "institution_uid")
		private Institution institution;
		
		@Column
		private String depositTransactionIds;
		
		@Column
		private String loanTransactionIds;
		
		@Column
		private String spLoanTransactionIds;

		public long getReceiptsUid() {
			return receiptsUid;
		}

		public void setReceiptsUid(long receiptsUid) {
			this.receiptsUid = receiptsUid;
		}

		public Customer getCustomer() {
			return customer;
		}

		public void setCustomer(Customer customer) {
			this.customer = customer;
		}

		public LocalDateTime getGeneratedDate() {
			return generatedDate;
		}

		public void setGeneratedDate(LocalDateTime generatedDate) {
			this.generatedDate = generatedDate;
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

		public Institution getInstitution() {
			return institution;
		}

		public void setInstitution(Institution institution) {
			this.institution = institution;
		}

		public String getDepositTransactionIds() {
			return depositTransactionIds;
		}

		public void setDepositTransactionIds(String depositTransactionIds) {
			this.depositTransactionIds = depositTransactionIds;
		}

		public String getLoanTransactionIds() {
			return loanTransactionIds;
		}

		public void setLoanTransactionIds(String loanTransactionIds) {
			this.loanTransactionIds = loanTransactionIds;
		}

		public String getSpLoanTransactionIds() {
			return spLoanTransactionIds;
		}

		public void setSpLoanTransactionIds(String spLoanTransactionIds) {
			this.spLoanTransactionIds = spLoanTransactionIds;
		}
		
		
		

}
