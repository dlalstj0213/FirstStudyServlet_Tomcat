<%@page contentType="text/html;charset=utf-8" import="java.sql.*, rhie.db.ConnectionPoolBean"%>
<jsp:useBean id="pool" class="rhie.db.ConnectionPoolBean" scope="application"/>

<%!
	String sql = "update BOARD set EMAIL = ?, SUBJECT = ?, CONTENT = ? where SEQ=?";
%>

<%
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String seqStr = request.getParameter("seq");
		if(seqStr != null) seqStr = seqStr.trim();
		int seq = Integer.parseInt(seqStr);
		String email = request.getParameter("email");
		String subject = request.getParameter("subject");
		String content = request.getParameter("content");

		out.println("<script language='javascript'>");
		

		Connection con = null;
		PreparedStatement pstmt = null;
		try{
			con = pool.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, email);
			pstmt.setString(2, subject);
			pstmt.setString(3, content);
			pstmt.setInt(4, seq);
			int i = pstmt.executeUpdate();
			if(i>0){
				out.println("alert('수정 성공')");
			}else{
				out.println("alert('수정 실패')");
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