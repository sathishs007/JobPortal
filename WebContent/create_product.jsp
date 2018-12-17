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
		$('#btnSubmit').click(function(e) {
			var isValid2 = true;
			$('select[type="text"]').each(function() {
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
</script>



<html>
<body>

	<section class="smallSection">
		<div class="box-list">
			<div class="item">
				<div class="row">
					<div class="col-sm-12 col-md-12 col-xs-12 col-lg-12"
						style="border: 0px solid #fff;">
						<br>
						<div class="text-center">
							<h3 class="text-center no-margin titleunderline">Product
								Creation</h3>
							<hr />
						</div>
						<br />
						
						<div class="warning" style="width: 100%;text-align:left;">
							<c:if test="${not empty sucessmessage}">
								<div class="alert alert-success" role="alert">
							<button type="button" class="close" data-dismiss="alert"
									aria-label="Close">
									<span aria-hidden="true">&times;</span>
								</button>
									<strong>Success</strong>
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
							</div>
						<form:form id="productCreation" modelAttribute="product"
							commandName="product" method="post">
							<c:if test="${not empty message}">
								<div class="error">
									<c:out value="${message}"></c:out>
								</div>
							</c:if>
							<div class="row">
								<div class="col-sm-6">
									<div class="form-group">
										<!-- <label for="contact-subject">Product Type</label> -->
										<form:select name="category" type="text" class="form-control"
											path="productType" id="contact-subject" placeholder="Subject">
											<form:option value="">Product Type</form:option>
											<form:option value="gold">Gold</form:option>
											<form:option value="platinum">Platinum</form:option>
											<form:option value="silver">Silver</form:option>
										</form:select>
										<form:errors path="productType" cssClass="message red"></form:errors>
										<p id="errorproducttype" class="red"></p>
									</div>
								</div>
								<div class="col-sm-6">
									<div class="form-group">
										<!-- <label for="register-surname">Services<font
										style="color: red;">*</font></label> -->
										<form:input type="text" class="form-control"
											id="register-surname" path="services" placeholder="Services" />
										<form:errors path="services" cssClass="error" />
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-sm-6">
									<div class="form-group">
										<!-- <label for="register-surname">Product price(In dollars)<font
										style="color: red;">*</font></label> -->
										<form:input type="text" class="form-control"
											id="register_productprice" path="productPrice"
											placeholder="Product price(In dollars)" />
										<form:errors path="productPrice" cssClass="error" />

									</div>
								</div>
								<div class="col-sm-6">
									<div class="form-group">
										<!-- <label for="register-surname">Validity Date(No.of Days)<font
										style="color: red;">*</font></label> -->
										<form:input type="text" class="form-control"
											id="register-validitydate" path="durationDate"
											placeholder="Validity Date(No.of Months)" />
										<form:errors path="durationDate" cssClass="error" />
									</div>
								</div>
							</div>
							<div style="float: right;">
								<button type="submit" id="btnSubmit"
									class="btn btn-t-primary btn-theme">Register</button>
							</div>
							<br>
							<br>
						</form:form>
						<div align="left">
							<label for="register-surname"><span
								style="color: #008753;">Note</span> : Services must be separated
								by comma 
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>
</body>
</html>


