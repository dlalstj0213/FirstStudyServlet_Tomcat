<%@page contentType="text/html;charset=utf-8"
	import="java.sql.*, rhie.db.ConnectionPoolBean"%>
<jsp:useBean id="pool" class="rhie.db.ConnectionPoolBean" scope="application" />
<jsp:useBean id="boardDao" class="board.mv.model.BoardDAO" scope="application" />
<jsp:useBean id="boardDto" class="board.mv.model.BoardDTO"/>

<jsp:setProperty name="boardDto" property="*"/>
<!-- property 이름과 param의 이름이 같을 시는 어차피 이름이 똑같기 때문에 "*"으로 자동화 시킨다. -->
<!--  단 property 와 param의 이름을 꼭 같아야 한다. -->

<script language='javascript'>
	if(<%=boardDao.insert(boardDto)%>){
		alert('작성 성공');
	}else{
		alert('작성 실패');
	}
location.href='b_list.jsp';
</script>

