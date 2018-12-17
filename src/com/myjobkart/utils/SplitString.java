package com.myjobkart.utils;

import java.util.ArrayList;
import java.util.List;

public class SplitString {

	private List<String> val;

	public List<String> strSplit(String str) {
		try {
			final List<String> val = new ArrayList<String>();
			int i = 1;
			for (final String retval : str.split("-")) {
				if (i > 1) {
					val.add("-" + retval);
				} else {
					val.add(retval);
				}
				i++;
			}
			return val;
		} catch (final Exception e) {
			// e.printStackTrace();
		}
		return this.val;
	}

}
