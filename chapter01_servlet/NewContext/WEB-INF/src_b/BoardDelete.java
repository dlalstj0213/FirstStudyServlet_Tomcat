package rhie.sv.board;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;

public class BoardDelete extends HttpServlet{
	String url = "jdbc:oracle:thin:@127.0.0.1:1521:JAVA";
	String user = "servlet";
	String pwd = "java";
	Connection con;
	PreparedStatement pstmt;
	String sql = "delete from BOARD where SEQ=?";
	
	public BoardDelete(){
		System.out.println("check BoardDelete Constructor");
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
	public void doGet(HttpServletRequest req, HttpServletResponse res)throws IOException, ServletException{
		String seqStr = req.getParameter("seq");
		if(seqStr != null) seqStr = seqStr.trim();
		int seq = Integer.parseInt(seqStr);
		
		res.setContentType("text/html;charset=utf-8");
	    PrintWriter pw = res.getWriter();
		pw.println("<script language='javascript'>");
		try{
			pstmt.setInt(1, seq);
			int i = pstmt.executeUpdate();
			if(i>0){
				pw.println("alert('삭제 성공')");
			}else{
				pw.println("alert('삭제 실패')");
			}
			pw.println("location.href='b_list.do'");
			pw.println("</script>");
		}catch(SQLException se){
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

//자동으로 뒤로가는 방법
 // res.setContentType("text/html; charset=utf-8");
        // PrintWriter pw = res.getWriter();
        // pw.println("<meta charset='utf-8'>");
        // pw.println("<META HTTP-EQUIV='Refresh' CONTENT='1;URL=list.do'> ");