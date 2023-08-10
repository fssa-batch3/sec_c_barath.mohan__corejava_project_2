package com.fssa.proplan.model;

import com.fssa.proplan.enumclass.TransactionType;

// This class represents a financial transaction
public class Transaction {

	// Enum that defines different types of transactions (e.g., credit, debit)
	private TransactionType transactionType;

	// User involved in the transaction
	private User user1;

	// Amount of money involved in the transaction
	private double amount;

	// Additional information or notes about the transaction
	private String remarks;

	// Constructor to create a new Transaction object
	public Transaction(User user, TransactionType transactionType, double amount, String remarks) {
		this.transactionType = transactionType;
		this.user1 = user;
		this.amount = amount; 
		this.remarks = remarks;
	}

	// Getter method to retrieve the type of the transaction
	public TransactionType getTransactionType() {
		return transactionType;
	}

	// Getter method to retrieve the user involved in the transaction
	public User getUser() {
		return user1;
	}

	// Getter method to retrieve the amount of the transaction
	public double getAmount() {
		return amount;
	}

	// Getter method to retrieve any remarks or notes about the transaction
	public String getRemarks() {
		return remarks;
	}
}
