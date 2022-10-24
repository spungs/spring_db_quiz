package com.care.db_quiz.service;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.care.db_quiz.dto.MemberDTO;
import com.care.db_quiz.repository.IMemberDAO;

@Service
public class MemberService {
	@Autowired private IMemberDAO dao;
	@Autowired private HttpSession session;
	
	public String register(MemberDTO member) {
		if(member.getId().isEmpty() || member.getPw().isEmpty() 
				|| member.getConfirm().isEmpty() || member.getUsername().isEmpty()) {
			return "모두 입력해주세요.";
		}
		
		if(member.getPw().equals(member.getConfirm()) == false) {
			return "비밀번호가 일치하지 않습니다.";
		}
		if(dao.selectId(member.getId()) != null) {
			return "이미 사용중인 아이디입니다.";
		}
		dao.insert(member);
		return "회원가입 성공";
	}
	
	public String login(MemberDTO member) {
		if(member.getId().isEmpty() || member.getPw().isEmpty()) {
			return "모두 입력해주세요.";
		}
		
		MemberDTO m = dao.selectId(member.getId());
		
		if(m == null || m.getPw().equals(member.getPw()) == false) {
			return "아이디 / 비밀번호를 다시 확인해주세요.";
		}
		
		session.setAttribute("id", member.getId());
		session.setAttribute("username", m.getUsername());
		session.setAttribute("address", m.getAddress());
		session.setAttribute("mobile", m.getMobile());
		return "로그인 성공";
	}
	
	public String update(MemberDTO member) {
		if(member.getPw().isEmpty() || member.getConfirm().isEmpty()
				|| member.getUsername().isEmpty() || member.getAddress().isEmpty()) {
			return "비밀번호와 이름을 모두 입력해주세요. ";
		}
		if(member.getPw().equals(member.getConfirm())==false) {
			return "입력하신 두 비밀번호가 일치하지 않습니다.";
		}
		
		dao.update(member);
		session.invalidate();
		return "업데이트 성공";
	}
	
	public String delete(String pw, String confirm) {
		if(pw.isEmpty() || confirm.isEmpty()) {
			return "모두 입력해주세요";
		}
		MemberDTO m = dao.selectId((String)session.getAttribute("id"));
		if(pw.equals(m.getPw()) == false) {
			return "비밀번호가 틀렸습니다.";
		}
		
		dao.delete(m.getId());
		session.invalidate();
		return "탈퇴 성공";
	}
}
