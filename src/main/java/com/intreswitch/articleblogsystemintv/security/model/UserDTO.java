package com.intreswitch.articleblogsystemintv.security.model;

import com.intreswitch.articleblogsystemintv.entities.Author;

import java.io.Serializable;

public class UserDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Author author;
	private String token;

	public UserDTO(Author author, String token) {
		this.author = author;
		this.token = token;
	}

	public Author getAuthor() {
		return author;
	}

	public void setAuthor(Author author) {
		this.author = author;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
}
