<%@page contentType="text/html;charset=utf-8"
	import="mvc.domain.Board"%>

		<!DOCTYPE html>
			<html>
				<head>
					<title> 게시글 수정(MVC)</title>
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
				<h3> 게시글 수정 (with MVC) </h3>
				</font>
				<font color='gray' size='4' face='휴먼편지체'>
				<a href='board.do'>목록</a>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<a href='board.do?m=write'>글쓰기</a><br/>
				</font>
				<hr width='600' size='2' color='gray' noshade>
				</center>

				<form name='input' method='post' action='board.do?m=update'>
				
<%
	Board board = (Board)request.getAttribute("board");
	if(board != null){
%>
			<input type='hidden' name='seq' value='<%=board.getSeq()%>'>
				<table border='0' width='600' align='center' cellpadding='3' cellspacing='1' bordercolor='gray'>
				<tr>
				   <td width='20%' align='center' >WRITER</td>
				   <td>
					  <input name='writer' readonly value='<%=board.getWriter()%>' size='80'/>
				   </td>
				</tr>

				<tr>
					<td align='center'>EMAIL</td>
					<td><input name='email' value='<%=board.getEmail()%>' size='80'/></td>
				</tr>

				<tr>
					<td align='center'>SUBJECT</td>
					<td><input name='subject' value='<%=board.getSubject()%>' size='80'/></td>
				</tr>
							
				<tr>
					<td align='center'>CONTENT</td>
					<td><textarea id='ta' name='content' rows='15' cols='60'><%=board.getContent()%></textarea></td>
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
	}else{
	}
%>