package com.example.dongheemonitor.service;

import com.example.dongheemonitor.entity.DailyCommitRecord;
import com.example.dongheemonitor.repository.DailyCommitRecordRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.objenesis.SpringObjenesis;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class GitHubService {

    @Value("${github.commit-url}")
    private String commitUrl;

    @Value("${slack.donghee-id}")
    private String dongheeId;

    private final RestTemplate restTemplate = new RestTemplate();
    private final DailyCommitRecordRepository dailyCommitRecordRepository;
    private final SlackService slackService;

    @Scheduled(cron = "0 0 0 * * *")
    public Boolean checkDailyCommits() {
        LocalDate yesterday = LocalDate.now().minusDays(1);
        ResponseEntity<String> response = restTemplate.getForEntity(commitUrl, String.class);

        try {
            JSONArray commits = new JSONArray(response.getBody());
            log.info("Commits array size : " + commits.length());

            Map<String, Boolean> commitStatuses = new HashMap<>();
            commitStatuses.put("Haru-Lee", false);
            commitStatuses.put("Haru-arp", false);

            for (int i = 0; i < commits.length(); i++) {
                // TODO : 중간에 커밋이 하나라도 있다면 Loop 중지 로직이 필요

                JSONObject commit = commits.getJSONObject(i);
                JSONObject commitDetails = commit.getJSONObject("commit").getJSONObject("author");
                String authorName = commitDetails.getString("name");
                String commitDate = commitDetails.getString("date");

                LocalDate commitLocalDate = LocalDate.parse(commitDate, DateTimeFormatter.ISO_DATE_TIME);
                if (commitLocalDate.isEqual(yesterday)) {
                    commitStatuses.computeIfPresent(authorName, (key, value) -> true);
                }
            }
            log.info("Haru-Lee committed today: {}", commitStatuses.get("Haru-Lee"));
            log.info("Haru-arp committed today: {}", commitStatuses.get("Haru-arp"));


            boolean commitHappened = commitStatuses.containsValue(true);

            dailyCommitRecordRepository.save(
                    DailyCommitRecord.builder()
                            .author("대-동희")
                            .timestamp(LocalDateTime.now())
                            .commited(commitHappened)
                            .build()
            );

            if (!commitHappened) {
                slackService.sendMessage("#일반", "<@%s> 동희님은 오늘 하나도 작업을 하지시 않았습니다 하,,,".formatted(dongheeId));
            }

            return commitHappened;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
