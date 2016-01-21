<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Blogger.com</title>
<h1>Welcome to Blogger.com</h1>
</head>

<body style="background-color:pink;font-family:courier">

<B><font size="150%"></font>Registration Page</B>

<br>

<B><font-color="red">${Validation}</font></B>


<form action="/register" method="post">

<BR><BR> <label for="fname"> First Name </label> 	<input type="text" id="fname" name="fname"/>
<BR><BR> <label for="lname"> Last Name </label> 	<input type="text" id="lname" name="lname"/>

<BR><BR> <label for="email"> Email </label> 	<input type="email" id="email" name="email"/>
<BR><BR> <label for="password"> Password </label> <input type="password" id="password" name="password"/>
<BR><BR> <label for="password1"> Confirm Password</label> <input type="password" id="password1 "name="password1"/>

<BR><BR> 		<input type="submit" value="Sign Up"/>
<input type="button" value="Cancel"/>

</form>

</body>
</html>