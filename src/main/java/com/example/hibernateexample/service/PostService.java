package com.example.hibernateexample.service;

import com.example.hibernateexample.entity.Post;
import com.example.hibernateexample.model.PostData;
import com.example.hibernateexample.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class PostService {
    @Autowired
    private final RestTemplate restTemplate;

    private final PostRepository postDataRepository;

    @Autowired
    public PostService(RestTemplate restTemplate, PostRepository postDataService) {
        this.restTemplate = restTemplate;
        this.postDataRepository= postDataService;
    }

    public Post savePost(Post post) {
        return postDataRepository.save(post);
    }

    public List<Post> savePosts(PostData[] posts) {
        List<Post> postDataList = new ArrayList<>();
        for (PostData postData : posts) {
            Post post = new Post();
            post.setPostId(post.getPostId());
            post.setUserId(post.getUserId());
            post.setTitle(postData.getTitle());
            post.setBody(postData.getBody());
            postDataList.add(post);
        }
        return postDataRepository.saveAll(postDataList);
    }

    @Transactional
    public Page<Post> findAll(Pageable pageable) {
        return postDataRepository.findAll(pageable);
    }
}
