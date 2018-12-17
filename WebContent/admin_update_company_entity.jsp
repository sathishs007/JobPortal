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
						<form:form id="productCreation" modelAttribute="updateCompany"
							commandName="updateCompany" method="post">
							<c:if test="${not empty message}">
								<div class="error">
									<c:out value="${message}"></c:out>
								</div>
							</c:if>
							<div class="row">
								<div class="col-sm-6">
									<div class="form-group">
										<!-- <label for="register-surname">Product price(In dollars)<font
										style="color: red;">*</font></label> -->
										<form:input type="text" class="form-control"
											id="register_productprice" path="companyName"
											placeholder="Enter the Company Name" />
										<form:errors path="companyName" cssClass="error" />

									</div>
								</div>
								<div class="col-sm-6">
									<div class="form-group">
										<!-- <label for="register-surname">Validity Date(No.of Days)<font
										style="color: red;">*</font></label> -->
										<form:input type="email" class="form-control"
											id="register-validitydate" path="email"
											placeholder="Enter the Email" />
										<form:errors path="email" cssClass="error" />
									</div>
								</div>
							</div>
							<div>
								<button type="submit" id="btnSubmit"
									class="btn btn-t-primary btn-theme">Update</button>

								<c:if test="${not empty companyId}">
									<a href="admin_company_view.html"> <input value="Cancel"
										class="btn btn-t-primary btn-theme" type="button"></a>
								</c:if>

								<c:if test="${not empty industryId}">
									<a href="admin_industry_view.html"> <input value="Cancel"
										class="btn btn-t-primary btn-theme" type="button"></a>
								</c:if>

							</div>
							<br>
							<br>
						</form:form>

					</div>
				</div>
			</div>
		</div>
	</section>
</body>
</html>


