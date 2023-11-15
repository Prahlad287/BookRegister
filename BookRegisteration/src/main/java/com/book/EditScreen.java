package com.book;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
@WebServlet("/editScreen")
public class EditScreen extends HttpServlet{

	static String Query="SELECT BOOKNAME,BOOKEDITION,BOOKPRICE FROM BOOKDATA where ID=?";
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter out=resp.getWriter();
		resp.setContentType("text/html");
		int id=Integer.parseInt(req.getParameter("id"));
		
		//Load the Driver
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/book","root","root");
			System.out.println("Connetction is successfully created");
			PreparedStatement psmt=con.prepareStatement(Query);
			psmt.setInt(1, id);
			ResultSet rs=psmt.executeQuery();
			rs.next();
			out.println("<form action='editurl?id="+id+"' method='post'>");
			out.println("<table align='center'>");
			out.println("<tr>");
			out.println("<td> Book Name </td>");
			out.println("<td> <input type='text' name='bookName' value='"+rs.getString(1)+"'></td>");
			out.println("</tr>");
			out.println("<tr>");
			out.println("<td> Book Edition </td>");
			out.println("<td> <input type='text' name='bookEdition' value='"+rs.getString(2)+"'></td>");
			out.println("</tr>");
			out.println("<tr>");
			out.println("<td> Book Price </td>");
			out.println("<td> <input type='text' name='bookPrice' value='"+rs.getFloat(3)+"'></td>");
			out.println("<tr>");
			out.println("<td> <input type='submit' value='Edit'></td>");
			out.println("<td> <input type='reset' value='cancel'></td>");
			out.println("</tr>");
			out.println("</table>");
			out.println("</form>");
			
		
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
