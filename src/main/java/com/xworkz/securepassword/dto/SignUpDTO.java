package com.xworkz.securepassword.dto;

public class SignUpDTO {
	private String email;
	private Long mobileNo;
	private String userName;
	private String type;
	private char[] password;

	public SignUpDTO() {
		System.out.println("object is created :" + this.getClass().getSimpleName());
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Long getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(Long mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public char[] getPassword() {
		return password;
	}

	public void setPassword(char[] password) {
		this.password = password;
	}

	public boolean isFirstLog() {
		// TODO Auto-generated method stub
		return false;
	}
}
