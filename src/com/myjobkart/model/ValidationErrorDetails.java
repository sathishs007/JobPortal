package com.myjobkart.model;

public class ValidationErrorDetails {

	private String field;
	private String issue;
	
	public String getField() {
		return field;
	}
	public void setField(String field) {
		this.field = field;
	}
	public String getIssue() {
		return issue;
	}
	public void setIssue(String issue) {
		this.issue = issue;
	}
	@Override
	public String toString() {
		return "Details [field=" + field + ", issue=" + issue + "]";
	}
	
	
}
