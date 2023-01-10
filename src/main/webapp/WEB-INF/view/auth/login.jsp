<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!-- BS5 -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
<!-- BSICON -->
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.9.1/font/bootstrap-icons.css">

<style>
.container{width : 300px; margin:100px auto; text-align:center; position:relative;}
.container *{margin-bottom:10px;}
.container .msg{ position:absolute;left:0px;right:0px;top:-15px; margin:auto; font-size:0.5rem; color:gray; padding-left:15px;}
</style>


</head>
<body>
<section class="container">
	<div class="msg">${msg}</div>
	<h1>LOGIN</h1>
	<form name="loginfrm" action="${pageContext.request.contextPath}/auth/login.do" method="post">
		<input type="text" name="email" placeholder="example@example.com" class="form-control" />
		<input type="password" name="pwd"  placeholder="Insert Password" class="form-control" />
	
		<button class="btn btn-primary w-100">로그인</button>
		<a class="w-100 btn btn-secondary">카카오로그인</a>
		<a href="${pageContext.request.contextPath}/member/join.do">회원가입</a>
		<a href="javascript:void(0)">아이디분실</a>
		<a href="javascript:void(0)">패스워드분실</a>
	</form>
</section>



</body>
</html>