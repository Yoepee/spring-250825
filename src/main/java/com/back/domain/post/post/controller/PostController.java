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
        return getWriteFormHtml("", "", "");
    }

    @PostMapping("/doWrite")
    @ResponseBody
    @Transactional
    public String write(
            @RequestParam(defaultValue = "") String title,
            @RequestParam(defaultValue = "") String content
    ) {
        if (title.isBlank()) return getWriteFormHtml("제목을 입력해주세요.", title, content);
        if (content.isBlank()) return getWriteFormHtml("내용을 입력해주세요.", title, content);
        Post post = postService.write(title, content);

        return "%d번 글이 생성되었습니다.".formatted(post.getId());
    }

    private String getWriteFormHtml(String errorMessage, String title, String content){
        return """
                <div style="color:red;">%s</div>
                <form action="doWrite" method="post">
                  <input type="text" name="title" placeholder="제목" value="%s" autofocus>
                  <br>
                  <textarea name="content" placeholder="내용">%s</textarea>
                  <br>
                  <input type="submit" value="작성">
                </form>
                """.formatted(errorMessage, title, content);
    }
}
