package com.fssa.proplan.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.fssa.proplan.exceptions.DaoException;
import com.fssa.proplan.logger.Logger;
import com.fssa.proplan.model.User;

public class UserDao {

	static Logger logger = new Logger();

	// Adds a new user to the database based on the provided User object.
	public boolean addUser(User user) throws DaoException {
		try (Connection con = ConnectionUtil.getSchemaConnection()) {
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
				logger.info(rowAffected + " row/rows affected");

				// Creates a new balance entry for the newly added user.

				BalanceDao.createNewUserBalance(user);

			}

		} catch (SQLException e) {
			throw new DaoException(e.getMessage());
		}
		return true;
	}
	
	
	// Adds a new user to the database based on the provided User object.
	public boolean updateUser(User user) throws DaoException {
		try (Connection con = ConnectionUtil.getSchemaConnection()) {
			// SQL query to insert a new user into the 'user' table.
			String query = "UPDATE user set name=?,phone_num=?,profession=?, display_name =? where email_id=?";

			// Prepares the SQL query with the provided user details.
			try (PreparedStatement psmt = con.prepareStatement(query)) {
				// Sets the user details in the PreparedStatement.
				psmt.setString(1, user.getName());
				psmt.setString(2, user.getPhoneNumber());
				psmt.setString(3, user.getProfession());
				psmt.setString(4, user.getDisplayName());
				psmt.setString(5, user.getEmailId());
	
				// Executes the insert query and returns the number of rows affected.
				int rowAffected = psmt.executeUpdate();
 
				// Prints the number of rows affected by the insert query.
				logger.info(rowAffected + " row/rows affected");

			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new DaoException(e.getMessage());
			
		}
		return true;
	}

	// Retrieves all user emails from the 'user' table and returns them as an
	// ArrayList of Strings.
	public List<String> getAllUserEmails() throws DaoException {

		// ArrayList to store the user email addresses.
		List<String> userNames = new ArrayList<>();

		try (Connection con = ConnectionUtil.getSchemaConnection()) {
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
	public boolean clearAllUsers() throws DaoException {

		try (Connection con = ConnectionUtil.getSchemaConnection()) {
			// SQL query to delete all records from the 'user' table.
			String query = "DELETE FROM user";

			// Creates a Statement object to execute the delete query.
			try (Statement smt = con.createStatement()) {

				// Executes the delete query and retrieves the number of rows affected.
				int rowAffected = smt.executeUpdate(query);

				// Prints the number of rows affected by the delete query.
				logger.info(rowAffected + " row/rows affected");

				logger.info("User Table values are cleared");
			}

		} catch (SQLException e) {
			throw new DaoException(e.getMessage());
		}

		return true;
	}

	// Checks if a user with the provided details exists in the 'user' table.
	public boolean isUserExist(User user) throws DaoException {
		// Retrieves all user email addresses from the 'user' table.
		List<String> userEmails = getAllUserEmails();

		// Checks if the provided user's email is in the list of user emails.
		return userEmails.contains(user.getEmailId());
	}

	// Retrieves the user_id of a user based on their name from the 'user' table.
	public static int getUserIdByEmail(String email) throws DaoException {

		int userId = 0;

		try (Connection con = ConnectionUtil.getSchemaConnection()) {
			// SQL query to retrieve the user_id based on the user's name.
			String query = "SELECT user_id FROM user where email_id=?";

			// Prepares the SQL query with the provided user name.
			try (PreparedStatement psmt = con.prepareStatement(query)) {

				// Sets the user name in the PreparedStatement.
				psmt.setString(1, email);

				// Executes the query and retrieves the results in a ResultSet.
				try (ResultSet rs = psmt.executeQuery();) {

					// Retrieves the user_id from the ResultSet.
					while (rs.next()) {
						userId = rs.getInt("user_id");
					}

					return userId;
				}

			}

		} catch (SQLException e) {
			throw new DaoException(e.getMessage());
		}
	}

	// Deletes a user from the 'user' table based on the provided User object.
	public static boolean deleteUser(User user) throws DaoException {

		// Retrieves the user_id of the user based on their name.
		int userId = getUserIdByEmail(user.getName());

		try (Connection con = ConnectionUtil.getSchemaConnection()) {

			// SQL query to delete the user from the 'user' table.
			String query = "DELETE FROM user WHERE user_id = ?";

			// Prepares the SQL query with the user_id.
			try (PreparedStatement psmt = con.prepareStatement(query)) {

				// Sets the user_id in the PreparedStatement.
				psmt.setInt(1, userId);

				// Executes the delete query.
				psmt.executeUpdate();

			}

			return true;
		} catch (SQLException e) {
			throw new DaoException(e.getMessage());
		}
	}

	// Deletes a user from the 'user' table based on the provided User object.
	public User login(String email, String password) throws DaoException {

		try (Connection con = ConnectionUtil.getSchemaConnection()) {

			// SQL query to delete the user from the 'user' table.
			String query = "SELECT * FROM user WHERE email_id = ? AND password = ?";

			User user;
			// Prepares the SQL query with the user_id.
			try (PreparedStatement psmt = con.prepareStatement(query)) {

				// Sets the user_id in the PreparedStatement.
				psmt.setString(1, email);
				psmt.setString(2, password);

				// Executes the delete query.
				try (ResultSet rs = psmt.executeQuery()) {

					if (rs.next()) {
						user = new User();

						user.setName(rs.getString("name"));
						user.setPhoneNumber(rs.getString("phone_num"));
						user.setEmailId(rs.getString("email_id"));
						user.setProfession(rs.getString("profession"));
						user.setPassword(rs.getString("password"));
						return user;
					}
				}

			}
			return null;

		} catch (SQLException e) {
			throw new DaoException(e.getMessage());
		}
	}
	
	// Deletes a user from the 'user' table based on the provided User object.
	public User getUserByEmail(String email) throws DaoException {

		try (Connection con = ConnectionUtil.getSchemaConnection()) {

			// SQL query to delete the user from the 'user' table.
			String query = "SELECT * FROM user WHERE email_id = ?";

			User user;
			// Prepares the SQL query with the user_id.
			try (PreparedStatement psmt = con.prepareStatement(query)) {

				
				psmt.setString(1, email);
				

				// Executes the delete query.
				try (ResultSet rs = psmt.executeQuery()) {

					if (rs.next()) {
						user = new User();
						user.setDisplayName(rs.getString("display_name"));
						user.setName(rs.getString("name"));
						user.setPhoneNumber(rs.getString("phone_num"));
						user.setEmailId(rs.getString("email_id"));
						user.setProfession(rs.getString("profession"));
						user.setPassword(rs.getString("password"));
						return user; 
					}
				}

			}
			return null;

		} catch (SQLException e) {
			throw new DaoException(e.getMessage());
		}
	}
	

}
