package rhie.sv.board;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;

public class UpdateServlet extends HttpServlet{
	String url = "jdbc:oracle:thin:@127.0.0.1:1521:JAVA";
	String user = "servlet";
	String pwd = "java";
	Connection con;
	PreparedStatement pstmt;
	String sql = "select * from BOARD where SEQ=?";
	
	public UpdateServlet(){
		System.out.println("check UpdateServlet Constructor");
	}
	
	@Override
	public void init()throws ServletException{
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection(url, user, pwd);
			pstmt = con.prepareStatement(sql);
		}catch(ClassNotFoundException cnfe){
		}catch(SQLException se){
		}
	}
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res)throws IOException, ServletException{
		req.setCharacterEncoding("utf-8");
		res.setContentType("text/html;charset=utf-8");
		PrintWriter pw = res.getWriter();
		String seqStr = req.getParameter("seq");
		if(seqStr != null) seqStr = seqStr.trim();
		int seq = Integer.parseInt(seqStr);
		
		ResultSet rs = null;
		String writer = null;
		String email = null;
		String subject = null;
		String content = null;
		try{
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
					"<title> 게시글 수정 </title>"+
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
				"<h3> 게시글 수정 </h3>"+
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
				if(pw != null) pw.close();
			}catch(SQLException se){
			}
		}
	}
	@Override 
	public void destroy(){
		try{
			if(pstmt != null) pstmt.close();
			if(con != null) con.close();
		}catch(SQLException se){
		}
	}
}