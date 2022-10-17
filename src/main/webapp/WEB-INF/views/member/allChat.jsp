<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.6.1.js" ></script>
<style>
.chatting {
	width: 500px;
	display:none;
}

.messageArea {
	overflow-y: auto;
	border: 1px solid black;
	height: 500px;
	display: flex;
	flex-direction: column;
	background-color: #b2c7d9;
}

.messageArea>p {
	text-align: center;
	width: 100%;
}

.sendMsg {
	width: 75%;
}

#sendBtn {
	width: 20%
}
.chat{
margin-bottom:10px;
padding:8px;
border-radius:3px;}
.left{
position:relative;
max-width:300px;
align-self:flex-start;
background-color:#fff;
-webkit-border-radius:10px;
-moz-border-radius:10px;
margin-left:16px;
padding:15px;
}
.left:after{
content:'';
position:absolute;
border-style:solid;
border-width:15px 15px 15px 0;
border-color:transparent #fff;
display:block;
width:0;
z-index:1;
left:-15px;
top:12px;
}
.right{
position:relative;
max-width:300px;
align-self:flex-end;
background-color:#ffeb33;
-webkit-border-radius:10px;
-moz-border-radius:10px;
margin-right:16px;
padding:15px;
}

.right:after{
content:'';
position:absolute;
border-style:solid;
border-width:15px 0 15px 15px;
border-color:transparent #ffeb33;
display:block;
width:0;
z-index:1;
right:-15px;
top:12px;
}

</style>
</head>
<body>
	<h1>전체채팅</h1>
	<hr>
	<button onclick="initChat('${sessionScope.m.memberId}')">채팅 시작</button>
	<div class="chatting">
		<div class="messageArea"></div>
		<div class="sendBox">
			<input type="text" id="sendMsg">
			<button id="sendBtn" onclick="sendMsg();">전송</button>
		</div>

	</div>
	<script>
		let ws;
		function initChat(param){
			memberId=param;
			//웹소켓 연결 시도
			ws = new WebSocket("ws://192.168.10.50/chat.do");
			//웹소켓 연결 성공시 실행할 함수
			ws.onopen = startChat;
			//서버에서 데이터를 받으면 처리할 함수 지정
			ws.onmessage = receiveMsg;
			//종료 함수
			ws.onclose = endChat;
			$(".chatting").show();
			
			console.log(ws);
		}
		
		function startChat(){
			console.log("웹소켓 연결 완료");
			const data ={type:"enter",msg:memberId};
			ws.send(JSON.stringify(data));
			appendChat("<p>채팅방에 입장 하였습니다.</p>")
		}
		
		function receiveMsg(param){
			console.log(param);
			appendChat(param.data);
		}
		function endChat(){
			console.log("끝")
		}
		
		function sendMsg(request){
			// 1) 연결 클라이언트 IP 취득
		    
			const msg = $("#sendMsg").val();
			if(msg !=''){
				//서버에 소켓으로 문자열을 전송
				const data = {type:"chat",msg:msg};
				ws.send(JSON.stringify(data));
				appendChat("<div class='chat right'>"+msg+"</div>");
				$("#sendMsg").val("");
			}
		}
		
		function appendChat(msg){
			$(".messageArea").append(msg);
			$(".messageArea").scrollTop($(".messageArea")[0].scrollHeight);
		}
		
		$("#sendMsg").on("keyup",function(key){
			if(key.keyCode==13){
				sendMsg();
			}
		})
	</script>
</body>
</html>