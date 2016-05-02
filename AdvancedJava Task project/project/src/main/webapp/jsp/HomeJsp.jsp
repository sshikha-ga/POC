<%@page import="com.ga.persistance.entity.Role_Permission"%>
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

<%@include file="Header.jsp" %>
<%
 	ArrayList<Role_Permission> rpList = (ArrayList<Role_Permission>) session.getAttribute("Permissions"); 
%>

<%
	for(int i=0;i<rpList.size();i++){
		Role_Permission rp = rpList.get(i); 
%>	
	
	<div>
	
		<% if(rp.getAction().equalsIgnoreCase("CreateTask") || rp.getAction().equalsIgnoreCase("DisplayTask")){ %>
			<h5> <a href="<%=request.getContextPath() %>/UserController?action=<%=rp.getAction() %>"><%=rp.getPermission_Name() %></a> </h5>	
		<%	}else{ %>
		<h5> <a href="<%=request.getContextPath() %>/AdminController?action=<%=rp.getAction() %>"><%=rp.getPermission_Name() %></a> </h5>
		<%} %>
	</div>
<%} %>
</body>
</html>