package customer;

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

@WebServlet("/CustomerLogin")
public class CustomerLogin extends HttpServlet
{
   
  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException 
  {
	  String accountnumber  = req.getParameter("accno");
	  String pincode = req.getParameter("pincode");
	  
	  try
	  {
		  Class.forName("com.mysql.cj.jdbc.Driver");
		  Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/bank","root","vaishu");
		  PreparedStatement ps= con.prepareStatement("select * from customer where accountnumber=? and pincode=?");
		  ps.setLong(1, Long.parseLong(accountnumber));
		  ps.setInt(2, Integer.parseInt(pincode));
		  
		  ResultSet rs = ps.executeQuery();
		   if(rs.next())
		   {
			    PrintWriter pw= res.getWriter();
				pw.println("<h1>WELCOME CUSTOMER</h1>");
				
			    RequestDispatcher rd = req.getRequestDispatcher("CustomerMenu.html");
			    rd.include(req,res);
			    
		   }
		   else
		   {
			   PrintWriter pw= res.getWriter();
			   pw.println("<h1>Invalid Credentials</h1>");
			   
			   RequestDispatcher rd = req.getRequestDispatcher("CustomerLogin.html");
			   rd.include(req,res);
			   
		   } 
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

