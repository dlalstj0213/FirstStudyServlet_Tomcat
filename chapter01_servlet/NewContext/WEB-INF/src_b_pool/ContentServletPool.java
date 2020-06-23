package rhie.sv.board;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;
import rhie.db.ConnectionPoolBean;

public class ContentServletPool extends HttpServlet {
	String sql= "select * from BOARD where SEQ = ?";
	
	public ContentServletPool(){
		System.out.println("ContentServletPool 생성자 호출");
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
		pw.println("<font color='gray' size='4' face='휴먼편지체'>");
		pw.println("<hr width='600' size='2' color='gray' noshade>");
		pw.println("<h3>게시글 (with Pool)</h3>");
		pw.println("<font color='gray' size='4' face='휴먼편지체'>");
		pw.println("<a href='write.html'>글쓰기</a>");
		pw.println("</font>");
		pw.println("<hr width='600' size='2' color='gray' noshade>");
		pw.println("</font>");
		pw.println("<table border='2' width='600' align='center' noshade>");
		pw.println("<tr>");
		pw.println("<td width='20%' align='center'>No</td>");
		pw.println("<td>"+seq+"</td>");
		pw.println("</tr>");

		ConnectionPoolBean pool = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			pool = getPool();
			if(pool == null) return;
			con = pool.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, seq);
			rs = pstmt.executeQuery();
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
			pw.println("<font color='gray' size='4' face='휴먼편지체'>");
			pw.println("<a href='update_page.do?seq="+seq+"'>수정</a>");
			pw.println(" &nbsp;&nbsp;|");
			pw.println("<a href='del.do?seq="+seq+"'>삭제</a>");
			pw.println(" &nbsp;&nbsp;| ");
			pw.println("<a href='b_list.do'>목록</a>");
			pw.println("</font>");
			pw.println("<hr width='600' size='2' color='gray' noshade>");
			pw.println("</center>");
			try{
				if(rs != null) rs.close();	
				if(pstmt != null) pstmt.close();
				if(con != null) pool.returnConnection(con);				
			}catch(SQLException se){
			}
		}
	}
}
//pw.println("<a href='update.do?seq="+seq+"&writer="+writer+"'>수정</a>");
