package com.back.domain.post.post.controller;

import com.back.domain.post.post.dto.PostWriteForm;
import com.back.domain.post.post.entity.Post;
import com.back.domain.post.post.service.PostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
@RequestMapping("/posts")
public class PostController {
    private final PostService postService;

    @GetMapping("/write")
    public String showWrite() {
        return "post/post/write";
    }

    @PostMapping("/write")
    @Transactional
    public String doWrite(
            @ModelAttribute("form") @Valid PostWriteForm writeForm,
            BindingResult bindingResult,
            Model model
    ) {
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
