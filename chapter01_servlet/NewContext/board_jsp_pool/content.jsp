<%@page contentType="text/html;charset=utf-8" import="java.sql.*, rhie.db.ConnectionPoolBean"%>
<jsp:useBean id="pool" class="rhie.db.ConnectionPoolBean" scope="application"/>

<%!
	String sql= "select * from BOARD where SEQ = ?";
%>

<%
		request.setCharacterEncoding("utf-8");
		String seqStr = request.getParameter("seq");
		int seq = Integer.parseInt(seqStr.trim());
%>

<!DOCTYPE html>
<style>
table, th, td {
border: 1px solid black;
border-collapse: collapse;
th, td {
padding: 5px;
}
a { text-decoration:none }
</style>
<meta charset='utf-8'>
<center>
<font color='gray' size='4' face='휴먼편지체'>
<hr width='600' size='2' color='gray' noshade>
<h3>게시글 (with Pool)</h3>
<font color='gray' size='4' face='휴먼편지체'>
<a href='write.html'>글쓰기</a>
</font>
<hr width='600' size='2' color='gray' noshade>
</font>
<table border='2' width='600' align='center' noshade>
<tr>
<td width='20%' align='center'>No</td>
<%
	out.println("<td>"+seq+"</td>");
%>
</tr>
		
<%
		response.setContentType("text/html;charset=utf-8");
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			con = pool.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, seq);
			rs = pstmt.executeQuery();
			while(rs.next()){
				String writer = rs.getString("WRITER");
				String email = rs.getString("EMAIL");
				String subject = rs.getString("SUBJECT");
				String content = rs.getString("CONTENT");

				out.println("<tr>");
				out.println("<td width='20%' align='center'>Writer</td>");
				out.println("<td>"+writer+"</td>");
				out.println("</tr>");
				out.println("<tr>");
				out.println("<td align='center'>E-mail</td>");
				out.println("<td>"+email+"</td>");
				out.println("</tr>");
				out.println("<tr>");
				out.println("<td align='center'>Subject</td>");
				out.println("<td>"+subject+"</td>");
				out.println("</tr>");
				out.println("<tr>");
				out.println("<td align='center'>Contents</td>");
				out.println("<td>"+content+"</td>");
				out.println("</tr>");
			}
		}catch(SQLException se){
			System.out.println("SQLException : "+se);
		}finally{
			out.println("</table>");
			out.println("<hr width='600' size='2' color='gray' noshade>");
			out.println("<font color='gray' size='4' face='휴먼편지체'>");
			out.println("<a href='update_page.jsp?seq="+seq+"'>수정</a>");
			out.println(" &nbsp;&nbsp;|");
			out.println("<a href='del.jsp?seq="+seq+"'>삭제</a>");
			out.println(" &nbsp;&nbsp;| ");
			out.println("<a href='b_list.jsp'>목록</a>");
			out.println("</font>");
			out.println("<hr width='600' size='2' color='gray' noshade>");
			out.println("</center>");
			try{
				if(rs != null) rs.close();	
				if(pstmt != null) pstmt.close();
				if(con != null) pool.returnConnection(con);				
			}catch(SQLException se){
			}
		}
%>
