package com.example.dongheemonitor.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class GithubCommit {

    private Commit commit;

    @Getter
    @Setter
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Commit {
        private Author author;
        private Commiter commiter;
        private String message;


        @Getter
        @Setter
        @JsonIgnoreProperties(ignoreUnknown = true)
        public static class Author {
            private String name;
            private String date;
        }

        @Getter
        @Setter
        @JsonIgnoreProperties(ignoreUnknown = true)
        public static class Commiter {
            private String name;
            private String date;
        }
    }
}
