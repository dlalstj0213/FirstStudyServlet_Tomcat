<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" 
	import="page.mvc.vo.ListResult, mvc.domain.Board, java.util.ArrayList"%>
    
<!DOCTYPE html>
<html>
<head>
	<title> Spring Board </title>
	<meta charset="utf-8">
	<style>
		a{text-decoration:none}
	</style>
</head>
<body>
<center>
<font color='gray' size='4' face='휴먼편지체'>
<hr width='600' size='2' color='gray' noshade>
<h3> Spring Board</h3>
<font color='gray' size='4' face='휴먼편지체'>
<a href='../'>인덱스</a>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
<a href='../board_mvc/board.do?m=write'>글쓰기</a><br/>
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
	ListResult result = (ListResult)request.getAttribute("result");
	int currentPage = result.getCurrentPage();
	long totalCount = result.getTotalCount();
	long totalPageCount = result.getTotalPageCount();
	ArrayList<Board> list = (ArrayList<Board>)result.getList();
	if(list != null && list.size() != 0){
		for(Board board : list){
%>
<%--여기서 부터 반복문 돌릴 필요 있음 --%>

    <TR align='center' noshade>
		<TD><%=board.getSeq()%></TD>
		<TD><%=board.getWriter()%></TD>
		<TD><%=board.getEmail()%></TD>
	    <TD>
	      <a href="../board_mvc/board.do?m=delete&seq=<%=board.getSeq() %>">
		    <%=board.getSubject()%>
		  </a>
		</TD>
		<TD><%=board.getRdate()%></TD>
	   </TR> 
	   
				<%
					}
					} else {
				%>
				<tr>
					<td align='center' colspan="5">데이터가 없음</td>
				</tr>
				<%
					}
				%>
<%-- end --%>

</TABLE>
<hr width='600' size='2' color='gray' noshade>
<font color='gray' size='3' face='휴먼편지체'>

    총 페이지 갯수 : <%=totalPageCount%>
    
    &nbsp;&nbsp;&nbsp;
    
    <%-- 페이지 넘버 시작(반복문으로 입력 요망) --%>
<%
	for(int i=1; i<=totalPageCount; i++){
		if(i==currentPage){
%>
        <a href="page.do?cp=<%=i%>">
                	<strong><%=i%></strong>
    	</a>&nbsp;
<%
		} else {
%>
        <a href="page.do?cp=<%=i%>">
                	<%=i%>
    	</a>&nbsp;
<% 
		}
	}
%>
    <%-- end --%>

    총 게시글 갯수 : <%=totalCount%> <%-- 총 게시글 갯수 --%>
    
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
       페이지 싸이즈 : 
    <select id="psId" name="ps" onchange="f(this)">
    	<%
    		if(result.getPageSize() == 3){
    		%>
    		   <option value="3" selected>3</option>
		       <option value="5">5</option>
		       <option value="10">10</option>
    		<%	
    		}else if(result.getPageSize() == 5){
    			%>
    		   <option value="3">3</option>
		       <option value="5" selected>5</option>
		       <option value="10">10</option>
    			<%
    		}else{ //10
    			%>
    		   <option value="3">3</option>
		       <option value="5">5</option>
		       <option value="10" selected>10</option>
    			<%
    		}
    	%>
    </select>
    
    <script language="javascript">
    <%--
    window.onload = function(){
    	var sel = document.getElementById("psId");
    	for(var i=0; i<sel.length; i++){
    		if(sel[i].value==<%=ps%>){
    			
    		}
    	}
    }--%>
       function f(select){
           //var el = document.getElementById("psId");
           var ps = select.value;
           //alert("ps : " + ps);
           location.href="page.do?&ps="+ps;
       }
    </script>
    
</font>
<hr width='600' size='2' color='gray' noshade>
    
</center>
</body>
</html>