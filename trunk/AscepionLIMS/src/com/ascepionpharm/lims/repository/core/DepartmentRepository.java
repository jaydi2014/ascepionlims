package com.ascepionpharm.lims.repository.core;

import java.sql.*;
import java.util.*;

import com.ascepionpharm.lims.universal.*;

/**
 * DepartmentRepository: performs all database actions to the DEPARTMENT table.
 *
 * @author Li Jun Mulin
 * @version 
 *
 */

public class DepartmentRepository extends LIMSRepository implements
		Destroyable, Closable {

	public DepartmentRepository() throws RepositoryException {
		this.className = "Class: DepartmentRepository. ";

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

	public DepartmentRepository(Connection conn) throws RepositoryException {
		this.className = "Class: DepartmentRepository. ";
		prepareCalls(conn);
	}

	public void prepareCalls(Connection conn) throws RepositoryException {
		String get = "SELECT * FROM DEPARTMENT WHERE DEPARTMENT_ID=?";
		String all = "SELECT * FROM DEPARTMENT ORDER BY DEPARTMENT_NAME";

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
			Item department = new Item();
			department.setId(results.getInt("DEPARTMENT_ID"));
			department.setType(results.getString("DEPARTMENT_NAME"));
			return department;
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
			Collection departments = new ArrayList();
			results = getAllStmt.executeQuery();

			while (results.next()) {
				departments.add(makeBean(results));
			}
			return (Item[]) departments
					.toArray(new Item[0]);
		} catch (SQLException e) {
			throw new RepositoryException(className
					+ "SQLException caught in method getAll. " + e.getMessage());
		} catch (Exception e) {
			throw new RepositoryException(className
					+ "Unknown error caught in method getAll. " + e.getMessage());
		}
	}
	
	public List getAllDwr() throws RepositoryException{
		Item[] all = getAll();
		List allDwr = new ArrayList();
		for(int i=0;i<all.length;i++){
			allDwr.add(all[i]);
		}
		return allDwr;
	}

	public Item[] getList(int departmentid) throws RepositoryException {
		try {
			ResultSet results;
			getStmt.clearParameters();
			Collection departments = new ArrayList();
			getStmt.setInt(1, departmentid);
			results = getStmt.executeQuery();

			while (results.next()) {
				departments.add(makeBean(results));
			}
			return (Item[]) departments
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
