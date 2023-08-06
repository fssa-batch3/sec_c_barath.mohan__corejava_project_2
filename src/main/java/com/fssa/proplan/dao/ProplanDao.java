package com.fssa.proplan.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.fssa.proplan.exceptions.DaoException;

public class ProplanDao {

	public static Connection getSchemaConnection() throws DaoException {
		// Database URL, username, and password for the MySQL database.
		String url = "jdbc:mysql://localhost:3306/proplan";
		String user = "root";
		String password = "123456";

		// Establishes a connection to the database using DriverManager.
		try {
			return DriverManager.getConnection(url, user, password);
		} catch (SQLException ex) {

			throw new DaoException(ex.getMessage());
		}
	}
}
