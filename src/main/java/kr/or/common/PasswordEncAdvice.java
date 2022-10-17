package kr.or.common;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import kr.or.member.model.vo.Member;

@Component
@Aspect
public class PasswordEncAdvice {

	@Autowired
	private SHA256Util passEnc;
//	@Pointcut(value = "execution(* kr.or.member.model.service.MemberService.insertMember(..))")
//	public void insertPointcut() {}
	
	@Pointcut(value = "execution(* kr.or.member.model.service.MemberService.*Member(kr.or.member.model.vo.Member))")
	public void insertPointcut() {}
	
	
	@Before(value="insertPointcut()")
	public void insertPointcut(JoinPoint jp) throws Exception {
	Object[] args = jp.getArgs();
	Member m = (Member)args[0];
	System.out.println(m);
	String memberPw = m.getMemberPw();
	System.out.println(memberPw);
	if(memberPw !=null) {
		String encPw = passEnc.encData(memberPw);
		System.out.println(encPw);
		m.setMemberPw(encPw);
	}
	}
	
	
}
