package com.ascepionpharm.lims.universal;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * FileFeeder: operate the general formatter about LIMS.
 * 
 * @author Li Jun Mulin
 * @version
 * 
 */

public class FormatterFeeder {
	private static final Log logger = LogFactory.getLog(FormatterFeeder.class);

	public static String validateNull(Object object) {
		if (object == null || object.equals("null")) {
			return "";
		} else {
			return object.toString();
		}
	}
}
