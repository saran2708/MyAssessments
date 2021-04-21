<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>View Records</title>
<style>  
div {
    display: flex;
    padding: 5px;
    justify-content: space-between;
}
table{
border-spacing: 2px;
width:50%;
}
th{
align:center;
}
table, th, td {  
    border: black;  
    border-collapse: collapse;  
}  
th, td {  
    padding: 10px;  
}  
table#empdetails tr:nth-child(even) {  
    background-color: #eee;  
}  
table#empdetails tr:nth-child(odd) {  
    background-color: #fff;  
}  
table#empdetails th {  
    color: white;  
    background-color: gray;  
}  
</style>  
<script type="text/javascript">
function getNextRecords(){
	var page = parseInt(document.getElementById("page").value);
	page++;
	var offset = parseInt(document.getElementById("offset").value);
	offset=offset+2;
	document.getElementById("page").value=page;
	document.getElementById("offset").value=offset
}
function getPreviousRecords(){
	var page = parseInt(document.getElementById("page").value);
	page--;
	var offset = parseInt(document.getElementById("offset").value);
	offset=offset-2;
	document.getElementById("page").value=page;
	document.getElementById("offset").value=offset
}
</script>
</head>
<body>
<form:form action="searchAndSort" method="post" modelAttribute="searchEmployees">
<div align="center">
<form:label path="minSalary">Minimum Salary : </form:label><form:input path="minSalary" />
Maximum Salary :  <form:input path="maxSalary" />
Sort Order by :  <form:select path="sortingElement" >
<form:option value="id">ID</form:option>
<form:option value="login">Login</form:option>
<form:option value="name">Name</form:option>
<form:option value="salary">Salary</form:option>
</form:select>
Sorting Order :  <form:select path="sortOrder" >
<form:option value="Ascending"/>
<form:option value="Descending"/>
</form:select>
 </div>
 <div id="searchDiv" >
 <input type="submit" id="searchBtn" value="Search"/>
 </div>
 <hr>
 <table id="empdetails" align="center">
 <tr>
  <th> S.No</th>
 <th> ID</th>
 <th>Login </th>
 <th>Name</th>
 <th>Salary</th>
 <th>&nbsp;</th>
 </tr>
 <c:if test="${employeeList.size() > 0}">
 	<c:forEach items="${employeeList}"  var="employee" varStatus="itr">
			  <tr>
			  		 <td>${itr.index+1}</td>
                    <td><a href="updateEmployee/${employee.id}">${employee.id}</a></td>
                    <td>${employee.login}</td>
                    <td>${employee.name}</td>
                     <td>${employee.salary}</td>
                     <td><a href="deleteEmployee/${employee.id}">Delete </a></td>
                 </tr>
     </c:forEach>
</c:if>
 <c:if test="${employeeList.size()<=0}">
 <tr>
 <td colspan="6" align="center">
 	No records to display!!!
 </td>
 </tr>
 </c:if>
    </table>
    <div>
     <c:if test="${searchEmployees.page > 1}">
 	 <input type="submit" id="prevBtn" value="Previous" onclick="getPreviousRecords()"/>
 	 </c:if>
 	  <c:if test="${searchEmployees.page <= 1}">
 	 <input type="submit" id="prevBtn" value="Previous" readonly="readonly" disabled="disabled"/>
 	 </c:if>
 		${searchEmployees.page}
 	<c:if test="${searchEmployees.page >= 1 && employeeList.size()>0}">
 	  <input type="submit" id="nxtBtn" value="Next" onclick="getNextRecords()"/>
 	  </c:if>
 	  	<c:if test="${searchEmployees.page >= 1 && employeeList.size()<=0}">
 	  	  <input type="submit" id="nxtBtn" value="Next" disabled="disabled"/>
 	  	</c:if>
    </div>
    <form:hidden path="page" id="page"/>
     <form:hidden path="offset" id="offset"/>
 </form:form>
</body>
</html>
