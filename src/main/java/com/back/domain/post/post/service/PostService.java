package com.back.domain.post.post.service;

import com.back.domain.member.member.entity.Member;
import com.back.domain.post.post.entity.Post;
import com.back.domain.post.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    public long count() {
        return postRepository.count();
    }

    public Post write(Member author, String title, String content) {
        Post post = new Post(author, title, content);
        return postRepository.save(post);
    }

    public Post findById(int id) {
        return postRepository.findById(id).orElseThrow(RuntimeException::new);
    }

    public List<Post> findAll() {
        return postRepository.findAll();
    }

    public void delete(Post post) {
        postRepository.delete(post);
    }
}
