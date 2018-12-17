package com.myjobkart.service;

import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

import javax.sql.rowset.serial.SerialException;

import com.myjobkart.bo.AppliedJobBO;
import com.myjobkart.bo.EmployerActivityBO;
import com.myjobkart.bo.EmployerProfileBO;
import com.myjobkart.bo.EntityBO;
import com.myjobkart.bo.JobAlertBO;
import com.myjobkart.bo.JobPostBO;
import com.myjobkart.bo.JobSeekerLoginBO;
import com.myjobkart.bo.JobseekerActivityBO;
import com.myjobkart.bo.JobseekerBO;
import com.myjobkart.bo.JobseekerProfileBO;
import com.myjobkart.bo.PaymentBO;
import com.myjobkart.bo.SavejobBO;
import com.myjobkart.bo.ViewJobseekerBO;
import com.myjobkart.exception.MyJobKartException;

/**
 * Owner : Scube Technologies Created Date: Nov-22-2014 Created by :
 * Sathishkumar.s Description : JobSeekerServiceImpl Class is a Class which is
 * responsible for access the data from Controller then convert it into
 * persistent Object then sent it into DAO class. Reviewed by :
 */
public interface JobSeekerService<E> extends GenericService<JobseekerBO> {

	boolean findByEmail(String emailAddress) throws MyJobKartException;

	JobSeekerLoginBO jobseekerApproved(JobSeekerLoginBO jobseekerLoginBO)
			throws MyJobKartException;

	List<JobseekerBO> getAllJobseekers();

	List<JobseekerBO> getjobseeker(JobseekerBO jobSeekerRegistrationBO);
	
	List<JobseekerActivityBO> retriveJobseekerActivity(JobseekerActivityBO jobseekerActivityBO);

	/**
	 * This Method Used To Job seeker Login
	 * 
	 * @param jobSeekerLoginBO
	 * @return
	 * @throws MyJobKartException
	 */
	JobSeekerLoginBO jobseekerSignin(JobSeekerLoginBO jobSeekerLoginBO)
			throws MyJobKartException;

	JobSeekerLoginBO active(JobSeekerLoginBO jobSeekerLoginBO)
			throws MyJobKartException;

	boolean jobseekerForgetPassword(JobSeekerLoginBO jobSeekerLoginBO)
			throws MyJobKartException, FileNotFoundException;

	/**
	 * This Method Used To Perform change password after login.
	 * 
	 * @param changePassword
	 * @return
	 */

	boolean jobseekerChangePasswordAfterLogin(JobSeekerLoginBO changePassword);

	boolean jobseekerActivation(String inputParam) throws MyJobKartException;
	/**
	 * This Method used to perform the job seeker create profile.
	 * 
	 * @param profile
	 * @return
	 */

	JobseekerProfileBO jobseekerCreateProfile(JobseekerProfileBO profile);
	
	JobAlertBO createJobAlert(JobAlertBO alert) throws MyJobKartException;

	/**
	 * This Method Used To perform the job seeker delete profile.
	 * 
	 * @param deleteProfile
	 * @return
	 * @throws MyJobKartException
	 */
	JobseekerProfileBO jobseekerDeleteProfile(JobseekerProfileBO deleteProfile)
			throws MyJobKartException;
	/**
	 * This method used to perform the delete the saved job details.
	 * 
	 * @param savejobBO
	 * @return
	 * @throws MyJobKartException
	 */
	SavejobBO jobseekerDeleteSavedJobs(SavejobBO savejobBO)
			throws MyJobKartException;

	/**
	 * This method used to perform the delete the applied jobs.
	 * 
	 * @param appliedJobBO
	 * @return
	 * @throws MyJobKartException
	 */
	AppliedJobBO jobseekerDeleteAppliedJobs(AppliedJobBO appliedJobBO)
			throws MyJobKartException;

	JobseekerProfileBO updateProfile(JobseekerProfileBO updateProfile)
			throws MyJobKartException;
	
	JobseekerProfileBO eduProfile(JobseekerProfileBO updateProfile)
			throws MyJobKartException;
	
	JobseekerProfileBO expDetails(JobseekerProfileBO updateProfile)
			throws MyJobKartException;

	JobseekerProfileBO retriveJobseeker(JobseekerProfileBO reteriveprofile)
			throws MyJobKartException, SerialException, SQLException;

	JobPostBO jobSearch(JobPostBO jobPostBO) throws MyJobKartException;
	

	
	JobPostBO jobTitleCount(JobPostBO jobPostBO) throws MyJobKartException;
	
	JobPostBO joblocationCount(JobPostBO jobPostBO) throws MyJobKartException;

	EmployerProfileBO companyDetails(long employerId) throws MyJobKartException;

	JobseekerProfileBO retriveJobseeker() throws MyJobKartException;

	JobPostBO searchJob(JobPostBO jobPostBO) throws MyJobKartException;

	boolean profileStatus(JobseekerProfileBO jobseekerProfileBO);

	JobPostBO refineResult(JobPostBO jobPostBO) throws MyJobKartException;

	JobPostBO jobsearchlist(JobPostBO jobPostBO) throws MyJobKartException;

	
	
	/**
	 * This Method used to perform the job seeker applied jobs.
	 * 
	 * @param appliedJobBO
	 * @param saveJobId
	 * @return
	 */
	boolean jobseekerAppliedJob(AppliedJobBO appliedJobBO, long saveJobId);
	boolean checkProfileId(long jobseekerId);

	boolean savedJob(SavejobBO savejobBO);

	/**
	 * This Method used to perform the retrieve the saved job details.
	 * 
	 * @param savejobBO
	 * @return
	 * @throws MyJobKartException
	 */
	SavejobBO reteriveSavedJobs(SavejobBO savejobBO) throws MyJobKartException;

	AppliedJobBO reteriveAppliedJobs(AppliedJobBO appliedJobBO)
			throws MyJobKartException;

	boolean savejob(long id) throws MyJobKartException;

	boolean applied(long id) throws MyJobKartException;

	AppliedJobBO jobSeekerApplied(AppliedJobBO appliedJobBO);

	SavejobBO jobseekerSavedJob(AppliedJobBO appliedJobBO);

	JobseekerBO retriveRegisteredJobseeker() throws MyJobKartException;
	
	JobseekerBO retriveRegisteredJobseeker(long registerId)
			throws MyJobKartException;

	JobseekerBO updateJobseeker(JobseekerBO jobseekerBO)
			throws MyJobKartException;

	JobseekerBO deleteJobseeker(JobseekerBO deleteProfile)
			throws MyJobKartException;

	JobseekerProfileBO retrieveJobseekerProfile() throws MyJobKartException;

	JobPostBO appliedJobSearchDate(JobPostBO jobPostBO)
			throws MyJobKartException;

	JobPostBO appliedJobSearch(JobPostBO jobPostBO) throws MyJobKartException;

	SavejobBO reteriveSavedJob(SavejobBO savejobBO) throws MyJobKartException;

	JobseekerBO renewalRegisteredJobseeker() throws MyJobKartException;

	boolean profileStatus1(JobseekerProfileBO jobseekerProfileBO);
	
	boolean activeJobseeker (JobSeekerLoginBO jobseekerProfileBO);

	EmployerProfileBO employeeDetail(ViewJobseekerBO empdetail);
	
	//PaymentBO jobseekerAddPayment(PaymentBO paymentBO);

	JobseekerBO renewalJobseekerAlert(String email);
	
	PaymentBO productsEnrolledList(long registerId);

	PaymentBO deleteJobseekerEnrolledDetails(PaymentBO savejobBO);

	PaymentBO lastMonthPaymentList(long registerId);

	PaymentBO retriveJobseekerPayment() throws MyJobKartException;

	SavejobBO retriveJobseekersSavedJobs() throws MyJobKartException;

	AppliedJobBO retriveJobseekersAppliedJobs() throws MyJobKartException;

    JobAlertBO createAlert(JobAlertBO jobalert) throws MyJobKartException;

	/**
	 * @param jobSeekerProfileBO
	 * @return
	 */
	JobseekerProfileBO getJobSeekerProfile(JobseekerProfileBO jobSeekerProfileBO);

	/**
	 * @param viewJobseekerBO
	 * @return
	 */
	List<ViewJobseekerBO> viewJobSeekerHistory(ViewJobseekerBO viewJobseekerBO);
	JobseekerBO getJobseekerRegistration(JobseekerBO jobSeekerBO);

	/**
	 * @param login
	 * @return
	 */
	boolean activeJobseekerStatus(JobSeekerLoginBO login);

	/**
	 * @param updateProfile 
	 * @return
	 * @throws MyJobKartException 
	 */
	JobseekerProfileBO retrieveJobseekerResume(JobseekerProfileBO updateProfile) throws MyJobKartException;


	/**
	 * @param retrivealert
	 * @return
	 */
	List<JobAlertBO> retriveJobAlert(JobAlertBO retrivealert);

	/**
	 * @param deleteAlert
	 * @return
	 */
	JobAlertBO deleteAlert(JobAlertBO deleteAlert) throws MyJobKartException;

	/**
	 * @param statusBO
	 * @return
	 */
	boolean jobalertStatus(JobAlertBO statusBO);

	/**
	 * @param alertBO
	 * @return
	 */
	JobAlertBO retriveAlert(JobAlertBO alertBO) throws MyJobKartException;

	/**
	 * @param updateJobAlert
	 * @return
	 */
	JobAlertBO updateAlert(JobAlertBO updateJobAlert) throws MyJobKartException;

	/**
	 * @param reteriveprofile
	 * @return
	 */
	/*JobAlertBO retriveJobAlert(JobseekerProfileBO reteriveprofile);*/

	
	boolean emailVerifications(String email);

	/**
	 * @param email
	 * @return
	 */
	boolean Mobile_verification(String mobileNo);
	
	JobseekerProfileBO retriveJobseekerBO(long companyId);

	/**
	 * @param jobPostBO
	 * @return
	 * @throws MyJobKartException
	 */
	JobPostBO JobByTitle(JobPostBO jobPostBO) throws MyJobKartException;

}

	