package com.fssa.proplan.dao;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.jupiter.api.Test;

import com.fssa.proplan.dao.UserDao;
import com.fssa.proplan.model.User;

public class TestUserDao {

	
	@Test
	public  void testGetUserConnection() throws SQLException {
		String url = "jdbc:mysql://localhost:3306/proplan";
		String user = "root";
		String password = "123456";

		Connection con = UserDao.getUserConnection();
		
		if(con!=null) {
			con.close();
			assertTrue(true); 
		}
		else {
			assertFalse(true);
		}
		
	}
	
	
	@Test
	public void testAddUser() throws SQLException {
		User user = new User("mohan", "1234567890", "student", "mohan@gmail.com", "baGra@t1");
		
		if(UserDao.addUser(user)) {
			assertTrue(true); 
			
		}
		else {
			assertFalse(true);	
		}
		
		
	}
	
	
	
	
	
	
}
