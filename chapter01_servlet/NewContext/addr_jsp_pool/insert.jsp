<%@page contentType="text/html;charset=utf-8" import="java.sql.*, rhie.db.ConnectionPoolBean"%>
<jsp:useBean id="pool" class="rhie.db.ConnectionPoolBean" scope="application"/>

<%!
	String sql="insert into ADDRESS values(ADDRESS_SEQ.nextval, ?, ?, SYSDATE)";
%>

<%
		request.setCharacterEncoding("utf-8");
		String name = request.getParameter("name");
		String addr = request.getParameter("addr");
       
	    response.setContentType("text/html;charset=utf-8");
		out.println("<script language='javascript'>");
	    
		Connection con = null;
		PreparedStatement pstmt = null;
		try{
			con = pool.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, name);
			pstmt.setString(2, addr);
			int i = pstmt.executeUpdate();
			if(i>0){
				out.println("alert('입력 성공')");
			}else{
				out.println("alert('입력 실패')");
			}
		}catch(SQLException se){
			
		}finally{
			try{
				if(pstmt != null) pstmt.close();
				if(con != null) pool.returnConnection(con);
			}catch(SQLException se){
			}
		}
        out.println("location.href='list.jsp'");
		out.println("</script>");
%>