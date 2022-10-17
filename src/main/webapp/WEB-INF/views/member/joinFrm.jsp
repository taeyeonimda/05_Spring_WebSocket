<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
<script src="https://code.jquery.com/jquery-3.6.1.js" ></script>
</head>
<body>
	<form action="/join.do" method="post">
	<fieldset>
	<legend>회원가입</legend>
	아이디 : <input type="text" name="memberId"><span id="idChk"></span><br>
	비밀번호 : <input type="password" name="memberPw"><br>
	이름 : <input type="text" name="memberName"><br>
	전화번호 : <input type="text" name="phone"><br>
	이메일 : <input type="text" name="email"><br>
	<input type="submit" value="회원가입">
	</fieldset>
	</form>
	<a href="/">메인으로</a>
	<script>
	$("[name=memberId]").on("change",function(){
		const memberId = $(this).val();
		$.ajax({
			url:"/idCheck.do",
			data:{memberId : memberId},
			success:function(data){
				if(data == "0"){
					$("#idChk").text("사용 가능한 아이디 입니다.");
					$("#idChk").css("color","green");
				}else{
					$("#idChk").text("사용 불가능한 아이디 입니다.");
					$("#idChk").css("color","red");
				}
				
			}
		})
	});
	</script>
</body>
</html>