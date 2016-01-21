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

<B><font size="150%"></font>Sign In Here...</B>

<form method="post" action="/login">
${Validation}


<br><br> <label for="email"> Email Id </label> 	<input type="email" id="email" name="email"/>

<br><br> <label for="password"> Password </label> <input type="password" id="password" name="password"/>

<br><br> <input type="submit" value="Sign In" name="submit"/> <input type="button" value="Cancel"/>

<br>
<br>

New User? Sign Up Here  <input type="submit" value="Sign Up" name="signup"/>

</form>
</body>
</html>