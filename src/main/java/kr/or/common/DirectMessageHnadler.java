package kr.or.common;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import kr.or.dm.model.service.DirectMessageService;
import kr.or.dm.model.vo.DirectMessage;


public class DirectMessageHnadler extends TextWebSocketHandler{
	//쪽지 소켓에 접속한 회원정보 저장할 MAP (key id, value 접속세션)
	HashMap<String,WebSocketSession> connectionMembers;
	@Autowired
	private DirectMessageService service;
	
	
	public DirectMessageHnadler() {
		super();
		connectionMembers = new HashMap<String,WebSocketSession>();
	}

	@Override
	public void afterConnectionEstablished(WebSocketSession session)throws Exception {
		
	
		
	}
	
	//클라이언트가 웹소켓 서버로 메세지를 전송하면 수행되는 메소드
	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message)throws Exception{
		JsonParser parser = new JsonParser();
		System.out.println("전송메시지 : "+ message.getPayload());
		JsonElement element = parser.parse(message.getPayload());
		String type = element.getAsJsonObject().get("type").getAsString();
		if(type.equals("enter")) {
			String memberId = element.getAsJsonObject().get("memberId").getAsString();
			connectionMembers.put(memberId,session);
			//최초 접속 시 현재 내가 읽지않은 쪽지 수를 리턴
			int dmCount = service.dmCount(memberId);
			JsonObject obj = new JsonObject();
			obj.addProperty("type", "myDmCount");
			obj.addProperty("dmCount", dmCount);
			String resultStr = new Gson().toJson(obj);
			TextMessage tm = new TextMessage(resultStr);
			session.sendMessage(tm);
		}else if(type.equals("sendDm")) {
			String receiver = element.getAsJsonObject().get("receiver").getAsString();
			String sender = element.getAsJsonObject().get("sender").getAsString();
			String dmContent = element.getAsJsonObject().get("dmContent").getAsString();
			DirectMessage dm = new DirectMessage();
			dm.setSender(sender);
			dm.setReceiver(receiver);
			dm.setDmContent(dmContent);
			int result = service.sendDm(dm);
			JsonObject obj = new JsonObject();
			obj.addProperty("type", "sendDmResult");
			obj.addProperty("sendResult", result);
			String resultStr = new Gson().toJson(obj);
			//1.쪽지 보낸사람에게 성공 실패 여부
			TextMessage senderMsg = new TextMessage(String.valueOf(resultStr));
			session.sendMessage(senderMsg);
			//2.쪽지 받은 사람에게 받은쪽지 갯수 전송
			
		}
	}
	
	//클라이언트와 연결이 끊어졌을때 자동으로 수행되는 메소드
	@Override
	public void afterConnectionClosed(WebSocketSession session,CloseStatus status)throws Exception {
		
	}
	

}
