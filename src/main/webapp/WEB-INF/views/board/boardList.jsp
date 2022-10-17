<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<link rel="stylesheet"
	href="https://fonts.googleapis.com/icon?family=Material+Icons" />
<style>
.pagination {
	list-style: none;
	display: inline-block;
}

.pagination>li {
	display: inline-block;
	margin: 0 5px 0 5px;
	width: 20px;
	height: 25px;
	border: 1px solid black;
	text-align: center;
	border: 1px solid #293243;
	background: #222;
	cursor: pointer;
}

.pagination>li>a {
	width: 20px;
	height: 25px;
	text-decoration: none;
	color: #fff;
	outline: none;
}
</style>
<title>전체 게시물</title>
</head>
<body>
<h1>전체회원조회</h1>
	<hr>
	
	<table border="1">
		<tr>
		<th>게시물 번호</th>
		<th>제목</th>
		<th>글쓴이</th>
		<th>글쓴날</th>
		</tr>
		<c:forEach items="${list }" var="b" varStatus ="i">
		<tr>
			<td>${(reqPage-1)*numPerPage + i.count }</td>
			<td class="boardNo">${b.boardNo }</td>
			<td class="boardTitle">${b.boardTitle }</td>
			<td>${b.boardWriter }</td>
			<td>${b.boardDate }</td>
		</tr>
		</c:forEach>
		<tfoot>
		<tr>
		<td colspan="4"><div id="pageNavi">${pageNavi }</div></td>
		</tr>
		</tfoot>
	</table>
	
	
	<c:if test ="${not empty sessionScope.m}">
	<a href="/boardWriteFrm.do">게시글 작성</a>
	</c:if>
	<a href="/">메인으로</a>
	<script>
		$(".boardNo").hide();
		$(".boardTitle").on("click",function(){
			const boardNo = $(this).prev().text();
			location.href="/boardView.do?boardNo="+boardNo;
		});
	</script>
</body>
</html>