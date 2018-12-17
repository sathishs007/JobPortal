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
<title>Myjobkart</title>
<link rel="apple-touch-icon"
	href="resources/theme/images/apple-touch-icon.png">
<link rel="shortcut icon" href="resources/theme/images/favicon.ico"
	type="image/x-icon">
<link href="resources/plugins/bootstrap-3.3.2/css/bootstrap.min.css"
	rel="stylesheet">
<link
	href="resources/plugins/font-awesome-4.2.0/css/font-awesome.min.css"
	rel="stylesheet">
<link href="resources/plugins/magnific-popup/magnific-popup.css"
	rel="stylesheet">
<link href="resources/theme/css/theme.css" rel="stylesheet">
<link href="resources/theme/css/theme-custom.css" rel="stylesheet">
</head>
<body>
	<div class="box-list">
		<div class="item">
			<div class="row">
			<h3 class="text-center no-margin titleunderline">Contacts Details</h3>
				<hr />
			<c:if test="${!empty viewcontactdetail}">
			<div class="pi-responsive-table-sm">
								<table style="border: 1px solid;">
									<tbody>
									<tr>
										<td style="padding-left:50px;width:200px;"> <label class="color-white-mute list-capitalize"> Name </label></td>
									<td class="color-white-mute list-capitalize ">:</td>
									<td><c:out value="${viewcontactdetail.name}"></c:out></td>
									</tr>
									<tr>
									<td style="padding-left:50px;width:200px;"> <label class="color-white-mute list-capitalize "> Phone Number </label></td>
									<td>:</td>
									<td><c:out value="${viewcontactdetail.phoneno}"></c:out></td>
									</tr>
									<tr>
									<td style="padding-left:50px;width:200px;"> <label class="color-white-mute list-capitalize "> Email </label></td>
										<td class="color-white-mute list-capitalize ">:</td>
									<td><c:out value="${viewcontactdetail.email}"></c:out></td>
									</tr>
									<tr>
									<td style="padding-left:50px;width:200px;"> <label class="color-white-mute list-capitalize "> Subject </label></td>
							<td class="color-white-mute list-capitalize ">:</td>
									<td><c:out value="${viewcontactdetail.subject}"></c:out></td>
									</tr>
									<tr style="vertical-align:top;">
									<td style="padding-left:50px;"> <label class="color-white-mute list-capitalize "> Message </label></td>								
									<td class="color-white-mute list-capitalize ">:</td>
									<td style="padding-right:50px;"><p><c:out value="${viewcontactdetail.message}" escapeXml="false"></c:out></p>
									</td>
									</tr>
									</tbody>
								</table>
							</div>
			</c:if>
			<div class="box-list" >
						<a href="view_contact.html" style="margin-left:450px;"> <input type="button"  
							value="Back" class="btn btn-t-primary btn-theme"/>
						</a>
			</div>
			</div>
		</div>
	</div>
	

</body>
</html>