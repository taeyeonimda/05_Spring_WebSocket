package kr.or.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import kr.or.member.model.service.MemberService;

//@Component
public class ScheduleTest {
	@Autowired
	private MemberService service;
	
	
	@Scheduled(fixedDelay = 5000)
	public void scheduleTest1() {
		System.out.println("예약작업 자동 실행 메소드!!");
	}
	@Scheduled(fixedDelay = 10000)
	public void scheduleTest2() {
		System.out.println("10초 마다 실행하는 ㅁ함수");
	}
	
	@Scheduled(cron ="55 * * * * *" )
	public void scheduleTest3() {
		System.out.println("크론식으로 동작하는 함수");
	}
	
	@Scheduled(cron ="0 44 10 * * *" )
	public void scheduleTest4() {
//		service.deleteMember(133); 10시44분0초에 133번 멤버 삭제
	}
}
