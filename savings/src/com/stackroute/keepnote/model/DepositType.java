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
@org.hibernate.annotations.Proxy(lazy=false)
public class DepositType implements Serializable {

	    @javax.persistence.Id
		@Column
		@GeneratedValue(strategy = GenerationType.SEQUENCE)
	    private long depositTypeUid;
		
		@Column
		private String deposit_type;
		
		@Column
		private String amount;
		
		@ManyToOne(fetch = FetchType.EAGER)
		@JoinColumn(name = "institution_uid")
		private Institution institution;
		
		public Institution getInstitution() {
			return institution;
		}

		public void setInstitution(Institution institution) {
			this.institution = institution;
		}

		public long getDepositTypeUid() {
			return depositTypeUid;
		}

		public void setDepositTypeUid(long depositTypeUid) {
			this.depositTypeUid = depositTypeUid;
		}

		public String getDeposit_type() {
			return deposit_type;
		}

		public void setDeposit_type(String deposit_type) {
			this.deposit_type = deposit_type;
		}

		public String getAmount() {
			return amount;
		}

		public void setAmount(String amount) {
			this.amount = amount;
		}

		
		
	
}
