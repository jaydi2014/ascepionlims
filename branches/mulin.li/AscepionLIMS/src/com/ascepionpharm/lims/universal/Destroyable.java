package com.ascepionpharm.lims.universal;

/**
 * Destroyable: common interface that all Repository classes implement to accomplish DBConnection free.
 *
 * @author Li Jun Mulin 
 * @version 
 *
 */

public interface Destroyable {
   public void destroy() throws RepositoryException;
}