package com.vgnit.shop.dto;

public class UserDto 
{
	private String fullname;
	private String gender;
	private String mobile;
	private String email;
	private String password;
	private String role;
	
	public String getFullname() {
		return fullname;
	}
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public UserDto(String fullname, String gender, String mobile, String email, String password, String role) {
		super();
		this.fullname = fullname;
		this.gender = gender;
		this.mobile = mobile;
		this.email = email;
		this.password = password;
		this.role = role;
	}
	public UserDto() {
		super();
		// TODO Auto-generated constructor stub
	}
}
