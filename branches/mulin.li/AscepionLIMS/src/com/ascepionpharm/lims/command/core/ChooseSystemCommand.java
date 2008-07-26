package com.ascepionpharm.lims.command.core;

import javax.servlet.http.HttpSession;
import com.ascepionpharm.lims.universal.*;

/**
 * ChooseSystemCommand: performs all actions to choosesystem.
 *
 * @author Li Jun Mulin
 * @version 
 *
 */

public class ChooseSystemCommand extends LIMSCommand {

	public ChooseSystemCommand(String next) {
		this.next = next;
		this.name = "ChooseSystemCommand";
	}

	public void performTask() throws CommandException {
		HttpSession mySession = req.getSession();
		mySession.setAttribute("mySystem", req.getParameter("system"));

		if (req.getParameter("system").equals("admin")) {
			req.setAttribute("myMessage",
					"Welcome to the Ascepion Administration System.");
		} else if (req.getParameter("system").equals("purchase")) {
			req.setAttribute("myMessage",
					"Welcome to the Ascepion Purchasing System.");
		} else if (req.getParameter("system").equals("colony")) {
			req.setAttribute("myMessage",
					"Welcome to the CGI Colony Management System.");
		} else if (req.getParameter("system").equals("chemistry")) {
			req.setAttribute("myMessage",
					"Welcome to the CGI Chemistry LIMS System.");
		} else if (req.getParameter("system").equals("molbio")) {
			req.setAttribute("myMessage",
					"Welcome to the CGI Molecular Biology LIMS System.");
		} else if (req.getParameter("system").equals("browser")) {
			req.setAttribute("myMessage",
					"Welcome to the CGI Core LIMS System.");
		} else if (req.getParameter("system").equals("admin")) {
			req.setAttribute("myMessage", "CGI LIMS Administration.");
		} else if (req.getParameter("system").equals("purchase")) {
			req.setAttribute("myMessage",
					"Welcome to the CGI Purchasing System.");
		} else {
			req.setAttribute("myMessage", "System Unknown.");
		}
	}

}
