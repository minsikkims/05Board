<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>	

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
header .top-header{height:40px;}
header .top-header ul{
display:flex;
justify-content:right;align-items:center;
height:40px;
font-size:0.6rem;
}
header .top-header ul li{
	margin-left:10px;
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
			</a> > BOARD
		</div>
		
		<h1>자유게시판</h1>
		<p>Page No : ( <span style="color:red;">${pagedto.criteria.pageno} </span> / ${pagedto.totalpage} )</p>
		<table class="table">
			<thead>
				<tr>
					<th>NO</th>
					<th>제목</th>
					<th>작성자</th>
					<th>날짜</th>
					<th>조회수</th>
				</tr>
			</thead>
			<tbody>
			
			<c:forEach var="dto" items="${list}" varStatus="status" >
			 	<tr>
					<td>${dto.no}</td>
					<td><a href="${pageContext.request.contextPath}/board/read.do?bno=${dto.no}&pageno=${pagedto.criteria.pageno}">${dto.title}</a> </td>
					<td>${dto.email }</td>
					<td>${dto.regdate }</td>
					<td>${dto.count }</td>
				</tr>	
			</c:forEach>
				
			</tbody>
			<tfoot>
				
				<tr>
					<!-- 페이지네이션 -->
					<td colspan="3">
						<nav aria-label="Page navigation example" >
						  <ul class="pagination"  style="height:30px;">
						  
						  <!-- PREV 버튼 -->
						  <c:if test="${pagedto.prev}">
							 	 <li class="page-item">
							      <a class="page-link" href="${pageContext.request.contextPath}/board/list.do?pageno=${pagedto.nowBlock * pagedto.pagePerBlock - pagedto.pagePerBlock*2 + 1}" aria-label="Previous">
							        <span aria-hidden="true">&laquo;</span>
							      </a>
							    </li> 
						  </c:if>

						    
						    <!-- 페이지번호 -->
						    <c:forEach begin="${pagedto.startPage}" end="${pagedto.endPage }" var="pageno" step="1">
							    <li class="page-item"><a class="page-link" href="${pageContext.request.contextPath}/board/list.do?pageno=${pageno}">${pageno}</a></li>
						    </c:forEach>
						    
						

						    
						    <!-- NEXT버튼 -->
						    <c:if test="${pagedto.next}">
							    <li class="page-item">
							      <a class="page-link" href="${pageContext.request.contextPath}/board/list.do?pageno=${pagedto.nowBlock*pagedto.pagePerBlock+1}" aria-label="Next">
							        <span aria-hidden="true">&raquo;</span>
							      </a>
							    </li>
						     </c:if>
						     
						  </ul>
						</nav>
					</td>
					<!-- 글쓰기/처음으로 -->
					<td colspan="2" style="text-align:right;">
						<a href="${pageContext.request.contextPath}/board/post.do" class="btn btn-danger">글쓰기</a>
						<a href="${pageContext.request.contextPath}/board/list.do" class="btn btn-success">처음으로</a>
					</td>
					 
				</tr>
			</tfoot>	
		</table>
		
		
	
	</section>





</body>
</html>