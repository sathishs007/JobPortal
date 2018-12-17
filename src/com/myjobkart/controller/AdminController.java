package com.myjobkart.controller;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import javax.imageio.ImageIO;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.rowset.serial.SerialException;
import javax.validation.Valid;

import org.apache.commons.io.IOUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;

import sun.security.validator.ValidatorException;

import com.myjobkart.bo.AdminLoginBO;
import com.myjobkart.bo.AppliedJobBO;
import com.myjobkart.bo.BannerPostBO;
import com.myjobkart.bo.ContactBO;
import com.myjobkart.bo.EmployerBO;
import com.myjobkart.bo.EmployerLoginBO;
import com.myjobkart.bo.EmployerProfileBO;
import com.myjobkart.bo.EntityBO;
import com.myjobkart.bo.FeedbackBO;
import com.myjobkart.bo.JobPostBO;
import com.myjobkart.bo.JobSeekerLoginBO;
import com.myjobkart.bo.JobseekerBO;
import com.myjobkart.bo.JobseekerProfileBO;
import com.myjobkart.bo.NewsLetterBO;
import com.myjobkart.bo.PaymentBO;
import com.myjobkart.bo.ProductBO;
import com.myjobkart.bo.ResponseObject;
import com.myjobkart.bo.SaveCandidateBO;
import com.myjobkart.bo.SavejobBO;
import com.myjobkart.bo.ShortListCandidate;
import com.myjobkart.bo.CompanyEntity;
import com.myjobkart.bo.WalkinBO;
import com.myjobkart.exception.JLogger;
import com.myjobkart.exception.MyJobKartException;
import com.myjobkart.service.AdminService;
import com.myjobkart.service.EmployerService;
import com.myjobkart.service.JobSeekerService;
import com.myjobkart.utils.CookiesGenerator;
import com.myjobkart.utils.Dropdownutils;
import com.myjobkart.utils.JobtrolleyValidator;

/**
 * Owner : Scube Technologies Created Date: Nov-20-2014 Created by :
 * sathishkumar.s Description : Admin Controller is a controller this will calls
 * the appropriate services to do the respective operations.. Reviewed by :
 * 
 */

/**
 * @author Administrator
 * 
 */
@Controller
@Scope("session")
@SessionAttributes("/admin")
public class AdminController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5857977996611066292L;

	private static final JLogger LOGGER = JLogger
			.getLogger(AdminController.class);

	@Autowired
	private AdminService<?> adminService;

	@Autowired
	private JobSeekerService<?> jobSeekerService;

	@Autowired
	private EmployerService<?> employerService;

	@Autowired
	ServletContext servletContext;

	List<EmployerBO> employerNotificationList;
	JobseekerProfileBO reteriveprofile;
	List<JobseekerProfileBO> profileList;
	List<JobseekerBO> jobseekerList;
	List<EmployerBO> allEmployerList;
	List<BannerPostBO> allBannerList;
	List<JobseekerBO> notificationsList;

	JobseekerProfileBO jobseekerProfileBO;
	List<JobseekerProfileBO> jobseekerProfileList;
	List<FeedbackBO> feedbackList;
	JobseekerBO updateJobseeker;
	JobseekerBO jobseekerBO;
	List<JobseekerBO> registeredList;
	List<JobPostBO> allJobList;
	JobPostBO updateJobPostBO;
	List<JobPostBO> jobPostList;
	AppliedJobBO appliedJobBO;
	/* List<SaveCandidateBO> saveResumeList; */
	List<AppliedJobBO> appliedJobList;
	List<EmployerBO> allEmployers;
	List<SavejobBO> saveJobList;
	List<SavejobBO> jobseekerSaveJobList;
	EmployerBO updateEmployer;
	List<PaymentBO> employerPaymentList;
	List<AppliedJobBO> appliedJob;
	List<ProductBO> productList;
	ProductBO updateProduct;
	List<SaveCandidateBO> retrieveResumeList = new ArrayList<SaveCandidateBO>();
	List<ShortListCandidate> shortListCandidates = new ArrayList<ShortListCandidate>();

	/**
	 * This method used to perform the login function (admin)
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/admin_sign_in", method = RequestMethod.GET)
	public String login(Model model, HttpServletRequest request) {

		HttpSession session = request.getSession();
		if (null != session.getAttribute("jobseekerId")) {
			model.addAttribute("message", "You have already loggedin!");
			return "redirect:/jobseeker_sign_in.html";
		}
		if (null != session.getAttribute("loginId")) {
			model.addAttribute("message", "You have already loggedin!");
			return "redirect:/employer_sign_in.html";
		}
		if (null != session.getAttribute("adminId")) {
			model.addAttribute("message", "You have already loggedin!");
			return "redirect:/admin_sign_in";
		}
		model.addAttribute("adminLogin", new CookiesGenerator()
				.cookiesVerifications(request, new AdminLoginBO(), "admin"));
		return "admin_sign_in";
	}

	@RequestMapping(value = "/delete_bannar", method = RequestMethod.GET)
	public String deleteBanner(Model model, HttpServletRequest request)
			throws MyJobKartException {
		try {

			final String id = request.getParameter("id");
			final long bannerId = Long.parseLong(id);
			BannerPostBO deleteProfile = new BannerPostBO();
			deleteProfile.setBannerId(bannerId);
			deleteProfile.setIsDelete(false);
			deleteProfile = this.employerService
					.deleteBannerList(deleteProfile);
			if (deleteProfile != null) {
				model.addAttribute("message", deleteProfile.getResponse());
			} else {
				model.addAttribute("message",
						"BannerList is not deleted successfully");
			}

		} catch (final Exception e) {
			e.printStackTrace();
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("Delete banner failed:" + e.getMessage());
			}
			LOGGER.info("Delete banner failed:" + e.getMessage());

		}
		return "redirect:/renewal_bannarList.html";

	}

	/**
	 * This method used to perform the login function (admin)
	 * 
	 * @param model
	 * @return
	 * @throws MyJobKartException
	 */
	@RequestMapping(value = "/admin_sign_in", method = RequestMethod.POST)
	public String login(
			@Valid @ModelAttribute("adminLogin") AdminLoginBO adminLoginBO,
			BindingResult result, HttpServletRequest request, Model model,
			HttpServletResponse httpResponse) throws MyJobKartException {
		AdminController.LOGGER.entry();
		HttpSession session = request.getSession();
		String pwLength = adminLoginBO.getPassword();
		if (null != session.getAttribute("jobseekerId")) {
			model.addAttribute("message", "You have already loggedin!");
			return "jobseeker_sign_in";
		}
		if (null != session.getAttribute("loginId")) {
			model.addAttribute("message", "You have already loggedin!");
			return "employer_sign_in";
		}
		if (null != session.getAttribute("adminId")) {
			model.addAttribute("message", "You have already loggedin!");
			return "admin_sign_in";
		}
		String response = null;

		try {
			boolean rememberMe = adminLoginBO.getRememberMe();
			adminLoginBO = this.adminService.authendicate(adminLoginBO);

			// model.addAttribute("message", "EmailID and password not match");
			if (adminLoginBO.isActive()) {
				session.setAttribute("adminId", adminLoginBO.getId());
				session.setAttribute("userType", adminLoginBO.getUserType());
				// This set of part is used to add the user email and password
				// to cookies.
				Map<String, String> cookiesObject = new HashMap<String, String>();
				cookiesObject.put("email", adminLoginBO.getEmailAddress());
				cookiesObject.put("password", adminLoginBO.getPassword());
				CookiesGenerator cookiesGenerator = new CookiesGenerator();
				cookiesGenerator.addCookies(request, httpResponse,
						cookiesObject, "admin", rememberMe);

				response = "redirect:/admin_home.html";

			} else {

				if (0 != pwLength.length()) {
					if (pwLength.length() >= 4) {
						model.addAttribute("errormessage",
								"Your account does not Exists or Invalid");
						return "admin_sign_in";
					}
				}
			}
		} catch (final MyJobKartException jb) {
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("Admin sign in failed:" + jb.getErrorCode() + jb);
			}
			LOGGER.info("Admin sign in failed:" + jb.getErrorCode() + jb);
		}
		AdminController.LOGGER.exit();
		return response;

	}

	/**
	 * This method used to perform the jobseeker registration
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/add_jobseeker", method = RequestMethod.GET)
	public String addjobSeeker(Model model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		if (null == session.getAttribute("adminId")) {
			return "redirect:/admin_sign_in.html";
		}

		final JobseekerBO jobseekerBO = new JobseekerBO();
		model.addAttribute("jobseeker", jobseekerBO);
		return "add_jobseeker";
	}

	/**
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/add_employer", method = RequestMethod.GET)
	public String employerRegistration(Model model, HttpServletRequest request) {
		companyList(model);
		HttpSession session = request.getSession();
		if (null == session.getAttribute("adminId")) {
			return "redirect:/admin_sign_in.html";
		}

		final EmployerBO employerRegistrationBO = new EmployerBO();
		model.addAttribute("employer", employerRegistrationBO);
		return "add_employer";
	}

	@RequestMapping(value = "employer_details", method = RequestMethod.GET)
	public String reteriveEmployer(Model model, HttpServletRequest request)
			throws SerialException, SQLException {
		AdminController.LOGGER.entry();
		model.addAttribute("employerSearch", new EmployerBO());
		HttpSession session = request.getSession();
		if (null == session.getAttribute("adminId")) {
			return "redirect:/admin_sign_in.html";
		}

		String message = request.getParameter("message");
		model.addAttribute("message", message);
		EmployerBO allEmployer = new EmployerBO();

		try {

			allEmployer = this.employerService.retrieveRegisteredEmployer();
			this.allEmployerList = allEmployer.getRegisteredList();

			model.addAttribute("registeredEmployer", this.allEmployerList);

		} catch (final MyJobKartException jb) {
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("Employer detail failed:" + jb.getErrorCode() + jb);
			}
			LOGGER.info("Employer detail failed:" + jb.getErrorCode() + jb);
		}
		AdminController.LOGGER.exit();
		return "employer_details";
	}

	@RequestMapping(value = "/employerSearch", method = RequestMethod.POST)
	public String employerSearch(
			@Valid @ModelAttribute("employerSearch") EmployerBO search,
			BindingResult result, HttpServletRequest request, Model model) {
		HttpSession session = request.getSession();
		try {
			EmployerBO employer = null;
			List<EmployerBO> searchEmployer = new ArrayList<EmployerBO>();
			if (null != this.allEmployerList) {
				for (final EmployerBO profileBO : this.allEmployerList) {
					if (profileBO.getFirstName().toLowerCase()
							.contains(search.getSearchElement().toLowerCase())) {
						employer = profileBO;
						searchEmployer.add(employer);
					}
				}
				if (searchEmployer.size() == 0) {
					model.addAttribute("errormessage", "No record found..");
				}
				model.addAttribute("registeredEmployer", searchEmployer);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "employer_details";
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "active_employer_details", method = RequestMethod.GET)
	public String activeEmployer(Model model, HttpServletRequest request)
			throws SerialException, SQLException {
		AdminController.LOGGER.entry();

		HttpSession session = request.getSession();
		if (null == session.getAttribute("adminId")) {
			return "redirect:/admin_sign_in.html";
		}

		EmployerBO allEmployer = new EmployerBO();
		final List<EmployerBO> activeEmployerList = new ArrayList<EmployerBO>();
		int page = 1;
		final String paging = request.getParameter("page");

		model.addAttribute("employeeNameSearch", new EmployerBO());
		try {

			if (null != paging) {

				page = Integer.parseInt(paging);
				final List<EmployerBO> activeEmployer = (List<EmployerBO>) session
						.getAttribute("activeEmployerList");

				final ResponseObject<EmployerBO> reponseObject = this
						.pagination(page, activeEmployer);

				model.addAttribute("registeredEmployers", reponseObject);

			} else {

				allEmployer = this.employerService.retrieveRegisteredEmployer();
				this.allEmployerList = allEmployer.getRegisteredList();
				if (null != this.allEmployerList
						&& this.allEmployerList.size() > 0) {

					for (final EmployerBO employerBO : this.allEmployerList) {
						if (employerBO.getIsActive()) {
							EmployerBO employer = new EmployerBO();
							employer = employerBO;
							activeEmployerList.add(employer);

						}
					}
					session.setAttribute("activeEmployerList",
							activeEmployerList);
					final ResponseObject<EmployerBO> responseObject = this
							.pagination(1, activeEmployerList);
					model.addAttribute("registeredEmployers", responseObject);
					model.addAttribute("activeEmpDetail", "white");
					model.addAttribute("employeeNameSearch", new EmployerBO());
				}
				if (null != activeEmployerList
						|| 0 == activeEmployerList.size()) {
					model.addAttribute("Infomessage", "Please create employer");
				}
			}
		} catch (final MyJobKartException jb) {
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("Active employer details failed:"
						+ jb.getErrorCode() + jb);
			}
			LOGGER.info("Active employer details failed:" + jb.getErrorCode()
					+ jb);
		}
		AdminController.LOGGER.exit();
		return "employer_details";

	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "de_active_employer_details", method = RequestMethod.GET)
	public String deActiveEmployer(Model model, HttpServletRequest request)
			throws SerialException, SQLException {
		AdminController.LOGGER.entry();
		HttpSession session = request.getSession();
		if (null == session.getAttribute("adminId")) {
			return "redirect:/admin_sign_in.html";
		}

		EmployerBO allEmployer = new EmployerBO();
		final List<EmployerBO> deActiveEmployerList = new ArrayList<EmployerBO>();
		int page = 1;
		final String paging = request.getParameter("page");

		model.addAttribute("employeeNameSearch", new EmployerBO());
		try {

			if (null != paging) {
				final List<EmployerBO> deActiveEmployer = (List<EmployerBO>) session
						.getAttribute("deActiveEmployerList");
				page = Integer.parseInt(paging);

				final ResponseObject<EmployerBO> reponseObject = this
						.pagination(page, deActiveEmployer);

				model.addAttribute("registeredEmployer1", reponseObject);

			} else {

				allEmployer = this.employerService.retrieveRegisteredEmployer();
				this.allEmployerList = allEmployer.getRegisteredList();
				if (null != this.allEmployerList
						&& this.allEmployerList.size() > 0) {

					for (final EmployerBO employerBO : this.allEmployerList) {

						if (!employerBO.getIsActive()) {
							EmployerBO employer = new EmployerBO();
							employer = employerBO;
							deActiveEmployerList.add(employer);

						}
					}
					session.setAttribute("deActiveEmployerList",
							deActiveEmployerList);
					final ResponseObject<EmployerBO> responseObject = this
							.pagination(1, deActiveEmployerList);
					model.addAttribute("registeredEmployer1", responseObject);
					model.addAttribute("deactiveEmpDetail", "white");
					model.addAttribute("employeeNameSearch", new EmployerBO());
				} else {
					model.addAttribute("Infomessage", "Record not found");
				}
			}
		} catch (final MyJobKartException jb) {
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("Deactive employer details failed:"
						+ jb.getErrorCode() + jb);
			}
			LOGGER.info("Deactive employer details failed:" + jb.getErrorCode()
					+ jb);
		}
		AdminController.LOGGER.exit();
		return "employer_details";

	}

	public void industryList(Model model) {
		LOGGER.entry();
		List<String> industryList = new ArrayList<String>();
		List<EntityBO> allIndustryList = new ArrayList<EntityBO>();
		try {
			allIndustryList = adminService.retrieveIndustryList();
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

	public void companyList(Model model) {
		LOGGER.entry();
		ArrayList<String> companyList = new ArrayList<String>();
		List<CompanyEntity> allCompanyList = new ArrayList<CompanyEntity>();
		try {
			allCompanyList = employerService.retrieveCompanyList();
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

	@RequestMapping(value = "/update_employer", method = RequestMethod.GET)
	public String updateRegistration(Model model, HttpServletRequest request) {
		AdminController.LOGGER.entry();
		companyList(model);
		try {

			HttpSession session = request.getSession();
			if (null == session.getAttribute("adminId")) {
				return "redirect:/admin_sign_in.html";
			}

			final String id = request.getParameter("id");

			final long jobseekerId = Long.parseLong(id);
			for (final EmployerBO employerBO : this.allEmployerList) {
				if (employerBO.getId() == jobseekerId) {
					this.updateEmployer = employerBO;
					model.addAttribute("updateProfile", employerBO);
				}
			}
		} catch (final NullPointerException ne) {
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("update employer failed:" + ne.getMessage() + ne);
			}
			LOGGER.info("update employer  failed:" + ne.getMessage() + ne);
		}
		AdminController.LOGGER.exit();
		return "update_employer";
	}

	@RequestMapping(value = "/employer_view_details", method = RequestMethod.GET)
	public String viewRegistration(Model model, HttpServletRequest request) {
		AdminController.LOGGER.entry();
		try {
			HttpSession session = request.getSession();
			if (null == session.getAttribute("adminId")) {
				return "redirect:/admin_sign_in.html";
			}

			final String id = request.getParameter("id");

			final long jobseekerId = Long.parseLong(id);
			for (final EmployerBO employerBO : this.allEmployerList) {
				if (employerBO.getId() == jobseekerId) {
					this.updateEmployer = employerBO;
					if (employerBO.getTermsConditionsAgreed()) {
						employerBO.setTermsConditions("Agree");
					} else {
						employerBO.setTermsConditions("Not-Agree");
					}
					model.addAttribute("updateProfile", employerBO);
				}
			}
		} catch (final NullPointerException ne) {
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("employer view details failed:" + ne.getMessage()
						+ ne);
			}
			LOGGER.info("employer view details  failed:" + ne.getMessage() + ne);
		}
		AdminController.LOGGER.exit();
		return "employer_view_details";
	}

	@RequestMapping(value = "/delete_employer", method = RequestMethod.GET)
	public String deleteEmployer(Model model, HttpServletRequest request)
			throws MyJobKartException {
		AdminController.LOGGER.entry();
		try {
			final String id = request.getParameter("id");
			final long deletedId = Long.parseLong(id);

			final HttpSession session = request.getSession();
			final long adminid = (Long) session.getAttribute("adminId");

			EmployerBO deleteProfile = new EmployerBO();
			deleteProfile.setId(deletedId);
			deleteProfile.setDeletedBy(adminid);
			deleteProfile.setModifiedBy(adminid);
			deleteProfile.setIsDeleted(false);
			final Date deletedDate = new Date();
			deleteProfile.setDeletedDate(deletedDate);
			deleteProfile = this.employerService.deleteEmployer(deleteProfile);
			if (null != deleteProfile.getResponse()) {
				model.addAttribute("message", deleteProfile.getResponse());

			} else {
				model.addAttribute("message",
						"data has been updated failed,please contact Administrator.");

			}
		} catch (final MyJobKartException jb) {
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("delete employer failed:" + jb.getErrorCode() + jb);
			}
			LOGGER.info("delete employer failed:" + jb.getErrorCode() + jb);
		}
		AdminController.LOGGER.exit();
		return "redirect:/employer_details.html";
	}

	/**
	 * Employer Registration method is used for registering the employer in the
	 * system.
	 * 
	 * @param registrationBO
	 * @return registrationBO
	 * @throws MyJobKartException
	 */

	@RequestMapping(value = "/update_employer", method = RequestMethod.POST)
	public String updateEmployerRegistration(
			@Valid @ModelAttribute("updateProfile") EmployerBO registration,
			BindingResult result, Model model, HttpServletRequest request)
			throws MyJobKartException {
		AdminController.LOGGER.entry();
		companyList(model);
		try {
			HttpSession session = request.getSession();
			if (null == session.getAttribute("adminId")) {
				return "redirect:/admin_sign_in.html";
			}

			if (!registration.getEmailAddress().equalsIgnoreCase(
					registration.getConfirmEmailAddress())) {
				result.rejectValue("confirmEmailAddress", "Validate.Email");
				return "update_employer";
			}

			if (!registration.getPassword().equalsIgnoreCase(
					registration.getConfirmPassword())) {
				result.rejectValue("confirmPassword", "Validate.Password");
				return "update_employer";
			}
			long companyId = 0;
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

			if (null != registration.getFirstName()) {
				final String status = JobtrolleyValidator.validate(registration
						.getFirstName());
				if (null != status) {
					model.addAttribute("message", status);
					return "update_employer";
				}

			}

			final File rootDir = new File(
					this.servletContext
							.getRealPath("/WEB-INF/images/companylogo.jpg"));
			final BufferedImage image = ImageIO.read(rootDir);
			final ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ImageIO.write(image, "jpg", baos);
			final byte[] res = baos.toByteArray();

			registration.setCompanyLogo(res);

			if (null != registration.getLastName()) {
				final String status = JobtrolleyValidator.validate(registration
						.getLastName());
				if (null != status) {
					model.addAttribute("message", status);
					return "update_employer";
				}

			}

			if (result.hasErrors()) {
				return "update_employer";
			}

			registration.setId(this.updateEmployer.getId());

			registration.setCreatedBy(this.updateEmployer.getCreatedBy());
			registration.setCreated(this.updateEmployer.getCreated());
			registration.setVersion(this.updateEmployer.getVersion());
			registration.setModifiedBy(this.updateEmployer.getModifiedBy());
			registration.setIsActive(this.updateEmployer.getIsActive());
			registration.setIsDeleted(this.updateEmployer.getIsDeleted());
			registration.setTermsConditionsAgreed(true);
			registration = this.employerService.updateEmployer(registration);
			// model.addAttribute("message", "Your Account Successfuly Create");

			if (null != registration.getErrorCode()) {
				model.addAttribute("message", registration.getErrorMessage());

				return "update_employer";
			} else {
				model.addAttribute("message", registration.getResponse());
				final EmployerBO employerBO = this.empclear();
				model.addAttribute("employer", employerBO);
				return "redirect:/employer_details.html";
			}
		} catch (final Exception e) {
			e.printStackTrace();
			// log.debug(registration.getErrorMessage() + e);
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("update employer failed:" + e.getMessage());
			}
			LOGGER.info("update employer failed:" + e.getMessage());
		}
		AdminController.LOGGER.exit();
		return null;
	}

	@RequestMapping(value = "/admin_home", method = RequestMethod.GET)
	public String reteriveJobseeker(Model model, HttpServletRequest request) {
		AdminController.LOGGER.entry();
		try {

			String message = request.getParameter("ErrorMessage");
			model.addAttribute("message", message);

			HttpSession session = request.getSession();
			if (null == session.getAttribute("adminId")) {
				return "redirect:/admin_sign_in.html";
			}
			SaveCandidateBO saveCandidateBO = new SaveCandidateBO();
			JobPostBO jobPostBO = new JobPostBO();
			List<JobPostBO> jobPostBOList;
			List<SaveCandidateBO> saveResumeList;
			final EmployerLoginBO employerLoginBO = new EmployerLoginBO();
			long jobpostcount = 0;
			long appliedjobcount = 0;
			long activeJobCount = 0;
			long inActiveJobCount = 0;
			long savejobcount = 0;
			long shortListCount = 0;
			long unShortListCount = 0;
			long selectedResumeCount = 0;
			long unSelectedResumeCount = 0;
			long appliedcount = 0;
			long deappliedcount = 0;
			long totalappliedcount = 0;
			long profilecount = 0;
			long jobseekerActiveCount = 0;
			long jobseekerInActiveCount = 0;
			long employerActiveCount = 0;
			long employerInActiveCount = 0;
			final List<AppliedJobBO> appliedJobs = new ArrayList<AppliedJobBO>();
			final List<JobseekerProfileBO> profileLists = new ArrayList<JobseekerProfileBO>();
			jobPostBO = this.employerService.retrieveJobPost(jobPostBO);
			jobPostBOList = jobPostBO.getJobPostList();
			if (null != jobPostBOList && jobPostBOList.size() != 0) {
				jobpostcount = jobPostBOList.size();
				model.addAttribute("jobpostcount", jobpostcount);
				for (final JobPostBO jobPost : jobPostBOList) {
					if (jobPost.getIsActive()) {
						activeJobCount = activeJobCount + 1;
					} else if (!jobPost.getIsActive()) {
						inActiveJobCount = inActiveJobCount + 1;
					}
				}
				model.addAttribute("ActiveJobPost", activeJobCount);
				model.addAttribute("InActiveJobPost", inActiveJobCount);
			} else {
				model.addAttribute("ActiveJobPost", activeJobCount);
				model.addAttribute("InActiveJobPost", inActiveJobCount);
				model.addAttribute("jobpostcount", jobpostcount);
			}
			this.appliedJobBO = new AppliedJobBO();
			this.appliedJobBO.setEmployerLogin(employerLoginBO);
			this.appliedJobBO = this.employerService
					.reteriveAppliedJobs(this.appliedJobBO);
			this.appliedJobList = this.appliedJobBO.getJobPostList();
			if (null != this.appliedJobList && this.appliedJobList.size() != 0) {
				appliedjobcount = this.appliedJobList.size();
				model.addAttribute("appliedjobcount", appliedjobcount);
				for (final AppliedJobBO appliedJobBO : this.appliedJobList) {
					if (appliedJobBO.getIsShortlisted()) {
						shortListCount = shortListCount + 1;
					} else if (!appliedJobBO.getIsShortlisted()) {
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

			saveCandidateBO.setEmployerLoginBO(employerLoginBO);
			saveCandidateBO = this.employerService
					.reteriveCandidate(saveCandidateBO);
			saveResumeList = saveCandidateBO.getJobProfileList();
			if (null != saveResumeList && saveResumeList.size() != 0) {
				savejobcount = saveResumeList.size();
				model.addAttribute("savejobcount", savejobcount);
				for (final SaveCandidateBO candidateBO : saveResumeList) {
					if (candidateBO.getIsShortListed()) {
						selectedResumeCount = selectedResumeCount + 1;
					} else if (!candidateBO.getIsShortListed()) {
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

			this.appliedJobBO = this.jobSeekerService
					.reteriveAppliedJobs(this.appliedJobBO);
			this.appliedJobList = this.appliedJobBO.getJobPostList();
			if (null != this.appliedJobList && this.appliedJobList.size() != 0) {
				totalappliedcount = this.appliedJobList.size();
				model.addAttribute("totalappliedcount", totalappliedcount);
				for (final AppliedJobBO jobBO : this.appliedJobList) {
					this.appliedJobBO = new AppliedJobBO();
					if (jobBO.getIsDeleted()) {
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
				model.addAttribute("deactiveappliedcount", deappliedcount);
				model.addAttribute("activeappliedcount", appliedcount);
				model.addAttribute("totalappliedcount", totalappliedcount);
			}
			SavejobBO savejob = new SavejobBO();
			savejob = this.jobSeekerService.reteriveSavedJob(savejob);
			this.saveJobList = savejob.getJobPostList();
			if (null != this.saveJobList && this.saveJobList.size() != 0) {

				for (final SavejobBO savejobBO : this.saveJobList) {

					this.saveJobList.size();
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
				model.addAttribute("totalsavecount", savejobcount);
				model.addAttribute("totalactivesavecount", savejobcount);
				model.addAttribute("totaldeactivesavecount", savejobcount);
				model.addAttribute("totalapplysavecount", savejobcount);
			}

			this.reteriveprofile = new JobseekerProfileBO();
			this.reteriveprofile = this.jobSeekerService
					.retriveJobseeker(this.reteriveprofile);
			if (null != this.reteriveprofile.getJobseekerProfileList()
					&& this.reteriveprofile.getJobseekerProfileList().size() != 0) {
				final long total = this.reteriveprofile
						.getJobseekerProfileList().size();
				for (final JobseekerProfileBO bo : this.reteriveprofile
						.getJobseekerProfileList()) {
					this.reteriveprofile = new JobseekerProfileBO();
					if (bo.getIsDelete()) {
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

			this.jobseekerBO = new JobseekerBO();
			this.jobseekerBO = this.jobSeekerService
					.retriveRegisteredJobseeker();
			this.registeredList = this.jobseekerBO.getRegisteredList();
			final long registeredCount = this.registeredList.size();
			if (this.registeredList.size() != 0) {

				for (final JobseekerBO jobseekerBO : this.registeredList) {
					if (jobseekerBO.getIsActive()) {
						jobseekerActiveCount = jobseekerActiveCount + 1;
					} else if (!jobseekerBO.getIsActive()) {
						jobseekerInActiveCount = jobseekerInActiveCount + 1;
					}
				}

				model.addAttribute("RegisteredCount", registeredCount);
				model.addAttribute("ActiveCount", jobseekerActiveCount);
				model.addAttribute("InactiveCount", jobseekerInActiveCount);

			} else {
				model.addAttribute("RegisteredCount", registeredCount);
				model.addAttribute("ActiveCount", jobseekerActiveCount);
				model.addAttribute("InactiveCount", jobseekerInActiveCount);
			}
			final EmployerBO allEmployer = this.employerService
					.retrieveRegisteredEmployer();
			this.allEmployerList = allEmployer.getRegisteredList();
			final long employerRegisteredCount = this.allEmployerList.size();
			if (null != this.allEmployerList
					&& this.allEmployerList.size() != 0) {
				for (final EmployerBO employerBO : this.allEmployerList) {
					if (employerBO.getIsActive()) {
						employerActiveCount = employerActiveCount + 1;
					} else if (!employerBO.getIsActive()) {
						employerInActiveCount = employerInActiveCount + 1;
					}
				}

				model.addAttribute("EmployerRegisteredCount",
						employerRegisteredCount);
				model.addAttribute("EmployerActiveCount", employerActiveCount);
				model.addAttribute("EmployerInactiveCount",
						employerInActiveCount);
			} else {
				model.addAttribute("EmployerRegisteredCount",
						employerRegisteredCount);
				model.addAttribute("EmployerActiveCount", employerActiveCount);
				model.addAttribute("EmployerInactiveCount",
						employerInActiveCount);
			}

		} catch (final Exception jb) {
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("admin home failed:" + jb.getMessage());
			}
			LOGGER.info("admin home failed:" + jb.getMessage());

		}
		return "admin_home";
	}

	@SuppressWarnings("rawtypes")
	private ResponseObject pagination(int page, List<EmployerBO> dataLsit) {
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
		final ResponseObject<EmployerBO> ro = new ResponseObject<EmployerBO>();
		final List<EmployerBO> list = dataLsit;
		if (null != list) {
			noOfRecords = list.size();
		}
		noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
		ro.setCurrentPage(page);
		ro.setRecordsPerPage(recordsPerPage);
		ro.setTotalPages(noOfPages);
		int count = 0;

		final List<EmployerBO> pageList = new ArrayList<EmployerBO>();
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

	/**
	 * This method used to perform the jobseeker registration
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
	@RequestMapping(value = "/add_jobseeker", method = RequestMethod.POST)
	public String addjobSeeker(
			@Valid @ModelAttribute("jobseeker") JobseekerBO jobseekerBO,
			BindingResult result, Model model,
			@RequestParam("profileImages") MultipartFile profileImage,
			HttpServletRequest request) throws MyJobKartException,
			SerialException, SQLException, IOException, ValidatorException {
		AdminController.LOGGER.entry();
		try {
			HttpSession session = request.getSession();
			if (null == session.getAttribute("adminId")) {
				return "redirect:/admin_sign_in.html";
			}
			if (!profileImage.isEmpty()) {
				jobseekerBO.setProfileImage(profileImage.getBytes());
			} else {
				final File rootDir = new File(
						this.servletContext
								.getRealPath("/WEB-INF/images/jobseeker.jpg"));
				final BufferedImage image = ImageIO.read(rootDir);
				final ByteArrayOutputStream baos = new ByteArrayOutputStream();
				ImageIO.write(image, "png", baos);
				final byte[] res = baos.toByteArray();
				jobseekerBO.setProfileImage(res);
			}

			if (!profileImage.isEmpty()) {
				final String str = this.imagevalied(profileImage);
				if (null != str) {
					model.addAttribute("message",
							"Profile Image Only jpeg,jpg,png formats are allowed  ");
					return "add_jobseeker";
				}
			}
			if (!jobseekerBO.getEmailAddress().equalsIgnoreCase(
					jobseekerBO.getConfirmEmailAddress())) {
				result.rejectValue("confirmEmailAddress", "Validate.Email");
				return "add_jobseeker";
			}

			if (!jobseekerBO.getPassword().equalsIgnoreCase(
					jobseekerBO.getConfirmPassword())) {
				result.rejectValue("confirmPassword", "Validate.Password");
				return "add_jobseeker";
			}
			if (null != jobseekerBO.getFirstName()) {
				final String status = JobtrolleyValidator.validate(jobseekerBO
						.getFirstName());
				if (null != status) {
					model.addAttribute("message", status);
					return "add_jobseeker";
				}

			}
			if (null != jobseekerBO.getLastName()) {
				final String status = JobtrolleyValidator.validate(jobseekerBO
						.getLastName());
				if (null != status) {
					model.addAttribute("message", status);
					return "add_jobseeker";
				}

			}

			if (result.hasErrors()) {
				return "add_jobseeker";
			}
			if (this.jobSeekerService
					.findByEmail(jobseekerBO.getEmailAddress())) {
				model.addAttribute("message", "Email already exists");
				return "add_jobseeker";
			}

			jobseekerBO = this.jobSeekerService.create(jobseekerBO);
			if (null != jobseekerBO.getErrorCode()) {
				model.addAttribute("message", jobseekerBO.getErrorMessage());
				return "add_jobseeker";
			} else {
				model.addAttribute(
						"message",
						"Jobseeker Account has been Created Successfully. Please Check Your Mail for Activation");
				final JobseekerBO jobseeker = this.clear();

				model.addAttribute("jobseeker", jobseeker);
				return "add_jobseeker";
			}

		} catch (final MyJobKartException jb) {
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("Add jobseeker failed:" + jb.getErrorCode() + jb);
			}
			LOGGER.info("Add jobseeker failed:" + jb.getErrorCode() + jb);
		}
		AdminController.LOGGER.exit();
		return null;
	}

	@RequestMapping(value = "/admin_jobseekers", method = RequestMethod.GET)
	public String retrieveAdminJobseekers(Model model,
			HttpServletRequest request) {
		AdminController.LOGGER.entry();
		
		model.addAttribute("jobseekerSearch", new JobseekerBO());
		final List<JobseekerBO> profileLists = new ArrayList<JobseekerBO>();
		JobseekerBO reteriveprofiles;

		HttpSession session = request.getSession();
		if (null == session.getAttribute("adminId")) {
			return "redirect:/admin_sign_in.html";
		}

		String message = request.getParameter("message");
		model.addAttribute("message", message);
		if (null != request.getParameter("infomessage")) {
			String infomessage = request.getParameter("infomessage");
			model.addAttribute("infomessage", infomessage);
		}

		try {

			this.jobseekerBO = new JobseekerBO();
			this.jobseekerBO = this.jobSeekerService
					.retriveRegisteredJobseeker();
			this.registeredList = this.jobseekerBO.getRegisteredList();
			if (0 != this.registeredList.size()) {

				for (JobseekerBO bo : registeredList) {
					this.reteriveprofile = new JobseekerProfileBO();

					if (bo.getIsActive()) {
						bo.setStatus("Active");

					} else {
						bo.setStatus("De-Active");
					}

					reteriveprofiles = bo;
					profileLists.add(reteriveprofiles);

				}

				this.jobseekerList = profileLists;

				model.addAttribute("registeredList", profileLists);
				/* model.addAttribute("activeAllColor", "white"); */
			} else {
				model.addAttribute("message",
						"Retrive Failer!..Please Contact Administrator.. ");
			}
		} catch (final Exception e) {
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("Admin jobseekers failed:" + e.getMessage());
			}
			LOGGER.info("Admin jobseekers failed:" + e.getMessage());

		}

		AdminController.LOGGER.exit();
		return "admin_jobseekers";
	}

	@RequestMapping(value = "/jobseekerSearch", method = RequestMethod.POST)
	public String jobseekerSearch(
			@Valid @ModelAttribute("jobseekerSearch") JobseekerBO search,
			BindingResult result, HttpServletRequest request, Model model) {
		HttpSession session = request.getSession();
		try {
			JobseekerBO jobseeker = null;
			List<JobseekerBO> searchJobseeker = new ArrayList<JobseekerBO>();
			if (null != jobseekerList) {
				for (final JobseekerBO profileBO : jobseekerList) {
					if (profileBO.getFirstName().toLowerCase()
							.contains(search.getSearchElement().toLowerCase())) {
						jobseeker = profileBO;
						searchJobseeker.add(jobseeker);
					}
				}
				if (searchJobseeker.size() == 0) {
					model.addAttribute("errormessage", "No record found..");
				}
				model.addAttribute("registeredList", searchJobseeker);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "admin_jobseekers";
	}

	@RequestMapping(value = "/active_admin_jobseekers", method = RequestMethod.GET)
	public String retrieveActiveAdminJobseekers(Model model,
			HttpServletRequest request) {
		AdminController.LOGGER.entry();
		HttpSession session = request.getSession();
		if (null == session.getAttribute("adminId")) {
			return "redirect:/admin_sign_in.html";
		}

		final List<JobseekerBO> activeList = new ArrayList<JobseekerBO>();
		int page = 1;
		final String paging = request.getParameter("page");

		model.addAttribute("jobSeekerNameSearch", new JobseekerBO());
		try {
			if (null != paging) {

				page = Integer.parseInt(paging);
				final List<JobseekerBO> activeLists = (List<JobseekerBO>) session
						.getAttribute("activeList");
				final ResponseObject<JobseekerBO> reponseObject = this
						.jobseekerpagination(page, activeLists);

				model.addAttribute("registeredLists", reponseObject);
			} else {
				if (0 != this.registeredList.size()) {
					for (final JobseekerBO jobseekerBO : this.registeredList) {
						if (jobseekerBO.getIsActive()) {
							JobseekerBO jobseeker = new JobseekerBO();
							jobseeker = jobseekerBO;
							activeList.add(jobseeker);
						}
					}
					session.setAttribute("activeList", activeList);
					final ResponseObject<JobseekerBO> responseObject = this
							.jobseekerpagination(1, activeList);

					model.addAttribute("registeredLists", responseObject);
					model.addAttribute("jobSeekerNameSearch", new JobseekerBO());
					model.addAttribute("activeColor", "white");
				} else {
					model.addAttribute("message",
							"Retrive Failer!..Please Contact Administrator.. ");
					return "admin_jobseekers";
				}
			}
		} catch (final Exception e) {
			// e.printStackTrace();
			model.addAttribute("message",
					"Retrive Failer!..Please Contact Administrator.. ");
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("Active Admin jobseekers failed:" + e.getMessage());
			}
			LOGGER.info("Active Admin jobseekers failed:" + e.getMessage());
		}

		AdminController.LOGGER.exit();
		return "admin_jobseekers";
	}

	@RequestMapping(value = "/inactive_admin_jobseekers", method = RequestMethod.GET)
	public String retrieveInActiveAdminJobseekers(Model model,
			HttpServletRequest request) {
		AdminController.LOGGER.entry();
		HttpSession session = request.getSession();
		if (null == session.getAttribute("adminId")) {
			return "redirect:/admin_sign_in.html";
		}
		final List<JobseekerBO> inActiveList = new ArrayList<JobseekerBO>();
		int page = 1;
		final String paging = request.getParameter("page");

		model.addAttribute("jobSeekerNameSearch", new JobseekerBO());
		try {
			if (null != paging) {

				page = Integer.parseInt(paging);
				final List<JobseekerBO> inActiveLists = (List<JobseekerBO>) session
						.getAttribute("inActiveList");
				final ResponseObject<JobseekerBO> reponseObject = this
						.jobseekerpagination(page, inActiveLists);

				model.addAttribute("registeredList1", reponseObject);

			} else {
				if (0 != this.registeredList.size()) {
					for (final JobseekerBO jobseekerBO : this.registeredList) {
						if (!jobseekerBO.getIsActive()) {
							JobseekerBO jobseeker = new JobseekerBO();
							jobseeker = jobseekerBO;
							inActiveList.add(jobseeker);
						}
					}

					session.setAttribute("inActiveList", inActiveList);
					final ResponseObject<JobseekerBO> responseObject = this
							.jobseekerpagination(1, inActiveList);
					model.addAttribute("registeredList1", responseObject);
					model.addAttribute("jobSeekerNameSearch", new JobseekerBO());
					model.addAttribute("inactiveColor", "white");
				} else {
					model.addAttribute("message",
							"Retrive Failer!..Please Contact Administrator.. ");
				}
			}
		} catch (final Exception e) {
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("Inactive Admin jobseekers failed:"
						+ e.getMessage());
			}
			LOGGER.info("Inactive Admin jobseekers failed:" + e.getMessage());

		}

		AdminController.LOGGER.exit();
		return "admin_jobseekers";
	}

	@SuppressWarnings("rawtypes")
	private ResponseObject jobseekerprofilepagination(int page,
			List<JobseekerProfileBO> dataLsit) {
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
		final ResponseObject<JobseekerProfileBO> ro = new ResponseObject<JobseekerProfileBO>();
		final List<JobseekerProfileBO> list = dataLsit;
		if (null != list) {
			noOfRecords = list.size();
		}
		noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
		ro.setCurrentPage(page);
		ro.setRecordsPerPage(recordsPerPage);
		ro.setTotalPages(noOfPages);
		int count = 0;
		final List<JobseekerProfileBO> pageList = new ArrayList<JobseekerProfileBO>();
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

	@SuppressWarnings("rawtypes")
	private ResponseObject jobseekerpagination(int page,
			List<JobseekerBO> dataLsit) {
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

	@RequestMapping(value = "/admin_update_jobseeker", method = RequestMethod.GET)
	public String updateRegisteredJobseeker(Model model,
			HttpServletRequest request) {
		AdminController.LOGGER.entry();
		HttpSession session = request.getSession();
		if (null == session.getAttribute("adminId")) {
			return "redirect:/admin_sign_in.html";
		}

		try {
			final String id = request.getParameter("id");
			final long jobseekerId = Long.parseLong(id);
			for (final JobseekerBO jobseekerBO : this.registeredList) {
				if (jobseekerBO.getId() == jobseekerId) {
					this.updateJobseeker = jobseekerBO;
					model.addAttribute("updateProfile", this.updateJobseeker);
				}
			}
		} catch (final NullPointerException ne) {
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("Admin update jobseekers failed:"
						+ ne.getMessage() + ne);
			}
			LOGGER.info("Admin update jobseekers failed:" + ne.getMessage()
					+ ne);
		}
		AdminController.LOGGER.exit();
		return "admin_update_jobseeker";
	}

	@RequestMapping(value = "/admin_jobseeker_details", method = RequestMethod.GET)
	public String jobseekerDetails(Model model, HttpServletRequest request) {
		AdminController.LOGGER.entry();
		try {
			HttpSession session = request.getSession();
			if (null == session.getAttribute("adminId")) {
				return "redirect:/admin_sign_in.html";
			}

			final String id = request.getParameter("id");
			final long jobseekerId = Long.parseLong(id);
			for (final JobseekerBO jobseekerBO : this.registeredList) {
				if (jobseekerBO.getId() == jobseekerId) {
					this.updateJobseeker = jobseekerBO;
					if (jobseekerBO.getTermsConditionsAgreed()) {
						this.updateJobseeker.setTermsConditions("Agreed");
					} else {
						this.updateJobseeker.setTermsConditions("Not-Agreed");
					}
					model.addAttribute("viewJobseeker", this.updateJobseeker);
				}
			}
		} catch (final NullPointerException ne) {
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("Admin  jobseekers details failed:"
						+ ne.getMessage() + ne);
			}
			LOGGER.info("Admin  jobseekers details failed:" + ne.getMessage()
					+ ne);
		}
		AdminController.LOGGER.exit();
		return "admin_jobseeker_details";
	}

	@RequestMapping(value = "/jobseeker_image", method = RequestMethod.GET)
	public void showJobseekerLoginImage(@RequestParam("id") Integer itemId,
			HttpServletResponse response, HttpServletRequest request)
			throws ServletException, IOException, SQLException {

		final String id = request.getParameter("id");
		final long imgid = Long.parseLong(id);
		for (final JobseekerBO jobseekerBO : this.registeredList) {

			if (jobseekerBO.getId() == imgid) {
				response.setContentType("image/jpeg, image/jpg, image/png, image/gif");
				response.getOutputStream().write(
						jobseekerBO.getProfileImage().getBytes(1,
								(int) jobseekerBO.getProfileImage().length()));
				response.getOutputStream().close();
			}
		}
	}

	/**
	 * This method used to perform the jobseeker registration
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
	 */
	@RequestMapping(value = "/admin_update_jobseeker", method = RequestMethod.POST)
	public String updateJobseeker(
			@Valid @ModelAttribute("updateProfile") JobseekerBO jobseekerBO,
			BindingResult result, Model model,
			@RequestParam("profileImages") MultipartFile profileImage,
			HttpServletRequest request) throws MyJobKartException,
			SerialException, SQLException, IOException {

		AdminController.LOGGER.entry();
		HttpSession session = request.getSession();
		if (null == session.getAttribute("adminId")) {
			return "redirect:/admin_sign_in.html";
		}

		try {

			if (result.hasErrors()) {
				return "admin_update_jobseeker";
			}

			if (!profileImage.isEmpty()) {
				jobseekerBO.setProfileImage(profileImage.getBytes());
			} else {
				if (this.updateJobseeker.getProfileImage().length() != 0) {
					jobseekerBO.setProfileImage(this.updateJobseeker
							.getProfileImage().getBytes(
									1,
									(int) this.updateJobseeker
											.getProfileImage().length()));
				} else {
					final File rootDir = new File(
							this.servletContext
									.getRealPath("/WEB-INF/images/male.png"));
					final BufferedImage image = ImageIO.read(rootDir);
					final ByteArrayOutputStream baos = new ByteArrayOutputStream();
					ImageIO.write(image, "png", baos);
					final byte[] res = baos.toByteArray();
					jobseekerBO.setProfileImage(res);
				}
			}
			if (!profileImage.isEmpty()) {
				final String str = this.imagevalied(profileImage);
				if (null != str) {

					model.addAttribute("message",
							"ProfileImage Only jpeg,jpg,png formats are allowed  ");
					return "admin_update_jobseeker";

				}
			}

			if (!jobseekerBO.getEmailAddress().equalsIgnoreCase(
					jobseekerBO.getConfirmEmailAddress())) {
				result.rejectValue("confirmEmailAddress", "Validate.Email");

				return "admin_update_jobseeker";
			}

			if (!jobseekerBO.getPassword().equalsIgnoreCase(
					jobseekerBO.getConfirmPassword())) {
				result.rejectValue("confirmPassword", "Validate.Password");
				return "admin_update_jobseeker";
			}
			if (null != jobseekerBO.getFirstName()) {
				final String status = JobtrolleyValidator.validate(jobseekerBO
						.getFirstName());
				if (null != status) {
					model.addAttribute("message", status);
					return "admin_update_jobseeker";
				}

			}
			if (null != jobseekerBO.getLastName()) {
				final String status = JobtrolleyValidator.validate(jobseekerBO
						.getLastName());
				if (null != status) {
					model.addAttribute("message", status);
					return "admin_update_jobseeker";
				}

			}

			jobseekerBO.setId(this.updateJobseeker.getId());
			jobseekerBO.setCreatedBy(this.updateJobseeker.getCreatedBy());
			jobseekerBO.setCreated(this.updateJobseeker.getCreated());
			jobseekerBO.setVersion(this.updateJobseeker.getVersion());
			jobseekerBO.setModifiedBy(this.updateJobseeker.getModifiedBy());
			jobseekerBO.setIsDeleted(this.updateJobseeker.getIsDeleted());
			jobseekerBO.setIsActive(this.updateJobseeker.getIsActive());

			jobseekerBO = this.jobSeekerService.updateJobseeker(jobseekerBO);
			// model.addAttribute("message", "Your Account Successfuly Create");

			if (null != jobseekerBO.getErrorCode()) {
				model.addAttribute("message", jobseekerBO.getErrorMessage());

				return "admin_update_jobseeker";
			} else {
				model.addAttribute("message", jobseekerBO.getResponse());
				final JobseekerBO jobseeker = this.clear();
				model.addAttribute("jobseeker", jobseeker);
				return "redirect:/admin_jobseekers.html";
			}
		} catch (final Exception e) {
			e.printStackTrace();
			// log.debug(registration.getErrorMessage() + e);
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("Admin update jobseekers failed:" + e.getMessage());
			}
			LOGGER.info("Admin update jobseekers failed:" + e.getMessage() + e);
		}
		AdminController.LOGGER.exit();
		return null;
	}

	@RequestMapping(value = "/admin_delete_jobseeker", method = RequestMethod.GET)
	public String deleteJobseeker(Model model, HttpServletRequest request)
			throws MyJobKartException {
		AdminController.LOGGER.entry();
		try {
			final String id = request.getParameter("id");
			final long deletedId = Long.parseLong(id);

			final HttpSession session = request.getSession();
			final long adminid = (Long) session.getAttribute("adminId");

			JobseekerBO deleteProfile = new JobseekerBO();
			deleteProfile.setId(deletedId);
			deleteProfile.setDeletedBy(adminid);
			deleteProfile.setModifiedBy(adminid);
			deleteProfile.setIsDeleted(false);
			final Date deletedDate = new Date();
			deleteProfile.setDeletedDate(deletedDate);
			deleteProfile = this.jobSeekerService
					.deleteJobseeker(deleteProfile);
			if (null != deleteProfile.getResponse()) {
				model.addAttribute("message", deleteProfile.getResponse());

			} else {
				model.addAttribute("message",
						"Data has been updated failed,Please Contact Administrator.");

			}
		} catch (final MyJobKartException jb) {
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("Admin delete jobseekers failed:"
						+ jb.getErrorCode() + jb);
			}
			LOGGER.info("Admin delete jobseekers failed:" + jb.getErrorCode()
					+ jb);
		}
		AdminController.LOGGER.exit();
		return "redirect:/admin_jobseekers.html";
	}

	@RequestMapping(value = "/admin_jobseeker_profiles", method = RequestMethod.GET)
	public String retrieveJobseekerProfile(Model model,
			HttpServletRequest request) {
		AdminController.LOGGER.entry();
		HttpSession session = request.getSession();
		if (null == session.getAttribute("adminId")) {
			return "redirect:/admin_sign_in.html";
		}
		String message = request.getParameter("message");
		model.addAttribute("message", message);

		try {

			this.jobseekerProfileBO = new JobseekerProfileBO();
			this.jobseekerProfileBO = this.jobSeekerService
					.retrieveJobseekerProfile();

			this.jobseekerProfileList = this.jobseekerProfileBO
					.getJobseekerProfileList();
			model.addAttribute("jobseekerProfile", this.jobseekerProfileList);
			model.addAttribute("resumeSearch", new JobseekerProfileBO());
		} catch (final Exception e) {
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("Admin jobseeker profiles failed:"
						+ e.getMessage());
			}
			LOGGER.info("Admin jobseeker profiles failed:" + e.getMessage());

		}

		AdminController.LOGGER.exit();
		return "admin_jobseeker_profiles";
	}

	@RequestMapping(value = "/active_jobseeker_profiles", method = RequestMethod.GET)
	public String retrieveActiveJobseekerProfile(Model model,
			HttpServletRequest request) {
		AdminController.LOGGER.entry();
		model.addAttribute("resumeSearch", new JobseekerProfileBO());
		final List<JobseekerProfileBO> activeList = new ArrayList<JobseekerProfileBO>();
		int page = 1;
		final String paging = request.getParameter("page");
		final HttpSession session = request.getSession();
		try {
			if (null != paging) {
				page = Integer.parseInt(paging);
				final List<JobseekerProfileBO> activeLists = (List<JobseekerProfileBO>) session
						.getAttribute("activeList");
				final ResponseObject<JobseekerProfileBO> reponseObject = this
						.jobpagination(page, activeLists);
				model.addAttribute("jobseekerProfiles", reponseObject);
			} else {
				if (0 != this.jobseekerProfileList.size()) {
					for (final JobseekerProfileBO jobseekerProfileBO : this.jobseekerProfileList) {
						if (!jobseekerProfileBO.getIsActive()) {
							JobseekerProfileBO profileBO = new JobseekerProfileBO();
							profileBO = jobseekerProfileBO;
							activeList.add(profileBO);
						}
					}
					session.setAttribute("activeList", activeList);
					final ResponseObject<JobseekerProfileBO> reponseObject = this
							.jobpagination(page, activeList);
					model.addAttribute("jobseekerProfiles", reponseObject);
					model.addAttribute("activeColor", "white");
				} else {
					model.addAttribute("message",
							"Retrive Failer!..please Contact Administrator.. ");
				}
			}
		} catch (final Exception e) {
			e.printStackTrace();
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("Active joseeker profiles failed:"
						+ e.getMessage());
			}
			LOGGER.info("Active jobseeker profiles failed:" + e.getMessage());
		}

		AdminController.LOGGER.exit();
		return "admin_jobseeker_profiles";
	}

	@RequestMapping(value = "/inactive_jobseeker_profiles", method = RequestMethod.GET)
	public String retrieveInActiveJobseekerProfile(Model model,
			HttpServletRequest request) {
		AdminController.LOGGER.entry();
		model.addAttribute("resumeSearch", new JobseekerProfileBO());
		final List<JobseekerProfileBO> inActiveList = new ArrayList<JobseekerProfileBO>();
		int page = 1;
		final String paging = request.getParameter("page");
		final HttpSession session = request.getSession();
		try {
			if (null != paging) {

				page = Integer.parseInt(paging);
				final List<JobseekerProfileBO> inActiveLists = (List<JobseekerProfileBO>) session
						.getAttribute("inActiveList");
				final ResponseObject<JobseekerProfileBO> reponseObject = this
						.jobpagination(page, inActiveLists);

				model.addAttribute("jobseekerProfile1", reponseObject);

			} else {
				if (0 != this.jobseekerProfileList.size()) {
					for (final JobseekerProfileBO jobseekerProfileBO : this.jobseekerProfileList) {
						if (jobseekerProfileBO.getIsActive()) {
							JobseekerProfileBO profileBO = new JobseekerProfileBO();
							profileBO = jobseekerProfileBO;
							inActiveList.add(profileBO);
						}
					}
					session.setAttribute("inActiveList", inActiveList);
					final ResponseObject<JobseekerProfileBO> responseObject = this
							.jobpagination(1, inActiveList);
					model.addAttribute("jobseekerProfile1", responseObject);
				} else {
					model.addAttribute("message",
							"Retrive Failer!..Please Contact Administrator.. ");
				}
			}
		} catch (final Exception e) {
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("Inactive joseeker profiles failed:"
						+ e.getMessage());
			}
			LOGGER.info("Inactive jobseeker profiles failed:" + e.getMessage());

		}

		AdminController.LOGGER.exit();
		model.addAttribute("inactiveColor", "white");
		return "admin_jobseeker_profiles";
	}

	@RequestMapping(value = "/admin_jobseeker_profile_details", method = RequestMethod.GET)
	public String jobseekerProfileDetails(Model model,
			HttpServletRequest request) throws SQLException, MyJobKartException {
		AdminController.LOGGER.entry();
		HttpSession session = request.getSession();
		String userType = "";
		if (null != session.getAttribute("userType")) {
			userType = (String) session.getAttribute("userType");
		}
		if (!userType.equalsIgnoreCase("admin")) {
			if (null == session.getAttribute("jobseekerId")) {
				return "redirect:/jobseeker_sign_in.html";
			}
		}
		try {

			List<JobseekerProfileBO> profileBOList = new ArrayList<JobseekerProfileBO>();
			final String id = request.getParameter("id");

			final long jobseekerId = Long.parseLong(id);
			JobseekerProfileBO jobSeekerProfileBO = null;
			this.reteriveprofile = new JobseekerProfileBO();
			reteriveprofile.setId(jobseekerId);
			this.reteriveprofile = this.jobSeekerService
					.retriveJobseeker(this.reteriveprofile);

			if (0 != this.reteriveprofile.getJobseekerProfileList().size()) {
				for (JobseekerProfileBO profile : reteriveprofile
						.getJobseekerProfileList()) {
					jobSeekerProfileBO = new JobseekerProfileBO();
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
			model.addAttribute("educationalDetail", profileBOList);

			final JobseekerProfileBO jobseekerProfileBO = new JobseekerProfileBO();
			model.addAttribute("searchjobseeker", jobseekerProfileBO);
			model.addAttribute("similar", this.profileList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		AdminController.LOGGER.exit();
		return "admin_jobseeker_profile_details";
	}

	/*
	 * @RequestMapping(value = "/admin_jobseeker_update_profile", method =
	 * RequestMethod.GET) public String updateProfile(Model model,
	 * HttpServletRequest request) { AdminController.LOGGER.entry(); try {
	 * HttpSession session = request.getSession(); if (null ==
	 * session.getAttribute("adminId")) { return "redirect:/admin_sign_in.html";
	 * }
	 * 
	 * final String id = request.getParameter("id"); final long jobseekerId =
	 * Long.parseLong(id); for (final JobseekerProfileBO profileBO :
	 * this.jobseekerProfileList) { if (profileBO.getId() == jobseekerId) {
	 * this.jobseekerProfileBO = profileBO; model.addAttribute("updateProfile",
	 * this.jobseekerProfileBO); } } } catch (final NullPointerException ne) {
	 * if (LOGGER.isDebugEnabled()) {
	 * LOGGER.debug("Admin joseeker update profile  failed:" + ne.getMessage() +
	 * ne); } LOGGER.info("Admin jobseeker update profile failed:" +
	 * ne.getMessage() + ne); } AdminController.LOGGER.exit(); return
	 * "admin_jobseeker_update_profile"; }
	 */

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
	/*
	 * @RequestMapping(value = "/admin_jobseeker_update_profile", method =
	 * RequestMethod.POST) public String updateProfile(
	 * 
	 * @Valid @ModelAttribute("updateProfile") JobseekerProfileBO updateProfile,
	 * BindingResult result, HttpServletRequest request, Model model,
	 * 
	 * @RequestParam("file") MultipartFile file,
	 * 
	 * @RequestParam("file1") MultipartFile file1) throws MyJobKartException,
	 * IOException, SerialException, SQLException, ValidatorException {
	 * AdminController.LOGGER.entry(); HttpSession session =
	 * request.getSession(); if (null == session.getAttribute("adminId")) {
	 * return "redirect:/admin_sign_in.html"; }
	 * 
	 * final long adminid = (Long) session.getAttribute("adminId"); try {
	 * updateProfile.setEmailId(this.jobseekerProfileBO.getEmailId()); if
	 * (result.hasErrors()) { return "admin_jobseeker_update_profile"; }
	 * 
	 * if (file1.isEmpty() && this.jobseekerProfileBO.getProfileImage().length()
	 * != 0) { updateProfile.setProfileImage(this.jobseekerProfileBO
	 * .getProfileImage().getBytes( 1, (int)
	 * this.jobseekerProfileBO.getProfileImage() .length())); } else {
	 * updateProfile.setProfileImage(file1.getBytes()); if (!file1.isEmpty()) {
	 * 
	 * final String photo = this.imagevalied(file1); if (null != photo) {
	 * model.addAttribute("message",
	 * "ProfileImage Only jpeg,jpg,png formats are allowed  "); return
	 * "admin_jobseeker_update_profile"; } } } if (file.isEmpty() &&
	 * this.jobseekerProfileBO.getUploadResume().length() != 0) {
	 * updateProfile.setUploadResume(this.jobseekerProfileBO
	 * .getUploadResume().getBytes( 1, (int)
	 * this.jobseekerProfileBO.getUploadResume() .length())); } else {
	 * updateProfile.setUploadResume(file.getBytes()); if (!file.isEmpty()) {
	 * final String resume = this.resumevalied(file); if (null != resume) {
	 * model.addAttribute("message",
	 * "Resume Only msword,pdf.. formats are allowed"); return
	 * "admin_jobseeker_update_profile";
	 * 
	 * } } } if (null != updateProfile.getFirstName()) { final String status =
	 * JobtrolleyValidator .validate(updateProfile.getFirstName()); if (null !=
	 * status) { model.addAttribute("message", status); return
	 * "admin_jobseeker_update_profile"; }
	 * 
	 * } if (null != updateProfile.getLastName()) { final String status =
	 * JobtrolleyValidator .validate(updateProfile.getLastName()); if (null !=
	 * status) { model.addAttribute("message", status); return
	 * "admin_jobseeker_update_profile"; }
	 * 
	 * } updateProfile.setCreatedBy(this.jobseekerProfileBO.getCreatedBy());
	 * updateProfile.setIsActive(this.jobseekerProfileBO.getIsActive());
	 * updateProfile.setModifiedBy(adminid);
	 * updateProfile.setIsDelete(this.jobseekerProfileBO.getIsDelete());
	 * 
	 * updateProfile.setJobSeekerLogin(this.jobseekerProfileBO
	 * .getJobSeekerLogin());
	 * updateProfile.setVersion(this.jobseekerProfileBO.getVersion());
	 * updateProfile = this.jobSeekerService.updateProfile(updateProfile);
	 * 
	 * if (null != updateProfile.getResponse()) { model.addAttribute("message",
	 * updateProfile.getResponse()); return
	 * "redirect:/admin_jobseeker_profiles.html";
	 * 
	 * } else { model.addAttribute("message",
	 * "Data has been updated failed,Please contact Administrator."); return
	 * "admin_jobseeker_update_profile"; } } catch (final MyJobKartException jb)
	 * { jb.printStackTrace(); if (LOGGER.isDebugEnabled()) {
	 * LOGGER.debug("Admin joseeker update profile  failed:" + jb.getErrorCode()
	 * + jb); } LOGGER.info("Admin jobseeker update profile failed:" +
	 * jb.getErrorCode() + jb); } AdminController.LOGGER.exit(); return null; }
	 */

	/*
	 * @RequestMapping(value = "/admin_jobseeker_delete_profile", method =
	 * RequestMethod.GET) public String deleteProfile(Model model,
	 * HttpServletRequest request) throws MyJobKartException {
	 * AdminController.LOGGER.entry(); String id = null; long deletedId = 0; try
	 * { if (request.getParameter("id") != null) { id =
	 * request.getParameter("id"); deletedId = Long.parseLong(id); }
	 * 
	 * final HttpSession session = request.getSession(); final long adminid =
	 * (Long) session.getAttribute("adminId");
	 * 
	 * JobseekerProfileBO deleteProfile = new JobseekerProfileBO();
	 * deleteProfile.setId(deletedId); deleteProfile.setIsDelete(false);
	 * deleteProfile.setDeleteBy(adminid); deleteProfile.setModifiedBy(adminid);
	 * deleteProfile.setModified(new Date()); final Date deletedDate = new
	 * Date(); deleteProfile.setDeletedDate(deletedDate); deleteProfile =
	 * this.jobSeekerService .jobseekerDeleteProfile(deleteProfile); if (null !=
	 * deleteProfile.getResponse()) { model.addAttribute("message",
	 * deleteProfile.getResponse());
	 * 
	 * } else { model.addAttribute("message",
	 * "Data has been updated failed,Please contact Administrator.");
	 * 
	 * } } catch (final MyJobKartException jb) { if (LOGGER.isDebugEnabled()) {
	 * LOGGER.debug("Admin joseeker delete profile  failed:" + jb.getErrorCode()
	 * + jb); } LOGGER.info("Admin jobseeker delete profile failed:" +
	 * jb.getErrorCode() + jb); } AdminController.LOGGER.exit(); return
	 * "redirect:/admin_jobseeker_profiles.html"; }
	 */

	@RequestMapping(value = "/jobseeker_profile_image", method = RequestMethod.GET)
	public void showJobseekerProfileImage(@RequestParam("id") Integer itemId,
			HttpServletResponse response, HttpServletRequest request)
			throws ServletException, IOException, SQLException {

		final String id = request.getParameter("id");
		final long imgid = Long.parseLong(id);
		for (final JobseekerProfileBO jobseekerProfileBO : this.jobseekerProfileList) {

			if (jobseekerProfileBO.getId() == imgid) {
				response.setContentType("image/jpeg, image/jpg, image/png, image/gif");
				response.getOutputStream().write(
						jobseekerProfileBO.getProfileImage().getBytes(
								1,
								(int) jobseekerProfileBO.getProfileImage()
										.length()));
				response.getOutputStream().close();
			}
		}
	}

	@RequestMapping(value = "/profileResumeDisplay", method = RequestMethod.GET)
	public void showResume(@RequestParam("id") Integer itemId,
			HttpServletResponse response, HttpServletRequest request)
			throws ServletException, IOException, SQLException {
		final String id = request.getParameter("id");
		final long imgid = Long.parseLong(id);
		for (final JobseekerProfileBO prifiles : this.jobseekerProfileList) {
			if (prifiles.getId() == imgid) {
				response.setContentType("application/msword");
				response.getOutputStream().write(
						prifiles.getUploadResume().getBytes(1,
								(int) prifiles.getUploadResume().length()));
				response.getOutputStream().close();
			}
		}

	}

	@SuppressWarnings("rawtypes")
	private ResponseObject jobpagination(int page,
			List<JobseekerProfileBO> dataLsit) {
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
		final ResponseObject<JobseekerProfileBO> ro = new ResponseObject<JobseekerProfileBO>();
		final List<JobseekerProfileBO> list = dataLsit;
		if (null != list) {
			noOfRecords = list.size();
		}
		noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
		ro.setCurrentPage(page);
		ro.setRecordsPerPage(recordsPerPage);
		ro.setTotalPages(noOfPages);
		int count = 0;
		final List<JobseekerProfileBO> pageList = new ArrayList<JobseekerProfileBO>();
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

	public EmployerBO empclear() {
		final EmployerBO employerBO = new EmployerBO();
		employerBO.setFirstName(null);
		return employerBO;

	}

	public JobseekerBO clear() {
		final JobseekerBO jobseekerBO = new JobseekerBO();

		jobseekerBO.setFirstName(null);

		return jobseekerBO;

	}

	@RequestMapping(value = "/admin_job_post", method = RequestMethod.GET)
	public String jobPost(Model model, HttpServletRequest request) {
		AdminController.LOGGER.entry();
		companyList(model);
		industryList(model);
		HttpSession session = request.getSession();
		if (null == session.getAttribute("adminId")) {
			return "redirect:/admin_sign_in.html";
		}

		String message = request.getParameter("message");
		model.addAttribute("message", message);

		try {

			JobPostBO allJobPost = new JobPostBO();
			allJobPost = this.adminService.reteriveAllJoBpost();
			this.allJobList = allJobPost.getJobPostList();

			if (null == allJobPost.getJobPostList()) {
				model.addAttribute("Infomessage", "No JobPost Found.. ");
			} else {
				model.addAttribute("JoBpost", this.allJobList);
				model.addAttribute("adminJobPost", new JobPostBO());

				return "admin_job_post";
			}
		} catch (final Exception e) {
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("Admin job post  failed:" + e.getMessage());
			}
			LOGGER.info("Admin job post failed:" + e.getMessage());

		}

		return "admin_job_post";
	}

	@RequestMapping(value = "/active_jobpost", method = RequestMethod.GET)
	public String isActiveJopList(Model model, HttpServletRequest request) {

		AdminController.LOGGER.entry();
		HttpSession session = request.getSession();
		if (null == session.getAttribute("adminId")) {
			return "redirect:/admin_sign_in.html";
		}

		JobPostBO allJobPost = new JobPostBO();
		final List<JobPostBO> activeJobPostList = new ArrayList<JobPostBO>();
		int page = 1;
		model.addAttribute("adminJobPost", new JobPostBO());
		final String paging = request.getParameter("page");

		try {

			if (null != paging) {

				page = Integer.parseInt(paging);
				final List<JobPostBO> activeLists = (List<JobPostBO>) session
						.getAttribute("ActiveJobPostList");
				final ResponseObject<JobPostBO> reponseObject = this
						.jobpostpagination(page, activeLists);

				model.addAttribute("JoBposts", reponseObject);

			} else {
				allJobPost = this.adminService.reteriveAllJoBpost();
				this.allJobList = allJobPost.getJobPostList();
				if (null != this.allJobList && this.allJobList.size() > 0) {

					for (final JobPostBO jobPostBO : this.allJobList) {

						if (jobPostBO.getIsActive()) {
							JobPostBO jobpost = new JobPostBO();
							jobpost = jobPostBO;
							activeJobPostList.add(jobpost);
						}
					}
					session.setAttribute("ActiveJobPostList", activeJobPostList);
					final ResponseObject<JobPostBO> responseObject = this
							.jobpostpagination(1, activeJobPostList);
					model.addAttribute("JoBposts", responseObject);
					model.addAttribute("activeJobpost", "white");

				} else {
					model.addAttribute("Infomessage", "Record not found...");
				}
			}
		} catch (final Exception e) {
			e.printStackTrace();
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("Active jobpost  failed:" + e.getMessage());
			}
			LOGGER.info("Active jobpost failed:" + e.getMessage());
		}
		AdminController.LOGGER.exit();
		return "admin_job_post";

	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "de_active_jobpost", method = RequestMethod.GET)
	public String deActivejobpost(Model model, HttpServletRequest request) {
		AdminController.LOGGER.entry();
		HttpSession session = request.getSession();
		if (null == session.getAttribute("adminId")) {
			return "redirect:/admin_sign_in.html";
		}

		JobPostBO allJobPost = new JobPostBO();
		final List<JobPostBO> deActiveJobPostList = new ArrayList<JobPostBO>();
		int page = 1;
		final String paging = request.getParameter("page");

		model.addAttribute("adminJobPost", new JobPostBO());
		try {
			if (null != paging) {
				page = Integer.parseInt(paging);
				final List<JobPostBO> deActiveJobPost = (List<JobPostBO>) session
						.getAttribute("deActiveJobPostList");
				final ResponseObject<EmployerBO> reponseObject = this
						.jobpostpagination(page, deActiveJobPost);
				model.addAttribute("JoBpost1", reponseObject);
			} else {
				allJobPost = this.adminService.reteriveAllJoBpost();
				this.allJobList = allJobPost.getJobPostList();
				if (null != this.allJobList && this.allJobList.size() > 0) {

					for (final JobPostBO jobPostBO : this.allJobList) {
						if (!jobPostBO.getIsActive()) {
							JobPostBO jobpost = new JobPostBO();
							jobpost = jobPostBO;
							deActiveJobPostList.add(jobpost);
						}
					}
					session.setAttribute("deActiveJobPostList",
							deActiveJobPostList);
					final ResponseObject<EmployerBO> responseObject = this
							.jobpostpagination(1, deActiveJobPostList);
					model.addAttribute("JoBpost1", responseObject);
					model.addAttribute("deactiveJobpost", "white");

				} else {
					model.addAttribute("Infomessage", "Record not found");
				}
			}
		} catch (final Exception e) {
			e.printStackTrace();
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("Deactive jobpost  failed:" + e.getMessage());
			}
			LOGGER.info("Deactive jobpost failed:" + e.getMessage());
		}
		AdminController.LOGGER.exit();
		return "admin_job_post";
	}

	@SuppressWarnings("null")
	@RequestMapping(value = "/update_jobpost", method = RequestMethod.GET)
	public String updateJobPost(Model model, HttpServletRequest request) {
		AdminController.LOGGER.entry();
		getCurrencyType(model);
		companyList(model);
		industryList(model);
		getMinimumExperiences(model);
		getMaximumExperiences(model);
		getMaximumSalary(model);
		getMinimumSalary(model);
		getFunctionarea(model);
		getJobType(model);
		getLocation(model);
		getPgqualifications(model);
		getUgqualifications(model);
		try {
			HttpSession session = request.getSession();
			if (null == session.getAttribute("adminId")) {
				return "redirect:/admin_sign_in.html";
			}

			final String id = request.getParameter("id");

			final long jobpostId = Long.parseLong(id);
			for (final JobPostBO jobPostBO : this.allJobList) {
				if (jobPostBO.getId() == jobpostId) {
					this.updateJobPostBO = jobPostBO;
					model.addAttribute("jobupdate", jobPostBO);
				}
			}
		} catch (final NullPointerException ne) {
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("update jobpost  failed:" + ne.getMessage() + ne);
			}
			LOGGER.info("update jobpost failed:" + ne.getMessage() + ne);
		}
		AdminController.LOGGER.exit();
		return "admin_jobpost_update";
	}

	@SuppressWarnings("null")
	@RequestMapping(value = "/view_jobpost", method = RequestMethod.GET)
	public String viewJobPost(Model model, HttpServletRequest request) {
		AdminController.LOGGER.entry();

		try {

			HttpSession session = request.getSession();
			if (null == session.getAttribute("adminId")) {
				return "redirect:/admin_sign_in.html";
			}
			final String id = request.getParameter("id");

			final long jobId = Long.parseLong(id);

			for (final JobPostBO jobPostBO : this.allJobList) {
				if (jobPostBO.getId() == jobId) {
					this.updateJobPostBO = jobPostBO;

					model.addAttribute("jobDetail", jobPostBO);
				}
			}
		} catch (final NullPointerException ne) {
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("view jobpost  failed:" + ne.getMessage() + ne);
			}
			LOGGER.info("view jobpost failed:" + ne.getMessage() + ne);

		}
		AdminController.LOGGER.exit();
		return "admin_jobpost_view";
	}

	@SuppressWarnings("null")
	@RequestMapping(value = "/delete_jobpost", method = RequestMethod.GET)
	public String deleteJobPost(Model model, HttpServletRequest request)
			throws MyJobKartException {
		AdminController.LOGGER.entry();
		try {
			final String id = request.getParameter("id");
			final long deletedId = Long.parseLong(id);

			final HttpSession session = request.getSession();
			final long adminid = (Long) session.getAttribute("adminId");

			JobPostBO deleteProfile = new JobPostBO();
			deleteProfile.setId(deletedId);
			deleteProfile.setDeletedBy(adminid);
			deleteProfile.setModifiedBy(adminid);
			deleteProfile.setIsDeleted(false);
			final Date deletedDate = new Date();
			deleteProfile.setDeletedDate(deletedDate);
			deleteProfile = this.employerService.deleteJobPost(deleteProfile);
			if (null != deleteProfile.getResponse()) {
				model.addAttribute("message", deleteProfile.getResponse());

			} else {
				model.addAttribute("message",
						"Data has been updated failed,Please contact Administrator.");

			}

		} catch (final MyJobKartException jb) {
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("delete jobpost  failed:" + jb.getErrorCode() + jb);
			}
			LOGGER.info("delete jobpost failed:" + jb.getErrorCode() + jb);
		}
		AdminController.LOGGER.exit();
		return "redirect:/admin_job_post.html";
	}

	@RequestMapping(value = "/jobPostDisplay", method = RequestMethod.GET)
	public void showJobPostImage(@RequestParam("id") Integer itemId,
			HttpServletResponse response, HttpServletRequest request)
			throws ServletException, IOException, SQLException {
		final String id = request.getParameter("id");
		final long imgid = Long.parseLong(id);
		for (final JobPostBO jobPostBO : this.allJobList) {
			if (jobPostBO.getId() == imgid) {
				response.setContentType("image/jpeg, image/jpg, image/png, image/gif");
				response.getOutputStream().write(
						jobPostBO.getPhoto().getBytes(1,
								(int) jobPostBO.getPhoto().length()));
				response.getOutputStream().close();
			}
		}

	}

	@RequestMapping(value = "/update_jobpost", method = RequestMethod.POST)
	public String updateJobPost(
			@ModelAttribute("jobupdate") @Valid JobPostBO jobpost,
			BindingResult result, Model model, HttpServletRequest request)
			throws MyJobKartException, IOException, SerialException,
			SQLException, ValidatorException {
		AdminController.LOGGER.entry();

		HttpSession session = request.getSession();
		if (null == session.getAttribute("adminId")) {
			return "redirect:/admin_sign_in.html";
		}
		long id = 0;
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

		id = (Long) session.getAttribute("adminId");
		final EmployerLoginBO employerLogin = new EmployerLoginBO();
		employerLogin.setId(id);

		jobpost.setId(this.updateJobPostBO.getId());

		if (null != jobpost.getContactPerson()) {
			final String status = JobtrolleyValidator.validate(jobpost
					.getContactPerson());
			if (null != status) {
				model.addAttribute("message",
						"JobPost has been successfully updated");
				return "admin_jobpost_update";
			}

		}
		long companyId = 0;
		if (null != jobpost.getCompanyName()) {
			jobpost.getCompanyName();
			companyId = getcompany(jobpost.getCompanyName());
			if (companyId != 0) {
				jobpost.setCompanyId(companyId);
			}

		}
		if (null != jobpost.getOtherCompany()) {
			jobpost.setCompanyName(jobpost.getOtherCompany());
			companyId = getcompany(jobpost.getCompanyName());
			if (companyId == 0) {
				companyId = adminService.addNewCompany(jobpost
						.getOtherCompany());
				jobpost.setCompanyId(id);
			} else {
				jobpost.setCompanyId(companyId);
			}
		}

		jobpost.setCreatedBy(id);
		jobpost.setModifiedBy(id);
		jobpost.setIsDeleted(true);
		jobpost.setIsActive(this.updateJobPostBO.getIsActive());
		jobpost.setVersion(this.updateJobPostBO.getVersion());
		jobpost.setEmpId(id);
		jobpost.setEmployerLogin(employerLogin);
		jobpost = this.employerService.updateJobPost(jobpost);
		if (null != jobpost.getResponse()) {
			model.addAttribute("message", jobpost.getResponse());
			return "redirect:/admin_job_post.html";
		} else {
			model.addAttribute("Infomessage",
					"Data has been updated failed,Please contact Administrator.");
			return "admin_jobpost_update";
		}
	}

	@SuppressWarnings("rawtypes")
	private ResponseObject jobpostpagination(int page,
			List<JobPostBO> jobPostList) {
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
		final ResponseObject<JobPostBO> ro = new ResponseObject<JobPostBO>();
		final List<JobPostBO> list = jobPostList;
		if (null != list) {
			noOfRecords = list.size();
		}
		noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
		ro.setCurrentPage(page);
		ro.setRecordsPerPage(recordsPerPage);
		ro.setTotalPages(noOfPages);
		int count = 0;

		final List<JobPostBO> pageList = new ArrayList<JobPostBO>();
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

	public String imagevalied(MultipartFile profileImage) {
		String str = null;
		if (profileImage.getContentType().equals("image/jpeg")
				|| profileImage.getContentType().equals("image/jpg")
				|| profileImage.getContentType().equals("image/png")) {

		} else {
			str = "ProfileImage Only jpeg,jpg,png formats are allowed  ";

		}
		return str;
	}

	public String resumevalied(MultipartFile resumeFile) {
		String str = null;
		if (resumeFile.getContentType().equals("application/msword")
				|| resumeFile.getContentType().equals("application/pdf")) {

		} else {
			str = "Resume or Docment Only msword,pdf.. formats are allowed  ";

		}
		return str;
	}

	@RequestMapping(value = "/employeeNameSearch", method = RequestMethod.POST)
	public String appliedJobSearch(
			@Valid @ModelAttribute("employeeNameSearch") EmployerProfileBO updateProfile,
			BindingResult result, HttpServletRequest request, Model model) {

		int page = 1;

		List<EmployerBO> allEmployeList = new ArrayList<EmployerBO>();
		final String paging = request.getParameter("page");
		try {

			if (null != paging) {

				page = Integer.parseInt(paging);

				final ResponseObject<EmployerBO> reponseObject = this
						.pagination(page, this.allEmployerList);
				model.addAttribute("registeredEmployer", reponseObject);

			} else {

				for (final EmployerBO profileBO : this.allEmployerList) {
					if (profileBO
							.getFirstName()
							.toLowerCase()
							.contains(
									updateProfile.getSearchElement()
											.toLowerCase())) {
						this.updateEmployer = profileBO;
						allEmployeList.add(this.updateEmployer);
					}
				}
				allEmployeList = allEmployeList;
				final ResponseObject<JobseekerProfileBO> responseObject = this
						.pagination(1, allEmployeList);
				model.addAttribute("registeredEmployer", responseObject);

			}

		} catch (final Exception e) {
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("EmployeeNameSearch failed:" + e.getMessage());
			}
			LOGGER.info("EmployeeNameSearch failed:" + e.getMessage());

		}
		return "employer_details";
	}

	@RequestMapping(value = "/companyNameSearch", method = RequestMethod.POST)
	public String adminEmployerHistroy(
			@Valid @ModelAttribute("companyNameSearch") EntityBO companyBO,
			BindingResult result, HttpServletRequest request, Model model) {

		try {
			long sNo = 0;
			List<EntityBO> companyList = new ArrayList<EntityBO>();
			String value = companyBO.getCompanyName();
			List<JobPostBO> companyProfileList = employerService
					.viewRecruiters(value);
			if (null != companyProfileList && companyProfileList.size() > 0) {
				for (JobPostBO companyProfle : companyProfileList) {
					sNo++;
					companyBO = new EntityBO();
					companyBO.setsNo(sNo);
					companyBO.setCompaniesId(companyProfle.getCompanyId());
					companyBO.setCompanyName(companyProfle.getCompanyName());
					companyList.add(companyBO);
					model.addAttribute("companyList", companyList);
				}
			} else {
				model.addAttribute("infomessage", "No company found");
			}

		} catch (final Exception e) {
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("EmployeeNameSearch failed:" + e.getMessage());
			}
			LOGGER.info("EmployeeNameSearch failed:" + e.getMessage());

		}
		return "admin_employer_history";
	}

	@RequestMapping(value = "/jobSeekerNameSearch", method = RequestMethod.POST)
	public String jobSeekerNameSearch(
			@Valid @ModelAttribute("jobSeekerNameSearch") JobseekerProfileBO updateProfile,
			BindingResult result, HttpServletRequest request, Model model) {

		int page = 1;

		List<JobseekerBO> alljobseekerList = new ArrayList<JobseekerBO>();
		final String paging = request.getParameter("page");
		try {

			if (null != paging) {
				page = Integer.parseInt(paging);
				final ResponseObject<JobseekerBO> reponseObject = this
						.jobseekerpagination(page, this.registeredList);
				model.addAttribute("registeredList", reponseObject);
			} else {

				if (this.registeredList.size() > 0) {

					for (final JobseekerBO profileBO : this.registeredList) {
						if (profileBO.getFirstName().equalsIgnoreCase(
								updateProfile.getSearchElement())) {
							this.jobseekerBO = profileBO;
							alljobseekerList.add(this.jobseekerBO);
						}
					}
					alljobseekerList = alljobseekerList;
					final ResponseObject<JobseekerProfileBO> responseObject = this
							.jobseekerpagination(1, alljobseekerList);
					model.addAttribute("registeredList", responseObject);

				} else {

				}
			}
		} catch (final Exception e) {
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("JobseekerNameSearch failed:" + e.getMessage());
			}
			LOGGER.info("JobseekerNameSearch failed:" + e.getMessage());

		}
		return "admin_jobseekers";
	}

	@RequestMapping(value = "/resumeSearch", method = RequestMethod.POST)
	public String resumeSearch(
			@Valid @ModelAttribute("resumeSearch") JobseekerProfileBO updateProfile,
			BindingResult result, HttpServletRequest request, Model model) {

		// int page = 1;

		List<JobseekerProfileBO> alljobseekerList = new ArrayList<JobseekerProfileBO>();
		try {
			this.jobseekerProfileBO = this.jobSeekerService
					.retrieveJobseekerResume(updateProfile);

			List<JobseekerProfileBO> jobseekerresumeList = jobseekerProfileBO
					.getJobseekerResumeList();

			if (jobseekerresumeList.size() > 0) {
				model.addAttribute("jobseekerProfile", jobseekerresumeList);
			}
		} catch (final Exception e) {
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("Resume Search failed:" + e.getMessage());
			}
			LOGGER.info("Resume Search failed:" + e.getMessage());

		}
		return "admin_jobseeker_profiles";
	}

	@RequestMapping(value = "/adminJobPost", method = RequestMethod.POST)
	public String adminJobPost(
			@Valid @ModelAttribute("adminJobPost") EmployerProfileBO updateProfile,
			BindingResult result, HttpServletRequest request, Model model) {

		int count = 0;

		List<JobPostBO> alljobpost = new ArrayList<JobPostBO>();
		/* final String paging = request.getParameter("page"); */
		try {

			/*
			 * if (null != paging) { page = Integer.parseInt(paging); final
			 * ResponseObject<JobPostBO> reponseObject = this
			 * .jobpostpagination(page, this.allJobList);
			 * model.addAttribute("JoBpost", reponseObject); } else {
			 */

			for (final JobPostBO profileBO : this.allJobList) {
				if (profileBO
						.getJobTitle()
						.toLowerCase()
						.contains(
								updateProfile.getSearchElement().toLowerCase())) {

					this.updateJobPostBO = profileBO;
					count = count + 1;
					this.updateJobPostBO.setsNo(count);
					alljobpost.add(this.updateJobPostBO);

				}
			}

			// alljobpost = alljobpost;
			model.addAttribute("JoBpost", alljobpost);
		} catch (final Exception e) {
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("Admin Jobpost failed:" + e.getMessage());
			}
			LOGGER.info("Admin Jobpost failed:" + e.getMessage());

		}
		return "admin_job_post";
	}

	@RequestMapping(value = "/renewal_admin_jobseekers", method = RequestMethod.GET)
	public String renewalAdminJobseekers(Model model, HttpServletRequest request) {
		AdminController.LOGGER.entry();
		model.addAttribute("jobSeekerNameSearch", new JobseekerBO());
		int page = 1;
		final String paging = request.getParameter("page");
		try {
			if (null != paging) {
				page = Integer.parseInt(paging);
				final ResponseObject<JobseekerBO> reponseObject = this
						.jobseekerpagination(page, this.registeredList);
				model.addAttribute("registeredList", reponseObject);
			} else {
				this.jobseekerBO = new JobseekerBO();
				this.jobseekerBO = this.jobSeekerService
						.renewalRegisteredJobseeker();
				this.registeredList = this.jobseekerBO.getRegisteredList();
				if (0 != this.registeredList.size()) {
					final ResponseObject<JobseekerBO> responseObject = this
							.jobseekerpagination(1, this.registeredList);
					model.addAttribute("registeredList", responseObject);
				} else {
					model.addAttribute("message",
							"Retrive Failer!..Please contact Administrator.. ");
				}
			}
		} catch (final Exception e) {
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("Renewal admin jobseekers failed:"
						+ e.getMessage());
			}
			LOGGER.info("Renewal admin obseekers failed:" + e.getMessage());

		}

		AdminController.LOGGER.exit();
		return "renewal_admin_jobseekers";
	}

	@RequestMapping(value = "/admin_jobseekers_profile_status", method = RequestMethod.GET)
	public String profileStatus(Model model, HttpServletRequest request) {
		AdminController.LOGGER.entry();
		JobSeekerLoginBO login = new JobSeekerLoginBO();
		try {
			final String jobseekid = request.getParameter("id");
			String status = request.getParameter("status");
			final long jobseekerId = Long.parseLong(jobseekid);
			for (final JobseekerBO jobseekerBO : this.registeredList) {
				if (jobseekerBO.getId() == jobseekerId) {
					login.setEmailAddress(jobseekerBO.getEmailAddress());
					login.setPassword(jobseekerBO.getPassword());
					login.setConfirmPassword(jobseekerBO.getConfirmPassword());
					login.setIsActive(true);
					Date date = new Date();
					login.setCreated(date);
					login.setModified(date);
					login.setCreatedBy(jobseekerBO.getId());
					login.setModifiedBy(jobseekerBO.getId());
					login.setId(jobseekerBO.getId());

					boolean active = this.jobSeekerService
							.activeJobseeker(login);

					if (active) {
						login.setIsActive(true);
						model.addAttribute("message",
								"Jobseeker Activated Sucessfully.");
						return "redirect:/admin_jobseekers.html";
					} else {
						login.setIsActive(false);
						return "redirect:/admin_jobseekers.html";
					}

				}
			}

		} catch (final NullPointerException ne) {
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("Admin jobseekers profile status failed:"
						+ ne.getMessage() + ne);
			}
			LOGGER.info("Admin jobseekers profile status failed:"
					+ ne.getMessage() + ne);
		}
		AdminController.LOGGER.exit();
		return "redirect:/admin_jobseekers.html";
	}

	@RequestMapping(value = "/admin_jobseekers_active", method = RequestMethod.GET)
	public String jobseekerStatus(Model model, HttpServletRequest request)
			throws SerialException, MyJobKartException, SQLException {
		AdminController.LOGGER.entry();
		JobSeekerLoginBO login = new JobSeekerLoginBO();
		try {
			long profileActiveCount = 0;
			boolean profileStatus = false;
			final String status = request.getParameter("status");
			final String[] arr = status.split(",");
			final String num = arr[1];
			final long id = Integer.parseInt(num);
			final String jbstatus = arr[0];

			// final long jobseekerId = Long.parseLong(jobseekid);

			for (final JobseekerBO jobseekerBO : this.registeredList) {
				if (jobseekerBO.getId() == id) {
					login.setEmailAddress(jobseekerBO.getEmailAddress());
					login.setPassword(jobseekerBO.getPassword());
					login.setConfirmPassword(jobseekerBO.getConfirmPassword());
					/* login.setIsActive(true); */

					if (jbstatus.equals("Active")) {
						login.setIsActive(false);
					} else {
						login.setIsActive(true);
					}

					Date date = new Date();
					login.setCreated(date);
					login.setModified(date);
					login.setCreatedBy(jobseekerBO.getId());
					login.setModifiedBy(jobseekerBO.getId());
					login.setJobseekerBO(jobseekerBO);

					if (null != jobseekerBO.getEmailAddress()) {

						JobSeekerLoginBO jobSeekerLoginBO = new JobSeekerLoginBO();
						jobSeekerLoginBO = this.jobSeekerService
								.jobseekerSignin(login);
						if (null != jobSeekerLoginBO) {
							login.setId(jobSeekerLoginBO.getId());
						}

						reteriveprofile.setJobseekerRegId(jobseekerBO.getId());
						reteriveprofile = jobSeekerService
								.retriveJobseeker(reteriveprofile);

						List<JobseekerProfileBO> jobseekerProfileList = reteriveprofile
								.getJobseekerProfileList();
						if (null != jobseekerProfileList
								&& jobseekerProfileList.size() != 0) {
							for (JobseekerProfileBO profileBO : jobseekerProfileList) {
								if (profileBO.getIsActive() == true) {
									profileActiveCount++;
								}
								if ((0 == profileActiveCount && jbstatus
										.equalsIgnoreCase("De-Active"))
										|| (0 != profileActiveCount && jbstatus
												.equalsIgnoreCase("Active"))) {
									if (jbstatus.equals("Active")) {
										profileBO.setIsActive(false);
									} else {
										profileBO.setIsActive(true);
									}

									profileStatus = this.jobSeekerService
											.profileStatus(profileBO);

								}
							}
						}

					}

					if (profileStatus) {
						profileStatus = this.jobSeekerService
								.activeJobseekerStatus(login);
					}

					if (profileStatus) {

						if (login.getIsActive()) {

							model.addAttribute("message",
									"Profile Activation is success.");
						} else {
							model.addAttribute("message",
									"Profile De-Activation is success.");
						}
						return "redirect:/admin_jobseekers.html";

					} else {

						return "redirect:/admin_jobseekers.html";
					}

				}
			}

		} catch (final NullPointerException ne) {
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("Admin jobseekers profile status failed:"
						+ ne.getMessage() + ne);
			}
			LOGGER.info("Admin jobseekers profile status failed:"
					+ ne.getMessage() + ne);
		}
		AdminController.LOGGER.exit();
		return "redirect:/admin_jobseekers.html";
	}

	@RequestMapping(value = "/emp_profile_status", method = RequestMethod.GET)
	public String empprofileStatus(Model model, HttpServletRequest request) {
		AdminController.LOGGER.entry();

		EmployerLoginBO login = new EmployerLoginBO();
		try {
			final String empid = request.getParameter("id");
			final long employerId = Long.parseLong(empid);
			for (final EmployerBO employerBO : this.allEmployerList) {
				if (employerBO.getId() == employerId) {
					login.setEmailAddress(employerBO.getEmailAddress());
					login.setPassword(employerBO.getPassword());
					login.setConfirmPassword(employerBO.getConfirmPassword());
					login.setIsActive(true);
					login.setCreatedBy(employerBO.getId());
					login.setModifiedBy(employerBO.getId());
					login.setId(employerBO.getId());

					boolean active = this.employerService.activeEmployer(login);

					if (active) {

						if (login.getIsActive()) {

							model.addAttribute("message",
									"Profile Activation is success.");
						} else {
							model.addAttribute("message",
									"Profile De-Activation is success.");
						}
						return "redirect:/employer_details.html";

					} else {
						login.setIsActive(false);
						return "redirect:/employer_details.html";
					}

				}
			}

		} catch (final NullPointerException ne) {
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("Emp profile status failed:" + ne.getMessage()
						+ ne);
			}
			LOGGER.info("Emp profile status failed:" + ne.getMessage() + ne);
		}

		AdminController.LOGGER.exit();
		return "redirect:/employer_details.html";
	}

	@RequestMapping(value = "/emp_profile_Active", method = RequestMethod.GET)
	public String empprofileStatusActive(Model model, HttpServletRequest request) {
		AdminController.LOGGER.entry();

		EmployerLoginBO login = new EmployerLoginBO();
		try {
			final String status = request.getParameter("status");

			final String[] arr = status.split(",");
			final String num = arr[1];
			final long id = Integer.parseInt(num);
			final String jbstatus = arr[0];
			for (final EmployerBO employerBO : this.allEmployerList) {
				if (employerBO.getId() == id) {
					login.setEmailAddress(employerBO.getEmailAddress());
					login.setPassword(employerBO.getPassword());
					login.setConfirmPassword(employerBO.getConfirmPassword());

					if (jbstatus.equals("Active")) {
						login.setIsActive(false);
					} else {
						login.setIsActive(true);
					}
					login.setCreatedBy(employerBO.getId());
					login.setModifiedBy(employerBO.getId());
					login.setId(employerBO.getId());

					boolean active = this.employerService
							.empprofileStatusActive(login);

					if (active) {

						if (login.getIsActive()) {

							model.addAttribute("message",
									"Profile Activation is success.");
						} else {
							model.addAttribute("message",
									"Profile De-Activation is success.");
						}
						return "redirect:/employer_details.html";

					} else {
						login.setIsActive(false);
						return "redirect:/employer_details.html";
					}

				}
			}

		} catch (final NullPointerException ne) {
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("Emp profile status failed:" + ne.getMessage()
						+ ne);
			}
			LOGGER.info("Emp profile status failed:" + ne.getMessage() + ne);
		}

		AdminController.LOGGER.exit();
		return "redirect:/employer_details.html";
	}

	@RequestMapping(value = "/renewal_admin_employeers", method = RequestMethod.GET)
	public String renewalAdminEmployeers(Model model, HttpServletRequest request)
			throws MyJobKartException, SerialException, SQLException {
		AdminController.LOGGER.entry();
		HttpSession session = request.getSession();
		if (null == session.getAttribute("adminId")) {
			return "redirect:/admin_sign_in.html";
		}
		List<EmployerBO> allEmployerRenewalList = new ArrayList<EmployerBO>();

		EmployerBO allEmployer = new EmployerBO();

		allEmployer = employerService.renewelRegisteredEmployer();
		allEmployerRenewalList = allEmployer.getRegisteredList();

		try {

			if (null != allEmployerRenewalList
					&& allEmployerRenewalList.size() > 0) {

				model.addAttribute("renewalEmployer", allEmployerRenewalList);
			} else {
				model.addAttribute("Infomessage", "Record not found");
			}
		} catch (final Exception e) {

		}
		AdminController.LOGGER.exit();
		return "renewal_admin_employeers";

	}

	@RequestMapping(value = "/notifications_alerts", method = RequestMethod.GET)
	public String renewalAlerts(Model model, HttpServletRequest request) {
		AdminController.LOGGER.entry();
		// notificationsList = new ArrayList<JobseekerBO>();
		HttpSession session = request.getSession();
		if (null == session.getAttribute("adminId")) {
			return "redirect:/admin_sign_in.html";
		}

		model.addAttribute("jobSeekerNameSearch", new JobseekerBO());
		int page = 1;
		final String paging = request.getParameter("page");
		try {
			if (null != paging) {
				page = Integer.parseInt(paging);
				@SuppressWarnings("unchecked")
				final ResponseObject<JobseekerBO> reponseObject = this
						.jobseekerpagination(page, this.notificationsList);
				model.addAttribute("notificationsList", reponseObject);
			} else {
				this.jobseekerBO = new JobseekerBO();
				this.jobseekerBO = this.jobSeekerService
						.renewalRegisteredJobseeker();
				this.notificationsList = this.jobseekerBO.getRegisteredList();
				model.addAttribute("notiAlters", "white");
				if (0 != this.notificationsList.size()) {
					@SuppressWarnings("unchecked")
					final ResponseObject<JobseekerBO> responseObject = this
							.jobseekerpagination(1, this.notificationsList);
					model.addAttribute("notificationsList", responseObject);
				} else {
					model.addAttribute("message",
							"Retrive Failer!..please contact Administrator.. ");
				}
			}

			EmployerBO allEmployer = new EmployerBO();
			// String paging = request.getParameter("page");
			model.addAttribute("employeeNameSearch", new EmployerBO());
			// = new ArrayList<EmployerBO>();

			if (null != paging) {

				page = Integer.parseInt(paging);

				@SuppressWarnings("unchecked")
				final ResponseObject<EmployerBO> reponseObject = this
						.pagination(page, this.employerNotificationList);
				model.addAttribute("registeredEmployer", reponseObject);
			} else {

				allEmployer = this.employerService.renewelRegisteredEmployer();
				this.employerNotificationList = allEmployer.getRegisteredList();
			}
			if (null != this.employerNotificationList
					&& this.employerNotificationList.size() > 0) {

				@SuppressWarnings("unchecked")
				final ResponseObject<EmployerBO> responseObject = this
						.pagination(1, this.employerNotificationList);
				model.addAttribute("registeredEmployer", responseObject);
			} else {
				model.addAttribute("message", "please create employer");
			}

		} catch (final MyJobKartException jb) {
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("Notifications_alerts failed:" + jb.getErrorCode()
						+ jb);
			}
			LOGGER.info("Notifications_alerts failed:" + jb.getErrorCode() + jb);
		}

		catch (final Exception e) {
			e.printStackTrace();

		}

		AdminController.LOGGER.exit();
		return "notifications_alerts";

	}

	@RequestMapping(value = "/admin_jobseekers_payment", method = RequestMethod.GET)
	public String retrieveJobseekersPayment(Model model,
			HttpServletRequest request) throws MyJobKartException {
		AdminController.LOGGER.entry();

		int page = 1;
		final String paging = request.getParameter("page");

		PaymentBO paymentBO = new PaymentBO();
		paymentBO = jobSeekerService.retriveJobseekerPayment();
		List<PaymentBO> registeredPayment = paymentBO.getJobseekerPaymentList();
		try {
			if (null != paging) {
				page = Integer.parseInt(paging);
				final ResponseObject<PaymentBO> reponseObject = this
						.paymentpagination(page, registeredPayment);
				model.addAttribute("registeredList", reponseObject);
			} else {

				if (0 != registeredPayment.size()) {
					final ResponseObject<PaymentBO> responseObject = this
							.paymentpagination(1, registeredPayment);
					model.addAttribute("registeredPayment", responseObject);
				} else {
					model.addAttribute("message",
							"Retrive Failer!..please contact Administrator.. ");
				}

			}
		} catch (final Exception e) {

		}

		AdminController.LOGGER.exit();
		return "admin_jobseekers_history";
	}

	private ResponseObject<PaymentBO> paymentpagination(int page,
			List<PaymentBO> registeredPayment2) {
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
		final List<PaymentBO> list = registeredPayment2;
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

	@RequestMapping(value = "/jobseeker_payment_details", method = RequestMethod.GET)
	public String paymentsDetails(Model model, HttpServletRequest request)
			throws MyJobKartException {
		AdminController.LOGGER.entry();
		final String id = request.getParameter("id");
		try {
			final long Id = Long.parseLong(id);
			PaymentBO paymentBO = new PaymentBO();
			paymentBO = jobSeekerService.retriveJobseekerPayment();
			List<PaymentBO> registeredPayment = paymentBO
					.getJobseekerPaymentList();
			for (final PaymentBO details : registeredPayment) {
				if (Id == details.getId()) {
					model.addAttribute("paymentDetail", details);
				}
			}

		} catch (final NullPointerException ne) {
			if (AdminController.LOGGER.isDebugEnabled()) {
				AdminController.LOGGER.debug(ne.getMessage() + ne);
			}
		}

		AdminController.LOGGER.exit();
		return "jobseeker_payment_details";
	}

	@RequestMapping(value = "/employeer_payment_details", method = RequestMethod.GET)
	public String paymentDetails(Model model, HttpServletRequest request) {
		AdminController.LOGGER.entry();
		final String id = request.getParameter("id");
		try {
			final long Id = Long.parseLong(id);

			for (final PaymentBO details : this.employerPaymentList) {
				if (Id == details.getId()) {
					model.addAttribute("paymentDetail", details);
				}
			}

		} catch (final NullPointerException ne) {
			if (AdminController.LOGGER.isDebugEnabled()) {
				AdminController.LOGGER.debug(ne.getMessage() + ne);
			}
		}

		AdminController.LOGGER.exit();
		return "employeer_payment_details";
	}

	@RequestMapping(value = "/admin_employer_payment", method = RequestMethod.GET)
	public String retrieveEmployerPayment(Model model,
			HttpServletRequest request) {
		AdminController.LOGGER.entry();

		HttpSession session = request.getSession();
		if (null == session.getAttribute("adminId")) {
			return "redirect:/admin_sign_in.html";
		}

		// model.addAttribute("jobSeekerNameSearch", new PaymentBO());
		int page = 1;
		final String paging = request.getParameter("page");
		try {
			if (null != paging) {

				page = Integer.parseInt(paging);

				@SuppressWarnings("unchecked")
				final ResponseObject<PaymentBO> reponseObject = this
						.paymentpagination(page, this.employerPaymentList);
				model.addAttribute("employerPaymentList", reponseObject);
			} else {

				PaymentBO paymentBO = this.employerService
						.employerPaymentHistory();
				this.employerPaymentList = paymentBO.getEnrolledList1();
				if (null != this.employerPaymentList
						&& this.employerPaymentList.size() > 0) {

					@SuppressWarnings("unchecked")
					final ResponseObject<PaymentBO> responseObject = this
							.paymentpagination(1, this.employerPaymentList);
					model.addAttribute("employerPaymentList", responseObject);
					model.addAttribute("adminEmpPay", "white");
				} else {
					model.addAttribute("message", "please create employer");
				}

			}

		} catch (final Exception e) {

		}

		AdminController.LOGGER.exit();
		return "admin_employer_history";
	}

	@RequestMapping(value = "/admin_jobseekers_history", method = RequestMethod.GET)
	public String retrieveAdminJobseekershistory(Model model,
			HttpServletRequest request) {
		AdminController.LOGGER.entry();
		model.addAttribute("jobSeekerNameSearch", new JobseekerBO());
		int page = 1;
		final String paging = request.getParameter("page");
		try {
			if (null != paging) {
				page = Integer.parseInt(paging);
				@SuppressWarnings("unchecked")
				final ResponseObject<JobseekerBO> reponseObject = this
						.jobseekerpagination(page, this.registeredList);
				model.addAttribute("registeredList", reponseObject);
			} else {
				this.jobseekerBO = new JobseekerBO();
				this.jobseekerBO = this.jobSeekerService
						.retriveRegisteredJobseeker();
				this.registeredList = this.jobseekerBO.getRegisteredList();
				if (0 != this.registeredList.size()) {
					final ResponseObject<JobseekerBO> responseObject = this
							.jobseekerpagination(1, this.registeredList);
					model.addAttribute("registeredList", responseObject);
				} else {
					model.addAttribute("message",
							"Retrive Failer!..please contact Administrator.. ");
				}
			}
		} catch (final Exception e) {
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("Admin jobseekers history failed:"
						+ e.getMessage());
			}
			LOGGER.info("Admin jobseekers history failed:" + e.getMessage());

		}

		AdminController.LOGGER.exit();
		return "admin_jobseekers_history";
	}

	@SuppressWarnings("unchecked")
	@RequestMapping(value = "employer_history", method = RequestMethod.GET)
	public String reteriveEmployerHistory(Model model,
			HttpServletRequest request) throws SerialException, SQLException {
		AdminController.LOGGER.entry();
		EmployerBO allEmployer = new EmployerBO();
		int page = 1;
		final String paging = request.getParameter("page");
		model.addAttribute("employeeNameSearch", new EmployerBO());
		try {

			if (null != paging) {

				page = Integer.parseInt(paging);

				final ResponseObject<EmployerBO> reponseObject = this
						.pagination(page, this.allEmployerList);

				model.addAttribute("registeredEmployer", reponseObject);

			} else {

				allEmployer = this.employerService.retrieveRegisteredEmployer();
				this.allEmployerList = allEmployer.getRegisteredList();
				// model.addAttribute("registeredEmployer", allEmployerList);
				if (null != this.allEmployerList
						&& this.allEmployerList.size() > 0) {

					final ResponseObject<EmployerBO> responseObject = this
							.pagination(1, this.allEmployerList);
					model.addAttribute("registeredEmployer", responseObject);
				} else {
					model.addAttribute("message", "please create employer");
				}
			}

		} catch (final MyJobKartException jb) {
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("Employer_history failed :" + jb.getErrorCode()
						+ jb);
			}
			LOGGER.info("Employer_history failed:" + jb.getErrorCode() + jb);
		}
		AdminController.LOGGER.exit();
		return "employer_history";

	}

	@RequestMapping(value = "/admin_jobseekers_savedjobs", method = RequestMethod.GET)
	public String retriveJobseekersSavedJobs(Model model,
			HttpServletRequest request) {
		AdminController.LOGGER.entry();
		HttpSession session = request.getSession();
		if (null == session.getAttribute("adminId")) {
			return "redirect:/admin_sign_in.html";
		}

		SavejobBO savejobBO;
		int page = 1;
		final String paging = request.getParameter("page");
		try {
			new HashSet<String>();
			if (null != paging) {

				page = Integer.parseInt(paging);

				final ResponseObject<SavejobBO> reponseObject = this
						.paginations(page, this.jobseekerSaveJobList);

				model.addAttribute("jobseekerSaveJobList", reponseObject);

			} else {

				savejobBO = new SavejobBO();
				request.getSession();
				savejobBO = this.jobSeekerService.retriveJobseekersSavedJobs();
				this.jobseekerSaveJobList = savejobBO.getAdminSaveJobList();

				if (null != this.jobseekerSaveJobList
						&& this.jobseekerSaveJobList.size() > 0) {

					final ResponseObject<SavejobBO> responseObject = this
							.paginations(1, this.jobseekerSaveJobList);
					model.addAttribute("jobseekerSaveJobList", responseObject);
					model.addAttribute("savedJobsColor", "white");
				} else {
					model.addAttribute("Infomessage", "Record not found");
				}
			}

		} catch (final Exception e) {
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("admin jobseekers savedjobs failed:"
						+ e.getMessage() + e);
			}
			LOGGER.info("admin jobseekers savedjobs failed:" + e.getMessage()
					+ e);
		}
		AdminController.LOGGER.exit();
		return "admin_jobseekers_history";
	}

	@SuppressWarnings("rawtypes")
	private ResponseObject paginations(int page,
			List<SavejobBO> jobseekerSaveJobList) {
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
		final ResponseObject<SavejobBO> ro = new ResponseObject<SavejobBO>();
		final List<SavejobBO> list = jobseekerSaveJobList;
		if (null != list) {
			noOfRecords = list.size();
		}
		noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
		ro.setCurrentPage(page);
		ro.setRecordsPerPage(recordsPerPage);
		ro.setTotalPages(noOfPages);
		int count = 0;
		final List<SavejobBO> pageList = new ArrayList<SavejobBO>();
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

	@RequestMapping(value = "/admin_jobseeker_savejobdetails", method = RequestMethod.GET)
	public String jobseekerSaveJobDetails(Model model,
			HttpServletRequest request) throws MyJobKartException {
		AdminController.LOGGER.entry();
		try {

			SavejobBO saveJobseeker;
			final String id = request.getParameter("id");
			final long saveJobId = Long.parseLong(id);
			request.getSession();
			saveJobseeker = this.jobSeekerService.retriveJobseekersSavedJobs();
			this.jobseekerSaveJobList = saveJobseeker.getAdminSaveJobList();
			for (final SavejobBO savejobBO : this.jobseekerSaveJobList) {
				if (savejobBO.getId() == saveJobId) {
					saveJobseeker = savejobBO;
					model.addAttribute("savejobJobseeker", saveJobseeker);
				}
			}
		} catch (final NullPointerException ne) {
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("admin jobseeker savejobdetails failed:"
						+ ne.getMessage() + ne);
			}
			LOGGER.info("admin jobseeker savejobdetails failed:"
					+ ne.getMessage() + ne);
		}
		AdminController.LOGGER.exit();
		return "admin_jobseeker_savejobdetails";
	}

	/*
	 * @RequestMapping(value = "/admin_jobseekers_appliedjobs", method =
	 * RequestMethod.GET) public String retriveJobseekersAppliedJobs(Model
	 * model, HttpServletRequest request) { AdminController.LOGGER.entry();
	 * HttpSession session = request.getSession(); if (null ==
	 * session.getAttribute("adminId")) { return "redirect:/admin_sign_in.html";
	 * }
	 * 
	 * AppliedJobBO appliedJobBO; int page = 1; final String paging =
	 * request.getParameter("page"); try { new HashSet<String>(); if (null !=
	 * paging) {
	 * 
	 * page = Integer.parseInt(paging);
	 * 
	 * final ResponseObject<AppliedJobBO> reponseObject = this
	 * .appliedJobPaginations(page, this.appliedJobList);
	 * 
	 * model.addAttribute("appliedJobList", reponseObject);
	 * 
	 * } else {
	 * 
	 * appliedJobBO = new AppliedJobBO(); request.getSession(); appliedJobBO =
	 * this.jobSeekerService .retriveJobseekersAppliedJobs();
	 * this.appliedJobList = appliedJobBO.getAdminAppliedJobList();
	 * 
	 * if (null != this.appliedJobList && this.appliedJobList.size() > 0) {
	 * 
	 * final ResponseObject<AppliedJobBO> responseObject = this
	 * .appliedJobPaginations(1, this.appliedJobList);
	 * model.addAttribute("appliedJobList", responseObject);
	 * model.addAttribute("appliedJobsColor", "white");
	 * 
	 * } else { model.addAttribute("Infomessage", "Record not found"); } }
	 * 
	 * } catch (final Exception e) { if
	 * (AdminController.LOGGER.isDebugEnabled()) {
	 * AdminController.LOGGER.debug(e.getMessage() + e); } if
	 * (LOGGER.isDebugEnabled()) {
	 * LOGGER.debug("admin jobseeker applied jobs failed:" + e.getMessage() +
	 * e); } LOGGER.info("admin jobseeker savejobdetails failed:" +
	 * e.getMessage() + e); } AdminController.LOGGER.exit(); return
	 * "admin_jobseekers_history"; }
	 */

	@SuppressWarnings("rawtypes")
	private ResponseObject appliedJobPaginations(int page,
			List<AppliedJobBO> appliedJobList) {
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
		final ResponseObject<AppliedJobBO> ro = new ResponseObject<AppliedJobBO>();
		final List<AppliedJobBO> list = appliedJobList;
		if (null != list) {
			noOfRecords = list.size();
		}
		noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
		ro.setCurrentPage(page);
		ro.setRecordsPerPage(recordsPerPage);
		ro.setTotalPages(noOfPages);
		int count = 0;
		final List<AppliedJobBO> pageList = new ArrayList<AppliedJobBO>();
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

	@RequestMapping(value = "/admin_employer_history", method = RequestMethod.GET)
	public String adminEmployerHistory(Model model, HttpServletRequest request) {
		AdminController.LOGGER.entry();

		HttpSession session = request.getSession();
		if (null == session.getAttribute("adminId")) {
			return "redirect:/admin_sign_in.html";
		}

		try {
			long sno = 0;
			List<JobPostBO> jobpostCompanyList = new ArrayList<JobPostBO>();
			JobPostBO countCompany = null;
			String value = request.getParameter("val");
			List<JobPostBO> employerProfileList = this.employerService
					.viewRecruiters(value);
			if (null != employerProfileList & employerProfileList.size() > 0) {
				for (JobPostBO jobCompany : employerProfileList) {
					countCompany = new JobPostBO();
					sno = sno + 1;
					countCompany.setsNo(sno);
					countCompany.setCompaniesId(jobCompany.getCompanyId());
					countCompany.setCompanyName(jobCompany.getCompanyName());
					jobpostCompanyList.add(countCompany);
					model.addAttribute("companyList", jobpostCompanyList);

				}
			} else {
				model.addAttribute("errorMessage", "No company found");
			}

		} catch (final Exception e) {
			e.printStackTrace();
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("admin employer history failed:" + e.getMessage()
						+ e);
			}
			LOGGER.info("admin employer history failed:" + e.getMessage() + e);
		}
		AdminController.LOGGER.exit();
		model.addAttribute("companyNameSearch", new EntityBO());
		return "admin_employer_history";

	}

	@RequestMapping(value = "/short_listed_candiadte_history", method = RequestMethod.GET)
	public String shortListedCandidate(Model model, HttpServletRequest request) {
		AdminController.LOGGER.entry();
		HttpSession session = request.getSession();
		if (null == session.getAttribute("adminId")) {
			return "redirect:/admin_sign_in.html";
		}

		int page = 1;
		final String paging = request.getParameter("page");
		ShortListCandidate candidate = new ShortListCandidate();
		try {
			if (null != paging) {
				page = Integer.parseInt(paging);
				final ResponseObject<ShortListCandidate> reponseObject = this
						.paginations2(page, this.shortListCandidates);
				model.addAttribute("shortListCandidates", reponseObject);

			} else {
				candidate = this.employerService.shortListCandidates();
				model.addAttribute("shortListHistory", "white");
				this.shortListCandidates = candidate.getCandidateList();
				if (0 != this.shortListCandidates.size()) {
					final ResponseObject<ShortListCandidate> responseObject = this
							.paginations2(1, this.shortListCandidates);
					model.addAttribute("shortListCandidates", responseObject);

				} else {
					model.addAttribute("message",
							"You have not select any resume..please find a resume ");
				}
			}

		} catch (final Exception e) {
			e.printStackTrace();
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("short listed candiadte history failed:"
						+ e.getMessage() + e);
			}
			LOGGER.info("short listed candiadte history failed:"
					+ e.getMessage() + e);

		}
		AdminController.LOGGER.exit();
		return "admin_employer_history";

	}

	private ResponseObject<ShortListCandidate> paginations2(int page,
			List<ShortListCandidate> shortListCandidates2) {
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
		final ResponseObject<ShortListCandidate> ro = new ResponseObject<ShortListCandidate>();
		final List<ShortListCandidate> list = shortListCandidates2;
		if (null != list) {
			noOfRecords = list.size();
		}
		noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
		ro.setCurrentPage(page);
		ro.setRecordsPerPage(recordsPerPage);
		ro.setTotalPages(noOfPages);
		int count = 0;

		final List<ShortListCandidate> pageList = new ArrayList<ShortListCandidate>();
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

	@RequestMapping(value = "/short_list_candidate", method = RequestMethod.GET)
	public String viewShortListed(Model model, HttpServletRequest request)
			throws SerialException, MyJobKartException, SQLException {
		AdminController.LOGGER.entry();
		HttpSession session = request.getSession();
		try {

			JobseekerProfileBO profileBO = new JobseekerProfileBO();
			ShortListCandidate candidate = new ShortListCandidate();

			if (null == session.getAttribute("loginId")) {
				model.addAttribute("userType", "admin");
			}

			String id = request.getParameter("id");
			String profileId = request.getParameter("prfId");
			Long jobseekerProfileId = Long.parseLong(profileId);
			profileBO.setId(jobseekerProfileId);
			JobseekerProfileBO jobeduProfile;
			jobeduProfile = this.jobSeekerService.retriveJobseeker(profileBO);
			List<JobseekerProfileBO> professionalList = jobeduProfile
					.getJobprofessionalList();
			List<JobseekerProfileBO> educationList = jobeduProfile
					.getJobEductionProfileList();
			model.addAttribute("educationDetails", educationList);
			model.addAttribute("experienceDetails", professionalList);
			model.addAttribute("Infomessage", "May be the FRESHER candidate!");
			final long shotlistId = Long.parseLong(id);
			candidate = this.employerService.shortListCandidates();
			for (final ShortListCandidate employerBO : this.shortListCandidates) {
				if (employerBO.getShortlistId() == shotlistId) {
					model.addAttribute("candidate", employerBO);
				}
			}
		} catch (final NullPointerException ne) {
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("short list candidate failed:" + ne.getMessage()
						+ ne);
			}
			LOGGER.info(" short list candidate failed:" + ne.getMessage() + ne);
		}
		AdminController.LOGGER.exit();
		if (null != session.getAttribute("adminId")) {
			return "admin_short_list_candidate";
		}
		return "short_list_candidate";
	}

	@RequestMapping(value = "/short_list_candidate_delete", method = RequestMethod.GET)
	public String shortListDeleteProfile(Model model, HttpServletRequest request) {
		AdminController.LOGGER.entry();
		HttpSession session = request.getSession();
		try {
			long loginId = (long) session.getAttribute("loginId");
			long regid = (Long) session.getAttribute("employerRegisterId");

			String id = request.getParameter("id");
			long deletedId = Long.parseLong(id);
			ShortListCandidate shortListCandidateBO = new ShortListCandidate();
			shortListCandidateBO.setShortlistId(deletedId);
			shortListCandidateBO.setModifiedBy(regid);
			shortListCandidateBO.setDeletedBy(regid);
			shortListCandidateBO.setIsDeleted(false);
			shortListCandidateBO.setDeletedDate(new Date());

			shortListCandidateBO = employerService
					.shortListDeleteProfile(shortListCandidateBO);
			if (null != shortListCandidateBO.getResponse()) {
				model.addAttribute("sucessmessage",
						shortListCandidateBO.getResponse());

			} else {
				model.addAttribute("infomessage", "delete is failed");
			}

		} catch (Exception je) {
			je.printStackTrace();
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("short list candidate delete failed:"
						+ je.getMessage());
			}
			LOGGER.info(" short list candidate delete failed:"
					+ je.getMessage());
		}
		AdminController.LOGGER.exit();
		return "redirect:/short_list_candidate_view.html";
	}

	@RequestMapping(value = "/short_list_candidate_view", method = RequestMethod.GET)
	public String shortListCandidates(Model model, HttpServletRequest request)
			throws MyJobKartException {
		AdminController.LOGGER.entry();
		HttpSession session = request.getSession();
		model.addAttribute("shortlistSearch", new ShortListCandidate());
		if (null == session.getAttribute("loginId")
				&& null == session.getAttribute("adminId")) {
			return "redirect:/employer_sign_in.html";
		}
		long employerId = 0;
		String infomessage = request.getParameter("infomessage");
		String sucessmessage = request.getParameter("sucessmessage");
		if (null != sucessmessage) {
			model.addAttribute("sucessmessage", sucessmessage);
		} else {
			model.addAttribute("infomessage", infomessage);
		}
		ShortListCandidate shortList = new ShortListCandidate();

		if (null != session.getAttribute("loginId")) {
			employerId = (Long) session.getAttribute("loginId");
			shortList.setCreatedBy(employerId);
		} else if (null != session.getAttribute("adminId")
				&& null != request.getParameter("email")) {
			EmployerLoginBO employerLogin = new EmployerLoginBO();
			String employerEmail = request.getParameter("email");
			employerLogin.setEmailAddress(employerEmail);
			employerLogin = this.employerService.authendicate(employerLogin);
			shortList.setCreatedBy(employerLogin.getId());
		}

		shortList = this.employerService.shortListCandidatesView(shortList);
		this.shortListCandidates = shortList.getCandidateList();

		try {

			if (null != shortList.getCandidateList()) {

				model.addAttribute("shortListCandidate",
						this.shortListCandidates);
			} else {
				model.addAttribute("Infomessage",
						"No Shortlisted Records Found");
			}
		} catch (final Exception e) {
			e.printStackTrace();
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("short list candidate view failed:"
						+ e.getMessage() + e);
			}
			LOGGER.info(" short list candidate view failed:" + e.getMessage()
					+ e);
		}

		if (null != session.getAttribute("adminId")) {
			return "admin_short_list_candidate_view";
		}

		AdminController.LOGGER.exit();
		return "short_list_candidate_view";
	}

	@RequestMapping(value = "/shortlistSearch", method = RequestMethod.POST)
	public String shortlistSearch(
			@Valid @ModelAttribute("shortlistSearch") ShortListCandidate search,
			BindingResult result, HttpServletRequest request, Model model) {
		HttpSession session = request.getSession();
		try {
			ShortListCandidate resume = null;
			List<ShortListCandidate> shortlistResume = new ArrayList<ShortListCandidate>();
			if (null != shortListCandidates) {
				for (ShortListCandidate profileBO : shortListCandidates) {
					if (profileBO.getJobTitle().toLowerCase()
							.contains(search.getSearchElement().toLowerCase())) {
						resume = profileBO;
						shortlistResume.add(resume);
					}
				}
				if (shortlistResume.isEmpty()) {
					model.addAttribute("errormessage", "No record found..");
				}

				model.addAttribute("shortListCandidate", shortlistResume);

				if (null != session.getAttribute("adminId")) {
					model.addAttribute("shortlistSearch",
							new ShortListCandidate());
					return "admin_short_list_candidate_view";
				}

				if (null != session.getAttribute("loginId")) {
					return "short_list_candidate_view";
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "short_list_candidate_view";
	}

	@RequestMapping(value = "/saved_list_candidate", method = RequestMethod.GET)
	public String savedShortListed(Model model, HttpServletRequest request)
			throws SerialException, MyJobKartException, SQLException {
		AdminController.LOGGER.entry();
		try {
			final String id = request.getParameter("id");
			String profileId = request.getParameter("prfid");
			long jobseekerId = Long.parseLong(id);
			JobseekerProfileBO profileBO = new JobseekerProfileBO();

			List<JobseekerProfileBO> professionalList = new ArrayList<JobseekerProfileBO>();
			List<JobseekerProfileBO> educationList = new ArrayList<JobseekerProfileBO>();
			JobseekerProfileBO jobeduProfile;

			// Retrieved jobseekerProfileList by profileID
			long jobseekerProfileId = Long.parseLong(profileId);
			profileBO.setId(jobseekerProfileId);
			jobeduProfile = this.jobSeekerService.retriveJobseeker(profileBO);
			// educational List
			educationList = jobeduProfile.getJobEductionProfileList();
			// professional List
			professionalList = jobeduProfile.getJobprofessionalList();
			model.addAttribute("educationList", educationList);
			model.addAttribute("professionalList", professionalList);
			model.addAttribute("Infomessage", "May be the FRESHER candidate!");
			for (SaveCandidateBO reteriveprofile : this.retrieveResumeList) {
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
				LOGGER.debug("saved list candidate  failed:" + ne.getMessage()
						+ ne);
			}
			LOGGER.info(" saved list candidate  failed:" + ne.getMessage() + ne);
		}
		AdminController.LOGGER.exit();
		return "saved_list_candidate";
	}

	@RequestMapping(value = "/admin_applied_jobdetails", method = RequestMethod.GET)
	public String jobseekerAppliedobDetails(Model model,
			HttpServletRequest request) {
		AdminController.LOGGER.entry();
		try {
			AppliedJobBO appliedJobseeker;
			final String id = request.getParameter("id");
			if (id != null) {
				final long appliedJobId = Long.parseLong(id);
				for (final AppliedJobBO appliedJobBO : this.appliedJobList) {
					if (appliedJobBO.getId() == appliedJobId) {
						appliedJobseeker = appliedJobBO;
						model.addAttribute("appliedJobseeker", appliedJobseeker);
					}
				}
			}
		} catch (final NullPointerException ne) {
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("admin applied jobdetails  failed:"
						+ ne.getMessage() + ne);
			}
			LOGGER.info(" admin applied jobdetails  failed:" + ne.getMessage()
					+ ne);
		}
		AdminController.LOGGER.exit();
		return "admin_applied_jobdetails";

	}

	@RequestMapping(value = "/employer_notification_alert", method = RequestMethod.GET)
	public String employerNotifications(Model model, HttpServletRequest request)
			throws MyJobKartException {
		AdminController.LOGGER.entry();
		HttpSession session = request.getSession();
		if (null == session.getAttribute("adminId")) {
			return "redirect:/admin_sign_in.html";
		}
		EmployerBO allEmployer = new EmployerBO();
		int page = 1;
		final String paging = request.getParameter("page");
		try {
			if (null != paging) {
				page = Integer.parseInt(paging);
				final ResponseObject<EmployerBO> reponseObject = this
						.pagination4(page, this.employerNotificationList);
				model.addAttribute("employerNotificationList", reponseObject);
			} else {

				allEmployer = this.employerService.renewelRegisteredEmployer();
				this.employerNotificationList = allEmployer.getRegisteredList();
				model.addAttribute("empNotiAlters", "white");
				if (this.employerNotificationList.size() > 0) {
					final ResponseObject<EmployerBO> responseObject = this
							.pagination4(1, this.employerNotificationList);
					model.addAttribute("employerNotificationList",
							responseObject);
				} else {
					model.addAttribute("message", "Retrieval Failure");
				}
			}
		} catch (final Exception exception) {
			exception.printStackTrace();
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("employer notification alert  failed:"
						+ exception.getMessage());
			}
			LOGGER.info(" employer notification alert  failed:"
					+ exception.getMessage());
		}
		AdminController.LOGGER.exit();
		return "notifications_alerts";

	}

	private ResponseObject<EmployerBO> pagination4(int page,
			List<EmployerBO> employerNotificationList2) {
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
		final ResponseObject<EmployerBO> ro = new ResponseObject<EmployerBO>();
		final List<EmployerBO> list = employerNotificationList2;
		if (null != list) {
			noOfRecords = list.size();
		}
		noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
		ro.setCurrentPage(page);
		ro.setRecordsPerPage(recordsPerPage);
		ro.setTotalPages(noOfPages);
		int count = 0;

		final List<EmployerBO> pageList = new ArrayList<EmployerBO>();
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

	@RequestMapping(value = "/renewal_bannarList", method = RequestMethod.GET)
	public String renewalBannerList(Model model, HttpServletRequest request) {
		AdminController.LOGGER.entry();
		String message = request.getParameter("message");
		model.addAttribute("message", message);

		this.allBannerList = new ArrayList<BannerPostBO>();
		int page = 1;
		final String paging = request.getParameter("page");
		model.addAttribute("renewalbannerposting", new EmployerBO());

		try {

			if (null != paging) {

				page = Integer.parseInt(paging);

				@SuppressWarnings("unchecked")
				final ResponseObject<BannerPostBO> reponseObject = this
						.bannerpagination(page, this.allBannerList);

				model.addAttribute("bannerPostingList", reponseObject);

			} else {

				this.allBannerList = this.employerService.renewelBannarList();

			}
			if (this.allBannerList.size() > 0) {

				@SuppressWarnings("unchecked")
				final ResponseObject<BannerPostBO> responseObject = this
						.bannerpagination(1, this.allBannerList);
				model.addAttribute("bannerPostingList", responseObject);
			} else {
				model.addAttribute("message", "please post the banner");
			}

		} catch (final Exception jb) {
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("renewal bannarList  failed:" + jb.getMessage());
			}
			LOGGER.info(" renewal bannarListt  failed:" + jb.getMessage());
		}
		return "renewal_bannarList";

	}

	@SuppressWarnings("rawtypes")
	private ResponseObject bannerpagination(int page,
			List<BannerPostBO> dataLsit) {
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
		final ResponseObject<BannerPostBO> ro = new ResponseObject<BannerPostBO>();
		final List<BannerPostBO> list = dataLsit;
		if (null != list) {
			noOfRecords = list.size();
		}
		noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
		ro.setCurrentPage(page);
		ro.setRecordsPerPage(recordsPerPage);
		ro.setTotalPages(noOfPages);
		int count = 0;
		final List<BannerPostBO> pageList = new ArrayList<BannerPostBO>();
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

	/**
	 * Start to Product Developments Product Paginations
	 * 
	 * @param page
	 * @param productBO
	 * @return
	 */

	private ResponseObject<ProductBO> paginations5(int page,
			List<ProductBO> productBO) {
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
		final ResponseObject<ProductBO> ro = new ResponseObject<ProductBO>();
		final List<ProductBO> list = productBO;
		if (null != list) {
			noOfRecords = list.size();
		}
		noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
		ro.setCurrentPage(page);
		ro.setRecordsPerPage(recordsPerPage);
		ro.setTotalPages(noOfPages);
		int count = 0;

		final List<ProductBO> pageList = new ArrayList<ProductBO>();
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

	@RequestMapping(value = "/create_product", method = RequestMethod.GET)
	public String createProduct(Model model, HttpServletRequest request)
			throws MyJobKartException {
		AdminController.LOGGER.entry();
		HttpSession session = request.getSession();
		if (null == session.getAttribute("adminId")) {
			return "redirect:/admin_sign_in.html";
		}

		ProductBO productBO = new ProductBO();
		model.addAttribute("product", productBO);
		AdminController.LOGGER.exit();
		return "create_product";
	}

	@RequestMapping(value = "/create_product", method = RequestMethod.POST)
	public String createProduct(Model model,
			@Valid @ModelAttribute("product") ProductBO productBO,
			BindingResult result, Model modle, HttpServletRequest request)
			throws MyJobKartException {

		try {
			HttpSession session = request.getSession();
			if (null == session.getAttribute("adminId")) {
				return "redirect:/admin_sign_in.html";
			}
			if (result.hasErrors()) {
				return "create_product";
			}

			boolean productStatus = this.adminService
					.productAuthentication(productBO);
			if (productStatus) {
				model.addAttribute("infomessage", "Product Already Exist");
				return "create_product";
			}

			long adminId = (Long) session.getAttribute("adminId");
			productBO.setCreatedBy(adminId);
			productBO.setIsActive(false);
			productBO.setIsDeleted(true);
			productBO = adminService.createProduct(productBO);

			if (null != productBO.getErrorCode()) {
				model.addAttribute("message", productBO.getErrorMessage());

			} else {
				model.addAttribute("message", "Product Create is Sucessfully");
				return "redirect:/viewProduct";
			}

		} catch (final MyJobKartException jb) {
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("create product failed:" + jb.getErrorCode() + jb);
			}
			LOGGER.info("create product failed:" + jb.getErrorCode() + jb);
		}

		return "redirect:/viewProduct";
	}

	@RequestMapping(value = "/viewProduct", method = RequestMethod.GET)
	public String viewProduct(Model model, HttpServletRequest request)
			throws MyJobKartException {
		AdminController.LOGGER.entry();
		HttpSession session = request.getSession();
		if (null == session.getAttribute("adminId")) {
			return "redirect:/admin_sign_in.html";
		}

		String message = request.getParameter("message");
		model.addAttribute("message", message);
		ProductBO product = new ProductBO();
		int page = 1;
		final String paging = request.getParameter("page");
		try {
			product = this.adminService.viewProduct();
			this.productList = product.getRegisteredList();

			if (null != this.productList || this.productList.size() > 0) {

				model.addAttribute("productList", this.productList);

			} else {
				model.addAttribute("message", "No Product Found");
			}

		} catch (final Exception exception) {
			exception.printStackTrace();
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("viewProductfailed:" + exception.getMessage());
			}
			LOGGER.info("viewProduct failed:" + exception.getMessage());
		}
		AdminController.LOGGER.exit();
		return "viewProduct";

	}

	/**
	 * Product activation
	 * 
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/product_activation", method = RequestMethod.GET)
	public String productActivation(Model model, HttpServletRequest request) {
		try {
			String reqstatus = request.getParameter("status");
			String array[] = reqstatus.split(",");
			long productId = Long.parseLong(array[1]);
			String status = array[0];
			ProductBO productBO = new ProductBO();
			productBO.setProductId(productId);
			if (status.equals("Active")) {
				productBO.setIsActive(false);

			} else {
				productBO.setIsActive(true);
			}
			boolean result = adminService.productActivation(productBO);
			if (result) {
				model.addAttribute("message",
						"Product Activated is sucessfully");
				return "redirect:/viewProduct";
			} else {
				model.addAttribute("message", "Product is not Activated");
				return "redirect:/viewProduct";
			}
		} catch (Exception e) {
			e.printStackTrace();
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("product activation failed:" + e.getMessage());
			}
			LOGGER.info("product activation failed:" + e.getMessage());
		}
		return "redirect:/viewProduct";
	}

	@RequestMapping(value = "/productUpdate", method = RequestMethod.GET)
	public String productUpdate(Model model, HttpServletRequest request) {

		try {
			HttpSession session = request.getSession();
			if (null == session.getAttribute("adminId")) {
				return "redirect:/admin_sign_in.html";
			}

			String reqId = request.getParameter("id");
			long productId = Long.parseLong(reqId);

			if (0 != productList.size()) {
				for (ProductBO productBO1 : productList) {

					if (productId == productBO1.getProductId()) {
						updateProduct = productBO1;

					}

				}

			}
			model.addAttribute("product", updateProduct);
		} catch (Exception e) {
			e.printStackTrace();
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("product Update failed:" + e.getMessage());
			}
			LOGGER.info("product Update failed:" + e.getMessage());
		}
		return "productUpdate";
	}

	@RequestMapping(value = "/productUpdate", method = RequestMethod.POST)
	public String productUpdate(
			@Validated @ModelAttribute("product") ProductBO productBO,
			BindingResult result, HttpServletRequest request, Model model) {

		try {
			HttpSession session = request.getSession();
			if (null == session.getAttribute("adminId")) {
				return "redirect:/admin_sign_in.html";
			}

			if (result.hasErrors()) {
				return "productUpdate";
			}
			productBO.setProductId(updateProduct.getProductId());
			productBO.setIsActive(updateProduct.getIsActive());
			productBO.setIsDeleted(updateProduct.getIsDeleted());
			productBO = adminService.productUpdate(productBO);
			if (null != productBO.getErrorCode()) {
				model.addAttribute("message", productBO.getErrorMessage());
				return "redirect:/viewProduct";

			} else {
				model.addAttribute("message", "Product is Update Sucessfully");
				return "redirect:/viewProduct";
			}
		} catch (Exception e) {
			e.printStackTrace();
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("product Update failed:" + e.getMessage());
			}
			LOGGER.info("product Update failed:" + e.getMessage());
		}

		return "redirect:/viewProduct";
	}

	/**
	 * Delete Product
	 * 
	 * @param model
	 * @param request
	 * @return
	 * @throws MyJobKartException
	 */
	@RequestMapping(value = "/deleteProduct", method = RequestMethod.GET)
	String deleteProduct(Model model, HttpServletRequest request)
			throws MyJobKartException {
		AdminController.LOGGER.entry();
		try {
			HttpSession session = request.getSession();
			long adminId = (Long) session.getAttribute("adminId");
			String requestId = request.getParameter("id");
			long productId = Long.parseLong(requestId);
			ProductBO productBO = new ProductBO();
			productBO.setProductId(productId);
			productBO.setModifiedBy(adminId);
			productBO.setModified(new Date());
			productBO.setIsDeleted(false);
			productBO = adminService.deleteProduct(productBO);
			if (null != productBO.getErrorCode()) {
				model.addAttribute("message", productBO.getErrorMessage());
				return "redirect:/viewProduct";
			} else {
				model.addAttribute("message", "Product Deleted is Sucessfully");
				return "redirect:/viewProduct";
			}
		} catch (MyJobKartException je) {
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("delete product failed:" + je.getErrorCode(), je);
			}
			LOGGER.info("delete product failed:" + je.getErrorCode(), je);
		}
		AdminController.LOGGER.exit();
		return "redirect:/viewProduct";

	}

	/**
	 * Active Product List
	 * 
	 * @param model
	 * @param request
	 * @return
	 * @throws MyJobKartException
	 */

	@RequestMapping(value = "/product_active", method = RequestMethod.GET)
	public String activeProcuct(Model model, HttpServletRequest request)
			throws MyJobKartException {
		AdminController.LOGGER.entry();
		HttpSession session = request.getSession();
		if (null == session.getAttribute("adminId")) {
			return "redirect:/admin_sign_in.html";
		}
		List<ProductBO> listProduct = new ArrayList<ProductBO>();
		int page = 1;
		final String paging = request.getParameter("page");

		try {
			if (null != paging) {
				page = Integer.parseInt(paging);
				final List<ProductBO> activeLists = (List<ProductBO>) session
						.getAttribute("activeList");

				final ResponseObject<ProductBO> reponseObject = this
						.paginations5(page, activeLists);

				model.addAttribute("listProduct", reponseObject);
			} else {

				for (ProductBO product : this.productList) {
					if (product.getIsActive()) {
						listProduct.add(product);

					}

				}
				if (null == listProduct && listProduct.size() == 0) {

					model.addAttribute("message", "Active Product Not Found");

				}

				session.setAttribute("activeList", listProduct);

				final ResponseObject<ProductBO> responseObject = this
						.paginations5(1, listProduct);
				model.addAttribute("listProduct", responseObject);

			}
		} catch (final Exception exception) {
			exception.printStackTrace();
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("product active failed:" + exception.getMessage());
			}
			LOGGER.info("product active failed:" + exception.getMessage());
		}
		AdminController.LOGGER.exit();
		return "viewProduct";

	}

	@RequestMapping(value = "/deactive_product", method = RequestMethod.GET)
	public String deactiveProduct(Model model, HttpServletRequest request)
			throws MyJobKartException {
		AdminController.LOGGER.entry();
		HttpSession session = request.getSession();
		if (null == session.getAttribute("adminId")) {
			return "redirect:/admin_sign_in.html";
		}

		List<ProductBO> deactiveProductList = new ArrayList<ProductBO>();

		int page = 1;
		final String paging = request.getParameter("page");

		try {
			if (null != paging) {
				page = Integer.parseInt(paging);
				final List<ProductBO> deactiveLists = (List<ProductBO>) session
						.getAttribute("deactiveLists");

				final ResponseObject<ProductBO> reponseObject = this
						.paginations5(page, deactiveLists);

				model.addAttribute("deactiveProductList", reponseObject);
			} else {

				for (ProductBO product : this.productList) {

					if (false == product.getIsActive()) {

						// ProductBO productBO = new ProductBO();
						// productBO=product;
						deactiveProductList.add(product);
					}

				}
				if (null == deactiveProductList
						|| deactiveProductList.size() == 0) {

					model.addAttribute("message", "De-Active Not Record Found ");

				}

				session.setAttribute("deactiveLists", deactiveProductList);
				final ResponseObject<ProductBO> responseObject = this
						.paginations5(1, deactiveProductList);
				model.addAttribute("deactiveProductList", responseObject);
			}

		} catch (final Exception exception) {
			exception.printStackTrace();
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("deactive product failed:"
						+ exception.getMessage());
			}
			LOGGER.info("deactive product failed:" + exception.getMessage());
		}
		AdminController.LOGGER.exit();
		return "viewProduct";

	}

	@RequestMapping(value = "/view_contact", method = RequestMethod.GET)
	public String viewContact(Model model, HttpServletRequest request) {
		String infomessage = request.getParameter("Infomessage");
		model.addAttribute("Infomessage", infomessage);
		model.addAttribute("contactSearch", new ContactBO());
		int page = 1;
		final String paging = request.getParameter("page");

		try {

			List<ContactBO> contactList = adminService
					.retrieveUserConatctDetails(new ContactBO());

			if (null != contactList || contactList.size() > 0) {
				model.addAttribute("contactList", contactList);
			} else {

				model.addAttribute("Infomessage", "Contact List Not Found");
			}

		} catch (final Exception exception) {
			exception.printStackTrace();
		}
		return "view_contact";
	}

	@RequestMapping(value = "/contactSearch", method = RequestMethod.POST)
	public String contactSearch(
			@Valid @ModelAttribute("contactSearch") ContactBO search,
			BindingResult result, HttpServletRequest request, Model model) {
		String infomessage = request.getParameter("Infomessage");
		model.addAttribute("Infomessage", infomessage);
		int page = 1;
		final String paging = request.getParameter("page");
		ContactBO contactBO = new ContactBO();
		if (null != search.getSearchElement()) {
			contactBO.setSearchElement(search.getSearchElement());
		}

		try {

			List<ContactBO> contactList = adminService
					.retrieveUserConatctDetails(contactBO);

			if (null != contactList || contactList.size() > 0) {
				model.addAttribute("contactList", contactList);
			} else {

				model.addAttribute("Infomessage", "Contact List Not Found");
			}

		} catch (final Exception exception) {
			exception.printStackTrace();
		}
		return "view_contact";
	}

	@RequestMapping(value = "/view_contact_details", method = RequestMethod.GET)
	public String viewContactDetail(Model model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		if (null == session.getAttribute("adminId")) {
			return "redirect:/admin_sign_in.html";
		}
		try {
			String cid = request.getParameter("id");
			long id = Long.parseLong(cid);

			List<ContactBO> contactList = adminService
					.retrieveUserConatctDetails(new ContactBO());
			for (ContactBO viewcontact : contactList)
				if (id == viewcontact.getId()) {
					model.addAttribute("viewcontactdetail", viewcontact);
				}
		} catch (final Exception exception) {
			exception.printStackTrace();
		}
		return "view_contact_details";

	}

	@RequestMapping(value = "/view_contact_delete", method = RequestMethod.GET)
	public String contactDelete(Model model, HttpServletRequest request) {
		String infomessage = request.getParameter("Infomessage");
		model.addAttribute("Infomessage", infomessage);
		HttpSession session = request.getSession();
		if (null == session.getAttribute("adminId")) {
			return "redirect:/admin_sign_in.html";
		}
		try {
			if (null != request.getParameter("id")) {
				String id = request.getParameter("id");
				long deleteId = Long.parseLong(id);
				ContactBO contactBO = new ContactBO();
				contactBO.setId(deleteId);
				contactBO.setIsDelete(true);
				contactBO = adminService.deleteContact(contactBO);
				if (null != contactBO) {
					model.addAttribute("Infomessage",
							"Contact Deleted Successfully..");
					return "redirect:/view_contact.html";
				}
			}
		} catch (final Exception exception) {
			exception.printStackTrace();
		}
		return "redirect:/view_contact.html";

	}

	@RequestMapping(value = "/view_feedback", method = RequestMethod.GET)
	public String viewFeedback(Model model, HttpServletRequest request) {
		String infomessage = request.getParameter("Infomessage");
		model.addAttribute("Infomessage", infomessage);
		model.addAttribute("feedbackSearch", new FeedbackBO());
		try {
			feedbackList = adminService
					.retriveFeedbackDetails(new FeedbackBO());
			if (null != this.feedbackList || this.feedbackList.size() > 0) {
				model.addAttribute("feedbackList", this.feedbackList);
			} else {
				model.addAttribute("Infomessage", "No FeedBack List Found...");

			}

		} catch (final Exception exception) {
			exception.printStackTrace();
		}
		return "view_feedback";
	}

	@RequestMapping(value = "/feedbackSearch", method = RequestMethod.POST)
	public String feedbackSearch(
			@Valid @ModelAttribute("feedbackSearch") FeedbackBO search,
			BindingResult result, HttpServletRequest request, Model model) {
		String infomessage = request.getParameter("Infomessage");
		model.addAttribute("Infomessage", infomessage);
		int page = 1;
		final String paging = request.getParameter("page");
		FeedbackBO feedbackBO = new FeedbackBO();
		if (null != search.getSearchElement()) {
			feedbackBO.setSearchElement(search.getSearchElement());
		}

		try {

			List<FeedbackBO> feedbackLists = adminService
					.retriveFeedbackDetails(feedbackBO);

			if (null != feedbackLists || feedbackLists.size() > 0) {
				model.addAttribute("feedbackList", feedbackLists);
			} else {

				model.addAttribute("Infomessage", "Feedback List Not Found");
			}

		} catch (final Exception exception) {
			exception.printStackTrace();
		}
		return "view_feedback";
	}

	@RequestMapping(value = "/view_feedback_details", method = RequestMethod.GET)
	public String viewfeedbackDetail(Model model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		if (null == session.getAttribute("adminId")) {
			return "redirect:/admin_sign_in.html";
		}
		try {
			String fid = request.getParameter("id");
			long id = Long.parseLong(fid);
			for (FeedbackBO viewfeedback : feedbackList)
				if (id == viewfeedback.getId()) {
					model.addAttribute("viewfeedbackdetail", viewfeedback);
				}
		} catch (final Exception exception) {
			exception.printStackTrace();
		}
		return "view_feedback_details";

	}

	@RequestMapping(value = "/view_feedback_delete", method = RequestMethod.GET)
	public String feedbackDelete(Model model, HttpServletRequest request) {
		String infomessage = request.getParameter("Infomessage");
		model.addAttribute("Infomessage", infomessage);
		HttpSession session = request.getSession();
		if (null == session.getAttribute("adminId")) {
			return "redirect:/admin_sign_in.html";
		}
		try {
			if (null != request.getParameter("id")) {
				String id = request.getParameter("id");
				long deleteId = Long.parseLong(id);
				FeedbackBO feedbackBO = new FeedbackBO();
				feedbackBO.setId(deleteId);
				feedbackBO.setIsDelete(true);
				feedbackBO = adminService.deleteFeedback(feedbackBO);
				if (null != feedbackBO) {
					model.addAttribute("Infomessage",
							"FeedBack Deleted Successfully..");
					return "redirect:/view_feedback.html";
				}
			}
		} catch (final Exception exception) {
			exception.printStackTrace();
		}
		return "redirect:/view_feedback.html";

	}

	public void getMinimumExperiences(Model model) {

		List<String> minimumExperiencesList = new ArrayList<String>();
		Dropdownutils.getMinimumExperiences();
		minimumExperiencesList = Dropdownutils.getMinimumExperiences();
		model.addAttribute("minExperiences", minimumExperiencesList);

	}

	public void getMaximumExperiences(Model model) {

		List<String> maximumExperiencesList = new ArrayList<String>();
		maximumExperiencesList = Dropdownutils.getMaximumExperiences();
		model.addAttribute("maxExperiences", maximumExperiencesList);

	}

	public void getJobType(Model model) {

		List<String> jobTypeList = new ArrayList<String>();
		jobTypeList = Dropdownutils.getJobType();
		model.addAttribute("jobTypeList", jobTypeList);

	}

	public void getLocation(Model model) {

		List<String> locationList = new ArrayList<String>();
		locationList = Dropdownutils.getLocation();
		model.addAttribute("locationList", locationList);

	}

	public void getCurrencyType(Model model) {

		List<String> currencyTypeList = new ArrayList<String>();
		currencyTypeList = Dropdownutils.getCurrencytype();
		model.addAttribute("currencyTypeList", currencyTypeList);

	}

	public void getMinimumSalary(Model model) {

		List<String> minimumSalary = new ArrayList<String>();
		minimumSalary = Dropdownutils.getMinimumSalary();
		model.addAttribute("minimumSalary", minimumSalary);

	}

	public void getMaximumSalary(Model model) {

		List<String> maximumSalary = new ArrayList<String>();
		maximumSalary = Dropdownutils.getMaximumSalary();
		model.addAttribute("maximumSalary", maximumSalary);

	}

	public void getFunctionarea(Model model) {

		List<String> functionAreaList = new ArrayList<String>();
		functionAreaList = Dropdownutils.getFunctionarea();
		model.addAttribute("functionAreaList", functionAreaList);

	}

	public void getUgqualifications(Model model) {

		List<String> UgqualificationsList = new ArrayList<String>();
		UgqualificationsList = Dropdownutils.getUgqualifications();
		model.addAttribute("UgqualificationsList", UgqualificationsList);

	}

	public void getPgqualifications(Model model) {

		List<String> pgqualificationsList = new ArrayList<String>();
		pgqualificationsList = Dropdownutils.getPgqualifications();
		model.addAttribute("pgqualificationsList", pgqualificationsList);

	}

	@RequestMapping(value = "/ajax_news", method = RequestMethod.GET)
	@ResponseBody
	public String newsLetterCreations(@RequestParam String email)
			throws IOException, SQLException {
		LOGGER.entry();
		/* String path = new UrlPathHelper().getPathWithinApplication(request); */
		String message = null;
		try {
			NewsLetterBO bo = new NewsLetterBO();
			bo.setEmailId(email);
			if (adminService.findByEmail(bo.getEmailId())) {
				message = "Email ID already exists";
				return message;
			}
			bo.setIsdeleted(true);
			bo = adminService.newsLetterCreate(bo);
			if (null != bo) {

				message = "Registration successfully completed";

			} else {

				message = "Your Enquiry Postponed,please contact Administrator.";
				return message;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		LOGGER.exit();
		return message;

	}

	@RequestMapping(value = "/upload_files", method = RequestMethod.GET)
	public String createEntity(Model model, HttpServletRequest request) {
		LOGGER.entry();
		String message = request.getParameter("message");
		model.addAttribute("message", message);
		EntityBO uploadEntity = new EntityBO();
		model.addAttribute("uploadFile", uploadEntity);
		LOGGER.exit();
		return "upload_files";
	}

	@RequestMapping(value = "/upload_files", method = RequestMethod.POST)
	public String createEntity(
			@Valid @ModelAttribute("uploadFile") EntityBO uploadFile,
			BindingResult result, HttpServletRequest request, Model model,
			@RequestParam("uploadCompany") MultipartFile uploadEntity)
			throws MyJobKartException, IOException, SerialException,
			SQLException, ValidatorException {
		LOGGER.entry();

		try {
			model.addAttribute("viewTable", "viewTable");
			HttpSession session = request.getSession();
			if (null == session.getAttribute("adminId")) {
				return "redirect:/admin_sign_in.html";
			}

			ArrayList<String> companyList = new ArrayList<String>();
			long adminId = (Long) session.getAttribute("adminId");

			EntityBO uploadFiles = new EntityBO();
			uploadFiles.setEntity(uploadFile.getEntityType());
			if (!uploadEntity.isEmpty()) {

				// uploaded csv File contents to convert ByteStream to String

				ByteArrayInputStream stream = new ByteArrayInputStream(
						uploadEntity.getBytes());
				String uploadEntityFiles = IOUtils.toString(stream);
				String tokenizerValues = null;

				// Company Entity is working this loop
				if (uploadFile.getEntityType().equalsIgnoreCase("Company")) {

					// convert String separated with comma using StringTokenizer
					StringTokenizer tokenString = new StringTokenizer(
							uploadEntityFiles, ",");
					// iterate StringTokenizer values add in ArrayList
					while (tokenString.hasMoreTokens()) {
						tokenizerValues = tokenString.nextToken();

						String[] tokens = tokenizerValues.split("\\r?\\n");
						String tokensValues = null;
						for (int i = 0; i < tokens.length; i++) {
							tokensValues = tokens[i];
							companyList.add(tokensValues);
						}

					}

					// The ArrayList and created date and modified date set in
					// EntityBO class
					uploadFiles.setEntityName(companyList);
					uploadFiles.setCreatedBy(adminId);
					uploadFiles.setCreated(new Date());
					uploadFiles.setModified(new Date());
					uploadFiles.setModifiedBy(adminId);

					// EntityBO objects to giving to Service methods

					uploadFiles = this.adminService.createCompany(uploadFiles);

					// adminService response to the Send data

					if (null != uploadFiles) {
						model.addAttribute("newCompanyList",
								uploadFiles.getNewEntityList());
						model.addAttribute("existingCompanyList",
								uploadFiles.getExistingEntityList());
						model.addAttribute("message", uploadFiles.getResponse());
						return "upload_files";
					}
				}

				// Industry Entity is working this loop
				if (uploadFile.getEntityType().equalsIgnoreCase("Industry")) {

					// convert String separated with comma using StringTokenizer
					StringTokenizer tokenString = new StringTokenizer(
							uploadEntityFiles, ",");

					// iterate StringTokenizer values add in ArrayList
					while (tokenString.hasMoreTokens()) {
						tokenizerValues = tokenString.nextToken();
						String[] tokens = tokenizerValues.split("\\r?\\n");
						String tokensValues = null;

						for (int i = 0; i < tokens.length; i++) {
							tokensValues = tokens[i];
							companyList.add(tokensValues);
						}
					}
					// The ArrayList and created date and modified date set in
					// EntityBO class
					uploadFiles.setEntityName(companyList);
					uploadFiles.setCreatedBy(adminId);
					uploadFiles.setCreated(new Date());
					uploadFiles.setModified(new Date());
					uploadFiles.setModifiedBy(adminId);

					// EntityBO objects to giving to Service methods
					uploadFiles = adminService.createIndustry(uploadFiles);
					// adminService response to the Send data
					if (null != uploadFiles) {
						model.addAttribute("newCompanyList",
								uploadFiles.getNewEntityList());
						model.addAttribute("existingCompanyList",
								uploadFiles.getExistingEntityList());
						model.addAttribute("message", uploadFiles.getResponse());
						return "upload_files";
					}
				}

			} else {

				model.addAttribute("message", "Upload the Files ........");
				return "redirect:/upload_files.html";
			}

		} catch (Exception exception) {
			exception.printStackTrace();
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("Admin Controller Upload Entity File  failed:"
						+ exception.getMessage());
			}
			LOGGER.info("Admin Controller Upload Entity File  failed:"
					+ exception.getMessage());
		}

		model.addAttribute("uploadFile", uploadFile);
		LOGGER.exit();
		return "upload_files";
	}

	@RequestMapping(value = "admin_employer_upload", method = RequestMethod.GET)
	public String employerUpload(Model model, HttpServletRequest request) {
		String sucessmessage = request.getParameter("sucessmessage");
		String infomessage = request.getParameter("infomessage");
		if (null != sucessmessage) {
			model.addAttribute("sucessmessage", sucessmessage);
		} else {
			model.addAttribute("infomessage", infomessage);
		}
		EmployerProfileBO employerBO = new EmployerProfileBO();
		model.addAttribute("employerUpload", employerBO);
		return "admin_employer_upload";
	}

	@RequestMapping(value = "admin_employer_upload", method = RequestMethod.POST)
	public String employerUpload(
			@Valid @ModelAttribute("employerUpload") EmployerProfileBO employerBO,
			BindingResult result, HttpServletRequest request, Model model,
			@RequestParam("uploadEmployer") MultipartFile excelfile) {
		try {
			EntityBO uploadFiles = new EntityBO();
			ArrayList<String> companyList = new ArrayList<String>();
			List<EmployerProfileBO> uploadEmployerList = new ArrayList<EmployerProfileBO>();
			int i = 0;
			long adminId = 0;
			// Creates a workbook object from the uploaded excelfile
			XSSFWorkbook workbook = new XSSFWorkbook(excelfile.getInputStream());
			// Creates a worksheet object representing the first sheet
			XSSFSheet worksheet = workbook.getSheetAt(0);
			// Reads the data in excel file until last row is encountered
			Iterator<Row> rowIterator = worksheet.iterator();

			while (rowIterator.hasNext()) {
				Row row = rowIterator.next(); // Read Rows from Excel document
				employerBO = new EmployerProfileBO();
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

							// Serial No
							if (0 == employerBO.getsNo()) {
								employerBO.setsNo((long) cell
										.getNumericCellValue());
								break;
							}

							// Password
							if (null == employerBO.getPassword()) {
								long pass = (long) (cell.getNumericCellValue());
								String password = Long.toString(pass);
								employerBO.setPassword(password);
								break;
							}
							// Confirm Password
							if (null == employerBO.getConfirmPassword()) {
								long conPass = (long) (cell
										.getNumericCellValue());
								String conPassword = Long.toString(conPass);
								employerBO.setConfirmPassword(conPassword);
								break;
							}

							// Contact Number
							if (null == employerBO.getContactNo()) {
								employerBO.setContactNo((long) cell
										.getNumericCellValue());
								break;
							}

							// Mobile Number
							if (null == employerBO.getMobileNo()) {
								employerBO.setMobileNo((long) cell
										.getNumericCellValue());
								break;
							}

						case Cell.CELL_TYPE_STRING:

							// First Name
							if (null == employerBO.getFirstName()) {
								employerBO.setFirstName(cell
										.getStringCellValue());
								break;
							}
							// Last Name
							if (null == employerBO.getLastName()) {
								employerBO.setLastName(cell
										.getStringCellValue());
								break;
							}
							// Email Address
							if (null == employerBO.getEmailId()) {
								employerBO
										.setEmailId(cell.getStringCellValue());
								break;
							}
							// ConfirmEmail Address
							if (null == employerBO.getConfirmEmail()) {
								employerBO.setConfirmEmail(cell
										.getStringCellValue());
								break;
							}

							// Company Name
							if (null == employerBO.getCompanyName()) {
								employerBO.setCompanyName(cell
										.getStringCellValue());
								break;
							}
							// Company Web site
							if (null == employerBO.getCompanyWebsite()) {
								employerBO.setCompanyWebsite(cell
										.getStringCellValue());
								break;
							}

							// Company Address
							if (null == employerBO.getAddressLine1()) {
								employerBO.setAddressLine1(cell
										.getStringCellValue());
								break;
							}
							// Industry Type
							if (null == employerBO.getIndustryType()) {
								employerBO.setIndustryType(cell
										.getStringCellValue());
								break;
							}

						}

					} else {
						break;
					}
				}
				if (row.getRowNum() >= 1 && employerBO.getsNo() > 0) {
					employerBO.setsNo(0);
					HttpSession session = request.getSession();
					if (null != (Long) session.getAttribute("adminId")) {
						adminId = (Long) session.getAttribute("adminId");
						employerBO.setModifiedBy(adminId);
						employerBO.setIsDelete(true);
						employerBO.setIsActive(true);
					}
					long companyId = 0;
					if (null != employerBO.getCompanyName()) {
						companyId = getcompany(employerBO.getCompanyName());
						employerBO.setCompanyId(companyId);
					}
					if (companyId == 0) {
						long companyid = adminService.addNewCompany(employerBO
								.getCompanyName());
						employerBO.setCompanyId(companyid);
					}
					if (this.employerService.findByEmail(employerBO
							.getEmailId())) {
						model.addAttribute("infomessage",
								employerBO.getEmailId() + " "
										+ "This Account is Already Exists");
						return "admin_employer_upload";
					}

					if (null != employerBO.getIndustryType()) {
						companyList.add(employerBO.getIndustryType());
						uploadFiles.setEntityName(companyList);
						uploadFiles.setCreatedBy(adminId);
						uploadFiles.setCreated(new Date());
						uploadFiles.setModified(new Date());
						uploadFiles.setModifiedBy(adminId);

						// EntityBO objects to giving to Service methods
						uploadFiles = adminService.createIndustry(uploadFiles);
					}

					final File rootDir = new File(
							this.servletContext
									.getRealPath("/WEB-INF/images/companylogo.jpg"));
					final BufferedImage image = ImageIO.read(rootDir);
					final ByteArrayOutputStream baos = new ByteArrayOutputStream();
					ImageIO.write(image, "jpg", baos);
					final byte[] res = baos.toByteArray();

					employerBO.setCompanyLogo(res);
					employerBO.setProductEnrolled("trail");
					if (null != uploadFiles) {
						uploadEmployerList.add(employerBO);
					} else {
						model.addAttribute("infomessage",
								"Employer has been uploaded Failed");
						return "admin_employer_upload";
					}
				}
			}

			long uploadEmployerid = adminService
					.uploadEmployer(uploadEmployerList);
			if (uploadEmployerid != 0) {
				model.addAttribute("sucessmessage",
						"Employer has been uploaded successfully");
			} else {
				model.addAttribute("infomessage",
						"Employer has been uploaded Failed");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "admin_employer_upload";
	}

	public long getcompany(String companyName) {
		LOGGER.entry();
		List<CompanyEntity> allCompanyList = new ArrayList<CompanyEntity>();
		long companyId = 0;
		try {
			allCompanyList = employerService.retrieveCompanyList();

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

	// Paginations in Company Entity

	@SuppressWarnings("rawtypes")
	private ResponseObject companyEntityPaginations(int page,
			List<EntityBO> dataLsit) {
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
		final ResponseObject<EntityBO> ro = new ResponseObject<EntityBO>();
		final List<EntityBO> list = dataLsit;
		if (null != list) {
			noOfRecords = list.size();
		}
		noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
		ro.setCurrentPage(page);
		ro.setRecordsPerPage(recordsPerPage);
		ro.setTotalPages(noOfPages);
		int count = 0;
		final List<EntityBO> pageList = new ArrayList<EntityBO>();
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

	@RequestMapping(value = "admin_company_view", method = RequestMethod.GET)
	public String adminCompanyEntityView(Model model, HttpServletRequest request)
			throws MyJobKartException {
		LOGGER.entry();
		String sucessmessage = request.getParameter("message");
		model.addAttribute("sucessmessage", sucessmessage);
		try {

			// get the All active Company Record from CompanyVO class
			List<EntityBO> companyEntityList = this.adminService
					.adminCompanyEntityView();

			if (null == companyEntityList || companyEntityList.size() == 0) {

				// check the No Record Found conditions in CompanyVO
				if (sucessmessage == null) {
					model.addAttribute("infomessage",
							"No Company Entity Record Found. Please Upload Company Entity ");
					return "admin_company_view";
				}
			} else {
				// the Company Record send to admin_company_view.jsp Page
				// through model attribute
				model.addAttribute("companyEntityList", companyEntityList);
			}
		} catch (Exception exception) {
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("Admin Controller Upload Company View :"
						+ exception.getMessage());
			}
			LOGGER.info("Admin Controller Upload Company View :"
					+ exception.getMessage());
		}
		LOGGER.exit();
		return "admin_company_view";
	}

	@RequestMapping(value = "/admin_update_company_entity", method = RequestMethod.GET)
	public String adminUpdateCompanyEntity(Model model,
			HttpServletRequest request) {
		LOGGER.entry();
		try {
			String requestId = request.getParameter("companiesId");
			long companiesId = Long.parseLong(requestId);
			EntityBO entityBO = new EntityBO();
			entityBO.setCompaniesId(companiesId);
			// get the one particular Record from CompanyVO using primarykey in
			// companiesId
			entityBO = this.adminService.getCompanyEntity(entityBO);
			// the EntityBO object send to admin_update_company_entity.jsp Page
			// through model attribute
			model.addAttribute("updateCompany", entityBO);
		} catch (Exception exception) {
			exception.printStackTrace();
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("Admin Controller Update Company Entity Failed:"
						+ exception.getMessage());
			}
			LOGGER.info("Admin Controller Update Company Entity Failed"
					+ exception.getMessage());
		}
		LOGGER.exit();
		return "admin_update_company_entity";
	}

	@RequestMapping(value = "/admin_update_company_entity", method = RequestMethod.POST)
	public String adminUpdateCompanyEntity(Model model,
			HttpServletRequest request,
			@Valid @ModelAttribute("updateCompany") EntityBO updateCompany,
			BindingResult result) throws MyJobKartException {
		LOGGER.entry();

		String requestId = request.getParameter("companiesId");
		long companiesId = Long.parseLong(requestId);
		boolean updateCompanyResult = false;
		try {
			EntityBO entityBO = new EntityBO();
			entityBO.setCompaniesId(companiesId);
			// get the All active Company Record from CompanyVO class
			List<String> companyEntityList = this.adminService
					.reteriveCompany();
			updateCompany.setCompaniesId(companiesId);
			updateCompany.setModifiedBy(companiesId);
			updateCompany.setModified(new Date());
			boolean duplicateCompany = companyEntityList.contains(updateCompany
					.getCompanyName());
			// get the one particular Record from CompanyVO using primarykey in
			// companiesId
			entityBO = this.adminService.getCompanyEntity(entityBO);

			// This logic is Update Company entity if edit the company Name,
			// that Company Name is same as current Record or enter new company
			// Name this name is checking Existing
			if ((entityBO.getCompanyName().equalsIgnoreCase(updateCompany
					.getCompanyName()))) {
				updateCompanyResult = this.adminService
						.adminUpdateCompanyEntity(updateCompany);

			} else if (!duplicateCompany) {

				updateCompanyResult = this.adminService
						.adminUpdateCompanyEntity(updateCompany);

			} else {
				result.rejectValue("companyName", "Validate.Company");
				return "admin_update_company_entity";

			}

			if (updateCompanyResult) {
				model.addAttribute("message", "Sucessfully Update");
				return "redirect:/admin_company_view.html";
			}

		} catch (Exception exception) {

			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("Admin Controller Update Company Entity Failed:"
						+ exception.getMessage());
			}
			LOGGER.info("Admin Controller Update Company Entity Failed"
					+ exception.getMessage());
		}
		LOGGER.exit();
		return "admin_update_company_entity";
	}

	@RequestMapping(value = "/admin_delete_company_entity", method = RequestMethod.GET)
	public String adminDeleteCompanyEntity(Model model,
			HttpServletRequest request) throws MyJobKartException {
		LOGGER.entry();
		try {
			HttpSession session = request.getSession();
			long adminId = (Long) session.getAttribute("adminId");
			String requestId = request.getParameter("companiesId");
			long companiesId = Long.parseLong(requestId);
			EntityBO entityBO = new EntityBO();
			entityBO.setCompaniesId(companiesId);
			entityBO.setModified(new Date());
			entityBO.setModifiedBy(adminId);
			entityBO.setIsDeleted(true);
			boolean deleteStatus = this.adminService
					.adminDeleteCompanyEntity(entityBO);
			if (deleteStatus) {
				model.addAttribute("message", "Sucessfully Deleted");
				return "redirect:/admin_company_view.html";
			}
		} catch (Exception exception) {
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("Admin Controller Delete Company Entity Failed:"
						+ exception.getMessage());
			}
			LOGGER.info("Admin Controller Delete Company Entity Failed"
					+ exception.getMessage());

		}
		LOGGER.exit();
		return "admin_company_view";
	}

	@RequestMapping(value = "admin_industry_view", method = RequestMethod.GET)
	public String adminIndustryEntityView(Model model,
			HttpServletRequest request) throws MyJobKartException {
		LOGGER.entry();
		String sucessmessage = request.getParameter("message");
		model.addAttribute("sucessmessage", sucessmessage);
		try {

			List<EntityBO> industryEntityList = this.adminService
					.adminIndustryEntityView();
			if (industryEntityList.size() == 0 || industryEntityList == null) {

				if (sucessmessage == null) {
					model.addAttribute("infomessage",
							"No Industry Entity Record Found. Please Upload Company Entity ");
					return "admin_industry_view";
				}

			}
			model.addAttribute("industryEntityList", industryEntityList);
			return "admin_industry_view";
		} catch (Exception exception) {
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("Admin Controller Admin Upload Industry View "
						+ exception.getMessage());
			}
			LOGGER.info("Admin Controller Admin Upload Industry View "
					+ exception.getMessage());
		}
		return "admin_industry_view";
	}

	@RequestMapping(value = "/admin_update_industry_entity", method = RequestMethod.GET)
	public String adminUpdateIndustryEntity(Model model,
			HttpServletRequest request) {
		LOGGER.entry();
		try {
			String requestId = request.getParameter("industryId");
			long companiesId = Long.parseLong(requestId);
			EntityBO entityBO = new EntityBO();
			entityBO.setCompaniesId(companiesId);
			entityBO = this.adminService.getIndustryEntity(entityBO);
			model.addAttribute("updateCompany", entityBO);
		} catch (Exception exception) {
			exception.printStackTrace();
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("Admin Controller Update Industry Entity Failed:"
						+ exception.getMessage());
			}
			LOGGER.info("Admin Controller Update Industry Entity Failed"
					+ exception.getMessage());
		}
		LOGGER.exit();
		return "admin_update_company_entity";
	}

	@RequestMapping(value = "/admin_update_industry_entity", method = RequestMethod.POST)
	public String adminUpdateIndustryEntity(Model model,
			HttpServletRequest request,
			@Valid @ModelAttribute("updateCompany") EntityBO updateCompany,
			BindingResult result) throws MyJobKartException {
		LOGGER.entry();

		String requestId = request.getParameter("industryId");
		long companiesId = Long.parseLong(requestId);
		boolean updateCompanyResult = false;
		try {
			EntityBO entityBO = new EntityBO();
			entityBO.setCompaniesId(companiesId);
			List<String> companyEntityList = this.adminService
					.reteriveIndustry();

			updateCompany.setCompaniesId(companiesId);
			updateCompany.setModifiedBy(companiesId);
			updateCompany.setModified(new Date());
			boolean duplicateCompany = companyEntityList.contains(updateCompany
					.getCompanyName());
			entityBO = this.adminService.getIndustryEntity(entityBO);
			if ((entityBO.getCompanyName().equalsIgnoreCase(updateCompany
					.getCompanyName()))) {
				updateCompanyResult = this.adminService
						.adminUpdateIndutryEntity(updateCompany);

			} else if (!duplicateCompany) {

				updateCompanyResult = this.adminService
						.adminUpdateIndutryEntity(updateCompany);

			} else {
				result.rejectValue("companyName", "Validate.Company");
				return "admin_update_company_entity";

			}

			if (updateCompanyResult) {
				model.addAttribute("message", "Sucessfully Update");
				return "redirect:/admin_industry_view.html";
			}

		} catch (Exception exception) {

			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("Admin Controller Update Industry Entity Failed:"
						+ exception.getMessage());
			}
			LOGGER.info("Admin Controller Update Industry Entity Failed"
					+ exception.getMessage());
		}
		LOGGER.exit();
		return "admin_industry_view";
	}

	@RequestMapping(value = "/admin_delete_industry_entity", method = RequestMethod.GET)
	public String adminDeleteIndustryEntity(Model model,
			HttpServletRequest request) throws MyJobKartException {
		LOGGER.entry();
		try {
			HttpSession session = request.getSession();
			long adminId = (Long) session.getAttribute("adminId");
			String requestId = request.getParameter("industryId");
			long companiesId = Long.parseLong(requestId);
			EntityBO entityBO = new EntityBO();
			entityBO.setCompaniesId(companiesId);
			entityBO.setModified(new Date());
			entityBO.setModifiedBy(adminId);
			entityBO.setIsDeleted(true);

			boolean deleteStatus = this.adminService
					.adminDeleteIndustryEntity(entityBO);

			if (deleteStatus) {
				model.addAttribute("message", "Sucessfully Deleted");
				return "redirect:/admin_industry_view.html";
			}
		} catch (Exception exception) {
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("Admin Controller Delete Company Entity Failed:"
						+ exception.getMessage());
			}
			LOGGER.info("Admin Controller Delete Company Entity Failed"
					+ exception.getMessage());

		}
		LOGGER.exit();
		return "admin_industry_view";
	}

	@RequestMapping(value = "/employer_Invitation", method = RequestMethod.GET)
	public String employerinvitation(Model model, HttpServletRequest request)
			throws MyJobKartException {
		AdminController.LOGGER.entry();
		HttpSession session = request.getSession();
		boolean status = false;
		try {
			EntityBO entityname = new EntityBO();
			/*
			 * List<ContactBO> contactList =
			 * adminService.retrieveUserConatctDetails();
			 */
			List<EntityBO> EntityBOList = new ArrayList<EntityBO>();

			EntityBOList = adminService.retrievemail();
			status = adminService.setemployerinvitation(EntityBOList);
			if (status) {
				return "redirect:/admin_home.html";
			}
		} catch (final Exception exception) {
			exception.printStackTrace();
		}
		return "redirect:/admin_home.html";

	}

	@RequestMapping(value = "/image_employer_details", method = RequestMethod.GET)
	public void showEmployerLoginImage(@RequestParam("id") Integer itemId,
			HttpServletResponse response, HttpServletRequest request)
			throws ServletException, IOException, SQLException,
			MyJobKartException {
		final String id = request.getParameter("id");
		long employerRegisterId = Long.parseLong(id);
		List<EmployerBO> employerRegisteredList = new ArrayList<EmployerBO>();
		EmployerBO employerBO = new EmployerBO();
		EmployerProfileBO employerProfileBO = new EmployerProfileBO();
		employerProfileBO.getEmployerProfileList();
		employerBO.setId(employerRegisterId);

		employerProfileBO.setEmployerRegistion(employerBO);
		employerProfileBO = employerService.retriveEmployer(employerProfileBO);

		if (null != employerProfileBO) {
			if (employerProfileBO.getEmployerRegistion().getId() == employerRegisterId) {
				if (null != employerProfileBO.getCompanyLogo()) {
					response.setContentType("image/jpeg, image/jpg, image/png, image/gif");
					response.getOutputStream().write(
							employerProfileBO.getCompanyLogo().getBytes(
									1,
									(int) employerProfileBO.getCompanyLogo()
											.length()));
					response.getOutputStream().close();
				} else {
					final File rootDir = new File(
							this.servletContext
									.getRealPath("/WEB-INF/images/company logo.jpg"));
					final BufferedImage image = ImageIO.read(rootDir);
					final ByteArrayOutputStream baos = new ByteArrayOutputStream();
					ImageIO.write(image, "jpg", baos);
					final byte[] res = baos.toByteArray();
					employerProfileBO.setCompanyLogo(res);
					response.setContentType("image/jpeg, image/jpg, image/png, image/gif");
					response.getOutputStream().write(
							employerProfileBO.getCompanyLogo().getBytes(
									1,
									(int) employerProfileBO.getCompanyLogo()
											.length()));
					response.getOutputStream().close();
				}
			}
		}

	}

	@RequestMapping(value = "/admin_walkin_post", method = RequestMethod.GET)
	public String walkinJob(Model model, HttpServletRequest request)
			throws MyJobKartException {
		AdminController.LOGGER.entry();
		HttpSession session = request.getSession();
		WalkinBO walkinBO = new WalkinBO();
		List<WalkinBO> walkinBOList = null;

		if (null == session.getAttribute("adminId")) {
			return "redirect:/admin_sign_in.html";
		}
		if (null != request.getParameter("type")) {
			walkinBO.setStatus(request.getParameter("type"));
		} else {
			walkinBO.setStatus(null);
		}

		walkinBOList = this.adminService.retriveAllwalkins(walkinBO);

		String message = request.getParameter("message");
		model.addAttribute("message", message);
		try {

			if (null == walkinBOList) {
				model.addAttribute("message", "No JobPost Found.. ");
			} else {
				if (null != walkinBOList) {
					for (final WalkinBO postjob : walkinBOList) {
						walkinBO = new WalkinBO();
						if (postjob.getIsActive()) {
							postjob.setStatus("Active");
						} else {
							postjob.setStatus("De-Active");
						}
						walkinBO.setStatus(postjob.getStatus());
					}
				}

				model.addAttribute("WalkinJoBpost", walkinBOList);
			}
		} catch (final Exception e) {
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("Admin Walkinjobpost  failed:" + e.getMessage());
			}
			LOGGER.info("Admin Walkinjobpost failed:" + e.getMessage());
		}
		return "admin_walkin_post";
	}

	/**
	 * @param page
	 * @param walkinBOList
	 * @return
	 */
	private ResponseObject<WalkinBO> jbpagination(int page,
			List<WalkinBO> walkinBOList) {
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
		final ResponseObject<WalkinBO> ro = new ResponseObject<WalkinBO>();
		final List<WalkinBO> list = walkinBOList;
		if (null != list) {
			noOfRecords = list.size();
		}
		noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
		ro.setCurrentPage(page);
		ro.setRecordsPerPage(recordsPerPage);
		ro.setTotalPages(noOfPages);
		int count = 0;
		final List<WalkinBO> pageList = new ArrayList<WalkinBO>();
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

	@RequestMapping(value = "/view_walkin", method = RequestMethod.GET)
	public String viewWalkinjob(Model model, HttpServletRequest request)
			throws MyJobKartException {
		AdminController.LOGGER.entry();
		try {
			HttpSession session = request.getSession();
			List<WalkinBO> walkinBOList = new ArrayList<WalkinBO>();
			WalkinBO walkinBO = new WalkinBO();
			if (null == session.getAttribute("adminId")) {
				return "redirect:/admin_sign_in.html";
			}
			String jobId = request.getParameter("id");
			long jobPostId = Long.parseLong(jobId);
			walkinBO.setId(jobPostId);

			walkinBO = this.employerService.retriveWalkin(walkinBO);

			final String jobpostid = request.getParameter("id");
			final long jobpostId = Long.parseLong(jobpostid);
			model.addAttribute("jobDetail", walkinBO);

		} catch (final NullPointerException ne) {
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("view jobpost  failed:" + ne.getMessage() + ne);
			}
			LOGGER.info("view jobpost failed:" + ne.getMessage() + ne);
		}
		AdminController.LOGGER.exit();
		return "admin_walkinjob_view";
	}

	@RequestMapping(value = "/delete_walkin", method = RequestMethod.GET)
	public String deleteWalkins(Model model, HttpServletRequest request)
			throws MyJobKartException {
		AdminController.LOGGER.entry();
		try {
			final String jobId = request.getParameter("id");
			final long deletedId = Long.parseLong(jobId);
			HttpSession session = request.getSession();
			WalkinBO walkinBO = new WalkinBO();

			final long adminid = (Long) session.getAttribute("adminId");

			walkinBO.setJobId(deletedId);
			walkinBO.setId(deletedId);
			walkinBO.setDeletedBy(adminid);
			walkinBO.setModifiedBy(adminid);
			walkinBO.setIsDeleted(false);
			Date deletedDate = new Date();
			walkinBO.setDeletedDate(deletedDate);

			walkinBO = this.employerService.deleteWalkin(walkinBO);
			if (null != walkinBO) {
				model.addAttribute("sucessmessage",
						"Walkin Job has been deleted Successfully");
			} else {
				model.addAttribute("message", "Data Has Been Updated Failed");

			}
		} catch (final MyJobKartException jb) {
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("Walkin delete profile failed:"
						+ jb.getErrorCode() + jb);

			}
			LOGGER.info("Walkin delete profile failed:" + jb.getErrorCode()
					+ jb);

		}
		AdminController.LOGGER.exit();
		return "redirect:/admin_walkin_post.html";
	}

	@RequestMapping(value = "/update_walkin", method = RequestMethod.GET)
	public String updateWalkin(Model model, HttpServletRequest request)
			throws MyJobKartException {

		AdminController.LOGGER.entry();
		try {

			HttpSession session = request.getSession();
			if (null == session.getAttribute("adminId")) {
				return "redirect:/admin_sign_in.html";
			}
			List<WalkinBO> walkinBOList = new ArrayList<WalkinBO>();
			String jobId = request.getParameter("id");
			long jobpostId = Long.parseLong(jobId);
			WalkinBO walkinBO = new WalkinBO();
			walkinBO.setId(jobpostId);

			walkinBOList = this.employerService.retrieveWalkinJobs(walkinBO);

			WalkinBO walkin = null;
			getCurrencyType(model);
			getMinimumExperiences(model);
			getMaximumExperiences(model);
			getMaximumSalary(model);
			getMinimumSalary(model);
			getFunctionarea(model);
			getJobType(model);
			industryList(model);
			companyList(model);
			getLocation(model);
			getPgqualifications(model);
			getUgqualifications(model);

			if (null != walkinBOList && walkinBOList.size() != 0) {
				for (final WalkinBO bo : walkinBOList) {
					walkin = new WalkinBO();
					if (jobpostId == bo.getId()) {
						walkin.setId(jobpostId);
						walkin.setJobId(bo.getId());
						walkin.setCompanyName(bo.getCompanyName());
						walkin.setCompanyProfile(bo.getCompanyProfile());
						walkin.setNoVacancies(bo.getNoVacancies());
						walkin.setJobDescription(bo.getJobDescription());
						walkin.setKeywords(bo.getKeywords());
						walkin.setFunctionArea(bo.getFunctionArea());
						walkin.setMaxExp(bo.getMaxExp());
						walkin.setMaxSalary(bo.getMaxSalary());
						walkin.setMinExp(bo.getMinExp());
						walkin.setMinSalary(bo.getMinSalary());
						walkin.setSalary(bo.getSalary());
						walkin.setJobLocation(bo.getJobLocation());
						walkin.setIndustryType(bo.getIndustryType());
						walkin.setAddress(bo.getAddress());
						walkin.setUgQualification(bo.getUgQualification());
						walkin.setPgQualification(bo.getPgQualification());
						walkin.setDoctorate(bo.getDoctorate());
						walkin.setRole(bo.getRole());
						walkin.setRoleCategory(bo.getRoleCategory());
						walkin.setContactPerson(bo.getContactPerson());
						walkin.setContactNo(bo.getContactNo());
						walkin.setJobTitle(bo.getJobTitle());
						walkin.setAddress(bo.getAddress());
						walkin.setStartDate(bo.getStartDate());
						walkin.setEndDate(bo.getEndDate());
						walkin.setContactNo(bo.getContactNo());
						walkin.setCurrencyType(bo.getCurrencyType());
						walkin.setPostedBy(bo.getPostedBy());
						walkin.setJobResponse(bo.getJobResponse());

						model.addAttribute("walkinupdate", walkin);
					}

				}

			}

		} catch (final NullPointerException ne) {
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("update jobpost  failed:" + ne.getMessage() + ne);
			}
			LOGGER.info("update jobpost failed:" + ne.getMessage() + ne);
		}
		AdminController.LOGGER.exit();
		return "admin_walkin_update";
	}

	@RequestMapping(value = "/update_walkin", method = RequestMethod.POST)
	public String updateWalkin(
			@Valid @ModelAttribute("walkinupdate") WalkinBO walkinBO,
			BindingResult result, Model model, HttpServletRequest request)
			throws MyJobKartException, SQLException {

		AdminController.LOGGER.entry();

		HttpSession session = request.getSession();
		List<WalkinBO> walkinBOList = new ArrayList<WalkinBO>();
		if (null == session.getAttribute("adminId")) {
			return "redirect:/admin_sign_in.html";
		}

		if (null == session.getAttribute("adminId")) {
			return "redirect:/admin_sign_in.html";
		}
		try {
			/*
			 * if (result.hasErrors()) { return "admin_walkin_post"; }
			 */

			getCurrencyType(model);
			getMinimumExperiences(model);
			getMaximumExperiences(model);
			getMaximumSalary(model);
			getMinimumSalary(model);
			getFunctionarea(model);
			getJobType(model);
			industryList(model);
			companyList(model);
			getLocation(model);
			getPgqualifications(model);
			getUgqualifications(model);
			walkinBO.setId(walkinBO.getId());

			walkinBOList = this.employerService.retrieveWalkinJobs(walkinBO);
			WalkinBO walkin = null;
			EmployerBO employerBO = null;
			EmployerLoginBO empLoginBO = null;
			if (null != walkinBOList && walkinBOList.size() != 0) {
				for (final WalkinBO bo : walkinBOList) {
					walkin = new WalkinBO();
					employerBO = new EmployerBO();
					empLoginBO = new EmployerLoginBO();
					empLoginBO.setId(bo.getEmployerLogin().getId());
					employerBO.setId(bo.getEmployerRegistion().getId());

				}
			}

			long id = (Long) session.getAttribute("adminId");
			String jobId = request.getParameter("id");
			long jobpostId = Long.parseLong(jobId);

			walkinBO.setJobId(jobpostId);
			walkinBO.setCreatedBy(id);
			walkinBO.setModifiedBy(id);
			walkinBO.setIsActive(true);
			walkinBO.setIsDeleted(true);
			walkinBO.setEmployerLogin(empLoginBO);
			walkinBO.setEmployerRegistion(employerBO);
			walkinBO.getJobResponse();

			walkinBO = employerService.updateWalkin(walkinBO);

			if (null != walkinBO) {
				model.addAttribute("sucessmessage",
						"Walkin Job Updated Successfully");
				return "redirect:/admin_walkin_post.html";
			} else {
				model.addAttribute("infomessage",
						"Data Has Been Updated Failed,Please Contact Administrator.");
				return "admin_walkin_update";
			}
		} catch (final Exception ee) {
			ee.printStackTrace();

		}
		return "admin_walkin_post";

	}

	@RequestMapping(value = "/walkin_status", method = RequestMethod.GET)
	public String walkinStatus(Model model, HttpServletRequest request) {
		AdminController.LOGGER.entry();
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
					model.addAttribute("message", "Profile  is Activate");
				} else {
					model.addAttribute("message", "Profile is De-Activate");
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
		AdminController.LOGGER.exit();
		return "redirect:/admin_walkin_post.html";

	}

}