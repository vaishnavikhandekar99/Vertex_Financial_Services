package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateTable 
{
   public static void main(String[] args) throws ClassNotFoundException, SQLException
   {
	   Class.forName("com.mysql.cj.jdbc.Driver");
	   Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bank","root","vaishu");
	   System.out.println("Establish connection successfully");
	   Statement st = con.createStatement();
	   System.out.println("Statement Created successfully");  
	   st.execute("create table admin(id int primary key,name varchar(20),email varchar(20),password varchar(20))");
	   System.out.println("admin table created successfuly");
	   st.execute("create table customer(id int primary key,name varchar(20),accountnumber long,pincode int,balance double)");
	   System.out.println("customer table created successfuly");
	   
	   
   }
}
