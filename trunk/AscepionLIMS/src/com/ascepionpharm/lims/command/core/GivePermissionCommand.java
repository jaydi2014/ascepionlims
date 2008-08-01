package com.ascepionpharm.lims.command.core;

import java.util.*;

import com.ascepionpharm.lims.entity.core.*;
import com.ascepionpharm.lims.repository.core.*;
import com.ascepionpharm.lims.universal.*;

/**
 * GivePermissionCommand: performs all actions to givepermission.
 * 
 * @author Li Jun Mulin
 * @version
 * 
 */

public class GivePermissionCommand extends LIMSCommand{

	public GivePermissionCommand(String next) {
		this.next = next;
		this.name = "GivePermissionCommand";
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
		         PermissionBean permission = new PermissionBean();
		         permission.setPageId(Integer.parseInt(req.getParameter("pageid")));
		         permission.setEmployeeTypeId(Integer.parseInt(req.getParameter("employeetypeid")));
		         if(Integer.parseInt(req.getParameter("granted")) == 0){
		        	 permission.setGranted(1);
		         }
		         if(Integer.parseInt(req.getParameter("granted")) == 1){
		        	 permission.setGranted(0);
		         }
		         
		         permissionRepos.grantPermission(permission);
		         
		         req.setAttribute("myMessage", "Authority has changed.");

		      } catch (RepositoryException re){
		         throw new CommandException(re.getMessage());
		      } finally {
		         callClosable(closableConnections);
		      }
	}

}
