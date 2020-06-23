package rhie.sv.addr;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;
import rhie.db.ConnectionPoolBean;

public class AddrDelPool extends HttpServlet{
	String sql = "delete from ADDRESS where SEQ=?";
	
	public AddrDelPool(){
		System.out.println("check AddrDelPool Constructor");
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
		String seqStr = req.getParameter("seq");
		if(seqStr != null) seqStr = seqStr.trim();
		int seq = Integer.parseInt(seqStr);

		ConnectionPoolBean pool = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		try{
			pool = getPool();
			if(pool == null) return;
			con = pool.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, seq);
			pstmt.executeUpdate();
		}catch(SQLException se){
		}finally{
			try{
				if(pstmt != null) pstmt.close();
				if(con != null) pool.returnConnection(con);
			}catch(SQLException se){
			}
		}
		res.sendRedirect("list.do");
	}
}
