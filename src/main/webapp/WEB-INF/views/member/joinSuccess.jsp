<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="./jsp_header.jsp"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>유저 등록 완료 화면</title>
<link rel="stylesheet" type="text/css" href="resources/css/style.css">
</head>

<body>
	<div align="center" class="body">
		<h2>유저 등록 완료 화면</h2>
		<br> <br> <b><font color="red">${message}</font></b><br>

		<table border='1' width='600' cellpadding='0' cellspacing='0'>
			<tr height="40px">
				<td bgcolor='cccccc' width='100' align='center'>
					<font size='2'>유저ID</font>
				</td>
				<td>${user.userid}</td>
			</tr>

			<tr height="40px">
				<td bgcolor='cccccc' width='100' align='center'>이름:</td>
				<td>${user.name}</td>
			</tr>
		
<%-- 			<tr height="40px">
				<td bgcolor='cccccc' width='100' align='center'>전화번호:</td>
				<td>${user.lphone}<span>-</span>${user.mphone }<span>-</span>${user.rphone }</td>
			</tr> --%>
 			<tr height="40px">
				<td bgcolor='cccccc' width='100' align='center'>E-MAIL:</td>
				<td>${user.email}</td>
			</tr>
<%--		<tr height="40px">
				<td bgcolor='cccccc' width='100' align='center'>우편번호:</td>
				<td>${user.zip}</td>
			</tr>
			<tr height="40px">
				<td bgcolor='cccccc' width='100' align='center'>주소:</td>
				<td>${user.addr1}</td>
			</tr>
			<tr height="40px">
				<td bgcolor='cccccc' width='100' align='center'>상세주소:</td>
				<td>${user.addr2}</td>
			</tr> --%>
		</table>
		<br>
		<ul id="ullog">
			<li id="lilogb">
				<a href="/dressfi/login.do">로그인</a>
			</li>
		</ul>
	</div>
</body>
</html>