package com.jerezm.practice.model;

import lombok.Getter;

import java.util.Date;

@Getter
public class Item {
    private Long id;
    private String content;
    private boolean isDone;
    private Date createdAt;
    private Date updatedAt;
}
