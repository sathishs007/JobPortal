/**
 * 
 */
package com.myjobkart.service;

import java.sql.SQLException;
import java.util.List;

import javax.sql.rowset.serial.SerialException;

import com.myjobkart.bo.AppliedJobBO;
import com.myjobkart.bo.BannerPostBO;
import com.myjobkart.bo.BookingBO;
import com.myjobkart.bo.CompanyEntity;
import com.myjobkart.bo.EmployerActivityBO;
import com.myjobkart.bo.EmployerBO;
import com.myjobkart.bo.EmployerLoginBO;
import com.myjobkart.bo.EmployerProfileBO;
import com.myjobkart.bo.EmployerSubuserBO;
import com.myjobkart.bo.FeedbackBO;
import com.myjobkart.bo.JobPostBO;
import com.myjobkart.bo.JobseekerProfileBO;
import com.myjobkart.bo.PaymentBO;
import com.myjobkart.bo.ProductBO;
import com.myjobkart.bo.SaveCandidateBO;
import com.myjobkart.bo.ShortListCandidate;
import com.myjobkart.bo.ViewJobseekerBO;
import com.myjobkart.bo.WalkinBO;
import com.myjobkart.exception.MyJobKartException;
import com.myjobkart.vo.EmployerjobAlertsVO;
import com.myjobkart.vo.JobseekerActivityVO;
import com.paypal.api.payments.Payment;

/**
 * Owner : Scube Technologies Created Date: Nov-20-2014 Created by : Aravindhan
 * Description : Employer Service interface is used to call the appropriate
 * services to do the business logic required for the system and here it extends
 * the Generic service which is used to extends the appropriate generic methods
 * in the service layer. Reviewed by :
 * 
 */
public interface EmployerService<E> extends GenericService<EmployerBO> {

	/**
	 * This method is used to approve the employer after registering in the
	 * system and the appropriate service methods will be called from here to do
	 * the busines logic.
	 * 
	 * @param loginBO
	 * @throws MyJobKartException
	 */
	EmployerLoginBO approveEmployer(EmployerLoginBO loginBO)
			throws MyJobKartException;

	/**
	 * This method used to search and find mail id.
	 * 
	 * @param emailAddress
	 * @return
	 * @throws MyJobKartException
	 */
	boolean findByEmail(String emailAddress) throws MyJobKartException;

	/**
	 * 
	 * @return
	 */

	List<EmployerBO> getAllEmployers();

	void updateOrder(BookingBO orderBO, Payment payment);



	List<JobPostBO> getAllJobPost();

	List<EmployerProfileBO> retriveEmployerProfile();

	/**
	 * This method used for getunapproveEmployers list
	 * 
	 * @return
	 * @throws MyJobKartException
	 */

	PaymentBO addPayment(PaymentBO paymentBO);

	List<EmployerBO> getemployers(EmployerBO employerRegistrationBO)
			throws MyJobKartException;

	public List<String> getAllEmailList() throws MyJobKartException;

	String findByLogin(String emailAddress, String password)
			throws MyJobKartException;

	EmployerLoginBO authendicate(EmployerLoginBO employerLoginBO)
			throws MyJobKartException;

	/*public List<EmployerjobAlertsVO> employerjob_alert();*/

	boolean changeAuthendication(EmployerLoginBO changePassword)
			throws MyJobKartException;

	boolean forgetAuthentication(EmployerLoginBO employerLoginBO)
			throws MyJobKartException;

	boolean employerActivation(String inputParam) throws MyJobKartException;

	boolean createProfile(EmployerProfileBO profile)
			throws MyJobKartException, SQLException;

	EmployerProfileBO retriveEmployer(EmployerProfileBO reteriveprofile)
			throws MyJobKartException;

	public EmployerBO getEmployerRegistration(EmployerLoginBO employerLogin);

	EmployerProfileBO updateProfile(EmployerProfileBO updateProfile)
			throws MyJobKartException, SQLException;

	EmployerProfileBO deleteProfile(EmployerProfileBO updateProfile)
			throws MyJobKartException;

	SaveCandidateBO deleteProfile(SaveCandidateBO deleteProfile)
			throws MyJobKartException;

	AppliedJobBO deleteAppliedCandidate(AppliedJobBO deleteCandidate)
			throws MyJobKartException;

	long jobPost(List<JobPostBO> jobPostBO) throws MyJobKartException;

	JobseekerProfileBO searchJobseeker(JobseekerProfileBO jobseekerProfile)
			throws MyJobKartException;

	//JobPostBO retriveEmployer() throws MyJobKartException;

	List<CompanyEntity> retrieveCompanyList();

	JobPostBO reteriveJobPost(JobPostBO appliedJobBO)
			throws MyJobKartException, SerialException, SQLException;



	boolean profileStatus(EmployerProfileBO employerProfileBO);

	boolean updateSubUser(EmployerSubuserBO subUser);

	JobseekerProfileBO refineResults(JobseekerProfileBO profileBO)
			throws MyJobKartException;

	AppliedJobBO reteriveAppliedJobs(AppliedJobBO appliedJobBO)
			throws MyJobKartException, SerialException, SQLException;

	JobPostBO updateJobPost(JobPostBO jobPostBO) throws MyJobKartException;

	JobPostBO retrieveJobPost(JobPostBO jobPostBO) throws MyJobKartException;

	boolean shortlistCandidate(ShortListCandidate shortListCandidate)
			throws MyJobKartException;

	boolean saveProfile(SaveCandidateBO saveCandidateBO);

	boolean activeEmployer(EmployerLoginBO login);

	boolean employeerProfileCheck(EmployerProfileBO profileCheck);

	SaveCandidateBO reteriveCandidate(SaveCandidateBO saveCandidateBO)
			throws MyJobKartException;

	JobPostBO deleteJobPost(JobPostBO deleteProfile) throws MyJobKartException;

	EmployerBO retrieveRegisteredEmployer() throws MyJobKartException, SerialException, SQLException;

	EmployerBO retrieveRegisteredEmployer(long employerRegisterId)
			throws MyJobKartException, SerialException, SQLException;

	EmployerBO renewelRegisteredEmployer() throws MyJobKartException, SerialException, SQLException;

	EmployerBO updateEmployer(EmployerBO updateProfile)
			throws MyJobKartException;

	EmployerBO deleteEmployer(EmployerBO deleteProfile)
			throws MyJobKartException;

	SaveCandidateBO employeerSaveResume(SaveCandidateBO saveCandidateBO);

	boolean profileStatus1(EmployerProfileBO employerProfileBO);

	void saveProfileView(ViewJobseekerBO viewBo);

	BookingBO createOrder(BookingBO orderBO);

	PaymentBO addPayments(PaymentBO paymentBO);

	SaveCandidateBO getJobseekerId(SaveCandidateBO candidateBO);

	JobPostBO getJobposting(JobPostBO jobPostBO);

	JobPostBO getShortlistId(JobPostBO candidate);

	boolean saveBannerPost(BannerPostBO bannerpost);

	List<BannerPostBO> retrieveBannerList(String fileName)
			throws SerialException, SQLException;

	List<BannerPostBO> renewelBannerList(long id) throws SerialException,
	SQLException;

	List<BannerPostBO> renewelBannarList() throws SerialException, SQLException;

	EmployerBO employerRenewalAlert(String email) throws SerialException, SQLException;

	BannerPostBO deleteBannerList(BannerPostBO deleteProfile);

	boolean bannerName(BannerPostBO bannerpost);

	BannerPostBO updateBanner(BannerPostBO bannerPostBO);

	PaymentBO productsEnrolledList(long loginId);

	PaymentBO deleteJobseekerEnrolledDetails(PaymentBO savejobBO);

	PaymentBO employerLastMonthEnrolledList(long registerId);

	PaymentBO employerPaymentHistory();

	SaveCandidateBO reteriveCandidate();

	ShortListCandidate shortListCandidates();

	ShortListCandidate shortListCandidatesView(
			ShortListCandidate shortlistCandidate);

	/**
	 * @return
	 */
	List<JobPostBO> viewRecruiters(String val);

	public EmployerLoginBO activatedEmployers(EmployerLoginBO employerLoginBO);

	/**
	 * @param jobPostBO
	 * @return
	 */
	boolean jobPostProfileStatus(JobPostBO jobPostBO);

	/**
	 * @param shortListCandidateBO
	 * @return
	 */
	ShortListCandidate shortListDeleteProfile(
			ShortListCandidate shortListCandidateBO);

	/**
	 * @param bannerpost
	 * @return
	 */
	BannerPostBO payBanner(BannerPostBO bannerpost);

	/**
	 * @param empId
	 * @return
	 */
	EmployerBO employerRegisteration(EmployerBO employerBO);

	List<EmployerBO> retrivecount();

	public List<ProductBO> getProductList();

	public FeedbackBO feedbackBO(FeedbackBO feedbackBO);

	/**
	 * @param login
	 * @return
	 */
	boolean empprofileStatusActive(EmployerLoginBO login);

	/**
	 * @return
	 */
	List<JobPostBO> jobbyExperience();

	/**
	 * @return
	 */
	List<JobPostBO> jobbyLocation();

	boolean emailVerification(String email);

	boolean Mobile_verifications(String mobileNumber);

	List<EmployerActivityBO> retriveEmpactivity(EmployerActivityBO employerActivityBO);

	boolean createSubuserProfile(EmployerSubuserBO profile)
			throws MyJobKartException, SQLException;

	boolean findbyEmailSubuser(String emailAdress) throws MyJobKartException;

	List<EmployerSubuserBO> retriveSubusers(EmployerSubuserBO reteiveprofile)
			throws MyJobKartException;

	EmployerSubuserBO subuserdelete(EmployerSubuserBO updateSubuser)
			throws MyJobKartException;

	/**
	 * @param updatesubuser
	 * @return
	 * @throws SQLException
	 */
	EmployerSubuserBO updateSubuser(EmployerSubuserBO updatesubuser)
			throws MyJobKartException, SQLException;

	/**
	 * @param retriveprofile
	 * @return
	 * @throws MyJobKartException
	 */
	EmployerSubuserBO retriveSubuser(EmployerSubuserBO retriveprofile)
			throws MyJobKartException;

	/**
	 * @param retriveSubuser
	 * @return
	 */

	long getSubuserCount(long id);

	/* boolean subuserStatus(EmployerSubuserBO subuserProfile); */

	long retriveCompanyId(List<String> companyName);

	BookingBO getOrderByOrderId(String orderId);

	void updateOrderStatus(String orderId, String status);


	boolean getemployerProduct(long empReg);


	long createWalkinprofile(List<WalkinBO> walkinList) throws MyJobKartException;

	List<WalkinBO> retrieveWalkinJobs(WalkinBO walkinBO) throws MyJobKartException;

	WalkinBO retriveWalkin(WalkinBO walkinBO);


	boolean walkinStatus(WalkinBO walkinBO);



	WalkinBO deleteWalkin(WalkinBO walkinBO) throws MyJobKartException;

	WalkinBO updateWalkin(WalkinBO walkinBO)throws MyJobKartException;

	/**
	 * @param jobPostBO
	 * @return
	 * @throws SQLException 
	 * @throws SerialException 
	 */
	JobPostBO retriveEmployer(JobPostBO jobPostBO) throws SerialException, SQLException;

	public List<EmployerjobAlertsVO> employerjob_alert();
}
