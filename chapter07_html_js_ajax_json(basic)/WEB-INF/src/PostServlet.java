package rhie.sv;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

public class PostServlet extends HttpServlet 
{
	public void service(HttpServletRequest req, HttpServletResponse res)
                throws ServletException, java.io.IOException{
		// System.out.println("Ŭ���̾�Ʈ�� ��û�� ����");
		req.setCharacterEncoding("utf-8");
		String addr = req.getParameter("addr");
		res.setContentType("text/html;charset=utf-8");
		PrintWriter pw = res.getWriter();
		pw.println("<center>");
			pw.println("<h1>����� �ּҴ� <font color='red'>"+addr+"�Դϴ�.(post���)</font></h1>");
			pw.println("<br/><br/>");
			pw.println("<a href='./'>�ε���</a>");
		pw.println("</center>");
		
		System.out.println("hello servelet");
	}
}