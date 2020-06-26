<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
   <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
   
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
	<c:if test="${!empty sessionScope.loginUser}">
		<h3>${sessionScope.loginUser.name}</h3>
	</c:if>
	<br/>
	<a href="paging/page.do?m=list&cp=1&ps=5">리스트</a>(Paging 기법)<br/>
	<a href="paging/page.do?m=list&cp=1&ps=5">리스트</a>(Paging JSTL and EL : 회원전용)<br/>
	<a href="file/file.do?m=form">파일폼</a><br/>
	<a href="file/file.do?m=list">파일 리스트</a><br/>
	<br/><br/>
	
	<c:if test="${empty sessionScope.loginUser}">
	<a href="login/login.do?m=form">로그인</a><br/>
	</c:if>
	<c:if test="${!empty sessionScope.loginUser}">
	<a href="login/login.do?m=out">로그아웃</a><br/>
	</c:if>
</body>
</html>