package com.fssa.proplan.validator;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.fssa.proplan.exceptions.UserException;
import com.fssa.proplan.model.User;

class TestUserValidator {

	// Test cases for isValidName() method
	UserValidator userValidator = new UserValidator();

	@Test
	void testIsValidUserValid() throws UserException {
		User user1 = new User("Barath", "1234567890", "student", "mohan1@gmail.com", "baGra@t1");

		assertTrue(userValidator.isValidUser(user1));
	}

	@Test
	void testIsValidUserInvalid() throws UserException {

		User user1 = new User();

		user1.setDisplayName("barathCharm");
		user1.setEmailId("barath@123");
		user1.setName("barath");
		user1.setPassword("0");
		user1.setPhoneNumber("1234567890");
		user1.setProfession("student");

		assertThrows(UserException.class, () -> userValidator.isValidUser(user1));
	}

	@Test
	void testValidName() throws UserException {
		// Test if the method accepts a valid name with alphabets and spaces
		assertTrue(userValidator.isValidName("John Doe"));
	}

	@Test
	void testValidNameWithWhitespace() throws UserException {
		// Test if the method accepts a valid name with leading and trailing spaces
		assertTrue(userValidator.isValidName("   Jane Smith   "));
	}

	@Test
	void testInvalidNameWithDigits() {
		// Test if the method throws an exception for a name containing digits
		assertThrows(UserException.class, () -> userValidator.isValidName("John123"));
	}

	@Test
	void testNullName() {
		// Test if the method throws an exception for a null name
		assertThrows(UserException.class, () -> userValidator.isValidName(null));
	}

	@Test
	void testEmptyNameWithWhitespace() {
		// Test if the method throws an exception for an empty name with whitespace
		assertThrows(UserException.class, () -> userValidator.isValidName("    "));
	}

	@Test
	void testEmptyName() {
		// Test if the method throws an exception for an empty name
		assertThrows(UserException.class, () -> userValidator.isValidName(""));
	}

	// Test cases for isValidEmail() method

	@Test
	void testValidEmail() throws UserException {
		// Test if the method accepts a valid email address
		String validEmail = "barath23@gmail.com";
		assertTrue(userValidator.isValidEmail(validEmail));
	}

	@Test
	void testInvalidEmail() {
		// Test if the method throws an exception for an invalid email address
		String invalidEmail = "invalid_email";
		assertThrows(UserException.class, () -> userValidator.isValidEmail(invalidEmail));
	}

	@Test
	void testEmailWithLeadingAndTrailingSpaces() throws UserException {
		// Test if the method accepts a valid email address with leading and trailing
		// spaces
		String emailWithSpaces = "  john.doe@example.com  ";
		assertTrue(userValidator.isValidEmail(emailWithSpaces));
	}

	@Test
	void testNullEmail() {
		// Test if the method throws an exception for a null email address
		String nullEmail = null;
		assertThrows(UserException.class, () -> userValidator.isValidEmail(nullEmail));
	}

	@Test
	void testEmptyEmail() {
		// Test if the method throws an exception for an empty email address
		String emptyEmail = "";
		assertThrows(UserException.class, () -> userValidator.isValidEmail(emptyEmail));
	}

	// Test cases for isValidPassword() method

	@Test
	void testValidPassword() throws UserException {
		// Test if the method accepts a valid password with all required criteria met
		String validPassword = "P@ssw0rd";
		assertTrue(userValidator.isValidPassword(validPassword));
	}

	@Test
	void testNullPassword() {
		// Test if the method throws an exception for a null password
		String nullPassword = null;
		assertThrows(UserException.class, () -> userValidator.isValidPassword(nullPassword));
	}

	@Test
	void testEmptyPassword() {
		// Test if the method throws an exception for an empty password
		String emptyPassword = "";
		assertThrows(UserException.class, () -> userValidator.isValidPassword(emptyPassword));
	}

	@Test
	void testWhitespacePassword() {
		// Test if the method throws an exception for a password containing only
		// whitespace
		String whitespacePassword = "     ";
		assertThrows(UserException.class, () -> userValidator.isValidPassword(whitespacePassword));
	}

	@Test
	void testPasswordShorterThanEightCharacters() {
		// Test if the method throws an exception for a password shorter than eight
		// characters
		String shortPassword = "Ab1$";
		assertThrows(UserException.class, () -> userValidator.isValidPassword(shortPassword));
	}

	@Test
	void testPasswordMissingUppercaseLetter() {
		// Test if the method throws an exception for a password without an uppercase
		// letter
		String passwordWithoutUppercase = "test@123";
		assertThrows(UserException.class, () -> userValidator.isValidPassword(passwordWithoutUppercase));
	}

	@Test
	void testPasswordMissingLowercaseLetter() {
		// Test if the method throws an exception for a password without a lowercase
		// letter
		String passwordWithoutLowercase = "TEST@123";
		assertThrows(UserException.class, () -> userValidator.isValidPassword(passwordWithoutLowercase));
	}

	@Test
	void testPasswordMissingDigit() {
		// Test if the method throws an exception for a password without a digit
		String passwordWithoutDigit = "Test@Pass";
		assertThrows(UserException.class, () -> userValidator.isValidPassword(passwordWithoutDigit));
	}

	@Test
	void testPasswordMissingSpecialCharacter() {
		// Test if the method throws an exception for a password without a special
		// character
		String passwordWithoutSpecialChar = "Test1234";
		assertThrows(UserException.class, () -> userValidator.isValidPassword(passwordWithoutSpecialChar));
	}

	// Test cases for isValidProfession() method

	@Test
	void testValidProfession() throws UserException {
		// Test if the method accepts a valid profession with alphabets and spaces
		String validProfession = "Software Developer";
		assertTrue(userValidator.isValidProfession(validProfession));
	}

	@Test
	void testValidProfessionWithWhitespace() throws UserException {
		// Test if the method accepts a valid profession with leading and trailing
		// spaces
		String validProfession = "   Data Analyst   ";
		assertTrue(userValidator.isValidProfession(validProfession));
	}

	@Test
	void testInvalidProfessionWithNumbers() {
		// Test if the method throws an exception for a profession containing numbers
		String invalidProfession = "Web Developer 123";
		assertThrows(UserException.class, () -> userValidator.isValidProfession(invalidProfession));
	}

	@Test
	void testEmptyProfession() {
		// Test if the method throws an exception for an empty profession
		String emptyProfession = "";
		assertThrows(UserException.class, () -> userValidator.isValidProfession(emptyProfession));
	}

	@Test
	void testNullProfession() {
		// Test if the method throws an exception for a null profession
		String nullProfession = null;
		assertThrows(UserException.class, () -> userValidator.isValidProfession(nullProfession));
	}

	@Test
	void testProfessionWithOnlyDigits() {
		// Test if the method throws an exception for a profession containing only
		// digits
		String professionWithDigits = "12345";
		assertThrows(UserException.class, () -> userValidator.isValidProfession(professionWithDigits));
	}

	// Test cases for isValidPhoneNumber() method

	@Test
	void testValidPhoneNumber() throws UserException {
		// Test if the method accepts a valid phone number with exactly 10 digits
		assertTrue(userValidator.isValidPhoneNumber("1234567890"));
	}

	@Test
	void testValidPhoneNumberWithSpaces() throws UserException {
		// Test if the method accepts a valid phone number with leading and trailing
		// spaces
		assertTrue(userValidator.isValidPhoneNumber("  1234567890  "));
	}

	@Test
	void testInvalidPhoneNumberLessThan10Digits() {
		// Test if the method throws an exception for a phone number with less than 10
		// digits
		assertThrows(UserException.class, () -> userValidator.isValidPhoneNumber("12345"));
	}

	@Test
	void testInvalidPhoneNumberMoreThan10Digits() {
		// Test if the method throws an exception for a phone number with more than 10
		// digits
		assertThrows(UserException.class, () -> userValidator.isValidPhoneNumber("12345678901"));
	}

	@Test
	void testInvalidPhoneNumberNonNumeric() {
		// Test if the method throws an exception for a phone number containing
		// non-numeric characters
		assertThrows(UserException.class, () -> userValidator.isValidPhoneNumber("abc123xyz"));
	}

	@Test
	void testNullPhoneNumber() {
		// Test if the method throws an exception for a null phone number
		assertThrows(UserException.class, () -> userValidator.isValidPhoneNumber(null));
	}

	@Test
	void testEmptyPhoneNumber() {
		// Test if the method throws an exception for an empty phone number
		assertThrows(UserException.class, () -> userValidator.isValidPhoneNumber(""));
	}

}
