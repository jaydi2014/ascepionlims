package com.ascepionpharm.lims.repository.chemistry;

import java.sql.*;
import java.util.*;
import java.io.*;

import oracle.jdbc.OracleResultSet;
import oracle.sql.BLOB;

import com.ascepionpharm.lims.entity.chemistry.*;
import com.ascepionpharm.lims.universal.*;

/**
 * ChemicalFileRepository: performs all database actions to the EXPERIMENTFILE
 * table.
 * 
 * @author Li Jun Mulin
 * @version
 * 
 */
public class ExperimentFileRepository extends LIMSRepository implements
		Destroyable, Closable {

	PreparedStatement selectFileByNameStmt;
	PreparedStatement deleteFileStmt;
	PreparedStatement getNameByIdStmt;
	PreparedStatement getByExperimentIdStmt;
	
	public ExperimentFileRepository() throws RepositoryException {
		this.className = "Class: ExperimentFileRepository. ";

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

	public ExperimentFileRepository(Connection conn) throws RepositoryException {
		this.className = "Class: ExperimentFileRepository. ";
		connection = conn;
		prepareCalls(conn);
	}

	public void prepareCalls(Connection conn) throws RepositoryException {
		String get = "SELECT * FROM EXPERIMENTFILE WHERE FILE_ID = ?";
		String selectFileByName = "SELECT * FROM EXPERIMENTFILE WHERE FILE_NAME = ?";
		String deletefile = "DELETE FROM EXPERIMENTFILE WHERE FILE_ID = ?";
		String getnamebyid = "SELECT FILE_NAME FROM EXPERIMENTFILE WHERE FILE_ID = ?";
		String getbyexperimentid = "SELECT * FROM EXPERIMENTFILE WHERE EXPERIMENT_ID = ?";
		
		try {
			getStmt = conn.prepareStatement(get);
			selectFileByNameStmt = conn.prepareStatement(selectFileByName);
			deleteFileStmt = conn.prepareStatement(deletefile);
			getNameByIdStmt = conn.prepareStatement(getnamebyid);
			getByExperimentIdStmt = conn.prepareStatement(getbyexperimentid);
		} catch (SQLException e) {
			throw new RepositoryException(className
					+ "SQLException caught in constructor. " + e.getMessage());
		}
	}

	public void closeCalls() throws RepositoryException {
		try {
			getStmt.close();
			selectFileByNameStmt.close();
			deleteFileStmt.close();
			getNameByIdStmt.close();
			getByExperimentIdStmt.close();
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
	
	private ExperimentFileBean makeBean(ResultSet results) throws RepositoryException {
		try {
			ExperimentFileBean experimentFile = new ExperimentFileBean();
			experimentFile.setFile_id(results.getInt("FILE_ID"));
			experimentFile.setExperiment_id(results.getInt("EXPERIMENT_ID"));
			experimentFile.setFileName(results.getString("FILE_NAME"));
			experimentFile.setFileContext(FileFeeder.writeBFile(results.getBlob("FILE_CONTEXT"),results.getString("FILE_NAME")));

			return experimentFile;
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
						+ "Could not find FILE_ID " + id);
		} catch (SQLException e) {
			throw new RepositoryException(className
					+ "SQLException caught in method get. " + e.getMessage());
		} catch (Exception e) {
			throw new RepositoryException(className
					+ "Unknown error caught in method get. " + e.getMessage());
		}
	}
	
	public String getNameById(int id) throws RepositoryException {
		try {
			ResultSet results;
			getNameByIdStmt.clearParameters();
			getNameByIdStmt.setInt(1, id);
			results = getNameByIdStmt.executeQuery();

			if (results.next())
				return results.getString("FILE_NAME");
			else
				throw new RepositoryException(className
						+ "RepositoryException caught in method getNameById. "
						+ "Could not find FILE_ID " + id);
		} catch (SQLException e) {
			throw new RepositoryException(className
					+ "SQLException caught in method getNameById. " + e.getMessage());
		} catch (Exception e) {
			throw new RepositoryException(className
					+ "Unknown error caught in method getNameById. " + e.getMessage());
		}
	}

	public Item[] getAll() throws RepositoryException {
		return null;
	}

	public ExperimentFileBean[] getList(int id) throws RepositoryException {
		try {
			ResultSet results;
			getByExperimentIdStmt.clearParameters();
			Collection files = new ArrayList();
			getByExperimentIdStmt.setInt(1, id);
			results = getByExperimentIdStmt.executeQuery();

			while (results.next()) {
				files.add(makeBean(results));
			}
			return (ExperimentFileBean[]) files
					.toArray(new ExperimentFileBean[0]);
		} catch (SQLException e) {
			throw new RepositoryException(className
					+ "SQLException caught in method getList. " + e.getMessage());
		} catch (Exception e) {
			throw new RepositoryException(className
					+ "Unknown error caught in method getList. " + e.getMessage());
		}
	}

	public void put(Item item) throws RepositoryException {

	}

	public void remove(Item item) throws RepositoryException {
		
	}

	public void update(Item item) throws RepositoryException {

	}

	public Item selectByName(String fileName)throws RepositoryException {
		try {
			ResultSet results;
			selectFileByNameStmt.clearParameters();
			selectFileByNameStmt.setString(1, fileName);
			results = selectFileByNameStmt.executeQuery();

			if (results.next())
				return makeBean(results);
			else
				throw new RepositoryException(className
						+ "RepositoryException caught in method get. "
						+ "Could not find FILE_NAME " + fileName);
		} catch (SQLException e) {
			throw new RepositoryException(className
					+ "SQLException caught in method selectByName. " + e.getMessage());
		} catch (Exception e) {
			throw new RepositoryException(className
					+ "Unknown error caught in method selectByName. " + e.getMessage());
		}
	}
	
	public void removeFile(int fileId)throws RepositoryException {
		try {
			deleteFileStmt.clearParameters();
			deleteFileStmt.setInt(1, fileId);
			deleteFileStmt.execute();

		} catch (SQLException e) {
			throw new RepositoryException(className
					+ "SQLException caught in method remove. " + e.getMessage());
		} catch (Exception e) {
			throw new RepositoryException(className
					+ "Unknown error caught in method remove. " + e.getMessage());
		}
	}
	
	public synchronized long insertFile(Item myExperimentFile) throws RepositoryException{
		ExperimentFileBean experimentFile = (ExperimentFileBean)myExperimentFile;
		int fileId = experimentFile.getExperiment_id();
		String fliename = experimentFile.getFileName();
		File fileContext = experimentFile.getFileContext();
		
		try {
			connection.setAutoCommit(false);
			Statement stmt=connection.createStatement();
			
			String sql="INSERT INTO EXPERIMENTFILE VALUES (EXPERIMENTFILE_SEQ.NEXTVAL,"+ fileId +",'" + fliename + "',EMPTY_BLOB())";
			stmt.execute(sql);
			
			sql="SELECT EXPERIMENTFILE_SEQ.CURRVAL FROM DUAl";
			ResultSet rs = stmt.executeQuery(sql);
			rs.next();
			long id = rs.getInt(1);
			
			sql="SELECT FILE_CONTEXT FROM EXPERIMENTFILE WHERE FILE_ID=" + id + " FOR UPDATE"; 
			rs = stmt.executeQuery(sql);
			rs.next();
			BLOB blob=((OracleResultSet)rs).getBLOB("FILE_CONTEXT");
			OutputStream out=blob.getBinaryOutputStream();
			InputStream in=new FileInputStream(fileContext);
			int size=blob.getBufferSize();
			byte[] buffer=new byte[size];
			int len;
			while((len=in.read(buffer)) != -1)
			out.write(buffer,0,len);
			in.close();
			out.close(); 

			connection.commit();
			
			return id;
			
		} catch (SQLException e) {
			throw new RepositoryException(className
					+ "SQLException caught in method insertFile. "
					+ e.getMessage());
		} catch (Exception e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				throw new RepositoryException(className
						+ "SQLException caught in method insertFile. "
						+ e.getMessage());
			}
			throw new RepositoryException(className
					+ "Unknown error caught in method insertFile. "
					+ e.getMessage());
		}
	}
	
	public static void main(String[] args){
		ExperimentFileBean ef = new ExperimentFileBean();
		File file = new File("C:\\log\\AscepiomLIMS.log");
		String filename = "Ascent1111.jpg";
		ef.setExperiment_id(2);
		ef.setFileName(filename);
		ef.setFileContext(file);
		
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
			new ExperimentFileRepository(cn).getList(141);
			//new ChemicalFileRepository(cn).get(30);
//			ChemicalFileBean relatedFile=(ChemicalFileBean)new ChemicalFileRepository(cn).get(172);
//			System.out.println(new ChemicalFileRepository(cn).getNameById(172));
//			System.getProperty("catalina.home");
			new ChemicalFileRepository(cn).removeFile(102);
		}catch (RepositoryException e) {
			e.printStackTrace();
		}catch (SQLException e) {
			e.printStackTrace();
		}catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

}
