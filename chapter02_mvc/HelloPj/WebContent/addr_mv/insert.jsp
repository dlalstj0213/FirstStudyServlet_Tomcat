<%@page contentType="text/html;charset=utf-8"
	import="java.sql.*, rhie.db.ConnectionPoolBean"%>
<jsp:useBean id="pool" class="rhie.db.ConnectionPoolBean"
	scope="application" />
<jsp:useBean id="addrDao" class="addr.mv.model.AddrDAO"
	scope="application" />
<jsp:useBean id="addrDto" class="addr.mv.model.AddrDTO"/>
<!--
<jsp:setProperty name="addrDto" property="name" param="name"/>
<jsp:setProperty name="addrDto" property="addr" param="addr"/>
-->
<jsp:setProperty name="addrDto" property="*"/>
<!-- property 이름과 param의 이름이 같을 시는 어차피 이름이 똑같기 때문에 "*"으로 자동화 시킨다. -->
<!--  단 property 와 param의 이름을 꼭 같아야 한다. -->	

<!-- 방법 1
<!--%addrDao.setPool(pool);
	boolean flag = addrDao.insert(addrDto);
%>
<script language='javascript'>
	if(< %=flag%>){
		alert("입력 성공 by MV");
	}else{
		alert("입력 실패 by MV");
	}
location.href='list.jsp';
</script>
-->

<!-- 방법 2 -->
<script language='javascript'>
	if(<%=addrDao.insert(addrDto)%>){
		alert("입력 성공 by MV");
	}else{
		alert("입력 실패 by MV");
	}
location.href='list.jsp';
</script>