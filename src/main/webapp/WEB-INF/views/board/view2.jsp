<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>글 보기: ${board.title}</title>
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

.table th {
	font-family: 'Nanum Gothic', sans-serif;
	font-size: 0.938em;
	font-weight: 700;
}

.table td span {
	font-family: 'Gothic A1', sans-serif;
	font-size: 0.813em;
	font-weight: 500;
	color: gray;
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

.tcenter {
	text-align: center;
}

/* .commentView th {
	height: 25px;
	background-color: #b0e0e6;
	padding: 5px 5px 5px 5px;
}

.commentView td.writer {
	width: 20%;
	background-color: white;
	padding: 5px 10px 5px 10px;
}

.commentView td.content {
	width: 80%;
	background-color: white;
	padding: 5px 10px 5px 10px;
}

textarea.commentForm {
	width: 80%;
	height: 50px;
}

input.commentBt {
	margin-left: 2%;
	width: 15%;
} */
</style>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<!-- 구글 icon -->
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.7.0/css/all.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
<script type="text/javascript">
	function errCodeCheck(){
		var errCode = <%=request.getParameter("errCode")%>;
		if(errCode != null || errCode != ""){
			switch (errCode) {
			case 1:
				alert("댓글 내용이 없습니다!");
				break;
			case 2:
				alert("이미 추천하셨습니다!");
				break;
			case 3:
				alert("자기 글은 추천할 수 없습니다.!");
				break;
			case 4:
				alert("로그인하셔야 추천할 수 있습니다.!");
				break;
			}
		}		
	}
	
	function deleteReply(rno, bno){
		if(confirm("선택하신 댓글을 삭제하시겠습니까?")){
			location.href = "deleteReply.do?rno=" + rno + "&bno=" + bno;
		}		
	}
	
	function recommendReply(rno, bno){
		if(confirm("선택하신 댓글을 추천하시겠습니까?")){
			location.href = "recommendReply.do?rno=" + rno + "&bno=" + bno;	
		}		
	}
	
	function moveAction(where){
		switch (where) {
		case 1:
			if(confirm("글을 삭제하시겠습니까?")){
				location.href ="delete.do?bno=${board.bno}";
			}
			break;

		case 2:
			if(confirm("글을 수정하시겠습니까?")){
				location.href = "modify.do?bno=${board.bno}";
			}
			break;
			
		case 3:
			location.href = "list.do";			
			break;
			
		case 4:
			if(confirm("글을 추천하시겠습니까?")){
				location.href = "recommend.do?bno=${board.bno}";
			}
			break;
		}
	}
</script>
</head>
<body class="bg-light" onload="errCodeCheck()">
	<section class="mt-2">
		<div class="container">
			<h4 class="font-weight-bold">Q&A 게시판</h4>
			<table class="table table-bordered bg-light">


				<tr class="tcenter thead-dark">
					<th colspan="4">제목</th>
				</tr>
				<tr class="tcenter">
					<th colspan="4">
						<h5 class="text-left">${board.title}</h5>
					</th>
				</tr>
				<tr>
					<td colspan="4" align="left">
						<p>${board.content}</p>
						<br /> <br />
						<c:if test="${board.writerId != userId}">
							<p class="tcenter">
								<a href="#" onclick="moveAction(4)">
									<small class="text-primary"><i class="fas fa-thumbs-up"></i>&nbsp;추천 </small>
								</a>
								<span>
									<small class="text-primary">:${message}</small>
								</span>
							</p>
						</c:if>
					</td>
				<tr class="tcenter">

					<th>첨부파일</th>
					<td colspan="4" align="left">
						<c:forEach var="file" items="${fileList}">
							<a href="filedown.do?fileName=${file.ofilename}" class="fileview">
								<img src="<c:url value="/images/${file.ofilename}"/>" width="300" />
								<font size="2px">${file.ofilename}</font> <font size="2px">(${file.filesize} byte)</font><br>
							</a>
						</c:forEach>
						<c:if test="${empty fileList}">
							<font color="#A6A6A6" size="2px"> 첨부된 파일이 없습니다. </font>
						</c:if>
					</td>
				</tr>



				<tr class="tcenter thead-dark">
					<th>작성자</th>
					<th>조회수</th>
					<th>추천수</th>
					<th>작성일</th>
				</tr>
				<tr class="tcenter">
					<td>${board.writerId}</td>
					<td>${board.viewcnt}</td>
					<td>${board.recommendcnt}</td>
					<td>${board.regDate}</td>
				</tr>
			</table>
		</div>
	</section>
	<section>
		<div class="container">
			<table class="table table-bordered">
				<tr class="tcenter thead-dark">
					<th colspan="2">댓글</th>
				</tr>
				<c:forEach var="comment" items="${replyList}">
					<tr class="tcenter">
						<td class="font-weight-bold">
							<p>${comment.writerId}
								<c:if test="${comment.writerId == userId}">
									<br />
									<a href="#" onclick="deleteReply(${comment.rno}, ${comment.bno})">
										<span>
											<small class="text-danger">삭제</small>
										</span>
									</a>
								</c:if>
							</p>
						</td>
						<td class="content" align="left">
							<span class="date">${comment.regDate}</span>
							<p class="text-break">${comment.content}</p>
							<c:if test="${comment.writerId != userId}">
								<p class="tcenter">
									<a href="#" onclick="recommendReply(${comment.rno}, ${comment.bno})">
										<small class="text-primary"><i class="fas fa-thumbs-up"></i>&nbsp;좋아요 ${comment.recommendcnt}</small>
									</a>
									<span>
										<small class="text-primary">:${message}</small>
									</span>
								</p>
							</c:if>

						</td>

					</tr>
				</c:forEach>
				<tr class="tcenter">
					<td class="writer">
						<strong>댓글 쓰기</strong>
					</td>
					<td class="content">
						<form action="writeReply.do" method="post">
							<input type="hidden" id="writer" name="writer" value="${userName}" />
							<input type="hidden" id="writerId" name="writerId" value="${userId}" />
							<input type="hidden" id="bno" name="bno" value="${board.bno}" />
							<div class="form-inline">
								<textarea id="content" name="content" rows="1" class="form-control rounded w-75"></textarea>
								<input type="submit" value="확인" class="btn btn-secondary ml-2" />
							</div>
						</form>
					</td>
				</tr>
			</table>

			<br />
			<div class="row justify-content-center">
				<c:choose>
					<c:when test="${board.writerId == userId}">
						<input type="button" value="삭제" class="btn btn-outline-secondary" onclick="moveAction(1)" />
						<input type="button" value="수정" class="btn btn-outline-secondary mx-1" onclick="moveAction(2)" />
						<input type="button" value="목록" class="btn btn-outline-secondary" onclick="moveAction(3)" />
					</c:when>
					<c:otherwise>
						<input type="button" value="목록" class="btn btn-outline-secondary" onclick="moveAction(3)" />
					</c:otherwise>
				</c:choose>
			</div>
		</div>
	</section>
</body>
</html>