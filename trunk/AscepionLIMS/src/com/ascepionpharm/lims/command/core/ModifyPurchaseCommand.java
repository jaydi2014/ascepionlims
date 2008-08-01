package com.ascepionpharm.lims.command.core;

import java.util.*;

import com.ascepionpharm.lims.entity.core.*;
import com.ascepionpharm.lims.repository.core.*;
import com.ascepionpharm.lims.universal.*;

/**
 * PurchaseRequestCommand: performs all actions to modifypurchase.
 * 
 * @author Li Jun Mulin
 * @version
 * 
 */

public class ModifyPurchaseCommand extends LIMSCommand{
	public ModifyPurchaseCommand(String next) {
		this.next = next;
		this.name = "ModifyPurchaseCommand";
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
		
		  try {
		         PurchasingBean purchase = new PurchasingBean();
		         purchase.setId(Integer.parseInt(req.getParameter("purchaseId")));
		         purchase.setBankId(Integer.parseInt(req.getParameter("bankId")));
		         purchase.setPurchaseDate(DateFeeder.toSQLDate(req.getParameter("purchaseDate"), "MM/dd/yyyy"));
		         purchase.setPurchaseSource(req.getParameter("purchaseSource"));
		         purchase.setPurchasePerson(req.getParameter("purchasePerson"));
		         purchase.setPurchaseName(req.getParameter("purchaseName"));
		         purchase.setPurchaseNumber(Integer.parseInt(req.getParameter("purchaseNumber")));
		         purchase.setTotleprice(Float.parseFloat(req.getParameter("totleprice")));
		         purchase.setInvoiceArriveTime(DateFeeder.toSQLDate(req.getParameter("invoiceArriveTime"), "MM/dd/yyyy"));
		         purchase.setInvoiceNumber(req.getParameter("invoiceNumber"));
		         purchase.setPaymentway(req.getParameter("paymentway"));
		         purchase.setIsArrive(Integer.parseInt(req.getParameter("isArrive")));
		         purchase.setCommentLine(req.getParameter("commentLine"));
		         purchase.setIsApproved(Integer.parseInt(req.getParameter("isApproved")));
		         
		         purchaseRepos.update(purchase);            

		         req.setAttribute("myMessage", "Purchase Updated successfully.");

		      } catch (RepositoryException re){
		         throw new CommandException(re.getMessage());
		      } finally {
		         callClosable(closableConnections);
		      }
	}
}
