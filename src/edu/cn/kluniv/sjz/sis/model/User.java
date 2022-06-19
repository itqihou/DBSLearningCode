package edu.cn.kluniv.sjz.sis.model;

public class User {
	private String Account;
	private String Password;
	private int role;
	public User(String account, String password) {
		Account = account;
		Password = password;
	}
	public User(String account, String password, int role) {
		Account = account;
		Password = password;
		this.role = role;
	}
	public String getAccount() {
		return Account;
	}
	public void setAccount(String account) {
		Account = account;
	}
	public String getPassword() {
		return Password;
	}
	public void setPassword(String password) {
		Password = password;
	}
	public int getRole() {
		return role;
	}
	public void setRole(int role) {
		this.role = role;
	}
	
	
}
