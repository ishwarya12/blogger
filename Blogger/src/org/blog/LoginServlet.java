package org.blog;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  public ConnectionManager conn = new ConnectionManager();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
			String email = request.getParameter("email");
			String password = request.getParameter("password");
			String defaultpage = "/WEB-INF/views/login.jsp";
			
			if (email.equals("") || password.equals("")) {
				
				if( request.getParameter("signup") != null) {
					defaultpage = "/WEB-INF/views/register.jsp";
				}
				else {
					request.setAttribute("Validation", "One or more fields are empty");	
					defaultpage = "/WEB-INF/views/login.jsp";
				}	
				request.getRequestDispatcher(defaultpage).forward(request, response);	
				return;
			}
	
			
			if(!email.equals("") && !password.equals("") && request.getParameter("submit") != null) {
				
				int uid = conn.validateUser(email,password);
				System.out.println("in login page"+uid);
				if (uid != 0) {
					//request.setAttribute("ID", uid);
					//defaultpage = "/WEB-INF/views/home.jsp";
					HttpSession sess = request.getSession();
					sess.setAttribute("id", uid);
					
					response.sendRedirect("/blog");
					return;
				}
				else {
					request.setAttribute("Validation", "Unable to Login.. Please try again.");	
					defaultpage = "/WEB-INF/views/login.jsp";
				}
				request.getRequestDispatcher(defaultpage).forward(request, response);			
				return;
			}
			
			if(request.getParameter("cancel") != null) {
				defaultpage = "/WEB-INF/views/login.jsp";
				request.getRequestDispatcher(defaultpage).forward(request, response);			
				return;
			}
			
	}

}
