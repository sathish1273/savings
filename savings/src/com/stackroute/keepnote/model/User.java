package com.stackroute.keepnote.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

@Entity
@org.hibernate.annotations.Proxy(lazy=false)
public class User { 

	@javax.persistence.Id
	@Column
	@GeneratedValue(strategy=GenerationType.SEQUENCE,generator="USER_SEQ")
	@SequenceGenerator(name = "USER_SEQ", sequenceName = "USER_SEQ", allocationSize = 1)
private long userId;
private String username;
private String password;
private String firstname;
private String lastname;
private String email;
private String phone;
private LocalDate createdAt;
private String placeOfBirth;
private String firstMobileNo;
private String favouritGame;
private String motherMaidenName;
private int loggedStatus;

@ManyToOne(fetch = FetchType.EAGER)
@JoinColumn(name = "institution_uid")
private Institution institution;


@OneToOne(fetch = FetchType.EAGER,optional = false)
@JoinColumn(name = "address_uid")
private Address address;

@ManyToOne(fetch = FetchType.EAGER)
@JoinColumn(name = "role_uid")
private Role role;

@Column
private int status;




public int getLoggedStatus() {
	return loggedStatus;
}

public void setLoggedStatus(int loggedStatus) {
	this.loggedStatus = loggedStatus;
}

public String getPlaceOfBirth() {
	return placeOfBirth;
}

public void setPlaceOfBirth(String placeOfBirth) {
	this.placeOfBirth = placeOfBirth;
}

public String getFirstMobileNo() {
	return firstMobileNo;
}

public void setFirstMobileNo(String firstMobileNo) {
	this.firstMobileNo = firstMobileNo;
}

public String getFavouritGame() {
	return favouritGame;
}

public void setFavouritGame(String favouritGame) {
	this.favouritGame = favouritGame;
}

public String getMotherMaidenName() {
	return motherMaidenName;
}

public void setMotherMaidenName(String motherMaidenName) {
	this.motherMaidenName = motherMaidenName;
}

public int getStatus() {
	return status;
}

public void setStatus(int status) {
	this.status = status;
}

public long getUserId() {
	return userId;
}

public void setUserId(long userId) {
	this.userId = userId;
}

public String getUsername() {
	return username;
}

public void setUsername(String username) {
	this.username = username;
}

public String getPassword() {
	return password;
}

public void setPassword(String password) {
	this.password = password;
}

public String getFirstname() {
	return firstname;
}

public void setFirstname(String firstname) {
	this.firstname = firstname;
}

public String getLastname() {
	return lastname;
}

public void setLastname(String lastname) {
	this.lastname = lastname;
}

public String getEmail() {
	return email;
}

public void setEmail(String email) {
	this.email = email;
}

public String getPhone() {
	return phone;
}

public void setPhone(String phone) {
	this.phone = phone;
}

public LocalDate getCreatedAt() {
	return createdAt;
}

public void setCreatedAt(LocalDate createdAt) {
	this.createdAt = createdAt;
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

public Role getRole() {
	return role;
}

public void setRole(Role role) {
	this.role = role;
}




}
