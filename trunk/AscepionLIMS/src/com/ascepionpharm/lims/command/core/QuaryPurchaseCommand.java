package com.ascepionpharm.lims.command.core;

import java.util.*;
import java.sql.Date;
import com.ascepionpharm.lims.entity.core.*;
import com.ascepionpharm.lims.repository.core.*;
import com.ascepionpharm.lims.universal.*;

/**
 * PurchaseRequestCommand: performs all actions to quarypurchase.
 * 
 * @author Li Jun Mulin
 * @version
 * 
 */

public class QuaryPurchaseCommand extends LIMSCommand {
	public QuaryPurchaseCommand(String next) {
		this.next = next;
		this.name = "QuaryPurchaseCommand";
	}

	public void performTask() throws CommandException {
		PurchasingRepository purchaseRepos;

		Closable[] closableConnections;
		Collection connections = new ArrayList();

		try {
			purchaseRepos = new PurchasingRepository(conn);
			connections.add(purchaseRepos);

			closableConnections = (Closable[]) connections
					.toArray(new Closable[0]);
		} catch (RepositoryException re) {
			throw new CommandException(name + "Error making db connections. "
					+ re.getMessage());
		}

		String way = req.getParameter("way");
		try {
			if(way.equals("purchaseperson")){
				String person = req.getParameter("yourname");
				String personlike = "%" + person + "%";
				PurchasingBean[] purchases1 = (PurchasingBean[])purchaseRepos.getByPerson(personlike);
				req.setAttribute("purchase",purchases1);
			}
			if(way.equals("purchasename")){
				String warename = req.getParameter("warename");
				String warenamelike = "%" + warename + "%";
				PurchasingBean[] purchases2 = (PurchasingBean[])purchaseRepos.getByName(warenamelike);
				req.setAttribute("purchase",purchases2);
			}
			if(way.equals("purchasedate")){
				Date startDate = DateFeeder.toSQLDate(req.getParameter("startdate"), "MM/dd/yyyy");
				Date endtDate = DateFeeder.toSQLDate(req.getParameter("enddate"), "MM/dd/yyyy");
				PurchasingBean[] purchases3 = (PurchasingBean[])purchaseRepos.getByDate(startDate, endtDate);
				req.setAttribute("purchase",purchases3);
			}
			if(way.equals("purchasetype")){
				int itemid = Integer.parseInt(req.getParameter("type"));
				PurchasingBean[] purchases4 = (PurchasingBean[])purchaseRepos.getByType(itemid);
				req.setAttribute("purchase",purchases4);
			}
			if(way.equals("purchaseproject")){
				int projectid = Integer.parseInt(req.getParameter("project"));
				PurchasingBean[] purchases5 = (PurchasingBean[])purchaseRepos.getByProject(projectid);
				req.setAttribute("purchase",purchases5);
			}
			if(way.equals("purchasedepartment")){
				int departmentid = Integer.parseInt(req.getParameter("department"));
				PurchasingBean[] purchases6 = (PurchasingBean[])purchaseRepos.getByDepartment(departmentid);
				req.setAttribute("purchase",purchases6);
			}
			if(way.equals("accountname")){
				int bankid = Integer.parseInt(req.getParameter("account"));
				PurchasingBean[] purchases7 = (PurchasingBean[])purchaseRepos.getByAccount(bankid);
				req.setAttribute("purchase",purchases7);
			}

		} catch (RepositoryException re) {
			throw new CommandException(re.getMessage());
		} finally {
			callClosable(closableConnections);
		}
	}
}
