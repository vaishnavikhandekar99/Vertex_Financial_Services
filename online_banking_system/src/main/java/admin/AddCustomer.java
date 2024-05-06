package admin;

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

@WebServlet("/AddCustomer")
public class AddCustomer extends HttpServlet
{
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException 
    {
    	String id=req.getParameter("id");
    	String name = req.getParameter("name");
    	String pincode = req.getParameter("pincode");
    	
    	Random random =new Random();
		Long accountnumber = random.nextLong(100000000000l);
		
		double balance=500;
		
    	try
    	{
    		Class.forName("com.mysql.cj.jdbc.Driver");
    		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/bank","root","vaishu");
    		PreparedStatement ps= con.prepareStatement("insert into customer(id,name,pincode,accountnumber,balance) values(?,?,?,?,?)");
    		
    		ps.setInt(1,  Integer.parseInt(id));
    		ps.setString(2, name);
    		ps.setInt(3,  Integer.parseInt(pincode));
    		ps.setLong(4, accountnumber);
    		ps.setDouble(5, balance);
            
    		ps.execute();
    		
    		PrintWriter pw=res.getWriter();
			pw.println("<h1>Data Saved Successfully</h1>");
			
			//PrintWriter pw=res.getWriter();
			//pw.println("<h1>Data Saved Successfully</h1>");
			
			//RequestDispatcher rd=req.getRequestDispatcher("AdminMenu.html");
			//rd.include(req, res);
    		
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
