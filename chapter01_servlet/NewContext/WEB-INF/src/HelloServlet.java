package rhie.hello;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

public class HelloServlet extends HttpServlet 
{
	public void service(HttpServletRequest req, HttpServletResponse res)
                throws ServletException, java.io.IOException{
		// System.out.println("Ŭ���̾�Ʈ�� ��û�� ����");
		
		res.setContentType("text/html;charset=utf-8");
		PrintWriter pw = res.getWriter();
		pw.println("<center>");
			pw.println("<h1>���� �μ��� ó�� ������ ������^^��<h1>");
			pw.println("<h2>���� �μ��� ó�� ������ ������^^��<h2>");
			pw.println("<h3>���� �μ��� ó�� ������ ������^^��<h3>");
			pw.println("<br/><br/>");
			pw.println("<a href='./'>�ε���</a>");
		pw.println("</center>");
		
		System.out.println("hello servelet");
	}
}