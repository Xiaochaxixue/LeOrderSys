<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form name="form1" method="POST" action="ModifyFileServlet?action=guFile&id=${id}" ENCTYPE="multipart/form-data">  
	    请选择你要上传的文件：<input name="qfile" type="file" id="qfile" value="请选择文件..."   style="margin-top:5px;height:30px;" ><br>
	    <%-- <input id="id" name="id" type= "hidden" value="${id}">  --%>
	    <input type="submit" value="提交">   
	</form>
</body>	
</html>
<script type="text/javascript">
$(document).ready(function(){
	$("#gujian").addClass("active");
});	
</script>