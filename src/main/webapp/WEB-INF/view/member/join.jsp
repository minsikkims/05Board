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
.container{width : 500px; margin:100px auto; text-align:center; position:relative;}
.container *{margin-bottom:10px;}
.container .msg{ position:absolute;left:0px;right:0px;top:-15px; margin:auto; font-size:0.5rem; color:red; padding-left:15px;}
</style>


</head>
<body>
<section class="container">
	<div class="msg">${msg}</div>
	<h1>MEMBER JOIN</h1>
	<form name="joinfrm" action="${pageContext.request.contextPath}/member/join.do" method="post" onsubmit="return false">
		<input type="text" name="email" placeholder="example@example.com" class="form-control" />
		<input type="password" name="pwd"  placeholder="Insert Password" class="form-control" />
		<input type="text" name="phone"  placeholder="0xx-xxxx-xxxx" class="form-control" />
		<div class="row" style="margin-bottom:0px;">
			<div class="col-8">
				<input type="text" name="zipcode"  placeholder="우편번호를 입력하세요" class="form-control" />
			</div>
			<div class="col" style="text-align:right">
				<button class="btn btn-secondary">우편번호 검색</button>	
			</div>	
		</div>
		<input type="text" name="addr1"  placeholder="기본주소 입력"  class="form-control" />
		<input type="text" name="addr2"  placeholder="상세주소 입력" class="form-control" />
		<button class="btn btn-secondary" onclick="isValid()">회원가입</button>
		<input type="reset" value="초기화" class="btn btn-danger" />
	</form>
</section>

<script defer>
	const isValid=function(){
		const joinfrm = document.joinfrm;
		alert("[JS] func isValid");
		//email 공백여부 등 Validation Check
		
		joinfrm.submit();
	}
</script>


</body>
</html>