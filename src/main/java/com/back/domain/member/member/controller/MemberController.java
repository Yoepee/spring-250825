package com.back.domain.member.member.controller;

import com.back.domain.member.member.dto.LoginForm;
import com.back.domain.member.member.dto.RegisterForm;
import com.back.domain.member.member.entity.Member;
import com.back.domain.member.member.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {
    private final MemberService memberService;

    @GetMapping("/login")
    public String showLogin(LoginForm loginForm) {
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
        if (!registerForm.getPassword().equals(registerForm.getPassword_conform())) {
            bindingResult.rejectValue("password","checkedPassword","비밀번호가 일치하지 않습니다.");
            return "member/member/register";
        }

        Optional<Member> opMember = memberService.findByUsername(registerForm.getUsername());
        if (opMember.isPresent()) {
            bindingResult.rejectValue("username","checkedMemberByUsername","이미 존재하는 계정입니다.");
            return "member/member/register";
        }

        try {
            memberService.create(registerForm.getUsername(), registerForm.getPassword(), registerForm.getEmail());
        }catch (DataIntegrityViolationException e){
            e.printStackTrace();
            bindingResult.reject("registerError",e.getMessage());
            return  "member/member/register";
        }catch (Exception e) {
            e.printStackTrace();
            bindingResult.reject("registerError", e.getMessage());
            return "member/member/register";
        }

        return "post/post/list";
    }
}
