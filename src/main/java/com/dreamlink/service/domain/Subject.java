package com.dreamlink.service.domain;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Subject {
    private int id;
    private int welfareId;
    private int serviceListId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
