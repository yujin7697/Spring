<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>MyLogin</h1>

<form action="${pageContext.request.contextPath}/myLogin" method="post">
	ID : <input name="username" /><br/>
	PW : <input name="password" type="password"/><br/>
	<input type="submit" />
</form>
<div>${msg }</div>
<div>${param.error }</div>
</body>
</html>