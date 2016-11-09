<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<script type="text/javascript" src="http://cdnjs.cloudflare.com/ajax/libs/jquery/2.0.3/jquery.min.js"></script>
		<script type="text/javascript" src="http://netdna.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
		<link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css">
		<title>Logowanie</title>
	</head>
	<body>
		<div class="cover">
			<div class="navbar navbar-default">
				<div class="container">
					<div class="navbar-header">
						<button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#navbar-ex-collapse">
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
							<li class="active"><a href="/reg/login">Zaloguj</a></li>
						</ul>
					</div>
				</div>
			</div>
			<div class="section">
				<div class="container">
					<div class="row">
						<div class="col-md-8 col-md-offset-2">
							<h1 class="text-center text-muted">Logowanie</h1>
							<c:if test="${errors != null}">
								<div class="alert alert-danger">
									${errors}
								</div>
							</c:if>
							<c:if test="${logout != null}">
								<div class="alert alert-success">
									${logout}
								</div>
							</c:if>
							<form action="<c:url value="/login"></c:url>" method="post">
								<div class="form-group">
									<input class="form-control" placeholder="Email" name='email' type="text">
								</div>
								<div class="form-group">
									<input class="form-control" placeholder="Haslo" name='password' type="password" value="">
								</div>
								<input class="btn btn-primary" type="submit" value="Zaloguj">
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</body>
</html>