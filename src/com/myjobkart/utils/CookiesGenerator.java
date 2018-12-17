/**
 * 
 */
package com.myjobkart.utils;

import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.myjobkart.bo.AdminLoginBO;
import com.myjobkart.bo.EmployerLoginBO;
import com.myjobkart.bo.JobSeekerLoginBO;

public class CookiesGenerator {

	public void addCookies(HttpServletRequest request,
			HttpServletResponse response, Map<String, String> cookiesObject,
			String type, boolean rememberMe) {

		if (type.equalsIgnoreCase("jobSeeker")) {
			if (rememberMe) {
				Cookie j_EmailCookie = new Cookie("j_cookieEmail",
						cookiesObject.get("email"));
				Cookie j_PasswordCookie = new Cookie("j_cookiePass",
						cookiesObject.get("password"));
				Cookie j_RememerCookie = new Cookie("j_cookieRemember", "1");
				j_EmailCookie.setMaxAge(60 * 60 * 24 * 15);// 15 days
				j_PasswordCookie.setMaxAge(60 * 60 * 24 * 15);
				j_RememerCookie.setMaxAge(60 * 60 * 24 * 15);
				response.addCookie(j_EmailCookie);
				response.addCookie(j_PasswordCookie);
				response.addCookie(j_RememerCookie);
			} else {
				Cookie j_EmailCookie = new Cookie("j_cookieEmail", null);
				Cookie j_PasswordCookie = new Cookie("j_cookiePass", null);
				Cookie j_RememerCookie = new Cookie("j_cookieRemember", null);
				j_EmailCookie.setMaxAge(0);
				j_PasswordCookie.setMaxAge(0);
				j_RememerCookie.setMaxAge(0);
				response.addCookie(j_EmailCookie);
				response.addCookie(j_PasswordCookie);
				response.addCookie(j_RememerCookie);
			}
		}
		if (type.equalsIgnoreCase("employer")) {
			if (rememberMe) {
				Cookie e_EmailCookie = new Cookie("e_cookieEmail",
						cookiesObject.get("email"));
				Cookie e_PasswordCookie = new Cookie("e_cookiePass",
						cookiesObject.get("password"));
				Cookie e_RememerCookie = new Cookie("e_cookieRemember", "1");
				e_EmailCookie.setMaxAge(60 * 60 * 24 * 15);// 15 days
				e_PasswordCookie.setMaxAge(60 * 60 * 24 * 15);
				e_RememerCookie.setMaxAge(60 * 60 * 24 * 15);
				response.addCookie(e_EmailCookie);
				response.addCookie(e_PasswordCookie);
				response.addCookie(e_RememerCookie);
			} else {
				Cookie e_EmailCookie = new Cookie("e_cookieEmail", null);
				Cookie e_PasswordCookie = new Cookie("e_cookiePass", null);
				Cookie e_RememerCookie = new Cookie("e_cookieRemember", null);
				e_EmailCookie.setMaxAge(0);
				e_PasswordCookie.setMaxAge(0);
				e_RememerCookie.setMaxAge(0);
				response.addCookie(e_EmailCookie);
				response.addCookie(e_PasswordCookie);
				response.addCookie(e_RememerCookie);
			}
		}
		if (type.equalsIgnoreCase("admin")) {
			if (rememberMe) {
				Cookie a_EmailCookie = new Cookie("a_cookieEmail",
						cookiesObject.get("email"));
				Cookie a_PasswordCookie = new Cookie("a_cookiePass",
						cookiesObject.get("password"));
				Cookie a_RememerCookie = new Cookie("a_cookieRemember", "1");
				a_EmailCookie.setMaxAge(60 * 60 * 24 * 15);// 15 days
				a_PasswordCookie.setMaxAge(60 * 60 * 24 * 15);
				a_RememerCookie.setMaxAge(60 * 60 * 24 * 15);
				response.addCookie(a_EmailCookie);
				response.addCookie(a_PasswordCookie);
				response.addCookie(a_RememerCookie);
			} else {
				Cookie a_EmailCookie = new Cookie("a_cookieEmail", null);
				Cookie a_PasswordCookie = new Cookie("a_cookiePass", null);
				Cookie a_RememerCookie = new Cookie("a_cookieRemember", null);
				a_EmailCookie.setMaxAge(0);
				a_PasswordCookie.setMaxAge(0);
				a_RememerCookie.setMaxAge(0);
				response.addCookie(a_EmailCookie);
				response.addCookie(a_PasswordCookie);
				response.addCookie(a_RememerCookie);
			}
		}
	}

	public Object cookiesVerifications(HttpServletRequest request,Object formObject, String type) {

		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			if (type.equalsIgnoreCase("jobSeeker")) {
				JobSeekerLoginBO jobSeekerLoginBO = (JobSeekerLoginBO) formObject;
				for (Cookie cookie : cookies) {
					if (cookie.getName().equals("j_cookieEmail")) {
						jobSeekerLoginBO.setEmailAddress(cookie.getValue());
					}
					if (cookie.getName().equals("j_cookiePass")) {
						jobSeekerLoginBO.setPassword(cookie.getValue());
					}
					if (cookie.getName().equals("j_cookieRemember")) {
						jobSeekerLoginBO.setRememberMe(true);
					}
				}
				return jobSeekerLoginBO;
			}
			if (type.equalsIgnoreCase("employer")) {
				EmployerLoginBO employerLoginBO = (EmployerLoginBO) formObject;
				for (Cookie cookie : cookies) {
					if (cookie.getName().equals("e_cookieEmail")) {
						employerLoginBO.setEmailAddress(cookie.getValue());
					}
					if (cookie.getName().equals("e_cookiePass")) {
						employerLoginBO.setPassword(cookie.getValue());
					}
					if (cookie.getName().equals("e_cookieRemember")) {
						employerLoginBO.setRememberMe(true);
					}
				}
				return employerLoginBO;
			}
			if (type.equalsIgnoreCase("admin")) {
				AdminLoginBO adminLoginBO = (AdminLoginBO) formObject;
				for (Cookie cookie : cookies) {
					if (cookie.getName().equals("a_cookieEmail")) {
						adminLoginBO.setEmailAddress(cookie.getValue());
					}
					if (cookie.getName().equals("a_cookiePass")) {
						adminLoginBO.setPassword(cookie.getValue());
					}
					if (cookie.getName().equals("a_cookieRemember")) {
						adminLoginBO.setRememberMe(true);
					}
				}
				return adminLoginBO;
			}
		}
		return formObject;
	}
}
