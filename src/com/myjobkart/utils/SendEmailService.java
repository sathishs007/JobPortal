package com.myjobkart.utils;

import javax.mail.MessagingException;

import com.myjobkart.model.EmailModel;

public interface SendEmailService {

	
	public String sendEmail(String emailTo, String subject, String message,EmailModel model) throws MessagingException;
	

	
	

}
