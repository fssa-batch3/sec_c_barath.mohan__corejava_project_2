package com.fssa.proplan.service;

import java.util.ArrayList;

import com.fssa.proplan.dao.TransactionDao;
import com.fssa.proplan.dao.UserDao;
import com.fssa.proplan.exceptions.DaoException;
import com.fssa.proplan.exceptions.TransactionException;
import com.fssa.proplan.model.User;
import com.fssa.proplan.validator.TransactionValidator;

public class TransactionService {

	private TransactionDao transactionDao;
	private TransactionValidator transactionValidator;

	// Constructor to initialize the TransactionService with the provided
	// TransactionDao and TransactionValidator.
	public TransactionService(TransactionDao transactionDao, TransactionValidator transactionValidator) {
		this.transactionDao = transactionDao;
		this.transactionValidator = transactionValidator;
	}

	// Method to add income for the given user with the specified amount and
	// remarks.
	public boolean addIncome(User user, double amount, String remarks) throws DaoException, TransactionException {
		// Check if the user is null or doesn't exist in the UserDao.
		if (user == null || !UserDao.isUserExist(user)) {
			throw new DaoException("The user doesn't exist");
		}

		// Validate the amount of income using the TransactionValidator.
		if (transactionValidator.addIncome(amount)) {
			// If the amount is valid, add the income to the TransactionDao and print
			// success message.
			transactionDao.addIncome(user, amount, remarks);
			System.out.println("Income has been added successfully");
			return true;
		}

		// If the amount is not valid, return false.
		return false;
	}

	// Method to add an expense for the given user with the specified amount and
	// remarks.
	public boolean addExpense(User user, double amount, String remarks) throws DaoException, TransactionException {
		// Check if the user is null or doesn't exist in the UserDao.
		if (user == null || !UserDao.isUserExist(user)) {
			throw new DaoException("The user doesn't exist");
		}

		// Validate the amount of expense using the TransactionValidator.
		if (transactionValidator.addExpense(user, amount)) {
			// If the amount is valid, add the expense to the TransactionDao and print
			// success message.
			transactionDao.addExpense(user, amount, remarks);
			System.out.println("Expense has been added successfully");
			return true;
		}

		// If the amount is not valid, return false.
		return false;
	}

	// Static method to get the income transaction details for the given user.
	public static ArrayList<ArrayList<String>> getIncomeTransactionDetails(User user) throws DaoException {
		// Check if the user is null or doesn't exist in the UserDao.
		if (user == null || !UserDao.isUserExist(user)) {
			throw new DaoException("The user doesn't exist");
		}

		// Retrieve the income transaction details from the TransactionDao.
		return TransactionDao.getIncomeTransactionDetails(user);
	}

	// Static method to get the expense transaction details for the given user.
	public static ArrayList<ArrayList<String>> getExpenseTransactionDetails(User user) throws DaoException {
		// Check if the user is null or doesn't exist in the UserDao.
		if (user == null || !UserDao.isUserExist(user)) {
			throw new DaoException("The user doesn't exist");
		}

		// Retrieve the expense transaction details from the TransactionDao.
		return TransactionDao.getExpenseTransactionDetails(user);
	}

}
