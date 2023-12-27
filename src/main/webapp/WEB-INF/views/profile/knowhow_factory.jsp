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
        @import url('https://fonts.googleapis.com/css?family=Gothic+A1|Nanum+Gothic|Noto+Sans+KR&display=swap');

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
    <!--//추가 css-->

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

    <script>
        //tooltip script
        // 	$(document).ready(function() {
        // 		$('[data-toggle="tooltip"]').tooltip();
        // 	});
    </script>
    <script type="text/javascript">
        function fAction(where) {
            switch (where) {
                case 1:
                    if (confirm("팔로우를 취소하시겠습니까?")) {
                        location.href = "delfl.do?name=${profile.mf_name}&&i_mdiv=${i_mdiv}&i_mnum=${i_mnum}";
                    }
                    break;

                case 2:
                    if (confirm("이 사용자를 팔로우 하시겠습니까?")) {
                        location.href = "incfl.do?name=${profile.mf_name}&&i_mdiv=${i_mdiv}&i_mnum=${i_mnum}";
                    }
                    break;
            }
        }
    </script>
</head>
<body style="padding-top: 70px">
<!--  -->
<!--메뉴-->
<!-- <span id="count" class="badge bg-theme"></span> 안읽은 제안서 갯수 출력-->
<header class="header bg-light">
    <nav class="navbar fixed-top navbar-expand-lg navbar-light bg-light">
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
                                   data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                    <span class="text-secondary mr-2">${userName}님</span>
                                    <img src="<c:url value="/resources/img/${p_img}/"/>" alt="프로필사진" height="18"
                                         class="rounded-circle">
                                </a>
                                <div class="dropdown-menu my-dr" aria-labelledby="navbarDropdown1">
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
    <div class="container">
        <div class="row mt-4">
            <div class="col-sm-12 col-md-12 col-xl-4 mb-4 mypage-fill pofill-bor">
                <!-- 프로필정보수정  -->
                <!-- **회원만보이게**  -->
                <div class="d-flex justify-content-between mb-4 invisible">
                    <a class="text-secondary text-decoration-none "
                       href="${pageContext.request.contextPath}/profile/pmodify.do?userId=${userId}">
                        <i class="fas fa-cog"></i>
                        <span> 프로필 수정</span>
                    </a>
                    <!-- 글쓰기  -->
                    <a class="text-secondary text-decoration-none  "
                       href="${pageContext.request.contextPath}/profile/portfoliowrite.do?userId=${userId}">
                        <i class="fas fa-pen"></i>
                        <span> 글쓰기</span>
                    </a>
                </div>
                <div class="d-flex justify-content-between mb-1">
                    <div class="dropdown show float-right">
                        <a class="btn btn-sm dropdown-toggle" href="#" role="button" data-toggle="dropdown"
                           aria-haspopup="true" aria-expanded="false">
                            <i class="fas fa-share-alt fa-1x text-888"></i>
                        </a>
                        <div class="dropdown-menu social dropdown-menu-right dropdown-menu-lg-left ">
                            <a class="dropdown-item" href="#">Kakao</a>
                            <a class="dropdown-item" href="#">Facebook</a>
                            <a class="dropdown-item" href="#">Twite</a>
                        </div>
                    </div>

                </div>


                <div class="d-flex profileimg mb-2">
                    <img src="<c:url value="/resources/img/${profile.mf_proimg}/"/>" alt="프로필사진" width="150"
                         class="rounded-circle"/>
                </div>

                <!-- 닉네임  -->
                <div class="d-flex justify-content-start mb-1">
                    <h3>
                        <span class="font-weight-bold">${profile.mf_name}</span>
                    </h3>
                    <%-- &#149; ${profile.mf_dept } --%>
                </div>

                <!-- 팔로우  -->
                <div class="d-flex mb-1">
                    <a class="text-decoration-none" href="${pageContext.request.contextPath}/profile/followerView.do">
                        <span class="text-black-50 font-weight-bold">팔로워 0</span>
                        <span class="sr-only">팔로워 수 코딩자리</span>

                    </a>
                    <a class="text-decoration-none" href="${pageContext.request.contextPath}/profile/followingView.do">
                        <span class="text-black-50 font-weight-bold ml-3">팔로잉 0</span>
                        <span class="sr-only">팔로잉 수 코딩자리</span>
                    </a>
                </div>
                <div class="d-flex justify-content-center mb-3">
                    <c:if test="${folyn eq 'n'}">
                        <button class="btn btn-secondary bg-ddd text-center text-black font-weight-bold w-100 mr-1"
                                onclick="fAction(2)">팔로우
                        </button>

                    </c:if>
                    <c:if test="${folyn eq 'y'}">
                        <button class="btn btn-secondary active bg-ddd text-center text-black font-weight-bold w-100 mr-1"
                                onclick="fAction(1)">팔로우 취소
                        </button>

                    </c:if>
                    <a class="snschat text-decoration-none text-muted" href="${profile.mf_kakaourl}"
                       data-toggle="tooltip" data-placement="bottom" title="카카오톡 오픈채팅">
                        <img src="${pageContext.request.contextPath}/resources/images/svg/open-kakao.svg">
                    </a>
                </div>
                <div class="mb-4">
                    <strong class="text-center text-muted font-weight-bold">${profile.mf_introduce }</strong>
                </div>

                <table class="table table-borderless table-sm">
                    <tr>
                        <th>
                            <span class="">연&nbsp;&nbsp;락&nbsp;&nbsp;처</span>
                        </th>
                        <td colspan="2">
                            <span class="">${profile.mf_phone}</span>
                        </td>
                    </tr>
                    <tr>
                        <th>
                            <span class="">홈페이지</span>
                        </th>
                        <td colspan="2">
								<span class="">
									<a href="${profile.mf_homepage }">${profile.mf_homepage }</a>
								</span>
                        </td>
                    </tr>
                    <tr>
                        <th>
                            <span class="">공장분야</span>
                        </th>
                        <td colspan="2">
                            <span class="">${profile.mf_dept }</span>
                        </td>
                    </tr>
                    <tr>
                        <th>
                            <span class="">사업자등록</span>
                        </th>
                        <td colspan="2">
                            <span class="">${profile.mf_business }</span>
                        </td>
                    </tr>
                    <tr>
                        <th>
                            <span class="">사업자번호</span>
                        </th>
                        <td colspan="2">
                            <span class="">${profile.mf_bnum}</span>
                        </td>
                    </tr>
                    <tr>
                        <th>
                            <span class="">상&nbsp;호&nbsp;명</span>
                        </th>
                        <td colspan="2">
                            <span class="">${profile.mf_bname }</span>
                        </td>
                    </tr>
                    <tr>
                        <th>
                            <span class="">사업자주소</span>
                        </th>
                        <td colspan="2">
                            <span class="">${profile.mf_badd1 }${profile.mf_badd2 }</span>
                        </td>
                    </tr>
                    <tr>
                        <th>
                            <span class="">활동지역</span>
                        </th>
                        <td colspan="2">
                            <span class="label-1 font-weight-bold">${ano1}</span>
                            <span class="label-1 font-weight-bold">${ano2}</span>
                            <span class="label-1 font-weight-bold">${ano3}</span>
                        </td>

                    </tr>
                </table>
            </div>


            <!-- 프로필 정보 -->
            <div class="col-sm-12 col-md-12 col-xl-8  mypage-fill">

                <!--포트폴리오 네비-->
                <ul class="nav nav-pills pople">
                    <li class="nav-item">
                        <a class="nav-link"
                           href="${pageContext.request.contextPath}/profile/userhome.do?i_mdiv=${i_mdiv}&i_mnum=${i_mnum}">모두보기</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link"
                           href="${pageContext.request.contextPath}/profile/portfoliolist_design.do?userId=${profile.mf_userId}">포트폴리오 </a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link active"
                           href="${pageContext.request.contextPath}/profile/knowhow_design.do?userId=${profile.mf_userId}">노하우 </a>
                    </li>
                </ul>
                <!-- 이미지게시물  -->
                <div class="container">
                    <div class="row">
                        <c:forEach items="${knowhowList}" var="item">
                            <div class="col-sm-12 col-md-12 col-xl-4 px-2">
                                <a href="${pageContext.request.contextPath}/profile/knowhowview.do?hno=${item.hno}&userId=${userId}">
                                    <img class="img-fluid rounded border" src="${item.h_fileloc }"
                                         alt="${item.h_fileloc }" style="width: 400px; height: 180px;"/>
                                </a>
                                <p class="sl-title d-flex justify-content-around">
                                    <a href="#" class="card-link">
                                        <i class="far fa-heart"></i> ${item.h_likecnt }
                                    </a>
                                    <a href="#" class="card-link">
                                        <i class="fas fa-eye"></i> ${item.h_viewcnt }
                                    </a>
                                    <a href="#" class="card-link">
                                        <i class="far fa-comment-alt"></i> ${item.h_replycnt }
                                    </a>
                                </p>
                                <div class="text-break pb-5">
                                    <span>${item.h_title }</span>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                </div>
                <!-- //이미지게시물 -->

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