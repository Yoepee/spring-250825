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
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/posts")
public class PostController {
    private final PostService postService;

    @GetMapping("/write")
    public String showWrite() {
        return "post/post/write";
    }

    @GetMapping("/detail/{id}")
    @Transactional(readOnly = true)
    public String showDetail(@PathVariable("id") int id, Model model) {
        Post post = postService.findById(id);
        if (post == null) return null;
        model.addAttribute("post", post);
        return "post/post/detail";
    }

    @PostMapping("/write")
    @Transactional
    public String doWrite(
            @ModelAttribute("form") @Valid PostWriteForm writeForm,
            BindingResult bindingResult,
            Model model
    ) {
        if(bindingResult.hasErrors()) {
            return "post/post/write";
        }
        Post post = postService.write(writeForm.getTitle(), writeForm.getContent());
        model.addAttribute("post", post);

        return "redirect:/posts/detail/%d".formatted(post.getId());
    }
}
