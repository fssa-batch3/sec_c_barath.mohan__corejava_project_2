package com.fssa.proplan.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.fssa.proplan.enumclass.TransactionType;
import com.fssa.proplan.model.User; 

public class TransactionDao {

	public static Connection getTransactionConnection() throws SQLException {
		// Establishes a connection to the database
		String url = "jdbc:mysql://localhost:3306/proplan";
		String user = "root";
		String password = "123456";

		return DriverManager.getConnection(url, user, password);
	}

	 // Adds income transaction for the given user.
    public boolean addIncome(User user, double amount, String remarks) throws SQLException {
        Connection con = null;

        try {
            con = getTransactionConnection();  
            String query = "INSERT INTO transactions(user_id,transaction_type,date,amount,balance,remarks) "
                    + "VALUES(?,?,?,?,?,?)";

            PreparedStatement psmt = con.prepareStatement(query);
            java.util.Date utilDate = new java.util.Date();
            java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());

            // Calculate the new balance after adding the income.
            double balance = BalanceDao.getBalanceByUser(user) + amount;

            psmt.setInt(1, UserDao.getUserIdByName(user.getName()));
            psmt.setString(2, TransactionType.INCOME.getStringValue());
            psmt.setDate(3, sqlDate);
            psmt.setDouble(4, amount);
            psmt.setDouble(5, balance);
            psmt.setString(6, remarks);

            int rowAffected = psmt.executeUpdate();

            // Update the user's balance after adding income.
            BalanceDao.updateUserBalance(user, balance);

            System.out.println(rowAffected + " row/rows affected ");

        } catch (SQLException e) {
            throw new SQLException(e.getMessage());
        } finally {
            if (con != null) {
                con.close();
            }
        }
        return true;
    }

	public boolean addExpense(User user, double amount, String remarks) throws SQLException {

		Connection con = null;

		try {
			con = getTransactionConnection();
			String query = "INSERT INTO transactions(user_id,transaction_type,date,amount,balance,remarks) "
					+ "VALUES(?,?,?,?,?,?)";

			PreparedStatement psmt = con.prepareStatement(query);
			java.util.Date utilDate = new java.util.Date();
			java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());

			double balance = BalanceDao.getBalanceByUser(user) - amount;
			psmt.setInt(1, UserDao.getUserIdByName(user.getName()));
			psmt.setString(2, TransactionType.EXPENSE.getStringValue());
			psmt.setDate(3, sqlDate);
			psmt.setDouble(4, amount);
			psmt.setDouble(5, balance);
			psmt.setString(6, remarks);

			int rowAffected = psmt.executeUpdate();

			BalanceDao.updateUserBalance(user, balance);

			System.out.println(rowAffected + "row/rows affected ");

		} catch (SQLException e) {
			throw new SQLException(e.getMessage());
		} finally {
			if (con != null) {
				con.close();
			}
		}
		return true;
	}

	public static boolean clearAllTransactionDetails() throws SQLException {

		Connection con = null;

		try {
			con = getTransactionConnection();
			String query = "TRUNCATE TABLE transactions";

			Statement smt = con.createStatement();

			int rowAffected = smt.executeUpdate(query);

			System.out.println(rowAffected + "row/rows affected ");

			System.out.println("Transactions Table values are cleared");
			smt.close();

		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			if (con != null) {
				con.close();
			}
		}

		return true;
	}

	 // Retrieves the transaction details (income/expense) for the given user and type.
    public static ArrayList<ArrayList<String>> getTransactionDetails(User user, String type) throws SQLException {
        Connection con = null;

        try {
            con = getTransactionConnection();

            String query = "SELECT transaction_type,date,amount,remarks FROM transactions where user_id=? AND transaction_type=?";

            // Get the user's ID from the user's name.
            int userId = UserDao.getUserIdByName(user.getName());

            PreparedStatement psmt = con.prepareStatement(query);

            psmt.setInt(1, userId);
            psmt.setString(2, type);

            ResultSet rs = psmt.executeQuery();

            ArrayList<ArrayList<String>> transactionDetails = new ArrayList<ArrayList<String>>();

            // Fetch the transaction details from the result set and add to the list.
            while (rs.next()) {
                ArrayList<String> transactionDetail = new ArrayList<String>();
                transactionDetail.add(rs.getString("transaction_type"));
                transactionDetail.add(rs.getDate("date") + "");
                transactionDetail.add(rs.getDouble("amount") + "");
                transactionDetail.add(rs.getString("remarks"));
                transactionDetails.add(transactionDetail);
            }

            psmt.close();
            rs.close();
            return transactionDetails;

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            if (con != null) {
                con.close();
            }
        }
        return null;
    }

	public static ArrayList<ArrayList<String>> getIncomeTransactionDetails(User user) throws SQLException {
		// Retrieves the income transaction details for the given user.

		return getTransactionDetails(user, TransactionType.INCOME.getStringValue());
	}

	public static ArrayList<ArrayList<String>> getExpenseTransactionDetails(User user) throws SQLException {
		// Retrieves the expense transaction details for the given user.
		return getTransactionDetails(user, TransactionType.EXPENSE.getStringValue());

	}

}
