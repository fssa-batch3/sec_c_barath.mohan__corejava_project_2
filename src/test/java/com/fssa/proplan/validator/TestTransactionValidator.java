package com.fssa.proplan.validator;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import com.fssa.proplan.enumclass.TransactionType;
import com.fssa.proplan.exceptions.TransactionException;
import com.fssa.proplan.model.Transaction;
import com.fssa.proplan.model.User;

class TestTransactionValidator {

	@Test
	void testValidateTransactionTypeValid() throws TransactionException {
		TransactionValidator validation = new TransactionValidator();
		assertTrue(validation.validateTransactionType(TransactionType.INCOME));
	}

	@Test
	void testValidateTransactionTypeInvalid() {
		TransactionValidator validation = new TransactionValidator();
		assertThrows(TransactionException.class, () -> validation.validateTransactionType(null));
	}

	@Test
	void testValidateUserValid() throws TransactionException {
		TransactionValidator validation = new TransactionValidator();
		User validUser = new User();
		assertTrue(validation.validateUser(validUser));
	}

	@Test
	void testValidateUserInvalid() {
		TransactionValidator validation = new TransactionValidator();
		assertThrows(TransactionException.class, () -> validation.validateUser(null));
	}

	@Test 
	void testValidateAmountValid() throws TransactionException {
		TransactionValidator validation = new TransactionValidator();
		assertTrue(validation.validateAmount(100.0));
	}
 
	@Test
	void testValidateAmountInvalid() {
		TransactionValidator validation = new TransactionValidator();
		assertThrows(TransactionException.class, () -> validation.validateAmount(-50.0));
	}

	@Test
	void testValidateTransactionValid() throws TransactionException {
		TransactionValidator validation = new TransactionValidator();
		Transaction validTransaction = new Transaction(new User(), TransactionType.EXPENSE, 100.0,"testing");
		assertTrue(validation.validateTransaction(validTransaction));
	}

	@Test
	void testValidateTransactionInvalid() {
		TransactionValidator validation = new TransactionValidator();
		Transaction invalidTransaction = new Transaction(null, null, -50.0,"testing");
		assertThrows(TransactionException.class, () -> validation.validateTransaction(invalidTransaction));
	}

}
