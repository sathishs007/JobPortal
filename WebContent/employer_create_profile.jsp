
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript">
function newCompany() {
	document.getElementById("newCompany").style.display = 'none';
	document.getElementById("textCompany").style.display = 'block';
	document.getElementById("dropCompany").style.display = 'none';
}


</script>
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
	
	$(document).ready(function() {
		$('#proceed').click(function(e) {
			var product = $("#productType").val();
			var months = $("#noofmonths").val();
			var ajaxdata = $(
			"#productType")
			.val();
	var ajaxdata1 = $(
			"#noofmonths")
			.val();
	var value = 'productType='+ ajaxdata+ '&&noofmonths='+ ajaxdata1;
	$
	.ajax({
		url : "product_enroll",
		type : "post",
		data : value,
		cache : false,
		success : function(paymentBO) {
			alert("susses");
			$('#modal-advanced').modal('hide');
			$('#removeshow').hide();
		document.getElementById("productEnrolled").innerHTML = "product enrolled";
		document.getElementById("enroll").value = paymentBO.productType;
		$('#content').hide();
		
		}

	});
					
	  });
	});
	
	$(document).ready(function() {
		$('#trailproceed').click(function(e) {
	$.ajax({
		url : "product_enroll_trail",
		type : "post",
		cache : false,
		success : function(paymentBO) {
			alert("susses");
			document.getElementById("productEnrolled").innerHTML = "product enrolled";
			document.getElementById("enrolltrail").value = paymentBO.productType;
			$('#modal-trail').modal('hide');
		
		}

	});
					
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

<script>
	$(document)
			.ready(
					function() {
						$('#stateInput')
								.change(
										function() {
											$
													.ajax({
														type : "GET",
														url : 'locations',
														data : 'state='
																+ $(
																		"#stateInput")
																		.val(),
														success : function(data) {
															//	var obj = JSON.parse(data);
															var len = data.length;

															var location = data;

															$('#cityInput')
																	.text(data);
															var html = '<option value="">Select</option>';
															var len = data.length;
															delimiter: ",";
															for ( var i = 0; i < len; i++) {
																html += '<option value="' + data[i]+ '">'
																		+ data[i]
																		+ '</option>';
															}
															html += '</option>';

															$('#cityInput')
																	.html(html);

														},
													});
										});
					});
</script>
<title>MyjobKart</title>
</head>
<body>
	 <script type="text/javascript">
		function fileCheck(obj) {
			var fileExtension = [ 'jpeg', 'jpg', 'png', 'bmp' ];
			if ($.inArray($(obj).val().split('.').pop().toLowerCase(),
					fileExtension) == -1){
				document.getElementById("PhotoInput").value='';
				alert("Only '.jpeg','.jpg', '.png',  '.bmp' formats are allowed.");
			}
		}
	</script> 


	<script language="javascript" type="text/javascript">
		function limitText(limitField, limitCount, limitNum) {
			if (limitField.value.length > limitNum) {
				limitField.value = limitField.value.substring(0, limitNum);
			} else {
				limitCount.value = limitNum - limitField.value.length;
			}
		}
	</script>
	<!-- <div class="container">
		<div class="col-sm-12"> -->
	<div class="box-list">
		<div class="item">
			<div class="row">
				<h3 class="text-center no-margin titleunderline">Create Your Job Profile</h3>
				<hr />
				<!-- form post a job -->
				<form:form id="myForm" method="post" class="login-form clearfix"
					commandName="profile" enctype="multipart/form-data"
					modelAttribute="profile">


					<!-- Start Warning Message  -->
					<center>
						<div class="warning" style="width: 100%; text-align: left;">

							<c:if test="${not empty infomessage}">
								<div class="alert alert-info" role="alert">
									<button type="button" class="close" data-dismiss="alert"
										aria-label="Close">
										<span aria-hidden="true">&times;</span>
									</button>
									<strong>Info!</strong>
									<c:out value="${infomessage}"></c:out>
								</div>
							</c:if>
						</div>
					</center>
					<!-- End Warning Message  -->

					<div class="form-group">
						
						<div class="row clearfix">
							<div class="col-xs-6">
						<label>First Name</label> <font style="color: red;">* </font>
								<form:input type="text" class="form-control" path="firstName"
									id="firstNameInput" placeholder="First Name" maxlength="20" />
								<form:errors path="firstName" cssClass="error" />
							</div>
							<div class="col-xs-6">
							<label>Last Name</label> <font style="color: red;">* </font>
								<form:input type="text" class="form-control" path="lastName"
									id="lastNameInput" placeholder="Last Name" maxlength="20" />
								<form:errors path="lastName" cssClass="error" />
							</div>
						</div>
					</div>
					<div class="form-group">
						
						<div class="row clearfix">
							<div class="col-xs-6">
							<label>Email</label> <font style="color: red;">* </font>
								<form:input type="email" class="form-control" path="emailId"
									id="lemailIdInput" placeholder="Email Id" disabled="true" />
								<form:errors path="emailId" cssClass="error" />
							</div>
							<div class="col-xs-6">
							<label>Secondary Email</label> <font style="color: red;">* </font>
								<form:input type="email" class="form-control"
									path="secondaryEmail" id="secondaryEmailInput"
									placeholder="Secondary Email ID" maxlength="75" />
								<form:errors path="secondaryEmail" cssClass="error" />
							</div>
						</div>
					</div>
					<div class="form-group">
						<div class="row clearfix">
							<div class="col-xs-6">
						<label>Phone Number</label> <font style="color: red;">* </font>
								<form:input type="text" class="form-control" path="contactNo"
									id="contactNoInput" placeholder="Phone Number" maxlength="10" />
								<form:errors path="contactNo" cssClass="error" />
							</div>
							<div class="col-xs-6">
									<label>Mobile Number</label> <font style="color: red;">* </font>
								<form:input type="text" class="form-control" path="mobileNo"
									id="mobileNoInput" placeholder="Mobile Number" maxlength="10" />
								<form:errors path="mobileNo" cssClass="error" />
							</div>
						</div>
					</div>
					<div class="form-group">
						<div class="row clearfix">
							
							<div class="col-xs-3" style="display: block" id="dropCompany">
									<label>Company Name</label> <font style="color: red;">*
									</font>
									<form:select type="texty" path="companyName"
											id="companyNameInput" class="form-control">
											<form:option value="">Select Company Name</form:option>
											<form:options items="${CompanyList}" />
										</form:select>
								</div>
								
								<div class="col-xs-3" style="display: none" id = "textCompany">
										<label>Company Name</label> <font style="color: red;">*
										</font>
										<form:input type="texty" path="otherCompany"
											id="otherCompanyInput" class="form-control" />
										<form:errors path="otherCompany" cssClass="error" />
									</div>
						
											
							<div class="col-xs-3">
							<label>Industry Type</label> <font style="color: red;">* </font>
								<form:select type="text" path="companyType" class="form-control">
									<form:option value="">Select Indrustry Type</form:option>
									<form:options items="${IndustryList}" />

								</form:select>

								<form:errors path="companyType" cssClass="error" />
							</div>
							<div class="col-xs-3">
							<label>Company Sites</label> <font style="color: red;">* </font>
								<form:input type="text" class="form-control"
									path="companyWebsite" id="companyWebsiteInput"
									placeholder="Company Website" maxlength="70" />
								<form:errors path="companyWebsite" cssClass="error" />
								<div class="color-black-mute">
												<small>Format:www.example.com</small>
											</div>
							</div>
							
							<div class="col-xs-3">
							<label>Employer Type</label> <font style="color: red;">* </font>
								<form:select type="text" path="industryType"
									id="IndustryTypeInput" title="Industry Type"
									class="form-control">
									<form:option value="">Select Employer Type</form:option>
									<form:option value="company">Company</form:option>
									<form:option value="consultancy">Consultancy</form:option>
								</form:select>
								<form:errors path="industryType" cssClass="error" />
							</div>
						</div>
					</div>
					
						
						<div class="form-group">
						<div class="row clearfix">
						<div class="col-xs-3" id="newCompany" style="display: block">
				          <input type="button" id="otherCompany" value="Other Names" 
										class="btn btn-theme  btn-block" onclick="newCompany()" />
				          
				                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                       
						
						</div>
						</div>
						</div>	
						
						

					<div class="form-group">

						<div class="row clearfix">
							<div class="col-xs-6">
							<label>Permanent Address </label> <font style="color: red;">* </font>
								<form:input type="text" class="form-control" path="addressLine1"
									id="addressLine1Input" placeholder="Permanent Address" maxlength="100" />
								<form:errors path="addressLine1" cssClass="error" />
							</div>
							<div class="col-xs-6">
							<label>Pincode </label> <font style="color: red;">* </font>
								<form:input type="text" class="form-control" path="pinCode"
									id="pinCodeInput" placeholder="Pin Code" maxlength="6" />
								<form:errors path="pinCode" cssClass="error" />
							</div>
							
							
						</div>
					</div>
					
					<div class="form-group">
						<div class="row clearfix">
							<div class="col-xs-4">
							<label>City</label> <font style="color: red;">* </font>
							<form:select type="text" class="form-control" path="city"
										id="locationInput">
										<form:option value="">Select City</form:option>
										<form:options items="${cityList}" />
									</form:select>
									<form:errors path="city" cssClass="error" />
							</div>
							<div class="col-xs-4">
							<label>State</label> <font style="color: red;">* </font>
							
							<form:select type="text" path="state" id="nationalityInput"
										class="form-control">
							<form:option value="">Select State</form:option>
										<form:options items="${stateList}" />
									</form:select>
									<form:errors path="state" cssClass="error" />
							</div>
							<div class="col-xs-4">
							<label>Country</label> <font style="color: red;">* </font>
								<form:input type="text" class="form-control" path="country" value="India"
									id="countryInput" placeholder="Country" readonly="true" />
								<form:errors path="country" cssClass="error" />
							</div>
						</div>
					</div>
					<div class="form-group">
						
						<div class="row clearfix">
							<div class="col-xs-6">
							<label>Roles</label> <font style="color: red;">*
						</font>
								<form:input type="text" class="form-control"
									path="currentDesignation" id="currentDesignationInput"
									placeholder="Roles" maxlength="75" />
								<form:errors path="currentDesignation" cssClass="error" />
							</div>
							
							<div class="col-xs-6">
							<label>Hiring Purpose</label> <font style="color: red;">* </font>
								<form:input type="text" class="form-control"
									path="hiringPurpose" id="hiringPurposeInput"
									placeholder="Hiring Purpose" maxlength="50" />
								<form:errors path="hiringPurpose" cssClass="error" />
							</div>
						</div>
					</div>
					<div class="form-group">
					<div class="row clearfix">
					<div class="col-xs-6">					
					<label>Company Logo</label> <font style="color: red;">*
										</font> <input type="file" name="companyLogos" path="companyLogo"
											id="PhotoInput" placeholder="Photo"
											onchange="fileCheck(this)" />
										<div class="color-black-mute">
											<small>Logo jpeg,jpg,png Formats Only</small>
										</div>
										</div>
						
					</div>
					</div>

					<div class="form-group">
						<label>Company Profile</label>
						<form:textarea name="text" cols="30" rows="8"
							path="companyProfile" placeholder="Company Profile"
							class="form-control"
							onKeyUp="limitText(this.form.companyProfile,this.form.countdown,50000);"
							onKeyDown="limitText(this.form.companyProfile,this.form.countdown,50000);"></form:textarea>
							<form:errors path="companyProfile" cssClass="error" />
					</div>

                  
					<div id="content" class="form-group">
						<form:radiobutton path="productEnrolled" value="trail"/>
						<label for="productEnrolled">Trail</label>
						 <form:radiobutton
									path="productEnrolled"  id="enroll"  value="paid" style="margin-left:10px;"/> 
						<label for="productEnrolled">Paid Service</label><form:errors
									path="productEnrolled" cssClass="error" />
					</div>
					
					<div class="form-group">
						<label class="radio-inline" id="productEnrolled"
							style="color: green;"></label>
					</div>
					<div class="form-group ">
						<input type="submit" id="btnSubmit" value="Submit"
							class="btn btn-t-primary btn-theme" /><a
							href="employer_profile_view.html"> <input type="button"
							value="Cancel" class="btn btn-t-primary btn-theme" /></a>
					</div>
				</form:form>
				<!-- end form post a job -->
			</div>
		</div>
	</div>

	<div class="modal fade" id="myModal">

		<div class="modal-dialog ">
			<div class="modal-content"></div>
		</div>

	</div>
	
</body>
</html>