<%@ page contentType="text/html;charset=utf-8"%><%
	String n = request.getParameter("n");

	if(n != null){
		n = n.trim();

		if(n.contains("김")){
            out.write("<font color='green'>김유민, 김형섭, 김희주</font>");
		}else if(n.contains("이")){
			out.write("<font color='green'>이경빈, 이민서, 이수빈, 이정훈, 이준성, 이현호</font>");
		}else if(n.contains("희")){
			out.write("<font color='green'>김희주, 서지희, 윤희영</font>");
		}else{
			out.write("");
		}
	}else{
		out.write("");
	}
%>

