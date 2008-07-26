package com.ascepionpharm.lims.universal;

import javax.servlet.http.HttpServletRequest;

/**
 * Command: commomn interface that all actions of the system implements.
 *
 * @author Li Jun Mulin
 * @version 
 *
 */
 
public interface Command {      
   public String execute(HttpServletRequest request,ConnectionPool connectionPool) throws CommandException;
}
