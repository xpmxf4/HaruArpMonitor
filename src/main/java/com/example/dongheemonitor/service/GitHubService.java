package com.example.dongheemonitor.service;

import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
public class GitHubService {

    @Value("${github.commit-url}")
    private String commitUrl;

    private final RestTemplate restTemplate = new RestTemplate();

    public Boolean checkDailyCommits() {
        LocalDate yesterday = LocalDate.now().minusDays(1);
        ResponseEntity<String> response = restTemplate.getForEntity(commitUrl, String.class);

        try {
            JSONArray commits = new JSONArray(response.getBody());
            log.info("Commits array size : " + commits.length());

            Map<String, Boolean> commitStatus = new HashMap<>();
            commitStatus.put("Haru-Lee", false);
            commitStatus.put("Haru-arp", false);

            for (int i = 0; i < commits.length(); i++) {
                JSONObject commit = commits.getJSONObject(i);
                JSONObject commitDetails = commit.getJSONObject("commit").getJSONObject("author");
                String authorName = commitDetails.getString("name");
                String commitDate = commitDetails.getString("date");

                log.info("name : " + authorName + " , date : " + commitDate);
                LocalDate commitLocalDate = LocalDate.parse(commitDate, DateTimeFormatter.ISO_DATE_TIME);
                if (commitLocalDate.isEqual(yesterday)) {
                    commitStatus.computeIfPresent(authorName, (key, value) -> true);
                }
            }
            log.info("Haru-Lee committed today: {}", commitStatus.get("Haru-Lee"));
            log.info("Haru-arp committed today: {}", commitStatus.get("Haru-arp"));

            return commitStatus.containsValue(true);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
