package rhie.sv.board;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;

public class ContentServlet extends HttpServlet 
{
	String url = "jdbc:oracle:thin:@127.0.0.1:1521:JAVA";
	String usr = "servlet";
	String pwd = "java";
	Connection con;
	PreparedStatement pstmt;
	String sql= "select * from BOARD where SEQ = ?";
	
	public ContentServlet(){
		System.out.println("ContentServlet ������ ȣ��");
	}
	
	@Override 
	public void init() throws ServletException {
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection(url, usr, pwd);
			pstmt = con.prepareStatement(sql);
		}catch(ClassNotFoundException cnfe){
		}catch(SQLException se){}
	}
	
	@Override
	public void destroy(){
		try{
			if(pstmt != null) pstmt.close();
			if(con != null) con.close();
		}catch(SQLException se){}
	}
	
	@Override
	public void service(HttpServletRequest req, HttpServletResponse res)
		throws IOException, ServletException {
		req.setCharacterEncoding("utf-8");
		String seqStr = req.getParameter("seq");
		int seq = Integer.parseInt(seqStr.trim());
		
		res.setContentType("text/html;charset=utf-8");
	    PrintWriter pw = res.getWriter();
		
		pw.println("<!DOCTYPE html>");
		pw.println("<style>");
		pw.println("table, th, td {");
		pw.println("border: 1px solid black;");
		pw.println("border-collapse: collapse;");
		pw.println("}");
		pw.println("th, td {");
		pw.println("padding: 5px;");
		pw.println("}");
		pw.println("a { text-decoration:none }");
		pw.println("</style>");
		pw.println("<meta charset='utf-8'>");
		pw.println("<center>");
		pw.println("<font color='gray' size='4' face='�޸�����ü'>");
		pw.println("<hr width='600' size='2' color='gray' noshade>");
		pw.println("<h3>�Խñ�</h3>");
		pw.println("<font color='gray' size='4' face='�޸�����ü'>");
		pw.println("<a href='write.html'>�۾���</a>");
		pw.println("</font>");
		pw.println("<hr width='600' size='2' color='gray' noshade>");
		pw.println("</font>");
		pw.println("<table border='2' width='600' align='center' noshade>");
		pw.println("<tr>");
		pw.println("<td width='20%' align='center'>No</td>");
		pw.println("<td>"+seq+"</td>");
		pw.println("</tr>");

		ResultSet rs = null;
		try{
			pstmt.setInt(1, seq);
			System.out.println("check1");
			rs = pstmt.executeQuery();
			System.out.println("check2");
			while(rs.next()){
				String writer = rs.getString("WRITER");
				String email = rs.getString("EMAIL");
				String subject = rs.getString("SUBJECT");
				String content = rs.getString("CONTENT");
				
				pw.println("<tr>");
				pw.println("<td width='20%' align='center'>Writer</td>");
				pw.println("<td>"+writer+"</td>");
				pw.println("</tr>");
				pw.println("<tr>");
				pw.println("<td align='center'>E-mail</td>");
				pw.println("<td>"+email+"</td>");
				pw.println("</tr>");
				pw.println("<tr>");
				pw.println("<td align='center'>Subject</td>");
				pw.println("<td>"+subject+"</td>");
				pw.println("</tr>");
				pw.println("<tr>");
				pw.println("<td align='center'>Contents</td>");
				pw.println("<td>"+content+"</td>");
				pw.println("</tr>");
			}
		}catch(SQLException se){
			System.out.println("SQLException : "+se);
		}finally{
			pw.println("</table>");
			pw.println("<hr width='600' size='2' color='gray' noshade>");
			pw.println("<font color='gray' size='4' face='�޸�����ü'>");
			pw.println("<a href='update_page.do?seq="+seq+"'>����</a>");
			pw.println(" &nbsp;&nbsp;|");
			pw.println("<a href='del.do?seq="+seq+"'>����</a>");
			pw.println(" &nbsp;&nbsp;| ");
			pw.println("<a href='b_list.do'>���</a>");
			pw.println("</font>");
			pw.println("<hr width='600' size='2' color='gray' noshade>");
			pw.println("</center>");
			try{
				if(rs != null) rs.close();
				if(pw != null) pw.close();			
			}catch(SQLException se){
			}
		}
	}
}
//pw.println("<a href='update.do?seq="+seq+"&writer="+writer+"'>����</a>");
