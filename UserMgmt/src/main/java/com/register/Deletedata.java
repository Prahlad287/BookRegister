package com.register;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
@WebServlet("/Deleteurl")
public class Deletedata extends HttpServlet{
	static String query="delete from User where id=?";
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter out=resp.getWriter();
		resp.setContentType("text/html");
		out.println("<link rel='stylesheet' href='css/bootstrap.css'></link>");
//		out.println("<marquee><h2 class='text-primary'>User Data</h2></marquee>");
		int id=Integer.parseInt(req.getParameter("id"));
	try {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/UserRegister","root","root");
		PreparedStatement psmt=con.prepareStatement(query);
		psmt.setInt(1, id);
	
		out.println("<div style='margin:auto;width:300px;margin-top:100px;'>");
		int count=psmt.executeUpdate();
		
		if (count==1) {
			out.println("<h2 class='bg-success text-light text-center'>Record Delete successfully</h2>");
		}else {
			out.println("<h2 class='bg-danger text-light text-center'>Record is not Delete</h2>");
		}
	} catch (Exception e) {
		out.println("<h2 class='bg-danger text-light text-center'>"+e.getMessage()+"</h2>");
	}
	out.println("<a href='Home.html' class='btn btn-outline-success d-block'>Home</a>");
	out.println("<br>");
	out.println("<a href='showdata' class='btn btn-outline-success d-block'>Show Data</a>");
	out.println("</div>");
	out.close();
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	// TODO Auto-generated method stub
	doGet(req, resp);
	}

}
