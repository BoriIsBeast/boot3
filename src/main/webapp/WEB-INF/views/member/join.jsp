<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
	
	<!-- <form action="./join" method="POST" enctype="multipart/form-data"> -->
		<form:form modelAttribute="memberVO" method="POST" enctype="multipart/form-data">
		
		ID<form:input path="id" id="id"/>
		<div>
			<form:errors path="id"></form:errors>
		</div>
		
		PW<form:password path="pw" id="pw"/>
		<div>
			<form:errors path="pw"></form:errors>
		</div>
		PWCheck<form:password path="checkPw" id="checkPw"/>
		<div>
			<form:errors path="checkPw"></form:errors>
		</div>
		
		NAME<form:input path="name" id="name"/>
		<div>
			<form:errors path="name"></form:errors>
		</div>
		EMAIL<form:input path="email" id="email"/>
		<div>
			<form:errors path="email"></form:errors>
		</div> 
		PHONE<form:input path="phone" id="phone"/>


		<div>
			File<input type="file" name="file">

		</div>
		<button type="submit">Add</button>
	
	<!-- </form> -->
	</form:form>

	<div class="row mt-4">
		<div class="form-check">
			<input class="form-check-input" type="checkbox" value=""
				id="all"> <label class="form-check-label"
				for="all"> checkbox-All </label>
		</div>
		<div class="form-check">
			<input class="form-check-input ch" type="checkbox" value=""
				id="check1"> <label class="form-check-label"
				for="check1"> checkbox2 </label>
		</div>
		<div class="form-check">
			<input class="form-check-input ch" type="checkbox" value=""
				id="check2"> <label class="form-check-label"
				for="check2"> checkbox3 </label>
		</div>

		<div class="form-check">
			<input class="form-check-input ch" type="checkbox" value=""
				id="check3"> <label class="form-check-label"
				for="check3"> checkbox4 </label>
		</div>


	</div>
	
	


	<c:import url="../temp/header_script.jsp"></c:import>
	<script type="text/javascript">
	$("#all").click(function(){
		$(".ch").prop("checked", $("#all").prop("checked"))
	});
	
	$(".ch").on("click", function(){
		let check = true;
		
		$(".ch").each(function(idx, item){
			if(!$(item).prop("checked")){
				check = false;
			}
		});
		
		$("#all").prop("checked",check);
	});
	
	</script>
</body>
</html>