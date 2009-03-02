package com.ascepionpharm.lims.repository.chemistry;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

import com.ascepionpharm.lims.entity.chemistry.*;
import com.ascepionpharm.lims.entity.core.*;
import com.ascepionpharm.lims.universal.*;

/**
 * ProjectReactionRepository: performs all database actions to the ProjectReaction
 * table.
 * 
 * @author Li Jun Mulin
 * @version
 * 
 */
public class ProjectReactionRepository extends LIMSRepository implements
Destroyable, Closable{
	
	PreparedStatement insertStmt;
	PreparedStatement deleteStmt;
	PreparedStatement getStmt;
	PreparedStatement getReactionByProjectStmt;
	PreparedStatement getProjectByReactionStmt;

	public ProjectReactionRepository() throws RepositoryException {
		this.className = "Class: ProjectReactionRepository. ";

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

	public ProjectReactionRepository(Connection conn) throws RepositoryException {
		this.className = "Class: ProjectReactionRepository. ";
		prepareCalls(conn);
	}

	public void prepareCalls(Connection conn) throws RepositoryException {
		String insert = "INSERT INTO PROJECT_REACTION VALUES (?,?)";
		String delete = "DELETE * FROM PROJECT_REACTION WHERE PROJECT_ID = ?,REACTION_ID = ?";
		String get = "SELECT * FROM PROJECT_REACTION WHERE PROJECT_ID = ? AND REACTION_ID = ?";
		String getbyproject = "SELECT * FROM PROJECT_REACTION WHERE PROJECT_ID = ?";
		String getbyReaction = "SELECT P.NAME FROM PROJECT P,PROJECT_REACTION PR WHERE P.PROJECT_ID = PR.PROJECT_ID AND PR.REACTION_ID = ?";
		try {
			insertStmt = conn.prepareStatement(insert);
			deleteStmt = conn.prepareStatement(delete);
			getStmt = conn.prepareStatement(get);
			getReactionByProjectStmt = conn.prepareStatement(getbyproject);
			getProjectByReactionStmt = conn.prepareStatement(getbyReaction);
		} catch (SQLException e) {
			throw new RepositoryException(className
					+ "SQLException caught in constructor. " + e.getMessage());
		}
	}
	
	public void closeCalls() throws RepositoryException {
		try {
			insertStmt.close();
			deleteStmt.close();
			getStmt.close();
			getReactionByProjectStmt.close();
			getProjectByReactionStmt.close();
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
	
	private ProjectReactionBean makeBean(ResultSet results) throws RepositoryException {
		try {
			ProjectReactionBean pm = new ProjectReactionBean();
			pm.setProject_id(results.getInt("PROJECT_ID"));
			pm.setReaction_id(results.getInt("REACTION_ID"));
			
			return pm;
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
	

	public Item[] getAll() throws RepositoryException {
		return null;
	}

	public Item[] getList(int id) throws RepositoryException {
		try {
			ResultSet results;		
			getReactionByProjectStmt.clearParameters();
			Collection rs = new ArrayList();
			getReactionByProjectStmt.setInt(1, id);

			results = getReactionByProjectStmt.executeQuery();
			while(results.next()){
				rs.add(makeBean(results));
			}
			return (ProjectReactionBean[])rs.toArray(new ProjectReactionBean[0]);
		} catch (SQLException e) {
			throw new RepositoryException(className
					+ "SQLException caught in method get. " + e.getMessage());
		} catch (Exception e) {
			throw new RepositoryException(className
					+ "unknown error caught in method get. " + e.getMessage());
		}
	}

	public void put(Item myProjectReactionBean) throws RepositoryException {
		ProjectReactionBean pm = (ProjectReactionBean) myProjectReactionBean;
		
		try {
			boolean bogus;		
			insertStmt.clearParameters();
			insertStmt.setInt(1, pm.getProject_id());
			insertStmt.setInt(2, pm.getReaction_id());

			bogus = insertStmt.execute();
		} catch (SQLException e) {
			throw new RepositoryException(className
					+ "SQLException caught in method put. " + e.getMessage());
		} catch (Exception e) {
			throw new RepositoryException(className
					+ "unknown error caught in method put. " + e.getMessage());
		}
	}

	public void remove(Item myProjectReactionBean) throws RepositoryException {
		ProjectReactionBean pm = (ProjectReactionBean) myProjectReactionBean;
		
		try {
			boolean bogus;		
			deleteStmt.clearParameters();
			deleteStmt.setInt(1, pm.getProject_id());
			deleteStmt.setInt(2, pm.getReaction_id());

			bogus = deleteStmt.execute();
		} catch (SQLException e) {
			throw new RepositoryException(className
					+ "SQLException caught in method remove. " + e.getMessage());
		} catch (Exception e) {
			throw new RepositoryException(className
					+ "unknown error caught in method remove. " + e.getMessage());
		}
	}

	public void update(Item item) throws RepositoryException {
		
	}

	public Item get(int id) throws RepositoryException {
		return null;
	}
	
	public Item get(int proId, int reaId) throws RepositoryException {
		try {
			ProjectReactionBean pm = null;
			ResultSet results;		
			getStmt.clearParameters();
			getStmt.setInt(1, proId);
			getStmt.setInt(2, reaId);

			results = getStmt.executeQuery();
			while(results.next()){
				pm = makeBean(results);
			}
			return pm;
		} catch (SQLException e) {
			throw new RepositoryException(className
					+ "SQLException caught in method get. " + e.getMessage());
		} catch (Exception e) {
			throw new RepositoryException(className
					+ "unknown error caught in method get. " + e.getMessage());
		}
	}
	
	public String getProjectName(int reaId) throws RepositoryException {
		String projectName = null;
		try {
			ResultSet results;		
			getProjectByReactionStmt.clearParameters();
			getProjectByReactionStmt.setInt(1, reaId);

			results = getProjectByReactionStmt.executeQuery();
			results.next();
			projectName = results.getString(1);
			return projectName;
			
		} catch (SQLException e) {
			throw new RepositoryException(className
					+ "SQLException caught in method get. " + e.getMessage());
		} catch (Exception e) {
			throw new RepositoryException(className
					+ "unknown error caught in method get. " + e.getMessage());
		}
	}
}


