package com.yourservlet;
import com.yourbean.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class ComputerSum extends HttpServlet {
	private static final long serialVersionUID = 1L;
    Data data;
	
    //ʹ��init������ʼ��
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        data = new Data();
    }
    
    //��doGet�����е���doPost����
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	//��doPost�����б�д��������
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//��CreateJavaBean�е�Data��������bean����
		Data databean = new Data();
		//����������bean��������HttpServletRequest������,��ָ�����ҹؼ���Ϊdata,�ò������bean����������ΪRequest
		request.setAttribute("data", databean);
		
		if (request.getParameter("type").equals("dengcha")) {	//�Ȳ��������
			double a = Integer.parseInt(request.getParameter("a"));
			double d = Integer.parseInt(request.getParameter("d"));
			double n = Integer.parseInt(request.getParameter("n"));
			double sum = a*n + n*(n-1)*d/2;
			databean.setA(a);	//�����ݴ洢��databean��
			databean.setD(d);
			databean.setN(n);
			databean.setSum(sum);
			// ���÷��ص�html��Ϣ
			databean.resetMess();
			databean.setMess(
				"<table border=\"1\">"
				+ "<tr>"
				+ "<th>���е�����</th>"
				+ "<th>�Ȳ����еĹ���</th>"
				+ "<th>��������</th>"
				+ "<th>��ͽ��</th>"
				+ "</tr>"
				+ "	<tr>"
				+ "<td>" + a + "</td>"
				+ "<td>" + d + "</td>"
				+ "<td>" + n + "</td>"
				+ "<td>" + sum + "</td>"
				+ "</tr>"
				+ "</table>"
			);
			//Servlet����showResult���渺��չʾ
			RequestDispatcher dispatcher = request.getRequestDispatcher("showResult.jsp");
			dispatcher.forward(request, response);
		} else {	//�ȱ��������
			double a = Integer.parseInt(request.getParameter("a"));
			double q = Integer.parseInt(request.getParameter("q"));
			double n = Integer.parseInt(request.getParameter("n"));
			double sum = q==1 ? a*n : (a-a*Math.pow(q, n))/(1-q);
			databean.setA(a);
			databean.setQ(q);
			databean.setN(n);
			databean.setSum(sum);
			// ���÷��ص�html��Ϣ
			databean.resetMess();
			databean.setMess(
				"<table border=\"1\">"
				+ "<tr>"
				+ "<th>���е�����</th>"
				+ "<th>�ȱ����еĹ���</th>"
				+ "<th>��������</th>"
				+ "<th>��ͽ��</th>"
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