package com.book;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.cj.protocol.Resultset;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
@WebServlet("/booklist")
public class BookList extends HttpServlet {

	static String Query="SELECT ID, BOOKNAME,BOOKEDITION,BOOKPRICE FROM BOOKDATA";
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter out=resp.getWriter();
		resp.setContentType("text/html");
	
		
		//Load the Driver
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/book","root","root");
			System.out.println("Connetction is successfully created");
			PreparedStatement psmt=con.prepareStatement(Query);
			ResultSet rs=psmt.executeQuery();
			out.println("<table border='1' align='center'>");
			out.println("<tr>");
			out.println("<th> Book Id</th>");
			out.println("<th> Book Name</th>");
			out.println("<th> Book Edition</th>");
			out.println("<th> Book Price</th>");
			out.println("<th> Edit</th>");
			out.println("<th> Delete</th>");
			out.println("</tr>");
			while (rs.next()) {
				out.println("<tr>");
				out.println("<td>"+rs.getInt(1)+"</td>");
				out.println("<td>"+rs.getString(2)+"</td>");
				out.println("<td>"+rs.getString(3)+"</td>");
				out.println("<td>"+rs.getFloat(4)+"</td>");
				out.println("<td><a href='editScreen?id="+rs.getInt(1)+"'>Edit</a></td>");
				out.println("<td><a href='deleteurl?id="+rs.getInt(1)+"'>Delete</a></td>");
				out.println("</tr>");
				
			}
			out.println("</table>");			
		
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			out.println("<h2>" +e.getMessage()+"</h2>");
		}
		out.println("<a href='Home.html'>Home</a>");
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		doGet(req, resp);
	}

}
