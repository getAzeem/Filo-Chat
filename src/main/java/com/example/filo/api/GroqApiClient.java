package com.example.filo.api;

import com.example.filo.api.dto.GroqRequest;
import com.example.filo.api.dto.GroqResponse;
import com.example.filo.config.GroqConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class GroqApiClient {
    @Autowired
    private final RestTemplate restTemplate;
    private final GroqConfig groqConfig;

    public GroqApiClient(RestTemplate restTemplate, GroqConfig groqConfig) {
        this.restTemplate = restTemplate;
        this.groqConfig = groqConfig;
    }

    public GroqResponse sendMessage(GroqRequest request) {
        String url = groqConfig.getApiUrl();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + groqConfig.getApiKey());

        HttpEntity<GroqRequest> entity = new HttpEntity<>(request, headers);
        ResponseEntity<GroqResponse> response = restTemplate.exchange(url, HttpMethod.POST, entity, GroqResponse.class);

        return response.getBody();
    }
}
