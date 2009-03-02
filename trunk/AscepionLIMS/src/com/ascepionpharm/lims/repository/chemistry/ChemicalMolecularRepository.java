package com.ascepionpharm.lims.repository.chemistry;

import java.sql.*;
import java.util.*;
import java.io.*;

import com.ascepionpharm.lims.entity.chemistry.*;
import com.ascepionpharm.lims.entity.core.PurchasingBean;
import com.ascepionpharm.lims.universal.*;

/**
 * ChemicalMolecularRepository: performs all database actions to the CHEMICALMOLECULAR
 * table.
 * 
 * @author Li Jun Mulin
 * @version
 * 
 */
public class ChemicalMolecularRepository extends LIMSRepository implements
Destroyable, Closable{

	PreparedStatement insertSampleStmt;
	PreparedStatement upgrateSampleStmt;
	PreparedStatement getByInnerNameStmt;
	PreparedStatement getByChemicalNameStmt;
	PreparedStatement getBySmilesStmt;
	PreparedStatement getByMolecularFormulaStmt;
	PreparedStatement getAllInnerNameStmt;
	PreparedStatement getMolByProjectStmt;

	public ChemicalMolecularRepository() throws RepositoryException {
		this.className = "Class: ChemicalMolecularRepository. ";

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

	public ChemicalMolecularRepository(Connection conn) throws RepositoryException {
		this.className = "Class: ChemicalMolecularRepository. ";
		prepareCalls(conn);
	}

	public void prepareCalls(Connection conn) throws RepositoryException {
		String get = "SELECT * FROM CHEMICALMOLECULAR WHERE CHEMICALMOLECULAR_ID = ?";
		String insert = "INSERT INTO CHEMICALMOLECULAR VALUES (CHEMICALMOLECULAR_SEQ.NEXTVAL,?,?,?,?,?)";
		String update = "UPDATE CHEMICALMOLECULAR SET INNER_NAME = ?,CHEMICAL_NAME = ?,SMILES = ?,RELATED_FILES = ?,MOLECULAR_FORMULA = ? WHERE CHEMICALMOLECULAR_ID = ?";
		String getbyinnername = "SELECT * FROM CHEMICALMOLECULAR WHERE INNER_NAME = ?";
		String getbychemicalname = "SELECT * FROM CHEMICALMOLECULAR WHERE CHEMICAL_NAME = ?";
		String getbysmiles = "SELECT * FROM CHEMICALMOLECULAR WHERE SMILES = ?";
		String getbyformula = "SELECT * FROM CHEMICALMOLECULAR WHERE MOLECULAR_FORMULA = ?";
		String getallinnername = "SELECT INNER_NAME FROM CHEMICALMOLECULAR";
		String getbyproject = "SELECT C.* FROM PROJECT_CHEMICALMOLECULAR PM,CHEMICALMOLECULAR C WHERE PM.CHEMICALMOLECULAR_ID = C.CHEMICALMOLECULAR_ID AND PM.PROJECT_ID = ?";
		try {
			getStmt = conn.prepareStatement(get);
			insertSampleStmt = conn.prepareStatement(insert);
			upgrateSampleStmt = conn.prepareStatement(update);
			getByInnerNameStmt = conn.prepareStatement(getbyinnername);
			getByChemicalNameStmt=conn.prepareStatement(getbychemicalname);
			getBySmilesStmt = conn.prepareStatement(getbysmiles);
			getByMolecularFormulaStmt = conn.prepareStatement(getbyformula);
			getAllInnerNameStmt = conn.prepareStatement(getallinnername);
			getMolByProjectStmt = conn.prepareStatement(getbyproject);
		} catch (SQLException e) {
			throw new RepositoryException(className
					+ "SQLException caught in constructor. " + e.getMessage());
		}
	}
	
	public void closeCalls() throws RepositoryException {
		try {
			getStmt.close();
			insertSampleStmt.close();
			upgrateSampleStmt.close();
			getByInnerNameStmt.close();
			getByChemicalNameStmt.close();
			getBySmilesStmt.close();
			getByMolecularFormulaStmt.close();
			getAllInnerNameStmt.close();
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
	
	private ChemicalMolecularBean makeBean(ResultSet results) throws RepositoryException {
		try {
			ChemicalMolecularBean chemicalMolecular = new ChemicalMolecularBean();
			chemicalMolecular.setId(results.getInt("CHEMICALMOLECULAR_ID"));
			chemicalMolecular.setInnerName(results.getString("INNER_NAME"));
			chemicalMolecular.setChemicalName(results.getString("CHEMICAL_NAME"));
			chemicalMolecular.setSmiles(results.getString("SMILES"));
			chemicalMolecular.setRelatedFiles(results.getString("RELATED_FILES"));
			chemicalMolecular.setMolecularFormula(results.getString("MOLECULAR_FORMULA"));
			
			
			return chemicalMolecular;
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
						+ "Could not find CHEMICALMOLECULAR_ID " + id);
		} catch (SQLException e) {
			throw new RepositoryException(className
					+ "SQLException caught in method get. " + e.getMessage());
		} catch (Exception e) {
			throw new RepositoryException(className
					+ "Unknown error caught in method get. " + e.getMessage());
		}
	}

	public List getAllInnerName() throws RepositoryException {
		try {
			ResultSet results;
			getAllInnerNameStmt.clearParameters();
			List allInnerName = new ArrayList();
			results = getAllInnerNameStmt.executeQuery();

			while (results.next()) {
				allInnerName.add(results.getString("INNER_NAME"));
			}
			return allInnerName;
		} catch (SQLException e) {
			throw new RepositoryException(className
					+ "SQLException caught in method getAllInnerName. " + e.getMessage());
		} catch (Exception e) {
			throw new RepositoryException(className
					+ "Unknown error caught in method getAllInnerName. " + e.getMessage());
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
				mols.add(makeBean(results));
			}
			return (ChemicalMolecularBean[])mols.toArray(new ChemicalMolecularBean[0]);
		} catch (SQLException e) {
			throw new RepositoryException(className
					+ "SQLException caught in method get. " + e.getMessage());
		} catch (Exception e) {
			throw new RepositoryException(className
					+ "unknown error caught in method get. " + e.getMessage());
		}
	}

	public void put(Item myChemicalMolecular) throws RepositoryException {
		ChemicalMolecularBean chemicalMolecular = (ChemicalMolecularBean) myChemicalMolecular;
		
		try {
			boolean bogus;		
			insertSampleStmt.clearParameters();
			insertSampleStmt.setString(1, chemicalMolecular.getInnerName());
			insertSampleStmt.setString(2, chemicalMolecular.getChemicalName());
			insertSampleStmt.setString(3, chemicalMolecular.getSmiles());
			insertSampleStmt.setString(4, chemicalMolecular.getRelatedFiles());
			insertSampleStmt.setString(5, chemicalMolecular.getMolecularFormula());

			bogus = insertSampleStmt.execute();
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

	public void update(Item myChemicalMolecular) throws RepositoryException {
		ChemicalMolecularBean chemicalMolecular = (ChemicalMolecularBean) myChemicalMolecular;
		try {
			boolean bogus;
			upgrateSampleStmt.clearParameters();
			upgrateSampleStmt.setString(1, chemicalMolecular.getInnerName());
			upgrateSampleStmt.setString(2, chemicalMolecular.getChemicalName());
			upgrateSampleStmt.setString(3, chemicalMolecular.getSmiles());
			upgrateSampleStmt.setString(4, chemicalMolecular.getRelatedFiles());
			upgrateSampleStmt.setString(5, chemicalMolecular.getMolecularFormula());
			upgrateSampleStmt.setInt(6, chemicalMolecular.getId());

			bogus = upgrateSampleStmt.execute();
		} catch (SQLException e) {
			throw new RepositoryException(className
					+ "SQLException caught in method update. " + e.getMessage());
		} catch (Exception e) {
			throw new RepositoryException(className
					+ "unknown error caught in method update. " + e.getMessage());
		}
	}
	
	public Item[] getByInnerName(String innerName) throws RepositoryException {
		try {
			ResultSet results;
			getByInnerNameStmt.clearParameters();
		
			Collection molecular = new ArrayList();
			getByInnerNameStmt.setString(1,innerName);
		
			results = getByInnerNameStmt.executeQuery();
			while (results.next()) {
				molecular.add(makeBean(results));
			}
			return (ChemicalMolecularBean[]) molecular.toArray(new ChemicalMolecularBean[0]);
		} catch (SQLException e) {
			throw new RepositoryException(className
					+ "SQLException caught in method getByInnerName. " + e.getMessage());
		} catch (Exception e) {
			throw new RepositoryException(className
					+ "Unknown error caught in method getByInnerName. " + e.getMessage());
		}
	}
	
	
	public Item[] getByChemicalName(String chemicalName) throws RepositoryException {
		try {
			ResultSet results;
			getByChemicalNameStmt.clearParameters();
			Collection molecular = new ArrayList();
			getByChemicalNameStmt.setString(1, chemicalName);
			results = getByChemicalNameStmt.executeQuery();

			while (results.next()) {
				molecular.add(makeBean(results));
			}
			return (ChemicalMolecularBean[]) molecular.toArray(new ChemicalMolecularBean[0]);
		} catch (SQLException e) {
			throw new RepositoryException(className
					+ "SQLException caught in method getByChemicalName. " + e.getMessage());
		} catch (Exception e) {
			throw new RepositoryException(className
					+ "Unknown error caught in method getByChemicalName. " + e.getMessage());
		}
	}
	
	public Item[] getBySmiles(String smiles) throws RepositoryException {
		try {
			ResultSet results;
			getBySmilesStmt.clearParameters();
			Collection molecular = new ArrayList();
			getBySmilesStmt.setString(1, smiles);
			results = getBySmilesStmt.executeQuery();

			while (results.next()) {
				molecular.add(makeBean(results));
			}
			return (ChemicalMolecularBean[]) molecular.toArray(new ChemicalMolecularBean[0]);
		} catch (SQLException e) {
			throw new RepositoryException(className
					+ "SQLException caught in method getBySmiles. " + e.getMessage());
		} catch (Exception e) {
			throw new RepositoryException(className
					+ "Unknown error caught in method getBySmiles. " + e.getMessage());
		}
	}
	
	public Item[] getByFormula(String formula) throws RepositoryException {
		try {
			ResultSet results;
			getByMolecularFormulaStmt.clearParameters();
			Collection molecular = new ArrayList();
			getByMolecularFormulaStmt.setString(1, formula);
			results = getByMolecularFormulaStmt.executeQuery();
			while (results.next()) {
				molecular.add(makeBean(results));
			}
			return (ChemicalMolecularBean[]) molecular.toArray(new ChemicalMolecularBean[0]);
		} catch (SQLException e) {
			throw new RepositoryException(className
					+ "SQLException caught in method getByFormula. " + e.getMessage());
		} catch (Exception e) {
			throw new RepositoryException(className
					+ "Unknown error caught in method getByFormula. " + e.getMessage());
		}
	}
	
	public String checkInnerName(String innerName) throws RepositoryException{
		try {
			String name = null;
			ResultSet results;
			getByInnerNameStmt.clearParameters();
		
			Collection molecular = new ArrayList();
			getByInnerNameStmt.setString(1,innerName);
		
			results = getByInnerNameStmt.executeQuery();
			while (results.next()) {
				name = results.getString("SMILES");
			}
			if(name == null) return "no find";
			else return name;
		} catch (SQLException e) {
			throw new RepositoryException(className
					+ "SQLException caught in method checkInnerName. " + e.getMessage());
		} catch (Exception e) {
			throw new RepositoryException(className
					+ "Unknown error caught in method checkInnerName. " + e.getMessage());
		}
	}
	
	public static void main(String[] args){
		ChemicalMolecularBean cf = new ChemicalMolecularBean();
		cf.setInnerName("aa");
		cf.setChemicalName("bb");
		cf.setSmiles("cc");
		cf.setMolecularFormula("ba");
		cf.setRelatedFiles(",1,2");
		cf.setId(91);
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection cn = DriverManager.getConnection("jdbc:oracle:thin:lims/limsprod@localhost:1521:DEV1");
			System.out.println("mulin");
			//new ChemicalFileRepository(cn).insertFile(cf);
			//new ChemicalFileRepository(cn).get(30);
			//ChemicalMolecularBean[] c = (ChemicalMolecularBean[])new ChemicalMolecularRepository(cn).getByInnerName("a");
			System.out.println(new ChemicalMolecularRepository(cn).getAllInnerName().size());
			//System.out.println(c[0].getSmiles());
		}catch (RepositoryException e) {
			e.printStackTrace();
		}catch (SQLException e) {
			e.printStackTrace();
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}


