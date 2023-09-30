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
<title>Login Twitter Clone</title>

<%@include file="partials/header-links.jsp"%>

</head>
<body style="min-height: 100vh;" class="d-flex  justify-content-center align-items-center">

	<main class="login-page w-100">
		<form action="login" method="post" class="col-lg-5 border m-auto py-4">
			
			<h2 class="text-center">Login to Twitter Clone</h2>
			<br>
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
			
			<br>		

			<div class="form-group">
				<label for="email">Email address:</label> 
				<input type="email" name="email"
					class="form-control" placeholder="Enter email" id="email" value="${email}">
			</div>
			<div class="form-group">
				<label for="pwd">Password:</label> <input name="password" type="password"
					class="form-control" placeholder="Enter password" id="pwd">
			</div>
			
			<div class="d-flex justify-content-between">
				<div class="form-group form-check">
					<label class="form-check-label"> <input name="password"
						class="form-check-input" type="checkbox"> Remember me
					</label>
				</div>
				
				<span><a href="register">Register to TwitterClone</a></span>

			</div>
			<button type="submit" class="btn btn-primary">Login</button>
			<a href="home" class=" d-block w-100 mt-3">Go back to Home</a>
		</form>
		
	</main>



</body>
</html>