package com.back.domain.member.member.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class LoginForm {
    @Size(min=3, max=25)
    @NotBlank(message="아이디는 필수 입니다.")
    private String username;
    @NotBlank(message="비밀번호는 필수 입니다.")
    private String password;
}
