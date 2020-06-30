package rhie.sv;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

public class GetServlet extends HttpServlet 
{
	public void service(HttpServletRequest req, HttpServletResponse res)
                throws ServletException, java.io.IOException{
		// System.out.println("클라이언트의 요청을 받음");
		String name = req.getParameter("name");
		String ageStr = req.getParameter("age");
		int age = Integer.parseInt(ageStr);
		
		res.setContentType("text/html;charset=utf-8");
		PrintWriter pw = res.getWriter();
		pw.println("<center>");
			pw.println("<h1>방가^^"+name+"님 ("+age+"살)<h1>");
			pw.println("<a href='./'>방가^^"+name+"님 ("+age+"살)</a>");
			pw.println("<br/><br/>");
			pw.println("<a href='./'>인덱스</a>");
		pw.println("</center>");
		
		System.out.println("hello servelet");
	}
}
	// (doGet, doPost로 오버라이딩을 했을 시)결과는 똑같음
	// public void doPost(HttpServletRequest req, HttpServletResponse res)
	// public void doGet(HttpServletRequest req, HttpServletResponse res)
                // throws ServletException, java.io.IOException{
		// System.out.println("클라이언트의 요청을 받음");
		// String name = req.getParameter("name");
		// String ageStr = req.getParameter("age");
		// int age = Integer.parseInt(ageStr);
		
		// res.setContentType("text/html;charset=utf-8");
		// PrintWriter pw = res.getWriter();
		// pw.println("<center>");
			// pw.println("<h1>방가^^"+name+"님 ("+age+"살)<h1>");
			// pw.println("<a href='./'>방가^^"+name+"님 ("+age+"살)</a>");
			// pw.println("<br/><br/>");
			// pw.println("<a href='./'>인덱스</a>");
		// pw.println("</center>");
		
		// System.out.println("hello servelet");
	// }