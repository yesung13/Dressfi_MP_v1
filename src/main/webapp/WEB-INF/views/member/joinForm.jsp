<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ include file="./jsp_header.jsp" %>
<HTML>
<HEAD>
    <TITLE>회원 등록</TITLE>
    <META http-equiv="Content-Type" content="text/html; charset=utf-8">
    <link rel="stylesheet" type="text/css" href="resources/css/style.css">
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
                            fullAddr += (extraAddr !== '' ? ' (' + extraAddr
                                + ')' : '');
                        }
                        // 우편번호와 주소 정보를 해당 필드에 넣는다.
                        document.getElementById('zip').value = data.zonecode; //5자리 새우편번호 사용
                        document.getElementById('addr1').value = fullAddr;//기본주소
                        document.getElementById('addr2').value = ""; //상세주소 클리어
                        document.getElementById('addr2').focus(); // 커서를 상세주소 필드로 이동한다.
                    }
                }).open();
        }
    </script>

    <SCRIPT>
        function Check_id() {
            if (joinForm.id.value.length < 1) {
                alert("아이디를 입력하세요.");
                joinForm.id.focus();
                return false;
            }

            var loc = '/dressfi/checkid.do?userId=' + joinForm.id.value
            location.href = loc;
        }

        function Check_Dup() {
            if (joinForm.re_id.value != "1") {
                alert("중복확인을 클릭하여 주세요.");
                joinForm.id.value = "";
                joinForm.id.focus();
                return false;
            }
            if (joinForm.pass.value != joinForm.pass2.value) {
                alert("패스워드가 일치하지않습니다.");
                joinForm.pass.value = "";
                joinForm.pass2.value = "";
                joinForm.pass.focus();
                return false;
            }
            return true;
        }
    </SCRIPT>
</HEAD>

<BODY>
<br>
<br>
<center>
    <font size='3'><b> 회원 가입 </b></font>
    <TABLE border='0' width='700' cellpadding='0' cellspacing='0'>
        <TR>
            <TD>
                <hr size='1' noshade>
            </TD>
        </TR>
    </TABLE>
    <p align="left">
        <span class="fieldError">${errMsg}</span>
    </p>
    <br>

    <form:form modelAttribute="user" Name='joinForm' Method='post' Action='/dressfi/join.do'
               onsubmit='return Check_Dup()'>
    <!-- return 값이 TRUE이면 실행,F면 XX -->

    <TABLE border='2' width='600' cellSpacing=0 cellPadding=5 align=center>
        <TR>
            <TD bgcolor='cccccc' width='100' align='center'>
                <font size='2'>아 이 디</font>
            </TD>
            <TD bgcolor='cccccc'>
                <form:input path="id" maxLength='20' size='10' value="${userId}" cssClass="userId"/>
                <input type='button' OnClick='Check_id()' value='ID 중복검사'>
                <span class="fieldError">
							<form:errors path="id"/>
						</span>
                <span class="fieldError"> *${message}</span>
                <input type="hidden" name="re_id" value="${reDiv}">
            </TD>
        </TR>
        <TR>
            <TD bgcolor='cccccc' align='center'>
                <font size='2'>비 밀 번 호</font>
            </TD>
            <TD bgcolor='cccccc'>
                <form:password path="pass" maxlength="20" cssClass="password" size='10'/>
                <span class="fieldError">
							<form:errors path="pass"/>
						</span>
            </TD>
        </TR>
        <TR>
            <TD bgcolor='cccccc' align='center'>
                <font size='2'>비밀번호확인</font>
            </TD>
            <TD bgcolor='cccccc'>
                <input type="password" name="pass2" maxlength="20" cssClass="password" size='10' value=""
                       showPassword="true"/>
            </TD>
        </TR>
        <TR>
            <TD bgcolor='cccccc' align='center'>
                <font size='2'>이 름</font>
            </TD>
            <TD bgcolor='cccccc'>
                <form:input path="name" maxlength="20" cssClass="name" size='10'/>
                <span class="fieldError">
							<form:errors path="name"/>
						</span>
            </TD>
        </TR>
        <TR>
            <TD bgcolor='cccccc' align='center'>
                <font size='2'>우 편 번 호</font>
                <span class="fieldError">
            </TD>
            <TD bgcolor='cccccc'>
                <form:input path='zip' maxlength='7' size='7'/>
                <input type='button' OnClick='DaumPostcode()' value='우편번호검색'>
                <span class="fieldError">
							<form:errors path="zip"/>
						</span>
            </TD>
        </TR>
        <TR>
            <TD bgcolor='cccccc' align='center'>
                <font size='2'>주 소</font>
            </TD>
            <TD bgcolor='cccccc'>
                <form:input maxlength='50' size='50' path='addr1'/>
                <span class="fieldError">
							<form:errors path="addr1"/>
						</span>
                <BR>
                <form:input maxlength='50' size='50' path='addr2'/>
                <span class="fieldError">
							<form:errors path="addr2"/>
						</span>
            </TD>
        </TR>
        <TR>
            <TD bgcolor='cccccc' align='center'>
                <font size='2'>전 화 번 호</font>
            </TD>
            <TD bgcolor='cccccc'>
                <input type='text' maxlength='20' size='20' name='phone'>
                <span class="fieldError">
							<form:errors path="phone"/>
						</span>
            </TD>
        </TR>
        <TR>
            <TD bgcolor='cccccc' align='center'>
                <font size='2'>E - M a i l</font>
            </TD>
            <TD bgcolor='cccccc'>
                <input type='text' maxlength='50' size='50' name='email'>
                <span class="fieldError">
							<form:errors path="email"/>
						</span>
            </TD>
        </TR>
    </TABLE>
    <br>
    <TABLE border='0' width='700' cellpadding='0' cellspacing='0'>
        <TR>
            <TD>
                <hr size='1' noshade>
            </TD>
        </TR>
    </TABLE>
    <TABLE>
        <TR>
            <TD colspan='2' align='center'>
                <input type='submit' value='회원가입'>
            </TD>
        </TR>
    </TABLE>
    </form:form>
</BODY>
</HTML>