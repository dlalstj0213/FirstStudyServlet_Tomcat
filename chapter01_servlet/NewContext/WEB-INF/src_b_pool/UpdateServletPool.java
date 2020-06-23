package rhie.sv.board;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;
import rhie.db.ConnectionPoolBean;

public class UpdateServletPool extends HttpServlet{

	String sql = "select * from BOARD where SEQ=?";
	
	public UpdateServletPool(){
		System.out.println("check UpdateServletPool Constructor");
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
		req.setCharacterEncoding("utf-8");
		res.setContentType("text/html;charset=utf-8");
		PrintWriter pw = res.getWriter();
		String seqStr = req.getParameter("seq");
		if(seqStr != null) seqStr = seqStr.trim();
		int seq = Integer.parseInt(seqStr);
		
		String writer = null;
		String email = null;
		String subject = null;
		String content = null;
		ConnectionPoolBean pool = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			pool = getPool();
			if(pool == null) return;
			con = pool.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, seq);
			rs = pstmt.executeQuery();
			while(rs.next()){
				writer = rs.getString("WRITER");
				email = rs.getString("EMAIL");
				subject = rs.getString("SUBJECT");
				content = rs.getString("CONTENT");
			}
			pw.println(
				"<!DOCTYPE html>"+
				"<html>"+
				"<head>"+
					"<title> 게시글 수정</title>"+
					"<meta charset='utf-8'>"+
					"<style>"+
					"table, th, td {"+
					   "border: 1px solid black;"+
					   "border-collapse: collapse;"+
					"}"+
					"th, td {"+
					   "padding: 5px;"+
					"}"+
					"a { text-decoration:none }"+
				"</style>"+
					"<script src='https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js'></script>"+
					"<script>"+
						"function f(){"+
							"input.email.value = '';"+
							"input.subject.value = '';"+
							//input.content.innerText = '';
							"$('#ta').text('');"+
							
							"input.email.focus();"+
						"}"+
					"</script>"+
				"</head>"+
				"<body>"+
				"<center>"+
				"<font color='gray' size='4' face='휴먼편지체'>"+
				"<hr width='600' size='2' color='gray' noshade>"+
				"<h3> 게시글 수정 (with pool) </h3>"+
				"</font>"+
				"<font color='gray' size='4' face='휴먼편지체'>"+
				"<a href='b_list.do'>목록</a>"+
				"&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+
				"<a href='write.html'>글쓰기</a><br/>"+
				"</font>"+
				"<hr width='600' size='2' color='gray' noshade>"+
				"</center>"+

				"<form name='input' method='post' action='update.do'>"+
				"<input type='hidden' name='seq' value='"+seq+"'>"+
				"<table border='0' width='600' align='center' cellpadding='3' cellspacing='1' bordercolor='gray'>"+
				"<tr>"+
				   "<td width='20%' align='center' >WRITER</td>"+
				   "<td>"+
					  "<input name='writer' readonly value='"+writer+"' size='80'/>"+
				   "</td>"+
				"</tr>"+

				"<tr>"+
					"<td align='center'>EMAIL</td>"+
					"<td><input name='email' value='"+email+"' size='80'/></td>"+
				"</tr>"+

				"<tr>"+
					"<td align='center'>SUBJECT</td>"+
					"<td><input name='subject' value='"+subject+"' size='80'/></td>"+
				"</tr>"+
							
				"<tr>"+
					"<td align='center'>CONTENT</td>"+
					"<td><textarea id='ta' name='content' rows='15' cols='60'>"+content+"</textarea></td>"+
				"</tr>"+
				"<tr>"+
					 "<td colspan='2' align='center'>"+
						"<input type='submit' value='수정'>"+
						"<input type='button' value='다시입력' onclick='f()'>"+
					 "</td>"+
				"</tr>"+

				"</table>"+
				"<hr width='600' size='2' color='gray' noshade>"+
				"</form>"+
				"</body>"+
				"</html>"
				);
		}catch(SQLException se){
		}finally{
			try{
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(con != null) pool.returnConnection(con);
			}catch(SQLException se){
			}
		}
	}
}