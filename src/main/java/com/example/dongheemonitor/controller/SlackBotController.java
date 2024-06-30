package com.example.dongheemonitor.controller;

import com.example.dongheemonitor.service.*;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/alarm")
@RequiredArgsConstructor
public class SlackBotController {

    private final SlackService slackService;
    private final GitHubService gitHubService;
    private final RiotService riotService;

    @GetMapping("/slack")
    public String sendAlert() {
        try {
            slackService.sendMessage("#봇테스트", "<@U063VSQ1F4P> 일 해라 노예야.");
            return "사용자 호출 완료!";
        } catch (Exception e) {
            log.error(e.getMessage());
            return "API 호출에 문제가 있습니다 ㅠ";
        }
    }

    @GetMapping("/test1")
    public Boolean githubTest() {
        return gitHubService.checkDailyCommits();
    }

    @GetMapping("/test2")
    public boolean riotTest() {
        return riotService.isDongHeePlaying();
    }

}
