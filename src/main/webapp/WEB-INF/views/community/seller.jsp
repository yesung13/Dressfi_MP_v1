<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
    <title>도소매회원 공간</title>
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
            /* 	max-width: 100%;
            height: auto; */
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
    <script type="text/javascript">
        $(document).ready(function () {
            $('#selectBtn').trigger('click');
        });

        function selectBtn() {
            var select1 = $('#select1 option:selected').val();
            var select2 = $('#select2 option:selected').val();
            var select3 = $('#select3 option:selected').val();
            var select4 = $('#select4 option:selected').val();
            var select5 = $('#select5 option:selected').val();
            var item = Object();
            item.select1 = select1;
            item.select2 = select2;
            item.select3 = select3;
            item.select4 = select4;
            item.select5 = select5;
            $
                .ajax({
                    url: 'getSeller.do',
                    type: 'POST',
                    data: item,
                    dataType: 'json',
                    success: function (resultList) {
                        if (resultList.length == 0) {
                            alert("데이터가 없습니다.");
                            $('#imglist').empty();
                            return;
                        }
                        $('#imglist').empty();
                        var html = '';
                        for (var i = 0; i < resultList.length; i++) {
                            var result = resultList[i];
                            html += '<div class="col-sm-12 col-md-6 col-xl-3 px-2">';
                            html += '<div class="media mb-2">';
                            html += '<div>';
                            html += '<a href="${pageContext.request.contextPath}/profile/userhome.do?i_mdiv=' + result.I_MDIV + '&i_mnum=' + result.I_MNUM + '">';
                            html += '<img src="${pageContext.request.contextPath}/resources/img/' + result.MC_PROIMG + '" alt="사진" class="profile mr-3 mt-3 rounded-circle border img-fluid" style="width:50px; height: 50px;" />';
                            html += '</a>';
                            html += '</div>';
                            html += '<div class="media-body pt-4">';
                            html += '<div class="card-text">';
                            html += result.MC_NAME;
                            html += '</div>';
                            html += '<small class="card-text">';
                            //DB에 저장된 사용자 값- 사용자 정보를 뿌려주기 위함 필요시 더 생성해야함
                            if (result.MC_DIV == '1') {
                                html += '도매';
                            } else if (result.MC_DIV == '2') {
                                html += '소매';
                            }
                            html += '</small>';
                            html += '</div>';
                            html += '</div>';
                            html += '<a href="${pageContext.request.contextPath}/profile/userpphotoview.do?ino=' + result.INO + '&i_mnum=' + result.I_MNUM + '&i_mdiv=' + result.I_MDIV + '">';
                            html += '<img class="img-fluid rounded border" src="' + result.I_FILELOC + '" alt="사진" style="width: 400px; height: 250px;"/>';
                            html += '</a>';
                            html += '<p class="sl-title d-flex justify-content-around">';
                            html += '<a href="#" class="card-link">';
                            html += '<i class="far fa-heart"></i>' + result.I_LIKECNT;
                            html += '</a>';
                            html += '<a href="#" class="card-link">';
                            html += '<i class="fas fa-eye"></i>' + result.I_VIEWCNT;
                            html += '</a>';
                            html += '<a href="#" class="card-link">';
                            html += '<i class="far fa-comment-alt"></i>' + result.I_REPLYCNT;
                            html += '</a>';
                            html += '</p>';
                            html += '<div class="text-justify  text-truncate" style="height: 50px;">';
                            html += '<span>' + result.I_TEXT + '</span>';
                            html += '</div>';
                            html += '</div>';
                        }
                        $('#imglist').append(html);
                    }
                });
        }
    </script>
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
                                   data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                    <span class="text-secondary mr-2">${userName}님${loginUser.name}</span>
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
<section class="optionList_section">
    <div class="container mt-3">
        <div class="d-flex">
            <div class=" mr-1">
                <select class="custom-select custom-select-sm border border-secondary mb-3" id="select1">
                    <option value="0" selected>정렬</option>
                    <option value="1">최신순</option>
                    <option value="2">인기순</option>
                    <option value="3">팔로잉</option>
                </select>
            </div>
            <div class=" mr-1">
                <select class="custom-select custom-select-sm border border-secondary mb-3" id="select2">
                    <option value="0" selected>구분</option>
                    <option value="1">도매</option>
                    <option value="2">소매</option>
                </select>
            </div>
            <div class=" mr-1">
                <select class="custom-select custom-select-sm border border-secondary mb-3" id="select3">
                    <option value="0" selected>의류분야</option>
                    <option value="1 ">남성</option>
                    <option value="2 ">여성</option>
                    <option value="3 ">아동</option>
                    <option value="4 ">기타</option>
                </select>
            </div>
            <div class=" mr-1">
                <select class="custom-select custom-select-sm border border-secondary mb-3" id="select4">
                    <option value="0" selected>세부의류분야</option>
                    <option value="1">상의</option>
                    <option value="2">하의</option>
                    <option value="3">신발</option>
                    <option value="4">기타</option>
                </select>
            </div>
            <div class=" mr-1">
                <select class="custom-select custom-select-sm border border-secondary mb-3" id="select5">
                    <option value="0" selected>지역</option>
                    <option value="10">서울</option>
                    <option value="20">강원</option>
                    <option value="30">대전</option>
                </select>
            </div>
            <div class=" mr-1">
                <button type="button" class="btn btn-sm btn-dark" onClick="selectBtn()" id="selectBtn">검색</button>
            </div>
        </div>

        <!-- 이미지게시물  -->
        <div class="row" id="imglist"></div>
        <!-- //이미지게시물 -->

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
                <a class="" href="${pageContext.request.contextPath}/member/adminlogin.do">
                    <span class="small">관리자</span>
                </a>
            </div>
        </div>
    </div>
</footer>
</body>
</html>