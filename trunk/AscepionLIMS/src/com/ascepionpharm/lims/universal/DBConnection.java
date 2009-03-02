package com.ascepionpharm.lims.universal;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * DBConnection: provides db connection information for entire system.
 * 
 * @author Li Jun Mulin
 * @version
 * 
 */
public class DBConnection {
	private static final Log logger = LogFactory.getLog(DBConnection.class);
	private static DBConnection instance;

	private String driver;
	private String user;
	private String pass;
	private String dbURL;

	public static Properties getProperties() throws FileNotFoundException,
			IOException {
		Properties pp = new Properties();
		pp.load(new FileInputStream(System.getProperty("catalina.home")+
				 "\\webapps\\AscepionLIMS\\WEB-INF\\classes\\System.properties"));
		return pp;
	}

	private DBConnection() {
		try {
			Properties pp = getProperties();
			driver = pp.getProperty("database.driver");
			pass = pp.getProperty("database.password");
			user = pp.getProperty("database.user");
			dbURL = pp.getProperty("database.dburl");
		} catch (FileNotFoundException e) {
			logger.error("can not fine properties file " + e.getMessage());
		} catch (IOException e) {
			logger.error("io exception when get LIMSConnectionPool " + e.getMessage());
		}
	}

	public static DBConnection getInstance() {
		if (instance == null){
			instance = new DBConnection();
			logger.info("db connection success");
		}
		return instance;
	}

	public String getDriver() {
		return driver;
	}

	public String getUser() {
		return user;
	}

	public String getPass() {
		return pass;
	}

	public String getDbURL() {
		return dbURL;
	}

	public static void main(String[] args) throws ClassNotFoundException,
			SQLException {
		Connection connection = null;
		Class.forName("oracle.jdbc.driver.OracleDriver");
		connection = DriverManager.getConnection("jdbc:oracle:thin:lims/limsprod@localhost:1521:DEV1");

		if (connection != null) {
			System.out.println("connection success");
		} else {
			System.out.println("connection failed");
		}
	}
}