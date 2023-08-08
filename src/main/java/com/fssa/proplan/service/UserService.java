package com.fssa.proplan.service;

import java.util.ArrayList;
import com.fssa.proplan.dao.BalanceDao;
import com.fssa.proplan.dao.TransactionDao;
import com.fssa.proplan.dao.UserDao;
import com.fssa.proplan.exceptions.DaoException;
import com.fssa.proplan.exceptions.UserException;
import com.fssa.proplan.model.User;
import com.fssa.proplan.validator.UserValidator;

public class UserService {
	private UserDao userDao;
	private UserValidator userValidator;

	public UserService(UserDao userDao, UserValidator userValidator) {
		this.userDao = userDao;
		this.userValidator = userValidator;
	}
	


	// Adds a new user to the database if the user is valid and doesn't already
	// exist.
	public boolean addUser(User user) throws DaoException, UserException {
		// Check if the user is valid based on the userValidator
		if (userValidator.isValidUser(user)) {
			// Check if the user already exists in the database
			if (userDao.isUserExist(user)) {
				throw new DaoException("User already exists");
			}
			// Add the user to the database
			userDao.addUser(user);
			System.out.println("User is added to DB successfully!");
			return true;
		}
		
		return false;
	}

	// Clears all user-related data from the database, including balance and
	// transaction details.
	public boolean clearAll() throws DaoException {
		// Clear all balance details from the database
		BalanceDao.clearAllBalanceDetails();

		// Clear all transaction details from the database
		TransactionDao.clearAllTransactionDetails();

		// Clear all user details from the database
		userDao.clearAllUsers();

		return true;
	}

	// Method to get all user email addresses from the DAO
	public ArrayList<String> getAllUserEmails() throws DaoException {

		// ArrayList to store the user email addresses.
		ArrayList<String> userNames = userDao.getAllUserEmails();

		return userNames;
	}

	// Method to check if a user exists based on the provided user object
	public boolean isUserExist(User user) throws DaoException, UserException {

		// Validate the user object using the UserValidator
		if (userValidator.isValidUser(user)) {

			// Check if the user exists in the DAO
			return userDao.isUserExist(user);
		}

		return false;
	} 

	// Method to delete a user based on the provided user object
//	public boolean deleteUser(User user) throws DaoException, UserException {
//
//		// Validate the user object using the UserValidator
//		if (userValidator.isValidUser(user)) {
//
//			// Check if the user exists in the DAO
//			if (userDao.isUserExist(user)) {
//
//				// If the user exists, delete the user using the UserDao
//				userDao.deleteUser(user);
//			}
//		}
//
//		return false;
//	}

}
