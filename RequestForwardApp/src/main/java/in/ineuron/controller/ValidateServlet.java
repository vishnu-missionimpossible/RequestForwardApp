package in.ineuron.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
//import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ValidateServlet
 */
@WebServlet(urlPatterns = { "/test1" }, 
initParams = { 
		@WebInitParam(name = "url", value = "jdbc:mysql://localhost:3306/ineuron"), 
		@WebInitParam(name = "user", value = "root"), 
		@WebInitParam(name = "password", value = "Password")
})
public class ValidateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Connection connection = null;
	int rowCount = 0;
	PreparedStatement pstmt = null;
	ResultSet rs=null;
	
	
	@Override
	public void init() throws ServletException {
		System.out.println("RegistrationServlet is initializing...");
		ServletConfig config = getServletConfig();
		String url = config.getInitParameter("url");
		String user = config.getInitParameter("user");
		String password = config.getInitParameter("password");
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(url, user, password);
			
			String sqlSelectQuery="select name,password from credentials";
			pstmt = connection.prepareStatement(sqlSelectQuery);
			rs = pstmt.executeQuery();
			
		} catch(ClassNotFoundException cfe) {
			cfe.printStackTrace();
		}catch (SQLException se) {
			se.printStackTrace();
		}
		
    }
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uname = request.getParameter("uname");
		String upwd = request.getParameter("upwd");
		
		  try {
			if(rs.next()) {
				if(uname.equals(rs.getString(1)) && upwd.equals(rs.getString(2))) {
					//ServletContext sc = request.getServletContext();
					RequestDispatcher rd = request.getRequestDispatcher("/inbox.jsp");
					rd.forward(request, response);
				}
				else {
					RequestDispatcher rd = request.getRequestDispatcher("/error.jsp");
					rd.forward(request, response);
				}
				
			  }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	

}
