package com.google.share.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

import org.apache.log4j.*;

/**
 * Ths FileUtil class is used to centralize common file functions
 * such as reading from a file or checking the file name.
 *
 * @author Ajay Sharma
 */
public class FileUtil {

	final static Logger log= Logger.getLogger(FileUtil.class);
	/**
	 * method to scanner of a given file path.
	 * @param inFile String.
	 * @return scanner for a given file.
	 */
	public static Scanner readFile(final String fileName){    

		if(FileUtil.isEmpty(fileName)){
			log.info("Given File Path is empty ");
			return null;
		}

		FileInputStream fInputStream = null;
		Scanner scanner = null;

		try {  
			fInputStream = new FileInputStream(fileName);
			scanner = new Scanner(fInputStream);
		} catch (FileNotFoundException e) {  
			log.info(e.getMessage());
		}  
		return scanner; 
	}
	/**
	 * method to check empty string
	 * @param string
	 * @return boolean
	 */
	public static boolean isEmpty(final String str)
	{
		if ((str == null) || (str.trim().length() == 0)) {
			return true;
		}
		return false;
	}

	/**
	 * method to validate and convert string as double
	 * @param string
	 * @return double
	 */
	public static Double parseDoubleWithDefault(final String s) {
		try {
			return Double.parseDouble(s);
		}
		catch (NumberFormatException e) {
			log.error("Corrupted file : Unable to convet " + e.toString());
			return 0.0;
		}
	}

}
