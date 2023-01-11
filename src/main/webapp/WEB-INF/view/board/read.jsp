<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- BS5 -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
	crossorigin="anonymous">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
	crossorigin="anonymous"></script>
<!-- BSICON -->
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.9.1/font/bootstrap-icons.css">

<style>
body {
	padding: 10px;
}

a {
	text-decoration: none;
	color: black;
}

ul {
	list-style: none;
	padding: 0px;
}

header {
	magin-bottom: 10px;
}

header .top-header {
	height: 40px;
}

header .top-header ul {
	display: flex;
	justify-content: right;
	align-items: center;
	height: 40px;
	font-size: 0.6rem;
}

header .top-header ul li {
	margin-left: 10px;
}

header nav {
	height: 50px;
	background-color: #F2F2F2
}

header nav ul {
	height: 50px;
	display: flex;
	justify-content: space-around;
	align-items: center;
}

header nav ul li {
	
}

section {
	height: 600px;
	width: 95vw;
	margin-top: 10px;
}

.container {
	position: relative;
}

.container * {
	margin-bottom: 10px;
}

.container .msg {
	position: absolute;
	left: 0px;
	right: 0px;
	top: -15px;
	margin: auto;
	font-size: 0.5rem;
	color: red;
	padding-left: 15px;
}
</style>


</head>
<body>
	<header>
		<div class="top-header">
			<ul>
				<li><a href="javascript:void(0)">나의정보</a></li>
				<li><a href="${pageContext.request.contextPath}/auth/logout.do">로그아웃</a></li>
			</ul>
		</div>
		<nav>
			<ul>
				<li><a href="javascript:void(0)">회사소개</a></li>
				<li><a href="${pageContext.request.contextPath}/notice/list.do">공지사항</a></li>
				<li><a href="${pageContext.request.contextPath}/board/list.do">자유게시판</a></li>
				<li><a href="javascript:void(0)">ETC</a></li>
			</ul>
		</nav>
	</header>

	<section class="container">
		<!-- 메시지 -->
		<div class="msg">${msg}</div>
		<!-- 페이지경로표시 -->
		<div>
			<a href="${pageContext.request.contextPath}/main.do"> <i
				class="bi bi-house-door"></i>
			</a> > BOARD > READ
		</div>

		<h1>자유게시판</h1>
		<p></p>
		
		<table class="table w-75">
				<!-- Title / Count-->
				<tr>
					<th style="width:80px;">제목</th>
					<td style="width:480px;">${boarddto.title }</td>
					<th>조회수</th>
					<td>${boarddto.count }</td>
				</tr>
				<!-- Email / regdate -->
				<tr>
					<th>작성자</th>
					<td>${boarddto.email }</td>
					<th>등록날짜</th>
					<td>${boarddto.regdate }</td>
					
				</tr>
				<!-- Content -->
				<tr>
					<td  colspan=4 style="height:300px;overflow:auto;">${boarddto.content}</td>
				<!-- 	<td></td>
					<td></td>
					<td></td> -->
				</tr>
				<!-- Button -->
				<tr>
					<td colspan=4>
						<button class="btn btn-primary"  data-bs-toggle="modal" data-bs-target="#exampleModal">첨부파일</button>
						<a class="btn btn-primary" href="${pageContext.request.contextPath}/board/list.do?pageno=${pagedto.criteria.pageno}">이전으로</a>
						<button class="btn btn-primary">수정하기</button>
						<button class="btn btn-primary">삭제하기</button>
					</td>
<!-- 					<td></td>
					<td></td>
					<td></td> -->
					
				</tr>																					
		</table>
		
		<!-- Modal -->
		<div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
		  
		  <div class="modal-dialog modal-dialog-centered">
		    <div class="modal-content">
		      <div class="modal-header">
		        <h5 class="modal-title" id="exampleModalLabel">첨부파일 리스트</h5>
		        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
		      </div>
		      
		      <div class="modal-body">
				<c:set var="filenames" value="${fn:split(boarddto.filename,';')}" />
				<c:set var="filesizes" value="${fn:split(boarddto.filesize,';')}" />
			      
	 			<c:forEach var="i" begin="0" step="1" end="${fn:length(filenames)-1}" >
					
						<a href="${pageContext.request.contextPath}/board/download.do?dirpath=${boarddto.dirpath}&filename=${filenames[i]}">${filenames[i]} (${filesizes[i]}Byte)</a><br>
					
				</c:forEach>   	     
			    

		      </div>
		      <div class="modal-footer">
		       	<button type="button" class="btn btn-primary">ZIP 받기</button>
		        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">닫기</button>
		 
		      </div>
		    </div>
		  </div>
		  
		</div>
	
	</section>





</body>
</html>