package com.back.domain.post.post.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class PostWriteForm {
    @NotBlank
    @Size(min=2, max=20)
    String title;

    @NotBlank
    @Size(min=2, max=100)
    String content;
}
