package com.ascepionpharm.lims.universal;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import java.util.*;
import java.sql.*;
import com.ascepionpharm.lims.command.core.*;


/**
 * MainServlet: the main servlet of the Core LIMS system.
 * 
 * @author Li Jun Mulin
 * @version
 * 
 */

public class MainServlet extends HttpServlet {
	private static final Log logger = LogFactory.getLog(MainServlet.class);
	private HashMap commands;
	private String error = "common/Error.jsp";
	private String jspdir = "/";
	private ConnectionPool pool;

	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		initCommands();
		try {
			pool = LIMSConnectionPool.getInstance();
		} catch (FileNotFoundException e) {
			logger.error("can not find properties file " + e.getMessage());
		} catch (IOException e) {
			logger.error("io exception when get LIMSConnectionPool " + e.getMessage());
		} catch (SQLException e) {
			logger.error("sql exception when get LIMSConnectionPool " + e.getMessage());
		}
	}

	public void service(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		String next;

		try {
			req.setCharacterEncoding("UTF-8");
			Command cmd = lookupCommand(req.getParameter("cmd"));
			next = cmd.execute(req, pool);
		} catch (CommandException e) {
			next = error;
			req.setAttribute("javax.servlet.jsp.jspException", e);
		}

		RequestDispatcher rd;
		rd = getServletContext().getRequestDispatcher(jspdir + next);
		rd.forward(req, res);
	}

	private Command lookupCommand(String cmd) throws CommandException {
		if (cmd == null)
			cmd = "main-menu";
		if (commands.containsKey(cmd))
			return (Command) commands.get(cmd);
		else
			throw new CommandException("Invalid Command Identifier");
	}

	private void initCommands() {
		commands = new HashMap();
		
		commands.put("main-menu", new NullCommand("core/Login.jsp"));
        commands.put("login", new LoginCommand("core/Main.jsp"));
        commands.put("choose-system", new ChooseSystemCommand("core/Main.jsp"));
        
        //Administration
        commands.put("new-limspage", new NewLIMSPageCommand("core/Message.jsp"));
        commands.put("new-employee", new NewEmployeeCommand("core/Message.jsp"));
        commands.put("give-permission", new GivePermissionCommand("core/Message.jsp"));
        commands.put("update-employee", new UpdateEmployeeCommand("core/Message.jsp"));
        
        //Purchasing
        commands.put("purchase-request", new PurchaseRequestCommand("core/Message.jsp"));
        
		// //ALERT
		// commands.put("check-status", new
		// CheckApplicationStatusCommand("status.jsp"));
		// //ADMIN
		// //chemistry
		// commands.put("update-smiles", new
		// UpdateSampleSmilesCommand("message.jsp"));
		// //CORE
		// //General
		// commands.put("main-menu", new NullCommand("login.jsp"));
		// commands.put("login", new LoginCommand("main.jsp"));
	}
}