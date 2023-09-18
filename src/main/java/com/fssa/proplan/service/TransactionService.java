package com.fssa.proplan.service;

import java.util.List;

import com.fssa.proplan.dao.BalanceDao;
import com.fssa.proplan.dao.TransactionDao;
import com.fssa.proplan.dao.UserDao;
import com.fssa.proplan.enumclass.TransactionType;
import com.fssa.proplan.errormessages.TransactionError;
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
	private UserDao userDao;

	// Constructor to initialize the TransactionService with the provided
	// TransactionDao and TransactionValidator.
	public TransactionService(TransactionDao transactionDao, TransactionValidator transactionValidator,
			UserDao userDao) {
		this.transactionDao = transactionDao;
		this.transactionValidator = transactionValidator;
		this.userDao = userDao;
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
		if (!userDao.isUserExist(transaction.getUser())) {
			throw new DaoException(UserValidationErrors.USER_NOT_EXISTS);
		}
  
		// Determine the type of transaction (income or expense)
		if (transaction.getTransactionType() == TransactionType.INCOME) {
			// Add the income transaction to the database
			transactionDao.addIncome(transaction.getUser(), transaction.getAmount(), transaction.getRemarks());
			logger.debug("Income is added successfully");
		} else {  
			double balance=getBalance(transaction.getUser());
			if(balance<transaction.getAmount()) {
				throw new TransactionException(TransactionError.INVALID_WITHDRAW_LIMIT_REACHES);
			}
			// Add the expense transaction to the database
			transactionDao.addExpense(transaction);
			logger.debug("Expense is added successfully");
		}

		// Return true to indicate successful transaction addition
		return true;
	}

	// Static method to get the income transaction details for the given user.
	public List<Transaction> getIncomeTransactionDetails(User user) throws DaoException {
		// Check if the user is null or doesn't exist in the UserDao.
		if (user == null || !userDao.isUserExist(user)) {
			throw new DaoException(UserValidationErrors.USER_NOT_EXISTS);
		}

		// Retrieve the income transaction details from the TransactionDao.
		return TransactionDao.getIncomeTransactionDetails(user);
	}

	
	
	// Static method to get the expense transaction details for the given user.
	public List<Transaction> getExpenseTransactionDetails(User user) throws DaoException {
		// Check if the user is null or doesn't exist in the UserDao.
		if (user == null || !userDao.isUserExist(user)) {
			throw new DaoException(UserValidationErrors.USER_NOT_EXISTS);
		}

		// Retrieve the expense transaction details from the TransactionDao.
		return TransactionDao.getExpenseTransactionDetails(user);
	}
	
	
	
	
	public List<Transaction> getAllTransactionDetails(User user) throws DaoException {
		// Check if the user is null or doesn't exist in the UserDao.
		if (user == null || !userDao.isUserExist(user)) {
			throw new DaoException(UserValidationErrors.USER_NOT_EXISTS);
		}

		// Retrieve the all transaction details from the TransactionDao.
		return TransactionDao.getAllTransactionDetails(user);
	}
	
	public double getBalance(User user) throws DaoException {
		// Check if the user is null or doesn't exist in the UserDao.
		if (user == null || !userDao.isUserExist(user)) {
			throw new DaoException(UserValidationErrors.USER_NOT_EXISTS);
		}

		// Retrieve the all transaction details from the TransactionDao.
		return BalanceDao.getBalanceByUser(user);
	}
	
	
	public double getTotalIncome(User user) throws DaoException {
	
		if (user == null || !userDao.isUserExist(user)) {
			throw new DaoException(UserValidationErrors.USER_NOT_EXISTS);
		} 

		return TransactionDao.getTotalIncome(user);
	}

	
	
	public double getTotalExpense(User user) throws DaoException {
		if (user == null || !userDao.isUserExist(user)) {
			throw new DaoException(UserValidationErrors.USER_NOT_EXISTS);
		}

		return TransactionDao.getTotalExpense(user);
	}
	
	
	
	
	
 
}
