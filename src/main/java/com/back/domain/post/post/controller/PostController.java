package com.back.domain.post.post.controller;

import com.back.domain.post.post.entity.Post;
import com.back.domain.post.post.service.PostService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
@RequestMapping("/posts")
public class PostController {
    private final PostService postService;

    @GetMapping("/write")
    public String write() {
        return "post/post/write";
    }

    @AllArgsConstructor
    @Getter
    public static class WriteForm {
        @NotBlank
        @Size(min=2, max=20)
        String title;

        @NotBlank
        @Size(min=2, max=100)
        String content;
    }

    @PostMapping("/doWrite")
    @Transactional
    public String write(
            @Valid WriteForm writeForm,
            BindingResult bindingResult,
            Model model
    ) {
        System.out.println(bindingResult.hasErrors());
        if(bindingResult.hasErrors()) {
            String errorMessage = bindingResult
                    .getFieldErrors()
                    .stream()
                    .map(FieldError::getDefaultMessage)
                    .collect(Collectors.joining("\n"));
            model.addAttribute("errorMessage", errorMessage);
            return "post/post/write";
        }
        Post post = postService.write(writeForm.getTitle(), writeForm.getContent());
        model.addAttribute("post", post);

        return "post/post/list";
    }
}
