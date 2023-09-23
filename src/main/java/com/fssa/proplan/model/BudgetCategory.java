package com.fssa.proplan.model;

import java.util.List;

public class BudgetCategory {

	private String categoryName;
	private double budgetAmount;
	private double amountSpent;
	private List<Transaction> transactions;
	
	public String getCategoryName() { 
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public double getBudgetAmount() {
		return budgetAmount;
	}
	public void setBudgetAmount(double budgetAmount) {
		this.budgetAmount = budgetAmount;
	}
	public double getAmountSpent() {
		return amountSpent;
	}
	public void setAmountSpent(double amountSpent) {
		this.amountSpent = amountSpent;
	}
	public List<Transaction> getTransactions() {
		return transactions;
	}
	public void setTransactions(List<Transaction> transactions) {
		this.transactions = transactions;
	}
	@Override
	public String toString() {
		return "BudgetCategory [categoryName=" + categoryName + ", budgetAmount=" + budgetAmount + ", amountSpent="
				+ amountSpent + ", transactions=" + transactions + "]";
	}

	
	
}
