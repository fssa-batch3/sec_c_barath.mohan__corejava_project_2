package com.fssa.proplan.dao;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.jupiter.api.Test;

import com.fssa.proplan.exceptions.DaoException;
import com.fssa.proplan.model.User;

public class TestUserDao {

	@Test
	public void testAddUser() throws DaoException {
		User user = new User("mohan", "1234567890", "student", "mohan@gmail.com", "baGra@t1");
 
		if (UserDao.addUser(user)) {
			assertTrue(true);
		} else {
			assertFalse(true);
		}

	}

}
