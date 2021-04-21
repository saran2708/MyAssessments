<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Employee Details</title>
</head>
<body>
<form:form action="/salarymgmt/saveEmployee" method="post" modelAttribute="employee">
<table>
<tr>
	<td>Employee id : </td>
	<td><form:input  path="id" readonly="true"/></td>
</tr>
<tr>
	<td>Login </td>
	<td><form:input path="login" /></td>
</tr>
<tr>
	<td>Name</td>
	<td><form:input path="name" /></td>
</tr>
<tr>
	<td>Salary </td>
	<td><form:input path="Salary"/></td>
</tr>
</table>
<input type="submit" value="Update"/>
</form:form>
</body>
</html>