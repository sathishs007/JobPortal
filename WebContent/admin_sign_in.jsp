<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>


<div class="page">
	<!-- quick search -->
	
	<!--/ quick search -->
	<!--/ HEADERS -->

	<!-- page title -->
	
	<!--/ page title -->


	<main class="page-content">
	<div class="grid-row bg-color2" style="margin-left: 35%">
		<div class="grid-vid ">
			<div class="grid-col grid-col-4 ">
				<div class="row">
					<br />
					<button type="submit"
						class="wpb_button wpb_btn-rounded wpb_btn-small " value="Submit"
						style="margin-left: 35%">
						<i class="fa fa-user"></i>&nbsp;&nbsp;Admin Login
					</button>
				</div>


				<section class="widget widget-appointment">
					<form:form method="POST" modelAttribute="adminLogin">
						<c:if test="${not empty message}">
							<div class="message red">${message}</div>
						</c:if>
						<fieldset>
							<label> <font color="red">*</font>Email:
							</label>
							<div class="row">
								<form:input type="email" placeholder="Enter Email" path="emailAddress" />
								<i class="fa fa-envelope"></i>
								<form:errors path="emailAddress" cssClass="error" />

							</div>
							<br /> <label> <font color="red">*</font>Password:
							</label>
							<div class="row">
								<form:input type="password" placeholder="Enter Password"
									path="password" />
								<i class="fa fa-lock"></i>
								<form:errors path="password" cssClass="error" />
							</div>
							<br />
							<div class="wpb_content_element grid-vid">
								<div class="white-space-10"></div>
								<div class="grid-col grid-col-3 " style="margin-left: -40%">
									<div class="checkbox flat-checkbox">
										<label> <input type="checkbox"> <span
											class="fa fa-check"></span> Remember me?
										</label>
									</div>
								</div><br/>
								<button class="wpb_button wpb_btn-rounded wpb_btn-small ">Submit</button>
								 <a href="index.html">	<span 
										class="wpb_button wpb_btn-rounded wpb_btn-alt wpb_btn-small">Cancel</span>
								</a>
							
								<div class="grid-col grid-col-3"
									 style="margin-left:-40%">
									<a href="admin_forgot_password.html"><strong style="font-size: 11px;color: #008fd5">Forgot
											password?</strong></a>
								</div>
							</div>
						</fieldset>
					</form:form>
				</section>
			</div>
		</div>
	</div>

	<!-- FOOTERS -->
</div>




