package com.back.domain.post.post.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record PostWriteForm(
        @NotBlank(message = "제목을 입력해주세요.")
        @Size(min = 2, max = 20, message = "제목은 2 ~ 20자 이내로 작성해주세요.")
        String title,

        @NotBlank(message = "내용을 입력해주세요.")
        @Size(min = 2, max = 100, message = "내용은 2 ~ 100자 이내로 작성해주세요.")
        String content
) {
}
