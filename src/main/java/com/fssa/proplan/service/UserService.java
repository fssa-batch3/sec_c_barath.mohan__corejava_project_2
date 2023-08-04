package com.fssa.proplan.service;

import java.sql.SQLException;

import com.fssa.proplan.dao.BalanceDao;
import com.fssa.proplan.dao.TransactionDao;
import com.fssa.proplan.dao.UserDao;
import com.fssa.proplan.model.User;
import com.fssa.proplan.validator.UserValidator;

public class UserService {
	private UserDao userDao;
	private UserValidator userValidator;

	public UserService(UserDao userDao, UserValidator userValidator) {
		this.userDao = userDao;
		this.userValidator = userValidator;
	}

	// Adds a new user to the database if the user is valid and doesn't already exist.
	public boolean addUser(User user) throws SQLException {
		// Check if the user is valid based on the userValidator
		if (userValidator.isValidUser(user)) {
			// Check if the user already exists in the database
			if (userDao.isUserExist(user)) {
				throw new IllegalArgumentException("User already exists");
			}
			// Add the user to the database
			userDao.addUser(user);
			System.out.println("User is added to DB successfully!");
			return true;
		}
		return false;
	}

	// Clears all user-related data from the database, including balance and transaction details.
	public boolean clearAll() throws SQLException {
		// Clear all balance details from the database
		BalanceDao.clearAllBalanceDetails();

		// Clear all transaction details from the database
		TransactionDao.clearAllTransactionDetails();

		// Clear all user details from the database
		userDao.clearAllUsers();

		return true;
	}

}
