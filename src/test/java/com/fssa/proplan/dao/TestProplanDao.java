package com.fssa.proplan.dao;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.jupiter.api.Test;

import com.fssa.proplan.exceptions.DaoException;

public class TestProplanDao {

	@Test
	public void testGetSchemaConnection() throws DaoException, SQLException {
		String url = "jdbc:mysql://localhost:3306/proplan";
		String user = "root";
		String password = "123456";

		Connection con = ConnectionUtil.getSchemaConnection();

		if (con != null) { 
			con.close();
			assertTrue(true);
		} else {
			assertFalse(true);
		}

	} 
}
 