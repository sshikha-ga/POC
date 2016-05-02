<html>
<head>
 <!-- <script>
        history.forward();
    </script> -->
</head>

<body>

<h2>Login</h2>
<form action="<%=request.getContextPath()%>/LoginController?action=login" method="post">
	
	<label>User name : </label>
	<input type="text" name="username"> <br>
	
	<label>Password : </label>
	<input type="text" name="password">  <br>
	
	<input type="submit" value="login">
		
</form>

</body>
</html>
