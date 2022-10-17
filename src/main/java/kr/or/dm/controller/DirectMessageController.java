package kr.or.dm.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

import kr.or.dm.model.service.DirectMessageService;
import kr.or.dm.model.vo.DirectMessage;

@Controller
public class DirectMessageController {

	@Autowired
	private DirectMessageService service;
	
	@RequestMapping(value="/dmMain.do")
	public String dmMain() {
		return "dm/dmMain";
	}
	@RequestMapping(value="/dmMain2.do")
	public String dmMain2() {
		return "dm/dmMain2";
	}
	
	@ResponseBody
	@RequestMapping(value="/sendDm.do")
	public String sendDm(DirectMessage dm) {
		int result = service.sendDm(dm);
		return String.valueOf(result);
	}
	

	@ResponseBody
	@RequestMapping(value="/getAllDm.do",produces = "application/json;charset=utf-8")
	public String getSendDm(DirectMessage dm) {
		ArrayList<DirectMessage> list = service.getReceiveDm(dm);
		
		return new Gson().toJson(list);
	}
	
	@ResponseBody
	@RequestMapping(value="/dmDetail.do",produces = "application/json;charset=utf-8")
	public String dmDetail(int dmNo) {
		DirectMessage dm = service.selectOneDm(dmNo);
		return new Gson().toJson(dm);
	}
}
