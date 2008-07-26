package com.ascepionpharm.lims.repository.core;

import java.sql.*;
import java.util.*;

import com.ascepionpharm.lims.entity.core.*;
import com.ascepionpharm.lims.universal.*;

/**
 * DepartmentRepository: performs all database actions to the BANKACCOUNT table.
 * 
 * @author Li Jun Mulin
 * @version
 * 
 */

public class BankAccountRepository extends LIMSRepository implements
		Destroyable, Closable {
	public BankAccountRepository() throws RepositoryException {
		this.className = "Class: BankAccountRepository. ";

		try {
			Class.forName(myDbBean.getDriver());
			connection = DriverManager.getConnection(myDbBean.getDbURL());
			prepareCalls(connection);
		} catch (SQLException se) {
			throw new RepositoryException(className
					+ "SQLException caught in constructor. " + se.getMessage());
		} catch (ClassNotFoundException ce) {
			throw new RepositoryException(className + ce.getMessage());
		}
	}

	public BankAccountRepository(Connection conn) throws RepositoryException {
		this.className = "Class: BankAccountRepository. ";
		prepareCalls(conn);
	}
	
	public void prepareCalls(Connection conn) throws RepositoryException {
		String get = "SELECT * FROM BANKACCOUNT WHERE BANK_ID = ? ";
		String all = "SELECT * FROM BANKACCOUNT ";

		try {
			getStmt = conn.prepareStatement(get);
			getAllStmt = conn.prepareStatement(all);
		} catch (SQLException e) {
			throw new RepositoryException(className
					+ "SQLException caught in constructor. " + e.getMessage());
		}
	}
	
	public void closeCalls() throws RepositoryException {
		try {
			getStmt.close();
			getAllStmt.close();
		} catch (SQLException e) {
			throw new RepositoryException(className
					+ "Calls could not be closed. " + e.getMessage());
		}
	}
	
	private BankAccountBean makeBean(ResultSet results) throws RepositoryException {
		try {
			BankAccountBean bankaccount = new BankAccountBean();
			bankaccount.setId(results.getInt("BANK_ID"));
			bankaccount.setBankName(results.getString("BANK_NAME"));
			bankaccount.setAccountName(results.getString("ACCOUNT_NAME"));
			bankaccount.setAccountNumber(results.getString("ACCOUNT_NUMBER"));

			return bankaccount;
		} catch (SQLException se) {
			throw new RepositoryException(className
					+ "SQLException caught in method makeBean. "
					+ se.getMessage());
		} catch (Exception e) {
			throw new RepositoryException(className
					+ "unknown error caught in method makeBean. "
					+ e.getMessage());
		}
	}

	public void destroy() throws RepositoryException {
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				throw new RepositoryException(className
						+ "Connection could not be closed. " + e.getMessage());
			}
		}
	}

	public Item get(int id) throws RepositoryException {
		try {
			ResultSet results;
			getStmt.clearParameters();
			getStmt.setInt(1, id);
			results = getStmt.executeQuery();

			if (results.next())
				return makeBean(results);
			else
				throw new RepositoryException(className
						+ "RepositoryException caught in method get. "
						+ "Could not find BANK_ID " + id);
		} catch (SQLException e) {
			throw new RepositoryException(className
					+ "SQLException caught in method get. " + e.getMessage());
		} catch (Exception e) {
			throw new RepositoryException(className
					+ "Unknown error caught in method get. " + e.getMessage());
		}
	}

	public Item[] getAll() throws RepositoryException {
		try {
			ResultSet results;
			getAllStmt.clearParameters();
			Collection bankaccounts = new ArrayList();
			results = getAllStmt.executeQuery();

			while (results.next()) {
				bankaccounts.add(makeBean(results));
			}
			return (BankAccountBean[]) bankaccounts
					.toArray(new BankAccountBean[0]);
		} catch (SQLException e) {
			throw new RepositoryException(className
					+ "SQLException caught in method getAll. " + e.getMessage());
		} catch (Exception e) {
			throw new RepositoryException(className
					+ "Unknown error caught in method getAll. " + e.getMessage());
		}
	}

	public Item[] getList(int id) throws RepositoryException {
		return null;
	}

	public void put(Item item) throws RepositoryException {

	}

	public void remove(Item item) throws RepositoryException {

	}

	public void update(Item item) throws RepositoryException {

	}

}
