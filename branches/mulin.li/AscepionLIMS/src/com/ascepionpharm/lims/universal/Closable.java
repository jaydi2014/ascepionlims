package com.ascepionpharm.lims.universal;

/**
 * Closable: commomn interface that all Repository of the system implements to accomplish DBConnection free
 *
 * @author Li Jun Mulin
 * @version 
 *
 */

public interface Closable {
   public void closeCalls() throws RepositoryException;
}