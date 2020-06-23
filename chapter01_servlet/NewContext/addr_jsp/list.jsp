<%@page contentType="text/html;charset=utf-8" import="java.sql.*"%>

<%!
	String url = "jdbc:oracle:thin:@127.0.0.1:1521:JAVA";
	String user = "servlet";
	String pwd = "java";
	Connection con;
	Statement stmt;
	String sql = "select * from ADDRESS order by SEQ desc";
	
	public void jspInit(){
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection(url, user, pwd);
			stmt = con.createStatement();
		}catch(ClassNotFoundException cnfe){
		}catch(SQLException se){
		}
	}
	public void jspDestroy(){
		try{
			if(stmt != null) stmt.close();
			if(con != null) con.close();
		}catch(SQLException se){
		}
	}
%>
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
Address List JSP
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
<%
	ResultSet rs = null;
	try{
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
		}catch(SQLException se){
		}
	}
%>
</table>
</center>