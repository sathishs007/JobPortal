package com.myjobkart.bo;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

public class BookingBO extends BaseBO{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private long custId;
	
	private String transactionId;
	
	private long pkgId;
	
	private String transactionDate;
	
	private String paymentStatus;
	
	private String paymentMode;
	
	private String orderAmount;
	
	private String currency;
	
	private String returnUrl;
	
	private String cancelUrl;
	
	private String shipping;
	
	private String tax;
	
	private String paymentIntent;
	
	private String confirmationNo;
	
	
	private long travelerId;	
	
	private String packageName;	
	
	private String description;
	
	private int sNO;
	
	private BigDecimal packagePrice;	
	
	private long imageId;
	
	private String imageName;
	
	private String bkDate;
	
	private String bookingStatus;
	
	private List<CommonsMultipartFile> files;
	
	private String fileName;
	
	private String userType;
	
	private String selectProduct;
	
	private String productType;
	
	private int totalMonth;
	
	private EmployerBO employerRegistration;
	
	private String message;
	
	private String employerEmail;
	
	private String webSite;
	
	private String employerName;
	
	private String emailSubject;
	
	private String emailBodyCondent;

	/**
	 * @return the custId
	 */
	public long getCustId() {
		return custId;
	}

	/**
	 * @param custId the custId to set
	 */
	public void setCustId(long custId) {
		this.custId = custId;
	}

	/**
	 * @return the transactionId
	 */
	public String getTransactionId() {
		return transactionId;
	}

	/**
	 * @param transactionId the transactionId to set
	 */
	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	/**
	 * @return the pkgId
	 */
	public long getPkgId() {
		return pkgId;
	}

	/**
	 * @param pkgId the pkgId to set
	 */
	public void setPkgId(long pkgId) {
		this.pkgId = pkgId;
	}

	/**
	 * @return the transactionDate
	 */
	public String getTransactionDate() {
		return transactionDate;
	}

	/**
	 * @param transactionDate the transactionDate to set
	 */
	public void setTransactionDate(String transactionDate) {
		this.transactionDate = transactionDate;
	}

	/**
	 * @return the paymentStatus
	 */
	public String getPaymentStatus() {
		return paymentStatus;
	}

	/**
	 * @param paymentStatus the paymentStatus to set
	 */
	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	/**
	 * @return the paymentMode
	 */
	public String getPaymentMode() {
		return paymentMode;
	}

	/**
	 * @param paymentMode the paymentMode to set
	 */
	public void setPaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;
	}

	/**
	 * @return the orderAmount
	 */
	public String getOrderAmount() {
		return orderAmount;
	}

	/**
	 * @param orderAmount the orderAmount to set
	 */
	public void setOrderAmount(String orderAmount) {
		this.orderAmount = orderAmount;
	}

	/**
	 * @return the currency
	 */
	public String getCurrency() {
		return currency;
	}

	/**
	 * @param currency the currency to set
	 */
	public void setCurrency(String currency) {
		this.currency = currency;
	}

	/**
	 * @return the returnUrl
	 */
	public String getReturnUrl() {
		return returnUrl;
	}

	/**
	 * @param returnUrl the returnUrl to set
	 */
	public void setReturnUrl(String returnUrl) {
		this.returnUrl = returnUrl;
	}

	/**
	 * @return the cancelUrl
	 */
	public String getCancelUrl() {
		return cancelUrl;
	}

	/**
	 * @param cancelUrl the cancelUrl to set
	 */
	public void setCancelUrl(String cancelUrl) {
		this.cancelUrl = cancelUrl;
	}

	/**
	 * @return the shipping
	 */
	public String getShipping() {
		return shipping;
	}

	/**
	 * @param shipping the shipping to set
	 */
	public void setShipping(String shipping) {
		this.shipping = shipping;
	}

	/**
	 * @return the tax
	 */
	public String getTax() {
		return tax;
	}

	/**
	 * @param tax the tax to set
	 */
	public void setTax(String tax) {
		this.tax = tax;
	}

	/**
	 * @return the paymentIntent
	 */
	public String getPaymentIntent() {
		return paymentIntent;
	}

	/**
	 * @param paymentIntent the paymentIntent to set
	 */
	public void setPaymentIntent(String paymentIntent) {
		this.paymentIntent = paymentIntent;
	}

	/**
	 * @return the travelerBOList
	 */
	

	/**
	 * @return the travelerId
	 */
	public long getTravelerId() {
		return travelerId;
	}

	/**
	 * @param travelerId the travelerId to set
	 */
	public void setTravelerId(long travelerId) {
		this.travelerId = travelerId;
	}

	/**
	 * @return the packageName
	 */
	public String getPackageName() {
		return packageName;
	}

	/**
	 * @param packageName the packageName to set
	 */
	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the sNO
	 */
	public int getsNO() {
		return sNO;
	}

	/**
	 * @param sNO the sNO to set
	 */
	public void setsNO(int sNO) {
		this.sNO = sNO;
	}

	/**
	 * @return the packagePrice
	 */
	public BigDecimal getPackagePrice() {
		return packagePrice;
	}

	/**
	 * @param packagePrice the packagePrice to set
	 */
	public void setPackagePrice(BigDecimal packagePrice) {
		this.packagePrice = packagePrice;
	}

	/**
	 * @return the imageId
	 */
	public long getImageId() {
		return imageId;
	}

	/**
	 * @param imageId the imageId to set
	 */
	public void setImageId(long imageId) {
		this.imageId = imageId;
	}

	/**
	 * @return the imageName
	 */
	public String getImageName() {
		return imageName;
	}

	/**
	 * @param imageName the imageName to set
	 */
	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	/**
	 * @return the bkDate
	 */
	public String getBkDate() {
		return bkDate;
	}

	/**
	 * @param bkDate the bkDate to set
	 */
	public void setBkDate(String bkDate) {
		this.bkDate = bkDate;
	}

	/**
	 * @return the bookingStatus
	 */
	public String getBookingStatus() {
		return bookingStatus;
	}

	/**
	 * @param bookingStatus the bookingStatus to set
	 */
	public void setBookingStatus(String bookingStatus) {
		this.bookingStatus = bookingStatus;
	}

	/**
	 * @return the files
	 */
	public List<CommonsMultipartFile> getFiles() {
		return files;
	}

	/**
	 * @param files the files to set
	 */
	public void setFiles(List<CommonsMultipartFile> files) {
		this.files = files;
	}

	/**
	 * @return the fileName
	 */
	public String getFileName() {
		return fileName;
	}

	/**
	 * @param fileName the fileName to set
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	/**
	 * @return the confirmationNo
	 */
	public String getConfirmationNo() {
		return confirmationNo;
	}

	/**
	 * @param confirmationNo the confirmationNo to set
	 */
	public void setConfirmationNo(String confirmationNo) {
		this.confirmationNo = confirmationNo;
	}

	/**
	 * @return the userType
	 */
	public String getUserType() {
		return userType;
	}

	/**
	 * @param userType the userType to set
	 */
	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getSelectProduct() {
		return selectProduct;
	}

	public void setSelectProduct(String selectProduct) {
		this.selectProduct = selectProduct;
	}

	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	public int getTotalMonth() {
		return totalMonth;
	}

	public void setTotalMonth(int totalMonth) {
		this.totalMonth = totalMonth;
	}

	public EmployerBO getEmployerRegistration() {
		return employerRegistration;
	}

	public void setEmployerRegistration(EmployerBO employerRegistration) {
		this.employerRegistration = employerRegistration;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getEmployerEmail() {
		return employerEmail;
	}

	public void setEmployerEmail(String employerEmail) {
		this.employerEmail = employerEmail;
	}

	public String getWebSite() {
		return webSite;
	}

	public void setWebSite(String webSite) {
		this.webSite = webSite;
	}

	public String getEmployerName() {
		return employerName;
	}

	public void setEmployerName(String employerName) {
		this.employerName = employerName;
	}

	public String getEmailSubject() {
		return emailSubject;
	}

	public void setEmailSubject(String emailSubject) {
		this.emailSubject = emailSubject;
	}

	public String getEmailBodyCondent() {
		return emailBodyCondent;
	}

	public void setEmailBodyCondent(String emailBodyCondent) {
		this.emailBodyCondent = emailBodyCondent;
	}	
	
}
