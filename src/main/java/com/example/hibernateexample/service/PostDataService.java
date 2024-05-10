package com.example.hibernateexample.service;

import com.example.hibernateexample.entity.Post;
import com.example.hibernateexample.model.PostData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PostDataService {
    private final RestTemplate restTemplate;
    private final PostService postService;

    @Autowired
    public PostDataService(RestTemplate restTemplate, PostService postService) {
        this.restTemplate = restTemplate;
        this.postService = postService;
    }

    @Value("${api.jsonplaceholder}")
    private String api;

    public PostData save(PostData postData) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<PostData> entity = new HttpEntity<>(postData, headers);
        PostData result = restTemplate.postForEntity(api+ "/posts", entity, PostData.class).getBody();
        return result;
    }

    public PostData update(Long id,PostData postData) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<PostData> entity = new HttpEntity<>(postData, headers);
        PostData result = restTemplate.postForEntity(api+ "/posts/",+id +"/comments"+entity, PostData.class).getBody();
        return result;
    }

    public Object findAll() {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<PostData[]> httpEntity = new HttpEntity<>(headers);
        PostData[] result = restTemplate.getForObject(this.api+"/posts", PostData[].class, httpEntity);
        postService.savePosts(result);
        return result;
    }

    public List<PostData> findAllByQueryParam(Long postId){
        HttpEntity<List<PostData>> httpEntity = new HttpEntity<>(getHeader());
        String urlTemplate = UriComponentsBuilder.fromHttpUrl(this.api + "/comments")
                .queryParam("postId", "{postId}")
                .encode()
                .toUriString();
        Map<String, Object> params = new HashMap<>();
        params.put("postId", postId);

        List<PostData> result = restTemplate.exchange(
                urlTemplate,
                HttpMethod.GET,
                httpEntity,
                List.class,
                params).getBody();
        return result;
    }

    public HttpHeaders getHeader(){
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        return headers;
    }

    public Page<Post> findAll(Pageable pageable) {
        return postService.findAll(pageable);
    }
}
