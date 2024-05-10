package com.example.hibernateexample.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostData {
    private Long id;
    private Long userId;
    private String title;
    private String body;
}
