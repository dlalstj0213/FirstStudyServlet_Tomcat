package rhie.hello;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

public class HelloServlet extends HttpServlet 
{
	public void service(HttpServletRequest req, HttpServletResponse res)
                throws ServletException, java.io.IOException{
		// System.out.println("클라이언트의 요청을 받음");
		
		res.setContentType("text/html;charset=utf-8");
		PrintWriter pw = res.getWriter();
		pw.println("<center>");
			pw.println("<h1>여긴 민서가 처음 열었던 서블릿ㅎ^^ㅎ<h1>");
			pw.println("<h2>여긴 민서가 처음 열었던 서블릿ㅎ^^ㅎ<h2>");
			pw.println("<h3>여긴 민서가 처음 열었던 서블릿ㅎ^^ㅎ<h3>");
			pw.println("<br/><br/>");
			pw.println("<a href='./'>인덱스</a>");
		pw.println("</center>");
		
		System.out.println("hello servelet");
	}
}