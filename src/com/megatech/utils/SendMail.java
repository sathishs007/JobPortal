package com.megatech.utils;

import java.util.Properties;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendMail {

	public String send(String toaddress, String subject, String bodyContent) {
		String navigation = null;
		try {
			String host = "mail.scubetechnologies.co.in";
			String from = "enquiry@scubetechnologies.co.in";
			String pass = "scube123";
			Properties props = System.getProperties();
			// props.put("mail.smtp.starttls.enable", "true");
			props.put("mail.smtp.host", host);
			props.put("mail.smtp.user", from);
			props.put("mail.smtp.password", pass);
			props.put("mail.smtp.port", "25");
			props.put("mail.smtp.auth", "true");
			props.put("mail.debug", "true");
			Session session = Session.getInstance(props, new MailAuthenticator(
					"enquiry@scubetechnologies.co.in", "scube123"));
			MimeMessage message = new MimeMessage(session);
			Address fromAddress = new InternetAddress(from);
			Address toAddress = new InternetAddress(toaddress);
			message.setRecipient(Message.RecipientType.TO, toAddress);
			navigation = sendMail(message, subject, bodyContent, session, host,
					from, pass, fromAddress);
			return navigation;
		} catch (Exception ex) {
			// ex.printStackTrace();
		}
		return "failure";
	}

	public String sendMail(MimeMessage message, String subject,
			String bodyContent, Session session, String host, String from,
			String pass, Address fromAddress) {
		try {
			message.setFrom(fromAddress);
			message.setSubject(subject);
			message.setText(bodyContent);
			Transport transport = session.getTransport("smtp");
			transport.connect(host, from, pass);
			message.saveChanges();
			Transport.send(message);
			transport.close();
			return "success";
		} catch (Exception e) {
			// e.printStackTrace();
		}
		return "failure";
	}

	public static void main(String[] args) {
		SendMail sm = new SendMail();

		sm.send("hr@scubetechnologies.co.in", "Hello", "Test Mail");

	}
}
