<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="./update" method="post">
		<input type="hidden" name="num" value="${vo.num}">
		<input type="text" name="title" value="${vo.title}">
		<input type="text" name="writer" value="${vo.writer}" disabled>
		<textarea rows="" cols="" name="contents">${vo.contents}</textarea>
		<button type="submit">button</button>	
	
	</form>
</body>
</html>