package com.fssa.proplan.dao;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.jupiter.api.Test;

import com.fssa.proplan.exceptions.DaoException;

 class TestProplanDao {

	@Test
	 void testGetSchemaConnection() throws DaoException, SQLException {

		Connection con = ConnectionUtil.getSchemaConnection();

		if (con != null) { 
			con.close();
			assertTrue(true);
		} else {
			assertFalse(true); 
		}

	} 
} 
 