package com.myjobkart.gateway;

public class SendEmailClient {

	public static void main(String[] args) {

		final String to = "hr@scubetechnologies.co.in";
		final String subject = "Test";
		final String message = "A test message";
		final SendMail sendMail = new SendMail();
		sendMail.send(to, subject, message);
	}
}
