<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<% int task_id = (Integer) request.getAttribute("task_id"); %>
<div>
	<form action="<%=request.getContextPath() %>/UserController?action=addWorkLog" method="post">
		<label>Start Time : </label> <input type="text" name="hour">hour<input type="text" name="minute">minutes<br>
		<label>Time spent : </label> <input type="text" name="totalHour">hour<input type="text" name="totalMinute">minutes<br>
		<input type="hidden" value="<%=task_id %>" name="taskId">
		<input type="submit" value="save">
	</form>
</div>
	
</body>
</html>