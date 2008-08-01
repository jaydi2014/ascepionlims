package com.ascepionpharm.lims.repository.core;

import java.sql.*;
import java.util.*;

import com.ascepionpharm.lims.universal.*;

/**
 * EmployeeTypeRepository: performs all database actions to the EMPLOYEETYPE table.
 *
 * @author Li Jun Mulin
 * @version 
 *
 */

public class EmployeeTypeRepository extends LIMSRepository implements
		Destroyable, Closable {

	public EmployeeTypeRepository() throws RepositoryException {
		this.className = "Class: EmployeeTypeRepository. ";

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

	public EmployeeTypeRepository(Connection conn) throws RepositoryException {
		this.className = "Class: EmployeeTypeRepository. ";
		prepareCalls(conn);
	}

	public void prepareCalls(Connection conn) throws RepositoryException {
		String get = "SELECT * FROM EMPLOYEETYPE WHERE EMPLOYEETYPE_ID=?";
		String all = "SELECT * FROM EMPLOYEETYPE ORDER BY EMPLOYEETYPE_NAME";

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

	private Item makeBean(ResultSet results)
			throws RepositoryException {
		try {
			Item employeetype = new Item();
			employeetype.setId(results.getInt("EMPLOYEETYPE_ID"));
			employeetype.setType(results.getString("EMPLOYEETYPE_NAME"));
			return employeetype;
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

	public Item get(int id) throws RepositoryException {
		return null;
	}

	public Item[] getAll() throws RepositoryException {
		try {
			ResultSet results;
			getAllStmt.clearParameters();
			Collection employeetypes = new ArrayList();
			results = getAllStmt.executeQuery();

			while (results.next()) {
				employeetypes.add(makeBean(results));
			}
			return (Item[]) employeetypes
					.toArray(new Item[0]);
		} catch (SQLException e) {
			throw new RepositoryException(className
					+ "SQLException caught in method getAll. " + e.getMessage());
		} catch (Exception e) {
			throw new RepositoryException(className
					+ "Unknown error caught in method getAll. " + e.getMessage());
		}
	}

	public Item[] getList(int employeetypeid) throws RepositoryException {
		try {
			ResultSet results;
			getStmt.clearParameters();
			Collection employeetypes = new ArrayList();
			getStmt.setInt(1, employeetypeid);
			results = getStmt.executeQuery();

			while (results.next()) {
				employeetypes.add(makeBean(results));
			}
			return (Item[]) employeetypes
					.toArray(new Item[0]);
		} catch (SQLException e) {
			throw new RepositoryException(className
					+ "SQLException caught in method getList. " + e.getMessage());
		} catch (Exception e) {
			throw new RepositoryException(className
					+ "Unknown error caught in method getList. " + e.getMessage());
		}
	}

	public void put(Item item) throws RepositoryException {

	}

	public void remove(Item item) throws RepositoryException {

	}

	public void update(Item item) throws RepositoryException {

	}

}
