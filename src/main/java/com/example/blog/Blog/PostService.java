package com.example.blog.Blog;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final PostMapper postMapper;

    public List<PostResponse> getPosts() {
        return postRepository.findAll()
                .stream()
                .map(postMapper::toResponse)
                .toList();
    }

    // public string store()
    // public string update()
    // public string destroy()
}
