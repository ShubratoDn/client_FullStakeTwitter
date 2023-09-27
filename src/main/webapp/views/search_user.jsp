

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
<title>Follow to someone</title>

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
	
		<h3 class="my-4">Search User by name</h3>
		
		<div class="search_box">			
			<input id="search_user_input" class="form-control my-2" type="text" name="search_user" placeholder="Search By User name">			
		</div>
		
		<div class="user_list">		
<!-- 			<div class="border rounded px-3 py-2 d-flex justify-content-between align-items-center my-2"> -->
<!-- 				<span> -->
<!-- 					<img src="/resources/image/userImages/43d80c4d0281929ec399Profilepic.jpeg" class="user-image mr-1"> Shubrato Debnath -->
<!-- 				</span>  -->
<!-- 				<a href="home" class="btn btn-sm btn-success"> View Profile</a> -->
<!-- 			</div>						 -->
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


	<script>
	
	 // Function to update the user list based on the search result
    function updateUserList(users) {
        const user_list = document.querySelector('.user_list');
        user_list.innerHTML = ''; // Clear the existing user list

        users.forEach(user => {
        	let image = "/resources/image/userImages/"+user.image;
        	        		
            const userDiv = document.createElement('div');
            userDiv.classList.add('border', 'rounded', 'px-3', 'py-2', 'd-flex', 'justify-content-between', 'align-items-center', 'my-2');
            userDiv.innerHTML = `
                <span>
                    <img src="`+image+`" class="user-image mr-1"> `+user.name+`
                </span>
                <a href="profile/`+user.id+`" class="btn btn-sm btn-success"> View Profile</a>
            `;
            user_list.appendChild(userDiv);
        });
    }
	
	
	
	document.getElementById('search_user_input').addEventListener('input', function () {
        let inputValue = this.value.trim();
        let url = "/user/"+inputValue;
        console.log(url);
        if (inputValue.length > 0) {
            // Send a GET request to the backend
            fetch(url)
                .then(response => response.json())
                .then(data => {
                    // Update the user list with the search results
                    updateUserList(data);
//                     console.log(data);
                })
                .catch(error => console.error('Error:', error));
        } else {
            // Clear the user list if the input is empty
            updateUserList([]);
        }
    });
	</script>


</body>
</html>