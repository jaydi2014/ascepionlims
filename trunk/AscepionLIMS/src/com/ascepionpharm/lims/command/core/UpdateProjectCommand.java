package com.ascepionpharm.lims.command.core;

import java.util.*;

import com.ascepionpharm.lims.entity.core.*;
import com.ascepionpharm.lims.repository.core.*;
import com.ascepionpharm.lims.universal.*;


/**
 * LoginCommand: performs all actions to updateproject
 * 
 * @author Li Jun Mulin
 * @version
 * 
 */
public class UpdateProjectCommand extends LIMSCommand{
	public UpdateProjectCommand(String next) {
		this.next = next;
		this.name = "UpdateProjectCommand";
	}

	public void performTask() throws CommandException {
		ProjectRepository projectRepos;

		Closable[] closableConnections;
		Collection connections = new ArrayList();
		
		try {
			projectRepos = new ProjectRepository(conn);
			connections.add(projectRepos);
			
			closableConnections = (Closable[]) connections
					.toArray(new Closable[0]);
		} catch (RepositoryException re) {
			throw new CommandException(name + "Error making db connections. "
					+ re.getMessage());
		}
		
		  try {
		         ProjectBean project = new ProjectBean();
		         project.setId(Integer.parseInt(req.getParameter("id").trim()));
		         project.setDepartmentId(Integer.parseInt(req.getParameter("department").trim()));
		         project.setName(req.getParameter("name"));
		         project.setDescription(req.getParameter("description"));
		         project.setMembers(req.getParameter("members"));
		         project.setStart_time(DateFeeder.toSQLDate(req.getParameter("start_time"), "MM/dd/yyyy"));
		         project.setEnd_time(DateFeeder.toSQLDate(req.getParameter("end_time"), "MM/dd/yyyy"));
		         project.setStatus(Integer.parseInt(req.getParameter("status").trim()))	;
		         
		         projectRepos.update(project);            

		         req.setAttribute("myMessage", "Project updated successfully.");

		      } catch (RepositoryException re){
		         throw new CommandException(re.getMessage());
		      } finally {
		         callClosable(closableConnections);
		      }
	}
}
