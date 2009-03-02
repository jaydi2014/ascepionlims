package com.ascepionpharm.lims.command.core;

import com.ascepionpharm.lims.universal.*;

/**
 * ChooseSystemCommand: performs all actions to logout.
 *
 * @author Li Jun Mulin
 * @version 
 *
 */

public class LogoutCommand extends LIMSCommand{
	public LogoutCommand(String next) {
		this.next = next;
		this.name = "LogoutCommand";
	}

	public void performTask() throws CommandException {
		req.getSession().invalidate();
	}
}
