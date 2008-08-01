package com.ascepionpharm.lims.universal;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * CommandException: catches and reports exceptions caught in Command classes.
 * 
 * @author Li Jun Mulin
 * @version
 * 
 */
public class CommandException extends Exception {

	private static final Log logger = LogFactory.getLog(CommandException.class);

	public CommandException() {
		super();
	}

	public CommandException(String msg) {
		logger.error(msg);
	}
}