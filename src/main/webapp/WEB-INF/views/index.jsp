<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<script type="text/javascript" src="http://cdnjs.cloudflare.com/ajax/libs/jquery/2.0.3/jquery.min.js"></script>
		<script type="text/javascript" src="http://netdna.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
		<link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css">
		<title>Strona glowna</title>
	</head>
	<body>
		<div class="cover">
			<div class="navbar navbar-default">
				<div class="container">
					<div class="navbar-header">
						<button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#navbar-ex-collapse">
							<span class="sr-only">Toggle navigation</span> 
							<span class="icon-bar"></span> <span class="icon-bar"></span> 
							<span class="icon-bar"></span>
						</button>
					</div>
					<div class="collapse navbar-collapse" id="navbar-ex-collapse">
						<ul class="nav navbar-nav navbar-left">
							<li class="active"><a href="/reg/">Strona glowna</a></li>
							<li><a href="/reg/contacts">Twoje kontakty</a></li>
							<li><a href="/reg/registration">Rejestracja<br></a></li>
							<security:authorize var="isAuthenticatedByUser" access="hasAuthority('USER') and isAuthenticated()" />
							<security:authorize var="isAuthenticatedByAdmin" access="hasAuthority('ADMIN') and isAuthenticated()" />
							<security:authentication var="principal" property="principal" />
							<c:choose>
								<c:when test="${isAuthenticatedByUser}">
									<li><a href="/reg/login?logout">Wyloguj (<i>${principal.username}</i>)
									</a></li>
								</c:when>
								<c:when test="${isAuthenticatedByAdmin}">
									<li><a href="/reg/admin">Admin</a></li>
									<li><a href="/reg/login?logout">Wyloguj (<i>${principal.username}</i>)
									</a></li>
								</c:when>
								<c:otherwise>
									<li><a href="/reg/login">Zaloguj</a></li>
								</c:otherwise>
							</c:choose>
						</ul>
					</div>
				</div>
			</div>
			<div class="container">
				<div class="row">
					<div class="col-md-12 text-center">
						<c:if test="${message != null}">
							<div class="alert alert-success">${message}</div>
						</c:if>
						<a href="/reg/contacts">
						<img src="<c:url value="/resources/jsp/images/contacts_256.png" />"></a>
					</div>
				</div>
			</div>
			<div class="container">
				<div class="row">
					<div class="col-md-12 text-center">
						<h1 class="text-center text-muted">Twoje kontakty</h1>
						<p>Przegladaj i dodawaj nowe kontakty</p>
						<a class="btn btn-primary" href="/reg/contacts">Zobacz kontakty<br></a>
						<br><br>
					</div>
				</div>
			</div>
		</div>
	</body>
</html>