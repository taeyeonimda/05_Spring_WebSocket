<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>내쪽지함</title>
<script src="https://code.jquery.com/jquery-3.6.1.js" ></script>
</head>
<body>
	<h1>내 쪽지함</h1>
	<hr>
	<div class="sendDm">
	<p>받는사람 : <input name="receiver" id="receiver"></p>
	<p>내용 : <input name="dmContent" id="dmContent"></p>
	<button type="button" id="sendBtn">쪽지 보내기</button>
	<script>
	$("#sendBtn").on("click",function(){
		const receiver = $("input[name=receiver]").val();
		const dmContent = $("input[name=dmContent]").val();
		const sender = "${sessionScope.m.memberId}";
		$.ajax({
			url:"/sendDm.do",
			data:{
			sender: sender,
			receiver: receiver,
			dmContent: dmContent
			},
			success: function(data){
				console.log(data);
			}
		})//ajax
	});
	</script>
	</div> 
	<h3>받은 쪽지함</h3>
	<table border="1" class="receiveDmTbl">
	<tr>
	<th>보낸사람</th>
	<th>내용</th>
	<th>시간</th>
	<th>읽음 여부</th>
	</tr>
	</table>
	<h3>보낸 쪽지함</h3>
	<table border="1" class="sendDmTbl">
	<tr>
	<th>보낸사람</th>
	<th>내용</th>
	<th>시간</th>
	<th>읽음 여부</th>
	</tr>
	</table>
	
	<br><hr>
	
</body>
</html>