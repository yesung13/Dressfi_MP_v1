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

        .table {
            word-wrap: break-word;
            word-break: break-all;
        }

        .table th {
            font-family: 'Nanum Gothic', sans-serif;
            font-size: 0.938em;
            font-weight: 700;
            color: black;
        }

        .table td {
            font-family: 'Gothic A1', sans-serif;
            font-size: 0.813em;
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
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/profile.css">
    <!-- 구글 icon -->
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.0/css/all.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
    <script type="text/javascript">
        function fnCmdDelete(sampleNo) {
            document.designForm.mdno.value = sampleNo;
            document.designForm.action = "MDdeletec.do";
            document.designForm.submit();
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
                                <a class="dropdown-item" href="/dressfi/member/logout.do">로그아웃</a>
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
<section>
    <div class="container mt-1">
        <!-- Nav tabs -->
        <ul class="nav nav-tabs flex-column flex-sm-row">
            <li class="nav-item">
                <a class="flex-sm-fill text-sm-center nav-link" href="/dressfi/member/adminlist.do">일반회원</a>
            </li>
            <li class="nav-item">
                <a class="flex-sm-fill text-sm-center nav-link active" href="/dressfi/member/adminMDlist.do">디자이너회원</a>
            </li>
            <li class="nav-item">
                <a class="flex-sm-fill text-sm-center nav-link" href="/dressfi/member/adminMFlist.do">공장회원</a>
            </li>
            <li class="nav-item">
                <a class="flex-sm-fill text-sm-center nav-link" href="/dressfi/member/adminMClist.do">도소매회원</a>
            </li>
        </ul>

        <!-- Tab panes -->
        <div class="tab-content">

            <!-- 디자이너회원 리스트-->
            <div id="pro1" class="container">
                <h4 class="font-weight-bolder mt-4 mb-4">디자이너회원 리스트</h4>
                <!-- 위와 같이 테이블 넣어주면됨  -->
                <form:form id="designForm" name="designForm" method="post" class="all_layout">
                    <!--POST방식 -->
                    <input type="hidden" name="mdno"/>
                    <!--사용자에게 노출시키지 않고 개발단에서 특정 정책에 의해 액션을 취할때 사용 , 서버에 데이터 전송할때 사용-->
                    <table class="table table-bordered mt-3 ">
                        <thead>
                        <tr class="table-info text-center">
                            <th>번호</th>
                            <th>아이디</th>
                            <th>이름</th>
                            <th>휴대폰번호</th>
                            <th>홈페이지</th>
                            <th>카카오오픈채팅주소</th>
                            <th>가입일</th>
                            <th>삭제</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="result" items="${dlist}" varStatus="status">
                            <tr align="center">
                                <td>${result.mdno}</td>
                                <td>${result.md_userId}</td>
                                <td>${result.md_name}</td>
                                <td>${result.md_phone}</td>
                                <td>${result.md_homepage}</td>
                                <td>${result.md_kakaourl}</td>
                                <td>${result.md_joindate}</td>
                                <td>
                                    <a href="#" onclick="fnCmdDelete('${result.mdno}')" class="btn btn-info">삭제</a>
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