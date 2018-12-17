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
			$('input[type="password"]').each(function() {
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
</script>

<html>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>MyjobKart</title>
</head>
<body>
<div class="container">
<div class="col-md-12">
	<div class="box-list">
		<div class="item">
			<div class="row">
				<div class="block-section bg-color2">
					<!-- <div class="container"> -->
					<div class="panel panel-md">
						<div class="panel-body">
							<div class="row">
								<c:if test="${not empty message}">
								<div class="wpb_alert wpb_alert_alt wpb_alert_alt_confirm">
									<div class="messages">${message}</div>
								</c:if>
								
									<div class="white-space-10"></div>
									<div class="modal-header">

										<h4 class="modal-title">
											<i class="fa fa-unlock-alt pull-left bordered-right"></i>
											Change Password 
										</h4>

									</div>
									<div class=modal-body>
										<form:form id="myForm" method="post"
											class="login-form clearfix" commandName="changePassword">

											<div class="form-group">
												<label>Password</label><font style="color: red;">* </font>
												<form:input name="password" type="password" path="password"
													class="form-control" id="passwordInput"
													placeholder="Enter New Password" maxlength="8" oncopy="return false" onpaste="return false" />
												<form:errors path="password" cssClass="error" />
											</div>
											<div class="form-group">
												<label>Confirm Password</label><font style="color: red;">* </font>
												<form:input type="password" class="form-control"
													path="confirmPassword" id="passwordInput"
													placeholder="Enter confirm Password" maxlength="8" oncopy="return false" onpaste="return false" />
												<form:errors path="confirmPassword" cssClass="error" />
											</div>
											
											<div class="modal-footer">
												<button id="btnSubmit" class="btn btn-theme btn-success">Submit</button>

												<a href="jobseeker_home.html"> <input type="button"
													value="Cancel" class="btn btn-default btn-theme" /></a>
											</div>
											<div class="error">
												<div class="col-md-12" style="margin-left: 50px;"></div>
											</div>
										</form:form>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
</body>
</html>