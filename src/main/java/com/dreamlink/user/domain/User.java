package com.dreamlink.user.domain;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class User {
    private int id;
    private String gender;
    private LocalDate birth;
    private String region;
    private String interest;
    private String name;
    private String phone;
    private String loginId;
    private String password;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
