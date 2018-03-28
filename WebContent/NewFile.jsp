<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<bean id="multipartResolver" class="org.springframework.web.multipart.commons.CommonsMultipartResolver" />
<title>Insert title here</title>
</head>
<body>
<form action="Search" method="post" enctype="multipart/form-data">
<input type="file" name="pic" >图片</input>
<button type ="submit">search</button>
</form>
<hr>
<form action="Register" method="post" enctype="multipart/form-data">
<input type="file" name="pic1">图片</input>
<input type="file" name="pic2">图片</input>
<input type="file" name="pic3">图片</input>
<input type="text" name="name">姓名</input>
<button type ="submit">register</button>
</form>
</body>
</html>