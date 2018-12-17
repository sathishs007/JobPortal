
<!-- Footer -->

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
 <script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script> 

<script>
	function ValidateEmail(email) {
		var expr = /^([\w-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([\w-]+\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\]?)$/;
		return expr.test(email);
	};
	$(document)
			.ready(
					function() {
						$('#btnSubmits')
								.click(
										function(e) {
											var isValid = true;
											document
													.getElementById('errornews').innerHTML = "";
											$('input[type="emails"]')
													.each(
															function() {
																if ($.trim($(
																		this)
																		.val()) == '') {
																	isValid = false;
																	$(this)
																			.css(
																					{
																						"border" : "1px solid red",
																					});
																} else {
																	if (!ValidateEmail($(
																			"#emailId")
																			.val())) {
																		document
																				.getElementById('errornews').innerHTML = "Invalid email address.";
																		return false;
																	}
																	$
																			.ajax({
																				type : "GET",
																				url : 'ajax_news.html',
																				data : 'email='
																						+ $(
																								"#emailId")
																								.val(),
																				success : function(
																						data) {
																					//	var obj = JSON.parse(data);
																					var mess = data;
																					document
																							.getElementById('errornews').innerHTML = mess;
																					if (mess == ("Registration successfully completed")) {
																						$(
																								"#emailId")
																								.val(
																										" ");
																					}

																				},
																			});
																	isValid = true;
																	$(this)
																			.css(
																					{
																						"border" : "",
																						"background" : ""
																					});
																}
															});
											if (isValid == false)
												e.preventDefault();

										});
					});
</script>



<footer class="footer" id="footer"
	style="background: #222; border-top: 1px solid #e6e6e6;">
	<div class="footer-widgets">
		<div class="container">
			<div class="row">
				<div class="col-sm-6 col-md-3">
					<!-- Widget :: Custom Menu -->
					<div class="widget_nav_menu widget widget__footer">
						<h3 class="widget-title">Information</h3>
						<div class="widget-content">
							<ul>
								<li><a href="FAQ.html">FAQ</a></li>
								<!-- 	<li><a href="employer_blog.html">Blogs</a></li> -->
								<li><a href="employer_about_as.html">About Us</a></li>
								<!-- <li><a href="expertAdvice.html">Expert Advice</a></li> -->
								<li><a href="employer_contact.html">Contact Us</a></li>
								<li><a href="feedback.html">Report a Problem</a></li>
							</ul>
						</div>
					</div>
					<!-- /Widget :: Custom Menu -->
				</div>
				<div class="clearfix visible-sm"></div>
				<div class="col-sm-6 col-md-3">
					<!-- Widget :: Categories -->
					<div class="widget_categories widget widget__footer">
						<h3 class="widget-title">Categories</h3>
						<div class="widget-content">
							<ul>
								<li><a href="find_jobs.html?all=all">Browse All
										Categories</a></li>
								<li><a href="find_jobs.html">Jobs
										By Location</a></li>
								<li><a href="find_jobs.html">Jobs
										By Company</a></li>
								<li><a href="find_jobs.html">Jobs
										By Skills</a></li>

							</ul>
						</div>
					</div>
					<!-- /Widget :: Categories -->
				</div>
				<div class="clearfix visible-sm"></div>
				<div class="col-sm-6 col-md-3">
					<!-- Widget :: Categories -->
					<div class="widget_categories widget widget__footer">
						<h3 class="widget-title">Employers</h3>
						<div class="widget-content">
							<ul>
								<li><a href="find_resumes.html">Find Candidates</a></li>
								<li><a href="job_posting.html?jobpost=jobspost">Post
										Jobs</a></li>

								<!-- <li><a href="manage_response.html">Manage Responses</a></li> -->
								<li><a href="product_services.html">Product & Services</a></li>
								<li><a href="employer_contact.html">Request Call</a></li>

							</ul>
						</div>
					</div>
					<!-- /Widget :: Categories -->
				</div>

				<div class="clearfix visible-sm"></div>


				<div class="col-sm-6 col-md-3">
					<div class="widget_newsletter widget widget__footer">
						<h3 class="widget-title">Get Our Newsletter</h3>
						<div class="widget-content">
							<p>You can subscribe here to receive MyJobKart articles about
								industry trends</p>

							<form id="NewsLetterId">
								<c:if test="${not empty messageemail}">
									<div style="color: #808080">
										<c:out value="${messageemail}"></c:out>
									</div>
								</c:if>
								<div class="row">
									<div class="form-group"
										style="padding-right: 43px; padding-left: 16px;">

										<input type="emails" id="emailId" path="emailId"
											placeholder="Email" class="newsletterfooter">
									</div>
									<!-- <div class="form-group" style="padding-left: 16px;">
										<input type="reset" id="btnButton"
											class="btn btn-theme btn-warning" value="Subscribe" />
									</div> -->
									<button type="button" class="btn btn-theme btn-warning" 
										id="btnSubmits" style="margin-left: 18px; padding:7px 7px;">Subscribe</button>

								</div>
							</form>
							<span id="errornews" style="color: #fff"> </span>



						</div>
					</div>
					<!-- /Widget :: Newsletter -->
				</div>
			</div>
		</div>
	</div>
	<br/>
	<div class="footer-copyright"
		style="background-color: #181818; padding: 20px;">
		<div class="container">
			<div class="row" style="text-align: center;">
				<div class="col-sm-12 col-md-12">
					<a> Copyright &copy; 2016 MyJobKart &nbsp;| &nbsp;All Rights
						Reserved</a>
				</div>

			</div>
		</div>
	</div>
</footer>