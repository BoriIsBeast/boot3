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

    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
</head>
<body>
<c:import url="../temp/header.jsp"></c:import>

<div class="container">
	<h1>Detail Page</h1>
</div>

	title : ${vo.title}
	writer : ${vo.writer}
	contents : ${vo.contents}
	
	<hr>
	<h6>첨부파일</h6>
	<ul>
	<c:forEach items="${vo.filesVOs}" var="f">
	<li>
		<a href="./fileDown?fileNum=${f.fileNum}">${f.oriName}</a>
	</li>
	</c:forEach>
	
	</ul>
					<a href="./update?num=${vo.num}">update</a>
					<a href="./delete?num=${vo.num}">delete</a>


<!-- Option 1: Bootstrap Bundle with Popper -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
</body>
</html>