package com.fssa.proplan.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.fssa.proplan.exceptions.DaoException;
import com.fssa.proplan.logger.Logger;
import com.fssa.proplan.model.Budget;
import com.fssa.proplan.model.BudgetCategory;
import com.fssa.proplan.model.User;

public class BudgetDao {

	public boolean addBudget(Budget budget) throws DaoException {

		try (Connection con = ConnectionUtil.getSchemaConnection()) {

			String query = "INSERT INTO budget(monthly_income,expense_percentage,budget_amount,user_id, amount_spent) VALUES(?,?,?,?,?)";

			try (PreparedStatement psmt = con.prepareStatement(query)) {

				psmt.setDouble(1, budget.getMonthlyIncome());
				psmt.setDouble(2, budget.getExpensePercentage());
				psmt.setDouble(3, budget.getBudgetAmount());
				psmt.setInt(4, UserDao.getUserIdByEmail(budget.getUser().getEmailId()));
				psmt.setDouble(5, 0);

				int rowAffected = psmt.executeUpdate();

				addBudgetCategories(budget.getBudgetCategory(),
						UserDao.getUserIdByEmail(budget.getUser().getEmailId()));
				Logger.info(rowAffected + "row/rows affected");
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new DaoException(ex.getMessage());
		}
		return true;

	}

	public boolean addBudgetCategories(List<BudgetCategory> budgetCategories, int userId) throws DaoException {

		try (Connection con = ConnectionUtil.getSchemaConnection()) {

			// SQL query to insert a new record with user_id and initial balance of 0

			for (BudgetCategory budgetCategory : budgetCategories) {

				String query = "INSERT INTO budget_categories(user_id,category_name,budget_amount,amount_spent) VALUES(?,?,?,?)";

				try (PreparedStatement psmt = con.prepareStatement(query)) {

					psmt.setDouble(1, userId);
					psmt.setString(2, budgetCategory.getCategoryName());
					psmt.setDouble(3, budgetCategory.getBudgetAmount());
					psmt.setDouble(4, 0);

					int rowAffected = psmt.executeUpdate();

					Logger.info(rowAffected + "row/rows affected & Balance has been set 0");
				}
			}

		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new DaoException(ex.getMessage());
		}
		return true;

	}

	public int getBudgetIdByUser(User user) throws DaoException {

		try (Connection con = ConnectionUtil.getSchemaConnection()) {

			String query = "SELECT budget_id FROM budget WHERE user_id=?";

			try (PreparedStatement psmt = con.prepareStatement(query)) {

				psmt.setInt(1, UserDao.getUserIdByEmail(user.getEmailId()));

				try (ResultSet rs = psmt.executeQuery()) {

					if (rs.next()) {
						int budgetId = rs.getInt("budget_id");
						return budgetId;
					}
				}

				Logger.info("Fetched Budget Id using UserId");

			}

		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new DaoException(ex.getMessage());
		}
		return -1;
 
	}

	public boolean isBudgetExists(User user) throws DaoException {

		try (Connection con = ConnectionUtil.getSchemaConnection()) {

			String query = "SELECT budget_id FROM budget WHERE user_id=?";

			try (PreparedStatement psmt = con.prepareStatement(query)) {

				psmt.setInt(1, UserDao.getUserIdByEmail(user.getEmailId()));

				try (ResultSet rs = psmt.executeQuery()) {

					if (rs.next()) {
						Logger.info("User already created budget!");
						return true;
					}
				}

			}

		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new DaoException(ex.getMessage());
		}
		return false;

	}

	public Budget getBudgetByUser(User user) throws DaoException {

		try (Connection con = ConnectionUtil.getSchemaConnection()) {

			String query = "SELECT monthly_income,expense_percentage,budget_amount,amount_spent FROM budget WHERE user_id=?";

			try (PreparedStatement psmt = con.prepareStatement(query)) {

				psmt.setInt(1, UserDao.getUserIdByEmail(user.getEmailId()));

				try (ResultSet rs = psmt.executeQuery()) {
					Budget budget;
					if (rs.next()) {
						budget = new Budget();
						budget.setBudgetAmount(rs.getDouble("budget_amount"));
						double expensePercentage = rs.getDouble("expense_percentage");
						budget.setExpensePercentage(expensePercentage);
						budget.setMonthlyIncome(rs.getDouble("monthly_income"));
						budget.setSavingsPercentage(100 - expensePercentage);
						budget.setAmountSpent(rs.getDouble("amount_spent"));
						budget.setBudgetCategory(
								getBudgetCategoriesByUserId(UserDao.getUserIdByEmail(user.getEmailId())));
						Logger.info("Budget object is fetched successfully");
						return budget;
					}
				}

			}

		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new DaoException(ex.getMessage());
		}
		return null;

	}

	public ArrayList<BudgetCategory> getBudgetCategoriesByUserId(int userId) throws DaoException {

		try (Connection con = ConnectionUtil.getSchemaConnection()) {

			String query = "SELECT category_name,budget_amount,amount_spent FROM budget_categories WHERE user_id=?";

			try (PreparedStatement psmt = con.prepareStatement(query)) {

				psmt.setInt(1, userId);

				try (ResultSet rs = psmt.executeQuery()) {
					ArrayList<BudgetCategory> budgetCategories = new ArrayList<BudgetCategory>();
					while (rs.next()) {
						BudgetCategory category = new BudgetCategory();
						category.setAmountSpent(rs.getDouble("amount_spent"));
						category.setBudgetAmount(rs.getDouble("budget_amount"));
						category.setCategoryName(rs.getString("category_name"));
						budgetCategories.add(category);

					}
					Logger.info("Budget categories are fetched successfully");
					return budgetCategories;
				}

			}

		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new DaoException(ex.getMessage());
		}

	}

	public static int getCategoryIdByName(String categoryName, User user) throws DaoException {

		try (Connection con = ConnectionUtil.getSchemaConnection()) {

			System.out.println("cat name " + categoryName);
			System.out.println("user id" + UserDao.getUserIdByEmail(user.getEmailId()));
			String query = "SELECT category_id FROM budget_categories WHERE user_id=? AND category_name=?";

			try (PreparedStatement psmt = con.prepareStatement(query)) {

				psmt.setInt(1, UserDao.getUserIdByEmail(user.getEmailId()));
				psmt.setString(2, categoryName);

				try (ResultSet rs = psmt.executeQuery()) {

					if (rs.next()) {
						int categoryId = rs.getInt("category_id");
						return categoryId;
					}
				}

				Logger.info("Fetched Category Id using UserId");

			}

		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new DaoException(ex.getMessage());
		}
		return -1;

	}

	public static double getCategoryAmountSpent(int categoryId) throws DaoException {

		try (Connection con = ConnectionUtil.getSchemaConnection()) {

			String query = "SELECT amount_spent FROM budget_categories WHERE category_id=?";

			try (PreparedStatement psmt = con.prepareStatement(query)) {

				psmt.setInt(1, categoryId);

				try (ResultSet rs = psmt.executeQuery()) {

					if (rs.next()) {

						return rs.getDouble("amount_spent");

					}
				}

				Logger.info("Fetched Category Amount spent using Category Id");

			}

		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new DaoException(ex.getMessage());
		}
		return -1;

	}

	public static boolean addCategoryExpense(int categoryId, double amount) throws DaoException {

		try (Connection con = ConnectionUtil.getSchemaConnection()) {

			String query = "UPDATE budget_categories SET amount_spent=? WHERE category_id=?";

			try (PreparedStatement psmt = con.prepareStatement(query)) {

				double amountSpent = getCategoryAmountSpent(categoryId);
				amountSpent += amount;
				psmt.setDouble(1, amountSpent);
				psmt.setInt(2, categoryId);

				int rowAffected = psmt.executeUpdate();

				Logger.info(rowAffected + "row/rows affected & Category Amount spent has been updated");

			}

		} catch (

		SQLException ex) {
			ex.printStackTrace();
			throw new DaoException(ex.getMessage());
		}
		return true;

	}

	public static double getTotalCategoryAmountSpent(User user) throws DaoException {

		try (Connection con = ConnectionUtil.getSchemaConnection()) {

			String query = "SELECT amount_spent FROM budget_categories WHERE user_id=?";

			try (PreparedStatement psmt = con.prepareStatement(query)) {

				psmt.setInt(1, UserDao.getUserIdByEmail(user.getEmailId()));

				try (ResultSet rs = psmt.executeQuery()) {

					double totalAmountSpent = 0;
					while (rs.next()) {

						totalAmountSpent += rs.getDouble("amount_spent");

					}
					Logger.info("Fetched All Category Amount spent using user Id");
					return totalAmountSpent;
				}

			}

		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new DaoException(ex.getMessage());
		}

	}
	
	public static double getBudgetAmountSpent(int userId) throws DaoException {

		try (Connection con = ConnectionUtil.getSchemaConnection()) {

			String query = "SELECT amount_spent FROM budget WHERE user_id=?";

			try (PreparedStatement psmt = con.prepareStatement(query)) {

				psmt.setInt(1, userId);

				try (ResultSet rs = psmt.executeQuery()) {

					if (rs.next()) {

						return rs.getDouble("amount_spent");

					}
				}

				Logger.info("Fetched Category Amount spent using Category Id");

			}

		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new DaoException(ex.getMessage());
		}
		return -1;

	}
	
	public static boolean addBudgetExpense(int userId, double amount) throws DaoException {

		try (Connection con = ConnectionUtil.getSchemaConnection()) {

			String query = "UPDATE budget SET amount_spent=? WHERE user_id=?";

			try (PreparedStatement psmt = con.prepareStatement(query)) {

				double amountSpent = getBudgetAmountSpent(userId);
				amountSpent += amount;
				psmt.setDouble(1, amountSpent);
				psmt.setInt(2, userId);

				int rowAffected = psmt.executeUpdate();

				Logger.info(rowAffected + "row/rows affected & Budget Amount spent has been updated");

			}

		} catch (

		SQLException ex) {
			ex.printStackTrace();
			
			throw new DaoException(ex.getMessage());
		}
		return true;

	}
	
	

}
