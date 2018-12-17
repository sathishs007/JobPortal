package com.myjobkart.gateway;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

public class MailAuthenticator extends Authenticator {

	String user;
	String pw;

	public MailAuthenticator(String username, String password) {
		super();
		this.user = username;
		this.pw = password;
	}

	@Override
	public PasswordAuthentication getPasswordAuthentication() {
		return new PasswordAuthentication(this.user, this.pw);
	}
}
