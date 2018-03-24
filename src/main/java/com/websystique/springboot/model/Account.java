package com.websystique.springboot.model;

public class Account {
	private String id;
	private String password;
	private String token;
	public Account(String id, String password, String token) {
		super();
		this.id = id;
		this.password = password;
		this.token = token;
	}
	public Account(String id, String password) {
		super();
		this.id = id;
		this.password = password;
	}
	
	public Account() {
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	@Override
	public String toString() {
		return "Account [id=" + id + ", password=" + password + ", token=" + token + "]";
	}
	
	
	
	
	
}
