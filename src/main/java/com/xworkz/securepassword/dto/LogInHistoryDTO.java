package com.xworkz.securepassword.dto;

import java.util.Date;

public class LogInHistoryDTO {

	private int lId;
	private String userName;
	private Date lastLogIn;
	
	public LogInHistoryDTO() {
		System.out.println("created\t"+this.getClass().getSimpleName());
	}

	public int getlId() {
		return lId;
	}

	public void setlId(int lId) {
		this.lId = lId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Date getLastLogIn() {
		return lastLogIn;
	}

	public void setLastLogIn(Date lastLogIn) {
		this.lastLogIn = lastLogIn;
	}

	@Override
	public String toString() {
		return "LogInHistoryDTO [lId=" + lId + ", userName=" + userName + ", lastLogIn=" + lastLogIn + "]";
	}
}
