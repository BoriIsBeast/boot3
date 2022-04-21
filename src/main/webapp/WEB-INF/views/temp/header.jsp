<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<header class="container-fluid">
		<nav class="navbar-expand-lg navbar navbar-dark bg-dark">
	  <div class="container-fluid">
	    <a class="navbar-brand" href="#">Navbar</a>
	    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
	      <span class="navbar-toggler-icon"></span>
	    </button>
	    <div class="collapse navbar-collapse" id="navbarSupportedContent">
	      <ul class="navbar-nav me-auto mb-2 mb-lg-0">
	        <li class="nav-item">
	          <a class="nav-link active" aria-current="page" href="/">Home</a>
	        </li>
	        <li class="nav-item">
	          <a class="nav-link active" href="/board/list">Board</a>
	        </li>
	        <li class="nav-item">
	          <a class="nav-link active" href="/member/join">Join</a>
	        </li>
	        <li class="nav-item">
	          <a class="nav-link active" href="/member/login">Login</a>
	        </li>
	        <li class="nav-item dropdown">
	          <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
	            회원 관련
	          </a>
	          <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
	           <c:choose>
	           	<c:when test="${not empty member}">
	            <li><a href="/member/mypage?id=${member.id}">Mypage</a></li>
	           	<li><a href="/member/logout">로그아웃</a></li>
	           	</c:when>
	           	<c:otherwise>
	           	<li><a class="dropdown-item" href="/member/join">회원가입</a></li>
	            <li><a class="dropdown-item" href="/member/login">로그인</a></li>
	           	</c:otherwise>
	           	</c:choose>
	            <li><hr class="dropdown-divider"></li>
	            <li><a class="dropdown-item" href="#">Something else here</a></li>
	          </ul>
	        </li>
	        <li class="nav-item">
	          <a class="nav-link disabled">Disabled</a>
	        </li>
	      </ul>
	      <form class="d-flex">
	        <input class="form-control me-2" type="search" placeholder="Search" aria-label="Search">
	        <button class="btn btn-outline-success" type="submit">Search</button>
	      </form>
	    </div>
	  </div>
	</nav>
</header>