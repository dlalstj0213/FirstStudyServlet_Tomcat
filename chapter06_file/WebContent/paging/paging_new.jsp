<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" 
	import="page.mvc.vo.ListResult, mvc.domain.Board, java.util.ArrayList"%>
   <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../login/login_check_module.jsp"/><%--해당 페이지 소스의 결과값을 포함(가지고옴)--%>
<%--<%@ include file="../login/login_check_module.jsp"%> --%>
<%-- 해당페이지의 소스포함시키지만 현재 페이지와 충돌 날 경우도 있음(비추) --%>

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
<h3> Board (MVC paging JSTL+EL)_</h3>
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

<c:if test="${empty result.list}">
			<tr>
				<td align='center' colspan="5">데이터가 없음</td>
			</tr>
</c:if>


<%--여기서 부터 반복문 돌릴 필요 있음 by jstl and el--%>
<c:forEach items="${result.list}" var="board">
    <TR align='center' noshade>
		<TD>${board.seq}</TD>
		<TD>${board.writer}</TD>
		<TD>${board.email}</TD>
	    <TD>
	      <a href="../board_mvc/board.do?m=delete&seq=${board.seq}">
		   ${board.subject}
		  </a>
		</TD>
		<TD>${board.rdate}</TD>
	   </TR> 
</c:forEach>   
<%-- end --%>

</TABLE>
<hr width='600' size='2' color='gray' noshade>
<font color='gray' size='3' face='휴먼편지체'>

   (총 페이지 갯수 : ${result.totalPageCount}) 
    
    &nbsp;&nbsp;&nbsp;
    
    <%-- 페이지 넘버 시작(반복문으로 입력 요망) --%>
<c:forEach begin="1" end="${result.totalPageCount}" var="i">
        <a href="page.do?cp=${i}">
	<c:choose>
		<c:when test="${i==result.currentPage}">
                	<strong>${i}</strong>
        </c:when>
        <c:otherwise>${i}</c:otherwise>
    </c:choose>
    	</a>&nbsp;
</c:forEach>
    <%-- end --%>

    (총 게시글 갯수 : ${result.totalCount}) <%-- 총 게시글 갯수 --%>
    
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
       페이지 싸이즈 : 
    <select id="psId" name="ps" onchange="f(this)">
	<c:choose>
		<c:when test="${result.pageSize == 3}">
    		   <option value="3" selected>3</option>
		       <option value="5">5</option>
		       <option value="10">10</option>
		 </c:when>
		 <c:when test="${result.pageSize == 5}">
    		   <option value="3">3</option>
		       <option value="5" selected>5</option>
		       <option value="10">10</option>
    	 </c:when>
    	 <c:otherwise>
    		   <option value="3">3</option>
		       <option value="5">5</option>
		       <option value="10" selected>10</option>
    	 </c:otherwise>
    	</c:choose>
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