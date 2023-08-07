<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>form PAGE</h1>
	<form action="${pageContext.request.contextPath}/memo/post">
		<input name="id" placeholder="id" /><br /> 
		<input name="text" placeholder="text" /><br /> 
		<input name="writer" placeholder="writer" /><br />
		<input type="submit" />
	</form>
</body>
</html>