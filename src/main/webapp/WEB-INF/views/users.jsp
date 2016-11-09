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
		<script src="http://ajax.googleapis.com/ajax/libs/angularjs/1.4.8/angular.min.js"></script>
		<script type="text/javascript" src="http://cdnjs.cloudflare.com/ajax/libs/jquery/2.0.3/jquery.min.js"></script>
		<script type="text/javascript" src="http://netdna.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
		<script type="text/javascript" src="https://cdn.rawgit.com/adonespitogo/angular-base64-upload/master/src/angular-base64-upload.js"></script>
		<script src="http://localhost:8080/reg/resources/jsp/scripts/users.js"></script>
		<link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css">
		<title>Uzytkownicy</title>
		<style type="text/css">
		.text-center {
			text-align: center;
		}
		</style>
	</head>
	<body ng-app="contactsApp">
		<section ng-controller="contactsCtrl">
			<div class="cover">
				<div class="navbar navbar-default">
					<div class="container">
						<div class="navbar-header">
							<button type="button" class="navbar-toggle" data-toggle="collapse"
								data-target="#navbar-ex-collapse">
								<span class="sr-only">Toggle navigation</span> 
								<span class="icon-bar"></span> 
								<span class="icon-bar"></span> 
								<span class="icon-bar"></span>
							</button>
						</div>
						<div class="collapse navbar-collapse" id="navbar-ex-collapse">
							<ul class="nav navbar-nav navbar-left">
								<li><a href="/reg/">Strona glowna</a></li>
								<li><a href="/reg/contacts">Twoje kontakty</a></li>
								<li><a href="/reg/registration">Rejestracja<br></a></li>
								<security:authorize var="isAuthenticatedByUser" access="hasAuthority('USER') and isAuthenticated()" />
								<security:authorize var="isAuthenticatedByAdmin" access="hasAuthority('ADMIN') and isAuthenticated()" />
								<security:authentication var="principal" property="principal" />
								<c:choose>
									<c:when test="${isAuthenticatedByUser}">
										<li><a href="/reg/login?logout">Wyloguj (<i>${principal.username}</i>)</a></li>
									</c:when>
									<c:when test="${isAuthenticatedByAdmin}">
										<li class="active"><a href="/reg/admin">Admin</a></li>
										<li><a href="/reg/login?logout">Wyloguj (<i>${principal.username}</i>)</a></li>
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
							<h1 class="text-center text-muted">Uzytkownicy</h1>
						</div>
						<div class="col-md-12">
							<input type="text" name="search" placeholder="Znajdz uzytkownika" ng-model="search">
						</div>
					</div>
				</div>
				<div class="container">
					<div class="row">
						<div class="col-md-12" ng-init="getUsers()">
							<table class="table table-hover">
								<thead align="center">
									<tr>
										<th></th>
										<th>Imie</th>
										<th>Nazwisko</th>
										<th>Email</th>
										<th>Typ</th>
										<th>Opcje</th>
									</tr>
								</thead>
								<tbody>
									<tr ng-repeat="user in users | filter: search">
										<td><img check-image ng-src="http://localhost:8080/reg/resources/images/default.png" alt="image" width="50" height="50" /></td>
										<td>{{user.firstName}}</td>
										<td>{{user.lastName}}</td>
										<td>{{user.email}}</td>
										<td>{{user.role}}</td>
										<td>
											<button ng-if="user.role != 'ADMIN'" ng-click="deleteUser(user.id)" class="btn btn-primary btn-sm">Usun</button>
										</td>
									</tr>
								</tbody>
							</table>
						</div>
					</div>
				</div>
			</div>
		</section>
	</body>
</html>