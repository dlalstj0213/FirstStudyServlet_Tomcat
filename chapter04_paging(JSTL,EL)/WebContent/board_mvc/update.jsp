<%@page contentType="text/html;charset=utf-8"%>

<script language='javascript'>
	if(${flag}){
		alert("수정 성공 by MV");
	}else{
		alert("수정 실패 by MV");
	}
location.href='board.do';
</script>