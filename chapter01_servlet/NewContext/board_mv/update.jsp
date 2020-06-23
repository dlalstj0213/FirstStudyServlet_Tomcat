<%@page contentType="text/html;charset=utf-8"
	import="java.sql.*, java.util.ArrayList, board.mv.model.BoardDTO, rhie.db.ConnectionPoolBean"%>
<jsp:useBean id="pool" class="rhie.db.ConnectionPoolBean" scope="application" />
<jsp:useBean id="boardDao" class="board.mv.model.BoardDAO" scope="application" />
<jsp:useBean id="boardDto" class="board.mv.model.BoardDTO"/>

<jsp:setProperty name="boardDto" property="*"/>

<script language='javascript'>
	if(<%=boardDao.updatePost(boardDto)%>){
		alert("수정 성공 by MV");
	}else{
		alert("수정 실패 by MV");
	}
location.href='b_list.jsp';
</script>