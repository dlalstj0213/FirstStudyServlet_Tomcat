<%@page contentType="text/html;charset=utf-8"%>


<%-- 방법1 --%>
<%-- 
<%
	boolean flag = (Boolean)request.getAttribute("flag");
%>

<script language='javascript'>
	if(<%=flag%>){
		alert("입력 성공 by MVC 1");
	}else{
		alert("입력 실패 by MVC 1");
	}
location.href='addr.do';
</script>
--%>

<%-- 방법2 --%>

<script language='javascript'>
	if(${flag}){
		alert("입력 성공 by MVC 2");
	}else{
		alert("입력 실패 by MVC 2");
	}
location.href='addr.do';
</script>