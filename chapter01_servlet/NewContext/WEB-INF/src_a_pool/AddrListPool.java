package rhie.sv.addr;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;
import rhie.db.ConnectionPoolBean;

public class AddrListPool extends HttpServlet{	
	String sql = "select * from ADDRESS order by SEQ desc";
	
	public AddrListPool(){
		System.out.println("check AddrListPool Constructor");
	}
	
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
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res)throws IOException, ServletException{
		res.setContentType("text/html;charset=utf-8");
		PrintWriter pw = res.getWriter();
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
		pw.println("<center>");
		pw.println("<h1>");
		pw.println("Address List(with Pool) ");
		pw.println("</h1>");
		pw.println("<a href='input.html'>입력</a><br/>");
		pw.println("<table border='1' cellpadding='7' cellspacing='2' width='50%'>");
		pw.println("<tr>");
		pw.println("<th>번호</th>");
		pw.println("<th>이름</th>");
		pw.println("<th>주소</th>");
		pw.println("<th>날짜</th>");
		pw.println("<th>삭제</th>");
		pw.println("</tr>");
		
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
				int seq = rs.getInt(1);
				String name = rs.getString(2);
				String addr = rs.getString(3);
				Date rdate = rs.getDate(4);
				
					pw.println("<tr>");
					pw.println("<td align='center'>"+seq+"</td>");
					pw.println("<td>"+name+"</td>");
					pw.println("<td>"+addr+"</td>");
					pw.println("<td>"+rdate+"</td>");
					pw.println("<td align='center'><a href='del.do?seq="+seq+"'>삭제</a></td>");
					pw.println("</tr>");
			}
		}catch(SQLException se){
		}finally{
			pw.println("</table>");
			pw.println("</center>");
			try{
				if(rs != null) rs.close();
				if(stmt != null) stmt.close();
				if(con != null) pool.returnConnection(con);
			}catch(SQLException se){
			}
		}
	}
}