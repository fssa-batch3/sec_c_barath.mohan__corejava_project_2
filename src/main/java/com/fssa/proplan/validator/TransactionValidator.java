package com.fssa.proplan.validator;

import com.fssa.proplan.enumclass.TransactionType;
import com.fssa.proplan.errormessages.TransactionError;
import com.fssa.proplan.errormessages.UserValidationErrors;
import com.fssa.proplan.exceptions.TransactionException;
import com.fssa.proplan.model.Transaction;
import com.fssa.proplan.model.User;

public class TransactionValidator {

	public boolean validateTransaction(Transaction transaction) throws TransactionException {
		return validateTransactionType(transaction.getTransactionType()) && validateUser(transaction.getUser())
				&& validateAmount(transaction.getAmount())&& validateRemarks(transaction.getRemarks());
	} 
 
	public boolean validateTransactionType(TransactionType transactionType) throws TransactionException {
		if (transactionType == null) {
			throw new TransactionException(TransactionError.INVALID_TRANSACTION_TYPE);
		} 
		return true;
	}  

	public boolean validateUser(User user) throws TransactionException {
		if (user == null) {
			throw new TransactionException(UserValidationErrors.INVALID_USER_NULL);
		}
		return true; // You can add more user validation logic here
	}

	public boolean validateAmount(double amount) throws TransactionException {
		if (amount <= 0) {

			throw new TransactionException(TransactionError.INVALID_AMOUNT_ZERO);
		}
		return true; // You can add more specific amount validation logic here
	}
	
	public boolean validateRemarks(String remarks) throws TransactionException {
		if (remarks.trim().isEmpty() || remarks==null) {
 
			throw new TransactionException(TransactionError.INVALID_REMARKS);
		}
		return true; // You can add more specific amount validation logic here
	}
	
}
