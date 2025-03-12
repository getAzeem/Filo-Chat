package com.example.filo.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.io.entity.StringEntity;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Service
public class GroqService {
    @Value("${groq.api.key}")
    private String groqApiKey;

    private static final String API_URL = "https://api.groq.com/openai/v1/chat/completions";
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public String getGroqResponse(String userMessage) {
        try (CloseableHttpClient client = HttpClients.createDefault()) {
            HttpPost request = new HttpPost(API_URL);
            request.addHeader("Authorization", "Bearer " + groqApiKey);
            request.addHeader("Content-Type", "application/json");

            // Create request body
            String requestBody = "{"
                    + "\"model\": \"llama3-70b\","
                    + "\"messages\": [{\"role\": \"user\", \"content\": \"" + userMessage + "\"}]"
                    + "}";

            request.setEntity(new StringEntity(requestBody, StandardCharsets.UTF_8));

            // Send request
            try (CloseableHttpResponse response = client.execute(request)) {
                JsonNode jsonResponse = objectMapper.readTree(response.getEntity().getContent());
                return jsonResponse.get("choices").get(0).get("message").get("content").asText();
            }
        } catch (IOException e) {
            return "Error: Unable to fetch response from Groq.";
        }
    }
}
