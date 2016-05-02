<%@page import="com.ga.persistance.entity.Task"%>
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
<jsp:include page="Header.jsp"></jsp:include>
<a href="<%=request.getContextPath()%>/UserController?action=CreateTask">Add Task</a>

<h3>Tasks</h3>
<%
		ArrayList<Task> taskList = (ArrayList<Task>)request.getAttribute("TaskList");
		int user_id = (Integer)session.getAttribute("User");
%>

<%
		for(int i=0;i<taskList.size();i++){
		Task task = taskList.get(i);
%>			
		<a href="<%=request.getContextPath() %>/UserController?action=getTaskDetails&Task_Id=<%=task.getId() %>"><%=task.getTitle() %></a>
		
		<%
			if(user_id==1){
		%>
			<%=task.getHours() %> h <%=task.getMinutes() %>
		<%} %>
		<br>	
<%	} %>



</body>
</html>