package rhie.sv.addr;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;

public class AddrDel extends HttpServlet{
	String url = "jdbc:oracle:thin:@127.0.0.1:1521:JAVA";
	String user = "servlet";
	String pwd = "java";
	Connection con;
	PreparedStatement pstmt;
	String sql = "delete from ADDRESS where SEQ=?";
	
	public AddrDel(){
		System.out.println("check AddrDel Constructor");
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
		
		try{
			pstmt.setInt(1, seq);
			pstmt.executeUpdate();
		}catch(SQLException se){
		}
		res.sendRedirect("list.do");
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