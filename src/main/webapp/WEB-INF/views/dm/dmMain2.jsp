<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>내쪽지함</title>
<script src="https://code.jquery.com/jquery-3.6.1.js"></script>
<link rel="stylesheet" href="/resources/css/dmCss.css">
</head>
<body>
<jsp:include page="/WEB-INF/views/common/header.jsp"></jsp:include>
	<h1>내 쪽지함</h1>
	<hr>
	<div class="sendDm">

		<input type="hidden" id="sender" value="${sessionScope.m.memberId }">
		<button type="button" id="sendBtn" onclick="sendDmModal();">쪽지
			보내기</button>
		<script>
			
		</script>
	</div>
	<h3>받은 쪽지함</h3>
	<button onclick="getReceiveDm()">받은쪽지 가져오기</button>
	<table border="1" class="receiveDmTbl">
		<thead>
			<tr>
				<th>보낸사람</th>
				<th>내용</th>
				<th>시간</th>
				<th>읽음 여부</th>
			</tr>
		</thead>
		<tbody></tbody>
	</table>
	<h3>보낸 쪽지함</h3>
	<button onclick="getSendDm()">보낸쪽지 가져오기</button>
	<table border="1" class="sendDmTbl">
		<thead>
			<tr>
				<th>받은사람</th>
				<th>내용</th>
				<th>시간</th>
				<th>읽음 여부</th>
			</tr>
		</thead>
		<tbody></tbody>
	</table>
	
	<div id="sendDm-modal" class="modal-wrapper">
		<div class="modal">
			<div class="modal-header">
				<h2>쪽지보내기</h2>
			</div>
			<hr>
			<div class="modal-content">
				<div class="sendDmFrm">
					<label>수신자 : </label> <select name="receiver"></select>
					<textarea name="dmContent"></textarea>
					<button onclick="dmSend();">쪽지보내기</button>
					<button onclick="closeModal();">닫기</button>
				</div>
			</div>
		</div>
	</div>
	
	<!-- 쪽지 상세보기 모달 -->
	<div id="dmDetail" class="modal-wrapper">
		<div class="modal">
			<div class="modal-header">
				<h2>쪽지 내용</h2>
			</div>
			<hr>
			<div class="modal-content">
				<div class="DmFrm">
					<div>
					<span>발신자: </span>
					<span id="detailSender"></span>
					</div>
					<div>
					<span>날짜 : </span>
					<span id="detailDate"></span>
					</div>
					<div id="detailContent"></div>
					<button onclick="replyDm()();">답장</button>
					<button onclick="closeDetail();">닫기</button>
				</div>
			</div>
		</div>
	</div>
	
	<br>
	<hr>
	<script src="/resources/js/dm.js"></script>

</body>
</html>