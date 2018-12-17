/**
 * 
 */
package com.myjobkart.utils;

import java.io.FileNotFoundException;

import com.myjobkart.service.JobtrolleyResourceBundle;

/**
 * @author Administrator
 * 
 */
public class EmailTemplates {

	public static String activationTemaplate(String name, String emailId)
			throws FileNotFoundException {
		/*final JobtrolleyResourceBundle jobtrolleyResourceBundle = null;*/
		String str =JobtrolleyResourceBundle.getValue("JobSeekerActivation");
		final String bodyContent = "Dear "
				+ name
				+ ","
				+ "\n\n\tClick here to Confirmation Your Registration in Myjobkart:\n"
				+ str
				+ emailId
				+ "\n"
				+ "\n\n\n\n\nRegards,\n Customer Support,\nMyjobkart Consultancy Services.";
		return bodyContent;
	}

}
