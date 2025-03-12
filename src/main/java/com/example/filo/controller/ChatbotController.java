package com.example.filo.controller;

import com.example.filo.api.dto.GroqResponse;
import com.example.filo.service.ChatbotService;
import com.example.filo.service.GroqService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@CrossOrigin(origins = "http://localhost:3939")
@RestController
@RequestMapping("/api")
public class ChatbotController {

    @Value("${groq.api.url}")  // ✅ Load API URL from properties
    private String groqApiUrl;

    @Value("${groq.api.key}")  // ✅ Load API Key from properties
    private String groqApiKey;

    private final RestTemplate restTemplate = new RestTemplate();

    @PostMapping("/chat")
    public ResponseEntity<Map<String, String>> chat(@RequestBody Map<String, String> request) {
        String message = request.get("message");

        if (message == null || message.isEmpty()) {
            return ResponseEntity.badRequest().body(Map.of("error", "Message cannot be empty"));
        }

        // ✅ Call Groq API
        String aiResponse = getAIResponse(message);

        Map<String, String> response = new HashMap<>();
        response.put("reply", aiResponse);

        return ResponseEntity.ok(response);
    }

    private String getAIResponse(String userMessage) {
        // ✅ HTTP Headers
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + groqApiKey);
        headers.set("Content-Type", "application/json");

        // ✅ Request Body
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("model", "llama3-8b-8192");  // ✅ Supported model from Groq API
        requestBody.put("messages", List.of(
                Map.of("role", "system", "content", "You are an AI assistant."),
                Map.of("role", "user", "content", userMessage)
        ));

        HttpEntity<Map<String, Object>> request = new HttpEntity<>(requestBody, headers);

        try {
            ResponseEntity<Map> response = restTemplate.exchange(groqApiUrl, HttpMethod.POST, request, Map.class);
            Map<String, Object> responseBody = response.getBody();

            if (responseBody != null && responseBody.containsKey("choices")) {
                return ((Map<String, String>) ((Map<?, ?>) ((List<?>) responseBody.get("choices")).get(0)).get("message")).get("content");
            }
        } catch (Exception e) {
            return "Error communicating with AI service: " + e.getMessage();
        }

        return "AI did not return a response.";
    }
}