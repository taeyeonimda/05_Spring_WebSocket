<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>전체회원조회</title>
</head>
<body>
	<h1>전체회원조회</h1>
	<hr>
	<table border="1">
		<tr>
		<th>회원번호</th>
		<th>아이디</th>
		<th>비밀번호</th>
		<th>이름</th>
		<th>전화번호</th>
		<th>이메일</th>
		</tr>
		<c:forEach items="${list }" var="m">
		<tr>
			<td>${m.memberNo }</td>
			<td>${m.memberId }</td>
			<td>${m.memberPw }</td>
			<td>${m.memberName }</td>
			<td>${m.phone }</td>
			<td>${m.email }</td>
		</tr>
		</c:forEach>
	</table>
	<a href="/">메인으로</a>
</body>
</html>