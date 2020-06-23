package rhie.sv.board;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;
import rhie.db.ConnectionPoolBean;

public class WriteInsertPool extends HttpServlet {
	String sql="insert into BOARD values(BOARD_SEQ.nextval, ?, ?, ?, ?, SYSDATE)";
	
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
	
	public WriteInsertPool(){
		System.out.println("WriteInsert 생성자 호출");
	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res)
		throws IOException, ServletException {
		req.setCharacterEncoding("utf-8");
		String writer = req.getParameter("writer");
		String email = req.getParameter("email");
		String subject = req.getParameter("subject");
		String content = req.getParameter("content");
       
		ConnectionPoolBean pool = null;
		Connection con = null;
		PreparedStatement pstmt = null;
	    res.setContentType("text/html;charset=utf-8");
	    PrintWriter pw = res.getWriter();
		pw.println("<script language='javascript'>");
	    try{
			pool = getPool();
			if(pool == null) return;
			con = pool.getConnection();
			pstmt = con.prepareStatement(sql);
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
			try{
				if(pstmt != null) pstmt.close();
				if(con != null) pool.returnConnection(con);
			}catch(SQLException se){
			}
		}
	}
}
