package customer;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Random;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/savecustomer")
public class SaveCustomer extends HttpServlet
{
   @Override
protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException 
   {
	  String id=req.getParameter("id");
	  String name=req.getParameter("name");
	  String pincode=req.getParameter("pincode"); 
	   
	  Random random = new Random();                                //To generate accountnumber we take random class.
	  Long accountnumber = random.nextLong(100000000000l);  
	                                                             
	  double balance = 500;
	  
	  try
	  {
		  Class.forName("com.mysql.cj.jdbc.Driver");
		  Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/bank","root","vaishu");
		  PreparedStatement ps=con.prepareStatement("insert into customer(id,name,accountnumber,balance,pincode) values(?,?,?,?,?)");
		  ps.setInt(1, Integer.parseInt(id));
		  ps.setString(2,name);
		  ps.setLong(3, accountnumber);
		  ps.setDouble(4, balance);
		  ps.setInt(5, Integer.parseInt(pincode));
		 
		  ps.execute();
		  
		  PrintWriter pw= res.getWriter();
		  pw.println("<h1 align=center>Account Created Successfully</h1>");
		  pw.println("<h3 align=center>Now</h3>");
		  pw.println("<h1 align=center>LOGIN</h1>");
		  
		  RequestDispatcher rd=req.getRequestDispatcher("CustomerLogin.html");
		  rd.include(req, res);
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
