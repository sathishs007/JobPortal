package com.myjobkart.utils;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.paypal.core.rest.OAuthTokenCredential;
import com.paypal.core.rest.PayPalRESTException;

public class AccessTokenGenerator {

	private static final Logger LOGGER = Logger
			.getLogger(AccessTokenGenerator.class);

	private static String accessToken = null;

	private static String PAYPAL_SERVER_URL;
	private static String PAYPAL_MODE;
	private static String PAYPAL_CLIENT_ID;
	private static String PAYPAL_CLIENT_SECRET;

	static {
		try {
			PAYPAL_SERVER_URL = MyjobkartResourceBundle
					.getValue("paypal.service.endPoint.url");
			PAYPAL_MODE = MyjobkartResourceBundle.getValue("paypal.service.mode");
			PAYPAL_CLIENT_SECRET = MyjobkartResourceBundle
					.getValue("paypal.clientSecret");
			PAYPAL_CLIENT_ID = MyjobkartResourceBundle
					.getValue("paypal.clientID");
		} catch (FileNotFoundException e) {
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("AccessTokenGenerator Static Block has failed:"
						+ e.getMessage());
			}
			LOGGER.info("AccessTokenGenerator Static Block has failed:"
					+ e.getMessage());
		}
	}

	public static String getAccessToken() throws PayPalRESTException,
			FileNotFoundException {
		// ###AccessToken
		// Retrieve the access token from
		// OAuthTokenCredential by passing in
		// ClientID and ClientSecret
		Map<String, String> configurationMap = new HashMap<String, String>();
		configurationMap.put("oauth.EndPoint", PAYPAL_SERVER_URL);
		configurationMap.put("service.EndPoint", PAYPAL_SERVER_URL);
		configurationMap.put("mode", PAYPAL_MODE);
		if (accessToken == null) {

			// ClientID and ClientSecret retrieved from configuration
			String clientID = PAYPAL_CLIENT_ID;
			String clientSecret = PAYPAL_CLIENT_SECRET;
			OAuthTokenCredential merchantTokenCredential = new OAuthTokenCredential(
					clientID, clientSecret, configurationMap);
			accessToken = merchantTokenCredential.getAccessToken();
		}
		return accessToken;
	}
}
