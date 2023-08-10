package com.fssa.proplan.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.fssa.proplan.exceptions.DaoException;
import com.fssa.proplan.logger.Logger;

import io.github.cdimascio.dotenv.Dotenv;

public class ConnectionUtil {
	static Logger logger = new Logger();

	public static Connection getSchemaConnection() throws DaoException, SQLException {

		String url;
		String userName;
		String passWord;

		if (System.getenv("CI") != null) {
			url = System.getenv("DATABASE_HOST");
			userName = System.getenv("DATABASE_USERNAME");
			passWord = System.getenv("DATABASE_PASSWORD");
		} else {
			Dotenv env = Dotenv.load();
			url = env.get("DATABASE_HOST");
			userName = env.get("DATABASE_USERNAME");
			passWord = env.get("DATABASE_PASSWORD");
		}
		try {
			return DriverManager.getConnection(url, userName, passWord);
		} catch (SQLException e) {
			e.printStackTrace();

		}
		return null;
	}

	public static void main(String[] args) throws SQLException {

		try {
			getSchemaConnection();
			logger.info("connected successfully");

		} catch (DaoException e) {

			e.printStackTrace();
		}
	}
}
