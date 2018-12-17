package com.myjobkart.utils;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.myjobkart.model.ErrorMessage;
import com.myjobkart.model.ValidationError;
import com.paypal.core.rest.PayPalRESTException;

public class WebHelper {
	
	/*
	 * Parse PayPal response error 
	 */
	public static ValidationError parseJsonErrorMessage(String jsonErrorMsg) {
		Gson gson = new Gson();
		return gson.fromJson(jsonErrorMsg.substring(jsonErrorMsg.indexOf('{')),
				ValidationError.class);
	}

	/*
	 * Forward user to specified application resource
	 */
	public static void forward(HttpServletRequest request,
			HttpServletResponse response, String resource)
			throws ServletException, IOException {

		request.getRequestDispatcher(resource).forward(request, response);
	}
	
	/*
	 * Redirect user to specified application resource
	 */
	public static void redirect(HttpServletRequest request,
			HttpServletResponse response, String resource)
			throws ServletException, IOException {
		response.sendRedirect(getContextPath(request) + "/signin");
	}

	/*
	 * Get server context path
	 */
	public static String getContextPath(HttpServletRequest request) {
		String scheme = request.getScheme();
		String host = request.getServerName();
		int port = request.getServerPort();
		String contextpath = request.getContextPath();
		return (scheme + "://" + host + ":" + port + contextpath);
	}

	/*
	 * check session validity
	 */
	public static boolean checkSessionValidity(HttpServletRequest request) {
		boolean valid = true;
		if (!request.getRequestURI().contains("signin")
				&& !request.getRequestURI().contains("signup")) {
			HttpSession session = request.getSession(false);
			if (session != null) {
				valid = (Boolean) session.getAttribute(
						"isSessionActive") != null ? (Boolean) request.getSession()
						.getAttribute("isSessionActive") : false;
			} else {
				valid = false;
			}
		}
		return valid;
	}
	
	/*
	 * Forms error message
	 */
	public static void formErrorMessage(HttpServletRequest request,
			PayPalRESTException pex) {
		ValidationError error = parseJsonErrorMessage(pex.getMessage());
		ErrorMessage errorMsg = new ErrorMessage();
		errorMsg.addList(error.getValidationErrorList());
		request.setAttribute("error", errorMsg);
	}

}
