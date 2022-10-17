package kr.or.member.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

import kr.or.common.MailSender;
import kr.or.member.model.service.MemberService;
import kr.or.member.model.vo.Member;

@Controller
public class MemberController {

	@Autowired
	private MemberService service;
	
	@RequestMapping(value="/login.do")
	public String login(Member member, HttpSession session)throws NullPointerException{
		Member m = service.selectOneMember(member);
		if(m!=null) {
			session.setAttribute("m", m);
		}
		
		return"redirect:/";
	}
	
	@RequestMapping(value = "/selectAllMember.do")
	public String selectAllMember(Model model) {
		ArrayList<Member> list = service.selectAllMember();
		model.addAttribute("list",list);
		return "member/memberList";
	}
	
	@RequestMapping(value="/joinFrm.do")
	public String joinFrm() {
		return "member/joinFrm";
	}
	
	@RequestMapping(value="/join.do")
	public String join(Member m) {
		int result = service.insertMember(m);
		if(result>0) {
			return "redirect:/";
		}else {
			return "member/joinFrm";
		}
	}
	
	@RequestMapping(value="/logout.do")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}
	
	@RequestMapping(value="/searchMemberId.do")
	public String serarchMemberId(Member member,Model model) {
		Member m = service.selectOneMember(member);
		if(m!=null) {
			model.addAttribute("m",m);
			return "member/searchMember";
		}else {
			return "redirect:/";
		}
	}
	
	@RequestMapping(value="/mypage.do")
	public String mypage() {
		return "member/mypage";
	}
	
	@RequestMapping(value="/update.do")
	public String updateMember(Member m,HttpSession session) {
		int result = service.updateMember(m);
		if(result>0) {
			Member member = (Member)session.getAttribute("m");
			member.setMemberPw(m.getMemberPw());
			member.setPhone(m.getPhone());
			member.setEmail(m.getEmail());
			return "redirect:/mypage.do";
		}else {
			return "redirect:/";
		}
	}
	
	@RequestMapping(value="/deleteMember.do")
	public String deleteMember(int memberNo) {
		int result = service.deleteMember(memberNo);
		if(result>0) {
			return "redirect:/logout.do";
		}else {
			return "redirect:/";
		}
	}
	
	@RequestMapping(value = "/searchMemberName.do")
	public String searchMemberName(String memberName,Model model) {
		ArrayList<Member> list = service.searchMemberName(memberName);
		model.addAttribute("list",list);
		return "member/memberList";
	}
	
	@RequestMapping(value = "/searchMember1.do")
	public String searchMember1(String type,String keyword,Model model) {
		ArrayList<Member> list = service.searchMember1(type,keyword);
		model.addAttribute("list",list);
		return "member/memberList";
	}
	
	@RequestMapping(value = "/searchMember2.do")
	public String searchMember2(Member member,Model model) {
		ArrayList<Member> list = service.searchMember2(member);
		model.addAttribute("list",list);
		return "member/memberList";
	}
	
	@RequestMapping(value = "/idList.do")
	public String idList(Model model) {
		ArrayList<String> list = service.idList();
		model.addAttribute("list",list);
		return "/member/idList";
	}
	
	@RequestMapping(value = "/searchMember3.do")
	public String searchMember3(String[] memberId, Model model) {
		ArrayList<Member> list = service.searchMember3(memberId);		
		model.addAttribute("list",list);
		return "/member/memberList";
	}
	
	@RequestMapping(value="/searchMember4.do")
	public String searchMember4(Model model) {
		ArrayList<Member>list = service.searchMember4();
		model.addAttribute("list",list);
		return "/member/memberList";
	}
	
	@ResponseBody
	@RequestMapping(value="/idCheck.do")
	public String idCheck(Member member) {
		Member m = service.selectOneMember(member);
		if(m==null) {
			return "0";
		}else {
			return "1";
		}
	}
	
	@ResponseBody
	@RequestMapping(value="/ajaxAllMember.do",produces = "application/json;charset=utf-8")
	public String ajaxAllMember() {
		ArrayList<Member> list = service.selectAllMember();
			Gson gson = new Gson();
			String result = gson.toJson(list);
			return result;	
	}
	
	@RequestMapping(value="/changePwFrm.do")
	public String changePwFrm() {
		return "member/changePwFrm";
	}
	
	@ResponseBody
	@RequestMapping(value="/chkPw.do")
	public String chkPw(Member m) {
		Member member = service.chkPwMember(m);
		if(member==null) {
			return "0";
		}else {
			return "1";
		}
	}
	
	@ResponseBody
	@RequestMapping(value="/chnagePw.do")
	public String changePw(Member m) {
		int result = service.updateMember(m);
		if(result==1) {
			return "1";
		}else {
			return "0";
		}
	}
	
	@RequestMapping(value="/findPwFrm.do")
	public String findPwFrm() {
		return "member/findPwFrm";
	}
	@RequestMapping(value="/findPwFrm2.do")
	public String findPwFrm2() {
		return "member/findPwFrm2";
	}
	
	@ResponseBody
	@RequestMapping(value="/findPw.do")
	public Object findPw(HttpSession session, Member m) {
		Member member = service.findPw(m);
		Gson gson = new Gson();
		if(member != null) {
			MailSender sender = new MailSender();
			String memberPw = member.getMemberPw();
			String randomCode = sender.sendMail2(member.getEmail());
			Member mem = new Member();
			
			mem.setMemberId(member.getMemberId());
			mem.setMemberPw(memberPw);
			mem.setRandomCode(randomCode);
			System.out.println(mem);
			session.setAttribute("m", mem);
			return gson.toJson(mem);
		} else {
			return gson.toJson(null);

		}
	}
	
	
	@ResponseBody
	@RequestMapping(value="/findPw2.do")
	public Object findPw2(HttpSession session, Member m) {
		Member member = service.findPw(m);
		Gson gson = new Gson();
		if(member != null) {
			MailSender sender = new MailSender();
			String memberPw = member.getMemberPw();
			String randomCode = sender.sendMail2(member.getEmail());
			Member mem = new Member();
			
			mem.setMemberId(member.getMemberId());
			mem.setMemberPw(memberPw);
			mem.setRandomCode(randomCode);
			System.out.println(mem);
			session.setAttribute("m", mem);
			return gson.toJson(mem);
		} else {
			return gson.toJson(null);

		}
	}
	
	@RequestMapping(value="/allMemberChat.do")
	public String allMemberChat() {
		return "member/allChat";
	}
	
	@ResponseBody
	@RequestMapping(value="/selectAllMemberId.do",produces = "application/json;charset=utf-8")
	public String selectAllMemberId() {
		ArrayList<String> list = service.idList();
		return new Gson().toJson(list);
	}
}
