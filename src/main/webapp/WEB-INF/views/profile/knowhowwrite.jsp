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
        $(document).ready(function () {
            /* $('[data-toggle="tooltip"]').tooltip(); */

            $("#imgInp").change(function () {
                //alert(this.value);
                //선택한 이미지 경로 표시
                readURL(this);
            });
        });

        function readURL(input) {
            if (input.files && input.files[0]) {
                var reader = new FileReader(); //파일을 읽기 위한 FileReader객체 생성
                reader.onload = function (e) { //파일 읽어들이기를 성공했을때 호출되는 이벤트 핸들러
                    $('#blah').attr('src', e.target.result);
                    $('#img').css('display', 'none');
                    $('#upimg').css('display', 'block');
                    $('#h_fileloc').val(e.target.result);
                    //이미지 Tag의 SRC속성에 읽어들인 File내용을 지정
                    //(아래 코드에서 읽어들인 dataURL형식)
                }
                reader.readAsDataURL(input.files[0]);
                //File내용을 읽어 dataURL형식의 문자열로 저장
            }
        }//readURL()-- //file 양식으로 이미지를 선택(값이 변경) 되었을때 처리하는 코드
    </script>


</head>
<body style="padding-top: 70px">
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
<div class="container mt-5">
    <div class="jumbotron border bg-light">
        <p class="text-center">
            <i class="far fa-images fa-2x text-888" id="img" style="display: block;"><span
                    class="lead text-center text-888 text-bold ml-2">커버 업로드</span></i> <i id="upimg"
                                                                                          style="display: none;"><img
                id="blah" style="width: 100%; height: 100%" class="far fa-images fa-2x text-888" alt="..."/></i>
        </p>
        <p class="d-flex justify-content-center ml-5">
            <input type="file" id="imgInp" name="file"/>
        </p>
    </div>

</div>
<!--글쓰기 -->

<section>

    <div class="container photo-margin mt-2">
        <div class="row  ml-3">

            <form action="knowhowwrite_insert.do" method="post" enctype="multipart/form-data">
                <ul class="nav nav-pills pople">
                    <li class="nav-item">
                        <strong class="nav-link active">카테고리</strong>
                    </li>
                    <li class="nav-item">
                        <select class="custom-select mb-3" id="h_category" name="h_category">
                            <option selected>선택</option>
                            <option value="1">패션코디팁</option>
                            <option value="2">의류지식</option>
                            <option value="3">나의리폼</option>
                            <option value="4">원단과부자재</option>
                            <option value="5">패션뉴스</option>
                            <option value="6">생활필수</option>
                        </select>
                    </li>
                </ul>
                <div class="form-group col-12">
                    <input type="text" class="form-control" id="h_title" name="h_title" placeholder="제목을 입력해 주세요.">
                    <input type="hidden" id="h_name" name="h_name" value="${userName}">
                    <input type="hidden" id="h_fileloc" name="h_fileloc">
                </div>

                <div class="form-group col-12">
                    <textarea class="form-control" rows="5" placeholder="글내용을 입력해 주세요." id="h_text"
                              name="h_text"></textarea>
                </div>
                <div class="form-group float-right">
                    <button type="submit" class="btn btn-secondary float-right">올리기</button>
                    <button type="reset" class="btn btn-secondary  float-right	 mr-1">취소</button>
                </div>
            </form>

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