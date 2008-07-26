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
		String all = "SELECT * FROM PROJECT WHERE STATUS = 1 ";

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
			return (ProjectBean[]) projects
					.toArray(new ProjectBean[0]);
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
