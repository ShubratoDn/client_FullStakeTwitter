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
            <a href="#"> <img alt="logo" src="resources/image/logo.png" class="logo"> TwitterClone</a>
        </div>

        <ul class="nav-links">
            <li class="nav-link">
                <a href="/home"><i class="fa-solid fa-house nav-icon"></i><span>Home</span></a>
            </li>
            <li class="nav-link">
                <a href="/profile"> <img class="user_image_nav" alt="user" src="resources/image/userImages/<%=user.getImage() %>"> <span><%=user.getName() %></span></a>
            </li>  
             <li class="nav-link">
                <a href="search_user"><i class="fas fa-users"></i><span> Search User </span></a>
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

    