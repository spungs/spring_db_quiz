package com.care.db_quiz.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.care.db_quiz.dto.MemberDTO;
import com.care.db_quiz.repository.IMemberDAO;
import com.care.db_quiz.service.MemberService;


@Controller
public class MemberController {
	@Autowired private IMemberDAO dao;
	@Autowired private MemberService service;
	@Autowired private HttpSession session;
	
	@RequestMapping("header")
	public String header() {
		return "default/header";
	}
	@RequestMapping("main")
	public String main() {
		return "default/main";
	}
	@RequestMapping("footer")
	public String footer() {
		return "default/footer";
	}
	@RequestMapping("index")
	public String index() {
		return "member/index";
	}
	@GetMapping("register")
	public String register() {
		return "member/register";
	}
	@GetMapping("login")
	public String login() {
		return "member/login";
	}
	
	
	@PostMapping("register")
	public String register(MemberDTO member, RedirectAttributes ra) {
		
		String result = service.register(member);
		//회원가입 실패 시
		if(result.equals("회원가입 성공") == false) {
			ra.addFlashAttribute("msg", result);
			return "redirect:index";
		}
		//회원가입 성공 시
		ra.addFlashAttribute("msg", result);
		return "redirect:index";
	}
	
	@PostMapping("login")
	public String login(MemberDTO member, RedirectAttributes ra, Model model) {
		
		String result = service.login(member);
		//로그인 실패 시
		if(result.equals("로그인 성공") == false) {
			model.addAttribute("msg", result);
			return "redirect:login";
		}
		//로그인 성공 시
		
		ra.addFlashAttribute("msg", result);
		return "redirect:index";
	}
	
	@GetMapping("logout")
	public String logout(RedirectAttributes ra) {
		//session에 id가 없다면(비정상적으로 접근했다면)
		if(session.getAttribute("id") == null) {
			ra.addFlashAttribute("msg","로그인 후 이용해주세요.");
			return "redirect:index"; 
		}
		//로그아웃 성공 시
		session.invalidate();
		ra.addFlashAttribute("msg","로그아웃 완료");
		return "redirect:index"; 
	}
	
	@GetMapping("memberInfo")
	public String memberInfo(Model model) {
		//model에 list를 담아서 return
		model.addAttribute("members", dao.list());
		return "member/memberInfo";
	}
	
	@GetMapping("userInfo")
	public String userInfo(String id, Model model) {
		//model에 user정보를 담아서 return
		model.addAttribute("member", dao.selectId(id));
		return "member/userInfo";
	}
	
	@GetMapping("update")
	public String update(HttpSession session, String id) {
		String sessionId = (String) session.getAttribute("id");
		if(sessionId == null || sessionId.isEmpty()) {
			return "redirect:login";
		}
		if(sessionId.equals(id) == false) {
			return "redirect:index";
		}
		return "member/update";
	}
	
	@PostMapping("update")
	public String update(MemberDTO member, RedirectAttributes ra, Model model) {
		String result = service.update(member);
		//업데이트 실패 시 새로고침
		if(result.equals("업데이트 성공") == false) {
			model.addAttribute("msg", result);
			return "member/update";
		}
		
		//업데이트 성공 시 index로 이동
		ra.addFlashAttribute("msg", result);
		return "redirect:index";
	}
	
	@GetMapping("delete")
	public String delete(HttpSession session, String id) {
		String sessionId = (String) session.getAttribute("id");
		if(sessionId == null || sessionId.isEmpty()) {
			return "redirect:login";
		}
		if(sessionId.equals(id) == false) {
			return "redirect:index";
		}
		return "member/delete";
	}
	
	@PostMapping("delete")
	public String delete(String pw, String confirm, RedirectAttributes ra, Model model) {
		String result = service.delete(pw, confirm);
		// 탈퇴 실패시 새로고침
		if(result.equals("탈퇴 성공") == false) {
			model.addAttribute("msg", result);
			return "member/delete";
		}
		// 탈퇴 성공시 index로 이동
		ra.addFlashAttribute("msg", result);
		return "redirect:index";
		
	}
}
