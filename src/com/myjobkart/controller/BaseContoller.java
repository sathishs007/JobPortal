package com.myjobkart.controller;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.rowset.serial.SerialException;
import javax.validation.Valid;

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
import org.springframework.web.servlet.ModelAndView;

import com.myjobkart.bo.AppliedJobBO;
import com.myjobkart.bo.BannerPostBO;
import com.myjobkart.bo.CompanyEntity;
import com.myjobkart.bo.EmployerBO;
import com.myjobkart.bo.EmployerLoginBO;
import com.myjobkart.bo.EmployerProfileBO;
import com.myjobkart.bo.JobPostBO;
import com.myjobkart.bo.JobSeekerLoginBO;
import com.myjobkart.bo.JobseekerBO;
import com.myjobkart.bo.JobseekerProfileBO;
import com.myjobkart.bo.PaymentBO;
import com.myjobkart.bo.ResponseObject;
import com.myjobkart.bo.SaveCandidateBO;
import com.myjobkart.bo.SavejobBO;
import com.myjobkart.bo.ViewJobseekerBO;
import com.myjobkart.bo.WalkinBO;
import com.myjobkart.exception.JLogger;
import com.myjobkart.exception.MyJobKartException;
import com.myjobkart.service.AdminService;
import com.myjobkart.service.EmployerService;
import com.myjobkart.service.JobSeekerService;
import com.myjobkart.vo.EmployerProfileVO;

@Controller
@Scope("session")
public class BaseContoller implements Serializable {

	private static final long serialVersionUID = -6907660092999752957L;

	private static final JLogger LOGGER = JLogger
			.getLogger(BaseContoller.class);

	@Autowired
	private EmployerService<?> employerService;

	@Autowired
	private AdminService<?> adminService;

	@Autowired
	private JobSeekerService<?> jobSeekerService;

	@Autowired
	ServletContext servletContext;

	JobseekerProfileBO reteriveprofile;
	// List<BannerPostBO> bannerPostList;
	long jobId = 0;
	JobPostBO jobPostBO;
	final String DATE_FORMAT = "[0-9]{4}-[0-9]{1,2}-[0-9]{1,2}";
	List<JobPostBO> jobPostList;

	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public ModelAndView welcome(HttpServletRequest request) {

		return new ModelAndView("redirect:/home.html");
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public ModelAndView logout(Model model, HttpServletRequest request)
			throws MyJobKartException, MalformedURLException {

		final HttpSession session = request.getSession();
		session.invalidate();
		this.addEmployee(model, request);
		return new ModelAndView("redirect:/home.html");
	}

	/**
	 * Job trolley Home Page
	 * 
	 * @param model
	 * @param request
	 * @return
	 * @throws MyJobKartException
	 * @throws MalformedURLException
	 */
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public ModelAndView addEmployee(Model model, HttpServletRequest request)
			throws MyJobKartException, MalformedURLException {

		try {

			// Retrieve the company name based on the industry type.
			Map<String, List<EmployerProfileVO>> companyList = adminService
					.retrieveCompanyName();
			if (null != companyList && !companyList.isEmpty()) {
				model.addAttribute("companyListVal", companyList);
			}

			for (Map.Entry<String, List<EmployerProfileVO>> entry : companyList
					.entrySet()) {
				List<EmployerProfileVO> employerProfileList = entry.getValue();
				model.addAttribute("JobDescriptions", employerProfileList);
			}

			String value = request.getParameter("val");
			if (null != value) {
				Map<String, List<String>> retrieveEntityList = adminService
						.retrieveEntityList(value);
				if (null != retrieveEntityList && !retrieveEntityList.isEmpty()) {
					model.addAttribute("companyListVal", retrieveEntityList);
				}
			}

			/*
			 * //Retrieve the top employer image for based on employer
			 * registration. List<EmployerProfileBO> employerProfileList = new
			 * ArrayList<EmployerProfileBO>(); employerProfileList =
			 * this.employerService.retriveEmployerProfile();
			 * model.addAttribute("JobDescriptions", employerProfileList);
			 */

		} catch (final Exception e) {
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug(" Home  failed:" + e.getMessage());
			}
			LOGGER.info(" Home failed:" + e.getMessage());

		}

		model.addAttribute("searchjob", new JobPostBO());
		return new ModelAndView("home");
	}

	@RequestMapping(value = "/information_technology", method = RequestMethod.GET)
	public @ResponseBody
	Map<String, List<EmployerProfileVO>> informationTechnology(Model model,
			HttpServletRequest request, @RequestParam String id)
			throws ParseException {
		String informationTech = id;
		Map<String, List<EmployerProfileVO>> industryList = new HashMap<String, List<EmployerProfileVO>>();

		Map<String, List<EmployerProfileVO>> companyList = adminService
				.retrieveCompanyName();

		for (Map.Entry<String, List<EmployerProfileVO>> industryType : companyList
				.entrySet()) {
			String industryName = (String) industryType.getKey();
			if (industryName.startsWith(informationTech)) {
				industryList.put(industryName, industryType.getValue());
			}
		}
		companyList = null;
		companyList = industryList;
		if (null != companyList && !companyList.isEmpty()) {
			model.addAttribute("searchjob", new JobPostBO());
			model.addAttribute("companyListVal", companyList);
		}

		return companyList;
	}

	/**
	 * This method heip to view the job details help of jobpost id
	 * 
	 * @param model
	 * @param request
	 * @return
	 * @throws MyJobKartException
	 * @throws SQLException
	 * @throws SerialException
	 */
	@RequestMapping(value = "/search_job_details", method = RequestMethod.GET)
	public String jobseekerjobSearch(Model model, HttpServletRequest request)
			throws MyJobKartException, SerialException, SQLException {
		BaseContoller.LOGGER.entry();
		HttpSession session = request.getSession();
		String active = request.getParameter("active");
		String saveActive = request.getParameter("disable");
		model.addAttribute("active", active);
		model.addAttribute("saveActive", saveActive);

		final String id = request.getParameter("id");
		final long jobPostId = Long.valueOf(id);
		JobPostBO jobpost = new JobPostBO();
		jobpost.setJobId(jobPostId);
		jobpost.setIsActive(true);
		JobPostBO jobPost = employerService.retriveEmployer(jobpost);

		model.addAttribute("jobPostDetail", jobPost.getJobPostList().get(0));
		model.addAttribute("searchjob", new JobPostBO());

		BaseContoller.LOGGER.exit();
		if (null != request.getParameter("search")) {
			return "search_job_details";
		}
		if (null != session.getAttribute("jobseekerId")) {
			return "jobseeker_search_job_details";
		}
		if (null != session.getAttribute("loginId")) {
			return "employer_jobpost_details";
		}
		if (null != session.getAttribute("adminId")) {
			return "admin_search_job_details";
		}
		return "job_post_details";
	}

	/**
	 * this method again to search the job
	 * 
	 * @param jobPostBO
	 * @param result
	 * @param model
	 * @return
	 * @throws MyJobKartException
	 */
	@RequestMapping(value = "/search_job_details", method = RequestMethod.POST)
	public String jobseekerjobSearch(
			@Valid @ModelAttribute("searchjob") JobPostBO jobPostBO,
			BindingResult result, Model model, HttpServletRequest request)
			throws MyJobKartException {

		BaseContoller.LOGGER.entry();
		String jobDescription = null;
		String keySkills = null;
		final String paging = request.getParameter("page");
		final List<JobPostBO> list = new ArrayList<JobPostBO>();
		final HttpSession session = request.getSession();
		try {
			if (null == paging) {
				if (null != jobPostBO.getKeywords()
						&& null != jobPostBO.getOtherSalaryDetails()
						&& null != jobPostBO.getJobTitle()
						&& null != jobPostBO.getMaxSalary()) {

					this.jobPostList = jobPostBO.getJobPostList();
				} else {
					jobPostBO = this.jobSeekerService.jobSearch(jobPostBO);
					this.jobPostList = jobPostBO.getJobPostList();
				}
				for (final JobPostBO postjob : this.jobPostList) {
					jobPostBO = new JobPostBO();
					if (postjob.getJobDescription().length() > 250) {
						jobDescription = postjob.getJobDescription().substring(
								0, 250);
						jobPostBO.setJobDescription(jobDescription);
					} else {
						jobPostBO
								.setJobDescription(postjob.getJobDescription());
					}
					if (postjob.getKeywords().length() > 50) {
						keySkills = postjob.getKeywords().substring(0, 50);
						jobPostBO.setKeywords(keySkills);
					} else {
						jobPostBO.setKeywords(postjob.getKeywords());
					}

					jobPostBO.setCompanyName(postjob.getCompanyName());
					jobPostBO.setCreated(postjob.getCreated());
					jobPostBO.setExperiance(postjob.getExperiance());
					jobPostBO.setJobLocation(postjob.getJobLocation());
					jobPostBO.setSalary(postjob.getSalary());
					jobPostBO.setPostedBy(postjob.getPostedBy());

					jobPostBO.setJobTitle(postjob.getJobTitle());
					jobPostBO.setId(postjob.getId());
					list.add(jobPostBO);
					session.setAttribute("searchList", list);
				}

				model.addAttribute("searchjob", new JobPostBO());

				final ResponseObject<JobPostBO> responseObject = this
						.pagination(1, list);
				model.addAttribute("JobDescriptions", responseObject);

			} else {

				if (null != jobPostBO.getKeywords()
						&& null != jobPostBO.getOtherSalaryDetails()
						&& null != jobPostBO.getJobTitle()
						&& null != jobPostBO.getMaxSalary()) {

					this.jobPostList = jobPostBO.getJobPostList();
				} else {
					jobPostBO = this.jobSeekerService.jobSearch(jobPostBO);
					this.jobPostList = jobPostBO.getJobPostList();
				}
				for (final JobPostBO postjob : this.jobPostList) {
					jobPostBO = new JobPostBO();
					if (postjob.getJobDescription().length() > 250) {
						jobDescription = postjob.getJobDescription().substring(
								0, 250);
						jobPostBO.setJobDescription(jobDescription);
					} else {
						jobPostBO
								.setJobDescription(postjob.getJobDescription());
					}
					if (postjob.getKeywords().length() > 50) {
						keySkills = postjob.getKeywords().substring(0, 50);
						jobPostBO.setKeywords(keySkills);
					} else {
						jobPostBO.setKeywords(postjob.getKeywords());
					}

					jobPostBO.setCompanyName(postjob.getCompanyName());
					jobPostBO.setCreated(postjob.getCreated());
					jobPostBO.setExperiance(postjob.getExperiance());
					jobPostBO.setJobLocation(postjob.getJobLocation());
					jobPostBO.setSalary(postjob.getSalary());
					jobPostBO.setPostedBy(postjob.getPostedBy());

					jobPostBO.setJobTitle(postjob.getJobTitle());
					jobPostBO.setId(postjob.getId());
					list.add(jobPostBO);
					session.setAttribute("searchList", list);
				}

				final int page = Integer.parseInt(paging);

				if (null != list) {
					final ResponseObject<JobPostBO> ro = this.pagination(page,
							list);
					model.addAttribute("JobDescriptions", ro);
				}
			}
		} catch (final NullPointerException ne) {
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("Search job details failed:" + ne.getMessage()
						+ ne);
			}
			LOGGER.info("Search job details  failed:" + ne.getMessage() + ne);

		}

		BaseContoller.LOGGER.exit();

		if (null != session.getAttribute("jobseekerId")) {
			return "find_jobs";
		}
		if (null != session.getAttribute("loginId")) {
			return "find_others";
		}
		if (null != session.getAttribute("userType")) {
			return "find_admin";
		}
		return "job_details";

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

	@RequestMapping(value = "/find_jobs", method = RequestMethod.GET)
	public String findjobs(Model model, HttpServletRequest request)
			throws MyJobKartException {
		HttpSession session = request.getSession();
		JobPostBO jobPostBO = new JobPostBO();

		String infomessage = request.getParameter("Infomessage");
		model.addAttribute("Infomessage", infomessage);

		String companyId = request.getParameter("companyId");
		final String paging = request.getParameter("page");
		String compId = request.getParameter("companyId");
		String companylocation = request.getParameter("location");
		String companyTitle = request.getParameter("jobtitle");
		if (null != request.getParameter("search")) {
			String searchStatus = request.getParameter("search");
			model.addAttribute("searchStatus", searchStatus);
		}
		/*
		 * String catagorieSkills = request.getParameter("catSkills"); String
		 * catagorieCompany = request.getParameter("catCompany"); String
		 * catagorieLocation = request.getParameter("catLocation");
		 * jobPostBO.setKeywords(catagorieSkills);
		 * jobPostBO.setCompanyName(catagorieCompany);
		 * jobPostBO.setAddress(catagorieLocation);
		 */

		if (null != compId) {
			jobPostBO.setCompanyId(Long.parseLong(companyId));
		}
		if (null != companylocation) {
			jobPostBO.setJobLocation(companylocation);
		}
		if (null != companyTitle) {
			jobPostBO.setJobTitle(companyTitle);
		}

		findJobPosts(jobPostBO, model, paging, session, request);

		return "find_jobs";
	}

	@RequestMapping(value = "/find_jobs", method = RequestMethod.POST)
	public String findjobs(
			@Valid @ModelAttribute("searchjob") JobPostBO jobPostBO,
			BindingResult result, Model model, HttpServletRequest request)
			throws MyJobKartException {
		final String paging = request.getParameter("page");
		final HttpSession session = request.getSession();

		if (null != request.getParameter("aid")) {

			jobPostBO.setSearchType(request.getParameter("aid"));
		}
		findJobPosts(jobPostBO, model, paging, session, request);

		return "find_jobs";
	}

	private void findJobPosts(JobPostBO jobpostBO, Model model, String paging,
			HttpSession session, HttpServletRequest request) {

		try {
			List<JobPostBO> countCompany = new ArrayList<JobPostBO>();
			List<JobPostBO> countCompanyList = new ArrayList<JobPostBO>();
			List<JobPostBO> list = new ArrayList<JobPostBO>();

			List<JobPostBO> countTitle = new ArrayList<JobPostBO>();
			List<JobPostBO> countLocation = new ArrayList<JobPostBO>();
			String jobDescription = null;
			String keySkills = null;
			int totalJobpost = 0;

			int page = 1;
			model.addAttribute("searchjob", new JobPostBO());
			final JobPostBO reteriveprofile = this.employerService
					.retriveEmployer(jobpostBO);
			final List<JobPostBO> searchList = reteriveprofile.getJobPostList();
			if (null != searchList){
			totalJobpost =searchList.size();
			}
			model.addAttribute("totalJobpost",totalJobpost);
			countCompany = reteriveprofile.getCompanyList();
			if (null != countCompany) {
				for (JobPostBO postBO : countCompany) {
					if (null != postBO.getCompanyName()) {
						long company_Id = getcompany(postBO.getCompanyName());
						postBO.setCompanyId(company_Id);
						countCompanyList.add(postBO);
					}
				}
			}
			if (null != paging) {
				page = Integer.parseInt(paging);
				ResponseObject<JobPostBO> reponseObject = null;
				if (null != jobPostList) {
					reponseObject = pagination(page, jobPostList);
				} else if (null != searchList) {
					reponseObject = this.pagination(page, searchList);
				}
				countLocation = reteriveprofile.getCountLocationList();
				model.addAttribute("locationList", countLocation);
				countTitle = reteriveprofile.getCountTitleList();
				model.addAttribute("titleList", countTitle);
				model.addAttribute("countCompany", countCompanyList);
				// end test for pagination count.
				model.addAttribute("JobDescriptions", reponseObject);
			} else {

				countCompany = reteriveprofile.getCompanyList();
				countLocation = reteriveprofile.getCountLocationList();
				model.addAttribute("locationList", countLocation);
				countTitle = reteriveprofile.getCountTitleList();
				model.addAttribute("titleList", countTitle);
				model.addAttribute("countCompany", countCompanyList);

				// Retrieved SavedJobList by JobseekerID
				List<SavejobBO> savejobList = new ArrayList<SavejobBO>();
				AppliedJobBO appliedJobBO = new AppliedJobBO();
				SavejobBO savejobBO = new SavejobBO();
				if (null != session.getAttribute("jobseekerId")) {
					final long jobseekerId = (Long) session
							.getAttribute("jobseekerId");
					appliedJobBO.setLoginId(jobseekerId);
				}
				savejobBO = this.jobSeekerService
						.jobseekerSavedJob(appliedJobBO);
				savejobList = savejobBO.getSavejobList();

				// Retrieved JobseekerAppliedJobList by JobseekerLoginId
				List<AppliedJobBO> appliedJobList = new ArrayList<AppliedJobBO>();
				appliedJobBO = this.jobSeekerService
						.jobSeekerApplied(appliedJobBO);
				appliedJobList = appliedJobBO.getAppliedJobList();

				if (null != reteriveprofile.getJobPostList()) {

					jobPostList = reteriveprofile.getJobPostList();
					if (null != session.getAttribute("jobseekerId")) {

						this.appliedJobs(model, request);
						this.saveJobs(model, request);

						for (final JobPostBO jobPost : jobPostList) {
							this.jobId = jobPost.getId();

							if (jobPost.getJobDescription().length() > 250) {
								jobDescription = jobPost.getJobDescription()
										.substring(0, 250);
								jobPost.setJobDescription(jobDescription);
							} else {
								jobPost.setJobDescription(jobPost
										.getJobDescription());
							}

							if (jobPost.getKeywords().length() > 50) {
								keySkills = jobPost.getKeywords().substring(0,
										50);
								jobpostBO.setKeywords(keySkills);
							} else {
								jobpostBO.setKeywords(jobPost.getKeywords());
							}
							if (null != appliedJobList) {
								for (final AppliedJobBO ajob : appliedJobList) {
									if (this.jobId == ajob.getJobId()) {

										jobPost.setIsVisiable(false);
										break;
									} else {

										jobPost.setIsVisiable(true);
									}
								}
							} else {
								jobPost.setIsVisiable(true);
							}

							if (null != savejobList) {
								for (final SavejobBO saveJobBO : savejobList) {

									if (jobPost.getId() == saveJobBO.getJobId()) {
										jobPost.setIsdisable(false);
										break;
									} else {
										jobPost.setIsdisable(true);

									}

								}
							} else {
								jobPost.setIsdisable(true);

							}
							list.add(jobPost);
							session.setAttribute("searchList", list);
							// model.addAttribute("searchjob", jobPostBO);
						}

						final ResponseObject<JobPostBO> responseObject = this
								.pagination(1, list);
						model.addAttribute("JobDescriptions", responseObject);
					} else {
						list = jobPostList;
						session.setAttribute("searchList", list);

						final ResponseObject<JobPostBO> responseObject = this
								.pagination(1, list);
						model.addAttribute("JobDescriptions", responseObject);
					}
				}
			}
			// } else block ends here.
		} catch (Exception e) {
			e.printStackTrace();
		}

		model.addAttribute("searchjob", new JobPostBO());

	}

	private ResponseObject<JobPostBO> pagination(int page,
			List<JobPostBO> dataLsit) {
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
		final List<JobPostBO> list = dataLsit;
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

	private ResponseObject<JobseekerProfileBO> paginations(int page,
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

	/**
	 * This method check the jobseeker login or not
	 * 
	 * @param model
	 * @param request
	 * @return
	 * @throws MyJobKartException
	 */
	@RequestMapping(value = "/save_jobs", method = RequestMethod.GET)
	public String savejobs(Model model, HttpServletRequest request)
			throws MyJobKartException {
		BaseContoller.LOGGER.entry();
		final HttpSession session = request.getSession();
		SavejobBO savejobBO;
		try {
			if (null != session.getAttribute("jobseekerId")) {

				final String id = request.getParameter("id");
				final long jobpostId = Long.valueOf(id);
				JobPostBO jobPost = new JobPostBO();
				jobPost.setJobId(jobpostId);

				final JobPostBO reteriveprofile = this.employerService
						.retriveEmployer(jobPost);
				this.jobPostList = reteriveprofile.getJobPostList();
				for (final JobPostBO appliedjob : this.jobPostList) {
					if (appliedjob.getId() == jobpostId) {
						savejobBO = new SavejobBO();
						final long jobseekerId = (Long) session
								.getAttribute("jobseekerId");
						savejobBO.setCreatedBy(jobseekerId);
						savejobBO.setModifiedBy(jobseekerId);
						savejobBO.setCompanyName(appliedjob.getCompanyName());
						savejobBO.setNoVacancies(appliedjob.getNoVacancies());
						savejobBO.setJobLocation(appliedjob.getJobLocation());
						savejobBO.setPostedBy(appliedjob.getPostedBy());
						savejobBO.setJobTitle(appliedjob.getJobTitle());
						savejobBO.setAddress(appliedjob.getAddress());
						savejobBO.setContactNo(appliedjob.getContactNo());
						savejobBO.setContactPerson(appliedjob
								.getContactPerson());
						savejobBO.setCurrencyType(appliedjob.getCurrencyType());
						savejobBO.setFunctionArea(appliedjob.getFunctionArea());
						savejobBO.setIndustryType(appliedjob.getIndustryType());
						savejobBO.setJobDescription(appliedjob
								.getJobDescription());
						savejobBO.setKeywords(appliedjob.getKeywords());
						savejobBO.setMaxSalary(appliedjob.getMaxSalary());
						savejobBO.setMinSalary(appliedjob.getMinSalary());
						savejobBO.setMaxExp(appliedjob.getMaxExp());
						savejobBO.setMinExp(appliedjob.getMinExp());
						savejobBO.setPgQualification(appliedjob
								.getPgQualification());
						savejobBO.setUgQualification(appliedjob
								.getUgQualification());
						savejobBO.setOtherSalaryDetails(appliedjob
								.getOtherSalaryDetails());
						savejobBO.setJobResponse(appliedjob.getJobResponse());
						final JobPostBO jobPostBO = new JobPostBO();
						jobPostBO.setId(appliedjob.getId());
						savejobBO.setJobpostBO(jobPostBO);
						EmployerBO employerBO = new EmployerBO();
						employerBO.setId(appliedjob.getEmployerProfile()
								.getId());
						savejobBO.setEmployerRegistration(employerBO);
						final EmployerLoginBO employerLoginBO = new EmployerLoginBO();
						employerLoginBO.setId(appliedjob.getEmployerLogin()
								.getId());
						savejobBO.setEmployerLogin(employerLoginBO);
						savejobBO.setIsDeleted(true);
						final JobSeekerLoginBO jobSeekerLoginBO = new JobSeekerLoginBO();
						jobSeekerLoginBO.setId(jobseekerId);
						savejobBO.setJobseekerLogin(jobSeekerLoginBO);
						final boolean saveJobs = this.jobSeekerService
								.savedJob(savejobBO);

						if (saveJobs) {
							model.addAttribute("message",
									"Your job has been Successfully saved!");
							return "redirect:/jobseeker_saved_jobs.html";
						} else {
							this.findjobs(model, request);
							model.addAttribute("searchjob", new JobPostBO());
							model.addAttribute("message",
									"Your account does not exisit,please contact Administrator.");
							return "find_jobs";
						}
					}
				}
			} else {
				return "redirect:/jobseeker_sign_in.html";
			}

		} catch (final Exception e) {
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("Save jobs failed:" + e.getMessage());
			}
			LOGGER.info("Save jobs  failed:" + e.getMessage());

		}
		return null;
	}

	@RequestMapping(value = "/save_jobs", method = RequestMethod.POST)
	public String savejobs(
			@Valid @ModelAttribute("searchjob") JobPostBO jobPostBO,
			BindingResult result, Model model, HttpServletRequest request)
			throws MyJobKartException {
		BaseContoller.LOGGER.entry();
		String jobDescription = null;
		String keySkills = null;
		final List<JobPostBO> list = new ArrayList<JobPostBO>();
		final HttpSession session = request.getSession();
		final String paging = request.getParameter("page");
		try {

			if (null == paging) {
				if (null != jobPostBO.getKeywords()
						&& null != jobPostBO.getOtherSalaryDetails()
						&& null != jobPostBO.getJobTitle()
						&& null != jobPostBO.getMaxSalary()) {

					this.jobPostList = jobPostBO.getJobPostList();
				} else {
					final String[] arr = jobPostBO.getCompanyName().split(",");
					if (arr.length > 1) {

						final String companyName = arr[1];
						jobPostBO.setCompanyName(companyName);
					}
					jobPostBO = this.jobSeekerService.jobSearch(jobPostBO);
					this.jobPostList = jobPostBO.getJobPostList();
				}
				for (final JobPostBO postjob : this.jobPostList) {
					jobPostBO = new JobPostBO();
					if (postjob.getJobDescription().length() > 250) {
						jobDescription = postjob.getJobDescription().substring(
								0, 250);
						jobPostBO.setJobDescription(jobDescription);
					} else {
						jobPostBO
								.setJobDescription(postjob.getJobDescription());
					}
					if (postjob.getKeywords().length() > 50) {
						keySkills = postjob.getKeywords().substring(0, 50);
						jobPostBO.setKeywords(keySkills);
					} else {
						jobPostBO.setKeywords(postjob.getKeywords());
					}
					jobPostBO.setCompanyName(postjob.getCompanyName());
					jobPostBO.setCreated(postjob.getCreated());
					jobPostBO.setExperiance(postjob.getExperiance());
					jobPostBO.setJobLocation(postjob.getJobLocation());
					jobPostBO.setSalary(postjob.getSalary());
					jobPostBO.setPostedBy(postjob.getPostedBy());
					jobPostBO.setJobTitle(postjob.getJobTitle());
					jobPostBO.setId(postjob.getId());
					list.add(jobPostBO);
					session.setAttribute("searchList", list);
				}
				final ResponseObject<JobPostBO> responseObject = this
						.pagination(1, list);
				model.addAttribute("JobDescriptions", responseObject);
			} else {

				if (null != jobPostBO.getKeywords()
						&& null != jobPostBO.getOtherSalaryDetails()
						&& null != jobPostBO.getJobTitle()
						&& null != jobPostBO.getMaxSalary()) {

					this.jobPostList = jobPostBO.getJobPostList();
				} else {
					final String[] arr = jobPostBO.getCompanyName().split(",");
					if (arr.length > 1) {

						final String companyName = arr[1];
						jobPostBO.setCompanyName(companyName);
					}
					jobPostBO = this.jobSeekerService.jobSearch(jobPostBO);
					this.jobPostList = jobPostBO.getJobPostList();
				}
				for (final JobPostBO postjob : this.jobPostList) {
					jobPostBO = new JobPostBO();
					if (postjob.getJobDescription().length() > 250) {
						jobDescription = postjob.getJobDescription().substring(
								0, 250);
						jobPostBO.setJobDescription(jobDescription);
					} else {
						jobPostBO
								.setJobDescription(postjob.getJobDescription());
					}
					if (postjob.getKeywords().length() > 50) {
						keySkills = postjob.getKeywords().substring(0, 50);
						jobPostBO.setKeywords(keySkills);
					} else {
						jobPostBO.setKeywords(postjob.getKeywords());
					}
					jobPostBO.setCompanyName(postjob.getCompanyName());
					jobPostBO.setCreated(postjob.getCreated());
					jobPostBO.setExperiance(postjob.getExperiance());
					jobPostBO.setJobLocation(postjob.getJobLocation());
					jobPostBO.setSalary(postjob.getSalary());
					jobPostBO.setPostedBy(postjob.getPostedBy());
					jobPostBO.setJobTitle(postjob.getJobTitle());
					jobPostBO.setId(postjob.getId());
					list.add(jobPostBO);
					session.setAttribute("searchList", list);
				}
				final int page = Integer.parseInt(paging);

				if (null != list) {
					final ResponseObject<JobPostBO> ro = this.pagination(page,
							list);
					model.addAttribute("JobDescriptions", ro);
				}
			}
		} catch (final NullPointerException ne) {
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("Save jobs failed:" + ne.getMessage() + ne);
			}
			LOGGER.info("Save jobs  failed:" + ne.getMessage() + ne);

		}
		BaseContoller.LOGGER.exit();

		if (null != session.getAttribute("loginId")) {

			return "find_others";
		}
		if (null != session.getAttribute("jobseekerId")) {
			return "find_jobs";
		}

		return "job_details";
	}

	/**
	 * This method check the jobseeker login or not
	 * 
	 * @param model
	 * @param request
	 * @return
	 * @throws MyJobKartException
	 */
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(Model model, HttpServletRequest request)
			throws MyJobKartException {
		BaseContoller.LOGGER.entry();
		final HttpSession session = request.getSession();

		if (null != session.getAttribute("jobseekerId")) {
			return "redirect:/jobseeker_home_view.html";
		}
		if (null != session.getAttribute("loginId")) {
			return "redirect:/employer_home_view.html";

		}
		if (null != session.getAttribute("userType")) {
			return "redirect:/home.html";
		}
		return "redirect:/home.html";
	}

	/**
	 * This method method check the employer login or not
	 * 
	 * @param model
	 * @param request
	 * @return
	 * @throws MyJobKartException
	 * @throws SQLException
	 * @throws SerialException
	 */
	@RequestMapping(value = "/save_resume", method = RequestMethod.GET)
	public String saveresume(Model model, HttpServletRequest request)
			throws MyJobKartException, SerialException, SQLException {
		BaseContoller.LOGGER.entry();
		HttpSession session = request.getSession();
		if (null == session.getAttribute("loginId")) {
			return "redirect:/employer_sign_in.html";
		}
		SaveCandidateBO saveCandidateBO = new SaveCandidateBO();
		JobseekerProfileBO jobseekerProfileBO = new JobseekerProfileBO();
		EmployerProfileBO profile = new EmployerProfileBO();
		jobPostBO = new JobPostBO();
		String jobseekerId = request.getParameter("id");
		long jobseekerProfId = Long.parseLong(jobseekerId);
		jobseekerProfileBO.setId(jobseekerProfId);
		saveCandidateBO.setJobseekerProfileBO(jobseekerProfileBO);
		saveCandidateBO = this.employerService
				.reteriveCandidate(saveCandidateBO);
		/*
		 * if(null != saveCandidateBO.getJobProfileList() &&
		 * saveCandidateBO.getJobProfileList().size() !=0){
		 * model.addAttribute("Infomessage", "Already Saved this Resume");
		 * return "redirect:/jobseeker_profile_details"; }
		 */

		String email = (String) session.getAttribute("emailId");
		profile.setEmailId(email);
		profile = this.employerService.retriveEmployer(profile);
		if (null == profile) {
			model.addAttribute("Infomessage",
					"Please creater your Employer profile");
			return "redirect:/employer_create_profile";
		}

		else {

			if (null != session.getAttribute("loginId")) {
				final long id = (Long) session.getAttribute("loginId");

				jobPostBO.setId(id);
				jobPostBO = this.employerService.retrieveJobPost(jobPostBO);
				List<JobPostBO> employerJobPostList = jobPostBO
						.getJobPostList();

				// Retrieved JobseekerProfileList
				this.reteriveprofile = this.jobSeekerService.retriveJobseeker();
				List<JobseekerProfileBO> profileList = this.reteriveprofile
						.getJobseekerProfileList();
				if (0 != employerJobPostList.size()) {
					if (null != profileList) {
						// JobseekerProfile to EmployerSavedCandidate
						for (final JobseekerProfileBO profileBO : profileList) {
							if (jobseekerProfId == profileBO.getId()) {
								saveCandidateBO = new SaveCandidateBO();
								saveCandidateBO.setCollege(profileBO
										.getCollege());
								int noOfExperience = (int) (long) (profileBO
										.getNoOfExperience());
								saveCandidateBO
										.setExperienceInYear(noOfExperience);
								saveCandidateBO.setCompanyName(profileBO
										.getCompanyName());
								saveCandidateBO.setCompanyType(profileBO
										.getCompanyType());
								saveCandidateBO.setCreatedBy(id);
								saveCandidateBO.setCurrentIndustry(profileBO
										.getCurrentIndustry());
								if (null != profileBO.getCurrentSalary()) {
									saveCandidateBO.setCurrentSalary(profileBO
											.getCurrentSalary());
								}
								saveCandidateBO
										.setDegree(profileBO.getDegree());
								saveCandidateBO.setDeleteBy(profileBO
										.getDeleteBy());
								saveCandidateBO.setDeletedDate(profileBO
										.getDeletedDate());
								saveCandidateBO.setDesignation(profileBO
										.getDesignation());
								saveCandidateBO.setDomainSkills(profileBO
										.getDomainSkills());
								saveCandidateBO.setEmailId(profileBO
										.getEmailId());

								if (null != profileBO.getExpectedCtc()) {
									saveCandidateBO.setExpectedCtc(profileBO
											.getExpectedCtc());
								}
								saveCandidateBO.setExpEndDate(profileBO
										.getExpEndDate());
								saveCandidateBO.setExperienceInMonth(profileBO
										.getExperienceInMonth());
								saveCandidateBO.setExpStartDate(profileBO
										.getExpStartDate());
								saveCandidateBO.setFirstName(profileBO
										.getFirstName());
								saveCandidateBO.setFunction(profileBO
										.getFunction());
								saveCandidateBO
										.setGender(profileBO.getGender());
								saveCandidateBO.setIsActive(profileBO
										.getIsActive());
								saveCandidateBO.setGetPrivilege(profileBO
										.getGetPrivilege());
								saveCandidateBO.setGrade(profileBO.getGrade());
								saveCandidateBO.setIsDelete(true);
								final EmployerLoginBO employerLoginBO = new EmployerLoginBO();
								employerLoginBO.setId(id);
								saveCandidateBO
										.setEmployerLoginBO(employerLoginBO);
								saveCandidateBO.setJobType(profileBO
										.getJobType());
								saveCandidateBO.setKeySkills(profileBO
										.getKeySkills());
								saveCandidateBO.setLanguagesKnown(profileBO
										.getLanguagesKnown());
								saveCandidateBO.setLastName(profileBO
										.getLastName());
								saveCandidateBO.setLocation(profileBO
										.getLocation());
								saveCandidateBO.setMaritalStatus(profileBO
										.getMaritalStatus());
								saveCandidateBO.setModifiedBy(id);
								saveCandidateBO.setNationality(profileBO
										.getNationality());
								saveCandidateBO.setPhone(profileBO.getPhone());
								saveCandidateBO.setPreferredIndustry(profileBO
										.getPreferredIndustry());
								saveCandidateBO.setPreferredLocation(profileBO
										.getPreferredLocation());
								saveCandidateBO.setProfileImages(profileBO
										.getProfileImage());
								saveCandidateBO.setResumeTitle(profileBO
										.getResumeTitle());
								saveCandidateBO.setSpecialisation(profileBO
										.getSpecialisation());
								saveCandidateBO
										.setTermsConditionsAgreed(profileBO
												.getTermsConditionsAgreed());
								saveCandidateBO.setUploadResumes(profileBO
										.getUploadResume());
								saveCandidateBO.setYearOfPassing(profileBO
										.getYearOfPassing());
								saveCandidateBO.setCurrentSalaryPer(profileBO
										.getCurrentSalaryPer());
								saveCandidateBO.setExpectedCtcPer(profileBO
										.getExpectedCtcPer());

								saveCandidateBO.setProfiledescription(profileBO
										.getProfiledescription());
								final JobseekerProfileBO bo = new JobseekerProfileBO();
								bo.setId(profileBO.getId());
								saveCandidateBO.setJobseekerProfileBO(bo);

							}
						}
					}

				} else {
					model.addAttribute("Infomessage", "Create a Job Post.. ");
					return "redirect:/job_posting.html";

				}
				String empEmail = (String) session.getAttribute("emailId");
				saveCandidateBO.setEmailId(empEmail);
				final EmployerLoginBO employerLoginBO = new EmployerLoginBO();
				employerLoginBO.setId(id);
				saveCandidateBO.setEmployerLoginBO(employerLoginBO);
				final boolean saveProfile = employerService
						.saveProfile(saveCandidateBO);

				if (saveProfile) {
					model.addAttribute("sucessmessage",
							"Candidate resume Successfully saved.. ");
					return "redirect:/candidate_resume.html";
				} else {
					model.addAttribute("infomessage",
							"Candidate resume save failer contact Admin.. ");
					return "find_resumes";
				}

			} else {
				return "redirect:/employer_sign_in_before.html";
			}
		}

	}

	/**
	 * This method used to perform the retrieve jobseeker_profile function
	 * (jobseeker)
	 * 
	 * @param model
	 * @param request
	 * @return
	 * @throws MyJobKartException
	 * @throws ParseException
	 */
	@RequestMapping(value = "/find_resumes", method = RequestMethod.GET)
	public String findResumes(Model model, HttpServletRequest request)
			throws MyJobKartException, ParseException {
		BaseContoller.LOGGER.entry();
		HttpSession session = request.getSession();
		JobseekerProfileBO jobseekerProfileBO = new JobseekerProfileBO();

		model.addAttribute("searchjobseeker", new JobseekerProfileBO());
		List<PaymentBO> enrolledList = new ArrayList<PaymentBO>();
		// List<SaveCandidateBO> saveCandidateList = new
		// ArrayList<SaveCandidateBO>();

		if (null != session.getAttribute("loginId")
				|| null != session.getAttribute("adminId")) {

			String message = request.getParameter("infomessage");
			model.addAttribute("infomessage", message);
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date dateWithoutTime = sdf.parse(sdf.format(new Date()));
			Date endDate;
			PaymentBO paymentBO = new PaymentBO();
			JobseekerProfileBO reteriveprofile = null;
			String email = (String) session.getAttribute("emailId");
			long companyId = 0;

			String profileTitle = request.getParameter("title");

			if (null != profileTitle) {
				jobseekerProfileBO.setProfiledescription(profileTitle);
			}

			try {
				// List<JobseekerProfileBO> profileList = new
				// ArrayList<JobseekerProfileBO>();
				if (null != session.getAttribute("loginId")) {

					// Check the enrolled service, payId is nothing as
					// employerloginId
					final long loginId = (Long) session.getAttribute("loginId");
					paymentBO = this.employerService
							.productsEnrolledList(loginId);
					enrolledList = paymentBO.getEnrolledList();

					// Retrieved employer Profile by employerEmail
					EmployerProfileBO profileCheck = new EmployerProfileBO();
					profileCheck.setEmailId(email);
					profileCheck = this.employerService
							.retriveEmployer(profileCheck);
					if (null == profileCheck) {
						model.addAttribute("Infomessage",
								"Please Creater Your Profile");
						return "redirect:/employer_create_profile.html";
					}
					companyId = profileCheck.getCompanyId();
					reteriveprofile = this.jobSeekerService
							.retriveJobseekerBO(companyId);
				}
				if (null != session.getAttribute("paid")) {
					Boolean paid = (Boolean) session.getAttribute("paid");
					if (paid) {
						if (null != enrolledList && enrolledList.size() > 0) {
							for (final PaymentBO payment : enrolledList) {
								endDate = payment.getEndDate();
								if (endDate.after(dateWithoutTime)) {
									String paging = request
											.getParameter("page");
									jobseekerProfileBO.setCompanyId(companyId);
									findJobSeekers(jobseekerProfileBO, model,
											paging, session, request);

								} else {
									return "redirect:/product_services.html";
								}
							}
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
				if (LOGGER.isDebugEnabled()) {
					LOGGER.debug("Jobseeker find resumes failed:"
							+ e.getMessage());
				}
				LOGGER.info("Jobseeker find resumes  failed:" + e.getMessage());
			}
		} else {
			return "redirect:/employer_sign_in.html";
		}
		return "find_resumes";
	}

	private void findJobSeekers(JobseekerProfileBO jobseekerProfileBO,
			Model model, String paging, HttpSession session,
			HttpServletRequest request) throws MyJobKartException,
			SerialException, SQLException {

		long profileId = 0;
		long saveCandidateId = 0;
		String resumeTile = null;

		List<JobseekerProfileBO> jobseekerProfileList = new ArrayList<JobseekerProfileBO>();

		final List<JobPostBO> list = null;
		// final List<JobseekerProfileBO> dupList = new
		// ArrayList<JobseekerProfileBO>();
		JobseekerProfileBO ProfileBO = null;
		List<SaveCandidateBO> saveCandidateList = new ArrayList<SaveCandidateBO>();
		List<JobseekerProfileBO> profileList = new ArrayList<JobseekerProfileBO>();
		List<JobseekerProfileBO> countTitleList = new ArrayList<JobseekerProfileBO>();

		// Employer savedCandidate List by employer LoginId
		final long id = (Long) session.getAttribute("loginId");
		SaveCandidateBO saveCandidateBO = new SaveCandidateBO();
		saveCandidateBO.setLoginId(id);
		saveCandidateBO = this.employerService
				.employeerSaveResume(saveCandidateBO);
		saveCandidateList = saveCandidateBO.getSaveCandidateBOList();

		// Retrieved Jobseeker ProfileList based on Search criteria
		jobseekerProfileBO = this.employerService
				.searchJobseeker(jobseekerProfileBO);
		jobseekerProfileList = jobseekerProfileBO.getJobProfileList();
		/*
		 * List<JobseekerProfileBO> countProfileList = jobseekerProfileBO
		 * .getJobTittleCountList();
		 */

		if (null != jobseekerProfileList) {
			for (final JobseekerProfileBO jobseekerProfile : jobseekerProfileList) {
				ProfileBO = new JobseekerProfileBO();
				profileId = jobseekerProfile.getId();
				ProfileBO.setProfileImage(jobseekerProfile.getProfileImage()
						.getBytes(
								1,
								(int) jobseekerProfile.getProfileImage()
										.length()));
				ProfileBO.setProfiledescription(jobseekerProfile
						.getProfiledescription());
				if (jobseekerProfile.getProfileDescriptions().length() > 250) {
				ProfileBO.setProfileDescriptions(jobseekerProfile.getProfileDescriptions().substring(0,250));
				}else{
					ProfileBO.setProfileDescriptions(jobseekerProfile.getProfileDescriptions());
				}
				ProfileBO.setId(jobseekerProfile.getId());
				ProfileBO.setFirstName(jobseekerProfile.getFirstName());

				if (null != jobseekerProfile.getResumeTitle()
						&& jobseekerProfile.getResumeTitle().length() > 250) {
					resumeTile = jobseekerProfile.getResumeTitle().substring(0,
							250);
					ProfileBO.setResumeTitle(resumeTile);

				} else {
					ProfileBO.setResumeTitle(jobseekerProfile.getResumeTitle());
				}
				if (jobseekerProfile.getKeySkills().length() > 100) {
					resumeTile = jobseekerProfile.getKeySkills().substring(0,
							100);
					ProfileBO.setKeySkills(resumeTile);

				} else {
					ProfileBO.setKeySkills(jobseekerProfile.getKeySkills());
				}

				ProfileBO.setPhone(jobseekerProfile.getPhone());
				ProfileBO.setEmailId(jobseekerProfile.getEmailId());
				ProfileBO.setCollege(jobseekerProfile.getCollege());
				ProfileBO.setDegree(jobseekerProfile.getDegree());

				ProfileBO.setCreated(jobseekerProfile.getCreated());
				ProfileBO.setCreatedDate(jobseekerProfile.getCreatedDate());

				// Check Employer, Saved jobseeker profile List
				if (null != saveCandidateList && saveCandidateList.size() > 0) {
					for (final SaveCandidateBO saveCandidate : saveCandidateList) {
						saveCandidateId = saveCandidate.getProfileId();
						if (profileId == saveCandidateId) {
							ProfileBO.setIsVisiable(false);
							break;
						} else {
							ProfileBO.setIsVisiable(true);
						}
					}
				}
				profileList.add(ProfileBO);
			}
		} else {
			model.addAttribute("message", "Search element not match");
		}

		if (profileList.size() == 0) {

			model.addAttribute("message", "Search element not match");
		}

		// Check Pagination
		if (null == paging) {
			final ResponseObject<JobseekerProfileBO> responseObject = this
					.paginations(1, profileList);
			model.addAttribute("ResumeDescriptions", responseObject);
			countTitleList = jobseekerProfileBO.getJobTittleCountList();
			model.addAttribute("countTitleList", countTitleList);

		} else {

			final int page = Integer.parseInt(paging);

			if (null != profileList) {
				final ResponseObject<JobseekerProfileBO> ro = this.paginations(
						page, profileList);
				model.addAttribute("ResumeDescriptions", ro);
				countTitleList = jobseekerProfileBO.getJobTittleCountList();
				model.addAttribute("countTitleList", countTitleList);

			}
		}
	}

	@SuppressWarnings("unused")
	@RequestMapping(value = "/find_resumes", method = RequestMethod.POST)
	public String retriveJobseeker(
			@ModelAttribute("searchjobseeker") @Valid JobseekerProfileBO jobseekerProfileBO,
			BindingResult result, Model model, HttpServletRequest request)
			throws MyJobKartException, IOException, SerialException,
			SQLException {

		long profileId = 0;
		long saveCandidateId = 0;
		String resumeTile = null;
		List<JobseekerProfileBO> profileList = new ArrayList<JobseekerProfileBO>();
		BaseContoller.LOGGER.entry();
		HttpSession session = request.getSession();
		if (null != session.getAttribute("jobseekerId")) {
			model.addAttribute("message", "You have already loggedin!");
			return "redirect:/jobseeker_home.html";
		}

		final String paging = request.getParameter("page");
		if (null != session.getAttribute("loginId")) {

			// Check the enrolled service, payId is nothing as
			// employerloginId
			final long loginId = (Long) session.getAttribute("loginId");
			String email = (String) session.getAttribute("emailId");

			// Retrieved employer Profile by employerEmail
			EmployerProfileBO profileCheck = new EmployerProfileBO();
			profileCheck.setEmailId(email);
			profileCheck = this.employerService.retriveEmployer(profileCheck);

			// Check the current company jobseeker profile,Does not visible for
			// our working company.
			long companyId = profileCheck.getCompanyId();
			reteriveprofile = this.jobSeekerService
					.retriveJobseekerBO(companyId);
			jobseekerProfileBO.setCompanyId(companyId);
			if (null == profileCheck) {
				model.addAttribute("Infomessage", "Please Creater Your Profile");
				return "redirect:/employer_create_profile.html";
			}
		}
		try {

			findJobSeekers(jobseekerProfileBO, model, paging, session, request);

		} catch (final NullPointerException ne) {
			ne.printStackTrace();
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("Find resumes failed:" + ne.getMessage() + ne);
			}
			LOGGER.info("Find resumes  failed:" + ne.getMessage() + ne);

		}
		BaseContoller.LOGGER.exit();
		return "find_resumes";

	}

	@RequestMapping(value = "/company_details", method = RequestMethod.GET)
	public String companydetails(Model model, HttpServletRequest request)
			throws MyJobKartException {
		BaseContoller.LOGGER.entry();
		String company;
		this.jobPostBO = new JobPostBO();
		String jobDescription = null;
		String keySkills = null;
		final List<JobPostBO> list = new ArrayList<JobPostBO>();
		company = request.getParameter("companyName");
		final String paging = request.getParameter("page");
		final HttpSession session = request.getSession();
		try {

			AppliedJobBO appliedJobBO = new AppliedJobBO();
			final long jobseekerId = (Long) session.getAttribute("jobseekerId");
			appliedJobBO.setLoginId(jobseekerId);
			SavejobBO savejobBO = this.jobSeekerService
					.jobseekerSavedJob(appliedJobBO);
			List<SavejobBO> savejobList = savejobBO.getSavejobList();
			appliedJobBO.setLoginId(jobseekerId);
			appliedJobBO = this.jobSeekerService.jobSeekerApplied(appliedJobBO);
			List<AppliedJobBO> appliedJobList = appliedJobBO
					.getAppliedJobList();

			if (null == paging) {
				for (final JobPostBO postjob : this.jobPostList) {
					if (company.equalsIgnoreCase(postjob.getCompanyName())) {
						this.jobPostBO = new JobPostBO();
						if (postjob.getJobDescription().length() > 250) {
							jobDescription = postjob.getJobDescription()
									.substring(0, 250);
							this.jobPostBO.setJobDescription(jobDescription);
						} else {
							this.jobPostBO.setJobDescription(postjob
									.getJobDescription());
						}
						if (postjob.getKeywords().length() > 50) {
							keySkills = postjob.getKeywords().substring(0, 50);
							this.jobPostBO.setKeywords(keySkills);
						} else {
							this.jobPostBO.setKeywords(postjob.getKeywords());
						}

						this.jobPostBO.setCompanyName(postjob.getCompanyName());
						this.jobPostBO.setCreated(postjob.getCreated());
						this.jobPostBO.setExperiance(postjob.getExperiance());
						this.jobPostBO.setJobLocation(postjob.getJobLocation());
						this.jobPostBO.setSalary(postjob.getSalary());
						this.jobPostBO.setPostedBy(postjob.getPostedBy());
						this.jobPostBO.setContactNo(postjob.getContactNo());
						this.jobPostBO.setContactPerson(postjob
								.getContactPerson());

						this.jobPostBO.setJobTitle(postjob.getJobTitle());
						this.jobPostBO.setId(postjob.getId());
						if (null != session.getAttribute("jobseekerId")) {
							if (null != appliedJobList) {
								for (final AppliedJobBO ajob : appliedJobList) {
									if (this.jobId == ajob.getJobId()) {

										this.jobPostBO.setIsVisiable(false);
										break;
									} else {

										this.jobPostBO.setIsVisiable(true);
									}
								}
							}
							if (null != savejobList) {
								for (final SavejobBO saveJobBO : savejobList) {

									if (this.jobPostBO.getId() == saveJobBO
											.getJobId()) {
										this.jobPostBO.setIsdisable(false);
										break;
									} else {
										this.jobPostBO.setIsdisable(true);
									}

								}
							}
						}

						list.add(this.jobPostBO);
						session.setAttribute("searchList", list);
					}
				}
				model.addAttribute("searchjob", new JobPostBO());
				final ResponseObject<JobPostBO> responseObject = this
						.pagination(1, list);
				model.addAttribute("JobDescriptions", responseObject);

			} else {

				for (final JobPostBO postjob : this.jobPostList) {
					if (company.equalsIgnoreCase(postjob.getCompanyName())) {
						this.jobPostBO = new JobPostBO();
						if (postjob.getJobDescription().length() > 250) {
							jobDescription = postjob.getJobDescription()
									.substring(0, 250);
							this.jobPostBO.setJobDescription(jobDescription);
						} else {
							this.jobPostBO.setJobDescription(postjob
									.getJobDescription());
						}
						if (postjob.getKeywords().length() > 50) {
							keySkills = postjob.getKeywords().substring(0, 50);
							this.jobPostBO.setKeywords(keySkills);
						} else {
							this.jobPostBO.setKeywords(postjob.getKeywords());
						}

						this.jobPostBO.setCompanyName(postjob.getCompanyName());
						this.jobPostBO.setCreated(postjob.getCreated());
						this.jobPostBO.setExperiance(postjob.getExperiance());
						this.jobPostBO.setJobLocation(postjob.getJobLocation());
						this.jobPostBO.setSalary(postjob.getSalary());
						this.jobPostBO.setPostedBy(postjob.getPostedBy());
						this.jobPostBO.setContactNo(postjob.getContactNo());
						this.jobPostBO.setContactPerson(postjob
								.getContactPerson());

						this.jobPostBO.setJobTitle(postjob.getJobTitle());
						this.jobPostBO.setId(postjob.getId());

						if (null != session.getAttribute("jobseekerId")) {
							if (null != appliedJobList) {
								for (final AppliedJobBO ajob : appliedJobList) {
									if (this.jobId == ajob.getJobId()) {

										this.jobPostBO.setIsVisiable(false);
										break;
									} else {

										this.jobPostBO.setIsVisiable(true);
									}
								}
							}
							if (null != savejobList) {
								for (final SavejobBO saveJobBO : savejobList) {

									if (this.jobPostBO.getId() == saveJobBO
											.getJobId()) {
										this.jobPostBO.setIsdisable(false);
										break;
									} else {
										this.jobPostBO.setIsdisable(true);
									}

								}
							}

						}

						list.add(this.jobPostBO);
						session.setAttribute("searchList", list);
					}

				}

				final int page = Integer.parseInt(paging);

				if (null != list) {
					final ResponseObject<JobPostBO> ro = this.pagination(page,
							list);
					model.addAttribute("JobDescriptions", ro);
				}

			}

			if (null != session.getAttribute("loginId")) {
				if (null != company || null != request.getParameter("id")) {
					return "find_others";
				}

			} else {
				if (null != session.getAttribute("jobseekerId")) {
					if (null != company || null != request.getParameter("id")) {
						return "find_jobs";
					} else {
						return "job_details";
					}

				}
			}
			if (null != session.getAttribute("userType")) {
				if (null != company || null != request.getParameter("id")) {
					return "find_admin";
				} else {
					return "find_admin_search_details";
				}
			}

		} catch (final Exception e) {
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("Company details failed:" + e.getMessage());
			}
			LOGGER.info("Company details  failed:" + e.getMessage());

		}
		return "job_details";
	}

	@RequestMapping(value = "/applied_jobs", method = RequestMethod.GET)
	public String appliedJob(Model model, HttpServletRequest request) {
		BaseContoller.LOGGER.entry();
		AppliedJobBO appliedJobBO;
		try {
			final HttpSession session = request.getSession();
			// List<JobseekerProfileBO> profileList = new
			// ArrayList<JobseekerProfileBO>();

			if (null != session.getAttribute("jobseekerId")) {

				JobseekerProfileBO profileBO = new JobseekerProfileBO();
				String email = (String) session.getAttribute("emailId");
				profileBO.setEmailId(email);
				this.reteriveprofile = this.jobSeekerService
						.retriveJobseeker(profileBO);
				List<JobseekerProfileBO> profileList = reteriveprofile
						.getJobseekerProfileList();
				if (null == profileList || 0 == profileList.size()) {
					model.addAttribute("Infomessage",
							"please create a profile and activated the Profile");
					return "redirect:/jobseeker_create_profile.html";
				}
				final String id = request.getParameter("id");
				final long jobpostId = Long.parseLong(id);
				final long applied = 0;
				JobPostBO jobPost = new JobPostBO();
				jobPost.setJobId(jobpostId);
				final JobPostBO reteriveprofile = this.employerService
						.retriveEmployer(jobPost);
				this.jobPostList = reteriveprofile.getJobPostList();
				for (final JobPostBO appliedjob : this.jobPostList) {
					if (appliedjob.getId() == jobpostId) {
						appliedJobBO = new AppliedJobBO();
						final long jobseekerId = (Long) session
								.getAttribute("jobseekerId");
						appliedJobBO.setCreatedBy(jobseekerId);
						appliedJobBO.setModifiedBy(jobseekerId);
						appliedJobBO
								.setCompanyName(appliedjob.getCompanyName());
						appliedJobBO
								.setNoVacancies(appliedjob.getNoVacancies());
						appliedJobBO
								.setJobLocation(appliedjob.getJobLocation());
						appliedJobBO.setPostedBy(appliedjob.getPostedBy());
						appliedJobBO.setJobTitle(appliedjob.getJobTitle());
						appliedJobBO.setAddress(appliedjob.getAddress());
						appliedJobBO.setContactNo(appliedjob.getContactNo());
						appliedJobBO.setCurrencyType(appliedjob
								.getCurrencyType());
						appliedJobBO.setContactPerson(appliedjob
								.getContactPerson());
						appliedJobBO.setFunctionArea(appliedjob
								.getFunctionArea());
						appliedJobBO.setIndustryType(appliedjob
								.getIndustryType());
						appliedJobBO.setJobDescription(appliedjob
								.getJobDescription());
						appliedJobBO
								.setNoVacancies(appliedjob.getNoVacancies());
						appliedJobBO.setKeywords(appliedjob.getKeywords());
						appliedJobBO.setMaxSalary(appliedjob.getMaxSalary());
						appliedJobBO.setMinSalary(appliedjob.getMinSalary());
						appliedJobBO.setMaxExp(appliedjob.getMaxExp());
						appliedJobBO.setMinExp(appliedjob.getMinExp());
						appliedJobBO.setPgQualification(appliedjob
								.getPgQualification());
						appliedJobBO.setUgQualification(appliedjob
								.getUgQualification());
						appliedJobBO.setOtherSalaryDetails(appliedjob
								.getOtherSalaryDetails());
						appliedJobBO
								.setJobResponse(appliedjob.getJobResponse());
						appliedJobBO.setEmployeeEmail(appliedjob.getEmailId());
						final JobPostBO jobPostBO = new JobPostBO();
						jobPostBO.setId(appliedjob.getId());
						appliedJobBO.setJobpostBO(jobPostBO);

						final EmployerLoginBO employerLoginBO = new EmployerLoginBO();
						employerLoginBO.setId(appliedjob.getEmployerLogin()
								.getId());
						appliedJobBO.setEmployerLogin(employerLoginBO);
						appliedJobBO.setIsDeleted(true);
						final JobSeekerLoginBO jobSeekerLoginBO = new JobSeekerLoginBO();
						jobSeekerLoginBO.setId(jobseekerId);
						appliedJobBO.setJobseekerLogin(jobSeekerLoginBO);

						for (JobseekerProfileBO jobseekerbo : profileList) {
							if (jobseekerId == jobseekerbo.getJobSeekerLogin()
									.getId()) {
								appliedJobBO
										.setName(jobseekerbo.getFirstName());
								appliedJobBO.setKeySkills(jobseekerbo
										.getKeySkills());
								appliedJobBO.setDegree(jobseekerbo.getDegree());

								appliedJobBO.setResumeTitle(jobseekerbo
										.getResumeTitle());

								appliedJobBO.setExperienceInYear(jobseekerbo
										.getExperienceInYear());
								appliedJobBO.setPhone(jobseekerbo.getPhone());
								appliedJobBO.setJobseekerEmail(jobseekerbo
										.getEmailId());
							}
						}
						if (0 != jobseekerId) {
							boolean jobid = jobSeekerService
									.checkProfileId(jobseekerId);
							if (jobid) {

								EmployerBO employerBO = new EmployerBO();
								employerBO.setId(appliedjob
										.getEmployerProfile().getId());
								appliedJobBO
										.setEmployerRegistration(employerBO);

								final boolean applyJobs = this.jobSeekerService
										.jobseekerAppliedJob(appliedJobBO,
												applied);

								if (applyJobs) {
									model.addAttribute("message",
											"Your Job has been applied successfully.");
									return "redirect:/jobseeker_applied_jobs.html";
								} else {
									final JobPostBO jobopstBO = new JobPostBO();
									this.findjobs(model, request);
									model.addAttribute("searchjob", jobopstBO);
									model.addAttribute("message",
											"Your account does not exisit,please contact Administrator.");
									return "find_jobs";
								}
							} else {
								model.addAttribute("Infomessage",
										"please create a profile and activated then apply");
								return "redirect:/jobseeker_create_profile.html";
							}
						} else {
							model.addAttribute("message",
									"Please Create a Profile and activated");
							return "redirect:/find_jobs.html";
						}
					}
				}
			} else {
				model.addAttribute("Infomessage",
						"please sign in and apply jobs");
				return "redirect:/jobseeker_sign_in.html";
			}

		} catch (final Exception e) {
			e.printStackTrace();
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("Applied jobs failed:" + e.getMessage());
			}
			LOGGER.info("Applied jobs  failed:" + e.getMessage());

		}
		BaseContoller.LOGGER.exit();
		return "redirect:/find_jobs.html";

	}

	@RequestMapping(value = "/applied_jobs", method = RequestMethod.POST)
	public String appliedjobs(
			@Valid @ModelAttribute("searchjob") JobPostBO jobPostBO,
			BindingResult result, Model model, HttpServletRequest request)
			throws MyJobKartException {
		BaseContoller.LOGGER.entry();
		String jobDescription = null;
		String keySkills = null;
		final List<JobPostBO> list = new ArrayList<JobPostBO>();
		final HttpSession session = request.getSession();
		final String paging = request.getParameter("page");
		try {

			if (null == paging) {

				if (null != jobPostBO.getKeywords()
						|| null != jobPostBO.getOtherSalaryDetails()
						|| null != jobPostBO.getJobTitle()
						|| null != jobPostBO.getMaxSalary()) {

					this.jobPostList = jobPostBO.getJobPostList();
				} else {

					final String[] arr = jobPostBO.getCompanyName().split(",");
					if (arr.length > 1) {

						final String companyName = arr[1];
						jobPostBO.setCompanyName(companyName);
					}
					jobPostBO = this.jobSeekerService.jobSearch(jobPostBO);
					this.jobPostList = jobPostBO.getJobPostList();
				}

				for (final JobPostBO postjob : this.jobPostList) {
					jobPostBO = new JobPostBO();
					if (postjob.getJobDescription().length() > 250) {
						jobDescription = postjob.getJobDescription().substring(
								0, 250);
						jobPostBO.setJobDescription(jobDescription);
					} else {
						jobPostBO
								.setJobDescription(postjob.getJobDescription());
					}
					if (postjob.getKeywords().length() > 50) {
						keySkills = postjob.getKeywords().substring(0, 50);
						jobPostBO.setKeywords(keySkills);
					} else {
						jobPostBO.setKeywords(postjob.getKeywords());
					}

					jobPostBO.setCompanyName(postjob.getCompanyName());
					jobPostBO.setCreated(postjob.getCreated());
					jobPostBO.setExperiance(postjob.getExperiance());
					jobPostBO.setJobLocation(postjob.getJobLocation());
					jobPostBO.setSalary(postjob.getSalary());
					jobPostBO.setPostedBy(postjob.getPostedBy());

					jobPostBO.setJobTitle(postjob.getJobTitle());
					jobPostBO.setId(postjob.getId());
					list.add(jobPostBO);
					session.setAttribute("searchList", list);
				}
				final ResponseObject<JobPostBO> responseObject = this
						.pagination(1, list);
				model.addAttribute("JobDescriptions", responseObject);

			} else {

				if (null != jobPostBO.getKeywords()
						|| null != jobPostBO.getOtherSalaryDetails()
						|| null != jobPostBO.getJobTitle()
						|| null != jobPostBO.getMaxSalary()) {

					this.jobPostList = jobPostBO.getJobPostList();
				} else {

					final String[] arr = jobPostBO.getCompanyName().split(",");
					if (arr.length > 1) {

						final String companyName = arr[1];
						jobPostBO.setCompanyName(companyName);
					}
					jobPostBO = this.jobSeekerService.jobSearch(jobPostBO);
					this.jobPostList = jobPostBO.getJobPostList();
				}

				for (final JobPostBO postjob : this.jobPostList) {
					jobPostBO = new JobPostBO();
					if (postjob.getJobDescription().length() > 250) {
						jobDescription = postjob.getJobDescription().substring(
								0, 250);
						jobPostBO.setJobDescription(jobDescription);
					} else {
						jobPostBO
								.setJobDescription(postjob.getJobDescription());
					}
					if (postjob.getKeywords().length() > 50) {
						keySkills = postjob.getKeywords().substring(0, 50);
						jobPostBO.setKeywords(keySkills);
					} else {
						jobPostBO.setKeywords(postjob.getKeywords());
					}

					jobPostBO.setCompanyName(postjob.getCompanyName());
					jobPostBO.setCreated(postjob.getCreated());
					jobPostBO.setExperiance(postjob.getExperiance());
					jobPostBO.setJobLocation(postjob.getJobLocation());
					jobPostBO.setSalary(postjob.getSalary());
					jobPostBO.setPostedBy(postjob.getPostedBy());

					jobPostBO.setJobTitle(postjob.getJobTitle());
					jobPostBO.setId(postjob.getId());
					list.add(jobPostBO);
					session.setAttribute("searchList", list);
				}
				final int page = Integer.parseInt(paging);

				if (null != list) {
					final ResponseObject<JobPostBO> ro = this.pagination(page,
							list);
					model.addAttribute("JobDescriptions", ro);
				}

			}
		} catch (final NullPointerException ne) {
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("Applied jobs failed:" + ne.getMessage() + ne);
			}
			LOGGER.info("Applied jobs  failed:" + ne.getMessage() + ne);

		}
		BaseContoller.LOGGER.exit();

		if (null != session.getAttribute("loginId")) {
			return "find_others";

		}
		if (null != session.getAttribute("jobseekerId")) {
			return "find_jobs";
		}

		return "job_details";
	}

	/*
	 * @RequestMapping(value = "/imageDisplay", method = RequestMethod.GET)
	 * public void showImage(@RequestParam("id") Integer itemId,
	 * HttpServletResponse response, HttpServletRequest request) throws
	 * ServletException, IOException, SQLException, MyJobKartException { final
	 * String id = request.getParameter("id"); final long imgid =
	 * Long.parseLong(id); //List<JobseekerProfileBO> profileList = new
	 * ArrayList<JobseekerProfileBO>(); // reteriveprofile =
	 * jobSeekerService.retriveJobseeker(); reteriveprofile.setId(imgid);
	 * this.reteriveprofile = this.jobSeekerService
	 * .retriveJobseeker(this.reteriveprofile); List<JobseekerProfileBO>
	 * profileList = this.reteriveprofile.getJobseekerProfileList(); for (final
	 * JobseekerProfileBO prifiles : profileList) { if (prifiles.getId() ==
	 * imgid) {
	 * response.setContentType("image/jpeg, image/jpg, image/png, image/gif");
	 * response.getOutputStream().write( prifiles.getProfileImage().getBytes(1,
	 * (int) prifiles.getProfileImage().length()));
	 * response.getOutputStream().close(); } }
	 * 
	 * }
	 */

	@RequestMapping(value = "/savedJobImageDisplay", method = RequestMethod.GET)
	public void showSavedJobImage(@RequestParam("id") Integer itemId,
			HttpServletResponse response, HttpServletRequest request)
			throws ServletException, IOException, SQLException {
		final HttpSession session = request.getSession();
		try {
			long jobseekerId = 0;
			SavejobBO savejobBO = new SavejobBO();
			EmployerBO employerbo = new EmployerBO();
			if (null != (Long) session.getAttribute("jobseekerId")) {
				jobseekerId = (Long) session.getAttribute("jobseekerId");
			}
			final String id = request.getParameter("id");
			final long imgid = Long.parseLong(id);
			final JobSeekerLoginBO jobSeekerLoginBO = new JobSeekerLoginBO();
			jobSeekerLoginBO.setId(jobseekerId);
			savejobBO.setJobseekerLogin(jobSeekerLoginBO);
			savejobBO.setId(imgid);
			savejobBO = this.jobSeekerService.reteriveSavedJobs(savejobBO);
			final List<SavejobBO> savejobBOList = savejobBO.getJobPostList();

			if (null != savejobBOList) {
				for (final SavejobBO savejob : savejobBOList) {
					if (savejob.getId() == imgid) {
						long employerRegisterId = savejob
								.getEmployerRegistration().getId();
						employerbo.setId(employerRegisterId);
						EmployerProfileBO employerProfileBO = new EmployerProfileBO();
						employerProfileBO.setEmployerRegistion(employerbo);
						employerProfileBO = employerService
								.retriveEmployer(employerProfileBO);

						if (employerProfileBO.getEmployerRegistion().getId() == employerRegisterId) {
							if (null != employerProfileBO.getCompanyLogo()) {
								response.setContentType("image/jpeg, image/jpg, image/png, image/gif");
								response.getOutputStream()
										.write(employerProfileBO
												.getCompanyLogo()
												.getBytes(
														1,
														(int) employerProfileBO
																.getCompanyLogo()
																.length()));
								response.getOutputStream().close();
							} else {
								final File rootDir = new File(
										this.servletContext
												.getRealPath("/WEB-INF/images/company logo.jpg"));
								final BufferedImage image = ImageIO
										.read(rootDir);
								final ByteArrayOutputStream baos = new ByteArrayOutputStream();
								ImageIO.write(image, "jpg", baos);
								final byte[] res = baos.toByteArray();
								employerProfileBO.setCompanyLogo(res);
								response.setContentType("image/jpeg, image/jpg, image/png, image/gif");
								response.getOutputStream()
										.write(employerProfileBO
												.getCompanyLogo()
												.getBytes(
														1,
														(int) employerProfileBO
																.getCompanyLogo()
																.length()));
								response.getOutputStream().close();
							}
						}
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private List<AppliedJobBO> appliedList(long jobseekerId)
			throws MyJobKartException {
		LOGGER.entry();
		AppliedJobBO appliedJobBO = new AppliedJobBO();
		final JobSeekerLoginBO jobSeekerLoginBO = new JobSeekerLoginBO();
		List<AppliedJobBO> appliedJobList = null;
		try {
			jobSeekerLoginBO.setId(jobseekerId);
			appliedJobBO.setJobseekerLogin(jobSeekerLoginBO);
			appliedJobBO = this.jobSeekerService
					.reteriveAppliedJobs(appliedJobBO);
			appliedJobList = appliedJobBO.getJobPostList();
		} catch (final MyJobKartException e) {
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("Reterive applied jobs failed:" + e.getMessage()
						+ e);
			}
			LOGGER.info("Reterive applied jobs failed:" + e.getMessage() + e);
		}
		LOGGER.exit();
		return appliedJobList;

	}

	@RequestMapping(value = "/appliedJobImageDisplay", method = RequestMethod.GET)
	public void showAppliedJobImage(@RequestParam("id") Integer itemId,
			HttpServletResponse response, HttpServletRequest request)
			throws ServletException, IOException, SQLException,
			MyJobKartException {
		long jobseekerId = 0;
		final HttpSession session = request.getSession();
		if (null != (Long) session.getAttribute("jobseekerId")) {
			jobseekerId = (Long) session.getAttribute("jobseekerId");
		}
		List<AppliedJobBO> appliedjobBOList = appliedList(jobseekerId);
		AppliedJobBO appliedBO = new AppliedJobBO();
		final String id = request.getParameter("id");
		final long imgid = Long.parseLong(id);
		EmployerBO employerbo = new EmployerBO();
		appliedBO.setId(imgid);
		appliedBO = this.jobSeekerService.reteriveAppliedJobs(appliedBO);
		appliedjobBOList = appliedBO.getJobPostList();

		for (final AppliedJobBO appliedJobBO : appliedjobBOList) {
			if (appliedJobBO.getId() == imgid) {

				long employerRegisterId = appliedJobBO
						.getEmployerRegistration().getId();
				employerbo.setId(employerRegisterId);
				EmployerProfileBO employerProfileBO = new EmployerProfileBO();
				employerProfileBO.setEmployerRegistion(employerbo);
				employerProfileBO = employerService
						.retriveEmployer(employerProfileBO);

				if (employerProfileBO.getEmployerRegistion().getId() == employerRegisterId) {
					if (null != employerProfileBO.getCompanyLogo()) {
						response.setContentType("image/jpeg, image/jpg, image/png, image/gif");
						response.getOutputStream().write(
								employerProfileBO.getCompanyLogo().getBytes(
										1,
										(int) employerProfileBO
												.getCompanyLogo().length()));
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
										(int) employerProfileBO
												.getCompanyLogo().length()));
						response.getOutputStream().close();
					}
				}

			}
		}

	}

	@RequestMapping(value = "/jobImageDisplay", method = RequestMethod.GET)
	public void showJobImage(@RequestParam("id") Integer itemId,
			HttpServletResponse response, HttpServletRequest request)
			throws ServletException, IOException, SQLException,
			MyJobKartException {
		String id = request.getParameter("id");
		long imgid = Long.valueOf(id);
		EmployerProfileBO employerProfileBO = new EmployerProfileBO();
		employerProfileBO.setId(imgid);
		employerProfileBO = employerService.retriveEmployer(employerProfileBO);
		if (null != employerProfileBO.getCompanyLogo()) {
			response.setContentType("image/jpeg, image/jpg, image/png, image/gif");
			response.getOutputStream().write(
					employerProfileBO.getCompanyLogo().getBytes(1,
							(int) employerProfileBO.getCompanyLogo().length()));
			response.getOutputStream().close();
		}

	}

	@RequestMapping(value = "/imageLoginDisplay", method = RequestMethod.GET)
	public void showLoginImage(@RequestParam("id") Integer profileId,
			HttpServletResponse response, HttpServletRequest request)
			throws ServletException, IOException, SQLException,
			MyJobKartException {
		JobseekerProfileBO profileBO = new JobseekerProfileBO();
		if (null != profileId) {
			profileBO.setId(profileId);
		}
		JobseekerProfileBO profile = this.jobSeekerService
				.retriveJobseeker(profileBO);

		// profileList = this.reteriveprofile.getJobseekerProfileList();
		if (null != profile.getJobseekerProfileList()) {
			if (profileId == profile.getJobseekerProfileList().get(0).getId()) {
				response.setContentType("image/jpeg, image/jpg, image/png, image/gif");
				response.getOutputStream().write(
						profile.getJobseekerProfileList()
								.get(0)
								.getProfileImage()
								.getBytes(
										1,
										(int) profile.getJobseekerProfileList()
												.get(0).getProfileImage()
												.length()));
				response.getOutputStream().close();
			} else {

				final File rootDir = new File(
						this.servletContext
								.getRealPath("/WEB-INF/images/male.png"));
				final BufferedImage image = ImageIO.read(rootDir);
				final ByteArrayOutputStream baos = new ByteArrayOutputStream();
				ImageIO.write(image, "png", baos);
				final byte[] res = baos.toByteArray();
				profile.setProfileImage(res);
				response.setContentType("image/jpeg, image/jpg, image/png, image/gif");
				response.getOutputStream().write(
						profile.getProfileImage().getBytes(1,
								(int) profile.getProfileImage().length()));
				response.getOutputStream().close();
			}
		} else {
			if (null != profile.getProfileImage()) {
				response.setContentType("image/jpeg, image/jpg, image/png, image/gif");
				response.getOutputStream().write(
						profile.getProfileImage().getBytes(1,
								(int) profile.getProfileImage().length()));
				response.getOutputStream().close();
			} else {

				final File rootDir = new File(
						this.servletContext
								.getRealPath("/WEB-INF/images/male.png"));
				final BufferedImage image = ImageIO.read(rootDir);
				final ByteArrayOutputStream baos = new ByteArrayOutputStream();
				ImageIO.write(image, "png", baos);
				final byte[] res = baos.toByteArray();
				profile.setProfileImage(res);
				response.setContentType("image/jpeg, image/jpg, image/png, image/gif");
				response.getOutputStream().write(
						profile.getProfileImage().getBytes(1,
								(int) profile.getProfileImage().length()));
				response.getOutputStream().close();
			}

		}
	}

	@RequestMapping(value = "/imageEmployerLoginDisplay", method = RequestMethod.GET)
	public void showEmployerLoginImage(@RequestParam("id") Integer jobPostId,
			HttpServletResponse response, HttpServletRequest request)
			throws ServletException, IOException, SQLException,
			MyJobKartException {
		final HttpSession session = request.getSession();
		EmployerBO employerBO = new EmployerBO();
		long employerRegisterId = 0;
		EmployerProfileBO employerProfileBO = new EmployerProfileBO();
		JobPostBO jobPostBO = new JobPostBO();
		employerProfileBO.getEmployerProfileList();
		if (null != (Long) session.getAttribute("employerRegisterId")) {
			employerRegisterId = (Long) session
					.getAttribute("employerRegisterId");
			employerBO.setId(employerRegisterId);
		}
		if (null != (Long) session.getAttribute("employerRegId")) {
			employerRegisterId = (Long) session.getAttribute("employerRegId");
			employerBO.setId(employerRegisterId);
		}
		if (null != jobPostId && 0 != jobPostId) {

			jobPostBO.setJobId(jobPostId);
			jobPostBO = this.employerService.retrieveJobPost(jobPostBO);
			jobPostBO = jobPostBO.getJobPostList().get(0);
			employerProfileBO.setId(jobPostBO.getEmployerProfile().getId());
		}

		if (0 != employerBO.getId()) {
			employerProfileBO.setEmployerRegistion(employerBO);
		}
		employerProfileBO = employerService.retriveEmployer(employerProfileBO);

		if (null != employerProfileBO) {
			if (employerProfileBO.getEmployerRegistion().getId() == employerRegisterId
					|| jobPostBO.getEmployerProfile().getId() == employerProfileBO
							.getId()) {
				if (null != employerProfileBO.getCompanyLogo()) {
					response.setContentType("image/jpeg, image/jpg, image/png, image/gif");
					response.getOutputStream().write(
							employerProfileBO.getCompanyLogo().getBytes(
									1,
									(int) employerProfileBO.getCompanyLogo()
											.length()));
					response.getOutputStream().close();
				} else {
					employerBO = this.employerService
							.retrieveRegisteredEmployer(employerRegisterId);
					List<EmployerBO> employerRegisteredList = new ArrayList<EmployerBO>();
					employerRegisteredList = employerBO.getRegisteredList();
					if (null != employerRegisteredList
							&& employerRegisteredList.size() != 0) {
						for (final EmployerBO employerbo : employerRegisteredList) {

							response.setContentType("image/jpeg, image/jpg, image/png, image/gif");
							response.getOutputStream().write(
									employerbo.getCompanyLogo().getBytes(
											1,
											(int) employerbo.getCompanyLogo()
													.length()));
							response.getOutputStream().close();

						}
					}
				}
			}
		} else {
			employerBO = this.employerService
					.retrieveRegisteredEmployer(employerRegisterId);
			// List<EmployerBO> employerRegisteredList = new
			// ArrayList<EmployerBO>();
			List<EmployerBO> employerRegisteredList = employerBO
					.getRegisteredList();
			for (final EmployerBO employerbo : employerRegisteredList) {

				response.setContentType("image/jpeg, image/jpg, image/png, image/gif");
				response.getOutputStream().write(
						employerbo.getCompanyLogo().getBytes(1,
								(int) employerbo.getCompanyLogo().length()));
				response.getOutputStream().close();

			}
		}

	}

	@RequestMapping(value = "/imageAdminJobpost", method = RequestMethod.GET)
	public void showEmployerLoginAdmin(@RequestParam("id") Integer itemId,
			HttpServletResponse response, HttpServletRequest request)
			throws ServletException, IOException, SQLException,
			MyJobKartException {
		// List<EmployerBO> employerRegisteredList = new
		// ArrayList<EmployerBO>();
		final String id = request.getParameter("id");
		final long imgid = Long.parseLong(id);
		// EmployerBO employerBO = new EmployerBO();
		EmployerBO employerBO = employerService
				.retrieveRegisteredEmployer(imgid);
		List<EmployerBO> employerRegisteredList = employerBO
				.getRegisteredList();
		for (final EmployerBO employerbo : employerRegisteredList) {
			if (employerbo.getId() == imgid) {
				response.setContentType("image/jpeg, image/jpg, image/png, image/gif");
				response.getOutputStream().write(
						employerbo.getCompanyLogo().getBytes(1,
								(int) employerbo.getCompanyLogo().length()));
				response.getOutputStream().close();

			}
		}

	}

	/*
	 * @RequestMapping(value = "/profileimageDisplay", method =
	 * RequestMethod.GET) public void showProfileImage(@RequestParam("id")
	 * Integer itemId, HttpServletResponse response, HttpServletRequest request)
	 * throws ServletException, IOException, SQLException, MyJobKartException {
	 * final String id = request.getParameter("id"); final long imgid =
	 * Long.parseLong(id);
	 * 
	 * List<JobseekerProfileBO> profileList = new
	 * ArrayList<JobseekerProfileBO>(); this.reteriveprofile =
	 * jobSeekerService.retriveJobseeker(); profileList =
	 * this.reteriveprofile.getJobseekerProfileList();
	 * 
	 * for (final JobseekerProfileBO prifiles : profileList) { if
	 * (prifiles.getJobSeekerLogin().getId() == imgid) {
	 * response.setContentType("image/jpeg, image/jpg, image/png, image/gif");
	 * response.getOutputStream().write( prifiles.getProfileImage().getBytes(1,
	 * (int) prifiles.getProfileImage().length()));
	 * response.getOutputStream().close(); } }
	 * 
	 * }
	 */

	public List<AppliedJobBO> appliedJobs(Model model,
			HttpServletRequest request) {
		new SavejobBO();
		final HttpSession session = request.getSession();
		// List<AppliedJobBO> appliedJobList = new ArrayList<AppliedJobBO>();
		final long jobseekerId = (Long) session.getAttribute("jobseekerId");
		AppliedJobBO appliedJobBO = new AppliedJobBO();
		appliedJobBO.setLoginId(jobseekerId);
		appliedJobBO = this.jobSeekerService.jobSeekerApplied(appliedJobBO);
		List<AppliedJobBO> appliedJobList = appliedJobBO.getAppliedJobList();
		return appliedJobList;
	}

	public List<SavejobBO> saveJobs(Model model, HttpServletRequest request) {
		final AppliedJobBO appliedJobBO = new AppliedJobBO();
		SavejobBO savejobBO = new SavejobBO();
		final HttpSession session = request.getSession();
		// List<SavejobBO> savejobList = new ArrayList<SavejobBO>();
		final long jobseekerId = (Long) session.getAttribute("jobseekerId");
		appliedJobBO.setLoginId(jobseekerId);
		savejobBO = this.jobSeekerService.jobseekerSavedJob(appliedJobBO);
		List<SavejobBO> savejobList = savejobBO.getSavejobList();

		return savejobList;
	}

	public List<JobPostBO> jobPostList(List<JobPostBO> jobPostList) {
		String jobDescription = null;
		String keySkills = null;
		final List<JobPostBO> list = new ArrayList<JobPostBO>();
		for (final JobPostBO postjob : jobPostList) {
			this.jobPostBO = new JobPostBO();
			if (postjob.getJobDescription().length() > 250) {
				jobDescription = postjob.getJobDescription().substring(0, 250);
				this.jobPostBO.setJobDescription(jobDescription);
			} else {
				this.jobPostBO.setJobDescription(postjob.getJobDescription());
			}
			if (postjob.getKeywords().length() > 50) {
				keySkills = postjob.getKeywords().substring(0, 50);
				this.jobPostBO.setKeywords(keySkills);
			} else {
				this.jobPostBO.setKeywords(postjob.getKeywords());
			}
			this.jobPostBO.setCompanyName(postjob.getCompanyName());
			this.jobPostBO.setCreated(postjob.getCreated());
			this.jobPostBO.setExperiance(postjob.getExperiance());
			this.jobPostBO.setJobLocation(postjob.getJobLocation());
			this.jobPostBO.setSalary(postjob.getSalary());
			this.jobPostBO.setPostedBy(postjob.getPostedBy());
			this.jobPostBO.setContactNo(postjob.getContactNo());
			this.jobPostBO.setContactPerson(postjob.getContactPerson());
			this.jobPostBO.setJobTitle(postjob.getJobTitle());
			this.jobPostBO.setId(postjob.getId());
			list.add(this.jobPostBO);
		}
		return list;
	}

	public List<JobPostBO> jobPostDetails(JobPostBO jobPostBO) {
		BaseContoller.LOGGER.entry();
		String jobDescription = null;
		String keySkills = null;
		final List<JobPostBO> list = new ArrayList<JobPostBO>();
		List<JobPostBO> jobPostLists = null;

		try {
			if (null != jobPostBO.getKeywords()
					&& null != jobPostBO.getOtherSalaryDetails()
					&& null != jobPostBO.getJobTitle()
					&& null != jobPostBO.getMaxSalary()) {

				jobPostLists = jobPostBO.getJobPostList();
			}
			if (jobPostBO.getKeywords().isEmpty()
					&& jobPostBO.getSearchElement().isEmpty()
					&& jobPostBO.getMinExp().isEmpty()) {
				jobPostBO = jobSeekerService.searchJob(jobPostBO);
				jobPostLists = jobPostBO.getCatagoriesList();
			} else {
				jobPostBO = this.jobSeekerService.jobSearch(jobPostBO);
				jobPostLists = jobPostBO.getJobPostList();
			}

			if (null != jobPostLists) {
				for (final JobPostBO postjob : jobPostLists) {
					jobPostBO = new JobPostBO();
					if (postjob.getJobDescription().length() > 250) {
						jobDescription = postjob.getJobDescription().substring(
								0, 250);
						jobPostBO.setJobDescription(jobDescription);
					} else {
						jobPostBO
								.setJobDescription(postjob.getJobDescription());
					}
					if (postjob.getKeywords().length() > 50) {
						keySkills = postjob.getKeywords().substring(0, 50);
						jobPostBO.setKeywords(keySkills);
					} else {
						jobPostBO.setKeywords(postjob.getKeywords());
					}
					jobPostBO.setCompanyName(postjob.getCompanyName());
					jobPostBO.setCreated(postjob.getCreated());
					jobPostBO.setExperiance(postjob.getExperiance());
					jobPostBO.setJobLocation(postjob.getJobLocation());
					jobPostBO.setSalary(postjob.getSalary());
					jobPostBO.setPostedBy(postjob.getPostedBy());
					jobPostBO.setContactNo(postjob.getContactNo());
					jobPostBO.setContactPerson(postjob.getContactPerson());
					jobPostBO.setJobTitle(postjob.getJobTitle());
					jobPostBO.setMinExp(postjob.getMinExp());
					jobPostBO.setMaxExp(postjob.getMaxExp());
					jobPostBO.setId(postjob.getId());
					jobPostBO.setsDate(postjob.getsDate());
					jobPostBO.seteDate(postjob.geteDate());
					list.add(jobPostBO);

				}
			}
		} catch (final MyJobKartException e) {
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("Jobpost search  failed:" + e.getMessage() + e);
			}
			LOGGER.info("Jobpost search failed:" + e.getMessage() + e);

		}
		BaseContoller.LOGGER.exit();
		return list;
	}

	public List<JobPostBO> jobdetails(Model model, HttpServletRequest request,
			JobPostBO jobPostBO) {
		BaseContoller.LOGGER.entry();
		String jobDescription = null;
		String keySkills = null;
		final List<JobPostBO> list = new ArrayList<JobPostBO>();
		final HttpSession session = request.getSession();
		try {

			// Retrieved JobseekerSavedJobList by JobseekerLoginID
			// List<SavejobBO> savejobList = new ArrayList<SavejobBO>();
			AppliedJobBO appliedJobBO = new AppliedJobBO();
			// SavejobBO savejobBO = new SavejobBO();
			List<JobPostBO> jobPostLists = null;

			final long jobseekerId = (Long) session.getAttribute("jobseekerId");
			appliedJobBO.setLoginId(jobseekerId);
			SavejobBO savejobBO = this.jobSeekerService
					.jobseekerSavedJob(appliedJobBO);
			List<SavejobBO> savejobList = savejobBO.getSavejobList();

			// Retrieved JobseekerAppliedJobList by JobseekerLoginId
			// List<AppliedJobBO> appliedJobList = new
			// ArrayList<AppliedJobBO>();
			appliedJobBO.setLoginId(jobseekerId);
			appliedJobBO = this.jobSeekerService.jobSeekerApplied(appliedJobBO);
			List<AppliedJobBO> appliedJobList = appliedJobBO
					.getAppliedJobList();

			if (null != jobPostBO.getSearchType()
					&& !jobPostBO.getSearchType().isEmpty()) {

				jobPostLists = jobPostBO.getJobPostList();
			}
			if (jobPostBO.getKeywords().isEmpty()
					&& jobPostBO.getSearchElement().isEmpty()
					&& jobPostBO.getMinExp().isEmpty()) {
				jobPostBO = jobSeekerService.searchJob(jobPostBO);
				jobPostLists = jobPostBO.getCatagoriesList();
			}

			else {
				jobPostBO = this.jobSeekerService.jobSearch(jobPostBO);
				jobPostLists = jobPostBO.getJobPostList();
			}
			List<JobPostBO> countCompany = new ArrayList<JobPostBO>();
			List<JobPostBO> countLocation = new ArrayList<JobPostBO>();
			List<JobPostBO> countTitle = new ArrayList<JobPostBO>();
			countCompany = jobPostBO.getCompanyList();
			countLocation = jobPostBO.getCountLocationList();
			model.addAttribute("locationList", countLocation);
			countTitle = jobPostBO.getCountTitleList();
			model.addAttribute("titleList", countTitle);
			model.addAttribute("countCompany", countCompany);

			this.appliedJobs(model, request);
			this.saveJobs(model, request);

			for (final JobPostBO jobPost : jobPostLists) {
				this.jobId = jobPost.getId();
				if (jobPost.getJobDescription().length() > 250) {
					jobDescription = jobPost.getJobDescription().substring(0,
							250);
					jobPost.setJobDescription(jobDescription);
				} else {
					jobPost.setJobDescription(jobPost.getJobDescription());
				}

				if (jobPost.getKeywords().length() > 50) {
					keySkills = jobPost.getKeywords().substring(0, 50);
					jobPostBO.setKeywords(keySkills);
				} else {
					jobPostBO.setKeywords(jobPost.getKeywords());
				}
				if (null != appliedJobList) {
					for (final AppliedJobBO ajob : appliedJobList) {
						if (this.jobId == ajob.getJobId()) {

							jobPost.setIsVisiable(false);
							break;
						} else {

							jobPost.setIsVisiable(true);
						}
					}
				} else {
					jobPost.setIsVisiable(true);
				}

				if (null != savejobList) {
					for (final SavejobBO saveJobBO : savejobList) {

						if (jobPost.getId() == saveJobBO.getJobId()) {
							jobPost.setIsdisable(false);
							break;
						} else {
							jobPost.setIsdisable(true);
						}

					}
				} else {
					jobPost.setIsdisable(true);
				}

				list.add(jobPost);
				// session.setAttribute("searchList", list);

			}
		} catch (final MyJobKartException j) {
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("Job detail search failed:" + j.getMessage() + j);
			}
			LOGGER.info("Job detail search  failed:" + j.getMessage() + j);

		}
		BaseContoller.LOGGER.exit();
		return list;

	}

	@RequestMapping(value = "/jobseeker_appliedtitle", method = RequestMethod.GET)
	public String appliedtitle(
			@Valid @ModelAttribute("searchjob") JobPostBO jobPostBO,
			BindingResult result, Model model, HttpServletRequest request)
			throws MyJobKartException {
		BaseContoller.LOGGER.entry();
		HttpSession session = request.getSession();
		if (null == session.getAttribute("jobseekerId")) {
			return "redirect:/jobseeker_sign_in.html";
		}

		String jobDescription = null;
		final List<JobPostBO> list = new ArrayList<JobPostBO>();

		try {
			long sno = 1;

			final long jobseekerId = (Long) session.getAttribute("jobseekerId");
			jobPostBO.setId(jobseekerId);
			jobPostBO = this.jobSeekerService.appliedJobSearch(jobPostBO);
			this.jobPostList = jobPostBO.getJobPostList();
			if (null == this.jobPostList || this.jobPostList.size() == 0) {
				model.addAttribute("message",
						"You have not applied any job..please find a job");
				model.addAttribute("clicktitle", "white");
			} else {
				for (final JobPostBO postjob : this.jobPostList) {
					jobPostBO = new JobPostBO();
					if (postjob.getJobDescription().length() > 250) {
						jobDescription = postjob.getJobDescription().substring(
								0, 250);
						jobPostBO.setJobDescription(jobDescription);
					} else {
						jobPostBO
								.setJobDescription(postjob.getJobDescription());
					}
					if (postjob.getKeywords().length() > 50) {
						postjob.getKeywords().substring(0, 50);
					} else {
						jobPostBO.setKeywords(postjob.getKeywords());
					}
					jobPostBO.setsNo(sno++);
					jobPostBO.setCompanyName(postjob.getCompanyName());
					jobPostBO.setCreated(postjob.getCreated());
					jobPostBO.seteDate(postjob.geteDate());
					jobPostBO.setJobLocation(postjob.getJobLocation());
					jobPostBO.setSalary(postjob.getSalary());
					jobPostBO.setPostedBy(postjob.getPostedBy());

					jobPostBO.setJobTitle(postjob.getJobTitle());
					jobPostBO.setId(postjob.getId());
					// jobPostBO.setIsVisiable(false);
					// jobPostBO.setIsdisable(false);
					list.add(jobPostBO);
					// session.setAttribute("searchList", list);
				}

				model.addAttribute("appliedJobTitle", list);
				model.addAttribute("clicktitle", "white");
			}

		} catch (final NullPointerException ne) {
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("Jobseeker applied title failed:"
						+ ne.getMessage() + ne);
			}
			LOGGER.info("Jobseeker applied title  failed:" + ne.getMessage()
					+ ne);

		}
		BaseContoller.LOGGER.exit();
		return "jobseeker_applied_jobs_map";
	}

	@RequestMapping(value = "/jobseeker_appliedmonth", method = RequestMethod.GET)
	public String appliedMonth(
			@Valid @ModelAttribute("searchjob") JobPostBO jobPostBO,
			BindingResult result, Model model, HttpServletRequest request)
			throws MyJobKartException {
		BaseContoller.LOGGER.entry();
		HttpSession session = request.getSession();
		if (null == session.getAttribute("jobseekerId")) {
			return "redirect:/jobseeker_sign_in.html";
		}

		String jobDescription = null;
		final List<JobPostBO> list = new ArrayList<JobPostBO>();

		try {
			long sno = 1;
			final long jobseekerId = (Long) session.getAttribute("jobseekerId");
			jobPostBO.setId(jobseekerId);
			jobPostBO = this.jobSeekerService.appliedJobSearchDate(jobPostBO);
			this.jobPostList = jobPostBO.getJobPostList();
			if (null == this.jobPostList || this.jobPostList.size() == 0) {
				model.addAttribute("message",
						"You have not applied any job..please find a job");
				model.addAttribute("clickmonth", "white");
			} else {
				for (final JobPostBO postjob : this.jobPostList) {
					jobPostBO = new JobPostBO();
					if (postjob.getJobDescription().length() > 250) {
						jobDescription = postjob.getJobDescription().substring(
								0, 250);
						jobPostBO.setJobDescription(jobDescription);
					} else {
						jobPostBO
								.setJobDescription(postjob.getJobDescription());
					}
					if (postjob.getKeywords().length() > 50) {
						postjob.getKeywords().substring(0, 50);
					} else {
						jobPostBO.setKeywords(postjob.getKeywords());
					}

					jobPostBO.setsNo(sno++);
					jobPostBO.setCompanyName(postjob.getCompanyName());
					jobPostBO.setCreated(postjob.getCreated());
					jobPostBO.seteDate(postjob.geteDate());
					jobPostBO.setJobLocation(postjob.getJobLocation());
					jobPostBO.setSalary(postjob.getSalary());
					jobPostBO.setMinSalary(postjob.getMinSalary());
					jobPostBO.setMaxSalary(postjob.getMaxSalary());
					jobPostBO.setMinExp(postjob.getMaxExp());
					jobPostBO.setMaxExp(postjob.getMaxExp());
					jobPostBO.setPostedBy(postjob.getPostedBy());
					jobPostBO.setJobTitle(postjob.getJobTitle());
					jobPostBO.setId(postjob.getId());
					// jobPostBO.setIsVisiable(false);
					// jobPostBO.setIsdisable(false);

					list.add(jobPostBO);
					// session.setAttribute("searchList", list);
				}
			}

			model.addAttribute("appliedJob6month", list);
			model.addAttribute("clickmonth", "white");

		} catch (final MyJobKartException jb) {
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("Jobseeker applied months failed:"
						+ jb.getMessage() + jb);
			}
			LOGGER.info("Jobseeker applied months  failed:" + jb.getMessage()
					+ jb);

		}
		BaseContoller.LOGGER.exit();
		return "jobseeker_applied_jobs_map";

	}

	@RequestMapping(value = "/jobseeker_appliedcompany", method = RequestMethod.GET)
	public String apliedjobs(
			@Valid @ModelAttribute("searchjob") JobPostBO jobPostBO,
			BindingResult result, Model model, HttpServletRequest request)
			throws MyJobKartException {
		BaseContoller.LOGGER.entry();
		HttpSession session = request.getSession();
		if (null == session.getAttribute("jobseekerId")) {
			return "redirect:/jobseeker_sign_in.html";
		}
		String jobDescription = null;
		final List<JobPostBO> list = new ArrayList<JobPostBO>();

		// List<JobPostBO> jobPostList = new ArrayList<JobPostBO>();
		try {

			long sno = 1;

			final long jobseekerId = (Long) session.getAttribute("jobseekerId");
			jobPostBO.setId(jobseekerId);
			jobPostBO = this.jobSeekerService.appliedJobSearch(jobPostBO);
			this.jobPostList = jobPostBO.getJobPostList();
			if (null == this.jobPostList || this.jobPostList.size() == 0) {
				model.addAttribute("message",
						"You have not applied any job..please find a job");
				model.addAttribute("clickcompany", "white");
			} else {
				for (final JobPostBO postjob : this.jobPostList) {
					jobPostBO = new JobPostBO();
					jobPostBO.setsNo(sno++);
					if (postjob.getJobDescription().length() > 250) {
						jobDescription = postjob.getJobDescription().substring(
								0, 250);
						jobPostBO.setJobDescription(jobDescription);
					} else {
						jobPostBO
								.setJobDescription(postjob.getJobDescription());
					}
					if (postjob.getKeywords().length() > 50) {
						postjob.getKeywords().substring(0, 50);
					} else {
						jobPostBO.setKeywords(postjob.getKeywords());
					}

					jobPostBO.setCompanyName(postjob.getCompanyName());
					jobPostBO.setCreated(postjob.getCreated());
					jobPostBO.seteDate(postjob.geteDate());
					jobPostBO.setJobLocation(postjob.getJobLocation());
					jobPostBO.setSalary(postjob.getSalary());
					jobPostBO.setPostedBy(postjob.getPostedBy());
					// jobPostBO.setIsVisiable(false);
					// jobPostBO.setIsdisable(false);
					jobPostBO.setJobTitle(postjob.getJobTitle());
					jobPostBO.setId(postjob.getId());
					list.add(jobPostBO);
					// session.setAttribute("searchList", list);
				}
				model.addAttribute("appliedCompany", list);
				model.addAttribute("clickcompany", "white");
			}

		} catch (final NullPointerException ne) {
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("Jobseeker applied company failed:"
						+ ne.getMessage() + ne);
			}
			LOGGER.info("Jobseeker applied company  failed:" + ne.getMessage()
					+ ne);

		}
		BaseContoller.LOGGER.exit();
		return "jobseeker_applied_jobs_map";
	}

	@RequestMapping(value = "/appliedJobImagehistryDisplay", method = RequestMethod.GET)
	public void showAppliedJobhistryImage(@RequestParam("id") Integer itemId,
			HttpServletResponse response, HttpServletRequest request)
			throws ServletException, IOException, SQLException,
			MyJobKartException {
		final String id = request.getParameter("id");
		final long imgid = Long.parseLong(id);

		// List<JobPostBO> jobImageList = new ArrayList<JobPostBO>();
		// jobImageList = this.employerService.getAllJobPost();
		JobPostBO jobPost = new JobPostBO();
		jobPost.setEmpId(imgid);
		jobPostBO = this.employerService.retrieveJobPost(jobPost);
		List<JobPostBO> jobImageList = jobPostBO.getJobPostList();

		EmployerBO employerbo = new EmployerBO();
		if (null != jobImageList) {
			for (JobPostBO jobPostBO : jobImageList) {
				if (jobPostBO.getId() == imgid) {

					long employerRegisterId = jobPostBO.getEmployerProfile()
							.getId();
					employerbo.setId(employerRegisterId);
					EmployerProfileBO employerProfileBO = new EmployerProfileBO();
					employerProfileBO.setEmployerRegistion(employerbo);
					employerProfileBO = employerService
							.retriveEmployer(employerProfileBO);

					if (employerProfileBO.getEmployerRegistion().getId() == employerRegisterId) {
						if (null != employerProfileBO.getCompanyLogo()) {
							response.setContentType("image/jpeg, image/jpg, image/png, image/gif");
							response.getOutputStream().write(
									employerProfileBO.getCompanyLogo()
											.getBytes(
													1,
													(int) employerProfileBO
															.getCompanyLogo()
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
									employerProfileBO.getCompanyLogo()
											.getBytes(
													1,
													(int) employerProfileBO
															.getCompanyLogo()
															.length()));
							response.getOutputStream().close();
						}
					}
				}
			}
		}

	}

	@RequestMapping(value = "/walkin_jobs", method = RequestMethod.GET)
	public String walkinJobs(Model model, HttpServletRequest request)
			throws MyJobKartException {

		BaseContoller.LOGGER.entry();
		final List<WalkinBO> list = new ArrayList<WalkinBO>();

		WalkinBO walkinBO = new WalkinBO();
		List<WalkinBO> walkinBOList = null;

		walkinBOList = this.employerService.retrieveWalkinJobs(new WalkinBO());

		final String sucessmessage = request.getParameter("sucessmessage");
		model.addAttribute("sucessmessage", sucessmessage);
		final String paging = request.getParameter("page");
		try {
			int page = 1;
			if (null != paging) {
				page = Integer.parseInt(paging);
				final ResponseObject<WalkinBO> reponseObject = this
						.jbpagination(page, walkinBOList);
				model.addAttribute("JobDescription", reponseObject);
			} else {
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
								String jobDescription = postjob
										.getJobDescription().substring(0, 250);
								postjob.setJobDescription(jobDescription);
							} else {
								postjob.setJobDescription(postjob
										.getJobDescription());
							}

							if (postjob.getKeywords().length() > 50) {
								String keySkill = postjob.getKeywords()
										.substring(0, 50);
								postjob.setKeywords(keySkill);
							} else {
								postjob.setKeywords(postjob.getKeywords());
							}

							if (postjob.getIsActive()) {
								postjob.setStatus("Active");
							} else {
								postjob.setStatus("De-Active");
							}
							walkinBO = postjob;
							list.add(walkinBO);
						}
						walkinBOList = list;
					}
					final ResponseObject<JobPostBO> responseObject = this
							.jbpagination(1, walkinBOList);
					model.addAttribute("JobDescription", responseObject);
				}
			}
		} catch (final Exception he) {
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("walkin_jobs has failed:" + he.getMessage() + he);

			}
			LOGGER.info("walkin_jobs has failed:" + he.getMessage() + he);

		}

		return "walkin_jobs";
	}

	/**
	 * @param i
	 * @param list
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	private ResponseObject jbpagination(int page, List<WalkinBO> List) {
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
		final List<WalkinBO> list = List;
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

	@RequestMapping(value = "/walkin_search_details", method = RequestMethod.GET)
	public String walkinJobsearch(Model model, HttpServletRequest request)
			throws MyJobKartException {

		BaseContoller.LOGGER.entry();
		HttpSession session = request.getSession();
		String jobId = request.getParameter("id");
		// String walkinId = request.getParameter("id");
		final long walkinPostId = Long.parseLong(jobId);
		WalkinBO walkinBO = new WalkinBO();
		if (null != jobId) {
			walkinBO.setId(walkinPostId);
		} else {
			final long id = (Long) session.getAttribute("loginId");
			walkinBO.setId(id);
		}
		walkinBO = this.employerService.retriveWalkin(walkinBO);

		final String walkinpostid = request.getParameter("id");
		try {

			final long walkinpostId = Long.parseLong(walkinpostid);
			model.addAttribute("jobPostDetail", walkinBO);

		} catch (final NullPointerException ne) {
			if (LOGGER.isDebugEnabled()) {
				LOGGER.debug("walkin_jobpost_details has failed:"
						+ ne.getMessage() + ne);
			}
			LOGGER.info("walkin_jobpost_details has failed:" + ne.getMessage()
					+ ne);
		}

		return "walkin_search_details";

	}

}
