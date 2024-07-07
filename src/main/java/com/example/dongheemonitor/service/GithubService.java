package com.example.dongheemonitor.service;

import com.example.dongheemonitor.config.GithubApiConfig;
import com.example.dongheemonitor.dto.GithubCommit;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class GithubService {

    private final GithubApiConfig githubApiConfig;
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public List<GithubCommit> getCommits() throws IOException {
        ResponseEntity<String> commitApiResponse
                = restTemplate.getForEntity(githubApiConfig.getCommitUrl(), String.class);
        return objectMapper.readValue(
                commitApiResponse.getBody(),
                new TypeReference<>() {
                }
        );
    }

    public boolean isCommitOnDate(LocalDate date, List<GithubCommit> commits) {
        for (GithubCommit commit : commits) {
            String authorName = commit.getCommit().getAuthor().getName();
            LocalDate commitDate = LocalDate.parse(commit.getCommit().getAuthor().getDate());
            if (commitDate.isEqual(date)) {
                return true;
            }
        }
        return false;
    }
}
