package rhie.sv.board;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;

public class BoardUpdate extends HttpServlet{
	String url = "jdbc:oracle:thin:@127.0.0.1:1521:JAVA";
	String user = "servlet";
	String pwd = "java";
	Connection con;
	PreparedStatement pstmt;
	String sql = "update BOARD set EMAIL = ?, SUBJECT = ?, CONTENT = ? where SEQ=?";
	
	public BoardUpdate(){
		System.out.println("check BoardUpdate Constructor");
	}
	
	@Override
	public void init()throws ServletException{
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection(url, user, pwd);
			pstmt = con.prepareStatement(sql);
		}catch(ClassNotFoundException cnfe){
		}catch(SQLException se){
		}
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
		
		try{
			pstmt.setString(1, email);
			pstmt.setString(2, subject);
			pstmt.setString(3, content);
			pstmt.setInt(4, seq);
			System.out.println("Err-1");
			int i = pstmt.executeUpdate();
			System.out.println("Err-2");
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
			if(pw != null) pw.close();
			res.sendRedirect("b_list.do");
		}
	}
	@Override 
	public void destroy(){
		try{
			if(pstmt != null) pstmt.close();
			if(con != null) con.close();
		}catch(SQLException se){
		}
	}
}