package rhie.sv;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;
import rhie.db.*;

public class BoardServletPool extends HttpServlet{
	String sql = "select * from BOARD order by SEQ desc";
	
	private ConnectionPoolBean getPool(){
		ConnectionPoolBean pool = null;
		ServletContext application = getServletContext();
		Object obj = application.getAttribute("pool");
		try{
			if(obj == null){
				pool = new ConnectionPoolBean();
				application.setAttribute("pool", pool);
			}else{
				pool = (ConnectionPoolBean)obj;
			}
		}catch(ClassNotFoundException cnfe){
		}catch(SQLException se){
		}
		return pool;
	}
	
	public void service(HttpServletRequest req, HttpServletResponse res)throws IOException, ServletException{
		res.setContentType("text/html;charset=utf-8");
		PrintWriter pw = res.getWriter();
		
		pw.println("<!DOCTYPE html>");
		pw.println("<html>");
		pw.println("<head>");
		pw.println("<title> 게시판(with Pool) </title>");
		pw.println("<meta charset='utf-8'>");
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
		pw.println("</head>");
		pw.println("<body>");
		pw.println("<center>");
		pw.println("<font color='gray' size='4' face='휴먼편지체'>");
		pw.println("<hr width='600' size='2' color='gray' noshade>");
		pw.println("<h3> 게시판 (with Pool)</h3>");
		pw.println("<font color='gray' size='4' face='휴먼편지체'>");
		pw.println("<a href='../'>인덱스</a>");
		pw.println("&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
		pw.println("<a href='write.html'>글쓰기</a><br/>");
		pw.println("</font>");
		pw.println("<hr width='600' size='2' color='gray' noshade>");
		pw.println("<TABLE border='2' width='600' align='center' noshade>");
		pw.println("<TR size='2' align='center' noshade bgcolor='AliceBlue'>");
		pw.println("<th bgcolor='AliceBlue'>no</th>");
		pw.println("<th color='gray'>writer</th>");
		pw.println("<th color='gray'>e-mail</th>");
		pw.println("<th color='gray'>subject</th>");
		pw.println("<th color='gray'>date</th>");
		pw.println("</TR>");

		ConnectionPoolBean pool = null;
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		try{
			pool = getPool();
			if(pool == null) return;
			con = pool.getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			while(rs.next()){
				int seq = rs.getInt("SEQ");
				String writer = rs.getString("WRITER");
				String email = rs.getString("EMAIL");
				String subject = rs.getString("SUBJECT");
				java.sql.Date rdate = rs.getDate("RDATE");
				
				pw.println("<TR align='center' noshade>");
				pw.println("<TD>"+seq+"</TD>");
				pw.println("<TD>"+writer+"</TD>");
				pw.println("<TD>"+email+"</TD>");
				pw.println("<TD>");
				pw.println("<a href='content.do?seq="+seq+"'>");
				pw.println(subject);
				pw.println("</a>");
				pw.println("</TD>");
				pw.println("<TD>"+rdate+"</TD>");
				pw.println("</TR>");
			}
		}catch(SQLException se){
		}finally{
			try{
				if(rs != null) rs.close();
				if(stmt != null) stmt.close();
				if(con != null) pool.returnConnection(con);
			}catch(SQLException se){
			}
			pw.println("</TABLE>");
			pw.println("</center>");
			pw.println("</body>");
			pw.println("</html>");
		}

	}
}