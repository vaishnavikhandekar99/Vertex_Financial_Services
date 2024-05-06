package admin;

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

@WebServlet("/ViewCustomer")
public class ViewCustomer extends HttpServlet
{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	{
		String id= req.getParameter("id");
		
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/bank","root","vaishu");
			PreparedStatement ps=con.prepareStatement("select * from customer where id=?");
			ps.setInt(1, Integer.parseInt(id));
			ResultSet rs = ps.executeQuery();
			
			PrintWriter pw = resp.getWriter();
			if(rs.next())
			{
				
				pw.println("Id: "+id);   // pw.println("Id: "+rs.getInt(1)); pw.println("Id: "+rs.getInt("id")); 
				pw.println("Name: "+rs.getString("name")); 
				pw.println("Account Number: "+rs.getLong("accountnumber"));
				pw.println("Pincode: "+rs.getInt("pincode"));
				pw.println("Balance: "+rs.getDouble("balance"));
			}
			else
			{
				pw.println("id not found");
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
