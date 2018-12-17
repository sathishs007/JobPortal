package com.myjobkart.utils;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.log4j.Logger;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;
import org.springframework.ui.velocity.VelocityEngineUtils;

import com.myjobkart.utils.EmailCodes;
import com.myjobkart.bo.BookingBO;
import com.myjobkart.bo.EntityBO;
import com.myjobkart.bo.JobAlertBO;
import com.myjobkart.bo.JobPostBO;
import com.myjobkart.model.EmailModel;

@Service("emailManager")
public class SendEmailServiceImpl implements SendEmailService {

	@Autowired
	private JavaMailSender mailSender;

	@Autowired
	private VelocityEngine velocityEngine;

	private static final Logger LOGGER = Logger
			.getLogger(SendEmailServiceImpl.class);

	Map<String, Object> modelValue = null;

	@Override
	public String sendEmail(final String toEmail, final String subjects,
			final String messageContent, final EmailModel model)
			throws MessagingException {
		LOGGER.info("Confirmation email sent with out attachment:" + toEmail);

		MimeMessagePreparator preparator = new MimeMessagePreparator() {
			public void prepare(MimeMessage mimeMessage) throws Exception {
				MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
				message.setTo(toEmail);
				message.setFrom("enquiry@scubetechnologies.co.in");
				message.setSubject(subjects);
				message.setSentDate(new Date());
				String text = getTemplate(messageContent, model);
				message.setText(text, true);
			}
		};
		try {
			mailSender.send(preparator);
		} catch (Exception e) {
			return "failed";
		}
		return "success";
	}
	
	public boolean sendMailAlert(final String toaddress , final String subject,
			final String bodyContent, final EmailModel model ) {
		LOGGER.info("Confirmation email sent with out attachment:" + toaddress);

		MimeMessagePreparator preparator = new MimeMessagePreparator() {
			public void prepare(MimeMessage mimeMessage) throws Exception {
				MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
				message.setTo(toaddress);
	            message.setFrom("enquiry@scubetechnologies.co.in");
				message.setSentDate(new Date());
				message.setSubject(subject);
				String text = getTemplate(bodyContent, model);
				message.setText(text, true);
			}

			

		};
		try {

			mailSender.send(preparator);
		} catch (Exception e) {

			e.printStackTrace();

			return false;
		}
		return true;
	}
	
	
	public String sendProductEmail(final BookingBO model)
			 {
		LOGGER.info("Confirmation email sent with out attachment:" + model.getEmployerEmail());

		MimeMessagePreparator preparator = new MimeMessagePreparator() {
			public void prepare(MimeMessage mimeMessage) throws Exception {
				MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
				message.setTo(model.getEmployerEmail());
				message.setFrom("enquiry@scubetechnologies.co.in");
				message.setSubject(model.getEmailSubject());
				message.setSentDate(new Date());
				String messageContent = model.getEmailBodyCondent();
				String text = getproductTemplate(messageContent, model);
				message.setText(text, true);
			}
		};
		try {
			mailSender.send(preparator);
		} catch (Exception e) {
			return "failed";
		}
		return "success";
	}
	
	private String getproductTemplate(String bodyContent, BookingBO model) {
		String templateContent = null;
		// TODO Auto-generated method stub
		if (EmailCodes.PAYMENT_STATUS.endsWith(bodyContent)) {
			modelValue = new HashMap<String, Object>();
			modelValue.put("pType", model.getProductType());
			modelValue.put("pAmount", model.getEmployerName());
			modelValue.put("Pmonth", model.getEmployerEmail());
			modelValue.put("pDate", model.getTransactionDate());
			modelValue.put("pNo", model.getTransactionId());
			modelValue.put("pStatus", model.getPaymentStatus());
			templateContent = VelocityEngineUtils.mergeTemplateIntoString(
					velocityEngine, "velocity/productOrderSuccess.vm",
					"UTF-8", modelValue);
		}
		return templateContent;
	}

	private String getTemplate(String bodyContent, EmailModel model) {
		String templateContent = null;
		// TODO Auto-generated method stub
		if (EmailCodes.ACTIVE_REQUEST.endsWith(bodyContent)) {
			modelValue = new HashMap<String, Object>();
			modelValue.put("url", model.getUrl());
			modelValue.put("fname", model.getFirstname());
			modelValue.put("email", model.getEmail());
			modelValue.put("web", model.getWebSite());
			templateContent = VelocityEngineUtils.mergeTemplateIntoString(
					velocityEngine, "velocity/accountActivationSuccess.vm",
					"UTF-8", modelValue);
		}
		
		if(EmailCodes.EMPLOYER_ALERT.endsWith(bodyContent)) {
			modelValue = new HashMap<String, Object>();
			modelValue.put("url",model.getUrl());
			modelValue.put("joburl", model.getJobUrl());
			String[] tempJobtitle = model.getJobtitle().split(",");
			modelValue.put("Jobtitle",tempJobtitle);
			modelValue.put("firstname", model.getFirstname());
			modelValue.put("emailId", model.getEmailId());
			modelValue.put("keyskills", model.getKeySkills());
			
			templateContent = VelocityEngineUtils.mergeTemplateIntoString(
					velocityEngine, "velocity/employerAlert.vm", "UTF-8",
					modelValue);
		}
		
	
		if (EmailCodes.PASSWORD_CHANGE.endsWith(bodyContent)) {
			modelValue = new HashMap<String, Object>();
			modelValue.put("url", model.getUrl());
			// modelValue.put("fname", model.getFirstname());
			modelValue.put("email", model.getEmail());
			modelValue.put("web", model.getWebSite());
			templateContent = VelocityEngineUtils.mergeTemplateIntoString(
					velocityEngine, "velocity/changepassword.vm", "UTF-8",
					modelValue);
		}
		if (EmailCodes.ACTIVE_CONTACT.endsWith(bodyContent)) {
			modelValue = new HashMap<String, Object>();
			modelValue.put("phoneno", model.getPhoneno());
			modelValue.put("fname", model.getFirstname());
			modelValue.put("email", model.getEmail());
			modelValue.put("subject", model.getSubject());
			modelValue.put("message", model.getMessage());
			templateContent = VelocityEngineUtils.mergeTemplateIntoString(
					velocityEngine, "velocity/Contact.vm", "UTF-8", modelValue);
		}
		if (EmailCodes.ACTIVE_FEEDBACK.endsWith(bodyContent)) {
			modelValue = new HashMap<String, Object>();
			modelValue.put("web", model.getWebSite());
			modelValue.put("phoneno", model.getPhoneno());
			modelValue.put("fname", model.getFirstname());
			modelValue.put("email", model.getEmail());
			modelValue.put("subject", model.getSubject());
			modelValue.put("area", model.getArea());
			modelValue.put("detail", model.getDetails());
			templateContent = VelocityEngineUtils
					.mergeTemplateIntoString(velocityEngine,
							"velocity/Feedback.vm", "UTF-8", modelValue);
		}

		if (EmailCodes.SHORTLIST.endsWith(bodyContent)) {

			modelValue = new HashMap<String, Object>();
			modelValue.put("fname", model.getFirstname());
			modelValue.put("companyname", model.getCompanyName());
			modelValue.put("jobtitle", model.getJobtitle());
			modelValue.put("contactemail", model.getContactEmail());
			modelValue.put("salary", model.getSalary());
			modelValue.put("location", model.getPreferedlocation());

			templateContent = VelocityEngineUtils.mergeTemplateIntoString(
					velocityEngine, "velocity/ShortList.vm", "UTF-8",
					modelValue);
		}

		if (EmailCodes.JOBALERT_CREATED.equalsIgnoreCase(bodyContent)) {
			modelValue = new HashMap<String, Object>();
			modelValue.put("alertName", model.getAlertName());
			modelValue.put("keySkills", model.getKeySkills());
			modelValue.put("preferredLocation", model.getPreferedlocation());
			modelValue.put("jobType", model.getJobType());
			modelValue.put("role", model.getRole());
			modelValue.put("experienceInYear", model.getExperienceInYear());
			modelValue.put("preferredIndustry", model.getPreferredIndustry());
			modelValue.put("emailId", model.getEmailId());
			modelValue.put("contactemail", model.getContactEmail());
			templateContent = VelocityEngineUtils.mergeTemplateIntoString(
					velocityEngine, "velocity/jobAlertCreation.vm", "UTF-8",
					modelValue);
		}
		
		if(EmailCodes.JOBALERT_CREATION.equalsIgnoreCase(bodyContent)){
			modelValue = new HashMap<String, Object>();
			modelValue.put("firstName",model.getFirstname());
			modelValue.put("alertName",model.getAlertName());
			modelValue.put("keySkills",model.getKeySkills());
			modelValue.put("preferredLocation",model.getPreferedlocation());
			modelValue.put("jobType",model.getJobType());
			modelValue.put("role",model.getRole());
			modelValue.put("experienceInYear",model.getExperienceInYear());
			modelValue.put("preferredIndustry",model.getPreferredIndustry());
			modelValue.put("emailId",model.getEmailId());
			modelValue.put("contactemail",model.getContactEmail());
			templateContent = VelocityEngineUtils.mergeTemplateIntoString(
					velocityEngine, "velocity/JobAlert.vm", "UTF-8",
					modelValue);
		}

		return templateContent;
}

	
	public boolean sendMailAlert(final String toaddress, final String subject,
			final String bodyContent,final JobPostBO volist) {
		LOGGER.info("Confirmation email sent with out attachment:" + toaddress);

		MimeMessagePreparator preparator = new MimeMessagePreparator() {
			public void prepare(MimeMessage mimeMessage) throws Exception {
				MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
				message.setTo(toaddress);
				message.setFrom("enquiry@scubetechnologies.co.in");
				message.setSentDate(new Date());
				message.setSubject(subject);
				String text = getTemplate(bodyContent, volist);
				message.setText(text, true);
			}

		};
		try {

			mailSender.send(preparator);
		} catch (Exception e) {

			e.printStackTrace();

			return false;
		}
		return true;
	}

	protected String getTemplate(String bodyContent, JobPostBO volist) {
		String templateContent=null;
		if(EmailCodes.EMPLOYER_ALERT.endsWith(bodyContent))
		{
			System.out.println("volist------------"+volist);

			modelValue=new HashMap<String,Object>();
			modelValue.put("emailid",volist.getEmailId());
			templateContent = VelocityEngineUtils.mergeTemplateIntoString(
					velocityEngine, "velocity/employerAlert.vm", "UTF-8",
					modelValue);
		}

		return templateContent;


	}
	
	
	public boolean sendMailid(final String toaddress, final String subject,
			final String bodyContent,final EntityBO bolist) {
		LOGGER.info("Confirmation email sent with out attachment:" + toaddress);

		MimeMessagePreparator preparator = new MimeMessagePreparator() {
			public void prepare(MimeMessage mimeMessage) throws Exception {
				MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
				message.setTo(toaddress);
				message.setFrom("enquiry@scubetechnologies.co.in");
				message.setSentDate(new Date());
				message.setSubject(subject);
				String text = getTemplate(bodyContent, bolist);
				message.setText(text, true);
			}

	};
	try {
			
			mailSender.send(preparator);
		} catch (Exception e) {

			e.printStackTrace();
		
		return false;
	}
	return true;
	}
	@SuppressWarnings("deprecation")
	private String getTemplate(String bodyContent,EntityBO bolist) 
	{
		String templateContent=null;
		if(EmailCodes.EMPLOYER_INVITATION.endsWith(bodyContent))
		{
			modelValue=new HashMap<String,Object>();
			modelValue.put("emailid", bolist.getEmail());
			templateContent = VelocityEngineUtils.mergeTemplateIntoString(
					velocityEngine, "velocity/invitation.vm", "UTF-8",
					modelValue);
			}
		
		return templateContent;
	}
}