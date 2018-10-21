package com.step.forum.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Comment {

    private int id;
    private String description;
    private LocalDateTime writeDate;
    private Topic topic;
    private User user;

}
