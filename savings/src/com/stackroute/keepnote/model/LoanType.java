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
public class LoanType implements Serializable {
	
	   @javax.persistence.Id
		@Column
		@GeneratedValue(strategy = GenerationType.SEQUENCE)
	    private long loanType_uid;
		
		@Column
		private String type;
		
		@ManyToOne(fetch = FetchType.EAGER)
		@JoinColumn(name = "institution_uid")
		private Institution institution;
		
		public Institution getInstitution() {
			return institution;
		}

		public void setInstitution(Institution institution) {
			this.institution = institution;
		}

		public long getLoanType_uid() {
			return loanType_uid;
		}

		public void setLoanType_uid(long loanType_uid) {
			this.loanType_uid = loanType_uid;
		}

		public String getType() {
			return type;
		}

		public void setType(String type) {
			this.type = type;
		}


		
		
		

}
