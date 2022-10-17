<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>아이디목록</title>
</head>
<body>
	<h1>아이디목록</h1>
	<hr>
	<form action="searchMember3.do">
	<table border="1">
		<tr>
		<th>아이디</th>
		</tr>
		<c:forEach items="${list }" var="memberId">
		<tr>
			<td><input type="checkbox" name="memberId" value="${memberId }">
			${memberId }</td>	
		</tr>
		</c:forEach>
	</table>
	<input type="submit" value="조회">
	</form>
	<a href="/">메인으로</a>
</body>
</html>