package com.myjobkart.dao;

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
import com.myjobkart.vo.AppliedJobVO;
import com.myjobkart.vo.EmployerActivityVO;
import com.myjobkart.vo.EmployerAlertVO;
import com.myjobkart.vo.EmployerVO;
import com.myjobkart.vo.EntrolledSeviceVO;
import com.myjobkart.vo.JobAlertVO;
import com.myjobkart.vo.JobPostVO;
import com.myjobkart.vo.JobseekerActivityVO;
import com.myjobkart.vo.JobseekerEducationVO;
import com.myjobkart.vo.JobseekerLoginVO;
import com.myjobkart.vo.JobseekerProductServiceVO;
import com.myjobkart.vo.JobseekerProfessionalVO;
import com.myjobkart.vo.JobseekerProfileVO;
import com.myjobkart.vo.JobseekerVO;
import com.myjobkart.vo.SaveJobVO;


/**
 * @author Administrator
 * @param <T>
 * 
 */
public interface JobSeekerDAO extends GenericDAO<JobseekerVO> {

	void jobSeekerApproved(JobseekerLoginVO jobseekerLogin)
			throws MyJobKartException;

	List<JobseekerVO> getjobseekers(JobseekerVO jobseekerRegistration)
			throws MyJobKartException;

	JobseekerLoginVO jobseekerForgetPassword(String string, String emailAddress)
			throws MyJobKartException;

	JobseekerLoginVO jobseekerChangePasswordAfterLogin(
			JobseekerLoginVO jobseekerLoginVO);

	long jobseekerActivation(JobseekerLoginVO jobseekerLoginVO,
			JobseekerProductServiceVO jobseekerProductServiceVO);

	JobseekerProfileVO jobseekerCreateProfile(
			JobseekerProfileVO jobseekerProfileVO);
	
	long createJobAlert(JobAlertVO jobalertVO);
	
	public JobseekerVO findByEmail(String string, String emailAddress);
	
	public JobseekerVO findByJobseekerEmail(String emailAddress);

	

	JobseekerProfileBO retriveJobseeker(
			JobseekerProfileBO jobseekerProfileBO) throws MyJobKartException,
			SerialException, SQLException;
	
	
	JobseekerProfileVO updateProfile(JobseekerProfileVO updateProfile)
			throws MyJobKartException;
	
	JobseekerEducationVO eduProfile(JobseekerEducationVO vo)
			throws MyJobKartException;
	
	JobseekerProfessionalVO experienceDetails(JobseekerProfessionalVO vo)
			throws MyJobKartException;

	/**
	 * This Method Used To Perform The Delete The Job seeker Delete profile.
	 * 
	 * @param deleteProfile
	 * @return
	 * @throws MyJobKartException
	 */
	int jobseekerDeleteProfile(JobseekerProfileVO deleteProfile)
			throws MyJobKartException;
	
	int jobseekerDeleteSavedJobs(SaveJobVO saveJobVO) throws MyJobKartException;

	int jobseekerDeleteAppliedJobs(AppliedJobVO appliedJobVO)
			throws MyJobKartException;

	JobPostBO jobSearch(JobPostBO jobPostBO) throws MyJobKartException;
	
	JobPostBO JobByTitle(JobPostBO jobPostBO) throws MyJobKartException;
	
	JobPostBO jobTitleCount(JobPostBO jobPostBO) throws MyJobKartException;
	
	JobPostBO joblocationCount(JobPostBO jobPostBO) throws MyJobKartException;

	List<JobPostBO> refineResult(JobPostBO jobPostBO)
			throws MyJobKartException;

	EmployerProfileBO companyDetails(long employerId)
			throws MyJobKartException;

	List<JobseekerProfileBO> retriveJobseeker() throws MyJobKartException;
	
	List<JobPostVO>employerAlert(String keySkills);
	
	EmployerAlertVO addEmployerAlert(List<EmployerAlertVO> employerAlertList);
	
	
	
	JobPostBO searchJob(JobPostBO jobPostBO)
			throws MyJobKartException;

	JobseekerProfileVO profileStatus(JobseekerProfileVO jobseekerProfileVO);

	JobPostBO jobsearchlist(JobPostBO jobPostBO) throws MyJobKartException;

	/**
	 * This method used to perform the job seeker applied jobs.
	 * 
	 * @param appliedJobVO
	 * @param saveJobId
	 * @return
	 */
	long jobseekerAppliedJob(AppliedJobVO appliedJobVO, long saveJobId);

	List<JobseekerProfileBO> checkProfileId(long jobseekerId);

	long savedJob(SaveJobVO saveJobVO);

	List<SavejobBO> reteriveSavedJobs(SavejobBO savejobBO)
			throws MyJobKartException;

	List<AppliedJobBO> reteriveAppliedJobs(AppliedJobBO appliedJobBO)
			throws MyJobKartException;

	SaveJobVO savejob(long id);

	AppliedJobVO applied(long id);

	List<AppliedJobBO> jobSeekerApplied(AppliedJobBO appliedJobBO);
	
	public List<JobseekerActivityBO> retriveJobseekerActivity(JobseekerActivityBO jobseekerActivityBO);

	List<SavejobBO> jobseekerSavedJob(AppliedJobBO appliedJobBO);

	List<JobseekerBO> retriveRegisteredJobseeker() throws MyJobKartException;
	
	List<JobseekerBO> retriveRegisteredJobseeker(long registerId) throws MyJobKartException;

	List<JobseekerBO> renewalRegisteredJobseeker() throws MyJobKartException;

	int deleteJobseeker(JobseekerVO deleteProfile) throws MyJobKartException;

	JobseekerVO updateJobseeker(JobseekerVO jobseekerVO)
			throws MyJobKartException;

	List<JobseekerProfileBO> retrieveJobseekerProfile()
			throws MyJobKartException;

	List<JobPostBO> appliedJobSearch(JobPostBO jobPostBO);

	List<JobPostBO> appliedJobSearchDate(JobPostBO jobPostBO);

	List<SavejobBO> reteriveSavedJob(SavejobBO savejobBO)
			throws MyJobKartException;

	JobseekerVO profileStatus1(JobseekerVO jobseekerProfileVO);
	
	JobseekerLoginVO activeJobseeker(JobseekerLoginVO loginVO,JobseekerProductServiceVO jobseekerProductServiceVO) throws MyJobKartException;

	

	EmployerProfileBO employeeDetail(ViewJobseekerBO empdetail);

	List<JobseekerBO> renerenewalJobseekerAlert(String email);

	List<PaymentBO> productsEnrolledList(long registerId);

	int deleteJobseekerEnrolledDetails(EntrolledSeviceVO saveJobVO);

	List<PaymentBO> lastMonthPaymentList(long registerId);

	List<PaymentBO> retriveJobseekerPayment();

	List<SavejobBO> retriveJobseekersSavedJobs();

	List<AppliedJobBO> retriveJobseekersAppliedJobs();
	
	JobseekerProfileVO getprofileId(JobseekerProfileVO profVO);
	
	JobseekerEducationVO getEducation(JobseekerEducationVO educationVO);
	
	JobseekerProfessionalVO getExperience(JobseekerProfessionalVO professionalVO);

	/**
	 * @param viewJobseekerBO
	 * @return
	 */
	List<ViewJobseekerBO> viewJobSeekerHistory(ViewJobseekerBO viewJobseekerBO);
	
	
	JobseekerVO getJobseekerRegistration(JobseekerVO jobseekerVO);

	/**
	 * @param jobseekerVO
	 * @return
	 */
	JobSeekerLoginBO activeJobseekerStatus(JobSeekerLoginBO jobseekerLoginBO);

	/**
	 * @param profile
	 * @return
	 */
	List<JobseekerProfileBO> retrieveJobseekerResume(JobseekerProfileBO profile);
	
	
	boolean emailVerifications(String email);


	/**
	 * @param retrivealert 
	 * @return
	 */
	List<JobAlertBO> retriveJobAlert(JobAlertBO retrivealert);

	/**
	 * @param jobalertVO
	 * @return
	 * @throws MyJobKartException 
	 */
	int deleteAlert(JobAlertBO jobAlertBO) throws MyJobKartException;

	/**
	 * @param jobalertVO
	 * @return
	 */
	JobAlertVO jobalertStatus(JobAlertVO jobalertVO);

	/**
	 * @param alertVO
	 */
	boolean updateAlert(JobAlertVO alertVO);
	
	JobAlertVO updateAlertid(JobAlertVO alertVO);

	/**
	 * @return
	 */
	JobAlertBO retriveAlert(JobAlertBO alertBO) throws MyJobKartException ;

	/**
	 * @return
	 */


	

	/**
	 * @param email
	 * @return
	 */
	boolean Mobile_verification(String mobileNo);
	
	public long activity(JobseekerActivityVO activityVO);
	
    List<JobseekerProfileBO> retriveJobseekerBO(long companyId);



}
