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
			$('input[type="file"]').each(function() {
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

<script type="text/javascript">
		function fileCheck(obj) {
			var fileExtension = [ 'xlsx'];
			if ($.inArray($(obj).val().split('.').pop().toLowerCase(),
					fileExtension) == -1) {
				document.getElementById("uploadFiles").value = '';
				alert("Only '.xlsx' is formats are allowed.");
			}
		}
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
						
						<div style="margin-left: 0.5%; margin-bottom: -10px">
							<h2 class="textalign" style="margin-top: -10px;">
								Upload JobPost</span>
							</h2>
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
						<form:form id="productCreation" modelAttribute="uploadFile"
							commandName="uploadFile" enctype="multipart/form-data"
							action="jobpost_upload.html" method="POST">
							<div class="form-group">
								<div class="row clearfix">
									<div class="col-xs-6">
										<label>Upload Files</label> <input type="file"
											name="uploadCompany" path="uploadCompany" onchange="fileCheck(this)"
											 id="uploadFiles" size="50"
											placeholder="RESUME" />
									</div>
								</div>
							</div>
							<div>
								<button type="submit" id="btnSubmit"
									class="btn btn-primary textalign">Submit</button>
								<!-- <button type="cancel" 
									class="btn btn-primary textalign" >Cancel</button>	 -->
								<a href="employer_home.html"> <input value="Cancel"
									class="btn btn-primary textalign" type="button"></a>
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


