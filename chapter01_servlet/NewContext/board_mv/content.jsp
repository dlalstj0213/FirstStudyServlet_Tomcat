<%@page contentType="text/html;charset=utf-8"
	import="java.sql.*, java.util.ArrayList, board.mv.model.BoardDTO, rhie.db.ConnectionPoolBean"%>
<jsp:useBean id="pool" class="rhie.db.ConnectionPoolBean" scope="application" />
<jsp:useBean id="boardDao" class="board.mv.model.BoardDAO" scope="application" />
<jsp:useBean id="boardDto" class="board.mv.model.BoardDTO"/>

<jsp:setProperty name="boardDto" property="*"/>
<%
	boardDao.setPool(pool);
	ArrayList<BoardDTO> list = boardDao.showContent(boardDto);
%>
<!DOCTYPE html>
<style>
table, th, td {
border: 1px solid black;
border-collapse: collapse;
th, td {
padding: 5px;
}
a { text-decoration:none }
</style>
<meta charset='utf-8'>
<center>
<font color='gray' size='4' face='휴먼편지체'>
<hr width='600' size='2' color='gray' noshade>
<h3>게시글 (MV)</h3>
<font color='gray' size='4' face='휴먼편지체'>
<a href='write.html'>글쓰기</a>
</font>
<hr width='600' size='2' color='gray' noshade>
</font>
<table border='2' width='600' align='center' noshade>

<%
	if(list.size() != 0){
		for(BoardDTO dto : list){
%>
<tr>
<td width='20%' align='center'>No</td>
<td><%=dto.getSeq()%></td>
</tr>

<tr>
<td width='20%' align='center'>Writer</td>
<td><%=dto.getWriter()%></td>
</tr>

<tr>
<td align='center'>E-mail</td>
<td><%=dto.getEmail()%></td>
</tr>

<tr>
<td align='center'>Subject</td>
<td><%=dto.getSubject()%></td>
</tr>

<tr>
<td align='center'>Contents</td>
<td><%=dto.getContent()%></td>
</tr>
</table>
<hr width='600' size='2' color='gray' noshade>
<font color='gray' size='4' face='휴먼편지체'>
<a href='update_page.jsp?seq=<%=dto.getSeq()%>'>수정</a>
 &nbsp;&nbsp;|
<a href='del.jsp?seq=<%=dto.getSeq()%>'>삭제</a>
 &nbsp;&nbsp;| 
<a href='b_list.jsp'>목록</a>
</font>
<hr width='600' size='2' color='gray' noshade>
</center>
<%
		}
	}else{
	}
%>
