package com.dreamlink.welfare.domain;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Welfare {
    private int id;
    private String entity;
    private String title;
    private String department;
    private String phoneCall;
    private String applicant;
    private String service;
    private String book;
    private String addition;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
