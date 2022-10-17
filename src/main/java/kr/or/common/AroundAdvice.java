package kr.or.common;

import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.util.StopWatch;

//실행흐름에 직접관여해서 실행 전/후 모두 처리 가능
public class AroundAdvice {

	public Object aroundTest(ProceedingJoinPoint pjp) throws Throwable {
		String methodName = pjp.getSignature().getName();
		Object[] args = pjp.getArgs();
		StopWatch stopWatch = new StopWatch();
		stopWatch.start();
		//비즈니스 로직을 수행하는 메소드(서비스의 메소드를 수행하는 메소드
		//비즈니스로직 수행 결과를 Object형태로 반환
		Object obj = pjp.proceed();
		stopWatch.stop();
		System.out.println(methodName+"()수행시간: "+stopWatch.getTotalTimeMillis()+"(ms)");
		System.out.println(args+"()수행시간: "+stopWatch.getTotalTimeMillis()+"(ms)");
		return obj;
		
	}
}
