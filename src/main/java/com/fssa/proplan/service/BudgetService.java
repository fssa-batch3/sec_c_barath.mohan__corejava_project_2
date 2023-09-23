package com.fssa.proplan.service;

import com.fssa.proplan.dao.BudgetDao;
import com.fssa.proplan.dao.UserDao;
import com.fssa.proplan.exceptions.BudgetValidationException;
import com.fssa.proplan.exceptions.DaoException;
import com.fssa.proplan.model.Budget;
import com.fssa.proplan.model.User;
import com.fssa.proplan.validator.BudgetValidator;

public class BudgetService {

	private BudgetValidator budgetValidator;
	private BudgetDao budgetDao;
	private UserDao userDao;

	public BudgetService(BudgetValidator budgetValidator, BudgetDao budgetDao, UserDao userDao) {

		this.budgetValidator = budgetValidator;
		this.budgetDao = budgetDao;
		this.userDao = userDao;
	}

	public boolean createBudget(Budget budget) throws DaoException, BudgetValidationException {

		if (budgetValidator.isValidBudget(budget) && userDao.isUserExist(budget.getUser())) {
			if (!budgetDao.isBudgetExists(budget.getUser())) {
				budgetDao.addBudget(budget); 
				return true;
			} else {
				throw new BudgetValidationException("Budget already created");
			}

		}
		return false;
	}
 
	public int getBudgetIdByUser(User user) throws DaoException {

		if (userDao.isUserExist(user)) {
			return budgetDao.getBudgetIdByUser(user);
		}
		return -1; 
	}
	
	public Budget getBudgetByUser(User user) throws DaoException {

		if (userDao.isUserExist(user)) {
			return budgetDao.getBudgetByUser(user);
		}
		return null;
	
	}
	

}
