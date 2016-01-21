package org.blog;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RegistrationServlet
 */
@WebServlet("/register")
public class RegistrationServlet extends HttpServlet {
	
	public ConnectionManager conn = new ConnectionManager();
	
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.getRequestDispatcher("/WEB-INF/views/register.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String defaultpage = "/WEB-INF/views/register.jsp";
		
		String fname = request.getParameter("fname");
		String lname = request.getParameter("lname");
		
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String cpassword = request.getParameter("password1");
		
		
		if (fname.equals("") || lname.equals("") || email.equals("") || password.equals("") || cpassword.equals("")) {
			request.setAttribute("Validation", "One or more fields are empty");
			defaultpage = "/WEB-INF/views/register.jsp";
			
		} else if(!password.equals(cpassword)) {
			request.setAttribute("Validation", "Password Mismatch");
			defaultpage = "/WEB-INF/views/register.jsp";

		}else {
			conn.loadUserInfo(fname, lname, email, password);
			defaultpage = "/WEB-INF/views/login.jsp";
		}
		
		request.getRequestDispatcher(defaultpage).forward(request, response);
	}
	

}
