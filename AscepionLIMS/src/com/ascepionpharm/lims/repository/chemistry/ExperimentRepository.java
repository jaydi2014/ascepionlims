package com.ascepionpharm.lims.repository.chemistry;

import java.sql.*;
import java.util.*;
import java.io.*;

import oracle.jdbc.OracleResultSet;
import oracle.sql.BLOB;

import com.ascepionpharm.lims.entity.chemistry.*;
import com.ascepionpharm.lims.entity.core.*;
import com.ascepionpharm.lims.universal.*;

/**
 * ReactionRepository: performs all database actions to the Experiment table.
 * 
 * @author Li Jun Mulin
 * @version
 * 
 */
public class ExperimentRepository extends LIMSRepository implements
		Destroyable, Closable {

	PreparedStatement insertExperimentStmt;
	PreparedStatement updateExperimentStmt;
	PreparedStatement getByReactionStmt;

	public ExperimentRepository() throws RepositoryException {
		this.className = "Class: ExperimentRepository. ";

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

	public ExperimentRepository(Connection conn) throws RepositoryException {
		this.className = "Class: ExperimentRepository. ";
		connection = conn;
		prepareCalls(conn);
	}

	public void prepareCalls(Connection conn) throws RepositoryException {
		String get = "SELECT * FROM EXPERIMENT WHERE EXPERIMENT_ID = ?";
		String insert = "INSERT INTO EXPERIMENT VALUES (EXPERIMENT_SEQ.NEXTVAL,?,?,?,?,?,EMPTY_BLOB(),?,?,?)";
		String update = "UPDATE EXPERIMENT SET NAME = ?, EXPDATE = ?, TEMPERATURE = ?, HUMIDITY = ?, PROCESS = ?, COMMENTLINE = ?, RESULT = ? WHERE EXPERIMENT_ID = ?";
		String getbyreaction = "SELECT * FROM EXPERIMENT WHERE REACTION_ID = ?";

		try {
			getStmt = conn.prepareStatement(get);
			insertExperimentStmt = conn.prepareStatement(insert);
			updateExperimentStmt = conn.prepareStatement(update);
			getByReactionStmt = conn.prepareStatement(getbyreaction);

		} catch (SQLException e) {
			throw new RepositoryException(className
					+ "SQLException caught in constructor. " + e.getMessage());
		}
	}

	public void closeCalls() throws RepositoryException {
		try {
			getStmt.close();
			insertExperimentStmt.close();
			updateExperimentStmt.close();
			getByReactionStmt.close();
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

	private ExperimentBean makeBean(ResultSet results)
			throws RepositoryException {
		try {
			ExperimentBean experiment = new ExperimentBean();
			experiment.setExperiment_id(results.getInt("EXPERIMENT_ID"));
			experiment.setName(results.getString("NAME"));
			experiment.setDate(results.getDate("EXPDATE"));
			experiment.setTemperature(results.getInt("TEMPERATURE"));
			experiment.setHumidity(results.getInt("HUMIDITY"));
			experiment.setReaction_id(results.getInt("REACTION_ID"));
			experiment.setRawRatio(FileFeeder.readObject(results
					.getBlob("RAWRATIO")));
			experiment.setProcess(results.getString("PROCESS"));
			experiment.setComment(results.getString("COMMENTLINE"));
			experiment.setResult(results.getString("RESULT"));

			return experiment;
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

	public Item[] getAll() throws RepositoryException {
		return null;
	}

	public Item[] getList(int id) throws RepositoryException {
		return null;
	}

	public void putBefore(Item myExperiment) throws RepositoryException {
		ExperimentBean experiment = (ExperimentBean) myExperiment;

		try {
			boolean bogus;
			insertExperimentStmt.clearParameters();
			insertExperimentStmt.setString(1, experiment.getName());
			insertExperimentStmt.setDate(2, experiment.getDate());
			insertExperimentStmt.setInt(3, experiment.getTemperature());
			insertExperimentStmt.setInt(4, experiment.getHumidity());
			insertExperimentStmt.setInt(5, experiment.getReaction_id());
			insertExperimentStmt.setString(6, experiment.getProcess());
			insertExperimentStmt.setString(7, experiment.getComment());
			insertExperimentStmt.setString(8, experiment.getResult());

			bogus = insertExperimentStmt.execute();

		} catch (SQLException e) {
			throw new RepositoryException(className
					+ "SQLException caught in method put. " + e.getMessage());
		} catch (Exception e) {
			throw new RepositoryException(className
					+ "unknown error caught in method put. " + e.getMessage());
		}
	}

	public void put(Item item) throws RepositoryException {

	}

	public int getLastId() throws RepositoryException {
		try {
			Statement stmt = connection.createStatement();

			String sql = "SELECT EXPERIMENT_ID FROM EXPERIMENT WHERE ROWNUM = 1 ORDER BY EXPERIMENT_ID DESC";
			ResultSet rs = stmt.executeQuery(sql);
			rs.next();
			int id = rs.getInt(1);

			return id;

		} catch (SQLException e) {
			throw new RepositoryException(className
					+ "SQLException caught in method getLastId. "
					+ e.getMessage());
		} catch (Exception e) {
			throw new RepositoryException(className
					+ "unknown error caught in method getLastId. "
					+ e.getMessage());
		}
	}

	public synchronized long updateRawRatio(Item myExperiment, int id)
			throws RepositoryException {

		ExperimentBean experiment = (ExperimentBean) myExperiment;
		Object obj = experiment.getRawRatio();
		if (obj == null) {
			obj = new Object();
		}

		try {
			connection.setAutoCommit(false);
			Statement stmt = connection.createStatement();

			String sql = "UPDATE EXPERIMENT SET RAWRATIO = EMPTY_BLOB() WHERE EXPERIMENT_ID = "
					+ id;
			stmt.execute(sql);

			sql = "SELECT RAWRATIO FROM EXPERIMENT WHERE EXPERIMENT_ID=" + id
					+ " FOR UPDATE";
			ResultSet rs = stmt.executeQuery(sql);
			rs.next();
			BLOB blob = ((OracleResultSet) rs).getBLOB("RAWRATIO");
			OutputStream out = blob.getBinaryOutputStream();
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(bos);
			oos.writeObject(obj);
			ByteArrayInputStream bis = new ByteArrayInputStream(bos
					.toByteArray());
			int size = blob.getBufferSize();
			byte[] buffer = new byte[size];
			int len;
			while ((len = bis.read(buffer)) != -1)
				out.write(buffer, 0, len);
			bos.close();
			oos.close();
			bis.close();
			out.close();

			connection.commit();

			return id;
		} catch (SQLException e) {
			throw new RepositoryException(className
					+ "SQLException caught in method updateRawRatio. "
					+ e.getMessage());
		} catch (Exception e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				throw new RepositoryException(className
						+ "SQLException caught in method updateRawRatio. "
						+ e.getMessage());
			}
			throw new RepositoryException(className
					+ "Unknown error caught in method updateRawRatio. "
					+ e.getMessage());
		}
	}

	public synchronized int insert(Item myExperiment)
			throws RepositoryException {
		putBefore(myExperiment);
		int id = getLastId();
		updateRawRatio(myExperiment, id);
		return id;
	}

	public void remove(Item item) throws RepositoryException {

	}

	public void update(Item myExperiment) throws RepositoryException {
		ExperimentBean experiment = (ExperimentBean) myExperiment;

		try {
			boolean bogus;
			updateExperimentStmt.clearParameters();
			updateExperimentStmt.setString(1, experiment.getName());
			updateExperimentStmt.setDate(2, experiment.getDate());
			updateExperimentStmt.setInt(3, experiment.getTemperature());
			updateExperimentStmt.setInt(4, experiment.getHumidity());
			updateExperimentStmt.setString(5, experiment.getProcess());
			updateExperimentStmt.setString(6, experiment.getComment());
			updateExperimentStmt.setString(7, experiment.getResult());
			updateExperimentStmt.setInt(8, experiment.getExperiment_id());
			bogus = updateExperimentStmt.execute();

			updateRawRatio(myExperiment, experiment.getExperiment_id());

		} catch (SQLException e) {
			throw new RepositoryException(className
					+ "SQLException caught in method update. " + e.getMessage());
		} catch (Exception e) {
			throw new RepositoryException(className
					+ "unknown error caught in method update. "
					+ e.getMessage());
		}
	}

	public ExperimentBean[] getByReaction(int reactionId) throws RepositoryException {
		try {
			ResultSet results;
			getByReactionStmt.clearParameters();
			Collection experiments = new ArrayList();
			getByReactionStmt.setInt(1, reactionId);
			results = getByReactionStmt.executeQuery();

			while (results.next()) {
				experiments.add(makeBean(results));
			}
			return (ExperimentBean[])experiments.toArray(new ExperimentBean[0]);
		} catch (SQLException e) {
			throw new RepositoryException(className
					+ "SQLException caught in method getByReaction. "
					+ e.getMessage());
		} catch (Exception e) {
			throw new RepositoryException(className
					+ "Unknown error caught in method getByReaction. "
					+ e.getMessage());
		}
	}

	public static void main(String[] args) {

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection cn = DriverManager
					.getConnection("jdbc:oracle:thin:lims/limsprod@localhost:1521:DEV1");
			System.out.println("mulin");
			List list = new ArrayList();
			Map mapa = new HashMap();
			Map mapb = new HashMap();
			mapa.put("1", "a");
			mapa.put("2", "b");
			mapb.put("1", "hdd");
			mapb.put("2", "hdddddddd");
			list.add(mapa);
			list.add(mapb);
			ExperimentBean exp = new ExperimentBean();
			exp.setName("mulifdfagfnli");
			exp.setDate(DateFeeder.toSQLDate("02/21/2019", "MM/dd/yyyy"));
			exp.setHumidity(15);
			exp.setTemperature(20);
			exp.setProcess("fdfdsagag");
			exp.setComment("comment");
			exp.setResult("dfsdgfgfgf");
			exp.setExperiment_id(42);
			exp.setRawRatio(list);
			System.out.println(new ExperimentRepository(cn).getByReaction(203));
			// new ChemicalFileRepository(cn).get(30);
			// ChemicalFileBean relatedFile=(ChemicalFileBean)new
			// ChemicalFileRepository(cn).get(172);
			// System.out.println(new
			// ChemicalFileRepository(cn).getNameById(172));
			// System.getProperty("catalina.home");
		} catch (RepositoryException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}
