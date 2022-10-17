<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>05_WebSocket</title>
<script src="https://code.jquery.com/jquery-3.6.1.js" ></script>
</head>
<body>
	<h1>05_Spring_WebSocket</h1>
	<c:choose>
	<c:when test="${empty sessionScope.m.memberName }">
	<form action="/login.do" method="post">
		<fieldset>
			<legend>로그인</legend>
			아이디 : <input type="text" name="memberId"><br>
			비밀번호 : <input type="password" name="memberPw"><br>
			<input type="submit" value="로그인">
		</fieldset>
	</form>
	<a href="/joinFrm.do">회원가입</a>
	<a href="/findPwFrm.do">비밀번호찾기</a>
	<a href="/findPwFrm2.do">비밀번호찾기</a>
	<form action="/searchMemberId.do">
		아이디 : <input type="text" name ="memberId"><br>
		<input type="submit" value="조회">
	</form>
	</c:when>
	<c:otherwise>
	<h2>[${sessionScope.m.memberName }]</h2>
	<h3><a href="/allMemberChat.do">전체채팅</a></h3>
	<h3><a href="/dmMain.do">쪽지(내가한거)</a></h3>
	<h3><a href="/dmMain2.do">쪽지(강사님)</a></h3>
	<h3><a href="/boardList.do?reqPage=1">게시판</a></h3>
	<h3><a href="/selectAllMember.do">전체회원조회</a></h3>
	<h3><a href="/logout.do">로그아웃</a></h3>
	<h3><a href="/mypage.do">마이페이지</a></h3>
	<h3><a href="/deleteMember.do?memberNo=${sessionScope.m.memberNo }">회원탈퇴</a></h3>
	
	<form action="/searchMemberName.do">
		이름 : <input type="text" name ="memberName"><br>
		<input type="submit" value="조회">
	</form>
	<form action="/searchMember1.do" method="post">
		<select name="type">
			<option value="id">아이디</option>
			<option value="name">이름</option>
		</select>
		<input type="text" name="keyword">
		<input type="submit" value="검색">
	</form>
	<h3>아이디 or 이름으로 검색</h3>
	<p>아이디만 입력하고 검색하면 아이디만 검색
	이름만 입력하고 검색하는 경우 이름으로 조회,
	둘다 입력하고 검색하는 경우 두개and로 조회</p>
	<form action ="/searchMember2.do">
	아이디 : <input type="text" name="memberId"><br>
	이름: <input type="text" name="memberName"><br>
	<input type="submit" value="검색">
	</form>
	<h3><a href="/idList.do">전체회원 아이디 목록</a></h3>
	<h3><a href="/searchMember4.do">전체회원 아이디 목록</a></h3>
	</c:otherwise>
	</c:choose>
	<button id="allMemberAjax">전체 회원 조회(AJAX)</button>
	<div id="ajaxResult">
	
	</div>
	<script>
	$("#allMemberAjax").on("click",function(){
		$.ajax({
			url:"/ajaxAllMember.do",
			success:function(data){
				const table =$("<table border='1'>");
				const titleTr =$("<tr>");
				titleTr.html("<th>번호</th><th>아이디</th><th>이름</th><th>전화번호</th>");
				table.append(titleTr);
				for(let i=0;i<data.length;i++){
					const tr = $("<tr>");
					tr.append("<td>"+data[i].memberNo+"</td>");
					tr.append("<td>"+data[i].memberId+"</td>");
					tr.append("<td>"+data[i].memberName+"</td>");
					tr.append("<td>"+data[i].phone+"</td>");
					table.append(tr);
				}
				$("#ajaxResult").html(table);
			}
		});
	});
	</script>
</body>
</html>