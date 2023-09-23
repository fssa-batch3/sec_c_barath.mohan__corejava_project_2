package com.fssa.proplan.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.fssa.proplan.exceptions.DaoException;
import com.fssa.proplan.logger.Logger;

public class ConnectionUtil {

	public static Connection getSchemaConnection() throws DaoException, SQLException {

		Connection con = null;

		String url;
		String userName;
		String passWord;

//		// Check if the "CI" environment variable is set
//		String environment = System.getenv("CI");
//
//		if (environment != null && environment.equalsIgnoreCase("true")) {
//			// Use cloud database credentials
//			url = System.getenv("DATABASE_HOST");
//			userName = System.getenv("DATABASE_USERNAME");
//			passWord = System.getenv("DATABASE_PASSWORD");
//		} else {
//			// Use local database credentials
//			url = System.getenv("DATABASE_HOST");
//			userName = System.getenv("DATABASE_USERNAME");
//			passWord = System.getenv("DATABASE_PASSWORD");
//		}

		url = System.getenv("DATABASE_HOST");
		userName = System.getenv("DATABASE_USERNAME");
		passWord = System.getenv("DATABASE_PASSWORD");
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println(url);
			return DriverManager.getConnection(url, userName, passWord);

		} catch (SQLException | ClassNotFoundException e) {
			System.out.println(e.getMessage());
			throw new DaoException("Connection failure" + e.getMessage());
		}

	}

//	public static void main(String[] args) throws SQLException {
//
//		try {
//			getSchemaConnection();
//
//			Logger.info("connected successfully");
//
//		} catch (DaoException e) {
//			Logger.info(e.getMessage());
//		}
//	}
}
