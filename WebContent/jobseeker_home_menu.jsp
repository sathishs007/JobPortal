<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html>
<body>
	<div class="block-section text-center">
		<div class="row">
			<div class="panel-group no-margin" id="accordion">
				<!-- profile picture section -->
				<%-- <div class="panel panel-default">
					<div class="panel-heading">
						<div class="panel-title">
							<a class="collapsed" data-toggle="expand"
								data-parent="#accordion" href="#collapseOne"
								aria-controls="collapseOne">
								<h4 class=" no-margin with-ic" style="text-align: center;">${name}</h4>
								<div class="collapse-toogle"></div>
							</a>
						</div>
					</div>
					<div id="collapseOne" class="panel-collapse collapse in">
						<div class="">
							<img src="imageLoginDisplay.html?id=${profileId}"
								class="img-circle" alt="" width="50%" height="60%" />
						</div>
					</div>
				</div> --%>

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
								<li><a href="jobseeker_home.html"> <img
										src="resources/theme/images/menu_arrow.png"
										class="side-menu-icon">&nbsp;My Home
								</a></li>
								<li><a href="jobseeker_change_password_after_login.html">
										<img src="resources/theme/images/menu_arrow.png"
										class="side-menu-icon">&nbsp;Change Password
								</a></li>
								<li><a href="jobseeker_create_jobalert.html">
										<img src="resources/theme/images/menu_arrow.png"
										class="side-menu-icon">&nbsp;Create JobAlert
								</a></li>
								<li><a href="jobseeker_jobalert_view.html"><img 
								         src="resources/theme/images/menu_arrow.png"
										class="side-menu-icon">&nbsp;View My JobAlert</a></li>
								<li><a href="jobseeker_create_profile.html"><img 
								          src="resources/theme/images/menu_arrow.png"
										class="side-menu-icon">&nbsp;Create Job Profile</a></li>
								<li><a href="jobseeker_profile_view.html"><img 
								         src="resources/theme/images/menu_arrow.png"
										class="side-menu-icon">&nbsp;View My Profile</a></li>
								<li><a href="jobseeker_view.html"><img 
								         src="resources/theme/images/menu_arrow.png"
										class="side-menu-icon">&nbsp;View History</a></li>

							</ul>
							<div class="white-space-20"></div>
						</div>
					</div>
				</div>
				<div class="panel panel-default">
					<div class="panel-heading">
						<div class="panel-title">
							<a class="collapsed" data-toggle="expand"
								data-parent="#accordion" href="#collapseFour"
								aria-controls="collapseFour">
								<h4 class=" no-margin with-ic" style="text-align: center">My
									Application</h4>
								<div class="collapse-toogle">
								</div>

							</a>
						</div>
					</div>
					<div id="collapseFour" class="panel-collapse collapse in">
						<div class="panel-body  panel-body-lg">
							<ul class="list-unstyled" style="text-align: left;">
								<li><a href="find_jobs.html"><img 
								         src="resources/theme/images/menu_arrow.png"
										class="side-menu-icon">&nbsp;Search Jobs</a></li>
								<li><a href="jobseeker_saved_jobs.html"><img 
								         src="resources/theme/images/menu_arrow.png"
										class="side-menu-icon">&nbsp;Saved Jobs</a></li>
								<li><a href="jobseeker_applied_jobs.html"><img 
								         src="resources/theme/images/menu_arrow.png"
										class="side-menu-icon">&nbsp;Applied Jobs</a></li>
								<li><a href="jobseeker_appliedmonth.html"><img 
								         src="resources/theme/images/menu_arrow.png"
										class="side-menu-icon">&nbsp;Application History</a></li>

							</ul>
							<div class="white-space-20"></div>
						</div>
					</div>
				</div>
				<!-- <div class="panel panel-default">
					<div class="panel-heading">
						<div class="panel-title">
							<a class="collapsed" data-toggle="expand"
								data-parent="#accordion" href="#collapseSeven"
								aria-controls="collapseSeven">
								<h4 class=" no-margin with-ic" style="text-align: center;">My
									Payments</h4>
								<div class="collapse-toogle">
									<i class="fa fa-plus ic-open ic-circle-md bg-color1"></i> <i
										class="fa fa-minus ic-close ic-circle-md bg-color1"></i>
								</div>
							</a>
						</div>
					</div>
					<div id="collapseSeven" class="panel-collapse collapse in">
						<div class="panel-body  panel-body-lg">
							<ul class="list-unstyled " style="text-align: left">
								<li><a href="jobseeker_renewal_alert.html"><img
										src="resources/theme/images/arrow.jpeg"
										style="height: 5%; width: 5%">&nbsp;Payment History</a></li>
								<li><a href="jobseeker_enrolled_details.html"><img
										src="resources/theme/images/arrow.jpeg"
										style="height: 5%; width: 5%">&nbsp;<b>Products enrolled</b></a></li>

								<li><a href="payment_services.html"> <img
										src="resources/theme/images/arrow.jpeg"
										style="height: 5%; width: 5%">&nbsp;<b>add Payment</b>
								</a></li>
								<li><a href="my_notifications_jobseeker.html"> <img
										src="resources/theme/images/arrow.jpeg"
										style="height: 5%; width: 5%">&nbsp;<b>Notifications</b>
								</a></li>
								<li><a href="jobseeker_renewal_details.html"> <img
										src="resources/theme/images/arrow.jpeg"
										style="height: 5%; width: 5%">&nbsp;<b>Renewal</b>
								</a></li>
							</ul>
							<div class="white-space-20"></div>
						</div>
					</div>
				</div> -->
				<div class="panel panel-default" style="visibility: hidden">
					<div class="panel-heading">
						<div class="panel-title">
							<a class="collapsed" data-toggle="collapse"
								data-parent="#accordion" href="#collapseFive"
								aria-controls="collapseFive">
								<h4 class=" no-margin with-ic" style="text-align: left">My
									Visits</h4>
								<div class="collapse-toogle">
									<i class="fa fa-plus ic-open ic-circle-md bg-color1"></i> <i
										class="fa fa-minus ic-close ic-circle-md bg-color1"></i>
								</div>
							</a>
						</div>
					</div>
					<div id="collapseFive" class="panel-collapse collapse ">
						<div class="panel-body  panel-body-lg">
							<ul class="list-unstyled " style="text-align: left">
								<li><a href=""><img
										src="resources/theme/images/arrow.jpeg"
										style="height: 5%; width: 5%">&nbsp;Jobs & Updates</a></li>
								<li><a href=""><img
										src="resources/theme/images/arrow.jpeg"
										style="height: 5%; width: 5%">&nbsp;Manage Following</a></li>
								<li><a href=""><img
										src="resources/theme/images/arrow.jpeg"
										style="height: 5%; width: 5%">&nbsp;RecruiterConnection</a></li>

							</ul>
							<div class="white-space-20"></div>
						</div>
					</div>
				</div>
				<div class="panel panel-default" style="visibility: hidden">
					<div class="panel-heading">
						<div class="panel-title">
							<a class="collapsed" data-toggle="collapse"
								data-parent="#accordion" href="#collapseSix"
								aria-controls="collapseSix">
								<h4 class=" no-margin with-ic" style="text-align: left">My
									Settings</h4>
								<div class="collapse-toogle">
									<i class="fa fa-plus ic-open ic-circle-md bg-color1"></i> <i
										class="fa fa-minus ic-close ic-circle-md bg-color1"></i>
								</div>
							</a>
						</div>
					</div>
					<div id="collapseSix" class="panel-collapse collapse ">
						<div class="panel-body  panel-body-lg">
							<ul class="list-unstyled " style="text-align: left">
								<li><a href="jobseeker_profile_view.html"><img
										src="resources/theme/images/arrow.jpeg"
										style="height: 5%; width: 5%">&nbsp;Visibility Settings</a></li>
								<li><a href=""><img
										src="resources/theme/images/arrow.jpeg"
										style="height: 5%; width: 5%">&nbsp;Communication
										Settings</a></li>
								<li><a href=""><img
										src="resources/theme/images/arrow.jpeg"
										style="height: 5%; width: 5%">&nbsp;Block Companies</a></li>
								<li><a href="jobseeker_change_password_after_login.html"><img
										src="resources/theme/images/arrow.jpeg"
										style="height: 5%; width: 5%">&nbsp;Change Password</a></li>

							</ul>
							<div class="white-space-20"></div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>