package com.fssa.proplan.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import com.fssa.proplan.exceptions.DaoException;
import com.fssa.proplan.model.User;

public class UserDao {

	// Adds a new user to the database based on the provided User object.
	public static boolean addUser(User user) throws DaoException {
		try (Connection con = ProplanDao.getSchemaConnection()) {
			// SQL query to insert a new user into the 'user' table.
			String query = "INSERT INTO user(name,phone_num,profession,email_id,password,active) "
					+ "VALUES(?,?,?,?,?,?)";

			// Prepares the SQL query with the provided user details.
			try (PreparedStatement psmt = con.prepareStatement(query)) {
				// Sets the user details in the PreparedStatement.
				psmt.setString(1, user.getName());
				psmt.setString(2, user.getPhoneNumber());
				psmt.setString(3, user.getProfession());
				psmt.setString(4, user.getEmailId());
				psmt.setString(5, user.getPassword());
				psmt.setInt(6, 1); // Sets the 'active' field to 1 indicating an active user.

				// Executes the insert query and returns the number of rows affected.
				int rowAffected = psmt.executeUpdate();

				// Prints the number of rows affected by the insert query.
				System.out.println(rowAffected + " row/rows affected");

				// Creates a new balance entry for the newly added user.
				try {
					BalanceDao.createNewUserBalance(user);
				} catch (DaoException ex) {
					throw new DaoException(ex.getMessage());
				}

			}

		} catch (SQLException e) {
			throw new DaoException(e.getMessage());
		}
		return true;
	}

	// Retrieves all user emails from the 'user' table and returns them as an
	// ArrayList of Strings.
	public static ArrayList<String> getAllUserEmails() throws DaoException {

		// ArrayList to store the user email addresses.
		ArrayList<String> userNames = new ArrayList<String>();

		try (Connection con = ProplanDao.getSchemaConnection()) {
			// SQL query to retrieve all email addresses from the 'user' table.
			String query = "SELECT email_id FROM user";

			// Creates a Statement object to execute the query.
			try (Statement smt = con.createStatement()) {

				// Executes the query and retrieves the results in a ResultSet.
				try (ResultSet resultSet = smt.executeQuery(query)) {

					// Iterates through the ResultSet and adds each email to the ArrayList.
					while (resultSet.next()) {
						userNames.add(resultSet.getString(1));
					}
				}
			}

		} catch (SQLException e) {
			throw new DaoException(e.getMessage());
		}

		return userNames;
	}

	// Clears all user records from the 'user' table.
	public static boolean clearAllUsers() throws DaoException {

		try (Connection con = ProplanDao.getSchemaConnection()) {
			// SQL query to delete all records from the 'user' table.
			String query = "DELETE FROM user";

			// Creates a Statement object to execute the delete query.
			try (Statement smt = con.createStatement()) {

				// Executes the delete query and retrieves the number of rows affected.
				int rowAffected = smt.executeUpdate(query);

				// Prints the number of rows affected by the delete query.
				System.out.println(rowAffected + " row/rows affected");

				System.out.println("User Table values are cleared");
			}

		} catch (SQLException e) {
			throw new DaoException(e.getMessage());
		}

		return true;
	}

	// Checks if a user with the provided details exists in the 'user' table.
	public static boolean isUserExist(User user) throws DaoException {
		// Retrieves all user email addresses from the 'user' table.
		ArrayList<String> userEmails = getAllUserEmails();

		// Checks if the provided user's email is in the list of user emails.
		return userEmails.contains(user.getEmailId());
	}

	// Retrieves the user_id of a user based on their name from the 'user' table.
	public static int getUserIdByName(String name) throws DaoException {

		int user_id = 0; 

		try (Connection con = ProplanDao.getSchemaConnection()) {
			// SQL query to retrieve the user_id based on the user's name.
			String query = "SELECT user_id FROM user where name=?";

			// Prepares the SQL query with the provided user name.
			try (PreparedStatement psmt = con.prepareStatement(query)) {

				// Sets the user name in the PreparedStatement.
				psmt.setString(1, name);

				// Executes the query and retrieves the results in a ResultSet.
				try (ResultSet rs = psmt.executeQuery();) {

					// Retrieves the user_id from the ResultSet.
					while (rs.next()) {
						user_id = rs.getInt("user_id");
					}

					return user_id;
				}

			}

		} catch (SQLException e) {
			throw new DaoException(e.getMessage());
		}
	}

	// Deletes a user from the 'user' table based on the provided User object.
	public static boolean deleteUser(User user) throws DaoException {

		// Retrieves the user_id of the user based on their name.
		int user_id = getUserIdByName(user.getName());

		try (Connection con = ProplanDao.getSchemaConnection()) {

			// SQL query to delete the user from the 'user' table.
			String query = "DELETE FROM user WHERE user_id = ?";

			// Prepares the SQL query with the user_id.
			try (PreparedStatement psmt = con.prepareStatement(query)) {

				// Sets the user_id in the PreparedStatement.
				psmt.setInt(1, user_id);

				// Executes the delete query.
				psmt.executeUpdate();

				psmt.close();
			}

			return true; 
		} catch (SQLException e) {
			throw new DaoException(e.getMessage());
		}
	}

}
