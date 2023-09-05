package com.fssa.proplan.model;


import com.fssa.proplan.dao.TransactionDao;
import com.fssa.proplan.dao.UserDao;
import com.fssa.proplan.exceptions.DaoException;
import com.fssa.proplan.logger.Logger;
import com.fssa.proplan.service.TransactionService;
import com.fssa.proplan.service.UserService;
import com.fssa.proplan.validator.TransactionValidator;
import com.fssa.proplan.validator.UserValidator;


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
		
	}

	public String toString() {
		String log="";
		log+="Name : "+this.name+"\n";
		log+="Display Name : "+this.displayName+"\n";
		log+="Email id : "+this.emailId+"\n";
		log+="PhNo : "+this.phoneNumber+"\n";
		log+="Profession : "+this.profession+"\n";
		log+="Password : "+this.password+"\n";
		
		return log;
	}
	
//	public static void main(String[] args) throws DaoException {
//		User user1 = new User("dhilip", "1234567890", "student", "barathdh@gmail.com", "baGra@t1");
//		UserService userService = new UserService(new UserDao(), new UserValidator());
//		TransactionService transactionService= new TransactionService(new TransactionDao(), new TransactionValidator(), new UserDao());
//		System.out.println(transactionService.getTotalIncome(user1));
//		System.out.println(transactionService.getTotalExpense(user1));
//		
//	}

}
