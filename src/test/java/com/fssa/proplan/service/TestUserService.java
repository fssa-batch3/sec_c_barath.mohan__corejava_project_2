package com.fssa.proplan.service;

import java.sql.SQLException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.fssa.proplan.dao.UserDao;
import com.fssa.proplan.model.User;
import com.fssa.proplan.validator.UserValidator;

public class TestUserService {

	private UserService userService= new UserService(new UserDao(),new UserValidator());
	
	@Test 
	public void testAddUser() throws SQLException {
		
		
		User user1 = new User("dhilip", "1234567890", "student", "barathdh@gmail.com", "baGra@t1");
		

		
		if(userService.addUser(user1)) {
			Assertions.assertTrue(true,"User is added successfully!");
			
		}
		else {
			Assertions.fail("User is not added. UserService failed");
		}	

	}
	
}
