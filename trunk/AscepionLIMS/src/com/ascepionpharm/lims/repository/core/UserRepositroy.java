package com.ascepionpharm.lims.repository.core;

import java.sql.*;
import java.util.*;
import com.ascepionpharm.lims.entity.core.*;
import com.ascepionpharm.lims.universal.*;

/**
 * UserRespository: performs all database actions to the LIMS_USER table.
 *
 * @author Li Jun Mulin
 * @version 
 *
 */

public class UserRepositroy extends LIMSRepository implements Destroyable, Closable {

	PreparedStatement getByUserNameStmt;
	PreparedStatement isValidLoginStmt;

	public UserRepositroy() throws RepositoryException {
		this.className = "Class: UserRepository. ";

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

	public UserRepositroy(Connection conn) throws RepositoryException {
		this.className = "Class: UserRepository. ";
		prepareCalls(conn);
	}

	public void prepareCalls(Connection conn) throws RepositoryException {
		String get = "SELECT * FROM LIMS_USER WHERE USER_ID=?";
		String all = "SELECT * FROM LIMS_USER WHERE ACTIVE = 1 ORDER BY NAME";
		this.sqlCreate = "{call core_pkg.insertuser(?,?,?,?,?,?)}";
		this.sqlUpdate = "{call core_pkg.updateuser(?,?,?,?,?,?,?,?)}";
		this.sqlRemove = "Remove ";
		String getByUserName = "SELECT * FROM LIMS_USER WHERE USERNAME = ?";
		String isValidLogin = "SELECT * FROM LIMS_USER WHERE ACTIVE = 1 AND USERNAME = ? AND PASSWORD = ?";

		try {
			getStmt = conn.prepareStatement(get);
			getAllStmt = conn.prepareStatement(all);
			putStmt = conn.prepareCall(sqlCreate);
			updStmt = conn.prepareCall(sqlUpdate);
			remStmt = conn.prepareCall(sqlRemove);
			getByUserNameStmt = conn.prepareStatement(getByUserName);
			isValidLoginStmt = conn.prepareStatement(isValidLogin);
		} catch (SQLException e) {
			throw new RepositoryException(className
					+ "SQLException caught in constructor. " + e.getMessage());
		}
	}

	public void closeCalls() throws RepositoryException {
		try {
			getStmt.close();
			getAllStmt.close();
			putStmt.close();
			updStmt.close();
			remStmt.close();
			getByUserNameStmt.close();
			isValidLoginStmt.close();
		} catch (SQLException e) {
			throw new RepositoryException(className
					+ "Calls could not be closed. " + e.getMessage());
		}
	}

	public UserBean makeBean(ResultSet results) throws RepositoryException {
		try {
			UserBean user = new UserBean();
			user.setId(results.getInt("USER_ID"));
			user.setUserNumber(results.getInt("USER_NUMBER"));
			user.setTypeId(results.getInt("EMPLOYEETYPE_ID"));
			user.setName(results.getString("NAME"));
			user.setUsername(results.getString("USERNAME"));
			user.setPassword(results.getString("PASSWORD"));
			user.setActive(results.getInt("ACTIVE"));
			user.setDepartmentId(results.getInt("DEPARTMENT_ID"));

			return user;
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
		try {
			ResultSet results;
			getStmt.clearParameters();
			getStmt.setInt(1, id);
			results = getStmt.executeQuery();

			if (results.next())
				return makeBean(results);
			else
				throw new RepositoryException(this.className
						+ "RepositoryException caught in method get. "
						+ "Could not find USER_ID " + id);
		} catch (SQLException e) {
			throw new RepositoryException(this.className
					+ "SQLException caught in method get. " + e.getMessage());
		} catch (Exception e) {
			throw new RepositoryException(this.className
					+ "Unknown error caught in method get. " + e.getMessage());
		}
	}

	public Item[] getList(int id) throws RepositoryException {
		return null;
	}

	public Item[] getAll() throws RepositoryException {
		try {
			Collection users = new ArrayList();
			ResultSet results;
			results = getAllStmt.executeQuery();
			while (results.next()) {
				users.add(results);
			}
			return (UserBean[]) users.toArray(new UserBean[0]);
		} catch (SQLException e) {
			throw new RepositoryException(className
					+ "SQLException caught in method getAll. " + e.getMessage());
		} catch (Exception e) {
			throw new RepositoryException(className
					+ "Unknown error in method getAll. " + e.getMessage());
		}
	}

	public void put(Item myUserBean) throws RepositoryException {
		UserBean user = (UserBean) myUserBean;
		try {
			boolean bogus;

			putStmt.clearParameters();
			putStmt.setInt(1, user.getTypeId());
			putStmt.setString(2, user.getName());
			putStmt.setInt(3, user.getUserNumber());
			putStmt.setString(4, user.getUsername());
			putStmt.setString(5, user.getPassword());
			putStmt.setInt(6, user.getDepartmentId());

			bogus = putStmt.execute();
		} catch (SQLException e) {
			throw new RepositoryException(className
					+ "SQLException caught in method put. " + e.getMessage());
		} catch (Exception e) {
			throw new RepositoryException(className
					+ "unknown error caught in method put. " + e.getMessage());
		}
	}

	public void remove(Item myUserBean) throws RepositoryException {
		UserBean user = (UserBean) myUserBean;
		try {
			boolean bogus;

			remStmt.clearParameters();
			bogus = remStmt.execute();
		} catch (SQLException se) {
			throw new RepositoryException(className
					+ "SQLException caught in method remove. "
					+ se.getMessage());
		} catch (Exception e) {
			throw new RepositoryException(className
					+ "unknown error caught in method remove. "
					+ e.getMessage());
		}
	}

	public void update(Item myUserBean) throws RepositoryException {
		UserBean user = (UserBean) myUserBean;
		try {
			boolean bogus;

			updStmt.clearParameters();
			updStmt.setInt(1, user.getId());
			updStmt.setInt(2, user.getTypeId());
			updStmt.setString(3, user.getName());
			updStmt.setInt(4, user.getUserNumber());
			updStmt.setString(5, user.getUsername());
			updStmt.setString(6, user.getPassword());
			updStmt.setInt(7, user.getDepartmentId());
			updStmt.setInt(8, user.getActive());

			bogus = updStmt.execute();
		} catch (SQLException e) {
			throw new RepositoryException(className
					+ "SQLException caught in method update. " + e.getMessage());
		} catch (Exception e) {
			throw new RepositoryException(className
					+ "unknown error caught in method update. " + e.getMessage());
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

	public Item getByUserName(String username) throws RepositoryException {
		try {
			ResultSet results;
			getByUserNameStmt.clearParameters();
			getByUserNameStmt.setString(1, username);
			results = getByUserNameStmt.executeQuery();

			if (results.next())
				return makeBean(results);
			else
				throw new RepositoryException(
						this.className
								+ "RepositoryException caught in method getByUserName. "
								+ "Could not find USER " + username);
		} catch (SQLException e) {
			throw new RepositoryException(this.className
					+ "SQLException caught in method getByUserName. "
					+ e.getMessage());
		} catch (Exception e) {
			throw new RepositoryException(this.className
					+ "Unknown error caught in method getByUserName. "
					+ e.getMessage());
		}
	}

	public int isValidLogin(String username, String password)
			throws RepositoryException {
		try {
			ResultSet results;
			isValidLoginStmt.clearParameters();
			isValidLoginStmt.setString(1, username);
			isValidLoginStmt.setString(2, password);
			results = isValidLoginStmt.executeQuery();

			if (results.next())
				return 1;
			else
				return 0;
		} catch (SQLException e) {
			throw new RepositoryException(this.className
					+ "SQLException caught in method isValidLogin. "
					+ e.getMessage());
		} catch (Exception e) {
			throw new RepositoryException(this.className
					+ "Unknown error caught in method isValidLogin. "
					+ e.getMessage());
		}
	}
}
