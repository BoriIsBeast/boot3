<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	 
	<form action="./add" method="POST">
		title<input type="text" name="title">
		writer<input type="text" name="writer">
		contents<input type="text" name="contents">
		<button type="submit">Add</button>
	</form>
</body>
</html>