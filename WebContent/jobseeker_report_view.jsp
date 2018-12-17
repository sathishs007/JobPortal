<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<%@ page trimDirectiveWhitespaces="true"%>

<link rel="stylesheet"
	href="//code.jquery.com/ui/1.12.0/themes/base/jquery-ui.css">
<link rel="stylesheet" href="/resources/demos/style.css">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.0/jquery-ui.js"></script>


<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<style>
span.pagebanner {
	background-color: #eee;
	border: 1px dotted #999;
	padding: 2px 4px 2px 4px;
	width: 100%;
	margin-top: 10px;
	display: block;
	border-bottom: none;
}

span.pagelinks {
	background-color: #eee;
	border: 1px dotted #999;
	padding: 6px 4px 6px 4px;
	width: 100%;
	display: block;
	border-top: none;
	margin-bottom: -5px;
}
</style>
<link rel="stylesheet" type="text/css"
	href="resources/theme/css/jquery-ui-1.10.4.custom.css" />
<link rel="stylesheet" type="text/css"
	href="resources/theme/css/tables.css" />
<link rel="stylesheet" type="text/css"
	href="resources/theme/css/theme-custom.css" />
	<script>
	$(function() {
		
		$("#datepicker").datepicker({
			onSelect : function(selected) {
				var dt = new Date(selected);
				dt.setDate(dt.getDate());
				$("#todatepicker").datepicker("option", "minDate", dt);

			}
		});
		$("#todatepicker").datepicker({
			numberOfMonths : 1,
			onSelect : function(selected) {
				var dt = new Date(selected);
				var dt2 = new Date(selected);
				dt.setDate(dt.getDate() - 1);
				dt2.setDate(dt.getDate() + 1);
				$("#datepicker").datepicker("option", "maxDate", dt);
				$("#datepicker").datepicker("option", "minDate", dt2);
			}
		});
		
		
	});
</script>
<title>MyjobKart|Jobseeker|home</title>
</head>
<body>
	<div class="box-list">
		<div class="item">
			<div class="row">
				<h3 class="text-center no-margin titleunderline">View Jobseeker
					Reports</h3>
				<hr />
				

				<c:if test="${not empty message}">
					<br />
					<center>
						<div class="message red">${message}</div>
					</center>
				</c:if>

				<c:if test="${!empty jobseekerActivityBOList}">
					<div class="pi-responsive-table-sm" >
						<c:if test="${!empty jobseekerActivityBOList}">
							<display:table id="data"  name="${jobseekerActivityBOList}"
								requestURI="/jobseeker_report_view.html" pagesize="20"
								export="false"
								class="pi-table pi-table-complex pi-table-hovered pi-round pi-table-shadow pi-table-all-borders font-color">
								<display:column property="SNo" title="S.NO" />
								<display:column property="jobseekerName" title="JobSeeker" />
								<display:column property="activityStatus" title="Operation Performed" />
								<display:column property="activityDate" title="Date" />
								<display:column property="employerName" title="Employer" />
								<display:column property="jobPostTitle" title="Job Title" />
									<%-- <display:column url="/jobseeker_profile_details.html" media="html"
								property="jobseekerName"	paramId="id" paramProperty="jbprfId" title="JobSeeker">
									<a href="jobseeker_profile_details.html?id=${data.jbprfId}"></a>
								</display:column> 
								
								<display:column url="/search_job_details.html" media="html"
								property="jobPostTitle"	paramId="id" paramProperty="jbId" title="Job Title">
									<a href="employer_jobpost_details.html?id=${data.jbId}"></a>
								</display:column> --%> 
							</display:table>
						</c:if>
					</div>
				</c:if>
				<div>
				<button id="btnCancel" onclick="history.go(-1);" type="button"
					class="btn btn-t-primary btn-theme">Back</button>

			</div>
			</div>
			
			
		</div>
	</div>
</body>
</html>