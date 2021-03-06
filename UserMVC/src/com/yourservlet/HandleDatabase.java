package com.yourservlet;

import com.yourbean.UserBean;

import java.io.IOException;
import java.sql.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class HandleDatabase
 */
@WebServlet("/HandleDatabase")
public class HandleDatabase extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public void init() throws ServletException {
        super.init();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		UserBean database = new UserBean();
		request.setAttribute("database",database);
		
		request.setCharacterEncoding("utf-8");
		//注册
		if (request.getParameter("type").equals("register")){
			String logname = request.getParameter("lognaem");
			String password = request.getParameter("password");
			if(logname == ""){
				System.out.println("用户名不能为空!");
				return;
			}else if(password == ""){
				System.out.println("密码不能为空!");
				return;
			}
			String email = request.getParameter("email");
			String phone = request.getParameter("phone");
			String message = request.getParameter("message");
			database.setLogname(logname);
			database.setPassword(password);
			database.setEmail(email);
			database.setPhone(phone);
			database.setMessage(message);
			
			try {
				Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			}catch(Exception e){}
			
			Connection con;
			Statement sql;
			
			try {
				String uri = "jdbc:sqlserver://127.0.0.1:1433;DatabaseName=JspMVC";
				con=DriverManager.getConnection(uri,"sa","yqy");
				sql=con.createStatement();
				String strPrev = "insert into member (logname, password,";
				String strNext = ") values ('"+logname+"', '"+password;
				if (email != ""){
					strPrev += ",email";
					strNext += "','" + email;
				}
				if (phone != ""){
					strPrev += ",phone";
					strNext += "','" + phone;
				}
				if (message != ""){
					strPrev += ",message";
					strNext += "','" + message;
				}
				String str = strPrev + strNext + "');";
				System.out.println(str);
				sql.execute(str);
			}catch (SQLException e) {
				System.out.println(e);
		}
			RequestDispatcher dispatcher = request.getRequestDispatcher("showRegisterMess.jsp");
			dispatcher.forward(request, response);
	}else if (request.getParameter("type").equals("login")) {//登录
		String logname = request.getParameter("logname");
		String password = request.getParameter("password");
		if (logname == "") {
			System.out.println("用户名不能为空");
			return ;
		} else if (password == "") {
			System.out.println("密码不能为空");
		}
		database.setLogname(logname);
		
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		}catch(Exception e){}
		
		Connection con;
		Statement sql;
		ResultSet rs;
		
		try{
			String uri = "jdbc:sqlserver://127.0.0.1:1433;DatabaseName=JspMVC";
			con=DriverManager.getConnection(uri,"sa","yqy");
			sql=con.createStatement();
			String str = "select * from member where logname='"+logname+"' and password='"+password+"';";
			System.out.println("str ==> " + str);
			rs = sql.executeQuery(str);
			if (rs.next()) {
				database.setLogname(logname);
				System.out.println(rs.getString("logname"));					
			} else {
				System.out.println("用户不存在");
			}
		}catch (SQLException e) {
			System.out.println(e);
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher("showLoginMess.jsp");
		dispatcher.forward(request, response);
	}
	}
}
