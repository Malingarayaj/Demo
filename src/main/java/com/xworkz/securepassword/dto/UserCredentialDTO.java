package com.xworkz.securepassword.dto;

public class UserCredentialDTO {
	private String appName;
	private String loggedUser;
	private String appUrl;
	private String appUserId;
	private String appPassword;
	private String appNote;

	public UserCredentialDTO() {
		System.out.println("created.....\t" + this.getClass().getSimpleName());
	}

	public UserCredentialDTO(String appName, String loggedUser) {
		// TODO Auto-generated constructor stub
		this.appName = appName;
		this.loggedUser = loggedUser;
	}

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	public String getLoggedUser() {
		return loggedUser;
	}

	public void setLoggedUser(String loggedUser) {
		this.loggedUser = loggedUser;
	}

	public String getAppUrl() {
		return appUrl;
	}

	public void setAppUrl(String appUrl) {
		this.appUrl = appUrl;
	}

	public String getAppUserId() {
		return appUserId;
	}

	public void setAppUserId(String appUserId) {
		this.appUserId = appUserId;
	}

	public String getAppPassword() {
		return appPassword;
	}

	public void setAppPassword(String appPassword) {
		this.appPassword = appPassword;
	}

	public String getAppNote() {
		return appNote;
	}

	public void setAppNote(String appNote) {
		this.appNote = appNote;
	}

	@Override
	public String toString() {
		return "UserCredentialDTO [appName=" + appName + ", loggedUser=" + loggedUser + ", appUrl=" + appUrl
				+ ", appUserId=" + appUserId + ", appPassword=" + appPassword + ", appNote=" + appNote + "]";
	}
}
