package com.ascepionpharm.lims.universal;

import javax.servlet.http.HttpServletRequest;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * LIMSCommand: abstract class that all LIMS commands extend
 * 
 * @author Li Jun Mulin
 * @version
 * 
 */

public abstract class LIMSCommand implements Command {
	protected ConnectionPool pool;
	protected Connection conn;
	protected String next;
	protected String failure;
	protected String name;
	protected HttpServletRequest req;

	public String execute(HttpServletRequest request,
			ConnectionPool connectionPool) throws CommandException {
		req = request;
		try {
			if (connectionPool == null) {
				pool = LIMSConnectionPool.getInstance();
				conn = pool.getConnection();
			} else {
				conn = connectionPool.getConnection();
			}
		} catch (IOException e) {
			throw new CommandException(e.getMessage());
		} catch (SQLException e) {
			throw new CommandException(e.getMessage());
		}

		try {
			performTask();
		} catch (CommandException e) {
			throw new CommandException(e.getMessage());
		}
		connectionPool.free(conn);
		if(req.getAttribute("failure") != null && "failure".equals(req.getAttribute("failure"))){
			failure = "core/Message.jsp";
			req.removeAttribute("failure");
			return failure;
		}
		else{
			return next;
		}
	}

	public abstract void performTask() throws CommandException;

	public void callClosable(Closable[] closableConnections)
			throws CommandException{
		for (int i = 0; i < closableConnections.length; i++) {
			try {
				closableConnections[i].closeCalls();
			} catch (RepositoryException e) {
				throw new CommandException(e.getMessage());
			}
		}
	}
}