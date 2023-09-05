package com.fssa.proplan.model;

import java.time.LocalDate;

import com.fssa.proplan.enumclass.TransactionType;

// This class represents a financial transaction
public class Transaction {

	// Enum that defines different types of transactions (e.g., credit, debit)
	private TransactionType transactionType;

	// User involved in the transaction
	private User user;

	// Amount of money involved in the transaction
	private double amount;

	// Additional information or notes about the transaction
	private String remarks;

	private LocalDate date;
	
	private double balance;

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	// Constructor to create a new Transaction object
	public Transaction(User user, TransactionType transactionType, double amount, String remarks) {
		this.transactionType = transactionType;
		this.user = user;
		this.amount = amount;
		this.remarks = remarks;
	}

	public void setUser(User user1) {
		this.user = user1;
	}

	public void setTransactionType(TransactionType transactionType) {
		this.transactionType = transactionType;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public Transaction() {
		// empty constructor
	}

	// Getter method to retrieve the type of the transaction
	public TransactionType getTransactionType() {
		return transactionType;
	}

	// Getter method to retrieve the user involved in the transaction
	public User getUser() {
		return user;
	}

	// Getter method to retrieve the amount of the transaction
	public double getAmount() {
		return amount;
	}

	// Getter method to retrieve any remarks or notes about the transaction
	public String getRemarks() {
		return remarks;
	}

	@Override
	public String toString() {
		return "Transaction [transactionType=" + transactionType + ", user=" + user + ", amount=" + amount
				+ ", remarks=" + remarks + ", date=" + date + "]";
	}
}
