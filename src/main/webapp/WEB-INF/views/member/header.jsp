<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	//세션값 받기
	Object userid = session.getAttribute("userId");
	session.setAttribute("userId", userid);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>DressFi</title>
<style>
#hr {
	display: block;
	margin-top: 0.5em;
	margin-left: auto;
	margin-right: auto;
	border: 1px solid #ddd;
}

#map {
	width: 100%;
	height: 100%;
}

.navbar img {
	width: 24px;
	height: 24px;
}

.nav-link {
	/* font-family: 'Nanum Gothic', sans-serif; */
	font-size: 1.0em;
	font-weight: 700;
	color: black;
}

.nav-link:hover {
	/* font-family: 'Nanum Gothic', sans-serif; */
	font-size: 1.0em;
	font-weight: 700;
	color: #999999;
}

.form-inline {
	font-size: 0.813rem;
}

.form-inline a:hover { /*클릭하지 않은 링크*/
	text-decoration: none;
}

.form-inline a img {
	width: 2.0em;
	height: 2.0em;
}

.form-inline .rounded-circle {
	width: 2.4em;
	height: 2.4em;
}

.carousel-item img {
	width: 0 auto;
	height: 400px;
	max-height: 400px;
}

.verticalScroll {
	width: 100%;
	height: 600px;
	overflow: hidden;
	overflow-y: scroll;
}

img {
	width: 0 auto;
}
</style>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/profile.css">
<!-- 구글 icon -->
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.0/css/all.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</head>
<body>
	<header class="header bg-light">
		<nav class="navbar sticky-top navbar-expand-lg navbar-light">
			<div class="container">
				<a class="navbar-brand mb-0 h1" href="/dressfi/">
					<img src="<%=request.getContextPath()%>/resources/images/svg/clothing-hanger.svg" class="d-inline-block align-top" alt="logo">
					<span class="d-inline-block align-bottom">DressFi</span>
				</a>
				<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
					<span class="navbar-toggler-icon"></span>
				</button>

				<div class="collapse navbar-collapse" id="navbarSupportedContent">
					<ul class="navbar-nav mr-auto">
						<li class="nav-item dropdown">
							<a class="nav-link dropdown-toggle" href="#" id="nbd_1" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">파트너</a>
							<div class="dropdown-menu" aria-labelledby="nbd_1">
								<a class="dropdown-item" href="<%=request.getContextPath()%>/community/design.do">디자이너</a>
								<a class="dropdown-item" href="#">공장</a>
								<a class="dropdown-item" href="#">도소매</a>
								<a class="dropdown-item" href="#">원단&부자재</a>
							</div>
						</li>
						<li class="nav-item dropdown">
							<a class="nav-link dropdown-toggle" href="#" id="nbd_2" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">커뮤니티</a>
							<div class="dropdown-menu" aria-labelledby="nbd_2">
								<a class="dropdown-item" href="<%=request.getContextPath()%>/community/knowhow.do">노하우</a>
								<a class="dropdown-item" href="<%=request.getContextPath()%>/community/qanda.do">Q&A</a>
							</div>
						</li>
						<li class="nav-item">
							<a class="nav-link" href="<%=request.getContextPath()%>/community/notice.do" role="button">공지사항</a>
						</li>
					</ul>

					<form class="form-inline my-2 my-lg-0">

						<%
							//아이디 세션이 없을경우 로그인
							if (session.getValue("userId") == null) {
						%>
						<a class="text-secondary mr-2" href="<%=request.getContextPath()%>/member/joinSelect.do">
							<i class="fas fa-user-alt"></i> 회원가입 |
						</a>
						<a class="text-secondary mr-2" href="<%=request.getContextPath()%>/member/login.do">
							<i class="fas fa-lock"></i> 로그인
						</a>
						<%
							// 세션에 아이디값이 있을경우 다른폼을 구성
							} else {
						%>
						<a class="text-secondary mr-2" href="#" data-toggle="tooltip" data-placement="bottom" title="알림">
							<img src="<%=request.getContextPath()%>/resources/images/svg/bell.svg">
						</a>
						<span class="text-secondary mr-2"> ${userName}님<%-- ${loginUser.name} --%></span>

						<ul class="navbar-nav mr-auto">
							<li class="nav-item dropdown">
								<a class="nav-link dropdown-toggle" href="#" id="nbd_3" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
									<img src="<%=request.getContextPath()%>/resources/images/profile_pic/m1.jpg" alt="프로필사진" class="rounded-circle">
								</a>
								<div class="dropdown-menu dropdown-menu-right" aria-labelledby="nbd_3">
									<a class="dropdown-item" href="<%=request.getContextPath()%>/profile/myhome.do">마이홈</a>
									<a class="dropdown-item" href="#">제안서관리</a>
									<a class="dropdown-item" href="<%=request.getContextPath()%>/member/logout.do">로그아웃</a>
									<!-- 관리자 로그인 시 -->
									<c:if test="${not empty admincode}">
										<a class="dropdown-item" href="<%=request.getContextPath()%>/member/adminlist.do">관리페이지</a>
									</c:if>
								</div>
							</li>
						</ul>
						<%
							}
						%>
					</form>
					<form class="form-inline my-2 my-lg-0">
						<input class="form-control mr-sm-2" type="search" placeholder="Search" aria-label="Search">
						<button class="btn btn-outline-dark my-2 my-sm-0" type="submit">Search</button>
					</form>
				</div>
			</div>
		</nav>
	</header>