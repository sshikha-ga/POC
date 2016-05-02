<%@page import="com.ga.domain.modal.TaskDto"%>
<%@page import="com.ga.persistence.entity.Task"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	
	<h2>Task</h2>
	<a href="<%=request.getContextPath() %>/TaskController?action=addTask">Add Task</a><br><br>
	<%
		int user_id = (Integer)session.getAttribute("User");
		ArrayList<TaskDto> taskList = (ArrayList<TaskDto>)request.getAttribute("TaskList");
		
	%>
	
	<%
		    for(int i=0;i<taskList.size();i++){
		        TaskDto task = taskList.get(i);
	%>	    
	<a href="<%=request.getContextPath()%>/TaskController?action=getTaskDetails&Task_Id=<%=task.getTaskId() %>"><%=task.getTitle() %></a>
	
	<%if(user_id==1){
	    %>
	    <%=task.getHours() %> hours <%=task.getMinutes() %> minutes
	    
	<%} %>
	<br>
	<%} %>

	<%-- <input type="button" value="cancel" id="cancel" onclick="document.location.href='<%=request.getContextPath() %>/TaskController?action=displayMenu'">
 --%>	
</body>
</html>