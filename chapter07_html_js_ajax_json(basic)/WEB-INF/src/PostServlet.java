package rhie.sv;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

public class PostServlet extends HttpServlet 
{
	public void service(HttpServletRequest req, HttpServletResponse res)
                throws ServletException, java.io.IOException{
		// System.out.println("클라이언트의 요청을 받음");
		req.setCharacterEncoding("utf-8");
		String addr = req.getParameter("addr");
		res.setContentType("text/html;charset=utf-8");
		PrintWriter pw = res.getWriter();
		pw.println("<center>");
			pw.println("<h1>당신의 주소는 <font color='red'>"+addr+"입니다.(post방식)</font></h1>");
			pw.println("<br/><br/>");
			pw.println("<a href='./'>인덱스</a>");
		pw.println("</center>");
		
		System.out.println("hello servelet");
	}
}