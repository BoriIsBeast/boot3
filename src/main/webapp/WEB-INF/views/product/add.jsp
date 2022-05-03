<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %><!-- form으로 해야됨 spring 아님  -->
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
	
	 
	<!-- <form action="./add" method="POST" enctype="multipart/form-data"> -->
	<form:form modelAttribute="productVO" method="POST" enctype="multipart/form-data">
	
		
		<!-- <div>Name<input type="text" name="productName"id="productName"></div> -->
		<div>Name <form:input path="productName" id="productName"/></div>
		<div>
			<form:errors path="productName"></form:errors>
		</div>
		
		<!-- <div>Price<input type="text" name="productPrice" id="productPrice"></div> -->
		<div>Price <form:input path="productPrice" id="productPrice"/></div>
		<div>
			<form:errors path="productPrice"></form:errors>
		</div>
		
		<!-- <div>Count<input type="text" name="productCount" id="productCount"></div> -->
		<div>Count <form:input path="productCount"/></div>
		<div>
			<form:errors path="productCount"></form:errors>
		</div>		
		
		<!-- Detail<textarea rows="" cols="" name="productDetail" id="productDetail"></textarea> -->
		<div>Detail <form:textarea path="productDetail" id="productDetail"/></div>
		<div>
			<form:errors path="productDetail"></form:errors>
		</div>
		
		<!-- <div class="row mb-3">
			<div class="form-check">
			  <input class="form-check-input sale" type="radio" value="1" name="sale" id="flexRadioDefault1">
			  <label class="form-check-label" for="flexRadioDefault1">
			    판매
			  </label>
			</div>
			<div class="form-check">
			  <input class="form-check-input sale" type="radio" value="0" name="sale" id="flexRadioDefault2" checked>
			  <label class="form-check-label" for="flexRadioDefault2">
			    판매중지
			  </label>
			</div>
		</div>
 -->		
		
		판매<form:radiobutton path="sale" id="flexRadioDefault1" value="1"/>
		판매중지<form:radiobutton path="sale" id="flexRadioDefault2" value="0"/>
		<div>
			<form:errors path="sale"></form:errors>
		</div>
		
		
		<button id="fileAdd" type="button" class="btn btn-danger d-block">FileAdd</button>
		<div id="fileResult"></div>
		
		<button id="add2" type="submit">작성하기</button>
	
	</form:form>
	<!-- </form> -->
	
	
	
	
	</div>
	<script type="text/javascript" src="../resources/js/fileAdd.js"></script>
	<script type="text/javascript" src="../js/summernote.js"></script>
	<script type="text/javascript">
		summernoteInit("productDetail", "");
	
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
			let sale =0;
			$(".sale").each(function(idx, item){
				if($(item).prop("checked")){
					sale = $(item).val();
				}
			})
			
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
			formData.append("sale", sale);
			
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
		
	
		
	
	</script>
</body>
</html>

<!-- <div>
		File<input type="file"name="files">
		File<input type="file"name="files">
		</div> -->