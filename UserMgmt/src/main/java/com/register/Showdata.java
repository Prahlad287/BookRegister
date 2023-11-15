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
@WebServlet("/showdata")
public class Showdata extends HttpServlet{
	
				static String query="select id,Name,Email, Mobile,Dob,City,Gender from User";
						
			@Override
			protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
				PrintWriter out=resp.getWriter();
				resp.setContentType("text/html");
				out.println("<link rel='stylesheet' href='css/bootstrap.css'></link>");
				out.println("<marquee><h2 class='text-primary'>User Data</h2></marquee>");
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/UserRegister","root","root");
				PreparedStatement psmt=con.prepareStatement(query);
				ResultSet rs=psmt.executeQuery();
				out.println("<div style='margin:auto;width:900px;margin-top:100px;'>");
				out.println("<table class='table table-hover table-striped'>");
				out.println("<tr>");
				out.println("<th>ID</th>");
				out.println("<th>Name</th>");
				out.println("<th>Email</th>");
				out.println("<th>Mobile_No</th>");
				out.println("<th>DOB</th>");
				out.println("<th>City</th>");
				out.println("<th>Gender</th>");
				out.println("<th>Edit</th>");
				out.println("<th>Delete</th>");
				out.println("</tr>");
				while (rs.next()) {
					out.println("<tr>");
					out.println("<td>"+rs.getInt(1)+"</td>");
					out.println("<td>"+rs.getString(2)+"</td>");
					out.println("<td>"+rs.getString(3)+"</td>");
					out.println("<td>"+rs.getString(4)+"</td>");
					out.println("<td>"+rs.getString(5)+"</td>");
					out.println("<td>"+rs.getString(6)+"</td>");
					out.println("<td>"+rs.getString(7)+"</td>");
					out.println("<td><a href='editurl?id="+rs.getInt(1)+"'>edit</a> </td>");
					out.println("<td><a href='Deleteurl?id="+rs.getInt(1)+"'>delete</a> </td>");

					out.println("</tr>");
				}
			
				out.println("</table>");
			
			} catch (Exception e) {
				out.println("<h2 class='bg-danger text-light text-center'>"+e.getMessage()+"</h2>");
			}
			
			out.println("<a href='Home.html' class='btn btn-outline-success d-block'>Home</a>");

			out.println("</div>");
			out.close();
			}
			@Override
			protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			// TODO Auto-generated method stub
			doGet(req, resp);
			}
}
