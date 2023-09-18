package com.fssa.proplan.errormessages;

public interface BudgetValidationError{

	public static final String NULL_OBJECT = "The Budget cannot be null";
	public static final String INVALID_MONTHLY_INCOME = "The Monthly income should be greater than 0";
	public static final String INVALID_BUDGET_AMOUNT = "The Budget amount should be greater than 0";
	public static final String INVALID_EXPENSE_PERCENTAGE = "The Expense Percentage value should be above 0 and below 100";
	public static final String INVALID_SAVINGS_PERCENTAGE = "The Savings Percentage value should be above 0 and below 100";
	public static final String INVALID_BUDGET_CATEGORY = "The Budget Category cannot be null or empty";
}
