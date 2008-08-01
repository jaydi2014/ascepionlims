package com.ascepionpharm.lims.repository.core;

import java.sql.*;
import java.util.*;

import com.ascepionpharm.lims.entity.core.*;
import com.ascepionpharm.lims.universal.*;

/**
 * EmployeeRepository: performs all database actions to the EMPLOYEE table.
 *
 * @author Li Jun Mulin
 * @version 
 *
 */

public class EmployeeRepository extends LIMSRepository implements Destroyable,
		Closable {

	PreparedStatement getAllActiveStmt;
	PreparedStatement getAllInactiveStmt;
	PreparedStatement getSupervisorByIdStmt;

	public EmployeeRepository() throws RepositoryException {
		this.className = "Class: EmployeeRepository. ";

		try {
			Class.forName(myDbBean.getDriver());
			connection = DriverManager.getConnection(myDbBean.getDbURL());
			prepareCalls(connection);
		} catch (ClassNotFoundException e) {
			throw new RepositoryException(className + e.getMessage());
		} catch (SQLException e) {
			throw new RepositoryException(className
					+ "SQLException caught in constructor. " + e.getMessage());
		}
	}

	public EmployeeRepository(Connection conn) throws RepositoryException {
		this.className = "Class: EmployeeRepository. ";
		prepareCalls(conn);
	}

	public void prepareCalls(Connection conn) throws RepositoryException {
		String get = "SELECT * FROM EMPLOYEE WHERE EMPLOYEE_ID=?";
		String all = "SELECT * FROM EMPLOYEE ORDER BY NAME";
		this.sqlCreate = "{call core_pkg.insertemployee(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
		this.sqlUpdate = "{call core_pkg.updateemployee(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
		this.sqlRemove = "Remove ";
		String allactive = "SELECT * FROM EMPLOYEE WHERE ACTIVE = 1 ORDER BY to_number(INIT)";
		String allinactive = "SELECT * FROM EMPLOYEE WHERE ACTIVE = 0 ORDER BY to_number(INIT)";
        String supervisorbyid = "SELECT NAME FROM EMPLOYEE WHERE EMPLOYEE_ID=?";

		try {
			getStmt = conn.prepareStatement(get);
			getAllStmt = conn.prepareStatement(all);
			putStmt = conn.prepareCall(sqlCreate);
			updStmt = conn.prepareCall(sqlUpdate);
			remStmt = conn.prepareCall(sqlRemove);
			getAllActiveStmt = conn.prepareStatement(allactive);
			getAllInactiveStmt = conn.prepareStatement(allinactive);
			getSupervisorByIdStmt = conn.prepareStatement(supervisorbyid);
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
			getAllActiveStmt.close();
			getAllInactiveStmt.close();
			getSupervisorByIdStmt.close();
		} catch (SQLException e) {
			throw new RepositoryException(className
					+ "Calls could not be closed. " + e.getMessage());
		}
	}

	private EmployeeBean makeBean(ResultSet results) throws RepositoryException {
		try {
			EmployeeBean employee = new EmployeeBean();
			employee.setId(results.getInt("EMPLOYEE_ID"));
			employee.setEmployeeNumber(results.getString("EMPLOYEE_NUMBER"));
			employee.setTypeId(results.getInt("EMPLOYEETYPE_ID"));
			employee.setName(results.getString("NAME"));
			employee.setAge(results.getInt("AGE"));
			employee.setUsername(results.getString("USERNAME"));
			employee.setPassword(results.getString("PASSWORD"));
			employee.setSex(results.getString("SEX"));
			employee.setActive(results.getInt("ACTIVE"));
			employee.setDepartmentId(results.getInt("DEPARTMENT_ID"));
			employee.setIdentity(results.getString("IDENTITY"));
			employee.setEmail(results.getString("EMAIL"));
			employee.setLabPhone(results.getString("LAB_PHONE"));
			employee.setMobilePhone(results.getString("MOBILE_PHONE"));
			employee.setTitle(results.getString("TITLE"));
			employee.setParty(results.getString("PARTY"));
			employee.setBrith(results.getDate("BRITH"));
			employee.setPactStartTime(results.getDate("PACT_START_TIME"));
			employee.setPactEndTime(results.getDate("PACT_END_TIME"));
			employee.setEntranceTime(results.getDate("ENTRANCE_TIME"));
			employee.setLeaveTime(results.getDate("LEAVE_TIME"));
			employee.setDegree(results.getString("DEGREE"));
			employee.setSchool(results.getString("SCHOOL"));
			employee.setRecordplace(results.getString("RECORDPLACE"));
			employee.setCommentline(results.getString("COMMENTLINE"));
			employee.setSupervisorId(results.getInt("SUPERVISOR_ID"));
			employee.setNavisionCode(results.getString("NAVISION_CODE"));
			employee.setNativeplace(results.getString("NATIVEPLACE"));
			employee.setInit(results.getString("INIT"));

			return employee;
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

	public void destroy() throws RepositoryException{
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
						+ "Could not find EMPLOYEE_ID " + id);
		} catch (SQLException e) {
			throw new RepositoryException(className
					+ "SQLException caught in method get. " + e.getMessage());
		} catch (Exception e) {
			throw new RepositoryException(className
					+ "Unknown error caught in method get. " + e.getMessage());
		}
	}

	public Item[] getList(int id) throws RepositoryException {
		return null;
	}

	public Item[] getAll() throws RepositoryException {
		try {
			ResultSet results;
			getAllStmt.clearParameters();
			Collection employees = new ArrayList();
			results = getAllStmt.executeQuery();

			while (results.next()) {
				employees.add(makeBean(results));
			}
			return (EmployeeBean[]) employees
					.toArray(new EmployeeBean[0]);
		} catch (SQLException e) {
			throw new RepositoryException(className
					+ "SQLException caught in method getAll. " + e.getMessage());
		} catch (Exception e) {
			throw new RepositoryException(className
					+ "Unknown error caught in method getAll. " + e.getMessage());
		}
	}

	public void put(Item myEmployeeBean) throws RepositoryException {
		EmployeeBean employee = (EmployeeBean)myEmployeeBean;
		
		try {
			boolean bogus;

			putStmt.clearParameters();
			putStmt.setInt(1, employee.getTypeId());
			putStmt.setString(2, employee.getEmployeeNumber());
			putStmt.setString(3, employee.getName());
			putStmt.setDate(4, employee.getBrith());
			putStmt.setInt(5, employee.getAge());
			putStmt.setString(6, employee.getSex());
			putStmt.setString(7, employee.getIdentity());
			putStmt.setString(8, employee.getNativeplace());
			putStmt.setString(9, employee.getDegree());
			putStmt.setString(10, employee.getSchool());
			putStmt.setDate(11, employee.getPactStartTime());
			putStmt.setDate(12, employee.getPactEndTime());
			putStmt.setDate(13, employee.getEntranceTime());
			putStmt.setDate(14, employee.getLeaveTime());
			putStmt.setString(15, employee.getParty());
			putStmt.setString(16, employee.getRecordplace());
			putStmt.setString(17, employee.getCommentline());
			putStmt.setString(18, employee.getEmail());
			putStmt.setString(19, employee.getLabPhone());
			putStmt.setString(20, employee.getMobilePhone());
			putStmt.setString(21, employee.getTitle());
			putStmt.setInt(22, employee.getSupervisorId());
			putStmt.setString(23, employee.getNavisionCode());
			putStmt.setString(24, employee.getInit());
			putStmt.setInt(25, employee.getDepartmentId());
			
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

	public void update(Item myEmployeeBean) throws RepositoryException {
		EmployeeBean employee = (EmployeeBean) myEmployeeBean;
		try {
			boolean bogus;

			updStmt.clearParameters();
			updStmt.setInt(1, employee.getId());
			updStmt.setInt(2, employee.getTypeId());
			updStmt.setString(3, employee.getEmployeeNumber());
			updStmt.setString(4, employee.getName());
			updStmt.setDate(5, employee.getBrith());
			updStmt.setInt(6, employee.getActive());
			updStmt.setInt(7, employee.getAge());
			updStmt.setString(8, employee.getSex());
			updStmt.setString(9, employee.getIdentity());
			updStmt.setString(10, employee.getNativeplace());
			updStmt.setString(11, employee.getDegree());
			updStmt.setString(12, employee.getSchool());
			updStmt.setDate(13, employee.getPactStartTime());
			updStmt.setDate(14, employee.getPactEndTime());
			updStmt.setDate(15, employee.getEntranceTime());
			updStmt.setDate(16, employee.getLeaveTime());
			updStmt.setString(17, employee.getParty());
			updStmt.setString(18, employee.getRecordplace());
			updStmt.setString(19, employee.getCommentline());
			updStmt.setString(20, employee.getEmail());
			updStmt.setString(21, employee.getLabPhone());
			updStmt.setString(22, employee.getMobilePhone());
			updStmt.setString(23, employee.getTitle());
			updStmt.setInt(24, employee.getSupervisorId());
			updStmt.setString(25, employee.getNavisionCode());
			updStmt.setString(26, employee.getInit());
			updStmt.setInt(27, employee.getDepartmentId());

			bogus = updStmt.execute();
		} catch (SQLException e) {
			throw new RepositoryException(className
					+ "SQLException caught in method update. " + e.getMessage());
		} catch (Exception e) {
			throw new RepositoryException(className
					+ "unknown error caught in method update. " + e.getMessage());
		}
	}

	public Item[] getAllActive() throws RepositoryException{
		try {
			ResultSet results;
			getAllActiveStmt.clearParameters();
			Collection actives = new ArrayList();
			results = getAllActiveStmt.executeQuery();

			while(results.next()){
				actives.add(makeBean(results));
			}
			return (EmployeeBean[])actives.toArray(new EmployeeBean[0]);
		} catch (SQLException e) {
			throw new RepositoryException(className
					+ "SQLException caught in method getAllActive. " + e.getMessage());
		} catch (Exception e) {
			throw new RepositoryException(className
					+ "Unknown error caught in method getAllActive. " + e.getMessage());
		}
	}
	
	public Item[] getAllInactive() throws RepositoryException{
		try {
			ResultSet results;
			getAllInactiveStmt.clearParameters();
			Collection inactives = new ArrayList();
			results = getAllInactiveStmt.executeQuery();

			while(results.next()){
				inactives.add(makeBean(results));
			}
			return (EmployeeBean[])inactives.toArray(new EmployeeBean[0]);
		} catch (SQLException e) {
			throw new RepositoryException(className
					+ "SQLException caught in method getAllInactive. " + e.getMessage());
		} catch (Exception e) {
			throw new RepositoryException(className
					+ "Unknown error caught in method getAllInactive. " + e.getMessage());
		}
	}
	
	public String getSupervisorById(int id) throws RepositoryException{
		try{
			ResultSet results;
			getSupervisorByIdStmt.clearParameters();
			getSupervisorByIdStmt.setInt(1, id);
			results = getSupervisorByIdStmt.executeQuery();
			
			return results.getString("NAME");
		}catch (SQLException e) {
			throw new RepositoryException(className
					+ "SQLException caught in method getSupervisorById. " + e.getMessage());
		} catch (Exception e) {
			throw new RepositoryException(className
					+ "Unknown error caught in method getSupervisorById. " + e.getMessage());
		}
	}
}
