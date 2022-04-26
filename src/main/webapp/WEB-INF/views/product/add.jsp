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

	<div class="row" id="list">
	<!-- 삼품 리스트 ajax, name, price, count -->
		
	</div>
	
	 
	<form action="./add" method="POST" enctype="multipart/form-data">
		Name<input type="text" name="productName"id="productName">
		Price<input type="text" name="productPrice" id="productPrice">
		Count<input type="text" name="productCount" id="productCount">
		Detail<textarea rows="" cols="" name="productDetail" id="productDetail"></textarea>
		
		
		<button id="fileAdd" type="button" class="btn btn-danger d-block">FileAdd</button>
		<div id="fileResult"></div>
		
		<button id="add" type="button">작성하기</button>
	</form>
	
	<script type="text/javascript">
	//
	let pn =1;
	$("#list").on("click", ".pager", function(){
			let checkPn =$(this).attr("data-pn");
			if(checkPn>0){
				pn = checkPn;
				getList()
			}else{
				//이전 또는 다음 Block이 없다
				alert("마지막페이지입니다");
			}
	});
	
		//list ajax url:AjaxList, Get
		
		getList();
		
		function getList(){
			$.ajax({
				type : "GET",
				url : "./ajaxList",
				data:{
					pn:pn,
					perPage:5
				},
				success : function(data){
					$("#list").html(data.trim())
				}
			});
		}
	
	
		//add
		$("#add").click(function(){
			let formData = new FormData();
			let productName = $("#productName").val();
			let productPrice = $("#productPrice").val();
			let productCount = $("#productCount").val();
			let productDetail = $("#productDetail").summernote("code"); //$("#productDetail").val();
			$(".files").each(function(idx, item){
				if(item.files.length>0){
				console.log(idx);                     //index번호
				console.log(item);                    //<input type = "file">
				console.log(item.files);              //input 태그의 file List
				console.log(item.files[0]);           //files list중 첫번째 파일
				console.log("length : ",item.files.length);
				console.log(item.files[0].name);      //files list중 첫번째 파일의 이름 
				//formData.append("파라미터명", 값);
				formData.append("files", item.files[0]);
				}
			});//each 끝
			
			formData.append("productName", productName);
			formData.append("productPrice", productPrice);
			formData.append("productCount", productCount);
			formData.append("productDetail", productDetail);
			
			 $.ajax({
				type:"POST",
				url:"./add",
				processData : false,
				contentType : false,
				data: formData/* {
					productName : productName,
					productPrice : productPrice,
					productCount : productCount,
					productDetail : productDetail
				} */,
				success:function(data){
					if(data.trim()=='1'){
						alert("상품 등록 완료");
						getList();
						$("#productName").val("");
						$("#productPrice").val("");
						$("#productCount").val("");
						$("#productDetail").summernote("code", ""); //$("#productDetail").val();
					}else{
						alert("삼품 등록 실패")
					}
				},
				error:function(){
					alert("error 발생");
				}
				
			}); 
			
			
		});
		
	
		//summernote
		  $('#productDetail').summernote({
			  
			  height:400
		  });
		
	
	
		let count = 0;	
	
	
		$("#fileAdd").click(function(){
			if(count>4){
				alert('최대 5개');
				return;
			}
			
			let result = '<div class="input-group">'
			 result = result + '<input name="files" type="file" class="form-control files" id="inputGroupFile04" aria-describedby="inputGroupFileAddon04" aria-label="Upload">';
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