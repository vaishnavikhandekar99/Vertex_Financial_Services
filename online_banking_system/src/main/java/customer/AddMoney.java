package customer;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/AddMoney")
public class AddMoney extends HttpServlet
{
   @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
   {
	 String accountnumber =req.getParameter("accno");
	 String pincode = req.getParameter("pincode");
	 String amount = req.getParameter("amount");
	 
	 try
	 {
		Class.forName("com.mysql.cj.jdbc.Driver") ;
		Connection con =DriverManager.getConnection("jdbc:mysql://localhost:3306/bank","root","vaishu");
		PreparedStatement ps=con.prepareStatement("select * from customer where accountnumber=? and pincode=?");
		ps.setLong(1, Long.parseLong(accountnumber));
		ps.setInt(2, Integer.parseInt(pincode));
		
		ResultSet rs=ps.executeQuery();
		if(rs.next())
		{
			Double dbbalance=rs.getDouble("balance");
			Double updatedbalance = dbbalance + Double.parseDouble(amount);
			
			PreparedStatement ps1=con.prepareStatement("update customer set balance=? where accountnumber=? and pincode=?");
			ps1.setDouble(1, updatedbalance);
			ps1.setLong(2, Long.parseLong(accountnumber));
			ps1.setInt(3, Integer.parseInt(pincode));
			
			ps1.execute();
			
			PrintWriter pw=resp.getWriter();
			pw.println("<h2>Amount Debited Successfully</h2>");
			pw.println();
			pw.println("Total balance is: " +updatedbalance);	
		    resp.setContentType("text/html");
		}
		
		else
		{
			PrintWriter pw=resp.getWriter();
			pw.println("<h1>INVALID CREDENTIALS</h1>");
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
