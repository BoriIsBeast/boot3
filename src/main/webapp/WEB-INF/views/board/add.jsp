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
	 
	<form action="./add" method="POST" enctype="multipart/form-data">
		title<input type="text" name="title">
		writer<input type="text" name="writer" value="${member.id}" readonly >
		
		Contents<textarea rows="" cols="" name="contents" id="contents"></textarea>
		
		<button id="fileAdd" type="button" class="btn btn-danger d-block">FileAdd</button>
		<div id="fileResult"></div>
		
		<button type="submit">Add</button>
	</form>
	<script type="text/javascript" src="../resources/js/fileAdd.js"></script>
	<script type="text/javascript" src="../js/summernote.js"></script>
	<script type="text/javascript">
		summernoteInit("contents","");
		fileAddInit(0);
	
		
	
	</script>
</body>
</html>

<!-- <div>
		File<input type="file"name="files">
		File<input type="file"name="files">
		</div> -->