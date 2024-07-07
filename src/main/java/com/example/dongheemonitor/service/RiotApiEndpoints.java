package com.example.dongheemonitor.service;

import lombok.Getter;

@Getter
public enum RiotApiEndpoints {
    ACTIVE_GAMES("spectator/v4/active-games/by-summoner/%s");

    private final String path;

    RiotApiEndpoints(String path) {
        this.path = path;
    }
}
