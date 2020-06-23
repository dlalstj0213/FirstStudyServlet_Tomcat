<%@page contentType="text/html;charset=utf-8" import="java.sql.*, rhie.db.ConnectionPoolBean"%>
<jsp:useBean id="pool" class="rhie.db.ConnectionPoolBean" scope="application"/>
<jsp:useBean id="addrDao" class="addr.mv.model.AddrDAO" scope="application"/>

<%
		String seqStr = request.getParameter("seq");
		if(seqStr != null) seqStr = seqStr.trim();
		int seq = Integer.parseInt(seqStr);
		
		addrDao.del(seq);
		
		response.sendRedirect("list.jsp");		
%>
