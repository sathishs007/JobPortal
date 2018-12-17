package com.megatech.utils;

import java.io.FileNotFoundException;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class MegatechResourceBundle {
	
	private static final String BUNDLE_NAME = "messages";
	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle
			.getBundle(BUNDLE_NAME);

	public static String getValue(String key) throws FileNotFoundException {
		try {
			return RESOURCE_BUNDLE.getString(key);

		} catch (MissingResourceException mex) {
			mex.printStackTrace();
			return "";
		} catch (Exception ex) {
			return "NOT FOUND";
		}
	}
}
