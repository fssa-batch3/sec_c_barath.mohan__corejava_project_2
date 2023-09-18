package com.fssa.proplan.model;

import java.util.ArrayList;
import java.util.List;

public class Budget {

	private double monthlyIncome; 
	private double expensePercentage;
	private double savingsPercentage;
	private double budgetAmount; 
	private double amountSpent; 
	private ArrayList<BudgetCategory> budgetCategory;
	private User user;
	
	public double getMonthlyIncome() {
		return monthlyIncome;
	}
	public void setMonthlyIncome(double monthlyIncome) {
		this.monthlyIncome = monthlyIncome;
	}
	public double getExpensePercentage() {
		return expensePercentage;
	} 
	public void setExpensePercentage(double expensePercentage) {
		this.expensePercentage = expensePercentage;
	}
	public double getSavingsPercentage() {
		return savingsPercentage;
	}
	public void setSavingsPercentage(double savingsPercentage) {
		this.savingsPercentage = savingsPercentage;
	}
	public List<BudgetCategory> getBudgetCategory() {
		return budgetCategory;
	}
	public void setBudgetCategory(ArrayList<BudgetCategory> budgetCategory) {
		this.budgetCategory = budgetCategory;
	}
	public double getBudgetAmount() {
		return budgetAmount;
	}
	public void setBudgetAmount(double budgetAmount) {
		this.budgetAmount = budgetAmount;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public double getAmountSpent() {
		return amountSpent;
	}
	public void setAmountSpent(double amountSpent) {
		this.amountSpent = amountSpent;
	}
	@Override
	public String toString() {
		return "Budget [monthlyIncome=" + monthlyIncome + ", expensePercentage=" + expensePercentage
				+ ", savingsPercentage=" + savingsPercentage + ", budgetAmount=" + budgetAmount + ", amountSpent="
				+ amountSpent + ", budgetCategory=" + budgetCategory + ", user=" + user + "]";
	}

	
}
