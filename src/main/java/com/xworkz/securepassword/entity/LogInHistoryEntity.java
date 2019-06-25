package com.xworkz.securepassword.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "LOGIN_HISTORY")
@NamedQueries({
	@NamedQuery(name = "getLogInHistory", query = "from LogInHistoryEntity where lId=( select max(lId) from LogInHistoryEntity where userName=:userName)")
})
public class LogInHistoryEntity {
	@Id
	@GenericGenerator(name = "login", strategy = "increment")
	@GeneratedValue(generator = "login")
	@Column(name = "LOG_IN_ID")
	private int lId;
	@Column(name = "LOGGED_USERNAME")
	private String userName;
	@Column(name = "LAST_LOGIN_DATE")
	private Date lastLogIn;

	public LogInHistoryEntity() {
		System.out.println("created\t" + this.getClass().getSimpleName());
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
		return "LogInHistoryEntity [lId=" + lId + ", userName=" + userName + ", lastLogIn=" + lastLogIn + "]";
	}
}
