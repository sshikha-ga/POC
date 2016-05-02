<%@page import="com.ga.persistence.entity.Worklog"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.ga.persistence.entity.Task"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<% Task task = (Task) request.getAttribute("Task"); 
int user_id = (Integer)session.getAttribute("User");
ArrayList<Worklog> WorkLogList = (ArrayList<Worklog>)request.getAttribute("WorkLogList");
%>


<label>Title : </label> <%=task.getTitle() %><br>
<label>Description : </label> <%=task.getDescription() %><br>
<label>Start Date : </label> <%=task.getStartDate() %><br>
<label>End Date : </label> <%=task.getEndDate() %><br>

<br>
		<%-- <%
			if(user_id!=1){
		%> --%>
			<a href="<%=request.getContextPath() %>/UserController?action=WorkLog&task_id=<%=task.getTaskId() %>">Add worklog</a>
		<%-- <%} %> --%>

	<br>
	<label><u>Work log</u></label><br>
<%
	for(int i=0;i<WorkLogList.size();i++){
	Worklog log = WorkLogList.get(i);
%>
		<%=log.getUserId().getUserName() %> : <%=log.getTotalHours() %> h <%=log.getTotalMinutes() %> m<br>
<%} %>


<input type="button" value="back" onclick="document.location.href='<%=request.getContextPath() %>/TaskController?action=displayTask'">



</body>
</html>