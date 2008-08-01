package com.ascepionpharm.lims.universal;

import java.io.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * FileFeeder: operate the out file about LIMS.
 *
 * @author Li Jun Mulin 
 * @version 
 *
 */

public class FileFeeder {
	private static final Log logger = LogFactory.getLog(FileFeeder.class);
	
	public static void writeFile(File file,String context){		
		try {
			FileOutputStream fp = new FileOutputStream(file, true);
			String line = context + "\r\n";
			fp.write(line.getBytes());
			fp.flush();
			fp.close();
		} catch (FileNotFoundException e) {
			logger.error("can not find file" + e.getMessage());
		} catch (IOException e) {
			logger.error("io exception when writeFile" + e.getMessage());
		}
	}
}
