<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

	<form:form action="login" modelAttribute="user" method="post">

		User Name : <form:input path="userName" /><br>
		Password : <form:input path="password" /><br>
		
		${msg}<br>
		
		<input type="submit" value="login"><br>

	</form:form>

</body>
</html>