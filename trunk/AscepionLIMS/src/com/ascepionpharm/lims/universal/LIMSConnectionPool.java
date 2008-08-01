package com.ascepionpharm.lims.universal;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import java.io.*;
import java.sql.*;
import java.util.Properties;

/**
 * LIMSConnectionPool: Initialize LIMSConnectionPool and get ConnectionPool
 * instance
 * 
 * @author Li Jun Mulin
 * @version
 * 
 */

public class LIMSConnectionPool extends ConnectionPool {
	private static final Log logger = LogFactory
			.getLog(LIMSConnectionPool.class);

	private static ConnectionPool instance;
	private static int initialConnections = 5;
	private static int maxConnections = 100;
	private static boolean waitIfBusy = true;

	private LIMSConnectionPool() throws SQLException, FileNotFoundException,
			IOException {
		super();
		Properties pp = getProperties();
		instance = new ConnectionPool(pp.getProperty("database.driver",
				"oracle.jdbc.driver.OracleDriver"), pp.getProperty(
				"database.dburl",
				"jdbc:oracle:thin:lims/limsprod@localhost:1521:DEV1"), pp
				.getProperty("database.user", "lims"), pp.getProperty(
				"database.password", "limsprod"), initialConnections,
				maxConnections, waitIfBusy);
	}

	private static Properties getProperties() throws FileNotFoundException,
			IOException {
		Properties pp = new Properties();
		pp
				.load(new FileInputStream(
						System.getProperty("catalina.home")+
						 "\\webapps\\AscepionLIMS\\WEB-INF\\classes\\System.properties"));
		return pp;
	}

	public static ConnectionPool getInstance() throws SQLException,
			FileNotFoundException, IOException {
		if (instance == null) {
			try {
				// DBConnection db = DBConnection.getInstance();
				new LIMSConnectionPool();
				if (instance != null) {
					logger.info("pool connection success");
				}
			} catch (SQLException re) {
				logger.error("error caught in getInstance. " + re.getMessage());
			}
		}
		return instance;
	}

	public static void main(String[] args) {
		try {
			ConnectionPool pool = LIMSConnectionPool.getInstance();
			Connection connection = pool.getConnection();
			if (pool == null)
				System.out.println("nullpool");
			else {
				System.out.println("successpool");
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}