<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>전문가회원가입</title>
    <style>
        #hr {
            display: block;
            margin-top: 0.5em;
            margin-left: auto;
            margin-right: auto;
            border-style: inset;
            border-width: 1px;
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
        $(document).ready(function () {
            $('.check-all').click(function () {
                $('.ck').prop('checked', this.checked);
            });
        });
    </script>
    <!-- 다음 주소  -->
    <script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
    <script>
        function DaumPostcode() {
            new daum.Postcode(
                {
                    oncomplete: function (data) {

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
                        document.getElementById('mf_bzip').value = data.zonecode; //5자리 새우편번호 사용
                        document.getElementById('mf_badd1').value = fullAddr;

                        document.getElementById('mf_badd2').value = "";
                        // 커서를 상세주소 필드로 이동한다.
                        document.getElementById('mf_badd2').focus();
                    }
                }).open();
        }
    </script>
    <script src="http://code.jquery.com/jquery-1.9.1.min.js"></script>
    <script>
        $(document).ready(function () {
            function readURL(input) {
                if (input.files && input.files[0]) {
                    var reader = new FileReader(); //파일을 읽기 위한 FileReader객체 생성
                    reader.onload = function (e) { //파일 읽어들이기를 성공했을때 호출되는 이벤트 핸들러
                        $('#blah').attr('src', e.target.result);
                        //이미지 Tag의 SRC속성에 읽어들인 File내용을 지정
                        //(아래 코드에서 읽어들인 dataURL형식)
                    }
                    reader.readAsDataURL(input.files[0]);
                    //File내용을 읽어 dataURL형식의 문자열로 저장
                }
            }//readURL()-- //file 양식으로 이미지를 선택(값이 변경) 되었을때 처리하는 코드
            $("#imgInp").change(function () {
                //alert(this.value);
                //선택한 이미지 경로 표시
                readURL(this);
            });
        });
    </script>
</head>
<body class="bg-light">
<header class="mb-2 bg-white">
    <div class="container">
        <div class="row navbar navbar-light">
            <a class="navbar-brand" href="/dressfi/">
                <img src="<%=request.getContextPath()%>/resources/images/svg/clothing-hanger.svg"
                     class="d-inline-block align-baseline" alt="logo">
                <span class="d-inline-block align-bottom">DressFi</span>
            </a>
        </div>
    </div>
</header>
<section>
    <div class="container mt-1">
        <!-- Nav tabs -->
        <ul class="nav nav-tabs flex-column flex-sm-row justify-content-center">
            <li class="nav-item">
                <a class="flex-sm-fill text-sm-center nav-link" href="joinPro.do">디자이너회원</a>
            </li>
            <li class="nav-item">
                <a class="flex-sm-fill text-sm-center nav-link active" href="joinPro2.do">공장회원</a>
            </li>
            <li class="nav-item">
                <a class="flex-sm-fill text-sm-center nav-link" href="joinPro3.do">도소매회원</a>
            </li>
        </ul>

        <!-- 공장 회원가입 -->
        <div class="container">
            <div class=" my-4 mx-4">
                <div class="">
                    <h4 class="font-weight-bolder mt-2 mb-4">공장회원</h4>
                    <form action="/dressfi/member/joinPro2.do" onsubmit="return Check_Pass()" method="post" name="join"
                          id="join" enctype="multipart/form-data">
                        <table class="table table-borderless">
                            <%-- 							<tr>
                                    <th>
                                        <span class="">아이디</span><span class="font-weight-bold text-danger"><small>&nbsp;(필수)</small></span>
                                    </th>
                                    <td colspan="2">
                                        <div class="input-group w-75">
                                            <input type="text" class="form-control" path="mf_userId" id="mf_userId" name="mf_userId" placeholder="4~15자의 영문, 숫자를 입력하세요" minlength="4" maxlength="16" />
                                        </div>
                                        <!-- 사용자 아이디 불러오기(수정불가능) -->
                                    </td>
                                    <td></td>
                                    <!-- 공간맞춘것 지우지 말것 -->
                                    <td></td>

                                </tr> --%>
                            <tr>
                                <th>
                                    <span class="">아이디</span><span class="font-weight-bold text-danger"><small>&nbsp;(필수)</small></span>
                                </th>
                                <td colspan="2">
                                    <div class="input-group w-75">
                                        <input type="text" class="form-control col-12" id="mf_userId" path="mf_userId"
                                               name="mf_userId" placeholder="이메일을 입력히세요"/>
                                    </div>
                                </td>
                                <td></td>
                                <!-- 공간맞춘것 지우지 말것 -->
                                <td></td>
                            </tr>
                            <tr>
                                <th>
                                    <span>비밀번호</span><span
                                        class="font-weight-bold text-danger"><small>&nbsp;(필수)</small></span>
                                </th>
                                <td>
                                    <div class="input-group w-75">
                                        <input type="password" class="form-control text-truncate" path="mf_passwd"
                                               name="mf_passwd" id="mf_passwd" placeholder="6~16자의 영문, 숫자, 특수기호를 입력하세요"
                                               minlength="6" maxlength="16"/>
                                    </div>
                                </td>
                            </tr>
                            <tr>
                                <th>
                                    <span>비밀번호 확인</span><span
                                        class="font-weight-bold text-danger"><small>&nbsp;(필수)</small></span>
                                </th>
                                <td>
                                    <!--pass check 조건을 여러개 둬야됨  -->
                                    <div class="input-group w-75">
                                        <input type="password" class="form-control" name="pass2" minlength="6"
                                               maxlength="16">
                                    </div>
                                </td>
                            </tr>
                            <tr>
                                <th>
                                    <span class="">닉네임</span><span class="font-weight-bold text-danger"><small>&nbsp;(필수)</small></span>
                                </th>
                                <td colspan="2">
                                    <div class="input-group w-50">
                                        <input type="text" class="form-control" path="mf_name" id="mf_name"
                                               name="mf_name" maxlength="20"/>
                                    </div>
                                </td>
                            </tr>
                            <tr>
                                <th>
                                    <span class="">휴대전화</span><span class="font-weight-bold text-danger"><small>&nbsp;(필수)</small></span>
                                </th>
                                <td colspan="2">
                                    <div class="input-group w-75">
                                        <input type="text" class="form-control" id="mf_phone" path="mf_phone"
                                               name="mf_phone" maxlength="11"/>
                                    </div>
                                </td>
                            </tr>

                            <tr>
                                <th>
                                    <span class="">공장분야</span><span class="font-weight-bold text-danger"><small>&nbsp;(필수)</small></span>
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
                                    <span class="">사업자등록</span><span class="font-weight-bold text-danger"><small>&nbsp;(필수)</small></span>
                                </th>
                                <td colspan="2">
                                    <div class="input-group w-50">
                                        <select class="custom-select" path="mf_business" name="mf_business"
                                                id="mf_business">
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
                                    <div class="input-group w-75">
                                        <input type="text" class="form-control" path="mf_bnum" id="mf_bnum"
                                               name="mf_bnum" maxlength="10" placeholder="-없이  입력하세요"
                                               aria-describedby="button-addon2"/>
                                    </div>
                                </td>

                            </tr>
                            <tr>
                                <th>
                                    <span class="">상호명</span><span
                                        class="font-weight-bold"><small>&nbsp;(선택)</small></span>
                                </th>
                                <td colspan="2">
                                    <div class="input-group w-50">
                                        <input type="text" class="form-control" path="mf_bname" id="mf_bname"
                                               name="mf_bname" placeholder="소속된 기관명 또는 상호명"/>
                                    </div>
                                </td>
                            </tr>
                            <tr>
                                <th>
                                    <span class="">사업장주소</span>
                                </th>
                                <td colspan="2">
                                    <div class="input-group w-50 mb-1">
                                        <input type="text" class="form-control col-12" id='mf_bzip' path='mf_bzip'
                                               name='mf_bzip' size='7' maxlength='7'/>
                                        <div class="input-group-append">
                                            <input type="button" class="btn btn-outline-secondary"
                                                   onClick="DaumPostcode()" id="button-addon2" value="우편번호">
                                        </div>
                                    </div>
                                    <input type="text" class="form-control mb-1 w-75" maxlength='50' size='50'
                                           id='mf_badd1' name='mf_badd1' path='mf_badd1'/>
                                    <input type="text" class="form-control w-75" maxlength='50' size='50' id='mf_badd2'
                                           name='mf_badd2' path='mf_badd2'/>
                                </td>
                            </tr>
                            <tr>
                                <th>
                                    <span class="">활동지역</span><span class="font-weight-bold text-danger"><small>&nbsp;(필수)</small></span>
                                </th>
                                <td colspan="2">
                                    <div class="form-inline">
                                        <select class="custom-select" path="mf_ano1" name="mf_ano1">
                                            <option value="10">서울</option>
                                            <option value="20">강원</option>
                                            <option value="30">대천</option>
                                        </select>
                                        <select class="custom-select mx-2" path="mf_ano2" name="mf_ano2">
                                            <option value="10">서울</option>
                                            <option value="20">강원</option>
                                            <option value="30">대천</option>
                                        </select>
                                        <select class="custom-select" path="mf_ano3" name="mf_ano3">
                                            <option value="10">서울</option>
                                            <option value="20">강원</option>
                                            <option value="30">대천</option>
                                        </select>
                                    </div>
                                </td>
                            </tr>
                            <tr>
                                <th>
                                    <span class="">홈페이지</span><span
                                        class="font-weight-bold"><small>&nbsp;(선택)</small></span>
                                </th>
                                <td colspan="2">
                                    <div class="input-group w-50">
                                        <input type="text" class="form-control" path="mf_homepage" name="mf_homepage"
                                               id="mf_homepage" placeholder="http://~"/>
                                    </div>
                                    <!--주소링크 가능하게 하여 입력받으면 링크 걸리게 만들기 -->
                                </td>
                            </tr>
                            <tr>
                                <th>
                                    <span class="">소개글</span><span
                                        class="font-weight-bold"><small>&nbsp;(선택)</small></span>
                                </th>
                                <td colspan="2">
                                    <div class="input-group w-75">
                                        <textarea class="form-control" rows="5" path="mf_introduce" id="mf_introduce"
                                                  name="mf_introduce"></textarea>
                                    </div>
                                </td>
                            </tr>
                            <tr>
                                <th>
                                    <span class="">카카오오픈채팅주소</span><span
                                        class="font-weight-bold"><small>&nbsp;(선택)</small></span>
                                </th>
                                <td colspan="2">
                                    <div class="input-group w-50">
                                        <input type="text" class="form-control" path="mf_kakaourl" id="mf_kakaourl"
                                               name="mf_kakaourl" placeholder="http://~"/>
                                    </div>
                                </td>
                            </tr>
                            <tr>
                                <th>
                                    <span class="">프로필사진</span><span class="font-weight-bold"><small>&nbsp;(선택)</small></span>
                                </th>
                                <td colspan="2">
                                    <div class="input-group w-50">
                                        <!-- 	<a href="#" class="for btn"> -->
                                        <!-- 클릭 시 사진 업로드 -->
                                        <img id="blah" class="rounded mb-1 img-fluid"
                                             style="width: 150px; height: 150px; background: gray;"/>
                                        <input type="file" id="imgInp" name="file"/>
                                    </div>
                                </td>
                            </tr>


                            <tr>
                                <th></th>
                                <td colspan="2">
                                    <button type="submit" class="btn btn-secondary w-75">회원 정보 저장</button>
                                </td>
                            </tr>
                        </table>
                    </form>
                </div>
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