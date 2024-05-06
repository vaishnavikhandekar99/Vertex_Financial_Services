package admin;

import java.io.IOException;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/AdminLogin")
public class AdminLogin extends HttpServlet
{
	@Override
	 protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException 
	   {
		   String email= req.getParameter("email");
		   String password= req.getParameter("password");
		   
		   try
		   {
			      Class.forName("com.mysql.cj.jdbc.Driver");
				  Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/bank","root","vaishu");
				  PreparedStatement ps=con.prepareStatement("select * from admin where email=? and password =?");
				  ps.setString(1, email);
				  ps.setString(2, password); 
				  
				  ResultSet rs = ps.executeQuery();
				  
				  if(rs.next())
				  {
//					  RequestDispatcher rd=req.getRequestDispatcher("AdminMenu.html");
//					  rd.forward(req, res);
					  
					  if(rs.getString("email").equals(email) && rs.getString("password").equals(password))
						{
						    PrintWriter pw= res.getWriter();
							pw.println("<h1>WELCOME ADMIN</h1>");
							
							RequestDispatcher rd=req.getRequestDispatcher("AdminMenu.html");
							rd.include(req, res);
						  //res.setContentType("text/html");
						}
						else
						{
							PrintWriter pw= res.getWriter();
							pw.println("<h1>invalid credentials!</h1>");
							RequestDispatcher rd=req.getRequestDispatcher("AdminLogin.html");
							rd.include(req, res);
						    //res.setContentType("text/html");
						}
				  }
				  else
				  {
					  PrintWriter pw= res.getWriter();
					  pw.println("<h1> Invalid credentials </h1>");
					  
					  RequestDispatcher rd=req.getRequestDispatcher("AdminLogin.html");
					  rd.include(req, res);
				  }
		   }
		   catch(ClassNotFoundException e) 
		   {
			   e.printStackTrace();
		   } 
		   catch(SQLException  e)
		   {
			   e.printStackTrace();
		   }
       } 
}