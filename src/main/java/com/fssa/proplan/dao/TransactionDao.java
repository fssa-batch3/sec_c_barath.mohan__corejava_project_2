package com.fssa.proplan.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.fssa.proplan.enumclass.TransactionType;
import com.fssa.proplan.exceptions.DaoException;
import com.fssa.proplan.logger.Logger;
import com.fssa.proplan.model.Transaction;
import com.fssa.proplan.model.User;

public class TransactionDao {
	static Logger logger = new Logger();

	// Adds income transaction for the given user.
	public boolean addIncome(User user, double amount, String remarks) throws DaoException {
		try (Connection con = ConnectionUtil.getSchemaConnection()) {
			String query = "INSERT INTO transactions(user_id,transaction_type,date,amount,balance,remarks) "
					+ "VALUES(?,?,?,?,?,?)";

			try (PreparedStatement psmt = con.prepareStatement(query)) {

				java.util.Date utilDate = new java.util.Date();
				java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());

				// Calculate the new balance after adding the income.
				double balance = BalanceDao.getBalanceByUser(user) + amount;

				psmt.setInt(1, UserDao.getUserIdByEmail(user.getEmailId()));
				psmt.setString(2, TransactionType.INCOME.getStringValue());
				psmt.setDate(3, sqlDate);
				psmt.setDouble(4, amount);
				psmt.setDouble(5, balance);
				psmt.setString(6, remarks);

				int rowAffected = psmt.executeUpdate();

				// Update the user's balance after adding income.

				BalanceDao.updateUserBalance(user, balance);

				logger.info(rowAffected + " row/rows affected ");
			}

		} catch (SQLException e) {
			// TODO: Add printStackTrace();
			throw new DaoException(e.getMessage());
		}
		return true;
	}

	public boolean addExpense(User user, double amount, String remarks) throws DaoException {

		try (Connection con = ConnectionUtil.getSchemaConnection()) {
			String query = "INSERT INTO transactions(user_id,transaction_type,date,amount,balance,remarks) "
					+ "VALUES(?,?,?,?,?,?)";

			try (PreparedStatement psmt = con.prepareStatement(query)) {

				java.util.Date utilDate = new java.util.Date();
				java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());

				double balance = BalanceDao.getBalanceByUser(user) - amount;
				psmt.setInt(1, UserDao.getUserIdByEmail(user.getEmailId()));
				psmt.setString(2, TransactionType.EXPENSE.getStringValue());
				psmt.setDate(3, sqlDate);
				psmt.setDouble(4, amount);
				psmt.setDouble(5, balance);
				psmt.setString(6, remarks);

				int rowAffected = psmt.executeUpdate();

				BalanceDao.updateUserBalance(user, balance);

				logger.info(rowAffected + "row/rows affected ");
			}

		} catch (SQLException e) {
			throw new DaoException(e.getMessage());
		}
		return true;
	}

	public static boolean clearAllTransactionDetails() throws DaoException {

		try (Connection con = ConnectionUtil.getSchemaConnection()) {
			String query = "TRUNCATE TABLE transactions";

			try (Statement smt = con.createStatement()) {

				int rowAffected = smt.executeUpdate(query);

				logger.info(rowAffected + "row/rows affected ");

				logger.info("Transactions Table values are cleared");

			}

		} catch (SQLException ex) {
			throw new DaoException(ex.getMessage());
		}

		return true;
	}

	// Retrieves the transaction details (income/expense) for the given user and
	// type.
	public static List<Transaction> getTransactionDetails(User user, String type) throws DaoException {
		try (Connection con = ConnectionUtil.getSchemaConnection()) {

			String query = "SELECT transaction_type,date,amount,remarks FROM transactions where user_id=? AND transaction_type=?";

			// Get the user's ID from the user's name.
			int userId = UserDao.getUserIdByEmail(user.getEmailId());

			List<Transaction> transactionDetails;
			
			try (PreparedStatement psmt = con.prepareStatement(query)) {
 
				psmt.setInt(1, userId);
				psmt.setString(2, type);
				try (ResultSet rs = psmt.executeQuery()) {

					transactionDetails = new ArrayList<>();

					// Fetch the transaction details from the result set and add to the list.
					while (rs.next()) {
						Transaction transaction = new Transaction();
						transaction.setAmount(rs.getDouble("amount"));
						transaction.setRemarks(rs.getString("remarks"));
						transaction.setTransactionType(TransactionType.valueOf(rs.getString("transaction_type")));
						transaction.setDate(rs.getDate("date").toLocalDate());
						transaction.setUser(user);
						
						
						transactionDetails.add(transaction);
					}

					return transactionDetails;
				}

			}

		} catch (SQLException ex) {
			throw new DaoException(ex.getMessage());
		}

	}

	public static List<Transaction> getAllTransactionDetails(User user) throws DaoException {
		try (Connection con = ConnectionUtil.getSchemaConnection()) {

			String query = "SELECT transaction_type,date,balance,amount,remarks FROM transactions where user_id=? ";

			// Get the user's ID from the user's name.
			int userId = UserDao.getUserIdByEmail(user.getEmailId());

			try (PreparedStatement psmt = con.prepareStatement(query)) {

				psmt.setInt(1, userId);

				try (ResultSet rs = psmt.executeQuery()) {

					List<Transaction> transactionDetails = new ArrayList<>();

					// Fetch the transaction details from the result set and add to the list.
					while (rs.next()) {
						
						Transaction transaction = new Transaction();
						transaction.setAmount(rs.getDouble("amount"));
						transaction.setRemarks(rs.getString("remarks"));
						System.out.println(rs.getString("transaction_type"));
						transaction.setTransactionType(TransactionType.valueOf(rs.getString("transaction_type").toUpperCase()));
						transaction.setDate(rs.getDate("date").toLocalDate());
						transaction.setBalance(rs.getDouble("balance"));
						transactionDetails.add(transaction);
					}

					return transactionDetails;
				}

			}

		} catch (SQLException ex) {
			throw new DaoException(ex.getMessage());
		}

	}

	public static double getTotalTransactionAmount(User user, String transactionType) throws DaoException {

		try (Connection con = ConnectionUtil.getSchemaConnection()) {
			String query = "SELECT amount FROM transactions where transaction_type=? ";

			try (PreparedStatement psmt = con.prepareStatement(query)) {

				psmt.setString(1, transactionType);

				ResultSet rs = psmt.executeQuery();
				double amount = 0;
				while (rs.next()) {
					amount += rs.getDouble("amount");
				}

				logger.info("Total Transaction amount is fetched successfully ");
				return amount;
			}

		} catch (SQLException e) {
			throw new DaoException(e.getMessage());
		}

	}

	public static double getTotalIncome(User user) throws DaoException {

		return getTotalTransactionAmount(user, TransactionType.INCOME.getStringValue());
	}

	public static double getTotalExpense(User user) throws DaoException {
		return getTotalTransactionAmount(user, TransactionType.EXPENSE.getStringValue());

	}

	public static  List<Transaction> getIncomeTransactionDetails(User user) throws DaoException {
		// Retrieves the income transaction details for the given user.

		return getTransactionDetails(user, TransactionType.INCOME.getStringValue());
	}

	public static List<Transaction> getExpenseTransactionDetails(User user) throws DaoException {
		// Retrieves the expense transaction details for the given user.
		return getTransactionDetails(user, TransactionType.EXPENSE.getStringValue());

	}

}
