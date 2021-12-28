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
public class Groups implements Serializable {
	
	    @javax.persistence.Id
	    @Column
	    @GeneratedValue(strategy=GenerationType.SEQUENCE,generator="GROUP_SEQ")
		@SequenceGenerator(name = "GROUP_SEQ", sequenceName = "GROUP_SEQ", allocationSize = 1)
	    private long group_uid;
		
		@Column
		private String groupName;
		
		
		@ManyToOne(fetch = FetchType.LAZY, optional = false)
	    @JoinColumn(name = "institution_uid", nullable = false)
		private Institution institution;
		
		
		@ManyToOne(fetch = FetchType.LAZY, optional = false)
	    @JoinColumn(name = "address_uid", nullable = false)
		private Address address;
		
		@Column
		private String groupLogo;

		public long getGroup_uid() {
			return group_uid;
		}

		public void setGroup_uid(long group_uid) {
			this.group_uid = group_uid;
		}

		public String getGroupName() {
			return groupName;
		}

		public void setGroupName(String groupName) {
			this.groupName = groupName;
		}

		public Institution getInstitution() {
			return institution;
		}

		public void setInstitution(Institution institution) {
			this.institution = institution;
		}

		public Address getAddress() {
			return address;
		}

		public void setAddress(Address address) {
			this.address = address;
		}

		public String getGroupLogo() {
			return groupLogo;
		}

		public void setGroupLogo(String groupLogo) {
			this.groupLogo = groupLogo;
		}

		
		

}
