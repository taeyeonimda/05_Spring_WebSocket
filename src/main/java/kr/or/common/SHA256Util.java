package kr.or.common;

import java.security.MessageDigest;

import org.springframework.stereotype.Component;

@Component
public class SHA256Util {
	public String encData(String data) throws Exception{
		//매개변수로 받은 값sha-256으로 암호화해서 문자열로 리턴하는 메소드
		//MessageDigest 클래스를 이용한 암호화
		MessageDigest mDigest = MessageDigest.getInstance("SHA-256");
		
		//Data에 들어있는 값을 바이트배열로 변환 객체에 넣기
		mDigest.update(data.getBytes());
		
		//SHa-256으로 변환된 데이터를 다시 꺼내옴
		byte[] msgStr = mDigest.digest();
		
		//byte범위가 -128~127 
		//문자열로 쓰기위해 0~255 범위로 사용
		//16진수로 변환해서 00~ff
		
		StringBuffer sb = new StringBuffer();
		for(int i =0;i<msgStr.length;i++) {
			byte str = msgStr[i];
			String encText = Integer.toString((str&0xff)+0x100,16).substring(1);
			sb.append(encText);
		}
		return sb.toString();
	}
}
