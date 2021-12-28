package com.stackroute.keepnote.model;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

@Entity
public class InterestType implements Serializable {
	 
	@javax.persistence.Id
	@Column
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="INTERESTTYPE_SEQ")
	@SequenceGenerator(name = "INTERESTTYPE_SEQ", sequenceName = "INTERESTTYPE_SEQ", allocationSize = 1)
	    private long interestType_uid;
	    
	    @Column
		private int transactionType;
		
		@Column
		private String interestType;
		
		@Column
		private String calculationType;
		
		@Column
		//@NotNull(message = "Please enter InterestRate")
		//@Size(min = 1,max = 100,message = "InterestRate should in between 1 to 100")
		private long interestRate;
		
		@Column
		private long idealDepositAmount;
		
		@Column
		private double fixedInterestAmount;
		
		@Column
		private double fine;
		
		@Column
		private int status;
		
		@ManyToOne(fetch = FetchType.EAGER)
		@JoinColumn(name = "institution_uid")
		private Institution institution;
		
		
		

		public double getFine() {
			return fine;
		}

		public void setFine(double fine) {
			this.fine = fine;
		}

		public long getIdealDepositAmount() {
			return idealDepositAmount;
		}

		public void setIdealDepositAmount(long idealDepositAmount) {
			this.idealDepositAmount = idealDepositAmount;
		}

		public long getInterestRate() {
			return interestRate;
		}

		public void setInterestRate(long intrestRate) {
			this.interestRate = intrestRate;
		}

		public int getTransactionType() {
			return transactionType;
		}

		public void setTransactionType(int transactionType) {
			this.transactionType = transactionType;
		}

		public Institution getInstitution() {
			return institution;
		}

		public void setInstitution(Institution institution) {
			this.institution = institution;
		}

		public long getInterestType_uid() {
			return interestType_uid;
		}

		public void setInterestType_uid(long interestType_uid) {
			this.interestType_uid = interestType_uid;
		}

		public String getInterestType() {
			return interestType;
		}

		public void setInterestType(String interestType) {
			this.interestType = interestType;
		}

		public String getCalculationType() {
			return calculationType;
		}

		public void setCalculationType(String calculationType) {
			this.calculationType = calculationType;
		}

		public double getFixedInterestAmount() {
			return fixedInterestAmount;
		}

		public void setFixedInterestAmount(double fixedInterestAmounn) {
			this.fixedInterestAmount = fixedInterestAmounn;
		}

		public int getStatus() {
			return status;
		}

		public void setStatus(int status) {
			this.status = status;
		}

		
}
