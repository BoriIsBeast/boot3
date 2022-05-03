<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<!-- Bootstrap CSS -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
	crossorigin="anonymous">
</head>
<body>
	<c:import url="../temp/header.jsp"></c:import>
	<c:import url="../temp/header_script.jsp"></c:import>
	Login Page

	<%-- <form action="./login" method="POST"> --%>
	<!-- HTML Form tag 대신 Spring Form tag 사용 -->
	<form:form modelAttribute="memberVO" method="POST">
		
		<!-- <input type="text" name="id" placeholder="아이디를 입력하세요"> -->
		ID<form:input path="id" cssClass="form-control" id="id"/>
		<div>
			<form:errors path="id"></form:errors>
		</div>
		
		<!-- <input type ="password" name="pw"> -->
		PW<form:password path="pw" cssClass="form-control" id="pw"/>
		<div>
			<form:errors path="pw" cssStyle="color:red"></form:errors>
		</div>
		
		
		<button type="submit">로그인</button>

		<div class="row">
			<button type="button" class="btn btn-danger" id="find">ID찾기</button>
		</div>
	</form:form>


	<!-- Option 1: Bootstrap Bundle with Popper -->
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
		crossorigin="anonymous"></script>
	<script type="text/javascript">
		$("#find").click(function(){
			location.href="./findId";
		})
	</script>
</body>
</html>