<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
	<title>Home</title>
</head>
<body>
<h1> LIST FILE </h1>

<div>UPLOAD DIR : ${rootDir}</div>
<div>
	<c:forEach items='${rootDir.listFiles()}' var = 'subdir'>
		<hr >
		FOLDER : ${subdir.getPath()}		
		<c:forEach items='${subdir.listFiles()}' var='file'>
				<a href="javascript:void(0)">${file.getName()}</a>
		</c:forEach>
		<hr >
	</c:forEach>
</div>


</body>
</html>