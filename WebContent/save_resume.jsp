<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<html>

<body>
	<div class="body-content clearfix">
		<div class="block-section bg-color2 line-bottom" id="sh-grid">
			<div class="container">
				<div class="row">
					<div class="col-lg-3">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal"
								aria-label="Close">
								<span aria-hidden="true">&times;</span>
							</button>
							<h4 class="modal-title">Save Resume</h4>
						</div>
						<div class="modal-body">
							<p>If Sure You add this Resume in your list</p>
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-theme btn-default"
								data-dismiss="modal">No</button>
							<a href="employer_home.html">
								<button type="button" class="btn btn-theme btn-success">Yes</button>
							</a>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script src="resources/plugins/jquery.js"></script>
	<script src="resources/plugins/jquery.easing-1.3.pack.js"></script>
	<script src="resources/plugins/bootstrap-3.3.2/js/bootstrap.min.js"></script>
	<script
		src="resources/plugins/magnific-popup/jquery.magnific-popup.min.js"></script>
	<script src="resources/theme/js/theme.js"></script>
	<script src="http://maps.googleapis.com/maps/api/js?sensor=false"
		type="text/javascript"></script>
	<script src="resources/plugins/gmap3.min.js"></script>
	<script src="resources/theme/js/map-detail.js"></script>
</body>
</html>