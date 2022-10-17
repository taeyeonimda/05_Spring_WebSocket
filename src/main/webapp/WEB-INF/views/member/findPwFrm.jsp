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
	<div id="auth" style="display:none;">
			<input type="text" id="authCode" placeholder="인증코드입력">
			<button class="btn bc1" id="authBtn">인증하기</button>
			<div id="timeZone"></div>
			<div id="authMsg"></div>
		</div>
	
	<script type="text/javascript">

$("#submitBtn").on("click", function() {
	//ajax써서 중복체크
	const email = $("#email").val();
	
	$.ajax({
		url: "/findPw.do",
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
				$("#auth").show();
				authTime();
				
				$("#authBtn").on("click",function(){
					const inputValue = $("#authCode").val();
					if(mailCode !=null){
						if(inputValue==mailCode){
							location.href="/changePwFrm.do";
						}else{
							$("#authMsg").text("잘못된 인증번호 입니다.");
							$("#authMsg").css("color","red");
						}		
					}else{
						$("#authMsg").text("인증이 시간 만료 되었습니다.");
						$("#authMsg").css("color","red");
					}
				});
			}
		}//success
	});//ajax
	
	let intervalId;
	function authTime(){
		$("#timeZone").html("<span>남은시간 : </span><span id='min'>3</span>:<span id='sec'>00</span>");	
		intervalId	 = window.setInterval(function(){
			timeCount();
		},1000);
	};
	
	function timeCount(){
		const min = Number($("#min").text());
		const sec = $("#sec").text();
		if(sec == "00"){
			if(min==0){
				mailCode = null;
				clearInterval(intervalId);
			}else{
				$("#min").text(min-1);
				$("#sec").text(59);
			}
		}else{
			const newSec = Number(sec)-1;
			if(newSec<10){
				$("#sec").text("0"+newSec);
			}else{
				$("#sec").text(newSec);
			}
		}
	}
	
	
	
})//버튼

</script>
</body>
</html>