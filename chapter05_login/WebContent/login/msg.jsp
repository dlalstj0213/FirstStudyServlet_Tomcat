<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
String result = (String)request.getAttribute("result");
%>

<script language='javascript'>
	if("<%=result%>" == "login-success"){
		alert("로그인 성공");
		location.href='../index.do';
	}else if("<%=result%>" == "login-fail") {
		alert("로그인 실패");
		location.href='login.do?m=form';
	}else if("<%=result%>" == "login-fail-exist") {
		alert("해당 아이디는 존재하지 않습니다.");
		location.href='login.do?m=form';		
	}else if("<%=result%>" == "login-fail-password"){
		alert("비밀번호가 일치하지 않습니다");
		location.href='login.do?m=form';
	}else if("<%=result%>" == "user-logined"){
		alert("이미 로그인 되어 있습니다");
		location.href='login.do?m=form';
	}else{
	}
</script>
