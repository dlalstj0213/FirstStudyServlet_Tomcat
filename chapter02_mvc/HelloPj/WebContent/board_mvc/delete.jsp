<%@page contentType="text/html;charset=utf-8"%>

<%
	boolean flag = (Boolean)request.getAttribute("flag");
%>

<script language='javascript'>
	if(<%=flag%>){
		alert("삭제 성공 by MVC");
	}else{
		alert("삭제 실패 by MVC");
	}
location.href='board.do';
</script>