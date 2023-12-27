<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    //세션값 받기
    Object userId = session.getAttribute("userId");
    session.setAttribute("userId", userId);
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>DressFi_관리자</title>
    <style>
        /* 구글 웹폰트 */
        @import url('https://fonts.googleapis.com/css?family=Gothic+A1|Nanum+Gothic|Noto+Sans+KR&display=swap');

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

        .table th {
            font-family: 'Nanum Gothic', sans-serif;
            font-size: 0.938em;
            font-weight: 700;
            color: black;
        }

        .table td {
            font-family: 'Gothic A1', sans-serif;
            font-size: 0.875em;
            font-weight: 600;
        }

        .table td a {
            font-family: 'Nanum Gothic', sans-serif;
            font-size: 0.938em;
            font-weight: 700;
            text-decoration: none;
        }

        .table .etc span {
            font-family: 'Gothic A1', sans-serif;
            font-size: 0.813em;
            font-weight: 600;
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
    <script type="text/javascript">
        function fnCmdDelete(sampleNo) {
            document.boardForm.bno.value = sampleNo;
            document.boardForm.action = "deleteb.do";
            document.boardForm.submit();
        }
    </script>
</head>
<body>
<header class="header bg-light">
    <nav class="navbar sticky-top navbar-expand-lg navbar-light">
        <div class="container">
            <a class="navbar-brand mb-0 h1" href="<%=request.getContextPath()%>/member/adminlist.do">
                <img src="${pageContext.request.contextPath}/resources/images/svg/clothing-hanger.svg"
                     class="d-inline-block align-top" alt="logo">
                <span class="d-inline-block align-bottom">DressFi_관리자</span>
            </a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
                    aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>

            <div class="collapse navbar-collapse" id="navbarSupportedContent">
                <ul class="navbar-nav mr-auto">
                    <li class="nav-item">
                        <a class="nav-link" href="<%=request.getContextPath()%>/member/adminlist.do">회원관리</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="<%=request.getContextPath()%>/board/adminboard.do">게시판관리</a>
                    </li>
                </ul>

                <form class="form-inline my-2 my-lg-0">

                    <%
                        //아이디 세션이 없을경우 로그인
                        if (session.getValue("userId") == null) {
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
                        <img src="${pageContext.request.contextPath}/resources/images/svg/bell.svg" alt="알림">
                    </a>
                    <ul class="navbar-nav mr-auto">
                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" href="#" id="nbd_3" role="button" data-toggle="dropdown"
                               aria-haspopup="true" aria-expanded="false">
                                <img src="<c:url value="/resources/img/${p_img}/"/>" alt="프로필사진" height="18"
                                     class="rounded-circle">
                            </a>
                            <div class="dropdown-menu dropdown-menu-right" aria-labelledby="nbd_3">
                                <a class="dropdown-item" href="/dressfi/">메인페이지</a>
                                <a class="dropdown-item" href="logout.do">로그아웃</a>
                            </div>
                        </li>
                    </ul>
                    <%
                        }
                    %>
                </form>

            </div>
        </div>
    </nav>
</header>

<section class="mt-5">
    <div class="container">
        <div class="row">
            <div class="col-md-12">

                <h4 class="font-weight-bolder mb-4">게시판 리스트</h4>

                <form:form id="boardForm" name="boardForm" method="post" class="all_layout">
                    <!--POST방식 -->
                    <input type="hidden" name="bno"/>
                    <!--사용자에게 노출시키지 않고 개발단에서 특정 정책에 의해 액션을 취할때 사용 , 서버에 데이터 전송할때 사용-->

                    <table class="table table-bordered" style="margin-top: 5px">
                        <thead>
                        <tr class="table-info text-center">
                            <th>번호</th>
                            <th>제목</th>
                            <th>작성자</th>
                            <th>댓글수</th>
                            <th>조회수</th>
                            <th>추천수</th>
                            <th>작성일</th>
                            <th>삭제</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="result" items="${board}" varStatus="status">
                            <tr align="center">
                                <td>${result.bno }</td>
                                <td>${result.title }</td>
                                <td>${result.writerId }</td>
                                <td>${result.replycnt }</td>
                                <td>${result.viewcnt }</td>
                                <td>${result.recommendcnt }</td>
                                <td>${result.regDate }</td>
                                <td>
                                    <a href="#" onclick="fnCmdDelete('${result.bno}')" class="btn btn-info">삭제</a>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </form:form>

            </div>
        </div>
    </div>
</section>

</body>
</html>