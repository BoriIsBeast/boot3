<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<c:import url="../temp/header_script.jsp"></c:import>
<!-- Bootstrap CSS -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
	crossorigin="anonymous">

</head>
<body>
	<c:import url="../temp/header.jsp"></c:import>
	<link
		href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-lite.min.css"
		rel="stylesheet">
	<script
		src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-lite.min.js"></script>
	
	<div class="container">
		
		<form action="./update" method="post" enctype="multipart/form-data">
			<input type="hidden" name="num" value="${vo.num}"> <input
				type="text" name="title" value="${vo.title}"> <input
				type="text" name="writer" value="${vo.writer}" disabled>
			<textarea rows="" cols="" name="contents" id="contents"></textarea>
			
			<button id="fileAdd" type="button" class="btn btn-danger d-block">FileAdd</button>
		
		<div>
			<c:forEach items="${vo.filesVOs}" var="fileVO">
				<h4>${fileVO.oriName}<button class="del" type="button" data-num=${fileVO.fileNum}>DELETE</button></h4>
			</c:forEach>
		</div>
		
		<div id="fileResult"></div>
			
			
			<button id="add" type="submit">button</button>

		</form>

	</div>
	<script type="text/javascript" src="../resources/js/fileAdd.js"></script>
	<script type="text/javascript" src="../js/summernote.js"></script>
	<!-- static은 빼는거임 -->
	<script type="text/javascript">
		summernoteInit("contents", "${vo.contents}");
		fileAddInit(${vo.filesVOs.size()});
		fileDeleteInit();
	</script>
</body>
</html>