package com.fssa.proplan.enumclass;

import java.util.HashMap;

public enum TransactionType {
	INCOME("Income"), EXPENSE("Expense");

	public final String value;

	TransactionType(String value) {
		this.value = value;
	}

	// Getter method to retrieve the value of the enum constant
	public String getValue() {
		return value;
	};

	public static TransactionType valueToEnumMapping(String value) {
		// Create a mapping of values to enum constants using a HashMap
		HashMap<String, TransactionType> valueToEnumMapping = new HashMap<String, TransactionType>();
		// Iterate through all BloodGroup enum constants and populate the mapping
		for (TransactionType transactionType : TransactionType.values()) {
			valueToEnumMapping.put(transactionType.getValue(), transactionType);
		}

		// Return the enum constant corresponding to the provided value
		return valueToEnumMapping.get(value);

	}

}
