<%@page contentType="text/html;charset=utf-8" import="java.sql.*, rhie.db.ConnectionPoolBean"%>
<jsp:useBean id="pool" class="rhie.db.ConnectionPoolBean" scope="application"/>

<%!
	String sql = "delete from ADDRESS where SEQ=?";
%>

<%
		String seqStr = request.getParameter("seq");
		if(seqStr != null) seqStr = seqStr.trim();
		int seq = Integer.parseInt(seqStr);
		
		Connection con = null;
		PreparedStatement pstmt = null;		
		try{
			con = pool.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, seq);
			pstmt.executeUpdate();
		}catch(SQLException se){
		}finally{
			try{
				if(pstmt != null) pstmt.close();
				if(con != null) pool.returnConnection(con);
			}catch(SQLException se){}
		}
		response.sendRedirect("list.jsp");
%>
