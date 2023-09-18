package com.fssa.proplan.model;

public class BudgetCategory {

	private String categoryName;
	private double budgetAmount;
	private double amountSpent;
	
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
	@Override
	public String toString() {
		return "BudgetCategory [categoryName=" + categoryName + ", budgetAmount=" + budgetAmount + ", amountSpent="
				+ amountSpent + "]";
	}
	
	
}
