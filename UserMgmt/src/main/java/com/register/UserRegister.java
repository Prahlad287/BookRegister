package com.register;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/register")
public class UserRegister extends HttpServlet {
			static String query="insert into User( Name,Email, Mobile,Dob,City,Gender) values(?,?,?,?,?,?)";
					
		@Override
		protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			PrintWriter out=resp.getWriter();
			resp.setContentType("text/html");
			out.println("<link rel='stylesheet' href='css/bootstrap.css'></link>");
		String name=req.getParameter("UserName");
		String email=req.getParameter("email");
		String mobile=req.getParameter("mobile");
		String dob=req.getParameter("dob");
		String City=req.getParameter("City");
		String gender=req.getParameter("gender");
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/UserRegister","root","root");
			PreparedStatement psmt=con.prepareStatement(query);
			psmt.setString(1, name);
			psmt.setString(2, email);
			psmt.setString(3, mobile);
			psmt.setString(4, dob);
			psmt.setString(5, City);
			psmt.setString(6, gender);
			int count=psmt.executeUpdate();
			out.println("<div class='card' style='margin:auto;width:300Px;margin-top:100px;'>");
			if (count==1) {
				out.println("<h2 class='bg-success text-light text-center'>Record Registered successfully</h2>");
			}else {
				out.println("<h2 class='bg-danger text-light text-center'>Record is not Registered</h2>");
			}
		} catch (Exception e) {
			out.println("<h2 class='bg-danger text-light text-center'>"+e.getMessage()+"</h2>");
		}
		out.println("<button class='btn btn-outline-success d-block'><a href='Home.html'>Home</a>"
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
