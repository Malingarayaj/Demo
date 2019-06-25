package com.xworkz.securepassword.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.xworkz.securepassword.controller.SignUpController;

@Entity
@Table(name = "UserEntity")

@NamedQueries({
	@NamedQuery(name = "fetchByNameMailMno", query = "from UserEntity userEntity where email=:sEmail or mobileNo=:sMobileNo or userName=:sName"),
	@NamedQuery(name = "fetchUserByName", query = "from UserEntity s where userName =: username"),
	@NamedQuery(name = "findUserByNamePwd", query = "from UserEntity where userName=:newName"),
	@NamedQuery(name = "logInBYNamePwd", query = "from UserEntity where userName=:usrname"),
	@NamedQuery(name = "disableAccount",query="update UserEntity user set disable='true' where userName=:username"),
	@NamedQuery(name = "enableAccount",query="update UserEntity user set disable='false' where userName=:username"),
	@NamedQuery(name="fetchByNameMailMobileNo",query="from UserEntity userEntity where email=:email and userName=:username and mobileNo=:mobileNo")

})
public class UserEntity implements Serializable {
	private static Logger logger = LoggerFactory.getLogger(UserEntity.class);

	@Id
	@Column(name = "S_USERNAME")
	private String userName;
	@Column(name = "S_EMAIL")
	private String email;
	@Column(name = "S_MOBILENO")
	private Long mobileNo;
	@Column(name = "S_TYPE")
	private String type;
	@Column(name = "S_PASSWORD")
	private String password;
	@Column(name = "S_SECUREPHRASE")
	private String securePhrase;
	@Column(name = "S_FIRSTLOG")
	private boolean firstLog;
	@Column(name = "S_LOGFAILED")
	private int logFailed;
	@Column(name = "DISABLE")
	private boolean disable;

	public UserEntity() {
		logger.info("OBJECT IS CREATED:" + this.getClass().getSimpleName());
	}

	public static Logger getLogger() {
		return logger;
	}

	public static void setLogger(Logger logger) {
		UserEntity.logger = logger;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSecurePhrase() {
		return securePhrase;
	}

	public void setSecurePhrase(String securePhrase) {
		this.securePhrase = securePhrase;
	}

	public boolean isFirstLog() {
		return firstLog;
	}

	public void setFirstLog(boolean firstLog) {
		this.firstLog = firstLog;
	}

	public int getLogFailed() {
		return logFailed;
	}

	public void setLogFailed(int logFailed) {
		this.logFailed = logFailed;
	}

	public boolean isDisable() {
		return disable;
	}

	public void setDisable(boolean disable) {
		this.disable = disable;
	}

	@Override
	public String toString() {
		return "UserEntity [userName=" + userName + ", email=" + email + ", mobileNo=" + mobileNo + ", type=" + type
				+ ", password=" + password + ", securePhrase=" + securePhrase + ", firstLog=" + firstLog
				+ ", logFailed=" + logFailed + ", disable=" + disable + "]";
	}

}
