package com.ascepionpharm.lims.repository.chemistry;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ascepionpharm.lims.entity.chemistry.*;
import com.ascepionpharm.lims.entity.core.*;
import com.ascepionpharm.lims.universal.*;

/**
 * SynthesisRouteRepository: performs all database actions to the SynthesisRoute
 * table.
 * 
 * @author Li Jun Mulin
 * @version
 * 
 */

public class SynthesisRouteRepository extends LIMSRepository implements
		Destroyable, Closable {

	PreparedStatement insertStmt;

	public SynthesisRouteRepository() throws RepositoryException {
		this.className = "Class: SynthesisRouteRepository. ";

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

	public SynthesisRouteRepository(Connection conn) throws RepositoryException {
		this.className = "Class: SynthesisRouteRepository. ";
		prepareCalls(conn);
	}

	public void prepareCalls(Connection conn) throws RepositoryException {
		String insert = "INSERT INTO SYNTHESISROUTE (LAST_REACTION,NEXT_REACTION) SELECT REACTION_ID,? FROM REACTION WHERE PRODUCT = ?";

		try {
			insertStmt = conn.prepareStatement(insert);
		} catch (SQLException e) {
			throw new RepositoryException(className
					+ "SQLException caught in constructor. " + e.getMessage());
		}
	}

	public void closeCalls() throws RepositoryException {
		try {
			insertStmt.close();
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

	private SynthesisRouteBean makeBean(ResultSet results)
			throws RepositoryException {
		try {
			SynthesisRouteBean sr = new SynthesisRouteBean();
			sr.setLast_reaction(results.getInt("LAST_REACTION"));
			sr.setNext_reaction(results.getInt("NEXT_REACTION"));

			return sr;
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
		return null;
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

	public void insertLN(int nextReactionId, int productId)
			throws RepositoryException {
		try {
			boolean bogus;
			insertStmt.clearParameters();
			insertStmt.setInt(1, nextReactionId);
			insertStmt.setInt(2, productId);
			bogus = insertStmt.execute();
		} catch (SQLException e) {
			throw new RepositoryException(className
					+ "SQLException caught in method insertLN. "
					+ e.getMessage());
		} catch (Exception e) {
			throw new RepositoryException(className
					+ "unknown error caught in method insertLN. "
					+ e.getMessage());
		}
	}
	
	public static void main(String[] args){
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection cn = DriverManager.getConnection("jdbc:oracle:thin:lims/limsprod@localhost:1521:DEV1");
	        new SynthesisRouteRepository(cn).insertLN(111, 236);
			//new ChemicalFileRepository(cn).get(30);
//			ChemicalFileBean relatedFile=(ChemicalFileBean)new ChemicalFileRepository(cn).get(172);
//			System.out.println(new ChemicalFileRepository(cn).getNameById(172));
//			System.getProperty("catalina.home");
		}catch (RepositoryException e) {
			e.printStackTrace();
		}catch (SQLException e) {
			e.printStackTrace();
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
}
