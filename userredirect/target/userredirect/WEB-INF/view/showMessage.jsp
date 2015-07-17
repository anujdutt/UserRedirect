<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>MESSAGE</title>
</head>
<body>
	<p>${username}</p>
	<c:choose>
		<c:when test="${roles=='No roles found.'}">
		${roles} 
		</c:when>
		<c:otherwise>
			<c:forEach var="role" items="${roles}">
		 ${role.userRole}<br>
			</c:forEach>
		</c:otherwise>
	</c:choose>
</body>
</html>