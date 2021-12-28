package com.stackroute.keepnote.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
@NamedNativeQueries({
@NamedNativeQuery(name = "getLastOccurrance", query = "SELECT  * from occurrance where rownum=1 order by occurrance_date desc",resultClass=Occurrance.class),
@NamedNativeQuery(name = "getUnpaidDeposits", query = "select * from occurrance where OCCURRANCE_UID not in (select deposits.OCCURRANCE_UID from deposits)", resultClass = Occurrance.class)
})

@Entity
@org.hibernate.annotations.Proxy(lazy=false)
public class Occurrance implements Serializable {
	@javax.persistence.Id
	@Column
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="OCCURRANCE_SEQ")
	@SequenceGenerator(name = "OCCURRANCE_SEQ", sequenceName = "OCCURRANCE_SEQ", allocationSize = 1)
	    private long occurranceUid;
	    
	    @ManyToOne(fetch = FetchType.EAGER)
	    @JoinColumn(name = "institution_uid")
	    private Institution institution;
	    
	    @Column
		private int transactionType;
		
		@Column
		private Date occurranceDate;
		
		@Column
		private String occurrancePlace;
		
		@Column
		private LocalDate coveredMonth;
		
		@Column
		private int status;
		
		@Column
		private String activehours;
		
		@ManyToOne(fetch = FetchType.EAGER)
	    @JoinColumn(name = "interestType_uid")
		private InterestType interestType;
		
		
		@OneToOne(fetch = FetchType.EAGER,optional = false)
	    @JoinColumn(name = "address_uid")
		private Address address;
		
		@Column
		private String userId;
		
		@ManyToOne(fetch = FetchType.EAGER)
		@JoinColumn(name = "ledgerId")
		private Ledger ledger ;
		
		@Column
		private String reason;
		
		

		public String getReason() {
			return reason;
		}

		public void setReason(String reason) {
			this.reason = reason;
		}

		public Ledger getLedger() {
			return ledger;
		}

		public void setLedger(Ledger ledger) {
			this.ledger = ledger;
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

		public InterestType getInterestType() {
			return interestType;
		}

		public void setInterestType(InterestType interestType) {
			this.interestType = interestType;
		}

		public long getOccurranceUid() {
			return occurranceUid;
		}

		public void setOccurranceUid(long occurranceUid) {
			this.occurranceUid = occurranceUid;
		}

		public Date getOccurranceDate() {
			return occurranceDate;
		}

		public void setOccurranceDate(Date occurranceDate) {
			this.occurranceDate = occurranceDate;
		}

		public String getOccurrancePlace() {
			return occurrancePlace;
		}

		public void setOccurrancePlace(String occurrancePlace) {
			this.occurrancePlace = occurrancePlace;
		}

	

		public LocalDate getCoveredMonth() {
			return coveredMonth;
		}

		public void setCoveredMonth(LocalDate coveredMonth) {
			this.coveredMonth = coveredMonth;
		}

		public int getStatus() {
			return status;
		}

		public void setStatus(int status) {
			this.status = status;
		}

		public String getActivehours() {
			return activehours;
		}

		public void setActivehours(String activehours) {
			this.activehours = activehours;
		}

		public Address getAddress() {
			return address;
		}

		public void setAddress(Address address) {
			this.address = address;
		}

		public String getUserId() {
			return userId;
		}

		public void setUserId(String userId) {
			this.userId = userId;
		}

	
		

		
}
