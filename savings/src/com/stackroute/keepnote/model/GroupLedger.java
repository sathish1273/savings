package com.stackroute.keepnote.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.SequenceGenerator;

@Entity
public class GroupLedger implements Serializable {

	        @javax.persistence.Id
			@Column
			@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="GROUPLEDGER_SEQ")
			@SequenceGenerator(name = "GROUPLEDGER_SEQ", sequenceName = "GROUPLEDGER_SEQ", allocationSize = 1)
		    private long group_ledger_uid;
			
	        @Column
	        private String group_members;
	        
	        @Column
	        private double group_amount_deposits;
	        
	        @Column
	        private double amount_loans;
	        
	        @Column
	        private double interests_amount_deposits;
	        
	        @Column
	        private double interests_amount_loans;
	        
	        @Column
	        private Date date;
	        
	        @Column
	    	private String new_entries;
	        
			
			@Column
			private String createdBy;
			
			@Column
			private String updatedBy;

			public long getGroup_ledger_uid() {
				return group_ledger_uid;
			}

			public void setGroup_ledger_uid(long group_ledger_uid) {
				this.group_ledger_uid = group_ledger_uid;
			}

			public String getGroup_members() {
				return group_members;
			}

			public void setGroup_members(String group_members) {
				this.group_members = group_members;
			}

			public double getGroup_amount_deposits() {
				return group_amount_deposits;
			}

			public void setGroup_amount_deposits(double group_amount_deposits) {
				this.group_amount_deposits = group_amount_deposits;
			}

			public double getAmount_loans() {
				return amount_loans;
			}

			public void setAmount_loans(double amount_loans) {
				this.amount_loans = amount_loans;
			}

			public double getInterests_amount_deposits() {
				return interests_amount_deposits;
			}

			public void setInterests_amount_deposits(double interests_amount_deposits) {
				this.interests_amount_deposits = interests_amount_deposits;
			}

			public double getInterests_amount_loans() {
				return interests_amount_loans;
			}

			public void setInterests_amount_loans(double interests_amount_loans) {
				this.interests_amount_loans = interests_amount_loans;
			}

			public Date getDate() {
				return date;
			}

			public void setDate(Date date) {
				this.date = date;
			}

			public String getNew_entries() {
				return new_entries;
			}

			public void setNew_entries(String new_entries) {
				this.new_entries = new_entries;
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
