<%@page import="com.ga.persistence.entity.Permission"%>
<%@page import="com.ga.persistence.entity.RolePermission"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

<%-- <%
 	ArrayList<Permission> permissions = (ArrayList<Permission>) session.getAttribute("Permissions"); 
%>

<%=permissions.size() %> --%>

<%-- 
<%
	for(int i=0;i<permissions.size();i++){
		Permission rp = permissions.get(i); 
%>	
	
	<div>
	
		<% if(rp.getAction().equalsIgnoreCase("CreateTask") || rp.getAction().equalsIgnoreCase("DisplayTask")){ %>
			<h5> <a href="<%=request.getContextPath() %>/UserController?action=<%=rp.getAction() %>"><%=rp.getPermissionName() %></a> </h5>	
		<%	}else{ %>
		<h5> <a href="<%=request.getContextPath() %>/AdminController?action=<%=rp.getAction() %>"><%=rp.getPermissionName() %></a> </h5>
		<%} %>
	</div>
<%} %> --%>

 <a href="<%=request.getContextPath() %>/TaskController?action=displayTask">Display Task</a>

</body>
</html>