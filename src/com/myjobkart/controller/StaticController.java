package com.myjobkart.controller;

import java.io.IOException;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.rowset.serial.SerialException;
import javax.validation.Valid;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import sun.security.validator.ValidatorException;

import com.myjobkart.bo.BannerPostBO;
import com.myjobkart.bo.ContactBO;
import com.myjobkart.bo.EmployerBO;
import com.myjobkart.bo.FeedbackBO;
import com.myjobkart.bo.JobseekerBO;
import com.myjobkart.bo.ProductBO;
import com.myjobkart.bo.ResponseObject;
import com.myjobkart.exception.JLogger;
import com.myjobkart.exception.MyJobKartException;
import com.myjobkart.service.AdminService;
import com.myjobkart.service.EmployerService;
import com.myjobkart.service.JobSeekerService;
import com.myjobkart.vo.ProductVO;

@Controller
@Scope("session")
public class StaticController implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1830780047202419921L;
	private static final JLogger LOGGER = JLogger
			.getLogger(StaticController.class);
	@Autowired
	private EmployerService<?> employerService;
	List<BannerPostBO> bannerPostList;
	List<JobseekerBO> registeredList;
	@Autowired
	private JobSeekerService<?> jobSeekerService;
	@Autowired
	private AdminService<?> adminService;

	@RequestMapping(value = "/about_as", method = RequestMethod.GET)
	public ModelAndView aboutAs(HttpServletRequest request, Model model)
			throws SerialException, SQLException {
		final HttpSession session = request.getSession();
		final String url = request.getRequestURL().toString();
		final String filename = FilenameUtils.getName(url);
		final String split[] = filename.split("\\.");
		final String fileName = split[0];
		this.bannerPostList = this.employerService.retrieveBannerList(fileName);
		model.addAttribute("bannerList", this.bannerPostList);
		session.setAttribute("bannerList", this.bannerPostList);
		return new ModelAndView("about_as");
	}

	@RequestMapping(value = "/aboutAsBannerImage", method = RequestMethod.GET)
	public void aboutAsBannerImage(@RequestParam("id") Integer itemId,
			HttpServletResponse response, HttpServletRequest request)
			throws ServletException, IOException, SQLException {
		final String id = request.getParameter("id");
		final long bannerId = Long.parseLong(id);
		for (final BannerPostBO prifiles : this.bannerPostList) {
			if (bannerId == prifiles.getBannerId()
					&& prifiles.getBannerImage() != null) {
				response.setContentType("image/jpeg, image/jpg, image/png, image/gif");
				response.getOutputStream().write(
						prifiles.getBannerImage().getBytes(1,
								(int) prifiles.getBannerImage().length()));
				response.getOutputStream().close();
			}
		}

	}

	@RequestMapping(value = "/FAQ", method = RequestMethod.GET)
	public ModelAndView faq(HttpServletRequest request) {
		request.getSession();
		/*
		 * if (null != session.getAttribute("userType")) { return new
		 * ModelAndView("admin_about_as"); }
		 */
		return new ModelAndView("FAQ");
	}

	@RequestMapping(value = "/contact", method = RequestMethod.GET)
	public ModelAndView contact(HttpServletRequest request) {
		request.getSession();
		/*
		 * if (null != session.getAttribute("userType")) { return new
		 * ModelAndView("admin_contact"); }
		 */
		return new ModelAndView("contact");
	}

	@RequestMapping(value = "/feedback", method = RequestMethod.GET)
	public String feedBack(Model model, HttpServletRequest request) {
		String sucessmessage = request.getParameter("sucessmessage");
		model.addAttribute("successmessage", sucessmessage);
		FeedbackBO feedback = new FeedbackBO();
		model.addAttribute("feedback", feedback);
		return "feedback";
	}

	@RequestMapping(value = "/feedback", method = RequestMethod.POST)
	public String employerContact(
			@Valid @ModelAttribute("feedback") FeedbackBO feedback,
			BindingResult result, HttpServletRequest request, Model model)
			throws MyJobKartException, IOException, SerialException,
			SQLException, ValidatorException {
		if (result.hasErrors()) {
			return "feedback";
		}
		try {
			feedback = employerService.feedbackBO(feedback);
			if (null != feedback) {
				model.addAttribute("sucessmessage",
						"Your FeedBack is successfully register and Myjobkart team contact shortly");
				return "redirect:/feedback.html";
			}
		} catch (final Exception jb) {
			jb.printStackTrace();

		}
		return null;
	}

	@RequestMapping(value = "/expertAdvice", method = RequestMethod.GET)
	public ModelAndView expertAdvice(HttpServletRequest request) {
		request.getSession();
		/*
		 * if (null != session.getAttribute("userType")) { return new
		 * ModelAndView("admin_advice"); }
		 */
		return new ModelAndView("expertAdvice");
	}

	@RequestMapping(value = "/jobseekers_services", method = RequestMethod.GET)
	public ModelAndView jobSeekerServices() {
		return new ModelAndView("jobseekers_services");
	}

	@RequestMapping(value = "/employer_services", method = RequestMethod.GET)
	public ModelAndView employerServices() {
		return new ModelAndView("employer_services");
	}

	@RequestMapping(value = "/text_communication", method = RequestMethod.GET)
	public ModelAndView jobSeekerTextServices() {
		return new ModelAndView("text_communication");
	}

	@RequestMapping(value = "/iconic_communication", method = RequestMethod.GET)
	public ModelAndView jobSeekerIconicServices() {
		return new ModelAndView("iconic_communication");
	}

	@RequestMapping(value = "/cover_letter", method = RequestMethod.GET)
	public ModelAndView jobSeekerCoverletterServices() {
		return new ModelAndView("cover_letter");
	}

	@RequestMapping(value = "/plug_in", method = RequestMethod.GET)
	public ModelAndView jobSeekerPluginServices() {
		return new ModelAndView("plug_in");
	}

	@RequestMapping(value = "/iamon_top", method = RequestMethod.GET)
	public ModelAndView jobSeekerIamontopServices() {
		return new ModelAndView("iamon_top");
	}

	@RequestMapping(value = "/connect_in", method = RequestMethod.GET)
	public ModelAndView jobSeekerConnectinServices() {
		return new ModelAndView("connect_in");
	}

	@RequestMapping(value = "/mobility", method = RequestMethod.GET)
	public ModelAndView jobSeekerMobilityServices() {
		return new ModelAndView("mobility");
	}

	@RequestMapping(value = "/place_banner", method = RequestMethod.GET)
	public ModelAndView employerPlacebannerServices() {
		return new ModelAndView("place_banner");
	}

	@RequestMapping(value = "/add_banner", method = RequestMethod.GET)
	public ModelAndView employerAddbannerServices() {
		return new ModelAndView("add_banner");
	}

	@RequestMapping(value = "/consultant_touch", method = RequestMethod.GET)
	public ModelAndView employerConsultanttouchServices() {
		return new ModelAndView("consultant_touch");
	}

	@RequestMapping(value = "/truead", method = RequestMethod.GET)
	public ModelAndView employerTrueadServices() {
		return new ModelAndView("truead");
	}

	@RequestMapping(value = "/application_tracking", method = RequestMethod.GET)
	public ModelAndView applicationtrackingServices() {
		return new ModelAndView("application_tracking");
	}

	@RequestMapping(value = "/career_site", method = RequestMethod.GET)
	public ModelAndView careersiteServices() {
		return new ModelAndView("career_site");
	}

	@RequestMapping(value = "/jobs_on_web", method = RequestMethod.GET)
	public ModelAndView jobsonwebServices() {
		return new ModelAndView("jobs_on_web");
	}

	@RequestMapping(value = "/jobtrolley_integration", method = RequestMethod.GET)
	public ModelAndView jobtrolleyintegrationServices() {
		return new ModelAndView("jobtrolley_integration");
	}

	@RequestMapping(value = "/branding", method = RequestMethod.GET)
	public ModelAndView brandingServices() {
		return new ModelAndView("branding");
	}

	@RequestMapping(value = "/e_learning_courses", method = RequestMethod.GET)
	public ModelAndView learningcoursesServices() {
		return new ModelAndView("e_learning_courses");
	}

	@RequestMapping(value = "/eapps", method = RequestMethod.GET)
	public ModelAndView eappsServices() {
		return new ModelAndView("eapps");
	}

	@RequestMapping(value = "/job_4you", method = RequestMethod.GET)
	public ModelAndView job4youServices() {
		return new ModelAndView("job_4you");
	}

	@RequestMapping(value = "/posting", method = RequestMethod.GET)
	public ModelAndView jobPostingServices() {
		return new ModelAndView("posting");
	}

	@RequestMapping(value = "/resdex", method = RequestMethod.GET)
	public ModelAndView resdexServices() {
		return new ModelAndView("resdex");
	}

	@RequestMapping(value = "/resume_writing", method = RequestMethod.GET)
	public ModelAndView resumewritingServices() {
		return new ModelAndView("resume_writing");
	}

	@RequestMapping(value = "/my_notifications", method = RequestMethod.GET)
	public ModelAndView notification(Model model, HttpServletRequest request) {

		EmployerBO allEmployer = new EmployerBO();
		List<EmployerBO> employerRenewalList = new ArrayList<EmployerBO>();
		int page = 1;
		final String paging = request.getParameter("page");
		model.addAttribute("employeeNameSearch", new EmployerBO());
		try {
			HttpSession session = request.getSession();
			if (null == session.getAttribute("loginId")) {
				return new ModelAndView("redirect:/employer_sign_in.html");
			}

			if (null != paging) {

				page = Integer.parseInt(paging);

				@SuppressWarnings("unchecked")
				final ResponseObject<EmployerBO> reponseObject = this
						.pagination2(page, employerRenewalList);

				model.addAttribute("registeredEmployer", reponseObject);

			} else {
				final String email = (String) session.getAttribute("emailId");
				allEmployer = this.employerService.employerRenewalAlert(email);
				employerRenewalList = allEmployer.getRegisteredList();

			}
			if (null != employerRenewalList && employerRenewalList.size() > 0) {

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
		StaticController.LOGGER.exit();
		return new ModelAndView("my_notifications");
	}

	@RequestMapping(value = "/my_notifications_jobseeker", method = RequestMethod.GET)
	public ModelAndView notificationJobSeeker(Model model,
			HttpServletRequest request) {
		JobseekerBO jobseekerBO = null;
		model.addAttribute("jobSeekerNameSearch", new JobseekerBO());
		int page = 1;
		final String paging = request.getParameter("page");
		try {
			if (null != paging) {
				page = Integer.parseInt(paging);
				@SuppressWarnings("unchecked")
				final ResponseObject<JobseekerBO> reponseObject = this
						.jobpagination1(page, this.registeredList);
				model.addAttribute("registeredList", reponseObject);
			} else {
				final HttpSession session = request.getSession();
				final String email = (String) session.getAttribute("emailId");
				jobseekerBO = new JobseekerBO();
				jobseekerBO = this.jobSeekerService
						.renewalJobseekerAlert(email);
				this.registeredList = jobseekerBO.getRegisteredList();
				if (0 != this.registeredList.size()) {
					@SuppressWarnings({ "unchecked" })
					final ResponseObject<JobseekerBO> responseObject = this
							.jobpagination1(1, this.registeredList);
					model.addAttribute("registeredList", responseObject);
				} else {
					model.addAttribute("message",
							"Retrive Failer!..please contact Administrator.. ");
				}
			}
		} catch (final Exception e) {
			e.printStackTrace();
		}

		StaticController.LOGGER.exit();
		return new ModelAndView("my_notifications_jobseeker");
	}

	@RequestMapping(value = "/jobseekers1_services", method = RequestMethod.GET)
	public ModelAndView jobseekerservices(HttpServletRequest request) {
		final HttpSession session = request.getSession();

		if (null == session.getAttribute("loginId")) {
			ModelAndView redirect = new ModelAndView(
					"redirect:/employer_sign_up.html");
			redirect.addObject(redirect);
			redirect.addObject("infomessage", "Employer Register and Active");
			return redirect;

		}

		if (null != session.getAttribute("jobseekerId")) {
			return new ModelAndView("jobseekers1_services");
		}

		if (null != session.getAttribute("userType")) {
			return new ModelAndView("admin_services");
		}
		return new ModelAndView("jobseekers1_services");
	}

	@RequestMapping(value = "/jobseeker_about_as", method = RequestMethod.GET)
	public ModelAndView jobseekerAboutUs() {
		return new ModelAndView("jobseeker_about_as");
	}

	@RequestMapping(value = "/jobseeker_contact", method = RequestMethod.GET)
	public ModelAndView jobseekerContact() {
		return new ModelAndView("jobseeker_contact");
	}

	@RequestMapping(value = "/jobseeker_blog", method = RequestMethod.GET)
	public ModelAndView jobseekerBlog(HttpServletRequest request) {
		final HttpSession session = request.getSession();
		if (null != session.getAttribute("userType")) {
			return new ModelAndView("jobseeker_blog");
		}
		return new ModelAndView("jobseeker_blog");
	}

	@RequestMapping(value = "/jobseeker_blog_single_post", method = RequestMethod.GET)
	public ModelAndView jobseekerBlogSinglePost(HttpServletRequest request) {
		final HttpSession session = request.getSession();
		if (null != session.getAttribute("userType")) {
			return new ModelAndView("jobseeker_blog_single_post");
		}
		return new ModelAndView("jobseeker_blog_single_post");
	}

	@RequestMapping(value = "/jobseeker_terms_privacy", method = RequestMethod.GET)
	public ModelAndView jobseekerTermsPrivacy(HttpServletRequest request) {
		final HttpSession session = request.getSession();
		if (null != session.getAttribute("loginId")) {
			return new ModelAndView("jobseeker_terms_privacy");
		}
		return new ModelAndView("jobseeker_terms_privacy");
	}

	@RequestMapping(value = "/employer_about_as", method = RequestMethod.GET)
	public ModelAndView employerAboutUs() {
		return new ModelAndView("employer_about_as");
	}

	@RequestMapping(value = "/employer_contact", method = RequestMethod.GET)
	public String employerContact(Model model, HttpServletRequest request) {
		String sucessmessage = request.getParameter("sucessmessage");
		model.addAttribute("sucessmessage", sucessmessage);
		ContactBO contact = new ContactBO();
		model.addAttribute("contact", contact);
		return "employer_contact";
	}

	@RequestMapping(value = "/employer_contact", method = RequestMethod.POST)
	public String employerContact(
			@Valid @ModelAttribute("contact") ContactBO contact,
			BindingResult result, HttpServletRequest request, Model model)
			throws MyJobKartException, IOException, SerialException,
			SQLException, ValidatorException {
		if (result.hasErrors()) {
			return "employer_contact";
		}
		contact = adminService.contactBo(contact);
		if (null != contact) {
			model.addAttribute("sucessmessage",
					"Your contact is successfully register and Myjobkart team contact shortly");
			return "redirect:/employer_contact.html";
		}
		return null;

	}

	@RequestMapping(value = "/employer_blog", method = RequestMethod.GET)
	public ModelAndView employerBlog() {
		return new ModelAndView("employer_blog");
	}

	@RequestMapping(value = "/employer_blog_single_post", method = RequestMethod.GET)
	public ModelAndView employerBlogSinglePost() {
		return new ModelAndView("employer_blog_single_post");
	}

	@RequestMapping(value = "/employer_terms_privacy", method = RequestMethod.GET)
	public ModelAndView employerTermsPrivacy() {
		return new ModelAndView("employer_terms_privacy");
	}

	@RequestMapping(value = "/blog_single_post", method = RequestMethod.GET)
	public ModelAndView blog() {
		return new ModelAndView("blog_single_post");
	}

	@RequestMapping(value = "/company_page", method = RequestMethod.GET)
	public ModelAndView company(HttpServletRequest request) {
		final HttpSession session = request.getSession();

		if (null != session.getAttribute("loginId")) {
			return new ModelAndView("employer_company_page");
		}
		if (null != session.getAttribute("jobseekerId")) {
			return new ModelAndView("jobseeker_company_page");
		}
		return new ModelAndView("company_page");
	}

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

	private ResponseObject pagination2(int page, List<EmployerBO> dataLsit) {
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

	@RequestMapping(value = "/employer2_services", method = RequestMethod.GET)
	public ModelAndView employerServices(HttpServletRequest request) {
		final HttpSession session = request.getSession();
		if (null != session.getAttribute("userType")) {
			if (null != request.getParameter("id")) {

				session.setAttribute("payId", request.getParameter("id"));
				return new ModelAndView("employer2_services");
			}
		}
		return new ModelAndView("employer2_services");
	}

	@RequestMapping(value = "/product_services", method = RequestMethod.GET)
	public ModelAndView employerService(HttpServletRequest request, Model model) {
		final HttpSession session = request.getSession();
		List<ProductBO> productList = new ArrayList<ProductBO>();
		String Infomessage = request.getParameter("Infomessage");

		if (null != session.getAttribute("adminId")) {

			ModelAndView redirect = new ModelAndView(
					"redirect:/admin_home.html");

			redirect.addObject(redirect);
			redirect.addObject("ErrorMessage",
					"You Are Admin User!Employer Only Purchase The Product.");

			return redirect;

		}

		// Check for purchase product.
		
		
		if (null != session.getAttribute("employerRegisterId")) {
		long RegId = (long) session.getAttribute("employerRegisterId");
		if (RegId != 0) {
			boolean isCheck = employerService.getemployerProduct(RegId);

			if (!isCheck) {
				model.addAttribute("infomessage",
						"Your product is already purchased");

			}
		}
		}

		productList = employerService.getProductList();
		if (null != productList) {
			for (ProductBO product : productList) {
				if (product.getIsActive()) {
					model.addAttribute("productList", productList);
				}
			}
		}
		model.addAttribute("Infomessage", Infomessage);

		if (null != session.getAttribute("loginId")) {

			if (null != session.getAttribute("userType")) {
				if (null != request.getParameter("id")) {
					session.setAttribute("payId", request.getParameter("id"));
					return new ModelAndView("product_services");
				}
			}

		} else {
			return new ModelAndView("product_services");
		}

		return new ModelAndView("product_services");

	}

	@RequestMapping(value = "/payment_services", method = RequestMethod.GET)
	public ModelAndView paymentService(HttpServletRequest request) {
		final HttpSession session = request.getSession();
		if (null != session.getAttribute("jobseekerId")) {

			if (null != request.getParameter("registerId")) {
				session.setAttribute("payId",
						request.getParameter("registerId"));
				return new ModelAndView("payment_services");
			}

		} else {
			return new ModelAndView("jobseekers_services");
		}

		return new ModelAndView("payment_services");

	}

}
