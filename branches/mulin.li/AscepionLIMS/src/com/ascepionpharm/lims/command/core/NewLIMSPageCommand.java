package com.ascepionpharm.lims.command.core;

import java.util.ArrayList;
import java.util.Collection;

import com.ascepionpharm.lims.entity.core.*;
import com.ascepionpharm.lims.repository.core.*;
import com.ascepionpharm.lims.universal.*;

/**
 * NewLIMSPageCommand: performs all actions to newlimspage.
 *
 * @author Li Jun Mulin
 * @version 
 *
 */

public class NewLIMSPageCommand extends LIMSCommand{

	public NewLIMSPageCommand(String next) {
		this.next = next;
		this.name = "NewLIMSPageCommand";
	}
	
	public void performTask() throws CommandException {
		PermissionRepository permissionRepos;
		
		Closable[] closableConnections;
		Collection connections = new ArrayList();
		
		try {
			permissionRepos = new PermissionRepository(conn);
			connections.add(permissionRepos);
			
			closableConnections = (Closable[]) connections
					.toArray(new Closable[0]);
		} catch (RepositoryException re) {
			throw new CommandException(name + "Error making db connections. "
					+ re.getMessage());
		}
		
		  try {
		         PageBean page = new PageBean();
		         page.setName(req.getParameter("url"));
		         
		         permissionRepos.insertPage(page);            
		         req.setAttribute("myMessage", "New LIMS Page inserted successfully.");

		      } catch (RepositoryException re){
		         throw new CommandException(re.getMessage());
		      } finally {
		         callClosable(closableConnections);
		      }
	}

}
