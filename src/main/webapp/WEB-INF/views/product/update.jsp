<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    
</head>
<body>
<c:import url="../temp/header.jsp"></c:import>
<link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-lite.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-lite.min.js"></script>

	<div class="container">
	
	<div class="row" id="list">
	<!-- 삼품 리스트 ajax, name, price, count -->
		
	</div>
	
	 
	<form action="./update" method="POST" enctype="multipart/form-data">
		<input type="hidden" name="productNum" value="${vo.productNum}">
		<div>Name<input type="text" name="productName"id="productName" value="${vo.productName}"></div>
		<div>Price<input type="text" name="productPrice" id="productPrice" value="${vo.productPrice}"></div>
		<div>Count<input type="text" name="productCount" id="productCount" value="${vo.productCount}"></div>
		Detail<textarea rows="" cols="" name="productDetail" id="productDetail"></textarea>
		
		<div class="row mb-3">
		<div class="form-check">
			  <input class="form-check-input sale" type="radio" value="1" ${vo.sale eq 1?'checked':''} name="sale" id="flexRadioDefault1">
			  <label class="form-check-label" for="flexRadioDefault1">
			    판매
			  </label>
			</div>
			<div class="form-check">
			  <input class="form-check-input sale" type="radio" value="0" name="sale" ${vo.sale eq 0?'checked':''} id="flexRadioDefault2">
			  <label class="form-check-label" for="flexRadioDefault2">
			    판매중지
			  </label>
			</div>
		
		</div>
		
		<button id="fileAdd" type="button" class="btn btn-danger d-block">FileAdd</button>
		
		<div>
			<c:forEach items="${vo.productFilesVO}" var="fileVO">
				<h4>${fileVO.oriName}<button class="del" type="button" data-num=${fileVO.fileNum}>DELETE</button></h4>
			</c:forEach>
		</div>
		
		<div id="fileResult"></div>
		
		<button id="add" type="submit">작성하기</button>
	</form>
	</div>
	<script type="text/javascript" src="../resources/js/fileAdd.js"></script>
	<script type="text/javascript" src="../js/summernote.js"></script><!-- static은 빼는거임 -->
	<script type="text/javascript">
		summernoteInit("productDetail", "${vo.productDetail}");
		fileAddInit(${vo.productFilesVO.size()});
		fileDeleteInit();
	</script>
</body>
</html>

<!-- <div>
		File<input type="file"name="files">
		File<input type="file"name="files">
		</div> -->