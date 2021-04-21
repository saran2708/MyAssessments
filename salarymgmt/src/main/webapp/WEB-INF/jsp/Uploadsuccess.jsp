<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Successful Upload</title>
<style>  
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
</head>
<body>
<div align="center">
<h4>
Uploaded Employee Details
 </h4>
 </div>
 <hr>
 <table id="empdetails" align="center">
 <tr>
  <th> S.No</th>
 <th> ID</th>
 <th>Login </th>
 <th>Name</th>
 <th>Salary</th>
 <tr>
 	<c:forEach items="${employeeList}"  var="employee" varStatus="itr">
			  <tr>
			  		 <td>${itr.index+1}</td>
                    <td>${employee.id}</td>
                    <td>${employee.login}</td>
                    <td>${employee.name}</td>
                     <td>${employee.salary}</td>
                    </tr>
     </c:forEach>
    </table>
</body>
</html>