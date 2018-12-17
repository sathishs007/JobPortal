<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>

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
	<!-- body-content -->
	
			<div class="box-list">
				<div class="item">
					<div class="row">
						<div class="text-center">
							<h3 class="text-center no-margin">Product Details</h3>
				<hr />
						</div>
						<!-- form login -->
						<form:form id="myForm" method="post" class="login-form clearfix"
							modelAttribute="payment" enctype="multipart/form-data">
							<c:if test="${not empty message}">
								<div class="error">${message}</div>
							</c:if>

							<div class="form-group">
								<label>Choose Product</label>
										<form:input type="text" path="selectProduct" id="selectPrroductInput"
											class="form-control" />
								</div>

							<div class="form-group">
								<label>No Of Months</label>
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
					
							<div class="text-right">
								<div class="form-group no-margin">
									<button class="btn btn-success btn-theme" id="btnSubmit" >Pay Now</button>


									<a href="employer_enrolled_details.html"> <input
										type="button" value="Cancel" class="btn btn-theme btn-default" /></a>
								</div>
							</div>
						</form:form>
						<!-- form login -->
					</div>
				</div>
			</div>
	</body>
</html>