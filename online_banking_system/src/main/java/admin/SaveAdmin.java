package admin;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.GenericServlet;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;

@WebServlet("/saveadmin")
public class SaveAdmin extends GenericServlet
{
	@Override
	   public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException
	   {
		   String id=req.getParameter("id");
		   String name=req.getParameter("name");
		   String email=req.getParameter("email");
		   String password=req.getParameter("password");
		   
		   try
			  {
				  Class.forName("com.mysql.cj.jdbc.Driver");
				  Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/bank","root","vaishu");
				  PreparedStatement ps=con.prepareStatement("insert into admin(id,name,email,password) values(?,?,?,?)");
				  ps.setInt(1, Integer.parseInt(id));
				  ps.setString(2,name);
				  ps.setString(3, email);
				  ps.setString(4, password);
				 
				  ps.execute();
				  
				  PrintWriter pw= res.getWriter();
				  pw.println("<h1 align=center>Account Created Successfully</h1>");
				  pw.println("<h3 align=center>Now</h3>");
				  pw.println("<h1 align=center>LOGIN</h1>");
				  
				  RequestDispatcher rd=req.getRequestDispatcher("AdminLogin.html");
				  rd.include(req, res);
				  
				  //PrintWriter pw= res.getWriter();
				  //pw.println("<h1> DATA SAVED SUCCESSFULLY IN ADMIN TABLE</h1>"); 
				                                                                     
			  }                                                     
			  catch(ClassNotFoundException e)
			  {
				  e.printStackTrace();
			  }
			  catch(SQLException e)
			  {
				  e.printStackTrace();
			  }
			 
		   }
	
}
