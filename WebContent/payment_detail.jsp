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
			echo("hello");
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

			if (isValid == false)
				e.preventDefault();

		});
	});
	
</script>
<html>

<body>
	<!-- wrapper page -->
	<!-- end main-header -->

	<script type="text/javascript">
    function fileCheck(obj) {
            var fileExtension = ['jpeg', 'jpg', 'png','bmp'];
            if ($.inArray($(obj).val().split('.').pop().toLowerCase(), fileExtension) == -1)
                alert("Only '.jpeg','.jpg', '.png', '.bmp' formats are allowed.");
    }
</script>


	<!-- body-content -->
	<div class="container">
		<div class="col-sm-12">
			<div class="box-list">
				<div class="item">
					<div class="row">
						<div class="text-center">
							<h3 class="margin-top">
								<i class=" fa fa-pencil"></i>Create Payment <i
									class="fa fa-user"></i>
							</h3>
						</div>
						<!-- form login -->
						<form:form id="myForm" method="post" class="login-form clearfix"
							modelAttribute="payment" enctype="multipart/form-data">
							<c:if test="${not empty message}">
								<div class="error">${message}</div>
							</c:if>

							<div class="form-group">
								<label>Choose Product</label>

								<div class="row clearfix">
									<div class="col-xs-6">
										<form:input type="text" path="selectProduct" id="selectPrroductInput"
											class="form-control" />
										<form:select id="selectPrroductInput" name="selectProduct"
											path="selectProduct" class="form-control">
											<form:option value="">Select</form:option>
											<form:option value="silver">Sliver</form:option>
											<form:option value="gold">Gold</form:option>
											<form:option value="platinum">Platinum</form:option>
										</form:select>

										<form:errors path="selectProduct" cssClass="error" />
									</div>
								</div>
							</div>

							<div class="form-group">
								<label>No Of Months</label>

								<div class="row clearfix">
									<div class="col-xs-6">
										<form:select type="text" id="noMonthInput" name="totalMonth"
											path="totalMonth" class="form-control">
											<form:option value="0">no of month</form:option>
											<form:option value="1">1</form:option>
											<form:option value="2">2</form:option>
											<form:option value="3">3</form:option>
											<form:option value="4">4</form:option>
											<form:option value="5">5</form:option>
										</form:select>

										<form:errors path="totalMonth" cssClass="error" />
									</div>
								</div>
							</div>
							<div class="text-center">
								<div class="form-group no-margin">
									<button type="submit"  class="btn btn-theme  btn-primary " id="btnSubmit">Pay Now</button>


									<a href="jobseeker_renewal_alert.html"> <input
										type="button" value="Cancel" class="btn btn-theme btn-default" /></a>
								</div>
							</div>
						</form:form>
						<!-- form login -->
					</div>
				</div>

			</div>
		</div>
	</div>
	
	
</body>
</html>