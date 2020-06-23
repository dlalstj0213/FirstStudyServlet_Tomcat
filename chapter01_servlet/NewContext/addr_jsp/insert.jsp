<%@page contentType="text/html;charset=utf-8" import="java.sql.*"%>

<%!
	String url = "jdbc:oracle:thin:@127.0.0.1:1521:JAVA";
	String usr = "servlet";
	String pwd = "java";
	Connection con;
	PreparedStatement pstmt;
	String sql="insert into ADDRESS values(ADDRESS_SEQ.nextval, ?, ?, SYSDATE)";

	@Override 
	public void jspInit(){
		try{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection(url, usr, pwd);
			pstmt = con.prepareStatement(sql);
		}catch(ClassNotFoundException cnfe){
		}catch(SQLException se){}
	}
	@Override
	public void jspDestroy(){
		try{
			if(pstmt != null) pstmt.close();
			if(con != null) con.close();
		}catch(SQLException se){}
	}
%>

<%
	request.setCharacterEncoding("utf-8");
	String name = request.getParameter("name");
	String addr = request.getParameter("addr");
   
	out.println("<script language='javascript'>");
	try{
		pstmt.setString(1, name);
		pstmt.setString(2, addr);
		int i = pstmt.executeUpdate();
		if(i>0){
			out.println("alert('입력 성공(with JSP)')");
		}else{
			out.println("alert('입력 실패 (with JSP)')");
		}
	}catch(SQLException se){}

		out.println("location.href='list.jsp'");
	out.println("</script>");
%>