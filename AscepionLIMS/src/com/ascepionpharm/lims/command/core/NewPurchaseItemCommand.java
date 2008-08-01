package com.ascepionpharm.lims.command.core;

import java.util.*;

import com.ascepionpharm.lims.entity.core.*;
import com.ascepionpharm.lims.repository.core.*;
import com.ascepionpharm.lims.universal.*;

/**
 * PurchaseRequestCommand: performs all actions to addaccountingitem.
 * 
 * @author Li Jun Mulin
 * @version
 * 
 */

public class NewPurchaseItemCommand extends LIMSCommand{
	public NewPurchaseItemCommand(String next) {
		this.next = next;
		this.name = "NewPurchaseItemCommand";
	}

	public void performTask() throws CommandException {
		AccountingItemRepository accountingItemRepos;

		Closable[] closableConnections;
		Collection connections = new ArrayList();
		
		try {
			accountingItemRepos = new AccountingItemRepository(conn);
			connections.add(accountingItemRepos);
			
			closableConnections = (Closable[]) connections
					.toArray(new Closable[0]);
		} catch (RepositoryException re) {
			throw new CommandException(name + "Error making db connections. "
					+ re.getMessage());
		}
		
		  try {
		         AccountingItemBean item = new AccountingItemBean();
		         item.setName(req.getParameter("name"));
		         item.setCommentline(req.getParameter("commentline"));
		         
		         accountingItemRepos.insertItem(item);            

		         req.setAttribute("myMessage", "New Accounting Item entered successfully.");

		      } catch (RepositoryException re){
		         throw new CommandException(re.getMessage());
		      } finally {
		         callClosable(closableConnections);
		      }
	}
}
