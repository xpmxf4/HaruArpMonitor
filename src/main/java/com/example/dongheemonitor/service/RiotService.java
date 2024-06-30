package com.example.dongheemonitor.service;

import com.example.dongheemonitor.entity.*;
import com.example.dongheemonitor.repository.GameCommitAlertRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.*;

@Service
@RequiredArgsConstructor
public class RiotService {

    @Value("${riot.api-key}")
    private String riotApiKey;

    @Value("${riot.donghee-puuid}")
    private String dongheePuuid;

    private final RestTemplate restTemplate = new RestTemplate();
    private final GameCommitAlertRepository gameCommitAlertRepository;

    public boolean isDongHeePlaying() {
        String url = String.format("https://api.riotgames.com/lol/spectator/v4/active-games/by-summoner/%s?api_key=%s", dongheePuuid, riotApiKey);
        try {
            String result = restTemplate.getForObject(url, String.class);
            // 게임 중인 상태 기록 저장
            GameCommitAlert alert = new GameCommitAlert("07년생피노키오", LocalDateTime.now(), true);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}