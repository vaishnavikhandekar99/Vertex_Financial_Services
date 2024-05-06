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

@WebServlet("/UpdateCustomer")
public class UpdateCustomer extends HttpServlet
{
  @Override
protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
  {
	String id=req.getParameter("id");
	String pincode = req.getParameter("pincode");
	String newname =req.getParameter("newname");
	String newpincode = req.getParameter("newpincode");
	
	try
	{
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/bank","root","vaishu");
		PreparedStatement ps=con.prepareStatement("update customer set name=?, pincode=? where id=? and pincode=?");
		ps.setString(1, newname);
		ps.setInt(2, Integer.parseInt(newpincode));
		ps.setInt(3, Integer.parseInt(id));
		ps.setInt(4, Integer.parseInt(pincode));
		
		int count = ps.executeUpdate();
		if(count != 0)
		{
			PrintWriter pw= resp.getWriter();
			pw.println("<h1>Data Updated Successfully</h1>");
			
//			PrintWriter pw1=resp.getWriter();
//			pw1.println("<h1>Data Updated Successfully</h1>");
//			
//			RequestDispatcher rd=req.getRequestDispatcher("AdminMenu.html");
//     		rd.include(req, resp);
		}
		else
		{
			PrintWriter pw= resp.getWriter();
			pw.println("<h1>Data not Found</h1>");
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
