package com.back.domain.member.member.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class RegisterForm {
    @Size(min=3, max=25)
    @NotBlank(message="아이디는 필수 입니다.")
    private String username;
    @NotBlank(message="비밀번호는 필수 입니다.")
    private String password;
    @NotBlank(message="비밀번호는 필수 입니다.")
    private String password_conform;
    @Email
    @NotBlank(message="이메일은 필수 입니다.")
    private String email;
}
