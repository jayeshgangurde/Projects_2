package com.utility;

import java.sql.Connection;
import java.sql.DriverManager;

public class JdbcHelper {
private static Connection con;
private static final String DRIVER="com.mysql.jdbc.Driver";
private static final String URL="jdbc:mysql://localhost:3306/dbproject";
private static final String USERNAME="root";
private static final String PASSWORD="Jayesh@6555";

public static Connection getConnectionObject() 
  {

	try 
	 {
		Class.forName("com.mysql.jdbc.Driver");
		con = DriverManager.getConnection("jdbc:mysql://localhost:3306/dbproject", "root", "Jayesh@6555");
	 } 
	catch (Exception e) 
	 {
		e.printStackTrace();
	 }

    return con;
   }

}
