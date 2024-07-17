<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    
<%
	if (session.getAttribute("email") == null) {
		response.sendRedirect("index.jsp");
	}
%>   


<!doctype html>
<html lang="en" data-bs-theme="auto">
  <head><script src="./assets/js/color-modes.js"></script>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="Mark Otto, Jacob Thornton, and Bootstrap contributors">
    <meta name="generator" content="Hugo 0.122.0">
    <title>CatalogBooks</title>

    <link rel="canonical" href="https://getbootstrap.com/docs/5.3/examples/sign-in/">


    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/@docsearch/css@3">

<link href="./assets/dist/css/bootstrap.min.css" rel="stylesheet">

    <style>
      .bd-placeholder-img {
        font-size: 1.125rem;
        text-anchor: middle;
        -webkit-user-select: none;
        -moz-user-select: none;
        user-select: none;
      }

      @media (min-width: 768px) {
        .bd-placeholder-img-lg {
          font-size: 3.5rem;
        }
      }

      .b-example-divider {
        width: 100%;
        height: 3rem;
        background-color: rgba(0, 0, 0, .1);
        border: solid rgba(0, 0, 0, .15);
        border-width: 1px 0;
        box-shadow: inset 0 .5em 1.5em rgba(0, 0, 0, .1), inset 0 .125em .5em rgba(0, 0, 0, .15);
      }

      .b-example-vr {
        flex-shrink: 0;
        width: 1.5rem;
        height: 100vh;
      }

      .bi {
        vertical-align: -.125em;
        fill: currentColor;
      }

      .nav-scroller {
        position: relative;
        z-index: 2;
        height: 2.75rem;
        overflow-y: hidden;
      }

      .nav-scroller .nav {
        display: flex;
        flex-wrap: nowrap;
        padding-bottom: 1rem;
        margin-top: -1px;
        overflow-x: auto;
        text-align: center;
        white-space: nowrap;
        -webkit-overflow-scrolling: touch;
      }

      .btn-bd-primary {
        --bd-violet-bg: #712cf9;
        --bd-violet-rgb: 112.520718, 44.062154, 249.437846;

        --bs-btn-font-weight: 600;
        --bs-btn-color: var(--bs-white);
        --bs-btn-bg: var(--bd-violet-bg);
        --bs-btn-border-color: var(--bd-violet-bg);
        --bs-btn-hover-color: var(--bs-white);
        --bs-btn-hover-bg: #6528e0;
        --bs-btn-hover-border-color: #6528e0;
        --bs-btn-focus-shadow-rgb: var(--bd-violet-rgb);
        --bs-btn-active-color: var(--bs-btn-hover-color);
        --bs-btn-active-bg: #5a23c8;
        --bs-btn-active-border-color: #5a23c8;
      }

      .bd-mode-toggle {
        z-index: 1500;
      }

      .bd-mode-toggle .dropdown-menu .active .bi {
        display: block !important;
      }
    </style>


    <!-- Custom styles for this template -->
    <link href="sign-in.css" rel="stylesheet">
  </head>
  <body class="py-4 bg-body-tertiary">
	<main class="w-100 m-auto px-4">
		<a href="bookcase.jsp" class="btn btn-warning">Back</a>
		<c:forEach items="${user}" var="user" varStatus="id">
			<form action="updateUser" method="get" class="w-100" style="display: flex; align-items: center; flex-direction: column;">
		        <svg xmlns="http://www.w3.org/2000/svg" width="100" height="100" fill="currentColor" class="bi bi-person-circle" viewBox="0 0 16 16">
				  <path d="M11 6a3 3 0 1 1-6 0 3 3 0 0 1 6 0"/>
				  <path fill-rule="evenodd" d="M0 8a8 8 0 1 1 16 0A8 8 0 0 1 0 8m8-7a7 7 0 0 0-5.468 11.37C3.242 11.226 4.805 10 8 10s4.757 1.225 5.468 2.37A7 7 0 0 0 8 1"/>
				</svg>
		        <div class="mb-3">
				 	<label for="exampleFormControlInput1" class="form-label">Username</label>
				  	<input name="name" type="text" class="form-control" id="exampleFormControlInput1" value="${user.name}">
				</div>

				<div class="mb-3">
				 	<label for="exampleFormControlInput2" class="form-label">Email</label>
				  	<input disabled="disabled" type="email" class="form-control" id="exampleFormControlInput2" value="${user.email}">
				</div>
				
				<div class="mb-3">
				 	<label for="exampleFormControlInput3" class="form-label">Password</label>
				  	<input name="password" type="password" class="form-control" id="exampleFormControlInput3" value="${user.password}">
				</div>
				
				<input type="hidden" name="id" value="${user.id}"/>
    			<button class="btn btn-primary" type="submit">Save changes</button>
	      	</form>
	      	
	      	<div style="display: flex; gap: 10px; align-items: center; flex-direction: column; margin-top: 20px">
	      		<form action="logout" method="post">
		        	<button class="btn btn-danger" type="submit">Logout</button>
		        </form>
		        
		        <form action="dropUser" method="post">
		        	<button class="btn btn-danger" type="submit" name="id" value="${user.id}">Delete account</button>
		        </form>		        	
		     </div>
		     </div>
		    </div>
		</c:forEach>
	</main>
<script src="./assets/dist/js/bootstrap.bundle.min.js"></script>

    </body>
</html>
