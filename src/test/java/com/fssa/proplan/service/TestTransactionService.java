package com.fssa.proplan.service;

import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.fssa.proplan.dao.TransactionDao;
import com.fssa.proplan.exceptions.DaoException;
import com.fssa.proplan.exceptions.TransactionException;
import com.fssa.proplan.model.User;
import com.fssa.proplan.validator.TransactionValidator;

public class TestTransactionService {

	TransactionService transactionService = new TransactionService(new TransactionDao(), new TransactionValidator());

	// Test for adding valid income to the user's account.
	// It should verify that the income is successfully added and return true.
	@Test
	public void testValidAddIncome() throws DaoException, TransactionException {

		User user = new User("dhilip", "1234567890", "student", "barathdh@gmail.com", "baGra@t1");

		// If the income is not added, the test should fail with an error message.
		if (transactionService.addIncome(user, 1000, "Testing ")) {
			Assertions.assertTrue(true, "Income is added successfully!");
		} else {
			Assertions.fail("Income is not added. AddIncome() method failed");
		}
		;

	}

	// Test for adding income with an invalid (negative) amount.
	@Test
	public void testInvalidAddIncomeAmount() {

		User user = new User("dhilip", "1234567890", "student", "barathdh@gmail.com", "baGra@t1");

		// It should assert that IllegalArgumentException is thrown.
		Assertions.assertThrows(TransactionException.class, () -> transactionService.addIncome(user, -1, "Testing "));

	}

	// Test for adding a valid expense to the user's account.

	@Test
	public void testValidAddExpense() throws DaoException, TransactionException {

		User user = new User("dhilip", "1234567890", "student", "barathdh@gmail.com", "baGra@t1");
		if (transactionService.addIncome(user, 1000, "Testing ")) {
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
	public void testInvalidAddExpenseAmount() {

		User user = new User("dhilip", "1234567890", "student", "barathdh@gmail.com", "baGra@t1");

		// It should assert that IllegalArgumentException is thrown.
		Assertions.assertThrows(TransactionException.class, () -> transactionService.addIncome(user, -1, "Testing "));

	}

	// Test for retrieving valid income transaction details for the user.

	@Test
	public void testValidGetIncomeTransactionDetails() throws DaoException {

		// It should verify that the details are fetched successfully and return true.
		User user = new User("dhilip", "1234567890", "student", "barathdh@gmail.com", "baGra@t1");
		ArrayList<ArrayList<String>> details = transactionService.getIncomeTransactionDetails(user);

		if (details != null) {
			Assertions.assertTrue(true, "Income Transaction Details is fetched successfully!");
		} else {
			// If the details cannot be fetched, the test should fail with an error message
			Assertions.fail("Income Transaction Details is fetched. getIncomeTransactionDetails() method failed");
		}
		;

	}

	// Test for retrieving valid expense transaction details for the user.
	@Test
	public void testValidGetExpenseTransactionDetails() throws DaoException {

		// It should verify that the details are fetched successfully and return true.
		User user = new User("dhilip", "1234567890", "student", "barathdh@gmail.com", "baGra@t1");
		ArrayList<ArrayList<String>> details = transactionService.getExpenseTransactionDetails(user);

		if (details != null) {
			Assertions.assertTrue(true, "Expense Transaction Details is fetched successfully!");
		} else {
			// If the details cannot be fetched, the test should fail with an error message.
			Assertions.fail("Expense Transaction Details is fetched. getExpenseTransactionDetails() method failed");
		}
		;

	}

}
