<%@page contentType="text/html;charset=utf-8" import="java.sql.*, rhie.db.ConnectionPoolBean"%>
<jsp:useBean id="pool" class="rhie.db.ConnectionPoolBean" scope="application"/>

<!DOCTYPE html>
<html>
<head>
<title> 게시판(with Pool) </title>
<meta charset='utf-8'>
<style>
table, th, td {
border: 1px solid black;
border-collapse: collapse;
}
th, td {
padding: 5px;
}
a { text-decoration:none }
</style>
</head>
<body>
<center>
<font color='gray' size='4' face='휴먼편지체'>
<hr width='600' size='2' color='gray' noshade>
<h3> 게시판 (with Pool)</h3>
<font color='gray' size='4' face='휴먼편지체'>
<a href='../'>인덱스</a>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<a href='write.html'>글쓰기</a><br/>
</font>
<hr width='600' size='2' color='gray' noshade>
<TABLE border='2' width='600' align='center' noshade>
<TR size='2' align='center' noshade bgcolor='AliceBlue'>
<th bgcolor='AliceBlue'>no</th>
<th color='gray'>writer</th>
<th color='gray'>e-mail</th>
<th color='gray'>subject</th>
<th color='gray'>date</th>
</TR>


<%!
	String sql = "select * from BOARD order by SEQ desc";
%>

<%
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		try{
			con = pool.getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			while(rs.next()){
				int seq = rs.getInt("SEQ");
				String writer = rs.getString("WRITER");
				String email = rs.getString("EMAIL");
				String subject = rs.getString("SUBJECT");
				java.sql.Date rdate = rs.getDate("RDATE");
%>	
				<TR align='center' noshade>
				<TD><%=seq%></TD>
				<TD><%=writer%></TD>
				<TD><%=email%></TD>
				<TD>
				<a href='content.jsp?seq=<%=seq%>'>
				<%=subject%>
				</a>
				</TD>
				<TD><%=rdate%></TD>
				</TR>
<%
			}
		}catch(SQLException se){
		}finally{
			try{
				if(rs != null) rs.close();
				if(stmt != null) stmt.close();
				if(con != null) pool.returnConnection(con);
			}catch(SQLException se){
			}
		}		
%>
			</TABLE>
			</center>
			</body>
			</html>