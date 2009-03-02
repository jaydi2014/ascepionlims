package com.ascepionpharm.lims.repository.core;

import java.sql.*;
import java.util.*;

import com.ascepionpharm.lims.entity.core.*;
import com.ascepionpharm.lims.universal.*;

/**
 * DepartmentRepository: performs all database actions to the PROJECT table.
 * 
 * @author Li Jun Mulin
 * @version
 * 
 */

public class ProjectRepository extends LIMSRepository implements Destroyable,
		Closable {
	PreparedStatement getAllInactiveStmt;
	PreparedStatement getAllNStmt;
	PreparedStatement getByDepartmentStmt;
	
	public ProjectRepository() throws RepositoryException {
		this.className = "Class: ProjectRepository. ";

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

	public ProjectRepository(Connection conn) throws RepositoryException {
		this.className = "Class: ProjectRepository. ";
		prepareCalls(conn);
	}

	public void prepareCalls(Connection conn) throws RepositoryException {
		String get = "SELECT * FROM PROJECT WHERE PROJECT_ID = ? ";
		String all = "SELECT * FROM PROJECT WHERE STATUS = 1 ORDER BY START_TIME";
		String allinactive = "SELECT * FROM PROJECT WHERE STATUS = 0 ORDER BY START_TIME";
		String alln = "SELECT * FROM PROJECT ORDER BY START_TIME";
		String getbydep = "SELECT * FROM PROJECT WHERE DEPARTMENT_ID = ? AND STATUS = 1";
		this.sqlCreate = "{call core_pkg.insertproject(?,?,?,?,?,?)}";
		this.sqlUpdate = "{call core_pkg.updateproject(?,?,?,?,?,?,?,?)}";

		try {
			getStmt = conn.prepareStatement(get);
			getAllStmt = conn.prepareStatement(all);
			putStmt = conn.prepareCall(sqlCreate);
			updStmt = conn.prepareCall(sqlUpdate);
			getAllInactiveStmt = conn.prepareStatement(allinactive);
			getAllNStmt = conn.prepareStatement(alln);
			getByDepartmentStmt =  conn.prepareStatement(getbydep);
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
			getAllInactiveStmt.close();
			getAllNStmt.close();
			getByDepartmentStmt.close();
		} catch (SQLException e) {
			throw new RepositoryException(className
					+ "Calls could not be closed. " + e.getMessage());
		}
	}

	private ProjectBean makeBean(ResultSet results) throws RepositoryException {
		try {
			ProjectBean project = new ProjectBean();
			project.setId(results.getInt("PROJECT_ID"));
			project.setDepartmentId(results.getInt("DEPARTMENT_ID"));
			project.setName(results.getString("NAME"));
			project.setDescription(results.getString("DESCRIPTION"));
			project.setMembers(results.getString("MEMBERS"));
			project.setStart_time(results.getDate("START_TIME"));
			project.setEnd_time(results.getDate("END_TIME"));
			project.setStatus(results.getInt("STATUS"));

			return project;
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
						+ "Could not find PROJECT_ID " + id);
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
			Collection projects = new ArrayList();
			results = getAllStmt.executeQuery();

			while (results.next()) {
				projects.add(makeBean(results));
			}
			return (ProjectBean[]) projects.toArray(new ProjectBean[0]);
		} catch (SQLException e) {
			throw new RepositoryException(className
					+ "SQLException caught in method getAll. " + e.getMessage());
		} catch (Exception e) {
			throw new RepositoryException(className
					+ "Unknown error caught in method getAll. "
					+ e.getMessage());
		}
	}
	
	public List getByDepartment(int departmentId) throws RepositoryException{
		try {
			ResultSet results;
			getByDepartmentStmt.clearParameters();
			getByDepartmentStmt.setInt(1, departmentId);
			results = getByDepartmentStmt.executeQuery();
			List projects = new ArrayList();

			while(results.next()){
				projects.add(makeBean(results));
			}
			
			return projects;
		} catch (SQLException e) {
			throw new RepositoryException(className
					+ "SQLException caught in method getByDepartment. " + e.getMessage());
		} catch (Exception e) {
			throw new RepositoryException(className
					+ "Unknown error caught in method getByDepartment. " + e.getMessage());
		}
	}
	
	public Item[] getAllN() throws RepositoryException {
		try {
			ResultSet results;
			getAllNStmt.clearParameters();
			Collection projects = new ArrayList();
			results = getAllNStmt.executeQuery();

			while (results.next()) {
				projects.add(makeBean(results));
			}
			return (ProjectBean[]) projects.toArray(new ProjectBean[0]);
		} catch (SQLException e) {
			throw new RepositoryException(className
					+ "SQLException caught in method getAllN. " + e.getMessage());
		} catch (Exception e) {
			throw new RepositoryException(className
					+ "Unknown error caught in method getAllN. "
					+ e.getMessage());
		}
	}
	
	public Item[] getAllinactive() throws RepositoryException{
		try {
			ResultSet results;
			getAllInactiveStmt.clearParameters();
			Collection projects = new ArrayList();
			results = getAllInactiveStmt.executeQuery();

			while (results.next()) {
				projects.add(makeBean(results));
			}
			return (ProjectBean[]) projects.toArray(new ProjectBean[0]);
		} catch (SQLException e) {
			throw new RepositoryException(className
					+ "SQLException caught in method getAllinactive. " + e.getMessage());
		} catch (Exception e) {
			throw new RepositoryException(className
					+ "Unknown error caught in method getAllinactive. "
					+ e.getMessage());
		}
	}

	public Item[] getList(int id) throws RepositoryException {
		return null;
	}

	public void put(Item myProjectBean) throws RepositoryException {
		ProjectBean project = (ProjectBean) myProjectBean;

		try {
			boolean bogus;

			putStmt.clearParameters();
			putStmt.setInt(1, project.getDepartmentId());
			putStmt.setString(2, project.getName());
			putStmt.setString(3, project.getDescription());
			putStmt.setString(4, project.getMembers());
			putStmt.setDate(5, project.getStart_time());
			putStmt.setDate(6, project.getEnd_time());

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

	public void update(Item myProjectBean) throws RepositoryException {
		ProjectBean project = (ProjectBean) myProjectBean;
		try {
			boolean bogus;

			updStmt.clearParameters();
			updStmt.setInt(1, project.getId());
			updStmt.setInt(2, project.getDepartmentId());
			updStmt.setString(3, project.getName());
			updStmt.setString(4, project.getDescription());
			updStmt.setString(5, project.getMembers());
			updStmt.setDate(6, project.getStart_time());
			updStmt.setDate(7, project.getEnd_time());
			updStmt.setInt(8, project.getStatus());

			bogus = updStmt.execute();
		} catch (SQLException e) {
			throw new RepositoryException(className
					+ "SQLException caught in method update. " + e.getMessage());
		} catch (Exception e) {
			throw new RepositoryException(className
					+ "unknown error caught in method update. " + e.getMessage());
		}
	}
}
