package com.fssa.proplan.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.fssa.proplan.exceptions.DaoException;
import com.fssa.proplan.logger.Logger;

import io.github.cdimascio.dotenv.Dotenv;

public class ConnectionUtil {
	static Logger logger = new Logger();

	public static Connection getSchemaConnection() throws DaoException, SQLException  {
		Connection con = null;
//
		String url="jdbc:mysql://localhost:3306/proplan";//jdbc:mysql://localhost:3306/your_database_name
		String userName="root";
		String passWord="123456";

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
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection(url, userName, passWord);
			logger.info("Connection successful");
		} catch (Exception e) {
			e.printStackTrace();
			throw new DaoException("Unable to connect to the database");
		}
//		finally {
//			if(con!=null) {
//				con.close();
//			}
//		}
		
		return con;
	}

	public static void main(String[] args) throws SQLException {

		try {
			getSchemaConnection();
			System.out.println("connected successfully");
			
		} catch (DaoException e) {

			e.printStackTrace();
		}
	}
}
