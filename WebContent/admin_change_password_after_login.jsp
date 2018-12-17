<!-- Themes styles-->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<main class="page-content">
<div class="grid-row">
	<div class="grid-col grid-col-9 bg-color2 bg1-width">
		<div class="vc_row-fluid ">
			<div class="vc_span9">
				<div class="grid-mdi">
					<div class="row">
						<button type="submit"
							class="wpb_button wpb_btn-rounded wpb_btn-small grid-vid2"
							disabled="true" value="Submit">
							<i class="fa fa-user"></i>&nbsp;&nbsp;Password Change
						</button>
					</div>
					<section class="widget widget-appointment">
						<form:form method="POST" modelAttribute="changePassword">
						<c:if test="${not empty message}">
								<div class="message red">${message}</div>
							</c:if>
							<fieldset>
								<label>Password<font color="red">*</font>
								</label>
								<div class="row">
									<i class="fa fa-lock"></i>
									<form:input name="password" type="password" path="password"
										id="passwordInput" placeholder="Enter New Password" />
									<form:errors path="password" cssClass="error" />
								</div>
								<label>Confirm Password<font color="red">*</font>
								</label>
								<div class="row">
									<i class="fa fa-lock"></i>
									<form:input type="password" path="confirmPassword"
										id="passwordInput" placeholder="Enter confirm Password" />
									<form:errors path="confirmPassword" cssClass="error" />
								</div>
							</fieldset>
							<br />
							<div class="wpb_content_element grid-vid">
								<button type="submit"
									class="wpb_button wpb_btn-rounded wpb_btn-small "
									value="Submit">Submit</button>
								<a href="view_admin_profile.html">
									<span 
										class="wpb_button wpb_btn-rounded wpb_btn-alt wpb_btn-small">Cancel</span>
								</a>
							</div>
						</form:form>
					</section>
					<!-- form login -->
				</div>
			</div>
		</div>
</main>
