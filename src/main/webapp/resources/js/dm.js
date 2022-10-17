/**
 * 
 */
function sendDmModal(){

	$.ajax({
		url: "/selectAllMemberId.do",
		success:function(list){
			$("[name=receiver]").empty();
			for(let i=0;i<list.length;i++){
				const option = $("<option>");
				option.val(list[i]);
				option.text(list[i]);
				$("[name=receiver]").append(option);
			}//for문
			$("#sendDm-modal").css("display","flex");
			
		}//success
	})//ajax
}

function closeModal(){
	$("#sendDm-modal").hide();
	$("textarea[name=dmContent]").val("");
}


function dmSend(){
	const receiver =$("[name=receiver]").val();
	const dmContent = $("[name=dmContent]").val();
	const sender = $("#sender").val();
	
	const data = {
			type:"sendDm",
			receiver: receiver,
			dmContent: dmContent,
			sender:sender
	};
	ws.send(JSON.stringify(data));
}


//
//function dmSend(){
//	const receiver =$("[name=receiver]").val();
//	const dmContent = $("[name=dmContent]").val();
//	const sender = $("#sender").val();
//	
//	
//	$.ajax({
//		url:"/sendDm.do",
//		data:{
//		sender: sender,
//		receiver: receiver,
//		dmContent: dmContent
//		},
//		success: function(data){
//			if(data=="1"){
//				getSendDm();
//				closeModal();
//				
//			}else{
//				alert("실패");
//			}
//		}
//	})//ajax
//}

function getReceiveDm(){
	const receiver = $("#sender").val();
	
	$.ajax({
		url: "/getAllDm.do",
		data:{ receiver: receiver},
		success:function(list){
			const tbody =$(".receiveDmTbl>tbody");
			tbody.empty();
			for(let i=0;i<list.length;i++){
				const dm = list[i];
				const tr = $("<tr>");
				
				const senderTd=$("<td>");
				senderTd.text(dm.sender);
				const contentTd=$("<td>");
				contentTd.text(dm.dmContent);
				contentTd.attr("onclick","dmDetail("+dm.dmNo+")");
				
				const dmDateTd = $("<td>");
				dmDateTd.text(dm.dmDate);
				const readTd=$("<td>");
				if(dm.readCheck == 0){
					tr.css("font-weight","bold");
					readTd.text("읽지않음");
				}else{
					readTd.text("읽음");
				}
				tr.append(senderTd).append(contentTd).append(dmDateTd).append(readTd);
				tbody.append(tr);
			}
		}
	})
}



function getSendDm(){
	const sender = $("#sender").val();
	
	$.ajax({
		url: "/getAllDm.do",
		data:{ sender: sender},
		success:function(list){
			
			const tbody =$(".sendDmTbl>tbody");
			tbody.empty();
			for(let i=0;i<list.length;i++){
				const dm = list[i];
				const tr = $("<tr>");
				
				const receiverTd=$("<td>");
				receiverTd.text(dm.receiver);
				const contentTd=$("<td>");
				contentTd.text(dm.dmContent);
				const dmDateTd = $("<td>");
				dmDateTd.text(dm.dmDate);
				const readTd=$("<td>");
				if(dm.readCheck == 0){
					tr.css("font-weight","bold");
					readTd.text("읽지않음");
				}else{
					readTd.text("읽음");
				}
				tr.append(receiverTd).append(contentTd).append(dmDateTd).append(readTd);
				tbody.append(tr);
			}//for문
			
		}
	})
}

function dmDetail(dmNo){
	$.ajax({
		url:"/dmDetail.do",
		data : {dmNo : dmNo},
		success : function(data){
			$("#detailSender").text(data.sender);
			$("#detailDate").text(data.dmDate);
			$("#detailContent").text(data.dmContent);
			if(data.readCheck == 0){
				getReceiveDm();
			}
			$("#dmDetail").css("display","flex");
		}
	})//ajax
	
	
}

function closeDetail(){
	$("#dmDetail").hide();
}

$(()=>{
	getReceiveDm();
	getSendDm();
});
