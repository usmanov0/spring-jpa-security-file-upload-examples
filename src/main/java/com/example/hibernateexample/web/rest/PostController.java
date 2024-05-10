package com.example.hibernateexample.web.rest;

import com.example.hibernateexample.entity.Post;
import com.example.hibernateexample.model.PostData;
import com.example.hibernateexample.service.PostDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PostController {

    private final PostDataService postDataService;
    private PostDataService postService;

    @Autowired
    public PostController(PostDataService postService, PostDataService postDataService) {
        this.postService = postService;
        this.postDataService = postDataService;
    }

    @PostMapping("/posts")
    public ResponseEntity create(@RequestBody PostData postData) {
        PostData post = postService.save(postData);
        return ResponseEntity.ok(post);
    }

    @GetMapping("/retrieve-posts")
    public ResponseEntity getAllPosts() {
        Object posts = postService.findAll();
        return ResponseEntity.ok(posts);
    }

    @GetMapping("/posts/params")
    public ResponseEntity getAllByParam(@RequestParam Long postId) {
        List<PostData> result = postService.findAllByQueryParam(postId);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/posts/pageable")
    public ResponseEntity getAllByPageable(Pageable pageable) {
        Page<Post> result = postService.findAll(pageable);
        return ResponseEntity.ok(result);
    }
}
