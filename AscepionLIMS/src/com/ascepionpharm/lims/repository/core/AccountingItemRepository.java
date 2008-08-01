package com.ascepionpharm.lims.repository.core;

import java.sql.*;
import java.util.*;

import com.ascepionpharm.lims.entity.core.*;
import com.ascepionpharm.lims.universal.*;

/**
 * DepartmentRepository: performs all database actions to the ACCOUNTINGITEM
 * table.
 * 
 * @author Li Jun Mulin
 * @version
 * 
 */

public class AccountingItemRepository extends LIMSRepository implements
		Destroyable, Closable {

	PreparedStatement insertItem;
	
	public AccountingItemRepository() throws RepositoryException {
		this.className = "Class: AccountingItemRepository. ";

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

	public AccountingItemRepository(Connection conn) throws RepositoryException {
		this.className = "Class: AccountingItemRepository. ";
		prepareCalls(conn);
	}
	
	public void prepareCalls(Connection conn) throws RepositoryException {
		String get = "SELECT * FROM ACCOUNTINGITEM WHERE ITEM_ID = ? ";
		String all = "SELECT * FROM ACCOUNTINGITEM ";
		String insertpurchaseitem = "INSERT INTO ACCOUNTINGITEM VALUES (ACCOUNTINGITEM_SEQ.NEXTVAL,?,?)";

		try {
			getStmt = conn.prepareStatement(get);
			getAllStmt = conn.prepareStatement(all);
			insertItem = conn.prepareStatement(insertpurchaseitem);
		} catch (SQLException e) {
			throw new RepositoryException(className
					+ "SQLException caught in constructor. " + e.getMessage());
		}
	}
	
	public void closeCalls() throws RepositoryException {
		try {
			getStmt.close();
			getAllStmt.close();
			insertItem.close();
		} catch (SQLException e) {
			throw new RepositoryException(className
					+ "Calls could not be closed. " + e.getMessage());
		}
	}
	
	private AccountingItemBean makeBean(ResultSet results) throws RepositoryException {
		try {
			AccountingItemBean accountitem = new AccountingItemBean();
			accountitem.setId(results.getInt("ITEM_ID"));
			accountitem.setName(results.getString("NAME"));
			accountitem.setCommentline(results.getString("COMMENTLINE"));

			return accountitem;
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
						+ "Could not find ITEM_ID " + id);
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
			Collection accountingitems = new ArrayList();
			results = getAllStmt.executeQuery();

			while (results.next()) {
				accountingitems.add(makeBean(results));
			}
			return (AccountingItemBean[]) accountingitems
					.toArray(new AccountingItemBean[0]);
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
	
	public void insertItem(Item myAccountingItem) throws RepositoryException{
		AccountingItemBean item = (AccountingItemBean) myAccountingItem;
		try {
			boolean bogus;
			insertItem.clearParameters();
			insertItem.setString(1, item.getName());
			insertItem.setString(2, item.getCommentline());
			
			bogus = insertItem.execute();
		} catch (SQLException e) {
			throw new RepositoryException(className
					+ "SQLException caught in method insertItem. "
					+ e.getMessage());
		} catch (Exception e) {
			throw new RepositoryException(className
					+ "Unknown error caught in method insertItem. "
					+ e.getMessage());
		}
	}

}
