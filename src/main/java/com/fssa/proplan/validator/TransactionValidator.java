package com.fssa.proplan.validator;

import com.fssa.proplan.dao.BalanceDao;
import com.fssa.proplan.errormessages.TransactionError;
import com.fssa.proplan.exceptions.DaoException;
import com.fssa.proplan.exceptions.TransactionException;
import com.fssa.proplan.model.User;

public class TransactionValidator {

	public boolean addIncome(double amount) throws TransactionException {

		if (amount < 1) {
			throw new TransactionException(TransactionError.INVALID_AMOUNT_ZERO);
		}

		return true;
	}

	public boolean addExpense(User user, double amount) throws TransactionException, DaoException {

		if (amount < 1) {
			throw new TransactionException(TransactionError.INVALID_AMOUNT_ZERO);
		}

		double balance = BalanceDao.getBalanceByUser(user);

		if (amount > balance) {
			throw new TransactionException(TransactionError.INVALID_WITHDRAW_LIMIT_REACHES);
		}

		return true;
	}

}
