package com.example.dongheemonitor.entity;

import lombok.*;
import org.springframework.data.annotation.*;
import org.springframework.data.mongodb.core.mapping.*;

import java.time.*;

@Getter
@Document(collection = "api_call_records_by_people")
public class ApiCallRecord {

    @Id
    private String id;

    private String endpoint;
    private LocalDateTime timestamp;

    public ApiCallRecord(String endpoint, LocalDateTime timestamp) {
        this.endpoint = endpoint;
        this.timestamp = timestamp;
    }
}
