package com.example.dongheemonitor.service;

import com.example.dongheemonitor.entity.*;
import com.example.dongheemonitor.repository.*;
import com.slack.api.*;
import com.slack.api.methods.*;
import com.slack.api.methods.request.chat.*;
import com.slack.api.methods.response.chat.*;
import lombok.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.*;

import java.io.*;
import java.time.*;

@Service
@RequiredArgsConstructor
public class SlackService {

    @Value("${slack.bot-token}")
    private String botToken;

    private final ApiCallRecordRepository apiCallRecordRepository;
    private final Slack slack = Slack.getInstance();

    public void sendMessage(String channel, String text) throws IOException, SlackApiException {
        ChatPostMessageResponse response = slack.methods(botToken).chatPostMessage(req -> req
                .channel(channel)
                .text(text)
        );
        if (!response.isOk()) {
            throw new RuntimeException("Slack API error: " + response.getError());
        }

        // API 호출 기록 저장
        ApiCallRecord record = new ApiCallRecord("sendMessage", LocalDateTime.now());
        apiCallRecordRepository.save(record);
    }
}
