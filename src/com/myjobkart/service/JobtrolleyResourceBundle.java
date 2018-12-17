package com.myjobkart.service;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.MissingResourceException;
import java.util.Properties;
import java.util.ResourceBundle;

public class JobtrolleyResourceBundle {
	
	/*public static String bundle(String str){ 
		 String path = null;
	Properties properties = new Properties();
	try {
		
	    properties.load(new FileInputStream("/usr/local/myjobkart/configration.properties"));
	     path = properties.getProperty(str);
	} catch (IOException e) {
	}
	return path;
	
	}*/
	
	
	private static final String BUNDLE_NAME = "configration";
	//private static final String DROPDOWN_NAME = "Dropdown";
	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle
			.getBundle(JobtrolleyResourceBundle.BUNDLE_NAME);

	public static String getValue(String key) throws FileNotFoundException {
		try {
			return JobtrolleyResourceBundle.RESOURCE_BUNDLE.getString(key);

		} catch (final MissingResourceException mex) {
			mex.printStackTrace();
			return "";
		} catch (final Exception ex) {
			return "NOT FOUND";
		}
	}
	
	
	
	private static final String DROPDOWN_NAME = "Dropdown";
	private static final ResourceBundle RESOURCE_BUNDLE_DROPDOWN_NAME = ResourceBundle
			.getBundle(JobtrolleyResourceBundle.DROPDOWN_NAME);

	
	public static String getDropdown(String key) throws FileNotFoundException {
		try {
			return JobtrolleyResourceBundle.RESOURCE_BUNDLE_DROPDOWN_NAME.getString(key);

		} catch (final MissingResourceException mex) {
			mex.printStackTrace();
			return "";
		} catch (final Exception ex) {
			return "NOT FOUND";
		}
	}
	
	
}