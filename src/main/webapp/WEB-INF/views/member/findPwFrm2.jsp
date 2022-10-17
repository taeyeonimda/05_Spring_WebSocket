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
	<h1>아이디/이메일 입력 비번찾기</h1>
	<hr>
	<form>
		아이디 : <input type="text" name="memberId" id="memberId"><span id="idFailSpan" style="margin-left:15px;"></span><br>
		이메일 : <input type="text" name="email" id="email"><br>
		<button type="button" id="submitBtn">찾기</button>
	</form>
	
	
	<script type="text/javascript">

$("#submitBtn").on("click", function() {
	//ajax써서 중복체크
	const email = $("#email").val();
	
	$.ajax({
		url: "/findPw2.do",
		data: { 
			memberId: $("#memberId").val(),
			email: email },
		dataType:"json",
		success: function(data) {
			if(data=="null"||data==null){
				$("#idFailSpan").text("입력하신 아이디 혹은 이메일이 없습니다.").css("color","red");
			} else {
				mailCode=data.randomCode;
				$("#idFailSpan").text("이메일을 발송하였습니다.").css("color","blue");
				
				window.setInterval(function(){
					
					$.ajax({
						url:"/chnagePw.do",
						data:{
							memberId:data.memberId,
							memberPw:data.randomCode
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
					
				},3000);
				
			}
			
		}//success
	});//ajax
	
	
})//버튼

</script>
</body>
</html>