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
		<script src="http://localhost:8080/reg/resources/jsp/scripts/contacts.js"></script>
		<link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css">
		<title>Kontakty</title>
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
								<li class="active"><a href="/reg/contacts">Twoje kontakty</a></li>
								<li><a href="/reg/registration">Rejestracja<br></a></li>
								<security:authorize var="isAuthenticatedByUser" access="hasAuthority('USER') and isAuthenticated()" />
								<security:authorize var="isAuthenticatedByAdmin" access="hasAuthority('ADMIN') and isAuthenticated()" />
								<security:authentication var="principal" property="principal" />
								<c:choose>
									<c:when test="${isAuthenticatedByUser}">
										<li><a href="/reg/login?logout">Wyloguj (<i>${principal.username}</i>)</a></li>
									</c:when>
									<c:when test="${isAuthenticatedByAdmin}">
										<li><a href="/reg/admin">Admin</a></li>
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
							<h1 class="text-center text-muted">Kontakty</h1>
						</div>
						<div class="col-md-12">
							<button class="btn btn-primary" type="button" data-toggle="modal" data-target="#addContactModal">Dodaj kontakt</button>
							<input type="text" name="search" placeholder="Znajdz kontakt" ng-model="search">
						</div>
					</div>
				</div>
				<div class="container">
					<div class="row">
						<div class="col-md-12">
							<c:if test="${(isAuthenticatedByUser) || (isAuthenticatedByAdmin)}">
								<div ng-init="getContacts('${userId}')"></div>
							</c:if>
							<table class="table table-hover">
								<thead align="center">
									<tr>
										<th></th>
										<th>Imie</th>
										<th>Nazwisko</th>
										<th>Telefon</th>
										<th>Email</th>
										<th>Typ</th>
										<th>Opcje</th>
									</tr>
								</thead>
								<tbody>
									<tr ng-repeat="contact in contacts | filter: search">
										<td><img check-image ng-src="http://localhost:8080/reg/resources/images/contact_{{contact.ownerUserId}}_{{contact.id}}.png" alt="image" width="50" height="50" /></td>
										<td>{{contact.firstName}}</td>
										<td>{{contact.lastName}}</td>
										<td>{{contact.phoneNumber}}</td>
										<td>{{contact.email}}</td>
										<td>{{contact.contactType}}</td>
										<td>
											<button ng-click="deleteContact(contact.id, '${userId}')" class="btn btn-primary btn-sm">Usun</button>
											<button type="button" data-toggle="modal" data-target="#updateContactModal" ng-click="createContactData(contact.id, '${userId}', contact.firstName, contact.lastName, contact.phoneNumber, contact.email,  contact.contactType)" class="btn btn-primary btn-sm">Edytuj</button>
										</td>
									</tr>
								</tbody>
							</table>
						</div>
					</div>
				</div>
				<div class="container">
					<!-- Modal -->
					<div class="modal fade" id="updateContactModal" role="dialog">
						<div class="modal-dialog">	
							<div class="modal-content">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal">&times;</button>
									<h4 class="modal-title">Edytuj kontakt</h4>
								</div>
								<div class="modal-body">
									<form ng-submit="updateContact(contact.id, '${userId}', contact.firstName, contact.lastName, contact.phoneNumber, contact.email, contact.contactType, file.base64)">
										<div class="form-group">
											<div class="form-group">
												<label>Imie:</label> 
												<input class="form-control" type="text" name="firstName" ng-model="contact.firstName">
											</div>
											<div class="form-group">
												<label>Nazwisko:</label> 
												<input class="form-control" type="text" name="lastName" ng-model="contact.lastName">
											</div>
											<div class="form-group">
												<label>Numer telefonu:</label> 
												<input class="form-control" type="number" name="phoneNumber" ng-model="contact.phoneNumber">
											</div>
											<div class="form-group">
												<label>Email:</label> 
												<input class="form-control" type="email" name="email" ng-model="contact.email">
											</div>
											<div class="form-group">
												<label>Typ:</label> 
												<select ng-init="getContactTypes()" ng-model="contact.contactType">
													<option ng-repeat="contactTypeItem in contactTypes" selected="selected">{{contactTypeItem}}</option>
												</select>
											</div>
											<div class="form-group">
												<label>Obrazek:</label> 
												<input type="file" ng-model="file" name="file" base-sixty-four-input maxsize="500" accept="image/*">
											</div>
											<input class="btn btn-primary btn-sm" type="submit" name="submit" value="Aktualizuj">
										</div>
									</form>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="container">
					<!-- Modal -->
					<div class="modal fade" id="addContactModal" role="dialog">
						<div class="modal-dialog">	
							<div class="modal-content">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal">&times;</button>
									<h4 class="modal-title">Dodaj kontakt</h4>
								</div>
								<div class="modal-body">
									<form name="addContactForm" ng-submit="addContact('${userId}', formfirstName, formLastName, formPhoneNumber, formEmail, formContactType, file.base64)">
										<div class="form-group">
											<div class="form-group">
												<label>Imie:</label> 
												<input class="form-control" type="text" name="formFirstName" ng-model="formfirstName">
											</div>
											<div class="form-group">
												<label>Nazwisko:</label> 
												<input class="form-control" type="text" name="formLastName" ng-model="formLastName">
											</div>
											<div class="form-group">
												<label>Numer telefonu:</label> 
												<input class="form-control" type="number" name="formPhoneNumber" ng-model="formPhoneNumber" min="000000" max="999999999">
												<div role="alert">
													<span class="error" ng-show="addContactForm.input.$error.number"> Niepoprawny numer telefonu.</span>
												</div>
											</div>
											<div class="form-group">
												<label>Email:</label> 
												<input class="form-control" type="email" name="formEmail" ng-model="formEmail">
												<div role="alert">
													<span class="error" ng-show="addContactForm.input.$error.email"> Niepoprawny email. </span>
												</div>
											</div>
											<div class="form-group">
												<label>Typ:</label> 
												<select ng-init="getContactTypes()" ng-model="formContactType">
													<option ng-repeat="contactTypeItem in contactTypes" selected="selected">{{contactTypeItem}}</option>
												</select>
											</div>
											<div class="form-group">
												<label>Obrazek:</label> 
												<input type="file" ng-model="file" name="file" base-sixty-four-input maxsize="500" accept="image/*">
											</div>
											<input class="btn btn-primary btn-sm" type="submit" name="submit" value="Dodaj">
										</div>
									</form>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</section>
	</body>
</html>