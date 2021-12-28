package com.stackroute.keepnote.model;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

@Entity
@org.hibernate.annotations.Proxy(lazy=false)
public class Institution implements Serializable {
	
	
	@Id
	@Column
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="INSTITUTION_SEQ")
	@SequenceGenerator(name = "INSTITUTION_SEQ", sequenceName = "INSTITUTION_SEQ", allocationSize = 1)
	private long institution_uid;
	
	@Column
	private String institutionName;
	
	@Column
	private String institutionLogo;
	
	@Column
	private int msgScheduledDate;
	
	/*
	 * @Column private LocalDate sangamStartDate;
	 */
	
	@OneToOne(optional = false)
	private Address address_id;
	
	@Column
	private int status;
	
	@Column
	private String messagesRequired;
	

	public String getMessagesRequired() {
		return messagesRequired;
	}

	public void setMessagesRequired(String messagesRequired) {
		this.messagesRequired = messagesRequired;
	}

	public int getMsgScheduledDate() {
		return msgScheduledDate;
	}

	public void setMsgScheduledDate(int msgScheduledDate) {
		this.msgScheduledDate = msgScheduledDate;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public long getInstitution_uid() {
		return institution_uid;
	}

	public void setInstitution_uid(long institution_uid) {
		this.institution_uid = institution_uid;
	}

	public String getInstitutionName() {
		return institutionName;
	}

	public void setInstitutionName(String institutionName) {
		this.institutionName = institutionName;
	}

	public String getInstitutionLogo() {
		return institutionLogo;
	}

	public void setInstitutionLogo(String institutionLogo) {
		this.institutionLogo = institutionLogo;
	}

	public Address getAddress_id() {
		return address_id;
	}

	public void setAddress_id(Address address_id) {
		this.address_id = address_id;
	}
	
	
	
	
	

}
