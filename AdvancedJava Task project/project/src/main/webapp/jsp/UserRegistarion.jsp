<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	
	<%@include file="Header.jsp" %>
	
	<h3>User Registration</h3>
	
	<form action="<%=request.getContextPath() %>/UserController?action=register" method="post">
	
	<label>User Name : </label>
	<input type="text" name="userName"><br>
	
	<label>Password : </label>
	<input type="text" name="password"><br>
	
	<label>Email : </label>
	<input type="text" name="email"><br>
	
	<label>Role : </label>
	<select name="role">
		<option value="1">Admin</option>
		<option value="2">User</option>
	</select> <br>
	
	<input type="submit" value="Register">
	
	</form>
	
</body>
</html>