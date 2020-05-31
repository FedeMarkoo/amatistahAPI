package com.amatistah.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Users")
public class User {
	
	@Id
	private Integer userID;
	private String userName;
	private String password;
	private Boolean passwordReseted;

	public Integer getUserID() {
		return userID;
	}

	public void setUserID(Integer userID) {
		this.userID = userID;
	}

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

	public Boolean getPasswordReseted() {
		return passwordReseted;
	}

	public void setPasswordReseted(Boolean passwordReseted) {
		this.passwordReseted = passwordReseted;
	}

}
