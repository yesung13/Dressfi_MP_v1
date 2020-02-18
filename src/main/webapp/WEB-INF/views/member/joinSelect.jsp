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
<title>DressFi</title>
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
			join.pass.focus();
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

</head>
<body class="bg-light">
	<header class="mb-2 bg-white">
		<div class="container p-auto">
			<div class="row navbar navbar-light">
				<a class="navbar-brand" href="/dressfi/">
					<img src="${pageContext.request.contextPath}/resources/images/svg/clothing-hanger.svg" class="d-inline-block align-baseline" alt="logo">
					<span class="d-inline-block align-bottom">DressFi</span>
				</a>
			</div>
		</div>
	</header>
	<section>
		<div class="container my-5">
			<div class="row d-flex justify-content-center">
				<div class="d-flex justify-content-around">
					<div class="col-lg-6 col-md-6 col-sm-12 mb-4">
						<!-- offset-lg-3 -->
						<button class="btn btn-secondary btn-lg " style="width: 200px; height: 200px" onclick="location.href='join.do'">
							<img src="${pageContext.request.contextPath}/resources/images/member1.png" class="mx-auto d-block" alt="..">
							<div class="mt-2">
								<strong>일반회원</strong>
							</div>
						</button>
						<div class="text-center mt-1">
							<small style="font-size: 1.0em; color: gray" class="font-weight-bold"> &gt; 의류업계 입문인 회원</small>
						</div>
					</div>

					<div class="col-lg-6 col-md-6 col-sm-12">
						<button class="btn btn-info btn-lg " style="width: 200px; height: 200px" onclick="location.href='joinPro.do'">
							<img src="${pageContext.request.contextPath}/resources/images/pro1.png" class="mx-auto pl-4 d-block" alt="..">
							<div class="mt-2">
								<strong>전문가회원</strong>
							</div>
						</button>
						<div class="text-center mt-1">
							<small style="font-size: 1.0em; color: gray" class="font-weight-bold "> &gt; 의류업계 전문인 회원</small>
						</div>
					</div>
				</div>

			</div>
		</div>
	</section>
	<footer class="footer bg-light mt-5">
		<div class="container p-auto">
			<div class="row fixed-bottom justify-content-center">
				<div class="">
					<p class="text-muted">Copyright © 2019 by DressFi Republic</p>
				</div>
			</div>
		</div>
	</footer>
</body>
</html>