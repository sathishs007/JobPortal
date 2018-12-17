<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html>

<body>
	<div class="block-section text-center ">

		<div class="row">
			<div class="panel-group no-margin" id="accordion">
				

				<div class="panel panel-default">
					<div class="panel-heading">
						<div class="panel-title">
							<a class="collapsed" data-toggle="expand"
								data-parent="#accordion" href="#collapseOne"
								aria-controls="collapseOne">
								<h4 class=" no-margin with-ic" style="text-align: center;">My
									Home</h4>
								<div class="collapse-toogle"></div>
							</a>
						</div>
					</div>
					<div id="collapseOne" class="panel-collapse collapse in">
						<div class="panel-body  panel-body-lg">
							<ul class="list-unstyled" style="text-align: left;">
								<li><a href="employer_home.html"><img
										src="resources/theme/images/menu_arrow.png"
										class="side-menu-icon">&nbsp;My Home </a></li>
								<li><a href="employer_change_password_after_login.html">
										<img src="resources/theme/images/menu_arrow.png"
										class="side-menu-icon">&nbsp;Change Password
								</a></li>
								<li><a href="employer_create_profile.html"><img
										src="resources/theme/images/menu_arrow.png"
										class="side-menu-icon">&nbsp;Create Profile</a></li>
								<li><a href="employer_profile_view.html"><img
										src="resources/theme/images/menu_arrow.png"
										class="side-menu-icon">&nbsp;View My Profile</a></li>
								<li><a href="employer_create_subuser.html"><img
										src="resources/theme/images/menu_arrow.png"
										class="side-menu-icon">&nbsp;Create Subuser Profile</a></li>

								<li><a href="employer_subuser_view.html"><img
										src="resources/theme/images/menu_arrow.png"
										class="side-menu-icon">&nbsp;View Subuser Profile</a></li>
							</ul>
							<div class="white-space-20"></div>
						</div>
					</div>
				</div>
				<div class="panel panel-default">
					<div class="panel-heading">
						<div class="panel-title">
							<a class="collapsed" data-toggle="expand"
								data-parent="#accordion" href="#collapseTwo"
								aria-controls="collapseTwo">
								<h4 class=" no-margin with-ic" style="text-align: center">Jobs</h4>
								<div class="collapse-toogle"></div>
							</a>
						</div>
					</div>
					<div id="collapseTwo" class="panel-collapse collapse in">
						<div class="panel-body  panel-body-lg">
							<ul class="list-unstyled" style="text-align: left;">
								<li><a href="job_posting.html"><img
										src="resources/theme/images/menu_arrow.png"
										class="side-menu-icon">&nbsp;Post a Job</a></li>
								<li><a href="employer_job_view.html"><img
										src="resources/theme/images/menu_arrow.png"
										class="side-menu-icon">&nbsp;View Job Posts</a></li>
								<li><a href="find_resumes.html"><img
										src="resources/theme/images/menu_arrow.png"
										class="side-menu-icon">&nbsp;Search JobSeekers</a></li>
								<li><a href="candidate_resume.html"><img
										src="resources/theme/images/menu_arrow.png"
										class="side-menu-icon">&nbsp;Saved Candidates</a></li>
								<li><a href="employer_applied_jobs.html"><img
										src="resources/theme/images/menu_arrow.png"
										class="side-menu-icon">&nbsp;Applied Candidates</a></li>
								<li><a href="short_list_candidate_view.html"><img
										src="resources/theme/images/menu_arrow.png"
										class="side-menu-icon">&nbsp;Shortlisted Candidates</a></li>
								<li><a href="employer_report_view.html"><img
										src="resources/theme/images/menu_arrow.png"
										class="side-menu-icon">&nbsp;Reports View</a></li>
								<li><a href="jobpost_upload.html"><img
										src="resources/theme/images/menu_arrow.png"
										class="side-menu-icon">&nbsp;Upload JobPosts</a></li>

								<li><a href="walkin_profile_creation.html"><img
										src="resources/theme/images/menu_arrow.png"
										class="side-menu-icon">&nbsp;Walking Jobs</a></li>
								<li><a href="walkin_job_view.html"><img
										src="resources/theme/images/menu_arrow.png"
										class="side-menu-icon">&nbsp;View Walking Jobs</a></li>


							</ul>
							<div class="white-space-20"></div>
						</div>
					</div>
				</div>
				<div class="panel panel-default">
					<div class="panel-heading">
						<div class="panel-title">
							<a class="collapsed" data-toggle="expand"
								data-parent="#accordion" href="#collapseThree"
								aria-controls="collapseThree">
								<h4 class=" no-margin with-ic" style="text-align: center;">Products
									Enrolled</h4>
								<div class="collapse-toogle"></div>
							</a>
						</div>
					</div>
					<div id="collapseThree" class="panel-collapse collapse in">
						<div class="panel-body  panel-body-lg">
							<ul class="list-unstyled" style="text-align: left;">
								<li><a href="employer_enrolled_details.html"><img
										src="resources/theme/images/menu_arrow.png"
										class="side-menu-icon">&nbsp;Products Enrolled</a></li>
								<!-- <li><a href="product_services.html"> <img
										src="resources/theme/images/menu_arrow.png"
										class="side-menu-icon">&nbsp;Payment Renewal
								</a></li> -->
								<li><a href="my_notifications.html"> <img
										src="resources/theme/images/menu_arrow.png"
										class="side-menu-icon">&nbsp;Notifications
								</a></li>
								<li><a href="employer_renewal_details.html"> <img
										src="resources/theme/images/menu_arrow.png"
										class="side-menu-icon">&nbsp;Renewal
								</a></li>
							</ul>
							<div class="white-space-20"></div>
						</div>
					</div>
				</div>
				<!-- <div class="panel panel-default">
					<div class="panel-heading">
						<div class="panel-title">
							<a class="collapsed" data-toggle="expand"
								data-parent="#accordion" href="#collapseFour"
								aria-controls="collapseFour">
								<h4 class=" no-margin with-ic" style="text-align: center;">Banner
									Management</h4>
								<div class="collapse-toogle"></div>
							</a>
						</div>
					</div>
					<div id="collapseFour" class="panel-collapse collapse in">
						<div class="panel-body  panel-body-lg">
							<ul class="list-unstyled" style="text-align: left">

								<li><a href="banner_posting.html"><img
										src="resources/theme/images/menu_arrow.png"
										class="side-menu-icon">&nbsp;<b>Post Banner</b></a></li>
								<li><a href="renewal_bannerList.html"><img
										src="resources/theme/images/menu_arrow.png"
										class="side-menu-icon">&nbsp;<b>View Banner</b></a></li>
							</ul>
							<div class="white-space-20"></div>
						</div>
					</div>
				</div> -->
			</div>
		</div>
	</div>
</body>
</html>