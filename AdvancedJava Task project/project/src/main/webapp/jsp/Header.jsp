<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

	<% if(session.getAttribute("UserName")==null){
			response.sendRedirect("index.jsp");
	} %>

	<div>
		Hello
		<%=session.getAttribute("UserName")%></div>

	<form action="<%=request.getContextPath()%>/LoginController?action=logout" method="post">
		<input type="submit" value="logout">
	</form>

</body>
</html>