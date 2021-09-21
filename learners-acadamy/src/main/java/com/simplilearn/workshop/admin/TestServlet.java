package com.simplilearn.workshop.admin;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

//import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

@WebServlet("/TestServlet")
public class TestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
	
		PrintWriter out = response.getWriter();
		response.setContentType("text/plain");
		
		// establish connection to the DB
		Connection myConnection = null;
		Statement myStatement = null;
		ResultSet myResultset = null;
		
		try {
			
			myConnection = DBConnection.getConnection();
		//create a sql statement
		String sql = "select * from `administrative-portal`.students";
		myStatement = myConnection.createStatement();
		
		
		//execute the sql statement
		myResultset = myStatement.executeQuery(sql);
		
		//process the resultset
		while(myResultset.next()) {
			String fname = myResultset.getString("fname");
			out.println(fname);
			
		}
		
		
		
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}

}