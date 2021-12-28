package com.stackroute.keepnote.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Interests implements Serializable {

	
	   @javax.persistence.Id
		@Column
		@GeneratedValue(strategy = GenerationType.SEQUENCE)
	    private long interests_uid;
		
		@Column
		private int interestRate;
		
		@Column
		private String status;
		
		@ManyToOne(fetch = FetchType.EAGER)
	    @JoinColumn(name = "interestType_uid")
		private InterestType interestType_id;
		
		@ManyToOne(fetch = FetchType.EAGER)
		@JoinColumn(name = "institution_uid")
		private Institution institution;
		
		public String getStatus() {
			return status;
		}

		public void setStatus(String status) {
			this.status = status;
		}

		public Institution getInstitution() {
			return institution;
		}

		public void setInstitution(Institution institution) {
			this.institution = institution;
		}

		public long getInterests_uid() {
			return interests_uid;
		}

		public void setInterests_uid(long interests_uid) {
			this.interests_uid = interests_uid;
		}

		public int getInterestRate() {
			return interestRate;
		}

		public void setInterestRate(int interestRate) {
			this.interestRate = interestRate;
		}

		public InterestType getInterestType_id() {
			return interestType_id;
		}

		public void setInterestType_id(InterestType interestType_id) {
			this.interestType_id = interestType_id;
		}
		
		
		
	
}
