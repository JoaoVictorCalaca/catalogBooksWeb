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
		<c:if test="${owner}">
			<p>teset</p>
		</c:if>

		<c:forEach items="${books}" var="book" varStatus="id">
			<div class="row featurette" style="margin-top: 20px">
		      <div class="col-md-7 order-md-2">
		        <h1 class="featurette-heading fw-normal lh-1"><strong>Title:</strong> ${book.name}</h1>
		        <p class="lead"><strong>User's commentary:</strong> ${book.description}</p>
		        
		        <div style="display: flex; gap: 10px">
		        	<form action="editBook" method="get">
		        		<input type="hidden" name="id" value="${book.id}" />
    					<button class="btn btn-primary" type="submit">Edit</button>
		        	</form>
		        	<form action="dropBook" method="post">
		        		<button class="btn btn-danger" type="submit" name="id" value="${book.id}">Delete</button>
		        	</form>		        	
		        </div>
		      </div>
		      
		      <div class="col-md-3 order-md-1">
		       	<img width="100%" style="border-radius: 8px" alt="Book cover" src="${book.cover}">
		      </div>
		    </div>
		</c:forEach>
	</main>
<script src="./assets/dist/js/bootstrap.bundle.min.js"></script>

    </body>
</html>
