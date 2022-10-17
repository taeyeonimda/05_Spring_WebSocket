package kr.or.common;

import java.util.ArrayList;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import kr.or.member.model.vo.Member;

@Component
@Aspect
public class AdviceClass {

//	@Pointcut(value = "execution(* kr.or.member.model.service.MemberService.*(..))")
//	public void allPointcut() {}
//	@Pointcut(value = "execution(* kr.or.member.model.service.MemberService.selectOneMember(..))")
//	public void onePointcut() {}
//	@Pointcut(value = "execution(* kr.or.member.model.service.MemberService.*Member())")
//	public void selectAll() {}
	@Pointcut(value = "execution(* kr.or.member.model.service.MemberService.insertMember(..))")
	public void insertPointcut() {}
	
	
	
	@Before("insertPointcut()")
	public void pwChange(JoinPoint jp) {
		Object[] args = jp.getArgs();
		Member m = (Member)args[0];
		System.out.println(m);
		m.setMemberPw("1q2w");
		System.out.println(m);	
	}
//	@AfterReturning(value="selectAll()",returning = "returnObj")
//	public void pwChange2(JoinPoint jp,Object returnObj) {
//		ArrayList<Member> list = (ArrayList<Member>)returnObj;
//		for(Member m : list) {
//			m.setMemberPw("test");
//		}
//		
//	}
}
