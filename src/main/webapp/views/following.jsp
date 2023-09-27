

<%@page import="java.util.List"%>
<%@page import="com.twitter.clone.entity.User"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false"%>

<%
	User loggedUser = null;
	if(session.getAttribute("user") != null){
		loggedUser = (User) session.getAttribute("user");
	}else{
		response.sendRedirect("/home");
	}
%>

<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>You are following them</title>

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
	
		<h3 class="my-4">You are following them</h3>		
		
		<div class="user_list">	
			
			<%
			
			List<User> following = (List<User>) request.getAttribute("following");
			if(following == null || following.isEmpty()){
				%>
				<h1 class="text-danger">You are NOT following anyone</h1>
				<%				
				
			}else{				
			
				for(User user: following){
					%>
				<div class="border rounded px-3 py-2 d-flex justify-content-between align-items-center my-2">
					<span>
						<img src="<%="/resources/image/userImages/"+user.getImage() %>" class="user-image mr-1"> <%=user.getName() %>
					</span> 
					<a href="profile/<%=user.getId()%>" class="btn btn-sm btn-success"> View Profile</a>
				</div>	
					<%
				}
			}
			%>			
			
							
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

	<script
		src="https://cdn.jsdelivr.net/npm/jquery@3.6.4/dist/jquery.slim.min.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
		
</body>

</html>