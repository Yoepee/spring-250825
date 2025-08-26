package com.back.domain.post.post.controller;

import com.back.domain.post.post.entity.Post;
import com.back.domain.post.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/posts")
public class PostController {
    private final PostService postService;

    @GetMapping("/write")
    @ResponseBody
    public String write() {
        return """
                <form action="doWrite" target="_blank" method="post">
                  <input type="text" name="title" placeholder="제목" value="안녕">
                  <br>
                  <textarea name="content" placeholder="내용">내용</textarea>
                  <br>
                  <input type="submit" value="작성">
                </form>
                """;
    }

    @PostMapping("/doWrite")
    @ResponseBody
    @Transactional
    public String write(
            @RequestParam(defaultValue = "") String title,
            @RequestParam(defaultValue = "") String content
    ) {
        Post post = postService.write(title, content);

        return "%d번 글이 생성되었습니다.".formatted(post.getId());
    }
}
