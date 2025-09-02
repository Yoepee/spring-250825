package com.back.domain.member.member.controller;

import com.back.domain.member.member.dto.RegisterForm;
import com.back.domain.member.member.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {
    private final MemberService memberService;

    @GetMapping("/login")
    public String join(Model model) {
        return "member/member/login";
    }

    @GetMapping("/register")
    public String showRegister(RegisterForm registerForm) {
        return "member/member/register";
    }

    @PostMapping("/register")
    public String register(@Valid RegisterForm registerForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "member/member/register";
        }
        System.out.println("registerForm"+registerForm);

        return "post/post/list";
    }
}
