package com.fssa.proplan.service;

import java.util.ArrayList;
import java.util.List;

import com.fssa.proplan.dao.TransactionDao;
import com.fssa.proplan.dao.UserDao;
import com.fssa.proplan.enumclass.TransactionType;
import com.fssa.proplan.errormessages.UserValidationErrors;
import com.fssa.proplan.exceptions.DaoException;
import com.fssa.proplan.exceptions.TransactionException;
import com.fssa.proplan.logger.Logger;
import com.fssa.proplan.model.Transaction;
import com.fssa.proplan.model.User;
import com.fssa.proplan.validator.TransactionValidator;

public class TransactionService {

	static Logger logger = new Logger();
	private TransactionDao transactionDao;
	private TransactionValidator transactionValidator;

	// Constructor to initialize the TransactionService with the provided
	// TransactionDao and TransactionValidator.
	public TransactionService(TransactionDao transactionDao, TransactionValidator transactionValidator) {
		this.transactionDao = transactionDao;
		this.transactionValidator = transactionValidator;
	}

	/**
	 * Adds a new transaction to the system.
	 *
	 * @param transaction The transaction to be added.
	 * @return True if the transaction was added successfully, otherwise false.
	 * @throws DaoException         If there is an issue with the data access layer.
	 * @throws TransactionException If there is an issue with the transaction data.
	 */
	public boolean addTransaction(Transaction transaction) throws DaoException, TransactionException {
		// Validate the transaction before proceeding
		transactionValidator.validateTransaction(transaction);

		// Check if the user associated with the transaction exists
		if (!UserDao.isUserExist(transaction.getUser())) {
			throw new DaoException(UserValidationErrors.USER_NOT_EXISTS);
		}

		// Determine the type of transaction (income or expense)
		if (transaction.getTransactionType() == TransactionType.INCOME) {
			// Add the income transaction to the database
			transactionDao.addIncome(transaction.getUser(), transaction.getAmount(), transaction.getRemarks());
			logger.debug("Income is added successfully");
		} else {
			// Add the expense transaction to the database
			transactionDao.addExpense(transaction.getUser(), transaction.getAmount(), transaction.getRemarks());
			logger.debug("Expense is added successfully");
		}

		// Return true to indicate successful transaction addition
		return true;
	}

	// Static method to get the income transaction details for the given user.
	public List<ArrayList<String>> getIncomeTransactionDetails(User user) throws DaoException {
		// Check if the user is null or doesn't exist in the UserDao.
		if (user == null || !UserDao.isUserExist(user)) {
			throw new DaoException(UserValidationErrors.USER_NOT_EXISTS);
		}

		// Retrieve the income transaction details from the TransactionDao.
		return TransactionDao.getIncomeTransactionDetails(user);
	}

	// Static method to get the expense transaction details for the given user.
	public List<ArrayList<String>> getExpenseTransactionDetails(User user) throws DaoException {
		// Check if the user is null or doesn't exist in the UserDao.
		if (user == null || !UserDao.isUserExist(user)) {
			throw new DaoException(UserValidationErrors.USER_NOT_EXISTS);
		}

		// Retrieve the expense transaction details from the TransactionDao.
		return TransactionDao.getExpenseTransactionDetails(user);
	}

}
