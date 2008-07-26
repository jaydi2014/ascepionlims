package com.ascepionpharm.lims.repository.core;

import java.sql.*;
import java.util.*;

import com.ascepionpharm.lims.entity.core.EmployeeBean;
import com.ascepionpharm.lims.entity.core.PurchasingBean;
import com.ascepionpharm.lims.entity.core.UserBean;
import com.ascepionpharm.lims.universal.*;

/**
 * PurchasingRepository: performs all database actions to the PURCHASING table.
 * 
 * @author Li Jun Mulin
 * @version
 * 
 */

public class PurchasingRepository extends LIMSRepository implements
		Destroyable, Closable {
	PreparedStatement getByNameStmt;
	PreparedStatement getByPersonStmt;

	public PurchasingRepository() throws RepositoryException {
		this.className = "Class: PurchasingRepository. ";

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

	public PurchasingRepository(Connection conn) throws RepositoryException {
		this.className = "Class: PurchasingRepository. ";
		prepareCalls(conn);
	}

	public void prepareCalls(Connection conn) throws RepositoryException {
		String get = "SELECT * FROM PURCHASING WHERE PURCHASE_ID=?";
		String all = "SELECT * FROM PURCHASING ORDER BY PURCHASE_ID";
		this.sqlCreate = "{call core_pkg.insertpurchasing(?,?,?,?,?,?,?,?,?)}";
		this.sqlUpdate = "{call core_pkg.updatepurchasing(?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
		this.sqlRemove = "Remove ";
		String getbyname = "SELECT * FROM PURCHASING WHERE PUCHASE_NAME LIKE %?%";
		String getbyperson = "SELECT * FROM PURCHASING WHERE PUCHASE_PERSON LIKE %?%";

		try {
			getStmt = conn.prepareStatement(get);
			getAllStmt = conn.prepareStatement(all);
			putStmt = conn.prepareCall(sqlCreate);
			updStmt = conn.prepareCall(sqlUpdate);
			remStmt = conn.prepareCall(sqlRemove);
			getByNameStmt = conn.prepareStatement(getbyname);
			getByPersonStmt = conn.prepareStatement(getbyperson);
		} catch (SQLException e) {
			throw new RepositoryException(className
					+ "SQLException caught in constructor. " + e.getMessage());
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

	public PurchasingBean makeBean(ResultSet results)
			throws RepositoryException {
		try {
			PurchasingBean purchase = new PurchasingBean();
			purchase.setId(results.getInt("PURCHASE_ID"));
			purchase.setPurchaseDate(results.getDate("PURCHASE_DATE"));
			purchase.setPurchaseSource(results.getString("PURCHASE_SOURCE"));
			purchase.setPurchaseName(results.getString("PURCHASE_NAME"));
			purchase.setPurchaseNumber(results.getInt("PURCHASE_NUMBER"));
			purchase.setTotleprice(results.getFloat("TOTLEPRICE"));
			purchase.setInvoiceArriveTime(results
					.getDate("INVOICE_ARRIVE_TIME"));
			purchase.setInvoiceNumber(results.getString("INVOICE_NUMBER"));
			purchase.setPaymentway(results.getString("PAYMENTWAY"));
			purchase.setIsArrive(results.getInt("ISARRIVE"));
			purchase.setCommentLine(results.getString("COMMENTLINE"));
			purchase.setIsApproved(results.getInt("ISAPPROVED"));
			purchase.setPurchasePerson(results.getString("PURCHASE_PERSON"));
			purchase.setDempartmentId(results.getInt("DEPARTMENT_ID"));
			purchase.setProjectId(results.getInt("PROJECT_ID"));
			purchase.setItemId(results.getInt("ITEM_ID"));
			purchase.setBankId(results.getInt("BANK_ID"));

			return purchase;
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
	
	public void closeCalls() throws RepositoryException {
		try {
			getStmt.close();
			getAllStmt.close();
			putStmt.close();
			updStmt.close();
			remStmt.close();
			getByNameStmt.close();
			getByPersonStmt.close();
		} catch (SQLException e) {
			throw new RepositoryException(className
					+ "Calls could not be closed. " + e.getMessage());
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
						+ "Could not find EMPLOYEE_ID " + id);
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
			Collection purchases = new ArrayList();
			results = getAllStmt.executeQuery();

			while (results.next()) {
				purchases.add(makeBean(results));
			}
			return (PurchasingBean[]) purchases.toArray(new PurchasingBean[0]);
		} catch (SQLException e) {
			throw new RepositoryException(className
					+ "SQLException caught in method getAll. " + e.getMessage());
		} catch (Exception e) {
			throw new RepositoryException(className
					+ "Unknown error caught in method getAll. "
					+ e.getMessage());
		}
	}

	public Item[] getList(int id) throws RepositoryException {
		return null;
	}

	public void put(Item myPurchasingBean) throws RepositoryException {
		PurchasingBean purchase = (PurchasingBean) myPurchasingBean;

		try {
			boolean bogus;

			putStmt.clearParameters();
			putStmt.setInt(1, purchase.getItemId());
			putStmt.setInt(2, purchase.getDempartmentId());
			putStmt.setInt(3, purchase.getProjectId());
			putStmt.setString(4, purchase.getPurchaseSource());
			putStmt.setString(5, purchase.getPurchasePerson());
			putStmt.setString(6, purchase.getPurchaseName());
			putStmt.setInt(7, purchase.getPurchaseNumber());
			putStmt.setFloat(8, purchase.getTotleprice());
			putStmt.setString(9, purchase.getCommentLine());

			bogus = putStmt.execute();
		} catch (SQLException e) {
			throw new RepositoryException(className
					+ "SQLException caught in method put. " + e.getMessage());
		} catch (Exception e) {
			throw new RepositoryException(className
					+ "unknown error caught in method put. " + e.getMessage());
		}
	}

	public void remove(Item item) throws RepositoryException {

	}

	public void update(Item myPurchasingBean) throws RepositoryException {
		PurchasingBean purchase = (PurchasingBean) myPurchasingBean;

		try {
			boolean bogus;

			putStmt.clearParameters();
			putStmt.setInt(1, purchase.getId());
			putStmt.setInt(2, purchase.getBankId());
			putStmt.setDate(3, purchase.getPurchaseDate());
			putStmt.setString(4, purchase.getPurchaseSource());
			putStmt.setString(5, purchase.getPurchasePerson());
			putStmt.setString(6, purchase.getPurchaseName());
			putStmt.setInt(7, purchase.getPurchaseNumber());
			putStmt.setFloat(8, purchase.getTotleprice());
			putStmt.setDate(9, purchase.getInvoiceArriveTime());
			putStmt.setString(10, purchase.getInvoiceNumber());
			putStmt.setString(11, purchase.getPaymentway());
			putStmt.setInt(12, purchase.getIsArrive());
			putStmt.setString(13, purchase.getCommentLine());
			putStmt.setInt(14, purchase.getIsApproved());
	
			bogus = putStmt.execute();
		} catch (SQLException e) {
			throw new RepositoryException(className
					+ "SQLException caught in method put. " + e.getMessage());
		} catch (Exception e) {
			throw new RepositoryException(className
					+ "unknown error caught in method put. " + e.getMessage());
		}
	}
	
}
