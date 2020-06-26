<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
       <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
       
<script>
	if(${empty sessionScope.loginUser}){
		alert("회원 서비스 입니다. 로그인을 먼저 해주세요.");
		location.href="../index.do";
	}
</script>