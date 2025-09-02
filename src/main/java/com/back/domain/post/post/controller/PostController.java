package com.back.domain.post.post.controller;

import com.back.domain.member.member.entity.Member;
import com.back.domain.member.member.service.MemberService;
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

import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/posts")
public class PostController {
    private final PostService postService;
    private final MemberService memberService;

    @GetMapping("/write")
    public String showWrite(@ModelAttribute("form") PostWriteForm writeForm) {
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

    @GetMapping("/list")
    public String showList(Model model) {
        List<Post> posts = postService.findAll();
        model.addAttribute("posts", posts);
        return "post/post/list";
    }

    @PostMapping("/write")
    @Transactional
    public String write(
            @ModelAttribute("form") @Valid PostWriteForm writeForm,
            BindingResult bindingResult,
            Model model,
            Principal principal
    ) {
        if(bindingResult.hasErrors()) {
            return "post/post/write";
        }
        String username = principal.getName();
        Member member = memberService.findByUsername(username).orElseThrow(() -> new RuntimeException());
        Post post = postService.write(member, writeForm.getTitle(), writeForm.getContent());
        model.addAttribute("post", post);

        return "redirect:/posts/detail/%d".formatted(post.getId());
    }
}
