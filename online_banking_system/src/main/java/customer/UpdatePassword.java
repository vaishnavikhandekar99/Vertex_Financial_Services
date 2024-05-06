package customer;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/UpdatePassword")
public class UpdatePassword extends HttpServlet
{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	{
		String id = req.getParameter("id");
		String pincode = req.getParameter("pincode");
		String newpincode = req.getParameter("newpincode");

		try 
		{
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank", "root","vaishu");
			
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
	}
}
