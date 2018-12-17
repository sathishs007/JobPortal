package com.myjobkart.controller;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

import javax.imageio.ImageIO;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.rowset.serial.SerialException;
import javax.validation.Valid;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import sun.security.validator.ValidatorException;

import com.myjobkart.bo.AppliedJobBO;
import com.myjobkart.bo.BannerPostBO;
import com.myjobkart.bo.BookingBO;
import com.myjobkart.bo.ChangePassword;
import com.myjobkart.bo.CompanyEntity;
import com.myjobkart.bo.EmployerActivityBO;
import com.myjobkart.bo.EmployerBO;
import com.myjobkart.bo.EmployerLoginBO;
import com.myjobkart.bo.EmployerProfileBO;
import com.myjobkart.bo.EmployerSubuserBO;
import com.myjobkart.bo.EntityBO;
import com.myjobkart.bo.JobPostBO;
import com.myjobkart.bo.JobseekerProfileBO;
import com.myjobkart.bo.PaymentBO;
import com.myjobkart.bo.ProductBO;
import com.myjobkart.bo.ResponseObject;
import com.myjobkart.bo.SaveCandidateBO;
import com.myjobkart.bo.ShortListCandidate;
import com.myjobkart.bo.WalkinBO;
import com.myjobkart.exception.JLogger;
import com.myjobkart.exception.MyJobKartException;
import com.myjobkart.service.AdminService;
import com.myjobkart.service.EmployerService;
import com.myjobkart.service.JobSeekerService;
import com.myjobkart.utils.AccessTokenGenerator;
import com.myjobkart.utils.AppHelper;
import com.myjobkart.utils.CookiesGenerator;
import com.myjobkart.utils.Dropdownutils;
import com.myjobkart.utils.MyjobkartResourceBundle;
import com.myjobkart.utils.SendEmailServiceImpl;
import com.myjobkart.utils.WebHelper;
import com.myjobkart.vo.EmployerjobAlertsVO;
import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payment;
import com.paypal.api.payments.PaymentExecution;
import com.paypal.core.rest.APIContext;
import com.paypal.core.rest.PayPalRESTException;

/**
 * Owner : Scube Technologies Created Date: Nov-20-2014 Created by :
 * sathishkumar.s Description : Employer Controller is a controller this will
 * calls the appropriate services to do the respective operations.. Reviewed by
 * :
 * 
 */

@Controller
@Scope("session")
@SessionAttributes("/employer")
public class EmployerController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7430150632947174966L;

	private static final JLogger LOGGER = JLogger
			.getLogger(EmployerController.class);

	@Autowired
	private EmployerService<?> employerService;

	@Autowired
	AdminService<?> adminService;

	@Autowired
	ServletContext servletContext;

	@Autowired
	private JobSeekerService<?> jobSeekerService;

	@Autowired
	SendEmailServiceImpl emailManager;

	List<AppliedJobBO> appliedJobList;
	List<String> companyList;
	List<AppliedJobBO> appliedList;
	List<SaveCandidateBO> saveResumeList;
	List<SaveCandidateBO> resumeList = new ArrayList<SaveCandidateBO>();
	List<PaymentBO> enrolledList = new ArrayList<PaymentBO>();
	List<JobPostBO> activeJobPostBOList = new ArrayList<JobPostBO>();
	List<PaymentBO> paymentDetails = new ArrayList<PaymentBO>();
	boolean paidshow;
	public EmployerProfileBO postpayment;
	int randomvalue = 0;
	int ranvalues = 0;
	JobPostBO jobPostbO;
	List<PaymentBO> paymentList = new ArrayList<PaymentBO>();

	static String PAYPAL_SERVER_URL;
	static String PAYPAL_MODE;
	String employerEmail;

	/**
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/employer_sign_up", method = RequestMethod.GET)
	public String employerRegistration(Model model, HttpServletRequest request) {
		String infomessage = request.getParameter("infomessage");
		model.addAttribute("infomessage", infomessage);
		// All CompanyList
		companyList(model);
		// catcha
		Random ran = new Random();
		randomvalue = ran.nextInt(10000);
		model.addAttribute("captcha", randomvalue);
		// end


		model.addAttribute("employer", new EmployerBO());
		return "employer_sign_up";
	}

	/**
	 * Employer Registration method is used for registering the employer in the
	 * system.
	 * 
	 * @param registrationBO
	 * @return registrationBO
	 * @throws MyJobKartException
	 */

	@RequestMapping(value = "/employer_sign_up", method = RequestMethod.POST)
	public String employerRegistration(
			@Valid @ModelAttribute("employer") EmployerBO registration,
			BindingResult result, Model model, HttpServletRequest request)
					throws MyJobKartException {
		long adminId = 0;
		long companyId = 0;
		EmployerController.LOGGER.entry();
		HttpSession session = request.getSession();
		if (null != session.getAttribute("adminId")
				|| null == session.getAttribute("adminId")) {

			// All CompanyList
			companyList(model);

			// Captcha
			Random ran = new Random();
			randomvalue = ran.nextInt(10000);
			model.addAttribute("captcha", randomvalue);
			// end
			try {
				if (!registration.getCompanyName().isEmpty()) {
					registration.getCompanyName();
					// get CompanyId
					companyId = getcompany(registration.getCompanyName());

					if (companyId != 0) {
						registration.setCompanyId(companyId);
					}

				}
				if (!registration.getOtherCompany().isEmpty()) {
					registration.setCompanyName(registration.getOtherCompany());
					companyId = getcompany(registration.getCompanyName());
					if (companyId == 0) {
						long id = adminService.addNewCompany(registration
								.getOtherCompany());
						registration.setCompanyId(id);
					} else {
						registration.setCompanyId(companyId);
					}

				}
				if (registration.getCompanyName().isEmpty()
						&& registration.getOtherCompany().isEmpty()) {
					model.addAttribute("infomessage",
							"Please Select Your Company Name");
					return "employer_sign_up";
				}

				final File rootDir = new File(
						this.servletContext
						.getRealPath("/WEB-INF/images/companylogo.jpg"));
				final BufferedImage image = ImageIO.read(rootDir);
				final ByteArrayOutputStream baos = new ByteArrayOutputStream();
				ImageIO.write(image, "jpg", baos);
				final byte[] res = baos.toByteArray();

				registration.setCompanyLogo(res);

				if (null != session.getAttribute("adminId")) {
					adminId = (long) session.getAttribute("adminId");
				}
				if (!registration.getEmailAddress().equalsIgnoreCase(
						registration.getConfirmEmailAddress())) {
					result.rejectValue("confirmEmailAddress", "Validate.Email");
					return "employer_sign_up";
				}
				if (!registration.getPassword().equalsIgnoreCase(
						registration.getConfirmPassword())) {

					result.rejectValue("confirmPassword", "Validate.Password");
					return "employer_sign_up";

				}

				if (result.hasErrors()) {
					return "employer_sign_up";
				}

				if (this.employerService.findByEmail(registration
						.getEmailAddress())) {
					model.addAttribute("infomessage",
							"Your Account is Already Exists");
					return "employer_sign_up";
				}

				registration = this.employerService.create(registration);

				if (null != registration.getErrorCode()) {
					model.addAttribute("message",
							registration.getErrorMessage());

					return "employer_sign_up";
				} else {
					model.addAttribute("sucessmessage",
							registration.getResponse());
					if (0 != adminId) {
						return "redirect:/employer_details.html";
					} else {
						EmployerBO employerBO = this.clear();
						model.addAttribute("employer", employerBO);
						return "redirect:/employer_sign_in.html";
					}
				}
			} catch (final Exception ex) {
				ex.printStackTrace();
				if (LOGGER.isDebugEnabled()) {
					LOGGER.debug("Employeer sign_up failed:" + ex.getMessage());
				}
				LOGGER.info("Employeer sign_up failed:" + ex.getMessage());

			}
		}
		EmployerController.LOGGER.exit();
		return null;
	}

	/**
	 * This method used to perform the login function (employer)
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/employer_sign_in", method = RequestMethod.GET)
	public String login(Model model, HttpServletRequest request) {
		EmployerController.LOGGER.entry();

		if (null != (String) request.getAttribute("ProductType")) {
			String productType = (String) request.getAttribute("ProductType");
			model.addAttribute("ProductType", productType);
			if (null != productType) {
				return "redirect:/payment_details.html";
			}
		}
		final String param = request.getParameter("param");
		final HttpSession session = request.getSession();
		String message = request.getParameter("sucessmessage");
		model.addAttribute("sucessmessage", message);

		if (null != session.getAttribute("jobseekerId")
				|| null != session.getAttribute("adminId")) {
			model.addAttribute("ErrorMessage", "You have already loggedin!");
			model.addAttribute("employerLogin", new EmployerLoginBO());
			return "redirect:/jobseeker_home.html";
		}

		try {

			if (null != param && param.equalsIgnoreCase("cancel")) {

				if (null != session.getAttribute("loginId")) {
					return "employer_home";
				} else {

					return "redirect:/employer_sign_in.html";
				}
			} else {
				model.addAttribute("employerLogin", new CookiesGenerator()
				.cookiesVerifications(request,new EmployerLoginBO() ,"employer"));
			}
		} catch (final Exception jb) {
			if (EmployerController.LOGGER.isDebugEnabled()) {
				EmployerController.LOGGER.debug(jb.getMessage() + jb);
			}
		}
		EmployerController.LOGGER.exit();
		return "employer_sign_in";
	}

	/**
	 * This method used to perform the login function (employer)
	 * 
	 * @param model
	 * @return
	 * @throws MyJobKartException
	 */

	@RequestMapping(value = "/employer_sign_in", method = RequestMethod.POST)
	public String login(
			@ModelAttribute("employerLogin") @Valid EmployerLoginBO employerLogin,
			BindingResult result, Model model, HttpServletRequest request,
			HttpServletResponse httpResponse) throws MyJobKartException {
		EmployerController.LOGGER.entry();
		final String response = null;
		final HttpSession session = request.getSession();
		if (null != session.getAttribute("jobseekerId")) {
			model.addAttribute("infomessage", "You have already loggedin!");
			return "employer_sign_in";
		}
		if (null != session.getAttribute("loginId")) {
			model.addAttribute("infomessage", "You have already loggedin!");
			return "employer_sign_in";
		}
		if (null != session.getAttribute("adminId")) {
			model.addAttribute("infomessage", "You have already loggedin!");
			return "employer_sign_in";
		}
		try {

			if (null != (String) request.getAttribute("ProductType")) {
				String productType = (String) request
						.getAttribute("ProductType");
				model.addAttribute("ProductType", productType);
				if (null != productType) {
					return "redirect:/payment_details.html";
				}
			}

			long loginId = 0;
			boolean rememberMe = employerLogin.getRememberMe();
			employerLogin = this.employerService.authendicate(employerLogin);
			if (employerLogin.getIsActive()) {

				EmployerSubuserBO retriveSubuser = new EmployerSubuserBO();
				if(0!=employerLogin.getSubuserId()){
					retriveSubuser.setId(employerLogin.getSubuserId());
					List<EmployerSubuserBO> subUserBoList = this.employerService
							.retriveSubusers(retriveSubuser);

					if (null != subUserBoList && 0 != subUserBoList.size()) {
						for (EmployerSubuserBO subUserBO : subUserBoList) {
							employerLogin.setId(subUserBO.getEmploginBO().getId());
							employerLogin.setEmailAddress(subUserBO.getEmploginBO()
									.getEmailAddress());
							employerLogin.setRegisterId(subUserBO.getEmpRegid());
						}

					}
				}

				session.setAttribute("loginId", employerLogin.getId());
				session.setAttribute("employerRegisterId",
						employerLogin.getRegisterId());
				session.setAttribute("emailId", employerLogin.getEmailAddress());
				session.setAttribute("userType", "Employer");

				// This set of part is used to add the user email and password
				// to cookies.
				Map<String, String> cookiesObject = new HashMap<String, String>();
				cookiesObject.put("email", employerLogin.getEmailAddress());
				cookiesObject.put("password", employerLogin.getPassword());
				CookiesGenerator cookiesGenerator = new CookiesGenerator();
				cookiesGenerator.addCookies(request, httpResponse,
						cookiesObject, "employer", rememberMe);

				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				Date dateWithoutTime = sdf.parse(sdf.format(new Date()));
				Date endDate;

				loginId = employerLogin.getId();
				PaymentBO paymentBO = this.employerService
						.productsEnrolledList(loginId);
				this.enrolledList = paymentBO.getEnrolledList();
				if (null != this.enrolledList) {
					for (final PaymentBO payment : this.enrolledList) {
						endDate = payment.getEndDate();
						if (endDate.after(dateWithoutTime)) {
							session.setAttribute("paid", true);

						} else {
							session.setAttribute("paid", false);
						}
					}
				} else {
					session.setAttribute("paid", false);
				}
				final String postjobs = (String) session
						.getAttribute("jobpost");
				if (null != session.getAttribute("jobpost")) {
					return "redirect:/job_posting.html";
				} else {

					return "redirect:/employer_home.html";
				}

				// return "redirect:/employer_home.html";
			} else {
				model.addAttribute("infomessage",
						"Your account does not exist or invalid ,please contact Administrator!");
				return "employer_sign_in";
			}
		} catch (final MyJobKartException jb) {
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("employeer sign in failed:" + jb.getErrorCode()
						+ jb);
			}
			LOGGER.info("employeer sign in failed:" + jb.getErrorCode() + jb);

		} catch (final Exception ex) {
			ex.printStackTrace();
			// TODO: handle exception
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("employeer sign in failed:" + ex.getMessage());
			}
			LOGGER.info("employeer sign in failed:" + ex.getMessage());
		}
		EmployerController.LOGGER.exit();
		return response;

	}

	/**
	 * This method used to perform the forget_password function (employer)
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/employer_forget_password", method = RequestMethod.GET)
	public String forgetPassword(Model model, HttpServletRequest request) {

		String sucessmessage = request.getParameter("infomessage");
		model.addAttribute("sucessmessage", sucessmessage);
		String infomessage = request.getParameter("infomessage");
		model.addAttribute("infomessage", infomessage);

		final EmployerLoginBO forgetLogin = new EmployerLoginBO();
		model.addAttribute("forgetLogin", forgetLogin);
		return "employer_forget_password";
	}

	/**
	 * This method used to perform the forget_password function (employer)
	 * 
	 * @param model
	 * @return
	 * @throws MyJobKartException
	 */
	@RequestMapping(value = "/employer_forget_password", method = RequestMethod.POST)
	public String forgetPassword(
			@ModelAttribute("forgetLogin") @Valid EmployerLoginBO forgetLogin,
			BindingResult result, HttpServletRequest request, Model model,
			Error error) throws MyJobKartException {
		EmployerController.LOGGER.entry();
		try {

			final boolean loginReset = this.employerService
					.forgetAuthentication(forgetLogin);

			if (loginReset) {
				model.addAttribute("infomessage",
						"Please check your Email for the password reset.");
			} else {
				model.addAttribute("infomessage",
						"Your account does not exist,Please contact administrator.");
			}
		} catch (final MyJobKartException jb) {
			if (EmployerController.LOGGER.isDebugEnabled()) {
				EmployerController.LOGGER.debug(jb.getErrorCode() + jb);
			}
		}
		EmployerController.LOGGER.exit();
		return "employer_forget_password";

	}

	/**
	 * This method used to perform the change_password function (employer)
	 * 
	 * @param model
	 * @return
	 */

	@RequestMapping(value = "/employer_change_password_after_login", method = RequestMethod.GET)
	public String changeLogin1(Model model, HttpServletRequest request)
			throws Exception {
		HttpSession session = request.getSession();
		if (null == session.getAttribute("loginId")) {
			return "redirect:/employer_sign_in.html";
		}

		final ChangePassword changePassword = new ChangePassword();

		model.addAttribute("changePassword", changePassword);
		return "employer_change_password_after_login";
	}

	/**
	 * This method used to perform the change_password function (employer)
	 * 
	 * @param model
	 * @return
	 * @throws MyJobKartException
	 */
	@RequestMapping(value = "/employer_change_password_after_login", method = RequestMethod.POST)
	public String changeLogin1(
			@ModelAttribute("changePassword") @Valid ChangePassword changePassword,
			BindingResult result, HttpServletRequest request, Model model)
					throws MyJobKartException {
		EmployerController.LOGGER.entry();
		HttpSession session = request.getSession();
		if (null == session.getAttribute("loginId")) {
			return "redirect:/employer_sign_in.html";
		}

		try {
			if (!changePassword.getPassword().equalsIgnoreCase(
					changePassword.getConfirmPassword())) {

				result.rejectValue("confirmPassword", "Validate.Password");
				return "employer_change_password_after_login";
			}
			if (result.hasErrors()) {
				return "employer_change_password_after_login";
			}
			String email = (String) session.getAttribute("emailId");
			final EmployerLoginBO changePassword1 = new EmployerLoginBO();
			if (request.getParameter("emailId") == null) {
				changePassword1.setEmailAddress(email);
			} else {
				changePassword1
				.setEmailAddress(request.getParameter("emailId"));
			}
			changePassword1.setPassword(changePassword.getPassword());
			changePassword1.setConfirmPassword(changePassword
					.getConfirmPassword());
			final boolean loginChanged = this.employerService
					.changeAuthendication(changePassword1);
			if (loginChanged) {
				model.addAttribute("sucessmessage",
						"Password Changed Has Been successfully Updated.");
				model.addAttribute("employerLogin", changePassword);
				return "redirect:/employer_home.html";
			} else {
				model.addAttribute("errormessage",
						"Your account does not exist,Please contact administrator.");
				return "employer_change_password_after_login";
			}
		} catch (final Exception jb) {
			if (EmployerController.LOGGER.isDebugEnabled()) {
				EmployerController.LOGGER.debug(jb.getMessage() + jb);
			}
		}
		EmployerController.LOGGER.exit();
		return null;

	}

	/**
	 * This method used to perform the change_password function (employer)
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/employer_change_password", method = RequestMethod.GET)
	public String changeLogin(Model model) {
		final ChangePassword changePassword = new ChangePassword();

		model.addAttribute("changePassword", changePassword);
		return "employer_change_password";
	}

	/**
	 * This method used to perform the change_password function (employer)
	 * 
	 * @param model
	 * @return
	 * @throws MyJobKartException
	 */
	@RequestMapping(value = "/employer_change_password", method = RequestMethod.POST)
	public String changeLogin(
			@ModelAttribute("changePassword") @Valid ChangePassword changePassword,
			BindingResult result, HttpServletRequest request, Model model)
					throws MyJobKartException {
		EmployerController.LOGGER.entry();
		HttpSession session = request.getSession();
		try {
			if (!changePassword.getPassword().equalsIgnoreCase(
					changePassword.getConfirmPassword())) {
				result.rejectValue("confirmPassword", "Validate.Password");
				/* model.addAttribute("message", "Password Should Be Equal."); */
				return "employer_change_password";
			}
			if (result.hasErrors()) {
				return "employer_change_password";
			}
			String email = (String) session.getAttribute("emailId");
			final EmployerLoginBO changePassword1 = new EmployerLoginBO();
			if (request.getParameter("emailId") == null) {
				changePassword1.setEmailAddress(email);
			} else {
				changePassword1
				.setEmailAddress(request.getParameter("emailId"));
			}
			changePassword1.setPassword(changePassword.getPassword());
			changePassword1.setConfirmPassword(changePassword
					.getConfirmPassword());
			final boolean loginChanged = this.employerService
					.changeAuthendication(changePassword1);
			if (loginChanged) {
				model.addAttribute("message",
						"Password change has been successfully updated.");
				model.addAttribute("employerLogin", changePassword);
				return "redirect:/employer_sign_in.html";
			} else {
				model.addAttribute("message",
						"Your account does not exist,Please contact administrator.");
				return "employer_change_password";
			}
		} catch (final Exception jb) {
			if (EmployerController.LOGGER.isDebugEnabled()) {
				EmployerController.LOGGER.debug(jb.getMessage() + jb);
			}
		}
		EmployerController.LOGGER.exit();
		return null;

	}

	/**
	 * This method used to perform the account activation function (employer)
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/employer_activation", method = RequestMethod.GET)
	public String employerActivation(Model model, HttpServletRequest request) {
		EmployerController.LOGGER.entry();
		EmployerLoginBO activation = new EmployerLoginBO();
		String emailId = request.getParameter("emailId");
		try {
			activation.setEmailAddress(emailId);
			activation = employerService.activatedEmployers(activation);
			if (activation.getIsActive()) {
				model.addAttribute("activationEmployer", activation);
				return "employer_activation";

			}
			/*
			 * else{ model.addAttribute("activation", activation); return
			 * "employer_activated"; }
			 */

		} catch (Exception e) {
			e.printStackTrace();
		}

		EmployerController.LOGGER.exit();

		model.addAttribute("activation", activation);
		return "employer_activation";

	}

	/**
	 * This method used to perform the account activation function (employer)
	 * 
	 * @param model
	 * @return
	 * @throws MyJobKartException
	 */
	@RequestMapping(value = "/employer_activation", method = RequestMethod.POST)
	public String employerActivation(
			@ModelAttribute("activation") @Valid EmployerLoginBO activation,
			BindingResult result, HttpServletRequest request, Model model)
					throws MyJobKartException {
		EmployerController.LOGGER.entry();

		try {
			final String emailId = request.getParameter("emailId");

			final boolean isActivation = this.employerService
					.employerActivation(emailId);

			if (isActivation) {
				model.addAttribute("sucessmessage",
						"Your account has been successfully activated.");
				model.addAttribute("employerLogin", activation);
				return "redirect:/employer_sign_in.html";
			} else {
				model.addAttribute("infomessage",
						"Your account does not exist,Please contact administrator.");
				return "employer_activation";
			}
		} catch (final MyJobKartException jb) {
			if (EmployerController.LOGGER.isDebugEnabled()) {
				EmployerController.LOGGER.debug(jb.getErrorCode() + jb);
			}
		}
		EmployerController.LOGGER.exit();
		return null;

	}

	@RequestMapping(value = "/employer_home", method = RequestMethod.GET)
	public ModelAndView employerhome(Model model, HttpServletRequest request)
			throws SerialException, SQLException {
		EmployerController.LOGGER.entry();
		String sucessmessage = request.getParameter("sucessmessage");
		model.addAttribute("sucessmessage", sucessmessage);
		JobPostBO jobPostBO = new JobPostBO();
		final EmployerLoginBO employerLoginBO = new EmployerLoginBO();
		long jobpostcount = 0;
		long appliedjobcount = 0;
		long activeJobCount = 0;
		long inActiveJobCount = 0;
		long savejobcount = 0;
		long regid;
		long shortListCount = 0;
		long unShortListCount = 0;
		long selectedResumeCount = 0;
		long unSelectedResumeCount = 0;
		try {

			final HttpSession session = request.getSession();
			if (null == session.getAttribute("loginId")) {
				return new ModelAndView("redirect:/employer_sign_in.html");
			}
			// List<JobPostBO> jobPostBOList = new ArrayList<JobPostBO>();
			final long id = (Long) session.getAttribute("loginId");
			jobPostBO.setId(id);
			jobPostBO = this.employerService.retrieveJobPost(jobPostBO);
			List<JobPostBO> jobPostBOList = jobPostBO.getJobPostList();
			if (null != jobPostBOList && jobPostBOList.size() != 0) {
				for (final JobPostBO jobPost : jobPostBOList) {
					if (jobPost.getIsActive() == true) {
						activeJobCount = activeJobCount + 1;
					} else if (jobPost.getIsActive() == false) {
						inActiveJobCount = inActiveJobCount + 1;
					}
				}
				model.addAttribute("ActiveJobPost", activeJobCount);
				model.addAttribute("InActiveJobPost", inActiveJobCount);

				jobpostcount = jobPostBOList.size();
				model.addAttribute("jobpostcount", jobpostcount);
			} else {
				model.addAttribute("ActiveJobPost", activeJobCount);
				model.addAttribute("InActiveJobPost", inActiveJobCount);
				model.addAttribute("jobpostcount", jobpostcount);
			}
			AppliedJobBO appliedJobBO = new AppliedJobBO();
			employerLoginBO.setId(id);
			appliedJobBO.setEmployerLogin(employerLoginBO);
			appliedJobBO = this.employerService
					.reteriveAppliedJobs(appliedJobBO);
			this.appliedJobList = appliedJobBO.getJobPostList();
			if (null != this.appliedJobList && this.appliedJobList.size() != 0) {

				appliedjobcount = this.appliedJobList.size();
				model.addAttribute("appliedjobcount", appliedjobcount);
				for (final AppliedJobBO appliedJob : this.appliedJobList) {
					if (appliedJob.getIsShortlisted() == true) {
						shortListCount = shortListCount + 1;
					} else if (appliedJob.getIsShortlisted() == false) {
						unShortListCount = unShortListCount + 1;
					}
				}
				model.addAttribute("ShortlistCount", shortListCount);
				model.addAttribute("UnShortlistCount", unShortListCount);
			} else {
				model.addAttribute("ShortlistCount", shortListCount);
				model.addAttribute("UnShortlistCount", unShortListCount);
				model.addAttribute("appliedjobcount", appliedjobcount);
			}
			SaveCandidateBO saveCandidateBO = new SaveCandidateBO();
			employerLoginBO.setId(id);
			saveCandidateBO.setEmployerLoginBO(employerLoginBO);
			saveCandidateBO = this.employerService
					.reteriveCandidate(saveCandidateBO);
			this.saveResumeList = saveCandidateBO.getJobProfileList();
			if (null != this.saveResumeList && this.saveResumeList.size() != 0) {

				savejobcount = this.saveResumeList.size();
				model.addAttribute("savejobcount", savejobcount);
				for (final SaveCandidateBO candidateBO : this.saveResumeList) {
					if (candidateBO.getIsShortListed() == true) {
						selectedResumeCount = selectedResumeCount + 1;
					} else if (candidateBO.getIsShortListed() == false) {
						unSelectedResumeCount = unSelectedResumeCount + 1;
					}
				}
				model.addAttribute("SelectedCount", selectedResumeCount);
				model.addAttribute("UnSelectedCount", unSelectedResumeCount);
			} else {
				model.addAttribute("SelectedCount", selectedResumeCount);
				model.addAttribute("UnSelectedCount", unSelectedResumeCount);
				model.addAttribute("savejobcount", savejobcount);
			}
			if (null != this.enrolledList) {
				regid = (Long) session.getAttribute("employerRegisterId");
				PaymentBO paymentBO = new PaymentBO();
				paymentBO = this.employerService.productsEnrolledList(regid);
				this.enrolledList = paymentBO.getEnrolledList();
				if (null != this.enrolledList) {
					for (PaymentBO payment : this.enrolledList) {
						if (payment.getPayId() == regid) {
							model.addAttribute("enrolledList", enrolledList);
						}
					}
				}
			}

		} catch (final MyJobKartException jb) {
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("Employeer home page failed:" + jb.getMessage()
						+ jb);
			}
			LOGGER.info("Employeer home page failed:" + jb.getMessage() + jb);
		}
		EmployerController.LOGGER.exit();
		return new ModelAndView("employer_home");
	}

	public void getCompanyType(Model model) {

		// List<String> companyTypeList = new ArrayList<String>();
		List<String> companyTypeList = Dropdownutils.getCompanyType();
		model.addAttribute("companyTypeList", companyTypeList);
	}

	public void getCity(Model model) {

		// List<String> cityList = new ArrayList<String>();
		List<String> cityList = Dropdownutils.getCity();
		model.addAttribute("cityList", cityList);

	}

	public void getStates(Model model) {

		// List<String> stateList = new ArrayList<String>();
		List<String> stateList = Dropdownutils.getStates();
		model.addAttribute("stateList", stateList);

	}

	public void industryList(Model model) {
		LOGGER.entry();
		List<String> industryList = new ArrayList<String>();
		// List<EntityBO> allIndustryList = new ArrayList<EntityBO>();
		try {
			List<EntityBO> allIndustryList = adminService
					.retrieveIndustryList();
			if (null != allIndustryList && allIndustryList.size() != 0) {
				for (EntityBO bo : allIndustryList) {
					industryList.add(bo.getIndustryName());
				}
				model.addAttribute("IndustryList", industryList);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		LOGGER.exit();
	}

	/**
	 * This method used to perform the create_profile function (employer)
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/employer_create_profile", method = RequestMethod.GET)
	public String createProfile(Model model, HttpServletRequest request) {

		// All CompanyList
		companyList(model);
		industryList(model);
		final HttpSession session = request.getSession();
		if (null == session.getAttribute("loginId")) {
			return "redirect:/employer_sign_in.html";
		}

		String email = (String) session.getAttribute("emailId");
		Boolean paid = (Boolean) session.getAttribute("paid");
		model.addAttribute("paidcheck", paid);

		try {

			EmployerProfileBO reteriveprofile = new EmployerProfileBO();
			reteriveprofile.setEmailId(email);
			reteriveprofile = this.employerService
					.retriveEmployer(reteriveprofile);

			long employerLoginId = (long) session
					.getAttribute("employerRegisterId");
			EmployerLoginBO employerLogin = new EmployerLoginBO();
			employerLogin.setRegisterId(employerLoginId);
			EmployerBO employerBO = employerService
					.getEmployerRegistration(employerLogin);

			if (null != reteriveprofile) {

				if (reteriveprofile.getIsActive()) {
					reteriveprofile.setStatus("Active");

				} else {

					reteriveprofile.setStatus("De-Active");
				}

				reteriveprofile.setFirstName(employerBO.getFirstName());
				reteriveprofile.setLastName(employerBO.getLastName());
				reteriveprofile.setCompanyName(employerBO.getCompanyName());
				reteriveprofile.setCompanyWebsite(employerBO.getWebSite());
				reteriveprofile.setMobileNo(employerBO.getMobileNumber());
				reteriveprofile.setContactNo(employerBO.getContactNumber());
				reteriveprofile.setEmailId(email);

				model.addAttribute("infomessage", "Already you have profile ");
				model.addAttribute("profile", reteriveprofile);
				model.addAttribute("JobDescription", reteriveprofile);
				return "employer_profile_view";

			} else {

				getCompanyType(model);
				getCity(model);
				getStates(model);
				EmployerProfileBO profileBO = new EmployerProfileBO();
				profileBO.setEmailId(email);
				profileBO.setFirstName(employerBO.getFirstName());
				profileBO.setLastName(employerBO.getLastName());
				profileBO.setCompanyName(employerBO.getCompanyName());
				profileBO.setCompanyWebsite(employerBO.getWebSite());
				profileBO.setMobileNo(employerBO.getMobileNumber());
				profileBO.setContactNo(employerBO.getContactNumber());
				profileBO.setEmailId(email);
				profileBO.setProductEnrolled("show");
				model.addAttribute("profile", profileBO);
			}

		}

		catch (MyJobKartException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "employer_create_profile";
	}

	@RequestMapping(value = "/employer_create_profile_payment", method = RequestMethod.GET)
	public String createProfilepayment(Model model, HttpServletRequest request) {

		PaymentBO payment = new PaymentBO();
		// List<ProductBO> productList = new ArrayList<ProductBO>();
		List<ProductBO> productLists = new ArrayList<ProductBO>();
		List<ProductBO> productList = employerService.getProductList();
		model.addAttribute("payments", payment);
		model.addAttribute("paidshow", paidshow);
		if (null != productList) {
			for (ProductBO product : productList) {
				if (product.getIsActive()) {
					productLists.add(product);
					model.addAttribute("productList", productLists);
				}
			}
		}

		return "employer_create_profile_payment";

	}

	@RequestMapping(value = "/employer_create_profile_payment", method = RequestMethod.POST)
	public String payment(Model model, HttpServletRequest request,
			@ModelAttribute("payments") PaymentBO payments) {

		HttpSession session = request.getSession();
		final long id = (Long) session.getAttribute("loginId");
		try {
			final EmployerLoginBO employerLogin = new EmployerLoginBO();
			employerLogin.setId(id);
			postpayment.setCreatedBy(id);
			postpayment.setModifiedBy(id);
			postpayment.setEmployerLogin(employerLogin);
			postpayment.setIsActive(true);
			postpayment.setIsDelete(true);
			final boolean isprofile = this.employerService
					.createProfile(postpayment);
			if (isprofile) {
				if (postpayment.getProductEnrolled().equals("trail")) {
					payments.setTotalMonth(1);
					payments.setTotalcost(0);
					payments.setProductType("trail");
					payments.setName("trail");
					payments.setPayId(id);
					payments.setIsDeleted(true);
					payments = this.employerService.addPayment(payments);
					session.setAttribute("paid", true);
					model.addAttribute("sucessmessage",
							"Your profile has been successfully created.");
					return "redirect:/employer_profile_view.html";
				} else {
					payments.setTotalMonth(1);
					int totalmonth = payments.getTotalMonth();
					if (payments.getSelectProduct().equalsIgnoreCase("Gold")) {
						final int totalcost = 2000 * totalmonth;
						payments.setTotalcost(totalcost);
					} else if (payments.getSelectProduct().equalsIgnoreCase(
							"Sliver")) {
						final int totalcost = 1000 * totalmonth;
						payments.setTotalcost(totalcost);
					}

					else if (payments.getSelectProduct().equalsIgnoreCase(
							"Platinum")) {
						final int totalcost = 3000 * totalmonth;
						payments.setTotalcost(totalcost);
					}
					payments.setIsDeleted(true);
					final long paidId = (Long) session.getAttribute("loginId");
					payments.setPayId(paidId);
					payments = this.employerService.addPayment(payments);

					session.setAttribute("paid", true);
					model.addAttribute("sucessmessage",
							"Your profile has been successfully created."
									+ "Please Activate Your Profile");

				}

			}
		} catch (MyJobKartException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		model.addAttribute("payments", payments);

		return "redirect:/employer_payment_confirmation";

	}

	@RequestMapping(value = "/employer_payment_confirmation", method = RequestMethod.GET)
	public String paymentConfirmation(Model model, HttpServletRequest request,
			@ModelAttribute("payments") PaymentBO payment) {
		try {

			HttpSession session = request.getSession();
			String email = (String) session.getAttribute("emailId");
			Boolean paid = (Boolean) session.getAttribute("paid");
			model.addAttribute("payments", payment);
			return "employer_payment_confirmation";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "employer_payment_confirmation";

	}

	/**
	 * This method used to perform the create_profile function (employer)
	 * 
	 * @param model
	 * @return
	 * @throws MyJobKartException
	 * @throws IOException
	 * @throws SQLException
	 * @throws ValidatorException
	 */
	@RequestMapping(value = "/employer_create_profile", method = RequestMethod.POST)
	public String createProfile(
			@ModelAttribute("profile") @Valid EmployerProfileBO profile,
			BindingResult result, HttpServletRequest request, Model model,
			@RequestParam("companyLogos") MultipartFile companyLogo)
					throws MyJobKartException, IOException, SQLException,
					ValidatorException {
		EmployerController.LOGGER.entry();
		HttpSession session = request.getSession();
		long companyId = 0;
		long cid = 0;
		long adminId = (long) session.getAttribute("loginId");
		try {

			// All CompanyList
			companyList(model);
			industryList(model);
			getCompanyType(model);
			getCity(model);
			getStates(model);

			if (!profile.getCompanyName().isEmpty()) {
				profile.getCompanyName();
				// get CompanyId
				companyId = getcompany(profile.getCompanyName());

				if (companyId != 0) {
					profile.setCompanyId(companyId);
				}

			}
			if (!profile.getOtherCompany().isEmpty()) {
				profile.setCompanyName(profile.getOtherCompany());
				companyId = getcompany(profile.getCompanyName());
				if (companyId == 0) {
					cid = adminService.addNewCompany(profile.getOtherCompany());
					profile.setCompanyId(cid);
				} else {
					profile.setCompanyId(companyId);
				}

			}

			EmployerProfileBO reteriveprofile = new EmployerProfileBO();
			String email = (String) session.getAttribute("emailId");
			reteriveprofile.setEmailId(email);
			reteriveprofile = this.employerService
					.retriveEmployer(reteriveprofile);

			if (profile.getCompanyName().isEmpty()
					&& profile.getOtherCompany().isEmpty()) {
				model.addAttribute("infomessage",
						"Please Select Your Company Name");
				profile.setEmailId(email);
				model.addAttribute("profile", profile);
				model.addAttribute("JobDescription", reteriveprofile);
				return "employer_create_profile";
			}

			if (companyLogo.isEmpty()) {
				model.addAttribute("infomessage",
						"Please upload Your Company Logo");
				profile.setEmailId(email);
				model.addAttribute("profile", profile);
				model.addAttribute("JobDescription", reteriveprofile);
				return "employer_create_profile";
			} else {
				profile.setCompanyLogo(companyLogo.getBytes());
			}

			final String str = this.imagevalied(companyLogo);
			if (null != str) {
				model.addAttribute("infomessage",
						"CompanyLogo Only jpeg,jpg,png formats are allowed  ");
				profile.setEmailId(email);
				model.addAttribute("profile", profile);
				model.addAttribute("JobDescription", reteriveprofile);
				return "employer_create_profile";
			}

			EmployerProfileBO employerProfileBO = new EmployerProfileBO();
			if (null == session.getAttribute("loginId")) {
				return "redirect:/employer_sign_in.html";
			}
			final long id = (Long) session.getAttribute("loginId");
			final long empRegId = (long) session
					.getAttribute("employerRegisterId");
			EmployerBO employerRegistraion = new EmployerBO();
			employerRegistraion.setId(empRegId);
			profile.setEmployerRegistion(employerRegistraion);

			employerProfileBO.setEmailId(email);
			employerProfileBO = this.employerService
					.retriveEmployer(employerProfileBO);

			profile.setEmailId(email);
			if (null == employerProfileBO) {

				if (profile.getEmailId().equalsIgnoreCase(
						profile.getSecondaryEmail())) {
					result.rejectValue("secondaryEmail",
							"Validate.SecondaryEmail");

					return "employer_create_profile";
				}

				if (result.hasErrors()) {

					return "employer_create_profile";
				}

				postpayment = profile;

				String trailcheck = profile.getProductEnrolled();
				if (trailcheck.equals("trail")) {
					model.addAttribute("trailshow", true);
					return "employer_create_profile_payment";
				}
				if (trailcheck.equals("paid")) {
					paidshow = true;
					return "redirect:/employer_create_profile_payment.html";
				}

				final EmployerLoginBO employerLogin = new EmployerLoginBO();
				employerLogin.setId(id);
				profile.setCreatedBy(id);
				profile.setModifiedBy(id);
				profile.setEmployerLogin(employerLogin);
				profile.setIsActive(true);
				profile.setIsDelete(true);

				final boolean isprofile = this.employerService
						.createProfile(profile);

				if (isprofile) {
					model.addAttribute("sucessmessage",
							"Your profile has been successfully created.");
					return "redirect:/employer_profile_view.html";
				} else {
					model.addAttribute("infomessage",
							"Your account does not exist, Please contact administrator.");
					return "employer_create_profile";
				}
			} else {
				model.addAttribute("infomessage",
						"You are already having the Profile ");
				return "employer_create_profile";
			}
		} catch (final NullPointerException ne) {

			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("Employeer create profile failed:"
						+ ne.getMessage() + ne);
			}
			LOGGER.info("Employeer create profile failed:" + ne.getMessage()
					+ ne);
		}
		EmployerController.LOGGER.exit();
		return null;

	}

	/**
	 * This method used to perform the retrieve employer_profile function
	 * (employer)
	 * 
	 * @param model
	 * @param request
	 * @return
	 * @throws MyJobKartException
	 */
	@RequestMapping(value = "/employer_profile_view", method = RequestMethod.GET)
	public String retriveEmployer(Model model, HttpServletRequest request)
			throws MyJobKartException {
		EmployerController.LOGGER.entry();
		try {

			HttpSession session = request.getSession();
			if (null == session.getAttribute("loginId")) {
				return "redirect:/employer_sign_in.html";
			}

			String sucessmessage = request.getParameter("sucessmessage");
			String infomessage = request.getParameter("infomessage");
			if (null != sucessmessage) {
				model.addAttribute("sucessmessage", sucessmessage);
			} else {
				model.addAttribute("infomessage", infomessage);
			}
			long empRegId = (long) session.getAttribute("employerRegisterId");
			EmployerBO employerBO = new EmployerBO();
			employerBO.setId(empRegId);

			EmployerProfileBO reteriveprofile = new EmployerProfileBO();
			reteriveprofile.setEmployerRegistion(employerBO);
			reteriveprofile = this.employerService
					.retriveEmployer(reteriveprofile);

			if (null == reteriveprofile) {
				model.addAttribute("infomessage",
						"Please Create Your Profile.. ");

			} else {
				if (reteriveprofile.getIsActive()) {
					reteriveprofile.setStatus("Active");
					model.addAttribute("JobDescription", reteriveprofile);
				} else {
					reteriveprofile.setStatus("De-Active");
					model.addAttribute("JobDescription", reteriveprofile);
				}

			}

		} catch (final MyJobKartException jb) {
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("employeer profile view:" + jb.getMessage() + jb);
			}
			LOGGER.info("employeer profile view:" + jb.getMessage() + jb);
		}
		EmployerController.LOGGER.exit();
		return "employer_profile_view";
	}

	@RequestMapping(value = "/employer_profile_details", method = RequestMethod.GET)
	public String employerDetails(Model model, HttpServletRequest request)
			throws MyJobKartException {
		EmployerController.LOGGER.entry();
		HttpSession session = request.getSession();
		if (null == session.getAttribute("loginId")) {
			return "redirect:/employer_sign_in.html";
		}
		final String id = request.getParameter("id");
		try {

			final long jobseekerId = Long.parseLong(id);
			EmployerProfileBO reteriveprofile = new EmployerProfileBO();
			String email = (String) session.getAttribute("emailId");
			reteriveprofile.setEmailId(email);
			reteriveprofile = employerService.retriveEmployer(reteriveprofile);

			if (jobseekerId == reteriveprofile.getId()) {
				model.addAttribute("employerDetail", reteriveprofile);

			}

		} catch (final NullPointerException ne) {
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("Employeer profile details:" + ne.getMessage()
						+ ne);
			}
			LOGGER.info("Employeer profile details:" + ne.getMessage() + ne);
		}

		EmployerController.LOGGER.exit();
		return "employer_profile_details";
	}

	/**
	 * This method used to perform theupdate employer_profile function
	 * (employer)
	 * 
	 * @param model
	 * @param request
	 * @return
	 * @throws MyJobKartException
	 */

	@SuppressWarnings("null")
	@RequestMapping(value = "/employer_update_profile", method = RequestMethod.GET)
	public String updateProfile(Model model, HttpServletRequest request)
			throws MyJobKartException {
		EmployerController.LOGGER.entry();

		// All CompanyList
		companyList(model);
		industryList(model);
		try {
			HttpSession session = request.getSession();
			if (null == session.getAttribute("loginId")) {
				return "redirect:/employer_sign_in.html";
			}

			final String id = request.getParameter("id");

			final long employerId = Long.parseLong(id);

			EmployerProfileBO reteriveprofile = new EmployerProfileBO();
			reteriveprofile.setId(employerId);
			reteriveprofile = employerService.retriveEmployer(reteriveprofile);
			getCompanyType(model);
			getCity(model);
			getStates(model);
			if (reteriveprofile.getId() == employerId) {

				model.addAttribute("updateProfile", reteriveprofile);
				return "employer_update_profile";
			}
		} catch (final NullPointerException ne) {
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("Employer profile update get:" + ne.getMessage()
						+ ne);
			}
			LOGGER.info("Employer profile update get:" + ne.getMessage() + ne);
		}
		EmployerController.LOGGER.exit();
		return "employer_update_profile";
	}

	@RequestMapping(value = "/employer_update_profile", method = RequestMethod.POST)
	public String updateProfile(
			@ModelAttribute("updateProfile") @Valid EmployerProfileBO updateProfile,
			BindingResult result, HttpServletRequest request, Model model,
			@RequestParam("companyLogos") MultipartFile companyLogo)
					throws MyJobKartException, IOException, SerialException,
					SQLException, ValidatorException {
		EmployerController.LOGGER.entry();
		long companyId = 0;

		// All CompanyList
		companyList(model);
		industryList(model);

		try {
			HttpSession session = request.getSession();
			long adminId = (long) session.getAttribute("loginId");
			String email = (String) session.getAttribute("emailId");

			if (null == session.getAttribute("loginId")) {
				return "redirect:/employer_sign_in.html";
			}

			String addCompany = updateProfile.getOtherCompany();
			if (!addCompany.isEmpty()) {
				EntityBO entity = new EntityBO();
				ArrayList<String> comNameList = new ArrayList<String>();
				comNameList.add(addCompany);
				entity.setEntityName(comNameList);
				entity.setCreatedBy(adminId);
				EntityBO companyEntity = adminService.createCompany(entity);
				if (null != companyEntity) {
					updateProfile.setCompanyName(addCompany);
				}
			}

			if (!updateProfile.getCompanyName().isEmpty()) {
				// get CompanyId
				companyId = getcompany(updateProfile.getCompanyName());

				if (companyId != 0) {
					updateProfile.setCompanyId(companyId);
				}

			}



			getCompanyType(model);
			getCity(model);
			getStates(model);


			if (email.equalsIgnoreCase(
					updateProfile.getSecondaryEmail())) {
				result.rejectValue("secondaryEmail", "Validate.SecondaryEmail");
				return "employer_update_profile";
			}

			if (companyLogo.getBytes().length > 100000) {
				model.addAttribute("infomessage",
						"Upload Only Maximum of 100kb Images");
				return "employer_update_profile";
			}
			updateProfile.setEmailId(email);
			if (result.hasErrors()) {
				return "employer_update_profile";
			}


			updateProfile = this.employerService.updateProfile(updateProfile);
			if (null != updateProfile.getResponse()) {
				model.addAttribute("sucessmessage", updateProfile.getResponse());
				return "redirect:/employer_profile_view.html";
			} else {
				model.addAttribute("infomessage",
						"Data Has Been Updated Failed,Please Contact Administrator");
				return "employer_update_profile";
			}

		} catch (Exception ex) {
			ex.printStackTrace();
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("Employer profile update failed:"
						+ ex.getMessage());
			}
			LOGGER.info("Employer profile update failed:" + ex.getMessage());
		}
		return "employer_update_profile";
	}

	@RequestMapping(value = "/employer_delete_profile", method = RequestMethod.GET)
	public String deleteProfile(Model model, HttpServletRequest request)
			throws MyJobKartException {
		EmployerController.LOGGER.entry();
		try {
			final String id = request.getParameter("id");
			final long deletedId = Long.parseLong(id);

			final HttpSession session = request.getSession();

			final long loginId = (Long) session.getAttribute("loginId");

			EmployerProfileBO deleteProfile = new EmployerProfileBO();
			deleteProfile.setId(deletedId);
			deleteProfile.setDeleteBy(loginId);
			deleteProfile.setModifiedBy(loginId);
			deleteProfile.setIsDelete(false);
			final Date deletedDate = new Date();
			deleteProfile.setDeletedDate(deletedDate);
			deleteProfile = this.employerService.deleteProfile(deleteProfile);
			if (null != deleteProfile.getResponse()) {
				model.addAttribute("message", deleteProfile.getResponse());

			} else {
				model.addAttribute("message",
						"Data Has Been Updated Failed,Please Contact Administrator");

			}
		} catch (final MyJobKartException jb) {
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("Employer delete profile failed:"
						+ jb.getErrorCode() + jb);
			}
			LOGGER.info("Employer delete profile failed:" + jb.getErrorCode()
					+ jb);
		}
		EmployerController.LOGGER.exit();
		return "redirect:/employer_profile_view.html";
	}

	@RequestMapping(value = "/delete_shortlist_candidate", method = RequestMethod.GET)
	public String shortlistdeleteProfile(Model model, HttpServletRequest request)
			throws MyJobKartException {
		EmployerController.LOGGER.entry();
		try {
			final String id = request.getParameter("id");
			final long deletedId = Long.parseLong(id);

			final HttpSession session = request.getSession();

			final long loginId = (Long) session.getAttribute("loginId");
			final long employerRegisterId = (Long) session
					.getAttribute("employerRegisterId");

			SaveCandidateBO deleteProfile = new SaveCandidateBO();
			deleteProfile.setId(deletedId);
			deleteProfile.setDeleteBy(loginId);
			deleteProfile.setModifiedBy(employerRegisterId);
			deleteProfile.setIsDelete(false);
			final Date deletedDate = new Date();
			deleteProfile.setDeletedDate(deletedDate);
			deleteProfile = this.employerService.deleteProfile(deleteProfile);
			if (null != deleteProfile.getResponse()) {
				model.addAttribute("sucessmessage", deleteProfile.getResponse());

			} else {
				model.addAttribute("infomessage",
						"Data Has Been Updated Failed,Please Contact Administrator");

			}
		} catch (final MyJobKartException jb) {
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug(" delete shortlist candidate failed:"
						+ jb.getErrorCode() + jb);
			}
			LOGGER.info(" delete shortlist candidate failed:"
					+ jb.getErrorCode() + jb);
		}
		EmployerController.LOGGER.exit();
		return "redirect:/candidate_resume";
	}

	@RequestMapping(value = "/delete_applied_candidate", method = RequestMethod.GET)
	public String deletapplidecandidate(Model model, HttpServletRequest request) {
		EmployerController.LOGGER.entry();
		try {
			String id = request.getParameter("id");
			long deleteid = Long.parseLong(id);
			HttpSession session = request.getSession();
			long loginId = (long) session.getAttribute("loginId");
			AppliedJobBO deleteCandidate = new AppliedJobBO();
			deleteCandidate.setId(deleteid);
			deleteCandidate.setDeletedBy(loginId);
			deleteCandidate.setModifiedBy(loginId);
			deleteCandidate.setIsDeleted(false);
			Date deletedDate = new Date();
			deleteCandidate.setDeletedDate(deletedDate);
			deleteCandidate = employerService
					.deleteAppliedCandidate(deleteCandidate);
			if (null != deleteCandidate.getJobResponse()) {
				model.addAttribute("message", deleteCandidate.getResponse());

			} else {
				model.addAttribute("message",
						"Data Has Been Updated Failed,Please Contact Administrator");

			}
		} catch (final MyJobKartException jb) {
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug(" delete applied candidate failed:"
						+ jb.getErrorCode() + jb);
			}
			LOGGER.info(" delete applied candidate failed:" + jb.getErrorCode()
					+ jb);
		}
		EmployerController.LOGGER.exit();
		return "employer_applied_jobs";

	}

	@RequestMapping(value = "/employer_delete_jobposting", method = RequestMethod.GET)
	public String deleteJobPosting(Model model, HttpServletRequest request)
			throws MyJobKartException {
		EmployerController.LOGGER.entry();
		try {
			final String id = request.getParameter("id");
			final long deletedId = Long.parseLong(id);

			final HttpSession session = request.getSession();

			final long employerRegisterId = (Long) session
					.getAttribute("employerRegisterId");

			JobPostBO deleteProfile = new JobPostBO();
			deleteProfile.setId(deletedId);
			deleteProfile.setDeletedBy(employerRegisterId);
			deleteProfile.setModifiedBy(employerRegisterId);
			deleteProfile.setIsDeleted(false);
			final Date deletedDate = new Date();
			deleteProfile.setDeletedDate(deletedDate);
			deleteProfile = this.employerService.deleteJobPost(deleteProfile);
			if (null != deleteProfile.getResponse()) {
				model.addAttribute("sucessmessage", deleteProfile.getResponse());

			} else {
				model.addAttribute("message",
						"Data Has Been Updated Failed,Please Contact Administrator");

			}
		} catch (final MyJobKartException jb) {

			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("employer_delete_jobposting has failed:"
						+ jb.getErrorCode() + jb);
			}
			LOGGER.info("employer_delete_jobposting has failed:"
					+ jb.getErrorCode() + jb);
		}
		EmployerController.LOGGER.exit();
		return "redirect:/employer_job_view.html";
	}

	public void getMinimumExperiences(Model model) {

		Dropdownutils.getMinimumExperiences();
		List<String> minimumExperiencesList = Dropdownutils
				.getMinimumExperiences();
		model.addAttribute("minExperiences", minimumExperiencesList);

	}

	public void getMaximumExperiences(Model model) {

		List<String> maximumExperiencesList = Dropdownutils
				.getMaximumExperiences();
		model.addAttribute("maxExperiences", maximumExperiencesList);

	}

	public void getJobType(Model model) {

		List<String> jobTypeList = Dropdownutils.getJobType();
		model.addAttribute("jobTypeList", jobTypeList);

	}

	public void getLocation(Model model) {

		List<String> locationList = Dropdownutils.getLocation();
		model.addAttribute("locationList", locationList);

	}

	public void getCurrencyType(Model model) {

		List<String> currencyTypeList = Dropdownutils.getCurrencytype();
		model.addAttribute("currencyTypeList", currencyTypeList);

	}

	public void getMinimumSalary(Model model) {

		List<String> minimumSalary = Dropdownutils.getMinimumSalary();
		model.addAttribute("minimumSalary", minimumSalary);

	}

	public void getMaximumSalary(Model model) {

		List<String> maximumSalary = Dropdownutils.getMaximumSalary();
		model.addAttribute("maximumSalary", maximumSalary);

	}

	public void getFunctionarea(Model model) {

		List<String> functionAreaList = Dropdownutils.getFunctionarea();
		model.addAttribute("functionAreaList", functionAreaList);

	}

	public void getUgqualifications(Model model) {

		List<String> UgqualificationsList = Dropdownutils.getUgqualifications();
		model.addAttribute("UgqualificationsList", UgqualificationsList);

	}

	public void getPgqualifications(Model model) {

		List<String> pgqualificationsList = Dropdownutils.getPgqualifications();
		model.addAttribute("pgqualificationsList", pgqualificationsList);

	}

	@RequestMapping(value = "/job_posting", method = RequestMethod.GET)
	public String jobPost(Model model, HttpServletRequest request)
			throws ParseException {
		HttpSession session = request.getSession();

		if (null != session.getAttribute("adminId")) {
			model.addAttribute("ErrorMessage",
					"You Are Admin User! Not Post A job.");

			return "redirect:/admin_home.html";

		}

		if (null == session.getAttribute("loginId")) {
			return "redirect:/employer_sign_in.html";
		}
		EmployerController.LOGGER.entry();
		String Infomessage = (String) request.getParameter("Infomessage");
		model.addAttribute("Infomessage", Infomessage);

		String email = (String) session.getAttribute("emailId");
		if (null != session.getAttribute("jobseekerId")) {
			model.addAttribute("ErrorMessage", "You have already loggedin!");
			return "redirect:/jobseeker_home.html";
		}

		getCurrencyType(model);
		getMinimumExperiences(model);
		getMaximumExperiences(model);
		getCompanyType(model);
		getMaximumSalary(model);
		getMinimumSalary(model);
		getFunctionarea(model);
		getJobType(model);
		getLocation(model);
		getPgqualifications(model);
		getUgqualifications(model);

		try {
			final long loginId = (Long) session.getAttribute("loginId");
			if (loginId != 0) {
				EmployerProfileBO profileCheck = new EmployerProfileBO();
				profileCheck.setId(loginId);
				profileCheck.setEmailId(email);
				profileCheck = this.employerService
						.retriveEmployer(profileCheck);

				if (null == profileCheck) {
					model.addAttribute("Infomessage",
							"Please Creater Your Profile");
					return "redirect:/employer_create_profile.html";
				}

				if (profileCheck.getIsActive()) {
					Boolean paid = (Boolean) session.getAttribute("paid");
					if (paid) {
						JobPostBO jobpost = new JobPostBO();
						jobpost.setCompanyName(profileCheck.getCompanyName());
						jobpost.setIndustryType(profileCheck.getCompanyType());
						model.addAttribute("jobpost", jobpost);
						return "job_posting";
					} else {
						model.addAttribute("Infomessage",
								"Employer Purchase Any Product");
						return "redirect:/product_services.html";
					}

				} else {
					final EmployerProfileBO profile1 = new EmployerProfileBO();

					profile1.setEmailId(email);
					model.addAttribute("profile", profile1);
					model.addAttribute("infomessage",
							"Please Active Profile In Order to Post a Job!");
					return "redirect:/employer_profile_view.html";
				}

			} else {
				return "redirect:/employer_sign_in.html";
			}

		} catch (final Exception ex) {
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("job_posting get has failed:" + ex.getMessage());
			}
			LOGGER.info("job_posting get has failed:" + ex.getMessage());
		}
		return "redirect:/employer_sign_in.html";
	}

	@RequestMapping(value = "/manage_response", method = RequestMethod.GET)
	public String manageResponse(Model model, HttpServletRequest request) {
		EmployerController.LOGGER.entry();

		final HttpSession session = request.getSession();
		if (null != session.getAttribute("jobseekerId")) {
			model.addAttribute("ErrorMessage", "You have already loggedin!");
			return "redirect:/jobseeker_home.html";
		}
		try {

			final long loginId = (Long) session.getAttribute("loginId");
			if (loginId != 0) {
				return "redirect:/employer_applied_jobs.html";
			} else {
				return "redirect:/employer_sign_in.html";
			}

		} catch (final Exception e) {

		}
		EmployerController.LOGGER.exit();
		return "redirect:/employer_sign_in.html";
	}

	/**
	 * This method used to perform the create_profile function (employer)
	 * 
	 * @param model
	 * @return
	 * @throws MyJobKartException
	 *
	 */

	@RequestMapping(value = "/job_posting", method = RequestMethod.POST)
	public String jobPost(@ModelAttribute("jobpost") @Valid JobPostBO jobpost,
			BindingResult result, Model model, HttpServletRequest request)
					throws MyJobKartException, IOException {

		EmployerController.LOGGER.entry();
		HttpSession session = request.getSession();
		List<JobPostBO> jobPostList = new ArrayList<JobPostBO>();
		if (null == session.getAttribute("loginId")) {
			return "redirect:/employer_sign_in.html";
		}

		if (null != session.getAttribute("jobseekerId")) {
			model.addAttribute("message", "You have already loggedin!");
			return "redirect:/jobseeker_sign_in.html";
		}

		if (null != session.getAttribute("adminId")) {
			model.addAttribute("message", "You have already loggedin!");
			return "redirect:/jobseeker_sign_in.html";
		}
		try {
			getCurrencyType(model);
			getMinimumExperiences(model);
			getMaximumExperiences(model);
			getMaximumSalary(model);
			getMinimumSalary(model);
			getFunctionarea(model);
			getJobType(model);
			getLocation(model);
			getPgqualifications(model);
			getUgqualifications(model);
			EmployerProfileBO profile = new EmployerProfileBO();
			final long id = (Long) session.getAttribute("loginId");
			String email = (String) session.getAttribute("emailId");
			profile.setEmailId(email);

			// profile.setEmployerRegistion(employerBO);
			profile = this.employerService.retriveEmployer(profile);

			if (null != profile) {

				jobpost.setCompanyName(profile.getCompanyName());
				jobpost.setCompanyId(profile.getCompanyId());
				jobpost.setIndustryType(profile.getCompanyType());
				jobpost.setEmployerRegistion(profile);
			} else {

				model.addAttribute("Infomessage",
						"Please creater your job profile");
				return "job_posting";
			}

			if (profile.getIsActive()) {

				if (null != session.getAttribute("loginId")) {

					if (result.hasErrors()) {
						return "job_posting";
					}
					Date startDate = jobpost.getStartDate();
					Date endDate = jobpost.getEndDate();

					if ((startDate != null) & (endDate != null)) {
						if (endDate.after(startDate)) {
							jobpost.setStartDate(startDate);
							jobpost.setEndDate(endDate);

						} else {
							result.rejectValue("endDate", "Validate.EndDate");
							return "job_posting";
						}
					}
					if (null != jobpost.getMaxExp()) {
						int maxExp = Integer.parseInt(jobpost.getMaxExp());
						int minExp = Integer.parseInt(jobpost.getMinExp());
						if (minExp > maxExp) {

							result.rejectValue("maxExp", "Validate.Experinces");
							return "job_posting";
						}
					}

					String response = jobpost.getResponse();
					if (null != response) {
						model.addAttribute("message",
								"Please select any one of the manage response ");
						return "job_posting";
					}

					String jobDescription = jobpost.getJobDescription()
							.replaceAll("<br>", "");
					String address = jobpost.getAddress()
							.replaceAll("<br>", "");
					String keySkills = jobpost.getKeywords().replaceAll("<br>",
							"");
					jobpost.setAddress(address);
					jobpost.setKeywords(keySkills);

					jobpost.setJobDescription(jobDescription);

					String[] maxSalary = null;
					String[] minSalary = null;
					int maxSal = 0;
					int minSal = 0;

					String maxSa = jobpost.getMaxSalary();
					String minSa = jobpost.getMinSalary();
					if (null != jobpost.getMinSalary()
							&& null != jobpost.getMaxSalary()) {
						if (minSa.contains("++")) {
							minSalary = minSa.split("\\s+Lac\\++");
							if (maxSa.contains("++")) {
								maxSalary = maxSa.split("\\sLac\\++");
							} else {
								maxSalary = maxSa.split("\\sLac");
							}
							for (String max : maxSalary) {
								maxSal = Integer.parseInt(max);
							}
							for (String min : minSalary) {
								minSal = Integer.parseInt(min);
							}
							if (minSal > maxSal) {
								result.rejectValue("maxSalary",
										"Validate.Salary");
								return ("job_posting");
							}
						} else {
							minSalary = minSa.split("\\s+Lac");
							if (maxSa.contains("++")) {
								maxSalary = maxSa.split("\\sLac\\++");
							} else {
								maxSalary = maxSa.split("\\sLac");
							}
							// maxSalary=maxSa.split("\\sLac");

							for (String max : maxSalary) {

								maxSal = Integer.parseInt(max);
							}
							for (String min : minSalary) {

								minSal = Integer.parseInt(min);
							}
							if (minSal > maxSal) {
								result.rejectValue("maxSalary",
										"Validate.Salary");
								return ("job_posting");
							}
						}
					}

					EmployerLoginBO employerLogin = new EmployerLoginBO();
					employerLogin.setId(id);
					jobpost.setCreatedBy(id);
					jobpost.setModifiedBy(id);
					jobpost.setIsDeleted(true);
					jobpost.setIsActive(true);
					jobpost.setEmployerLogin(employerLogin);
					jobpost.setId(0);
					jobPostList.add(jobpost);

					long jobPostId = this.employerService.jobPost(jobPostList);

					if (0 != jobPostId) {
						model.addAttribute("sucessmessage",
								jobpost.getResponse());
						return "redirect:/employer_job_view.html";
					} else {
						model.addAttribute("message", jobpost.getErrorMessage());
						return "job_posting";
					}
				} else {
					return "redirect:/employer_sign_in.html";
				}
			} else {
				model.addAttribute("Infomessage",
						"Please activate your profile");
				return "job_posting";
			}

		} catch (final Exception je) {
			je.printStackTrace();
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("job_posting post has failed:" + je.getMessage());
			}
			LOGGER.info("job_posting post has failed:" + je.getMessage());
		}

		return "job_posting";
	}

	@RequestMapping(value = "/profile_status", method = RequestMethod.GET)
	public String profileStatus(Model model, HttpServletRequest request) {
		EmployerController.LOGGER.entry();
		try {

			String status = request.getParameter("status");
			String statusvalue[] = status.split(",");
			String profileStatus = statusvalue[0];
			String profileId = statusvalue[1];
			long employerProfileId = Long.parseLong(profileId);
			final EmployerProfileBO employerProfileBO = new EmployerProfileBO();
			employerProfileBO.setId(employerProfileId);
			if (profileStatus.equals("Active")) {
				employerProfileBO.setIsActive(false);
			} else {
				employerProfileBO.setIsActive(true);
			}

			final boolean employerProfileStatus = employerService.profileStatus(employerProfileBO);

			if (employerProfileStatus) {
				if (employerProfileBO.getIsActive()) {
					model.addAttribute("sucessmessage",
							"Profile Activate is Successfully.");
				} else {
					model.addAttribute("sucessmessage",
							"Profile De-Activate is Successfully.");
				}
			} else {
				model.addAttribute("sucessmessage",
						"Your account does not exisit, Please Contact Administrator.");
			}
		} catch (final NullPointerException ne) {

			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("profile status has failed:" + ne.getMessage()
						+ ne);
			}
			LOGGER.info("profile status has failed:" + ne.getMessage() + ne);
		}
		EmployerController.LOGGER.exit();
		return "redirect:/employer_profile_view.html";
	}

	@RequestMapping(value = "/subuser_profile_status", method = RequestMethod.GET)
	public String subUserprofileStatus(Model model, HttpServletRequest request)
			throws MyJobKartException {
		EmployerController.LOGGER.entry();
		long subUserActiveCount = 0;
		try {
			String status = request.getParameter("status");
			String statusvalue[] = status.split(",");
			String subuserStatus = statusvalue[0];
			String subUserId = statusvalue[1];
			long subuserId = Long.parseLong(subUserId);
			final HttpSession session = request.getSession();
			EmployerSubuserBO subUser = new EmployerSubuserBO();

			subUser.setStatus(subuserStatus);
			subUser.setId(subuserId);
			long empID = Long.parseLong(session.getAttribute("loginId")
					.toString());
			EmployerSubuserBO retriveSubuser = new EmployerSubuserBO();
			retriveSubuser.setEmployerId(empID);
			List<EmployerSubuserBO> subUserBoList = this.employerService
					.retriveSubusers(retriveSubuser);

			if (null != subUserBoList) {
				for (EmployerSubuserBO subUserBO : subUserBoList) {
					if (subUserBO.getIsActivedVal()) {
						subUserActiveCount++;
					}
				}
			}
			if ((0 == subUserActiveCount && subuserStatus
					.equalsIgnoreCase("De-Active"))
					|| (0 != subUserActiveCount && subuserStatus
					.equalsIgnoreCase("Active")
					|| (10 >= subUserActiveCount && subuserStatus
					.equalsIgnoreCase("De-Active")))) {
				if (subuserStatus.equals("Active")) {
					subUser.setIsActivedVal(false);
				} else {
					subUser.setIsActivedVal(true);
				}
				final boolean subUserStatus = employerService
						.updateSubUser(subUser);
				if (subUserStatus) {
					if (subUser.getIsActivedVal()) {
						model.addAttribute("successmessage",
								"Subuser Profile Activation is success.");
					} else {
						model.addAttribute("successmessage",
								"Subuser Profile De-Activation is success.");
					}
					return "redirect:/employer_subuser_view";
				} else {
					model.addAttribute("Infomessage",
							"Your account does not exisit,please contact Administrator.");
					return "redirect:/employer_subuser_view";
				}
			} else {
				model.addAttribute("Infomessage",
						"One Subuser Profile only is Activate ");
				return "redirect:/employer_subuser_view";
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/employer_subuser_view";
	}

	EmployerProfileBO employerIndustryType(String emailId) {
		EmployerProfileBO employerProfile = new EmployerProfileBO();

		try {

			employerProfile.setEmailId(emailId);

			employerProfile = this.employerService
					.retriveEmployer(employerProfile);

			if (employerProfile.getIndustryType().equalsIgnoreCase("company")) {
				employerProfile.setIsActive(true);
			} else {
				employerProfile.setIsActive(false);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return employerProfile;
	}



	@SuppressWarnings("unused")
	@RequestMapping(value = "/employer_applied_jobs", method = RequestMethod.GET)
	public String reteriveAppliedJobs(Model model, HttpServletRequest request)
			throws MyJobKartException {
		EmployerController.LOGGER.entry();
		HttpSession session = request.getSession();
		if (null == session.getAttribute("loginId") && null == session.getAttribute("adminId")) {
			return "redirect:/employer_sign_in.html";
		}
		JobPostBO jobPostBO = new JobPostBO();
		long id = 0;
		String email = null;
		if(null != session.getAttribute("loginId")){
			id = (Long) session.getAttribute("loginId");
			jobPostBO.setId(id);
		}

		if(null !=  session.getAttribute("adminId") && null != request.getParameter("email")){
			EmployerLoginBO employerLogin = new EmployerLoginBO();
			email = request.getParameter("email");
			employerLogin.setEmailAddress(email);
			employerLogin = this.employerService.authendicate(employerLogin);
			id = employerLogin.getId();
			jobPostBO.setId(employerLogin.getId());
		}

		String userType = (String) session.getAttribute("userType");
		model.addAttribute("userType", userType);

		jobPostBO = this.employerService.retrieveJobPost(jobPostBO);
		List<JobPostBO> jobPostBOList  = jobPostBO.getJobPostList();

		String infomessage = request.getParameter("infomessage");
		String sucessmessage = request.getParameter("sucessmessage");
		if (null != sucessmessage) {
			model.addAttribute("sucessmessage", sucessmessage);
		} else {

			model.addAttribute("infomessage", infomessage);
		}

		AppliedJobBO appliedJobBO = new AppliedJobBO();
		SaveCandidateBO saveCandidateBO = new SaveCandidateBO();
		final List<AppliedJobBO> retrieveAppliedList = new ArrayList<AppliedJobBO>();
		final List<JobPostBO> jobTittleList = new ArrayList<JobPostBO>();
		int page = 1;
		final String paging = request.getParameter("page");
		try {

			EmployerProfileBO reteriveprofile = new EmployerProfileBO();
			if(null != session.getAttribute("emailId")){
				email = (String) session.getAttribute("emailId");
			}
			reteriveprofile.setEmailId(email);
			reteriveprofile = employerService.retriveEmployer(reteriveprofile);
			EmployerLoginBO employerLoginBO = new EmployerLoginBO();
			employerLoginBO.setId(id);
			appliedJobBO.setEmployerLogin(employerLoginBO);

			if (null != request.getParameter("id")) {
				String appliedCandidate = request.getParameter("id");
				long appliedId = Long.parseLong(appliedCandidate);
				appliedJobBO.setId(appliedId);
			}
			appliedJobBO = this.employerService
					.reteriveAppliedJobs(appliedJobBO);
			this.appliedJobList = appliedJobBO.getJobPostList();
			if (null != request.getParameter("id")) {

				for (AppliedJobBO appliedJob : appliedJobBO.getJobPostList()) {
					appliedJob.setResumeTitle(appliedJob.getJobTitle());
					appliedJobBO = appliedJob;

				}

				if (0 != jobPostBOList.size()) {

					for (JobPostBO jobpost : jobPostBOList) {

						if ((jobpost.getEmpId() == id)
								&& (true == jobpost.getIsActive())) {
							JobPostBO jobtittle = new JobPostBO();
							jobtittle.setJobTitle(jobpost.getJobTitle());
							jobtittle.setCompanyName(jobpost.getCompanyName());
							jobTittleList.add(jobtittle);

						}
					}
					this.activeJobPostBOList = jobTittleList;
				}

				model.addAttribute("saveCandidateBO", appliedJobBO);
				model.addAttribute("jobTittleList", activeJobPostBOList);
				model.addAttribute("IndustryTypeCompany", reteriveprofile);
				return "shortlist_candidate";

			}

			if (null != appliedJobBO.getJobPostList()) {
				this.appliedList = this.appliedJobList;

				model.addAttribute("appliedJobs", appliedList);
				model.addAttribute("IndustryTypeCompany", reteriveprofile);

			} else {

				model.addAttribute("infomessage",
						"You Have Not Select Any Resume..Please Find a Resume ");

			}

			if(null !=  session.getAttribute("adminId")){
				return "admin_employer_applied_jobs";
			}

		} catch (final Exception ex) {
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("employer applied jobs has failed:"
						+ ex.getMessage() + ex);
			}
			LOGGER.info("employer applied jobs has failed:" + ex.getMessage()
					+ ex);
		}
		EmployerController.LOGGER.exit();
		return "employer_applied_jobs";
	}

	@RequestMapping(value = "/viewAppliedJobSearch", method = RequestMethod.POST)
	public String viewAppliedJobSearch(
			@Valid @ModelAttribute("viewAppliedJobSearch") AppliedJobBO search,
			BindingResult result, HttpServletRequest request, Model model) {
		HttpSession session = request.getSession();
		try {
			AppliedJobBO applied = null;
			List<AppliedJobBO> appliedjobsearch = new ArrayList<AppliedJobBO>();
			if (null != appliedList) {
				for (AppliedJobBO appliedJobBO : this.appliedList) {
					if (appliedJobBO.getJobTitle().toLowerCase()
							.contains(search.getSearchElement().toLowerCase())) {
						applied = appliedJobBO;
						appliedjobsearch.add(applied);
					}
				}
				if (appliedjobsearch.isEmpty()) {
					model.addAttribute("errormessage", "No record found..");
				}

				model.addAttribute("appliedJobs", appliedjobsearch);
				if (null != session.getAttribute("adminId")) {
					model.addAttribute("viewAppliedJobSearch",
							new AppliedJobBO());
					return "admin_employer_applied_jobs";
				}

				if (null != session.getAttribute("loginId")) {
					return "employer_applied_jobs";
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "employer_applied_jobs";
	}

	@RequestMapping(value = "/employer_view_candidate", method = RequestMethod.GET)
	public String viewcandidate(Model model, HttpServletRequest request) {
		EmployerController.LOGGER.entry();

		try {

			HttpSession session = request.getSession();
			if (null == session.getAttribute("loginId")) {
				return "redirect:/employer_sign_in.html";
			}
			final long id = (Long) session.getAttribute("loginId");
			EmployerLoginBO employerLoginBO = new EmployerLoginBO();
			AppliedJobBO appliedJobBO = new AppliedJobBO();
			employerLoginBO.setId(id);
			appliedJobBO.setEmployerLogin(employerLoginBO);
			appliedJobBO = this.employerService
					.reteriveAppliedJobs(appliedJobBO);
			this.appliedJobList = appliedJobBO.getJobPostList();

			final long appliedId = Long.parseLong(request.getParameter("id"));
			if (null != appliedJobList) {
				for (final AppliedJobBO appliedJob : this.appliedJobList) {
					if (null != appliedJob.getUploadResume()
							&& appliedJob.getUploadResume().length() > 0) {
						appliedJob.setResumevisibile(true);
					} else {
						appliedJob.setResumevisibile(false);
					}
					if (appliedJob.getId() == appliedId) {
						appliedJobBO = appliedJob;

					}
				}
			}
			model.addAttribute("appliedJobs", appliedJobBO);

		} catch (final Exception ex) {
			ex.printStackTrace();
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("employer view candidate has failed:"
						+ ex.getMessage() + ex);
			}
			LOGGER.info("employer view candidate has failed:" + ex.getMessage()
					+ ex);
		}
		EmployerController.LOGGER.exit();
		return "employer_view_candidate";
	}



	@RequestMapping(value = "/shortlist_candidate", method = RequestMethod.GET)
	public String shortlistCandidate(Model model, HttpServletRequest request,
			@ModelAttribute("saveCandidateBO") AppliedJobBO saveCandidate,
			@RequestParam("id") long id, @RequestParam("loginId") long loginId) {
		EmployerController.LOGGER.entry();

		final HttpSession session = request.getSession();
		if (null == session.getAttribute("loginId")) {
			return "redirect:/employer_sign_in.html";
		}
		final long employerId = (Long) session.getAttribute("loginId");
		final String mail = (String) session.getAttribute("emailId");
		long jobseekerLoginId = 0;
		try {
			ShortListCandidate shortListCandidate = new ShortListCandidate();
			EmployerProfileBO profileCheck = new EmployerProfileBO();
			profileCheck.setId(employerId);
			profileCheck.setEmailId(mail);
			profileCheck = this.employerService.retriveEmployer(profileCheck);
			if (null == profileCheck) {
				model.addAttribute("Infomessage", "Please Creater Your Profile");
				return "redirect:/employer_create_profile.html";
			}

			ShortListCandidate shortList = new ShortListCandidate();
			shortList.setCreatedBy(employerId);
			shortList = this.employerService.shortListCandidatesView(shortList);
			List<ShortListCandidate> shortListedCandidates = shortList
					.getCandidateList();

			if (null != shortListedCandidates
					&& shortListedCandidates.size() > 0) {
				if ((null != shortListedCandidates)
						&& (shortListedCandidates.size() > 0)) {
					for (ShortListCandidate shortListed : shortListedCandidates) {
						if ((shortListed.getJobpostTittle()
								.equalsIgnoreCase(saveCandidate.getJobTitle()))
								&& (loginId == shortListed.getJobseekerId())) {
							model.addAttribute("infomessage",
									"This candidate already shortlisted,Please select another job title ");
							return "redirect:/employer_applied_jobs.html";
						}
					}
				}
			}
			for (final AppliedJobBO appliedJob : this.appliedJobList) {
				if (appliedJob.getId() == id) {
					shortListCandidate = new ShortListCandidate();
					shortListCandidate.setJobpostTittle(saveCandidate
							.getJobTitle());
					if (null != saveCandidate.getCompanyName()) {
						shortListCandidate.setShortListCompany(saveCandidate
								.getCompanyName());
					} else {
						shortListCandidate.setShortListCompany(profileCheck
								.getCompanyName());
					}
					shortListCandidate.setJobseekerId(appliedJob
							.getJobseekerLogin().getId());
					shortListCandidate.setContactMail(mail);
					shortListCandidate.setCreatedBy(employerId);
					shortListCandidate.setModifiedBy(employerId);
					shortListCandidate.setEmailId(appliedJob.getEmailId());
					shortListCandidate.setCurrentIndustry(appliedJob
							.getCurrentIndustry());
					shortListCandidate.setDegree(appliedJob.getDegree());
					shortListCandidate.setFirstName(appliedJob.getFirstName());
					shortListCandidate.setPhone(appliedJob.getPhone());
					shortListCandidate.setUploadResume(appliedJob
							.getUploadResume());
					shortListCandidate.setExperienceInMonth(appliedJob
							.getExperienceInMonth());
					shortListCandidate.setExperienceInYear(appliedJob
							.getExperienceInYear());
					shortListCandidate.setExpectedCtc(appliedJob
							.getExpectedCtc());
					shortListCandidate.setExpectedCtcPer(appliedJob
							.getExpectedCtcPer());
					shortListCandidate.setProfiledescription(appliedJob
							.getProfiledescription());
					shortListCandidate.setPreferredLocation(appliedJob
							.getPreferredLocation());
					shortListCandidate.setPreferredIndustry(appliedJob
							.getPreferredIndustry());
					shortListCandidate.setResumeTitle(appliedJob
							.getResumeTitle());
					shortListCandidate.setJobTitle(appliedJob.getJobTitle());
					shortListCandidate.setCompanyName(appliedJob
							.getCompanyName());
					shortListCandidate.setKeySkills(appliedJob.getKeySkills());
					final AppliedJobBO appliedJobBO = new AppliedJobBO();
					appliedJobBO.setId(appliedJob.getId());
					shortListCandidate.setAppliedJobBO(appliedJobBO);
				}
			}

			JobPostBO jobpostBO = new JobPostBO();
			final long employerRegisterId = (Long) session
					.getAttribute("employerRegisterId");
			jobpostBO.setEmployerRegisterID(employerRegisterId);
			jobpostBO.setJobTitle(saveCandidate.getJobTitle());
			JobPostBO jobpostBOList = this.employerService
					.reteriveJobPost(jobpostBO);
			if (null != jobpostBOList) {
				shortListCandidate.setEmpRegId(employerRegisterId);
				shortListCandidate.setJobId(jobpostBOList.getJobId());
			}
			final boolean shortlist = this.employerService
					.shortlistCandidate(shortListCandidate);
			if (shortlist) {
				model.addAttribute("sucessmessage",
						"Candidate Profile Successfully Shortlisted.. ");
				return "redirect:/short_list_candidate_view.html";
			} else {
				model.addAttribute("infomessage",
						"Candidate Profile  Shortlisted Failear Contact Admin.. ");

				return "redirect:/employer_applied_jobs.html";
			}

		} catch (final Exception ex) {
			ex.printStackTrace();
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("shortlist candidate has failed:"
						+ ex.getMessage());
			}
			LOGGER.info("shortlist candidate has failed:" + ex.getMessage());
		}
		EmployerController.LOGGER.exit();
		return "employer_applied_jobs";
	}


	@RequestMapping(value = "/candidate_resume", method = RequestMethod.GET)
	public String shortlistCandidateResume(Model model, HttpServletRequest request)
			throws MyJobKartException {
		EmployerController.LOGGER.entry();

		HttpSession session = request.getSession();
		model.addAttribute("saveResumeSearch", new SaveCandidateBO());

		if (null == session.getAttribute("loginId")
				&& null == session.getAttribute("adminId")) {
			return "redirect:/employer_sign_in.html";
		}
		long id = 0;
		String jobseekerProfileId = null;
		//String email =null;
		JobPostBO jobPostBO = new JobPostBO();
		List<JobPostBO> jobPostBOList = new ArrayList<JobPostBO>();
		if(null !=  session.getAttribute("loginId")){
			id = (Long) session.getAttribute("loginId");
			jobPostBO.setId(id);
		}else
			if(null !=  session.getAttribute("adminId") && null != request.getParameter("email")){
				EmployerLoginBO employerLogin = new EmployerLoginBO();
				String employerEmail = request.getParameter("email");
				employerLogin.setEmailAddress(employerEmail);
				employerLogin = this.employerService.authendicate(employerLogin);
				id = employerLogin.getId();
				jobPostBO.setId(employerLogin.getId());
			}
		jobPostBO = this.employerService.retrieveJobPost(jobPostBO);
		jobPostBOList = jobPostBO.getJobPostList();

		String sucessmessage = request.getParameter("sucessmessage");


		if (sucessmessage != null) {

			model.addAttribute("sucessmessage", sucessmessage);
		} else {
			String infomessage = request.getParameter("infomessage");
			model.addAttribute("infomessage", infomessage);
		}

		List<JobPostBO> jobTittleList = new ArrayList<JobPostBO>();
		try {
			String userType = (String) session.getAttribute("userType");
			model.addAttribute("userType", userType);

			// reterived the saved candidate list
			SaveCandidateBO saveCandidateBO = new SaveCandidateBO();
			if(null != request.getParameter("id")){
				jobseekerProfileId = request.getParameter("id");
				long jProfileId = Long.parseLong(jobseekerProfileId);
				JobseekerProfileBO jobseekerProfileBO = new JobseekerProfileBO();
				jobseekerProfileBO.setId(jProfileId);
				saveCandidateBO.setJobseekerProfileBO(jobseekerProfileBO);
			}else{
				EmployerLoginBO employerLoginBO = new EmployerLoginBO();
				employerLoginBO.setId(id);
				saveCandidateBO.setEmployerLoginBO(employerLoginBO);
			}
			saveCandidateBO = this.employerService.reteriveCandidate(saveCandidateBO);

			List<SaveCandidateBO> SaveCandidateBOList = saveCandidateBO.getJobProfileList();
			if (null != jobseekerProfileId) {
				if (null != SaveCandidateBOList) {
					for (SaveCandidateBO saveCandidate : SaveCandidateBOList) {
						saveCandidateBO = saveCandidate;
						saveCandidateBO.setSaveId(saveCandidate.getId());
					}
				}

				if (0 != jobPostBOList.size()) {

					for (JobPostBO jobpost : jobPostBOList) {

						if ((jobpost.getEmpId() == id)
								&& (true == jobpost.getIsActive())) {
							JobPostBO jobtittle = new JobPostBO();
							jobtittle.setJobTitle(jobpost.getJobTitle());
							jobtittle.setCompanyName(jobpost.getCompanyName());
							jobTittleList.add(jobtittle);

						}
					}

					this.activeJobPostBOList = jobTittleList;

				}

				model.addAttribute("saveCandidateBO", saveCandidateBO);
				model.addAttribute("jobTittleList", activeJobPostBOList);
				return "resume_shortlist_candidate";
			}

			if (null != SaveCandidateBOList) {

				this.resumeList = SaveCandidateBOList;
				if (null != resumeList && 0 != resumeList.size()) {
					model.addAttribute("saveresume", this.resumeList);
					//model.addAttribute("IndustryType", reteriveprofile);

				} else {

					model.addAttribute("infomessage",
							"You Have Not Select Any Resume..Please Find a Resume");

				}
			}

			if (null != session.getAttribute("adminId")) {
				model.addAttribute("saveResumeSearch", new SaveCandidateBO());
				return "admin_candidate_resume";
			}

		} catch (final Exception ex) {
			ex.printStackTrace();
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("candidate_resume has failed:" + ex.getMessage()
						+ ex);
			}
			LOGGER.info("candidate_resume has failed:" + ex.getMessage() + ex);
		}
		EmployerController.LOGGER.exit();
		return "candidate_resume";
	}

	@RequestMapping(value = "/saveResumeSearch", method = RequestMethod.POST)
	public String saveResumeSearch(
			@Valid @ModelAttribute("saveResumeSearch") SaveCandidateBO search,
			BindingResult result, HttpServletRequest request, Model model) {
		HttpSession session = request.getSession();
		try {
			SaveCandidateBO resume = null;
			List<SaveCandidateBO> saveResume = new ArrayList<SaveCandidateBO>();
			if (null != resumeList) {
				for (SaveCandidateBO profileBO : resumeList) {
					if (profileBO.getFirstName().toLowerCase()
							.contains(search.getSearchElement().toLowerCase())) {
						resume = profileBO;
						saveResume.add(resume);
					}
				}
				if (saveResume.size() == 0) {
					model.addAttribute("errormessage", "No record found..");
				}

				model.addAttribute("saveresume", saveResume);
				if (null != session.getAttribute("adminId")) {

					return "admin_candidate_resume";
				}

				if (null != session.getAttribute("loginId")) {
					return "candidate_resume";
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "candidate_resume";
	}

	@RequestMapping(value = "/candidate_view", method = RequestMethod.GET)
	public String candidateView(Model model, HttpServletRequest request)
			throws SQLException, MyJobKartException {
		EmployerController.LOGGER.entry();
		HttpSession session = request.getSession();
		if (null == session.getAttribute("loginId")) {
			return "redirect:/employer_sign_in.html";
		}
		final String profileId = request.getParameter("prfid");
		final String id = request.getParameter("id");

		try {
			long jobseekerId = Long.parseLong(id);
			JobseekerProfileBO profileBO = new JobseekerProfileBO();

			// List<JobseekerProfileBO> professionalList = new
			// ArrayList<JobseekerProfileBO>();
			// List<JobseekerProfileBO> educationList = new
			// ArrayList<JobseekerProfileBO>();
			JobseekerProfileBO jobeduProfile;

			// Retrieved jobseekerProfileList by profileID
			long jobseekerProfileId = Long.parseLong(profileId);
			profileBO.setId(jobseekerProfileId);
			jobeduProfile = this.jobSeekerService.retriveJobseeker(profileBO);
			// educational List
			List<JobseekerProfileBO> educationList = jobeduProfile
					.getJobEductionProfileList();
			// professional List
			List<JobseekerProfileBO> professionalList = jobeduProfile
					.getJobprofessionalList();

			model.addAttribute("educationList", educationList);
			model.addAttribute("professionalList", professionalList);
			model.addAttribute("Infomessage", "May be the FRESHER candidate!");

			for (SaveCandidateBO reteriveprofile : this.saveResumeList) {
				if (null != reteriveprofile.getUploadResume()) {
					reteriveprofile.setResumeVisibility(true);
				} else {
					reteriveprofile.setResumeVisibility(false);
				}

				if (jobseekerId == reteriveprofile.getId()) {
					model.addAttribute("candidateview", reteriveprofile);

				}
			}

		} catch (final NullPointerException ne) {
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("candidate_view has failed:" + ne.getMessage()
						+ ne);
			}
			LOGGER.info("candidate_view has failed:" + ne.getMessage() + ne);
		}

		EmployerController.LOGGER.exit();
		return "candidate_view";
	}



	@RequestMapping(value = "/resume_shortlist_candidate", method = RequestMethod.GET)
	public String shortListResume(Model model, HttpServletRequest request,
			@ModelAttribute("saveCandidateBO") SaveCandidateBO saveCandidate,
			@RequestParam("saveId") long saveId,
			@RequestParam("profileId") long jobseekerPrfId)
					throws MyJobKartException, SQLException {
		EmployerController.LOGGER.entry();
		final HttpSession session = request.getSession();
		if (null == session.getAttribute("loginId")) {
			return "redirect:/employer_sign_in.html";
		}

		final long employerId = (Long) session.getAttribute("loginId");
		String email = (String) session.getAttribute("emailId");
		EmployerProfileBO profileCheck = new EmployerProfileBO();
		profileCheck.setId(employerId);
		profileCheck.setEmailId(email);
		profileCheck = this.employerService.retriveEmployer(profileCheck);
		if (null == profileCheck) {
			model.addAttribute("Infomessage", "Please Creater Your Profile");
			return "redirect:/employer_create_profile.html";
		}
		try {

			ShortListCandidate shortList = new ShortListCandidate();
			shortList.setCreatedBy(employerId);
			shortList = this.employerService.shortListCandidatesView(shortList);
			List<ShortListCandidate> shortListedCandidates = shortList
					.getCandidateList();


			if (null != shortListedCandidates
					&& shortListedCandidates.size() > 0) {
				for (ShortListCandidate shortListed : shortListedCandidates) { // (shortListed.getProfileId()==jobseekerPrfId)
					if ((shortListed.getJobpostTittle()
							.equalsIgnoreCase(saveCandidate.getJobTitle()))
							&& (shortListed.getProfileId() == jobseekerPrfId)) {
						model.addAttribute("infomessage",
								"This candidate  already shortlisted,Please select another job title ");
						return "redirect:/candidate_resume.html";
					}
				}
			}
			SaveCandidateBO saveCandidateBO = new SaveCandidateBO();
			saveCandidateBO.setId(saveId);
			saveCandidateBO = this.employerService.reteriveCandidate(saveCandidateBO);
			ShortListCandidate shortListCandidate = null;
			List<SaveCandidateBO> SaveCandidateBOList = saveCandidateBO.getJobProfileList();
			if(null != SaveCandidateBOList && SaveCandidateBOList.size() != 0){
				for (final SaveCandidateBO reteriveprofile : SaveCandidateBOList) {
					if (null != reteriveprofile.getUploadResume()) {
						reteriveprofile.setIsShortListed(true);
					} else {
						reteriveprofile.setIsShortListed(false);
					}
					/* if (id == reteriveprofile.getId()) { */
					if (saveId == reteriveprofile.getId()) {
						shortListCandidate = new ShortListCandidate();
						shortListCandidate.setJobpostTittle(saveCandidate
								.getJobTitle());
						if (null != saveCandidate.getCompanyName()) {
							shortListCandidate.setShortListCompany(saveCandidate
									.getCompanyName());
						} else {
							shortListCandidate.setShortListCompany(profileCheck
									.getCompanyName());
						}
						shortListCandidate.setContactMail(reteriveprofile
								.getEmailId());
						shortListCandidate.setCreatedBy(employerId);
						shortListCandidate.setModifiedBy(employerId);
						shortListCandidate.setEmailId(reteriveprofile.getEmailId());
						shortListCandidate.setCurrentIndustry(reteriveprofile
								.getCurrentIndustry());
						shortListCandidate.setDegree(reteriveprofile.getDegree());
						shortListCandidate.setFirstName(reteriveprofile
								.getFirstName());
						shortListCandidate.setPhone(reteriveprofile.getPhone());
						shortListCandidate.setUploadResume(reteriveprofile
								.getUploadResume());
						shortListCandidate.setKeySkills(reteriveprofile
								.getKeySkills());
						shortListCandidate.setExperienceInMonth(reteriveprofile
								.getExperienceInMonth());
						shortListCandidate.setExperienceInYear(reteriveprofile
								.getExperienceInYear());
						shortListCandidate.setExpectedCtc(reteriveprofile
								.getExpectedCtc());
						shortListCandidate.setExpectedCtcPer(reteriveprofile
								.getExpectedCtcPer());
						shortListCandidate.setProfiledescription(reteriveprofile
								.getProfiledescription());
						shortListCandidate.setPreferredLocation(reteriveprofile
								.getPreferredLocation());
						shortListCandidate.setPreferredIndustry(reteriveprofile
								.getPreferredIndustry());
						shortListCandidate.setResumeTitle(reteriveprofile
								.getResumeTitle());
						shortListCandidate.setJobTitle(reteriveprofile
								.getProfiledescription());
						shortListCandidate.setCompanyName(reteriveprofile
								.getCompanyName());
						SaveCandidateBO candidateBO = new SaveCandidateBO();
						candidateBO.setId(saveId);
						shortListCandidate.setCandidateBO(candidateBO);
					}
				}
			}

			JobPostBO jobpostBO = new JobPostBO();
			final long employerRegisterId = (Long) session
					.getAttribute("employerRegisterId");
			EmployerLoginBO loginBO = new EmployerLoginBO();
			loginBO.setId(employerId);
			jobpostBO.setEmployerLogin(loginBO);
			jobpostBO.setJobTitle(saveCandidate.getJobTitle());
			JobPostBO jobpostBOList = this.employerService
					.reteriveJobPost(jobpostBO);

			if (null != jobpostBOList) {
				shortListCandidate.setEmpRegId(employerRegisterId);
				shortListCandidate.setJobId(jobpostBOList.getJobId());
				shortListCandidate.setProfileId(jobseekerPrfId);
			}
			boolean shortlist = this.employerService
					.shortlistCandidate(shortListCandidate);

			if (shortlist) {
				model.addAttribute("sucessmessage",
						"Candidate Profile Successfully Shortlisted");
				return "redirect:/short_list_candidate_view.html";
			} else {
				model.addAttribute("infomessage",
						"Candidate Profile  Shortlisted Failear Contact Admin");
				return "redirect:/short_list_candidate_view.html";
			}

		} catch (final NullPointerException ne) {
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("resume_shortlist_candidate has failed:"
						+ ne.getMessage() + ne);
			}
			LOGGER.info("resume_shortlist_candidate has failed:"
					+ ne.getMessage() + ne);
		}
		EmployerController.LOGGER.exit();
		return "redirect:/candidate_resume.html";
	}

	public EmployerBO clear() {
		final EmployerBO employerBO = new EmployerBO();
		employerBO.setFirstName(null);
		return employerBO;
	}

	@RequestMapping(value = "/job_post_profile_status", method = RequestMethod.GET)
	public String jobPostProfileStatus(Model model, HttpServletRequest request) {
		EmployerController.LOGGER.entry();

		String status = request.getParameter("status");
		String statusvalue[] = status.split(",");
		String sta = statusvalue[0];
		String longValue = statusvalue[1];
		long jobId = Long.parseLong(longValue);
		try {
			JobPostBO jobPostBO = new JobPostBO();
			jobPostBO.setStatus(sta);
			jobPostBO.setJobId(jobId);
			if (jobPostBO.getStatus().equalsIgnoreCase("Active")) {
				jobPostBO.setIsActive(false);
			} else {
				jobPostBO.setIsActive(true);
			}
			boolean update = this.employerService
					.jobPostProfileStatus(jobPostBO);
			if (update) {
				if (jobPostBO.getIsActive()) {
					model.addAttribute("sucessmessage", "Profile  is Activate");
				} else {
					model.addAttribute("sucessmessage",
							"Profile is De-Activate");
				}
			} else {
				model.addAttribute("infomessage",
						"Profile is Status not Activated");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("job_post_profile_status has failed:"
						+ ex.getMessage() + ex);
			}
			LOGGER.info("job_post_profile_status has failed:" + ex.getMessage()
					+ ex);
		}
		EmployerController.LOGGER.exit();
		return "redirect:/employer_job_view.html";
	}

	@SuppressWarnings("unused")
	@RequestMapping(value = "/employer_job_view", method = RequestMethod.GET)
	public String retrieveJobPosts(Model model, HttpServletRequest request)
			throws MyJobKartException {
		EmployerController.LOGGER.entry();
		HttpSession session = request.getSession();

		model.addAttribute("viewJobpostsearch", new JobPostBO());

		if (null == session.getAttribute("loginId")
				&& null == session.getAttribute("adminId")) {
			return "redirect:/employer_sign_in.html";
		}
		String jobDescription = null;
		String keySkills = null;
		final List<JobPostBO> list = new ArrayList<JobPostBO>();

		String userType = (String) session.getAttribute("userType");
		model.addAttribute("userType", userType);
		// Retrieved jobPost list based on Login ID
		JobPostBO jobPostBO = new JobPostBO();
		List<JobPostBO> jobPostBOList = null;

		if(null !=  session.getAttribute("adminId") && null != request.getParameter("email")){
			EmployerLoginBO employerLogin = new EmployerLoginBO();
			String employerEmail = request.getParameter("email");
			employerLogin.setEmailAddress(employerEmail);
			employerLogin = this.employerService.authendicate(employerLogin);
			jobPostBO.setId(employerLogin.getId());
			model.addAttribute("email", employerEmail);
		}




		if (null != (Long) session.getAttribute("loginId")) {
			final long id = (Long) session.getAttribute("loginId");
			jobPostBO.setId(id);
		}
		jobPostBO = this.employerService.retrieveJobPost(jobPostBO);

		if (null != jobPostBO) {
			jobPostBOList = jobPostBO.getJobPostList();
		}
		/*if (null == session.getAttribute("loginId")) {
			return "redirect:/employer_sign_in.html";
		}*/
		final String sucessmessage = request.getParameter("sucessmessage");
		final String infomessage = request.getParameter("infomessage");
		model.addAttribute("infomessage", infomessage);
		model.addAttribute("sucessmessage", sucessmessage);
		final String paging = request.getParameter("page");
		try {

			for (final JobPostBO postjob : jobPostBOList) {
				jobPostBO = new JobPostBO();

				if (postjob.getJobDescription().length() > 250) {
					jobDescription = postjob.getJobDescription().substring(0,
							250);
					jobPostBO.setJobDescription(jobDescription);
				} else {
					jobPostBO.setJobDescription(postjob.getJobDescription());
				}
				if (postjob.getKeywords().length() > 50) {
					keySkills = postjob.getKeywords().substring(0, 50);
					jobPostBO.setKeywords(keySkills);
				} else {
					jobPostBO.setKeywords(postjob.getKeywords());
				}

				if (postjob.getIsActive()) {
					postjob.setStatus("Active");
				} else {
					postjob.setStatus("De-Active");
				}
				jobPostBO.setCompanyName(postjob.getCompanyName());
				jobPostBO.setCreated(postjob.getCreated());
				jobPostBO.setExperiance(postjob.getExperiance());
				jobPostBO.setJobLocation(postjob.getJobLocation());
				jobPostBO.setSalary(postjob.getSalary());
				jobPostBO.setPostedBy(postjob.getPostedBy());
				jobPostBO.setStatus(postjob.getStatus());
				jobPostBO.setJobTitle(postjob.getJobTitle());
				jobPostBO.setId(postjob.getId());
				jobPostBO.setsNo(postjob.getsNo());
				list.add(jobPostBO);
			}

			model.addAttribute("JobDescription", list);
			if (null != session.getAttribute("loginId")) {
				return "employer_job_view";
			}

			if(null!= session.getAttribute("adminId")){


				return "admin_employer_job_view";

			}


		} catch (Exception jb) {

			jobPostBOList = new ArrayList<JobPostBO>();

			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("employer_job_view has failed:" + jb.getMessage()
						+ jb);
			}
			LOGGER.info("employer_job_view has failed:" + jb.getMessage() + jb);
		}

		EmployerController.LOGGER.exit();
		return "employer_job_view";
	}

	@RequestMapping(value = "/viewJobpostsearch", method = RequestMethod.POST)
	public String viewJobpostsearch(
			@Valid @ModelAttribute("viewJobpostsearch") JobPostBO search,
			BindingResult result, HttpServletRequest request, Model model)
			throws MyJobKartException {
		HttpSession session = request.getSession();

		if (null == session.getAttribute("loginId")
				&& null == session.getAttribute("adminId")) {
			return "redirect:/employer_sign_in.html";
		}
		String jobDescription = null;
		String keySkills = null;
		final List<JobPostBO> list = new ArrayList<JobPostBO>();

		String userType = (String) session.getAttribute("userType");
		model.addAttribute("userType", userType);
		JobPostBO jobPostBO = new JobPostBO();
		List<JobPostBO> jobPostBOList = null;

		if (null != session.getAttribute("adminId") && null != employerEmail) {
			EmployerLoginBO employerLogin = new EmployerLoginBO();
			// String employerEmail = request.getParameter("email");
			employerLogin.setEmailAddress(employerEmail);
			employerLogin = this.employerService.authendicate(employerLogin);
			jobPostBO.setId(employerLogin.getId());
		}
		if (null != (Long) session.getAttribute("loginId")) {
			final long id = (Long) session.getAttribute("loginId");
			jobPostBO.setId(id);
		}

		if (null != search.getSearchElement()) {
			jobPostBO.setSearchElement(search.getSearchElement());
		}

		jobPostBO = this.employerService.retrieveJobPost(jobPostBO);

		if (null != jobPostBO) {
			jobPostBOList = jobPostBO.getJobPostList();
		}

		final String sucessmessage = request.getParameter("sucessmessage");
		final String infomessage = request.getParameter("infomessage");
		model.addAttribute("infomessage", infomessage);
		model.addAttribute("sucessmessage", sucessmessage);
		final String paging = request.getParameter("page");
		try {

			for (final JobPostBO postjob : jobPostBOList) {
				jobPostBO = new JobPostBO();

				if (postjob.getJobDescription().length() > 250) {
					jobDescription = postjob.getJobDescription().substring(0,
							250);
					jobPostBO.setJobDescription(jobDescription);
				} else {
					jobPostBO.setJobDescription(postjob.getJobDescription());
				}
				if (postjob.getKeywords().length() > 50) {
					keySkills = postjob.getKeywords().substring(0, 50);
					jobPostBO.setKeywords(keySkills);
				} else {
					jobPostBO.setKeywords(postjob.getKeywords());
				}

				if (postjob.getIsActive()) {
					postjob.setStatus("Active");
				} else {
					postjob.setStatus("De-Active");
				}
				jobPostBO.setCompanyName(postjob.getCompanyName());
				jobPostBO.setCreated(postjob.getCreated());
				jobPostBO.setExperiance(postjob.getExperiance());
				jobPostBO.setJobLocation(postjob.getJobLocation());
				jobPostBO.setSalary(postjob.getSalary());
				jobPostBO.setPostedBy(postjob.getPostedBy());
				jobPostBO.setStatus(postjob.getStatus());
				jobPostBO.setJobTitle(postjob.getJobTitle());
				jobPostBO.setId(postjob.getId());
				jobPostBO.setsNo(postjob.getsNo());
				list.add(jobPostBO);
			}

			model.addAttribute("JobDescription", list);

			if (null != session.getAttribute("loginId")) {
				return "employer_job_view";
			}

			if (null != session.getAttribute("adminId")) {
				model.addAttribute("viewJobpostsearch", new JobPostBO());
				return "admin_employer_job_view";
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return "employer_job_view";

	}
	@RequestMapping(value = "/employer_jobpost_details", method = RequestMethod.GET)
	public String jobpostDetails(Model model, HttpServletRequest request)
			throws MyJobKartException {

		EmployerController.LOGGER.entry();
		HttpSession session = request.getSession();
		/*if (null == session.getAttribute("loginId")
				&& null == session.getAttribute("adminId")) {
			return "redirect:/employer_sign_in.html";
		}*/
		String jobId = null;
		long jobPostId = 0;
		if (null != request.getParameter("id")) {
			jobId = request.getParameter("id");
			jobPostId = Long.parseLong(jobId);
		} else if (null != request.getParameter("jbId")) {
			jobId = request.getParameter("jbId");
			jobPostId = Long.parseLong(jobId);
		}
		JobPostBO jobPost = new JobPostBO();
		// List<JobPostBO> jobPostBOList = new ArrayList<JobPostBO>();
		if (null != jobId) {
			jobPost.setEmpId(jobPostId);
		} else {
			final long id = (Long) session.getAttribute("loginId");
			jobPost.setId(id);
		}
		jobPost = this.employerService.retrieveJobPost(jobPost);
		List<JobPostBO> jobPostBOList = jobPost.getJobPostList();

		JobPostBO jobPostBO1;
		try {
			for (final JobPostBO jobPostBO : jobPostBOList) {
				jobPostBO1 = new JobPostBO();
				if (jobPostId == jobPostBO.getId()) {
					jobPostBO1 = jobPostBO;
					model.addAttribute("jobDetail", jobPostBO1);
				}
			}
			if (null != session.getAttribute("loginId")) {
				return "employer_jobpost_details";
			}

			if(null!= session.getAttribute("adminId")){


				return "admin_employer_jobpost_details";

			}
		} catch (final NullPointerException ne) {
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("employer_jobpost_details has failed:"
						+ ne.getMessage() + ne);
			}
			LOGGER.info("employer_jobpost_details has failed:"
					+ ne.getMessage() + ne);
		}
		EmployerController.LOGGER.exit();

		if (null != session.getAttribute("adminId")) {
			return "admin_employer_jobpost_details";
		}
		return "employer_jobpost_details";
	}

	@RequestMapping(value = "/employer_update_jobposting", method = RequestMethod.GET)
	public String updateJobPosting(Model model, HttpServletRequest request)
			throws MyJobKartException {
		EmployerController.LOGGER.entry();
		try {
			HttpSession session = request.getSession();
			if (null == session.getAttribute("loginId")) {
				return "redirect:/employer_sign_in.html";
			}

			JobPostBO jobPost = new JobPostBO();
			// List<JobPostBO> jobPostBOList = new ArrayList<JobPostBO>();
			final String jobpostid = request.getParameter("id");
			final long jobpostId = Long.parseLong(jobpostid);

			// retrieved the post job by if jobPostId, else employerLoginId
			if (null != jobpostid) {
				jobPost.setJobId(jobpostId);
			} 
			jobPost = this.employerService.retrieveJobPost(jobPost);
			JobPostBO jobPostBOList = jobPost.getJobPostList().get(0);

			getCurrencyType(model);
			getMinimumExperiences(model);
			getMaximumExperiences(model);
			getCompanyType(model);
			getMaximumSalary(model);
			getMinimumSalary(model);
			getFunctionarea(model);
			getJobType(model);
			getLocation(model);
			getPgqualifications(model);
			getUgqualifications(model);
			// All CompanyList
			companyList(model);
			industryList(model);

			if (jobpostId == jobPostBOList.getId()) {
				this.jobPostbO = jobPostBOList;
				model.addAttribute("jobupdate", jobPostBOList);
			}



		} catch (final NullPointerException ne) {
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("employer_update_jobposting has failed:"
						+ ne.getMessage() + ne);
			}
			LOGGER.info("employer_update_jobposting has failed:"
					+ ne.getMessage() + ne);
		}
		EmployerController.LOGGER.exit();
		return "employer_update_jobposting";
	}

	@RequestMapping(value = "/employer_update_jobposting", method = RequestMethod.POST)
	public String updateJobPost(
			@ModelAttribute("jobupdate") @Valid JobPostBO jobpost,
			BindingResult result, Model model, HttpServletRequest request)
					throws MyJobKartException, IOException, SerialException,
					SQLException, ValidatorException {
		EmployerController.LOGGER.entry();
		HttpSession session = request.getSession();
		if (null == session.getAttribute("loginId")) {
			return "redirect:/employer_sign_in.html";
		}
		getCurrencyType(model);
		getMinimumExperiences(model);
		getMaximumExperiences(model);
		getCompanyType(model);
		getMaximumSalary(model);
		getMinimumSalary(model);
		getFunctionarea(model);
		getJobType(model);
		getLocation(model);
		getPgqualifications(model);
		getUgqualifications(model);

		EmployerProfileBO profile = new EmployerProfileBO();
		String email = (String) session.getAttribute("emailId");
		profile.setEmailId(email);


		EmployerBO employerBO = new EmployerBO();

		profile.setEmployerRegistion(employerBO);
		profile = this.employerService.retriveEmployer(profile);

		if (null != profile) {

			jobpost.setCompanyName(profile.getCompanyName());
			jobpost.setCompanyId(profile.getCompanyId());
			jobpost.setIndustryType(profile.getCompanyType());
			jobpost.setCompanyId(profile.getCompanyId());
		} else {

			model.addAttribute("Infomessage", "Please creater your job post");
			return "job_posting";
		}

		if (result.hasErrors()) {
			return "employer_update_jobposting";
		}

		if (null != jobpost.getMaxExp() && null != jobpost.getMinExp()) {
			int maxExp = Integer.parseInt(jobpost.getMaxExp());
			int minExp = Integer.parseInt(jobpost.getMinExp());
			if (minExp > maxExp) {

				result.rejectValue("maxExp", "Validate.Experinces");
				return "employer_update_jobposting";
			}
		}

		String jobDescription = jobpost.getJobDescription().replaceAll("<br>",
				"");
		String address = jobpost.getAddress().replaceAll("<br>", "");
		String keySkills = jobpost.getKeywords().replaceAll("<br>", "");
		jobpost.setAddress(address);
		jobpost.setKeywords(keySkills);

		jobpost.setJobDescription(jobDescription);

		String[] maxSalary = null;
		String[] minSalary = null;
		int maxSal = 0;
		int minSal = 0;

		String maxSa = jobpost.getMaxSalary();
		String minSa = jobpost.getMinSalary();
		if (null != jobpost.getMinSalary() && null != jobpost.getMaxSalary()) {
			if (minSa.contains("++")) {
				minSalary = minSa.split("\\s+Lac\\++");
				if (maxSa.contains("++")) {
					maxSalary = maxSa.split("\\sLac\\++");
				} else {
					maxSalary = maxSa.split("\\sLac");
				}
				for (String max : maxSalary) {
					maxSal = Integer.parseInt(max);
				}
				for (String min : minSalary) {
					minSal = Integer.parseInt(min);
				}
				if (minSal > maxSal) {
					result.rejectValue("maxSalary", "Validate.Salary");
					return ("employer_update_jobposting");
				}
			} else {
				if (maxSa.contains("++")) {
					maxSalary = maxSa.split("\\sLac\\++");
				} else {
					maxSalary = maxSa.split("\\sLac");
				}
				minSalary = minSa.split("\\s+Lac");
				for (String max : maxSalary) {
					maxSal = Integer.parseInt(max);
				}
				for (String min : minSalary) {
					minSal = Integer.parseInt(min);
				}
				if (minSal > maxSal) {
					result.rejectValue("maxSalary", "Validate.Salary");
					return ("employer_update_jobposting");
				}
			}
		}

		Date startDate = jobpost.getStartDate();
		Date endDate = jobpost.getEndDate();
		if ((startDate != null) & (endDate != null)) {
			if (endDate.after(startDate)) {
				jobpost.setStartDate(startDate);
				jobpost.setEndDate(endDate);
			} else {
				result.rejectValue("endDate", "Validate.EndDate");
				return "employer_update_jobposting";
			}
		}


		jobpost = this.employerService.updateJobPost(jobpost);
		if (null != jobpost.getResponse()) {
			model.addAttribute("sucessmessage", jobpost.getResponse());
			return "redirect:/employer_job_view.html";
		} else {
			model.addAttribute("infomessage",
					"Data Has Been Updated Failed,Please Contact Administrator.");
			return "employer_update_jobposting";
		}
	}

	@RequestMapping(value = "/candidateresumeDisplay", method = RequestMethod.GET)
	public void showResume(@RequestParam("id") Integer itemId,
			HttpServletResponse response, HttpServletRequest request)
					throws ServletException, IOException, SQLException {
		final String id = request.getParameter("id");
		final long imgid = Long.parseLong(id);
		for (final SaveCandidateBO prifiles : this.saveResumeList) {

			if (null != prifiles.getUploadResume()
					&& prifiles.getUploadResume().length() > 0) {

				prifiles.setIsShortListed(true);
			} else {

				prifiles.setIsShortListed(false);
			}
			if (prifiles.getId() == imgid) {
				response.setContentType("application/msword");
				response.getOutputStream().write(
						prifiles.getUploadResume().getBytes(1,
								(int) prifiles.getUploadResume().length()));
				response.getOutputStream().close();
			}
		}

	}

	@RequestMapping(value = "/applycandidateresumeDisplay", method = RequestMethod.GET)
	public void candidateResume(@RequestParam("id") Integer itemId,
			HttpServletResponse response, HttpServletRequest request)
					throws ServletException, IOException, SQLException {
		final String id = request.getParameter("id");
		final long imgid = Long.parseLong(id);
		for (final AppliedJobBO prifiles : this.appliedJobList) {
			if (prifiles.getUploadResume().length() > 0) {

				prifiles.setResumevisibility(true);
			} else {

				prifiles.setResumevisibility(false);
			}
			if (prifiles.getId() == imgid) {
				response.setContentType("application/msword");
				response.getOutputStream().write(
						prifiles.getUploadResume().getBytes(1,
								(int) prifiles.getUploadResume().length()));
				response.getOutputStream().close();
			}
		}

	}

	@RequestMapping(value = "/showcandidateImage", method = RequestMethod.GET)
	public void showcandidateImage(@RequestParam("id") Integer itemId,
			HttpServletResponse response, HttpServletRequest request)
					throws ServletException, IOException, SQLException,
					MyJobKartException {
		final String id = request.getParameter("id");
		final long imgid = Long.parseLong(id);
		AppliedJobBO appliedJobBO = new AppliedJobBO();
		appliedJobBO.setId(imgid);
		appliedJobBO = employerService.reteriveAppliedJobs(appliedJobBO);
		List<AppliedJobBO> appliedJobList = appliedJobBO.getJobPostList();
		for (final AppliedJobBO prifiles : appliedJobList) {
			if (prifiles.getId() == imgid) {
				response.setContentType("image/jpeg, image/jpg, image/png, image/gif");
				response.getOutputStream().write(
						prifiles.getProfileImage().getBytes(1,
								(int) prifiles.getProfileImage().length()));
				response.getOutputStream().close();
			}
		}
	}

	@RequestMapping(value = "/showjobseekerimage", method = RequestMethod.GET)
	public void showImage(@RequestParam("id") Integer itemId,
			HttpServletResponse response, HttpServletRequest request)
					throws ServletException, IOException, SQLException,
					MyJobKartException {
		final String id = request.getParameter("id");
		final long imgid = Long.parseLong(id);
		SaveCandidateBO saveCandidateBO = new SaveCandidateBO();
		saveCandidateBO.setId(imgid);
		saveCandidateBO = employerService.reteriveCandidate(saveCandidateBO);
		List<SaveCandidateBO> saveResumeList = saveCandidateBO
				.getJobProfileList();
		if (null != saveResumeList) {
			for (final SaveCandidateBO prifiles : saveResumeList) {
				if (prifiles.getId() == imgid) {
					response.setContentType("image/jpeg, image/jpg, image/png, image/gif");
					response.getOutputStream().write(
							prifiles.getProfileImage().getBytes(1,
									(int) prifiles.getProfileImage().length()));
					response.getOutputStream().close();
				}
			}
		}

	}

	public String imagevalied(MultipartFile profileImage) {
		String str = null;
		if (profileImage.getContentType().equals("image/jpeg")
				|| profileImage.getContentType().equals("image/jpg")
				|| profileImage.getContentType().equals("image/png")) {

		} else {
			str = "Only Jpeg,Jpg,Png Formats Are Allowed  ";

		}
		return str;
	}

	public String resumevalied(MultipartFile resumeFile) {
		String str = null;
		if (resumeFile.getContentType().equals("application/msword")
				|| resumeFile.getContentType().equals("application/pdf")) {

		} else {
			str = "Only msword,pdf.. Formats Are Allowed  ";

		}
		return str;
	}

	/*
	 * @SuppressWarnings("unchecked") public EmployerBO
	 * showEmployerLoginImage(HttpServletRequest request) throws
	 * ServletException, IOException, SQLException { final HttpSession session =
	 * request.getSession(); final long employerRegisterId = (Long) session
	 * .getAttribute("employerRegisterId"); EmployerBO eBo = null; final
	 * List<EmployerBO> employerRegisteredList = (List<EmployerBO>) session
	 * .getAttribute("employerRegisterList"); for (final EmployerBO employerBO :
	 * employerRegisteredList) { if (employerBO.getId() == employerRegisterId) {
	 * eBo = new EmployerBO();
	 * eBo.setCompanyLogo(employerBO.getCompanyLogo().getBytes(1, (int)
	 * employerBO.getCompanyLogo().length())); } } return eBo;
	 * 
	 * }
	 */

	@RequestMapping(value = "/locations", method = RequestMethod.GET)
	public @ResponseBody
	List<String> getCity(@RequestParam String state) {
		final List<String> city = new ArrayList<String>();
		city.add("chennai");
		city.add("trichy");
		city.add("coimbathore");
		return city;

	}

	/*
	 * @RequestMapping(value = "/banner_posting", method = RequestMethod.GET)
	 * public String bannerPost(Model model, HttpServletRequest request) {
	 * 
	 * HttpSession session = request.getSession();
	 * 
	 * if (null == session.getAttribute("loginId")) { return
	 * "redirect:/employer_sign_in.html"; }
	 * 
	 * BannerPostBO bannerPostBO = new BannerPostBO(); try { long loginId =
	 * (Long) session.getAttribute("loginId"); String email = (String)
	 * session.getAttribute("emailId"); EmployerProfileBO profileCheck = new
	 * EmployerProfileBO(); profileCheck.setId(loginId);
	 * profileCheck.setEmailId(email); profileCheck =
	 * this.employerService.retriveEmployer(profileCheck); if (null ==
	 * profileCheck) { model.addAttribute("Infomessage",
	 * "Please Creater Your Profile"); return
	 * "redirect:/employer_create_profile.html"; }
	 * 
	 * model.addAttribute("bannerpost", bannerPostBO); return "banner_posting";
	 * } catch (Exception e) { e.printStackTrace(); }
	 * 
	 * return "banner_posting"; }
	 * 
	 * @RequestMapping(value = "/banner_posting", method = RequestMethod.POST)
	 * public String BannerPosting(
	 * 
	 * @ModelAttribute("bannerpost") @Valid BannerPostBO bannerpost,
	 * BindingResult result, HttpServletRequest request, Model model,
	 * 
	 * @RequestParam("banner") MultipartFile banner) { LOGGER.entry();
	 * 
	 * try { HttpSession session = request.getSession(); if (null ==
	 * session.getAttribute("loginId")) { return
	 * "redirect:/employer_sign_in.html"; } if (null !=
	 * session.getAttribute("loginId") || null !=
	 * session.getAttribute("adminId")) { Long loginId = (Long)
	 * session.getAttribute("loginId"); if (result.hasErrors()) { return
	 * "banner_posting"; }
	 * 
	 * if (banner.isEmpty()) { model.addAttribute("infomessage",
	 * "Upload Banner Image"); return "banner_posting";
	 * 
	 * }
	 * 
	 * if (!banner.isEmpty()) {
	 * 
	 * bannerpost.setBannerImage(banner.getBytes()); String bannerImage =
	 * imagevalied(banner); if (null != bannerImage) {
	 * model.addAttribute("message",
	 * "Upload Only jpeg,jpg,png formats are allowed  "); return
	 * "banner_posting"; }
	 * 
	 * EmployerLoginBO employerLogin = new EmployerLoginBO();
	 * employerLogin.setId(loginId);
	 * 
	 * bannerpost.setIsDelete(true);
	 * bannerpost.setEmployerRegister(employerLogin);
	 * bannerpost.setBannerImage(banner.getBytes());
	 * bannerpost.setCreatedBy(loginId); int totalmonth =
	 * bannerpost.getTotalMonth(); bannerpost.setTotalMonth(totalmonth);
	 * bannerpost.setStatus("NEW"); if
	 * (bannerpost.getPostPage().equalsIgnoreCase("home")) { int totalcost =
	 * 2000 * totalmonth; bannerpost.setTotalcost(totalcost); } else if
	 * (bannerpost.getPostPage().equalsIgnoreCase( "about_as")) { int totalcost
	 * = 1000 * totalmonth; bannerpost.setTotalcost(totalcost); }
	 * 
	 * Boolean isTrue = employerService.saveBannerPost(bannerpost); if (isTrue)
	 * { model.addAttribute("message",
	 * "Your Banner has been successfully created."); return
	 * "redirect:/renewal_bannerList.html"; } else {
	 * model.addAttribute("message",
	 * "Your Banner does not exisit,please contact Administrator."); return
	 * "banner_posting"; } }
	 * 
	 * }
	 * 
	 * } catch (Exception e) { e.printStackTrace();
	 * 
	 * }
	 * 
	 * return null;
	 * 
	 * }
	 * 
	 * @RequestMapping(value = "/banner_Image", method = RequestMethod.GET)
	 * public void bannerImage(@RequestParam("id") long imageReqId,
	 * HttpServletResponse response, HttpServletRequest request) { try {
	 * HttpSession session = request.getSession(); // List<BannerPostBO>
	 * allEmployer = new ArrayList<BannerPostBO>(); long loginId = (Long)
	 * session.getAttribute("loginId"); List<BannerPostBO> allEmployer =
	 * this.employerService .renewelBannerList(loginId);
	 * 
	 * String id = request.getParameter("id"); long imageId =
	 * Long.parseLong(id); for (BannerPostBO bannerBO : allEmployer) { if
	 * (bannerBO.getBannerId() == imageId) {
	 * response.setContentType("image/jpeg, image/jpg, image/png, image/gif");
	 * 
	 * response.getOutputStream().write( bannerBO.getBannerImage().getBytes(1,
	 * (int) bannerBO.getBannerImage().length()));
	 * response.getOutputStream().close();
	 * 
	 * }
	 * 
	 * } } catch (Exception e) { e.printStackTrace(); }
	 * 
	 * }
	 * 
	 * @RequestMapping(value = "/renewal_bannerList", method =
	 * RequestMethod.GET) public String renewalBannerList(Model model,
	 * HttpServletRequest request) { EmployerController.LOGGER.entry();
	 * 
	 * HttpSession session = request.getSession(); // List<BannerPostBO>
	 * allEmployer = new ArrayList<BannerPostBO>(); if (null ==
	 * session.getAttribute("loginId")) { return
	 * "redirect:/employer_sign_in.html"; }
	 * 
	 * // this.allEmployer = new ArrayList<BannerPostBO>(); int page = 1; final
	 * String paging = request.getParameter("page");
	 * model.addAttribute("renewalbannerposting", new EmployerBO()); String
	 * message = request.getParameter("infomessage"); String sucessmessage =
	 * request.getParameter("message"); if (null != sucessmessage) {
	 * model.addAttribute("sucessmessage", sucessmessage); } else {
	 * model.addAttribute("infomessage", message); }
	 * 
	 * try {
	 * 
	 * final long id = (Long) session.getAttribute("loginId");
	 * List<BannerPostBO> allEmployer = this.employerService
	 * .renewelBannerList(id);
	 * 
	 * if (null != paging) {
	 * 
	 * page = Integer.parseInt(paging);
	 * 
	 * @SuppressWarnings("unchecked") final ResponseObject<BannerPostBO>
	 * reponseObject = this .pagination1(page, allEmployer);
	 * 
	 * model.addAttribute("bannerPostingList", reponseObject);
	 * 
	 * } else {
	 * 
	 * // bannerPostingList.addAll(allEmployer);
	 * 
	 * @SuppressWarnings("unchecked") final ResponseObject<BannerPostBO>
	 * responseObject = this .pagination1(1, allEmployer);
	 * model.addAttribute("bannerPostingList", responseObject); if (null ==
	 * allEmployer || allEmployer.size() == 0) {
	 * model.addAttribute("infomessage", "Create Banner Post"); } }
	 * 
	 * } catch (final Exception jb) { if
	 * (EmployerController.LOGGER.isDebugEnabled()) { jb.printStackTrace(); } }
	 * return "renewal_bannerList";
	 * 
	 * }
	 */

	/*@RequestMapping(value = "/employer_renewal_list", method = RequestMethod.GET)
	public String employerRenewal(Model model, HttpServletRequest request) {
		EmployerController.LOGGER.entry();
		EmployerBO allEmployer = new EmployerBO();
		// List<EmployerBO> employerRenewalList = new ArrayList<EmployerBO>();
		int page = 1;
		final String paging = request.getParameter("page");
		model.addAttribute("employeeNameSearch", new EmployerBO());
		try {
			HttpSession session = request.getSession();
			if (null == session.getAttribute("loginId")) {
				return "redirect:/employer_sign_in.html";
			}

			final String email = (String) session.getAttribute("emailId");
			allEmployer = this.employerService.employerRenewalAlert(email);
			List<EmployerBO> employerRenewalList = allEmployer
					.getRegisteredList();

			if (null != paging) {

				page = Integer.parseInt(paging);

				@SuppressWarnings("unchecked")
				final ResponseObject<EmployerBO> reponseObject = this
						.pagination2(page, employerRenewalList);

				model.addAttribute("registeredEmployer", reponseObject);

			} else {

				allEmployer = this.employerService.employerRenewalAlert(email);
				employerRenewalList = allEmployer.getRegisteredList();

			}
			if (employerRenewalList.size() > 0) {

				@SuppressWarnings("unchecked")
				final ResponseObject<EmployerBO> responseObject = this
						.pagination2(1, employerRenewalList);
				model.addAttribute("registeredEmployer", responseObject);
			} else {
				model.addAttribute("message", "please create employer");
			}

		} catch (final Exception jb) {
			jb.printStackTrace();

		}
		return "employer_renewal_list";
	}*/

	@RequestMapping(value = "/bannar_posting", method = RequestMethod.POST)
	public String BannerPostingadmin(
			@ModelAttribute("bannerpay") @Valid BannerPostBO bannerpost,
			BindingResult result, HttpServletRequest request, Model model,
			@RequestParam("banner") MultipartFile banner) {
		LOGGER.entry();
		try {
			HttpSession session = request.getSession();
			String bannerId = request.getParameter("id");
			long postId = Long.parseLong(bannerId);
			if (null != session.getAttribute("loginId")) {
				Long loginId = (Long) session.getAttribute("loginId");
				if (result.hasErrors()) {
					return "bannar_posting";
				}

				if (banner.isEmpty()) {
					model.addAttribute("infomessage", "Upload Banner Image");
					return "bannar_posting";
				}
				if (!banner.isEmpty()) {

					bannerpost.setBannerImage(banner.getBytes());
					String bannerImage = imagevalied(banner);
					if (null != bannerImage) {
						model.addAttribute("infomessage",
								"Upload Only jpeg,jpg,png formats are allowed  ");
						return "bannar_posting";
					}

					EmployerLoginBO employerLogin = new EmployerLoginBO();
					employerLogin.setId(loginId);
					bannerpost.setIsDelete(true);
					bannerpost.setBannerId(postId);
					bannerpost.setEmployerRegister(employerLogin);
					bannerpost.setBannerImage(banner.getBytes());
					int totalmonth = bannerpost.getTotalMonth();
					bannerpost.setTotalMonth(totalmonth);
					if (bannerpost.getPostPage().equalsIgnoreCase("home")) {
						int totalcost = 2000 * totalmonth;
						bannerpost.setTotalcost(totalcost);
					} else if (bannerpost.getPostPage().equalsIgnoreCase(
							"about_as")) {
						int totalcost = 1000 * totalmonth;
						bannerpost.setTotalcost(totalcost);
					}

					Boolean isTrue = employerService.saveBannerPost(bannerpost);
					if (isTrue) {
						model.addAttribute("successmessage",
								"Your Banner has been successfully created.");
						return "redirect:/renewal_bannerList.html";
					} else {
						model.addAttribute("infomessage",
								"Your Banner does not exisit,please contact Administrator.");
						return "banner_posting";
					}
				}

			}

		} catch (Exception e) {
			e.printStackTrace();

		}

		return null;

	}

	@SuppressWarnings("null")
	@RequestMapping(value = "/bannar_posting", method = RequestMethod.GET)
	public String payBanner(Model model, HttpServletRequest request) {
		EmployerController.LOGGER.entry();
		BannerPostBO bannerPostBO = new BannerPostBO();
		try {

			HttpSession session = request.getSession();
			// List<BannerPostBO> allEmployer = new ArrayList<BannerPostBO>();
			long loginId = (Long) session.getAttribute("loginId");
			List<BannerPostBO> allEmployer = this.employerService
					.renewelBannerList(loginId);

			final String id = request.getParameter("id");

			final long employeerId = Long.parseLong(id);

			for (final BannerPostBO bo : allEmployer) {
				if (bo.getBannerId() == employeerId) {
					bannerPostBO.setBannerName(bo.getBannerName());
					bannerPostBO.setPostPage(bo.getPostPage());
					bannerPostBO.setTotalMonth(bo.getTotalMonth());

				}

			}

			model.addAttribute("bannerpay", bannerPostBO);

		} catch (final NullPointerException ne) {
			if (EmployerController.LOGGER.isDebugEnabled()) {
				EmployerController.LOGGER.debug(ne.getMessage() + ne);
			}

		} catch (final Exception e) {
			e.printStackTrace();
		}
		EmployerController.LOGGER.exit();
		return "bannar_posting";
	}

	@RequestMapping(value = "/bannar_posting_test", method = RequestMethod.POST)
	public String payBanner(
			@ModelAttribute("bannerUpdate") @Valid BannerPostBO bannerPostBO,
			Model model, HttpServletRequest request,
			@RequestParam("banner") MultipartFile banner, BindingResult result) {
		EmployerController.LOGGER.entry();
		try {

			final HttpSession session = request.getSession();
			String id = request.getParameter("id");
			long bannerId = Long.parseLong(id);
			bannerPostBO.setBannerId(bannerId);
			if (null != session.getAttribute("loginId")) {
				final Long loginId = (Long) session.getAttribute("loginId");

				/*
				 * if (result.hasErrors()) { return "banner_update"; }
				 */

				if (banner.isEmpty()) {
					model.addAttribute("infomessage", "Upload Banner Image");
					return "bannar_posting";

				}
				if (!banner.isEmpty()) {
					bannerPostBO.setBannerImage(banner.getBytes());
					final String bannerImage = this.imagevalied(banner);
					if (null != bannerImage) {
						model.addAttribute("infomessage",
								"Upload Only jpeg,jpg,png formats are allowed  ");
						return "bannar_posting";
					}
					/*
					 * if (banner.getBytes().length > 100000) {
					 * model.addAttribute("message",
					 * "Please upload low size images"); return "banner_update";
					 * }
					 */
					final EmployerLoginBO employerLogin = new EmployerLoginBO();
					employerLogin.setId(loginId);
					bannerPostBO.setIsDelete(true);
					bannerPostBO.setEmployerRegister(employerLogin);
					final int totalmonth = bannerPostBO.getTotalMonth();
					bannerPostBO.setTotalMonth(totalmonth);
					if (bannerPostBO.getPostPage().equalsIgnoreCase("home")) {
						final int totalcost = 2000 * totalmonth;
						bannerPostBO.setTotalcost(totalcost);
					} else if (bannerPostBO.getPostPage().equalsIgnoreCase(
							"about_as")) {
						final int totalcost = 1000 * totalmonth;
						bannerPostBO.setTotalcost(totalcost);
					}

					bannerPostBO.setModifiedBy(loginId);

					/*
					 * bannerPostBO.setCreatedBy(this.bannerPost.getCreatedBy());
					 * bannerPostBO.setVersion(this.bannerPost.getVersion());
					 * bannerPostBO.setBannerId(this.bannerPost.getBannerId());
					 */

					bannerPostBO = this.employerService
							.updateBanner(bannerPostBO);
					if (null != bannerPostBO.getResponse()) {
						model.addAttribute("sucessmessage",
								bannerPostBO.getResponse());
						return "redirect:/renewal_bannerList.html";
					} else {
						model.addAttribute("infomessage",
								"Data has been updated failed,please contact Administrator.");
						return "bannar_posting";
					}

				}

			}

		} catch (final Exception e) {
			e.printStackTrace();

		}

		return null;

	}

	@SuppressWarnings("null")
	@RequestMapping(value = "/banner_update", method = RequestMethod.GET)
	public String updateBanner(Model model, HttpServletRequest request,
			HttpServletResponse response) {
		EmployerController.LOGGER.entry();
		HttpSession session = request.getSession();
		if (null == session.getAttribute("loginId")) {
			return "redirect:/employer_sign_in.html";
		}

		BannerPostBO bannerPostBO = new BannerPostBO();
		try {

			// List<BannerPostBO> allEmployer = new ArrayList<BannerPostBO>();
			long loginId = (Long) session.getAttribute("loginId");
			List<BannerPostBO> allEmployer = this.employerService
					.renewelBannerList(loginId);

			final String id = request.getParameter("id");

			final long employeerId = Long.parseLong(id);

			for (final BannerPostBO bo : allEmployer) {
				if (bo.getBannerId() == employeerId) {
					bannerPostBO.setBannerName(bo.getBannerName());
					bannerPostBO.setPostPage(bo.getPostPage());
				}

			}

			model.addAttribute("bannerUpdate", bannerPostBO);

		} catch (final NullPointerException ne) {
			if (EmployerController.LOGGER.isDebugEnabled()) {
				EmployerController.LOGGER.debug(ne.getMessage() + ne);
			}

		} catch (final Exception e) {
			e.printStackTrace();
		}
		EmployerController.LOGGER.exit();
		return "banner_update";
	}

	@RequestMapping(value = "/banner_update", method = RequestMethod.POST)
	public String updateBanner(
			@ModelAttribute("bannerUpdate") @Valid BannerPostBO bannerPostBO,
			Model model, HttpServletRequest request,
			@RequestParam("banner") MultipartFile banner, BindingResult result) {
		EmployerController.LOGGER.entry();
		try {
			HttpSession session = request.getSession();
			if (null == session.getAttribute("loginId")) {
				return "redirect:/employer_sign_in.html";
			}

			String id = request.getParameter("id");
			long bannerId = Long.parseLong(id);
			bannerPostBO.setBannerId(bannerId);
			if (null != session.getAttribute("loginId")) {
				final Long loginId = (Long) session.getAttribute("loginId");
				if (result.hasErrors()) {
					return "banner_update";
				}

				if (banner.isEmpty()) {
					model.addAttribute("infomessage", "Upload Banner Image");
					return "banner_update";

				}
				if (!banner.isEmpty()) {
					bannerPostBO.setBannerImage(banner.getBytes());
					final String bannerImage = this.imagevalied(banner);
					if (null != bannerImage) {
						model.addAttribute("infomessage",
								"Upload Only jpeg,jpg,png formats are allowed  ");
						return "banner_update";
					}
					/*
					 * if (banner.getBytes().length > 100000) {
					 * model.addAttribute("message",
					 * "Please upload low size images"); return "banner_update";
					 * }
					 */
					final EmployerLoginBO employerLogin = new EmployerLoginBO();
					employerLogin.setId(loginId);
					bannerPostBO.setIsDelete(true);
					bannerPostBO.setEmployerRegister(employerLogin);
					final int totalmonth = bannerPostBO.getTotalMonth();
					bannerPostBO.setTotalMonth(totalmonth);
					if (bannerPostBO.getPostPage().equalsIgnoreCase("home")) {
						final int totalcost = 2000 * totalmonth;
						bannerPostBO.setTotalcost(totalcost);
					} else if (bannerPostBO.getPostPage().equalsIgnoreCase(
							"about_as")) {
						final int totalcost = 1000 * totalmonth;
						bannerPostBO.setTotalcost(totalcost);
					}

					bannerPostBO.setModifiedBy(loginId);

					/*
					 * bannerPostBO.setCreatedBy(this.bannerPost.getCreatedBy());
					 * bannerPostBO.setVersion(this.bannerPost.getVersion());
					 * bannerPostBO.setBannerId(this.bannerPost.getBannerId());
					 */

					/*
					 * Boolean
					 * saveBanner=this.employerService.saveBannerPost(bannerPostBO
					 * );
					 */
					bannerPostBO = this.employerService
							.updateBanner(bannerPostBO);
					if (null != bannerPostBO.getResponse()) {
						model.addAttribute("sucessmessage",
								bannerPostBO.getResponse());
						return "redirect:/renewal_bannerList.html";
					} else {
						model.addAttribute("infomessage",
								"Data has been updated failed,please contact Administrator.");
						return "banner_update";
					}

				}

			}

		} catch (final Exception e) {
			e.printStackTrace();

		}

		return null;

	}

	@SuppressWarnings("null")
	@RequestMapping(value = "/delete_banner", method = RequestMethod.GET)
	public String deleteBanner(Model model, HttpServletRequest request)
			throws MyJobKartException {
		HttpSession session = request.getSession();
		long loginId = (Long) session.getAttribute("loginId");
		try {

			final String id = request.getParameter("id");
			final long bannerId = Long.parseLong(id);
			BannerPostBO deleteProfile = new BannerPostBO();
			deleteProfile.setBannerId(bannerId);
			deleteProfile.setDeleteBy(loginId);
			deleteProfile.setDeletedDate(new Date());
			// deleteProfile.setModifiedBy(bannerId);
			// deleteProfile.setDeleteBy(loginId);
			// deleteProfile.setCreatedBy(bannerId);
			deleteProfile.setIsDelete(false);
			// Date deletedDate = new Date();
			// deleteProfile.setDeletedDate(deletedDate);

			deleteProfile = this.employerService
					.deleteBannerList(deleteProfile);
			if (deleteProfile != null) {
				model.addAttribute("sucessmessage", deleteProfile.getResponse());
			} else {
				model.addAttribute("infomessage",
						"BannerList is not deleted successfully");
			}

		} catch (final Exception e) {
			e.printStackTrace();

		}
		return "redirect:/renewal_bannerList.html";

	}

	@RequestMapping(value = "/banner_details", method = RequestMethod.GET)
	public String bannerDetails(Model model, HttpServletRequest request)
			throws SerialException, SQLException {
		EmployerController.LOGGER.entry();
		final String id = request.getParameter("id");
		try {

			HttpSession session = request.getSession();
			// List<BannerPostBO> allEmployer = new ArrayList<BannerPostBO>();
			long loginId = (Long) session.getAttribute("loginId");
			List<BannerPostBO> allEmployer = this.employerService
					.renewelBannerList(loginId);

			final long bannerId = Long.parseLong(id);

			for (final BannerPostBO details : allEmployer) {
				if (bannerId == details.getBannerId()) {
					model.addAttribute("bannerDetail", details);
				}
			}

		} catch (final NullPointerException ne) {
			if (EmployerController.LOGGER.isDebugEnabled()) {
				EmployerController.LOGGER.debug(ne.getMessage() + ne);
			}
		}

		EmployerController.LOGGER.exit();
		return "banner_details";
	}

	@RequestMapping(value = "/employer_enrolled_details", method = RequestMethod.GET)
	public String productsEnrolledList(Model model, HttpServletRequest request) {
		EmployerController.LOGGER.entry();
		final HttpSession session = request.getSession();
		if (null == session.getAttribute("loginId")) {
			return "redirect:/employer_sign_in.html";
		}
		try {

			long registerId = 0L;

			if (null != session.getAttribute("loginId")) {
				registerId = (Long) session.getAttribute("loginId");
			}
			PaymentBO paymentBO = new PaymentBO();
			paymentBO = this.employerService.productsEnrolledList(registerId);
			this.enrolledList = paymentBO.getEnrolledList();

			model.addAttribute("enrolledList", this.enrolledList);
		} catch (final Exception exception) {
			exception.printStackTrace();

		}
		EmployerController.LOGGER.exit();
		return "employer_enrolled_details";

	}

	@RequestMapping(value = "/view_employer_enrolled_details", method = RequestMethod.GET)
	public String employerEnrolledDetails(Model model,
			HttpServletRequest request) throws ParseException {
		EmployerController.LOGGER.entry();
		final String id = request.getParameter("id");
		try {
			HttpSession session = request.getSession();
			if (null == session.getAttribute("loginId")) {
				return "redirect:/employer_sign_in.html";
			}
			final long enrolledId = Long.parseLong(id);
			for (final PaymentBO paymentBO : this.enrolledList) {

				if (enrolledId == paymentBO.getId()) {
					model.addAttribute("enrolledList", paymentBO);

				}
			}

		} catch (final NullPointerException ne) {
			if (EmployerController.LOGGER.isDebugEnabled()) {
				EmployerController.LOGGER.debug(ne.getMessage() + ne);
			}
		}

		EmployerController.LOGGER.exit();
		return "view_employer_enrolled_details";
	}

	private ResponseObject<PaymentBO> pagination3(int page,
			List<PaymentBO> enrolledList2) {
		// TODO Auto-generated method stub
		final int recordsPerPage = 5;
		int noOfRecords = 0;
		int noOfPages = 0;
		int startingRecord = 1;
		int d;
		if (page > 1) {
			startingRecord = startingRecord * recordsPerPage * page
					- recordsPerPage;
		} else {
			startingRecord = 0;
		}

		final ResponseObject<PaymentBO> ro = new ResponseObject<PaymentBO>();
		final List<PaymentBO> list = enrolledList2;
		if (null != list) {
			noOfRecords = list.size();
		}
		noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
		ro.setCurrentPage(page);
		ro.setRecordsPerPage(recordsPerPage);
		ro.setTotalPages(noOfPages);
		int count = 0;
		final List<PaymentBO> pageList = new ArrayList<PaymentBO>();
		if (null != list) {
			for (int i = startingRecord; i < list.size(); i++) {
				if (count < 5) {
					pageList.add(list.get(i));
				}
				count++;
			}
		}
		final int a = page % 10;
		final int b = page / 10;
		if (a == 1) {
			final int c = a + b * 10;
			ro.setStart(c);
			if (noOfPages > c + 9) {
				ro.setRecords(c + 9);
			} else {
				ro.setRecords(noOfPages);
			}
		}
		if (a == 0) {
			final int f = b - 1;
			d = f * 10 + 1;
			ro.setStart(d);

			if (noOfPages > d + 9) {
				ro.setRecords(d + 9);
			} else {
				ro.setRecords(noOfPages);
			}
		} else {
			d = b * 10 + 1;
			ro.setStart(d);
			if (noOfPages > d + 9) {
				ro.setRecords(d + 9);
			} else {
				ro.setRecords(noOfPages);
			}
		}
		ro.setList(pageList);
		ro.setTotalRecords(noOfRecords);
		ro.setPage(page);
		return ro;
	}

	@RequestMapping(value = "/employer_delete_enrolled_details", method = RequestMethod.GET)
	public String deleteEmployerEnrolledDetails(Model model,
			HttpServletRequest request) throws MyJobKartException {
		EmployerController.LOGGER.entry();
		String id = null;
		long deletedId = 0L;
		try {
			HttpSession session = request.getSession();
			if (null == session.getAttribute("loginId")) {
				return "redirect:/employer_sign_in.html";
			}
			if (request.getParameter("id") != null) {
				id = request.getParameter("id");
				deletedId = Long.parseLong(id);
			}

			PaymentBO savejobBO = new PaymentBO();
			savejobBO.setId(deletedId);
			savejobBO.setIsDeleted(false);
			final Date deletedDate = new Date();
			savejobBO.setDeletedDate(deletedDate);
			savejobBO.setModified(new Date());
			savejobBO = this.employerService
					.deleteJobseekerEnrolledDetails(savejobBO);
			if (null != savejobBO.getResponse()) {
				model.addAttribute("message", savejobBO.getResponse());
			} else {
				model.addAttribute("message",
						"data has been updated failed,please contact Administrator.");

			}
		} catch (final Exception e) {
			e.printStackTrace();
		}
		EmployerController.LOGGER.exit();
		return "redirect:/employer_enrolled_details";
	}

	@RequestMapping(value = "/employer_last_month_enrolled_details", method = RequestMethod.GET)
	public String employerLastMonthEnrolledList(Model model,
			HttpServletRequest request) {

		EmployerController.LOGGER.entry();

		try {
			HttpSession session = request.getSession();
			if (null == session.getAttribute("loginId")) {
				return "redirect:/employer_sign_in.html";
			}
			// List<PaymentBO> lastMonthList = new ArrayList<PaymentBO>();
			int page = 1;
			long registerId = 0L;
			final String paging = request.getParameter("page");

			PaymentBO paymentBO = new PaymentBO();
			paymentBO = this.employerService
					.employerLastMonthEnrolledList(registerId);
			List<PaymentBO> lastMonthList = paymentBO.getEnrolledList();

			if (null != paging) {

				page = Integer.parseInt(paging);
				final ResponseObject<PaymentBO> reponseObject = this
						.pagination3(page, lastMonthList);
				model.addAttribute("lastMonthList", reponseObject);

			} else {

				if (null != session.getAttribute("employerRegisterId")) {

					registerId = (Long) session
							.getAttribute("employerRegisterId");
				}

				if (0 != lastMonthList.size()) {
					@SuppressWarnings({ "unchecked" })
					final ResponseObject<PaymentBO> responseObject = this
					.pagination3(1, lastMonthList);
					model.addAttribute("lastMonthList", responseObject);
				} else {
					model.addAttribute("message",
							"Retrive Failer!..please contact Administrator.. ");
				}

			}

		} catch (final Exception exception) {
			exception.printStackTrace();

		}
		EmployerController.LOGGER.exit();
		return "employer_renewal_list";

	}

	@RequestMapping(value = "/employer_current_month_enrolled_details", method = RequestMethod.GET)
	public String employerproductsEnrolledList(Model model,
			HttpServletRequest request) {
		EmployerController.LOGGER.entry();
		try {
			HttpSession session = request.getSession();
			if (null == session.getAttribute("loginId")) {
				return "redirect:/employer_sign_in.html";
			}
			// List<PaymentBO> employerList = new ArrayList<PaymentBO>();
			int page = 1;
			long registerId = 0L;
			final String paging = request.getParameter("page");

			PaymentBO paymentBO = new PaymentBO();
			paymentBO = this.employerService.productsEnrolledList(registerId);
			List<PaymentBO> employerList = paymentBO.getEnrolledList();

			if (null != paging) {

				page = Integer.parseInt(paging);
				final ResponseObject<PaymentBO> reponseObject = this
						.pagination3(page, employerList);
				model.addAttribute("employerList", reponseObject);

			} else {

				if (null != session.getAttribute("employerRegisterId")) {

					registerId = (Long) session
							.getAttribute("employerRegisterId");

				}

				if (0 != employerList.size()) {
					@SuppressWarnings({ "unchecked" })
					final ResponseObject<PaymentBO> responseObject = this
					.pagination3(1, employerList);
					model.addAttribute("employerList", responseObject);
				} else {
					model.addAttribute("message",
							"Retrive Failer!..please contact Administrator.. ");
				}

			}

		} catch (final Exception exception) {
			exception.printStackTrace();

		}
		EmployerController.LOGGER.exit();
		return "employer_renewal_list";

	}

	@RequestMapping(value = "/employer_renewal_details", method = RequestMethod.GET)
	public String employerRenewalList(Model model, HttpServletRequest request) {
		EmployerController.LOGGER.entry();
		EmployerBO allEmployer = new EmployerBO();
		HttpSession session = request.getSession();
		try {

			String email = null;
			if (null == session.getAttribute("loginId") && null == session.getAttribute("adminId")) {
				return "redirect:/employer_sign_in.html";
			}
			if(null != session.getAttribute("emailId")){
				email = (String) session.getAttribute("emailId");
			}else if(null != request.getParameter("email")){
				email = request.getParameter("email");
			}
			allEmployer = this.employerService.employerRenewalAlert(email);
			List<EmployerBO> employerRenewalDetails = allEmployer
					.getRegisteredList();
			if(null != employerRenewalDetails && employerRenewalDetails.size() != 0){
				model.addAttribute("employerRenewalDetails", employerRenewalDetails);
			}else{
				model.addAttribute("infomessage", "No Record Found.Please Contact Administrator.");
			}
		} catch (final Exception jb) {
			jb.printStackTrace();

		}

		if(null != session.getAttribute("adminId")){
			return "admin_employer_renewal_details";
		}
		
		return "employer_renewal_details";
	}

	@RequestMapping(value = "/payment_details", method = RequestMethod.GET)
	public String addpayment(Model model, HttpServletRequest request) {
		try {

			HttpSession session = request.getSession();
			if (null != session.getAttribute("loginId")) {

				PaymentBO paymentBO = new PaymentBO();

				long payId = (Long) session.getAttribute("employerRegisterId");

				if (payId != 0) {
					session.setAttribute("Payid", "payId");
				}

				if (null != session.getAttribute("employerRegisterId")) {
					long RegId = (long) session
							.getAttribute("employerRegisterId");
					if (RegId != 0) {
						boolean isCheck = employerService
								.getemployerProduct(RegId);

						if (!isCheck) {
							model.addAttribute("infomessage",
									"Your product is already purchased");
							return "redirect:/product_services.html";

						}
					}
				}

				final String type = request.getParameter("type");
				paymentBO.setSelectProduct(type);
				model.addAttribute("payment", paymentBO);
			} else {
				String productType = (String) request.getAttribute("productId");
				model.addAttribute("ProductType", productType);
				return "redirect:/employer_sign_in.html";
			}
		} catch (final Exception exception) {
			exception.printStackTrace();
		}
		return "payment_details";
	}

	@RequestMapping(value = "/payment_details", method = RequestMethod.POST)
	public String addpayment(
			@Valid @ModelAttribute("payment") PaymentBO paymentBO,
			BindingResult result, Model model, HttpServletRequest request)
					throws MyJobKartException {

		EmployerController.LOGGER.entry();

		try {
			final HttpSession session = request.getSession();

			ProductBO product = new ProductBO();
			// List<ProductBO> productList = new ArrayList<ProductBO>();
			product = this.adminService.viewProduct();
			List<ProductBO> productList = product.getRegisteredList();
			if (null != productList) {
				for (ProductBO productBO : productList) {
					if (paymentBO
							.getSelectProduct()
							.trim()
							.equalsIgnoreCase(productBO.getProductType().trim())) {
						final long totalcost = productBO.getProductPrice()
								* paymentBO.getTotalMonth();
						paymentBO.setTotalcost(totalcost);
						paymentBO
						.setSelectProduct(paymentBO.getSelectProduct());

						paymentBO.setCreated(productBO.getCreated());
						paymentBO.setProductType(productBO.getProductType());
						paymentBO.setServices(productBO.getServices());
						paymentBO.setProductPrice(productBO.getProductPrice());
						paymentBO.setDurationDate(productBO.getdurationDate());

						break;
					}
				}
			}

			paymentBO.setIsDeleted(true);
			final long paidId = (Long) session
					.getAttribute("employerRegisterId");
			paymentBO.setPayId(paidId);
			paymentBO.setPaymentMode("paypal");
			paymentList.add(paymentBO);

			model.addAttribute("paymentList", paymentList);
			return "redirect:/product_confirmation.html";

		} catch (final Exception e) {
			e.printStackTrace();
		}
		EmployerController.LOGGER.exit();
		return null;
	}

	@RequestMapping(value = "/product_confirmation", method = RequestMethod.GET)
	public String productConfirmation(Model model, HttpServletRequest request) {
		try {
			HttpSession session = request.getSession();
			SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
			if (null != session.getAttribute("loginId")) {
				PaymentBO paymentBO = null;

				final long empRegId = (Long) session
						.getAttribute("employerRegisterId");
				EmployerBO employerBO = new EmployerBO();
				employerBO.setId(empRegId);
				if (null != paymentList) {
					for (PaymentBO payment : paymentList) {
						paymentBO = new PaymentBO();
						paymentBO.setSelectProduct(payment.getSelectProduct());
						paymentBO.setTotalcost(payment.getTotalcost());
						paymentBO.setTotalMonth(payment.getTotalMonth());
						paymentBO.setPaymentDate(sdf.format(new Date()));
						paymentBO.setPaymentMode("paypal");
						paymentBO.setEmployerRegistration(employerBO);
						paymentBO.setEmpId(empRegId);
						paymentBO.setProductPrice(payment.getProductPrice());
						paymentBO.setProductType(payment.getProductType());
						paymentBO.setServices(payment.getServices());
						paymentBO.setDurationDate(payment.getDurationDate());

						model.addAttribute("paymentList", paymentBO);
						paymentDetails.add(paymentBO);
					}
				}
			} else {
				String productType = (String) request.getAttribute("productId");
				model.addAttribute("ProductType", productType);
				return "redirect:/employer_sign_in.html";
			}
		} catch (final Exception exception) {
			exception.printStackTrace();
		}
		return "product_confirmation";
	}

	/* Paypal payment related implementations */
	@RequestMapping(value = "/paypal_order", method = RequestMethod.GET)
	public void paypalOrder(Model model, HttpServletRequest request,
			HttpServletResponse response) {
		try {

			String paymentMode = request.getParameter("mode");
			if (paymentMode.equalsIgnoreCase("paypal")) {
				handlePayment(model, request, response);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("Paypal order get method has failed:"
						+ ex.getMessage());
			}
			LOGGER.info("Paypal order get method has failed:" + ex.getMessage());
		}
	}

	/*
	 * Handle Payment using options [CreditCard , PayPal]
	 */
	private void handlePayment(Model model, HttpServletRequest request,
			HttpServletResponse response) throws ServletException,
			UnsupportedEncodingException, IOException {

		String redirectUrl = null;
		String paymentMode = request.getParameter("mode");
		String orderAmount = request.getParameter("orderAmount");
		String empId = request.getParameter("empId");
		String productMonth = request.getParameter("pMonth");
		String productType = request.getParameter("pType");
		long employerRegId = Long.parseLong(empId);
		int totalMonth = Integer.parseInt(productMonth);

		// create User Order
		BookingBO orderBO = new BookingBO();
		EmployerBO employerBO = new EmployerBO();
		employerBO.setId(employerRegId);
		orderBO.setCustId(employerRegId);
		orderBO.setEmployerRegistration(employerBO);
		orderBO.setBookingStatus("Created");
		try {
			orderBO = employerService.createOrder(orderBO);
			/*
			 * Create a Order for database store using the request parameters
			 */

			orderBO.setPaymentMode(paymentMode.trim());
			orderBO.setOrderAmount(orderAmount.trim());
			orderBO.setPaymentIntent(MyjobkartResourceBundle
					.getValue("paypal.payment.intent"));
			orderBO.setEmployerRegistration(employerBO);
			orderBO.setTotalMonth(totalMonth);
			orderBO.setProductType(productType);
			orderBO.setCurrency(MyjobkartResourceBundle
					.getValue("paypal.currency"));
			orderBO.setShipping(MyjobkartResourceBundle
					.getValue("paypal.shipping"));
			orderBO.setTax(MyjobkartResourceBundle.getValue("paypal.tax"));

			if (paymentMode.equalsIgnoreCase("paypal")) {

				orderBO.setCancelUrl(WebHelper.getContextPath(request) + "/"
						+ MyjobkartResourceBundle.getValue("cancel.payment")
						+ "?orderId=" + orderBO.getId());
				orderBO.setReturnUrl(WebHelper.getContextPath(request) + "/"
						+ MyjobkartResourceBundle.getValue("execute.payment")
						+ "?orderId=" + orderBO.getId());
			}
		} catch (Exception ex) {
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("Paypal handle payment has failed:"
						+ ex.getMessage());
			}
			LOGGER.info("Paypal handle payment has failed:" + ex.getMessage());
			throw new ServletException(ex);
		}

		// create a Payment object
		Payment payment = null;
		try {

			// Create distinct Payment objects for create payment that
			// holds the appropriate payment methods (credit-card, PayPal)set
			// in the payment object
			payment = AppHelper.createPayment(orderBO);
		} catch (PayPalRESTException pex) {
			pex.printStackTrace();
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("Handle Payment has failed:" + pex.getMessage());
			}
			LOGGER.info("Handle Payment has failed:" + pex.getMessage());
			throw new ServletException(pex);
		}

		// save User Order details
		try {
			employerService.updateOrder(orderBO, payment);
		} catch (Exception ex) {
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("Handle Payment has failed:" + ex.getMessage());
			}
			LOGGER.info("Handle Payment has failed:" + ex.getMessage());
			throw new ServletException(ex);
		}
		if (paymentMode.equalsIgnoreCase("paypal")) {

			// redirect to PayPal for authorization
			redirectUrl = getApprovalURL(payment);
			response.sendRedirect(redirectUrl);
			return;
		}
	}

	private String getApprovalURL(Payment payment)
			throws UnsupportedEncodingException {
		String redirectUrl = null;
		List<Links> links = payment.getLinks();
		for (Links l : links) {
			if (l.getRel().equalsIgnoreCase("approval_url")) {
				redirectUrl = URLDecoder.decode(l.getHref(), "UTF-8");
				break;
			}
		}
		return redirectUrl;
	}

	@RequestMapping(value = "/product_enroll", method = RequestMethod.POST)
	public @ResponseBody
	PaymentBO bidsupdate(HttpServletRequest request) {

		String type = request.getParameter("productType");
		int totalmonth = Integer.parseInt(request.getParameter("noofmonths"));
		final HttpSession session = request.getSession();
		PaymentBO paymentBO = new PaymentBO();

		if (type.equalsIgnoreCase("Gold")) {
			final int totalcost = 2000 * totalmonth;
			paymentBO.setTotalcost(totalcost);
		} else if (type.equalsIgnoreCase("Sliver")) {
			final int totalcost = 1000 * totalmonth;
			paymentBO.setTotalcost(totalcost);
		}

		else if (type.equalsIgnoreCase("Platinum")) {
			final int totalcost = 3000 * totalmonth;
			paymentBO.setTotalcost(totalcost);
		}
		paymentBO.setIsDeleted(true);
		final long paidId = (Long) session.getAttribute("employerRegisterId");
		paymentBO.setPayId(paidId);
		paymentBO = employerService.addPayment(paymentBO);
		session.setAttribute("paid", true);

		paymentBO.setProductType(type);

		return paymentBO;
	}

	@RequestMapping(value = "/product_enroll_trail", method = RequestMethod.POST)
	public @ResponseBody
	PaymentBO producttrail(HttpServletRequest request) {
		final HttpSession session = request.getSession();
		PaymentBO paymentBO = new PaymentBO();

		final long paidId = (Long) session.getAttribute("employerRegisterId");
		try {
			paymentBO.setPayId(paidId);
			paymentBO.setTotalMonth(1);
			paymentBO.setTotalcost(0);
			paymentBO.setProductType("trail");
			paymentBO.setName("trail");
			paymentBO.setIsDeleted(true);
			paymentBO = employerService.addPayment(paymentBO);
			session.setAttribute("paid", true);
		} catch (final Exception e) {
			e.printStackTrace();
		}
		return paymentBO;
	}

	@RequestMapping(value = "/execute_paypal_order", method = RequestMethod.GET)
	public String executePaypalOrder(Model model, HttpServletRequest request,
			HttpServletResponse response) throws FileNotFoundException {

		// Retrieve PayerID form PayPal GET call
		String payerId = request.getParameter("PayerID");

		// Order Id used for internal tracking purpose
		String orderId = (String) request.getParameter("orderId");
		BookingBO bookingBO = employerService.getOrderByOrderId(orderId);

		// Construct a payment for complete payment execution
		Payment payment = new Payment();
		payment.setId(bookingBO.getTransactionId());
		PaymentExecution paymentExecute = new PaymentExecution();
		paymentExecute.setPayerId(payerId);
		HttpSession session = request.getSession();
		try {
			Map<String, String> configurationMap = new HashMap<String, String>();
			configurationMap.put("oauth.EndPoint", PAYPAL_SERVER_URL);
			configurationMap.put("service.EndPoint", PAYPAL_SERVER_URL);
			configurationMap.put("mode", PAYPAL_MODE);
			// set access token
			String accessToken = AccessTokenGenerator.getAccessToken();
			String requestId = UUID.randomUUID().toString();
			APIContext apiContext = new APIContext(accessToken, requestId);
			apiContext.setConfigurationMap(configurationMap);
			payment = payment.execute(apiContext, paymentExecute);
		} catch (PayPalRESTException pex) {
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("Execute paypal order method has failed:"
						+ pex.getMessage());
			}
			LOGGER.info("Execute paypal order method has failed:"
					+ pex.getMessage());
		}
		try {
			bookingBO = new BookingBO();
			employerService.updateOrderStatus(orderId, payment.getState());
			bookingBO = employerService.getOrderByOrderId(orderId);
			model.addAttribute("bookingBOObj", bookingBO);
		} catch (Exception sqlex) {
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("Execute paypal order method has failed:"
						+ sqlex.getMessage());
			}
			LOGGER.info("Execute paypal order method has failed:"
					+ sqlex.getMessage());
		}
		String statusMessages = MyjobkartResourceBundle
				.getValue("success.paypal.payment");
		model.addAttribute("paymentsuccess", statusMessages);

		BookingBO emailModel = new BookingBO();
		emailModel.setMessage(statusMessages);
		emailModel.setConfirmationNo(bookingBO.getConfirmationNo());
		emailModel.setPaymentStatus(bookingBO.getPaymentStatus());
		emailModel.setTransactionId(bookingBO.getTransactionId());
		emailModel.setProductType(bookingBO.getProductType());
		emailModel.setPaymentStatus(bookingBO.getPaymentStatus());
		String mailAddress = (String) session.getAttribute("emailId");
		emailModel.setEmployerEmail(mailAddress);
		String orderStatus = sendMailForProductOrder(emailModel);
		if (orderStatus == "Success") {
			model.addAttribute("successmessage", orderStatus);
			return "booking_success";
		} else {
			model.addAttribute("errormessage", orderStatus);
			return "booking_success";
		}
	}

	@SuppressWarnings("unused")
	private String sendMailForProductOrder(BookingBO emailModel) {
		try {
			String status = null;
			// send mail to user register
			String website = MyjobkartResourceBundle.getValue("website");
			String toaddress = emailModel.getEmployerEmail();
			String subject = MyjobkartResourceBundle
					.getValue("order.status.mail");
			String bodyContent = "productOrderSuccess";
			emailModel.setWebSite(website);
			emailModel.setEmployerName(emailModel.getEmployerRegistration()
					.getFirstName());
			emailModel.setEmailSubject(subject);
			emailModel.setEmailBodyCondent(bodyContent);
			status = emailManager.sendProductEmail(emailModel);
			if (status == "success") {
				return "Success";
			} else {
				return "Failed";
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("Send mail for package order has failed:"
						+ ex.getMessage());
			}
			LOGGER.info("Send mail for package order has failed:"
					+ ex.getMessage());
		}
		return "Success";
	}

	@RequestMapping(value = "/cancel_paypal_order", method = RequestMethod.GET)
	public String cancelPaypalOrder(Model model, HttpServletRequest request,
			HttpServletResponse response) {

		// Order Id used for internal tracking purpose
		String orderId = (String) request.getParameter("orderId");
		HttpSession session = request.getSession();
		BookingBO bookingBO = new BookingBO();
		try {
			employerService.updateOrderStatus(orderId, "cancelled");
			bookingBO = employerService.getOrderByOrderId(orderId);
			String statusMessages = MyjobkartResourceBundle
					.getValue("cancel.paypal.payment");
			model.addAttribute("paymentcancel", statusMessages);

			/*
			 * EmailModel emailModel = new EmailModel();
			 * emailModel.setMessages(statusMessages);
			 * emailModel.setTravelerBOList(travelerBOListGlobal);
			 * emailModel.setPackageBO(packageBOGlobal);
			 * emailModel.setConfirmationNo(bookingBO.getConfirmationNo());
			 * emailModel.setBookingStatus(bookingBO.getBookingStatus());
			 * emailModel.setBookingDate(bookingBO.getTransactionDate());
			 * emailModel.setPaymentStauts(bookingBO.getPaymentStatus());
			 * emailModel.setTransactionNo(bookingBO.getTransactionId()); String
			 * mailAddress = (String) session.getAttribute("emailAddress");
			 * String adminMailAddress = adminService.retrieveAdminEmail(0); if
			 * (null != adminMailAddress && !adminMailAddress.isEmpty()) {
			 * mailAddress = mailAddress + "," + adminMailAddress; } String
			 * agentMailAddress = agentService
			 * .retrieveAgentMailAddress(packageBOGlobal.getId()); if (null !=
			 * agentMailAddress && !agentMailAddress.isEmpty()) { mailAddress =
			 * mailAddress + "," + agentMailAddress; }
			 * emailModel.setEmail(mailAddress);
			 * sendMailForPackageOrder(emailModel);
			 */
		} catch (Exception ex) {
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("Cancel paypal order method has failed:"
						+ ex.getMessage());
			}
			LOGGER.info("Cancel paypal order method has failed:"
					+ ex.getMessage());
		}
		model.addAttribute("bookingBOObj", bookingBO);

		return "booking_success";
	}

	@RequestMapping(value = "/email_verification", method = RequestMethod.POST)
	public @ResponseBody
	String emailVerifications(Model model, @RequestParam String email)
			throws ParseException {

		boolean status = employerService.emailVerification(email);
		if (status) {
			return "Email Id already existed in the table";
		} else {
			return "notExist";

		}
	}

	@RequestMapping(value = "/Mobile_verifications", method = RequestMethod.POST)
	public @ResponseBody
	String Mobile_verifications(Model model, @RequestParam String mobileNumber)
			throws ParseException {

		boolean status = employerService.Mobile_verifications(mobileNumber);
		if (status) {
			return "mobile no already existed in the table";
		} else {
			return "notExist";
		}
	}

	public void companyList(Model model) {
		LOGGER.entry();
		companyList = new ArrayList<String>();
		try {
			List<CompanyEntity> allCompanyList = employerService
					.retrieveCompanyList();
			if (null != allCompanyList && allCompanyList.size() != 0) {
				for (CompanyEntity bo : allCompanyList) {
					companyList.add(bo.getCompanyName());
				}
				model.addAttribute("CompanyList", companyList);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		LOGGER.exit();
	}

	public long getcompany(String companyName) {
		LOGGER.entry();

		long companyId = 0;
		try {
			List<CompanyEntity> allCompanyList = employerService
					.retrieveCompanyList();

			if (null != allCompanyList && allCompanyList.size() != 0) {
				for (CompanyEntity bo : allCompanyList) {

					if (companyName.equalsIgnoreCase(bo.getCompanyName())) {
						companyId = bo.getCompaniesId();
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		LOGGER.exit();
		return companyId;
	}

	@RequestMapping(value = "/employer_clone_jobpost", method = RequestMethod.GET)
	public String clonejobpost(Model model, HttpServletRequest request)
			throws MyJobKartException {
		EmployerController.LOGGER.entry();
		try {
			HttpSession session = request.getSession();
			if (null == session.getAttribute("loginId")) {
				return "redirect:/employer_sign_in.html";
			}
			JobPostBO jobPostBO = new JobPostBO();
			// List<JobPostBO> jobPostBOList = new ArrayList<JobPostBO>();
			final String jobpostid = request.getParameter("id");
			final long jobpostId = Long.parseLong(jobpostid);

			// retrieved the post job by if jobPostId, else employerLoginId
			if (null != jobpostid) {
				jobPostBO.setJobId(jobpostId);
			} 
			jobPostBO = this.employerService.retrieveJobPost(jobPostBO);
			getCurrencyType(model);
			getMinimumExperiences(model);
			getMaximumExperiences(model);
			getCompanyType(model);
			getMaximumSalary(model);
			getMinimumSalary(model);
			getFunctionarea(model);
			getJobType(model);
			getLocation(model);
			getPgqualifications(model);
			getUgqualifications(model);

			JobPostBO jobPostBo = jobPostBO.getJobPostList().get(0);

			if (jobpostId == jobPostBo.getId()) {
				jobPostBo.setStartDate(new Date());
				jobPostBo.setEndDate(null);
				model.addAttribute("jobpost", jobPostBo);
			}else{
				model.addAttribute("jobpost", new JobPostBO());
			}




		} catch (final NullPointerException ne) {
			ne.printStackTrace();
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("employer_clone has been failed:"
						+ ne.getMessage() + ne);
			}
			LOGGER.info("employer_clone has been failed:" + ne.getMessage()
					+ ne);
		}
		EmployerController.LOGGER.exit();
		return "employer_clone";
	}

	@RequestMapping(value = "/employer_clone_jobpost", method = RequestMethod.POST)
	public String clone(@ModelAttribute("clonejob") @Valid JobPostBO jobpost,
			BindingResult result, Model model, HttpServletRequest request)
					throws MyJobKartException, IOException, SerialException,
					SQLException, ValidatorException {
		EmployerController.LOGGER.entry();
		HttpSession session = request.getSession();
		if (null == session.getAttribute("loginId")) {
			return "redirect:/employer_sign_in.html";
		}
		getCurrencyType(model);
		getMinimumExperiences(model);
		getMaximumExperiences(model);
		getMaximumSalary(model);
		getMinimumSalary(model);
		getFunctionarea(model);
		getJobType(model);
		getLocation(model);
		getPgqualifications(model);
		getUgqualifications(model);

		if (result.hasErrors()) {
			return "employer_clone";
		}

		// get the Employer Registeration details
		EmployerProfileBO profile = new EmployerProfileBO();
		final long id = (Long) session.getAttribute("loginId");
		String email = (String) session.getAttribute("emailId");
		profile.setEmailId(email);
		profile = this.employerService.retriveEmployer(profile);

		if (null != profile) {

			jobpost.setCompanyName(profile.getCompanyName());
			jobpost.setCompanyId(profile.getCompanyId());
			jobpost.setIndustryType(profile.getCompanyType());
			jobpost.setEmployerRegistion(profile);
		} 



		if (null != jobpost.getMaxExp() && null != jobpost.getMinExp()) {
			int maxExp = Integer.parseInt(jobpost.getMaxExp());
			int minExp = Integer.parseInt(jobpost.getMinExp());
			if (minExp > maxExp) {

				result.rejectValue("maxExp", "Validate.Experinces");
				return "employer_clone";
			}
		}
		String jobDescription = jobpost.getJobDescription().replaceAll("<br>",
				"");
		String address = jobpost.getAddress().replaceAll("<br>", "");
		String keySkills = jobpost.getKeywords().replaceAll("<br>", "");
		String companyname = jobpost.getCompanyName();
		jobpost.setAddress(address);
		jobpost.setKeywords(keySkills);
		jobpost.setJobDescription(jobDescription);
		jobpost.setCompanyName(companyname);

		String[] maxSalary = null;
		String[] minSalary = null;
		int maxSal = 0;
		int minSal = 0;
		String maxSa = jobpost.getMaxSalary();
		String minSa = jobpost.getMinSalary();
		if (null != jobpost.getMinSalary() && null != jobpost.getMaxSalary()) {
			if (minSa.contains("++")) {
				minSalary = minSa.split("\\s+Lac\\++");
				if (maxSa.contains("++")) {
					maxSalary = maxSa.split("\\sLac\\++");
				} else {
					maxSalary = maxSa.split("\\sLac");
				}
				for (String max : maxSalary) {
					maxSal = Integer.parseInt(max);
				}
				for (String min : minSalary) {
					minSal = Integer.parseInt(min);
				}
				if (minSal > maxSal) {
					result.rejectValue("maxSalary", "Validate.Salary");
					return ("employer_clone");
				}
			} else {
				if (maxSa.contains("++")) {
					maxSalary = maxSa.split("\\sLac\\++");
				} else {
					maxSalary = maxSa.split("\\sLac");
				}
				minSalary = minSa.split("\\s+Lac");
				for (String max : maxSalary) {
					maxSal = Integer.parseInt(max);
				}
				for (String min : minSalary) {
					minSal = Integer.parseInt(min);
				}
				if (minSal > maxSal) {
					result.rejectValue("maxSalary", "Validate.Salary");
					return ("employer_clone");
				}
			}
		}

		Date startDate = jobpost.getStartDate();
		Date endDate = jobpost.getEndDate();
		if ((startDate != null) & (endDate != null)) {
			if (endDate.after(startDate)) {
				jobpost.setStartDate(startDate);
				jobpost.setEndDate(endDate);
			} else {
				result.rejectValue("endDate", "Validate.EndDate");
				return "employer_clone";
			}
		}


		// Job Post set Active and isdelete 
		List<JobPostBO> jobPostList = new ArrayList<JobPostBO>();
		EmployerLoginBO employerLogin = new EmployerLoginBO();
		employerLogin.setId(id);
		jobpost.setCreatedBy(id);
		jobpost.setModifiedBy(id);
		jobpost.setIsDeleted(true);
		jobpost.setIsActive(true);
		jobpost.setEmployerLogin(employerLogin);
		jobpost.setId(0);
		jobPostList.add(jobpost);

		long jobId = employerService.jobPost(jobPostList);

		if (0 != jobId) {
			model.addAttribute("sucessmessage", jobpost.getResponse());
			return "redirect:/employer_job_view.html";
		} else {
			model.addAttribute("infomessage",
					"clone data has been Failed,Please Contact Administrator.");
			return "employer_clone";
		}
	}

	@RequestMapping(value = "jobpost_upload", method = RequestMethod.GET)
	public String jobPostUpload(Model model, HttpServletRequest request) {
		JobPostBO jobPostUpload = new JobPostBO();
		HttpSession session = request.getSession();
		if (null == session.getAttribute("loginId")) {
			return "redirect:/employer_sign_in.html";
		}
		String sucessmessage = request.getParameter("sucessmessage");
		String infomessage = request.getParameter("infomessage");
		if (null != sucessmessage) {
			model.addAttribute("sucessmessage", sucessmessage);
		} else {
			model.addAttribute("infomessage", infomessage);
		}
		model.addAttribute("jobPostUpload", jobPostUpload);
		return "jobpost_upload";
	}

	@RequestMapping(value = "/jobpost_upload", method = RequestMethod.POST)
	public String jobPostUpload(
			@Valid @ModelAttribute("uploadFile") JobPostBO bo,
			BindingResult result, HttpServletRequest request, Model model,
			@RequestParam("uploadCompany") MultipartFile excelfile) {

		HttpSession session = request.getSession();
		if (null == session.getAttribute("loginId")) {
			return "redirect:/employer_sign_in.html";
		}
		try {
			List<JobPostBO> uploadJobPost = new ArrayList<JobPostBO>();
			int i = 0;
			// Creates a workbook object from the uploaded excelfile
			XSSFWorkbook workbook = new XSSFWorkbook(excelfile.getInputStream());
			// Creates a worksheet object representing the first sheet
			XSSFSheet worksheet = workbook.getSheetAt(0);
			// Reads the data in excel file until last row is encountered
			Iterator<Row> rowIterator = worksheet.iterator();

			while (rowIterator.hasNext()) {
				Row row = rowIterator.next(); // Read Rows from Excel document
				System.out.println(row.getRowNum());
				bo = new JobPostBO();
				Iterator<Cell> cellIterator = row.cellIterator();// Read every
				// column
				// for every
				// row that
				// is READ
				while (cellIterator.hasNext()) {
					if (row.getRowNum() >= 1) {

						Cell cell = cellIterator.next(); // Fetch CELL
						switch (cell.getCellType()) { // Identify CELL type

						case Cell.CELL_TYPE_NUMERIC:

							if (DateUtil.isCellDateFormatted(cell)) {
								// Start Date
								if (null == bo.getStartDate()) {
									bo.setStartDate(cell.getDateCellValue());
									break;
								}
								// End Date
								if (null == bo.getEndDate()) {
									bo.setEndDate(cell.getDateCellValue());
									break;
								}

							}

							if (0 == bo.getEmpId()) {
								bo.setEmpId((long) cell.getNumericCellValue());
								break;
							}

							// Vacancies
							if (null == bo.getNoVacancies()) {
								bo.setNoVacancies((long) cell
										.getNumericCellValue());
								break;
							}
							// minExperience
							if (null == bo.getMinExp()) {
								long minExe = (long) (cell
										.getNumericCellValue());
								String minExep = Long.toString(minExe);
								bo.setMinExp(minExep);
								break;
							}
							// MaxExperience
							if (null == bo.getMaxExp()) {
								long max = (long) cell.getNumericCellValue();
								String maxExp = Long.toString(max);
								bo.setMaxExp(maxExp);
								break;
							}

							// Contact Number
							if (null == bo.getContactNo()) {
								bo.setContactNo((long) cell
										.getNumericCellValue());
								break;
							}

						case Cell.CELL_TYPE_STRING:

							// JobTitle
							if (null == bo.getJobTitle()) {
								bo.setJobTitle(cell.getStringCellValue());
								break;
							}
							// JobDescription
							if (null == bo.getJobDescription()) {
								bo.setJobDescription(cell.getStringCellValue());
								break;
							}
							// Key skills
							if (null == bo.getKeywords()) {
								bo.setKeywords(cell.getStringCellValue());
								break;
							}
							// JobType
							if (null == bo.getOtherSalaryDetails()) {
								bo.setOtherSalaryDetails(cell
										.getStringCellValue());
								break;
							}
							// Currency Type
							if (null == bo.getCurrencyType()) {
								bo.setCurrencyType(cell.getStringCellValue());
								break;
							}
							// Min Salary
							if (null == bo.getMinSalary()) {
								bo.setMinSalary(cell.getStringCellValue());
								break;
							}
							// Max Salary
							if (null == bo.getMaxSalary()) {
								bo.setMaxSalary(cell.getStringCellValue());
								break;
							}
							// Job Location
							if (null == bo.getJobLocation()) {
								bo.setJobLocation(cell.getStringCellValue());
								break;
							}
							// Industry Type
							if (null == bo.getIndustryType()) {
								bo.setIndustryType(cell.getStringCellValue());
								break;
							}
							// Function Area
							if (null == bo.getFunctionArea()) {
								bo.setFunctionArea(cell.getStringCellValue());
								break;
							}
							// UG Qualification
							if (null == bo.getUgQualification()) {
								bo.setUgQualification(cell.getStringCellValue());
								break;
							}
							// PG Qualification
							if (null == bo.getPgQualification()) {
								bo.setPgQualification(cell.getStringCellValue());
								break;
							}
							// Company Name
							if (null == bo.getCompanyName()) {
								bo.setCompanyName(cell.getStringCellValue());
								break;
							}
							// Contact Person
							if (null == bo.getContactPerson()) {
								bo.setContactPerson(cell.getStringCellValue());
								break;
							}
							// Company Address
							if (null == bo.getAddress()) {
								bo.setAddress(cell.getStringCellValue());
								break;
							}
							// Posted By
							if (null == bo.getPostedBy()) {
								bo.setPostedBy(cell.getStringCellValue());
								break;
							}
							// Manage Response
							if (null == bo.getResponse()) {
								bo.setJobResponse(cell.getStringCellValue());
								break;
							}

						}
					} else {
						break;
					}

				}
				if (row.getRowNum() >= 1 && bo.getEmpId() > 0) {
					bo.setEmpId(0);
					if (null != (Long) session.getAttribute("loginId")) {
						long id = (Long) session.getAttribute("loginId");
						EmployerLoginBO employerLogin = new EmployerLoginBO();
						employerLogin.setId(id);
						bo.setCreatedBy(id);
						bo.setModifiedBy(id);
						bo.setIsDeleted(true);
						bo.setIsActive(true);
						bo.setEmployerLogin(employerLogin);
					}
					if (null != (Long) session
							.getAttribute("employerRegisterId")) {
						long empReg_id = (long) session
								.getAttribute("employerRegisterId");
						bo.setEmployerRegisterID(empReg_id);
						EmployerProfileBO employerBO = new EmployerProfileBO();
						employerBO.setId(empReg_id);
						bo.setEmployerRegistion(employerBO);

						// EmployerProfileBO reteriveprofile = new
						// EmployerProfileBO();
						// reteriveprofile.setEmployerRegistion(employerBO);
						EmployerProfileBO reteriveprofile = this.employerService
								.retriveEmployer(employerBO);
						if (null != reteriveprofile) {
							bo.setCompanyName(reteriveprofile.getCompanyName());
							EntityBO companyBO = new EntityBO();
							companyBO.setId(reteriveprofile.getCompanyId());
							bo.setCompanyBO(companyBO);
							bo.setCompanyId(reteriveprofile.getCompanyId());
							bo.setOtherSalaryDetails(reteriveprofile
									.getIndustryType());
							bo.setIndustryType(reteriveprofile.getCompanyType());
						} else {
							model.addAttribute("infomessage",
									"Please create and Activate your Profile!");
							return "jobpost_upload";
						}
					}

					// VALIDATION PROCESS START
					Date startDate = bo.getStartDate();
					Date endDate = bo.getEndDate();

					if ((startDate != null) & (endDate != null)) {
						if (endDate.after(startDate)) {
							bo.setStartDate(startDate);
							bo.setEndDate(endDate);

						} else {
							result.rejectValue("endDate", "Validate.EndDate");
							return "jobpost_upload";
						}
					}
					if (null != bo.getMaxExp()) {
						int maxExp = Integer.parseInt(bo.getMaxExp());
						int minExp = Integer.parseInt(bo.getMinExp());
						if (minExp > maxExp) {

							result.rejectValue("maxExp", "Validate.Experinces");
							return "jobpost_upload";
						}
					}

					String response = bo.getResponse();
					if (null != response) {
						model.addAttribute("message",
								"Please select any one of the manage response ");
						return "jobpost_upload";
					}
					// VALIDATION PROCESS END
					uploadJobPost.add(bo);
				}
			}

			long jobPostId = employerService.jobPost(uploadJobPost);
			if (jobPostId != 0) {
				System.out.println("success");

				model.addAttribute("sucessmessage",
						"Your Jobpost has been successfully uploaded");
				return "jobpost_upload";

			} else {
				model.addAttribute("infomessage", "Your Jobpost upload failed");
				return "jobpost_upload";
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return "jobpost_upload";
	}

	@RequestMapping(value = "/captcha_varification", method = RequestMethod.POST)
	public @ResponseBody
	String captchaVarification(@RequestParam String value) {

		if (randomvalue != 0) {

			try {
				int x = Integer.parseInt(value);
			} catch (NumberFormatException ne) {
				return "failed";
			}

			if (randomvalue == Integer.parseInt(value)) {
				return "success";
			}
		}
		return "failed";
	}

	@RequestMapping(value = "/employer_report_view", method = RequestMethod.GET)
	public String retriveEmpactivity(Model model, HttpServletRequest request)
			throws MyJobKartException, SQLException {
		EmployerController.LOGGER.entry();
		EmployerProfileBO reteriveprofile = new EmployerProfileBO();

		// List<EmployerActivityBO> employerActivityBOList = new
		// ArrayList<EmployerActivityBO>();
		HttpSession session = request.getSession();
		long companyId = 0;
		if (null == session.getAttribute("loginId")
				&& null == session.getAttribute("adminId")) {
			return "redirect:/employer_sign_in.html";
		}

		if (null != request.getParameter("id")) {
			String cId = (String) request.getParameter("id");
			companyId = Long.parseLong(cId);
			reteriveprofile.setCompanyId(companyId);
			reteriveprofile = this.employerService
					.retriveEmployer(reteriveprofile);
			session.setAttribute("employerRegId", reteriveprofile
					.getEmployerRegistion().getId());
		}

		EmployerActivityBO employerActivityBO = new EmployerActivityBO();
		if (null != session.getAttribute("employerRegisterId")) {
			long id = (long) session.getAttribute("employerRegisterId");
			employerActivityBO.setEmpId(id);

		} else if (null != reteriveprofile) {
			employerActivityBO.setEmpId(reteriveprofile.getEmployerRegistion()
					.getId());
		}

		List<EmployerActivityBO> employerActivityBOList = this.employerService
				.retriveEmpactivity(employerActivityBO);

		model.addAttribute("employerActivityList", employerActivityBOList);
		model.addAttribute("employerActivityBO", employerActivityBO);

		if (null != session.getAttribute("adminId")) {
			return "admin_employer_report_view";
		}

		return "employer_report_view";

	}

	@RequestMapping(value = "/employer_report_view", method = RequestMethod.POST)
	public String retriveEmpactivity(
			@Valid @ModelAttribute("employerActivityBO") EmployerActivityBO activityBO,
			BindingResult result, Model model, HttpServletRequest request)
					throws ParseException {
		EmployerController.LOGGER.entry();
		HttpSession session = request.getSession();
		List<EmployerActivityBO> employerActivityBOList = null;
		try {

			if (activityBO.getStartDate() != null
					&& activityBO.getEndDate() == null) {
				result.reject("endDate", "Validate.EndDate");
				return "employer_report_view";
			}

			if (null != session.getAttribute("employerRegisterId")) {
				long id = (long) session.getAttribute("employerRegisterId");
				activityBO.setEmpId(id);
			} else if (null != session.getAttribute("employerRegId")) {
				long id = (long) session.getAttribute("employerRegId");
				activityBO.setEmpId(id);
			}

			employerActivityBOList = employerService
					.retriveEmpactivity(activityBO);

			if (employerActivityBOList != null
					&& employerActivityBOList.size() != 0) {
				model.addAttribute("employerActivityList",
						employerActivityBOList);
				if (null != session.getAttribute("adminId")) {
					return "admin_employer_report_view";
				}
				return "employer_report_view";
			} else {
				model.addAttribute("message", "No record found!!");
				if (null != session.getAttribute("adminId")) {
					return "admin_employer_report_view";
				}
				return "employer_report_view";
			}

		} catch (Exception ee) {

			employerActivityBOList = new ArrayList<EmployerActivityBO>();

			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("Retrive Reports Failed");
			}
			LOGGER.info("Retrive Reports Failed");
		}
		LOGGER.exit();
		model.addAttribute("employerActivityBO", new EmployerActivityBO());
		return "redirect:/employer_report_view";
	}

	@RequestMapping(value = "/retrieve_by_pagging", method = RequestMethod.GET)
	public String retrivePagination(
			@Valid @ModelAttribute("employerActivityBO") EmployerActivityBO activityBO,
			BindingResult result, Model model, HttpServletRequest request)
					throws ParseException {
		EmployerController.LOGGER.entry();
		HttpSession session = request.getSession();
		List<EmployerActivityBO> employerActivityBOList = null;
		try {
			// List<EmployerActivityBO> employerActivityBOList = new
			// ArrayList<EmployerActivityBO>();
			if (activityBO.getStartDate() != null
					&& activityBO.getEndDate() == null) {
				result.reject("endDate", "Validate.EndDate");
				return "employer_report_view";
			}

			if (null != session.getAttribute("employerRegisterId")) {
				long id = (long) session.getAttribute("employerRegisterId");
				activityBO.setEmpId(id);
				employerActivityBOList = this.employerService
						.retriveEmpactivity(activityBO);
			}

			if (employerActivityBOList != null
					&& employerActivityBOList.size() != 0) {
				model.addAttribute("employerActivityList",
						employerActivityBOList);
				return "employer_report_view";
			} else {
				model.addAttribute("message", "No record found!!");
				return "employer_report_view";
			}

		} catch (Exception ee) {

			employerActivityBOList = new ArrayList<EmployerActivityBO>();

			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("Retrive Reports Failed");
			}
			LOGGER.info("Retrive Reports Failed");
		}
		LOGGER.exit();
		model.addAttribute("employerActivityBO", new EmployerActivityBO());
		return "redirect:/employer_report_view";
	}

	/*
	 * Employer sub user creation get method
	 */
	@RequestMapping(value = "/employer_create_subuser", method = RequestMethod.GET)
	public String createSubuser(Model model, HttpServletRequest request)
			throws MyJobKartException, SQLException {

		HttpSession session = request.getSession();
		if (null == session.getAttribute("loginId")) {
			return "redirect:/employer_sign_in.html";
		}
		if (null != request.getParameter("successMessage")) {
			model.addAttribute("successMessage",
					request.getParameter("successMessage"));
		}
		long id = (Long) session.getAttribute("loginId");
		long subUserCount = employerService.getSubuserCount(id);
		if (subUserCount >= 10) {
			model.addAttribute("infomessage",
					"You have reached limit for create sub user!");
			return "redirect:/employer_subuser_view";
		}

		model.addAttribute("employerSubUserBO", new EmployerSubuserBO());
		return "employer_create_subuser";
	}

	@RequestMapping(value = "/employer_create_subuser", method = RequestMethod.POST)
	public String createSubuser(
			@Valid @ModelAttribute("employerSubUserBO") EmployerSubuserBO employerSubuserBO,
			BindingResult result, Model model, HttpServletRequest request)
					throws MyJobKartException, SQLException {

		EmployerController.LOGGER.entry();
		HttpSession session = request.getSession();
		if (null == session.getAttribute("loginId")) {
			return "redirect:/employer_sign_in.html";
		}

		if (result.hasErrors()) {
			return "employer_create_subuser";
		}

		if (!employerSubuserBO.getEmailAddress().equalsIgnoreCase(
				employerSubuserBO.getConfirmEmailAddress())) {
			result.rejectValue("confirmEmailAddress", "Validate.Email");
			return "employer_create_subuser";
		}

		if (this.employerService.findbyEmailSubuser(employerSubuserBO
				.getEmailAddress())) {
			model.addAttribute("errorMessage", "Your Account is Already Exists");
			return "employer_create_subuser";
		}

		if (!employerSubuserBO.getPassword().equalsIgnoreCase(
				employerSubuserBO.getConfirmPassword())) {
			result.rejectValue("confirmPassword", "Validate.Password");
			return "employer_create_subuser";
		}

		long id = (Long) session.getAttribute("loginId");
		long empRegId = (Long) session.getAttribute("employerRegisterId");
		employerSubuserBO.setEmployerId(id);
		employerSubuserBO.setEmpRegid(empRegId);
		boolean status = this.employerService
				.createSubuserProfile(employerSubuserBO);

		if (status) {
			model.addAttribute("successmessage", "Subuser created successfully");
			return "redirect:/employer_subuser_view";
		} else {
			model.addAttribute("employerSubUserBO", employerSubuserBO);
			model.addAttribute("errorMessage", "Subuser does not created");
		}
		return "employer_subuser_view";
	}

	@RequestMapping(value = "/employer_subuser_view", method = RequestMethod.GET)
	public String retriveSubusers(Model model, HttpServletRequest request)
			throws MyJobKartException {
		EmployerController.LOGGER.entry();
		try {
			HttpSession session = request.getSession();
			if (null == session.getAttribute("loginId")) {
				return "redirect:/employer_sign_in.html";
			}
			EmployerSubuserBO subuserBO = new EmployerSubuserBO();
			List<EmployerSubuserBO> subUserBOList = new ArrayList<EmployerSubuserBO>();
			EmployerSubuserBO retriveSubuser = new EmployerSubuserBO();
			long empID = Long.parseLong(session.getAttribute("loginId")
					.toString());
			retriveSubuser.setEmployerId(empID);
			List<EmployerSubuserBO> subUserBoList = this.employerService
					.retriveSubusers(retriveSubuser);
			if (null == subUserBoList || subUserBoList.size() == 0) {

				model.addAttribute("Infomessage",
						"No subUser Found. Please create the Sub user");
				return "employer_subuser_view";
			}
			for (EmployerSubuserBO ja : subUserBoList) {
				if (ja.getIsActivedVal()) {
					ja.setStatus("Active");

				} else {
					ja.setStatus("De-Active");
				}
				subuserBO = ja;
				subUserBOList.add(subuserBO);
			}

			if (null == session.getAttribute("loginId")) {
				return "redirect:/employer_sign_in.html";
			}

			String successmessage = request.getParameter("successmessage");
			String Infomessage = request.getParameter("infomessage");
			if (null != request.getParameter("successmessage")) {

				model.addAttribute("successmessage", successmessage);
			} else {

				model.addAttribute("Infomessage", Infomessage);
			}

			model.addAttribute("subUserList", subUserBOList);

		} catch (final MyJobKartException jb) {
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("employeer subuser view:" + jb.getMessage() + jb);
			}
			LOGGER.info("employeer subuser view:" + jb.getMessage() + jb);
		}
		EmployerController.LOGGER.exit();
		return "employer_subuser_view";
	}

	@RequestMapping(value = "/subuser_update_profile", method = RequestMethod.GET)
	public String updateSubuser(Model model, HttpServletRequest request)
			throws MyJobKartException, SQLException {
		EmployerController.LOGGER.entry();
		try {
			HttpSession session = request.getSession();
			if (null == session.getAttribute("loginId")) {
				return "redirect:/employer_sign_in.html";
			}

			String id = request.getParameter("id");

			long employerId = Long.parseLong(id);
			EmployerSubuserBO retrivesubuser = new EmployerSubuserBO();
			retrivesubuser.setId(employerId);
			EmployerSubuserBO employerSubuserBO = employerService
					.retriveSubuser(retrivesubuser);
			if (null != employerSubuserBO) {
				model.addAttribute("subuserupdate", employerSubuserBO);
			} else {
				model.addAttribute("errorMessage",
						"Somthing went wrong. Contact administrators!");
			}

		} catch (final NullPointerException ne) {
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("Employer Subuser profile update get:"
						+ ne.getMessage() + ne);
			}
			LOGGER.info("Employer Subuser profile update get:"
					+ ne.getMessage() + ne);
		}
		EmployerController.LOGGER.exit();
		return "subuser_update_profile";
	}

	@RequestMapping(value = "/subuser_update_profile", method = RequestMethod.POST)
	public String updateSubuser(
			@ModelAttribute("subuserupdate") @Valid EmployerSubuserBO subuserupdate,
			BindingResult result, HttpServletRequest request, Model model)
					throws MyJobKartException, IOException, SerialException,
					SQLException, ValidatorException {
		EmployerController.LOGGER.entry();

		try {
			HttpSession session = request.getSession();
			if (null == session.getAttribute("loginId")) {
				return "redirect :/employer_sign_in.html";
			}

			if (result.hasErrors()) {
				return "subuser_update_profile";
			}

			if (!subuserupdate.getEmailAddress().equalsIgnoreCase(
					subuserupdate.getConfirmEmailAddress())) {
				result.rejectValue("confirmEmailAddress", "Validate.Email");
				return "subuser_update_profile";
			}

			/*if (this.employerService.findbyEmailSubuser(subuserupdate
					.getEmailAddress())) {
				model.addAttribute("errorMessage",
						"Your Account is Already Exists");
				return "subuser_update_profile";
			}*/

			if (!subuserupdate.getPassword().equalsIgnoreCase(
					subuserupdate.getConfirmPassword())) {
				result.rejectValue("confirmPassword", "Validate.Password");
				return "employer_create_subuser";
			}



			subuserupdate = employerService.updateSubuser(subuserupdate);
			if (null != subuserupdate.getResponse()) {
				model.addAttribute("successmessage", subuserupdate.getResponse());
				return "redirect:/employer_subuser_view.html";
			} else {
				model.addAttribute("errormessage",
						"Data Has Been Updated Failed,Please Contact Administrator");
			}

		} catch (Exception ex) {
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("Subuser profile update failed:" + ex.getMessage());
			}
			LOGGER.info("Subuser profile update failed:" + ex.getMessage());
		}
		return "subuser_update_profile";
	}

	@RequestMapping(value = "/subuser_delete_profile", method = RequestMethod.GET)
	public String subuserdelete(Model model, HttpServletRequest request)
			throws MyJobKartException {
		EmployerController.LOGGER.entry();
		try {
			HttpSession session = request.getSession();
			if (null == session.getAttribute("loginId")) {
				return "redirect :/employer_sign_in.html";
			}

			String employerId = request.getParameter("id");
			long deletedId = Long.parseLong(employerId);
			long loginId = (Long) session.getAttribute("loginId");

			EmployerSubuserBO subuserdelete = new EmployerSubuserBO();
			subuserdelete.setId(deletedId);
			subuserdelete.setDeletedBy(loginId);
			subuserdelete.setModifiedBy(loginId);
			subuserdelete.setIsDeletedVal(true);
			final Date deletedDate = new Date();
			subuserdelete.setDeletedDate(deletedDate);
			subuserdelete = this.employerService.subuserdelete(subuserdelete);
			if (null != subuserdelete.getResponse()) {
				model.addAttribute("successmessage", subuserdelete.getResponse());
				return "redirect:/employer_subuser_view.html";
			} else {
				model.addAttribute("errormessage",
						"Data Has Been Updated Failed,Please Contact Administrator");
			}
		} catch (final MyJobKartException jb) {
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("Subuser delete profile failed:"
						+ jb.getErrorCode() + jb);
			}
			LOGGER.info("Subuser delete profile failed:" + jb.getErrorCode()
					+ jb);
		}
		EmployerController.LOGGER.exit();
		return "redirect:/employer_subuser_view.html";
	}

	@RequestMapping(value = "/employerProfileImageDisplay", method = RequestMethod.GET)
	public void showEmployerProfileImage(@RequestParam("id") Integer itemId,
			HttpServletResponse response, HttpServletRequest request)
					throws ServletException, IOException, SQLException,
					MyJobKartException {
		final String id = request.getParameter("id");
		final long imgid = Long.parseLong(id);
		EmployerProfileBO profile = new EmployerProfileBO();
		HttpSession session = request.getSession();
		String email = (String) session.getAttribute("emailId");
		if (email != null) {
			profile.setEmailId(email);
			profile = this.employerService.retriveEmployer(profile);
			// List<EmployerProfileBO> employerProfileList = new
			// ArrayList<EmployerProfileBO>();
			List<EmployerProfileBO> employerProfileList = this.employerService
					.retriveEmployerProfile();
			for (EmployerProfileBO profileBO : employerProfileList) {
				if (profileBO.getId() == imgid) {
					response.setContentType("image/jpeg, image/jpg, image/png, image/gif");
					response.getOutputStream().write(
							profileBO.getCompanyLogo().getBytes(1,
									(int) profileBO.getCompanyLogo().length()));
					response.getOutputStream().close();
				}
			}

		} else {

			// List<EmployerProfileBO> employerProfileList = new
			// ArrayList<EmployerProfileBO>();
			List<EmployerProfileBO> employerProfileList = this.employerService
					.retriveEmployerProfile();

			for (EmployerProfileBO profileBO : employerProfileList) {
				if (profileBO.getId() == imgid) {
					response.setContentType("image/jpeg, image/jpg, image/png, image/gif");
					response.getOutputStream().write(
							profileBO.getCompanyLogo().getBytes(1,
									(int) profileBO.getCompanyLogo().length()));
					response.getOutputStream().close();
				}
			}

		}

	}



	@RequestMapping(value = "/view_recruiters", method = RequestMethod.GET)
	public String viewRecruiters(Model model, HttpServletRequest request)
			throws MyJobKartException {
		EmployerController.LOGGER.entry();

		try {
			List<JobPostBO> pageOneProfileList = new ArrayList<JobPostBO>();
			List<JobPostBO> pageTwoProfileList = new ArrayList<JobPostBO>();
			List<JobPostBO> pageThreeProfileList = new ArrayList<JobPostBO>();
			EmployerProfileBO reteriveprofile = new EmployerProfileBO();
			List<EmployerProfileBO> empProfileList = new ArrayList<EmployerProfileBO>();
			JobPostBO countCompany;
			long compayCount = 0;
			String value = request.getParameter("val");
			List<JobPostBO> employerProfileList = this.employerService
					.viewRecruiters(value);
			if (null != employerProfileList & employerProfileList.size() > 0) {
				for (JobPostBO firstPageCompany : employerProfileList) {
					countCompany = new JobPostBO();
					reteriveprofile = new EmployerProfileBO();
					reteriveprofile.setCompanyId(firstPageCompany
							.getCompanyId());
					reteriveprofile = this.employerService
							.retriveEmployer(reteriveprofile);
					empProfileList.add(reteriveprofile);
					compayCount++;
					if (compayCount <= 20) {
						countCompany.setJobId(firstPageCompany.getJobId());
						countCompany.setCompanyId(firstPageCompany
								.getCompanyId());
						countCompany.setCompanyName(firstPageCompany
								.getCompanyName());
						pageOneProfileList.add(countCompany);
						model.addAttribute("companyList", pageOneProfileList);
					} else if (compayCount <= 40) {
						countCompany.setJobId(firstPageCompany.getJobId());
						countCompany.setCompanyId(firstPageCompany
								.getCompanyId());
						countCompany.setCompanyName(firstPageCompany
								.getCompanyName());
						pageTwoProfileList.add(countCompany);
						model.addAttribute("companyListTwo", pageTwoProfileList);
					} else if (compayCount <= 60) {
						countCompany.setJobId(firstPageCompany.getJobId());
						countCompany.setCompanyId(firstPageCompany
								.getCompanyId());
						countCompany.setCompanyName(firstPageCompany
								.getCompanyName());
						pageThreeProfileList.add(countCompany);
						model.addAttribute("companyListThree",
								pageThreeProfileList);
					}
				}
			} else {
				model.addAttribute("errorMessage", "No company found");
			}
			model.addAttribute("JobDescriptions", empProfileList);
		} catch (Exception ex) {
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("view recruiters:" + ex.getMessage());
			}
			LOGGER.info("view recruiters:" + ex.getMessage());
		}

		EmployerController.LOGGER.exit();
		return "view_recruiters";

	}

	@RequestMapping(value = "/walkin_profile_creation", method = RequestMethod.GET)
	public String createWalkinprofile(Model model, HttpServletRequest request)
			throws MyJobKartException {

		HttpSession session = request.getSession();
		if (null == session.getAttribute("loginId")) {
			return "redirect:/employer_sign_in.html";
		}
		if (null != request.getParameter("successMessage")) {
			model.addAttribute("successMessage",
					request.getParameter("successMessage"));
		}

		companyList(model);
		industryList(model);
		getCurrencyType(model);
		getMinimumExperiences(model);
		getMaximumExperiences(model);
		getMaximumSalary(model);
		getMinimumSalary(model);
		getFunctionarea(model);
		getJobType(model);
		getLocation(model);
		getPgqualifications(model);
		getUgqualifications(model);

		model.addAttribute("walkinBO", new WalkinBO());

		return "walkin_profile_creation";
	}



	@RequestMapping(value = "/walkin_profile_creation", method = RequestMethod.POST)
	public String createWalkinprofile(
			@Valid @ModelAttribute("walkinBO") WalkinBO walkinBO,
			BindingResult result, Model model, HttpServletRequest request)
					throws MyJobKartException, SQLException {

		EmployerController.LOGGER.entry();
		HttpSession session = request.getSession();
		long companyId = 0;
		List<WalkinBO> walkinList = new ArrayList<WalkinBO>();
		companyList(model);
		industryList(model);
		getCurrencyType(model);
		getMinimumExperiences(model);
		getMaximumExperiences(model);
		getMaximumSalary(model);
		getMinimumSalary(model);
		getFunctionarea(model);
		getJobType(model);
		getLocation(model);
		getPgqualifications(model);
		getUgqualifications(model);
		if (null == session.getAttribute("loginId")) {
			return "redirect:/employer_sign_in.html";
		}
		if (null != session.getAttribute("adminId")) {
			model.addAttribute("message", "You have already loggedin!");
			return "redirect:/admin_sign_in.html";
		}
		try {

			if (result.hasErrors()) {
				return "walkin_profile_creation";
			}

			if (!walkinBO.getCompanyName().isEmpty()) {
				walkinBO.getCompanyName();
				companyId = getcompany(walkinBO.getCompanyName());
				if (companyId != 0) {
					walkinBO.setCompanyId(companyId);
				}

			}
			if (!walkinBO.getOtherCompany().isEmpty()) {
				walkinBO.setCompanyName(walkinBO.getOtherCompany());
				companyId = getcompany(walkinBO.getCompanyName());
				if (companyId == 0) {
					long id = adminService.addNewCompany(walkinBO
							.getOtherCompany());
					walkinBO.setCompanyId(id);
				} else {
					walkinBO.setCompanyId(companyId);
				}

			}

			long jobId = 0;
			long empLoginId = (Long) session.getAttribute("loginId");
			long empRegId = (Long) session.getAttribute("employerRegisterId");

			EmployerBO employerBO = new EmployerBO();
			employerBO.setId(empRegId);
			final EmployerLoginBO employerLogin = new EmployerLoginBO();
			employerLogin.setId(empLoginId);

			if (null != walkinBO.getMaxExp() && null != walkinBO.getMinExp()) {
				int maxExp = Integer.parseInt(walkinBO.getMaxExp());
				int minExp = Integer.parseInt(walkinBO.getMinExp());
				if (minExp > maxExp) {

					result.rejectValue("maxExp", "Validate.Experinces");
					return "walkin_profile_creation";
				}
			}
			String response = walkinBO.getResponse();
			if (null != response) {
				model.addAttribute("message",
						"Please select any one of the manage response ");
				return "walkin_profile_creation";
			}

			String jobDescription = walkinBO.getJobDescription().replaceAll(
					"<br>", "");
			String address = walkinBO.getAddress().replaceAll("<br>", "");
			String keySkills = walkinBO.getKeywords().replaceAll("<br>", "");
			walkinBO.setAddress(address);
			walkinBO.setKeywords(keySkills);
			walkinBO.setJobDescription(jobDescription);

			String[] maxSalary = null;
			String[] minSalary = null;
			int maxSal = 0;
			int minSal = 0;
			String maxSa = walkinBO.getMaxSalary();
			String minSa = walkinBO.getMinSalary();

			if (null != walkinBO.getMinSalary()
					&& null != walkinBO.getMaxSalary()) {
				if (minSa.contains("++")) {
					minSalary = minSa.split("\\s+Lac\\++");
					if (maxSa.contains("++")) {
						maxSalary = maxSa.split("\\sLac\\++");
					} else {
						maxSalary = maxSa.split("\\sLac");
					}
					for (String max : maxSalary) {
						maxSal = Integer.parseInt(max);
					}
					for (String min : minSalary) {
						minSal = Integer.parseInt(min);
					}
					if (minSal > maxSal) {
						result.rejectValue("maxSalary", "Validate.Salary");
						return ("walkin_profile_creation");
					}
				} else {
					if (maxSa.contains("++")) {
						maxSalary = maxSa.split("\\sLac\\++");
					} else {
						maxSalary = maxSa.split("\\sLac");
					}
					minSalary = minSa.split("\\s+Lac");
					for (String max : maxSalary) {
						maxSal = Integer.parseInt(max);
					}
					for (String min : minSalary) {
						minSal = Integer.parseInt(min);
					}
					if (minSal > maxSal) {
						result.rejectValue("maxSalary", "Validate.Salary");
						return ("walkin_profile_creation");
					}
				}
			}
			Date startDate = walkinBO.getStartDate();
			Date endDate = walkinBO.getEndDate();
			if ((startDate != null) & (endDate != null)) {
				if (endDate.after(startDate)) {
					walkinBO.setStartDate(startDate);
					walkinBO.setEndDate(endDate);
				} else {
					result.rejectValue("endDate", "Validate.EndDate");
					return "walkin_profile_creation";
				}
			}

			walkinBO.setCreatedBy(empLoginId);
			walkinBO.setModifiedBy(empLoginId);
			walkinBO.setIsActive(true);
			walkinBO.setIsDeleted(true);
			walkinBO.setEmployerId(empLoginId);
			walkinBO.setEmpRegId(empRegId);

			walkinBO.setEmployerLogin(employerLogin);
			walkinBO.setEmployerRegistion(employerBO);
			walkinBO.getJobResponse();
			walkinList.add(walkinBO);

			jobId = this.employerService.createWalkinprofile(walkinList);

			if (0 != jobId) {
				model.addAttribute("sucessmessage",
						"Walkin Jobs has been sucessfully posted");
				return "redirect:/walkin_job_view.html";
			} else {
				model.addAttribute("infomessage", "walkin jobpost failed ");
			}

		} catch (final Exception je) {
			je.printStackTrace();
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("walkin_job post has failed:" + je.getMessage());
			}
			LOGGER.info("walkin job post has failed:" + je.getMessage());
		}

		return "walkin_profile_creation";
	}



	@SuppressWarnings("unused")
	@RequestMapping(value = "/walkin_job_view", method = RequestMethod.GET)
	public String retrieveWalkin(Model model, HttpServletRequest request)
			throws MyJobKartException {
		EmployerController.LOGGER.entry();
		HttpSession session = request.getSession();
		model.addAttribute("viewWalkinsearch", new WalkinBO());
		String jobDescription = null;
		String keySkills = null;
		final List<WalkinBO> list = new ArrayList<WalkinBO>();

		WalkinBO walkinBO = new WalkinBO();
		List<WalkinBO> walkinBOList = null;
		String userType = (String) session.getAttribute("userType");
		model.addAttribute("userType", userType);
		if (null != (Long) session.getAttribute("loginId")) {
			final long id = (Long) session.getAttribute("loginId");
			walkinBO.setId(id);
		}

		if(null!=session.getAttribute("employerRegisterId")){

			long empRegId = (Long) session.getAttribute("employerRegisterId");
			EmployerBO employerBO = new EmployerBO();
			employerBO.setId(empRegId);
			walkinBO.setEmployerRegistion(employerBO);
		}

		walkinBOList = this.employerService.retrieveWalkinJobs(walkinBO);

		/*if (null == session.getAttribute("loginId")) {
			return "redirect:/employer_sign_in.html";
		}*/
		final String sucessmessage = request.getParameter("sucessmessage");
		final String infomessage = request.getParameter("infomessage");
		model.addAttribute("infomessage", infomessage);
		model.addAttribute("sucessmessage", sucessmessage);
		final String paging = request.getParameter("page");
		try {

			if (null == walkinBOList || walkinBOList.size() == 0) {
				if (sucessmessage == null) {
					model.addAttribute("infomessage",
							"No Job Post Found. Please Post a Job ");
				}
			} else {
				if (null != walkinBOList) {
					for (final WalkinBO postjob : walkinBOList) {
						walkinBO = new WalkinBO();

						if (postjob.getJobDescription().length() > 250) {
							jobDescription = postjob.getJobDescription()
									.substring(0, 250);
							walkinBO.setJobDescription(jobDescription);
						} else {
							walkinBO.setJobDescription(postjob
									.getJobDescription());
						}
						if (postjob.getKeywords().length() > 50) {
							keySkills = postjob.getKeywords().substring(0, 50);
							walkinBO.setKeywords(keySkills);
						} else {
							walkinBO.setKeywords(postjob.getKeywords());
						}

						if (postjob.getIsActive()) {
							postjob.setStatus("Active");
						} else {
							postjob.setStatus("De-Active");
						}
						walkinBO.setCompanyName(postjob.getCompanyName());
						walkinBO.setCreated(postjob.getCreated());
						walkinBO.setExperiance(postjob.getExperiance());
						walkinBO.setJobLocation(postjob.getJobLocation());
						walkinBO.setSalary(postjob.getSalary());
						walkinBO.setPostedBy(postjob.getPostedBy());
						walkinBO.setStatus(postjob.getStatus());
						walkinBO.setJobTitle(postjob.getJobTitle());
						walkinBO.setId(postjob.getId());

						list.add(walkinBO);
					}
				}
				model.addAttribute("JobDescription", walkinBOList);

			}


			if(null!= session.getAttribute("adminId")){
				return "admin_walkin_job_view";
			}

		} catch (Exception jb) {

			walkinBOList = new ArrayList<WalkinBO>();
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("walkin_job_view has failed:" + jb.getMessage()
						+ jb);
			}
			LOGGER.info("walkin_job_view has failed:" + jb.getMessage() + jb);
		}
		EmployerController.LOGGER.exit();
		return "walkin_job_view";

	}

	@RequestMapping(value = "/viewWalkinsearch", method = RequestMethod.POST)
	public String viewWalkinsearch(
			@Valid @ModelAttribute("viewWalkinsearch") WalkinBO search,
			BindingResult result, HttpServletRequest request, Model model)
			throws MyJobKartException {
		EmployerController.LOGGER.entry();
		HttpSession session = request.getSession();
		String jobDescription = null;
		String keySkills = null;
		final List<WalkinBO> list = new ArrayList<WalkinBO>();

		WalkinBO walkinBO = new WalkinBO();
		List<WalkinBO> walkinBOList = null;
		String userType = (String) session.getAttribute("userType");
		model.addAttribute("userType", userType);
		if (null != (Long) session.getAttribute("loginId")) {
			final long id = (Long) session.getAttribute("loginId");
			walkinBO.setId(id);
		}

		if (null != session.getAttribute("employerRegisterId")) {

			long empRegId = (Long) session.getAttribute("employerRegisterId");
			EmployerBO employerBO = new EmployerBO();
			employerBO.setId(empRegId);
			walkinBO.setEmployerRegistion(employerBO);
		}

		if (null != search.getSearchElement()) {
			walkinBO.setSearchElement(search.getSearchElement());
		}

		walkinBOList = this.employerService.retrieveWalkinJobs(walkinBO);

		final String sucessmessage = request.getParameter("sucessmessage");
		final String infomessage = request.getParameter("infomessage");
		model.addAttribute("infomessage", infomessage);
		model.addAttribute("sucessmessage", sucessmessage);
		final String paging = request.getParameter("page");
		try {

			if (null == walkinBOList || walkinBOList.size() == 0) {
				if (sucessmessage == null) {
					model.addAttribute("infomessage",
							"No Job Post Found. Please Post a Job ");
				}
			} else {
				if (null != walkinBOList) {
					for (final WalkinBO postjob : walkinBOList) {
						walkinBO = new WalkinBO();

						if (postjob.getJobDescription().length() > 250) {
							jobDescription = postjob.getJobDescription()
									.substring(0, 250);
							walkinBO.setJobDescription(jobDescription);
						} else {
							walkinBO.setJobDescription(postjob
									.getJobDescription());
						}
						if (postjob.getKeywords().length() > 50) {
							keySkills = postjob.getKeywords().substring(0, 50);
							walkinBO.setKeywords(keySkills);
						} else {
							walkinBO.setKeywords(postjob.getKeywords());
						}

						if (postjob.getIsActive()) {
							postjob.setStatus("Active");
						} else {
							postjob.setStatus("De-Active");
						}
						walkinBO.setCompanyName(postjob.getCompanyName());
						walkinBO.setCreated(postjob.getCreated());
						walkinBO.setExperiance(postjob.getExperiance());
						walkinBO.setJobLocation(postjob.getJobLocation());
						walkinBO.setSalary(postjob.getSalary());
						walkinBO.setPostedBy(postjob.getPostedBy());
						walkinBO.setStatus(postjob.getStatus());
						walkinBO.setJobTitle(postjob.getJobTitle());
						walkinBO.setId(postjob.getId());

						list.add(walkinBO);
					}
				}
				model.addAttribute("JobDescription", walkinBOList);

			}

			if (null != session.getAttribute("adminId")) {
				model.addAttribute("viewWalkinsearch", new WalkinBO());
				return "admin_walkin_job_view";
			}

		} catch (Exception jb) {

			walkinBOList = new ArrayList<WalkinBO>();
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("walkin_job_view has failed:" + jb.getMessage()
						+ jb);
			}
			LOGGER.info("walkin_job_view has failed:" + jb.getMessage() + jb);
		}
		EmployerController.LOGGER.exit();
		return "walkin_job_view";

	}
	@RequestMapping(value = "/walkin_jobpost_details", method = RequestMethod.GET)
	public String walkinpostDetails(Model model, HttpServletRequest request)
			throws MyJobKartException {
		EmployerController.LOGGER.entry();
		HttpSession session = request.getSession();
		if ((null == session.getAttribute("loginId")) &&(null == session.getAttribute("adminId"))) {
			return "redirect:/employer_sign_in.html";
		}
		String jobId = request.getParameter("id");
		long jobPostId = Long.parseLong(jobId);
		WalkinBO walkinBO = new WalkinBO();
		if (null != jobId) {
			walkinBO.setId(jobPostId);
		} else {
			final long id = (Long) session.getAttribute("loginId");
			walkinBO.setId(id);
		}
		walkinBO = this.employerService.retriveWalkin(walkinBO);

		final String jobpostid = request.getParameter("id");
		try {
			final long jobpostId = Long.parseLong(jobpostid);
			model.addAttribute("jobDetail", walkinBO);

			if (null != session.getAttribute("loginId")) {
				return "walkin_jobpost_details";
			}

			if(null!= session.getAttribute("adminId")){


				return "admin_walkin_jobpost_details";

			}

		} catch (final NullPointerException ne) {
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("walkin_jobpost_details has failed:"
						+ ne.getMessage() + ne);
			}
			LOGGER.info("walkin_jobpost_details has failed:" + ne.getMessage()
					+ ne);
		}
		EmployerController.LOGGER.exit();

		return "walkin_jobpost_details";

	}

	@RequestMapping(value = "/walkin_delete_jobposting", method = RequestMethod.GET)
	public String deleteWalkins(Model model, HttpServletRequest request)
			throws MyJobKartException {
		EmployerController.LOGGER.entry();
		try {
			final String jobId = request.getParameter("id");
			final long deletedId = Long.parseLong(jobId);
			HttpSession session = request.getSession();
			WalkinBO walkinBO = new WalkinBO();

			final long empRegId = (Long) session
					.getAttribute("employerRegisterId");
			walkinBO.setId(deletedId);
			walkinBO.setDeletedBy(empRegId);
			walkinBO.setModifiedBy(empRegId);
			walkinBO.setIsDeleted(false);
			Date deletedDate = new Date();
			walkinBO.setDeletedDate(deletedDate);
			walkinBO = this.employerService.deleteWalkin(walkinBO);
			if (null != walkinBO.getResponse()) {
				model.addAttribute("sucessmessage", walkinBO.getResponse());
			} else {
				model.addAttribute("infomessage",
						"Data Has Been Updated Failed,Please Contact Administrator");
			}
		} catch (final MyJobKartException jb) {
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("Walkin delete profile failed:"
						+ jb.getErrorCode() + jb);

			}
			LOGGER.info("Walkin delete profile failed:" + jb.getErrorCode()
					+ jb);

		}
		EmployerController.LOGGER.exit();
		return "redirect:/walkin_job_view.html";
	}

	@RequestMapping(value = "/walkin_profile_status", method = RequestMethod.GET)
	public String walkinStatus(Model model, HttpServletRequest request) {
		EmployerController.LOGGER.entry();
		String status = request.getParameter("status");
		String statusvalue[] = status.split(",");
		String activestatus = statusvalue[0];
		String longValue = statusvalue[1];
		long jobId = Long.parseLong(longValue);
		try {
			WalkinBO walkinBO = new WalkinBO();
			walkinBO.setStatus(activestatus);
			walkinBO.setJobId(jobId);
			if (walkinBO.getStatus().equalsIgnoreCase("Active")) {
				walkinBO.setIsActive(false);
			} else {
				walkinBO.setIsActive(true);
			}
			boolean status1 = this.employerService.walkinStatus(walkinBO);
			if (status1) {
				if (walkinBO.getIsActive()) {
					model.addAttribute("sucessmessage", "Profile  is Activate");
				} else {
					model.addAttribute("sucessmessage",
							"Profile is De-Activate");
				}
			} else {
				model.addAttribute("infomessage",
						"Profile is Status not Activated");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("job_post_profile_status has failed:"
						+ ex.getMessage() + ex);
			}
			LOGGER.info("job_post_profile_status has failed:" + ex.getMessage()
					+ ex);
		}
		EmployerController.LOGGER.exit();
		return "redirect:/walkin_job_view.html";

	}

	@RequestMapping(value = "/walkin_update_jobposting", method = RequestMethod.GET)
	public String updateWalkinjobs(Model model, HttpServletRequest request)
			throws MyJobKartException {
		EmployerController.LOGGER.entry();
		try {
			HttpSession session = request.getSession();
			if (null == session.getAttribute("loginId")) {
				return "redirect:/employer_sign_in.html";
			}
			String jobId = request.getParameter("id");
			long jobpostId = Long.parseLong(jobId);
			WalkinBO walkinBO = new WalkinBO();
			walkinBO.setId(jobpostId);
			List<WalkinBO> walkinBOList = this.employerService
					.retrieveWalkinJobs(walkinBO);

			getCurrencyType(model);
			getMinimumExperiences(model);
			getMaximumExperiences(model);
			getCompanyType(model);
			getMaximumSalary(model);
			getMinimumSalary(model);
			getFunctionarea(model);
			getJobType(model);
			getLocation(model);
			getPgqualifications(model);
			getUgqualifications(model);
			companyList(model);
			industryList(model);

			if (null != walkinBOList && walkinBOList.size() != 0) {
				for (final WalkinBO bo : walkinBOList) {
					if (jobpostId == bo.getId()) {
						model.addAttribute("walkinupdate", bo);
					}

				}

			}

		} catch (final NullPointerException ne) {
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("Walkin jobpost  update get:" + ne.getMessage()
						+ ne);
			}
			LOGGER.info("Walkin jobpost update get:" + ne.getMessage() + ne);
		}
		EmployerController.LOGGER.exit();
		return "walkin_update_jobposting";

	}

	@RequestMapping(value = "/walkin_update_jobposting", method = RequestMethod.POST)
	public String updateWalkin(
			@Valid @ModelAttribute("walkinupdate") WalkinBO walkinBO,
			BindingResult result, Model model, HttpServletRequest request)
					throws MyJobKartException, SQLException, ValidatorException {
		EmployerController.LOGGER.entry();
		HttpSession session = request.getSession();
		if (null == session.getAttribute("loginId")) {
			return "redirect:/employer_sign_in.html";
		}
		try {
			if (!walkinBO.getOtherCompany().isEmpty()) {
				walkinBO.setCompanyName(walkinBO.getOtherCompany());
				long companyId = getcompany(walkinBO.getCompanyName());
				if (companyId != 0) {
					walkinBO.setCompanyId(companyId);
				}
			}

			getCurrencyType(model);
			getMinimumExperiences(model);
			getMaximumExperiences(model);
			getCompanyType(model);
			getMaximumSalary(model);
			getMinimumSalary(model);
			getFunctionarea(model);
			getJobType(model);
			getLocation(model);
			getPgqualifications(model);
			getUgqualifications(model);
			companyList(model);
			industryList(model);

			walkinBO = employerService.updateWalkin(walkinBO);

			if (null != walkinBO) {
				model.addAttribute("sucessmessage",
						"Walkin Job Updated Successfully");
				// return "redirect:/walkin_job_view.html";
			} else {
				model.addAttribute("infomessage",
						"Data Has Been Updated Failed,Please Contact Administrator.");
				return "walkin_update_jobposting";
			}
		} catch (Exception jb) {
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("walkin_job_view has failed:" + jb.getMessage()
						+ jb);
			}
			LOGGER.info("walkin_job_view has failed:" + jb.getMessage() + jb);
		}
		EmployerController.LOGGER.exit();
		return "walkin_job_view";

	}

	@RequestMapping(value = "employerjob_alert", method = RequestMethod.GET)
	public @ResponseBody
	List<String> allalert() {
		try {

			List<EmployerjobAlertsVO> employerjobList = employerService.employerjob_alert();

		} catch (Exception ex) {
			ex.printStackTrace();

		}
		return null;
	}

}
