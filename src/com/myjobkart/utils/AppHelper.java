package com.myjobkart.utils;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.myjobkart.bo.BookingBO;
import com.paypal.api.payments.Amount;
import com.paypal.api.payments.Details;
import com.paypal.api.payments.Payer;
import com.paypal.api.payments.Payment;
import com.paypal.api.payments.RedirectUrls;
import com.paypal.api.payments.Transaction;
import com.paypal.core.rest.APIContext;
import com.paypal.core.rest.PayPalRESTException;

public class AppHelper {
	/**
	 * Retrieve Credit card details with creditCardId
	 * 
	 * @param creditCardId
	 * @return
	 * @throws PayPalRESTException
	 */
	/*
	 * public static CreditCardDetail getCreditCardDetail(String creditCardId)
	 * throws PayPalRESTException { // Get Credit Card detail String accessToken
	 * = AccessTokenGenerator.getAccessToken(); CreditCard creditcard =
	 * CreditCard.get(accessToken, creditCardId); CreditCardDetail cardDetail =
	 * new CreditCardDetail(); cardDetail.setCvv(creditcard.getCvv2());
	 * cardDetail.setNumber(creditcard.getNumber());
	 * cardDetail.setType(creditcard.getType());
	 * cardDetail.setExpMonth(Integer.toString(creditcard.getExpireMonth()));
	 * cardDetail.setExpYear(Integer.toString(creditcard.getExpireYear()));
	 * 
	 * return cardDetail; }
	 */

	/**
	 * Creates credit card
	 * 
	 * @param request
	 * @return
	 * @throws PayPalRESTException
	 */
	/*
	 * public static CreditCard createCreditCard(HttpServletRequest request)
	 * throws PayPalRESTException {
	 * 
	 * String accessToken = AccessTokenGenerator.getAccessToken(); CreditCard
	 * creditCard = new CreditCard();
	 * creditCard.setExpireMonth(Integer.parseInt(
	 * request.getParameter("expire_month").trim()));
	 * creditCard.setExpireYear(Integer
	 * .parseInt(request.getParameter("expire_year").trim()));
	 * creditCard.setNumber(request.getParameter("credit_card_number").trim());
	 * creditCard.setType(request.getParameter("credit_card_type").trim());
	 * creditCard.setCvv2(request.getParameter("credit_card_cvv2").trim());
	 * 
	 * return creditCard.create(accessToken); }
	 */

	/**
	 * Creates payment using creditCardId
	 * 
	 * @param creditCardId
	 * @param order
	 * @return
	 * @throws PayPalRESTException
	 * @throws FileNotFoundException
	 */
	public static Payment createPayment(BookingBO orderBO)
			throws PayPalRESTException, FileNotFoundException {

		Payment payment = new Payment();

		// Create a payment object using PayPal as a payment method
		// This will involve PayPal redirection , where user authorization is
		// required
		if (orderBO.getPaymentMode().equalsIgnoreCase("paypal")) {
			Details amountDetails = new Details();
			amountDetails.setShipping(orderBO.getShipping());
			amountDetails.setSubtotal(orderBO.getOrderAmount());
			amountDetails.setTax(orderBO.getTax());

			Amount amount = new Amount();
			amount.setCurrency(orderBO.getCurrency());
			Double total = Double.parseDouble(orderBO.getTax())+ Double.parseDouble(orderBO.getShipping())+ Double.parseDouble(orderBO.getOrderAmount());
			amount.setTotal(String.format("%.2f", total));
			amount.setDetails(amountDetails);

			RedirectUrls redirectUrls = new RedirectUrls();
			redirectUrls.setCancelUrl(orderBO.getCancelUrl());
			redirectUrls.setReturnUrl(orderBO.getReturnUrl());

			Transaction transaction = new Transaction();
			transaction.setAmount(amount);
			List<Transaction> transactions = new ArrayList<Transaction>();
			transactions.add(transaction);

			Payer payer = new Payer();
			payer.setPaymentMethod(orderBO.getPaymentMode());

			payment.setIntent(orderBO.getPaymentIntent());
			payment.setPayer(payer);
			payment.setRedirectUrls(redirectUrls);
			payment.setTransactions(transactions);
		}

		// set access token

		Map<String, String> configurationMap = new HashMap<String, String>();
		configurationMap.put("oauth.EndPoint",
				"https://api-3t.sandbox.paypal.com/");
		configurationMap.put("service.EndPoint",
				"https://api-3t.sandbox.paypal.com/");
		configurationMap.put("mode", "sandbox");
		String accessToken = AccessTokenGenerator.getAccessToken();
		String requestId = UUID.randomUUID().toString();
		APIContext apiContext = new APIContext(accessToken, requestId);
		apiContext.setConfigurationMap(configurationMap);
		return payment.create(apiContext);
	}
}
