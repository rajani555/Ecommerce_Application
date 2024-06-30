package com.vgnit.shop.dto;

import java.time.LocalDate;

public class AddressDTO 
{
	private Long id;
	private String firstName;
	private String lastName;
	private String streetAddress;
	private String city;
	private String state;
	private String zipCode;
	
	// Attention Here !
	private Long userId;
	
	private String mobile;
	private LocalDate createdAt;
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getStreetAddress() {
		return streetAddress;
	}

	public void setStreetAddress(String streetAddress) {
		this.streetAddress = streetAddress;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
	public LocalDate getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDate createdAt) {
		this.createdAt = createdAt;
	}

	public AddressDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public AddressDTO(Long id, String firstName, String lastName, String streetAddress, String city, String state,
			String zipCode, Long userId, String mobile, LocalDate createdAt) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.streetAddress = streetAddress;
		this.city = city;
		this.state = state;
		this.zipCode = zipCode;
		this.userId = userId;
		this.mobile = mobile;
		this.createdAt = createdAt;
	}

}
