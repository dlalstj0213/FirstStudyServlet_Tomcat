<%@page contentType="text/html;charset=utf-8" import="java.sql.*"%>
<%!
	String url = "jdbc:oracle:thin:@127.0.0.1:1521:JAVA";
	String user = "servlet";
	String pwd = "java";
	Connection con;
	PreparedStatement pstmt;
	String sql = "delete from ADDRESS where SEQ=?";

	public void jspInit(){
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection(url, user, pwd);
			pstmt = con.prepareStatement(sql);
		}catch(ClassNotFoundException cnfe){
		}catch(SQLException se){
		}
	}
	public void jspDestroy(){
		try{
			if(pstmt != null) pstmt.close();
			if(con != null) con.close();
		}catch(SQLException se){
		}
	}
%>

<%
		String seqStr = request.getParameter("seq");
		if(seqStr != null) seqStr = seqStr.trim();
		int seq = Integer.parseInt(seqStr);
		
		try{
			pstmt.setInt(1, seq);
			pstmt.executeUpdate();
		}catch(SQLException se){
		}
		response.sendRedirect("list.jsp");
%>
