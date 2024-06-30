package com.example.dongheemonitor.entity;

import lombok.*;
import org.springframework.data.annotation.*;
import org.springframework.data.mongodb.core.mapping.*;

import java.time.*;

@Getter
@Document(collection = "game_commit_alerts")
public class GameCommitAlert {

    @Id
    private String id;

    private String summonnerName;
    private LocalDateTime timestamp;
    private boolean alerted;

    public GameCommitAlert(String summonnerName, LocalDateTime timestamp, boolean alerted) {
        this.summonnerName = summonnerName;
        this.timestamp = timestamp;
        this.alerted = alerted;
    }
}
