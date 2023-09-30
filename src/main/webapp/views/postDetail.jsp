

<%@page import="java.io.File"%>
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
<title>My Followers</title>

<!-- BS4 cdn -->
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">

<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link
	href="https://fonts.googleapis.com/css2?family=Montserrat:wght@400;600;700&display=swap"
	rel="stylesheet">

<!-- font awesome 6 -->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css"
	integrity="sha512-iecdLmaskl7CVkqkXNQ/ZH/XLlvWZOJyj7Yy7tcenmpD1ypASozpmT/E0iPtmFIB46ZmdtAc9eNBvH0H/ZpiBw=="
	crossorigin="anonymous" referrerpolicy="no-referrer" />


<!-- <link rel="stylesheet" href="../resources/css/style.css"> -->


<link rel="stylesheet" href="../resources/css/style.css">
<%-- <link rel="stylesheet" href="<%=session.getServletContext().getRealPath("/")+"resources"+File.separator+"css"+File.separator+"style.css" %>"> --%>
</head>

<body class="row">

	<%@page import="com.twitter.clone.entity.User"%>

    <%
    User user = new User(); 
	if(session.getAttribute("user") != null){
		user = (User) session.getAttribute("user");
	}
    %>
    <!-- navbar design -->
    <nav class="col-lg-3 border side-navbar">
        <div class="navbar-brand">
            <a href="/home">TwitterClone</a>
        </div>

        <ul class="nav-links">
            <li class="nav-link">
                <a href="/home"><i class="fa-solid fa-house nav-icon"></i><span>Home</span></a>
            </li>
            <li class="nav-link">
                <a href="/profile"> <img class="user_image_nav" alt="user" src="../resources/image/userImages/<%=user.getImage() %>"> <span><%=user.getName() %></span></a>
            </li>  
             <li class="nav-link">
                <a href="/search_user"><i class="fas fa-users"></i><span> Search User </span></a>
            </li>
             <li class="nav-link">
                <a href="/my-followers"><i class="fas fa-user-circle"></i><span> My followers</span></a>
            </li>
            <li class="nav-link">
                <a href="/following"><i class="fas fa-user-circle pr-2"></i><span>Following</span></a>
            </li>
            <li class="nav-link">
                <a href="#"><i class="fa-solid fa-gear nav-icon"></i><span>Settings</span></a>
            </li>
			<li class="nav-link">
                <a href="/logout"><i class="fa-solid fa-gear nav-icon"></i><span>Logout</span></a>
            </li>    
            
        </ul>
    </nav>

    

	<main class="offset-lg-3 col-lg-6">		
					
			<%@page import="com.twitter.clone.entity.Post"%>
			<%@page import="com.twitter.clone.services.PostServices"%>
			<%@page import="java.util.ArrayList"%>
			<%@page import="java.sql.Timestamp"%>
			<%@page import="java.util.concurrent.TimeUnit"%>
			<%@page import="java.util.Date"%>
			<%@page import="java.util.List"%>

			<%		
				
			
				Post post =  null;
				post = (Post) request.getAttribute("post");
				
				if(post == null){
					%>
						<div class="display-1 text-center text-danger mt-5">NO POST FOUND</div>
					<%
					
				}else{
				
					
					// Get the current time
					  Timestamp currentTime = new Timestamp(System.currentTimeMillis());

					  // Assuming yourDate is the Date object you want to display
					  Timestamp yourDate = post.getUpload_date(); // Provide your Date object here

					  // Calculate the time difference in milliseconds
					  long timeDiffInMillis = currentTime.getTime() - yourDate.getTime();
						
					  // Calculate the time difference in minutes, hours, and days
					  long minutesDiff = TimeUnit.MILLISECONDS.toMinutes(timeDiffInMillis);
					  long hoursDiff = TimeUnit.MILLISECONDS.toHours(timeDiffInMillis);
					  long daysDiff = TimeUnit.MILLISECONDS.toDays(timeDiffInMillis);

					  // Choose the appropriate time unit to display
					  String timeAgo;
					  if (minutesDiff < 60) {
					    timeAgo = minutesDiff + " min ago";
					  } else if (hoursDiff < 24) {
					    timeAgo = hoursDiff + " hour ago";
					  } else {
					    timeAgo = daysDiff + " day ago";
					  }	
					
			%>

			<div class="content-box">
				<a href="profile/<%=post.getUser().getId()%>">
					<div class="content-header-info d-flex align-items-center">
						<!-- user image -->
						<img class="content-owner-image p-1" src="../resources/image/userImages/<%=post.getUser().getImage() %>"
							alt="user">
	
						<!-- user info -->
						<div class="">
							<span class="font-weight-bold pl-3"><%=post.getUser().getName() %></span> <br>
							<span class="text-muted pl-3"><%=timeAgo %></span>
						</div>
					</div>
				</a>
				<!-- content text -->
				<div class="content-text my-3">
					<h4><%=post.getTitle() %></h4>
					<p><%=post.getContent() %></p>
				</div>				
				
				<%
					if(post.getPostMultimedia() != null){										
						String media_type = post.getPostMultimedia().getMedia_type();
						String media_path = post.getPostMultimedia().getPath();		

				%>	
				
				
				<!-- content-multimedia -->
				<div class="content-multimedia">
					<%
						if(media_type.equals("video")){
							%>
								<video class="card-img" controls>
								  <source src="../resources/post_files/<%=media_path %>" type="video/mp4">
								  You browser does not support this video
								</video>
								
							<%
						}else{
							%>
								<img class="card-img" src="../resources/post_files/<%=media_path %>" alt="POST">						
							<% 
						}
					%>
				</div>
				<%
					} 
				%>
				
				<!-- content interaction -->
				<div class="content-interaction">
					<button>
						<i class="fa-regular fa-heart p-1"></i> Love
					</button>
					<a href=""><i class="fa-sharp fa-regular fa-comment p-1"></i>
						Comment</a>
					<button>Share</button>
				</div>
			</div>
			<%
				}
			%>		
		
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