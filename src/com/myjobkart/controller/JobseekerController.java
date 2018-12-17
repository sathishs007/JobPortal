package com.myjobkart.controller;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.rowset.serial.SerialException;
import javax.validation.Valid;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

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
import com.myjobkart.bo.ChangePassword;
import com.myjobkart.bo.CompanyEntity;
import com.myjobkart.bo.EmployerActivityBO;
import com.myjobkart.bo.EmployerBO;
import com.myjobkart.bo.EmployerLoginBO;
import com.myjobkart.bo.EmployerProfileBO;
import com.myjobkart.bo.EntityBO;
import com.myjobkart.bo.JobAlertBO;
import com.myjobkart.bo.JobPostBO;
import com.myjobkart.bo.JobSeekerLoginBO;
import com.myjobkart.bo.JobseekerActivityBO;
import com.myjobkart.bo.JobseekerBO;
import com.myjobkart.bo.JobseekerProfileBO;
import com.myjobkart.bo.PaymentBO;
import com.myjobkart.bo.ResponseObject;
import com.myjobkart.bo.SavejobBO;
import com.myjobkart.bo.ViewJobseekerBO;
import com.myjobkart.exception.JLogger;
import com.myjobkart.exception.MyJobKartException;
import com.myjobkart.service.AdminService;
import com.myjobkart.service.EmployerService;
import com.myjobkart.service.JobSeekerService;
import com.myjobkart.service.JobtrolleyResourceBundle;
import com.myjobkart.utils.CookiesGenerator;
import com.myjobkart.utils.Dropdownutils;
import com.myjobkart.utils.JobtrolleyValidator;

/**
 * Owner : Scube Technologies Created Date: Nov-20-2014 Created by :
 * SathishKumar.s Description : Jobseeker Controller is a controller this will
 * calls the appropriate services to do the respective operations.. Reviewed by
 * :
 */

@Controller
@Scope("session")
@SessionAttributes("/jobseeker")
public class JobseekerController implements Serializable {

	private static final long serialVersionUID = -4295133677060982898L;

	private static final JLogger LOGGER = JLogger
			.getLogger(JobseekerController.class);

	// service class annotations
	@Autowired
	private JobSeekerService<?> jobSeekerService;

	@Autowired
	private EmployerService<?> employerService;



	@Autowired
	private AdminService<?> adminService;

	@Autowired
	ServletContext servletContext;

	List<JobseekerProfileBO> profileList;
	List<JobAlertBO> jobAlertList;
	List<JobseekerProfileBO> educationList;
	List<JobseekerProfileBO> professionalList;

	List<AppliedJobBO> appliedJobList;
	AppliedJobBO appliedJobBO;
	EmployerProfileBO empPro;
	List<ViewJobseekerBO> viewList;
	List<PaymentBO> enrolledJobseekerList;
	JobseekerBO retriveRegister;
	List<JobseekerProfileBO> profileList_global;
	List<JobAlertBO> jobalertList_global;
	List<JobseekerProfileBO> profileList_update;
	List<JobseekerProfileBO> jp_professoinal_list;
	int ranVal = 0;
	int ranvalue = 0;

	private JobseekerProfileBO reteriveprofile;

	/**
	 * This method used to perform the job seeker registration
	 * 
	 * @return
	 */
	@RequestMapping(value = "/jobseeker_sign_up", method = RequestMethod.GET)
	public String jobSeekerSignup(Model model) {
		// Using Captcha
		Random ran = new Random();
		ranvalue = ran.nextInt(10000);
		model.addAttribute("captcha", ranvalue);
		// end
		model.addAttribute("jobseeker", new JobseekerBO());
		return "jobseeker_sign_up";
	}

	/**
	 * This method used to perform the job seeker registration
	 * 
	 * @param jobseekerRegistrationBO
	 * @param result
	 * @param model
	 * @param error
	 * @return
	 * @throws MyJobKartException
	 * @throws IOException
	 * @throws SQLException
	 * @throws SerialException
	 * @throws ValidatorException
	 */
	@RequestMapping(value = "/jobseeker_sign_up", method = RequestMethod.POST)
	public String jobSeekerSignup(
			@Valid @ModelAttribute("jobseeker") JobseekerBO jobseekerBO,
			BindingResult result, Model model, HttpServletRequest request)
					throws MyJobKartException, SerialException, SQLException,
					IOException, ValidatorException {
		JobseekerController.LOGGER.entry();
		try {
			// Using Captcha
			Random ran = new Random();
			ranvalue = ran.nextInt(10000);
			model.addAttribute("captcha", ranvalue);
			// Captcha End
			if (result.hasErrors()) {
				return "jobseeker_sign_up";
			}
			if (!jobseekerBO.getEmailAddress().equalsIgnoreCase(
					jobseekerBO.getConfirmEmailAddress())) {
				result.rejectValue("confirmEmailAddress", "Validate.Email");
				return "jobseeker_sign_up";
			}
			if (!jobseekerBO.getPassword().equalsIgnoreCase(
					jobseekerBO.getConfirmPassword())) {
				result.rejectValue("confirmPassword", "Validate.Password");
				return "jobseeker_sign_up";
			}
			if (this.jobSeekerService
					.findByEmail(jobseekerBO.getEmailAddress())) {
				model.addAttribute("Infomessage", "Email Already Exists");
				return "jobseeker_sign_up";
			}
			long adminId = 0;
			HttpSession session = request.getSession();
			if (null != session.getAttribute("adminId")) {
				adminId = (Long) session.getAttribute("adminId");
			}
			File rootDir = new File(
					this.servletContext.getRealPath("/WEB-INF/images/male.png"));
			BufferedImage image = ImageIO.read(rootDir);
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ImageIO.write(image, "png", baos);
			byte[] res = baos.toByteArray();
			jobseekerBO.setProfileImage(res);
			jobseekerBO = this.jobSeekerService.create(jobseekerBO);
			if (null != jobseekerBO.getErrorCode()) {
				model.addAttribute("errormessage",
						jobseekerBO.getErrorMessage());
			} else {
				model.addAttribute("message", jobseekerBO.getResponse());
				if (0 != adminId) {
					return "redirect:admin_jobseekers.html";
				} else {
					return "redirect:jobseeker_sign_in";
				}
			}
		} catch (final MyJobKartException jb) {
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("Jobseeker sign up failed:" + jb.getErrorCode()
						+ jb);
			}
			LOGGER.info("Jobseeker sign up failed:" + jb.getErrorCode() + jb);
		}
		JobseekerController.LOGGER.exit();
		return "jobseeker_sign_up";
	}

	/**
	 * This method used to perform the login function (jobseeker)
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/jobseeker_sign_in", method = RequestMethod.GET)
	public String jobseekerSignin(Model model, HttpServletRequest request) {
		JobseekerController.LOGGER.entry();
		try {
			HttpSession session = request.getSession();
			if (null != session.getAttribute("loginId")) {
				model.addAttribute("message", "You have already loggedin!");
				model.addAttribute("jobSeekerLogin", new JobSeekerLoginBO());
				return "jobseeker_sign_in";
			}
			if (null != request.getParameter("successmessage"))
				model.addAttribute("sucessmessage",
						request.getParameter("successmessage"));
			if (null != request.getParameter("Infomessage"))
				model.addAttribute("Infomessage",
						request.getParameter("Infomessage"));
			if (null != request.getParameter("message"))
				model.addAttribute("message", request.getParameter("message"));

			model.addAttribute("jobSeekerLogin", new CookiesGenerator()
			.cookiesVerifications(request, new JobSeekerLoginBO(),
					"jobSeeker"));
		} catch (final Exception jb) {
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("Jobseeker sign in failed:" + jb.getMessage() + jb);
			}
			LOGGER.info("Jobseeker sign in failed:" + jb.getMessage() + jb);
		}
		JobseekerController.LOGGER.exit();
		return "jobseeker_sign_in";
	}

	/**
	 * This method used to perform the login function (job seeker)
	 * 
	 * @param model
	 * @return
	 * @throws MyJobKartException
	 * @throws IOException
	 * @throws SQLException
	 * @throws SerialException
	 */
	@RequestMapping(value = "/jobseeker_sign_in", method = RequestMethod.POST)
	public String jobseekerSignin(
			@Valid @ModelAttribute("jobSeekerLogin") JobSeekerLoginBO jobSeekerLoginBO,
			BindingResult result, HttpServletRequest request, Model model,
			HttpServletResponse response) throws MyJobKartException,
			IOException, SerialException, SQLException {
		JobseekerController.LOGGER.entry();
		try {
			HttpSession session = request.getSession();
			if (null != session.getAttribute("jobseekerId")
					|| null != session.getAttribute("loginId")
					|| null != session.getAttribute("adminId")) {
				model.addAttribute("Infomessage", "You have already loggedin!");
				return "jobseeker_sign_in";
			}
			boolean rememberMe = jobSeekerLoginBO.getRememberMe();
			jobSeekerLoginBO = this.jobSeekerService
					.jobseekerSignin(jobSeekerLoginBO);
			if (null != jobSeekerLoginBO && jobSeekerLoginBO.getIsActive()) {
				session.setAttribute("jobseekerId", jobSeekerLoginBO.getId());
				session.setAttribute("registerId",
						jobSeekerLoginBO.getRegisterId());
				session.setAttribute("emailId",
						jobSeekerLoginBO.getEmailAddress());
				session.setAttribute("userType", "Jobseeker");
				session.setAttribute("name", jobSeekerLoginBO.getName());
				// This set of part is used to add the user email and password
				// to cookies.
				Map<String, String> cookiesObject = new HashMap<String, String>();
				cookiesObject.put("email", jobSeekerLoginBO.getEmailAddress());
				cookiesObject.put("password", jobSeekerLoginBO.getPassword());
				CookiesGenerator cookiesGenerator = new CookiesGenerator();
				cookiesGenerator.addCookies(request, response, cookiesObject,
						"jobSeeker", rememberMe);
				return "redirect:/jobseeker_home.html";
			} else {
				model.addAttribute("Infomessage",
						"Your account does not exist or invalid ,please contact Administrator!");
			}
		} catch (final MyJobKartException jb) {
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("Jobseeker login failed:" + jb.getMessage());
			}
			LOGGER.info("Jobseeker login failed:" + jb.getMessage());
		}
		JobseekerController.LOGGER.exit();
		return "jobseeker_sign_in";
	}

	/**
	 * This method used to perform the login function (employer)
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/jobseeker_forget_password", method = RequestMethod.GET)
	public String jobseekerForgetPassword(Model model) {
		model.addAttribute("forgetLogin", new JobSeekerLoginBO());
		return "jobseeker_forget_password";
	}

	/**
	 * This method used to perform the login function (employer)
	 * 
	 * @param model
	 * @return
	 * @throws MyJobKartException
	 * @throws FileNotFoundException
	 */
	@RequestMapping(value = "/jobseeker_forget_password", method = RequestMethod.POST)
	public String jobseekerForgetPassword(
			@Valid @ModelAttribute("forgetLogin") JobSeekerLoginBO forgetLogin,
			BindingResult result, HttpServletRequest request, Model model)
					throws MyJobKartException, FileNotFoundException {
		JobseekerController.LOGGER.entry();
		try {
			boolean loginReset = this.jobSeekerService
					.jobseekerForgetPassword(forgetLogin);
			if (loginReset) {
				model.addAttribute("successmessage",
						"Please check your email for the password reset.");
			} else {
				model.addAttribute("message",
						"Your account does not exist,please contact Administrator.");
			}
		} catch (final MyJobKartException jb) {
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("Jobseeker forget password failed:"
						+ jb.getErrorCode() + jb);
			}
			LOGGER.info("Jobseeker forget password failed:" + jb.getErrorCode()
					+ jb);
		}
		model.addAttribute("forgetLogin", new JobSeekerLoginBO());
		JobseekerController.LOGGER.exit();
		return "jobseeker_forget_password";

	}

	/**
	 * This method used to perform the change password function (job seeker )
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/jobseeker_change_password_after_login", method = RequestMethod.GET)
	public String jobseekerChangePasswordAfterLogin(Model model,
			HttpServletRequest request) {
		HttpSession session = request.getSession();
		if (null == session.getAttribute("jobseekerId")) {
			return "redirect:/jobseeker_sign_in.html";
		}
		ChangePassword changePassword = new ChangePassword();
		model.addAttribute("changePassword", changePassword);
		return "jobseeker_change_password_after_login";
	}

	/**
	 * This method used to perform the change password after login function (job
	 * seeker)
	 * 
	 * @param model
	 * @return
	 * @throws MyJobKartException
	 */
	@RequestMapping(value = "/jobseeker_change_password_after_login", method = RequestMethod.POST)
	public String jobseekerChangePasswordAfterLogin(
			@Valid @ModelAttribute("changePassword") ChangePassword changePassword,
			BindingResult result, HttpServletRequest request, Model model)
					throws MyJobKartException {
		JobseekerController.LOGGER.entry();
		try {
			HttpSession session = request.getSession();
			if (null == session.getAttribute("jobseekerId")) {
				return "redirect:/jobseeker_sign_in.html";
			}
			if (result.hasErrors()) {
				return "jobseeker_change_password_after_login";
			}
			if (!changePassword.getPassword().equalsIgnoreCase(
					changePassword.getConfirmPassword())) {
				result.rejectValue("confirmPassword", "Validate.Password");
				return "jobseeker_change_password_after_login";
			}
			JobSeekerLoginBO LoginBO = new JobSeekerLoginBO();
			String email = (String) session.getAttribute("emailId");
			LoginBO.setEmailAddress(email);
			LoginBO.setPassword(changePassword.getPassword());
			LoginBO.setConfirmPassword(changePassword.getConfirmPassword());
			boolean loginChanged = this.jobSeekerService
					.jobseekerChangePasswordAfterLogin(LoginBO);
			if (loginChanged) {
				model.addAttribute("message",
						"password change has been successfully updated.");
				return "redirect:/jobseeker_home.html";
			} else {
				model.addAttribute("ErrorMessage",
						"Your account does not exist,please contact Administrator.");
			}
		} catch (final Exception jb) {
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("Jobseeker change password after login failed:"
						+ jb.getMessage() + jb);
			}
			LOGGER.info("Jobseeker change password after login failed:"
					+ jb.getMessage() + jb);

		}
		model.addAttribute("jobSeekerLogin", changePassword);
		JobseekerController.LOGGER.exit();
		return "jobseeker_change_password_after_login";
	}

	/**
	 * This method used to perform the change password before login function
	 * (job seeker)
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/jobseeker_change_password", method = RequestMethod.GET)
	public String jobseekerChangePasswordBeforeLogin(Model model) {
		ChangePassword changePassword = new ChangePassword();
		model.addAttribute("changePassword", changePassword);
		return "jobseeker_change_password";
	}

	/**
	 * This method used to perform the change password before login function
	 * (job seeker)
	 * 
	 * @param model
	 * @return
	 * @throws MyJobKartException
	 */
	@RequestMapping(value = "/jobseeker_change_password", method = RequestMethod.POST)
	public String jobseekerChangePasswordBeforeLogin(
			@Valid @ModelAttribute("changePassword") ChangePassword changePassword,
			BindingResult result, HttpServletRequest request, Model model)
					throws MyJobKartException {
		JobseekerController.LOGGER.entry();
		HttpSession session = request.getSession();
		try {
			if (result.hasErrors()) {
				return "jobseeker_change_password";
			}
			if (!changePassword.getPassword().equalsIgnoreCase(
					changePassword.getConfirmPassword())) {
				result.rejectValue("confirmPassword", "Validate.Password");
				model.addAttribute("errmessage", "PassWord should be equal.");
				return "jobseeker_change_password";
			}
			JobSeekerLoginBO loginBO = new JobSeekerLoginBO();
			String email = (String) session.getAttribute("emailId");
			loginBO.setEmailAddress(email);
			loginBO.setPassword(changePassword.getPassword());
			loginBO.setConfirmPassword(changePassword.getConfirmPassword());
			boolean loginChanged = this.jobSeekerService
					.jobseekerChangePasswordAfterLogin(loginBO);
			if (loginChanged) {
				model.addAttribute("successmessage",
						"password change has been successfully updated.");
				return "redirect:/jobseeker_sign_in.html";
			} else {
				model.addAttribute("message",
						"Your account does not exist,please contact Administrator.");
			}
		} catch (final Exception jb) {
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("Jobseeker change password  failed:"
						+ jb.getMessage() + jb);
			}
			LOGGER.info("Jobseeker change password  failed:" + jb.getMessage()
					+ jb);
		}
		model.addAttribute("jobSeekerLogin", changePassword);
		JobseekerController.LOGGER.exit();
		return "jobseeker_change_password";

	}

	public long getcompany(String companyName) {
		LOGGER.entry();
		long companyId = 0;
		try {
			List<CompanyEntity> allCompanyList = employerService
					.retrieveCompanyList();
			if (null != allCompanyList && allCompanyList.size() > 0) {
				for (CompanyEntity bo : allCompanyList) {
					if (companyName.equalsIgnoreCase(bo.getCompanyName())) {
						companyId = bo.getCompaniesId();
						break;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		LOGGER.exit();
		return companyId;
	}

	/**
	 * This method used to perform the job seeker activation function
	 * 
	 * @param model
	 * @return
	 * @throws MyJobKartException
	 */
	@RequestMapping(value = "/jobseeker_activation", method = RequestMethod.GET)
	public String jobseekerActivation(Model model, HttpServletRequest request)
			throws MyJobKartException {
		try {
			JobSeekerLoginBO activation = new JobSeekerLoginBO();
			String emailId = request.getParameter("emailId");
			activation.setEmailAddress(emailId);
			activation = jobSeekerService.active(activation);
			if (activation.getIsActive()) {
				model.addAttribute("activation", activation);
			} else {
				model.addAttribute("activation", activation);
			}
		} catch (final Exception e) {
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("Jobseeker activation failed:" + e.getMessage()
						+ e);
			}
			LOGGER.info("Jobseeker activation failed:" + e.getMessage() + e);
		}
		JobseekerController.LOGGER.exit();
		return "jobseeker_activation";
	}

	/**
	 * This method used to perform the job seeker activation
	 * 
	 * @param activation
	 * @param result
	 * @param request
	 * @param model
	 * @return
	 * @throws MyJobKartException
	 */
	@RequestMapping(value = "/jobseeker_activation", method = RequestMethod.POST)
	public String jobseekerActivation(
			@Valid @ModelAttribute("activation") JobSeekerLoginBO activation,
			BindingResult result, HttpServletRequest request, Model model)
					throws MyJobKartException {
		JobseekerController.LOGGER.entry();
		try {
			String emailId = request.getParameter("emailId");
			boolean isActivation = this.jobSeekerService
					.jobseekerActivation(emailId);
			if (isActivation) {
				model.addAttribute("successmessage",
						"Your account has been successfully activated.");
				return "redirect:/jobseeker_sign_in.html";
			} else {
				model.addAttribute("errormessage",
						"Your account does not exist,please contact Administrator.");
			}
		} catch (final Exception e) {
			if (JobseekerController.LOGGER.isDebugEnabled()) {
				JobseekerController.LOGGER.debug(e.getMessage() + e);
			}
		}
		model.addAttribute("jobSeekerLogin", activation);
		JobseekerController.LOGGER.exit();
		return "jobseeker_activation";
	}

	/**
	 * This Method used to perform the job seeker home(dash board)
	 * 
	 * @param model
	 * @param request
	 * @return
	 * @throws MyJobKartException
	 * @throws SerialException
	 * @throws SQLException
	 * @throws ParseException
	 */
	@RequestMapping(value = "/jobseeker_home", method = RequestMethod.GET)
	public ModelAndView jobseekerhome(Model model, HttpServletRequest request)
			throws MyJobKartException, SerialException, SQLException,
			ParseException {
		JobseekerController.LOGGER.entry();
		try {
			HttpSession session = request.getSession();
			if (null == session.getAttribute("jobseekerId")) {
				return new ModelAndView("redirect:/jobseeker_sign_in.html");
			}
			if (null != request.getParameter("message")) {
				model.addAttribute("message", request.getParameter("message"));
			}
			if (null != request.getParameter("ErrorMessage")) {
				model.addAttribute("ErrorMessage",
						request.getParameter("ErrorMessage"));
			}
			long appliedcount = 0;
			long deappliedcount = 0;
			long totalappliedcount = 0;
			long savecount = 0;
			long profilecount = 0;
			long viewJobseekerListSize = 0;
			long viewTotalJobseeker = 0;
			List<SavejobBO> saveJobList=null;
			final List<JobseekerProfileBO> profileLists = new ArrayList<JobseekerProfileBO>();
			List<ViewJobseekerBO> viewJobseekerList = new ArrayList<ViewJobseekerBO>();
			final long jobseekerId = (Long) session.getAttribute("jobseekerId");
			this.appliedJobList = this.appliedList(jobseekerId);
			if (null != this.appliedJobList && this.appliedJobList.size() != 0) {
				final List<AppliedJobBO> appliedJobs = new ArrayList<AppliedJobBO>();
				totalappliedcount = this.appliedJobList.size();
				model.addAttribute("totalappliedcount", totalappliedcount);
				for (final AppliedJobBO jobBO : this.appliedJobList) {
					this.appliedJobBO = new AppliedJobBO();
					if (jobBO.getIsDeleted() == true) {
						this.appliedJobBO = jobBO;
						appliedJobs.add(this.appliedJobBO);
					}
				}
				this.appliedJobList = appliedJobs;
				appliedcount = this.appliedJobList.size();
				model.addAttribute("activeappliedcount", appliedcount);
				deappliedcount = totalappliedcount - appliedcount;
				model.addAttribute("deactiveappliedcount", deappliedcount);
			} else {
				model.addAttribute("totalappliedcount", totalappliedcount);
				model.addAttribute("deactiveappliedcount", deappliedcount);
				model.addAttribute("activeappliedcount", appliedcount);
			}
			saveJobList = saveList(jobseekerId);
			if (null != saveJobList && saveJobList.size() != 0) {
				for (final SavejobBO savejobBO : saveJobList) {
					savecount =saveJobList.size();
					model.addAttribute("totalsavecount",
							savejobBO.getTotalsavedjob());
					model.addAttribute("totalactivesavecount",
							savejobBO.getTotalactivesavedjob());
					model.addAttribute("totaldeactivesavecount",
							savejobBO.getDeactive());
					model.addAttribute("totalapplysavecount",
							savejobBO.getTotalapplaysavedjob());
				}
			} else {
				model.addAttribute("totalsavecount", savecount);
				model.addAttribute("totalactivesavecount", savecount);
				model.addAttribute("totaldeactivesavecount", savecount);
				model.addAttribute("totalapplysavecount", savecount);
			}
			this.reteriveprofile = new JobseekerProfileBO();
			String email = (String) session.getAttribute("emailId");
			this.reteriveprofile.setEmailId(email);
			this.reteriveprofile = this.jobSeekerService
					.retriveJobseeker(this.reteriveprofile);
			profileList = reteriveprofile.getJobseekerProfileList();
			educationList = reteriveprofile.getJobEductionProfileList();
			if (null != this.reteriveprofile.getJobseekerProfileList()
					&& this.reteriveprofile.getJobseekerProfileList().size() != 0) {
				final long total = this.reteriveprofile
						.getJobseekerProfileList().size();
				for (final JobseekerProfileBO bo : this.reteriveprofile
						.getJobseekerProfileList()) {
					this.reteriveprofile = new JobseekerProfileBO();
					if (bo.getIsActive() == true) {
						this.reteriveprofile = bo;
						profileLists.add(this.reteriveprofile);

					}
				}
				this.profileList = profileLists;
				profilecount = this.profileList.size();
				model.addAttribute("totalprofilecount", total);
				model.addAttribute("profilecount", profilecount);
				model.addAttribute("inactiveprofilecount", total - profilecount);

			} else {
				model.addAttribute("totalprofilecount", profilecount);
				model.addAttribute("profilecount", profilecount);
				model.addAttribute("inactiveprofilecount", profilecount);
			}
			ViewJobseekerBO viewJobSeeker = new ViewJobseekerBO();
			viewJobSeeker.setId(jobseekerId);
			viewJobseekerList = this.jobSeekerService
					.viewJobSeekerHistory(viewJobSeeker);
			if (null != viewJobseekerList && viewJobseekerList.size() > 0) {
				for (ViewJobseekerBO viewJobseeker : viewJobseekerList) {
					viewTotalJobseeker += viewJobseeker.getDays();
				}
				viewJobseekerListSize = viewJobseekerList.size();
			}
			model.addAttribute("viewJobseekerListSize", viewJobseekerListSize);
			model.addAttribute("viewTotalJobseeker", viewTotalJobseeker);
		} catch (final MyJobKartException e) {
			e.printStackTrace();
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("Jobseeker home failed:" + e.getMessage() + e);
			}
			LOGGER.info("Jobseeker home failed:" + e.getMessage() + e);
		}
		JobseekerController.LOGGER.exit();
		return new ModelAndView("jobseeker_home");
	}

	/**
	 * This Method used to perform the update education for jobseeker details
	 * 
	 * @param model
	 * @param request
	 * @param colName
	 * @param idVal
	 * @param passYear
	 * @param percent
	 * @param qualifications
	 * @param department
	 * @param grade
	 * @return
	 * @throws ParseException
	 */

	@RequestMapping(value = "/update_education", method = RequestMethod.GET)
	public @ResponseBody
	List<JobseekerProfileBO> updateEducation(Model model,
			HttpServletRequest request, @RequestParam String colName,
			String idVal, String passYear, String percent,
			String qualifications, String department, String grade)
					throws ParseException {
		long eduId = Long.parseLong(idVal);
		HttpSession session = request.getSession();
		session.setAttribute("eduId", eduId);
		JobseekerProfileBO jobseekerBO = new JobseekerProfileBO();
		jobseekerBO.setEducationId(eduId);
		jobseekerBO.setCollege(colName);
		jobseekerBO.setYearOfPassing(passYear);
		jobseekerBO.setPercentage(percent);
		jobseekerBO.setDegree(qualifications);
		jobseekerBO.setDepartment(department);
		jobseekerBO.setGrade(grade);
		profileList_update.add(jobseekerBO);
		return profileList_update;
	}

	@RequestMapping(value = "/add_education", method = RequestMethod.GET)
	public @ResponseBody
	List<JobseekerProfileBO> addEducation(Model model,
			HttpServletRequest request, @RequestParam String colName,
			String passYear, String percent, String qualifications,
			String department, String grade) throws ParseException {
		HttpSession session = request.getSession();
		JobseekerProfileBO jobseekerBO = new JobseekerProfileBO();
		jobseekerBO.setCollege(colName);
		jobseekerBO.setYearOfPassing(passYear);
		jobseekerBO.setPercentage(percent);
		jobseekerBO.setDegree(qualifications);
		jobseekerBO.setDepartment(department);
		jobseekerBO.setGrade(grade);
		profileList_global.add(jobseekerBO);
		session.setAttribute("ProfileEducationList", profileList_global);
		return profileList_global;
	}

	@RequestMapping(value = "/add_professional_details", method = RequestMethod.GET)
	public @ResponseBody
	List<JobseekerProfileBO> addProfessionalDetails(Model model,
			HttpServletRequest request, @RequestParam String companyName,
			String companyType, String designation, String salary,
			String startDate, String endDate, String exprience)
					throws ParseException, MyJobKartException {
		HttpSession session = request.getSession();
		final long jobseekerId = (Long) session.getAttribute("jobseekerId");
		JobseekerProfileBO jobseekerBO = new JobseekerProfileBO();
		SimpleDateFormat format = new SimpleDateFormat("mm/dd/yyyy");
		jobseekerBO.setCompanyName(companyName);
		jobseekerBO.setCompanyType(companyType);
		jobseekerBO.setDesignation(designation);
		jobseekerBO.setLastSalary(salary);
		jobseekerBO.setStartDate(startDate);
		jobseekerBO.setEndDate(endDate);
		jobseekerBO.setExpStartDate(format.parse(startDate));
		jobseekerBO.setExpEndDate(format.parse(endDate));
		if (null != exprience) {
			if (exprience.equalsIgnoreCase("true")) {
				jobseekerBO.setExpStatus(true);
			} else {
				jobseekerBO.setExpStatus(false);
			}
		}
		EntityBO entity = new EntityBO();
		ArrayList<String> comNameList = new ArrayList<String>();
		comNameList.add(companyName);
		entity.setEntityName(comNameList);
		entity.setCreatedBy(jobseekerId);
		adminService.createCompany(entity);
		long companyID = 0;
		// Get Company Id
		String companyNamelist = jobseekerBO.getCompanyName();
		if (null != companyNamelist) {
			List<String> companyList = new ArrayList<String>();
			companyList.add(companyNamelist);
			companyID = employerService.retriveCompanyId(companyList);
			if (companyID != 0) {
				jobseekerBO.setCompanyId(companyID);
			}
		}
		jp_professoinal_list.add(jobseekerBO);

		if (null != jp_professoinal_list && !jp_professoinal_list.isEmpty()) {
			session.setAttribute("jobprofessionalList", jp_professoinal_list);
		}
		return jp_professoinal_list;
	}

	/**
	 * This method used to perform the profile Create function (jobseeker)
	 * 
	 * @param model
	 * @return
	 * @throws MyJobKartException
	 */
	public void dropdown(Model model) {
		List<String> qualificationList = Dropdownutils.getUgqualifications();
		model.addAttribute("qualification", qualificationList);

		List<String> jobTypeList = Dropdownutils.getJobType();
		model.addAttribute("jobType", jobTypeList);

		List<String> areaList = Dropdownutils.getFunctionarea();
		model.addAttribute("functionArea", areaList);
	}

	@RequestMapping(value = "/jobseeker_create_profile", method = RequestMethod.GET)
	public String jobseekerCreateProfile(Model model, HttpServletRequest request)
			throws MyJobKartException {

		profileList_global = new ArrayList<JobseekerProfileBO>();
		jp_professoinal_list = new ArrayList<JobseekerProfileBO>();

		HttpSession session = request.getSession();
		if (null == session.getAttribute("jobseekerId")) {
			return "redirect:/jobseeker_sign_in.html";
		}
		// All CompanyList
		companyList(model);
		industryList(model);
		getCompanyType(model);
		getCity(model);
		getStates(model);
		getLang(model);
		if (null != request.getParameter("Infomessage"))
			model.addAttribute("Infomessage",
					request.getParameter("Infomessage"));
		JobseekerProfileBO profile = new JobseekerProfileBO();
		String email = (String) session.getAttribute("emailId");
		profile.setEmailId(email);
		profile.setNationality("Indian");
		model.addAttribute("email", profile);
		dropdown(model);
		long jobregisterId = (long) session.getAttribute("registerId");
		this.retriveRegister = new JobseekerBO();
		JobseekerBO jobSeekerBO = new JobseekerBO();
		jobSeekerBO.setId(jobregisterId);
		jobSeekerBO = this.jobSeekerService
				.getJobseekerRegistration(jobSeekerBO);

		profile.setFirstName(jobSeekerBO.getFirstName());
		profile.setLastName(jobSeekerBO.getLastName());
		profile.setPhone(jobSeekerBO.getMobileNo());
		model.addAttribute("profile", profile);
		return "jobseeker_create_profile";
	}

	public void getCompanyType(Model model) {
		List<String> companyTypeList = Dropdownutils.getCompanyType();
		model.addAttribute("companyTypeList", companyTypeList);
	}

	public void getCity(Model model) {
		List<String> cityList = Dropdownutils.getCity();
		model.addAttribute("cityList", cityList);
	}

	public void getStates(Model model) {
		List<String> stateList = Dropdownutils.getStates();
		model.addAttribute("stateList", stateList);
	}

	public void getLang(Model model) {
		List<String> langList = Dropdownutils.getLang();
		model.addAttribute("langList", langList);
	}

	/**
	 * This method used to perform the login function (employer)
	 * 
	 * @param model
	 * @return
	 * @throws MyJobKartException
	 * @throws IOException
	 * @throws SQLException
	 * @throws SerialException
	 * @throws ValidatorException
	 */

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/jobseeker_create_profile", method = RequestMethod.POST)
	public String jobseekerCreateProfile(
			@Valid @ModelAttribute("profile") JobseekerProfileBO profile,
			BindingResult result, HttpServletRequest request, Model model,
			@RequestParam("resumeFile") MultipartFile resumeFile,
			@RequestParam("profileImages") MultipartFile profileImage)
					throws MyJobKartException, IOException, NumberFormatException,
					SerialException, SQLException, ValidatorException {
		JobseekerController.LOGGER.entry();
		List<JobseekerProfileBO> jobprofessionalLists = null;
		long companyId = 0;
		try {
			HttpSession session = request.getSession();
			if (null == session.getAttribute("jobseekerId")) {
				return "redirect:/jobseeker_sign_in.html";
			}
			if (result.hasErrors()) {
				model.addAttribute("education_details", profileList_global);
				model.addAttribute("professional_details", jp_professoinal_list);
				return "jobseeker_create_profile";
			}
			String email = (String) session.getAttribute("emailId");
			long jobSeekerId = (Long) session.getAttribute("jobseekerId");
			profile.setEmailId(email);
			Date startDate = profile.getExpStartDate();
			Date endDate = profile.getExpEndDate();
			if ((startDate != null) & (endDate != null)) {
				if (endDate.after(startDate)) {
					profile.setExpStartDate(startDate);
					profile.setExpEndDate(endDate);
				} else {
					model.addAttribute("Infomessage",
							"Exp Start and End date not be valid");
					return "jobseeker_create_profile";
				}
			}
			if (!profile.getCompanyName().isEmpty()) {
				profile.getCompanyName();
				// get CompanyId
				companyId = getcompany(profile.getCompanyName());
				if (companyId != 0) {
					profile.setCompanyId(companyId);
				}
			}
			// All CompanyList
			companyList(model);
			industryList(model);
			dropdown(model);
			getCompanyType(model);
			getCity(model);
			getStates(model);
			getLang(model);
			if (!profileImage.isEmpty()) {
				profile.setProfileImage(profileImage.getBytes());
				final String photo = this.imagevalied(profileImage);
				if (null != photo) {
					model.addAttribute("message",
							"ProfileImage Only jpeg,jpg,png formats are allowed  ");
					return "jobseeker_create_profile";
				}
			} else {
				final File rootDir = new File(
						this.servletContext
						.getRealPath("/WEB-INF/images/jobseeker.jpg"));
				final BufferedImage image = ImageIO.read(rootDir);
				final ByteArrayOutputStream baos = new ByteArrayOutputStream();
				ImageIO.write(image, "jpg", baos);
				final byte[] res = baos.toByteArray();
				// jobseekerBO.setProfileImage(res);
				profile.setProfileImage(res);
			}

			if (!resumeFile.isEmpty()) {
				final String resume = this.resumevalied(resumeFile);
				if (null != resume) {
					model.addAttribute("message",
							"Resume Only msword,pdf.. formats are allowed");
					return "jobseeker_create_profile";
				}

				if (profileImage.getSize() > 100000) {
					model.addAttribute("message",
							"Please upload max 100 KB size images");
					return "jobseeker_create_profile";
				}
			}

			String profileDescriptions = profile.getProfileDescriptions()
					.replaceAll("<br>", "");
			String keySkills = profile.getKeySkills().replaceAll("<br>", "");
			profile.setKeySkills(keySkills);
			profile.setProfileDescriptions(profileDescriptions);
			final JobSeekerLoginBO jobSeekerLogin = new JobSeekerLoginBO();
			jobSeekerLogin.setId(jobSeekerId);
			if (null != session.getAttribute("ProfileEducationList")) {
				List<JobseekerProfileBO> ProfileEducationList = (List<JobseekerProfileBO>) session
						.getAttribute("ProfileEducationList");
				profile.setJobProfileList(ProfileEducationList);
			}

			if (null != session.getAttribute("jobprofessionalList")) {
				jobprofessionalLists = (List<JobseekerProfileBO>) session
						.getAttribute("jobprofessionalList");

				profile.setJobseekerProfileList(jobprofessionalLists);
			}

			profile.setUploadResume(resumeFile.getBytes());
			profile.setCreatedBy(jobSeekerId);
			profile.setModifiedBy(jobSeekerId);
			profile.setJobSeekerLogin(jobSeekerLogin);
			profile.setIsActive(false);
			profile.setIsDelete(true);

			if (profileList_global.size() == 0) {
				model.addAttribute("successmessage",
						"Educational Details May not be empty!");
				return "jobseeker_create_profile";
			}

			if (null != session.getAttribute("registerId")) {
				long registerId = (Long) session.getAttribute("registerId");
				profile.setModifiedBy(registerId);

			}
			profile.getNoOfExperience();
			profile = this.jobSeekerService.jobseekerCreateProfile(profile);
			session.removeAttribute("ProfileEducationList");
			session.removeAttribute("jobprofessionalList");
			if (null != profile.getResponse()) {
				model.addAttribute("successmessage",
						"Your profile has been successfully created.");
				return "redirect:/jobseeker_profile_view.html";
			} else {
				model.addAttribute("infomessage",
						"Your account does not exist,please contact Administrator.");
				return "jobseeker_create_profile";
			}
		} catch (final Exception ne) {
			ne.printStackTrace();
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("Jobseeker create profile create failed:"
						+ ne.getMessage() + ne);
			}
			LOGGER.info("Jobseeker create profile create failed:"
					+ ne.getMessage() + ne);
		}
		JobseekerController.LOGGER.exit();
		return null;

	}

	/**
	 * This method used to perform the retrieve jobseeker_profile function
	 * (jobseeker)
	 * 
	 * @param model
	 * @param request
	 * @return
	 * @throws MyJobKartException
	 * @throws IOException
	 * @throws SQLException
	 * @throws SerialException
	 */
	@RequestMapping(value = "/jobseeker_profile_view", method = RequestMethod.GET)
	public String retriveJobseeker(Model model, HttpServletRequest request)
			throws MyJobKartException, IOException, SerialException,
			SQLException {
		JobseekerController.LOGGER.entry();
		HttpSession session = request.getSession();
		try {

			if ((null == session.getAttribute("jobseekerId"))&& (null == session.getAttribute("adminId"))) {
				return "redirect:/jobseeker_sign_in.html";
			}
			if (null != request.getParameter("successmessage")) {
				model.addAttribute("successmessage",
						request.getParameter("successmessage"));
			} else {
				model.addAttribute("Infomessage",
						request.getParameter("Infomessage"));
			}
			final List<JobseekerProfileBO> profileLists = new ArrayList<JobseekerProfileBO>();
			// Display Tags
			String userType = (String) session.getAttribute("userType");
			model.addAttribute("userType", userType);
			String email = null;
			//	model.addAttribute("userType", userType);
			this.reteriveprofile = new JobseekerProfileBO();
			JobseekerProfileBO profile = new JobseekerProfileBO();
			model.addAttribute("search", profile);
			if(null != session.getAttribute("emailId")){
			email = (String) session.getAttribute("emailId");
			}else if(null != request.getParameter("email")){
			email = request.getParameter("email");
			}
			
			this.reteriveprofile.setEmailId(email);
			this.reteriveprofile = this.jobSeekerService
					.retriveJobseeker(this.reteriveprofile);
			if (null != this.reteriveprofile.getJobseekerProfileList()
					&& 0 != this.reteriveprofile.getJobseekerProfileList()
					.size()) {
				for (JobseekerProfileBO bo : this.reteriveprofile
						.getJobseekerProfileList()) {
					this.reteriveprofile = new JobseekerProfileBO();
					if (bo.getIsActive()) {
						bo.setStatus("Active");
					} else {
						bo.setStatus("De-Active");
					}

					session.setAttribute("jobseekerProfileId",
							bo.getId());
					this.reteriveprofile = bo;
					profileLists.add(this.reteriveprofile);
				}
				this.profileList = profileLists;
				if (null != this.profileList) {
					model.addAttribute("searchJobseeker", this.profileList);
				}
			} else {
				model.addAttribute("Infomessage",
						"please create your profile.. ");
			}
		} catch (final MyJobKartException jb) {
			jb.printStackTrace();
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("Jobseeker profile view failed:"
						+ jb.getErrorCode() + jb);
			}
			LOGGER.info("Jobseeker profile view failed:" + jb.getErrorCode()
					+ jb);
		}
		if (null != session.getAttribute("jobseekerId")) {
			return "jobseeker_profile_view";
		}

		if(null!= session.getAttribute("adminId")){


			return "admin_jobseeker_profiles";

		}
		return "jobseeker_profile_view";
	}

	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public String search(
			@Valid @ModelAttribute("search") JobseekerProfileBO updateProfile,
			BindingResult result, HttpServletRequest request, Model model) {
		HttpSession session = request.getSession();
		if (this.profileList != null) {
			List<JobseekerProfileBO> profileLists = new ArrayList<JobseekerProfileBO>();
			for (JobseekerProfileBO profileBO : this.profileList) {
				if (profileBO
						.getLocation()
						.toLowerCase()
						.contains(
								updateProfile.getSearchElement().toLowerCase())) {
					this.reteriveprofile = profileBO;
					profileLists.add(this.reteriveprofile);
				}
			}
			model.addAttribute("searchJobseeker", profileLists);
		} else {
			model.addAttribute("Infomessage", "No record found..");
		}
		if (null != session.getAttribute("jobseekerId")) {
			return "jobseeker_profile_view";
		}

		if(null!= session.getAttribute("adminId")){


			return "admin_jobseeker_profiles";

		}

		return "jobseeker_profile_view";
	}

	@SuppressWarnings("rawtypes")
	private ResponseObject jobpagination1(int page, List<JobseekerBO> dataLsit) {
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
		final ResponseObject<JobseekerBO> ro = new ResponseObject<JobseekerBO>();
		final List<JobseekerBO> list = dataLsit;
		if (null != list) {
			noOfRecords = list.size();
		}
		noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
		ro.setCurrentPage(page);
		ro.setRecordsPerPage(recordsPerPage);
		ro.setTotalPages(noOfPages);
		int count = 0;
		final List<JobseekerBO> pageList = new ArrayList<JobseekerBO>();
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

	@RequestMapping(value = "/jobseeker_profile_details", method = RequestMethod.GET)
	public String jobseekerDetails(Model model, HttpServletRequest request)
			throws SQLException {
		HttpSession session = request.getSession();

		try {
			if (null != session.getAttribute("userType")) {
				String userType = (String) session.getAttribute("userType");
				model.addAttribute("userType",userType);

				if (userType.equalsIgnoreCase("admin") || userType.equalsIgnoreCase("Employer") ||userType.equalsIgnoreCase("Jobseeker")) {
					JobseekerProfileBO jobSeekerProfileBO = null;
					 long jobseekerId = 0;
					List<JobseekerProfileBO> profileBOList = new ArrayList<JobseekerProfileBO>();
					if(null != request.getParameter("id")){
					final String id = request.getParameter("id");
                    jobseekerId = Long.parseLong(id);
					
					this.reteriveprofile = new JobseekerProfileBO();
					reteriveprofile.setId(jobseekerId);
					}
					this.reteriveprofile = this.jobSeekerService
							.retriveJobseeker(this.reteriveprofile);
					if (null != this.reteriveprofile.getJobseekerProfileList() &&0 != this.reteriveprofile.getJobseekerProfileList().size()) {
						for (JobseekerProfileBO profile : reteriveprofile
								.getJobseekerProfileList()) {
							jobSeekerProfileBO = new JobseekerProfileBO();
							// jobSeekerProfileBO.setJobEductionProfileList(profile.getJobEductionProfileList());
							jobSeekerProfileBO = profile;
							if (jobseekerId == jobSeekerProfileBO.getId()) {
								if (null != jobSeekerProfileBO.getUploadResume()
										&& jobSeekerProfileBO.getUploadResume()
										.length() > 0) {
									jobSeekerProfileBO.setResumeVisibility(true);
								} else {
									jobSeekerProfileBO.setResumeVisibility(false);
								}
							}
						}
						model.addAttribute("jobseekerDetail", jobSeekerProfileBO);
						profileBOList = reteriveprofile.getJobEductionProfileList();
					}
					
					// Adding Jobseeker Experiences
					if(null != reteriveprofile.getJobprofessionalList() && reteriveprofile.getJobprofessionalList().size()>0){
					List<JobseekerProfileBO> professionalList=reteriveprofile.getJobprofessionalList();
						model.addAttribute("professionalList", professionalList);	
					}else{
						model.addAttribute("Infomessage", "May be the FRESHER Candidate!");
						
					}
					
					if( userType.equalsIgnoreCase("Employer")){
						if(null != request.getParameter("status")){
						String saveResumeStatus = request.getParameter("status");
						model.addAttribute("SaveResumeStatus", saveResumeStatus);
						}
					}
					model.addAttribute("educationalDetail", profileBOList);
					JobseekerProfileBO jobseekerProfileBO = new JobseekerProfileBO();
					model.addAttribute("searchjobseeker", jobseekerProfileBO);
					model.addAttribute("similar", this.profileList);
				}
			}else{
				return "redirect:/jobseeker_sign_in.html";
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		if (null != session.getAttribute("loginId")) {
			return "Employer_view_jobseeker_details";
		}

		if(null!= session.getAttribute("adminId")){


			return "admin_jobseeker_profile_details";

		}
		return "jobseeker_profile_details";
	}

	@RequestMapping(value = "/jobseeker_update_personal", method = RequestMethod.GET)
	public String updatePersonal(Model model, HttpServletRequest request)
			throws SerialException, MyJobKartException, SQLException {
		profileList_global = new ArrayList<JobseekerProfileBO>();
		jp_professoinal_list = new ArrayList<JobseekerProfileBO>();
		profileList_update = new ArrayList<JobseekerProfileBO>();

		JobseekerController.LOGGER.entry();
		HttpSession session = request.getSession();
		if (null == session.getAttribute("jobseekerId")) {
			return "redirect:/jobseeker_sign_in.html";
		}
		getCompanyType(model);
		industryList(model);
		getCity(model);
		getStates(model);
		getLang(model);

		final String id = request.getParameter("id");
		JobseekerProfileBO profileBO = new JobseekerProfileBO();

		final long jobseekerId = Long.parseLong(id);
		profileBO.setId(jobseekerId);
		session.setAttribute("jobprofileId", jobseekerId);

		profileBO = this.jobSeekerService.retriveJobseeker(profileBO);
		educationList = profileBO.getJobEductionProfileList();
		model.addAttribute("eduupdateProfile", educationList);
		model.addAttribute("jobProfileId", jobseekerId);

		for (final JobseekerProfileBO profile : this.profileList) {
			if (jobseekerId == profile.getId()) {
				this.reteriveprofile = profile;
				dropdown(model);
				model.addAttribute("updateProfile", profile);
			}
		}
		JobseekerController.LOGGER.exit();
		return "jobseeker_update_personal";
	}

	@RequestMapping(value = "/jobseeker_update_personal", method = RequestMethod.POST)
	public String updatePersonal(
			@Valid @ModelAttribute("updateProfile") JobseekerProfileBO updateProfile,
			BindingResult result, HttpServletRequest request, Model model,
			@RequestParam("resumeFile") MultipartFile resumeFile,
			@RequestParam("profileImages") MultipartFile profileImage)
					throws MyJobKartException, IOException, SerialException,
					SQLException, ValidatorException {
		JobseekerController.LOGGER.entry();
		HttpSession session = request.getSession();
		if (null == session.getAttribute("jobseekerId")) {
			return "redirect:/jobseeker_sign_in.html";
		}

		try {
			String email = (String) session.getAttribute("emailId");
			this.reteriveprofile.setEmailId(email);
			this.reteriveprofile = this.jobSeekerService
					.retriveJobseeker(this.reteriveprofile);

			getCompanyType(model);
			industryList(model);
			getCity(model);
			getStates(model);
			getLang(model);
			dropdown(model);

			String emails = (String) session.getAttribute("emailId");
			updateProfile.setEmailId(emails);
			Date startDate = updateProfile.getExpStartDate();
			Date endDate = updateProfile.getExpEndDate();
			if ((startDate != null) & (endDate != null)) {
				if (endDate.after(startDate)) {
					updateProfile.setExpStartDate(startDate);
					updateProfile.setExpEndDate(endDate);
				} else {
					model.addAttribute("message", "Date may not be valid");
					return "jobseeker_update_profile";
				}
			}

			String profileDescriptions = updateProfile.getProfileDescriptions()
					.replaceAll("<br>", "");
			String keySkills = updateProfile.getKeySkills().replaceAll("<br>",
					"");
			updateProfile.setProfileDescriptions(profileDescriptions);
			updateProfile.setKeySkills(keySkills);

			if (!profileImage.isEmpty()) {
				updateProfile.setProfileImage(profileImage.getBytes());
				final String photo = this.imagevalied(profileImage);
				if (null != photo) {
					model.addAttribute("message",
							"ProfileImage Only jpeg,jpg,png formats are allowed  ");
					return "jobseeker_create_profile";
				}

			} else if (null != this.reteriveprofile.getProfileImage()) {

				updateProfile.setProfileImage(this.reteriveprofile
						.getProfileImage().getBytes(
								1,
								(int) this.reteriveprofile.getProfileImage()
								.length()));
			} else {
				final File rootDir = new File(
						this.servletContext
						.getRealPath("/WEB-INF/images/jobseeker.jpg"));
				final BufferedImage image = ImageIO.read(rootDir);
				final ByteArrayOutputStream baos = new ByteArrayOutputStream();
				ImageIO.write(image, "jpg", baos);
				final byte[] res = baos.toByteArray();
				// jobseekerBO.setProfileImage(res);
				updateProfile.setProfileImage(res);
			}

			if (!resumeFile.isEmpty()) {
				updateProfile.setUploadResume(resumeFile.getBytes());
				if (!resumeFile.isEmpty()) {
					final String resume = this.resumevalied(resumeFile);
					if (null != resume) {
						model.addAttribute("message",
								"Resume Only msword,pdf.. formats are allowed");
						return "jobseeker_update_personal";
					}
				}

			} else if (null != this.reteriveprofile.getUploadResume()) {

				updateProfile.setUploadResume(this.reteriveprofile
						.getUploadResume().getBytes(
								1,
								(int) this.reteriveprofile.getUploadResume()
								.length()));
			}

			if (null != updateProfile.getFirstName()) {
				final String status = JobtrolleyValidator
						.validate(updateProfile.getFirstName());
				if (null != status) {
					model.addAttribute("message", status);
					return "jobseeker_update_personal";
				}

			}
			if (null != updateProfile.getLastName()) {
				final String status = JobtrolleyValidator
						.validate(updateProfile.getLastName());
				if (null != status) {
					model.addAttribute("message", status);
					return "jobseeker_update_personal";
				}

			}

			long jobSeekerId = (Long) session.getAttribute("jobseekerId");
			if (null != reteriveprofile) {
				updateProfile.setCreatedBy(this.reteriveprofile.getCreatedBy());
				updateProfile.setIsActive(this.reteriveprofile.getIsActive());
				updateProfile.setModifiedBy(jobSeekerId);
				updateProfile.setIsDelete(true);
				updateProfile.setJobSeekerLogin(this.reteriveprofile
						.getJobSeekerLogin());
				updateProfile.setVersion(this.reteriveprofile.getVersion());
			}
			if (null == updateProfile.getNoOfExperience()) {
				updateProfile.setNoOfExperience(this.reteriveprofile
						.getNoOfExperience());
			}

			if (result.hasErrors()) {
				return "jobseeker_update_personal";
			}

			updateProfile = this.jobSeekerService.updateProfile(updateProfile);
			if (null != updateProfile.getResponse()) {
				model.addAttribute("profileId", updateProfile.getId());
				model.addAttribute("successmessage",
						updateProfile.getResponse());
				return "redirect:/jobseeker_profile_view.html";

			} else {
				model.addAttribute("Infomessage",
						"data has been updated failed,please contact Administrator.");
				return "jobseeker_update_personal";
			}
		} catch (Exception jb) {
			jb.printStackTrace();
		}
		JobseekerController.LOGGER.exit();
		return null;
	}

	/**
	 * This method used to perform the update jobseeker_profile function
	 * (jobseeker)
	 * 
	 * @param model
	 * @param request
	 * @return
	 * @throws SQLException
	 * @throws MyJobKartException
	 * @throws SerialException
	 */

	@RequestMapping(value = "/jobseeker_update_profile", method = RequestMethod.GET)
	public String updateProfile(Model model, HttpServletRequest request)
			throws SerialException, MyJobKartException, SQLException {
		JobseekerController.LOGGER.entry();
		profileList_global = new ArrayList<JobseekerProfileBO>();
		jp_professoinal_list = new ArrayList<JobseekerProfileBO>();
		profileList_update = new ArrayList<JobseekerProfileBO>();
		getCompanyType(model);
		industryList(model);
		getCity(model);
		getStates(model);
		getLang(model);
		HttpSession session = request.getSession();
		if (null == session.getAttribute("jobseekerId")) {
			return "redirect:/jobseeker_sign_in.html";
		}
		final String id = request.getParameter("id");
		JobseekerProfileBO profileBO = new JobseekerProfileBO();

		final long jobseekerId = Long.parseLong(id);
		profileBO.setId(jobseekerId);

		profileBO = this.jobSeekerService.retriveJobseeker(profileBO);
		educationList = profileBO.getJobEductionProfileList();
		model.addAttribute("eduupdateProfile", educationList);
		model.addAttribute("jobProfileId", jobseekerId);
		for (final JobseekerProfileBO profile : this.profileList) {
			if (jobseekerId == profile.getId()) {
				this.reteriveprofile = profile;
				dropdown(model);
				model.addAttribute("updateProfile", profile);
			}
		}
		JobseekerController.LOGGER.exit();
		return "jobseeker_update_profile";
	}

	/**
	 * This method used to perform the update jobseeker_profile function
	 * (jobseeker)
	 * 
	 * @param updateProfile
	 * @param result
	 * @param request
	 * @param model
	 * @return
	 * @throws MyJobKartException
	 * @throws IOException
	 * @throws SQLException
	 * @throws SerialException
	 * @throws ValidatorException
	 */
	@RequestMapping(value = "/jobseeker_update_profile", method = RequestMethod.POST)
	public String updateProfile(
			@Valid @ModelAttribute("updateProfile") JobseekerProfileBO updateProfile,
			BindingResult result, HttpServletRequest request, Model model,
			@RequestParam("resumeFile") MultipartFile resumeFile,
			@RequestParam("profileImages") MultipartFile profileImage)
					throws MyJobKartException, IOException, SerialException,
					SQLException, ValidatorException {
		JobseekerController.LOGGER.entry();
		try {
			HttpSession session = request.getSession();
			if (null == session.getAttribute("jobseekerId")) {
				return "redirect:/jobseeker_sign_in.html";
			}
			industryList(model);
			String email = (String) session.getAttribute("emailId");
			updateProfile.setEmailId(email);
			Date startDate = updateProfile.getExpStartDate();
			Date endDate = updateProfile.getExpEndDate();
			if ((startDate != null) & (endDate != null)) {
				if (endDate.after(startDate)) {
					updateProfile.setExpStartDate(startDate);
					updateProfile.setExpEndDate(endDate);
				} else {
					model.addAttribute("message", "Date may not be valid");
					return "jobseeker_update_profile";
				}
			}

			if (!profileImage.isEmpty()) {
				updateProfile.setProfileImage(profileImage.getBytes());
				final String photo = this.imagevalied(profileImage);
				if (null != photo) {
					model.addAttribute("message",
							"ProfileImage Only jpeg,jpg,png formats are allowed  ");
					return "jobseeker_create_profile";
				}
			} else {
				final File rootDir = new File(
						this.servletContext
						.getRealPath("/WEB-INF/images/jobseeker.jpg"));
				final BufferedImage image = ImageIO.read(rootDir);
				final ByteArrayOutputStream baos = new ByteArrayOutputStream();
				ImageIO.write(image, "jpg", baos);
				final byte[] res = baos.toByteArray();
				// jobseekerBO.setProfileImage(res);
				updateProfile.setProfileImage(res);
			}

			if (!resumeFile.isEmpty()) {
				updateProfile.setUploadResume(resumeFile.getBytes());
				if (!resumeFile.isEmpty()) {
					final String resume = this.resumevalied(resumeFile);
					if (null != resume) {
						model.addAttribute("message",
								"Resume Only msword,pdf.. formats are allowed");
						return "jobseeker_update_profile";
					}
				}
			}

			if (null != updateProfile.getFirstName()) {
				final String status = JobtrolleyValidator
						.validate(updateProfile.getFirstName());
				if (null != status) {
					model.addAttribute("message", status);
					return "jobseeker_update_profile";
				}

			}
			if (null != updateProfile.getLastName()) {
				final String status = JobtrolleyValidator
						.validate(updateProfile.getLastName());
				if (null != status) {
					model.addAttribute("message", status);
					return "jobseeker_update_profile";
				}

			}

			long jobSeekerId = (Long) session.getAttribute("jobseekerId");
			updateProfile.setCreatedBy(this.reteriveprofile.getCreatedBy());
			updateProfile.setIsActive(this.reteriveprofile.getIsActive());
			updateProfile.setModifiedBy(jobSeekerId);
			updateProfile.setJobProfileList(profileList_update);
			updateProfile.setJobseekerProfileList(jp_professoinal_list);
			updateProfile.setIsDelete(true);
			updateProfile.setJobSeekerLogin(this.reteriveprofile
					.getJobSeekerLogin());
			updateProfile.setVersion(this.reteriveprofile.getVersion());
			updateProfile = this.jobSeekerService.updateProfile(updateProfile);
			if (null != updateProfile.getResponse()) {
				model.addAttribute("successmessage",
						updateProfile.getResponse());
				return "redirect:/jobseeker_profile_view.html";
			} else {
				model.addAttribute("Infomessage",
						"data has been updated failed,please contact Administrator.");
				return "jobseeker_update_profile";
			}
		} catch (Exception jb) {
			jb.printStackTrace();
		}
		JobseekerController.LOGGER.exit();
		return null;
	}

	/**
	 * This method used to perform the delete jobseeker_profile function
	 * (jobseeker)
	 * 
	 * @param model
	 * @param request
	 * @return
	 * @throws MyJobKartException
	 */
	@RequestMapping(value = "/jobseeker_delete_profile", method = RequestMethod.GET)
	public String jobseekerDeleteProfile(Model model, HttpServletRequest request)
			throws MyJobKartException {
		JobseekerController.LOGGER.entry();
		String id = null;
		long deletedId = 0;
		long loginId = 0;
		try {
			if (request.getParameter("id") != null) {
				id = request.getParameter("id");
				deletedId = Long.parseLong(id);
			}
			final HttpSession session = request.getSession();
			if (session.getAttribute("jobseekerId") != null) {
				loginId = (Long) session.getAttribute("jobseekerId");
			}
			JobseekerProfileBO deleteProfile = new JobseekerProfileBO();
			deleteProfile.setId(deletedId);
			deleteProfile.setIsDelete(false);
			deleteProfile.setDeleteBy(loginId);
			deleteProfile.setModifiedBy(loginId);
			deleteProfile.setModified(new Date());
			final Date deletedDate = new Date();
			deleteProfile.setDeletedDate(deletedDate);
			deleteProfile = this.jobSeekerService
					.jobseekerDeleteProfile(deleteProfile);
			if (null != deleteProfile.getResponse()) {
				model.addAttribute("successmessage",
						deleteProfile.getResponse());
			} else {
				model.addAttribute("Infomessagemessage",
						"data has been updated failed,please contact Administrator.");
			}
		} catch (final MyJobKartException jb) {
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("Jobseeker delete profile failed:"
						+ jb.getErrorCode() + jb);
			}
			LOGGER.info("Jobseeker delete profile failed:" + jb.getErrorCode()
					+ jb);
		}
		JobseekerController.LOGGER.exit();
		return "redirect:/jobseeker_profile_view.html";
	}

	@RequestMapping(value = "/jobseeker_delete_saved_job", method = RequestMethod.GET)
	public String jobseekerDeleteSavedJobs(Model model,
			HttpServletRequest request) throws MyJobKartException {
		JobseekerController.LOGGER.entry();
		String id = null;
		long deletedId = 0;
		long loginId = 0;
		try {
			if (request.getParameter("id") != null) {
				id = request.getParameter("id");
				deletedId = Long.parseLong(id);
			}
			final HttpSession session = request.getSession();
			if (session.getAttribute("jobseekerId") != null) {
				loginId = (Long) session.getAttribute("jobseekerId");
			}
			SavejobBO savejobBO = new SavejobBO();
			savejobBO.setId(deletedId);
			savejobBO.setDeletedBy(loginId);
			savejobBO.setIsDeleted(false);
			Date deletedDate = new Date();
			savejobBO.setDeletedDate(deletedDate);
			savejobBO.setModifiedBy(loginId);
			savejobBO.setModified(new Date());
			savejobBO = this.jobSeekerService
					.jobseekerDeleteSavedJobs(savejobBO);
			if (null != savejobBO.getResponse()) {
				model.addAttribute("message", savejobBO.getResponse());
			} else {
				model.addAttribute("message",
						"data has been updated failed,please contact Administrator.");

			}
		} catch (final MyJobKartException jb) {
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("Jobseeker delete saved job failed:"
						+ jb.getErrorCode() + jb);
			}
			LOGGER.info("Jobseeker delete saved job failed:"
					+ jb.getErrorCode() + jb);
		}
		JobseekerController.LOGGER.exit();
		return "redirect:/jobseeker_saved_jobs.html";
	}

	@RequestMapping(value = "/jobseeker_delete_applied_job", method = RequestMethod.GET)
	public String jobseekerDeleteAppliedJobs(Model model,
			HttpServletRequest request) throws MyJobKartException {
		JobseekerController.LOGGER.entry();
		String id = null;
		long deletedId = 0;
		long loginId = 0;
		try {
			if (request.getParameter("id") != null) {
				id = request.getParameter("id");
				deletedId = Long.parseLong(id);
			}
			final HttpSession session = request.getSession();
			if (session.getAttribute("jobseekerId") != null) {
				loginId = (Long) session.getAttribute("jobseekerId");
			}
			AppliedJobBO appliedJobBO = new AppliedJobBO();
			appliedJobBO.setId(deletedId);
			appliedJobBO.setDeletedBy(loginId);
			appliedJobBO.setIsDeleted(false);
			final Date deletedDate = new Date();
			appliedJobBO.setDeletedDate(deletedDate);
			appliedJobBO.setModifiedBy(loginId);
			appliedJobBO.setModified(new Date());
			appliedJobBO = this.jobSeekerService
					.jobseekerDeleteAppliedJobs(appliedJobBO);
			if (null != appliedJobBO.getResponse()) {
				model.addAttribute("message", appliedJobBO.getResponse());
			} else {
				model.addAttribute("message",
						"data has been updated failed,please contact Administrator.");
			}
		} catch (final MyJobKartException jb) {
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("Jobseeker delete applied job failed:"
						+ jb.getErrorCode() + jb);
			}
			LOGGER.info("Jobseeker delete applied job failed:"
					+ jb.getErrorCode() + jb);
		}
		JobseekerController.LOGGER.exit();
		return "redirect:/jobseeker_applied_jobs.html";
	}

	@RequestMapping(value = "/jobseeker_profile_status", method = RequestMethod.GET)
	public String profileStatus(Model model, HttpServletRequest request)
			throws Exception {
		JobseekerController.LOGGER.entry();
		try {
			String email = null;
			final String status = request.getParameter("status");
			final String[] arr = status.split(",");
			final String num = arr[1];
			final long id = Integer.parseInt(num);
			final String jbstatus = arr[0];
			int profileActiveCount = 0;
			final HttpSession session = request.getSession();
			if(null != session.getAttribute("emailId")){
			email = (String) session.getAttribute("emailId");
			}
			JobseekerProfileBO jobseekerProfileBO = new JobseekerProfileBO();
			jobseekerProfileBO.setEmailId(email);
			jobseekerProfileBO.setId(id);
			if (null != this.profileList) {
				for (JobseekerProfileBO jobseekerProfile : this.profileList) {
					if (jobseekerProfile.getIsActive()) {
						profileActiveCount++;
					}
				}
			}
			if ((0 == profileActiveCount && jbstatus
					.equalsIgnoreCase("De-Active"))
					|| (0 != profileActiveCount && jbstatus
					.equalsIgnoreCase("Active"))) {
				if (jbstatus.equals("Active")) {
					jobseekerProfileBO.setIsActive(false);
				} else {
					jobseekerProfileBO.setIsActive(true);
				}
				boolean profileStatus = this.jobSeekerService
						.profileStatus(jobseekerProfileBO);
				if (profileStatus) {
					if (jobseekerProfileBO.getIsActive()) {
						model.addAttribute("successmessage",
								"Profile Activation is success.");
					} else {
						model.addAttribute("successmessage",
								"Profile De-Activation is success.");
					}
					return "redirect:/jobseeker_profile_view.html";
				} else {
					model.addAttribute("Infomessage",
							"Your account does not exisit,please contact Administrator.");
					return "redirect:/jobseeker_profile_view.html";
				}
			} else {
				model.addAttribute("Infomessage",
						"One Profile only is Activate ");
				return "redirect:/jobseeker_profile_view.html";
			}
		} catch (final NullPointerException ne) {
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("Jobseeker profile status failed:"
						+ ne.getMessage() + ne);
			}
			LOGGER.info("Jobseeker profile status failed:" + ne.getMessage()
					+ ne);
		}
		JobseekerController.LOGGER.exit();
		return "redirect:/jobseeker_profile_view.html";
	}

	@RequestMapping(value = "/jobseeker_saved_jobs", method = RequestMethod.GET)
	public String reteriveSavedJobs(Model model, HttpServletRequest request) {

		HttpSession session = request.getSession();
		try {
			SavejobBO savejobBO = new SavejobBO();
			String userType = (String) session.getAttribute("userType");
			model.addAttribute("userType", userType);
			model.addAttribute("saveJobSearch", new SavejobBO());
			List<SavejobBO> saveJobList = getJobseekersSavedJobs(model,
					request, session, savejobBO);
			if (null != saveJobList && saveJobList.size() > 0) {
				model.addAttribute("savejobs", saveJobList);
			} else {
				model.addAttribute("errormessage",
						"You have not save any job..please find a job ");
			}
			if (null != session.getAttribute("jobseekerId")) {
				return "jobseeker_saved_jobs";
			}

			if(null!= session.getAttribute("adminId")){


				return "admin_jobseeker_saveJobs";

			}
		} catch (final Exception e) {
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("Jobseeker saved jobs failed:" + e.getMessage()
						+ e);
			}
			LOGGER.info("Jobseeker saved jobs failed:" + e.getMessage() + e);
		}

		JobseekerController.LOGGER.exit();
		return "jobseeker_saved_jobs";
	}

	@RequestMapping(value = "/admin_jobseeker_saved_jobs", method = RequestMethod.GET)
	public String adminReteriveSavedJobs(Model model, HttpServletRequest request) {

		HttpSession session = request.getSession();
		try{
			SavejobBO savejobBO = new SavejobBO();

			List<SavejobBO> saveJobList = getJobseekersSavedJobs(model,
					request, session, savejobBO);
			String userType=(String) session.getAttribute("userType");
			model.addAttribute("userType",userType);
			if (null != saveJobList && saveJobList.size() > 0) {
				model.addAttribute("savejobs",saveJobList);
			} else {
				model.addAttribute("errormessage",
						"You have not save any job..please find a job ");
			}

		} catch (final Exception e) {
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("Jobseeker saved jobs failed:" + e.getMessage()
						+ e);
			}
			LOGGER.info("Jobseeker saved jobs failed:" + e.getMessage() + e);
		}

		model.addAttribute("saveJobSearch", new SavejobBO());
		JobseekerController.LOGGER.exit();
		return "admin_jobseeker_saved_jobs";
	}

	@RequestMapping(value = "/saveJobSearch", method = RequestMethod.POST)
	public String saveJobSearch(
			@Valid @ModelAttribute("saveJobSearch") JobseekerProfileBO search,
			BindingResult result, HttpServletRequest request, Model model) {
		HttpSession session = request.getSession();
		try {
			SavejobBO savejobBO = new SavejobBO();
			savejobBO.setSearchElement(search.getSearchElement());
			List<SavejobBO> saveJobList = getJobseekersSavedJobs(model,
					request, session, savejobBO);
			String userType = (String) session.getAttribute("userType");
			model.addAttribute("userType", userType);

			if (null != saveJobList) {
				if (saveJobList.size() == 0) {
					model.addAttribute("errormessage", "No record found..");
				}
				model.addAttribute("savejobs", saveJobList);
				if (null != session.getAttribute("jobseekerId")) {
					return "jobseeker_saved_jobs";
				}

				if (null != session.getAttribute("adminId")) {
					model.addAttribute("saveJobSearch", new SavejobBO());
					return "admin_jobseeker_saved_jobs";
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return "jobseeker_saved_jobs";
	}
	private List<SavejobBO> getJobseekersSavedJobs(Model model, HttpServletRequest request,HttpSession session,SavejobBO savebo){


		List<SavejobBO> saveJobList=null;
		if (null != session.getAttribute("userType")) {
			String userType=session.getAttribute("userType").toString();
			if(userType.equalsIgnoreCase("admin")||userType.equalsIgnoreCase("Jobseeker")){
				String message = request.getParameter("message");
				model.addAttribute("message", message);
				JobseekerController.LOGGER.entry();
				try {				
					SavejobBO savejobBO = new SavejobBO();
					if(null!=session.getAttribute("jobseekerId")){
						long jobseekerId = (Long) session.getAttribute("jobseekerId");	
						//String userType = (String) session.getAttribute("userType");
						JobSeekerLoginBO jobSeekerLoginBO = new JobSeekerLoginBO();
						jobSeekerLoginBO.setId(jobseekerId);
						savejobBO.setJobseekerLogin(jobSeekerLoginBO);
					}
					else if(null != request.getParameter("RegisterId")){
						String registerId = request.getParameter("RegisterId");
						long jobseekerRegId = Long.valueOf(registerId);
						JobseekerBO jobseekerBO = new JobseekerBO();
						jobseekerBO.setId(jobseekerRegId);
						savejobBO.setJobseekerRegistration(jobseekerBO);
					}
					if (null != savebo.getSearchElement()) {
						savejobBO.setSearchElement(savebo.getSearchElement());
					}
					savejobBO = jobSeekerService.reteriveSavedJobs(savejobBO);
					saveJobList = savejobBO.getJobPostList();
				} catch (final Exception e) {
					if (LOGGER.isDebugEnabled()) {
						LOGGER.debug("Jobseeker saved jobs failed:" + e.getMessage()
								+ e);
					}


				}
			}
		}
		return saveJobList;
	}


	@RequestMapping(value = "/save_to_later_applied_jobs", method = RequestMethod.GET)
	public String jobseekerAppliedJob(Model model, HttpServletRequest request) {
		JobseekerController.LOGGER.entry();
		AppliedJobBO appliedJobBO = null;
		SavejobBO savedjobBO = new SavejobBO();
		try {
			final HttpSession session = request.getSession();
			final String id = request.getParameter("id");
			final long saveJobId = Long.parseLong(id);
			String jobId = request.getParameter("jobId");
			long appliedId = Long.parseLong(jobId);
			if (null != this.appliedJobList) {
				for (final AppliedJobBO appliedBO : this.appliedJobList) {
					if (appliedBO.getJobId() == appliedId) {
						model.addAttribute("message",
								"Already applied this job!");
						return "redirect:/jobseeker_saved_jobs.html";
					}
				}
			}
			// Retrived Jobseeker Saved job Details
			final long jobseekerId = (Long) session.getAttribute("jobseekerId");
			final JobSeekerLoginBO jobSeekerLoginBO = new JobSeekerLoginBO();
			jobSeekerLoginBO.setId(jobseekerId);
			savedjobBO.setJobseekerLogin(jobSeekerLoginBO);
			savedjobBO = this.jobSeekerService.reteriveSavedJobs(savedjobBO);
			List<SavejobBO> saveJobList = savedjobBO.getJobPostList();
			if (null != saveJobList) {
				for (final SavejobBO savejobBO : saveJobList) {
					if (saveJobId == savejobBO.getId()) {
						appliedJobBO = new AppliedJobBO();
						appliedJobBO.setCreatedBy(jobseekerId);
						appliedJobBO.setModifiedBy(jobseekerId);
						appliedJobBO.setCompanyName(savejobBO.getCompanyName());
						appliedJobBO.setNoVacancies(savejobBO.getNoVacancies());
						appliedJobBO.setJobLocation(savejobBO.getJobLocation());
						appliedJobBO.setPostedBy(savejobBO.getPostedBy());
						appliedJobBO.setJobTitle(savejobBO.getJobTitle());
						appliedJobBO.setAddress(savejobBO.getAddress());
						appliedJobBO.setContactNo(savejobBO.getContactNo());
						appliedJobBO.setCurrencyType(savejobBO
								.getCurrencyType());
						appliedJobBO.setContactPerson(savejobBO
								.getContactPerson());
						appliedJobBO.setFunctionArea(savejobBO
								.getFunctionArea());
						appliedJobBO.setIndustryType(savejobBO
								.getIndustryType());
						appliedJobBO.setJobDescription(savejobBO
								.getJobDescription());
						appliedJobBO.setNoVacancies(savejobBO.getNoVacancies());
						appliedJobBO.setKeywords(savejobBO.getKeywords());
						appliedJobBO.setMaxSalary(savejobBO.getMaxSalary());
						appliedJobBO.setMinSalary(savejobBO.getMinSalary());
						appliedJobBO.setMaxExp(savejobBO.getMaxExp());
						appliedJobBO.setMinExp(savejobBO.getMinExp());
						appliedJobBO.setPgQualification(savejobBO
								.getPgQualification());
						appliedJobBO.setUgQualification(savejobBO
								.getUgQualification());
						appliedJobBO.setOtherSalaryDetails(savejobBO
								.getOtherSalaryDetails());
						appliedJobBO.setJobResponse(savejobBO.getJobResponse());
						final JobPostBO jobPostBO = new JobPostBO();
						jobPostBO.setId(savejobBO.getJobpostBO().getId());
						appliedJobBO.setJobpostBO(jobPostBO);
						EmployerBO employerBO = new EmployerBO();
						employerBO.setId(savejobBO.getEmployerRegistration()
								.getId());
						appliedJobBO.setEmployerRegistration(employerBO);

						final EmployerLoginBO employerLoginBO = new EmployerLoginBO();
						employerLoginBO.setId(savejobBO.getEmployerLogin()
								.getId());
						appliedJobBO.setEmployerLogin(employerLoginBO);
						appliedJobBO.setIsDeleted(true);
						final JobSeekerLoginBO jobSeekerLogin = new JobSeekerLoginBO();
						jobSeekerLogin.setId(jobseekerId);
						appliedJobBO.setJobseekerLogin(jobSeekerLogin);
					}
				}
			}
			if (0 != jobseekerId) {
				boolean jobid = jobSeekerService.checkProfileId(jobseekerId);
				if (jobid) {
					final boolean applyJobs = this.jobSeekerService.jobseekerAppliedJob(
							appliedJobBO, saveJobId);
					if (applyJobs) {
						model.addAttribute("message",
								"Your Job has been applied successfully.");
						return "redirect:/jobseeker_applied_jobs.html";
					} else {

						model.addAttribute("message",
								"Your account does not exist,please contact Administrator.");
						return "redirect:/jobseeker_saved_jobs.html";
					}
				} else {
					model.addAttribute("Infomessage",
							"please create a profile and activated then apply");
					return "redirect:/jobseeker_create_profile.html";
				}
			} else {
				model.addAttribute("message",
						"Please Create a Profile and activated");
				return "redirect:/                                                                                                                                                                                                                                                  .html";
			}
		} catch (final Exception e) {
			e.printStackTrace();
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("save to later applied jobs failed:"
						+ e.getMessage());
			}
			LOGGER.info("save to later applied jobs failed:" + e.getMessage());
		}
		JobseekerController.LOGGER.exit();
		return "save_home";

	}

	@RequestMapping(value = "/jobseeker_saved_job_details", method = RequestMethod.GET)
	public String savedJobDetails(Model model, HttpServletRequest request) {
		JobseekerController.LOGGER.entry();
		HttpSession session = request.getSession();
		String responsePage = null;
		if (null != session.getAttribute("jobseekerId")||null != session.getAttribute("adminId")) {		
			final String id = request.getParameter("id");		
			try {
				responsePage = savedJobDetails(model,id,request);
			} catch (final MyJobKartException e) {
				e.printStackTrace();
				if (LOGGER.isDebugEnabled()) {
					LOGGER.debug("Jobseeker saved job details failed:"
							+ e.getMessage());
				}
				LOGGER.info("Jobseeker saved job details failed:" + e.getMessage());
			}
		}else{
			return "redirect:/jobseeker_sign_in.html";
		}
		return responsePage;
	}

	@RequestMapping(value = "/jobseeker_saved_job_details", method = RequestMethod.POST)
	private String savedJobDetails(Model model, String id,HttpServletRequest request)
			throws MyJobKartException {
		JobseekerController.LOGGER.entry();
		List<SavejobBO> saveJobList=null;
		try {

			HttpSession session = request.getSession();
			
			SavejobBO savejobBO=new SavejobBO();
			savejobBO.setJobId(Long.valueOf(id));
			String userType=(String)session.getAttribute("userType");
			model.addAttribute("userType", userType);
			savejobBO=jobSeekerService.reteriveSavedJobs(savejobBO);
			saveJobList=savejobBO.getJobPostList();
			final long saveJobId = Long.parseLong(id);
			for (final SavejobBO savedjob :saveJobList) {
				if (saveJobId == savedjob.getId()) {
					model.addAttribute("savedJobs", savedjob);
				}
			}
			if (null != session.getAttribute("jobseekerId")) {
				return "jobseeker_saved_job_details";
			}

			if(null!= session.getAttribute("adminId")){


				return "admin_jobseeker_saved_job_details";

			}
		} catch (final NullPointerException ne) {
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("Jobseeker saved jobs details failed:"
						+ ne.getMessage() + ne);
			}
			LOGGER.info("Jobseeker saved jobs details failed:"
					+ ne.getMessage() + ne);
		}
		JobseekerController.LOGGER.exit();
		return "jobseeker_saved_job_details";
	}

	@RequestMapping(value = "/jobseeker_applied_jobs", method = RequestMethod.GET)
	public String reteriveAppliedJobs(Model model, HttpServletRequest request)
			throws MyJobKartException {
		JobseekerController.LOGGER.entry();
		HttpSession session = request.getSession();
		if (null == session.getAttribute("jobseekerId")) {
			return "redirect:/jobseeker_sign_in.html";
		}
		String message = request.getParameter("message");
		model.addAttribute("message", message);
		model.addAttribute("appliedJobSearch", new AppliedJobBO());
		try {
			String userType=(String)session.getAttribute("userType");
			model.addAttribute("userType", userType);
			long jobseekerId =0;
			if(null!=session.getAttribute("jobseekerId")){
				jobseekerId = (Long) session.getAttribute("jobseekerId");	
			}
			List<AppliedJobBO> appliedJob = appliedList(jobseekerId);
			session.setAttribute("appliedJobList", appliedJob);
			if (null != appliedJob && appliedJob.size() > 0) {
				final List<AppliedJobBO> appliedJobs = new ArrayList<AppliedJobBO>();
				for (final AppliedJobBO jobBO : appliedJob) {
					
					this.appliedJobBO = new AppliedJobBO();
					if (jobBO.getIsDeleted() == true) {
						this.appliedJobBO = jobBO;
						appliedJobs.add(this.appliedJobBO);
					}
				}
				this.appliedJobList = appliedJobs;
				model.addAttribute("appliedJobs", this.appliedJobList);
			} else {
				model.addAttribute("errormessage",
						"You have not applied any job..please find a job ");
			}
			if (null != session.getAttribute("jobseekerId")) {
				return "jobseeker_applied_jobs";
			}

			if(null!= session.getAttribute("adminId")){


				return "admin_jobseeker_appliedJobs";

			}
		} catch (final Exception e) {
			e.printStackTrace();
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("Jobseeker applied jobs failed:" + e.getMessage()
						+ e);
			}
			LOGGER.info("Jobseeker applied jobs failed:" + e.getMessage() + e);
		}
		JobseekerController.LOGGER.exit();
		return "jobseeker_applied_jobs";
	}

	@RequestMapping(value = "/admin_jobseeker_applied_jobs", method = RequestMethod.GET)
	public String adminReteriveAppliedJobs(Model model, HttpServletRequest request)
			throws MyJobKartException {
		HttpSession session = request.getSession();
		try{
			List<AppliedJobBO> appliedJobList =getJobseekersAppliedJobs(model,request,session);
			String userType = (String) session.getAttribute("userType");
			model.addAttribute("userType",userType);
			if (null != appliedJobList && appliedJobList.size() > 0) {
				model.addAttribute("appliedJobs",appliedJobList);
				
			} else {
				model.addAttribute("errormessage",
						"You have not apply any job..please find a job ");
			}

		} catch (final Exception e) {
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("Jobseeker sapplied jobs failed:" + e.getMessage()
						+ e);
			}
			LOGGER.info("Jobseeker applied jobs failed:" + e.getMessage() + e);
		}
		
		model.addAttribute("appliedJobSearch", new AppliedJobBO());
		JobseekerController.LOGGER.exit();
		return "admin_jobseeker_applied_jobs";
	}

	private List<AppliedJobBO> getJobseekersAppliedJobs(Model model, HttpServletRequest request,HttpSession session){


		List<AppliedJobBO> appliedJobList = null;
		if (null != session.getAttribute("userType")) {
			String userType=session.getAttribute("userType").toString();
			if(userType.equalsIgnoreCase("admin")||userType.equalsIgnoreCase("Jobseeker")){
				String message = request.getParameter("message");
				model.addAttribute("message", message);
				JobseekerController.LOGGER.entry();
				try {				
					AppliedJobBO appliedBO = new AppliedJobBO();
					if(null!=session.getAttribute("jobseekerId")){
						long jobseekerId = (Long) session.getAttribute("jobseekerId");	
						//String userType = (String) session.getAttribute("userType");
						JobSeekerLoginBO jobSeekerLoginBO = new JobSeekerLoginBO();
						jobSeekerLoginBO.setId(jobseekerId);
						appliedBO.setJobseekerLogin(jobSeekerLoginBO);
					}
					else if(null != request.getParameter("RegisterId")){
						String registerId = request.getParameter("RegisterId");
						long jobseekerRegId = Long.valueOf(registerId);
						JobseekerBO jobseekerBO = new JobseekerBO();
						jobseekerBO.setId(jobseekerRegId);
						appliedBO.setJobseekerRegistration(jobseekerBO);
					}
					appliedBO = jobSeekerService.reteriveAppliedJobs(appliedBO);
					appliedJobList = appliedBO.getJobPostList();
				} catch (final Exception e) {
					if (LOGGER.isDebugEnabled()) {
						LOGGER.debug("Jobseeker applied jobs failed:" + e.getMessage()
								+ e);
					}


				}
			}
		}
		return appliedJobList;
	}


	@RequestMapping(value = "/jobseeker_applied_job_details", method = RequestMethod.GET)
	public String appliedJobDetails(Model model, HttpServletRequest request)
			throws MyJobKartException {
		JobseekerController.LOGGER.entry();
		HttpSession session = request.getSession();
		if ((null == session.getAttribute("jobseekerId")) &&(null == session.getAttribute("adminId"))) {
			return "redirect:/jobseeker_sign_in.html";
		}
		final String id = request.getParameter("id");
		try {
			final long appliedJobId = Long.parseLong(id);
			AppliedJobBO appliedJob = new AppliedJobBO();
			String userType = (String) session.getAttribute("userType");
			model.addAttribute("userType", userType);
			appliedJob.setId(appliedJobId);
			appliedJob = this.jobSeekerService.reteriveAppliedJobs(appliedJob);
					
			if (null != appliedJob) {
				this.appliedJobList = appliedJob.getJobPostList();
			}
			for (final AppliedJobBO appliedJobBO : this.appliedJobList) {
				if (appliedJobId == appliedJobBO.getId()) {
					model.addAttribute("appliedJobs", appliedJobBO);
				}
			}
			if (null != session.getAttribute("jobseekerId")) {
				return "jobseeker_applied_job_details";
			}

			if(null!= session.getAttribute("adminId")){


				return "admin_jobseeker_applied_job_details";

			}
		} catch (final NullPointerException ne) {
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("Jobseeker applied job details failed:"
						+ ne.getMessage() + ne);
			}
			LOGGER.info("Jobseeker applied job details failed:"
					+ ne.getMessage() + ne);
		}
		JobseekerController.LOGGER.exit();
		return "jobseeker_applied_job_details";
	}

	@RequestMapping(value = "/resumeDisplay", method = RequestMethod.GET)
	public String showResume(@RequestParam("id") Integer itemId,
			HttpServletResponse response, HttpServletRequest request,
			Model model) throws ServletException, IOException, SQLException {
		String id = request.getParameter("id");
		long JobseekerProfileId = Long.parseLong(id);
		HttpSession session = request.getSession();
		JobseekerProfileBO jobSeekerProfileBO = new JobseekerProfileBO();
		jobSeekerProfileBO.setProfesId(JobseekerProfileId);

		jobSeekerProfileBO = jobSeekerService
				.getJobSeekerProfile(jobSeekerProfileBO);
		if (null != jobSeekerProfileBO) {
			if (jobSeekerProfileBO.getUploadResume().length() > 0) {
				response.setContentType("application/msword");
				response.getOutputStream().write(
						jobSeekerProfileBO.getUploadResume().getBytes(
								1,
								(int) jobSeekerProfileBO.getUploadResume()
								.length()));
				response.getOutputStream().close();
			}
		} else {
			model.addAttribute("message",
					"Resume Is Not Uploaded For Jobseeker..!!");
		}
		if (null != session.getAttribute("jobseekerId")) {
			return "jobseeker_profile_view";
		} else {
			return "find_resumes_details";
		}
	}

	@RequestMapping(value = "/showimage", method = RequestMethod.GET)
	public void showImage(@RequestParam("id") Integer itemId,
			HttpServletResponse response, HttpServletRequest request)
					throws ServletException, IOException, SQLException {
		String id = request.getParameter("id");
		long imgid = Long.parseLong(id);
		JobseekerProfileBO jobseekerProfileBO = new JobseekerProfileBO();
		jobseekerProfileBO.setProfesId(imgid);
		jobseekerProfileBO = this.jobSeekerService
				.getJobSeekerProfile(jobseekerProfileBO);
		if (null != jobseekerProfileBO) {
			response.setContentType("image/jpeg, image/jpg, image/png, image/gif");
			response.getOutputStream().write(
					jobseekerProfileBO.getProfileImage()
					.getBytes(
							1,
							(int) jobseekerProfileBO.getProfileImage()
							.length()));
			response.getOutputStream().close();
		}
	}

	private List<AppliedJobBO> appliedList(long jobseekerId)
			throws MyJobKartException {
		JobseekerController.LOGGER.entry();
		AppliedJobBO appliedJobBO = new AppliedJobBO();
		final JobSeekerLoginBO jobSeekerLoginBO = new JobSeekerLoginBO();
		try {
			jobSeekerLoginBO.setId(jobseekerId);
			appliedJobBO.setJobseekerLogin(jobSeekerLoginBO);
			appliedJobBO = this.jobSeekerService
					.reteriveAppliedJobs(appliedJobBO);
			if (null != appliedJobBO) {
				this.appliedJobList = appliedJobBO.getJobPostList();
			}
		} catch (final MyJobKartException e) {
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("Reterive applied jobs failed:" + e.getMessage()
						+ e);
			}
			LOGGER.info("Reterive applied jobs failed:" + e.getMessage() + e);
		}
		JobseekerController.LOGGER.exit();
		return this.appliedJobList;
	}

	private List<SavejobBO> saveList(long jobseekerId)
			throws MyJobKartException {
		JobseekerController.LOGGER.entry();
		List<SavejobBO> saveJobList=null;
		SavejobBO savejobBO = new SavejobBO();
		final JobSeekerLoginBO jobSeekerLoginBO = new JobSeekerLoginBO();
		try {
			jobSeekerLoginBO.setId(jobseekerId);
			savejobBO.setJobseekerLogin(jobSeekerLoginBO);
			savejobBO = this.jobSeekerService.reteriveSavedJob(savejobBO);
			saveJobList = savejobBO.getJobPostList();
		} catch (final MyJobKartException e) {
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("Reterive saved jobs failed:" + e.getMessage() + e);
			}
			LOGGER.info("Reterive saved jobs failed:" + e.getMessage() + e);
		}
		JobseekerController.LOGGER.exit();
		return saveJobList;

	}

	public String imagevalied(MultipartFile profileImage) {
		String str = null;
		if (profileImage.getContentType().equals("image/jpeg")
				|| profileImage.getContentType().equals("image/jpg")
				|| profileImage.getContentType().equals("image/png")) {
		} else {
			str = "Only jpeg,jpg,png formats are allowed  ";
		}
		return str;
	}

	public String resumevalied(MultipartFile resumeFile) {
		String str = null;
		if (resumeFile
				.getContentType()
				.equals("application/vnd.openxmlformats-officedocument.wordprocessingml.document")
				|| resumeFile.getContentType().equals("application/pdf")
				|| resumeFile.getContentType().equals("application/msword")) {
		} else {
			str = "Only msword,pdf.. formats are allowed  ";
		}
		return str;
	}

	@RequestMapping(value = "/appliedJobSearch", method = RequestMethod.POST)
	public String appliedJobSearch(
			@Valid @ModelAttribute("appliedJobSearch") JobseekerProfileBO updateProfile,
			BindingResult result, HttpServletRequest request, Model model) {
		HttpSession session=request.getSession();
		try {
			List<AppliedJobBO> appliedJobs = new ArrayList<AppliedJobBO>();
			if (null != this.appliedJobList) {
				for (final AppliedJobBO profileBO : this.appliedJobList) {
					if (profileBO
							.getJobLocation()
							.toLowerCase()
							.contains(
									updateProfile.getSearchElement()
									.toLowerCase())) {
						this.appliedJobBO = profileBO;
						appliedJobs.add(this.appliedJobBO);
					}
				}
				if (appliedJobs.size() == 0) {
					model.addAttribute("errormessage", "No record found..");
				}
				model.addAttribute("appliedJobs", appliedJobs);
				if (null != session.getAttribute("jobseekerId")) {
					return "jobseeker_applied_jobs";
				}

				if(null!= session.getAttribute("adminId")){

					return "admin_jobseeker_applied_jobs";

				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "jobseeker_applied_jobs";
	}

	@RequestMapping(value = "/jobseeker_view_history", method = RequestMethod.GET)
	public String jobSeekerview(Model model, HttpServletRequest request) {
		String sucessmessage = request.getParameter("Infomessage");
		String days = request.getParameter("days");
		if (null != sucessmessage) {
			model.addAttribute("Infomessage", sucessmessage);
		} else {
			model.addAttribute("days", days);
		}
		HttpSession session = request.getSession();
		if (null == session.getAttribute("jobseekerId")) {
			return "redirect:/jobseeker_sign_in.html";
		}
		final ViewJobseekerBO jobseeker = new ViewJobseekerBO();
		model.addAttribute("jobseeker", jobseeker);
		model.addAttribute("viewhistory", "white");
		return "jobseeker_view_history";
	}

	@RequestMapping(value = "/jobseeker_view_day", method = RequestMethod.GET)
	public String jobSeekerViewDate(Model model, HttpServletRequest request)
			throws ParseException {
		HttpSession session = request.getSession();
		if (null == session.getAttribute("jobseekerId")) {
			return "redirect:/jobseeker_sign_in.html";
		}
		ViewJobseekerBO viewJobSeeker = new ViewJobseekerBO();
		final long jobseekerId = (Long) session.getAttribute("jobseekerId");
		viewJobSeeker.setId(jobseekerId);
		viewJobSeeker.setDays(10);
		List<ViewJobseekerBO> viewList = this.jobSeekerService
				.viewJobSeekerHistory(viewJobSeeker);
		if (null != viewList && viewList.size() > 0) {
			model.addAttribute("jobseekerViewDay", viewList);
		}
		model.addAttribute("viewday", "white");
		return "jobseeker_view_history";
	}

	@SuppressWarnings("unused")
	@RequestMapping(value = "/jobseeker_view_day20", method = RequestMethod.GET)
	public String jobSeekerViewDateOneMonth(Model model,
			HttpServletRequest request) throws ParseException {
		HttpSession session = request.getSession();
		if (null == session.getAttribute("jobseekerId")) {
			return "redirect:/jobseeker_sign_in.html";
		}
		ViewJobseekerBO viewJobSeeker = new ViewJobseekerBO();
		final long jobseekerId = (Long) session.getAttribute("jobseekerId");
		viewJobSeeker.setId(jobseekerId);
		viewJobSeeker.setDays(20);
		List<ViewJobseekerBO> viewList = this.jobSeekerService
				.viewJobSeekerHistory(viewJobSeeker);
		if (null == viewList) {
			model.addAttribute("days", "No Record Found ..");
		}
		model.addAttribute("jobseeker20Days", viewList);
		model.addAttribute("view20day", "white");
		return "jobseeker_view_history";
	}

	@SuppressWarnings("unused")
	@RequestMapping(value = "/jobseeker_view_day30", method = RequestMethod.GET)
	public String jobSeekerViewDateThreeMonth(Model model,
			HttpServletRequest request) throws ParseException {
		HttpSession session = request.getSession();
		if (null == session.getAttribute("jobseekerId")) {
			return "redirect:/jobseeker_sign_in.html";
		}
		ViewJobseekerBO viewJobSeeker = new ViewJobseekerBO();
		long jobseekerId = (Long) session.getAttribute("jobseekerId");
		viewJobSeeker.setId(jobseekerId);
		viewJobSeeker.setDays(30);
		List<ViewJobseekerBO> viewList = this.jobSeekerService
				.viewJobSeekerHistory(viewJobSeeker);
		if (null == viewList) {
			model.addAttribute("days", "No Record Found ..");
		}
		model.addAttribute("jobseeker30Days", viewList);
		model.addAttribute("view30day", "white");
		return "jobseeker_view_history";
	}

	private ResponseObject<PaymentBO> pagination3(int page,
			List<PaymentBO> enrolledJobseekerList2) {
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
		final List<PaymentBO> list = enrolledJobseekerList2;
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

	@RequestMapping(value = "/jobseeker_view", method = RequestMethod.GET)
	public String jobSeekerViewDateTotal(Model model, HttpServletRequest request)
			throws ParseException {
		HttpSession session = request.getSession();
		if (null == session.getAttribute("jobseekerId")) {
			return "redirect:/jobseeker_sign_in.html";
		}
		ViewJobseekerBO viewJobseekerBO = new ViewJobseekerBO();
		long jobseekerId = (Long) session.getAttribute("jobseekerId");
		long jobseekerRegId = (long) session.getAttribute("registerId");
		viewJobseekerBO.setId(jobseekerId);
		this.viewList = this.jobSeekerService
				.viewJobSeekerHistory(viewJobseekerBO);
		if (null == this.viewList || this.viewList.size() == 0) {
			model.addAttribute("Infomessage",
					"Employer not view any profile...");
			model.addAttribute("viewhistory", "white");
		}
		if (null != this.viewList) {
			List<ViewJobseekerBO> viewHistoryList = new ArrayList<ViewJobseekerBO>();
			for (ViewJobseekerBO viewJobseeker : this.viewList) {
				ViewJobseekerBO viewHistory = new ViewJobseekerBO();
				if (viewJobseeker.getJobseekerRegId() == jobseekerRegId) {
					viewHistory = viewJobseeker;
					viewHistoryList.add(viewHistory);
				}
			}
			model.addAttribute("appliedJobs", viewHistoryList);
			model.addAttribute("viewhistory", "white");
		}
		return "jobseeker_view_history";
	}

	@RequestMapping(value = "/jobseeker_employee_view_total", method = RequestMethod.GET)
	public String jobseekerViewHistoryDetails(Model model,
			HttpServletRequest request) {

		HttpSession session = request.getSession();
		if (null == session.getAttribute("jobseekerId")) {
			return "redirect:/jobseeker_sign_in.html";
		}
		String id = request.getParameter("id");
		long empId = Long.parseLong(id);
		ViewJobseekerBO empdetail = new ViewJobseekerBO();
		empdetail.setId(empId);
		this.empPro = this.jobSeekerService.employeeDetail(empdetail);
		model.addAttribute("employerDetail", this.empPro);
		return "jobseeker_employer_profile_details";
	}



	@RequestMapping(value = "/jobseeker_renewal_alert", method = RequestMethod.GET)
	public String renewalJobseekerAlert(Model model, HttpServletRequest request) {
		JobseekerController.LOGGER.entry();
		JobseekerBO jobseekerBO = null;
		model.addAttribute("jobSeekerNameSearch", new JobseekerBO());
		int page = 1;
		final String paging = request.getParameter("page");
		List<JobseekerBO> registeredList;
		final HttpSession session = request.getSession();
		String email = (String) session.getAttribute("emailId");
		jobseekerBO = new JobseekerBO();
		jobseekerBO = this.jobSeekerService.renewalJobseekerAlert(email);
		registeredList = jobseekerBO.getRegisteredList();
		try {
			if (null != paging) {
				page = Integer.parseInt(paging);
				@SuppressWarnings("unchecked")
				final ResponseObject<JobseekerBO> reponseObject = this
				.jobpagination1(page, registeredList);
				model.addAttribute("registeredList", reponseObject);
			} else {
				if (0 != registeredList.size()) {
					@SuppressWarnings({ "unchecked" })
					final ResponseObject<JobseekerBO> responseObject = this
					.jobpagination1(1, registeredList);
					model.addAttribute("registeredList", responseObject);
				} else {
					model.addAttribute("message",
							"Retrive Failer!..please contact Administrator.. ");
				}
			}
		} catch (final Exception e) {
			e.printStackTrace();
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("Jobseeker renewal alert failed:" + e.getMessage());
			}
			LOGGER.info("Jobseeker renewal alert failed:" + e.getMessage());
		}
		JobseekerController.LOGGER.exit();
		return "jobseeker_renewal_alert";
	}

	@RequestMapping(value = "/jobseeker_enrolled_details", method = RequestMethod.GET)
	public String productsEnrolledList(Model model, HttpServletRequest request) {
		JobseekerController.LOGGER.entry();
		try {
			final HttpSession session = request.getSession();
			int page = 1;
			long registerId = 0L;
			final String paging = request.getParameter("page");
			if (null != paging) {
				page = Integer.parseInt(paging);
				final ResponseObject<PaymentBO> reponseObject = this
						.pagination3(page, this.enrolledJobseekerList);
				model.addAttribute("enrolledJobseekerList", reponseObject);
			} else {
				if (null != session.getAttribute("registerId")) {
					registerId = (Long) session.getAttribute("registerId");
				}
				PaymentBO paymentBO = new PaymentBO();
				paymentBO = this.jobSeekerService
						.productsEnrolledList(registerId);
				this.enrolledJobseekerList = paymentBO.getEnrolledList();
				if (null != this.enrolledJobseekerList) {
					final ResponseObject<PaymentBO> responseObject = this
							.pagination3(1, this.enrolledJobseekerList);
					model.addAttribute("enrolledJobseekerList", responseObject);
				} else {
					model.addAttribute("message",
							"Retrive Failer!..please contact Administrator.. ");
				}
			}
		} catch (final Exception exception) {
			exception.printStackTrace();
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("Jobseeker enrolled details failed:"
						+ exception.getMessage());
			}
			LOGGER.info("Jobseeker enrolled details failed:"
					+ exception.getMessage());
		}
		JobseekerController.LOGGER.exit();
		return "jobseeker_enrolled_details";
	}

	@RequestMapping(value = "/view_jobseeker_enrolled_details", method = RequestMethod.GET)
	public String jobseekerEnrolledDetails(Model model,
			HttpServletRequest request) {
		JobseekerController.LOGGER.entry();
		final String id = request.getParameter("id");
		try {
			final long enrolledId = Long.parseLong(id);
			for (final PaymentBO paymentBO : this.enrolledJobseekerList) {
				if (enrolledId == paymentBO.getId()) {
					model.addAttribute("enrolledList", paymentBO);
				}
			}
		} catch (final NullPointerException ne) {
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("View jobseeker enrolled details failed:"
						+ ne.getMessage() + ne);
			}
			LOGGER.info("View jobseeker enrolled details failed:"
					+ ne.getMessage() + ne);
		}
		JobseekerController.LOGGER.exit();
		return "view_jobseeker_enrolled_details";
	}

	@RequestMapping(value = "/jobseeker_delete_enrolled_details", method = RequestMethod.GET)
	public String deleteJobseekerEnrolledDetails(Model model,
			HttpServletRequest request) throws MyJobKartException {
		JobseekerController.LOGGER.entry();
		String id = null;
		long deletedId = 0L;
		try {
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
			savejobBO = this.jobSeekerService
					.deleteJobseekerEnrolledDetails(savejobBO);
			if (null != savejobBO.getResponse()) {
				model.addAttribute("message", savejobBO.getResponse());
			} else {
				model.addAttribute("message",
						"data has been updated failed,please contact Administrator.");
			}
		} catch (final Exception e) {
			e.printStackTrace();
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("jobseeker enrolled details failed:"
						+ e.getMessage());
			}
			LOGGER.info("jobseeker enrolled details failed:" + e.getMessage());
		}
		JobseekerController.LOGGER.exit();
		return "redirect:/jobseeker_enrolled_details";
	}

	@RequestMapping(value = "/current_month_enrolled_details", method = RequestMethod.GET)
	public String paymentHistoryEnrolledList(Model model,
			HttpServletRequest request) {
		JobseekerController.LOGGER.entry();
		try {
			final HttpSession session = request.getSession();
			int page = 1;
			long registerId = 0L;
			final String paging = request.getParameter("page");
			PaymentBO paymentBO = this.jobSeekerService
					.productsEnrolledList(registerId);
			List<PaymentBO> paymentEnrolledList = paymentBO.getEnrolledList();
			// pagination
			if (null != paging) {
				page = Integer.parseInt(paging);
				final ResponseObject<PaymentBO> reponseObject = this
						.pagination3(page, paymentEnrolledList);
				model.addAttribute("paymentEnrolledList", reponseObject);
			} else {
				if (null != session.getAttribute("registerId")) {
					registerId = (Long) session.getAttribute("registerId");
				}
				if (0 != paymentEnrolledList.size()) {
					final ResponseObject<PaymentBO> responseObject = this
							.pagination3(1, paymentEnrolledList);
					model.addAttribute("paymentEnrolledList", responseObject);
				} else {
					model.addAttribute("message",
							"Retrive Failer!..please contact Administrator.. ");
				}
			}
		} catch (final Exception exception) {
			exception.printStackTrace();
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("current months enrolled detail failed:"
						+ exception.getMessage());
			}
			LOGGER.info("current months enrolled detail failed:"
					+ exception.getMessage());
		}
		JobseekerController.LOGGER.exit();
		return "jobseeker_renewal_alert";
	}

	@RequestMapping(value = "/last_month_enrolled_details", method = RequestMethod.GET)
	public String lastMonthPaymentHistoryList(Model model,
			HttpServletRequest request) {
		JobseekerController.LOGGER.entry();
		List<PaymentBO> lastMonthPaymentList = new ArrayList<PaymentBO>();
		try {
			final HttpSession session = request.getSession();
			int page = 1;
			long registerId = 0L;
			final String paging = request.getParameter("page");
			// retrieve the last month payment.
			PaymentBO paymentBO = jobSeekerService
					.lastMonthPaymentList(registerId);
			if (null != paymentBO.getEnrolledList()) {
				lastMonthPaymentList = paymentBO.getEnrolledList();
			}
			// pagination List
			if (null != paging) {
				page = Integer.parseInt(paging);
				final ResponseObject<PaymentBO> reponseObject = this
						.pagination3(page, lastMonthPaymentList);
				model.addAttribute("lastMonthPaymentList", reponseObject);
			} else {
				if (null != session.getAttribute("registerId")) {
					registerId = (Long) session.getAttribute("registerId");
				}
				if (0 != lastMonthPaymentList.size()) {
					final ResponseObject<PaymentBO> responseObject = this
							.pagination3(1, lastMonthPaymentList);
					model.addAttribute("lastMonthPaymentList", responseObject);
				} else {
					model.addAttribute("message",
							"Retrive Failer!..please contact Administrator.. ");
				}
			}
		} catch (final Exception exception) {
			exception.printStackTrace();
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("lasts months enrolled detail failed:"
						+ exception.getMessage());
			}
			LOGGER.info("lasts months enrolled detail failed:"
					+ exception.getMessage());
		}
		JobseekerController.LOGGER.exit();
		return "jobseeker_renewal_alert";
	}

	@RequestMapping(value = "/jobseeker_renewal_details", method = RequestMethod.GET)
	public String renewalAdminJobseekersList(Model model,
			HttpServletRequest request) {
		JobseekerController.LOGGER.entry();
		int page = 1;
		final String paging = request.getParameter("page");
		try {
			// Retrieve Renewal Jobseeker List.
			final HttpSession session = request.getSession();
			String email = (String) session.getAttribute("emailId");
			JobseekerBO jobseekerBO = this.jobSeekerService
					.renewalJobseekerAlert(email);
			List<JobseekerBO> jobseekerRenewalList = jobseekerBO
					.getRegisteredList();
			// pagination process
			if (null != paging) {
				page = Integer.parseInt(paging);
				@SuppressWarnings("unchecked")
				final ResponseObject<JobseekerBO> reponseObject = this
				.jobpagination1(page, jobseekerRenewalList);
				model.addAttribute("jobseekerRenewalList", reponseObject);
			} else {
				if (0 != jobseekerRenewalList.size()) {
					@SuppressWarnings({ "unchecked" })
					final ResponseObject<JobseekerBO> responseObject = this
					.jobpagination1(1, jobseekerRenewalList);
					model.addAttribute("jobseekerRenewalList", responseObject);
				} else {
					model.addAttribute("message",
							"Retrive Failer!..please contact Administrator.. ");
				}
			}
		} catch (final Exception e) {
			e.printStackTrace();
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("jobseeker renewal detail failed:"
						+ e.getMessage());
			}
			LOGGER.info("jobseeker renewal detail failed:" + e.getMessage());
		}
		JobseekerController.LOGGER.exit();
		return "jobseeker_renewal_details";
	}

	@RequestMapping(value = "/edit_jobseeker_update_experience", method = RequestMethod.GET)
	public String editupdateExp(Model model, HttpServletRequest request)
			throws SerialException, MyJobKartException, SQLException,
			ParseException {
		DateFormat format = new SimpleDateFormat("MM/dd/yyyy", Locale.US);
		// All CompanyList
		companyList(model);
		industryList(model);
		getCompanyType(model);
		getCity(model);
		getStates(model);
		getLang(model);
		dropdown(model);
		JobseekerController.LOGGER.entry();
		long professionalId = 0;
		HttpSession session = request.getSession();
		if (null == session.getAttribute("jobseekerId")) {
			return "redirect:/jobseeker_sign_in.html";
		}
		final String id = request.getParameter("id");
		JobseekerProfileBO profileBO = new JobseekerProfileBO();
		if (null != id) {
			final long jobseekerId = Long.parseLong(id);
			profileBO.setId(jobseekerId);
		}
		String experienceId = request.getParameter("prfId");
		if (null != experienceId) {
			professionalId = Long.parseLong(experienceId);
			session.setAttribute("professionalId", professionalId);
		}
		JobseekerProfileBO eduDetail = new JobseekerProfileBO();
		if (professionalList.size() > 0) {
			for (JobseekerProfileBO jobedu : professionalList) {
				if (jobedu.getProfesId() == professionalId) {
					eduDetail.setCompanyName(jobedu.getCompanyName());
					eduDetail.setCompanyType(jobedu.getCompanyType());
					eduDetail.setDesignation(jobedu.getDesignation());
					eduDetail.setLastSalary(jobedu.getLastSalary());
					eduDetail.setExpStartDate(format.parse(jobedu
							.getStartDate()));
					eduDetail.setExpEndDate(format.parse(jobedu.getEndDate()));
					eduDetail.setExpStatus(jobedu.getExpStatus());
				}
			}
			eduDetail.setProfesId(professionalId);
		}
		model.addAttribute("expStatus", eduDetail.getExpStatus());
		model.addAttribute("experienceDetails", professionalList);
		model.addAttribute("updateProfile", eduDetail);
		model.addAttribute("type", "edit");
		JobseekerController.LOGGER.exit();
		return "jobseeker_update_experience";
	}

	@RequestMapping(value = "/edit_jobseeker_update_educational", method = RequestMethod.GET)
	public String editupdateEdu(Model model, HttpServletRequest request)
			throws SerialException, MyJobKartException, SQLException {
		getCompanyType(model);
		industryList(model);
		getCity(model);
		getStates(model);
		getLang(model);
		dropdown(model);
		long educationId = 0;
		JobseekerController.LOGGER.entry();
		HttpSession session = request.getSession();
		if (null == session.getAttribute("jobseekerId")) {
			return "redirect:/jobseeker_sign_in.html";
		}
		final String id = request.getParameter("id");
		JobseekerProfileBO profileBO = new JobseekerProfileBO();
		if (null != id) {
			final long jobseekerId = Long.parseLong(id);
			profileBO.setId(jobseekerId);
		}
		String eduId = request.getParameter("eduId");
		if (null != eduId) {
			educationId = Long.parseLong(eduId);
			session.setAttribute("educationId", educationId);
		}
		JobseekerProfileBO eduDetail = new JobseekerProfileBO();
		if (educationList.size() > 0) {
			for (JobseekerProfileBO jobedu : educationList) {
				if (jobedu.getEducationId() == educationId) {
					eduDetail.setDegree(jobedu.getDegree());
					eduDetail.setDepartment(jobedu.getDepartment());
					eduDetail.setCollege(jobedu.getCollege());
					eduDetail.setYearOfPassing(jobedu.getYearOfPassing());
					eduDetail.setPercentage(jobedu.getPercentage());
					eduDetail.setGrade(jobedu.getGrade());
				}
			}
			eduDetail.setEducationId(educationId);
		}
		model.addAttribute("eduupdateProfile", educationList);
		model.addAttribute("updateProfile", eduDetail);
		model.addAttribute("type", "edit");
		JobseekerController.LOGGER.exit();
		return "jobseeker_update_educational";
	}

	@RequestMapping(value = "/jobseeker_update_experience", method = RequestMethod.GET)
	public String updateProfessional(Model model, HttpServletRequest request)
			throws SerialException, MyJobKartException, SQLException,
			ParseException {
		DateFormat format = new SimpleDateFormat("MM/dd/yyyy", Locale.US);
		// All CompanyList
		companyList(model);
		industryList(model);
		getCompanyType(model);
		getCity(model);
		getStates(model);
		getLang(model);
		dropdown(model);
		JobseekerController.LOGGER.entry();
		long educationId = 0;
		HttpSession session = request.getSession();
		if (null == session.getAttribute("jobseekerId")) {
			return "redirect:/jobseeker_sign_in.html";
		}
		final String id = request.getParameter("id");
		JobseekerProfileBO profileBO = new JobseekerProfileBO();
		if (null != id) {
			final long jobseekerId = Long.parseLong(id);
			session.setAttribute("jobprofileId", jobseekerId);
			profileBO.setId(jobseekerId);
		}
		String profileId = request.getParameter("profileId");
		if (null != profileId) {
			final long jobseekerId = Long.parseLong(profileId);
			profileBO.setId(jobseekerId);
		}
		String eduId = request.getParameter("eduId");
		if (null != eduId) {
			educationId = Long.parseLong(eduId);
			session.setAttribute("educationId", educationId);
		}
		JobseekerProfileBO jobeduProfile = this.jobSeekerService
				.retriveJobseeker(profileBO);
		professionalList = jobeduProfile.getJobprofessionalList();
		JobseekerProfileBO eduDetail = new JobseekerProfileBO();
		if (professionalList.size() > 0) {
			for (JobseekerProfileBO jobedu : professionalList) {
				eduDetail.setCompanyName(jobedu.getCompanyName());
				eduDetail.setCompanyType(jobedu.getCompanyType());
				eduDetail.setDesignation(jobedu.getDesignation());
				eduDetail.setLastSalary(jobedu.getLastSalary());
				eduDetail.setExpStartDate(format.parse(jobedu.getStartDate()));
				eduDetail.setExpEndDate(format.parse(jobedu.getEndDate()));
				eduDetail.setExpStatus(jobedu.getExpStatus());
			}
		}
		model.addAttribute("expStatus", eduDetail.getExpStatus());
		model.addAttribute("experienceDetails", professionalList);
		model.addAttribute("updateProfile", eduDetail);
		model.addAttribute("Infomessage", "May be the FRESHER Candidate!");
		JobseekerController.LOGGER.exit();
		return "jobseeker_update_experience";
	}

	@RequestMapping(value = "/jobseeker_update_experience", method = RequestMethod.POST)
	public String updateProfessional(
			@Valid @ModelAttribute("updateProfile") JobseekerProfileBO updateProfile,
			BindingResult result, HttpServletRequest request, Model model)
					throws MyJobKartException, IOException, SerialException,
					SQLException, ValidatorException {
		// All CompanyList
		companyList(model);
		industryList(model);
		JobseekerController.LOGGER.entry();
		HttpSession session = request.getSession();
		long professionalId = 0;
		if (null == session.getAttribute("jobseekerId")) {
			return "redirect:/jobseeker_sign_in.html";
		}
		try {
			String email = (String) session.getAttribute("emailId");
			long jobSeekersId = (Long) session.getAttribute("jobseekerId");
			updateProfile.setEmailId(email);
			updateProfile.getEducationId();
			JobseekerProfileBO jobeduProfile = new JobseekerProfileBO();
			String getCompany = updateProfile.getAddCompany();
			if (!getCompany.isEmpty()) {
				updateProfile.setCompanyName(getCompany);
			}
			EntityBO entity = new EntityBO();
			ArrayList<String> comNameList = new ArrayList<String>();
			comNameList.add(getCompany);
			entity.setEntityName(comNameList);
			entity.setCreatedBy(jobSeekersId);
			entity = adminService.createCompany(entity);
			long companyID = 0;
			// Get Company Id
			String companyNamelist = updateProfile.getCompanyName();
			if (null != companyNamelist) {
				List<String> companyList = new ArrayList<String>();
				companyList.add(companyNamelist);
				companyID = employerService.retriveCompanyId(companyList);
				if (companyID != 0) {
					updateProfile.setCompanyId(companyID);
				}
			}
			if (result.hasErrors()) {
				getCompanyType(model);
				model.addAttribute("type", "edit");
				model.addAttribute("experienceDetails", professionalList);
				return "jobseeker_update_experience";
			}
			if ((Long) session.getAttribute("professionalId") > 0) {
				professionalId = (Long) session.getAttribute("professionalId");
			}
			if (null != session.getAttribute("jobprofileId")) {
				JobseekerProfileBO profileBO = new JobseekerProfileBO();
				long jobSeekerId = (Long) session.getAttribute("jobprofileId");
				profileBO.setId(jobSeekerId);
				updateProfile.setCreatedBy(jobSeekerId);
				jobeduProfile = this.jobSeekerService
						.retriveJobseeker(profileBO);
			}
			DateFormat formate = new SimpleDateFormat("MM/dd/yyyy", Locale.US);
			professionalList = jobeduProfile.getJobprofessionalList();
			if (professionalList.size() > 0) {
				for (JobseekerProfileBO jobedu : professionalList) {
					if (jobedu.getProfesId() == professionalId) {
						if (updateProfile.getCompanyName().isEmpty()) {
							updateProfile.setCompanyName(jobedu
									.getCompanyName());
						}
						if (updateProfile.getCompanyType().isEmpty()) {
							updateProfile.setCompanyType(jobedu
									.getCompanyType());
						}
						if (updateProfile.getDesignation().isEmpty()) {
							updateProfile.setDesignation(jobedu
									.getDesignation());
						}
						if (updateProfile.getLastSalary().isEmpty()) {
							updateProfile.setLastSalary(jobedu.getLastSalary());
						}
						if (null == updateProfile.getExpStartDate()) {
							updateProfile.setExpStartDate(formate.parse(jobedu
									.getStartDate()));
						}
						if (null == updateProfile.getExpEndDate()) {
							updateProfile.setExpEndDate(formate.parse(jobedu
									.getEndDate()));
						}
					}
				}
			}
			updateProfile.setProfesId(professionalId);
			updateProfile.setModifiedBy(jobSeekersId);
			updateProfile.setJobSeekerLogin(this.reteriveprofile
					.getJobSeekerLogin());
			updateProfile = this.jobSeekerService.expDetails(updateProfile);
			if (null != updateProfile.getResponse()) {
				model.addAttribute("successmessage",
						updateProfile.getResponse());
				return "redirect:/jobseeker_profile_view.html";
			} else {
				model.addAttribute("Infomessage",
						"data has been updated failed,please contact Administrator.");
				return "jobseeker_update_experience";
			}
		} catch (Exception jb) {
			jb.printStackTrace();
		}
		JobseekerController.LOGGER.exit();
		return "jobseeker_update_experience";
	}

	@RequestMapping(value = "/jobseeker_update_educational", method = RequestMethod.GET)
	public String updateEdu(Model model, HttpServletRequest request)
			throws SerialException, MyJobKartException, SQLException {

		getCompanyType(model);
		getCity(model);
		getStates(model);
		getLang(model);
		dropdown(model);
		JobseekerController.LOGGER.entry();
		long educationId = 0;
		HttpSession session = request.getSession();
		if (null == session.getAttribute("jobseekerId")) {
			return "redirect:/jobseeker_sign_in.html";
		}
		final String id = request.getParameter("id");
		JobseekerProfileBO profileBO = new JobseekerProfileBO();
		if (null != id) {
			final long jobseekerId = Long.parseLong(id);
			session.setAttribute("SeekerProfileId", jobseekerId);
			profileBO.setId(jobseekerId);
		}
		String profileId = request.getParameter("profileId");
		if (null != profileId) {
			final long jobseekerId = Long.parseLong(profileId);
			profileBO.setId(jobseekerId);
		}
		String eduId = request.getParameter("eduId");
		if (null != eduId) {
			educationId = Long.parseLong(eduId);
			session.setAttribute("educationId", educationId);
		}
		JobseekerProfileBO eduDetail = new JobseekerProfileBO();
		JobseekerProfileBO jobeduProfile = this.jobSeekerService
				.retriveJobseeker(profileBO);
		educationList = jobeduProfile.getJobEductionProfileList();
		if (educationList.size() > 0) {
			for (JobseekerProfileBO jobedu : educationList) {
				if (jobedu.getEducationId() == educationId) {
					eduDetail.setDegree(jobedu.getDegree());
					eduDetail.setDepartment(jobedu.getDepartment());
					eduDetail.setCollege(jobedu.getCollege());
					eduDetail.setYearOfPassing(jobedu.getYearOfPassing());
					eduDetail.setPercentage(jobedu.getPercentage());
					eduDetail.setGrade(jobedu.getGrade());
				}
			}
		}
		model.addAttribute("eduupdateProfile", educationList);
		model.addAttribute("updateProfile", eduDetail);
		JobseekerController.LOGGER.exit();
		return "jobseeker_update_educational";
	}

	@RequestMapping(value = "/jobseeker_update_educational", method = RequestMethod.POST)
	public String updateEdu(
			@Valid @ModelAttribute("updateProfile") JobseekerProfileBO updateProfile,
			BindingResult result, HttpServletRequest request, Model model)
					throws MyJobKartException, IOException, SerialException,
					SQLException, ValidatorException {
		long educationId = 0;
		JobseekerController.LOGGER.entry();
		HttpSession session = request.getSession();
		if (null == session.getAttribute("jobseekerId")) {
			return "redirect:/jobseeker_sign_in.html";
		}
		try {
			String email = (String) session.getAttribute("emailId");
			updateProfile.setEmailId(email);
			updateProfile.getEducationId();
			if (result.hasErrors()) {
				dropdown(model);
				model.addAttribute("type", "edit");
				model.addAttribute("eduupdateProfile", educationList);
				return "jobseeker_update_educational";
			}
			long jobSeekerId = (Long) session.getAttribute("jobseekerId");
			if ((Long) session.getAttribute("educationId") > 0) {
				educationId = (Long) session.getAttribute("educationId");
			}
			updateProfile.setEducationId(educationId);
			updateProfile.setCreatedBy(jobSeekerId);
			updateProfile.setIsActive(this.reteriveprofile.getIsActive());
			updateProfile.setModifiedBy(jobSeekerId);
			updateProfile.setIsDelete(true);
			updateProfile.setJobSeekerLogin(this.reteriveprofile
					.getJobSeekerLogin());
			String yearOfPassing = updateProfile.getYearOfPassing();
			int yearLen = yearOfPassing.length();
			if (0 != yearLen) {
				if (yearLen > 4) {
					model.addAttribute("eduupdateProfile",
							"invalid void format");
				}
			}
			if (null != session.getAttribute("SeekerProfileId")) {
				long profileId = (long) session.getAttribute("SeekerProfileId");
				updateProfile.setProfesId(profileId);
			}
			updateProfile.setVersion(this.reteriveprofile.getVersion());
			updateProfile = this.jobSeekerService.eduProfile(updateProfile);
			if (null != updateProfile.getResponse()) {
				model.addAttribute("successmessage",
						updateProfile.getResponse());
				return "redirect:/jobseeker_profile_view.html";
			} else {
				model.addAttribute("Infomessage",
						"data has been updated failed,please contact Administrator.");
				return "jobseeker_update_educational";
			}
		} catch (Exception jb) {
			jb.printStackTrace();
		}
		JobseekerController.LOGGER.exit();
		return "jobseeker_update_educational";
	}

	@RequestMapping(value = "/jobseeker_create_jobalert", method = RequestMethod.GET)
	public String createJobAlert(Model model, HttpServletRequest request)
			throws MyJobKartException {
		industryList(model);
		HttpSession session = request.getSession();
		if (null == session.getAttribute("jobseekerId")) {
			return "redirect:/jobseeker_sign_in.html";
		}
		JobAlertBO jobAlertBO = new JobAlertBO();
		model.addAttribute("jobAlertBO", jobAlertBO);
		return "jobseeker_create_jobalert";
	}

	public void getKeySkills(Model model) {
		List<String> keyskillsList = new ArrayList<String>();
		model.addAttribute("keyskillsList", keyskillsList);
	}

	public void getPreferedLocation(Model model) {
		List<String> locationList = new ArrayList<String>();
		model.addAttribute("locationList", locationList);
	}

	public void getSalary(Model model) {
		List<String> salaryList = new ArrayList<String>();
		model.addAttribute("salaryList", salaryList);
	}

	public void getExperienceInYear(Model model) {
		List<String> experienceInYearList = new ArrayList<String>();
		model.addAttribute("experienceInYearList", experienceInYearList);
	}

	public void getAlertName(Model model) {
		List<String> alertList = new ArrayList<String>();
		model.addAttribute("alertList", alertList);
	}

	public void getRole(Model model) {
		List<String> roleList = new ArrayList<String>();
		model.addAttribute("roleList", roleList);
	}

	public void getJobType(Model model) {
		List<String> jobTypeList = Dropdownutils.getJobType();
		model.addAttribute("jobTypeList", jobTypeList);
	}

	public void getExperience(Model model) {
		List<String> experienceList = new ArrayList<String>();
		model.addAttribute("experienceList", experienceList);
	}

	@RequestMapping(value = "/jobseeker_create_jobalert", method = RequestMethod.POST)
	public String createJobAlert(
			@Valid @ModelAttribute("jobAlertBO") JobAlertBO alert,
			BindingResult result, HttpServletRequest request, Model model)
					throws MyJobKartException, IOException, NumberFormatException,
					SerialException, SQLException, ValidatorException {
		JobseekerController.LOGGER.entry();
		try {
			industryList(model);
			HttpSession session = request.getSession();
			if (null == session.getAttribute("jobseekerId")) {
				return "redirect:/jobseeker_sign_in.html";
			}
			if (result.hasErrors()) {
				return "jobseeker_create_jobalert";
			}
			long jobSeekerId = (Long) session.getAttribute("jobseekerId");
			String keySkills = alert.getKeySkills().replaceAll("<br>", "");
			alert.setKeySkills(keySkills);
			String alertName = alert.getAlertName().replaceAll("<br>", "");
			alert.setAlertName(alertName);
			String role = alert.getRole().replaceAll("<br>", "");
			alert.setRole(role);
			alert.setCreatedBy(jobSeekerId);
			alert.setModifiedBy(jobSeekerId);
			alert.setIsActive(false);
			alert.setIsDeleted(false);
			alert = this.jobSeekerService.createJobAlert(alert);
			if (null != alert.getResponse()) {
				model.addAttribute("successmessage",
						"Your alert has been successfully created...Please Check your Email...");
				return "redirect:/jobseeker_jobalert_view.html";
			} else {
				model.addAttribute("infomessage",
						"Your account does not exist,please contact Administrator.");
				return "jobseeker_create_jobalert";
			}
		} catch (final NullPointerException ne) {
			ne.printStackTrace();
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("Jobseeker alert creation failed:"
						+ ne.getMessage() + ne);
			}
			LOGGER.info("Jobseeker alert creation failed:" + ne.getMessage()
					+ ne);
		}
		JobseekerController.LOGGER.exit();
		return "jobseeker_create_jobalert";
	}

	@RequestMapping(value = "/jobseeker_jobalert_view", method = RequestMethod.GET)
	public String retriveAlert(Model model, HttpServletRequest request)
			throws MyJobKartException, IOException, SerialException,
			SQLException {
		JobseekerController.LOGGER.entry();
		HttpSession session = request.getSession();

		model.addAttribute("viewJobalerSearch", new JobAlertBO());

		if (null == session.getAttribute("jobseekerId")) {
			return "redirect:/jobseeker_sign_in.html";
		}
		long jobseekerId = Long.parseLong(session.getAttribute("jobseekerId")
				.toString());
		if (null != request.getParameter("successmessage")) {

			model.addAttribute("successmessage",
					request.getParameter("successmessage"));
		}
		if (null != request.getParameter("Infomessage")) {

			model.addAttribute("Infomessage",
					request.getParameter("Infomessage"));
		}
		if (null != request.getParameter("errorMessage")) {

			model.addAttribute("errorMessage",
					request.getParameter("errorMessage"));
		}
		JobAlertBO retrivealert = new JobAlertBO();
		retrivealert.setJobSeekerId(jobseekerId);
		try {
			List<JobAlertBO> jobAlertBoList = jobSeekerService
					.retriveJobAlert(retrivealert);
			if (null != jobAlertBoList && 0 != jobAlertBoList.size()) {
				model.addAttribute("jobAlertBoList", jobAlertBoList);
			} else {
				model.addAttribute("Infomessage",
						"No jobAlert Found!..please create the jobAlert.. ");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "jobseeker_jobalert_view";
	}

	@RequestMapping(value = "/viewJobalerSearch", method = RequestMethod.POST)
	public String viewJobalerSearch(
			@Valid @ModelAttribute("viewJobalerSearch") JobseekerProfileBO search,
			BindingResult result, HttpServletRequest request, Model model) {
		HttpSession session = request.getSession();
		try {
			JobAlertBO retrivealert = new JobAlertBO();
			long jobseekerId = Long.parseLong(session.getAttribute(
					"jobseekerId").toString());

			retrivealert.setSearchElement(search.getSearchElement());
			retrivealert.setJobSeekerId(jobseekerId);

			List<JobAlertBO> jobAlertBoList = jobSeekerService
					.retriveJobAlert(retrivealert);
			String userType = (String) session.getAttribute("userType");
			model.addAttribute("userType", userType);

			if (null != jobAlertBoList) {
				if (jobAlertBoList.size() == 0) {
					model.addAttribute("errormessage", "No record found..");
				}
				model.addAttribute("jobAlertBoList", jobAlertBoList);

				if (null != session.getAttribute("jobseekerId")) {
					return "jobseeker_jobalert_view";
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "jobseeker_jobalert_view";
	}

	@RequestMapping(value = "/jobseeker_delete_jobalert", method = RequestMethod.GET)
	public String deleteAlert(Model model, HttpServletRequest request)
			throws MyJobKartException {
		JobseekerController.LOGGER.entry();
		String id = null;
		long deletedId = 0;
		long loginId = 0;
		try {
			HttpSession session = request.getSession();
			if (null == session.getAttribute("jobseekerId")) {
				return "redirect:/jobseeker_sign_in.html";
			}
			if (null != request.getParameter("id")) {
				id = request.getParameter("id");
				deletedId = Long.parseLong(id);
				loginId = (Long) session.getAttribute("jobseekerId");
				JobAlertBO deleteAlert = new JobAlertBO();
				deleteAlert.setId(deletedId);
				deleteAlert.setIsDeleted(true);
				deleteAlert.setModifiedBy(loginId);
				deleteAlert = this.jobSeekerService.deleteAlert(deleteAlert);
				if (null != deleteAlert.getResponse()) {
					model.addAttribute("successmessage",
							deleteAlert.getResponse());
				} else {
					model.addAttribute("Infomessagemessage",
							"data has been updated failed,please contact Administrator.");
				}
			} else {
				model.addAttribute("errorMessage",
						"Some thing went wrong. Contact Administrators!");
			}
		} catch (final MyJobKartException jb) {
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("Jobseeker delete Alert failed:"
						+ jb.getErrorCode() + jb);
			}
			LOGGER.info("Jobseeker delete Alert failed:" + jb.getErrorCode()
					+ jb);
		}
		JobseekerController.LOGGER.exit();
		return "redirect:/jobseeker_jobalert_view.html";
	}

	@RequestMapping(value = "/jobseeker_jobalert_status", method = RequestMethod.GET)
	public String jobalertStatus(Model model, HttpServletRequest request)
			throws Exception {
		JobseekerController.LOGGER.entry();
		try {
			long alertActiveCount = 0;
			long alertId = 0;
			HttpSession session = request.getSession();
			if (null == session.getAttribute("jobseekerId")) {
				return "redirect:/jobseeker_sign_in.html";
			}
			if (0 < Long.parseLong(request.getParameter("id").toString())) {
				alertId = Long.parseLong(request.getParameter("id"));
			}
			String status = request.getParameter("status");
			long jobseekerId = (long) session.getAttribute("jobseekerId");
			JobAlertBO statusBO = new JobAlertBO();
			statusBO.setId(alertId);
			JobAlertBO retrivealert = new JobAlertBO();
			retrivealert.setJobSeekerId(jobseekerId);
			List<JobAlertBO> jobAlertBoList = jobSeekerService
					.retriveJobAlert(retrivealert);
			if (null != jobAlertBoList) {
				for (JobAlertBO alertBO : jobAlertBoList) {
					if (alertBO.getIsActive()) {
						alertActiveCount++;
					}
				}
			}
			if ((0 == alertActiveCount && status.equalsIgnoreCase("De-Active"))
					|| (0 != alertActiveCount && status
					.equalsIgnoreCase("Active"))) {
				if (status.equals("Active")) {
					statusBO.setIsActive(false);
				} else {
					statusBO.setIsActive(true);
				}
				boolean jobAlertBO = jobSeekerService.jobalertStatus(statusBO);
				if (jobAlertBO) {
					if (statusBO.getIsActive()) {
						model.addAttribute("successmessage",
								"JobAlert Activation is success.");
					} else {
						model.addAttribute("successmessage",
								"JobAlert De-Activation is success.");
					}
					return "redirect:/jobseeker_jobalert_view.html";
				} else {
					model.addAttribute("Infomessage",
							"Your account does not exisit,please contact Administrator.");
					return "redirect:/jobseeker_jobalert_view.html";
				}
			} else {
				model.addAttribute("Infomessage",
						"One JobAlert only is Activate ");
				return "redirect:/jobseeker_jobalert_view.html";
			}
		} catch (final NullPointerException ne) {
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("Jobseeker jobalert status failed:"
						+ ne.getMessage() + ne);
			}
			LOGGER.info("Jobseeker jobalert status failed:" + ne.getMessage()
					+ ne);
		}
		JobseekerController.LOGGER.exit();
		return "redirect:/jobseeker_jobalert_view.html";
	}

	@RequestMapping(value = "/jobseeker_update_jobalert", method = RequestMethod.GET)
	public String updateAlert(Model model, HttpServletRequest request)
			throws SerialException, MyJobKartException, SQLException,
			ParseException {
		JobseekerController.LOGGER.entry();
		industryList(model);
		try {
			HttpSession session = request.getSession();
			if (null == session.getAttribute("jobseekerId")) {
				return "redirect:/jobseeker_sign_in.html";
			}
			final String id = request.getParameter("id");
			final long jobseekerId = Long.parseLong(id);
			JobAlertBO reterivealert = new JobAlertBO();
			reterivealert.setId(jobseekerId);
			reterivealert = jobSeekerService.retriveAlert(reterivealert);
			if (null != reterivealert) {
				model.addAttribute("updateAlert", reterivealert);
			} else {
				model.addAttribute("errorMessage",
						"Some thing went wrong. Contact Administrators!");
				return "redirect:/jobseeker_jobalert_view.html";
			}
		} catch (final NullPointerException ne) {
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("Jobseeker JobAlert update get:" + ne.getMessage()
						+ ne);
			}
			LOGGER.info("Jobseeker JobAlert update get:" + ne.getMessage() + ne);
		}
		JobseekerController.LOGGER.exit();
		return "jobseeker_update_jobalert";
	}

	@RequestMapping(value = "/jobseeker_update_jobalert", method = RequestMethod.POST)
	public String updateAlert(
			@Valid @ModelAttribute("updateAlert") JobAlertBO updateJobAlert,
			BindingResult result, HttpServletRequest request, Model model)
					throws MyJobKartException, IOException, SerialException,
					SQLException, ValidatorException {
		JobseekerController.LOGGER.entry();
		try {
			HttpSession session = request.getSession();
			if (null == session.getAttribute("jobseekerId")) {
				return "redirect:/jobseeker_sign_in.html";
			}
			if (result.hasErrors()) {
				return "jobseeker_update_jobalert";
			}
			long jobSeekerId = (Long) session.getAttribute("jobseekerId");
			updateJobAlert.setJobSeekerId(jobSeekerId);
			updateJobAlert = this.jobSeekerService.updateAlert(updateJobAlert);
			if (null != updateJobAlert.getResponse()) {
				model.addAttribute("successmessage",
						updateJobAlert.getResponse());
				return "redirect:/jobseeker_jobalert_view.html";
			} else {
				model.addAttribute("errorMessage",
						"data has been updated failed,please contact Administrator.");
				return "jobseeker_update_jobalert";
			}
		} catch (Exception jb) {
			jb.printStackTrace();
		}
		JobseekerController.LOGGER.exit();
		return "jobseeker_update_jobalert";
	}

	@RequestMapping(value = "/jobseeker_jobalert_details", method = RequestMethod.GET)
	public String jobalertDetails(Model model, HttpServletRequest request)
			throws SQLException, MyJobKartException {
		JobseekerController.LOGGER.entry();
		try {
			HttpSession session = request.getSession();
			if (null == session.getAttribute("jobseekerId")) {
				return "redirect:/jobseeker_sign_in.html";
			}
			final String id = request.getParameter("id");
			final long jobseekerId = Long.parseLong(id);
			JobAlertBO reterivealert = new JobAlertBO();
			reterivealert.setId(jobseekerId);
			reterivealert = jobSeekerService.retriveAlert(reterivealert);
			if (null != reterivealert) {
				model.addAttribute("jobalertDetail", reterivealert);
				return "jobseeker_jobalert_details";
			}
		} catch (final NullPointerException ne) {
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("Jobseeker JobAlert update get:" + ne.getMessage()
						+ ne);
			}
			LOGGER.info("Jobseeker JobAlert update get:" + ne.getMessage() + ne);
		}
		JobseekerController.LOGGER.exit();
		return "jobseeker_jobalert_details";
	}

	@RequestMapping(value = "/email_verifications", method = RequestMethod.POST)
	public @ResponseBody
	String emailVerifications(Model model, @RequestParam String email)
			throws ParseException {
		boolean status = jobSeekerService.emailVerifications(email);
		if (status) {
			return "Email Id already existed in the table";
		} else {
			return "notExist";
		}
	}

	@RequestMapping(value = "/Mobile_verification", method = RequestMethod.POST)
	public @ResponseBody
	String Mobile_verification(Model model, @RequestParam String mobileNo)
			throws ParseException {
		boolean status = jobSeekerService.Mobile_verification(mobileNo);
		if (status) {
			return "mobile no already existed in the table";
		} else {
			return "notExist";
		}
	}

	@RequestMapping(value = "/download_resume", method = RequestMethod.GET)
	public void downloadResumes(@RequestParam("id") Integer itemId,
			HttpServletResponse response, HttpServletRequest request,
			Model model) throws ServletException, IOException, SQLException,
			JRException {
		JobseekerController.LOGGER.entry();
		JasperPrint jasperPrint = null;
		List<JobseekerProfileBO> profileBeanResources = new ArrayList<JobseekerProfileBO>();
		List<JobseekerProfileBO> profileBOList = new ArrayList<JobseekerProfileBO>();
		List<JobseekerProfileBO> experienceList = new ArrayList<JobseekerProfileBO>();
		// Jasper File Locations
		String jasperLocation = JobtrolleyResourceBundle
				.getValue("download.Resume");
		String subReport = JobtrolleyResourceBundle
				.getValue("download.subReport");
		ByteArrayOutputStream baos = new ByteArrayOutputStream();

		try {

			String id = request.getParameter("id");
			long jobseekerId = Long.parseLong(id);

			JobseekerProfileBO profileBO = new JobseekerProfileBO();
			profileBO.setId(jobseekerId);

			JobseekerProfileBO reteriveprofileBO = this.jobSeekerService
					.retriveJobseeker(profileBO);

			profileBeanResources = reteriveprofileBO.getJobseekerProfileList();

			profileBOList = reteriveprofileBO.getJobEductionProfileList();

			experienceList = reteriveprofileBO.getJobprofessionalList();

			JobseekerProfileBO jobseekerProfiles = profileBeanResources.get(0);

			JasperReport jasperReport = JasperCompileManager
					.compileReport(jasperLocation);
			JasperReport jasperSubReport = JasperCompileManager
					.compileReport(subReport);
			profileBeanResources.addAll(profileBOList);
			profileBeanResources.addAll(experienceList);
			JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource(
					profileBeanResources);
			JRDataSource jrDataSources = new JRBeanCollectionDataSource(
					experienceList);

			Map<String, Object> mapobj = new HashMap<String, Object>();
			mapobj.put("profileBeanResources", profileBeanResources);
			mapobj.put("experienceSubReport", jasperSubReport);
			mapobj.put("experienceDataSouces", jrDataSources);
			mapobj.put("keySkills", jobseekerProfiles.getKeySkills());
			mapobj.put("profileImage", jobseekerProfiles.getProfilePicture());
			jasperPrint = JasperFillManager
					.fillReport(jasperReport, mapobj, ds);

			JasperExportManager.exportReportToPdfStream(jasperPrint, baos);

			ServletOutputStream outStream = response.getOutputStream();
			response.setContentType("application/pdf");
			response.setContentLength(baos.size());
			baos.writeTo(outStream);

			outStream.flush();
			outStream.close();

		} catch (MyJobKartException e) {
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("Reterive Report Details  failed:"
						+ e.getMessage() + e);
			}
			LOGGER.info("Reterive Report Details failed:" + e.getMessage() + e);
		}
		JobseekerController.LOGGER.exit();

	}

	@RequestMapping(value = "/captcha_varificationss", method = RequestMethod.POST)
	public @ResponseBody
	String captchaVarificationss(@RequestParam String values) {
		if (ranvalue != 0) {
			try {
				int x = Integer.parseInt(values);
			} catch (NumberFormatException ne) {
				return "failed";
			}
			if (ranvalue == Integer.parseInt(values)) {
				return "success";
			}
		}
		return "failed";
	}

	public void companyList(Model model) {
		LOGGER.entry();
		try {
			List<CompanyEntity> allCompanyList = employerService
					.retrieveCompanyList();
			if (null != allCompanyList && allCompanyList.size() != 0) {
				List<String> companyList = new ArrayList<String>();
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

	public void industryList(Model model) {
		LOGGER.entry();
		try {
			List<EntityBO> allIndustryList = adminService
					.retrieveIndustryList();
			if (null != allIndustryList && allIndustryList.size() != 0) {
				List<String> industryList = new ArrayList<String>();
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

	@RequestMapping(value = "/create_jobalert", method = RequestMethod.GET)
	public String createJobalert(Model model, HttpServletRequest request)
			throws MyJobKartException {
		HttpSession session = request.getSession();
		if (null != session.getAttribute("jobseekerId")) {
			return "redirect:/jobseeker_create_jobalert";
		} else if (null != session.getAttribute("loginId")) {
			return "redirect:/employer_sign_in.html";
		} else if (null != session.getAttribute("adminId")) {
			return "redirect:/admin_sign_in";
		}
		industryList(model);
		if (null != request.getParameter("successmessage")) {
			model.addAttribute("successmessage",
					request.getParameter("successmessage"));
		}
		if (null != request.getParameter("Infomessage")) {
			model.addAttribute("Infomessage",
					request.getParameter("Infomessage"));
		}
		if (null != request.getParameter("Errormessage")) {
			model.addAttribute("Errormessage",
					request.getParameter("Errormessage"));
		}
		JobAlertBO jobalertBO = new JobAlertBO();
		model.addAttribute("jobalert", jobalertBO);
		return "create_jobalert";
	}

	@RequestMapping(value = "/create_jobalert", method = RequestMethod.POST)
	public String createJobalert(
			@Valid @ModelAttribute("jobalert") JobAlertBO jobalert,
			BindingResult result, HttpServletRequest request, Model model)
					throws MyJobKartException, IOException, NumberFormatException,
					SerialException, SQLException, ValidatorException {
		JobseekerController.LOGGER.entry();
		try {
			industryList(model);
			String keySkills = jobalert.getKeySkills().replaceAll("<br>", "");
			jobalert.setKeySkills(keySkills);
			String alertName = jobalert.getAlertName().replaceAll("<br>", "");
			jobalert.setAlertName(alertName);
			String role = jobalert.getRole().replaceAll("<br>", "");
			jobalert.setRole(role);
			final File rootDir = new File(
					this.servletContext.getRealPath("/WEB-INF/images/male.png"));
			final BufferedImage image = ImageIO.read(rootDir);
			final ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ImageIO.write(image, "png", baos);
			final byte[] res = baos.toByteArray();
			jobalert.setProfileImage(res);
			if (this.jobSeekerService.findByEmail(jobalert.getEmailId())) {
				model.addAttribute("Infomessage", "Email Already Exists");
				return "create_jobalert";
			}
			jobalert = jobSeekerService.createAlert(jobalert);
			if (null != jobalert.getResponse()) {
				model.addAttribute("successmessage",
						"Your alert has been successfully created...Please Check your Email...");
				return "redirect:/create_jobalert.html";
			} else {
				model.addAttribute("Errormessage",
						"Your alert has not created successfully.....");
				return "create_jobalert";
			}
		} catch (final NullPointerException ne) {
			ne.printStackTrace();
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("Jobseeker alert creation failed:"
						+ ne.getMessage() + ne);
			}
			LOGGER.info("Jobseeker alert creation failed:" + ne.getMessage()
					+ ne);
		}
		JobseekerController.LOGGER.exit();
		return "create_jobalert";
	}
	
	@RequestMapping(value = "/jobseeker_report_view", method = RequestMethod.GET)
	public String jobseekerReportView(Model model, HttpServletRequest request)
			throws MyJobKartException, SQLException {
		JobseekerController.LOGGER.entry();
		
		
		
		JobseekerActivityBO jobseekerActivityBO = new JobseekerActivityBO();
		HttpSession session = request.getSession();
		if (null == session.getAttribute("loginId")
				&& null == session.getAttribute("adminId")) {
			return "redirect:/employer_sign_in.html";
		}
		
		if(null != request.getParameter("email")){
			String emailId = request.getParameter("email");
			JobSeekerLoginBO jobSeekerLoginBO = new JobSeekerLoginBO();
			jobSeekerLoginBO.setEmailAddress(emailId);
			jobSeekerLoginBO = this.jobSeekerService
					.jobseekerSignin(jobSeekerLoginBO);
			
			JobseekerBO jobseekerBO = new JobseekerBO();
			jobseekerBO.setId(jobSeekerLoginBO.getRegisterId());
			jobseekerActivityBO.setJobseekerLoginBO(jobSeekerLoginBO);
			jobseekerActivityBO.setJobseekerBO(jobseekerBO);
			
				
			
			
		}

		
		if (null != session.getAttribute("registerId")) {
			long RegistrationId = (long) session.getAttribute("registerId");
			JobseekerBO jobseekerBO = new JobseekerBO();
			jobseekerBO.setId(RegistrationId);
			jobseekerActivityBO.setJobseekerBO(jobseekerBO);

		}

		List<JobseekerActivityBO> jobseekerActivityBOList = this.jobSeekerService
				.retriveJobseekerActivity(jobseekerActivityBO);
if(null != jobseekerActivityBOList && jobseekerActivityBOList.size() != 0){
		model.addAttribute("jobseekerActivityBOList", jobseekerActivityBOList);
		model.addAttribute("jobseekerActivityBO", jobseekerActivityBO);
}else{
		
		model.addAttribute("infomessage", "No Jobseeker Activity Records Found");
		return "redirect:/admin_jobseekers.html";
}

		/*if (null != session.getAttribute("adminId")) {
			return "admin_employer_report_view";
		}*/

		return "jobseeker_report_view";

	}
	
	
}
