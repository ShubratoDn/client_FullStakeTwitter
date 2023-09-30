
<%@page import="com.twitter.clone.entity.Post"%>
<%@page import="com.twitter.clone.entity.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  
<%
	
	User visitedUser = (User) request.getAttribute("visitedUser");	
	User loggedUser = null; 
	if(session.getAttribute("user") != null){
		loggedUser = (User) session.getAttribute("user");
	}
	
	if(loggedUser == null && visitedUser == null){
		response.sendRedirect("home");
	}
%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title><%=visitedUser.getName() %></title>

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
		
		    <main class="offset-lg-3 col-lg-9">
        <div class="container-fluid">
            <!-- profile container -->
            <div class="profile-container border d-flex align-items-center">
                <img src="resources/image/userImages/<%=visitedUser.getImage() %>" alt="User">
                <div class=" mx-4">
                    <table class="table">
                        <tr>
                            <th><b>Name </b></th>
                            <td><%=visitedUser.getName() %></td>
                        </tr>
                        <tr>
                            <th><b>Email </b></th>
                            <td><%=visitedUser.getEmail() %></td>
                        </tr>
                        <tr>
                            <th><b>Join Date </b></th>
                            <td>17 May, 2023</td>
                        </tr>
                    </table>
                </div>
                <%
                	if(request.getAttribute("ownerVisiting") == null){
                		%>
	                <button id="follow-button" class="btn btn-success follow-button" data-user-id="<%=visitedUser.getId() %>">Follow</button>                		
                		<%
                	}
                %>
            </div>

			
			<%
				Object postAdded = (Object) session.getAttribute("postAdded");
				if(postAdded != null){
					%>
					
            <div class="alert alert-success alert-dismissible fade show" role="alert">
                <strong>Congratulations!</strong> Post Added Successfully
                <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                  <span aria-hidden="true">&times;</span>
                </button>
            </div>
					
					<%
					session.removeAttribute("postAdded");
				}
			%>
			








			
			<%@page import="java.sql.Timestamp"%>
			<%@page import="java.util.concurrent.TimeUnit"%>
			<%@page import="java.util.List"%>	

			<!-- Post Starts -->
            <div class="user-blogs-container row">

				<%
				List<Post> allPosts = (List<Post>) request.getAttribute("allPosts");

				for (Post post : allPosts) {

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

				<!-- user blog card -->
                <div class="col-lg-4 my-2">
                    <div class="content-box">                        
                        <div class="content-header-info d-flex align-items-center">
                            <!-- user image -->
                            <img class="content-owner-image p-1" src="resources/image/userImages/<%=post.getUser().getImage() %>" alt="user">
        
                            <!-- user info -->
                            <div class="">
                                <span class="font-weight-bold pl-3"><%=post.getUser().getName() %></span>
                                <br>
                                <span class="text-muted pl-3"><%=timeAgo %></span>
                            </div>
                        </div>
                        <!-- content text -->
                        <div class="content-text my-3 position-relative">
                        	<a href="/post/<%=post.getId() %>" class="stretched-link"></a>
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
									  <source src="resources/post_files/<%=media_path %>" type="video/mp4">
									</video>										
									<%
								}else{
									%>
									<img class="card-img" src="resources/post_files/<%=media_path %>" alt="POST">						
									<% 
								}
							%>
						</div>
						<%
							} 
						%>
                        <!-- content interaction -->
                        <div class="content-interaction">
                            <button> <i class="fa-regular fa-heart p-1"></i> Love</button>
                            <a href=""><i class="fa-sharp fa-regular fa-comment p-1"></i> Comment</a>
                            <button>Share</button>
                        </div>
                    </div>
                </div>
			<%} %>


            </div>
			<!-- Blog Ends -->











        </div>
    </main>
		
		
		
	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
		


	<script>
		$(document).ready(function() {
		    // Attach a click event handler to the "Follow" button
		    $(".follow-button").on("click", function() {
		        // Get the user ID from the data attribute
		        var userId = $(this).data("user-id");
		
		        // Create a FormData object
		        var formData = new FormData();
		
		        // Append the user ID as a form field
		        formData.append("userId", userId);
		
		        // Send a POST request to "/follow" with the FormData
		        $.ajax({
		            type: "POST",
		            url: "/follow",
		            data: formData,
		            processData: false, // Prevent jQuery from processing the data
		            contentType: false, // Ensure the correct content type is set
		            success: function(response) {
		                // Handle the success response here, if needed
		                console.log("Successfully followed user with ID: " + response);
		                if(response === "FOLLOWED"){
		                	$("#follow-button").removeClass("btn-success").addClass("btn-danger").text("Unfollow");
		                }else if(response === "UNFOLLOWED"){
		                	$("#follow-button").removeClass("btn-danger").addClass("btn-success").text("Follow");
		                }
		            },
		            error: function(xhr, status, error) {
		                // Handle any errors that occur during the request
		                console.error("Error following user: " + error);
		            }
		        });
		    });
		});
	</script>
		
		<script>
		$(document).ready(function() {
		    var followButton = $("#follow-button");
		    var userId = followButton.data("user-id");
		    // Send an initial AJAX request to check if the user is following
		    $.ajax({
		        type: "POST",
		        url: "/isFollowing",
		        data: { userId: userId },
		        success: function(response) {
		        	// If the response is true, the user is following; update the button
		            if (response === true) {
		                $("#follow-button").removeClass("btn-success").addClass("btn-danger").text("Unfollow");
		            } else {
		                // If the response is false, the user is not following; button remains as "Follow"
		            }
		        },
		        error: function(xhr, status, error) {
		            // Handle any errors that occur during the request
		            console.error("Error checking if user is following: " + error);
		        }
		    });
		});
		</script>

<%session.removeAttribute("visitedUser"); %>>

</body>
</html>