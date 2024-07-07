package com.example.dongheemonitor.util;

import com.example.dongheemonitor.config.RiotApiConfig;
import com.example.dongheemonitor.service.RiotApiEndpoints;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.StringJoiner;

@Component
@RequiredArgsConstructor
public class UrlBuilder {

    private final RiotApiConfig riotApiConfig;

    public String buildRiotUrl(RiotApiEndpoints endPoint, Map<String, String> pathParams, Map<String, String> queryParams) {
        String baseUrl = riotApiConfig.getBaseUrl();
        String[] path = pathParams.values().toArray(new String[0]);
        String url = baseUrl + path;

        if (queryParams != null && !queryParams.isEmpty()) {
            StringJoiner query = new StringJoiner("&", "?", "");
            queryParams.forEach((key, value) -> query.add(key + "=" + value));
            url += query.toString();
        }

        return url;
    }
}
