package com.spring.pro30;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
public class MemberController {
	@Autowired
	private MemberService memberService;
	
	@RequestMapping(value="/member/listMembers.do", method=RequestMethod.GET)
	public String listMembers(Model model) {
		List<MemberVO> list = memberService.listMembers();
		model.addAttribute("members", list);
		return "/member/listMembers";
	}

	@RequestMapping(value="/login.do", method=RequestMethod.GET)
	public String login() {
		return "/member/login";
	}

	@RequestMapping(value="/login.do", method=RequestMethod.POST)
	public String loginProcess(HttpSession session, MemberVO vo, RedirectAttributes redirectAttributes) throws Exception {
		String returnURL = "";
		boolean has_login_session = session.getAttribute("login") != null;
		if(has_login_session) {
			session.removeAttribute("login");
		}
		MemberVO vo_ = memberService.loginMember(vo);
		boolean valid_login = vo_ != null;
		if(valid_login) {
			session.setAttribute("login", vo_);
			returnURL = "redirect:/";
		} else {
			String msg = "아이디 혹은 비밀번호가 맞지 않습니다";
			String encoded = URLEncoder.encode(msg, "UTF-8");
			redirectAttributes.addFlashAttribute("message", msg);
			returnURL = "redirect:/login.do";
		}
		return returnURL;
	}

	@RequestMapping(value="/logout.do", method=RequestMethod.GET)
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}

	@RequestMapping(value="/signup.do", method=RequestMethod.GET)
	public String signup() {
		return "/member/signup";
	}

	@RequestMapping(value="/member/signup.do", method=RequestMethod.POST)
	public String signupProcess(@ModelAttribute("mo") MemberVO vo, HttpSession session, RedirectAttributes redirectAttributes) {
		boolean result = memberService.signupMember(vo);
		String nextPage = "redirect:/signup.do";
		if(result) {
			session.setAttribute("login", vo);
			nextPage = "redirect:/";
		} else {
			redirectAttributes.addFlashAttribute("message", "회원가입 실패");
		}
		return nextPage;
	}
}
