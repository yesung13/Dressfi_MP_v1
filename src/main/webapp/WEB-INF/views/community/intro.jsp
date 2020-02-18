<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	//세션값 받기
	Object userId = session.getAttribute("userId");
	session.setAttribute("userId", userId);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>DressFi 소개</title>
<style>
#hr {
	display: block;
	margin-top: 0.5em;
	margin-left: auto;
	margin-right: auto;
	border: 1px solid #ddd;
}

.navbar img {
	vertical-align: middle;
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
	vertical-align: middle;
}

.form-inline .rounded-circle {
	
}

/* .optionMenu_section span {
	font-size: 1.188em;
} */
.verticalScroll {
	width: 100%;
	height: 600px;
	overflow: hidden;
	overflow-y: scroll;
	height: 600px;
}

li a:link { /*클릭하지 않은 링크*/
	color: black;
	text-decoration: none;
}

li a:hover { /*링크를 클릭하려고 마우스를 가져갔을때*/
	color: gray;
	text-decoration: none; /*밑줄 없음*/
}

img {
	width: 0 auto;
}
</style>
<!--추가 css-->
<link href="${pageContext.request.contextPath}/resources/css/bootstrap.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/resources/css/profile.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/resources/css/style-2.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/resources/css/font.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath}/resources/css/swiper.min.css" rel="stylesheet" type="text/css">
<!--//추가 cc-->

<!--변경전 기존css-->
<!--
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/profile.css">
-->
<!-- <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script> -->

<!--추가 js-->
<script src="${pageContext.request.contextPath}/resources/js/jquery-3.3.1.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/js/popper.min.js"></script>

<!-- Swiper JS -->
<script src="${pageContext.request.contextPath}/resources/js/swiper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
<script src="https://code.jquery.com/jquery-1.9.1.min.js"></script>
<!-- 구글 icon -->
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.0/css/all.css">


<!-- <security:authorize access="isAuthenticated()"> -->
<!-- 	<security:authentication property="principal.bNick" var="nick" /> -->
<!-- 	<!-- 웹 소켓 사용해서 현재 몇개의 쪽지가 도착했는지 구해오기. -->
<!-- 	<script type="text/javascript"> -->
<!-- 		var wsUri = "ws://localhost:8080/ex/count"; -->
<!-- 		function send_message() { -->
<!-- 			websocket = new WebSocket(wsUri); -->
<!-- 			websocket.onopen = function(evt) { -->
<!-- 				onOpen(evt); -->
<!-- 			}; -->
<!-- 			websocket.onmessage = function(evt) { -->
<!-- 				onMessage(evt); -->
<!-- 			}; -->
<!-- 			websocket.onerror = function(evt) { -->
<!-- 				onError(evt); -->
<!-- 			}; -->
<!-- 		} -->
<!-- 		function onOpen(evt) { -->
<%-- 			websocket.send("${nick}"); --%>
<!-- 		} -->
<!-- 		function onMessage(evt) { -->
<!-- 			$('#count').append(evt.data); -->
<!-- 		} -->
<!-- 		function onError(evt) { -->
<!-- 		} -->
<!-- 		$(document).ready(function() { -->
<!-- 			send_message(); -->
<!-- 		}); -->
<!-- 	</script> -->
<!-- </security:authorize> -->

</head>
<body>
	<!-- <span id="count" class="badge bg-theme"></span> 안읽은 제안서 갯수 출력-->
	<header class="header bg-light">
		<nav class="navbar sticky-top navbar-expand-lg navbar-light bg-light">
			<div class="container">
				<a class="navbar-brand" href="/dressfi/">
					<img src="${pageContext.request.contextPath}/resources/images/svg/clothing-hanger.svg" width="35" class="d-inline align-text-top" alt="logo">
					<span class="d-inline-block align-text-top">DressFi</span>
				</a>
				<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent1" aria-controls="navbarSupportedContent1" aria-expanded="false" aria-label="Toggle navigation">
					<span class="navbar-toggler-icon"></span>
				</button>

				<div class="collapse navbar-collapse" id="navbarSupportedContent1">

					<ul class="navbar-nav nav-width mr-auto">
						<li class="nav-item mr-2">
							<a class="nav-link" href="${pageContext.request.contextPath}/member/intro.do" role="button">DressFi 소개</a>
						</li>
						<li class="dropdown">
							<a class="nav-link dropdown-toggle mr-2" href="#" id="navbarDropdown1" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">전문가 공간</a>
							<div class="dropdown-menu" aria-labelledby="navbarDropdown1">
								<a class="dropdown-item" href="${pageContext.request.contextPath}/profile/design.do">디자이너 회원</a>
								<a class="dropdown-item" href="${pageContext.request.contextPath}/profile/factory.do">공장 회원</a>
								<a class="dropdown-item" href="${pageContext.request.contextPath}/profile/seller.do">도소매 회원</a>
							</div>
						</li>
						<li class="nav-item dropdown">
							<a class="nav-link dropdown-toggle" href="#" id="navbarDropdown1-2" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">커뮤니티</a>
							<div class="dropdown-menu" aria-labelledby="navbarDropdown1">
								<a class="dropdown-item" href="${pageContext.request.contextPath}/board/knowhow.do">노하우</a>
								<a class="dropdown-item" href="${pageContext.request.contextPath}/board/list.do">Q&A</a>
							</div>
						</li>
						<li class="nav-serch float-right">
							<form class="form-inline my-2 my-lg-0 main-serch">
								<input class="form-control mr-sm-2" type="search" placeholder="Search" aria-label="Search">
								<button class="btn my-2 my-sm-0 btn-sm" type="submit">
									<i class="fa fa-search fa-1x"></i>
								</button>
							</form>
						</li>


						<li class="nav-logright">
							<ul class="nav navbar-right nav-log">
								<%
									//아이디 세션이 없을경우 로그인
									if (session.getValue("userId") == null) {
								%>
								<li>
									<a class="text-secondary" href="${pageContext.request.contextPath}/member/joinSelect.do">
										<i class="fas fa-user-alt"></i> 회원가입 &nbsp;|
									</a>
								</li>
								<li>
									<a class="text-secondary" href="${pageContext.request.contextPath}/member/login.do">
										<i class="fas fa-lock"></i> 로그인
									</a>
								</li>

								<%
									// 세션에 아이디값이 있을경우 다른폼을 구성
									} else {
								%>
								<li class="dropdown">
									<a class="text-secondary dropdown-toggle" href="#" id="navbarDropdown2-1" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
										<span class="text-secondary mr-2">${userName}님${loginUser.name}</span>
										<img src="<c:url value="/resources/img/${p_img}/"/>" alt="프로필사진" height="18" class="rounded-circle">
									</a>
									<div class="dropdown-menu my-dr" aria-labelledby="navbarDropdown1">
										<a class="dropdown-item" href="${pageContext.request.contextPath}/profile/myhome.do">마이페이지</a>
										<input type="hidden" name="userId" value="${loginUser.userId}">
										<a class="dropdown-item" href="${pageContext.request.contextPath}/proposal/proposalrec.do">제안서관리</a>
										<a class="dropdown-item" href="${pageContext.request.contextPath}/member/logout.do">로그아웃</a>
										<c:if test="${not empty admincode}">
											<a class="dropdown-item" href="${pageContext.request.contextPath}/member/adminlist.do">관리페이지</a>
										</c:if>
									</div>
								</li>
								<li>
									<a class="text-secondary" href="${pageContext.request.contextPath}/board/list.do">
										<img src="${pageContext.request.contextPath}/resources/images/svg/bell.svg" height="18">
									</a>
								</li>
								<%
									}
								%>
							</ul>

						</li>

					</ul>


				</div>
			</div>
		</nav>
	</header>
	<section>
		<div class="container mt-3">
			<div class="row">
				<div class="col-xl-12">
					<h2 class="mb-5">
						DressFi&nbsp;<small>소개</small>
					</h2>

					<blockquote class="blockquote">
						<h5 class="mb-0">Crew(크루)</h5>
						<footer class="blockquote-footer">
							<cite title="Source Title">같은 목적을 가지고 목적을 달성하는 그룹을 의미</cite>
						</footer>
					</blockquote>
					<div class="mt-3">
						<img class="img-fluid" style="width: 1500px; height: 500px;" src="${pageContext.request.contextPath}/resources/images/profile_bg/intro.jpg" alt="intro" />
					</div>

					<h5 class="text-justify text-break mt-2">
						<strong>DressFi</strong>는 <b>의류업계 전문가</b> 또는 <b>의류업계 입문자</b>들의 커뮤니티 사이트입니다. <br>자신을 홍보할 수 있는 작품 또는 관련 사진을 프로필에 업로드 하여 자신을 어필 할 수 있으며, 제안서기능을 통해 원하는 분야의 크루들을 찾아 프로젝트를 계획할 수도 있습니다.
					</h5>
					<br>
					<dl class="row mb-2">
						<dt class="col-sm-12">
							<strong>이런 분들에게 유용합니다</strong>
						</dt>
						<dd class="col-sm-12 ml-2">
							<i class="far fa-lightbulb"></i>&nbsp;평소에 패션에 관심있는 패션 입문자분들
						</dd>
						<dd class="col-sm-12 ml-2">
							<i class="far fa-lightbulb"></i>&nbsp;옷을 잘 입고 싶어 코디를 원하는분들
						</dd>
						<dd class="col-sm-12 ml-2">
							<i class="far fa-lightbulb"></i>&nbsp;의류관련 직업에 대한 정보와 지식이 필요한분들
						</dd>
						<dd class="col-sm-12 ml-2">
							<i class="far fa-lightbulb"></i>&nbsp;디자인 샘플, 디자인한 상품을 상품화 및 납품하기 위해 공장 및 도매&middot;소매 상점이 필요하신 디자이너분들
						</dd>
						<dd class="col-sm-12 ml-2">
							<i class="far fa-lightbulb"></i>&nbsp;의류제작에 필요한 공장을 소유하고 계신 공장소유자분들
						</dd>
						<dd class="col-sm-12 ml-2">
							<i class="far fa-lightbulb"></i>&nbsp;의류 도매&middot;소매 상점을 운영하시는분들
						</dd>

					</dl>
					<dl class="row mb-5">
						<dt class="col-sm-12">
							<strong>세분화된 회원 구조</strong>
						</dt>
						<dd class="col-sm-12 ml-2">
							<i class="far fa-lightbulb"></i>&nbsp;의류업계 필수 분야로 옷을 디자인하는 <b>디자이너</b>, 옷을 생산하는<b>공장</b>, 옷을 판매하는 <b>도소매</b> 상점 이렇게 총 3개의 분야로 구분했습니다.
						</dd>
						<dd class="col-sm-12 ml-2 mb-5">
							<i class="far fa-lightbulb"></i>&nbsp;의류업계 전문가분들은 <b>전문가 회원</b>으로 등록하실 수 있으며, 자신을 어필할 게시물을 등록하여 홍보할 수 있습니다.
						</dd>


					</dl>
				</div>
			</div>
		</div>
	</section>

	<!--푸터-->
	<footer class="footer bg-light container-fluid">
		<div class="container">
			<div class="row">
				<div class="">
					<p class="small">
						<strong>고객센터 &gt;</strong>
					</p>
					<div class="sns-fill">
						<ul class="nav nav-pills float-right">
							<li class="nav-item">
								<a class="nav-link" href="#">
									<i class="fab fa-facebook"></i>
								</a>
							</li>
							<li class="nav-item">
								<a class="nav-link" href="#">
									<i class="fab fa-twitter"></i>
								</a>
							</li>
							<li class="nav-item">
								<a class="nav-link" href="#">
									<i class="fab fa-google-plus-g"></i>
								</a>
							</li>
						</ul>
					</div>
					<p>
						<label style="font-size: 1.7rem">
							<strong>1544-1004</strong>
						</label>
						<br> <span style="font-size: 0.9rem; margin-top: -20px;">평일 10:00~18:00(점심시간 12:00~13:00/주말&공휴일 제외)</span>
					</p>
					<address class="small">상호명(주)0000 이메일0000@0000.net 대표이사 0000 사업자등록번호0000-0000-0000 통신판매업신고번호제2018-서울서초-0000호 주소서울특별시 서초구 서초대로 0000</address>

					<p>Copyright © 2019 by DressFi Republic</p>
				</div>
				<div class="text-right">
					<!-- 					<button class="btn btn-secondary" onClick="location.href='/member/adminlogin.do';">
						<span class="sr-only">관리자</span>
					</button> -->
					<a class="" href="${pageContext.request.contextPath}/member/adminlogin.do">
						<span class="small">관리자</span>
					</a>
				</div>
			</div>
		</div>
	</footer>
</body>
</html>