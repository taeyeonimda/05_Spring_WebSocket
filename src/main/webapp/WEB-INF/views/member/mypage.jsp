<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>마이페이지</title>
</head>
<body>
<h1>마이페이지</h1>
<hr>

<form action="/update.do">
	번호 : <input type="text" name="memberNo" value="${sessionScope.m.memberNo }" disabled><br>
	아이디 : <input type="text" name="memberId" value="${sessionScope.m.memberId }" readonly><br>
	이름 : <input type="text" name="memberName" value="${sessionScope.m.memberName }" disabled><br>
	전화번호 : <input type="text" name="phone" value="${sessionScope.m.phone }"><br>
	이메일 : <input type="text" name="email" value="${sessionScope.m.email }"><br>
	<input type="submit" value="수정하기">
</form>
<a href="/changePwFrm.do">비밀번호 변경하기</a>
<a href="/">메인으로</a>
</body>
</html>