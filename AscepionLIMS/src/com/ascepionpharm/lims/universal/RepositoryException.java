package com.ascepionpharm.lims.universal;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Repository Exception: catches and reports exceptions caught in Repository classes.
 *
 * @author Li Jun Mulin
 * @version 
 *
 */
public class RepositoryException extends Exception {
	
	private static final Log logger = LogFactory.getLog(RepositoryException.class);

	
   public RepositoryException(){
		super();
	}
	public RepositoryException(String msg){
		logger.error(msg);
	}
}