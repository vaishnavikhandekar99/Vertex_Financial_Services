package admin;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ViewAllCustomer")
public class ViewAllCustomer extends HttpServlet   
{
  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
   {
	 try
	 {
		    resp.setContentType("text/html");
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/bank","root","vaishu");
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery("select * from customer");
			
			PrintWriter pw=resp.getWriter();
			
//			while(rs.next())
//			{
//				pw.println("Id: " + rs.getInt("id")); //pw.println("Id: "+rs.getInt(1)); 
//				pw.println("Name: " + rs.getString("name")); 
//				pw.println("Account Number: " + rs.getLong("accountnumber"));
//				pw.println("Pincode: " + rs.getInt("pincode"));
//				pw.println("Balance: " + rs.getDouble("balance"));
//			    pw.println("-----------------------------------");
//			}
		
			pw.println("<table border=2px cellspacing=0px cellpadding=10px><tr><td>ID</td><td>NAME</td><td>ACCOUNT NUMBER</td><td>PIN CODE</td><td>BALANCE</td></tr>");
    		
			while(rs.next())
			{
				 
	            int id = rs.getInt("id");
	            String name = rs.getString("name"); 
	            long accountnumber=rs.getLong("accountnumber");
	            int pincode=rs.getInt("pincode");
	            Double balance=rs.getDouble("balance");
	            
	             
	            
	            
//	            pw.println("ID: " + id + ", Name: " + name+ " Account Number: "+accountnumber+ " Pincode: "+pincode+ " Balance: "+balance); 
	            
	            pw.println("<tr><td>"+id+"</td><td>"+name+"</td><td>"+accountnumber+"</td><td>"+pincode+"</td><td>"+balance+"</td></tr>");
	            
		}
			
			pw.println("</table>");
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
