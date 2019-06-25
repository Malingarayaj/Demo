package com.xworkz.securepassword.dto;

public class LogInDTO {

	public LogInDTO() {
		System.out.println("created.....\t" + this.getClass().getSimpleName());
	}
	
	private String userName;
	private String password;
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return "LogInDTO [userName=" + userName + ", password=" + password + "]";
	}
}
