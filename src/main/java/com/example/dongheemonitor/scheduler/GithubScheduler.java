package com.example.dongheemonitor.scheduler;

import com.example.dongheemonitor.config.SlackApiConfig;
import com.example.dongheemonitor.dto.GithubCommit;
import com.example.dongheemonitor.entity.DailyCommitHistory;
import com.example.dongheemonitor.repository.DailyCommitHistoryRepository;
import com.example.dongheemonitor.service.GithubService;
import com.example.dongheemonitor.service.SlackService;
import com.slack.api.methods.SlackApiException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.*;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class GithubScheduler {

    private final SlackApiConfig slackApiConfig;
    private final GithubService githubService;
    private final SlackService slackService;
    private final DailyCommitHistoryRepository dailyCommitHistoryRepository;

    @Scheduled(cron = "0 0 0 * * *")
    public void checkCommitsDaily() {
        LocalDate yesterday = LocalDate.now().minusDays(1);
        try {
            List<GithubCommit> commits = githubService.getCommits();
            boolean isCommited = githubService.isCommitOnDate(yesterday, commits);

            dailyCommitHistoryRepository.save(
                    DailyCommitHistory.builder()
                            .author("대-동희")
                            .timestamp(LocalDateTime.now())
                            .commited(isCommited)
                            .build()
            );
        } catch (IOException e) {
            log.error(e.getMessage());
        }

        try {
            slackService.sendMessage(
                    "#일반",
                    "<@%s> 동희님은 오늘 작업을 한개도 안하셨네요...? 하,,,".formatted(slackApiConfig.getDongheeId()
                    )
            );
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }
}
