package org.blog;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import blog.model.Post;

/**
 * Servlet implementation class BlogServlet
 */
@WebServlet("/blog")
public class BlogServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public ConnectionManager conn = new ConnectionManager();
	
	public List<Post> postObj = new ArrayList<Post>();
	HttpSession sess;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("BLOG SERVICE");

		sess = request.getSession();
		
		int uid = (Integer) sess.getAttribute("id");
		
		System.out.println("Id in blog service"+uid);
		
		postObj = conn.retrievePosts(uid);
		
		request.setAttribute("posts", postObj);
		request.getRequestDispatcher("/WEB-INF/views/home.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String post = request.getParameter("upost");
		
		sess = request.getSession();
		
		int uid = (Integer) sess.getAttribute("id");
			
		if(!post.equals("") && request.getParameter("submit")!=null) {
			
			conn.loadBlogData(post, uid);
			request.setAttribute("upost", "");
		}
		request.getRequestDispatcher("/WEB-INF/views/success.jsp").forward(request, response);
	}

}
