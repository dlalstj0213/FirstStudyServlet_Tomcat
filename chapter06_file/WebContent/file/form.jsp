<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
   <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>File Upload Form</title>
</head>
<body>
    <center>
        <h2>File Upload Form</h2>
        <form name="f" method="post" action="file.do?m=upload" enctype="multipart/form-data">
            유져: <input type="text" name="writer" size="32"><br/>
            파일: <input type="file" name="fname"><br/><br/>
            <input type="submit" value="전송">
        </form>
    </center>
</body>
</html>