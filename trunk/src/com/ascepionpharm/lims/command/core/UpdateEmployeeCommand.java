package com.ascepionpharm.lims.command.core;

import java.util.*;

import com.ascepionpharm.lims.entity.core.*;
import com.ascepionpharm.lims.repository.core.*;
import com.ascepionpharm.lims.universal.*;

/**
 * UpdateEmployeeCommand: performs all actions to updateemployee.
 * 
 * @author Li Jun Mulin
 * @version
 * 
 */

public class UpdateEmployeeCommand extends LIMSCommand {
	
	public UpdateEmployeeCommand(String next) {
		this.next = next;
		this.name = "UpdateEmployeeCommand";
	}

	public void performTask() throws CommandException {
		EmployeeRepository employeeRepos;

		Closable[] closableConnections;
		Collection connections = new ArrayList();
		
		try {
			employeeRepos = new EmployeeRepository(conn);
			connections.add(employeeRepos);
			
			closableConnections = (Closable[]) connections
					.toArray(new Closable[0]);
		} catch (RepositoryException re) {
			throw new CommandException(name + "Error making db connections. "
					+ re.getMessage());
		}
		
		  try {
		         EmployeeBean employee = new EmployeeBean();
		         employee.setId(Integer.parseInt(req.getParameter("id")));
		         employee.setEmployeeNumber(req.getParameter("employeeNumber"));
		         employee.setName(req.getParameter("name"));
		         employee.setActive(Integer.parseInt(req.getParameter("active")));
		         employee.setBrith(DateFeeder.toSQLDate(req.getParameter("brith"), "MM/dd/yyyy"));
		         employee.setAge(Integer.parseInt(req.getParameter("age").trim()));
		         employee.setSex(req.getParameter("sex"));
		         employee.setIdentity(req.getParameter("identity"));
		         employee.setNativeplace(req.getParameter("nativeplace"));
		         employee.setDegree(req.getParameter("degree"));
		         employee.setSchool(req.getParameter("school"));
		         employee.setPactStartTime(DateFeeder.toSQLDate(req.getParameter("pactStartTime"), "MM/dd/yyyy"));
		         employee.setPactEndTime(DateFeeder.toSQLDate(req.getParameter("pactEndTime"), "MM/dd/yyyy"));
		         employee.setEntranceTime(DateFeeder.toSQLDate(req.getParameter("entranceTime"), "MM/dd/yyyy"));
		         employee.setLeaveTime(DateFeeder.toSQLDate(req.getParameter("leaveTime"), "MM/dd/yyyy"));
		         employee.setParty(req.getParameter("party"));
		         employee.setRecordplace(req.getParameter("recordplace"));
		         employee.setCommentline(req.getParameter("comment"));
		         employee.setEmail(req.getParameter("email"));
		         employee.setLabPhone(req.getParameter("labPhone").trim());
		         employee.setMobilePhone(req.getParameter("mobilePhone").trim());
		         employee.setTitle(req.getParameter("title"));
		         employee.setSupervisorId(Integer.parseInt(req.getParameter("supervisorName").trim()));
		         employee.setNavisionCode(req.getParameter("navisionCode"));
		         employee.setInit(req.getParameter("init"));
		         employee.setDepartmentId(Integer.parseInt(req.getParameter("department").trim()));
		         
		         employeeRepos.update(employee);            

		         req.setAttribute("myMessage", "Update employee successfully.");

		      } catch (RepositoryException re){
		         throw new CommandException(re.getMessage());
		      } finally {
		         callClosable(closableConnections);
		      }
	}
}
