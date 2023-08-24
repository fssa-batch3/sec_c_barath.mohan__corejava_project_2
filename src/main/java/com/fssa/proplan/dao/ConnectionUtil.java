package com.fssa.proplan.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.fssa.proplan.exceptions.DaoException;
import com.fssa.proplan.logger.Logger;

public class ConnectionUtil {
	static Logger logger = new Logger();

	public static Connection getSchemaConnection() throws DaoException, SQLException {

		Connection con = null;

		String url;
		String userName;
		String passWord;

		url = System.getenv("DATABASE_HOST");
		userName = System.getenv("DATABASE_USERNAME");
		passWord = System.getenv("DATABASE_PASSWORD");

		try {

			return DriverManager.getConnection(url, userName, passWord);

		} catch (SQLException e) {
			throw new DaoException("Connection failure");
		}

	}

	public static void main(String[] args) throws SQLException {

		try {
			getSchemaConnection();
			logger.info("connected successfully");

		} catch (DaoException e) {
			logger.info(e.getMessage());
		}
	}
}
