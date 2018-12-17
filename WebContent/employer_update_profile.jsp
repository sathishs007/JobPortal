<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>

<script type="text/javascript"
	src="http://js.nicedit.com/nicEdit-latest.js"></script>
<script type="text/javascript">
	//<![CDATA[
	bkLib.onDomLoaded(function() {
		nicEditors.allTextAreas()
	});
	//]]>
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
</script>

<script type="text/javascript">

function companyNameShowOther(){
	document.getElementById("companyName").style.display='none';
	document.getElementById("other").style.display='none';
	document.getElementById("addCompany").style.display='block';
}
</script>



<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>MyjobKart</title>
</head>
<body>

	<script type="text/javascript">
    function fileCheck(obj) {
            var fileExtension = ['jpeg', 'jpg', 'png',  'bmp'];
            if ($.inArray($(obj).val().split('.').pop().toLowerCase(), fileExtension) == -1){
            	document.getElementById("PhotoInput").value='';
                alert("Only '.jpeg','.jpg', '.png', '.bmp' formats are allowed.");
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
						<h3 class="text-center margin-top titleunderline">Update Your Profile</h3>
						<hr>
						<!-- form post a job -->
						<form:form id="myForm" method="post" class="login-form clearfix"
							commandName="updateProfile" enctype="multipart/form-data"
							modelAttribute="updateProfile">
							
							
							<!-- Start Warning Message  -->
							 <center><div class="warning" style="width: 100%;text-align:left;">
							<c:if test="${not empty sucessmessage}">
								<div class="alert alert-success" role="alert">
							<button type="button" class="close" data-dismiss="alert"
									aria-label="Close">
									<span aria-hidden="true">&times;</span>
								</button>
									<strong>Sucess</strong>
									<c:out value="${sucessmessage}"></c:out>
								</div>
							</c:if>
							
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
							<%-- <c:if test="${not empty message}">
								<div class="message red">${message}</div>
							</c:if> --%>
							</div>
							</center>
							 
							<!-- End Warning Message  -->
							
							
							
							
							


							<div class="form-group">
								
								<div class="row clearfix">
									<div class="col-xs-6">
									<label>First Name</label> <font style="color: red;">* </font>
										<form:input type="text" class="form-control" path="firstName"
											id="firstNameInput" placeholder="First Name" maxlength="50" />
										<form:errors path="firstName" cssClass="error" />
									</div>
									<div class="col-xs-6">
									<label>Last Name</label> <font style="color: red;">* </font>
										<form:input type="text" class="form-control" path="lastName"
											id="lastNameInput" placeholder="Last Name" maxlength="50" />
										<form:errors path="lastName" cssClass="error" />
									</div>
								</div>
							</div>


							<div class="form-group">
								
								<div class="row clearfix">
									<div class="col-xs-6">
								<label>Email</label> <font style="color: red;">* </font>
										<form:input type="email" class="form-control" path="emailId" 
											id="emailId" placeholder="Email Id" disabled="true" />
										<form:errors path="emailId" cssClass="error" />
									</div>
									
									
									<div class="col-xs-6">
									<label>Secondary Email</label> <font style="color: red;">* </font>
										<form:input type="email" class="form-control"
											path="secondaryEmail" id="secondaryEmailInput"
											placeholder="Secondary Email ID"  maxlength="100"/>
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
									<div class="col-xs-3" style="display: block;" id="companyName">
								<label>Company</label> <font style="color: red;">* </font>
										<form:select type="texty" path="companyName"
											id="companyNameInput" class="form-control">
											<form:option value="">Select Company Name</form:option>
											<form:options items="${CompanyList}" />
										</form:select>
									</div>
									<div class="col-xs-3" style="display: none;" id="addCompany">
								 <label>Company</label>
									<form:input type="texty" id="companyNameIn" class="form-control"
											path="otherCompany" 
											placeholder="Company Name" maxlength="100" />
									<form:errors path="otherCompany" cssClass="error" />
								</div>
									
									
									<div class="col-xs-3">
									<label>Industry Type</label> <font style="color: red;">* </font>
										<form:select path="companyType" class="form-control">
											<form:option value="">Select Company Type</form:option>
											<form:options items="${IndustryList}"  />
											
										</form:select>

										<form:errors path="companyType" cssClass="error" />
									</div>
									<div class="col-xs-3">
									<label>Company WebSite</label> <font style="color: red;">* </font>
										<form:input type="text" class="form-control"
											path="companyWebsite" id="companyWebsiteInput"
											placeholder="Company Website" maxlength="100" />
										<form:errors path="companyWebsite" cssClass="error" />
									</div>
									<div class="col-xs-3">
									<label>Employer Type</label> <font style="color: red;">* </font>
										<form:select path="industryType" id="IndustryTypeInput"
											title="Industry Type" class="form-control">
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
									
									<div class="col-xs-3" style="display: block;"  id="other">
									<input type="button" id="otherCompany" value="Other Names" 
										class="btn btn-theme  btn-block" onclick="companyNameShowOther()" />
							    </div>
								</div>
							</div>  

							<div class="form-group">
								
								<div class="row clearfix">
									<div class="col-xs-6">
								<label>Permanent Address</label> <font style="color: red;">* </font>
										<form:input type="text" class="form-control"
											path="addressLine1" id="addressLine1Input"
											placeholder="Permanent Address "  maxlength="200"/>
										<form:errors path="addressLine1" cssClass="error" />
									</div>
									
									<div class="col-xs-6">
									<label>Pincode </label> <font style="color: red;">* </font>
										<form:input type="text" class="form-control" path="pinCode"
											id="pinCodeInput" placeholder="PinCode" maxlength="6" />
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
											id="cityInput" placeholder="Country" readonly="true" />
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
											path="currentDesignation" 
											placeholder="Roles" maxlength="25" />
										<form:errors path="currentDesignation" cssClass="error" />
									</div>
									
									<div class="col-xs-6">
																	
									<label>Hiring Purpose</label> <font style="color: red;">*
								</font>
										<form:input type="text" class="form-control"
											path="hiringPurpose" id="hiringPurposeInput"
											placeholder="Hiring Purpose"  maxlength="100" />
										<form:errors path="hiringPurpose" cssClass="error" />
									</div>
									</div>
								</div>
							
							
							<div class="form-group">
								
								<div class="row clearfix">
																
									<div class="col-xs-4">
									<label>Product Type</label>
									 	<form:input type="texts" class="form-control"
											path="productEnrolled" id="productEnrolledInput"
											placeholder="Product Enrolled" readonly="true" />
										<form:errors path="productEnrolled" cssClass="error" />
									</div>
									
								        <div class="col-xs-4">
										<label>Company Logo</label> <font style="color: red;">*
										</font> <input type="file" name="companyLogos" path="companyLogo"
											id="PhotoInput" placeholder="Photo"
											onchange="fileCheck(this)" />
										<div class="color-black-mute">
											<small>Logo jpeg,jpg,png Formats Only</small>
										</div>
										</div>
									
									<!-- <div class="col-xs-4">
									<label>Photo(Optional)</label> <input type="file" name="file"
									path="uploadPhoto" id="PhotoInput" size="50"
									placeholder="Photo" onchange="fileCheck(this)" />
									</div> -->
									
								</div>
							</div>
							<div class="form-group">
								<label>Company Profile</label>
								<form:textarea name="text" cols="30" rows="8"
									path="companyProfile" placeholder="Company Profile"
									class="form-control"
									onKeyUp="limitText(this.form.companyProfile,this.form.countdown,50000);"
									onKeyDown="limitText(this.form.companyProfile,this.form.countdown,50000);"></form:textarea>
							</div>
							<div class="form-group ">
								<input type="submit" id="btnSubmit" value="Update Profile"
									class="btn btn-t-primary btn-theme" /><a
									href="employer_profile_view.html"> <input type="button"
									value="Cancel" class="btn btn-t-primary btn-theme" /></a>
							</div>
						</form:form>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>