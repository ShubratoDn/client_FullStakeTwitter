

<%@page import="com.twitter.clone.entity.User"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false"%>

<%
	User loggedUser = null; 
	if(session.getAttribute("user") != null){
		loggedUser = (User) session.getAttribute("user");
	}
%>

<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Home</title>

	<%@include file="partials/header-links.jsp"%>

</head>

<body class="row">

		<%
			if(loggedUser != null){
			%>
				<%@include file="partials/left_sidenav_after_log.jsp"%>
			<%				
			}else{
			%>
				<%@include file="partials/left_sidenav_before_log.jsp"%>
			<%
			}
		%>
	

	<main class="offset-lg-3 col-lg-6">
		<div class="body-nav">Home</div>
				
			<!-- Write blog -->
		<%
			if(loggedUser != null){
			%>
				<%@include file="partials/writeBlog.jsp"%>
			<%				
			}
		%>
		


		<div class="all-contents">
			<%@include file="partials/AllPosts.jsp" %>
		</div>

	</main>


	<!-- Right Side Nav -->
		<%
			if(loggedUser != null){
			%>
				<%@include file="partials/rightside_after_log.jsp"%>
			<%				
			}else{
			%>
				<%@include file="partials/rightside_before_log.jsp"%>
			<%
			}
		%>

		<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
		
</body>

</html>