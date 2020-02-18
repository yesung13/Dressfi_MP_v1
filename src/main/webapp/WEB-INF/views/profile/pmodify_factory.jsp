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
<title>프로필작성</title>
<style>
/* 구글 웹폰트 */
@import
	url('https://fonts.googleapis.com/css?family=Gothic+A1|Nanum+Gothic|Noto+Sans+KR&display=swap')
	;

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

a img[src="<c:url value='/resources/img/${p_img}'/>"] {
	width: 2.0em;
	height: 2.0em;
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
<!-- 다음 주소  -->
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<script>
	function DaumPostcode() {
		new daum.Postcode(
				{
					oncomplete : function(data) {

						var fullAddr = ''; // 최종 주소 변수
						var extraAddr = ''; // 조합형 주소 변수

						if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
							fullAddr = data.roadAddress;

						} else { // 사용자가 지번 주소를 선택했을 경우(J)
							fullAddr = data.jibunAddress;
						}

						if (data.userSelectedType === 'R') {

							if (data.bname !== '') {
								extraAddr += data.bname;
							}

							if (data.buildingName !== '') {
								extraAddr += (extraAddr !== '' ? ', '
										+ data.buildingName : data.buildingName);
							}
							// 조합형주소의 유무에 따라 양쪽에 괄호를 추가하여 최종 주소를 만든다.
							fullAddr += (extraAddr !== '' ? ' (' + extraAddr
									+ ')' : '');
						}

						// 우편번호와 주소 정보를 해당 필드에 넣는다.
						document.getElementById('zip').value = data.zonecode; //5자리 새우편번호 사용
						document.getElementById('addr1').value = fullAddr;

						document.getElementById('addr2').value = "";
						// 커서를 상세주소 필드로 이동한다.
						document.getElementById('addr2').focus();
					}
				}).open();
	}
</script>
<script src="http://code.jquery.com/jquery-1.9.1.min.js"></script>
<script>
	$(document).ready(function() {
		function readURL(input) {
			if (input.files && input.files[0]) {
				var reader = new FileReader(); //파일을 읽기 위한 FileReader객체 생성 
				reader.onload = function(e) { //파일 읽어들이기를 성공했을때 호출되는 이벤트 핸들러 
					$('#blah').attr('src', e.target.result);
					//이미지 Tag의 SRC속성에 읽어들인 File내용을 지정 
					//(아래 코드에서 읽어들인 dataURL형식) 
				}
				reader.readAsDataURL(input.files[0]);
				//File내용을 읽어 dataURL형식의 문자열로 저장 
			}
		}//readURL()-- //file 양식으로 이미지를 선택(값이 변경) 되었을때 처리하는 코드 
		$("#imgInp").change(function() {
			//alert(this.value); 
			//선택한 이미지 경로 표시 
			readURL(this);
		});
	});
</script>

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
									<a class="text-secondary dropdown-toggle" href="#" id="navbarDropdown2-1" role="button" data-toggle="dropdown" data-display="static" aria-haspopup="true" aria-expanded="false">
										<span class="text-secondary mr-2">${userName}님</span>
										<img src="<c:url value="/resources/img/${p_img}/"/>" alt="프로필사진" height="18" class="rounded-circle">
									</a>
									<div class="dropdown-menu my-dr dropdown-menu-lg-right" aria-labelledby="navbarDropdown1">
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
	<!--//메뉴-->

	<!-- 회원정보수정 부분 -->
	<section>
		<div class="container mt-1">
			<div class="row">
				<div class="col-sm-12 col-md-12 col-xl-12">
					<ul class="nav nav-pills pople justify-content-center">
						<li class="nav-item">
							<a class="text-sm-center nav-link active" href="${pageContext.request.contextPath}/profile/pmodify.do">프로필수정</a>
						</li>
						<li class="nav-item">
							<a class="text-sm-center nav-link" href="${pageContext.request.contextPath}/profile/peditpasswd.do">비밀번호변경</a>
						</li>
					</ul>
				</div>
				<div class="border my-4 py-5">
					<div class="px-3 px-sm-3 px-xl-5">
						<h4 class="font-weight-bolder mt-2 mb-4">프로필수정</h4>
						<form action="/dressfi/profile/fpmodify.do" method="post" enctype="multipart/form-data">
							<table class="table table-borderless">
								<tr>
									<td>
										<input type="hidden" id="mfno" name="mfno" class="form-control" value="${loginUser.mno}" />
									</td>
								</tr>
								<tr>
									<th>
										<span class="">아이디</span>
									</th>
									<td colspan="2">
										<input type="text" class="form-control disabled" placeholder="사용자 아이디 보이기" value="${userId}" name="userId" id="userId" readonly>
										<!-- 사용자 아이디 불러오기(수정불가능) -->
									</td>
									<td></td>
									<!-- 공간맞춘것 지우지 말것 -->
									<td></td>
								</tr>
								<tr>
									<th>
										<span class="">프로필 사진</span>
									</th>
									<td colspan="2">
										<a href="#">
											<!-- 클릭 시 사진 업로드 -->
											<img id="blah" src="<c:url value="/resources/img/${p_img}/"/>" class="rounded img-fluid float-left" alt="..." style="width: 150px; height: 150px"/>
											<input type="file" id="imgInp" name="file" />
										</a>
									</td>
								</tr>
								<tr>
									<th>
										<span class="">닉네임</span>
									</th>
									<td colspan="2">
										<input type="text" class="form-control" placeholder="사용자 성명 보이기" value="${loginUser.name}" readonly>
										<!-- 사용자 이름 불러오기(수정불가능) -->
									</td>

								</tr>
								<tr>
									<th>
										<span class="">휴대전화</span>
									</th>
									<td colspan="2">
										<input type="text" id="mf_phone" name="mf_phone" class="form-control" maxlength="11" value="${profile.mf_phone}">
										<!-- 사용자 휴대번호 불러오기 -->
									</td>
								</tr>
								<tr>
									<th>
										<span class="">홈페이지</span>
									</th>
									<td colspan="2">
										<input type="text" id="mf_homepage" name="mf_homepage" class="form-control" placeholder="주소입력" value="${profile.mf_homepage}" />
										<!--주소링크 가능하게 하여 입력받으면 링크 걸리게 만들기 -->
									</td>
								</tr>
								<tr>
									<th>
										<span class="">공장분야</span>
									</th>
									<td colspan="2">
										<div class="input-group w-50">
											<select class="custom-select" name="mf_dept">
												<option value="1">도로시공장</option>
												<option value="2">다이마루공장</option>
												<option value="3">직기공장</option>
												<option value="4">특수공장</option>
											</select>
										</div>
									</td>
								</tr>
								<tr>
									<th>
										<span class="">사업자등록</span>
									</th>
									<td colspan="2">
										<div class="input-group w-50">
											<select class="custom-select" path="mf_business" name="mf_business" id="mf_business">
												<option value="1">미등록(프리랜서)</option>
												<option value="2">등록(개인사업자)</option>
												<option value="3">등록(법인사업자)</option>
												<option value="4">등록(브랜드대리점)</option>
											</select>
										</div>
									</td>

								</tr>
								<tr>
									<th>
										<span class="">사업자번호</span>
									</th>
									<td colspan="2">
										<input type="text" id="mf_bnum" name="mf_bnum" class="form-control" maxlength="10" value="${profile.mf_bnum}" />
										<!--주소링크 가능하게 하여 입력받으면 링크 걸리게 만들기 -->
									</td>
								</tr>
								<tr>
									<th>
										<span class="">상&nbsp;호&nbsp;명</span>
									</th>
									<td colspan="2">
										<input type="text" id="mf_bname" name="mf_bname" class="form-control" placeholder="주소입력" value="${profile.mf_bname}" />
										<!--주소링크 가능하게 하여 입력받으면 링크 걸리게 만들기 -->
									</td>
								</tr>
								<tr>
									<th>
										<span class="">사업장주소</span>
									</th>
									<td colspan="2">
										<div class="input-group w-50 mb-1">
											<input type="text" class="form-control col-12" id='mf_bzip' path='mf_bzip' name='mf_bzip' size='7' maxlength='7' value="${profile.mf_bzip}" />
											<div class="input-group-append">
												<input type="button" class="btn btn-outline-secondary" onClick="DaumPostcode()" id="button-addon2" value="우편번호">
											</div>
										</div>
										<input type="text" class="form-control mb-1 w-75" maxlength='50' size='50' id='mf_badd1' name='mf_badd1' value="${profile.mf_badd1 }" path='mf_badd1' />
										<input type="text" class="form-control w-75" maxlength='50' size='50' id='mf_badd2' name='mf_badd2' path='mf_badd2' value="${profile.mf_badd2 }" />
									</td>
								</tr>

								<tr>
									<th>
										<span class="">활동지역</span>
									</th>
									<td colspan="2">
										<div class="form-inline">
											<select class="custom-select" name="mf_ano1">
												<option value="10">서울</option>
												<option value="20">강원</option>
												<option value="30">대천</option>
											</select>
											<select class="custom-select mx-2" name="mf_ano2">
												<option value="10">서울</option>
												<option value="20">강원</option>
												<option value="30">대천</option>
											</select>
											<select class="custom-select" name="mf_ano3">
												<option value="10">서울</option>
												<option value="20">강원</option>
												<option value="30">대천</option>
											</select>
										</div>
									</td>
								</tr>
								<tr>
									<th>
										<span class="">소개글</span>
									</th>
									<td colspan="2">
										<input type="text" id="mf_introduce" name="mf_introduce" class="form-control" placeholder="입력" value="${profile.mf_introduce}" />
										<!-- text로 입력받기 -->
									</td>
								</tr>
								<tr>
									<th>
										<span class="">카카오톡 오픈채팅</span>
									</th>
									<td colspan="2">
										<input type="text" id="mf_kakaourl" name="mf_kakaourl" class="form-control" placeholder="입력" value="${profile.mf_kakaourl}" />
										<!-- text로 입력받기 -->
									</td>
								</tr>
								<tr>
									<th></th>
									<td colspan="2">
										<button type="submit" class="btn btn-secondary active w-100">회원 정보 저장</button>
									</td>
								</tr>
							</table>
						</form>

					</div>
				</div>
			</div>

		</div>
	</section>
</body>
</html>