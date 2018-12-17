
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
<title>Myjobkart</title>
</head>
<body>


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
				<h3 class="text-center no-margin">Payment page</h3>
				<hr />
				<!-- form post a job -->
				
				
				
				
				<c:if test="${paidshow}">
				
				<div class="row text-center">
					<div class="col-sm-12">
					<div class="jumbotron" style="font-size: 15px;">
							<h3>Welcome to my Jobkart paid service</h3>
							<p style="font-size: 15px;">If you dont have an account you can create one below by entering your email address/username.<br>
							A password will be automatically emailed to you.</p>
							
							
							<form:form id="myForm" method="post" class="login-form clearfix"
						modelAttribute="payments" action="employer_create_profile_payment">
						<div class="form-group">
							<label>Select your Product Type</label>
							<div class="row clearfix text-center" >
								<div class="col-xs-3">
									
								</div> 
								<div class="col-xs-6">
								
								
								<form:select id="selectPrroductInput" name="selectProduct"
										path="selectProduct" class="form-control">
                                  <option value="">Select Category</option> 
                                  <c:forEach var="productLists" items="${productList}">
                                  <option value="${productLists.productType}">${productLists.productType}</option>
                                  </c:forEach>
                               </form:select>
								</div>
								
								<div class="col-xs-3">
							
								</div> 
							</div>
						</div>


					<p><input type="submit" id="btnSubmit" value="Proceed"
								class="btn btn-t-primary btn-theme" /><a
								href="employer_home.html"> <input type="button"
								value="Cancel" class="btn btn-t-primary btn-theme" /></p>
					</form:form>
							
					</div>
					</div>
				</div>
				
				
					
				</c:if>
				<c:if test="${trailshow}">
					<form:form id="myForm" method="post" class="login-form clearfix"
						modelAttribute="payments" action="employer_create_profile_payment">



						<div class="alert alert-warning" style="border: 16px solid transparent; border-color: #faebcc;">
							<strong style="font-size: 23px;">Trail Version!</strong><br>
							<div>Thanks for registering with
							myjobkart!. Please click to proceed for your <strong>30 days</strong> free service.
							After proceed you can start your profile search, jobpost,
							etc......</div>
						</div>



						<div class="modal-footer">
							
							<button class="btn btn-success btn-theme">Proceed</button>
							<a href="employer_home.html"><input type="button" value="Close" class="btn btn-default btn-theme"
								data-dismiss="modal" /> </a>

						</div>
					</form:form>
				</c:if>

				<!-- end form post a job -->
			</div>
		</div>
	</div>
	<!-- </div>
	</div> -->




</body>
</html>





