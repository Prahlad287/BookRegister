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

@WebServlet("/editurl")
public class UpdateUserData extends HttpServlet{
	static String query="select Name,Email, Mobile,Dob,City,Gender from User where id=?";
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter out=resp.getWriter();
		resp.setContentType("text/html");
		out.println("<link rel='stylesheet' href='css/bootstrap.css'></link>");
	//	out.println("<marquee><h2 class='text-primary'>User Data</h2></marquee>");
		int id=Integer.parseInt(req.getParameter("id"));
	try {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/UserRegister","root","root");
		PreparedStatement psmt=con.prepareStatement(query);
		psmt.setInt(1, id);
		ResultSet rs=psmt.executeQuery();
		rs.next();
		out.println("<div style='margin:auto;width:400px;margin-top:100px;'>");
		out.println("<form action='Editupdate?id="+id+ "' method='post'>");
		out.println("<table class='table table-hover table-striped'>");
		out.println("<tr>");
		out.println("<td>Name</td>");
		out.println("<td><input type='text' Name='Uname' value='"+rs.getString(1)+"'</th>");
		out.println("</tr>");
		out.println("<tr>");
		out.println("<td>Email</td>");
		out.println("<td><input type='email' Name='Email' value='"+rs.getString(2)+"'</th>");
		out.println("</tr>");
		out.println("<tr>");
		out.println("<td>Mobile_No</td>");
		out.println("<td><input type='text' Name='Mobile_No' value='"+rs.getString(3)+"'</th>");
		out.println("</tr>");
		out.println("<tr>");
		out.println("<td>DOB</td>");
		out.println("<td><input type='date' Name='DOB' value='"+rs.getString(4)+"'</th>");
		out.println("</tr>");
		out.println("<tr>");
		out.println("<td>City</td>");
		out.println("<td><input type='text' Name='City' value='"+rs.getString(5)+"'</th>");
		out.println("</tr>");
		out.println("<tr>");
		out.println("<td>Gender</td>");
		out.println("<td><input type='text' Name='Gender' value='"+rs.getString(6)+"'</th>");
		out.println("</tr>");
		out.println("<tr>");	
			out.println("<td><button type='submit' class='btn btn-outline-success'>edit</button> </td>");
			out.println("<td><button type='reset' class='btn btn-outline-danger'>Cancel</button> </td>");
			out.println("</tr>");
		
	
		out.println("</table>");
		out.println("</form>");
	
	} catch (Exception e) {
		out.println("<h2 class='bg-danger text-light text-center'>"+e.getMessage()+"</h2>");
	}
	out.println("<button class='btn btn-outline-success d-block'><a href='Home.html'>Home</a>"
			+ "</button>");
	out.println("<br>");
	out.println("<button class='btn btn-outline-success d-block'><a href='showdata'>Show Data</a>"
			+ "</button>");
	out.println("</div>");
	out.close();
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	// TODO Auto-generated method stub
	doGet(req, resp);
	}
}
