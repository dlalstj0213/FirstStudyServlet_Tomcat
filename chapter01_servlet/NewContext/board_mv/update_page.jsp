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
			<html>
				<head>
					<title> 게시글 수정(MV)</title>
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
					<script src='https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js'></script>
					<script>
						function f(){
							input.email.value = '';
							input.subject.value = '';
							$('#ta').text('');
							
							input.email.focus();
						}
					</script>
				</head>
				<body>
				<center>
				<font color='gray' size='4' face='휴먼편지체'>
				<hr width='600' size='2' color='gray' noshade>
				<h3> 게시글 수정 (with MV) </h3>
				</font>
				<font color='gray' size='4' face='휴먼편지체'>
				<a href='b_list.jsp'>목록</a>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<a href='write.html'>글쓰기</a><br/>
				</font>
				<hr width='600' size='2' color='gray' noshade>
				</center>

				<form name='input' method='post' action='update.jsp'>
				
<%
	if(list.size() != 0){
		for(BoardDTO dto : list){
%>
			<input type='hidden' name='seq' value='<%=dto.getSeq()%>'>
				<table border='0' width='600' align='center' cellpadding='3' cellspacing='1' bordercolor='gray'>
				<tr>
				   <td width='20%' align='center' >WRITER</td>
				   <td>
					  <input name='writer' readonly value='<%=dto.getWriter()%>' size='80'/>
				   </td>
				</tr>

				<tr>
					<td align='center'>EMAIL</td>
					<td><input name='email' value='<%=dto.getEmail()%>' size='80'/></td>
				</tr>

				<tr>
					<td align='center'>SUBJECT</td>
					<td><input name='subject' value='<%=dto.getSubject()%>' size='80'/></td>
				</tr>
							
				<tr>
					<td align='center'>CONTENT</td>
					<td><textarea id='ta' name='content' rows='15' cols='60'><%=dto.getContent()%></textarea></td>
				</tr>
				<tr>
					 <td colspan='2' align='center'>
						<input type='submit' value='수정'>
						<input type='button' value='다시입력' onclick='f()'>
					 </td>
				</tr>

				
				</table>
				<hr width='600' size='2' color='gray' noshade>
				</form>
				</body>
				</html>
<%
		}
	}else{
	}
%>