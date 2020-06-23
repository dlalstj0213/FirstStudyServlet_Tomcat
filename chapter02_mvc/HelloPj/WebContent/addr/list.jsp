<%@page contentType="text/html;charset=utf-8" import="java.sql.*, rhie.db.ConnectionPoolBean"%>
<jsp:useBean id="pool" class="rhie.db.ConnectionPoolBean" scope="application"/>


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
<center>
<h1>
Address List JSP with Pool
</h1>
<a href='input.html'>입력</a><br/>
<table border='1' cellpadding='7' cellspacing='2' width='50%'>
	<tr>
		<th>번호</th>
		<th>이름</th>
		<th>주소</th>
		<th>날짜</th>
		<th>삭제</th>
	</tr>
<%!
	String sql = "select * from ADDRESS order by SEQ desc";
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
				int seq = rs.getInt(1);
				String name = rs.getString(2);
				String addr = rs.getString(3);
				Date rdate = rs.getDate(4);
%>
<tr>
<td align='center'><%=seq%></td>		
<td><%=name%></td>				
<td><%=addr%></td>			
<td><%=rdate%></td>				
<td align='center'><a href='del.jsp?seq=<%=seq%>'>삭제</a></td>	
</tr>					
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
</table>
</center>