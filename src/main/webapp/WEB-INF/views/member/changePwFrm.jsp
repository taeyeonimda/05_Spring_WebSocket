<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>
<body>
<form >
<input type="hidden" name="memberId" id="memberId" value="${sessionScope.m.memberId }">
	기존 비밀번호  : <input type="password" id="originPw" name="originPw"><span id="spanChk"></span><br>
	변경할 비밀번호  : <input type="password" id="memberPw" name="memberPw"><br>
	비밀번호 확인 : <input type="password" id="memberPw2" name="memberPw2"><br>
	<input id="submitBtn" type="button" value="수정하기">
</form>
<script>
$("#originPw").on("change",function(){
	const originPw = $("#originPw").val();
	const memberId = $("#memberId").val();

	$.ajax({
		url:"/chkPw.do",
		data: {
			memberId:memberId,
			memberPw:originPw},
		success:function(data){
			if(data == "1"){
				$("#spanChk").html("맞는비밀번호");
				$("#spanChk").css("color","green");
			
				
				$("#submitBtn").on("click",function(){
					const memberPw = $("#memberPw").val();
					const memberPw2 = $("#memberPw2").val();
					

					if(memberPw == memberPw2){
						$.ajax({
							url:"/chnagePw.do",
							data:{
								memberId:memberId,
								memberPw:memberPw
							},
							success: function(data){

								if(data=="1"){
									alert("성공");
									location.href="/";
								}else{
									alert("실패");
									
								}
							}
							
						})//ajax
						
					}//비번이 같을때
					
					
				})//버튼눌렀을때
								
			}//if==1
		}
	})//ajax
})//change
</script>
</body>
</html>