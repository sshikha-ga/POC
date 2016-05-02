<%@page import="com.ga.persistance.entity.WorkLog"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.ga.persistance.entity.Task"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
  <link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
  <script src="//code.jquery.com/jquery-1.10.2.js"></script>
  <script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
</head>
<body>

<%
	Task task = (Task)request.getAttribute("Task");
	ArrayList<WorkLog> WorkLogList = (ArrayList<WorkLog>)request.getAttribute("WorkLogList");
	int user_id = (Integer)session.getAttribute("User");
%>
<jsp:include page="Header.jsp"></jsp:include>

<br>
<div>
<input type="hidden" value="<%=task.getId() %>">
<label>Task Title : </label> <%=task.getTitle() %><br>
<label>Description : </label> <%=task.getDesc() %><br>
<label>Start date : </label> <%=task.getStartDate() %><br>
<label>End date : </label> <%=task.getEndDate() %><br>
</div>

<br>
		<%
			if(user_id!=1){
		%>
			<a href="<%=request.getContextPath() %>/UserController?action=WorkLog&task_id=<%=task.getId() %>">Add worklog</a>
		<%} %>

	<br>
	<label><u>Work log</u></label><br>
<%
	for(int i=0;i<WorkLogList.size();i++){
	WorkLog log = WorkLogList.get(i);
%>
		<%=log.getUserName() %> : <%=log.getTotalHour() %> h <%=log.getTotalMinute() %> m<br>
<%} %>
</body>
</html>