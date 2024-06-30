package com.example.dongheemonitor.entity;

import lombok.*;
import org.springframework.data.annotation.*;
import org.springframework.data.mongodb.core.mapping.*;

import java.time.*;

@Getter
@Document(collection = "daily_commit_records")
public class DailyCommitRecord {

    @Id
    private String id;
    private String author;
    private LocalDateTime timestamp;
    private boolean commited;

    @Builder
    public DailyCommitRecord(String author, LocalDateTime timestamp, boolean commited) {
        this.author = author;
        this.timestamp = timestamp;
        this.commited = commited;
    }
}
