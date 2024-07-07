package com.example.dongheemonitor.service;

import com.example.dongheemonitor.config.RiotApiConfig;
import com.example.dongheemonitor.entity.*;
import com.example.dongheemonitor.repository.LolPlayingAlertHistoryRepository;
import com.example.dongheemonitor.util.UrlBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.*;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class RiotService {

    private final RiotApiConfig riotApiConfig;
    private final UrlBuilder urlBuilder;
    private final RestTemplate restTemplate;
    private final LolPlayingAlertHistoryRepository lolPlayingAlertHistoryRepository;

    public boolean isDongHeePlaying() {
        Map<String, String> pathParams = new HashMap<>();
        pathParams.put("summonerId", riotApiConfig.getDongheePuuid());

        Map<String, String> queryParams = new HashMap<>();
        queryParams.put("api_key", riotApiConfig.getApiKey());

        String url = urlBuilder.buildRiotUrl(RiotApiEndpoints.ACTIVE_GAMES, pathParams, queryParams);
        try {
            String result = restTemplate.getForObject(url, String.class);
            // 게임 중인 상태 기록 저장
            LolPlayingAlertHistory alert = new LolPlayingAlertHistory("07년생피노키오", LocalDateTime.now(), true);
            lolPlayingAlertHistoryRepository.save(alert);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}