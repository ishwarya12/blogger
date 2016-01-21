package org.blog;


import java.sql.Connection;
import java.sql.Date;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import blog.model.Post;


public class ConnectionManager {

	
	private static final String CHECK_USER_QUERY = "select * from userinfo where email = ? AND pwd = ?";   
	private static final String INSERT_USER_INFO = "insert into userinfo (fname, lname, email, pwd) values(?,?,?,?)";
	private static final String INSERT_BLOG= "insert into userblog (post, date, u_id) values(?,?,?)";
	private static final String RETRIEVE_POST = "select * from userblog where u_id= ?";
	final String url = "jdbc:mysql://localhost:3306/";
	    String dbName = "blog";
	    String driver = "com.mysql.jdbc.Driver";
	    String userName = "dbuser";
	    String password = "password";

	    private static ConnectionManager myObj;   
	    private Connection Con ;
	    ConnectionManager() {
	        System.out.println("Hello");
	        Con= createConnection();
	    }

	    @SuppressWarnings("rawtypes")
	    public Connection createConnection() {
	        Connection connection = null;
	        try {
	            // Load the JDBC driver
	            Class driver_class = Class.forName(driver);
	            Driver driver = (Driver) driver_class.newInstance();
	            DriverManager.registerDriver(driver);
	            connection = DriverManager.getConnection(url + dbName);
	        } catch (ClassNotFoundException e) {
	            e.printStackTrace();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        } catch (IllegalAccessException e) {
	            e.printStackTrace();
	        } catch (InstantiationException e) {
	            e.printStackTrace();
	        }
	        return connection;
	    }

	    /**
	     * Create a static method to get instance.
	     */
	    public static ConnectionManager getInstance() {
	        if (myObj == null) {
	            myObj = new ConnectionManager();
	        }
	        return myObj;
	    }

	    public static void main(String a[]) {
	    	ConnectionManager st = ConnectionManager.getInstance();
	    }
	    
	    public void loadUserInfo(String fname, String lname, String email, String password) {
			// TODO Auto-generated method stub
			
			 try (PreparedStatement ps = Con
	                    .prepareStatement(INSERT_USER_INFO))
	               {
		            ps.setString(1, fname);
		            ps.setString(2, lname);
		            ps.setString(3, email);
		            ps.setString(4, password);
		            ps.executeUpdate();
		            
		        } catch (SQLException e) {
		        	e.printStackTrace();
		        }
		}

		public int validateUser(String email, String password) {
			// TODO Auto-generated method stub
			int result = 0;
			try (PreparedStatement ps = Con.prepareStatement(CHECK_USER_QUERY)) {
	            ps.setString(1, email);
	            ps.setString(2,  password);
	            
	            try (ResultSet rs = ps.executeQuery()) {
	            	if (rs.next()) {
	            		return rs.getInt(1);
	            	}
	            } catch (SQLException e) {
	            	e.printStackTrace();
		            return result;
	            }

			} catch (SQLException e) {
	            e.printStackTrace();
	            return result;
	        }
			
			return result;
		}
		
		public void loadBlogData(String post, int id) {
			 try (PreparedStatement ps = Con
		                    .prepareStatement(INSERT_BLOG))
			 	{
				 System.out.println(post + "before load");
				 java.util.Calendar cal = java.util.Calendar.getInstance(); 
				 java.sql.Date timeNow = new Date(cal.getTimeInMillis());
				 
				 ps.setString(1, post);
		         ps.setDate(2,timeNow);
		         ps.setInt(3, id);
		         ps.executeUpdate();
		            
		        } catch (SQLException e) {
		        	e.printStackTrace();
		        }
		}
		
		public List<Post> retrievePosts(int u_id) {
			List<Post> posts = new ArrayList<>();
			try (PreparedStatement ps = Con.prepareStatement((RETRIEVE_POST))) {
				ps.setInt(1, u_id);
	            try (ResultSet rs = ps.executeQuery()) {
	            	while (rs.next()) {
	            		Post postObj = new Post();
	            		postObj.setId(rs.getInt(1));
	            		postObj.setPost(rs.getString(2));
	            		postObj.setDate(rs.getTimestamp(3));
	            		postObj.setUserId(rs.getInt(4));
	            		posts.add(postObj);
	            	}
	            } catch (SQLException e) {
	            	e.printStackTrace();
	            	return null;
	            }

			} catch (SQLException e) {
	            e.printStackTrace();
	            return null;
	        }
			return posts;
			
		}
	}
