package com.ascepionpharm.lims.universal;

import java.sql.*;
//import java.math.*;

/**
 * Abstract class that provides database API for each entity.
 *
 * @author Li Jun Mulin
 * @version 
 *
 */
public abstract class LIMSRepository implements Destroyable, Closable {
   protected DBConnection myDbBean = DBConnection.getInstance();
   protected Connection connection;
   
   protected String className = "";
   protected String seqSql = "select general_seq.NEXTVAL as next from dual";
   protected String	sqlCreate;
   protected String	sqlUpdate;
   protected String	sqlRemove;
   
   protected PreparedStatement getNextSeqId;
   protected PreparedStatement getStmt;
   protected PreparedStatement getAllStmt;
   protected CallableStatement putStmt;
   protected CallableStatement updStmt;
   protected CallableStatement remStmt;              	
  	
  	public abstract Item get(int id)      	throws RepositoryException;
  	public abstract Item[] getList(int id)  throws RepositoryException;
  	public abstract Item[] getAll()			throws RepositoryException;
	public abstract void put(Item item)  	throws RepositoryException;
	public abstract void update(Item item)  throws RepositoryException;
	public abstract void remove(Item item) 	throws RepositoryException;
	public abstract void destroy()          throws RepositoryException;

	public int getSeqId() throws RepositoryException {
		int returnValue =0;
		try {
			ResultSet results;
			getNextSeqId.clearParameters();
			results = getNextSeqId.executeQuery();
			if (results.next()) {
				returnValue = results.getInt("next");
			}
			return returnValue;
		} catch ( SQLException se ) {
			throw new RepositoryException("Class : Repository. " +
			"SQLexception caught getting next seqID. " + se.getMessage() );
		} catch (Exception e) {
			throw new RepositoryException("Class : Repository. " +
			"unknown error caught getting next seqID. " + e.getMessage());
		}
	}	
}