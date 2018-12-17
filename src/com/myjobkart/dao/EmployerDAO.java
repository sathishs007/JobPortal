/**
 * 
 */
package com.myjobkart.dao;

import java.sql.SQLException;
import java.util.List;
import java.util.Set;

import javax.sql.rowset.serial.SerialException;

import com.myjobkart.bo.BookingBO;
import com.myjobkart.vo.BookingVO;
import com.myjobkart.bo.AppliedJobBO;
import com.myjobkart.bo.BannerPostBO;
import com.myjobkart.bo.CompanyEntity;
import com.myjobkart.bo.EmployerActivityBO;
import com.myjobkart.bo.EmployerBO;
import com.myjobkart.bo.EmployerProfileBO;
import com.myjobkart.bo.EmployerSubuserBO;
import com.myjobkart.bo.JobPostBO;
import com.myjobkart.bo.JobseekerProfileBO;
import com.myjobkart.bo.PaymentBO;
import com.myjobkart.bo.ProductBO;
import com.myjobkart.bo.SaveCandidateBO;
import com.myjobkart.bo.ShortListCandidate;
import com.myjobkart.bo.ViewJobseekerBO;
import com.myjobkart.bo.WalkinBO;
import com.myjobkart.exception.MyJobKartException;
import com.myjobkart.vo.AppliedJobVO;
import com.myjobkart.vo.BannerPostVO;
import com.myjobkart.vo.EmployerActivityVO;
import com.myjobkart.vo.EmployerLoginVO;
import com.myjobkart.vo.EmployerProductServiceVO;
import com.myjobkart.vo.EmployerProfileVO;
import com.myjobkart.vo.EmployerSubuserVO;
import com.myjobkart.vo.EmployerVO;
import com.myjobkart.vo.EmployerjobAlertsVO;
import com.myjobkart.vo.EntrolledSeviceVO;
import com.myjobkart.vo.FeedbackVO;
import com.myjobkart.vo.JobPostVO;
import com.myjobkart.vo.JobseekerProfileVO;
import com.myjobkart.vo.SaveCandidateVO;
import com.myjobkart.vo.ShortListVO;
import com.myjobkart.vo.WalkinVO;

/**
 * Owner : Scube Technologies Created Date: Nov-20-2014 Created by : Aravindhan
 * Description : Employer DAO interface is used to call the appropriate
 * GenericDAOImpl to persist the data into the system and here it extends the
 * Generic DAO which is used to extends the appropriate generic methods in the
 * GenericDAOImpl Layer. Reviewed by :
 * 
 */
public interface EmployerDAO extends GenericDAO<EmployerVO> {

	public void approveEmployer(EmployerLoginVO login)
			throws MyJobKartException;

	public List<JobseekerProfileVO> retrivejobseekerList();
	
	public List<EmployerjobAlertsVO> employerjob_alert();
	
	public boolean addEmployerAlert(Set<JobPostVO> postVOList);
	
	public boolean addNextrun(EmployerjobAlertsVO employerjob);
	
	
	
	public List<EmployerVO> getunapproveEmployers(
			EmployerVO employerRegistration) throws MyJobKartException;

	public List<String> getAllEmailList() throws MyJobKartException;

	public EmployerLoginVO authendicate(String string, String emailAddress);
	
	public EmployerVO findByEmail(String string, String emailAddress);
	
	public EmployerLoginVO changeAuthendication(EmployerLoginVO employerLoginVO);

	public long employerActivation(EmployerLoginVO employerLoginVO,
			EmployerProductServiceVO employeerProductServiceVO);

	long createProfile(EmployerProfileVO employerProfileVO);
	
	public EmployerVO getByEmployerEmail(String employerEmailId);

	EmployerProfileVO retriveEmployer(EmployerProfileVO employerProfileVO);

	EmployerProfileVO updateProfile(EmployerProfileVO updateProfile)
			throws MyJobKartException;

	int deleteProfile(EmployerProfileVO updateProfile)
			throws MyJobKartException;

	int deleteProfile(SaveCandidateVO deleteProfile)
			throws MyJobKartException;

	int deleteAppliedCandidate(AppliedJobVO deleteCanidate)
			throws MyJobKartException;

	long jobPost(List<JobPostVO> jobPostVO) throws MyJobKartException;

	public JobseekerProfileBO searchJobseeker(JobseekerProfileBO jobseekerProfile)
			throws MyJobKartException;

	public EmployerVO getEmployerRegistraion(EmployerVO   employerVO);

	public JobPostBO reteriveJobPost(JobPostBO appliedJobBO);

	//JobPostBO retriveEmployer() throws MyJobKartException,SerialException, SQLException;

	List<CompanyEntity> retriveCompanyList() throws MyJobKartException,
	SerialException, SQLException;

	/*List<JobseekerProfileBO> advancedSearchJobseeker(
			JobseekerProfileBO jobseekerProfile) throws MyJobKartException;*/

	EmployerProfileVO profileStatus(EmployerProfileVO employerProfileVO);

	EmployerSubuserVO updateSubUserActive(EmployerSubuserVO subUserVO);

	List<JobPostBO> retrieveJobPosting() throws MyJobKartException;

	List<EmployerProfileBO> retriveEmployerProfile();

	List<JobseekerProfileBO> refineResult(JobseekerProfileBO profileBO)
			throws MyJobKartException;

	List<AppliedJobBO> reteriveAppliedJobs(AppliedJobBO appliedJobBO)
			throws MyJobKartException, SerialException, SQLException;

	List<JobPostBO> retrieveJobPosts(JobPostVO jobPostVO)
			throws SerialException, SQLException;

	JobPostVO updateJobPost(JobPostVO postVO) throws MyJobKartException;

	ShortListVO shortlistCandidate(ShortListVO shortListVO);

	long getJobseekerRegId(ShortListCandidate shortListCandidate);

	EmployerActivityVO shortlistCandidateActivity(EmployerActivityVO emActivityVO);

	long saveProfile(SaveCandidateVO saveCandidateVO);

	public EmployerProfileBO employeerProfileCheck(
			EmployerProfileVO employerProfileVO);

	List<SaveCandidateBO> reteriveCandidate(SaveCandidateBO saveCandidateBO)
			throws MyJobKartException;

	int deleteJobPost(JobPostVO deleteProfile) throws MyJobKartException;

	List<EmployerBO> retrieveRegisteredList() throws MyJobKartException,
	SerialException, SQLException;

	List<EmployerBO> retrieveRegisteredList(long employerRegisterId) throws MyJobKartException,
	SerialException, SQLException;

	List<EmployerBO> renewelRegisteredList() throws MyJobKartException,
	SerialException, SQLException;

	EmployerVO updateEmployer(EmployerVO updateProfile)
			throws MyJobKartException;

	int deleteEmployer(EmployerVO deleteProfile) throws MyJobKartException;

	List<SaveCandidateBO> employeerSaveResume(SaveCandidateBO saveCandidateBO)throws MyJobKartException;


	public EmployerVO profileStatus1(EmployerVO employerLoginVO);

	public void saveProfileView(ViewJobseekerBO viewBo);

	long addPayments(EntrolledSeviceVO entrolledSevice);

	public List<SaveCandidateBO> getJobseekerId(SaveCandidateBO candidateBO);

	public List<JobPostBO> getShortlistId(JobPostBO candidate);

	//public List<JobPostBO> getJopposting(JobPostBO jobPostBO);

	public JobPostVO getJoppost(JobPostVO jobPostVO);

	public EmployerVO getEmployerReg(EmployerVO employerVO);

	public long activity(EmployerActivityVO activityVO);

	public long saveBannerPost(BannerPostVO bannerPostVO);

	public List<BannerPostBO> getBannerList(String fileName)
			throws SerialException, SQLException;

	public List<BannerPostBO> getBannerList(long id) throws SerialException,
	SQLException;

	public List<BannerPostBO> getBannarList() throws SerialException,
	SQLException;

	public List<EmployerBO> employerRenewalAlert(String email)throws SerialException, SQLException;
			

	public int deleteBannerList(BannerPostVO jobPostVO)
			throws MyJobKartException;

	public long bannerName(BannerPostBO bannerName);

	BannerPostVO updateBanner(BannerPostVO bannerPostVO)
			throws MyJobKartException;

	int deleteEmployerEnrolledDetails(EntrolledSeviceVO saveJobVO);

	List<PaymentBO> productsEnrolledList(long registerId);

	List<PaymentBO> employerLastMonthEnrolledList(long registerId);

	public List<PaymentBO> employerPaymentHistory();

	public List<SaveCandidateBO> reteriveCandidate();


	public List<JobPostBO> viewRecruiters(String val);

	public List<ShortListCandidate> shortListCandidatesView(ShortListVO shortListVO);
	public List<ShortListCandidate> shortListCandidates();

	public BannerPostVO getBanner(BannerPostVO bannerVO);

	/**
	 * @param jobpostVO
	 * @return
	 */
	public JobPostVO jobPostProfileStatus(JobPostVO jobpostVO);

	/**
	 * @param shortListVO
	 * @return
	 */
	public int shortListDeleteProfile(ShortListVO shortListVO);

	/**
	 * @param empId
	 * @return
	 */
	public EmployerLoginVO getEmployerLogin(EmployerLoginVO employerLoginVO);

	/**
	 * @param employerBO
	 * @return
	 */
	public EmployerVO employerRegisteration(EmployerVO employerVO);
	List<EmployerBO> jobpostcount();



	public List<ProductBO> getProductList();

	BookingBO createOrder(BookingVO orderVO);

	public FeedbackVO feedbackVO(FeedbackVO feedback);

	/**
	 * @param employerVO
	 * @return
	 */
	public EmployerVO empprofileStatusActive(EmployerVO employerVO);



	/**
	 * @return
	 */
	public List<JobPostBO> jobbyExperience();

	/**
	 * @return
	 */
	public List<JobPostBO> jobbyLocation();


	boolean emailVerification(String email);

	/**
	 * @param mobileNo
	 * @return
	 */
	public boolean Mobile_verifications(String mobileNumber);

	public List<EmployerActivityBO> retriveEmpactivity(EmployerActivityBO employerActivityBO);

	long createSubuserprofile(EmployerSubuserVO employerSubuserVO);

	boolean findbyEmailSubuser(String emailAdress);


	List<EmployerSubuserBO> retriveSubusers (EmployerSubuserBO retriveprofile)
			throws MyJobKartException;


	/**
	 * @param deleteSubuser
	 * @return
	 */
	public int subuserdelete(EmployerSubuserBO deleteSubuser)
			throws MyJobKartException;

	/**
	 * @param updateSubuser
	 * @return
	 * @throws MyJobKartException
	 */
	boolean updateSubuser(EmployerSubuserVO vo)
			throws MyJobKartException;
	
	public EmployerSubuserVO getSubuser(EmployerSubuserBO bo);
			

	/**
	 * @param retriveprofile
	 * @return
	 */
	public EmployerSubuserBO retriveSubuser(EmployerSubuserBO retriveprofile)
			throws MyJobKartException;

	long getSubuserCount(long id);

	void updateOrder(BookingVO orderVO);

	long retriveCompanyId(List<String> companyName);

	BookingVO getOrderByOrderId(long orderId);

	void updateOrderStatus(String orderId, String status);

	boolean getemployerProduct(long empId);


	/**
	 * @param walkinVO
	 * @return
	 */
	long createWalkinprofile(List<WalkinVO> walkinVO) throws MyJobKartException ;



	/**
	 * @param walkinVO
	 * @return
	 */
	List<WalkinBO> retriveWalkinJobs(WalkinBO walkinBO) throws MyJobKartException, SQLException ;

	/**
	 * @param walkinBO
	 * @return
	 */
	public WalkinBO retriveWalkin(WalkinBO walkinBO);


	/**
	 * @param walkinVO
	 * @return
	 */
	public WalkinVO walkinStatus(WalkinVO walkinVO);

	/**
	 * @param walkinVO
	 * @return
	 */
	int deleteWalkin(WalkinVO walkinVO) throws MyJobKartException;

	/**
	 * @param walkinBO
	 * @return
	 */
	public WalkinBO retriveWalkinJobpost(WalkinBO walkinBO)throws MyJobKartException;

	/**
	 * @param vo
	 * @return
	 */
	public boolean updateWalkinjob(WalkinVO vo)throws MyJobKartException;

	/**
	 * @param walkinPost
	 * @return
	 */
	public WalkinVO getwalkinpost(WalkinVO walkinPost);

	/**
	 * @param jobPostBO
	 * @return
	 */
	public List<JobPostBO> getJobposting(JobPostBO jobPostBO);

	/**
	 * @param jobPostVO
	 * @return
	 */
	JobPostVO getJobpost(JobPostVO jobPostVO);

	/**
	 * @param jobPostBO
	 * @return
	 * @throws MyJobKartException
	 * @throws SerialException
	 * @throws SQLException
	 */
	JobPostBO retriveEmployer(JobPostBO jobPostBO) throws MyJobKartException,
			SerialException, SQLException;

}
