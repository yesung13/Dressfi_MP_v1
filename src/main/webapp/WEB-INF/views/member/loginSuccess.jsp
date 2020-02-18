<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
	<title>로그인화면</title>
</head>
<% System.out.println("LoginSuccess.jsp ==> 로그인 성공 화면:"); %>
<body>
	<div align="center" class="body">
		<h2>로그인화면</h2>
		<br>
		환영합니다,${loginUser.name}씨！
		<br><br><br>
	<a href="/dressfi/edit.do?userId=${loginUser.id}">[회원정보 수정]</a> 
	<br><br>
	<a href="/dressfi/logout.do">[logout]</a> 
	</div>	
	
</body>
</html>