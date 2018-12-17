
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
				<h3 class="text-center no-margin titleunderline">Update Educational Details</h3>
				<hr />
				
				<div class="pi-responsive-table-sm" >
						<table style="border: 1px solid;" >
							<thead style="background-color: #2a3f54">
								<tr>
									<th style="text-align: center; color: #fff; width: 15%;">Qualifications</th>
									<th style="text-align: center; color: #fff; width: 15%;">Department</th>
									<th style="text-align: center; color: #fff; width: 25%;">College</th>
									<th style="text-align: center; color: #fff; width: 20%;">Year of Passing</th>
									<th style="text-align: center; color: #fff; width: 10%;">Percentage</th>
									<th style="text-align: center; color: #fff; width: 20%;">Grade</th> 
									<th style="text-align: center; color: #fff; width: 10%;">Edit</th>

								</tr>
							</thead>
							<tbody>

								<c:forEach items="${eduupdateProfile}" var="searchResult">
									<tr>
										<td class="td-border list-capitalize"><c:out
												value="${searchResult.degree }"></c:out></td>
										<td class="td-border list-capitalize"><c:out
												value="${searchResult.department }"></c:out></td>
												<td class="td-border list-capitalize"><c:out
												value="${searchResult.college }"></c:out></td>
										<td class="td-border list-capitalize"><c:out
												value="${searchResult.yearOfPassing }"></c:out></td>
										<td class="td-border list-capitalize"><c:out
												value="${searchResult.percentage }"></c:out></td>
										 <td class="td-border list-capitalize"><c:out
												value="${searchResult.grade}"></c:out></td> 
												
												<td class="td-border"><a
											href="edit_jobseeker_update_educational.html?eduId=${searchResult.educationId}"
											class="btn btn-theme btn-xs btn-default"><li
												class=" fa fa-pencil"></li></a></td>
															
										<%-- <td class="td-border"><span id="editId" onclick="editfun(${searchResult.educationId});"
											class="btn btn-theme btn-xs btn-default"><li
												class=" fa fa-pencil"></li></span></td> --%>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
					</div>
				<!-- form post a job -->
				
				<div class="row">
				<c:if test="${type eq 'edit'}">
				
				 <form:form id="myForm" method="post" class="login-form clearfix"
					commandName="updateProfile" enctype="multipart/form-data" action="jobseeker_update_educational"
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
									<label>Qualifications</label> <font style="color: red;">*</font>
									<div class="row clearfix">
										<div class="col-xs-4">
											<form:select  type="textt" id="qualificationId" path="degree" 
												class="form-control">
												<option value="">Select Educational Qualification</option>
												<c:forEach items="${qualification}" var="i">
													<form:option value="${i}"></form:option>
														<c:out value="${i}"></c:out>
													</option>
												</c:forEach>
											</form:select>
											<form:errors path="degree" cssClass="error" />
										</div>

										<div class="col-xs-4">
												<form:input type="text1" class="form-control" path="department" name="department"
										id="departmentId" placeholder="Department" maxlength="50" />
												<form:errors path="department" cssClass="error" />
										</div>
										<div class="col-xs-4">

                                       <form:input  class="form-control" path="college" name="college"
										id="collegeId" placeholder="College" maxlength="50" />
												<form:errors path="college" cssClass="error" />
											</div>
										</div>


									</div>
								

								<div class="form-group">
									<label>Year Of Passing</label> <font style="color: red;">*</font>

									<div class="row clearfix">
										<div class="col-xs-4">
										<form:input type="textt" class="form-control" path="yearOfPassing" name="yearOfPassing"
										id="passyearId" placeholder="Year Of Passing" maxlength="4" data-date-format="yyyy" />
										<form:errors path="yearOfPassing" cssClass="error" />
											<div class="color-black-mute">
												<small>Format: yyyy</small>
											</div>
										</div>
										<div class="col-xs-4">
										
										<form:input type="text1" class="form-control" path="percentage" name="percentage"
										id="percentId" placeholder="Percentage/Grade" maxlength="4" data-date-format="yyyy" />
										<form:errors path="percentage" cssClass="error" />								
											
										</div>


										<div class="col-xs-4">

											<form:select id="gradeId" name="grade" path="grade"
												class="form-control">
												<form:option value="">Select Grade</form:option>
												<form:option value="First Class">First Class</form:option>
												<form:option value="Second Class">Second Class</form:option>

											</form:select>
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