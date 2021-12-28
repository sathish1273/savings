package com.stackroute.keepnote.model;

import java.io.Serializable;
import java.time.LocalDate;
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
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;


@Entity
@org.hibernate.annotations.Proxy(lazy=false)
public class Customer implements Serializable {

	@javax.persistence.Id
	@Column
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="CUSTOMER_SEQ")
	@SequenceGenerator(name = "CUSTOMER_SEQ", sequenceName = "CUSTOMER_SEQ", allocationSize = 1)
	    private long customerUid;
	
	@Column
	private long customerId;
		
		@Column
		private String customerName;
		
		@Column
		private String fatherName;
		
		@Column
		private String phoneNo;
		
		@Column
		private String gmail;
		
		@Column
		private String photo;
		
		@Column
		private String signature;
		
		@ManyToOne(fetch = FetchType.EAGER)
	    @JoinColumn(name = "occurrance_uid")
		private Occurrance occurrance ;
		
		@Column
		private LocalDate createdDate;
		
		@Column
		private String createdBy;
		
		
		@OneToOne(fetch = FetchType.EAGER,optional = false)
	    @JoinColumn(name = "address_uid")
		private Address address;
		
		@ManyToOne(fetch = FetchType.EAGER)
		@JoinColumn(name = "institution_uid")
		private Institution institution;
		
		@Column
		private int status;
		
	    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
	    private Set<Deposits> deposits;
		
	    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
	    private Set<Loans> loans;
	    
	    @Column
	    private double share;
	    
		@ManyToOne(fetch = FetchType.EAGER)
	    @JoinColumn(name = "withdrawalOccur_uid")
		private Occurrance withDrawalOccurrance;
		
		
		
		public long getCustomerId() {
			return customerId;
		}

		public void setCustomerId(long customerId) {
			this.customerId = customerId;
		}

		public Occurrance getWithDrawalOccurrance() {
			return withDrawalOccurrance;
		}

		public void setWithDrawalOccurrance(Occurrance withDrawalOccurrance) {
			this.withDrawalOccurrance = withDrawalOccurrance;
		}

		public double getShare() {
			return share;
		}

		public void setShare(double share) {
			this.share = share;
		}

		public Set<Deposits> getDeposits() {
			return deposits;
		}

		public void setDeposits(Set<Deposits> deposits) {
			this.deposits = deposits;
		}

		public Set<Loans> getLoans() {
			return loans;
		}

		public void setLoans(Set<Loans> loans) {
			this.loans = loans;
		}

		public int getStatus() {
			return status;
		}

		public void setStatus(int status) {
			this.status = status;
		}

		public String getSignature() {
			return signature;
		}

		public void setSignature(String signature) {
			this.signature = signature;
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

		public LocalDate getCreatedDate() {
			return createdDate;
		}

		public void setCreatedDate(LocalDate createdDate) {
			this.createdDate = createdDate;
		}

		public String getCreatedBy() {
			return createdBy;
		}

		public void setCreatedBy(String createdBy) {
			this.createdBy = createdBy;
		}

		public long getCustomerUid() {
			return customerUid;
		}

		public void setCustomerUid(long customerUid) {
			this.customerUid = customerUid;
		}

		public String getFatherName() {
			return fatherName;
		}

		public void setFatherName(String fatherName) {
			this.fatherName = fatherName;
		}

		public String getPhoneNo() {
			return phoneNo;
		}

		public void setPhoneNo(String phoneNo) {
			this.phoneNo = phoneNo;
		}

		public String getGmail() {
			return gmail;
		}

		public void setGmail(String gmail) {
			this.gmail = gmail;
		}

		

		public String getPhoto() {
			return photo;
		}

		public void setPhoto(String photo) {
			this.photo = photo;
		}

		public long getCustomer_uid() {
			return customerUid;
		}

		public void setCustomer_uid(long customer_uid) {
			this.customerUid = customer_uid;
		}

		public String getCustomerName() {
			return customerName;
		}

		public void setCustomerName(String customerName) {
			this.customerName = customerName;
		}

		/*
		 * public Groups getGroup() { return group; }
		 * 
		 * public void setGroup(Groups group) { this.group = group; }
		 */

		public Address getAddress() {
			return address;
		}

		public void setAddress(Address address) {
			this.address = address;
		}
		
		
		
		
}
