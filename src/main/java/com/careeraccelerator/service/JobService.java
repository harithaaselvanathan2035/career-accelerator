package com.careeraccelerator.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Service
@RequiredArgsConstructor
public class JobService {

    @Value("${rapidapi.key}")
    private String apiKey;

    private static final String API_HOST =
            "jsearch.p.rapidapi.com";

    public String searchJobs(
            String query,
            String location) {

        try {

            String searchQuery;

            if(location == null ||
                    location.isBlank()) {

                searchQuery =
                        URLEncoder.encode(
                                query,
                                StandardCharsets.UTF_8
                        );

            } else {

                searchQuery =
                        URLEncoder.encode(
                                query + " in " + location,
                                StandardCharsets.UTF_8
                        );
            }

            String url =
                    "https://jsearch.p.rapidapi.com/search"
                            + "?query=" + searchQuery
                            + "&page=1"
                            + "&num_pages=1";

            HttpHeaders headers =
                    new HttpHeaders();

            headers.set(
                    "X-RapidAPI-Key",
                    apiKey
            );

            headers.set(
                    "X-RapidAPI-Host",
                    API_HOST
            );

            HttpEntity<String> entity =
                    new HttpEntity<>(headers);

            RestTemplate restTemplate =
                    new RestTemplate();

            ResponseEntity<String> response =
                    restTemplate.exchange(
                            url,
                            HttpMethod.GET,
                            entity,
                            String.class
                    );

            return response.getBody();

        } catch (Exception e) {

            e.printStackTrace();

            return """
                    {
                      "status":"ERROR",
                      "message":"Unable to fetch jobs"
                    }
                    """;
        }
    }
}