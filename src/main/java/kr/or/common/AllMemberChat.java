package kr.or.common;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;



public class AllMemberChat extends TextWebSocketHandler{
	
	private ArrayList<WebSocketSession> sessionList;
	private HashMap<WebSocketSession,String> memberList;
	
	public AllMemberChat() {
		super();
		sessionList =  new ArrayList<WebSocketSession>();
		memberList = new HashMap<WebSocketSession,String>();
	}

	//클라이언트가 웹소켓에 최초로 접속했을때 자동으로 수행되는 메소드
	@Override
	public void afterConnectionEstablished(WebSocketSession session)throws Exception {
		System.out.println("클라 접속 ");
		sessionList.add(session);
		
		
	}
	
	//클라이언트가 웹소켓 서버로 메세지를 전송하면 수행되는 메소드
	@Override
	protected void handleTextMessage(WebSocketSession session, TextMessage message)throws Exception{
		System.out.println("사용자 전송 메시지: "+message.getPayload());
		JsonParser parser = new JsonParser();
		JsonElement element = parser.parse(message.getPayload());
		//key가 type값 추출
		String type = element.getAsJsonObject().get("type").getAsString();
		//key가 msg인 값 추출
		String msg = element.getAsJsonObject().get("msg").getAsString();
		if(type.equals("enter")) {
			memberList.put(session,msg);
			String sendMsg = "<p>"+msg+"님이 입장하였습니다.</p>";
			TextMessage tm = new TextMessage(sendMsg);
			for(WebSocketSession s:sessionList) {
				if(!s.equals(session)) {
					//클라이언트 전송용 객체 생성
					//클라이언트에게 전송
					s.sendMessage(tm);
				}
			}
		}else if(type.equals("chat")) {
			String sendMsg = "<div class='chat left'><span class='chatId'>"+
		memberList.get(session)+" : </span>"+msg+"</div>";
			TextMessage tm = new TextMessage(sendMsg);
			for(WebSocketSession s:sessionList) {
				if(!s.equals(session)) {
					//클라이언트 전송용 객체 생성
					//클라이언트에게 전송
					s.sendMessage(tm);
				}
			}
			
		}
	}
	
	//클라이언트와 연결이 끊어졌을때 자동으로 수행되는 메소드
	@Override
	public void afterConnectionClosed(WebSocketSession session,CloseStatus status)throws Exception {
		System.out.println("클라 접속끊김");
		sessionList.remove(session);
	}
}
