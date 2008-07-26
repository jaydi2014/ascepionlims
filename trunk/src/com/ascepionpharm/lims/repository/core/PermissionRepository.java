package com.ascepionpharm.lims.repository.core;

import java.sql.*;
import java.util.*;

import com.ascepionpharm.lims.entity.core.*;
import com.ascepionpharm.lims.universal.*;

/**
 * PermissionRepository: performs all database actions to the PERMISSIONS table.
 *
 * @author Li Jun Mulin
 * @version 
 *
 */

public class PermissionRepository extends LIMSRepository implements Destroyable,
		Closable {
	CallableStatement insertPageStmt;
	CallableStatement grantPermissionStmt;

	public PermissionRepository() throws RepositoryException {
		this.className = "Class: PermissionRepository. ";

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

	public PermissionRepository(Connection conn) throws RepositoryException {
		this.className = "Class: PermissionRepository. ";

		prepareCalls(conn);
	}

	public void prepareCalls(Connection conn) throws RepositoryException {
		String get = "SELECT L.PAGE_ID,L.PAGE_NAME,P.EMPLOYEETYPE_ID,P.GRANTED FROM PERMISSIONS P,LIMS_PAGE L WHERE L.PAGE_ID=P.PAGE_ID AND P.EMPLOYEETYPE_ID=?";
		this.sqlCreate = " ";
		this.sqlUpdate = " ";
		this.sqlRemove = " ";
		String insertPage = "{call core_pkg.insertlimspage(?)}";
		String grantPermission = "{call core_pkg.givepermission(?,?,?)}";

		try {
			getStmt = conn.prepareStatement(get);
			putStmt = conn.prepareCall(sqlCreate);
			updStmt = conn.prepareCall(sqlUpdate);
			remStmt = conn.prepareCall(sqlRemove);
			getNextSeqId = conn.prepareCall(seqSql);
			insertPageStmt = conn.prepareCall(insertPage);
			grantPermissionStmt = conn.prepareCall(grantPermission);
		} catch (SQLException e) {
			throw new RepositoryException(className
					+ "SQLException caught in constructor. " + e.getMessage());
		}
	}

	public void closeCalls() throws RepositoryException {
		try {
			getStmt.close();
			putStmt.close();
			updStmt.close();
			remStmt.close();
			getNextSeqId.close();
			insertPageStmt.close();
			grantPermissionStmt.close();
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

	private PermissionBean makeBean(ResultSet results)
			throws RepositoryException {
		try {
			PermissionBean permission = new PermissionBean();
			permission.setEmployeeTypeId(results.getInt("EMPLOYEETYPE_ID"));
			permission.setPageId(results.getInt("PAGE_ID"));
			permission.setURI(results.getString("PAGE_NAME"));
			permission.setGranted(results.getInt("GRANTED"));
			return permission;
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

	public Item[] getList(int employeetypeid) throws RepositoryException {
		try {
			ResultSet results;
			getStmt.clearParameters();
			Collection permissions = new ArrayList();
			getStmt.setInt(1, employeetypeid);
			results = getStmt.executeQuery();

			while(results.next()){
				permissions.add(makeBean(results));
			}
			return (PermissionBean[])permissions.toArray(new PermissionBean[0]);
		} catch (SQLException e) {
			throw new RepositoryException(className
					+ "SQLException caught in method get. " + e.getMessage());
		} catch (Exception e) {
			throw new RepositoryException(className
					+ "Unknown error caught in method get. " + e.getMessage());
		}
	}
	
	public Item get(int id) throws RepositoryException {
		return null;
	}

	public Item[] getAll() throws RepositoryException {
		return null;
	}

	public void put(Item item) throws RepositoryException {

	}

	public void remove(Item item) throws RepositoryException {

	}

	public void update(Item item) throws RepositoryException {

	}

	public void insertPage(Item myPageBean) throws RepositoryException {
		PageBean page = (PageBean) myPageBean;
		try {
			boolean bogus;
			insertPageStmt.clearParameters();
			insertPageStmt.setString(1, page.getName());
			
			bogus = insertPageStmt.execute();
		} catch (SQLException e) {
			throw new RepositoryException(className
					+ "SQLException caught in method insertPage. "
					+ e.getMessage());
		} catch (Exception e) {
			throw new RepositoryException(className
					+ "Unknown error caught in method insertPage. "
					+ e.getMessage());
		}
	}
	
	public void grantPermission(Item myPermissionBean) throws RepositoryException{
		PermissionBean permission = (PermissionBean) myPermissionBean;
		try {
			boolean bogus;
			grantPermissionStmt.clearParameters();
			grantPermissionStmt.setInt(1, permission.getPageId());
			grantPermissionStmt.setInt(2, permission.getEmployeeTypeId());
			grantPermissionStmt.setInt(3, permission.getGranted());
			
			bogus = grantPermissionStmt.execute();
		} catch (SQLException e) {
			throw new RepositoryException(className
					+ "SQLException caught in method grantPermission. "
					+ e.getMessage());
		} catch (Exception e) {
			throw new RepositoryException(className
					+ "Unknown error caught in method grantPermission. "
					+ e.getMessage());
		}
	}
}
