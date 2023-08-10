package com.fssa.proplan.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.fssa.proplan.errormessages.UserValidationErrors;
import com.fssa.proplan.exceptions.UserException;
import com.fssa.proplan.model.User;

public class UserValidator {

	public   boolean isValidUser(User user) throws UserException {
		if (user == null) {
			throw new UserException(UserValidationErrors.INVALID_USER_NULL);
		}
		isValidName(user.getName());
		isValidEmail(user.getEmailId());
		isValidPassword(user.getPassword());
		isValidProfession(user.getProfession());
		isValidPhoneNumber(user.getPhoneNumber());

		return true;
	} 

	public  boolean isValidName(String name) throws UserException {

		if (name == null || name.trim() == null || name.trim().equals("")) {
			throw new UserException(UserValidationErrors.INVALID_NAME_NULL);
		}
		String regexPattern = "^[a-zA-Z\\s]*$";

		Pattern pattern = Pattern.compile(regexPattern);

		Matcher matcher = pattern.matcher(name);

		if (!matcher.matches()) { 
			throw new UserException(UserValidationErrors.INVALID_NAME);
		}
		return true;
	}

	public  boolean isValidEmail(String email) throws UserException {

		if (email == null || email.trim() == null || email.trim().equals("")) {
			throw new UserException(UserValidationErrors.INVALID_EMAIL_NULL);
		}//		storing the regular expression pattern to validate the given user input
//		this pattern checks for '@' symbol and checks for '.' at end of the string
			String emailRegex = "^.*@.*\\..*$";

		Pattern pattern = Pattern.compile(emailRegex);
		Matcher matcher = pattern.matcher(email.trim());

		if (!matcher.matches()) {
			throw new UserException(UserValidationErrors.INVALID_EMAIL);
		} 
		return true;
	}

	public  boolean isValidPassword(String password) throws UserException {
		if (password == null || password.trim() == null || password.trim().equals("")) {
			throw new UserException(UserValidationErrors.INVALID_PASSWORD_NULL);
		}
		// At least one special character (e.g., !@#$%^&*()-_=+[]{}|;:'",.<>?/)
		String regexPattern = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";

		// Create a Pattern object from the regex pattern
		Pattern pattern = Pattern.compile(regexPattern);

		// Create a Matcher object to match the input password against the pattern
		Matcher matcher = pattern.matcher(password);

		// Return true if the password matches the pattern, otherwise false
		if (!matcher.matches()) {
			throw new UserException(UserValidationErrors.INVALID_PASSWORD);
		}
		return true;
	}

	public  boolean isValidProfession(String profession) throws UserException {
		if (profession == null || profession.trim() == null || profession.trim().equals("")) {
			throw new UserException(UserValidationErrors.INVALID_PROFESSION_NULL);
		}

		String regexPattern = "^[a-zA-Z\\s]*$";

		Pattern pattern = Pattern.compile(regexPattern);

		Matcher matcher = pattern.matcher(profession);

		if (!matcher.matches()) {
			throw new UserException(UserValidationErrors.INVALID_PROFESSION);
		}
		return true;

	}

	public  boolean isValidPhoneNumber(String phNo) throws UserException {
		if (phNo == null || phNo.trim() == null || phNo.trim().equals("")) {
			throw new UserException(UserValidationErrors.INVALID_PHNO_NULL);
		}
		String regexPattern = "^[0-9]{10}$";

		// Create a Pattern object from the regex pattern
		Pattern pattern = Pattern.compile(regexPattern);

		// Create a Matcher object to perform the validation
		Matcher matcher = pattern.matcher(phNo.trim());

		if (!matcher.matches()) {
			throw new UserException(UserValidationErrors.INVALID_PHNO);
		}
		return true;
	}

}
