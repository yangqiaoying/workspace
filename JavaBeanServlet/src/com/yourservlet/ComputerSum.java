package com.yourservlet;
import com.yourbean.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class ComputerSum extends HttpServlet {
	private static final long serialVersionUID = 1L;
    Data data;
	
    //使用init方法初始化
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        data = new Data();
    }
    
    //在doGet方法中调用doPost方法
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	//在doPost方法中编写处理过程
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//用CreateJavaBean中的Data方法创建bean对象
		Data databean = new Data();
		//将所创建的bean对象存放在HttpServletRequest对象中,并指定查找关键字为data,该步骤决定bean的生命周期为Request
		request.setAttribute("data", databean);
		
		if (request.getParameter("type").equals("dengcha")) {	//等差数列求和
			double a = Integer.parseInt(request.getParameter("a"));
			double d = Integer.parseInt(request.getParameter("d"));
			double n = Integer.parseInt(request.getParameter("n"));
			double sum = a*n + n*(n-1)*d/2;
			databean.setA(a);	//将数据存储在databean中
			databean.setD(d);
			databean.setN(n);
			databean.setSum(sum);
			// 设置返回的html信息
			databean.resetMess();
			databean.setMess(
				"<table border=\"1\">"
				+ "<tr>"
				+ "<th>数列的首项</th>"
				+ "<th>等差数列的公差</th>"
				+ "<th>所求项数</th>"
				+ "<th>求和结果</th>"
				+ "</tr>"
				+ "	<tr>"
				+ "<td>" + a + "</td>"
				+ "<td>" + d + "</td>"
				+ "<td>" + n + "</td>"
				+ "<td>" + sum + "</td>"
				+ "</tr>"
				+ "</table>"
			);
			//Servlet请求showResult界面负责展示
			RequestDispatcher dispatcher = request.getRequestDispatcher("showResult.jsp");
			dispatcher.forward(request, response);
		} else {	//等比数列求和
			double a = Integer.parseInt(request.getParameter("a"));
			double q = Integer.parseInt(request.getParameter("q"));
			double n = Integer.parseInt(request.getParameter("n"));
			double sum = q==1 ? a*n : (a-a*Math.pow(q, n))/(1-q);
			databean.setA(a);
			databean.setQ(q);
			databean.setN(n);
			databean.setSum(sum);
			// 设置返回的html信息
			databean.resetMess();
			databean.setMess(
				"<table border=\"1\">"
				+ "<tr>"
				+ "<th>数列的首项</th>"
				+ "<th>等比数列的公比</th>"
				+ "<th>所求项数</th>"
				+ "<th>求和结果</th>"
				+ "</tr>"
				+ "	<tr>"
				+ "<td>" + a + "</td>"
				+ "<td>" + q + "</td>"
				+ "<td>" + n + "</td>"
				+ "<td>" + sum + "</td>"
				+ "</tr>"
				+ "</table>"
			);
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("showResult.jsp");
			dispatcher.forward(request, response);
		}
	}

}
