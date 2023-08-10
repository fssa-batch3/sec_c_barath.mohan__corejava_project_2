package com.fssa.proplan.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.fssa.proplan.exceptions.DaoException;
import com.fssa.proplan.logger.Logger;
import com.fssa.proplan.model.User;

public class BalanceDao { 
	
	static Logger logger = new Logger();
	// Method to create a new user balance with initial value 0
	public static boolean createNewUserBalance(User user) throws DaoException {
 
		try (Connection con = ConnectionUtil.getSchemaConnection()) {

			// SQL query to insert a new record with user_id and initial balance of 0
			String query = "INSERT INTO balance(user_id,balance) VALUES(?,0)";

			try (PreparedStatement psmt = con.prepareStatement(query)) {

				// Set the user_id in the PreparedStatement using UserDao's method
				psmt.setInt(1, UserDao.getUserIdByEmail(user.getEmailId()));

				// Execute the update query to insert the new record
				int rowAffected = psmt.executeUpdate();

				logger.info(rowAffected + "row/rows affected & Balance has been set 0");
			}

		} catch (SQLException ex) {
			throw new DaoException(ex.getMessage());
		}
		return true; 
	}

	// Method to update the balance for a specific user
	public static boolean updateUserBalance(User user, double balance) throws DaoException {

		try (Connection con = ConnectionUtil.getSchemaConnection()) {

			// SQL query to update the balance of a user based on their user_id
			String query = "UPDATE balance SET balance = ? WHERE user_id = ?";

			try (PreparedStatement psmt = con.prepareStatement(query)) {

				// Set the new balance and user_id in the PreparedStatement using UserDao's
				// method
				psmt.setDouble(1, balance);
				psmt.setInt(2, UserDao.getUserIdByEmail(user.getEmailId()));

				// Execute the update query to update the user's balance
				int rowAffected = psmt.executeUpdate();

				logger.info(rowAffected + "row/rows affected & Balance has been updated");
			}

		} catch (SQLException ex) {
			throw new DaoException(ex.getMessage());
		}
		return true;
	}

	// Method to get the balance for a specific user
	public static double getBalanceByUser(User user) throws DaoException {

		try (Connection con = ConnectionUtil.getSchemaConnection()) {
			double balance = 0;

			// SQL query to fetch the balance for a user based on their user_id
			String query = "SELECT balance FROM balance WHERE user_id=?";

			try (PreparedStatement psmt = con.prepareStatement(query)) {

				// Set the user_id in the PreparedStatement using UserDao's method
				psmt.setInt(1, UserDao.getUserIdByEmail(user.getEmailId()));

				// Execute the query and get the result set
				try (ResultSet rs = psmt.executeQuery()) {

					// Check if there is a row with the user's balance and retrieve it
					if (rs.next()) {
						balance = rs.getDouble("balance");
					}

					logger.info("Balance Successfully fetched");
					
					return balance;
				}

			}

		} catch (SQLException ex) {
			throw new DaoException(ex.getMessage());
		}
	}

	// Method to clear all balance details in the 'balance' table
	public static boolean clearAllBalanceDetails() throws DaoException {

		try (Connection con = ConnectionUtil.getSchemaConnection()) {
			// SQL query to truncate (delete all records) the 'balance' table
			String query = "TRUNCATE TABLE balance";

			try (Statement smt = con.createStatement()) {

				// Execute the update query to clear all records in the 'balance' table
				int rowAffected = smt.executeUpdate(query);

				logger.info(rowAffected + "row/rows affected ");

				logger.info("Balance Table values are cleared");
			}

		} catch (SQLException ex) {
			throw new DaoException(ex.getMessage());
		}

		return true;
	}
}
