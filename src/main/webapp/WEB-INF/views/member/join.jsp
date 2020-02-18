<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>일반회원가입</title>
<style>
#hr {
	display: block;
	margin-top: 0.5em;
	margin-left: auto;
	margin-right: auto;
	border-style: inset;
	border-width: 1px;
}

.notuser a {
	font-stretch: expanded;
	font-size: 1.0em;
}

.notuser a { /*클릭하지 않은 링크*/
	color: black;
	text-decoration: none;
}

.notuser a:hover { /*링크를 클릭하려고 마우스를 가져갔을때*/
	color: gray;
	text-decoration: none; /*밑줄 없음*/
}

.form-group .row .col-lg-8 {
	font-size: 0.813em;
}

a img {
	width: 1.5em;
	height: 1.5em;
}

a span {
	font-stretch: expanded;
	font-size: 1.5em;
}
</style>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/profile.css">
<!-- 구글 icon -->
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.0/css/all.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
<script>
	/* 	//유효성 검사를 위한 소스
	 // Disable form submissions if there are invalid fields
	 (function() {
	 'use strict';
	 window.addEventListener('load', function() {
	 // Get the forms we want to add validation styles to
	 var forms = document.getElementsByClassName('needs-validation');
	 // Loop over them and prevent submission
	 var validation = Array.prototype.filter.call(forms, function(form) {
	 form.addEventListener('submit', function(event) {
	 if (form.checkValidity() === false) {
	 event.preventDefault();
	 event.stopPropagation();
	 }
	 form.classList.add('was-validated');
	 }, false);
	 });
	 }, false);
	 })(); */
	function Check_Pass() {
		if (join.passwd.value != join.pass2.value) {
			alert("패스워드가 일치하지않습니다.");
			join.passwd.value = "";
			join.pass2.value = "";
			join.passwd.focus();
			return false;
		}
		//alert("수정이 완료되었습니다.");
		return true;
	}
</script>
<script>
	//전체동의를 위한 소스
	$(document).ready(function() {
		$('.check-all').click(function() {
			$('.ck').prop('checked', this.checked);
		});
	});
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
<body class="bg-light">
	<header class="mb-2 bg-white">
		<div class="container p-auto">
			<div class="row navbar navbar-light">
				<a class="col-lg-6 offset-lg-3 navbar-brand" href="/dressfi/">
					<img src="<%=request.getContextPath()%>/resources/images/svg/clothing-hanger.svg" class="d-inline-block align-baseline" alt="logo">
					<span class="d-inline-block align-bottom">DressFi</span>
				</a>
			</div>
		</div>
	</header>
	<section>
		<div class="container">
			<div class="row">
				<div class="col-lg-6 offset-lg-3">
					<form:form modelAttribute="userVO" action="/dressfi/member/join.do" onsubmit="return Check_Pass()" method="post" name="join" id="join">
						<div class="form-group needs-validation mt-3">
							<h4 class="font-weight-bolder">일반회원</h4>
						</div>
						<div class="form-group">
							<label for="userId">아이디</label>
							<span class="font-weight-bold text-danger">
								<small>&nbsp;(필수)</small>
							</span>
							<form:input type="text" class="form-control col-12" id="userId" path="userId" name="userId" placeholder="이메일을 입력하세요" />
						</div>
						<div class="form-group">
							<label for="passwd">비밀번호</label>
							<span class="font-weight-bold text-danger">
								<small>&nbsp;(필수)</small>
							</span>
							<form:password class="form-control text-truncate" id="passwd" path="passwd" name="passwd" placeholder="6~16자의 영문, 숫자, 특수기호를 입력하세요" minlength="6" maxlength="16" />
						</div>
						<div class="form-group">
							<label for="pass2">비밀번호 확인</label>
							<span class="font-weight-bold text-danger">
								<small>&nbsp;(필수)</small>
							</span>
							<input type="password" class="form-control" id="pass2" name="pass2" minlength="6" maxlength="16" />
						</div>
						<div class="form-group">
							<label for="name">닉네임</label>
							<span class="font-weight-bold text-danger">
								<small>&nbsp;(필수)</small>
							</span>
							<!-- 는 boolean과 같으므로  설정하면 false이다-->
							<form:input type="text" class="form-control" id="name" path="name" name="name" maxlength="10" />
						</div>
						<div class="form-group">
							<label for="phone">휴대전화</label>
							<span class="font-weight-bold text-danger">
								<small>&nbsp;(필수)</small>
							</span>
							<form:input type="text" class="form-control" id="phone" path="phone" name="phone" maxlength="11" placeholder="-없이 입력하세요" />
						</div>
						<div class="form-group">
							<label for="homepage">홈페이지</label>
							<form:input type="text" class="form-control" id="homepage" path="homepage" name="homepage" placeholder="http://~" />
						</div>
						<div class="form-group">
							<label for="introduce">소개글</label>
							<form:input type="text" class="form-control" id="introduce" path="introduce" name="introduce" maxlength="40" placeholder="40자이내 자신을 간단히 소개하세요" />
						</div>
						<div class="form-group">
							<label for="kakaourl">카카오오픈채팅주소</label>
							<form:input type="text" class="form-control" id="kakaourl" path="kakaourl" name="kakaourl" />
						</div>
						<div class="form-group">
							<label>프로필사진</label>
							<div>
								<!-- 	<a href="#" class="for btn"> -->
								<!-- 클릭 시 사진 업로드 -->
								<img id="blah" class="rounded mb-1 img-fluid" style="width: 150px; height: 150px; background: gray;" />
							</div>
							<div>
								<form:input path="proimg" type="file" id="imgInp" name="file" />
							</div>
						</div>
						<!-- <div class="form-group">
							<label>약관동의</label>
							<div class="border rounded bg-white pt-4 pl-2">
								<div class="form-group row">
									<label for="check1" class="col-lg-8 col-9">
										만 14세 이상입니다 <span class="font-weight-bold text-danger">&nbsp;[필수]</span>
									</label>
									<div class="custom-control custom-checkbox col-lg-4 col-3">
										<input type="checkbox" class="ck custom-control-input" id="check1">
										<label class="custom-control-label" for="check1">
											<span class="font-weight-bold">동의</span>
										</label>
									</div>
								</div>
								<div class="form-group row">
									<label for="check2" class="col-lg-8 col-9">
										<a class="text-dark font-weight-bolder" href="join_check2.jsp">이용약관</a>
										<span class="font-weight-bold text-danger">&nbsp;[필수]</span>
									</label>
									<div class="custom-control custom-checkbox col-lg-4 col-3">
										<input type="checkbox" class="ck custom-control-input" id="check2" />
											<label class="custom-control-label" for="check2">
												<span class="font-weight-bold">동의</span>
											</label>
									</div>
								</div>
								<div class="form-group row">
									<label for="check3" class="col-lg-8 col-9">
										<a class="text-dark font-weight-bolder" href="#">개인정보취급방침</a>
										<span class="font-weight-bold text-danger">&nbsp;[필수]</span>
									</label>
									<div class="custom-control custom-checkbox col-lg-4 col-3">
										<input type="checkbox" class="ck custom-control-input" id="check3" />
											<label class="custom-control-label" for="check3">
												<span class="font-weight-bold">동의</span>
											</label>
									</div>
								</div>
								<div class="form-group row">
									<label for="check4" class="col-lg-8 col-9">
										이벤트 등 프로모션 알림 메일 수신<span class="font-weight-bold text-muted">&nbsp;[선택]</span>
									</label>
									<div class="custom-control custom-checkbox col-lg-4 col-3">
										<input type="checkbox" class="ck custom-control-input" id="check4" />
											<label class="custom-control-label" for="check4">
												<span class="font-weight-bold">동의</span>
											</label>
									</div>
								</div>
								<div class="form-group row">
									<label class="d-print-none col-lg-8 col-9"></label>
									<div class="custom-control custom-checkbox col-lg-4 col-3">
										<input type="checkbox" class="check-all custom-control-input" id="check-all" />
											<label for="check-all" class="custom-control-label">
												<span class="font-weight-bold">전체동의</span>
											</label>
									</div>
								</div>
							</div>
						</div> -->
						<button type="submit" class="btn btn-secondary w-100">가입하기</button>
					</form:form>
				</div>
			</div>
		</div>
	</section>
	<footer class="footer bg-light mt-5">
		<div class="container p-auto">
			<div class="row sticky-bottom justify-content-center">
				<div>
					<p class="text-muted">Copyright © 2019 by DressFi Republic</p>
				</div>
			</div>
		</div>
	</footer>
</body>
</html>