package com.fssa.proplan.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.fssa.proplan.dao.TransactionDao;
import com.fssa.proplan.dao.UserDao;
import com.fssa.proplan.enumclass.TransactionType;
import com.fssa.proplan.exceptions.DaoException;
import com.fssa.proplan.exceptions.TransactionException;
import com.fssa.proplan.model.Transaction;
import com.fssa.proplan.model.User;
import com.fssa.proplan.validator.TransactionValidator;

public class TestTransactionService {

	TransactionService transactionService = new TransactionService(new TransactionDao(), new TransactionValidator(), new UserDao());
	User user = new User("Mohan", "1234567890", "student", "barath@gmail.com", "baGra@t1");

	@Test
	void testValidAddIncome() throws DaoException, TransactionException {
 
		Transaction transaction = new Transaction(user, TransactionType.INCOME, 1000, "Testing ");
		// If the income is not added, the test should fail with an error message.
		if (transactionService.addTransaction(transaction)) {
			Assertions.assertTrue(true, "Income is added successfully!");
		} else {
			Assertions.fail("Income is not added. AddIncome() method failed"); 
		}

	}
 
	// Test for adding income with an invalid (negative) amount.
	@Test
	void testInvalidAddIncomeAmount() {

//		User user = new User("mohan", "1234567890", "student", ".com", "baGra@t1");
		Transaction transaction = new Transaction(user, TransactionType.INCOME, -1, "Testing ");

		// It should assert that IllegalArgumentException is thrown.
		Assertions.assertThrows(TransactionException.class, () -> transactionService.addTransaction(transaction));

	}

	// Test for adding a valid expense to the user's account.

	@Test
	void testValidAddExpense() throws DaoException, TransactionException {

//		User user = new User("mohan", "1234567890", "student", "barath@gmail.com", "baGra@t1");
		Transaction transaction = new Transaction(user, TransactionType.EXPENSE, 500, "Testing ");

		if (transactionService.addTransaction(transaction)) {
			// It should verify that the expense is successfully added and return true.
			Assertions.assertTrue(true, "Expense is added successfully!");
		} else {
			// If the expense is not added, the test should fail with an error message.
			Assertions.fail("Expense is not added. AddExpense() method failed");
		}
		;

	}

	// Test for adding expense with an invalid (negative) amount.

	@Test
	void testInvalidAddExpenseAmount() {

//		User user = new User("mohan", "1234567890", "student", "barath@gmail.com", "baGra@t1");
		Transaction transaction = new Transaction(user, TransactionType.EXPENSE, -1, "Testing ");

		// It should assert that IllegalArgumentException is thrown.
		Assertions.assertThrows(TransactionException.class, () -> transactionService.addTransaction(transaction));

	}

	// Test for retrieving valid income transaction details for the user.

	@Test
	void testValidGetIncomeTransactionDetails() throws DaoException {

		// It should verify that the details are fetched successfully and return true.
//		User user = new User("mohan", "1234567890", "student", "barath@gmail.com", "baGra@t1");

		List<ArrayList<String>> details = transactionService.getIncomeTransactionDetails(user);

		if (details != null) {
			Assertions.assertTrue(true, "Income Transaction Details is fetched successfully!");
		} else {
			// If the details cannot be fetched, the test should fail with an error message
			Assertions.fail("Income Transaction Details is f etched. getIncomeTransactionDetails() method failed");
		}
		;

	}

	// Test for retrieving valid expense transaction details for the user.
	@Test
	void testValidGetExpenseTransactionDetails() throws DaoException {

		// It should verify that the details are fetched successfully and return true.
//		User user = new User("mohan", "1234567890", "student", "barath@gmail.com", "baGra@t1");
		List<ArrayList<String>> details = transactionService.getExpenseTransactionDetails(user);

		if (details != null) {
			Assertions.assertTrue(true, "Expense Transaction Details is fetched successfully!");
		} else {
			// If the details cannot be fetched, the test should fail with an error message.
			Assertions.fail("Expense Transaction Details is fetched. getExpenseTransactionDetails() method failed");
		}
		;

	}

}
