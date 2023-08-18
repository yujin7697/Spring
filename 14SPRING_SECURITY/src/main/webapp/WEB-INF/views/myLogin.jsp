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
	<div>
		<input type="checkbox" name="remember-me" id="remember-me" />
		<label for="remember-me">로그인 상태 유지</label>
	</div>
	<input type="submit" />
</form>
<a href="${pageContext.request.contextPath}/join">회원가입</a>
<div>${msg }</div>
<div>${param.error}</div>
<div>${param.msg}</div>
</body>
</html>