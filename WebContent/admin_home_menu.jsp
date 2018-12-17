<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html>

<body>
	<!-- wrapper page -->
	<!-- end main-header -->
	<!-- body-content -->
	<div class="text-center " style="padding-top: 7%;">
		<div class="panel panel-default" >
			<div class="panel-heading" >
				<div class="panel-title">
					<a class="collapsed" data-toggle="expand" data-parent="#accordion"
						href="#collapseTwo" aria-controls="collapseTwo">
						<h4 class=" no-margin with-ic">Job Seeker</h4>
						<div class="collapse-toogle"></div>
					</a>
				</div>
			</div>
			<div id="collapseTwo" class="panel-collapse collapse in">
				<div class="panel-body  panel-body-lg">
					<ul class="list-unstyled" style="text-align: left">
						<li><a href="add_jobseeker.html"><img
								src="resources/theme/images/menu_arrow.png"
								class="side-menu-icon">&nbsp;Create JobSeeker</a></li>
						<li><a href="admin_jobseekers.html"><img
								src="resources/theme/images/menu_arrow.png"
								class="side-menu-icon">&nbsp;View JobSeekers</a></li>

						<!-- <li><a href="jobseeker_profile_view.html"><img
								src="resources/theme/images/menu_arrow.png"
								class="side-menu-icon">&nbsp;JobSeeker Profiles</a></li> -->
								
						

						<!-- <li><a href="admin_jobseeker_saved_jobs.html"><img
								src="resources/theme/images/menu_arrow.png"
								class="side-menu-icon">&nbsp;JobSeeker Saved Jobs</a></li>
						<li><a href="admin_jobseeker_applied_jobs.html"><img
								src="resources/theme/images/menu_arrow.png"
								class="side-menu-icon">&nbsp;JobSeeker Applied Jobs</a></li> -->
					</ul>
					<div class="white-space-20"></div>
				</div>
			</div>
		</div>
		<div class="panel panel-default">
			<div class="panel-heading">
				<div class="panel-title">
					<a class="collapsed" data-toggle="expand" data-parent="#accordion"
						href="#collapseThree" aria-controls="collapseThree">
						<h4 class=" no-margin with-ic">Employer</h4>
					</a>
				</div>
			</div>
			<div id="collapseThree" class="panel-collapse collapse in">
				<div class="panel-body  panel-body-lg">
					<ul class="list-unstyled" style="text-align: left">
						<li><a href="add_employer.html"><img
								src="resources/theme/images/menu_arrow.png"
								class="side-menu-icon">&nbsp;Create Employer</a></li>
						<li><a href="employer_details.html"><img
								src="resources/theme/images/menu_arrow.png"
								class="side-menu-icon">&nbsp;View Employers</a></li>


						<li><a href="admin_employer_history.html"><img
								src="resources/theme/images/menu_arrow.png"
								class="side-menu-icon">&nbsp;Employer History</a></li>

						<!-- <li><a href="renewal_admin_employeers.html"><img
								src="resources/theme/images/menu_arrow.png"
								class="side-menu-icon">&nbsp;Renewal</a></li> -->

						<li><a href="employer_Invitation"><img
								src="resources/theme/images/menu_arrow.png"
								class="side-menu-icon">&nbsp;Employer Invitation</a></li>

						<li><a href="walkin_job_view.html"><img
								src="resources/theme/images/menu_arrow.png"
								class="side-menu-icon">&nbsp;Walkin Jobs</a></li>
					</ul>
					<div class="white-space-20"></div>
				</div>
			</div>
		</div>
		<div class="panel panel-default">
			<div class="panel-heading">
				<div class="panel-title">
					<a class="collapsed" data-toggle="expand" data-parent="#accordion"
						href="#collapseFour" aria-controls="collapseFour">
						<h4 class=" no-margin with-ic" style="text-align: center;">Product
							Management</h4>
						<div class="collapse-toogle"></div>
					</a>
				</div>
			</div>
			<div id="collapseFour" class="panel-collapse collapse in">
				<div class="panel-body  panel-body-lg">
					<ul class="list-unstyled" style="text-align: left">

						<li><a href="create_product.html"><img
								src="resources/theme/images/menu_arrow.png"
								class="side-menu-icon">&nbsp;Create Product</a></li>
						<li><a href="viewProduct.html"><img
								src="resources/theme/images/menu_arrow.png"
								class="side-menu-icon">&nbsp;View Product</a></li>

						<li><a href="view_contact.html"><img
								src="resources/theme/images/menu_arrow.png"
								class="side-menu-icon">&nbsp;View Contact</a></li>
						<li><a href="view_feedback.html"><img
								src="resources/theme/images/menu_arrow.png"
								class="side-menu-icon">&nbsp;View FeedBack</a></li>
					</ul>
					<div class="white-space-20"></div>
				</div>
			</div>
		</div>
		
		<div class="panel panel-default">
			<div class="panel-heading">
				<div class="panel-title">
					<a class="collapsed" data-toggle="expand" data-parent="#accordion"
						href="#collapseFour" aria-controls="collapseFour">
						<h4 class=" no-margin with-ic" style="text-align: center;">Settings</h4>
						<div class="collapse-toogle"></div>
					</a>
				</div>
			</div>
			<div id="collapseFour" class="panel-collapse collapse in">
				<div class="panel-body  panel-body-lg">
					<ul class="list-unstyled" style="text-align: left">

						<li><a href="upload_files.html"><img
								src="resources/theme/images/menu_arrow.png"
								class="side-menu-icon">&nbsp;Upload Company</a></li>

						<li><a href="admin_employer_upload.html"><img
								src="resources/theme/images/menu_arrow.png"
								class="side-menu-icon">&nbsp;Upload Employer</a></li>
								
								<li><a href="admin_company_view.html"><img
								src="resources/theme/images/menu_arrow.png"
								class="side-menu-icon">&nbsp;View Company Entity</a></li>

						<li><a href="admin_industry_view.html"><img
								src="resources/theme/images/menu_arrow.png"
								class="side-menu-icon">&nbsp;View Industry Entity</a></li>
					</ul>
					<div class="white-space-20"></div>
				</div>
			</div>
		</div>
		
		

		<!-- <div class="panel panel-default" >
			<div class="panel-heading">
				<div class="panel-title">
					<a class="collapsed" data-toggle="collapse"
						data-parent="#accordion" href="#collapseFive"
						aria-controls="collapseFive">
						<h4 class=" no-margin with-ic">Settings</h4>
						<div class="collapse-toogle">
							<i class="fa fa-plus ic-open ic-circle-md bg-color1"></i> <i
								class="fa fa-minus ic-close ic-circle-md bg-color1"></i>
						</div>
					</a>
				</div>
			</div>
			<div id="collapseFive" class="panel-collapse collapse ">
				<div class="panel-body  panel-body-lg">
					<ul class="list-unstyled" style="text-align: left">
						<li><a href=""><img
								src="resources/theme/images/arrow.jpeg"
								style="height: 5%; width: 5%">&nbsp;Blog</a></li>
						<li><a href=""><img
								src="resources/theme/images/arrow.jpeg"
								style="height: 5%; width: 5%">&nbsp;Create Account</a></li>
						<li><a href=""><img
								src="resources/theme/images/arrow.jpeg"
								style="height: 5%; width: 5%">&nbsp;Communication Settings</a></li>
						<li><a href=""><img
								src="resources/theme/images/arrow.jpeg"
								style="height: 5%; width: 5%">&nbsp;Change Password</a></li>
					</ul>
					<div class="white-space-20"></div>
				</div>
			</div>
		</div> -->
	</div>
</body>
</html>