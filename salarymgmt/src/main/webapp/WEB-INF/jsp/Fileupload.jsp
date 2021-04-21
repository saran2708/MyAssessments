<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Upload Employee details</title>
<style>
th,td{
padding:10px;
}
#submit{
padding :5px;
}
</style>
</head>
<body>
<form:form method="post" action="upload" enctype="multipart/form-data">

 <table>
 <tr>
 	<th colspan = "2"> Upload Employee Details 
 	<hr>
 	</th>
 	</tr>
        <tr>
            <td>Select a file to upload</td>
            <td><input type="file" name="file" accept=".csv , text/csv"/></td>
        </tr>
        <tr>
          <td> &nbsp; </td>
            <td align="center"><input type="submit" id = "submit" value="Submit" /></td>
        </tr>
    </table>
</form:form>
</body>
</html>