<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
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
			var fileExtension = [ 'jpeg', 'jpg', 'png', 'bmp' ];
			if ($.inArray($(obj).val().split('.').pop().toLowerCase(),
					fileExtension) == -1){
				
				alert("Only '.jpeg','.jpg', '.png', '.bmp' formats are allowed.");
			location.reload();
			}
		}
	</script>

	<script type="text/javascript">
		function fileChecks(obj) {
			var fileExtension = [ 'doc', 'pdf', ];
			if ($.inArray($(obj).val().split('.').pop().toLowerCase(),
					fileExtension) == -1)
				alert("Only '.Word','PDF' formats are allowed.");
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

		function limitText1(limitField, limitCount, limitNum) {
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
					<!-- <h1 class="text-center no-margin">Banner Post</h1> -->
						<h3 class="text-center no-margin">Banner Post</h3>
				            <hr />
						<!-- form post a job -->
						<form:form id="myForm" method="post" class="login-form clearfix"
							commandName="bannerpay" enctype="multipart/form-data">
							<div class="warning" style="width: 100%;text-align: center; padding-left: 20px; padding-right: 19px;">
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
						</div>
							
							<c:if test="${not empty message}">
								<div class="wpb_alert wpb_alert_alt wpb_alert_alt_confirm">
									<h1>Success</h1>
									<c:out value="${message}"></c:out>
								</div>
							</c:if>

							<div class="form-group">
								<label>Banner Name</label> <font style="color: red;">* </font>
								<form:input type="text" class="form-control" path="bannerName"
									id="bannerNameInput" placeholder="Banner Name" />
								<form:errors path="bannerName" cssClass="error" />
							</div>

							<div class="form-group">
								<label>Upload</label><input type="file" name="banner"
									path="bannerImage" id="PhotoInput" size="50"
									onchange="fileCheck(this)" />
								<form:errors path="bannerImage" cssClass="error" />
							</div>

							<div class="form-group">
								<label>Post Page Type</label> <font style="color: red;">*
								</font>
								<form:select id="postPageInput" name="postPage" path="postPage"
									class="form-control">
									<form:option value="">Select </form:option>
									<form:option value="home">home page</form:option>
									<form:option value="about_as">About Us page</form:option>

								</form:select>
								<form:errors path="postPage" cssClass="error" />
							</div>

							<div class="form-group">
								<label>No Of Months</label> <font style="color: red;">* </font>

								<form:select id="noMonthInput" name="totalMonth"
									path="totalMonth" class="form-control">
									<form:option value="0">No of months</form:option>
									<form:option value="1">1</form:option>
									<form:option value="2">2</form:option>
									<form:option value="3">3</form:option>
									<form:option value="4">4</form:option>
									<form:option value="5">5</form:option>
								</form:select>
								<form:errors path="totalMonth" cssClass="error" />
							</div>

							<div class="form-group ">
								<input type="submit" value="Post Banner"
									class="btn btn-t-primary btn-theme" /> <a
									href="employer_home.html"> <input type="button"
									value="Cancel" class="btn btn-t-primary btn-theme" /></a>
							</div>
						</form:form>
					</div>
				</div>
			</div>
		<!-- </div>
	</div> -->
</body>
</html>