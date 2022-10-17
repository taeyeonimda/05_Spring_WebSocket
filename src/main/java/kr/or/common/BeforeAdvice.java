package kr.or.common;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;

import kr.or.member.model.vo.Member;

public class BeforeAdvice {
	
	public void beforeTest(JoinPoint jp) {
		//advice가 동작하는 메소드에 대한 정보가 들어있는 객체
		Signature sig = jp.getSignature();
		System.out.println(sig.getName());
		System.out.println(sig.toLongString());
		//advice가 동작하는 매개변수
		//메소드의 매개변수 타입과 갯수가 상관없이 처리하기위해 Object[]
		Object[] args = jp.getArgs();
		for(int i =0;i<args.length;i++) {
			System.out.println("args:"+args[i]);
		}
		System.out.println("beforeTest끝");
	}
	
	public void test2(JoinPoint jp) {
		Object[] args = jp.getArgs();
		Member m = (Member)args[0];
		System.out.println(m);
		m.setMemberPw("1234");
		System.out.println(m);
	}
}
