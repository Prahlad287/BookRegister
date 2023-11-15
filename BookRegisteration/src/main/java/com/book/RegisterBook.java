package com.book;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
@WebServlet("/register")
public class RegisterBook extends HttpServlet{
	
	static String Query="INSERT INTO BOOKDATA(BOOKNAME,BOOKEDITION,BOOKPRICE) VALUES(?,?,?)";
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter out=resp.getWriter();
		resp.setContentType("text/html");
	
		//GET THE BOOK INFO
		String bookName=req.getParameter("bookName");
		String bookEdition=req.getParameter("bookEdition");
		float bookPrice=Float.parseFloat(req.getParameter("bookPrice"));
		//Load the Driver
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/book","root","root");
			System.out.println("Connetction is successfully created");
			PreparedStatement psmt=con.prepareStatement(Query);
			psmt.setString(1, bookName);
			psmt.setString(2, bookEdition);
			psmt.setFloat(3, bookPrice);
			int count=psmt.executeUpdate();
			if(count==1) {
				out.println("<h2> Record is Registered Sucessfully</h2>");
			}
			else {
				out.println("<h2> Record is Not Registered Sucessfully</h2>");
			}
			
		
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			out.println("<h2>" +e.getMessage()+"</h2>");
		}
		out.println("<a href='Home.html'>Home</a>");
		out.println("<br>");
		out.println("<a href='booklist'>Book List</a>");
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		doGet(req, resp);
	}
}
