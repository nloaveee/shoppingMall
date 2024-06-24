package com.shop.controller;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.shop.dto.MemberFormDto;
import com.shop.entity.Member;
import com.shop.service.MemberService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MemberController {

	private final MemberService memberService;
	private final PasswordEncoder passwordEncoder;

	// http://localhost:8080/member/new
	@GetMapping("/member/new")
	public String memberForm(Model model) {

		model.addAttribute("memberFormDto", new MemberFormDto());
		return "member/memberForm";
	}

	@PostMapping("/member/new")
	public String insertMember(@Valid MemberFormDto memberFormDto, BindingResult bindingResult, Model model) {

		if (bindingResult.hasErrors()) {
			return "member/memberForm";
		}

		try {

			Member member = Member.createMember(memberFormDto, passwordEncoder);
			memberService.saveMember(member);

		} catch (IllegalStateException e) {
			model.addAttribute("errorMessage", e.getMessage());
			return "member/memberForm";
		}

		return "redirect:/";
	}

	@GetMapping("/member/login")
	public String loginForm() {
		return "member/memberLoginForm";
	}
}
