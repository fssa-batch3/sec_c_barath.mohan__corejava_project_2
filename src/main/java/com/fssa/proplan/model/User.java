package com.fssa.proplan.model;


import com.fssa.proplan.logger.Logger;


public class User {
	static Logger logger = new Logger();

	private String name;
	private String displayName;
	private String phoneNumber;
	private String profession;
	private String emailId;
	private String password;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name.trim();
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber.trim();
	}

	public String getProfession() {
		return profession;
	}

	public void setProfession(String profession) {
		this.profession = profession.trim();
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId.trim();
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password.trim();
	}

	public User(String name, String phoneNumber, String profession, String emailId, String password) {
		this.name = name.trim();
		this.phoneNumber = phoneNumber.trim();
		this.profession = profession.trim();
		this.emailId = emailId.trim();
		this.password = password.trim();
	}

	public User() {
		logger.info("New Account is created successfully");
	}



}
