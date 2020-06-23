<%@page contentType="text/html;charset=utf-8" 
        import="rhie.db.ConnectionPoolBean, java.util.*, board.mv.model.BoardDTO"%>
<jsp:useBean id="pool" class="rhie.db.ConnectionPoolBean" scope="application"/>
<jsp:useBean id="boardDao" class="board.mv.model.BoardDAO" scope="application"/>

<!DOCTYPE html>
<html>
<head>
<title> 게시판(with MV) </title>
<meta charset='utf-8'>
<style>
table, th, td {
border: 1px solid black;
border-collapse: collapse;
}
th, td {
padding: 5px;
}
a { text-decoration:none }
</style>
</head>
<body>
<center>
<font color='gray' size='4' face='휴먼편지체'>
<hr width='600' size='2' color='gray' noshade>
<h3> 게시판 (with MV)</h3>
<font color='gray' size='4' face='휴먼편지체'>
<a href='../'>인덱스</a>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<a href='write.html'>글쓰기</a><br/>
</font>
<hr width='600' size='2' color='gray' noshade>
<TABLE border='2' width='600' align='center' noshade>
<TR size='2' align='center' noshade bgcolor='AliceBlue'>
<th bgcolor='AliceBlue'>no</th>
<th color='gray'>writer</th>
<th color='gray'>e-mail</th>
<th color='gray'>subject</th>
<th color='gray'>date</th>
</TR>

<%
	boardDao.setPool(pool);
	ArrayList<BoardDTO> list = boardDao.list();
	if(list.size() != 0){
		for(BoardDTO dto : list){
%>	
				<TR align='center' noshade>
				<TD><%=dto.getSeq()%></TD>
				<TD><%=dto.getWriter()%></TD>
				<TD><%=dto.getEmail()%></TD>
				<TD>
				<a href='content.jsp?seq=<%=dto.getSeq()%>'>
				<%=dto.getSubject()%>
				</a>
				</TD>
				<TD><%=dto.getRdate()%></TD>
				</TR>
<%
		}
	}else{
%>
	<tr>
		<td align='center' colspan="5">데이터가 없음</td>
	</tr>
<%
	}
%>
			</TABLE>
			</center>
			</body>
			</html>