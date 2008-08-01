package com.ascepionpharm.lims.command.core;

import com.ascepionpharm.lims.universal.*;

/**
 * NullCommand: performs none actions.
 *
 * @author Li Jun Mulin
 * @version 
 *
 */

public class NullCommand extends LIMSCommand {
	   
	   public NullCommand(String next) {
	      this.next = next;
	      this.name = "NullCommand";
	   }

	   public void performTask() throws CommandException {
	      return;
	   }

	}
