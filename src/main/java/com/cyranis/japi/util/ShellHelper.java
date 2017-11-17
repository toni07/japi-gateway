package com.cyranis.japi.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * @author aepardeau on 17/11/2017.
 */
public class ShellHelper {

	/**
	 * **************************************************************************************
	 * attributes
	 * **************************************************************************************
	 */

	/**
	 * **************************************************************************************
	 * constructors
	 * **************************************************************************************
	 */

	/**
	 * **************************************************************************************
	 * methods
	 * **************************************************************************************
	 */
	public static String executeCommand(String command)
	{
		final StringBuffer output = new StringBuffer();
		Process p;
		try {
			p = Runtime.getRuntime().exec(command);
			p.waitFor();
			BufferedReader reader =
					new BufferedReader(new InputStreamReader(p.getInputStream()));

			String line = "";
			while ((line = reader.readLine())!= null) {
				output.append(line + "\n");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return output.toString();
	}

	/**
	 * **************************************************************************************
	 * getters & setters
	 * **************************************************************************************
	 */
}
