package kr.or.member.model.service;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.or.common.LogAdvice1;
import kr.or.common.LogAdvice2;
import kr.or.member.model.dao.MemberDao;
import kr.or.member.model.vo.Member;

@Service
public class MemberService {

	@Autowired
	private MemberDao dao;

	
	public Member selectOneMember(Member member) {
		System.out.println("selectOneMember 전");
		Member m = dao.selectOneMember(member);
		System.out.println("selectOneMember 후");
		return m;
	}
	

	
	public ArrayList<Member> selectAllMember() {
		System.out.println("메소드 시작");
		ArrayList<Member> list = dao.selectAllMember();
		System.out.println("메소드 끝");
		return list;
	}

	@Transactional
	public int insertMember(Member m) {
		
		return dao.insertMember(m);
	}

	@Transactional
	public int updateMember(Member m) {
		
		return dao.updateMember(m);
	}

	@Transactional
	public int deleteMember(int memberNo) {
		
		return dao.deleteMember(memberNo);
	}

	public ArrayList<Member> searchMemberName(String memberName) {
		ArrayList<Member> list = dao.searchMemberName(memberName);
		
		return list;
	}



	public ArrayList<Member> searchMember1(String type, String keyword) {
		HashMap<String, Object> map = new HashMap<String,Object>();
		map.put("type", type);
		map.put("keyword", keyword);
		ArrayList<Member> list = dao.searchMember1(map);
		
		return list;
	}



	public ArrayList<Member> searchMember2(Member member) {
		ArrayList<Member> list = dao.searchMember2(member);
		
		return list;
	}



	public ArrayList<String> idList() {
		
		return dao.idList();
	}



	public ArrayList<Member> searchMember3(String[] memberId) {
		
		return dao.searchMember3(memberId);
	}



	public ArrayList<Member> searchMember4() {
	
		return dao.searchMember4();
	}


	public Member chkPwMember(Member m) {
		Member member = dao.selectOneMember(m);
		return member;
	}



	public Member findPw(Member m) {
		Member member = dao.findPw(m);
		return member;
	}
}
