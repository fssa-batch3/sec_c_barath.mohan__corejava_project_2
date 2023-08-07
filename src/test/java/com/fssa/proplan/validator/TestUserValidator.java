package com.fssa.proplan.validator;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.fssa.proplan.exceptions.UserException;
import com.fssa.proplan.model.User;

public class TestUserValidator {

	// Test cases for isValidName() method
	
	@Test
	public void testIsValidUserValid() throws UserException {
		User user1 = new User("Barath", "1234567890", "student", "mohan1@gmail.com", "baGra@t1");
		
		
		assertTrue(UserValidator.isValidUser(user1));
	}
	
	@Test
	public void testIsValidUserInvalid() throws UserException {
		
		User user1 = new User();
		
		user1.setDisplayName("barathCharm");
		user1.setEmailId("barath@123");
		user1.setName("barath");
		user1.setPassword("0");
		user1.setPhoneNumber("1234567890");
		user1.setProfession("student");
		
		assertThrows(UserException.class, () -> UserValidator.isValidUser(user1));
	}
	
	

	
	@Test
	public void testValidName() throws UserException {
		// Test if the method accepts a valid name with alphabets and spaces
		assertTrue(UserValidator.isValidName("John Doe"));
	}

	@Test
	public void testValidNameWithWhitespace() throws UserException {
		// Test if the method accepts a valid name with leading and trailing spaces
		assertTrue(UserValidator.isValidName("   Jane Smith   "));
	}

	@Test
	public void testInvalidNameWithDigits() {
		// Test if the method throws an exception for a name containing digits
		assertThrows(UserException.class, () -> UserValidator.isValidName("John123"));
	}

	@Test
	public void testNullName() {
		// Test if the method throws an exception for a null name
		assertThrows(UserException.class, () -> UserValidator.isValidName(null));
	}

	@Test
	public void testEmptyNameWithWhitespace() {
		// Test if the method throws an exception for an empty name with whitespace
		assertThrows(UserException.class, () -> UserValidator.isValidName("    "));
	}

	@Test
	public void testEmptyName() {
		// Test if the method throws an exception for an empty name
		assertThrows(UserException.class, () -> UserValidator.isValidName(""));
	}

	// Test cases for isValidEmail() method

	@Test
	public void testValidEmail() throws UserException {
		// Test if the method accepts a valid email address
		String validEmail = "john.doe@example.com";
		assertTrue(UserValidator.isValidEmail(validEmail));
	}

	@Test
	public void testInvalidEmail() {
		// Test if the method throws an exception for an invalid email address
		String invalidEmail = "invalid_email";
		assertThrows(UserException.class, () -> UserValidator.isValidEmail(invalidEmail));
	}

	@Test
	public void testEmailWithLeadingAndTrailingSpaces() throws UserException {
		// Test if the method accepts a valid email address with leading and trailing
		// spaces
		String emailWithSpaces = "  john.doe@example.com  ";
		assertTrue(UserValidator.isValidEmail(emailWithSpaces));
	}

	@Test
	public void testNullEmail() {
		// Test if the method throws an exception for a null email address
		String nullEmail = null;
		assertThrows(UserException.class, () -> UserValidator.isValidEmail(nullEmail));
	}

	@Test
	public void testEmptyEmail() {
		// Test if the method throws an exception for an empty email address
		String emptyEmail = "";
		assertThrows(UserException.class, () -> UserValidator.isValidEmail(emptyEmail));
	}

	// Test cases for isValidPassword() method

	@Test
	public void testValidPassword() throws UserException {
		// Test if the method accepts a valid password with all required criteria met
		String validPassword = "P@ssw0rd";
		assertTrue(UserValidator.isValidPassword(validPassword));
	}

	@Test
	public void testNullPassword() {
		// Test if the method throws an exception for a null password
		String nullPassword = null;
		assertThrows(UserException.class, () -> UserValidator.isValidPassword(nullPassword));
	}

	@Test
	public void testEmptyPassword() {
		// Test if the method throws an exception for an empty password
		String emptyPassword = "";
		assertThrows(UserException.class, () -> UserValidator.isValidPassword(emptyPassword));
	}

	@Test
	public void testWhitespacePassword() {
		// Test if the method throws an exception for a password containing only
		// whitespace
		String whitespacePassword = "     ";
		assertThrows(UserException.class, () -> UserValidator.isValidPassword(whitespacePassword));
	}

	@Test
	public void testPasswordShorterThanEightCharacters() {
		// Test if the method throws an exception for a password shorter than eight
		// characters
		String shortPassword = "Ab1$";
		assertThrows(UserException.class, () -> UserValidator.isValidPassword(shortPassword));
	}

	@Test
	public void testPasswordMissingUppercaseLetter() {
		// Test if the method throws an exception for a password without an uppercase
		// letter
		String passwordWithoutUppercase = "test@123";
		assertThrows(UserException.class, () -> UserValidator.isValidPassword(passwordWithoutUppercase));
	}

	@Test
	public void testPasswordMissingLowercaseLetter() {
		// Test if the method throws an exception for a password without a lowercase
		// letter
		String passwordWithoutLowercase = "TEST@123";
		assertThrows(UserException.class, () -> UserValidator.isValidPassword(passwordWithoutLowercase));
	}

	@Test
	public void testPasswordMissingDigit() {
		// Test if the method throws an exception for a password without a digit
		String passwordWithoutDigit = "Test@Pass";
		assertThrows(UserException.class, () -> UserValidator.isValidPassword(passwordWithoutDigit));
	}

	@Test
	public void testPasswordMissingSpecialCharacter() {
		// Test if the method throws an exception for a password without a special
		// character
		String passwordWithoutSpecialChar = "Test1234";
		assertThrows(UserException.class, () -> UserValidator.isValidPassword(passwordWithoutSpecialChar));
	}

	// Test cases for isValidProfession() method

	@Test
	public void testValidProfession() throws UserException {
		// Test if the method accepts a valid profession with alphabets and spaces
		String validProfession = "Software Developer";
		assertTrue(UserValidator.isValidProfession(validProfession));
	}

	@Test
	public void testValidProfessionWithWhitespace() throws UserException {
		// Test if the method accepts a valid profession with leading and trailing
		// spaces
		String validProfession = "   Data Analyst   ";
		assertTrue(UserValidator.isValidProfession(validProfession));
	}

	@Test
	public void testInvalidProfessionWithNumbers() {
		// Test if the method throws an exception for a profession containing numbers
		String invalidProfession = "Web Developer 123";
		assertThrows(UserException.class, () -> UserValidator.isValidProfession(invalidProfession));
	}

	@Test
	public void testEmptyProfession() {
		// Test if the method throws an exception for an empty profession
		String emptyProfession = "";
		assertThrows(UserException.class, () -> UserValidator.isValidProfession(emptyProfession));
	}

	@Test
	public void testNullProfession() {
		// Test if the method throws an exception for a null profession
		String nullProfession = null;
		assertThrows(UserException.class, () -> UserValidator.isValidProfession(nullProfession));
	}

	@Test
	public void testProfessionWithOnlyDigits() {
		// Test if the method throws an exception for a profession containing only
		// digits
		String professionWithDigits = "12345";
		assertThrows(UserException.class, () -> UserValidator.isValidProfession(professionWithDigits));
	}

	// Test cases for isValidPhoneNumber() method

	@Test
	void testValidPhoneNumber() throws UserException {
		// Test if the method accepts a valid phone number with exactly 10 digits
		assertTrue(UserValidator.isValidPhoneNumber("1234567890"));
	}

	@Test
	void testValidPhoneNumberWithSpaces() throws UserException {
		// Test if the method accepts a valid phone number with leading and trailing
		// spaces
		assertTrue(UserValidator.isValidPhoneNumber("  1234567890  "));
	}

	@Test
	void testInvalidPhoneNumberLessThan10Digits() {
		// Test if the method throws an exception for a phone number with less than 10
		// digits
		assertThrows(UserException.class, () -> UserValidator.isValidPhoneNumber("12345"));
	}

	@Test
	void testInvalidPhoneNumberMoreThan10Digits() {
		// Test if the method throws an exception for a phone number with more than 10
		// digits
		assertThrows(UserException.class, () -> UserValidator.isValidPhoneNumber("12345678901"));
	}

	@Test
	void testInvalidPhoneNumberNonNumeric() {
		// Test if the method throws an exception for a phone number containing
		// non-numeric characters
		assertThrows(UserException.class, () -> UserValidator.isValidPhoneNumber("abc123xyz"));
	}

	@Test
	void testNullPhoneNumber() {
		// Test if the method throws an exception for a null phone number
		assertThrows(UserException.class, () -> UserValidator.isValidPhoneNumber(null));
	}

	@Test
	void testEmptyPhoneNumber() {
		// Test if the method throws an exception for an empty phone number
		assertThrows(UserException.class, () -> UserValidator.isValidPhoneNumber(""));
	}

}
