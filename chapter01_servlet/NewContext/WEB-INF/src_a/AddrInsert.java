package rhie.sv.addr;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;

public class AddrInsert extends HttpServlet 
{
	String url = "jdbc:oracle:thin:@127.0.0.1:1521:JAVA";
	String usr = "servlet";
	String pwd = "java";
	Connection con;
	PreparedStatement pstmt;
	String sql="insert into ADDRESS values(ADDRESS_SEQ.nextval, ?, ?, SYSDATE)";

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
		String name = req.getParameter("name");
		String addr = req.getParameter("addr");
       
	    res.setContentType("text/html;charset=utf-8");
	    PrintWriter pw = res.getWriter();
		pw.println("<script language='javascript'>");
	    try{
			pstmt.setString(1, name);
			pstmt.setString(2, addr);
			int i = pstmt.executeUpdate();
			if(i>0){
				pw.println("alert('입력 성공')");
			}else{
				pw.println("alert('입력 실패')");
			}
		}catch(SQLException se){}

            pw.println("location.href='list.do'");
		pw.println("</script>");
	}
}
