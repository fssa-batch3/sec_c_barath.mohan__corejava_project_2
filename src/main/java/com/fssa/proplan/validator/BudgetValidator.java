package com.fssa.proplan.validator;

import java.util.List;

import com.fssa.proplan.errormessages.BudgetValidationError;
import com.fssa.proplan.exceptions.BudgetValidationException;
import com.fssa.proplan.model.Budget;
import com.fssa.proplan.model.BudgetCategory;

public class BudgetValidator {

	public boolean isValidMonthlyIncome(double monthlyIncome) throws BudgetValidationException {
		// check if it's a positive value.
		if (monthlyIncome <= 0) {
			throw new BudgetValidationException(BudgetValidationError.INVALID_MONTHLY_INCOME);
		}
		return true;
	}

	public boolean isValidExpensePercentage(double expensePercentage) throws BudgetValidationException {
		// check if it's within a valid range (0-100).
		if (expensePercentage <= 0 && expensePercentage >= 100) {
			throw new BudgetValidationException(BudgetValidationError.INVALID_EXPENSE_PERCENTAGE);
		}
		return true;
	}

	public boolean isValidSavingsPercentage(double savingsPercentage) throws BudgetValidationException {
		// check if it's within a valid range (0-100).
		if (savingsPercentage <= 0 && savingsPercentage >= 100) {
			throw new BudgetValidationException(BudgetValidationError.INVALID_EXPENSE_PERCENTAGE);
		}
		return true;
	}

	public boolean isValidBudgetAmount(double budgetAmount) throws BudgetValidationException {
		// check if it's a positive value.
		if (budgetAmount <= 0) {
			throw new BudgetValidationException(BudgetValidationError.INVALID_BUDGET_AMOUNT);
		}
		return true;
	}

	public boolean isValidBudgetCategoryList(List<BudgetCategory> budgetCategoryList) throws BudgetValidationException {
		// check if the list is not null and not empty.
		if (budgetCategoryList == null && budgetCategoryList.isEmpty()) {
			throw new BudgetValidationException(BudgetValidationError.INVALID_BUDGET_CATEGORY);

		}
		return true;
	}

	public boolean isValidBudget(Budget budget) throws BudgetValidationException {

		if (budget == null) {
			throw new BudgetValidationException(BudgetValidationError.NULL_OBJECT);
		}

		return isValidMonthlyIncome(budget.getMonthlyIncome())
				&& isValidExpensePercentage(budget.getExpensePercentage())
				&& isValidSavingsPercentage(budget.getSavingsPercentage())
				&& isValidBudgetAmount(budget.getBudgetAmount())
				&& isValidBudgetCategoryList(budget.getBudgetCategory());
	}
}
