<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
        /* 구글 웹폰트 */
        /* @import
            url('https://fonts.googleapis.com/css?family=Gothic+A1|Nanum+Gothic|Noto+Sans+KR&display=swap')
            ; */
        #hr {
            display: block;
            margin-top: 0.5em;
            margin-left: auto;
            margin-right: auto;
            border: 1px solid #000;
        }

        .navbar img {
            vertical-align: middle;
            /* 	width: 24px;
            height: 24px; */
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
            /* 	width: 2.0em;
            height: 2.0em; */
        }

        .form-inline .rounded-circle {
            /* 	width: 2.4em;
            height: 2.4em; */

        }

        .snschat img {
            width: 2.0em;
            height: 2.0em;
        }

        /* .user_profile span[class="font-weight-bold"] {
            font-family: 'Noto Sans KR', sans-serif;
            font-size: 1.8em;
        } */

        /* .table th {
            font-family: 'Nanum Gothic', sans-serif;
            font-size: 0.938em;
            font-weight: 700;
            color: #999999;
        } */
        /
        .table td span {
            font-family: 'Gothic A1', sans-serif;
            font-size: 0.750em;
            font-weight: 600;
        }

        .table td a {
            font-family: 'Nanum Gothic', sans-serif;
            font-size: 0.938em;
            font-weight: 700;
            text-decoration: none;
            color: #4374D9;
        }

        .table .etc span {
            font-family: 'Gothic A1', sans-serif;
            font-size: 0.813em;
            font-weight: 600;
        }

        .verticalScroll {
            width: 100%;
            height: 600px;
            overflow: hidden;
            overflow-y: scroll;
        }

        .userimg {
            width: 13.0em;
            height: 13.0em;
        }

        .profileimg img {
            width: 8em;
            height: 8em;
        }

        table {
            table-layout: fixed;
        }

        th, td {
            text-align: left;
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
                <img src="${pageContext.request.contextPath}/resources/images/svg/clothing-hanger.svg" width="35"
                     class="d-inline align-text-top" alt="logo">
                <span class="d-inline-block align-text-top">DressFi</span>
            </a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent1"
                    aria-controls="navbarSupportedContent1" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>

            <div class="collapse navbar-collapse" id="navbarSupportedContent1">

                <ul class="navbar-nav nav-width mr-auto">
                    <li class="nav-item mr-2">
                        <a class="nav-link" href="${pageContext.request.contextPath}/member/intro.do" role="button">DressFi
                            소개</a>
                    </li>
                    <li class="dropdown">
                        <a class="nav-link dropdown-toggle mr-2" href="#" id="navbarDropdown1" role="button"
                           data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">전문가 공간</a>
                        <div class="dropdown-menu" aria-labelledby="navbarDropdown1">
                            <a class="dropdown-item" href="${pageContext.request.contextPath}/profile/design.do">디자이너
                                회원</a>
                            <a class="dropdown-item" href="${pageContext.request.contextPath}/profile/factory.do">공장
                                회원</a>
                            <a class="dropdown-item" href="${pageContext.request.contextPath}/profile/seller.do">도소매
                                회원</a>
                        </div>
                    </li>
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown1-2" role="button"
                           data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">커뮤니티</a>
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
                                <a class="text-secondary"
                                   href="${pageContext.request.contextPath}/member/joinSelect.do">
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
                                <a class="text-secondary dropdown-toggle" href="#" id="navbarDropdown2-1" role="button"
                                   data-toggle="dropdown" data-display="static" aria-haspopup="true"
                                   aria-expanded="false">
                                    <span class="text-secondary mr-2">${userName}님</span>
                                    <img src="<c:url value="/resources/img/${p_img}/"/>" alt="프로필사진" height="18"
                                         class="rounded-circle">
                                </a>
                                <div class="dropdown-menu my-dr dropdown-menu-lg-right"
                                     aria-labelledby="navbarDropdown1">
                                    <a class="dropdown-item"
                                       href="${pageContext.request.contextPath}/profile/myhome.do">마이페이지</a>
                                    <input type="hidden" name="userId" value="${loginUser.userId}">
                                    <a class="dropdown-item"
                                       href="${pageContext.request.contextPath}/proposal/proposalrec.do">제안서관리</a>
                                    <a class="dropdown-item" href="${pageContext.request.contextPath}/member/logout.do">로그아웃</a>
                                    <c:if test="${not empty admincode}">
                                        <a class="dropdown-item"
                                           href="${pageContext.request.contextPath}/member/adminlist.do">관리페이지</a>
                                    </c:if>
                                </div>
                            </li>
                            <li>
                                <a class="text-secondary" href="${pageContext.request.contextPath}/board/list.do">
                                    <img src="${pageContext.request.contextPath}/resources/images/svg/bell.svg"
                                         height="18">
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
<!--//메뉴-->

<section>
    <div class="container margin-30">
        <div class="row mt-4">
            <div class="col-sm-12 col-md-12 col-xl-8">
                <!--포트폴리오사진 -->
                <div class="mt-1  photo-view-fill  mb-5">
                    <div class="col-xl-12">
                        <div class="card profile-card-2">
                            <p class="font-weight-bold" style="font-size: 1.2em">${portfolio.i_title}</p>
                            <div class="">
                                <a href="#">
                                    <img class="img-fluid rounded border" src="${portfolio.i_fileloc }" alt="사진"
                                         style="width: 1000px; height: 700px"/>
                                </a>
                            </div>
                            <p class="text-justify text-break mt-2">${portfolio.i_text}</p>
                        </div>
                    </div>
                </div>
            </div>

            <!--작은사진-->
            <div class="col-sm-12 col-md-12 col-xl-4 photo-fill-2 mt-1">

                <!--좋아요 -->
                <div class="d-flex margin-30 ">
                    <button type="button" class="btn btn-light btn-small border w-100 mr-1" onclick="">
                        <i class="far fa-heart"></i>
                        <span class="text-info">32</span>
                    </button>
                    <button type="button" class="btn btn-light btn-small border w-100" disabled="disabled" onclick="">
                        <i class="fas fa-eye"></i>
                        <span class="text-info">32</span>
                    </button>
                    <button class="btn btn-sm dropdown show dropdown-toggle" type="button" id="textmdBtn"
                            data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        <i class="fas fa-ellipsis-v"></i>
                    </button>
                    <div class="dropdown-menu dropdown-menu-right" aria-labelledby="textmdBtn">
                        <a class="dropdown-item" href="#">수정</a>
                        <a class="dropdown-item" href="#">삭제</a>
                    </div>
                </div>
                <!--//좋아요-->

                <!--등록자-->
                <div class="d-flex flex-row justify-content-between margin-30">
                    <div class="">
                        <a href="${pageContext.request.contextPath}/profile/myhome.do">
                            <img src="<c:url value="/resources/img/${p_img}/"/>" alt="프로필사진" width="50" height="50"
                                 class="img-fluid rounded-circle ml-1">
                            <span class="ml-1 text-dark">
									<strong>${loginUser.name}</strong>
								</span>
                        </a>
                    </div>
                    <div class="">
                        <button class="btn btn-light mt-2" onClick="''">
                            <strong class="small font-weight-bold">팔로우</strong>
                        </button>
                    </div>

                </div>
                <!--//등록자-->
                <c:forEach items="${portfolioList}" var="item">
                    <c:if test="${portfolio.ino != item.ino }">
                        <div class="col-sm-6 col-md-6 col-lg-6 ">
                            <div class="card profile-card-2">
                                <div class="">
                                    <a href="${pageContext.request.contextPath}/profile/pphotoview.do?ino=${item.ino}&userId=${userId}">
                                        <img class="img-fluid rounded border" src="${item.i_fileloc }"
                                             alt="${i_fileloc }" style="width: 350px; height: 150px"/>
                                    </a>
                                </div>
                            </div>
                        </div>
                    </c:if>
                </c:forEach>

            </div>
        </div>
    </div>
    <!--댓글달기 -->
    <div class="container photo-margin border-top">
        <div class="row ml-3 mt-4">
            <div class="col-12 mb-1">
                <h5>
                    <span class="font-weight-bold">댓글</span>
                    12
                </h5>
            </div>
            <!-- 내 댓글 -->
            <form class="col-12 mb-3">
                <div class="orm-group ml-auto d-flex justify-content-start">
                    <div class="mr-2">
                        <img src="<c:url value="/resources/img/${p_img}/"/>" alt="프로필사진" width="30" height="30"
                             class="rounded-circle">
                    </div>
                    <div class="w-75">
                        <input type="text" class="form-control" id="exampleInputEmail1"
                               placeholder="칭찬과 격려의 글은 작성자에게 큰힘이 됩니다.">
                    </div>
                </div>
            </form>
            <!--//내 댓글  -->

            <!-- 다른사람 댓글  -->
            <div class="col-12 mb-3">
                <div>
                    <img src="${pageContext.request.contextPath}/resources/images/profile_pic/w5.jpg" alt="사진"
                         width="30" height="30" class="profile rounded-circle"/>
                    <span class="text-bold mr-2">달고나님</span>
                    <span>입력을 기억합니다.</span>
                    <p class="small text-888 ml-5">
                        30분전
                        <span class="ml-4">
								<i class="far fa-heart"></i> 좋아요
							</span>
                    </p>
                </div>
            </div>
            <!-- //다른사람 댓글  -->

            <!--페이지버튼 -->
            <!-- 				<div class="col-12 d-flex justify-content-center">
                <nav aria-label="Page navigation example">
                    Add class .pagination-lg for larger blocks or .pagination-sm for smaller blocks
                    <ul class="pagination">
                        <li class="page-item">
                            <a class="page-link" href="#" aria-label="Previous">
                                <span aria-hidden="true">&laquo;</span>
                                <span class="sr-only">Previous</span>
                            </a>
                        </li>
                        <li class="page-item">
                            <a class="page-link" href="#">1</a>
                        </li>
                        <li class="page-item">
                            <a class="page-link" href="#">2</a>
                        </li>
                        <li class="page-item">
                            <a class="page-link" href="#">3</a>
                        </li>
                        <li class="page-item">
                            <a class="page-link" href="#">4</a>
                        </li>
                        <li class="page-item">
                            <a class="page-link" href="#">5</a>
                        </li>
                        <li class="page-item">
                            <a class="page-link" href="#" aria-label="Next">
                                <span aria-hidden="true">&raquo;</span>
                                <span class="sr-only">Next</span>
                            </a>
                        </li>
                    </ul>
                </nav>
            </div> -->
            <!--//페이지버튼 -->
            <!--지우지마세요-여백둔것 -->
            <div class="mb-5 col-12 d-flex justify-content-center"></div>
        </div>
    </div>
    <!--//댓글달기 -->
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
                    <br>
                    <span style="font-size: 0.9rem; margin-top: -20px;">평일 10:00~18:00(점심시간 12:00~13:00/주말&공휴일 제외)</span>
                </p>
                <address class="small">상호명(주)0000 이메일0000@0000.net 대표이사 0000 사업자등록번호0000-0000-0000
                    통신판매업신고번호제2018-서울서초-0000호 주소서울특별시 서초구 서초대로 0000
                </address>

                <p>Copyright © 2019 by DressFi Republic</p>
            </div>
            <div class="text-right">
                <!-- 					<button class="btn btn-secondary" onClick="location.href='/member/adminlogin.do';">
                    <span class="sr-only">관리자</span>
                </button> -->
                <a class="" href="${pageContext.request.contextPath}/admin/adminlogin.do">
                    <span class="small">관리자</span>
                </a>
            </div>
        </div>
    </div>
</footer>
</body>
</html>