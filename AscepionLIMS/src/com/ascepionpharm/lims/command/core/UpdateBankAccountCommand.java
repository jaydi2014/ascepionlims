package com.ascepionpharm.lims.command.core;

import java.util.*;

import com.ascepionpharm.lims.entity.core.*;
import com.ascepionpharm.lims.repository.core.*;
import com.ascepionpharm.lims.universal.*;

/**
 * PurchaseRequestCommand: performs all actions to updatebankaccount.
 * 
 * @author Li Jun Mulin
 * @version
 * 
 */

public class UpdateBankAccountCommand extends LIMSCommand{
	public UpdateBankAccountCommand(String next) {
		this.next = next;
		this.name = "UpdateBankAccountCommand";
	}

	public void performTask() throws CommandException {
		BankAccountRepository bankAccountRepos;

		Closable[] closableConnections;
		Collection connections = new ArrayList();
		
		try {
			bankAccountRepos = new BankAccountRepository(conn);
			connections.add(bankAccountRepos);
			
			closableConnections = (Closable[]) connections
					.toArray(new Closable[0]);
		} catch (RepositoryException re) {
			throw new CommandException(name + "Error making db connections. "
					+ re.getMessage());
		}
		
		  try {
		         BankAccountBean bank = new BankAccountBean();
		         bank.setId(Integer.parseInt(req.getParameter("id")));
		         bank.setBankName(req.getParameter("bankName"));
		         bank.setAccountName(req.getParameter("accountName"));
		         bank.setAccountNumber(req.getParameter("accountNumber"));
		         
		         bankAccountRepos.updateBankAccounting(bank);            

		         req.setAttribute("myMessage", "Bank Account Updated successfully.");

		      } catch (RepositoryException re){
		         throw new CommandException(re.getMessage());
		      } finally {
		         callClosable(closableConnections);
		      }
	}
}
