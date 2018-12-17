<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<style>
.error {
	color: #ff0000;
}
</style>

<div class="page">
	<!-- quick search -->
	<form id="quick-search" class="quick-search">
		<fieldset>
			<legend>Quick Search:</legend>

			<input type="text" placeholder="Search by name" id="docname"
				name="docname" class="ui-autocomplete-input" autocomplete="off">

			<select>
				<option value="0">Speciality</option>
				<option value="1">Ophthomologist</option>
				<option value="2">Dermatologist</option>
				<option value="3">Neorologist</option>
				<option value="4">Surgeon</option>
			</select> <select>
				<option value="0">Locations</option>
				<option value="1">Primary Health Care</option>
				<option value="2">Gynaecological Clinic</option>
				<option value="3">Diagnosis With Precise</option>
				<option value="4">Cardiac Clinic</option>
				<option value="5">General Surgery</option>
			</select>

			<button type="submit">Search</button>
			<div class="switcher">
				<button id="quick-search-switcher" type="button">Find a
					doctor</button>
			</div>
		</fieldset>
	</form>
	<!--/ quick search -->
	<!--/ HEADERS -->

	<!-- page title -->
	<section class="page-title">
		<div class="grid-row clearfix">
			<h1>Forgot Password</h1>

			<nav class="bread-crumbs">
				<a href="welcome.html">Home</a>&nbsp;&nbsp;<i
					class="fa fa-angle-right"></i>&nbsp; <a href="#">Forgot
					Password</a>
			</nav>
		</div>
	</section>
	<!--/ page title -->

<main class="page-content">
	<div class="grid-row bg-color2">
		<div class="grid-md ">
			<div class="grid-col grid-col-4 ">
				<button type="submit" 
					class="wpb_button wpb_btn-rounded wpb_btn-small grid-vid2" value="Submit">
					<i class="fa fa-user"></i>&nbsp;&nbsp;Admin Forgot Password
				</button>
			<section class="widget widget-appointment">
				<form:form method="POST" modelAttribute="signIn">
				<c:if test="${not empty message}">
						<div class="message red">${message}</div>
					</c:if>
					<fieldset>
						<label> <font color="red">*</font>Email:
						</label>
						<div class="row">
							<form:input type="email" placeholder="Email" path="emailAddress" />
							<i class="fa fa-envelope"></i>
							<form:errors path="emailAddress" cssClass="error" />
						</div>
						</fieldset><br />
						<div class="wpb_content_element grid-vid">
							<button type="submit"
								class="wpb_button wpb_btn-rounded wpb_btn-small " value="Submit">Submit</button>
							<a href="admin_sign_in.html">
								<span 
									class="wpb_button wpb_btn-rounded wpb_btn-alt wpb_btn-small">Cancel</span>
							</a>
						</div>
				</form:form>
			</section>
		</div>
	</div>
	</main>
	<!-- FOOTERS -->
</div>




