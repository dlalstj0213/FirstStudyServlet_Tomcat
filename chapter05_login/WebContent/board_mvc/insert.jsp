<%@page contentType="text/html;charset=utf-8"%>

<%
	boolean flag = (Boolean)request.getAttribute("flag");
%>
<script language='javascript'>
	if(<%=flag%>){
		alert('작성 성공');
	}else{
		alert('작성 실패');
	}
location.href='board.do';
</script>

