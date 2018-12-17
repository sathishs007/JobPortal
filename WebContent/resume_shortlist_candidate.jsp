<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
<script>
	$(document).ready(function() {
		$('#btnSubmit').click(function(e) {
			var isValid = true;
			$('input[type="text"]').each(function() {
				if ($.trim($(this).val()) == '') {
					isValid = false;
					$(this).css({
						"border" : "1px solid red",
					});
				} else {
					$(this).css({
						"border" : "",
						"background" : ""
					});
				}
			});
			$('select[type="text"]').each(function() {
				if ($.trim($(this).val()) == '') {
					isValid = false;
					$(this).css({
						"border" : "1px solid red",
					});
				} else {
					$(this).css({
						"border" : "",
						"background" : ""
					});
				}
			});

			$('textarea[type="text"]').each(function() {
				if ($.trim($(this).val()) == '') {
					isValid = false;
					$(this).css({
						"border" : "1px solid red",
					});
				} else {
					$(this).css({
						"border" : "",
						"background" : ""
					});
				}
			});

			if (isValid == false)
				e.preventDefault();

		});
	});

	$(document).ready(function() {
		$('#btnSubmit').click(function(e) {
			var isValid1 = true;
			$('input[type="password"]').each(function() {
				if ($.trim($(this).val()) == '') {
					isValid1 = false;
					$(this).css({
						"border" : "1px solid red",
					});
				} else {
					$(this).css({
						"border" : "",
						"background" : ""
					});
				}
			});
			if (isValid1 == false)
				e.preventDefault();

		});
	});

	$(document).ready(function() {
		$('#btnSubmit').click(function(e) {
			var isValid2 = true;
			$('input[type="email"]').each(function() {
				if ($.trim($(this).val()) == '') {
					isValid2 = false;
					$(this).css({
						"border" : "1px solid red",
					});
				} else {
					$(this).css({
						"border" : "",
						"background" : ""
					});
				}
			});
			if (isValid2 == false)
				e.preventDefault();

		});
	});

	$(document).ready(function() {
		$('#close').click(function(e) {
			location.reload();
		});
	});
</script>
<!-- textarea Descriptions put edited pages -->
<script type="text/javascript"
	src="http://js.nicedit.com/nicEdit-latest.js"></script>
<script type="text/javascript">
	//<![CDATA[
	bkLib.onDomLoaded(function() {
		nicEditors.allTextAreas()
	});
	//]]>
</script>

<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>

<title>Myjobkart</title>
</head>
<body>

	
	<div class="container">
		<div class="col-sm-12">
			<div class="box-list">
				<div class="item">
					<div class="row">
						<h3 class="text-center no-margin titleunderline">ShortList Candidate
							Profile</h3>
						<hr />
						<!-- form post a job -->
						<form:form id="myForm" action="resume_shortlist_candidate.html"  method="get" class="login-form clearfix"
							modelAttribute="saveCandidateBO">

							<center>
								<div class="warning" style="width: 100%; text-align: left;">

									<c:if test="${not empty Infomessage}">
										<div class="alert alert-success" role="alert">
											<button type="button" class="close" data-dismiss="alert"
												aria-label="Close">
												<span aria-hidden="true">&times;</span>
											</button>
											<strong>Success</strong>
											<c:out value="${Infomessage}"></c:out>
										</div>
									</c:if>

									<c:if test="${not empty Infomessage}">
										<div class="alert alert-danger" role="alert">
											<button type="button" class="close" data-dismiss="alert"
												aria-label="Close">
												<span aria-hidden="true">&times;</span>
											</button>
											<strong>Oops!</strong>
											<c:out value="${Infomessage}"></c:out>
										</div>
									</c:if>
								</div>
							</center>


							<div class="form-group">
								<div class="row clearfix">
									<div class="col-xs-6">
										<label>First Name</label> <font style="color: red;">* </font>
										<form:input type="text" class="form-control" path="firstName"
											id="firstNameInput" placeholder="First Name" maxlength="20" readonly="true" />
										<form:errors path="firstName" cssClass="error" />
									</div>

									<div class="col-xs-6">
										<label>Email</label> <font style="color: red;">* </font>
										<form:input type="text" class="form-control" path="emailId"
											id="lastNameInput" placeholder="Last Name" maxlength="20" readonly="true" />
										<form:errors path="emailId" cssClass="error" />
									</div>
								</div>
							</div>

							 <div class="form-group">
								<div class="row clearfix">
									<div class="col-xs-6">
										<label>Job Tittle</label> <font style="color: red;">* </font>
										<form:input type="text" class="form-control"
											path="profiledescription" id="emailIdInput" placeholder="Email Id"
											maxlength="40" readonly="true" />
										<form:errors path="profiledescription" cssClass="error" />
									</div>


									<div class="col-xs-6">
										<label>Experience Year-Month</label> <font style="color: red;">*
										</font>
										<form:input type="text" class="form-control"
											path="expYearandMonth" id="profiledescription"
											placeholder="Profile Description" maxlength="40" readonly="true" />
										<form:errors path="expYearandMonth" cssClass="error" /> 
									</div>
								</div>
							</div> 

				 <div class="form-group">
								<div class="row clearfix">
									<div class="col-xs-6">
										
										<form:hidden  class="form-control"
											path="saveId" id="saveId" 
											  />
										<form:errors path="saveId" cssClass="error" />
									</div>


									<div class="col-xs-6">
										<form:hidden  class="form-control"
											path="profileId" id="profileId"
											placeholder="Profile Description" maxlength="40" readonly="true" />
										<form:errors path="profileId" cssClass="error" /> 
									</div>
								</div>
							</div> 



							<div class="form-group">
								
								<div class=" row clearfix">
									<div class="col-xs-6">
								<label>Job Type</label></br>	
									<form:select type="text" id="IndustryTypeInput" path="jobTitle" class="form-control">  
													<c:forEach items="${jobTittleList}" var="test">
														<form:option  value="${test.jobTitle}"/>
													</c:forEach>
											</form:select>
										
								</div>
							</div> 
							</div>
 


							<div class="form-group">
								<div class=" row clearfix">
									<%-- <div class="col-xs-6">
										<label>Phone Number</label> <font style="color: red;">*</font>
										 <form:select type="text" id="IndustryTypeInput" path="jobTitle">  
													<c:forEach items="${jobTittleList}" var="test">
														<form:option  value="${test.jobTitle}"/>
													</c:forEach>
											</form:select>
										<form:input type="text" class="form-control"
											path="contactNumber" id="contactNumberInput"
											placeholder="Phone Number" maxlength="10" />
										<form:errors path="contactNumber" cssClass="error" />
									</div> --%>
								<%-- 	<input  value="${searchResult.id}" name="id"/>
									<input type="hidden" value="${searchResult.profileId}" name="prfid"/> --%>
									<div class="col-xs-6">
										<div class="form-group" style="margin-top: 30px;">
											<button id="btnSubmit" class="btn btn-t-primary btn-theme">ShortList
												Candidate</button>
											<a href="candidate_resume.html"> <input
												type="button" value="Cancel"
												class="btn btn-t-primary btn-theme" /></a>
										</div>
									</div>
								</div>
							</div> 
							
							

						</form:form>
					</div>
				</div>
			</div>

			<!-- <div class="modal fade" id="myModal">

				<div class="modal-dialog ">
					<div class="modal-content"></div>
				</div>

			</div> -->
		</div>
	</div>
</body>
</html>
