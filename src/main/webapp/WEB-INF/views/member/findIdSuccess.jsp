<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"> -->
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>DressFi</title>
<style>
#hr {
	display: block;
	margin-top: 0.5em;
	margin-left: auto;
	margin-right: auto;
	border-style: inset;
	border-width: 1px;
}

.notuser a {
	font-stretch: expanded;
	font-size: 1.0em;
}

.notuser a { /*클릭하지 않은 링크*/
	color: black;
	text-decoration: none;
}

.notuser a:hover { /*링크를 클릭하려고 마우스를 가져갔을때*/
	color: gray;
	text-decoration: none; /*밑줄 없음*/
}

.form-group .row .col-lg-8 {
	font-size: 0.813em;
}

a img {
	width: 1.5em;
	height: 1.5em;
}

a span {
	font-stretch: expanded;
	font-size: 1.5em;
}
</style>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/profile.css">
<!-- 구글 icon -->
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.0/css/all.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
<%-- <link href="${pageContext.request.contextPath}/resources/css/main.css"
	rel="stylesheet" type="text/css"> --%>
</head>
<body class="bg-light">
	<!--<div align="center" class="body">
		<h2>아이디찾기</h2>
		<br> 아이디는 ${userId}입니다. <br> <br> <br>
		<input type="button" class="submitBt" onclick="location.href='login.do'" value="돌아가기" />
	</div> -->
	<header class="mb-2 bg-white">
		<div class="container p-auto">
			<div class="row navbar navbar-light">
				<a class="col-lg-6 offset-lg-3 navbar-brand" href="/dressfi/">
					<img src="<%=request.getContextPath()%>/resources/images/svg/clothing-hanger.svg" class="d-inline-block align-baseline" alt="logo">
					<span class="d-inline-block align-bottom">DressFi</span>
				</a>
			</div>
		</div>
	</header>
	<section>
		<div class="container">
			<div class="row">
				<div class="col-lg-6 offset-lg-3">

					<div class="form-group needs-validation mt-3">
						<h4 class="font-weight-bolder">아이디 찾기</h4>
					</div>

					<div class="form-inline mx-4 my-4">
						<h6>회원님의 아이디는</h6>
						<h5 class="text-body font-weight-bold ml-2">${userId}</h5>
					</div>
					<div class="form-group">
						<input type="submit" class="btn btn-secondary w-100" value="로그인하기" onclick="location.href='login.do'" />

					</div>
				</div>
			</div>
		</div>
	</section>
	<footer class="footer bg-light mt-3">
		<div class="container p-auto">
			<div class="row fixed-bottom justify-content-center">
				<div>
					<p class="text-muted">Copyright © 2019 by DressFi Republic</p>
				</div>
			</div>
		</div>
	</footer>

</body>
</html>