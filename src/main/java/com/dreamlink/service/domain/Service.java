package com.dreamlink.service.domain;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Service {
    private int id;
    private String name;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
