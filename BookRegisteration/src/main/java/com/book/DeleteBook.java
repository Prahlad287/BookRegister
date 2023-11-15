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
@WebServlet("/deleteurl")
public class DeleteBook extends HttpServlet {
	static String Query="delete from BOOKDATA WHERE ID=?";
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
			int count=psmt.executeUpdate();
			if(count==1) {
				out.println("<h2> Record is Deleted successfully </h2>");
			}else {
				out.println("<h2> Record is not Deleted successfully </h2>");
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
