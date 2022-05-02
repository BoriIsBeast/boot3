<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<c:import url="../temp/header_script.jsp"></c:import>	
	
	
	
	<form action="./findId" method="POST">
	Email을 입력하세요 <input type="text" name="email">
	
	<button type="submit">ID찾기!</button>
	</form>
</body>
</html>