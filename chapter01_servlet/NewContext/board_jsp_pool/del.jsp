<%@page contentType="text/html;charset=utf-8" import="java.sql.*, rhie.db.ConnectionPoolBean"%>
<jsp:useBean id="pool" class="rhie.db.ConnectionPoolBean" scope="application"/>

<%!
	String sql = "delete from BOARD where SEQ=?";
%>

<%
		String seqStr = request.getParameter("seq");
		if(seqStr != null) seqStr = seqStr.trim();
		int seq = Integer.parseInt(seqStr);
		
		Connection con = null;
		PreparedStatement pstmt = null;
		response.setContentType("text/html;charset=utf-8");
		out.println("<script language='javascript'>");
		try{
			con = pool.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, seq);
			int i = pstmt.executeUpdate();
			if(i>0){
				out.println("alert('삭제 성공')");
			}else{
				out.println("alert('삭제 실패')");
			}
			out.println("location.href='b_list.jsp'");
			out.println("</script>");
		}catch(SQLException se){
		}finally{
			try{
				if(pstmt != null) pstmt.close();
				if(con != null) pool.returnConnection(con);		
			}catch(SQLException se){
			}
		}
%>