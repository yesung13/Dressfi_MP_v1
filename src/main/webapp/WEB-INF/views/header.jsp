<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    //세션값 받기
    Object userid = session.getAttribute("userid");
    session.setAttribute("userid", userid);
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>DressFi index</title>
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
    <link rel="stylesheet" type="text/css" href="../../resources/css/profile.css">
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
                <img src="../../resources/images/svg/clothing-hanger.svg" class="d-inline-block align-top" alt="logo"> <span
                    class="d-inline-block align-bottom">DressFi</span>
            </a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
                    aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>

            <div class="collapse navbar-collapse" id="navbarSupportedContent">
                <ul class="navbar-nav mr-auto">
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" id="nbd_1" role="button" data-toggle="dropdown"
                           aria-haspopup="true" aria-expanded="false">Professional</a>
                        <div class="dropdown-menu" aria-labelledby="nbd_1">
                            <a class="dropdown-item" href="design.do">디자이너</a>
                            <a class="dropdown-item" href="#">공장</a>
                            <a class="dropdown-item" href="#">도소매</a>
                            <a class="dropdown-item" href="#">원단&부자재</a>
                        </div>
                    </li>
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" id="nbd_2" role="button" data-toggle="dropdown"
                           aria-haspopup="true" aria-expanded="false">Community</a>
                        <div class="dropdown-menu" aria-labelledby="nbd_2">
                            <a class="dropdown-item" href="knowhow.do">노하우</a>
                            <a class="dropdown-item" href="qanda.do">Q&A</a>
                        </div>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="notice.do" role="button">공지사항</a>
                    </li>
                </ul>

                <form class="form-inline my-2 my-lg-0">

                    <%
                        //아이디 세션이 없을경우 로그인
                        if (session.getValue("userid") == null) {
                    %>
                    <a class="text-secondary mr-2" href="join.do">
                        <i class="fas fa-user-alt"></i> 회원가입 |
                    </a>
                    <a class="text-secondary mr-2" href="login.do">
                        <i class="fas fa-lock"></i> 로그인
                    </a>
                    <%
                        // 세션에 아이디값이 있을경우 다른폼을 구성
                    } else {
                    %>
                    <a class="text-secondary mr-2" href="#" data-toggle="tooltip" data-placement="bottom" title="알림">
                        <img src="resources/images/svg/bell.svg" alt="알림">
                    </a>
                    <!-- 						<a class="text-secondary mr-2" href="logout.do">
                        <i class="fas fa-lock"></i> 로그아웃
                    </a> -->
                    <ul class="navbar-nav mr-auto">
                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" href="#" id="nbd_3" role="button" data-toggle="dropdown"
                               aria-haspopup="true" aria-expanded="false">
                                <img src="resources/images/profile_pic/m1.jpg" alt="프로필사진" class="rounded-circle">
                            </a>
                            <div class="dropdown-menu dropdown-menu-right" aria-labelledby="nbd_3">
                                <a class="dropdown-item" href="profile.do">마이홈</a>
                                <a class="dropdown-item" href="#">제안서관리</a>
                                <a class="dropdown-item" href="logout.do">로그아웃</a>
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
</body>
</html>