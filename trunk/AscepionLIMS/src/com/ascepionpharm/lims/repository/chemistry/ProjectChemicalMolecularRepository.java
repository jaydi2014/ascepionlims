package com.ascepionpharm.lims.repository.chemistry;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

import com.ascepionpharm.lims.entity.chemistry.*;
import com.ascepionpharm.lims.entity.core.PermissionBean;
import com.ascepionpharm.lims.universal.*;

/**
 * ProjectChemicalMolecularRepository: performs all database actions to the ProjectChemicalMolecular
 * table.
 * 
 * @author Li Jun Mulin
 * @version
 * 
 */
public class ProjectChemicalMolecularRepository extends LIMSRepository implements
Destroyable, Closable{
	
	PreparedStatement insertStmt;
	PreparedStatement deleteStmt;
	PreparedStatement getStmt;
	PreparedStatement getMolByProjectStmt;

	public ProjectChemicalMolecularRepository() throws RepositoryException {
		this.className = "Class: ProjectChemicalMolecularRepository. ";

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

	public ProjectChemicalMolecularRepository(Connection conn) throws RepositoryException {
		this.className = "Class: ProjectChemicalMolecularRepository. ";
		prepareCalls(conn);
	}

	public void prepareCalls(Connection conn) throws RepositoryException {
		String insert = "INSERT INTO PROJECT_CHEMICALMOLECULAR VALUES (?,?)";
		String delete = "DELETE * FROM PROJECT_CHEMICALMOLECULAR WHERE PROJECT_ID = ?,CHEMICALMOLECULAR_ID = ?";
		String get = "SELECT * FROM PROJECT_CHEMICALMOLECULAR WHERE PROJECT_ID = ? AND CHEMICALMOLECULAR_ID = ?";
		String getbyproject = "SELECT CH.* FROM CHEMICALMOLECULAR CH,PROJECT_CHEMICALMOLECULAR PC WHERE CH.CHEMICALMOLECULAR_ID = PC.CHEMICALMOLECULAR_ID AND PC.PROJECT_ID = ?";
		try {
			insertStmt = conn.prepareStatement(insert);
			deleteStmt = conn.prepareStatement(delete);
			getStmt = conn.prepareStatement(get);
			getMolByProjectStmt = conn.prepareStatement(getbyproject);
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
			getMolByProjectStmt.close();
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
	
	private ProjectChemicalMolecularBean makeBean(ResultSet results) throws RepositoryException {
		try {
			ProjectChemicalMolecularBean pm = new ProjectChemicalMolecularBean();
			pm.setProject_id(results.getInt("PROJECT_ID"));
			pm.setChemicalMolecular_id(results.getInt("CHEMICALMOLECULAR_ID"));
			
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
			getMolByProjectStmt.clearParameters();
			Collection mols = new ArrayList();
			getMolByProjectStmt.setInt(1, id);

			results = getMolByProjectStmt.executeQuery();
			while(results.next()){
				ChemicalMolecularBean chemicalMolecular = new ChemicalMolecularBean();
				chemicalMolecular.setId(results.getInt("CHEMICALMOLECULAR_ID"));
				chemicalMolecular.setInnerName(results.getString("INNER_NAME"));
				chemicalMolecular.setChemicalName(results.getString("CHEMICAL_NAME"));
				chemicalMolecular.setSmiles(results.getString("SMILES"));
				chemicalMolecular.setRelatedFiles(results.getString("RELATED_FILES"));
				chemicalMolecular.setMolecularFormula(results.getString("MOLECULAR_FORMULA"));
				mols.add(chemicalMolecular);
			}
			return (ChemicalMolecularBean[])mols.toArray(new ChemicalMolecularBean[0]);
		} catch (SQLException e) {
			throw new RepositoryException(className
					+ "SQLException caught in method getList. " + e.getMessage());
		} catch (Exception e) {
			throw new RepositoryException(className
					+ "unknown error caught in method getList. " + e.getMessage());
		}
	}

	public void put(Item myProjectChemicalMolecularBean) throws RepositoryException {
		ProjectChemicalMolecularBean pm = (ProjectChemicalMolecularBean) myProjectChemicalMolecularBean;
		
		try {
			boolean bogus;		
			insertStmt.clearParameters();
			insertStmt.setInt(1, pm.getProject_id());
			insertStmt.setInt(2, pm.getChemicalMolecular_id());

			bogus = insertStmt.execute();
		} catch (SQLException e) {
			throw new RepositoryException(className
					+ "SQLException caught in method put. " + e.getMessage());
		} catch (Exception e) {
			throw new RepositoryException(className
					+ "unknown error caught in method put. " + e.getMessage());
		}
	}

	public void remove(Item myProjectChemicalMolecularBean) throws RepositoryException {
		ProjectChemicalMolecularBean pm = (ProjectChemicalMolecularBean) myProjectChemicalMolecularBean;
		
		try {
			boolean bogus;		
			deleteStmt.clearParameters();
			deleteStmt.setInt(1, pm.getProject_id());
			deleteStmt.setInt(2, pm.getChemicalMolecular_id());

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
	
	public Item get(int proId, int molId) throws RepositoryException {
		try {
			ProjectChemicalMolecularBean pm = null;
			ResultSet results;		
			getStmt.clearParameters();
			getStmt.setInt(1, proId);
			getStmt.setInt(2, molId);

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
}


