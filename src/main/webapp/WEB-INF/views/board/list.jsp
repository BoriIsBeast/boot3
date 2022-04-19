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
	
	
<div class="container mt-4">
	<div class="row mt-4">
		<div class="alert alert-secondary text-center" role="alert">
  			<h4 style="text-transform: capitalize;">${board} List</h4>
		</div>
	
	</div>
	<form action="./list" method="get">
		<div>
			<select name="kind">
				<option value="col1">제목</option>
				<option value="col2">작성자</option>
				<option value="col3">본문</option>
			</select>
				<input type="text" name="search" value="${pager.search}">
				<button type="submit">검색</button>
		</div>
	</form>
		<div class="row mt-4">
		<table class="table table-hover">
				<thead>
					<tr>
						<th>Num</th>
						<th>Title</th>
						<th>Writer</th>
						<th>Hit</th>
						<th>Date</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${list}" var="vo">
						<tr>
							<td>${vo.num}</td>
							<td>${vo.title}</td>
							<td>${vo.writer}</td>
							<td>${vo.hit}</td>
							<td>${vo.regDate}</td>
						</tr>
					
					</c:forEach>
					</tbody>
		</table>
		
		
		
		
		</div>
		
		<div>
		<c:if test="${pager.pre}">
				<a href="./list?pn=${pager.pre?pager.startNum-1:1}&kind=${pager.kind}&search=${pager.search}">PREVIEW</a>
			</c:if>
		
			<c:forEach begin="${pager.startNum}" end="${pager.lastNum}" var="i">
				<a href="./list?pn=${i}&kind=${pager.kind}&search=${pager.search}">${i}</a>
			</c:forEach>
			
			<c:if test="${pager.next}">
				<a href="./list?pn=${pager.next?pager.lastNum+1:pager.lastNum}&kind=${pager.kind}&search=${pager.search}">NEXT</a>
			</c:if>
		</div>
		
		<div class="row col-1 justyfy-content-end">
			<a href="./add" type="button" class="btn btn-outline-primary">Write</a>
		</div>
</div>

<!-- Option 1: Bootstrap Bundle with Popper -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
</body>
</html>