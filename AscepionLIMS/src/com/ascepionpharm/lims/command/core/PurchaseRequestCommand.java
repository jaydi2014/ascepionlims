package com.ascepionpharm.lims.command.core;

import java.util.*;

import com.ascepionpharm.lims.entity.core.*;
import com.ascepionpharm.lims.repository.core.*;
import com.ascepionpharm.lims.universal.*;

/**
 * PurchaseRequestCommand: performs all actions to purchaserequest.
 * 
 * @author Li Jun Mulin
 * @version
 * 
 */

public class PurchaseRequestCommand extends LIMSCommand{
	public PurchaseRequestCommand(String next) {
		this.next = next;
		this.name = "PurchaseRequestCommand";
	}

	public void performTask() throws CommandException {
		PurchasingRepository purchaseRepos;
		AccountingItemRepository itemRepos;

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
		
		  try {
		         PurchasingBean purchase = new PurchasingBean();
		         purchase.setPurchasePerson(req.getParameter("purchasePerson"));
		         purchase.setItemId(Integer.parseInt(req.getParameter("accountItem")));
		         purchase.setProjectId(Integer.parseInt(req.getParameter("project")));
		         purchase.setDempartmentId(Integer.parseInt(req.getParameter("department")));
		         purchase.setPurchaseName(req.getParameter("purchaseName"));
		         purchase.setPurchaseSource(req.getParameter("purchaseSource"));
		         purchase.setPurchaseNumber(Integer.parseInt(req.getParameter("purchaseNumber")));
		         purchase.setTotleprice(Float.parseFloat(req.getParameter("totleprice")));
		         purchase.setCommentLine(req.getParameter("commentLine"));
		         
		         purchaseRepos.put(purchase);            

		         req.setAttribute("myMessage", "New Purchasing Request entered successfully.");

		      } catch (RepositoryException re){
		         throw new CommandException(re.getMessage());
		      } finally {
		         callClosable(closableConnections);
		      }
	}
}
