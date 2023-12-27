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
    <title>DressFi-제안서</title>
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
    <!--//추가 cc-->

    <!--변경전 기존css-->

    <!-- <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"> -->
    <%-- <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/profile.css"> --%>

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

    <script src="http://code.jquery.com/jquery-1.9.1.min.js"></script>
    <script>
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
                    $('#pr_fileloc').val(e.target.result);
                    //이미지 Tag의 SRC속성에 읽어들인 File내용을 지정
                    //(아래 코드에서 읽어들인 dataURL형식)
                }
                reader.readAsDataURL(input.files[0]);
                //File내용을 읽어 dataURL형식의 문자열로 저장
            }
        }//readURL()-- //file 양식으로 이미지를 선택(값이 변경) 되었을때 처리하는 코드
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
    <div class="container">
        <div class="row">
            <div class="col-sm-12 col-md-12 col-xl-12">
                <ul class="nav nav-pills pople">
                    <li class="nav-item">
                        <a class="text-sm-center nav-link"
                           href="${pageContext.request.contextPath}/proposal/proposalrec.do">받은 제안서</a>
                    </li>
                    <li class="nav-item">
                        <a class="text-sm-center nav-link"
                           href="${pageContext.request.contextPath}/proposal/proposalsend.do">보낸 제안서</a>
                    </li>
                    <li class="nav-item">
                        <a class="text-sm-center nav-link active"
                           href="${pageContext.request.contextPath}/proposal/proposalwrite.do">제안서 작성</a>
                    </li>
                </ul>
            </div>
            <div class="my-4 py-5">
                <h4 class="font-weight-bolder mt-2 mb-4">제안서 작성</h4>
                <form action="/dressfi/proposal/proposalwrite.do" method="post" enctype="multipart/form-data">

                    <div class="form-row">
                        <div class="form-group col-xl-9 col-md-8 col-sm-12">
                            <label for="sender">받는 사람</label>
                            <select class="form-control" style="height: 40px;" id="pr_target" name="pr_target">
                                <option value="0">모든 회원</option>
                                <option value="1">디자이너회원 전체</option>
                                <option value="2">공장회원 전체</option>
                                <option value="3">도&middot;소매 회원 전체</option>
                                <option value="4">팔로잉</option>
                            </select>
                        </div>
                    </div>

                    <div class="form-row">
                        <div class="form-group col-md-4">
                            <label for="p_ano1">선호지역&nbsp;1</label>
                            <select class="form-control" style="height: 40px;" id="p_ano1" name="p_ano1">
                                <option value="0">전국</option>
                                <option value="10">서울</option>
                                <option value="20">강원</option>
                                <option value="30">대전</option>
                            </select>
                        </div>
                    </div>

                    <div class="form-row">
                        <div class="form-group col-xl-6 col-md-6 col-sm-12">
                            <label for="sender">작업시작일</label>
                            <input name="pr_startdate" type="text" class="form-control" style="height: 40px;"
                                   id="sender" placeholder="20190101">
                        </div>
                        <div class="form-group col-xl-6 col-md-6 col-sm-12">
                            <label for="sender">작업종료일</label>
                            <input type="text" name="pr_enddate" class="form-control" style="height: 40px;" id="sender"
                                   placeholder="20191231">
                        </div>
                    </div>
                    <input type="hidden" name="pr_fileloc" id="pr_fileloc">
                    <div class="form-row">
                        <div class="form-group col-xl-12 col-md-12 col-sm-12">
                            <label for="p_title">제목</label>
                            <input type="text" class="form-control" style="height: 40px;" id="pr_title" name="pr_title">
                        </div>
                        <div class="form-group col-xl-12 col-md-12 col-sm-12 pl-4">
                            <label for="imgInp" class=" ">첨부파일</label>
                            <input type="file" class="form-control-file" id="imgInp" name="file">
                        </div>
                        <div class="form-group col-xl-12 col-md-12 col-sm-12">
                            <textarea class="form-control" rows="9" aria-label="With textarea"
                                      name="pr_context"></textarea>
                        </div>
                    </div>
                    <!-- 						<div class="input-group">
                        <div class="input-group-prepend">
                            <span class="input-group-text">With textarea</span>
                        </div>
                        <textarea class="form-control" aria-label="With textarea"></textarea>
                    </div> -->
                    <div class="form-row d-flex justify-content-end">
                        <div class="col-xl-6 col-md-12 col-sm-12">
                            <button type="submit" class="col-xl-6 col-md-6 col-sm-6 btn btn-dark active w-50">보내기
                            </button>
                            <button type="reset" class="col-xl-6 col-md-6 col-sm-6 btn btn-light active w-50 ">취소
                            </button>
                        </div>
                    </div>
                </form>
            </div>
        </div>

    </div>

</section>
</body>
</html>