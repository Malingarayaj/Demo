package com.xworkz.securepassword.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "USER_CREDENTIAL")
@NamedQueries({
		@NamedQuery(name = "checkUser", query = "from UserCredentialEntity entity where appName=:aName and loggedUser=:user"),
		@NamedQuery(name = "getAllCredentials", query = "from UserCredentialEntity entity where loggedUser=:userName"),
		@NamedQuery(name = "deleteCredentials", query = "delete from UserCredentialEntity entity where appName=:aName and loggedUser=:userName")})
public class UserCredentialEntity implements Serializable {

	@Id
	@Column(name = "APP_NAME")
	private String appName;
	@Column(name = "APP_LOGGEDUSER")
	private String loggedUser;
	@Column(name = "APP_URL")
	private String appUrl;
	@Column(name = "APP_USERID")
	private String appUserId;
	@Column(name = "APP_PASSWORD")
	private String appPassword;
	@Column(name = "APP_NOTE")
	private String appNote;

	public UserCredentialEntity() {
		System.out.println("created.....\t" + this.getClass().getSimpleName());
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
		return "UserCredentialEntity [appName=" + appName + ", loggedUser=" + loggedUser + ", appUrl=" + appUrl
				+ ", appUserId=" + appUserId + ", appPassword=" + appPassword + ", appNote=" + appNote + "]";
	}
}