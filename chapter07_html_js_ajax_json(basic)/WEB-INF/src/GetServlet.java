package rhie.sv;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

public class GetServlet extends HttpServlet 
{
	public void service(HttpServletRequest req, HttpServletResponse res)
                throws ServletException, java.io.IOException{
		// System.out.println("Ŭ���̾�Ʈ�� ��û�� ����");
		String name = req.getParameter("name");
		String ageStr = req.getParameter("age");
		int age = Integer.parseInt(ageStr);
		
		res.setContentType("text/html;charset=utf-8");
		PrintWriter pw = res.getWriter();
		pw.println("<center>");
			pw.println("<h1>�氡^^"+name+"�� ("+age+"��)<h1>");
			pw.println("<a href='./'>�氡^^"+name+"�� ("+age+"��)</a>");
			pw.println("<br/><br/>");
			pw.println("<a href='./'>�ε���</a>");
		pw.println("</center>");
		
		System.out.println("hello servelet");
	}
}
	// (doGet, doPost�� �������̵��� ���� ��)����� �Ȱ���
	// public void doPost(HttpServletRequest req, HttpServletResponse res)
	// public void doGet(HttpServletRequest req, HttpServletResponse res)
                // throws ServletException, java.io.IOException{
		// System.out.println("Ŭ���̾�Ʈ�� ��û�� ����");
		// String name = req.getParameter("name");
		// String ageStr = req.getParameter("age");
		// int age = Integer.parseInt(ageStr);
		
		// res.setContentType("text/html;charset=utf-8");
		// PrintWriter pw = res.getWriter();
		// pw.println("<center>");
			// pw.println("<h1>�氡^^"+name+"�� ("+age+"��)<h1>");
			// pw.println("<a href='./'>�氡^^"+name+"�� ("+age+"��)</a>");
			// pw.println("<br/><br/>");
			// pw.println("<a href='./'>�ε���</a>");
		// pw.println("</center>");
		
		// System.out.println("hello servelet");
	// }