package rhie.sv.board;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;
import rhie.db.ConnectionPoolBean;

public class BoardUpdatePool extends HttpServlet{
	String sql = "update BOARD set EMAIL = ?, SUBJECT = ?, CONTENT = ? where SEQ=?";
	
	public BoardUpdatePool(){
		System.out.println("check BoardUpdatePool Constructor");
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
	public void doPost(HttpServletRequest req, HttpServletResponse res)throws IOException, ServletException{
		req.setCharacterEncoding("utf-8");
		res.setContentType("text/html;charset=utf-8");
		String seqStr = req.getParameter("seq");
		if(seqStr != null) seqStr = seqStr.trim();
		int seq = Integer.parseInt(seqStr);
		String email = req.getParameter("email");
		String subject = req.getParameter("subject");
		String content = req.getParameter("content");
	    PrintWriter pw = res.getWriter();
		pw.println("<script language='javascript'>");
		
		ConnectionPoolBean pool = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		try{
			pool = getPool();
			if(pool == null) return;
			con = pool.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, email);
			pstmt.setString(2, subject);
			pstmt.setString(3, content);
			pstmt.setInt(4, seq);
			int i = pstmt.executeUpdate();
			if(i>0){
				pw.println("alert('수정 성공')");
			}else{
				pw.println("alert('수정 실패')");
			}
			pw.println("location.href='b_list.do'");
			pw.println("</script>");
		}catch(SQLException se){
			System.out.println("SQLException se : "+se);
		}finally{
			try{
				if(pstmt != null) pstmt.close();
				if(con != null) pool.returnConnection(con);
			}catch(SQLException se){
			}
		}
	}
}