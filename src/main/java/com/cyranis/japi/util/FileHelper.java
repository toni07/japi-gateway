package com.cyranis.japi.util;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

/**
 * @author aepardeau on 17/11/2017.
 */
public class FileHelper {

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
	public static void move(InputStream in, Path target)
	{
		try {
			Files.copy(in, target, StandardCopyOption.REPLACE_EXISTING);
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * **************************************************************************************
	 * getters & setters
	 * **************************************************************************************
	 */
}
