package com.dreamlink.post.domain;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Post {

    private int id;

    private String name;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
