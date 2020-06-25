<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Hello Project</title>
	<Style>
		a{text-decoration:none;font-size:12pt;}
	</Style>
</head>
<body style="text-align:center">
	<h2>Hello Project Index</h2>
	<br/>
	<a href="addr/list.jsp">주소록</a>(JSP)<br/>
	<a href="addr_mv/list.jsp">주소록</a>(Model1)<br/>
	<a href="addr_mvc/addr.do">주소록</a>(Model2)<br/>
	<a href="board_mvc/board.do">게시판</a>(Model2)<br/>
	<a href="paging/page.do?m=list&cp=1&ps=5">리스트</a>(Paging 기법)<br/>
	<a href="paging/page.do?m=list&cp=1&ps=5">리스트</a>(Paging JSTL and EL)<br/>
</body>
</html>