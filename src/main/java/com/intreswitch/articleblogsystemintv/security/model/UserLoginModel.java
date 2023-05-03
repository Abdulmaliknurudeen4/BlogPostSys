package com.intreswitch.articleblogsystemintv.security.model;

import java.io.Serializable;

public class UserLoginModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String username;
	private String password;

	public UserLoginModel() {
		// TODO Auto-generated constructor stub
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
