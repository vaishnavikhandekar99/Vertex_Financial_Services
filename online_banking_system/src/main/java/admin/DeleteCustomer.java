package admin;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/DeleteCustomer")
public class DeleteCustomer extends HttpServlet
{
  @Override
protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
  {
	String id=req.getParameter("id");
	String pincode=req.getParameter("pincode");

	try
	{
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/bank","root","vaishu");
		PreparedStatement ps= con.prepareStatement("delete from customer where id=? and pincode=?");  
		                                                                                              
		ps.setInt(1, Integer.parseInt(id));
		ps.setInt(2, Integer.parseInt(pincode));
		
		int count=ps.executeUpdate();
		PrintWriter pw=resp.getWriter();
		if(count != 0)
		{
			pw.print("Data Deleted Successfully");
		
//			PrintWriter pw1=resp.getWriter();
//			pw1.println("<h1>Data Deleted Successfully</h1>");
//			
//			RequestDispatcher rd=req.getRequestDispatcher("AdminMenu.html");
//			rd.include(req, resp);
    		
		}
		else
		{
			pw.print("Data not found");
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
