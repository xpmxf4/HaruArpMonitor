package com.example.dongheemonitor.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.*;
import java.util.*;

@Slf4j
@Service
public class GitHubService {

    @Value("${github.token}")
    private String githubToken;

    private final RestTemplate restTemplate = new RestTemplate();

    public ResponseEntity<String> getCommitAuthors(String repo, LocalDateTime since) {
        String url = String.format("https://api.github.com/repos/%s/commits?since=%s", repo, since.toString());
        return restTemplate.getForEntity(url, String.class);
    }

    static class Commit {
        private CommitDetails commit;

        public CommitDetails getCommit() {
            return commit;
        }

        public void setCommit(CommitDetails commit) {
            this.commit = commit;
        }
    }

    static class CommitDetails {
        private CommitAuthor author;

        public CommitAuthor getAuthor() {
            return author;
        }

        public void setAuthor(CommitAuthor author) {
            this.author = author;
        }
    }

    static class CommitAuthor {
        private String name;
        private String date;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }
    }
}
