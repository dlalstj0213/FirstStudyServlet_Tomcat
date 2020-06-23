package rhie.sv.board;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;

public class WriteInsert extends HttpServlet 
{
	String url = "jdbc:oracle:thin:@127.0.0.1:1521:JAVA";
	String usr = "servlet";
	String pwd = "java";
	Connection con;
	PreparedStatement pstmt;
	String sql="insert into BOARD values(BOARD_SEQ.nextval, ?, ?, ?, ?, SYSDATE)";
	
	public WriteInsert(){
		System.out.println("WriteInsert 생성자 호출");
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
	public void doPost(HttpServletRequest req, HttpServletResponse res)
		throws IOException, ServletException {
		req.setCharacterEncoding("utf-8");
		String writer = req.getParameter("writer");
		String email = req.getParameter("email");
		String subject = req.getParameter("subject");
		String content = req.getParameter("content");
       
	    res.setContentType("text/html;charset=utf-8");
	    PrintWriter pw = res.getWriter();
		pw.println("<script language='javascript'>");
	    try{
			pstmt.setString(1, writer);
			pstmt.setString(2, email);
			pstmt.setString(3, subject);
			pstmt.setString(4, content);
			int i = pstmt.executeUpdate();
			if(i>0){
				pw.println("alert('작성 성공')");
			}else{
				pw.println("alert('작성 실패')");
			}
			pw.println("location.href='b_list.do'");
			pw.println("</script>");
		}catch(SQLException se){
			
		}finally{
			if(pw != null) pw.close();
		}
	}
}
