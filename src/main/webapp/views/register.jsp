
<%@page import="com.twitter.clone.helpers.ServerMessage"%>
<%@page import="com.twitter.clone.entity.User"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@ page isELIgnored="false"%>
	
	<%
		if((User)session.getAttribute("user") != null){
			response.sendRedirect("home");				
		}
	%>
		
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Register to loggy</title>
<%@include file="partials/header-links.jsp"%>

</head>
<body style="min-height: 100vh;"
	class="d-flex  justify-content-center align-items-center">



	<main class="login-page w-100">
		<form action="register" enctype="multipart/form-data" method="post" class="col-lg-5 border m-auto py-4">
			<h2 class="text-center">Register to Loggy</h2>
			<br>

			<!-- Showing Error MEssage -->
			<%
			if (request.getAttribute("serverMsg") != null) {
				ServerMessage sm = (ServerMessage) request.getAttribute("serverMsg");
				if (sm.getMessage() != null) {
			%>
			<div class="alert <%=sm.getCss()%>" role="alert">
				<%
					for(String msg : sm.getMessage()){
						%>
							<li class="m-0"><%= msg %></li>
						<%
					}
				%>
			</div>
			<%
			}
			}
			%>


			<div class="form-group">
				<input type="text" value="${name}" name="name" class="form-control"
					placeholder="Enter your full name">
			</div>
			<div class="form-group">
				<input type="email" name="email" value="${email}"
					class="form-control" placeholder="Enter email" id="email">
			</div>
			<div class="form-group">
				<input type="password" name="password" class="form-control"
					placeholder="Enter password" id="pwd" value="${password}">
			</div>

			<div class="form-group">
				<label for="img">Your image:</label> <input type="file"
					name="user_image" class="form-control" placeholder="Enter password"
					id="img">
			</div>
			<input type="submit" class="btn w-100 btn-primary" value="Register Now">
		</form>
	</main>




</body>
</html>