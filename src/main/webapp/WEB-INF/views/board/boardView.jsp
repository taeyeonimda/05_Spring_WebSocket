<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>상세보기</title>
<style>
	.input-wrap{
	width:800px;
	margin:0 auto;
	}
	.input-wrap>label{
	display:block;
	width:800px;
	height:30px;
	line-height:30px;}
	.input-wrap>.input-class{
	width:800px;
	height:50px;
	margin-bottom:10px;}
	.aDiv{
	width:800px;
	margin:0 auto;
	text-align:center;}
</style>
</head>
<body>
<h1>게시글 상세보기</h1>
<hr>
<div class="input-wrap">
<label for = "boardNo">글번호</label>
<input class="input-class" id="boardNo" name="boardNo" value="${b.boardNo }" readonly>
</div>
<div class="input-wrap">
<label for = "boardTitle">글제목</label>
<input class="input-class" id="boardTitle" name="boardTitle" value="${b.boardTitle }" readonly>
</div>
<div class="input-wrap">
<label for = "boardWriter">글쓴이</label>
<input class="input-class" id="boardWriter" name="boardWriter" value="${b.boardWriter }" readonly>
</div>
<div class="input-wrap">
<label for = "boardContent">글내용</label>
<textarea class="input-class" id="boardContent" name="boardContent" readonly>${b.boardNo }</textarea>
</div>
<div class="input-wrap">
<label for = "boardDate">작성일</label>
<input class="input-class" id="boardDate" name="boardDate" value="${b.boardDate }"readonly>
</div>
<div class="input-wrap">
<label for = "filepath">첨부파일</label>
<c:forEach items="${b.fileList }" var="f">
<div class="input-wrap">
<span><a id="filepath" href="/boardFileDown.do?fileNo=${f.fileNo }">${f.filename }</a>
</span>
</div>
</c:forEach>
</div>
	<c:if test="${b.boardWriter eq sessionScope.m.memberId}">
	<div class="aDiv">
	<a href="/boardUpdateFrm2.do?boardNo=${b.boardNo }">수정하기</a>
	<a href="/boardDelete2.do?boardNo=${b.boardNo }">삭제하기</a>
	</div>
	</c:if>

</body>
</html>