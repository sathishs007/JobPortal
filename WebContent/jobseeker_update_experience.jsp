
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<script>
	$(document).ready(function() {
		$('#btnSubmit').click(function(e) {
			var isValid = true;
			$('input[type="textt"]').each(function() {
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
			$('select[type="textt"]').each(function() {
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

<script type="text/javascript">

function companyNameShowOther(){
	document.getElementById("companyName1").style.display='none';
	document.getElementById("other").style.display='none';
	document.getElementById("addCompany").style.display='block';
}
</script>



<link rel="stylesheet"
	href="//code.jquery.com/ui/1.12.0/themes/base/jquery-ui.css">
<link rel="stylesheet" href="/resources/demos/style.css">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.0/jquery-ui.js"></script>
<style type="text/css">

/* located in demo.css and creates a little calendar icon
 * instead of a text link for "Choose date"
 */
a.dp-choose-date {
	float: left;
	width: 16px;
	height: 16px;
	padding: 0;
	margin: 5px 3px 0;
	display: block;
	text-indent: -2000px;
	overflow: hidden;
	background: url(calendar.png) no-repeat;
}

a.dp-choose-date.dp-disabled {
	background-position: 0 -20px;
	cursor: default;
}
/* makes the input field shorter once the date picker code
 * has run (to allow space for the calendar icon
 */
input.dp-applied {
	width: 140px;
	float: left;
}
</style>


<script type="text/javascript">

$(function() {
	
	$("#expStartDateInput").datepicker();
});
</script>

	<script type="text/javascript">
	$(function() {
		
		$("#expEndDateInput").datepicker();
	});
</script>

   



<html>
<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- <script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
 -->
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
					fileExtension) == -1)
				document.getElementById("PhotoInput").value='';
				alert("Only '.jpeg','.jpg', '.png',  '.bmp' formats are allowed.");
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
			<div class="warning" style="width: 100%; text-align: left;">
					<c:if test="${empty experienceDetails}">
						<div class="alert alert-info" role="alert">
							<button type="button" class="close" data-dismiss="alert"
								aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
							<strong>Info!</strong>
							<c:out value="${Infomessage}"></c:out>
						</div>
					</c:if>
				</div>
				<c:if test="${!empty experienceDetails}">
				<h3 class="text-center no-margin titleunderline">Update Professional Details</h3>
				<hr />
				
				
				
				<div class="pi-responsive-table-sm" >
						<table style="border: 1px solid;" >
							<thead style="background-color: #2a3f54">
								<tr>
									<th style="text-align: center; color: #fff; width: 15%;">Company Name</th>
									<th style="text-align: center; color: #fff; width: 15%;">Industry Type</th>
									<th style="text-align: center; color: #fff; width: 25%;">Designation</th>
									<th style="text-align: center; color: #fff; width: 20%;">Salary</th>
									<th style="text-align: center; color: #fff; width: 10%;">Start Date</th>
									<th style="text-align: center; color: #fff; width: 20%;">End Date</th> 
									<th style="text-align: center; color: #fff; width: 10%;">Edit</th>

								</tr>
							</thead>
							<tbody>

								<c:forEach items="${experienceDetails}" var="searchResult">
									<tr>
										<td class="td-border list-capitalize"><c:out
												value="${searchResult.companyName }"></c:out></td>
										<td class="td-border list-capitalize"><c:out
												value="${searchResult.companyType }"></c:out></td>
												<td class="td-border list-capitalize"><c:out
												value="${searchResult.designation }"></c:out></td>
										<td class="td-border list-capitalize"><c:out
												value="${searchResult.lastSalary }"></c:out></td>
										<td class="td-border list-capitalize"><c:out
												value="${searchResult.startDate }"></c:out></td>
										 <td class="td-border list-capitalize"><c:out
												value="${searchResult.endDate}"></c:out></td> 
												
												<td class="td-border"><a
											href="edit_jobseeker_update_experience.html?prfId=${searchResult.profesId}"
											class="btn btn-theme btn-xs btn-default"><li
												class=" fa fa-pencil"></li></a></td>
																	
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
					</c:if>
					</div>
				<!-- form post a job -->
				
				<div class="row">
				<c:if test="${type eq 'edit'}">
				
				 <form:form id="myForm" method="post" class="login-form clearfix"
					commandName="updateProfile" enctype="multipart/form-data" action="jobseeker_update_experience"
					modelAttribute="updateProfile">


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
								<div class="col-xs-5" style="display: block;" id="companyName1">
									<label>Company</label><font style="color: red;">*</font>
								
										<form:select type="text" class="form-control"
										path="companyName" id="companyNameInput">
										<form:option value="">Select Company Name</form:option>
										<form:options items="${CompanyList}" />
									</form:select>
										
									<form:errors path="companyName" cssClass="error" />
								</div>
								
								<div class="col-xs-5" style="display: none" id="addCompany">
								<label>Company</label>
									<form:input type="text1" id="companyNameIn" class="form-control"
											path="addCompany" 
											placeholder="Company Name" maxlength="100" />
									<form:errors path="addCompany" cssClass="error" />
								</div>
								
						<div class="col-xs-3" class="form-control">		
						<label>Experience</label><font style="color: red;">*</font>
						<br>
						<c:if test="${expStatus == true}">
						<form:radiobutton path="expStatus" id="exprience"  name="getStatus"  value="false"/>
						<label for="expStatus">Previous</label>
						 <form:radiobutton
									path="expStatus"  id="exprience"   name="getStatus" value="true" checked="checked" style="margin-left:10px;"/> 
						<label for="expStatus">Current</label><form:errors
									path="expStatus" cssClass="error" />
					</c:if>
					<c:if test="${expStatus == false}">
						<form:radiobutton path="expStatus" id="exprience"  name="getStatus"  value="false" checked="checked"/>
						<label for="expStatus">Previous</label>
						 <form:radiobutton
									path="expStatus"  id="exprience"   name="getStatus" value="true"  style="margin-left:10px;"/> 
						<label for="expStatus">Current</label><form:errors
									path="expStatus" cssClass="error" />
					</c:if>
					</div>  
					
					<div class="col-xs-4">
					<label>Industry Type</label><font style="color: red;">*</font>
									<form:select type="textt" class="form-control"
										path="companyType" id="companyTypeId">
										<form:option value="">Select Industry Type</form:option>
										<form:options items="${IndustryList}" />
									</form:select>
									<form:errors path="companyType" cssClass="error" />
								</div>
								</div>
					
					</div>							
						
						<div class="form-group">
							<div class="row clearfix">
								<div class="col-xs-6" style="display: block;"  id="other">
									<input type="button" id="otherCompany" value="Other Names" 
										class="btn btn-t-primary btn-theme" onclick="companyNameShowOther()" />
								</div>
							
							</div>
							</div>
						
						<div class="form-group">

							<div class="row clearfix">
								<div class="col-xs-6">
									<label>Designation</label>
									<form:input type="textt" class="form-control"
										path="designation" id="designationInput"
										placeholder="Designation" maxlength="100" />
									<form:errors path="designation" cssClass="error" />
								</div>

								<div class="col-xs-6">
									<label>Last Drawn Salary </label><font style="color: red;">*</font>
									<form:input type="textt" class="form-control" path="lastSalary"
										id="lastSalaryId" placeholder="Last Salary Ex: 25000"
										maxlength="100" />
									<form:errors path="lastSalary" cssClass="error" />
								</div>
							</div>
						</div>
						<div class="form-group">
							
							<div class="row clearfix">
								<div class="col-xs-6">
                                  <label>Start Date</label><font style="color: red;">*</font>
									<form:input type="textt" class="form-control"
										id="expStartDateInput" path="expStartDate"
										placeholder="Exp Start Date"  />
									<div class="color-black-mute">
										<small>Format: mm/dd/yyyy</small>
									</div>
								<form:errors path="expStartDate" cssClass="error" />
								</div>
								
								<div class="col-xs-6">
								<label>End Date</label> <font style="color: red;">*</font>
									<form:input type="textt" class="form-control"
										id="expEndDateInput" path="expEndDate"
										placeholder="Exp End Date"  />
									<div class="color-black-mute">
										<small>Format: mm/dd/yyyy</small>
									</div>
									<form:errors path="expEndDate" cssClass="error" /> 
								</div>
							</div>
						</div>
											
					<div class="form-group ">
						<input type="submit" id="btnSubmit" value="Submit"
							class="btn btn-t-primary btn-theme" /><a
							href="jobseeker_profile_view.html"> <input type="button"
							value="Cancel" class="btn btn-t-primary btn-theme" /></a>
					</div>
				</form:form>
				</c:if>
				</div>
				<!-- end form post a job -->
			</div>
			</div>
		
	

	<div class="modal fade" id="myModal">

		<div class="modal-dialog ">
			<div class="modal-content"></div>
		</div>

	</div>
	
</body>
</html>