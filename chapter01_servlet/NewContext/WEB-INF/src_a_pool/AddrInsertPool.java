package rhie.sv.addr;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;
import rhie.db.ConnectionPoolBean;

public class AddrInsertPool extends HttpServlet {
	String sql="insert into ADDRESS values(ADDRESS_SEQ.nextval, ?, ?, SYSDATE)";

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
	public void doPost(HttpServletRequest req, HttpServletResponse res)
		throws IOException, ServletException {
		req.setCharacterEncoding("utf-8");
		String name = req.getParameter("name");
		String addr = req.getParameter("addr");
       
	    res.setContentType("text/html;charset=utf-8");
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
			pstmt.setString(1, name);
			pstmt.setString(2, addr);
			int i = pstmt.executeUpdate();
			if(i>0){
				pw.println("alert('입력 성공')");
			}else{
				pw.println("alert('입력 실패')");
			}
		}catch(SQLException se){
			
		}finally{
			try{
				if(pstmt != null) pstmt.close();
				if(con != null) pool.returnConnection(con);
			}catch(SQLException se){
			}
		}

        pw.println("location.href='list.do'");
		pw.println("</script>");
	}
}
