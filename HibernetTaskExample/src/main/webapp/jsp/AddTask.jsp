<%@page import="com.ga.persistence.entity.User"%>
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

	<%
	    ArrayList<User> userList = (ArrayList<User>)request.getAttribute("UserList");
	%>

	<h3>Add Task</h3>

	<form
		action="<%=request.getContextPath()%>/TaskController?action=saveTask"
		method="post">

		<label>Title : </label> <input type="text" name="title"> <br>

		<label>Description : </label> 
		<input type="text" name="desc"><br> 
		<label>Start Date : </label> 
		<input type="text" name="startDate"> <br>
		<label>End Date : </label> <input type="text" name="endDate"> <br>
		<label>Assign to : </label> <select multiple="multiple" name="AssignedUsers">
			<%
			    for(int i=0;i<userList.size();i++){
					User user = userList.get(i);
			%>
			<option value="<%=user.getUserId()%>"><%=user.getUserName()%></option>
			<%
			    }
			%>
		</select> <br> <input type="submit" value="add"> <input
			type="button" value="cancel" id="cancel"
			onclick="document.location.href='<%=request.getContextPath()%>/TaskController?action=displayTask'">
	</form>

</body>
</html>