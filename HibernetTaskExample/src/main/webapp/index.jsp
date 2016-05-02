<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	
<%-- <h4><a href="<%=request.getContextPath() %>/TaskController?action=displayTask">Display Tasks</a></h4>
<h4><a href="<%=request.getContextPath() %>/TaskController?action=addTask">Add Tasks</a></h4>
 --%>
 
 <h2>Login</h2>
<form action="<%=request.getContextPath()%>/LoginController?action=login" method="post">
	
	<label>User name : </label>
	<input type="text" name="username"> <br>
	
	<label>Password : </label>
	<input type="text" name="password">  <br>
	
	<input type="submit" value="login">
		
</form>
 
 	
</body>
</html>