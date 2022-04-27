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
	
	<c:import url="../temp/header_script.jsp"></c:import>
	
	<c:import url="../temp/header.jsp"></c:import>

    
	<link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-lite.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-lite.min.js"></script>
	
</head>
<body>
	 
	<form action="./add" method="POST" enctype="multipart/form-data">
		title<input type="text" name="title">
		writer<input type="text" name="writer">
		
		Contents<textarea rows="" cols="" name="contents" id="summernote"></textarea>
		
		<button id="fileAdd" type="button" class="btn btn-danger d-block">FileAdd</button>
		<div id="fileResult"></div>
		
		<button type="submit">Add</button>
	</form>
	<script type="text/javascript">
		
		$('#summernote').summernote({
			height:400,
			placeholder:'내용을 입력하세용',
			callbacks: {
				onImageUpload:function(files){
					//files upload한 이미지 파일 객체
					let formData = new FormData();
					formData.append("files", files[0]);
					
					//board/summerFileUpload
					$.ajax({
						type:"POST",
						url:"./summerFileUpload",
						data:formData,
						contentType:false,
						processData:false,
						success:function(data){
							$("#summernote").summernote('editor.insertImage', data.trim());
						}
					});
				},//onimageupload 끝
				onMediaDelete:function(files){
					let fileName = $(files[0]).attr("src");
					console.log(fileName);
					$.ajax({
						type:"GET",
						url:"./summerFileDelete",
						data:{
							fileName:fileName
						},
						success:function(data){
							console.log(data)
						}
					});
					
				}//onMediaDelete 끝
			}
		});
	
		let count = 0;	
	
	
		$("#fileAdd").click(function(){
			if(count>4){
				alert('최대 5개');
				return;
			}
			
			let result = '<div class="input-group">'
			 result = result + '<input name="files" type="file" class="form-control" id="inputGroupFile04" aria-describedby="inputGroupFileAddon04" aria-label="Upload">';
			 result = result + '<button class="btn btn-outline-secondary del" type="button" id="inputGroupFileAddon04">X</button>';
			 result = result + '</div>';
			$("#fileResult").append(result);
			count++;
		});
		
		$("#fileResult").on("click", ".del", function(){
			$(this).parent().remove();
			count--;
		});
	
	</script>
</body>
</html>

<!-- <div>
		File<input type="file"name="files">
		File<input type="file"name="files">
		</div> -->