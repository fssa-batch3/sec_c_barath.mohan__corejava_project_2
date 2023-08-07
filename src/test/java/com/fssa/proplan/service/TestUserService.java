package com.fssa.proplan.service;

import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.fssa.proplan.dao.UserDao;
import com.fssa.proplan.exceptions.DaoException;
import com.fssa.proplan.exceptions.UserException;
import com.fssa.proplan.model.User;
import com.fssa.proplan.validator.UserValidator;

public class TestUserService {

	private UserService userService = new UserService(new UserDao(), new UserValidator());

	@Test
	public void testAddUser() throws DaoException, UserException {

		User user1 = new User("Barath", "1234567890", "student", "mohan12@gmail.com", "baGra@t1");

		if (userService.addUser(user1)) {
			Assertions.assertTrue(true, "User is added successfully!");

		} else {
			Assertions.fail("User is not added. UserService failed");
		}

	}

	@Test
	public void testGetAllUserEmails() throws DaoException, UserException {

		ArrayList<String> userEmails = userService.getAllUserEmails();

		if (userEmails != null) {
			Assertions.assertTrue(true, "User emails fetched successfully!");

		} else {
			Assertions.fail("User emails are not fetched. getAllUserEmails failed");
		}

	}

	@Test
	public void testIsUserExistValid() throws DaoException, UserException {

		User user1 = new User("Barath", "1234567890", "student", "mohan123@gmail.com", "baGra@t1");

		if (userService.isUserExist(user1)) {
			Assertions.assertTrue(true);

		} else {
			Assertions.fail("User already exists. isUserExist failed");
		}

	}

	@Test
	public void testIsUserExistInvalid() throws DaoException, UserException {

		User user1 = new User("Barath", "1234567890", "student", "Gopal@gmail.com", "baGra@t1");

		if (userService.isUserExist(user1)) {
			Assertions.fail("User Doesn't exists. isUserExist failed");

		} else {
			Assertions.assertTrue(true);
		}

	}

//	@Test
//	public void testDeleteUser() throws DaoException, UserException {
//
//		User user1 = new User("Barath", "1234567890", "student", "mohan1@gmail.com", "baGra@t1");
//
//		if (userService.deleteUser(user1)) {
//			Assertions.assertTrue(true);
//
//		} else {
//			Assertions.fail("User is not deleteed. deleteUser failed");
//		}
//
//	}

//	@Test
//	public void testClearAll() throws DaoException, UserException {
//
//		
//		userService.clearAll();
//		
//		
//		
//		if () {
//			Assertions.assertTrue(true, "User is added successfully!");
//
//		} else {
//			Assertions.fail("User is not added. UserService failed");
//		}
//
//	}

}
