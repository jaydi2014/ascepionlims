package com.ascepionpharm.lims.repository.chemistry;

import java.sql.*;
import java.util.*;
import java.io.*;

import com.ascepionpharm.lims.entity.chemistry.*;
import com.ascepionpharm.lims.entity.core.*;
import com.ascepionpharm.lims.universal.*;

/**
 * ReactionRepository: performs all database actions to the Reaction table.
 * 
 * @author Li Jun Mulin
 * @version
 * 
 */
public class ReactionRepository extends LIMSRepository implements Destroyable,
		Closable {

	PreparedStatement insertReactionStmt;
	PreparedStatement updateReactionStmt;
	PreparedStatement getByInnerNameStmt;
	PreparedStatement deleteStmt;
	PreparedStatement checkStmt;
	PreparedStatement getNameBySP;

	public ReactionRepository() throws RepositoryException {
		this.className = "Class: ReactionRepository. ";

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

	public ReactionRepository(Connection conn) throws RepositoryException {
		this.className = "Class: ReactionRepository. ";
		connection = conn;
		prepareCalls(conn);
	}

	public void prepareCalls(Connection conn) throws RepositoryException {
		String get = "SELECT * FROM REACTION WHERE REACTION_ID = ?";
		String insert = "INSERT INTO REACTION VALUES (REACTION_SEQ.NEXTVAL,?,?,?,?,?,?,?,?)";
		String update = "UPDATE REACTION SET NAME = ?,PRODUCT = ?,REACTION_A = ?,REACTION_B = ?,REACTION_C = ?,CATALYST_A = ?,CATALYST_B = ?,CONDITIONS = ? WHERE REACTION_ID = ?";
		String getbyinnername = "SELECT * FROM REACTION WHERE NAME LIKE ?";
		String delete = "DELETE FROM REACTION WHERE REACTION_ID = ?";
		String check = "SELECT * FROM REACTION WHERE REACTION_A = ? AND PRODUCT = ?";
		String getnamebysp = "select A.* from reaction A,chemicalmolecular B,project_reaction C where A.product=B.chemicalmolecular_id and B.smiles=? and A.reaction_id=C.reaction_id and C.project_id=?";
		
		try {
			getStmt = conn.prepareStatement(get);
			insertReactionStmt = conn.prepareStatement(insert);
			updateReactionStmt = conn.prepareStatement(update);
			getByInnerNameStmt = conn.prepareStatement(getbyinnername);
			deleteStmt =  conn.prepareStatement(delete);
			checkStmt = conn.prepareStatement(check);
			getNameBySP = conn.prepareStatement(getnamebysp);

		} catch (SQLException e) {
			throw new RepositoryException(className
					+ "SQLException caught in constructor. " + e.getMessage());
		}
	}

	public void closeCalls() throws RepositoryException {
		try {
			getStmt.close();
			insertReactionStmt.close();
			updateReactionStmt.close();
			getByInnerNameStmt.close();
			deleteStmt.close();
			checkStmt.close();
			getNameBySP.close();
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

	private ReactionBean makeBean(ResultSet results) throws RepositoryException {
		try {
			ReactionBean reaction = new ReactionBean();
			reaction.setReaction_id(results.getInt("REACTION_ID"));
			reaction.setName(results.getString("NAME"));
			reaction.setProduct(results.getInt("PRODUCT"));
			reaction.setReaction_a(results.getInt("REACTION_A"));
			reaction.setReaction_b(results.getInt("REACTION_B"));
			reaction.setReaction_c(results.getInt("REACTION_C"));
			reaction.setCatalyst_a(results.getInt("CATALYST_A"));
			reaction.setCatalyst_b(results.getString("CATALYST_B"));
			reaction.setConditions(results.getString("CONDITIONS"));

			return reaction;
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
						+ "Could not find REACTION_ID " + id);
		} catch (SQLException e) {
			throw new RepositoryException(className
					+ "SQLException caught in method get. " + e.getMessage());
		} catch (Exception e) {
			throw new RepositoryException(className
					+ "Unknown error caught in method get. " + e.getMessage());
		}
	}
	
	public Item[] getByInnerName(String innerName) throws RepositoryException {
		try {
			ResultSet results;
			getByInnerNameStmt.clearParameters();
			getByInnerNameStmt.setString(1, innerName);
			Collection reactions = new ArrayList();
			results = getByInnerNameStmt.executeQuery();

			while(results.next()){
				reactions.add(makeBean(results));
			}
			return (ReactionBean[])reactions.toArray(new ReactionBean[0]);
		} catch (SQLException e) {
			throw new RepositoryException(className
					+ "SQLException caught in method getByInnerName. " + e.getMessage());
		} catch (Exception e) {
			throw new RepositoryException(className
					+ "Unknown error caught in method getByInnerName. " + e.getMessage());
		}
	}

	public Item[] getAll() throws RepositoryException {
		return null;
	}

	public Item[] getList(int id) throws RepositoryException {
		return null;
	}

	public void put(Item myReaction) throws RepositoryException {
		ReactionBean reaction = (ReactionBean) myReaction;

		try {
			boolean bogus;
			insertReactionStmt.clearParameters();
			insertReactionStmt.setString(1, reaction.getName());
			insertReactionStmt.setInt(2, reaction.getProduct());
			insertReactionStmt.setInt(3, reaction.getReaction_a());
			insertReactionStmt.setInt(4, reaction.getReaction_b());
			insertReactionStmt.setInt(5, reaction.getReaction_c());
			insertReactionStmt.setInt(6, reaction.getCatalyst_a());
			insertReactionStmt.setString(7, reaction.getCatalyst_b());
			insertReactionStmt.setString(8, reaction.getConditions());

			bogus = insertReactionStmt.execute();
			
		} catch (SQLException e) {
			throw new RepositoryException(className
					+ "SQLException caught in method put. " + e.getMessage());
		} catch (Exception e) {
			throw new RepositoryException(className
					+ "unknown error caught in method put. " + e.getMessage());
		}
	}
	
	public int getLastId() throws RepositoryException {
		try {
			Statement stmt=connection.createStatement();
			
			String sql="SELECT REACTION_ID FROM REACTION WHERE ROWNUM = 1 ORDER BY REACTION_ID DESC";
			ResultSet rs = stmt.executeQuery(sql);
			rs.next();
			int id = rs.getInt(1);
			
			return id;
			
		} catch (SQLException e) {
			throw new RepositoryException(className
					+ "SQLException caught in method getLastId. " + e.getMessage());
		} catch (Exception e) {
			throw new RepositoryException(className
					+ "unknown error caught in method getLastId. " + e.getMessage());
		}
	}
	
	public Item[] checkUnique(Item myReaction) throws RepositoryException {
		ReactionBean reaction = (ReactionBean) myReaction;
		try {
			ResultSet results;
			Collection reactions = new ArrayList();
			checkStmt.clearParameters();
			checkStmt.setInt(1, reaction.getReaction_a());
			checkStmt.setInt(2, reaction.getProduct());
			results = checkStmt.executeQuery();

			while(results.next()){
				reactions.add(makeBean(results));
			}
			return (ReactionBean[])reactions.toArray(new ReactionBean[0]);
		} catch (SQLException e) {
			throw new RepositoryException(className
					+ "SQLException caught in method checkUnique. " + e.getMessage());
		} catch (Exception e) {
			throw new RepositoryException(className
					+ "Unknown error caught in method checkUnique. " + e.getMessage());
		}
	}

	public void remove(Item item) throws RepositoryException {
		
	}

	public void update(Item item) throws RepositoryException {

	}
	
	public void delete(int id) throws RepositoryException {
		try {
			boolean bogus;
			deleteStmt.clearParameters();
			deleteStmt.setInt(1, id);
			bogus = deleteStmt.execute();
		} catch (SQLException e) {
			throw new RepositoryException(className
					+ "SQLException caught in method delete. " + e.getMessage());
		} catch (Exception e) {
			throw new RepositoryException(className
					+ "Unknown error caught in method delete. " + e.getMessage());
		}
	}

	
	public List getNameBySmilesAndProject(String smiles, int projectId) throws RepositoryException {
		try {
			ResultSet results;
			getNameBySP.clearParameters();
			getNameBySP.setString(1, smiles);
			getNameBySP.setInt(2, projectId);
			List reactions = new ArrayList();
			results = getNameBySP.executeQuery();

			while(results.next()){
				reactions.add(makeBean(results));
			}
			return reactions;
		} catch (SQLException e) {
			throw new RepositoryException(className
					+ "SQLException caught in method getNameBySmilesAndProject. " + e.getMessage());
		} catch (Exception e) {
			throw new RepositoryException(className
					+ "Unknown error caught in method getNameBySmilesAndProject. " + e.getMessage());
		}
	}
	
	public static void main(String[] args){
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection cn = DriverManager.getConnection("jdbc:oracle:thin:lims/limsprod@localhost:1521:DEV1");
			System.out.println("mulin");
			List list = new ArrayList();
			Map mapa = new HashMap();   
			Map mapb = new HashMap();  
	        mapa.put("1","a"); 
	        mapa.put("2","b");
	        mapb.put("1","mulin");
	        mapb.put("2","lijun");
	        list.add(mapa);
	        list.add(mapb);
	        System.out.println(new ReactionRepository(cn).getNameBySmilesAndProject("C1=CC2=C(C=C1)C=CC=C2",43));
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
