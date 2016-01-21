<%@page import="org.blog.ConnectionManager"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="blog.model.Post" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Blogger.com</title>
<h1>Welcome to Blogger.com</h1>
</head>

<body style="background-color:pink;font-family:courier">

<B><font size="150%"></font>Your Posts</B>
<form action="/blog" method="post">

<input type="hidden" name="id" value=${id}>

</form>

<form action="/blog" method="post">
<br>

<input type="hidden" name="posts" value=${posts}> 

 <%	List<Post> a = (ArrayList<Post>) request.getAttribute("posts");
	for(Post blog : a) {    
	out.println("<BR>");
    out.println(blog.getPost());
    out.println("<BR>");
} %>


<div>
<br>
<br>

<textarea placeholder="Hey say something here..." name="upost" id="upost" value ="upost" style="font-family:sans-serif; rows="4" cols="50"">
</textarea>
</div>
<br>

<input type="submit" id="submit" name="submit" value="Add Post" name="submit">
</form>


</body>
</html>