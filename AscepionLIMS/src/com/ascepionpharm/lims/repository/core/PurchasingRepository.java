package com.ascepionpharm.lims.repository.core;

import java.sql.*;
import java.sql.Date;
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
	PreparedStatement getByDateStmt;
	PreparedStatement getByTypeStmt;
	PreparedStatement getByProjectStmt;
	PreparedStatement getByDepartmentStmt;
	PreparedStatement getByAccountStmt;
	PreparedStatement approvedStmt;
	PreparedStatement deleteStmt;
	PreparedStatement getByPayedStmt;
	PreparedStatement getByInviceStmt;

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
		this.sqlUpdate = "{call core_pkg.updatepurchasing(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
		this.sqlRemove = "Remove ";
		String getbyperson = "SELECT * FROM PURCHASING WHERE PURCHASE_PERSON LIKE ? ORDER BY PURCHASE_DATE";
		String getbyname = "SELECT * FROM PURCHASING WHERE PURCHASE_NAME LIKE ? ORDER BY PURCHASE_DATE";
		String getbydate = "SELECT * FROM PURCHASING WHERE PURCHASE_DATE BETWEEN ? AND ? ORDER BY PURCHASE_DATE";
		String getbytype = "SELECT * FROM PURCHASING WHERE ITEM_ID = ? ORDER BY PURCHASE_DATE";
		String getbyproject = "SELECT * FROM PURCHASING WHERE PROJECT_ID = ? ORDER BY PURCHASE_DATE";
		String getbydepartment = "SELECT * FROM PURCHASING WHERE DEPARTMENT_ID = ? ORDER BY PURCHASE_DATE";
		String getbyaccount = "SELECT * FROM PURCHASING WHERE BANK_ID = ? ORDER BY PURCHASE_DATE";
		String approved = "UPDATE PURCHASING set ISAPPROVED=1 WHERE PURCHASE_ID=?";
		String delete = "DELETE FROM PURCHASING WHERE PURCHASE_ID=?";
		String getbypayed = "SELECT * FROM PURCHASING WHERE ISPAYED = ? ORDER BY PURCHASE_DATE";
		String getbyinvoice = "SELECT * FROM PURCHASING WHERE INVOICE_ARRIVE_TIME BETWEEN ? AND ? ORDER BY PURCHASE_DATE";

		try {
			getStmt = conn.prepareStatement(get);
			getAllStmt = conn.prepareStatement(all);
			putStmt = conn.prepareCall(sqlCreate);
			updStmt = conn.prepareCall(sqlUpdate);
			remStmt = conn.prepareCall(sqlRemove);
			getByNameStmt = conn.prepareStatement(getbyname);
			getByPersonStmt = conn.prepareStatement(getbyperson);
			getByDateStmt = conn.prepareStatement(getbydate);
			getByTypeStmt = conn.prepareStatement(getbytype);
			getByProjectStmt = conn.prepareStatement(getbyproject);
			getByDepartmentStmt = conn.prepareStatement(getbydepartment);
			getByAccountStmt = conn.prepareStatement(getbyaccount);
			approvedStmt = conn.prepareStatement(approved);
			deleteStmt = conn.prepareStatement(delete);
			getByPayedStmt = conn.prepareStatement(getbypayed);
			getByInviceStmt = conn.prepareStatement(getbyinvoice);
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
			purchase.setIsPayed(results.getInt("ISPAYED"));

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
			getByDateStmt.close();
			getByTypeStmt.close();
			getByProjectStmt.close();
			getByDepartmentStmt.close();
			getByAccountStmt.close();
			approvedStmt.close();
			deleteStmt.close();
			getByPayedStmt.close();
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

			updStmt.clearParameters();
			updStmt.setInt(1, purchase.getId());
			updStmt.setInt(2, purchase.getBankId());
			updStmt.setDate(3, purchase.getPurchaseDate());
			updStmt.setString(4, purchase.getPurchaseSource());
			updStmt.setString(5, purchase.getPurchasePerson());
			updStmt.setString(6, purchase.getPurchaseName());
			updStmt.setInt(7, purchase.getPurchaseNumber());
			updStmt.setFloat(8, purchase.getTotleprice());
			updStmt.setDate(9, purchase.getInvoiceArriveTime());
			updStmt.setString(10, purchase.getInvoiceNumber());
			updStmt.setString(11, purchase.getPaymentway());
			updStmt.setInt(12, purchase.getIsArrive());
			updStmt.setString(13, purchase.getCommentLine());
			updStmt.setInt(14, purchase.getIsApproved());
			updStmt.setInt(15, purchase.getIsPayed());

			bogus = updStmt.execute();
		} catch (SQLException e) {
			throw new RepositoryException(className
					+ "SQLException caught in method update. " + e.getMessage());
		} catch (Exception e) {
			throw new RepositoryException(className
					+ "unknown error caught in method update. "
					+ e.getMessage());
		}
	}

	public Item[] getByName(String warename) throws RepositoryException {
		try {
			ResultSet results;
			getByNameStmt.clearParameters();
			getByNameStmt.setString(1, warename);
			Collection purchases = new ArrayList();
			results = getByNameStmt.executeQuery();

			while (results.next()) {
				purchases.add(makeBean(results));
			}
			return (PurchasingBean[]) purchases.toArray(new PurchasingBean[0]);
		} catch (SQLException e) {
			throw new RepositoryException(className
					+ "SQLException caught in method getByName. "
					+ e.getMessage());
		} catch (Exception e) {
			throw new RepositoryException(className
					+ "Unknown error caught in method getByName. "
					+ e.getMessage());
		}
	}

	public Item[] getByPerson(String person) throws RepositoryException {
		try {
			ResultSet results;
			getByPersonStmt.clearParameters();
			getByPersonStmt.setString(1, person);
			Collection purchases = new ArrayList();
			results = getByPersonStmt.executeQuery();

			while (results.next()) {
				purchases.add(makeBean(results));
			}
			return (PurchasingBean[]) purchases.toArray(new PurchasingBean[0]);
		} catch (SQLException e) {
			throw new RepositoryException(className
					+ "SQLException caught in method getByPerson. "
					+ e.getMessage());
		} catch (Exception e) {
			throw new RepositoryException(className
					+ "Unknown error caught in method getByPerson. "
					+ e.getMessage());
		}
	}

	public Item[] getByDate(Date startDate, Date endDate)
			throws RepositoryException {
		try {
			ResultSet results;
			getByDateStmt.clearParameters();
			getByDateStmt.setDate(1, startDate);
			getByDateStmt.setDate(2, endDate);
			Collection purchases = new ArrayList();
			results = getByDateStmt.executeQuery();

			while (results.next()) {
				purchases.add(makeBean(results));
			}
			return (PurchasingBean[]) purchases.toArray(new PurchasingBean[0]);
		} catch (SQLException e) {
			throw new RepositoryException(className
					+ "SQLException caught in method getByDate. "
					+ e.getMessage());
		} catch (Exception e) {
			throw new RepositoryException(className
					+ "Unknown error caught in method getByDate. "
					+ e.getMessage());
		}
	}

	public Item[] getByType(int itemid) throws RepositoryException {
		try {
			ResultSet results;
			getByTypeStmt.clearParameters();
			getByTypeStmt.setInt(1, itemid);
			Collection purchases = new ArrayList();
			results = getByTypeStmt.executeQuery();

			while (results.next()) {
				purchases.add(makeBean(results));
			}
			return (PurchasingBean[]) purchases.toArray(new PurchasingBean[0]);
		} catch (SQLException e) {
			throw new RepositoryException(className
					+ "SQLException caught in method getByType. "
					+ e.getMessage());
		} catch (Exception e) {
			throw new RepositoryException(className
					+ "Unknown error caught in method getByType. "
					+ e.getMessage());
		}
	}

	public Item[] getByProject(int projectid) throws RepositoryException {
		try {
			ResultSet results;
			getByProjectStmt.clearParameters();
			getByProjectStmt.setInt(1, projectid);
			Collection purchases = new ArrayList();
			results = getByProjectStmt.executeQuery();

			while (results.next()) {
				purchases.add(makeBean(results));
			}
			return (PurchasingBean[]) purchases.toArray(new PurchasingBean[0]);
		} catch (SQLException e) {
			throw new RepositoryException(className
					+ "SQLException caught in method getByProject. "
					+ e.getMessage());
		} catch (Exception e) {
			throw new RepositoryException(className
					+ "Unknown error caught in method getByProject. "
					+ e.getMessage());
		}
	}

	public Item[] getByDepartment(int departmentid) throws RepositoryException {
		try {
			ResultSet results;
			getByDepartmentStmt.clearParameters();
			getByDepartmentStmt.setInt(1, departmentid);
			Collection purchases = new ArrayList();
			results = getByDepartmentStmt.executeQuery();

			while (results.next()) {
				purchases.add(makeBean(results));
			}
			return (PurchasingBean[]) purchases.toArray(new PurchasingBean[0]);
		} catch (SQLException e) {
			throw new RepositoryException(className
					+ "SQLException caught in method getByDepartment. "
					+ e.getMessage());
		} catch (Exception e) {
			throw new RepositoryException(className
					+ "Unknown error caught in method getByDepartment. "
					+ e.getMessage());
		}
	}

	public Item[] getByAccount(int bankid) throws RepositoryException {
		try {
			ResultSet results;
			getByAccountStmt.clearParameters();
			getByAccountStmt.setInt(1, bankid);
			Collection purchases = new ArrayList();
			results = getByAccountStmt.executeQuery();

			while (results.next()) {
				purchases.add(makeBean(results));
			}
			return (PurchasingBean[]) purchases.toArray(new PurchasingBean[0]);
		} catch (SQLException e) {
			throw new RepositoryException(className
					+ "SQLException caught in method getByAccount. "
					+ e.getMessage());
		} catch (Exception e) {
			throw new RepositoryException(className
					+ "Unknown error caught in method getByAccount. "
					+ e.getMessage());
		}
	}

	public void approved(int id) throws RepositoryException {
		try {
			approvedStmt.clearParameters();
			approvedStmt.setInt(1, id);
			approvedStmt.executeQuery();

		} catch (SQLException e) {
			throw new RepositoryException(className
					+ "SQLException caught in method approved. "
					+ e.getMessage());
		} catch (Exception e) {
			throw new RepositoryException(className
					+ "Unknown error caught in method approved. "
					+ e.getMessage());
		}
	}

	public void delete(int id) throws RepositoryException {
		try {
			deleteStmt.clearParameters();
			deleteStmt.setInt(1, id);
			deleteStmt.executeQuery();

		} catch (SQLException e) {
			throw new RepositoryException(className
					+ "SQLException caught in method delete. " + e.getMessage());
		} catch (Exception e) {
			throw new RepositoryException(className
					+ "Unknown error caught in method delete. "
					+ e.getMessage());
		}
	}

	public Item[] getByPayed(int ispayed) throws RepositoryException {
		try {
			ResultSet results;
			getByPayedStmt.clearParameters();
			getByPayedStmt.setInt(1, ispayed);
			Collection purchases = new ArrayList();
			results = getByPayedStmt.executeQuery();

			while (results.next()) {
				purchases.add(makeBean(results));
			}
			return (PurchasingBean[]) purchases.toArray(new PurchasingBean[0]);
		} catch (SQLException e) {
			throw new RepositoryException(className
					+ "SQLException caught in method getByPayed. "
					+ e.getMessage());
		} catch (Exception e) {
			throw new RepositoryException(className
					+ "Unknown error caught in method getByPayed. "
					+ e.getMessage());
		}
	}

	public Item[] getByInvoice(Date startDate, Date endDate)
			throws RepositoryException {
		try {
			ResultSet results;
			getByInviceStmt.clearParameters();
			getByInviceStmt.setDate(1, startDate);
			getByInviceStmt.setDate(2, endDate);
			Collection purchases = new ArrayList();
			results = getByInviceStmt.executeQuery();

			while (results.next()) {
				purchases.add(makeBean(results));
			}
			return (PurchasingBean[]) purchases.toArray(new PurchasingBean[0]);
		} catch (SQLException e) {
			throw new RepositoryException(className
					+ "SQLException caught in method getByInvoice. "
					+ e.getMessage());
		} catch (Exception e) {
			throw new RepositoryException(className
					+ "Unknown error caught in method getByInvoice. "
					+ e.getMessage());
		}
	}

	public static void main(String[] arg) {

		try {

			Class.forName("oracle.jdbc.driver.OracleDriver");

			Connection cn = DriverManager
					.getConnection("jdbc:oracle:thin:lims/limsprod@localhost:1521:DEV1");
			new PurchasingRepository(cn).delete(61);
		} catch (RepositoryException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
