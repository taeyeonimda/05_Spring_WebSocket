package kr.or.common;

import java.util.ArrayList;

import org.aspectj.lang.JoinPoint;

import kr.or.member.model.vo.Member;

public class AfterReturningAdvice {

	public void afterReturn(JoinPoint jp,Object returnObj) {
		String methodName = jp.getSignature().getName();
		System.out.println(methodName);
		
		ArrayList<Member>list = (ArrayList<Member>)returnObj;
		for(Member m : list) {
			m.setMemberPw("어쩌고");
		}
	}
}
