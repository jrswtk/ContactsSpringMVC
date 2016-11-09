<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
	    <meta http-equiv="Content-Type" content="text/html" ; charset="UTF-8">
	    <meta name="viewport" content="width=device-width, initial-scale=1">
	    <script type="text/javascript" src="http://cdnjs.cloudflare.com/ajax/libs/jquery/2.0.3/jquery.min.js"></script>
	    <script type="text/javascript" src="http://netdna.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
	    <link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css">
	    <title>Rejestracja</title>
	</head>
	<body>
	    <div class="cover">
	        <div class="navbar navbar-default">
	            <div class="container">
	                <div class="navbar-header">
	                    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#navbar-ex-collapse">
	                        <span class="sr-only">Toggle navigation</span> <span class="icon-bar"></span> <span class="icon-bar"></span> <span class="icon-bar"></span>
	                    </button>
	                </div>
	                <div class="collapse navbar-collapse" id="navbar-ex-collapse">
	                    <ul class="nav navbar-nav navbar-left">
	                        <li><a href="/reg/">Strona glowna</a>
	                        </li>
	                        <li><a href="/reg/contacts">Twoje kontakty</a>
	                        </li>
	                        <li class="active"><a href="/reg/registration">Rejestracja<br></a>
	                        </li>
	                        <security:authorize var="isAuthenticatedByUser" access="hasAuthority('USER') and isAuthenticated()" />
	                        <security:authorize var="isAuthenticatedByAdmin" access="hasAuthority('ADMIN') and isAuthenticated()" />
	                        <security:authentication var="principal" property="principal" />
	                        <c:choose>
	                            <c:when test="${isAuthenticatedByUser}">
	                                <li><a href="/reg/login?logout">Wyloguj (<i>${principal.username}</i>)
									</a>
	                                </li>
	                            </c:when>
	                            <c:when test="${isAuthenticatedByAdmin}">
	                                <li><a href="/reg/admin">Admin</a>
	                                </li>
	                                <li><a href="/reg/login?logout">Wyloguj (<i>${principal.username}</i>)
									</a>
	                                </li>
	                            </c:when>
	                            <c:otherwise>
	                                <li><a href="/reg/login">Zaloguj</a>
	                                </li>
	                            </c:otherwise>
	                        </c:choose>
	                    </ul>
	                </div>
	            </div>
	        </div>
	        <div class="section">
	            <div class="container">
	                <div class="row">
	                    <div class="col-md-12">
	                        <h1 class="text-center text-muted">Rejestracja</h1>
	                        <form:form action="/reg/registration" modelAttribute="accountForm">
	                            <div>
	                                <form:errors path="*" cssClass="alert alert-danger" element="div" />
	                            </div>
	                            <div class="form-group">
	                                <label class="control-label">Imie:
	                                    <br>
	                                </label>
	                                <form:input class="form-control" type="text" path="firstName" />
	                                <form:errors path="firstName" cssClass="text-danger" />
	                            </div>
	                            <div class="form-group">
	                                <label class="control-label">Nazwisko:</label>
	                                <form:input class="form-control" type="text" path="lastName" />
	                                <form:errors path="lastName" cssClass="text-danger" />
	                            </div>
	                            <div class="form-group">
	                                <label class="control-label">Email:</label>
	                                <form:input class="form-control" type="email" path="email" />
	                                <form:errors path="email" cssClass="text-danger" />
	                            </div>
	                            <div class="form-group">
	                                <label class="control-label">Haslo:</label>
	                                <form:input class="form-control" type="password" path="pass" />
	                                <form:errors path="pass" cssClass="text-danger" />
	                            </div>
	                            <div class="form-group">
	                                <label class="control-label">Powtorz haslo:</label>
	                                <form:input class="form-control" type="password" path="repeatPass" />
	                                <form:errors path="repeatPass" cssClass="text-danger" />
	                            </div>
	                            <div class="form-group">
	                                <input type="submit" class="btn btn-primary" value="Zarejestruj" />
	                            </div>
	                        </form:form>
	                    </div>
	                </div>
	            </div>
	        </div>
	    </div>
	</body>
</html>